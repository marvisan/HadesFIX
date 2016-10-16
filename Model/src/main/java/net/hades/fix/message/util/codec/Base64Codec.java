/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * Base64Codec.java
 *
 * $Id: Base64Codec.java,v 1.2 2009-08-03 09:41:42 vrotaru Exp $
 */
package net.hades.fix.message.util.codec;

/**
 * Codec for Base64 encoding/decoding.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 * @created 03/04/2009, 1:11:46 PM
 */
public class Base64Codec {
    
    // <editor-fold defaultstate="collapsed" desc="Constants">

    /** Base array length
     */
    private static final int BASE_LENGTH = 255;

    /** Lookup array length
     */
    private static final int LOOKUP_LENGTH = 64;

    /** Twenty four bit
     */
    private static final int TWENTYFOURBITGROUP = 24;

    /** Eight bit
     */
    private static final int EIGHTBIT = 8;

    /** Sixteen bit
     */
    private static final int SIXTEENBIT = 16;

    /** Six bit
     */
    private static final int SIXBIT = 6;

    /** Four bit
     */
    private static final int FOURBYTE = 4;

    /** Sign value
     */
    private static final int SIGN = -128;

    /** Pad value
     */
    private static final byte PAD = (byte) '=';

    /** Array of bytes in base64
     */
    private static byte[] base64Alphabet = new byte[BASE_LENGTH];

    /** Lookup array
     */
    private static byte[] lookUpBase64Alphabet = new byte[LOOKUP_LENGTH];

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">

    static {
        for (int i = 0; i < BASE_LENGTH; i++) {
            base64Alphabet[i] = -1;
        }
        for (int i = 'Z'; i >= 'A'; i--) {
            base64Alphabet[i] = (byte) (i - 'A');
        }
        for (int i = 'z'; i >= 'a'; i--) {
            base64Alphabet[i] = (byte) (i - 'a' + 26);
        }
        for (int i = '9'; i >= '0'; i--) {
            base64Alphabet[i] = (byte) (i - '0' + 52);
        }
        base64Alphabet['+'] = 62;
        base64Alphabet['/'] = 63;
        for (int i = 0; i <= 25; i++) {
            lookUpBase64Alphabet[i] = (byte) ('A' + i);
        }
        for (int i = 26, j = 0; i <= 51; i++, j++) {
            lookUpBase64Alphabet[i] = (byte) ('a' + j);
        }
        for (int i = 52, j = 0; i <= 61; i++, j++) {
            lookUpBase64Alphabet[i] = (byte) ('0' + j);
        }
        lookUpBase64Alphabet[62] = (byte) '+';
        lookUpBase64Alphabet[63] = (byte) '/';
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    private Base64Codec() {
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Public Methods">

    /**
     * This method returns true if the given string is encoded in base64 format.
     * @param data string to check
     * @return true if the given string is in base64 format
     */
    public static boolean isBase64(String data) {
        return isArrayByteBase64(data.getBytes());
    }

    /**
     * This method returns true if the given byte is a base64 byte
     * @param octet byte to check
     * @return true if the given octet is a base64 byte
     */
    public static boolean isBase64(byte octet) {
        return (octet == PAD || base64Alphabet[octet] != -1);
    }

    /**
     * This method returns true if the given byte array is a base64 byte array
     * @param arrayOctet array of bytes to check
     * @return true if the given byte array is a base64 array
     */
    public static boolean isArrayByteBase64(byte[] arrayOctet) {
        int length = arrayOctet.length;

        if (length == 0) {
            return true;
        } // end of if

        for (int i = 0; i < length; i++) {
            if (Base64Codec.isBase64(arrayOctet[i]) == false) {
                return false;
            } // end of if
        } // end of for (int i = 0; i < length; i++)

        return true;
    }

    /**
     * Encodes an array of bytes into Base64
     * @param binaryData Array containing binaryData
     * @return Base64-encoded array of bytes
     */
    public static byte[] encode(byte[] binaryData) {

        int lengthDataBits = binaryData.length * EIGHTBIT;
        int fewerThan24bits = lengthDataBits % TWENTYFOURBITGROUP;
        int numberTriplets = lengthDataBits / TWENTYFOURBITGROUP;
        byte encodedData[] = null;


        if (fewerThan24bits != 0) {
            encodedData = new byte[(numberTriplets + 1) * 4];
        } else {
            encodedData = new byte[numberTriplets * 4];
        } // end of if-else

        byte k = 0, l = 0, b1 = 0, b2 = 0, b3 = 0;

        int encodedIndex = 0;
        int dataIndex = 0;
        int i = 0;

        for (i = 0; i < numberTriplets; i++) {
            dataIndex = i * 3;
            b1 = binaryData[dataIndex];
            b2 = binaryData[dataIndex + 1];
            b3 = binaryData[dataIndex + 2];

            l = (byte) (b2 & 0x0f);
            k = (byte) (b1 & 0x03);

            encodedIndex = i * 4;
            byte val1 = ((b1 & SIGN) == 0) ? (byte) (b1 >> 2) : (byte) ((b1) >> 2 ^ 0xc0);
            byte val2 = ((b2 & SIGN) == 0) ? (byte) (b2 >> 4) : (byte) ((b2) >> 4 ^ 0xf0);
            byte val3 = ((b3 & SIGN) == 0) ? (byte) (b3 >> 6) : (byte) ((b3) >> 6 ^ 0xfc);

            encodedData[encodedIndex] = lookUpBase64Alphabet[val1];
            encodedData[encodedIndex + 1] = lookUpBase64Alphabet[val2 | (k << 4)];
            encodedData[encodedIndex + 2] = lookUpBase64Alphabet[(l << 2) | val3];
            encodedData[encodedIndex + 3] = lookUpBase64Alphabet[b3 & 0x3f];
        }

        dataIndex = i * 3;
        encodedIndex = i * 4;

        if (fewerThan24bits == EIGHTBIT) {
            b1 = binaryData[dataIndex];
            k = (byte) (b1 & 0x03);
            byte val1 = ((b1 & SIGN) == 0) ? (byte) (b1 >> 2) : (byte) ((b1) >> 2 ^ 0xc0);
            encodedData[encodedIndex] = lookUpBase64Alphabet[val1];
            encodedData[encodedIndex + 1] = lookUpBase64Alphabet[k << 4];
            encodedData[encodedIndex + 2] = PAD;
            encodedData[encodedIndex + 3] = PAD;
        } else if (fewerThan24bits == SIXTEENBIT) {
            b1 = binaryData[dataIndex];
            b2 = binaryData[dataIndex + 1];
            l = (byte) (b2 & 0x0f);
            k = (byte) (b1 & 0x03);

            byte val1 = ((b1 & SIGN) == 0) ? (byte) (b1 >> 2) : (byte) ((b1) >> 2 ^ 0xc0);
            byte val2 = ((b2 & SIGN) == 0) ? (byte) (b2 >> 4) : (byte) ((b2) >> 4 ^ 0xf0);

            encodedData[encodedIndex] = lookUpBase64Alphabet[val1];
            encodedData[encodedIndex + 1] = lookUpBase64Alphabet[val2 | (k << 4)];
            encodedData[encodedIndex + 2] = lookUpBase64Alphabet[l << 2];
            encodedData[encodedIndex + 3] = PAD;
        } // end of if-else

        return encodedData;
    }

    /**
     * Decodes Base64 bytes data into binary bytes
     * @param base64Data Byte array containing Base64 data
     * @return Array containing decoded data.
     */
    public static byte[] decode(byte[] base64Data) {
        if (base64Data.length == 0) {
            return new byte[0];
        } // end of if

        int numberQuadruple = base64Data.length / FOURBYTE;
        byte decodedData[] = null;
        byte b1 = 0, b2 = 0, b3 = 0, b4 = 0, marker0 = 0, marker1 = 0;

        int encodedIndex = 0;
        int dataIndex = 0;
        {
            int lastData = base64Data.length;

            while (base64Data[lastData - 1] == PAD) {
                if (--lastData == 0) {
                    return new byte[0];
                } // end of if
            } // end of while
            decodedData = new byte[lastData - numberQuadruple];
        }

        for (int i = 0; i < numberQuadruple; i++) {
            dataIndex = i * 4;
            marker0 = base64Data[dataIndex + 2];
            marker1 = base64Data[dataIndex + 3];

            b1 = base64Alphabet[base64Data[dataIndex]];
            b2 = base64Alphabet[base64Data[dataIndex + 1]];

            if (marker0 != PAD && marker1 != PAD) {
                b3 = base64Alphabet[marker0];
                b4 = base64Alphabet[marker1];

                decodedData[encodedIndex] = (byte) (b1 << 2 | b2 >> 4);
                decodedData[encodedIndex + 1] = (byte) (((b2 & 0xf) << 4) | ((b3 >> 2) & 0xf));
                decodedData[encodedIndex + 2] = (byte) (b3 << 6 | b4);
            } else if (marker0 == PAD) {
                decodedData[encodedIndex] = (byte) (b1 << 2 | b2 >> 4);
            } else if (marker1 == PAD) {
                b3 = base64Alphabet[marker0];

                decodedData[encodedIndex] = (byte) (b1 << 2 | b2 >> 4);
                decodedData[encodedIndex + 1] = (byte) (((b2 & 0xf) << 4) | ((b3 >> 2) & 0xf));
            } // end of if-else

            encodedIndex += 3;
        }

        return decodedData;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Package Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Private Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Inner Classes">
    // </editor-fold>
}
