/*
 *   Copyright (c) 2006-2014 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * FileSessSeqPersister.java
 *
 * $Id:$
 */
package net.hades.fix.engine.process.session.persist;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.logging.Level;
import java.util.concurrent.atomic.AtomicInteger;

import net.hades.fix.commons.exception.ExceptionUtil;
import net.hades.fix.engine.exception.ConfigurationException;
import net.hades.fix.engine.exception.SeqNoPersistenceException;

/**
 * Persister in file class for the session sequence numbers.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision:$
 */
public class FileSessSeqPersister extends SessSeqPersister {

    private static final String RX_SEQNUM_FILE_NAME = "rx.seq";
    private static final String TX_SEQNUM_FILE_NAME = "tx.seq";

    protected RandomAccessFile rxSeqNumFile;
    protected RandomAccessFile txSeqNumFile;

    public FileSessSeqPersister(String configDir, boolean resetAtStartup) throws ConfigurationException {
        super(configDir, resetAtStartup);
        initialise();
        LOGGER.log(Level.INFO, "Session seq number persister initialised : rxSeqNo [{0}] txSeqNo [{1}].",
                new Object[] { rxSeqNo.get(), txSeqNo.get() });
    }

    public synchronized int getNextRxSeqNo() throws SeqNoPersistenceException {
        rxSeqNo.incrementAndGet();
        persistRxSeqNo();

        return rxSeqNo.get();
    }

    public synchronized void setRxSeqNo(int rxSeqNo) throws SeqNoPersistenceException {
        this.rxSeqNo.set(rxSeqNo);
        persistRxSeqNo();
    }

    public synchronized int getNextTxSeqNo() throws SeqNoPersistenceException {
        txSeqNo.incrementAndGet();
        persistTxSeqNo();

        return txSeqNo.get();
    }

    public synchronized void setTxSeqNo(int txSeqNo) throws SeqNoPersistenceException {
        this.txSeqNo.set(txSeqNo);
        persistTxSeqNo();
    }

    private void persistRxSeqNo() throws SeqNoPersistenceException {
        try {
            rxSeqNumFile.seek(0);
            rxSeqNumFile.writeInt(rxSeqNo.intValue());
        } catch (IOException ex) {
            String logMsg = "Could not write RxSeqNum to seq file.";
            LOGGER.log(Level.SEVERE, "{0} {1}", new Object[] { logMsg, ExceptionUtil.getStackTrace(ex) });
            throw new SeqNoPersistenceException(logMsg, ex);
        }
    }

    private void persistTxSeqNo() throws SeqNoPersistenceException {
        try {
            txSeqNumFile.seek(0);
            txSeqNumFile.writeInt(txSeqNo.intValue());
        } catch (IOException ex) {
            String logMsg = "Could not write TxSeqNum to seq file.";
            LOGGER.log(Level.SEVERE, "{0} {1}", new Object[] { logMsg, ExceptionUtil.getStackTrace(ex) });
            throw new SeqNoPersistenceException(logMsg, ex);
        }
    }

    private void initialise() throws ConfigurationException {
        File rxFileData = new File(configDir + "/" + RX_SEQNUM_FILE_NAME);
        File txFileData = new File(configDir + "/" + TX_SEQNUM_FILE_NAME);
        try {
            rxSeqNumFile = new RandomAccessFile(rxFileData, "rws");
            if (!rxFileData.exists()) {
                rxSeqNo = new AtomicInteger(0);
                if (!rxFileData.createNewFile()) {
                    throw new ConfigurationException("Failed to create FIX session sequence file: rx.seq");
                }
            } else {
                if (rxSeqNumFile.length() > 0) {
                    rxSeqNo = new AtomicInteger(rxSeqNumFile.readInt());
                    if (resetAtStartup) {
                        rxSeqNo.set(0);
                    }
                }
            }
        } catch (IOException ex) {
            String errMsg = "Could not create/open sequence config file [" + RX_SEQNUM_FILE_NAME + "].";
            LOGGER.severe(errMsg);
            throw new ConfigurationException(errMsg);
        }
        try {
            txSeqNumFile = new RandomAccessFile(rxFileData, "rws");
            if (!txFileData.exists()) {
                txSeqNo = new AtomicInteger(0);
                if (!txFileData.createNewFile()) {
                    throw new ConfigurationException("Failed to create FIX session sequence file: tx.seq");
                }
            } else {
                if (txSeqNumFile.length() > 0) {
                    txSeqNo = new AtomicInteger(txSeqNumFile.readInt());
                    if (resetAtStartup) {
                        txSeqNo.set(0);
                    }
                }
            }
        } catch (IOException ex) {
            String errMsg = "Could not create/open sequence config file [" + TX_SEQNUM_FILE_NAME + "].";
            LOGGER.severe(errMsg);
            throw new ConfigurationException(errMsg);
        }
    }
}
