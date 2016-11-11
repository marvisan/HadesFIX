/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * RegistrationInstructionsResponseMsg.java
 *
 * $Id: RegistrationInstructionsResponseMsg.java,v 1.1 2011-10-29 02:16:41 vrotaru Exp $
 */
package net.hades.fix.message;

import net.hades.fix.message.anno.FIXVersion;
import net.hades.fix.message.anno.TagNumRef;
import net.hades.fix.message.comp.Parties;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.struct.Tag;
import net.hades.fix.message.util.TagEncoder;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import net.hades.fix.message.type.AcctIDSource;
import net.hades.fix.message.type.ApplVerID;
import net.hades.fix.message.type.BeginString;
import net.hades.fix.message.type.MsgType;
import net.hades.fix.message.type.RegistRejReasonCode;
import net.hades.fix.message.type.RegistStatus;
import net.hades.fix.message.type.RegistTransType;
import net.hades.fix.message.type.SessionRejectReason;
import net.hades.fix.message.type.TagNum;

/**
 * <p>Extracted from FIX protocol documentation <a href="http://www.fixprotocol.org/">FIX Protocol</a></p>
 * The Registration Instructions Response message type may be used by broker or fund manager (for CIV) in
 * response to a Registration Instructions message submitted by an institution or retail intermediary for an order or
 * for an allocation.
 * The Registration Instructions Response message is used to:<br/>
 * 1. confirm the receipt of a Registration Instructions message<br/>
 * 2. confirm changes to an existing Registration Instructions message (i.e. accept cancel and replace requests)<br/>
 * 3. relay Registration Instructions status information<br/>
 * 4. relay assigned client and account Ids for Registration Instructions messages with RegTransType=New<br/>
 * 5. reject Registration Instructions message<br/>
 * Each Registration Instructions Response message contains a RegistStatus field which is used to communicate the
 * current state of the Registration Instructions as understood by the broker or fund manager.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 21/04/2009, 9:25:04 AM
 */
@XmlAccessorType(XmlAccessType.NONE)
public abstract class RegistrationInstructionsResponseMsg extends FIXMsg {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.RegistID.getValue(),
        TagNum.RegistTransType.getValue(),
        TagNum.RegistRefID.getValue(),
        TagNum.ClOrdID.getValue(),
        TagNum.Account.getValue(),
        TagNum.AcctIDSource.getValue(),
        TagNum.RegistStatus.getValue(),
        TagNum.RegistRejReasonCode.getValue(),
        TagNum.RegistRejReasonText.getValue()
    }));

    protected static final Set<Integer> START_DATA_TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
    }));

    protected static final Set<Integer> REQUIRED_TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.RegistID.getValue(),
        TagNum.RegistTransType.getValue(),
        TagNum.RegistRefID.getValue(),
        TagNum.RegistStatus.getValue()
    }));

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">

    /**
     * TagNum = 513 REQUIRED. Starting with 4.3 version.
     */
    protected String registID;

    /**
     * TagNum = 514 REQUIRED. Starting with 4.3 version.
     */
    protected RegistTransType registTransType;

    /**
     * TagNum = 508 REQUIRED. Starting with 4.3 version.
     */
    protected String registRefID;

    /**
     * TagNum = 11. Starting with 4.3 version.
     */
    protected String clOrdID;

   /**
     * Starting with 4.3 version.
     */
    protected Parties parties;

    /**
     * TagNum = 1. Starting with 4.3 version.
     */
    protected String account;

    /**
     * TagNum = 660. Starting with 4.4 version.
     */
    protected AcctIDSource acctIDSource;

    /**
     * TagNum = 506 REQUIRED. Starting with 4.3 version.
     */
    protected RegistStatus registStatus;

    /**
     * TagNum = 507. Starting with 4.3 version.
     */
    protected RegistRejReasonCode registRejReasonCode;

    /**
     * TagNum = 496. Starting with 4.3 version.
     */
    protected String registRejReasonText;


    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public RegistrationInstructionsResponseMsg() {
        super();
    }

    public RegistrationInstructionsResponseMsg(Header header, ByteBuffer rawMsg)
    throws InvalidMsgException, TagNotPresentException, BadFormatMsgException {
        super(header, rawMsg);
    }

    public RegistrationInstructionsResponseMsg(BeginString beginString) throws InvalidMsgException {
        super(MsgType.RegistrationInstructionsResponse.getValue(), beginString);
    }

    public RegistrationInstructionsResponseMsg(BeginString beginString, ApplVerID applVerID) throws InvalidMsgException {
        super(MsgType.RegistrationInstructionsResponse.getValue(), beginString, applVerID);
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
    @TagNumRef(tagNum=TagNum.RegistID, required=true)
    public String getRegistID() {
        return registID;
    }

    /**
     * Message field setter.
     * @param registID field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.RegistID, required=true)
    public void setRegistID(String registID) {
        this.registID = registID;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.RegistTransType, required=true)
    public RegistTransType getRegistTransType() {
        return registTransType;
    }

    /**
     * Message field setter.
     * @param registTransType field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.RegistTransType, required=true)
    public void setRegistTransType(RegistTransType registTransType) {
        this.registTransType = registTransType;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.RegistRefID, required=true)
    public String getRegistRefID() {
        return registRefID;
    }

    /**
     * Message field setter.
     * @param registRefID field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.RegistRefID, required=true)
    public void setRegistRefID(String registRefID) {
        this.registRefID = registRefID;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.ClOrdID)
    public String getClOrdID() {
        return clOrdID;
    }

    /**
     * Message field setter.
     * @param clOrdID field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.ClOrdID)
    public void setClOrdID(String clOrdID) {
        this.clOrdID = clOrdID;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3")
    public Parties getParties() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Sets the Parties component if used in this message to the proper implementation
     * class.
     */
    @FIXVersion(introduced="4.3")
    public void setParties() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Sets the Parties component to null.
     */
    @FIXVersion(introduced="4.3")
    public void clearParties() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }
    
    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.3")
    @TagNumRef(tagNum = TagNum.Account)
    public String getAccount() {
        return account;
    }

    /**
     * Message field setter.
     * @param account field value
     */
    @FIXVersion(introduced = "4.3")
    @TagNumRef(tagNum = TagNum.Account)
    public void setAccount(String account) {
        this.account = account;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.4")
    @TagNumRef(tagNum = TagNum.AcctIDSource)
    public AcctIDSource getAcctIDSource() {
        return acctIDSource;
    }

    /**
     * Message field setter.
     * @param acctIDSource field value
     */
    @FIXVersion(introduced = "4.4")
    @TagNumRef(tagNum = TagNum.AcctIDSource)
    public void setAcctIDSource(AcctIDSource acctIDSource) {
        this.acctIDSource = acctIDSource;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.RegistStatus, required=true)
    public RegistStatus getRegistStatus() {
        return registStatus;
    }

    /**
     * Message field setter.
     * @param registStatus field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.RegistStatus, required=true)
    public void setRegistStatus(RegistStatus registStatus) {
        this.registStatus = registStatus;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.3")
    @TagNumRef(tagNum = TagNum.RegistRejReasonCode)
    public RegistRejReasonCode getRegistRejReasonCode() {
        return registRejReasonCode;
    }

    /**
     * Message field setter.
     * @param registRejReasonCode field value
     */
    @FIXVersion(introduced = "4.3")
    @TagNumRef(tagNum = TagNum.RegistRejReasonCode)
    public void setRegistRejReasonCode(RegistRejReasonCode registRejReasonCode) {
        this.registRejReasonCode = registRejReasonCode;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.3")
    @TagNumRef(tagNum = TagNum.RegistRejReasonText)
    public String getRegistRejReasonText() {
        return registRejReasonText;
    }

    /**
     * Message field setter.
     * @param registRejReasonText field value
     */
    @FIXVersion(introduced = "4.3")
    @TagNumRef(tagNum = TagNum.RegistRejReasonText)
    public void setRegistRejReasonText(String registRejReasonText) {
        this.registRejReasonText = registRejReasonText;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

    @Override
    protected void validateRequiredTags() throws TagNotPresentException {
        StringBuilder errorMsg = new StringBuilder("Tag value(s) for");
        boolean hasMissingTag = false;
        if (registID == null || registID.trim().isEmpty()) {
            errorMsg.append(" [RegistID]");
            hasMissingTag = true;
        }
        if (registTransType == null) {
            errorMsg.append(" [RegistTransType]");
            hasMissingTag = true;
        }
        if (registRefID == null) {
            errorMsg.append(" [RegistRefID]");
            hasMissingTag = true;
        }
        if (registStatus == null) {
            errorMsg.append(" [RegistStatus]");
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
            TagEncoder.encode(bao, TagNum.RegistID, registID);
            if (registTransType != null) {
                TagEncoder.encode(bao, TagNum.RegistTransType, registTransType.getValue());
            }
            TagEncoder.encode(bao, TagNum.RegistRefID, registRefID);
            TagEncoder.encode(bao, TagNum.ClOrdID, clOrdID);
            if (parties != null) {
                bao.write(parties.encode(MsgSecureType.ALL_FIELDS));
            }
            TagEncoder.encode(bao, TagNum.Account, account);
            if (acctIDSource != null) {
                TagEncoder.encode(bao, TagNum.AcctIDSource, acctIDSource.getValue());
            }
            if (registStatus != null) {
                TagEncoder.encode(bao, TagNum.RegistStatus, registStatus.getValue());
            }
            if (registRejReasonCode != null) {
                TagEncoder.encode(bao, TagNum.RegistRejReasonCode, registRejReasonCode.getValue());
            }
            TagEncoder.encode(bao, TagNum.RegistRejReasonText, registRejReasonText);

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
            case RegistID:
                registID = new String(tag.value, sessionCharset);
                break;
                
            case RegistTransType:
                registTransType = RegistTransType.valueFor(new String(tag.value, sessionCharset).charAt(0));
                break;

            case RegistRefID:
                registRefID = new String(tag.value, sessionCharset);
                break;

            case ClOrdID:
                clOrdID = new String(tag.value, sessionCharset);
                break;

            case Account:
                account = new String(tag.value, sessionCharset);
                break;

            case AcctIDSource:
                acctIDSource = AcctIDSource.valueFor(Integer.valueOf(new String(tag.value, sessionCharset)));
                break;

            case RegistStatus:
                registStatus = RegistStatus.valueFor(new String(tag.value, sessionCharset).charAt(0));
                break;

            case RegistRejReasonCode:
                registRejReasonCode = RegistRejReasonCode.valueFor(Integer.valueOf(new String(tag.value, sessionCharset)));
                break;

            case RegistRejReasonText:
                registRejReasonText = new String(tag.value, sessionCharset);
                break;

            default:
                String error = "Tag value [" + tag.tagNum + "] not present in [RegistrationInstructionsResponseMsg] fields.";
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
        StringBuilder b = new StringBuilder("{RegistrationInstructionsResponseMsg=");
        b.append(header != null ? header.toString() : "");
        b.append(System.getProperty("line.separator")).append("{Body=");
        printTagValue(b, TagNum.RegistID, registID);
        printTagValue(b, TagNum.RegistTransType, registTransType);
        printTagValue(b, TagNum.RegistRefID, registRefID);
        printTagValue(b, TagNum.ClOrdID, clOrdID);
        printTagValue(b, parties);
        printTagValue(b, TagNum.Account, account);
        printTagValue(b, TagNum.AcctIDSource, acctIDSource);
        printTagValue(b, TagNum.RegistStatus, registStatus);
        printTagValue(b, TagNum.RegistRejReasonCode, registRejReasonCode);
        printTagValue(b, TagNum.RegistRejReasonText, registRejReasonText);
        b.append("}");
        b.append(trailer != null ? trailer.toString() : "").append("}");

        return b.toString();
    }

    // </editor-fold>
}
