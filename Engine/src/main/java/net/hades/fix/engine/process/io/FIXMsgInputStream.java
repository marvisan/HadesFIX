/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * FIXMsgInputStream.java
 */
package net.hades.fix.engine.process.io;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PushbackInputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

import net.hades.fix.engine.exception.ConnectionException;

/**
 * Input stream that read FIX message bytes from abyte stream.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 */
public class FIXMsgInputStream {

    private static final Logger LOGGER = Logger.getLogger(FIXMsgInputStream.class.getName());

    public static final int BEGIN_STRING_TAG            = 56; // 8
    public static final int BODY_LENGTH_TAG             = 57; // 9
    public static final int CHECKSUM_TAG_1              = 49; // 1
    public static final int CHECKSUM_TAG_2              = 48; // 0
    public static final int SEPARATOR_TAG               = 61; // =

    private PushbackInputStream input;

    private ByteArrayOutputStream message;

    private int msgPointer;

    private int msgBodyLength;

    public FIXMsgInputStream(InputStream input) {
        if (input == null) {
            throw new IllegalArgumentException("Input stream cannot be null.");
        }
        this.input = new PushbackInputStream(input, 10240);
        message = new ByteArrayOutputStream(1024);
    }

    public byte[] readMessage() throws IOException, ConnectionException {
        byte[] result = null;
        int bodyLen;
        if (msgBodyLength == 0) {
            do {
                bodyLen = readBeginAndBodyLen();
            } while (bodyLen < 0);
            msgBodyLength = bodyLen;
        }
        readMsgBody();
        if (msgBodyLength > 0 && msgPointer == msgBodyLength) {
            // end of message reached
            result = message.toByteArray();
            message.reset();
            msgBodyLength = 0;
            msgPointer = 0;
        }

        return result;
    }

    public int available() throws IOException {
        return input.available();
    }
    
    //-----------------------------------------------------------------------------------------------

    private int readBeginAndBodyLen() throws IOException, ConnectionException {
        int bodyLen = -1;
        int current;
        byte[] tmpBuf = new byte[2];
        current = getNextByte();
        if (current == BEGIN_STRING_TAG) {
            tmpBuf[0] = (byte) current;
            current = getNextByte();
            if (current == SEPARATOR_TAG) {
                tmpBuf[1] = (byte) current;
                message.write(tmpBuf);
                readTagValue();
                current = getNextByte();
                if (current == BODY_LENGTH_TAG) {
                    message.write(current);
                    current = getNextByte();
                    if (current == SEPARATOR_TAG) {
                        message.write(current);
                        byte[] bodyLenArr = readTagValue();
                        String bodyLenStr = new String(bodyLenArr);
                        bodyLen = Integer.parseInt(bodyLenStr);
                    }
                }
            }
        }

        return bodyLen;
    }

    private void readMsgBody() throws IOException, ConnectionException {
        int noBytes;
        byte[] tmpBuff = new byte[1024];
        do {
            noBytes = input.read(tmpBuff);
            if (noBytes == -1) {
                throw new ConnectionException("End of message data stream reached.");
            }
            if (noBytes > msgBodyLength - msgPointer) {
                byte[] buff = new byte[msgBodyLength - msgPointer];
                System.arraycopy(tmpBuff, 0, buff, 0, buff.length);
                message.write(buff);
                byte[] left = new byte[noBytes + msgPointer - msgBodyLength];
                System.arraycopy(tmpBuff, noBytes - left.length, left, 0,left.length);
                input.unread(left);
                msgPointer += buff.length;
            } else {
                message.write(tmpBuff);
                msgPointer += tmpBuff.length;
            }
            if (msgPointer == msgBodyLength) {
                if (!readChecksum()) {
                    LOGGER.log(Level.SEVERE, "Inconsistent input stream. Resetting stream and dump existing data : {0}", message.toString());
                    message.reset();
                    msgBodyLength = 0;
                    msgPointer = 0;
                }
                break;
            }
        } while (noBytes > 0);
    }

    private boolean readChecksum() throws IOException, ConnectionException {
        boolean result = true;
        int current = getNextByte();
        if (current == CHECKSUM_TAG_1) {
            message.write(current);
            current = getNextByte();
            if (current == CHECKSUM_TAG_2) {
                message.write(current);
                readTagValue();
            } else {
                result = false;
            }
        } else {
            result = false;
        }

        return result;
    }

    private byte[] readTagValue() throws IOException, ConnectionException {
        byte b;
        byte[] tmpBuf = new byte[100];
        int idx = 0;
        do
        {
            b = (byte) getNextByte();
            tmpBuf[idx++] = b;
            message.write(b);
        } while(b != 1);
        byte[] tagValue = new byte[idx - 1];
        System.arraycopy(tmpBuf, 0, tagValue, 0, idx - 1);

        return tagValue;
    }

    private int getNextByte() throws IOException, ConnectionException {
        int next;
        if ((next = input.read()) == -1) {
            throw new ConnectionException("End of message data stream reached.");
        }

        return next;
    }
}
