/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * MarketDataRequestMsg.java
 *
 * $Id: MarketDataRequestRejectMsg.java,v 1.10 2011-04-28 10:07:43 vrotaru Exp $
 */
package net.hades.fix.message;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import net.hades.fix.message.anno.FIXVersion;
import net.hades.fix.message.anno.TagNumRef;
import net.hades.fix.message.comp.Parties;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.group.AltMDSourceGroup;
import net.hades.fix.message.struct.Tag;
import net.hades.fix.message.type.*;
import net.hades.fix.message.util.MsgUtil;
import net.hades.fix.message.util.TagEncoder;

/**
 * <p>Extracted from FIX protocol documentation <a href="http://www.fixprotocol.org/">FIX Protocol</a></p>
 * The Market Data Request Reject is used when the broker cannot honor the Market Data Request, due to
 * business or technical reasons. Brokers may choose to limit various parameters, such as the size of requests,
 * whether just the top of book or the entire book may be displayed, and whether Full or Incremental updates must
 * be used.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.10 $
 * @created 01/04/2009, 8:34:33 AM
 */
@XmlAccessorType(XmlAccessType.NONE)
public abstract class MarketDataRequestRejectMsg extends FIXMsg  {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1;

    protected static final Set<Integer> TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.MDReqID.getValue(),
        TagNum.MDReqRejReason.getValue(),
        TagNum.NoAltMDSource.getValue(),
        TagNum.Text.getValue()
    }));

    protected static final Set<Integer> START_DATA_TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.EncodedTextLen.getValue()
    }));

    protected static final Set<Integer> REQUIRED_TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.MDReqID.getValue()
    }));

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">
    
    /**
     * TagNum = 262 REQUIRED. Starting with 4.2 version.
     */
    protected String mdReqID;

    /**
     * TagNum = 281. Starting with 4.2 version.
     */
    protected MDReqRejReason mdReqRejReason;

    /**
     * Starting with 5.0SP1 version.
     */
    protected Parties parties;

    /**
     * TagNum = 816. Starting with 4.4 version.
     */
    protected Integer noAltMDSource;

    /**
     * Starting with 4.4 version.
     */
    protected AltMDSourceGroup[] altMDSourceGroups;

    /**
     * TagNum = 58. Starting with 4.2 version.
     */
    protected String text;

    /**
     * TagNum = 354. Starting with 4.2 version.
     */
    protected Integer encodedTextLen;

    /**
     * TagNum = 355. Starting with 4.2 version.
     */
    protected byte[] encodedText;


    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Constructors">
    
    public MarketDataRequestRejectMsg() {
        super();
    }
    
    public MarketDataRequestRejectMsg(Header header, ByteBuffer rawMsg)
        throws InvalidMsgException, TagNotPresentException, BadFormatMsgException {
        super(header, rawMsg);
    }

    public MarketDataRequestRejectMsg(BeginString beginString) throws InvalidMsgException {
        super(MsgType.MarketDataRequestReject.getValue(), beginString);
    }
    
    public MarketDataRequestRejectMsg(BeginString beginString, ApplVerID applVerID) throws InvalidMsgException {
        super(MsgType.MarketDataRequestReject.getValue(), beginString, applVerID);
    }
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Public Methods">

    @Override
    public Set<Integer> getFragmentTags() {
        return TAGS;
    }

    // ACCESSOR METHODS
    //////////////////////////////////////////

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.MDReqID, required=true)
    public String getMdReqID() {
        return mdReqID;
    }

    /**
     * Message field setter.
     * @param mdReqID field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.MDReqID, required=true)
    public void setMdReqID(String mdReqID) {
        this.mdReqID = mdReqID;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.MDReqRejReason)
    public MDReqRejReason getMdReqRejReason() {
        return mdReqRejReason;
    }

    /**
     * Message field setter.
     * @param mdReqRejReason field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.MDReqRejReason)
    public void setMdReqRejReason(MDReqRejReason mdReqRejReason) {
        this.mdReqRejReason = mdReqRejReason;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0SP1")
    public Parties getParties() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Sets the Parties component if used in this message to the proper implementation
     * class.
     */
    @FIXVersion(introduced="5.0SP1")
    public void setParties() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Sets the Parties component to null.
     */
    @FIXVersion(introduced="5.0SP1")
    public void clearParties() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.NoAltMDSource)
    public Integer getNoAltMDSource() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method sets the number of {@link AltMDSourceGroup} components. It will also create an array
     * of {@link AltMDSourceGroup} objects and set the <code>altMDSourceGroups</code>
     * field with this array.
     * The created objects inside the array need to be populated with data for encoding.<br/>
     * If there where already objects in <code>altMDSourceGroups</code> array they will be discarded.<br/>
     * @param noAltMDSource number of objects
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.NoAltMDSource)
    public void setNoAltMDSource(Integer noAltMDSource) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter for {@link AltMDSourceGroup} array of groups.
     * @return field array value
     */
    @FIXVersion(introduced="4.4")
    public AltMDSourceGroup[] getAltMDSourceGroups() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method adds a {@link AltMDSourceGroup} object to the existing array of <code>altMDSourceGroups</code>
     * and expands the static array with 1 place.<br/>
     * This method will also update <code>noAltMDSource</code> field to the proper value.<br/>
     * Note: If the <code>setNoAltMDSource</code> method has been called there will already be a number of objects in the
     * <code>altMDSourceGroups</code> array created.<br/>
     * @return newly created block and added to the array group object
     */
    @FIXVersion(introduced="4.4")
    public AltMDSourceGroup addAltMDSourceGroup() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method deletes a {@link AltMDSourceGroup} object from the existing array of <code>altMDSourceGroups</code>
     * and shrink the static array with 1 place.<br/>
     * If the array does not have the index position then a null object will be returned.)<br/>
     * This method will also update <code>noAltMDSource</code> field to the proper value.<br/>
     * @param index position in array to be deleted starting at 0
     * @return deleted block object
     */
    @FIXVersion(introduced="4.4")
    public AltMDSourceGroup deleteAltMDSourceGroup(int index) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Deletes all the {@link AltMDSourceGroup} objects from the <code>altMDSourceGroups</code> array
     * (sets the array to 0 length)<br/>
     * This method will also update <code>noAltMDSource</code> field and set it to null.<br/>
     * @return number of elements in array cleared
     */
    @FIXVersion(introduced="4.4")
    public int clearAltMDSourceGroups() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.Text)
    public String getText() {
        return text;
    }

    /**
     * Message field setter.
     * @param text field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.Text)
    public void setText(String text) {
        this.text = text;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.EncodedTextLen)
    public Integer getEncodedTextLen() {
        return encodedTextLen;
    }

    /**
     * Message field setter.
     * @param encodedTextLen field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.EncodedTextLen)
    public void setEncodedTextLen(Integer encodedTextLen) {
        this.encodedTextLen = encodedTextLen;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.EncodedText)
    public byte[] getEncodedText() {
        return encodedText;
    }

    /**
     * Message field setter.
     * @param encodedText field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.EncodedText)
    public void setEncodedText(byte[] encodedText) {
        this.encodedText = encodedText;
        if (encodedTextLen == null) {
            encodedTextLen = new Integer(encodedText.length);
        }
    }

    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

    @Override
    protected void validateRequiredTags() throws TagNotPresentException {
        StringBuilder errorMsg = new StringBuilder("Tag value(s) for");
        boolean hasMissingTag = false;
        if (mdReqID == null || mdReqID.trim().isEmpty()) {
            errorMsg.append(" [MDReqID]");
            hasMissingTag = true;
        }
        errorMsg.append(" is missing.");
        if (hasMissingTag) {
            throw new TagNotPresentException(errorMsg.toString());
        }
    }
    
    @Override
    protected byte[] encodeFragmentAll() throws TagNotPresentException, BadFormatMsgException {
        if (validateRequired) {
            validateRequiredTags();
        }
        byte[] result = new byte[0];
        ByteArrayOutputStream bao = new ByteArrayOutputStream();
        try {
            TagEncoder.encode(bao, TagNum.MDReqID, mdReqID);
            if (mdReqRejReason != null) {
                TagEncoder.encode(bao, TagNum.MDReqRejReason, mdReqRejReason.getValue());
            }
            if (parties != null) {
                bao.write(parties.encode(MsgSecureType.ALL_FIELDS));
            }
            if (noAltMDSource != null) {
                TagEncoder.encode(bao, TagNum.NoAltMDSource, noAltMDSource);
                if (altMDSourceGroups != null && altMDSourceGroups.length == noAltMDSource.intValue()) {
                    for (int i = 0; i < noAltMDSource.intValue(); i++) {
                        if (altMDSourceGroups[i] != null) {
                            bao.write(altMDSourceGroups[i].encode(MsgSecureType.ALL_FIELDS));
                        }
                    }
                } else {
                    String error = "AltMDSourceGroups field has been set but there is no data or the number of groups does not match.";
                    LOGGER.severe(error);
                    throw new BadFormatMsgException(SessionRejectReason.IncorrectCountForGroups,
                            TagNum.NoAltMDSource.getValue(), error);
                }
            }
            TagEncoder.encode(bao, TagNum.Text, text);
            if (encodedTextLen != null && encodedTextLen.intValue() > 0) {
                if (encodedText != null && encodedText.length > 0) {
                    encodedTextLen = new Integer(encodedText.length);
                    TagEncoder.encode(bao, TagNum.EncodedTextLen, encodedTextLen);
                    TagEncoder.encode(bao, TagNum.EncodedText, encodedText);
                }
            }
            result = bao.toByteArray();
        } catch (IOException ex) {
            String error = "Error writing to the byte array.";
            LOGGER.log(Level.SEVERE, "{0} Error was : {1}", new Object[] { error, ex.toString() });
            throw new BadFormatMsgException(error, ex);
        }
        
        return result;
    }
    
    @Override
    protected byte[] encodeFragmentSecured(boolean secured) throws TagNotPresentException, BadFormatMsgException {
        if (secured) {
            return new byte[0];
        } else {
            return encodeFragmentAll();
        }
    }
    
    @Override
    protected void setFragmentTagValue(Tag tag) throws BadFormatMsgException {
        TagNum tagNum = TagNum.fromString(tag.tagNum);
        switch (tagNum) {
            case MDReqID:
                mdReqID = new String(tag.value, sessionCharset);
                break;

            case MDReqRejReason:
                mdReqRejReason = MDReqRejReason.valueFor(new String(tag.value, sessionCharset).charAt(0));
                break;

            case Text:
                text = new String(tag.value, sessionCharset);
                break;

            case NoAltMDSource:
                noAltMDSource = new Integer(new String(tag.value, sessionCharset));
                break;

            default:
                String error = "Tag value [" + tag.tagNum + "] not present in MarketDataRequestReject.";
                LOGGER.severe(error);
                throw new BadFormatMsgException(SessionRejectReason.InvalidTagNumber, getHeader().getMsgType(),
                        tag.tagNum, error);
        }
    }

    @Override
    protected ByteBuffer setFragmentDataTagValue(Tag tag, ByteBuffer message) throws BadFormatMsgException {
        ByteBuffer result = message;
        if (tag.tagNum == TagNum.EncodedTextLen.getValue()) {
            try {
                encodedTextLen = new Integer(new String(tag.value, getSessionCharset()));
            } catch (NumberFormatException ex) {
                String error = "Tag [EncodedTextLen] requires an integer value. The value set was [" + tag.value + "].";
                LOGGER.log(Level.SEVERE, "{0} Error was: {1}", new Object[] { error, ex.toString() });
                throw new BadFormatMsgException(SessionRejectReason.IncorrectDataFormat, getHeader().getMsgType(),
                        TagNum.EncodedTextLen.getValue(), error);
            }
            Tag  dataTag = MsgUtil.getNextTag(message, encodedTextLen.intValue());
            encodedText = dataTag.value;
        }

        return result;
    }

    @Override
    protected Set<Integer> getFragmentDataTags() {
        return START_DATA_TAGS;
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
        StringBuilder b = new StringBuilder("{MarketDataRequestRejectMsg=");
        b.append(header != null ? header.toString() : "");
        b.append(System.getProperty("line.separator")).append("{Body=");
        printTagValue(b, TagNum.MDReqID, mdReqID);
        printTagValue(b, TagNum.MDReqRejReason, mdReqRejReason);
        printTagValue(b, parties);
        printTagValue(b, TagNum.NoAltMDSource, noAltMDSource);
        printTagValue(b, altMDSourceGroups);
        printTagValue(b, TagNum.Text, text);
        printTagValue(b, TagNum.EncodedTextLen, encodedTextLen);
        printTagValue(b, TagNum.EncodedText, encodedText);
        b.append("}");
        b.append(trailer != null ? trailer.toString() : "").append("}");
        
        return b.toString();
    }
    
    // </editor-fold>
}
