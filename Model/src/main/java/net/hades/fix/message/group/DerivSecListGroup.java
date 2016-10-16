/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * SecListGroup.java
 *
 * $Id: DerivSecListGroup.java,v 1.2 2011-09-28 08:10:21 vrotaru Exp $
 */
package net.hades.fix.message.group;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.MsgSecureType;
import net.hades.fix.message.anno.FIXVersion;
import net.hades.fix.message.anno.TagNumRef;
import net.hades.fix.message.comp.Instrument;
import net.hades.fix.message.comp.InstrumentExtension;
import net.hades.fix.message.comp.SecondaryPriceLimits;
import net.hades.fix.message.comp.Stipulations;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.struct.Tag;
import net.hades.fix.message.type.Currency;
import net.hades.fix.message.type.ExpirationCycle;
import net.hades.fix.message.type.SessionRejectReason;
import net.hades.fix.message.type.TagNum;
import net.hades.fix.message.util.DateConverter;
import net.hades.fix.message.util.MsgUtil;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import net.hades.fix.message.util.TagEncoder;

/**
 * Market data incremental refresh group data.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 * @created 29/04/2011, 11:10:10 AM
 */
@XmlAccessorType(XmlAccessType.NONE)
public abstract class DerivSecListGroup extends Group {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.Currency.getValue(),
        TagNum.CorporateAction.getValue(),
        TagNum.NoLegs.getValue(),
        TagNum.RelSymTransactTime.getValue(),
        TagNum.Text.getValue()
    }));

    protected static final Set<Integer> START_DATA_TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.EncodedTextLen.getValue()
    }));

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">

    @Override
    public Set<Integer> getFragmentTags() {
        return TAGS;
    }

    // ACCESSORS
    //////////////////////////////////////////

    /**
     * Starting with 4.3 version.
     */
    protected Instrument instrument;
    
    /**
     * Starting with 5.0SP1 version.
     */
    protected SecondaryPriceLimits secondaryPriceLimits;

    /**
     * TagNum = 15. Starting with 4.3 version.
     */
    protected Currency currency;
    
    /**
     * TagNum = 292. Starting with 5.0SP1 version.
     */
    protected String corporateAction;
    
    /**
     * TagNum = 827. Starting with 4.4 version.
     */
    protected ExpirationCycle expirationCycle;

    /**
     * Starting with 4.4 version.
     */
    protected InstrumentExtension instrumentExtension;

    /**
     * TagNum = 555. Starting with 4.3 version.
     */
    protected Integer noLegs;

    /**
     * Starting with 4.3 version.
     */
    protected InstrmtLegDerivSecListGroup[] instrmtLegDerivSecListGroups;

    /**
     * TagNum = 1504. Starting with 5.0SP2 version.
     */
    protected Date relSymTransactTime;

    /**
     * TagNum = 336. Starting with 4.3 version.
     */
    protected String tradingSessionID;

    /**
     * TagNum = 625. Starting with 4.3 version.
     */
    protected String tradingSessionSubID;

    /**
     * TagNum = 58. Starting with 4.3 version.
     */
    protected String text;

    /**
     * TagNum = 354. Starting with 4.3 version.
     */
    protected Integer encodedTextLen;

    /**
     * TagNum = 355. Starting with 4.3 version.
     */
    protected byte[] encodedText;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public DerivSecListGroup() {
    }

    public DerivSecListGroup(FragmentContext context) {
        super(context);
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Public Methods">

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.3")
    public Instrument getInstrument() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Sets the field to the proper implementation instance.
     */
    @FIXVersion(introduced = "4.3")
    public void setInstrument() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Sets the field to null.
     */
    @FIXVersion(introduced = "4.3")
    public void clearInstrument() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "5.0SP1")
    public SecondaryPriceLimits getSecondaryPriceLimits() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }
    
    /**
     * Sets the field to the proper implementation instance.
     */
    @FIXVersion(introduced = "5.0SP1")
    public void setSecondaryPriceLimits() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }
    
    /**
     * Sets the field to null.
     */
    @FIXVersion(introduced = "5.0SP1")
    public void clearSecondaryPriceLimits() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.Currency)
    public Currency getCurrency() {
        return currency;
    }

    /**
     * Message field setter.
     * @param currency field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.Currency)
    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0SP1")
    @TagNumRef(tagNum=TagNum.CorporateAction)
    public String getCorporateAction() {
        return corporateAction;
    }

    /**
     * Message field setter.
     * @param corporateAction field value
     */
    @FIXVersion(introduced="5.0SP1")
    @TagNumRef(tagNum=TagNum.CorporateAction)
    public void setCorporateAction(String corporateAction) {
        this.corporateAction = corporateAction;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4", retired="5.0SP1")
    @TagNumRef(tagNum=TagNum.ExpirationCycle)
    public ExpirationCycle getExpirationCycle() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field setter.
     * @param expirationCycle field value
     */
    @FIXVersion(introduced="4.4", retired="5.0SP1")
    @TagNumRef(tagNum=TagNum.ExpirationCycle)
    public void setExpirationCycle(ExpirationCycle expirationCycle) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.4")
    public InstrumentExtension getInstrumentExtension() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field setter.
     */
    @FIXVersion(introduced="4.4")
    public void setInstrumentExtension() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field setter.
     */
    @FIXVersion(introduced="4.4")
    public void clearInstrumentExtension() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    public Stipulations getStipulations() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Sets the Stipulations component if used in this message to the proper implementation
     * class.
     */
    @FIXVersion(introduced="4.4")
    public void setStipulations() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Sets the Stipulations component to null.
     */
    @FIXVersion(introduced="4.4")
    public void clearStipulations() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.3")
    @TagNumRef(tagNum = TagNum.NoLegs)
    public Integer getNoLegs() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method sets the number of {@link InstrmtLegDerivSecListGroup} groups. It will also create an array
     * of {@link InstrmtLegDerivSecListGroup} objects and set the <code>instrmtLegDerivSecListGroups</code> field with this array.
     * The created objects inside the array need to be populated with data for encoding.<br/>
     * If there where already objects in <code>instrmtLegDerivSecListGroups</code> array they will be discarded.<br/>
     * @param noLegs field value
     */
    @FIXVersion(introduced = "4.3")
    @TagNumRef(tagNum = TagNum.NoLegs)
    public void setNoLegs(Integer noLegs) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter for {@link InstrmtLegDerivSecListGroup} array of groups.
     * @return field array value
     */
    @FIXVersion(introduced = "4.3")
    public InstrmtLegDerivSecListGroup[] getInstrmtLegDerivSecListGroups() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method adds a {@link InstrmtLegDerivSecListGroup} object to the existing array of <code>instrmtLegDerivSecListGroups</code>
     * and expands the static array with 1 place.<br/>
     * This method will also update <code>noLegs</code> field to the proper value.<br/>
     * Note: If the <code>setNoLegs</code> method has been called there will already be a number of objects in the
     * <code>instrmtLegDerivSecListGroups</code> array created.<br/>
     * @return newly created block and added to the array group object
     */
    @FIXVersion(introduced = "4.3")
    public InstrmtLegDerivSecListGroup addInstrmtLegDerivSecListGroup() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method deletes a {@link InstrmtLegDerivSecListGroup} object from the existing array of <code>instrmtLegDerivSecListGroups</code>
     * and shrink the static array with 1 place.<br/>
     * If the array does not have the index position then a null object will be returned.)<br/>
     * This method will also update <code>noLegs</code> field to the proper value.<br/>
     * @param index position in array to be deleted starting at 0
     * @return deleted block object
     */
    @FIXVersion(introduced = "4.3")
    public InstrmtLegDerivSecListGroup deleteInstrmtLegDerivSecListGroup(int index) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Deletes all the {@link InstrmtLegDerivSecListGroup} objects from the <code>instrmtLegDerivSecListGroups</code> array
     * (sets the array to 0 length)<br/>
     * This method will also update <code>noLegs</code> field and set it to null.<br/>
     * @return number of elements in array cleared
     */
    @FIXVersion(introduced = "4.3")
    public int clearInstrmtLegDerivSecListGroups() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "5.0SP2")
    @TagNumRef(tagNum = TagNum.RelSymTransactTime)
    public Date getRelSymTransactTime() {
        return relSymTransactTime;
    }

    /**
     * Message field setter.
     * @param relSymTransactTime field value
     */
    @FIXVersion(introduced = "5.0SP2")
    @TagNumRef(tagNum = TagNum.RelSymTransactTime)
    public void setRelSymTransactTime(Date relSymTransactTime) {
        this.relSymTransactTime = relSymTransactTime;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3", retired="5.0SP1")
    @TagNumRef(tagNum=TagNum.TradingSessionID)
    public String getTradingSessionID() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field setter.
     * @param tradingSessionID field value
     */
    @FIXVersion(introduced="4.3", retired="5.0SP1")
    @TagNumRef(tagNum=TagNum.TradingSessionID)
    public void setTradingSessionID(String tradingSessionID) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3", retired="5.0SP1")
    @TagNumRef(tagNum=TagNum.TradingSessionSubID)
    public String getTradingSessionSubID() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field setter.
     * @param tradingSessionSubID field value
     */
    @FIXVersion(introduced="4.3", retired="5.0SP1")
    @TagNumRef(tagNum=TagNum.TradingSessionSubID)
    public void setTradingSessionSubID(String tradingSessionSubID) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.3")
    @TagNumRef(tagNum = TagNum.Text)
    public String getText() {
        return text;
    }

    /**
     * Message field setter.
     * @param text field value
     */
    @FIXVersion(introduced = "4.3")
    @TagNumRef(tagNum = TagNum.Text)
    public void setText(String text) {
        this.text = text;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.3")
    @TagNumRef(tagNum = TagNum.EncodedTextLen)
    public Integer getEncodedTextLen() {
        return encodedTextLen;
    }

    /**
     * Message field setter.
     * @param encodedTextLen field value
     */
    @FIXVersion(introduced = "4.3")
    @TagNumRef(tagNum = TagNum.EncodedTextLen)
    public void setEncodedTextLen(Integer encodedTextLen) {
        this.encodedTextLen = encodedTextLen;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.3")
    @TagNumRef(tagNum = TagNum.EncodedText)
    public byte[] getEncodedText() {
        return encodedText;
    }

    /**
     * Message field setter.
     * @param encodedText field value
     */
    @FIXVersion(introduced = "4.3")
    @TagNumRef(tagNum = TagNum.EncodedText)
    public void setEncodedText(byte[] encodedText) {
        this.encodedText = encodedText;
        if (encodedTextLen == null) {
            encodedTextLen = new Integer(encodedText.length);
        }
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">
    
    @Override
    protected int getFirstTag() {
        return TagNum.Symbol.getValue();
    }

    @Override
    protected void validateRequiredTags() throws TagNotPresentException {
        StringBuilder errorMsg = new StringBuilder("Tag value(s) for");
        boolean hasMissingTag = false;
        if (instrument == null || instrument.getSymbol() == null) {
            errorMsg.append(" [Symbol]");
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
            if (instrument != null) {
                bao.write(instrument.encode(MsgSecureType.ALL_FIELDS));
            }
            if (secondaryPriceLimits != null) {
                bao.write(secondaryPriceLimits.encode(MsgSecureType.ALL_FIELDS));
            }
            if (currency != null) {
                TagEncoder.encode(bao, TagNum.Currency, currency.getValue());
            }
            TagEncoder.encode(bao, TagNum.CorporateAction, corporateAction);
            if (expirationCycle != null) {
                TagEncoder.encode(bao, TagNum.ExpirationCycle, expirationCycle.getValue());
            }
            if (instrumentExtension != null) {
                bao.write(instrumentExtension.encode(MsgSecureType.ALL_FIELDS));
            }
            if (noLegs != null && noLegs.intValue() > 0) {
                TagEncoder.encode(bao, TagNum.NoLegs, noLegs);
                if (instrmtLegDerivSecListGroups != null && instrmtLegDerivSecListGroups.length == noLegs.intValue()) {
                    for (InstrmtLegDerivSecListGroup instrumentLeg : instrmtLegDerivSecListGroups) {
                        bao.write(instrumentLeg.encode(MsgSecureType.ALL_FIELDS));
                    }
                } else {
                    String error =
                            "InstrmtLegDerivSecListGroups field has been set but there is no data or the number of components does not match.";
                    LOGGER.severe(error);
                    throw new BadFormatMsgException(SessionRejectReason.IncorrectCountForGroups, TagNum.NoLegs.getValue(), error);
                }
            }
            TagEncoder.encodeUtcTimestamp(bao, TagNum.RelSymTransactTime, relSymTransactTime);
            TagEncoder.encode(bao, TagNum.TradingSessionID, tradingSessionID);
            TagEncoder.encode(bao, TagNum.TradingSessionSubID, tradingSessionSubID);
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
            case Currency:
                currency = Currency.valueFor(new String(tag.value, sessionCharset));
                break;
                
            case CorporateAction:
                corporateAction = new String(tag.value, sessionCharset);
                break;

            case ExpirationCycle:
                expirationCycle = ExpirationCycle.valueFor(Integer.valueOf(new String(tag.value, sessionCharset)));
                break;

            case NoLegs:
                noLegs = new Integer(new String(tag.value, sessionCharset));
                break;

            case RelSymTransactTime:
                relSymTransactTime = DateConverter.parseString(new String(tag.value, sessionCharset));
                break;

            case TradingSessionID:
                tradingSessionID = new String(tag.value, sessionCharset);
                break;

            case TradingSessionSubID:
                tradingSessionSubID = new String(tag.value, sessionCharset);
                break;

            case Text:
                text = new String(tag.value, sessionCharset);
                break;

            default:
                String error = "Tag value [" + tag.tagNum + "] not present in [SecListGroup] fields.";
                LOGGER.severe(error);
                throw new BadFormatMsgException(SessionRejectReason.InvalidTagNumber, tag.tagNum, error);
        }
    }

    @Override
    protected Set<Integer> getFragmentDataTags() {
        return START_DATA_TAGS;
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
                throw new BadFormatMsgException(SessionRejectReason.IncorrectDataFormat, TagNum.EncodedTextLen.getValue(), error);
            }
            Tag  dataTag = MsgUtil.getNextTag(message, encodedTextLen.intValue());
            encodedText = dataTag.value;
        }

        return result;
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
        b.append("{DerivSecListGroup=");
        printTagValue(b, instrument);
        printTagValue(b, secondaryPriceLimits);
        printTagValue(b, TagNum.Currency, currency);
        printTagValue(b, TagNum.CorporateAction, corporateAction);
        printTagValue(b, TagNum.ExpirationCycle, expirationCycle);
        printTagValue(b, instrumentExtension);
        printTagValue(b, TagNum.NoLegs, noLegs);
        printTagValue(b, instrmtLegDerivSecListGroups);
        printUTCDateTimeTagValue(b, TagNum.RelSymTransactTime, relSymTransactTime);
        printTagValue(b, TagNum.TradingSessionID, tradingSessionID);
        printTagValue(b, TagNum.TradingSessionSubID, tradingSessionSubID);
        printTagValue(b, TagNum.Text, text);
        printTagValue(b, TagNum.EncodedTextLen, encodedTextLen);
        printTagValue(b, TagNum.EncodedText, encodedText);
        b.append("}");

        return b.toString();
    }

    // </editor-fold>
}
