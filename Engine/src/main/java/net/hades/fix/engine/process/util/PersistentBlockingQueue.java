/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * PersistentBlockingQueue.java
 *
 * $Id: PersistentBlockingQueue.java,v 1.3 2010-06-27 02:46:08 vrotaru Exp $
 */
package net.hades.fix.engine.process.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * Persistent blocking queue implementation using files.
 *
 * @param <E> type of stored elements
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.3 $
 */
public class PersistentBlockingQueue<E> extends LinkedBlockingQueue<E> {

    private static final byte START_MSG = 2;        // STX
    private static final byte END_MSG = 3;          // ETX
    private static final byte START_LEN = 17;       // DS1
    private static final byte END_LEN = 18;         // DS2
    private static final byte START_DATA = 19;      // DS3
    private static final byte END_DATA = 20;        // DS4

    private static final long serialVersionUID = 1L;
    
    private RandomAccessFile dataFile;
    private RandomAccessFile readPosFile;
    private FileChannel dataChannel;
    private long currReadPos;

    private final ReentrantLock fileLock = new ReentrantLock();
    private final Condition notEmpty = fileLock.newCondition();

    public PersistentBlockingQueue(String path, String file) throws IOException, BadMessageStreamException {
        super();
        initialise(path, file);
    }

    @Override
    public void put(E element) {
        if (element == null) {
            Logger.getLogger(PersistentBlockingQueue.class.getName()).log(Level.WARNING, "Cannot put null in the queue");
            return;
        }
//        FileLock fileLock = null;
        final ReentrantLock lock = this.fileLock;
        try {
            lock.lockInterruptibly();
//            fileLock = dataChannel.lock();
            byte[] data = (byte[]) element;
            if (dataFile.length() > 0) {
                dataFile.seek(dataFile.length());
            }
            dataFile.writeByte(START_MSG);
            dataFile.writeByte(START_LEN);
            dataFile.writeInt(data.length);
            dataFile.writeByte(END_LEN);
            dataFile.writeByte(START_DATA);
            for (int i = 0; i < data.length; i++) {
                dataFile.writeByte(data[i]);
            }
            dataFile.writeByte(END_DATA);
            dataFile.writeByte(END_MSG);
            dataChannel.position(getCurrReadPos());
            dataChannel.force(true);
        } catch (InterruptedException ex) {
            Logger.getLogger(PersistentBlockingQueue.class.getName()).log(Level.SEVERE, "Put interrupted", ex);
        } catch (IOException ex) {
            Logger.getLogger(PersistentBlockingQueue.class.getName()).log(Level.SEVERE, "Could not lock the data file channel", ex);
        } finally {
            notEmpty.signal();
            lock.unlock();
//            try {
//                if (fileLock != null) {
//                    fileLock.release();
//                }
//            } catch (IOException ex) {
//                Logger.getLogger(PersistentBlockingQueue.class.getName()).log(Level.SEVERE, "Could not release file lock", ex);
//            }
        }
    }

    @Override
    public E take() throws InterruptedException {
        E result = null;
//        FileLock fileLock = null;
        final ReentrantLock lock = this.fileLock;

        try {
            try {
                Logger.getLogger(PersistentBlockingQueue.class.getName()).log(Level.INFO, "channel size=" + dataChannel.size() +
                        ", currentPosition=" + getCurrReadPos());
                lock.lockInterruptibly();
                while (dataChannel.size() == getCurrReadPos() || dataChannel.size() == 0) {
                    notEmpty.await();
                }
            } catch (InterruptedException ie) {
                notEmpty.signal(); // propagate to a non-interrupted thread
                throw ie;
            }
            
//            fileLock = dataChannel.lock();
            dataChannel.position(getCurrReadPos());
            byte msgByte = dataFile.readByte();
            if (msgByte != START_MSG) {
                throw new BadMessageStreamException("The message does not start with a [2] start message byte.");
            }
            msgByte = dataFile.readByte();
            if (msgByte != START_LEN) {
                throw new BadMessageStreamException("The message does not have a [17] start data length byte.");
            }
            int dataLen = dataFile.readInt();
            msgByte = dataFile.readByte();
            if (msgByte != END_LEN) {
                throw new BadMessageStreamException("The message does not have a [19=8] end data length byte.");
            }
            msgByte = dataFile.readByte();
            if (msgByte != START_DATA) {
                throw new BadMessageStreamException("The message does not have a [19] start data byte.");
            }
            ByteArrayOutputStream bao = new ByteArrayOutputStream();
            int i = 0;
            while (i < dataLen) {
                bao.write(dataFile.readByte());
                i++;
            }
            result = (E) bao.toByteArray();
            msgByte = dataFile.readByte();
            if (msgByte != END_DATA) {
                throw new BadMessageStreamException("The message does not ends with a [20] end data byte.");
            }
            msgByte = dataFile.readByte();
            if (msgByte != END_MSG) {
                throw new BadMessageStreamException("The message does not ends with a [3] end message byte.");
            }
            setCurrReadPos(dataFile.getFilePointer());
            // write next read sequenece in the read seq file
        } catch (IOException ex) {
            Logger.getLogger(PersistentBlockingQueue.class.getName()).log(Level.SEVERE, "Could not lock the data file channel", ex);
        } finally {
            lock.unlock();
//            try {
//
//                if (fileLock != null) {
//                    fileLock.release();
//                }
//            } catch (IOException ex) {
//                Logger.getLogger(PersistentBlockingQueue.class.getName()).log(Level.SEVERE, "Could not release file lock", ex);
//            }
        }

        return result;
    }

    private void initialise(String pathName, String fileName) throws IOException {
        createDataFile(pathName, fileName, "rws");
        createReadPosFile(pathName, fileName);
        resetDataFile(pathName, fileName);
    }

    private void createDataFile(String pathName, String fileName, String access) throws IOException {
        File fileData = new File(pathName + "/" + fileName + ".buf");
        if (!fileData.exists()) {
            fileData.createNewFile();
        }
        dataFile = new RandomAccessFile(fileData, access);
    }

    private void createReadPosFile(String pathName, String fileName) throws IOException {
        File fileIdx = new File(pathName + "/" + fileName + ".pos");
        if (!fileIdx.exists()) {
            fileIdx.createNewFile();
        }
        readPosFile = new RandomAccessFile(fileIdx, "rws");
        if (readPosFile.length() == 0) {
            readPosFile.writeLong(0);
        } else {
            setCurrReadPos(readPosFile.readLong());
        }
    }

    private void resetDataFile(String pathName, String fileName) throws FileNotFoundException, IOException {
        if (getCurrReadPos() > 0) {
            if (dataFile.length() == 0 || getCurrReadPos() > dataFile.length()) {
                throw new IOException("Current read position [" + getCurrReadPos() + "] does not match file size [" +
                        dataFile.length() + "].");
            }
            FileChannel bakCh = null;
            FileChannel dataCh = null;
            File bakFile = null;
            try {
                if (getCurrReadPos() == dataFile.length()) {
                    // end of file - just delete the buffer file and reset current position
                    dataFile.close();
                    File bufFile = new File(pathName + "/" + fileName + ".buf");
                    if (!bufFile.delete()) {
                        throw new IOException("Could not delete original buffer file.");
                    }
                    createDataFile(pathName, fileName, "rws");
                    dataChannel = dataFile.getChannel();
                    setCurrReadPos(0L);
                    return;
                } else {
                    bakFile = new File(pathName + "/" + fileName + ".buf.bak");
                    if (bakFile.exists()) {
                        if (!bakFile.delete()) {
                            throw new IOException("Could not delete previous BAK buffer file.");
                        }
                    }
                    bakFile.createNewFile();
                    RandomAccessFile filebak = new RandomAccessFile(bakFile, "rw");
                    bakCh = filebak.getChannel();
                    dataCh = dataFile.getChannel();
                    dataCh.transferTo(getCurrReadPos(), dataCh.size() - getCurrReadPos(), bakCh);
                }
            } finally {
                if (dataCh != null && dataCh.isOpen()) {
                    dataCh.close();
                }
                if (bakCh != null && bakCh.isOpen()) {
                    bakCh.close();
                }
            }
            File bufFile = new File(pathName + "/" + fileName + ".buf");
            if (bufFile.exists()) {
                if (!bufFile.delete()) {
                    throw new IOException("Could not delete original buffer file.");
                }
            }
            if (!bakFile.renameTo(bufFile)) {
                throw new IOException("Could not rename BAK file to the buffer file name.");
            }
            createDataFile(pathName, fileName, "rws");
            setCurrReadPos(0L);
        }
        dataChannel = dataFile.getChannel();
    }

    private long getCurrReadPos() {
        return currReadPos;
    }

    private void setCurrReadPos(long currReadPos) throws IOException {
        this.currReadPos = currReadPos;
        readPosFile.seek(0);
        readPosFile.writeLong(getCurrReadPos());
    }

}
