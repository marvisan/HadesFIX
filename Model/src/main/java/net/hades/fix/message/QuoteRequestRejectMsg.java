/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * QuoteRequestRejectMsg.java
 *
 * $Id: QuoteRequestRejectMsg.java,v 1.11 2011-04-28 10:07:44 vrotaru Exp $
 */
package net.hades.fix.message;

import net.hades.fix.message.anno.FIXVersion;
import net.hades.fix.message.struct.Tag;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import net.hades.fix.message.anno.TagNumRef;
import net.hades.fix.message.comp.RootParties;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.group.QuoteRequestRejectGroup;
import net.hades.fix.message.type.ApplVerID;
import net.hades.fix.message.type.BeginString;
import net.hades.fix.message.type.MsgType;
import net.hades.fix.message.type.RespondentType;
import net.hades.fix.message.type.SessionRejectReason;
import net.hades.fix.message.type.TagNum;
import net.hades.fix.message.util.BooleanConverter;
import net.hades.fix.message.util.MsgUtil;
import net.hades.fix.message.util.TagEncoder;

/**
 * <p>Extracted from FIX protocol documentation <a href="http://www.fixprotocol.org/">FIX Protocol</a></p>
 * The Quote Request Reject message is used to reject Quote Request messages for all quoting models.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.11 $
 * @created 01/04/2009, 8:34:33 AM
 */
@XmlAccessorType(XmlAccessType.NONE)
public abstract class QuoteRequestRejectMsg extends FIXMsg  {
    
    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = -2952157924439599749L;

    protected static final Set<Integer> TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.QuoteReqID.getValue(),
        TagNum.RFQReqID.getValue(),
        TagNum.QuoteRequestRejectReason.getValue(),
        TagNum.PrivateQuote.getValue(),
        TagNum.RespondentType.getValue(),
        TagNum.PreTradeAnonymity.getValue(),
        TagNum.NoRelatedSym.getValue(),
        TagNum.Text.getValue()
    }));

    protected static final Set<Integer> START_DATA_TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.EncodedTextLen.getValue()
    }));

    protected static final Set<Integer> REQUIRED_TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.QuoteReqID.getValue(),
        TagNum.QuoteRequestRejectReason.getValue()
    }));

    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Static Block">
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Attributes">
    
    /**
     * TagNum = 131 REQUIRED. Starting with 4.0 version.
     */
    protected String quoteReqID;

    /**
     * TagNum = 644. Starting with 4.3 version.
     */
    protected String rfqReqID;

    /**
     * TagNum = 658 REQUIRED. Starting with 4.3 version.
     */
    protected Integer quoteRequestRejectReason;

    /**
     * TagNum = 1171. Starting with 5.0SP1 version.
     */
    protected Boolean privateQuote;

    /**
     * TagNum = 1172. Starting with 5.0SP1 version.
     */
    protected RespondentType respondentType;

    /**
     * TagNum = 1091. Starting with 5.0SP1 version.
     */
    protected Boolean preTradeAnonymity;

    /**
     * Starting with 5.0SP1 version.
     */
    protected RootParties rootParties;

    /**
     * TagNum = 146 REQUIRED. Starting with 4.3 version.
     */
    protected Integer noRelatedSym;

    /**
     * Starting with 4.3 version.
     */
    protected QuoteRequestRejectGroup[] quoteRequestRejectGroups;

    /**
     * TagNum = 58. Starting with 4.0 version.
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
    
    public QuoteRequestRejectMsg() {
        super();
    }
    
    public QuoteRequestRejectMsg(Header header, ByteBuffer rawMsg)
        throws InvalidMsgException, TagNotPresentException, BadFormatMsgException {
        super(header, rawMsg);
    }

    public QuoteRequestRejectMsg(BeginString beginString) throws InvalidMsgException {
        super(MsgType.QuoteRequestReject.getValue(), beginString);
    }
    
    public QuoteRequestRejectMsg(BeginString beginString, ApplVerID applVerID) throws InvalidMsgException {
        super(MsgType.QuoteRequestReject.getValue(), beginString, applVerID);
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
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.QuoteReqID, required=true)
    public String getQuoteReqID() {
        return quoteReqID;
    }

    /**
     * Message field setter.
     * @param quoteReqID field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.QuoteReqID, required=true)
    public void setQuoteReqID(String quoteReqID) {
        this.quoteReqID = quoteReqID;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.RFQReqID)
    public String getRfqReqID() {
        return rfqReqID;
    }

    /**
     * Message field setter.
     * @param rfqReqID field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.RFQReqID)
    public void setRfqReqID(String rfqReqID) {
        this.rfqReqID = rfqReqID;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.QuoteRequestRejectReason)
    public Integer getQuoteRequestRejectReason() {
        return quoteRequestRejectReason;
    }

    /**
     * Message field setter.
     * @param quoteRequestRejectReason field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.QuoteRequestRejectReason)
    public void setQuoteRequestRejectReason(Integer quoteRequestRejectReason) {
        this.quoteRequestRejectReason = quoteRequestRejectReason;
    }


    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0SP1")
    @TagNumRef(tagNum=TagNum.PrivateQuote)
    public Boolean getPrivateQuote() {
        return privateQuote;
    }

    /**
     * Message field setter.
     * @param privateQuote field value
     */
    @FIXVersion(introduced="5.0SP1")
    @TagNumRef(tagNum=TagNum.PrivateQuote)
    public void setPrivateQuote(Boolean privateQuote) {
        this.privateQuote = privateQuote;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0SP1")
    @TagNumRef(tagNum=TagNum.RespondentType)
    public RespondentType getRespondentType() {
        return respondentType;
    }

    /**
     * Message field setter.
     * @param respondentType field value
     */
    @FIXVersion(introduced="5.0SP1")
    @TagNumRef(tagNum=TagNum.RespondentType)
    public void setRespondentType(RespondentType respondentType) {
        this.respondentType = respondentType;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0SP1")
    @TagNumRef(tagNum=TagNum.PreTradeAnonymity)
    public Boolean getPreTradeAnonymity() {
        return preTradeAnonymity;
    }

    /**
     * Message field setter.
     * @param preTradeAnonymity field value
     */
    @FIXVersion(introduced="5.0SP1")
    @TagNumRef(tagNum=TagNum.PreTradeAnonymity)
    public void setPreTradeAnonymity(Boolean preTradeAnonymity) {
        this.preTradeAnonymity = preTradeAnonymity;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0SP1")
    public RootParties getRootParties() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Sets the RootParties component class to the proper implementation.
     */
    @FIXVersion(introduced="5.0SP1")
    public void setRootParties() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Sets the RootParties component to null.
     */
    @FIXVersion(introduced="5.0SP1")
    public void clearRootParties() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.3")
    @TagNumRef(tagNum = TagNum.NoRelatedSym)
    public Integer getNoRelatedSym() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method sets the number of {@link QuoteRequestRejectGroup} groups. It will also create an array
     * of {@link QuoteRequestRejectGroup} objects and set the <code>quoteRequestRejectGroups</code> field with this array.
     * The created objects inside the array need to be populated with data for encoding.<br/>
     * If there where already objects in <code>quoteRequestRejectGroups</code> array they will be discarded.<br/>
     * @param noRelatedSym field value
     */
    @FIXVersion(introduced = "4.3")
    @TagNumRef(tagNum = TagNum.NoRelatedSym)
    public void setNoRelatedSym(Integer noRelatedSym) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter for {@link QuoteRequestRejectGroup} array of groups.
     * @return field array value
     */
    @FIXVersion(introduced = "4.3")
    public QuoteRequestRejectGroup[] getQuoteRequestRejectGroups() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method adds a {@link QuoteRequestRejectGroup} object to the existing array of <code>quoteRequestRejectGroups</code>
     * and expands the static array with 1 place.<br/>
     * This method will also update <code>noRelatedSym</code> field to the proper value.<br/>
     * Note: If the <code>setNoRelatedSym</code> method has been called there will already be a number of objects in the
     * <code>quoteRequestRejectGroups</code> array created.<br/>
     * @return newly created block and added to the array group object
     */
    @FIXVersion(introduced="4.3")
    public QuoteRequestRejectGroup addQuoteRequestRejectGroup() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method deletes a {@link QuoteRequestRejectGroup} object from the existing array of <code>quoteRequestRejectGroups</code>
     * and shrink the static array with 1 place.<br/>
     * If the array does not have the index position then a null object will be returned.)<br/>
     * This method will also update <code>noRelatedSym</code> field to the proper value.<br/>
     * @param index position in array to be deleted starting at 0
     * @return deleted block object
     */
    @FIXVersion(introduced="4.3")
    public QuoteRequestRejectGroup deleteQuoteRequestRejectGroup(int index) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Deletes all the {@link QuoteRequestRejectGroup} objects from the <code>quoteRequestRejectGroups</code> array
     * (sets the array to 0 length)<br/>
     * This method will also update <code>noRelatedSym</code> field and set it to null.<br/>
     * @return number of elements in array cleared
     */
    @FIXVersion(introduced="4.3")
    public int clearQuoteRequestRejectGroups() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.Text)
    public String getText() {
        return text;
    }

    /**
     * Message field setter.
     * @param text field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.Text)
    public void setText(String text) {
        this.text = text;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.EncodedTextLen)
    public Integer getEncodedTextLen() {
        return encodedTextLen;
    }

    /**
     * Message field setter.
     * @param encodedTextLen field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.EncodedTextLen)
    public void setEncodedTextLen(Integer encodedTextLen) {
        this.encodedTextLen = encodedTextLen;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.EncodedText)
    public byte[] getEncodedText() {
        return encodedText;
    }

    /**
     * Message field setter.
     * @param encodedText field value
     */
    @FIXVersion(introduced="4.3")
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

        if (quoteReqID == null || quoteReqID.trim().isEmpty()) {
            errorMsg.append(" [QuoteReqID]");
            hasMissingTag = true;
        }
        if (quoteRequestRejectReason == null) {
            errorMsg.append(" [QuoteRequestRejectReason]");
            hasMissingTag = true;
        }
        if (noRelatedSym == null || quoteRequestRejectGroups == null || quoteRequestRejectGroups.length == 0) {
            errorMsg.append(" [Instrument]");
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
            TagEncoder.encode(bao, TagNum.QuoteReqID, quoteReqID);
            TagEncoder.encode(bao, TagNum.RFQReqID, rfqReqID);
            TagEncoder.encode(bao, TagNum.QuoteRequestRejectReason, quoteRequestRejectReason);
            TagEncoder.encode(bao, TagNum.PrivateQuote, privateQuote);
            if (respondentType != null) {
                TagEncoder.encode(bao, TagNum.RespondentType, respondentType.getValue());
            }
            TagEncoder.encode(bao, TagNum.PreTradeAnonymity, preTradeAnonymity);
            if (rootParties != null) {
                bao.write(rootParties.encode(MsgSecureType.ALL_FIELDS));
            }
            if (noRelatedSym != null) {
                TagEncoder.encode(bao, TagNum.NoRelatedSym, noRelatedSym);
                if (quoteRequestRejectGroups != null && quoteRequestRejectGroups.length == noRelatedSym.intValue()) {
                    for (int i = 0; i < noRelatedSym.intValue(); i++) {
                        if (quoteRequestRejectGroups[i] != null) {
                            bao.write(quoteRequestRejectGroups[i].encode(MsgSecureType.ALL_FIELDS));
                        }
                    }
                } else {
                    String error = "QuoteRequestRejectGroups field has been set but there is no data or the number of groups does not match.";
                    LOGGER.severe(error);
                    throw new BadFormatMsgException(SessionRejectReason.IncorrectCountForGroups, getHeader().getMsgType(),
                        TagNum.NoRelatedSym.getValue(), error);
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
            case QuoteReqID:
                quoteReqID = new String(tag.value, getSessionCharset());
                break;

            case RFQReqID:
                rfqReqID = new String(tag.value, getSessionCharset());
                break;

            case QuoteRequestRejectReason:
                quoteRequestRejectReason = new Integer(new String(tag.value, getSessionCharset()));
                break;

            case PrivateQuote:
                privateQuote = BooleanConverter.parse(new String(tag.value, sessionCharset));
                break;

            case RespondentType:
                respondentType = RespondentType.valueFor(Integer.valueOf(new String(tag.value, getSessionCharset())));
                break;

            case PreTradeAnonymity:
                preTradeAnonymity = BooleanConverter.parse(new String(tag.value, sessionCharset));
                break;

            case NoRelatedSym:
                noRelatedSym = new Integer(new String(tag.value, getSessionCharset()));
                break;

            case Text:
                text = new String(tag.value, getSessionCharset());
                break;

            default:
                String error = "Tag value [" + tag.tagNum + "] not present in QuoteRequestRejectMsg.";
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
        StringBuilder b = new StringBuilder("{QuoteRequestRejectMsg=");
        b.append(header != null ? header.toString() : "");
        b.append(System.getProperty("line.separator")).append("{Body=");
        printTagValue(b, TagNum.QuoteReqID, quoteReqID);
        printTagValue(b, TagNum.RFQReqID, rfqReqID);
        printTagValue(b, TagNum.QuoteRequestRejectReason, quoteRequestRejectReason);
        printTagValue(b, TagNum.PrivateQuote, privateQuote);
        printTagValue(b, TagNum.RespondentType, respondentType);
        printTagValue(b, TagNum.PreTradeAnonymity, preTradeAnonymity);
        printTagValue(b, rootParties);
        printTagValue(b, TagNum.NoRelatedSym, noRelatedSym);
        printTagValue(b, quoteRequestRejectGroups);
        printTagValue(b, TagNum.Text, text);
        printTagValue(b, TagNum.EncodedTextLen, encodedTextLen);
        printTagValue(b, TagNum.EncodedText, encodedText);
        b.append("}");
        b.append(trailer != null ? trailer.toString() : "").append("}");
        
        return b.toString();
    }
    
    // </editor-fold>
}
