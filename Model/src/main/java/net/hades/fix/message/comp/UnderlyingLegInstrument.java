/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * UnderlyingLegLegInstrument.java
 *
 * $Id: UnderlyingLegInstrument.java,v 1.1 2011-10-13 07:18:34 vrotaru Exp $
 */
package net.hades.fix.message.comp;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.MsgSecureType;
import net.hades.fix.message.anno.FIXVersion;
import net.hades.fix.message.anno.TagNumRef;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.group.UnderlyingLegSecurityAltIDGroup;
import net.hades.fix.message.struct.Tag;
import net.hades.fix.message.type.PutOrCall;
import net.hades.fix.message.type.SessionRejectReason;
import net.hades.fix.message.type.TagNum;
import net.hades.fix.message.util.DateConverter;

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
 * The UnderlyingLegLegInstrument component block.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 16/12/2008, 7:24:31 PM
 */
@XmlAccessorType(XmlAccessType.NONE)
public abstract class UnderlyingLegInstrument extends Component {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.UnderlyingLegSymbol.getValue(),
        TagNum.UnderlyingLegSymbolSfx.getValue(),
        TagNum.UnderlyingLegSecurityID.getValue(),
        TagNum.UnderlyingLegSecurityIDSource.getValue(),
        TagNum.NoUnderlyingLegSecurityAltID.getValue(),
        TagNum.UnderlyingLegCFICode.getValue(),
        TagNum.UnderlyingLegSecurityType.getValue(),
        TagNum.UnderlyingLegSecuritySubType.getValue(),
        TagNum.UnderlyingLegMaturityMonthYear.getValue(),
        TagNum.UnderlyingLegMaturityDate.getValue(),
        TagNum.UnderlyingLegMaturityTime.getValue(),
        TagNum.UnderlyingLegStrikePrice.getValue(),
        TagNum.UnderlyingStrikeCurrency.getValue(),
        TagNum.UnderlyingLegOptAttribute.getValue(),
        TagNum.UnderlyingLegPutOrCall.getValue(),
        TagNum.UnderlyingLegSecurityExchange.getValue(),
        TagNum.UnderlyingLegSecurityDesc.getValue()
    }));

    protected static final Set<Integer> START_DATA_TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
    }));

    protected static final Set<Integer> REQUIRED_TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.UnderlyingLegSymbol.getValue()
    }));

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">

    /**
     * TagNum = 1330. Starting with 5.0SP1 version.
     */
    protected String underlyingLegSymbol;

    /**
     * TagNum = 1331. Starting with 5.0SP1 version.
     */
    protected String underlyingLegSymbolSfx;

    /**
     * TagNum = 1332. Starting with 5.0SP1 version.
     */
    protected String underlyingLegSecurityID;

    /**
     * TagNum = 1333. Starting with 5.0SP1 version.
     */
    protected String underlyingLegSecurityIDSource;

    /**
     * TagNum = 1334. Starting with 5.0SP1 version.
     */
    protected Integer noUnderlyingLegSecurityAltID;

    /**
     * Starting with 5.0SP1 version.
     */
    protected UnderlyingLegSecurityAltIDGroup[] underlyingLegSecurityAltIDGroups;

    /**
     * TagNum = 1344. Starting with 5.0SP1 version.
     */
    protected String underlyingLegCFICode;

    /**
     * TagNum = 1337. Starting with 5.0SP1 version.
     */
    protected String underlyingLegSecurityType;

    /**
     * TagNum = 1338. Starting with 5.0SP1 version.
     */
    protected String underlyingLegSecuritySubType;

    /**
     * TagNum = 1339. Starting with 5.0SP1 version.
     */
    protected String underlyingLegMaturityMonthYear;

    /**
     * TagNum = 1345. Starting with 5.0SP1 version.
     */
    protected Date underlyingLegMaturityDate;

    /**
     * TagNum = 1346. Starting with 5.0SP1 version.
     */
    protected Date underlyingLegMaturityTime;

    /**
     * TagNum = 1340. Starting with 5.0SP1 version.
     */
    protected Double underlyingLegStrikePrice;

    /**
     * TagNum = 1391. Starting with 5.0SP1 version.
     */
    protected Character underlyingLegOptAttribute;

    /**
     * TagNum = 1343. Starting with 5.0SP1 version.
     */
    protected PutOrCall underlyingLegPutOrCall;

    /**
     * TagNum = 1341. Starting with 5.0SP1 version.
     */
    protected String underlyingLegSecurityExchange;

    /**
     * TagNum = 1392. Starting with 5.0SP1 version.
     */
    protected String underlyingLegSecurityDesc;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public UnderlyingLegInstrument() {
    }

    public UnderlyingLegInstrument(FragmentContext context) {
        super(context);
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
    @FIXVersion(introduced="5.0SP1")
    @TagNumRef(tagNum=TagNum.UnderlyingLegSymbol, required=true)
    public String getUnderlyingLegSymbol() {
        return underlyingLegSymbol;
    }

    /**
     * Message field setter.
     * @param underlyingLegSymbol field value
     */
    @FIXVersion(introduced="5.0SP1")
    @TagNumRef(tagNum=TagNum.UnderlyingLegSymbol, required=true)
    public void setUnderlyingLegSymbol(String underlyingLegSymbol) {
        this.underlyingLegSymbol = underlyingLegSymbol;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0SP1")
    @TagNumRef(tagNum=TagNum.UnderlyingLegSymbolSfx)
    public String getUnderlyingLegSymbolSfx() {
        return underlyingLegSymbolSfx;
    }

    /**
     * Message field setter.
     * @param underlyingLegSymbolSfx field value
     */
    @FIXVersion(introduced="5.0SP1")
    @TagNumRef(tagNum=TagNum.UnderlyingLegSymbolSfx)
    public void setUnderlyingLegSymbolSfx(String underlyingLegSymbolSfx) {
        this.underlyingLegSymbolSfx = underlyingLegSymbolSfx;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0SP1")
    @TagNumRef(tagNum=TagNum.UnderlyingLegSecurityID)
    public String getUnderlyingLegSecurityID() {
        return underlyingLegSecurityID;
    }

    /**
     * Message field setter.
     * @param underlyingLegSecurityID field value
     */
    @FIXVersion(introduced="5.0SP1")
    @TagNumRef(tagNum=TagNum.UnderlyingLegSecurityID)
    public void setUnderlyingLegSecurityID(String underlyingLegSecurityID) {
        this.underlyingLegSecurityID = underlyingLegSecurityID;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0SP1")
    @TagNumRef(tagNum=TagNum.UnderlyingLegSecurityIDSource)
    public String getUnderlyingLegSecurityIDSource() {
        return underlyingLegSecurityIDSource;
    }

    /**
     * Message field setter.
     * @param underlyingLegSecurityIDSource field value
     */
    @FIXVersion(introduced="5.0SP1")
    @TagNumRef(tagNum=TagNum.UnderlyingLegSecurityIDSource)
    public void setUnderlyingLegSecurityIDSource(String underlyingLegSecurityIDSource) {
        this.underlyingLegSecurityIDSource = underlyingLegSecurityIDSource;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0SP1")
    @TagNumRef(tagNum=TagNum.NoUnderlyingLegSecurityAltID)
    public Integer getNoUnderlyingLegSecurityAltID() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method sets the number of {@link UnderlyingLegSecurityAltIDGroup} groups. It will also create an array
     * of {@link UnderlyingLegSecurityAltIDGroup} objects and set the <code>underlyingLegSecurityAltIDGroups</code>
     * field with this array.
     * The created objects inside the array need to be populated with data for encoding.<br/>
     * If there where already objects in <code>underlyingLegSecurityAltIDGroups</code> array they will be discarded.<br/>
     * @param noUnderlyingLegSecurityAltID number of MsgTypeGroup objects
     */
    @FIXVersion(introduced="5.0SP1")
    @TagNumRef(tagNum=TagNum.NoUnderlyingLegSecurityAltID)
    public void setNoUnderlyingLegSecurityAltID(Integer noUnderlyingLegSecurityAltID) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter for <code>UnderlyingLegSecurityAltIDGroup</code> array of groups.
     * @return field array value
     */
    @FIXVersion(introduced="5.0SP1")
    public UnderlyingLegSecurityAltIDGroup[] getUnderlyingLegSecurityAltIDGroups() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method adds a {@link UnderlyingLegSecurityAltIDGroup} object to the existing array of
     * <code>underlyingLegSecurityAltIDGroups</code> and expands the static array with 1 place.<br/>
     * This method will also update <code>noUnderlyingLegSecurityAltID</code> field to the proper value.<br/>
     * Note: If the <code>setNoUnderlyingLegSecurityAltID</code> method has been called there will already be a
     * number of objects in the <code>underlyingLegSecurityAltIDGroups</code> array created.<br/>
     * @return newly created block and added to the array group object
     */
    @FIXVersion(introduced="5.0SP1")
    public UnderlyingLegSecurityAltIDGroup addUnderlyingLegSecurityAltIDGroup() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method deletes a {@link UnderlyingLegSecurityAltIDGroup} object from the existing array
     * of <code>underlyingLegSecurityAltIDGroups</code> and shrink the static array with 1 place.<br/>
     * If the array does not have the index position then a null object will be returned.)<br/>
     * This method will also update <code>noUnderlyingLegSecurityAltID</code> field to the proper value.<br/>
     * @param index position in array to be deleted starting at 0
     * @return deleted block object
     */
    @FIXVersion(introduced="5.0SP1")
    public UnderlyingLegSecurityAltIDGroup deleteUnderlyingLegSecurityAltIDGroup(int index) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Deletes all the {@link UnderlyingLegSecurityAltIDGroup} objects from the <code>underlyingLegSecurityAltIDGroups</code>
     * array (sets the array to 0 length)<br/>
     * This method will also update <code>noUnderlyingLegSecurityAltID</code> field and set it to null.<br/>
     * @return number of elements in array cleared
     */
    @FIXVersion(introduced="5.0SP1")
    public int clearUnderlyingLegSecurityAltIDGroups() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0SP1")
    @TagNumRef(tagNum=TagNum.UnderlyingLegCFICode)
    public String getUnderlyingLegCFICode() {
        return underlyingLegCFICode;
    }

    /**
     * Message field setter.
     * @param underlyingLegCFICode field value
     */
    @FIXVersion(introduced="5.0SP1")
    @TagNumRef(tagNum=TagNum.UnderlyingLegCFICode)
    public void setUnderlyingLegCFICode(String underlyingLegCFICode) {
        this.underlyingLegCFICode = underlyingLegCFICode;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0SP1")
    @TagNumRef(tagNum=TagNum.UnderlyingLegSecurityType)
    public String getUnderlyingLegSecurityType() {
        return underlyingLegSecurityType;
    }

    /**
     * Message field setter.
     * @param underlyingLegSecurityType field value
     */
    @FIXVersion(introduced="5.0SP1")
    @TagNumRef(tagNum=TagNum.UnderlyingLegSecurityType)
    public void setUnderlyingLegSecurityType(String underlyingLegSecurityType) {
        this.underlyingLegSecurityType = underlyingLegSecurityType;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0SP1")
    @TagNumRef(tagNum=TagNum.UnderlyingLegSecuritySubType)
    public String getUnderlyingLegSecuritySubType() {
        return underlyingLegSecuritySubType;
    }

    /**
     * Message field setter.
     * @param underlyingLegSecuritySubType field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.UnderlyingLegSecuritySubType)
    public void setUnderlyingLegSecuritySubType(String underlyingLegSecuritySubType) {
        this.underlyingLegSecuritySubType = underlyingLegSecuritySubType;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0SP1")
    @TagNumRef(tagNum=TagNum.UnderlyingLegMaturityMonthYear)
    public String getUnderlyingLegMaturityMonthYear() {
        return underlyingLegMaturityMonthYear;
    }

    /**
     * Message field setter.
     * @param underlyingLegMaturityMonthYear field value
     */
    @FIXVersion(introduced="5.0SP1")
    @TagNumRef(tagNum=TagNum.UnderlyingLegMaturityMonthYear)
    public void setUnderlyingLegMaturityMonthYear(String underlyingLegMaturityMonthYear) {
        this.underlyingLegMaturityMonthYear = underlyingLegMaturityMonthYear;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0SP1")
    @TagNumRef(tagNum=TagNum.UnderlyingLegMaturityDate)
    public Date getUnderlyingLegMaturityDate() {
        return underlyingLegMaturityDate;
    }

    /**
     * Message field setter.
     * @param underlyingLegMaturityDate field value
     */
    @FIXVersion(introduced="5.0SP1")
    @TagNumRef(tagNum=TagNum.UnderlyingLegMaturityDate)
    public void setUnderlyingLegMaturityDate(Date underlyingLegMaturityDate) {
        this.underlyingLegMaturityDate = underlyingLegMaturityDate;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0SP1")
    @TagNumRef(tagNum=TagNum.UnderlyingLegMaturityTime)
    public Date getUnderlyingLegMaturityTime() {
        return underlyingLegMaturityTime;
    }

    /**
     * Message field setter.
     * @param underlyingLegMaturityTime field value
     */
    @FIXVersion(introduced="5.0SP1")
    @TagNumRef(tagNum=TagNum.UnderlyingLegMaturityTime)
    public void setUnderlyingLegMaturityTime(Date underlyingLegMaturityTime) {
        this.underlyingLegMaturityTime = underlyingLegMaturityTime;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0SP1")
    @TagNumRef(tagNum=TagNum.UnderlyingLegStrikePrice)
    public Double getUnderlyingLegStrikePrice() {
        return underlyingLegStrikePrice;
    }

    /**
     * Message field setter.
     * @param underlyingLegStrikePrice field value
     */
    @FIXVersion(introduced="5.0SP1")
    @TagNumRef(tagNum=TagNum.UnderlyingLegStrikePrice)
    public void setUnderlyingLegStrikePrice(Double underlyingLegStrikePrice) {
        this.underlyingLegStrikePrice = underlyingLegStrikePrice;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0SP1")
    @TagNumRef(tagNum=TagNum.UnderlyingLegOptAttribute)
    public Character getUnderlyingLegOptAttribute() {
        return underlyingLegOptAttribute;
    }

    /**
     * Message field setter.
     * @param underlyingLegOptAttribute field value
     */
    @FIXVersion(introduced="5.0SP1")
    @TagNumRef(tagNum=TagNum.UnderlyingLegOptAttribute)
    public void setUnderlyingLegOptAttribute(Character underlyingLegOptAttribute) {
        this.underlyingLegOptAttribute = underlyingLegOptAttribute;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0SP1")
    @TagNumRef(tagNum=TagNum.UnderlyingLegPutOrCall)
    public PutOrCall getUnderlyingLegPutOrCall() {
        return underlyingLegPutOrCall;
    }

    /**
     * Message field setter.
     * @param underlyingLegPutOrCall field value
     */
    @FIXVersion(introduced="5.0SP1")
    @TagNumRef(tagNum=TagNum.UnderlyingLegPutOrCall)
    public void setUnderlyingLegPutOrCall(PutOrCall underlyingLegPutOrCall) {
        this.underlyingLegPutOrCall = underlyingLegPutOrCall;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0SP1")
    @TagNumRef(tagNum=TagNum.UnderlyingLegSecurityExchange)
    public String getUnderlyingLegSecurityExchange() {
        return underlyingLegSecurityExchange;
    }

    /**
     * Message field setter.
     * @param underlyingLegSecurityExchange field value
     */
    @FIXVersion(introduced="5.0SP1")
    @TagNumRef(tagNum=TagNum.UnderlyingLegSecurityExchange)
    public void setUnderlyingLegSecurityExchange(String underlyingLegSecurityExchange) {
        this.underlyingLegSecurityExchange = underlyingLegSecurityExchange;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0SP1")
    @TagNumRef(tagNum=TagNum.UnderlyingLegSecurityDesc)
    public String getUnderlyingLegSecurityDesc() {
        return underlyingLegSecurityDesc;
    }

    /**
     * Message field setter.
     * @param underlyingLegSecurityDesc field value
     */
    @FIXVersion(introduced="5.0SP1")
    @TagNumRef(tagNum=TagNum.UnderlyingLegSecurityDesc)
    public void setUnderlyingLegSecurityDesc(String underlyingLegSecurityDesc) {
        this.underlyingLegSecurityDesc = underlyingLegSecurityDesc;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

    @Override
    protected int getFirstTag() {
        return TagNum.UnderlyingLegSymbol.getValue();
    }

    @Override
    protected void validateRequiredTags() throws TagNotPresentException {
        StringBuilder errorMsg = new StringBuilder("Tag value(s) for");
        boolean hasMissingTag = false;
         if (underlyingLegSymbol == null) {
            errorMsg.append(" [UnderlyingLegSymbol]");
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
            TagEncoder.encode(bao, TagNum.UnderlyingLegSymbol, underlyingLegSymbol);
            TagEncoder.encode(bao, TagNum.UnderlyingLegSymbolSfx, underlyingLegSymbolSfx);
            TagEncoder.encode(bao, TagNum.UnderlyingLegSecurityID, underlyingLegSecurityID);
            TagEncoder.encode(bao, TagNum.UnderlyingLegSecurityIDSource, underlyingLegSecurityIDSource);
            if (noUnderlyingLegSecurityAltID != null && noUnderlyingLegSecurityAltID.intValue() > 0) {
                TagEncoder.encode(bao, TagNum.NoUnderlyingLegSecurityAltID, noUnderlyingLegSecurityAltID);
                if (underlyingLegSecurityAltIDGroups != null && underlyingLegSecurityAltIDGroups.length == noUnderlyingLegSecurityAltID.intValue()) {
                    for (int i = 0; i < noUnderlyingLegSecurityAltID.intValue(); i++) {
                        if (underlyingLegSecurityAltIDGroups[i] != null) {
                            bao.write(underlyingLegSecurityAltIDGroups[i].encode(MsgSecureType.ALL_FIELDS));
                        }
                    }
                } else {
                    String error = "UnderlyingLegSecurityAltIDGroup field has been set but there is no data or the number of groups does not match.";
                    LOGGER.severe(error);
                    throw new BadFormatMsgException(SessionRejectReason.IncorrectCountForGroups,
                        TagNum.NoUnderlyingLegSecurityAltID.getValue(), error);
                }
            }
            TagEncoder.encode(bao, TagNum.UnderlyingLegCFICode, underlyingLegCFICode);
            TagEncoder.encode(bao, TagNum.UnderlyingLegSecurityType, underlyingLegSecurityType);
            TagEncoder.encode(bao, TagNum.UnderlyingLegSecuritySubType, underlyingLegSecuritySubType);
            TagEncoder.encode(bao, TagNum.UnderlyingLegMaturityMonthYear, underlyingLegMaturityMonthYear);
            TagEncoder.encodeDate(bao, TagNum.UnderlyingLegMaturityDate, underlyingLegMaturityDate);
            TagEncoder.encodeTZTime(bao, TagNum.UnderlyingLegMaturityTime, underlyingLegMaturityTime);
            TagEncoder.encode(bao, TagNum.UnderlyingLegStrikePrice, underlyingLegStrikePrice);
            TagEncoder.encode(bao, TagNum.UnderlyingLegOptAttribute, underlyingLegOptAttribute);
            if (underlyingLegPutOrCall != null) {
                TagEncoder.encode(bao, TagNum.UnderlyingLegPutOrCall, underlyingLegPutOrCall.getValue());
            }
            TagEncoder.encode(bao, TagNum.UnderlyingLegSecurityExchange, underlyingLegSecurityExchange);
            TagEncoder.encode(bao, TagNum.UnderlyingLegSecurityDesc, underlyingLegSecurityDesc, sessionCharset);
 
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
            case UnderlyingLegSymbol:
                underlyingLegSymbol = new String(tag.value, sessionCharset);
                break;

            case UnderlyingLegSymbolSfx:
                underlyingLegSymbolSfx = new String(tag.value, sessionCharset);
                break;

            case UnderlyingLegSecurityID:
                underlyingLegSecurityID = new String(tag.value, sessionCharset);
                break;

            case UnderlyingLegSecurityIDSource:
                underlyingLegSecurityIDSource = new String(tag.value, sessionCharset);
                break;

            case NoUnderlyingLegSecurityAltID:
                noUnderlyingLegSecurityAltID = new Integer(new String(tag.value, sessionCharset));
                break;

            case UnderlyingLegCFICode:
                underlyingLegCFICode = new String(tag.value, sessionCharset);
                break;

            case UnderlyingLegSecurityType:
                underlyingLegSecurityType = new String(tag.value, sessionCharset);
                break;

            case UnderlyingLegSecuritySubType:
                underlyingLegSecuritySubType = new String(tag.value, sessionCharset);
                break;

            case UnderlyingLegMaturityMonthYear:
                underlyingLegMaturityMonthYear = new String(tag.value, sessionCharset);
                break;

            case UnderlyingLegMaturityDate:
                underlyingLegMaturityDate = DateConverter.parseString(new String(tag.value, sessionCharset));
                break;

            case UnderlyingLegMaturityTime:
                underlyingLegMaturityTime = DateConverter.parseString(new String(tag.value, sessionCharset));
                break;

            case UnderlyingLegStrikePrice:
                underlyingLegStrikePrice = new Double(new String(tag.value, sessionCharset));
                break;

            case UnderlyingLegOptAttribute:
                underlyingLegOptAttribute = new Character(new String(tag.value, sessionCharset).charAt(0));
                break;

            case UnderlyingLegPutOrCall:
                underlyingLegPutOrCall = PutOrCall.valueFor(Integer.valueOf(new String(tag.value, sessionCharset)));
                break;

            case UnderlyingLegSecurityExchange:
                underlyingLegSecurityExchange = new String(tag.value, sessionCharset);
                break;

            case UnderlyingLegSecurityDesc:
                underlyingLegSecurityDesc = new String(tag.value, sessionCharset);
                break;

            default:
                String error = "Tag value [" + tag.tagNum + "] not present in [UnderlyingLegInstrument] fields.";
                LOGGER.severe(error);
                throw new BadFormatMsgException(SessionRejectReason.InvalidTagNumber, tag.tagNum, error);
        }
    }

    @Override
    protected ByteBuffer setFragmentDataTagValue(Tag tag, ByteBuffer message) throws BadFormatMsgException {
        return message;
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
        StringBuilder b = new StringBuilder(System.getProperty("line.separator"));
        b.append("{UnderlyingLegInstrument=");
        printTagValue(b, TagNum.UnderlyingLegSymbol, underlyingLegSymbol);
        printTagValue(b, TagNum.UnderlyingLegSymbolSfx, underlyingLegSymbolSfx);
        printTagValue(b, TagNum.UnderlyingLegSecurityID, underlyingLegSecurityID);
        printTagValue(b, TagNum.UnderlyingLegSecurityIDSource, underlyingLegSecurityIDSource);
        printTagValue(b, TagNum.NoUnderlyingLegSecurityAltID, noUnderlyingLegSecurityAltID);
        printTagValue(b, underlyingLegSecurityAltIDGroups);
        printTagValue(b, TagNum.UnderlyingLegCFICode, underlyingLegCFICode);
        printTagValue(b, TagNum.UnderlyingLegSecurityType, underlyingLegSecurityType);
        printTagValue(b, TagNum.UnderlyingLegSecuritySubType, underlyingLegSecuritySubType);
        printTagValue(b, TagNum.UnderlyingLegMaturityMonthYear, underlyingLegMaturityMonthYear);
        printDateTagValue(b, TagNum.UnderlyingLegMaturityDate, underlyingLegMaturityDate);
        printUTCTimeTagValue(b, TagNum.UnderlyingLegMaturityTime, underlyingLegMaturityTime);
        printTagValue(b, TagNum.UnderlyingLegStrikePrice, underlyingLegStrikePrice);
        printTagValue(b, TagNum.UnderlyingLegOptAttribute, underlyingLegOptAttribute);
        printTagValue(b, TagNum.UnderlyingLegPutOrCall, underlyingLegPutOrCall);
        printTagValue(b, TagNum.UnderlyingLegSecurityExchange, underlyingLegSecurityExchange);
        printTagValue(b, TagNum.UnderlyingLegSecurityDesc, underlyingLegSecurityDesc);
        b.append("}");

        return b.toString();
    }

    // </editor-fold>
}
