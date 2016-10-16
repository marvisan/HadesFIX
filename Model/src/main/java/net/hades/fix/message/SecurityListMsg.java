/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * SecurityDefinitionMsg.java
 *
 * $Id: SecurityListMsg.java,v 1.2 2011-04-29 03:11:02 vrotaru Exp $
 */
package net.hades.fix.message;

import net.hades.fix.message.anno.FIXVersion;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.group.SecListGroup;
import net.hades.fix.message.struct.Tag;
import net.hades.fix.message.util.BooleanConverter;
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

import net.hades.fix.message.anno.TagNumRef;
import net.hades.fix.message.comp.ApplicationSequenceControl;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.type.ApplVerID;
import net.hades.fix.message.type.BeginString;
import net.hades.fix.message.type.MsgType;
import net.hades.fix.message.type.SecurityListType;
import net.hades.fix.message.type.SecurityListTypeSource;
import net.hades.fix.message.type.SecurityRequestResult;
import net.hades.fix.message.type.SessionRejectReason;
import net.hades.fix.message.type.TagNum;
import net.hades.fix.message.util.DateConverter;
import net.hades.fix.message.util.TagEncoder;

/**
 * <p>Extracted from FIX protocol documentation <a href="http://www.fixprotocol.org/">FIX Protocol</a></p>
 * The Security List message is used to return a list of securities that matches the criteria specified in a
 * Security List Request.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 * @created 21/04/2009, 9:25:04 AM
 */
@XmlAccessorType(XmlAccessType.NONE)
public abstract class SecurityListMsg extends FIXMsg {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.SecurityReportID.getValue(),
        TagNum.ClearingBusinessDate.getValue(),
        TagNum.SecurityListID.getValue(),
        TagNum.SecurityListRefID.getValue(),
        TagNum.SecurityListDesc.getValue(),
        TagNum.SecurityListType.getValue(),
        TagNum.SecurityListTypeSource.getValue(),
        TagNum.SecurityReqID.getValue(),
        TagNum.SecurityResponseID.getValue(),
        TagNum.SecurityRequestResult.getValue(),
        TagNum.TransactTime.getValue(),
        TagNum.TotNoRelatedSym.getValue(),
        TagNum.MarketID.getValue(),
        TagNum.MarketSegmentID.getValue(),
        TagNum.LastFragment.getValue(),
        TagNum.NoRelatedSym.getValue()
    }));

    protected static final Set<Integer> START_DATA_TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.EncodedSecurityListDescLen.getValue()
    }));

    protected static final Set<Integer> REQUIRED_TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.SecurityReqID.getValue(),
        TagNum.SecurityListRequestType.getValue(),
        TagNum.SecurityRequestResult.getValue()
    }));

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">

    /**
     * Starting with 5.0SP1 version.
     */
    protected ApplicationSequenceControl applicationSequenceControl;

    /**
     * TagNum = 964. Starting with 5.0 version.
     */
    protected Integer securityReportID;

    /**
     * TagNum = 715. Starting with 5.0 version.
     */
    protected Date clearingBusinessDate;

    /**
     * TagNum = 1465. Starting with 5.0SP2 version.
     */
    protected String securityListID;

    /**
     * TagNum = 1466. Starting with 5.0SP2 version.
     */
    protected String securityListRefID;

    /**
     * TagNum = 1467. Starting with 5.0SP2 version.
     */
    protected String securityListDesc;

    /**
     * TagNum = 1468. Starting with 5.0SP2 version.
     */
    protected Integer encodedSecurityListDescLen;

    /**
     * TagNum = 1469. Starting with 5.0SP2 version.
     */
    protected byte[] encodedSecurityListDesc;

    /**
     * TagNum = 1470. Starting with 5.0SP2 version.
     */
    protected SecurityListType securityListType;

    /**
     * TagNum = 1471. Starting with 5.0SP2 version.
     */
    protected SecurityListTypeSource securityListTypeSource;

    /**
     * TagNum = 330 REQUIRED. Starting with 4.3 version.
     */
    protected String securityReqID;

    /**
     * TagNum = 321 REQUIRED. Starting with 4.4 version.
     */
    protected String securityResponseID;

    /**
     * TagNum = 321 REQUIRED. Starting with 4.3 version.
     */
    protected SecurityRequestResult securityRequestResult;

    /**
     * TagNum = 60. Starting with 5.0SP2 version.
     */
    protected Date transactTime;

    /**
     * TagNum = 393. Starting with 4.2 version.
     */
    protected Integer totNoRelatedSym;

    /**
     * TagNum = 1301. Starting with 5.0SP1 version.
     */
    protected String marketID;

    /**
     * TagNum = 1300. Starting with 5.0SP1 version.
     */
    protected String marketSegmentID;

    /**
     * TagNum = 893. Starting with 4.4 version.
     */
    protected Boolean lastFragment;

    /**
     * TagNum = 146. Starting with 4.3 version.
     */
    protected Integer noRelatedSyms;

    /**
     * Starting with 4.3 version.
     */
    protected SecListGroup[] secListGroups;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public SecurityListMsg() {
        super();
    }

    public SecurityListMsg(Header header, ByteBuffer rawMsg)
    throws InvalidMsgException, TagNotPresentException, BadFormatMsgException {
        super(header, rawMsg);
    }

    public SecurityListMsg(BeginString beginString) throws InvalidMsgException {
        super(MsgType.SecurityList.getValue(), beginString);
    }

    public SecurityListMsg(BeginString beginString, ApplVerID applVerID) throws InvalidMsgException {
        super(MsgType.SecurityList.getValue(), beginString, applVerID);
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
    public ApplicationSequenceControl getApplicationSequenceControl() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Sets the ApplicationSequenceControl component if used in this message to the proper implementation
     * class.
     */
    @FIXVersion(introduced="5.0SP1")
    public void setApplicationSequenceControl() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Clear the ApplicationSequenceControl component.
     */
    @FIXVersion(introduced="5.0SP1")
    public void clearApplicationSequenceControl() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.SecurityReportID)
    public Integer getSecurityReportID() {
        return securityReportID;
    }

    /**
     * Message field setter.
     * @param securityReportID field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.SecurityReportID)
    public void setSecurityReportID(Integer securityReportID) {
        this.securityReportID = securityReportID;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.ClearingBusinessDate)
    public Date getClearingBusinessDate() {
        return clearingBusinessDate;
    }

    /**
     * Message field setter.
     * @param clearingBusinessDate field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.ClearingBusinessDate)
    public void setClearingBusinessDate(Date clearingBusinessDate) {
        this.clearingBusinessDate = clearingBusinessDate;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0SP2")
    @TagNumRef(tagNum=TagNum.SecurityListID)
    public String getSecurityListID() {
        return securityListID;
    }

    /**
     * Message field setter.
     * @param securityListID field value
     */
    @FIXVersion(introduced="5.0SP2")
    @TagNumRef(tagNum=TagNum.SecurityListID)
    public void setSecurityListID(String securityListID) {
        this.securityListID = securityListID;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0SP2")
    @TagNumRef(tagNum=TagNum.SecurityListRefID)
    public String getSecurityListRefID() {
        return securityListRefID;
    }

    /**
     * Message field setter.
     * @param securityListRefID field value
     */
    @FIXVersion(introduced="5.0SP2")
    @TagNumRef(tagNum=TagNum.SecurityListRefID)
    public void setSecurityListRefID(String securityListRefID) {
        this.securityListRefID = securityListRefID;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0SP2")
    @TagNumRef(tagNum=TagNum.SecurityListDesc)
    public String getSecurityListDesc() {
        return securityListDesc;
    }

    /**
     * Message field setter.
     * @param securityListDesc field value
     */
    @FIXVersion(introduced="5.0SP2")
    @TagNumRef(tagNum=TagNum.SecurityListDesc)
    public void setSecurityListDesc(String securityListDesc) {
        this.securityListDesc = securityListDesc;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0SP2")
    @TagNumRef(tagNum=TagNum.EncodedSecurityListDescLen)
    public Integer getEncodedSecurityListDescLen() {
        return encodedSecurityListDescLen;
    }

    /**
     * Message field setter.
     * @param encodedSecurityListDescLen field value
     */
    @FIXVersion(introduced="5.0SP2")
    @TagNumRef(tagNum=TagNum.EncodedSecurityListDescLen)
    public void setEncodedSecurityListDescLen(Integer encodedSecurityListDescLen) {
        this.encodedSecurityListDescLen = encodedSecurityListDescLen;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0SP2")
    @TagNumRef(tagNum=TagNum.EncodedSecurityListDesc)
    public byte[] getEncodedSecurityListDesc() {
        return encodedSecurityListDesc;
    }

    /**
     * Message field setter.
     * @param encodedSecurityListDesc field value
     */
    @FIXVersion(introduced="5.0SP2")
    @TagNumRef(tagNum=TagNum.EncodedSecurityListDesc)
    public void setEncodedSecurityListDesc(byte[] encodedSecurityListDesc) {
        this.encodedSecurityListDesc = encodedSecurityListDesc;
        if (encodedSecurityListDescLen == null) {
            encodedSecurityListDescLen = new Integer(encodedSecurityListDesc.length);
        }
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0SP2")
    @TagNumRef(tagNum=TagNum.SecurityListType)
    public SecurityListType getSecurityListType() {
        return securityListType;
    }

    /**
     * Message field setter.
     * @param securityListType field value
     */
    @FIXVersion(introduced="5.0SP2")
    @TagNumRef(tagNum=TagNum.SecurityListType)
    public void setSecurityListType(SecurityListType securityListType) {
        this.securityListType = securityListType;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0SP2")
    @TagNumRef(tagNum=TagNum.SecurityListTypeSource)
    public SecurityListTypeSource getSecurityListTypeSource() {
        return securityListTypeSource;
    }

    /**
     * Message field setter.
     * @param securityListTypeSource field value
     */
    @FIXVersion(introduced="5.0SP2")
    @TagNumRef(tagNum=TagNum.SecurityListTypeSource)
    public void setSecurityListTypeSource(SecurityListTypeSource securityListTypeSource) {
        this.securityListTypeSource = securityListTypeSource;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.SecurityReqID, required=true)
    public String getSecurityReqID() {
        return securityReqID;
    }

    /**
     * Message field setter.
     * @param securityReqID field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.SecurityReqID, required=true)
    public void setSecurityReqID(String securityReqID) {
        this.securityReqID = securityReqID;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.SecurityResponseID, required=true)
    public String getSecurityResponseID() {
        return securityResponseID;
    }

    /**
     * Message field setter.
     * @param securityResponseID field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.SecurityResponseID, required=true)
    public void setSecurityResponseID(String securityResponseID) {
        this.securityResponseID = securityResponseID;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.SecurityRequestResult, required=true)
    public SecurityRequestResult getSecurityRequestResult() {
        return securityRequestResult;
    }

    /**
     * Message field setter.
     * @param securityRequestResult field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.SecurityRequestResult, required=true)
    public void setSecurityRequestResult(SecurityRequestResult securityRequestResult) {
        this.securityRequestResult = securityRequestResult;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0SP2")
    @TagNumRef(tagNum=TagNum.TransactTime)
    public Date getTransactTime() {
        return transactTime;
    }

    /**
     * Message field setter.
     * @param transactTime field value
     */
    @FIXVersion(introduced="5.0SP2")
    @TagNumRef(tagNum=TagNum.TransactTime)
    public void setTransactTime(Date transactTime) {
        this.transactTime = transactTime;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.4")
    @TagNumRef(tagNum = TagNum.TotNoRelatedSym)
    public Integer getTotNoRelatedSym() {
        return totNoRelatedSym;
    }

    /**
     * Message field setter.
     * @param totNoRelatedSym field value
     */
    @FIXVersion(introduced = "4.4")
    @TagNumRef(tagNum = TagNum.TotNoRelatedSym)
    public void setTotNoRelatedSym(Integer totNoRelatedSym) {
        this.totNoRelatedSym = totNoRelatedSym;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0SP1")
    @TagNumRef(tagNum=TagNum.MarketID)
    public String getMarketID() {
        return marketID;
    }

    /**
     * Message field setter.
     * @param marketID field value
     */
    @FIXVersion(introduced="5.0SP1")
    @TagNumRef(tagNum=TagNum.MarketID)
    public void setMarketID(String marketID) {
        this.marketID = marketID;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0SP1")
    @TagNumRef(tagNum=TagNum.MarketSegmentID)
    public String getMarketSegmentID() {
        return marketSegmentID;
    }

    /**
     * Message field setter.
     * @param marketSegmentID field value
     */
    @FIXVersion(introduced="5.0SP1")
    @TagNumRef(tagNum=TagNum.MarketSegmentID)
    public void setMarketSegmentID(String marketSegmentID) {
        this.marketSegmentID = marketSegmentID;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.LastFragment)
    public Boolean getLastFragment() {
        return lastFragment;
    }

    /**
     * Message field setter.
     * @param lastFragment field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.LastFragment)
    public void setLastFragment(Boolean lastFragment) {
        this.lastFragment = lastFragment;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.NoRelatedSym)
    public Integer getNoRelatedSyms() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method sets the number of {@link SecListGroup} groups. It will also create an array
     * of {@link SecListGroup} objects and set the <code>secListGroups</code> field with this array.
     * The created objects inside the array need to be populated with data for encoding.<br/>
     * If there where already objects in <code>secListGroups</code> array they will be discarded.<br/>
     * @param noRelatedSyms field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.NoRelatedSym)
    public void setNoRelatedSyms(Integer noRelatedSyms) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter for {@link SecListGroup} array of groups.
     * @return field array value
     */
    @FIXVersion(introduced="4.3")
    public SecListGroup[] getSecListGroups() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method adds a {@link SecListGroup} object to the existing array of <code>secListGroups</code>
     * and expands the static array with 1 place.<br/>
     * This method will also update <code>noRelatedSyms</code> field to the proper value.<br/>
     * Note: If the <code>setNoRelatedSyms</code> method has been called there will already be a number of objects in the
     * <code>secListGroups</code> array created.<br/>
     * @return newly created block and added to the array group object
     */
    @FIXVersion(introduced="4.3")
    public SecListGroup addSecListGroup() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method deletes a {@link SecListGroup} object from the existing array of <code>secListGroups</code>
     * and shrink the static array with 1 place.<br/>
     * If the array does not have the index position then a null object will be returned.)<br/>
     * This method will also update <code>noRelatedSyms</code> field to the proper value.<br/>
     * @param index position in array to be deleted starting at 0
     * @return deleted block object
     */
    @FIXVersion(introduced="4.3")
    public SecListGroup deleteSecListGroup(int index) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Deletes all the {@link SecListGroup} objects from the <code>secListGroups</code> array
     * (sets the array to 0 length)<br/>
     * This method will also update <code>noRelatedSyms</code> field and set it to null.<br/>
     * @return number of elements in array cleared
     */
    @FIXVersion(introduced="4.3")
    public int clearSecListGroup() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

    @Override
    protected void validateRequiredTags() throws TagNotPresentException {
    }

    @Override
    protected byte[] encodeFragmentAll() throws TagNotPresentException, BadFormatMsgException {
        if (validateRequired) {
            validateRequiredTags();
        }
        byte[] result = new byte[0];
        ByteArrayOutputStream bao = new ByteArrayOutputStream();
        try {
            if (applicationSequenceControl != null) {
                bao.write(applicationSequenceControl.encode(MsgSecureType.ALL_FIELDS));
            }
            TagEncoder.encode(bao, TagNum.SecurityReportID, securityReportID);
            TagEncoder.encodeDate(bao, TagNum.ClearingBusinessDate, clearingBusinessDate);
            TagEncoder.encode(bao, TagNum.SecurityListID, securityListID);
            TagEncoder.encode(bao, TagNum.SecurityListRefID, securityListRefID);
            TagEncoder.encode(bao, TagNum.SecurityListDesc, securityListDesc);
            if (encodedSecurityListDescLen != null && encodedSecurityListDescLen.intValue() > 0) {
                if (encodedSecurityListDesc != null && encodedSecurityListDesc.length > 0) {
                    encodedSecurityListDescLen = new Integer(encodedSecurityListDesc.length);
                    TagEncoder.encode(bao, TagNum.EncodedSecurityListDescLen, encodedSecurityListDescLen);
                    TagEncoder.encode(bao, TagNum.EncodedSecurityListDesc, encodedSecurityListDesc);
                }
            }
            if (securityListType != null) {
                TagEncoder.encode(bao, TagNum.SecurityListType, securityListType.getValue());
            }
            if (securityListTypeSource != null) {
                TagEncoder.encode(bao, TagNum.SecurityListTypeSource, securityListTypeSource.getValue());
            }
            TagEncoder.encode(bao, TagNum.SecurityReqID, securityReqID);
            TagEncoder.encode(bao, TagNum.SecurityResponseID, securityResponseID);
            if (securityRequestResult != null) {
                TagEncoder.encode(bao, TagNum.SecurityRequestResult, securityRequestResult.getValue());
            }
            TagEncoder.encodeUtcTimestamp(bao, TagNum.TransactTime, transactTime);
            TagEncoder.encode(bao, TagNum.TotNoRelatedSym, totNoRelatedSym);
            TagEncoder.encode(bao, TagNum.MarketID, marketID);
            TagEncoder.encode(bao, TagNum.MarketSegmentID, marketSegmentID);
            TagEncoder.encode(bao, TagNum.LastFragment, lastFragment);
            if (noRelatedSyms != null) {
                TagEncoder.encode(bao, TagNum.NoRelatedSym, noRelatedSyms);
                if (secListGroups != null && secListGroups.length == noRelatedSyms.intValue()) {
                    for (int i = 0; i < noRelatedSyms.intValue(); i++) {
                        if (secListGroups[i] != null) {
                            bao.write(secListGroups[i].encode(MsgSecureType.ALL_FIELDS));
                        }
                    }
                } else {
                    String error = "SecListGroups field has been set but there is no data or the number of groups does not match.";
                    LOGGER.severe(error);
                    throw new BadFormatMsgException(SessionRejectReason.IncorrectCountForGroups, getHeader().getMsgType(),
                            TagNum.NoRelatedSym.getValue(), error);
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
            case SecurityReportID:
                securityReportID = new Integer(new String(tag.value, sessionCharset));
                break;

            case ClearingBusinessDate:
                clearingBusinessDate = DateConverter.parseString(new String(tag.value, sessionCharset));
                break;

            case SecurityListID:
                securityListID = new String(tag.value, sessionCharset);
                break;

            case SecurityListRefID:
                securityListRefID = new String(tag.value, sessionCharset);
                break;

            case SecurityListDesc:
                securityListDesc = new String(tag.value, sessionCharset);
                break;

            case SecurityListType:
                securityListType = SecurityListType.valueFor(Integer.valueOf(new String(tag.value, sessionCharset)));
                break;

            case SecurityListTypeSource:
                securityListTypeSource = SecurityListTypeSource.valueFor(Integer.valueOf(new String(tag.value, sessionCharset)));
                break;

            case SecurityReqID:
                securityReqID = new String(tag.value, sessionCharset);
                break;

            case SecurityResponseID:
                securityResponseID = new String(tag.value, sessionCharset);
                break;

            case SecurityRequestResult:
                securityRequestResult = SecurityRequestResult.valueFor(Integer.valueOf(new String(tag.value, sessionCharset)));
                break;

            case TransactTime:
                transactTime = DateConverter.parseString(new String(tag.value, sessionCharset));
                break;

            case TotNoRelatedSym:
                totNoRelatedSym = new Integer(new String(tag.value, sessionCharset));
                break;

            case MarketID:
                marketID = new String(tag.value, sessionCharset);
                break;

            case MarketSegmentID:
                marketSegmentID = new String(tag.value, sessionCharset);
                break;

            case LastFragment:
                lastFragment = BooleanConverter.parse(new String(tag.value, sessionCharset));
                break;

            case NoRelatedSym:
                noRelatedSyms = new Integer(new String(tag.value, getSessionCharset()));
                break;

            default:
                String error = "Tag value [" + tag.tagNum + "] not present in [SecurityListMsg] fields.";
                LOGGER.severe(error);
                throw new BadFormatMsgException(SessionRejectReason.InvalidTagNumber, getHeader().getMsgType(),
                        tag.tagNum, error);
        }
    }

    @Override
    protected ByteBuffer setFragmentDataTagValue(Tag tag, ByteBuffer message) throws BadFormatMsgException {
        ByteBuffer result = message;
        if (tag.tagNum == TagNum.EncodedSecurityListDescLen.getValue()) {
            try {
                encodedSecurityListDescLen = new Integer(new String(tag.value, getSessionCharset()));
            } catch (NumberFormatException ex) {
                String error = "Tag [EncodedSecurityListDescLen] requires an integer value. The value set was [" + tag.value + "].";
                LOGGER.log(Level.SEVERE, "{0} Error was: {1}", new Object[] { error, ex.toString() });
                throw new BadFormatMsgException(SessionRejectReason.IncorrectDataFormat, getHeader().getMsgType(), TagNum.EncodedSecurityListDescLen.getValue(), error);
            }
            Tag  dataTag = MsgUtil.getNextTag(message, encodedSecurityListDescLen.intValue());
            encodedSecurityListDesc = dataTag.value;
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
        StringBuilder b = new StringBuilder("{SecurityListMsg=");
        b.append(header != null ? header.toString() : "");
        b.append(System.getProperty("line.separator")).append("{Body=");
        printTagValue(b, applicationSequenceControl);
        printTagValue(b, TagNum.SecurityReportID, securityReportID);
        printDateTagValue(b, TagNum.ClearingBusinessDate, clearingBusinessDate);
        printTagValue(b, TagNum.SecurityListID, securityListID);
        printTagValue(b, TagNum.SecurityListRefID, securityListRefID);
        printTagValue(b, TagNum.SecurityListDesc, securityListDesc);
        printTagValue(b, TagNum.EncodedSecurityDescLen, encodedSecurityListDescLen);
        printTagValue(b, TagNum.EncodedSecurityDesc, encodedSecurityListDesc);
        printTagValue(b, TagNum.SecurityListType, securityListType);
        printTagValue(b, TagNum.SecurityListTypeSource, securityListTypeSource);
        printTagValue(b, TagNum.SecurityReqID, securityReqID);
        printTagValue(b, TagNum.SecurityResponseID, securityResponseID);
        printTagValue(b, TagNum.SecurityRequestResult, securityRequestResult);
        printUTCDateTimeTagValue(b, TagNum.TransactTime, transactTime);
        printTagValue(b, TagNum.TotNoRelatedSym, totNoRelatedSym);
        printTagValue(b, TagNum.MarketID, marketID);
        printTagValue(b, TagNum.MarketSegmentID, marketSegmentID);
        printTagValue(b, TagNum.LastFragment, lastFragment);
        printTagValue(b, TagNum.NoRelatedSym, noRelatedSyms);
        printTagValue(b, secListGroups);

        b.append("}");
        b.append(trailer != null ? trailer.toString() : "").append("}");

        return b.toString();
    }

    // </editor-fold>
}
