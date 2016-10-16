/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * Trailer.java
 *
 * $Id: Trailer.java,v 1.10 2010-11-23 10:20:16 vrotaru Exp $
 */
package net.hades.fix.message;

import net.hades.fix.message.anno.FIXVersion;
import net.hades.fix.message.struct.Tag;
import net.hades.fix.message.util.format.NumberFormatter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import net.hades.fix.message.anno.TagNumRef;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.type.SessionRejectReason;
import net.hades.fix.message.type.TagNum;
import net.hades.fix.message.util.MsgUtil;

import java.util.logging.Level;

/**
 * FIX message trailer super class. All the versions trailer are the same
 * for now.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.10 $
 * @created 12/08/2008, 20:16:44
 */
public class Trailer extends FIXFragment {

    // <editor-fold defaultstate="collapsed" desc="Constants">
    
    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.SignatureLength.getValue(),
        TagNum.CheckSum.getValue()
    }));

    protected static final Set<Integer> REQUIRED_TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.CheckSum.getValue()
    }));

    protected static final Set<Integer> START_DATA_TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.SignatureLength.getValue()
    }));

    protected static final Set<Integer> START_COMP_TAGS = null;

   // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">

    protected FIXMsg msg;

    /** TagNum = 93. */
    protected Integer signatureLength;

    /** TagNum = 89. */
    protected byte[] signature;

    /** TagNum = 122 REQUIRED. */
    protected int checksum;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public Trailer(FIXMsg msg) {
        this.msg = msg;
        checksum = -1;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Public Methods">

    @Override
    public Set<Integer> getFragmentTags() {
        return TAGS;
    }

    @Override
    public Set<Integer> getFragmentAllTags() {
        return TAGS;
    }

    public byte[] encodeFragment(byte[] body) throws TagNotPresentException, BadFormatMsgException {
        if (validateRequired) {             validateRequiredTags();         }
        byte[] result = new byte[0];
        checksum = MsgUtil.calculateChecksum(body);
        ByteArrayOutputStream bao = new ByteArrayOutputStream();
        try {
            bao.write(TagNum.CheckSum.asBytes());
            bao.write("=".getBytes(msg.getSessionCharset()));
            bao.write(NumberFormatter.FIX_CHECKSUM.format(checksum).getBytes(msg.getSessionCharset()));
            bao.write(FIXMsg.SOH);
            result = bao.toByteArray();
        } catch (IOException ex) {
            String error = "Error writing to the byte array.";
            LOGGER.log(Level.SEVERE, "{0} Error was : {1}", new Object[] { error, ex.toString() });
            throw new BadFormatMsgException(error, ex);
        }

        return result;
    }

    public byte[] encodeSignature() throws BadFormatMsgException {

        byte[] result = new byte[0];

        if (signatureLength != null && signatureLength.intValue() > 0) {
            ByteArrayOutputStream bao = new ByteArrayOutputStream();
            try {
                if (signature != null && signature.length > 0) {
                    signatureLength = new Integer(signature.length);
                    bao.write(TagNum.SignatureLength.asBytes());
                    bao.write("=".getBytes(msg.getSessionCharset()));
                    bao.write(signatureLength.toString().getBytes(msg.getSessionCharset()));
                    bao.write(FIXMsg.SOH);
                    bao.write(TagNum.Signature.asBytes());
                    bao.write("=".getBytes(msg.getSessionCharset()));
                    bao.write(signature);
                    bao.write(FIXMsg.SOH);
                }
            } catch (IOException ex) {
                String error = "Error writing to the byte array.";
                LOGGER.log(Level.SEVERE, "{0} Error was : {1}", new Object[] { error, ex.toString() });
                throw new BadFormatMsgException(error, ex);
            }
            result = bao.toByteArray();
        }

        return result;
    }

    // ACCESSOR METHODS
    //////////////////////////////////////////

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.0")
    @TagNumRef(tagNum = TagNum.Signature)
    public byte[] getSignature() {
        return signature;
    }

    /**
     * Message field setter.
     * @param signature field value
     */
    @FIXVersion(introduced = "4.0")
    @TagNumRef(tagNum = TagNum.Signature)
    public void setSignature(byte[] signature) {
        this.signature = signature;
        if (signatureLength == null) {
            signatureLength = new Integer(signature.length);
        }
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.0")
    @TagNumRef(tagNum = TagNum.SignatureLength)
    public Integer getSignatureLength() {
        return signatureLength;
    }

    /**
     * Message field setter.
     * @param signatureLength field value
     */
    @FIXVersion(introduced = "4.0")
    @TagNumRef(tagNum = TagNum.SignatureLength)
    public void setSignatureLength(Integer signatureLength) {
        this.signatureLength = signatureLength;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.0")
    @TagNumRef(tagNum = TagNum.CheckSum, required = true)
    public int getChecksum() {
        return checksum;
    }

    /**
     * Message field setter.
     * @param checksum field value
     */
    @FIXVersion(introduced = "4.0")
    @TagNumRef(tagNum = TagNum.CheckSum, required = true)
    public void setChecksum(int checksum) {
        this.checksum = checksum;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

    @Override
    protected Set<Integer> getFragmentDataTags() {
        return START_DATA_TAGS;
    }

    @Override
    protected Set<Integer> getFragmentCompTags() {
        return START_COMP_TAGS;
    }

    @Override
    protected Set<Integer> getFragmentSecuredTags() {
        return SECURED_TAGS;
    }

    @Override
    protected void validateRequiredTags() throws TagNotPresentException {
    }

    @Override
    protected String getUnsupportedTagMessage() {
        throw new UnsupportedOperationException("Should not be used in FIX trailer fragment.");
    }

    @Override
    protected void setFragmentTagValue(Tag tag) throws BadFormatMsgException {

        TagNum tagNum = TagNum.fromString(tag.tagNum);
        switch (tagNum) {
            case CheckSum:
                checksum = Integer.parseInt(new String(tag.value, msg.getSessionCharset()));
                break;

            case SignatureLength:
                signatureLength = Integer.parseInt(new String(tag.value, msg.getSessionCharset()));
                break;

            default:
                String error = "Tag value [" + tag.tagNum + "] in not present in the trailer fields.";
                throw new BadFormatMsgException(SessionRejectReason.InvalidTagNumber, msg.getHeader().getMsgType(),
                    tag.tagNum, error);
        }
    }

    @Override
    protected void setFragmentCompTagValue(Tag tag, ByteBuffer message) {
        // no group tags in FIX 4.0 trailer
    }

    @Override
    protected ByteBuffer setFragmentDataTagValue(Tag tag, ByteBuffer message) throws BadFormatMsgException {
        try {
            signatureLength = new Integer(new String(tag.value, msg.getSessionCharset()));
        } catch (NumberFormatException ex) {
            String error = "Tag [SignatureLength] requires an integer value. The value set was [" + tag.value + "].";
            LOGGER.log(Level.SEVERE, "{0} Error was: {1}", new Object[] { error, ex.toString() });
            throw new BadFormatMsgException(SessionRejectReason.IncorrectDataFormat, msg.getHeader().getMsgType(),
                TagNum.SignatureLength.getValue(), error);
        }
        Tag dataTag = MsgUtil.getNextTag(message, signatureLength.intValue());
        signature = dataTag.value;

        return message;
    }

    @Override
    protected byte[] encodeFragmentAll() throws TagNotPresentException, BadFormatMsgException {
        throw new UnsupportedOperationException("Not to be used for Trail.");
    }

    @Override
    protected byte[] encodeFragmentSecured(boolean securedFields) throws TagNotPresentException, BadFormatMsgException {
        throw new UnsupportedOperationException("Not to be used for Trail.");
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Package Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Private Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Inner Classes">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="toString()">
    
    @Override
    public String toString() {
        StringBuilder b = new StringBuilder(System.getProperty("line.separator"));
        b.append("{Trailer=");
        printTagValue(b, TagNum.SignatureLength, signatureLength);
        printBase64TagValue(b, TagNum.Signature, signature);
        printTagValue(b, TagNum.CheckSum, checksum);
        b.append("}");

        return b.toString();
    }

    // </editor-fold>
}
