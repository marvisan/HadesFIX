/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * SecurityDefinitionMsg.java
 *
 * $Id: DerivativeSecurityListMsg.java,v 1.1 2011-09-27 08:57:26 vrotaru Exp $
 */
package net.hades.fix.message;

import net.hades.fix.message.anno.FIXVersion;
import net.hades.fix.message.anno.TagNumRef;
import net.hades.fix.message.comp.DerivativeSecurityDefinition;
import net.hades.fix.message.comp.UnderlyingInstrument;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.struct.Tag;
import net.hades.fix.message.util.BooleanConverter;
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

import net.hades.fix.message.comp.ApplicationSequenceControl;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.group.DerivSecListGroup;
import net.hades.fix.message.type.ApplVerID;
import net.hades.fix.message.type.BeginString;
import net.hades.fix.message.type.MsgType;
import net.hades.fix.message.type.SecurityRequestResult;
import net.hades.fix.message.type.SessionRejectReason;
import net.hades.fix.message.type.TagNum;
import net.hades.fix.message.util.TagEncoder;

/**
 * <p>Extracted from FIX protocol documentation <a href="http://www.fixprotocol.org/">FIX Protocol</a></p>
 * The Derivative Security List Request message is used to return a list of securities from the counterparty that
 * match criteria provided on the request.
 * Subscription for security status can be optionally specified by including the SubscriptionRequestType[263]
 * field on the message.
 * SecurityListRequestType[559] specifies the criteria of the request:
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 21/04/2009, 9:25:04 AM
 */
@XmlAccessorType(XmlAccessType.NONE)
public abstract class DerivativeSecurityListMsg extends FIXMsg {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.SecurityReportID.getValue(),
        TagNum.SecurityReqID.getValue(),
        TagNum.SecurityResponseID.getValue(),
        TagNum.SecurityRequestResult.getValue(),
        TagNum.ClearingBusinessDate.getValue(),
        TagNum.TransactTime.getValue(),
        TagNum.TotNoRelatedSym.getValue(),
        TagNum.LastFragment.getValue(),
        TagNum.NoRelatedSym.getValue()
    }));

    protected static final Set<Integer> START_DATA_TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
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
     * TagNum = 964. Starting with 5.0SP2 version.
     */
    protected Integer securityReportID;

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
     * TagNum = 715. Starting with 5.0 version.
     */
    protected Date clearingBusinessDate;
    
    /**
     * Starting with 4.3 version.
     */
    protected UnderlyingInstrument underlyingInstrument;
    
    /**
     * Starting with 5.0SP1 version.
     */
    protected DerivativeSecurityDefinition derivativeSecurityDefinition;

    /**
     * TagNum = 60. Starting with 5.0SP2 version.
     */
    protected Date transactTime;

    /**
     * TagNum = 393. Starting with 4.2 version.
     */
    protected Integer totNoRelatedSym;

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
    protected DerivSecListGroup[] derivSecListGroups;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public DerivativeSecurityListMsg() {
        super();
    }

    public DerivativeSecurityListMsg(Header header, ByteBuffer rawMsg)
    throws InvalidMsgException, TagNotPresentException, BadFormatMsgException {
        super(header, rawMsg);
    }

    public DerivativeSecurityListMsg(BeginString beginString) throws InvalidMsgException {
        super(MsgType.DerivativeSecurityList.getValue(), beginString);
    }

    public DerivativeSecurityListMsg(BeginString beginString, ApplVerID applVerID) throws InvalidMsgException {
        super(MsgType.DerivativeSecurityList.getValue(), beginString, applVerID);
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
    @FIXVersion(introduced="5.0SP2")
    @TagNumRef(tagNum=TagNum.SecurityReportID)
    public Integer getSecurityReportID() {
        return securityReportID;
    }

    /**
     * Message field setter.
     * @param securityReportID field value
     */
    @FIXVersion(introduced="5.0SP2")
    @TagNumRef(tagNum=TagNum.SecurityReportID)
    public void setSecurityReportID(Integer securityReportID) {
        this.securityReportID = securityReportID;
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
    @FIXVersion(introduced = "4.3")
    public UnderlyingInstrument getUnderlyingInstrument() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field setter.
     */
    @FIXVersion(introduced="4.3")
    public void setUnderlyingInstrument() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }
    
    /**
     * Message field clear.
     */
    @FIXVersion(introduced="4.3")
    public void clearUnderlyingInstrument() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "5.0SP1")
    public DerivativeSecurityDefinition getDerivativeSecurityDefinition() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }
    
    /**
     * Message field setter.
     */
    @FIXVersion(introduced = "5.0SP1")
    public void setDerivativeSecurityDefinition() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }
    
    /**
     * Message field clear.
     */
    @FIXVersion(introduced = "5.0SP1")
    public void clearDerivativeSecurityDefinition() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
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
     * This method sets the number of {@link DerivSecListGroup} groups. It will also create an array
     * of {@link DerivSecListGroup} objects and set the <code>derivSecListGroups</code> field with this array.
     * The created objects inside the array need to be populated with data for encoding.<br/>
     * If there where already objects in <code>derivSecListGroups</code> array they will be discarded.<br/>
     * @param noRelatedSyms field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.NoRelatedSym)
    public void setNoRelatedSyms(Integer noRelatedSyms) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter for {@link DerivSecListGroup} array of groups.
     * @return field array value
     */
    @FIXVersion(introduced="4.3")
    public DerivSecListGroup[] getDerivSecListGroups() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method adds a {@link DerivSecListGroup} object to the existing array of <code>derivSecListGroups</code>
     * and expands the static array with 1 place.<br/>
     * This method will also update <code>noRelatedSyms</code> field to the proper value.<br/>
     * Note: If the <code>setNoRelatedSyms</code> method has been called there will already be a number of objects in the
     * <code>derivSecListGroups</code> array created.<br/>
     * @return newly created block and added to the array group object
     */
    @FIXVersion(introduced="4.3")
    public DerivSecListGroup addDerivSecListGroup() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method deletes a {@link DerivSecListGroup} object from the existing array of <code>derivSecListGroups</code>
     * and shrink the static array with 1 place.<br/>
     * If the array does not have the index position then a null object will be returned.)<br/>
     * This method will also update <code>noRelatedSyms</code> field to the proper value.<br/>
     * @param index position in array to be deleted starting at 0
     * @return deleted block object
     */
    @FIXVersion(introduced="4.3")
    public DerivSecListGroup deleteDerivSecListGroup(int index) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Deletes all the {@link DerivSecListGroup} objects from the <code>derivSecListGroups</code> array
     * (sets the array to 0 length)<br/>
     * This method will also update <code>noRelatedSyms</code> field and set it to null.<br/>
     * @return number of elements in array cleared
     */
    @FIXVersion(introduced="4.3")
    public int clearDerivSecListGroup() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

    @Override
    protected void validateRequiredTags() throws TagNotPresentException {
        StringBuilder errorMsg = new StringBuilder("Tag value(s) for");
        boolean hasMissingTag = false;
        if (securityReqID == null || securityReqID.trim().isEmpty()) {
            errorMsg.append(" [SecurityReqID]");
            hasMissingTag = true;
        }
        if (securityResponseID == null) {
            errorMsg.append(" [SecurityResponseID]");
            hasMissingTag = true;
        }
        if (securityRequestResult == null) {
            errorMsg.append(" [SecurityRequestResult]");
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
            if (applicationSequenceControl != null) {
                bao.write(applicationSequenceControl.encode(MsgSecureType.ALL_FIELDS));
            }
            TagEncoder.encode(bao, TagNum.SecurityReportID, securityReportID);
            TagEncoder.encode(bao, TagNum.SecurityReqID, securityReqID);
            TagEncoder.encode(bao, TagNum.SecurityResponseID, securityResponseID);
            if (securityRequestResult != null) {
                TagEncoder.encode(bao, TagNum.SecurityRequestResult, securityRequestResult.getValue());
            }
            TagEncoder.encodeDate(bao, TagNum.ClearingBusinessDate, clearingBusinessDate);
            if (underlyingInstrument != null) {
                bao.write(underlyingInstrument.encode(MsgSecureType.ALL_FIELDS));
            }
            if (derivativeSecurityDefinition != null) {
                bao.write(derivativeSecurityDefinition.encode(MsgSecureType.ALL_FIELDS));
            }
            TagEncoder.encodeUtcTimestamp(bao, TagNum.TransactTime, transactTime);
            TagEncoder.encode(bao, TagNum.TotNoRelatedSym, totNoRelatedSym);
            TagEncoder.encode(bao, TagNum.LastFragment, lastFragment);
            if (noRelatedSyms != null) {
                TagEncoder.encode(bao, TagNum.NoRelatedSym, noRelatedSyms);
                if (derivSecListGroups != null && derivSecListGroups.length == noRelatedSyms.intValue()) {
                    for (int i = 0; i < noRelatedSyms.intValue(); i++) {
                        if (derivSecListGroups[i] != null) {
                            bao.write(derivSecListGroups[i].encode(MsgSecureType.ALL_FIELDS));
                        }
                    }
                } else {
                    String error = "DerivSecListGroups field has been set but there is no data or the number of groups does not match.";
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

            case SecurityReqID:
                securityReqID = new String(tag.value, sessionCharset);
                break;

            case SecurityResponseID:
                securityResponseID = new String(tag.value, sessionCharset);
                break;

            case SecurityRequestResult:
                securityRequestResult = SecurityRequestResult.valueFor(Integer.valueOf(new String(tag.value, sessionCharset)));
                break;

            case ClearingBusinessDate:
                clearingBusinessDate = DateConverter.parseString(new String(tag.value, sessionCharset));
                break;

            case TransactTime:
                transactTime = DateConverter.parseString(new String(tag.value, sessionCharset));
                break;

            case TotNoRelatedSym:
                totNoRelatedSym = new Integer(new String(tag.value, sessionCharset));
                break;

            case LastFragment:
                lastFragment = BooleanConverter.parse(new String(tag.value, sessionCharset));
                break;

            case NoRelatedSym:
                noRelatedSyms = new Integer(new String(tag.value, getSessionCharset()));
                break;

            default:
                String error = "Tag value [" + tag.tagNum + "] not present in [DerivativeSecurityListMsg] fields.";
                LOGGER.severe(error);
                throw new BadFormatMsgException(SessionRejectReason.InvalidTagNumber, getHeader().getMsgType(),
                        tag.tagNum, error);
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
        StringBuilder b = new StringBuilder("{DerivativeSecurityListMsg=");
        b.append(header != null ? header.toString() : "");
        b.append(System.getProperty("line.separator")).append("{Body=");
        printTagValue(b, applicationSequenceControl);
        printTagValue(b, TagNum.SecurityReportID, securityReportID);
        printTagValue(b, TagNum.SecurityReqID, securityReqID);
        printTagValue(b, TagNum.SecurityResponseID, securityResponseID);
        printTagValue(b, TagNum.SecurityRequestResult, securityRequestResult);
        printDateTagValue(b, TagNum.ClearingBusinessDate, clearingBusinessDate);
        printTagValue(b, underlyingInstrument);
        printTagValue(b, derivativeSecurityDefinition);
        printUTCDateTimeTagValue(b, TagNum.TransactTime, transactTime);
        printTagValue(b, TagNum.TotNoRelatedSym, totNoRelatedSym);
        printTagValue(b, TagNum.LastFragment, lastFragment);
        printTagValue(b, TagNum.NoRelatedSym, noRelatedSyms);
        printTagValue(b, derivSecListGroups);
        b.append("}");
        b.append(trailer != null ? trailer.toString() : "").append("}");

        return b.toString();
    }

    // </editor-fold>
}
