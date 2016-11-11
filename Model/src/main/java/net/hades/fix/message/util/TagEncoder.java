/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * TagEncoder.java
 *
 * $Id: TagEncoder.java,v 1.3 2010-02-07 07:15:12 vrotaru Exp $
 */
package net.hades.fix.message.util;

import net.hades.fix.message.FIXMsg;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.type.TagNum;
import net.hades.fix.message.util.format.DateFormatter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Date;

/**
 * Utility class to encode all the types of data for a FIX tag.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.3 $
 * @created 05/05/2009, 11:27:59 AM
 */
public class TagEncoder {

    private static final TagEncoder INSTANCE;

    static {
        INSTANCE = new TagEncoder();
    }

    private TagEncoder() {
    }

    /**
     * Encodes an Integer field as a FIX tag-value pair ended by a SOH byte.
     * @param bao target byte array stream to write to
     * @param tag tag to be written to the byte array stream
     * @param field value to be written to the byte array stream
     * @throws java.io.IOException encoding error
     */
    public static void encode(ByteArrayOutputStream bao, TagNum tag, Integer field) throws IOException {
        if (field != null) {
            bao.write(String.valueOf(tag.getValue()).getBytes(FIXMsg.DEFAULT_CHARACTER_SET));
            bao.write("=".getBytes(FIXMsg.DEFAULT_CHARACTER_SET));
            bao.write(field.toString().getBytes(FIXMsg.DEFAULT_CHARACTER_SET));
            bao.write(FIXMsg.SOH);
        }
    }

    /**
     * Encodes an Integer field as a FIX tag-value pair ended by a SOH byte.
     * @param bao target byte array stream to write to
     * @param tag tag to be written to the byte array stream
     * @param field value to be written to the byte array stream
     * @throws java.io.IOException encoding error
     */
    public static void encode(ByteArrayOutputStream bao, int tag, Integer field) throws IOException {
        if (field != null) {
            bao.write(String.valueOf(tag).getBytes(FIXMsg.DEFAULT_CHARACTER_SET));
            bao.write("=".getBytes(FIXMsg.DEFAULT_CHARACTER_SET));
            bao.write(field.toString().getBytes(FIXMsg.DEFAULT_CHARACTER_SET));
            bao.write(FIXMsg.SOH);
        }
    }

    /**
     * Encodes an String field as a FIX tag-value pair ended by a SOH byte.
     * @param bao target byte array stream to write to
     * @param field value to be written to the byte array stream
     * @param tag tag to be written to the byte array stream
     * @throws java.io.IOException encoding error
     */
    public static void encode(ByteArrayOutputStream bao, TagNum tag, Character field)
    throws IOException {
        if (field != null) {
            bao.write(String.valueOf(tag.getValue()).getBytes(FIXMsg.DEFAULT_CHARACTER_SET));
            bao.write("=".getBytes(FIXMsg.DEFAULT_CHARACTER_SET));
            bao.write(field.toString().getBytes());
            bao.write(FIXMsg.SOH);
        }
    }

    /**
     * Encodes an String field as a FIX tag-value pair ended by a SOH byte.
     * @param bao target byte array stream to write to
     * @param field value to be written to the byte array stream
     * @param tag tag to be written to the byte array stream
     * @throws java.io.IOException encoding error
     */
    public static void encode(ByteArrayOutputStream bao, int tag, Character field)
    throws IOException {
        if (field != null) {
            bao.write(String.valueOf(tag).getBytes(FIXMsg.DEFAULT_CHARACTER_SET));
            bao.write("=".getBytes(FIXMsg.DEFAULT_CHARACTER_SET));
            bao.write(field.toString().getBytes());
            bao.write(FIXMsg.SOH);
        }
    }

    /**
     * Encodes an Boolean field as a FIX tag-value pair ended by a SOH byte.
     * @param bao target byte array stream to write to
     * @param field value to be written to the byte array stream
     * @param tag tag to be written to the byte array stream
     * @throws java.io.IOException encoding error
     */
    public static void encode(ByteArrayOutputStream bao, TagNum tag, Boolean field)
    throws IOException {
        if (field != null) {
            bao.write(String.valueOf(tag.getValue()).getBytes(FIXMsg.DEFAULT_CHARACTER_SET));
            bao.write("=".getBytes(FIXMsg.DEFAULT_CHARACTER_SET));
            bao.write(BooleanConverter.formatYN(field.booleanValue()).getBytes(FIXMsg.DEFAULT_CHARACTER_SET));
            bao.write(FIXMsg.SOH);
        }
    }

    /**
     * Encodes an Boolean field as a FIX tag-value pair ended by a SOH byte.
     * @param bao target byte array stream to write to
     * @param field value to be written to the byte array stream
     * @param tag tag to be written to the byte array stream
     * @throws java.io.IOException encoding error
     */
    public static void encode(ByteArrayOutputStream bao, int tag, Boolean field)
    throws IOException {
        if (field != null) {
            bao.write(String.valueOf(tag).getBytes(FIXMsg.DEFAULT_CHARACTER_SET));
            bao.write("=".getBytes(FIXMsg.DEFAULT_CHARACTER_SET));
            bao.write(BooleanConverter.formatYN(field.booleanValue()).getBytes(FIXMsg.DEFAULT_CHARACTER_SET));
            bao.write(FIXMsg.SOH);
        }
    }

    /**
     * Encodes an String field as a FIX tag-value pair ended by a SOH byte.
     * @param bao target byte array stream to write to
     * @param field value to be written to the byte array stream
     * @param tag tag to be written to the byte array stream
     * @param sessionCharset session character set
     * @throws java.io.IOException encoding error
     */
    public static void encode(ByteArrayOutputStream bao, TagNum tag, String field, Charset sessionCharset)
    throws IOException {
        if (field != null && !field.trim().isEmpty()) {
            bao.write(String.valueOf(tag.getValue()).getBytes(FIXMsg.DEFAULT_CHARACTER_SET));
            bao.write("=".getBytes(FIXMsg.DEFAULT_CHARACTER_SET));
            bao.write(field.trim().getBytes(sessionCharset));
            bao.write(FIXMsg.SOH);
        }
    }

    /**
     * Encodes an String field as a FIX tag-value pair ended by a SOH byte.
     * @param bao target byte array stream to write to
     * @param field value to be written to the byte array stream
     * @param tag tag to be written to the byte array stream
     * @param sessionCharset session character set
     * @throws java.io.IOException encoding error
     */
    public static void encode(ByteArrayOutputStream bao, int tag, String field, Charset sessionCharset)
    throws IOException {
        if (field != null && !field.trim().isEmpty()) {
            bao.write(String.valueOf(tag).getBytes(FIXMsg.DEFAULT_CHARACTER_SET));
            bao.write("=".getBytes(FIXMsg.DEFAULT_CHARACTER_SET));
            bao.write(field.trim().getBytes(sessionCharset));
            bao.write(FIXMsg.SOH);
        }
    }

    /**
     * Encodes an Double field as a FIX tag-value pair ended by a SOH byte.
     * @param bao target byte array stream to write to
     * @param field value to be written to the byte array stream
     * @param tag tag to be written to the byte array stream
     * @throws java.io.IOException encoding error
     */
    public static void encode(ByteArrayOutputStream bao, TagNum tag, Double field)
    throws IOException {
        if (field != null) {
            bao.write(String.valueOf(tag.getValue()).getBytes(FIXMsg.DEFAULT_CHARACTER_SET));
            bao.write("=".getBytes(FIXMsg.DEFAULT_CHARACTER_SET));
            bao.write(NumberConverter.formatNumber(field).getBytes(FIXMsg.DEFAULT_CHARACTER_SET));
            bao.write(FIXMsg.SOH);
        }
    }

    /**
     * Encodes an Double field as a FIX tag-value pair ended by a SOH byte.
     * @param bao target byte array stream to write to
     * @param field value to be written to the byte array stream
     * @param tag tag to be written to the byte array stream
     * @throws java.io.IOException encoding error
     */
    public static void encode(ByteArrayOutputStream bao, int tag, Double field)
    throws IOException {
        if (field != null) {
            bao.write(String.valueOf(tag).getBytes(FIXMsg.DEFAULT_CHARACTER_SET));
            bao.write("=".getBytes(FIXMsg.DEFAULT_CHARACTER_SET));
            bao.write(NumberConverter.formatNumber(field).getBytes(FIXMsg.DEFAULT_CHARACTER_SET));
            bao.write(FIXMsg.SOH);
        }
    }

    /**
     * Encodes an int field as a FIX tag-value pair ended by a SOH byte.
     * @param bao target byte array stream to write to
     * @param field value to be written to the byte array stream
     * @param tag tag to be written to the byte array stream
     * @throws java.io.IOException encoding error
     */
    public static void encode(ByteArrayOutputStream bao, TagNum tag, int field)
    throws IOException {
        bao.write(String.valueOf(tag.getValue()).getBytes(FIXMsg.DEFAULT_CHARACTER_SET));
        bao.write("=".getBytes(FIXMsg.DEFAULT_CHARACTER_SET));
        bao.write(String.valueOf(field).getBytes(FIXMsg.DEFAULT_CHARACTER_SET));
        bao.write(FIXMsg.SOH);
    }

    /**
     * Encodes an int field as a FIX tag-value pair ended by a SOH byte.
     * @param bao target byte array stream to write to
     * @param field value to be written to the byte array stream
     * @param tag tag to be written to the byte array stream
     * @throws java.io.IOException encoding error
     */
    public static void encode(ByteArrayOutputStream bao, int tag, int field)
    throws IOException {
        bao.write(String.valueOf(tag).getBytes(FIXMsg.DEFAULT_CHARACTER_SET));
        bao.write("=".getBytes(FIXMsg.DEFAULT_CHARACTER_SET));
        bao.write(String.valueOf(field).getBytes(FIXMsg.DEFAULT_CHARACTER_SET));
        bao.write(FIXMsg.SOH);
    }

    /**
     * Encodes an char field as a FIX tag-value pair ended by a SOH byte.
     * @param bao target byte array stream to write to
     * @param field value to be written to the byte array stream
     * @param tag tag to be written to the byte array stream
     * @throws java.io.IOException encoding error
     */
    public static void encode(ByteArrayOutputStream bao, TagNum tag, char field)
    throws IOException {
        bao.write(String.valueOf(tag.getValue()).getBytes(FIXMsg.DEFAULT_CHARACTER_SET));
        bao.write("=".getBytes(FIXMsg.DEFAULT_CHARACTER_SET));
        bao.write(String.valueOf(field).getBytes(FIXMsg.DEFAULT_CHARACTER_SET));
        bao.write(FIXMsg.SOH);
    }

    /**
     * Encodes an char field as a FIX tag-value pair ended by a SOH byte.
     * @param bao target byte array stream to write to
     * @param field value to be written to the byte array stream
     * @param tag tag to be written to the byte array stream
     * @throws java.io.IOException encoding error
     */
    public static void encode(ByteArrayOutputStream bao, int tag, char field)
    throws IOException {
        bao.write(String.valueOf(tag).getBytes(FIXMsg.DEFAULT_CHARACTER_SET));
        bao.write("=".getBytes(FIXMsg.DEFAULT_CHARACTER_SET));
        bao.write(String.valueOf(field).getBytes(FIXMsg.DEFAULT_CHARACTER_SET));
        bao.write(FIXMsg.SOH);
    }

    /**
     * Encodes an String field as a FIX tag-value pair ended by a SOH byte.
     * @param bao target byte array stream to write to
     * @param field value to be written to the byte array stream
     * @param tag tag to be written to the byte array stream
     * @throws java.io.IOException encoding error
     */
    public static void encode(ByteArrayOutputStream bao, TagNum tag, String field)
    throws IOException {
        if (field != null && !field.trim().isEmpty()) {
            bao.write(String.valueOf(tag.getValue()).getBytes(FIXMsg.DEFAULT_CHARACTER_SET));
            bao.write("=".getBytes(FIXMsg.DEFAULT_CHARACTER_SET));
            bao.write(field.trim().getBytes(FIXMsg.DEFAULT_CHARACTER_SET));
            bao.write(FIXMsg.SOH);
        }
    }

    /**
     * Encodes an String field as a FIX tag-value pair ended by a SOH byte.
     * @param bao target byte array stream to write to
     * @param field value to be written to the byte array stream
     * @param tag tag to be written to the byte array stream
     * @throws java.io.IOException encoding error
     */
    public static void encode(ByteArrayOutputStream bao, int tag, String field)
    throws IOException {
        if (field != null && !field.trim().isEmpty()) {
            bao.write(String.valueOf(tag).getBytes(FIXMsg.DEFAULT_CHARACTER_SET));
            bao.write("=".getBytes(FIXMsg.DEFAULT_CHARACTER_SET));
            bao.write(field.trim().getBytes(FIXMsg.DEFAULT_CHARACTER_SET));
            bao.write(FIXMsg.SOH);
        }
    }

    /**
     * Encodes an Date field as a FIX tag-value pair ended by a SOH byte. The date is
     * encoded in FIX timestamp format.
     * @param bao target byte array stream to write to
     * @param field value to be written to the byte array stream
     * @param tag tag to be written to the byte array stream
     * @throws java.io.IOException encoding error
     * @throws net.hades.fix.message.exception.BadFormatMsgException bad text format of the date
     */
    public static void encodeTimestamp(ByteArrayOutputStream bao, TagNum tag, Date field)
    throws IOException, BadFormatMsgException {
        if (field != null) {
            bao.write(String.valueOf(tag.getValue()).getBytes(FIXMsg.DEFAULT_CHARACTER_SET));
            bao.write("=".getBytes(FIXMsg.DEFAULT_CHARACTER_SET));
            bao.write(DateConverter.formatDate(DateFormatter.getFixTSFormat(), field).getBytes(FIXMsg.DEFAULT_CHARACTER_SET));
            bao.write(FIXMsg.SOH);
        }
    }

    /**
     * Encodes an Date field as a FIX tag-value pair ended by a SOH byte. The date is
     * encoded in FIX timestamp format.
     * @param bao target byte array stream to write to
     * @param field value to be written to the byte array stream
     * @param tag tag to be written to the byte array stream
     * @throws java.io.IOException encoding error
     * @throws BadFormatMsgException bad text format of the date
     */
    public static void encodeTimestamp(ByteArrayOutputStream bao, int tag, Date field)
    throws IOException, BadFormatMsgException {
        if (field != null) {
            bao.write(String.valueOf(tag).getBytes(FIXMsg.DEFAULT_CHARACTER_SET));
            bao.write("=".getBytes(FIXMsg.DEFAULT_CHARACTER_SET));
            bao.write(DateConverter.formatDate(DateFormatter.getFixTSFormat(), field).getBytes(FIXMsg.DEFAULT_CHARACTER_SET));
            bao.write(FIXMsg.SOH);
        }
    }

    /**
     * Encodes an Date field as a FIX tag-value pair ended by a SOH byte. The date is
     * encoded in FIX UTC timestamp format.
     * @param bao target byte array stream to write to
     * @param field value to be written to the byte array stream
     * @param tag tag to be written to the byte array stream
     * @throws java.io.IOException encoding error
     * @throws BadFormatMsgException bad text format of the date
     */
    public static void encodeUtcTimestamp(ByteArrayOutputStream bao, TagNum tag, Date field)
    throws IOException, BadFormatMsgException {
        if (field != null) {
            bao.write(String.valueOf(tag.getValue()).getBytes(FIXMsg.DEFAULT_CHARACTER_SET));
            bao.write("=".getBytes(FIXMsg.DEFAULT_CHARACTER_SET));
            bao.write(DateConverter.formatUTCDate(DateFormatter.getFixTSFormat(), field).getBytes(FIXMsg.DEFAULT_CHARACTER_SET));
            bao.write(FIXMsg.SOH);
        }
    }

    /**
     * Encodes an Date field as a FIX tag-value pair ended by a SOH byte. The date is
     * encoded in FIX UTC timestamp format.
     * @param bao target byte array stream to write to
     * @param field value to be written to the byte array stream
     * @param tag tag to be written to the byte array stream
     * @throws java.io.IOException encoding error
     * @throws BadFormatMsgException bad text format of the date
     */
    public static void encodeUtcTimestamp(ByteArrayOutputStream bao, int tag, Date field)
    throws IOException, BadFormatMsgException {
        if (field != null) {
            bao.write(String.valueOf(tag).getBytes(FIXMsg.DEFAULT_CHARACTER_SET));
            bao.write("=".getBytes(FIXMsg.DEFAULT_CHARACTER_SET));
            bao.write(DateConverter.formatUTCDate(DateFormatter.getFixTSFormat(), field).getBytes(FIXMsg.DEFAULT_CHARACTER_SET));
            bao.write(FIXMsg.SOH);
        }
    }

    /**
     * Encodes an Date field as a FIX tag-value pair ended by a SOH byte. The date is
     * encoded in FIX TZ timestamp format.
     * @param bao target byte array stream to write to
     * @param field value to be written to the byte array stream
     * @param tag tag to be written to the byte array stream
     * @throws java.io.IOException encoding error
     * @throws BadFormatMsgException bad text format of the date
     */
    public static void encodeTZTimestamp(ByteArrayOutputStream bao, TagNum tag, Date field)
    throws IOException, BadFormatMsgException {
        if (field != null) {
            bao.write(String.valueOf(tag.getValue()).getBytes(FIXMsg.DEFAULT_CHARACTER_SET));
            bao.write("=".getBytes(FIXMsg.DEFAULT_CHARACTER_SET));
            bao.write(DateConverter.formatTZDate(field).getBytes(FIXMsg.DEFAULT_CHARACTER_SET));
            bao.write(FIXMsg.SOH);
        }
    }

    /**
     * Encodes an Date field as a FIX tag-value pair ended by a SOH byte. The date is
     * encoded in FIX TZ timestamp format.
     * @param bao target byte array stream to write to
     * @param field value to be written to the byte array stream
     * @param tag tag to be written to the byte array stream
     * @throws java.io.IOException encoding error
     * @throws BadFormatMsgException bad text format of the date
     */
    public static void encodeTZTimestamp(ByteArrayOutputStream bao, int tag, Date field)
    throws IOException, BadFormatMsgException {
        if (field != null) {
            bao.write(String.valueOf(tag).getBytes(FIXMsg.DEFAULT_CHARACTER_SET));
            bao.write("=".getBytes(FIXMsg.DEFAULT_CHARACTER_SET));
            bao.write(DateConverter.formatTZDate(field).getBytes(FIXMsg.DEFAULT_CHARACTER_SET));
            bao.write(FIXMsg.SOH);
        }
    }

    /**
     * Encodes an Date field as a FIX tag-value pair ended by a SOH byte. The date is
     * encoded in FIX date format.
     * @param bao target byte array stream to write to
     * @param field value to be written to the byte array stream
     * @param tag tag to be written to the byte array stream
     * @throws java.io.IOException encoding error
     * @throws BadFormatMsgException bad text format of the date
     */
    public static void encodeDate(ByteArrayOutputStream bao, TagNum tag, Date field)
    throws IOException, BadFormatMsgException {
        if (field != null) {
            bao.write(String.valueOf(tag.getValue()).getBytes(FIXMsg.DEFAULT_CHARACTER_SET));
            bao.write("=".getBytes(FIXMsg.DEFAULT_CHARACTER_SET));
            bao.write(DateConverter.formatDate(DateFormatter.getFixDateFormat(), field).getBytes(FIXMsg.DEFAULT_CHARACTER_SET));
            bao.write(FIXMsg.SOH);
        }
    }

    /**
     * Encodes an Date field as a FIX tag-value pair ended by a SOH byte. The date is
     * encoded in FIX date format.
     * @param bao target byte array stream to write to
     * @param field value to be written to the byte array stream
     * @param tag tag to be written to the byte array stream
     * @throws java.io.IOException encoding error
     * @throws BadFormatMsgException bad text format of the date
     */
    public static void encodeDate(ByteArrayOutputStream bao, int tag, Date field)
    throws IOException, BadFormatMsgException {
        if (field != null) {
            bao.write(String.valueOf(tag).getBytes(FIXMsg.DEFAULT_CHARACTER_SET));
            bao.write("=".getBytes(FIXMsg.DEFAULT_CHARACTER_SET));
            bao.write(DateConverter.formatDate(DateFormatter.getFixDateFormat(), field).getBytes(FIXMsg.DEFAULT_CHARACTER_SET));
            bao.write(FIXMsg.SOH);
        }
    }

    /**
     * Encodes an UTC Date field as a FIX tag-value pair ended by a SOH byte. The date is
     * encoded in FIX date format.
     * @param bao target byte array stream to write to
     * @param field value to be written to the byte array stream
     * @param tag tag to be written to the byte array stream
     * @throws java.io.IOException encoding error
     * @throws BadFormatMsgException bad text format of the date
     */
    public static void encodeUtcDate(ByteArrayOutputStream bao, TagNum tag, Date field)
    throws IOException, BadFormatMsgException {
        if (field != null) {
            bao.write(String.valueOf(tag.getValue()).getBytes(FIXMsg.DEFAULT_CHARACTER_SET));
            bao.write("=".getBytes(FIXMsg.DEFAULT_CHARACTER_SET));
            bao.write(DateConverter.formatUTCDate(DateFormatter.getFixDateFormat(), field).getBytes(FIXMsg.DEFAULT_CHARACTER_SET));
            bao.write(FIXMsg.SOH);
        }
    }

     /**
     * Encodes an UTC Date field as a FIX tag-value pair ended by a SOH byte. The date is
     * encoded in FIX date format.
     * @param bao target byte array stream to write to
     * @param field value to be written to the byte array stream
     * @param tag tag to be written to the byte array stream
     * @throws java.io.IOException encoding error
     * @throws BadFormatMsgException bad text format of the date
     */
    public static void encodeUtcDate(ByteArrayOutputStream bao, int tag, Date field)
    throws IOException, BadFormatMsgException {
        if (field != null) {
            bao.write(String.valueOf(tag).getBytes(FIXMsg.DEFAULT_CHARACTER_SET));
            bao.write("=".getBytes(FIXMsg.DEFAULT_CHARACTER_SET));
            bao.write(DateConverter.formatUTCDate(DateFormatter.getFixDateFormat(), field).getBytes(FIXMsg.DEFAULT_CHARACTER_SET));
            bao.write(FIXMsg.SOH);
        }
    }

    /**
     * Encodes an Date field as a FIX tag-value pair ended by a SOH byte. The time is
     * encoded in FIX time format.
     * @param bao target byte array stream to write to
     * @param field value to be written to the byte array stream
     * @param tag tag to be written to the byte array stream
     * @throws java.io.IOException encoding error
     * @throws BadFormatMsgException bad text format of the date
     */
    public static void encodeTime(ByteArrayOutputStream bao, TagNum tag, Date field)
    throws IOException, BadFormatMsgException {
        if (field != null) {
            bao.write(String.valueOf(tag.getValue()).getBytes(FIXMsg.DEFAULT_CHARACTER_SET));
            bao.write("=".getBytes(FIXMsg.DEFAULT_CHARACTER_SET));
            bao.write(DateConverter.formatDate(DateFormatter.getFixTimeFormat(), field).getBytes(FIXMsg.DEFAULT_CHARACTER_SET));
            bao.write(FIXMsg.SOH);
        }
    }

    /**
     * Encodes an Date field as a FIX tag-value pair ended by a SOH byte. The time is
     * encoded in FIX time format.
     * @param bao target byte array stream to write to
     * @param field value to be written to the byte array stream
     * @param tag tag to be written to the byte array stream
     * @throws java.io.IOException encoding error
     * @throws BadFormatMsgException bad text format of the date
     */
    public static void encodeTime(ByteArrayOutputStream bao, int tag, Date field)
    throws IOException, BadFormatMsgException {
        if (field != null) {
            bao.write(String.valueOf(tag).getBytes(FIXMsg.DEFAULT_CHARACTER_SET));
            bao.write("=".getBytes(FIXMsg.DEFAULT_CHARACTER_SET));
            bao.write(DateConverter.formatDate(DateFormatter.getFixTimeFormat(), field).getBytes(FIXMsg.DEFAULT_CHARACTER_SET));
            bao.write(FIXMsg.SOH);
        }
    }

    /**
     * Encodes an UTC time field as a FIX tag-value pair ended by a SOH byte. The time is
     * encoded in FIX time format.
     * @param bao target byte array stream to write to
     * @param field value to be written to the byte array stream
     * @param tag tag to be written to the byte array stream
     * @throws java.io.IOException encoding error
     * @throws BadFormatMsgException bad text format of the date
     */
    public static void encodeUTCTime(ByteArrayOutputStream bao, TagNum tag, Date field)
    throws IOException, BadFormatMsgException {
        if (field != null) {
            bao.write(String.valueOf(tag.getValue()).getBytes(FIXMsg.DEFAULT_CHARACTER_SET));
            bao.write("=".getBytes(FIXMsg.DEFAULT_CHARACTER_SET));
            bao.write(DateConverter.formatUTCDate(DateFormatter.getFixTimeFormat(), field).getBytes(FIXMsg.DEFAULT_CHARACTER_SET));
            bao.write(FIXMsg.SOH);
        }
    }

    /**
     * Encodes an UTC time field as a FIX tag-value pair ended by a SOH byte. The time is
     * encoded in FIX time format.
     * @param bao target byte array stream to write to
     * @param field value to be written to the byte array stream
     * @param tag tag to be written to the byte array stream
     * @throws java.io.IOException encoding error
     * @throws BadFormatMsgException bad text format of the date
     */
    public static void encodeUTCTime(ByteArrayOutputStream bao, int tag, Date field)
    throws IOException, BadFormatMsgException {
        if (field != null) {
            bao.write(String.valueOf(tag).getBytes(FIXMsg.DEFAULT_CHARACTER_SET));
            bao.write("=".getBytes(FIXMsg.DEFAULT_CHARACTER_SET));
            bao.write(DateConverter.formatUTCDate(DateFormatter.getFixTimeFormat(), field).getBytes(FIXMsg.DEFAULT_CHARACTER_SET));
            bao.write(FIXMsg.SOH);
        }
    }

    /**
     * Encodes an Date field as a FIX tag-value pair ended by a SOH byte. The time is
     * encoded in FIX TZ time format.
     * @param bao target byte array stream to write to
     * @param field value to be written to the byte array stream
     * @param tag tag to be written to the byte array stream
     * @throws java.io.IOException encoding error
     * @throws BadFormatMsgException bad text format of the date
     */
    public static void encodeTZTime(ByteArrayOutputStream bao, TagNum tag, Date field)
    throws IOException, BadFormatMsgException {
        if (field != null) {
            bao.write(String.valueOf(tag.getValue()).getBytes(FIXMsg.DEFAULT_CHARACTER_SET));
            bao.write("=".getBytes(FIXMsg.DEFAULT_CHARACTER_SET));
            bao.write(DateConverter.formatTZTime(field).getBytes(FIXMsg.DEFAULT_CHARACTER_SET));
            bao.write(FIXMsg.SOH);
        }
    }

    /**
     * Encodes an Date field as a FIX tag-value pair ended by a SOH byte. The time is
     * encoded in FIX TZ time format.
     * @param bao target byte array stream to write to
     * @param field value to be written to the byte array stream
     * @param tag tag to be written to the byte array stream
     * @throws java.io.IOException encoding error
     * @throws BadFormatMsgException bad text format of the date
     */
    public static void encodeTZTime(ByteArrayOutputStream bao, int tag, Date field)
    throws IOException, BadFormatMsgException {
        if (field != null) {
            bao.write(String.valueOf(tag).getBytes(FIXMsg.DEFAULT_CHARACTER_SET));
            bao.write("=".getBytes(FIXMsg.DEFAULT_CHARACTER_SET));
            bao.write(DateConverter.formatTZTime(field).getBytes(FIXMsg.DEFAULT_CHARACTER_SET));
            bao.write(FIXMsg.SOH);
        }
    }

    /**
     * Encodes an byte array field as a FIX tag-value pair ended by a SOH byte.
     * @param bao target byte array stream to write to
     * @param field value to be written to the byte array stream
     * @param tag tag to be written to the byte array stream
     * @throws java.io.IOException encoding error
     */
    public static void encode(ByteArrayOutputStream bao, TagNum tag, byte[] field)
    throws IOException {
        if (field != null) {
            bao.write(String.valueOf(tag.getValue()).getBytes(FIXMsg.DEFAULT_CHARACTER_SET));
            bao.write("=".getBytes(FIXMsg.DEFAULT_CHARACTER_SET));
            bao.write(field);
            bao.write(FIXMsg.SOH);
        }
    }

    /**
     * Encodes an byte array field as a FIX tag-value pair ended by a SOH byte.
     * @param bao target byte array stream to write to
     * @param field value to be written to the byte array stream
     * @param tag tag to be written to the byte array stream
     * @throws java.io.IOException encoding error
     */
    public static void encode(ByteArrayOutputStream bao, int tag, byte[] field)
    throws IOException {
        if (field != null) {
            bao.write(String.valueOf(tag).getBytes(FIXMsg.DEFAULT_CHARACTER_SET));
            bao.write("=".getBytes(FIXMsg.DEFAULT_CHARACTER_SET));
            bao.write(field);
            bao.write(FIXMsg.SOH);
        }
    }
}
