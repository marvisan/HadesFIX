/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * RegistrationInstructionsMsg.java
 *
 * $Id: RegistrationInstructionsMsg.java,v 1.2 2011-10-29 01:31:23 vrotaru Exp $
 */
package net.hades.fix.message;

import net.hades.fix.message.anno.FIXVersion;
import net.hades.fix.message.comp.Parties;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.group.DistribInstsGroup;
import net.hades.fix.message.struct.Tag;
import net.hades.fix.message.type.ApplVerID;
import net.hades.fix.message.type.MsgType;
import net.hades.fix.message.type.OwnershipType;
import net.hades.fix.message.type.SessionRejectReason;

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
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.group.RgstDtlsGroup;
import net.hades.fix.message.type.AcctIDSource;
import net.hades.fix.message.type.BeginString;
import net.hades.fix.message.type.RegistTransType;
import net.hades.fix.message.type.TagNum;
import net.hades.fix.message.util.TagEncoder;

/**
 * <p>Extracted from FIX protocol documentation <a href="http://www.fixprotocol.org/">FIX Protocol</a></p>
 * The Registration Instructions message type may be used by institutions or retail intermediaries wishing to
 * electronically submit registration information to a broker or fund manager (for CIV) for an order or for an
 * allocation.<br/>
 * A Registration Instructions message can be submitted as new, cancel or replace. The RegistTransType field
 * indicates the purpose of the message. When submitting replace or cancel RegistTransType messages the
 * RegistRefID field is required. Replacement Registration Instructions messages must contain all data for the
 * replacement registration.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 * @created 21/04/2009, 9:25:04 AM
 */
@XmlAccessorType(XmlAccessType.NONE)
public abstract class RegistrationInstructionsMsg extends FIXMsg {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.RegistID.getValue(),
        TagNum.RegistTransType.getValue(),
        TagNum.RegistRefID.getValue(),
        TagNum.ClOrdID.getValue(),
        TagNum.Account.getValue(),
        TagNum.AcctIDSource.getValue(),
        TagNum.RegistAcctType.getValue(),
        TagNum.TaxAdvantageType.getValue(),
        TagNum.OwnershipType.getValue(),
        TagNum.NoRegistDtls.getValue(),
        TagNum.NoDistribInsts.getValue()
    }));

    protected static final Set<Integer> START_DATA_TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
    }));

    protected static final Set<Integer> REQUIRED_TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.RegistID.getValue(),
        TagNum.RegistTransType.getValue(),
        TagNum.RegistRefID.getValue()
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
     * TagNum = 493. Starting with 4.3 version.
     */
    protected String registAcctType;

    /**
     * TagNum = 495. Starting with 4.3 version.
     */
    protected Integer taxAdvantageType;

    /**
     * TagNum = 517. Starting with 4.3 version.
     */
    protected OwnershipType ownershipType;

    /**
     * TagNum = 473. Starting with 4.3 version.
     */
    protected Integer noRegistDtls;

    /**
     * Starting with 4.3 version.
     */
    protected RgstDtlsGroup[] registDtlsGroups;

    /**
     * TagNum = 711. Starting with 4.3 version.
     */
    protected Integer noDistribInsts;

    /**
     * Starting with 4.3 version.
     */
    protected DistribInstsGroup[] distribInstsGroups;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public RegistrationInstructionsMsg() {
        super();
    }

    public RegistrationInstructionsMsg(Header header, ByteBuffer rawMsg)
    throws InvalidMsgException, TagNotPresentException, BadFormatMsgException {
        super(header, rawMsg);
    }

    public RegistrationInstructionsMsg(BeginString beginString) throws InvalidMsgException {
        super(MsgType.RegistrationInstructions.getValue(), beginString);
    }

    public RegistrationInstructionsMsg(BeginString beginString, ApplVerID applVerID) throws InvalidMsgException {
        super(MsgType.RegistrationInstructions.getValue(), beginString, applVerID);
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
    @FIXVersion(introduced = "4.3")
    @TagNumRef(tagNum = TagNum.RegistAcctType)
    public String getRegistAcctType() {
        return registAcctType;
    }

    /**
     * Message field setter.
     * @param registAcctType field value
     */
    @FIXVersion(introduced = "4.3")
    @TagNumRef(tagNum = TagNum.RegistAcctType)
    public void setRegistAcctType(String registAcctType) {
        this.registAcctType = registAcctType;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.3")
    @TagNumRef(tagNum = TagNum.TaxAdvantageType)
    public Integer getTaxAdvantageType() {
        return taxAdvantageType;
    }

    /**
     * Message field setter.
     * @param taxAdvantageType field value
     */
    @FIXVersion(introduced = "4.3")
    @TagNumRef(tagNum = TagNum.TaxAdvantageType)
    public void setTaxAdvantageType(Integer taxAdvantageType) {
        this.taxAdvantageType = taxAdvantageType;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.3")
    @TagNumRef(tagNum = TagNum.OwnershipType)
    public OwnershipType getOwnershipType() {
        return ownershipType;
    }

    /**
     * Message field setter.
     * @param ownershipType field value
     */
    @FIXVersion(introduced = "4.3")
    @TagNumRef(tagNum = TagNum.OwnershipType)
    public void setOwnershipType(OwnershipType ownershipType) {
        this.ownershipType = ownershipType;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.NoRegistDtls)
    public Integer getNoRegistDtls() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method sets the number of {@link RgstDtlsGroup} groups. It will also create an array
     * of {@link RgstDtlsGroup} objects and set the <code>registDtlsGroups</code> field with this array.
     * The created objects inside the array need to be populated with data for encoding.<br/>
     * If there where already objects in <code>registDtlsGroups</code> array they will be discarded.<br/>
     * @param noRegistDtls field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.NoRegistDtls)
    public void setNoRegistDtls(Integer noRegistDtls) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter for {@link RgstDtlsGroup} array of groups.
     * @return field array value
     */
    @FIXVersion(introduced="4.3")
    public RgstDtlsGroup[] getRgstDtlsGroups() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method adds a {@link RgstDtlsGroup} object to the existing array of <code>registDtlsGroups</code>
     * and expands the static array with 1 place.<br/>
     * This method will also update <code>noRegistDtls</code> field to the proper value.<br/>
     * Note: If the <code>setNoRegistDtls</code> method has been called there will already be a number of objects in the
     * <code>registDtlsGroups</code> array created.<br/>
     * @return newly created block and added to the array group object
     */
    @FIXVersion(introduced="4.3")
    public RgstDtlsGroup addRgstDtlsGroup() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method deletes a {@link RgstDtlsGroup} object from the existing array of <code>registDtlsGroups</code>
     * and shrink the static array with 1 place.<br/>
     * If the array does not have the index position then a null object will be returned.)<br/>
     * This method will also update <code>noRegistDtls</code> field to the proper value.<br/>
     * @param index position in array to be deleted starting at 0
     * @return deleted block object
     */
    @FIXVersion(introduced="4.3")
    public RgstDtlsGroup deleteRgstDtlsGroup(int index) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Deletes all the {@link RgstDtlsGroup} objects from the <code>registDtlsGroups</code> array
     * (sets the array to 0 length)<br/>
     * This method will also update <code>noRegistDtls</code> field and set it to null.<br/>
     * @return number of elements in array cleared
     */
    @FIXVersion(introduced="4.3")
    public int clearRgstDtlsGroup() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }
 
    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.NoDistribInsts)
    public Integer getNoDistribInsts() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method sets the number of {@link DistribInstsGroup} components. It will also create an array
     * of {@link DistribInstsGroup} objects and set the <code>distribInstsGroups</code>
     * field with this array.
     * The created objects inside the array need to be populated with data for encoding.<br/>
     * If there where already objects in <code>distribInstsGroups</code> array they will be discarded.<br/>
     * @param noDistribInsts number of MsgTypeGroup objects
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.NoDistribInsts)
    public void setNoDistribInsts(Integer noDistribInsts) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter for {@link DistribInstsGroup} array of groups.
     * @return field array value
     */
    @FIXVersion(introduced = "4.3")
    public DistribInstsGroup[] getDistribInstsGroups() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method adds a {@link DistribInstsGroup} object to the existing array of <code>distribInstsGroups</code>
     * and expands the static array with 1 place.<br/>
     * This method will also update <code>noDistribInsts</code> field to the proper value.<br/>
     * Note: If the <code>setNoDistribInsts</code> method has been called there will already be a number of objects in the
     * <code>distribInstsGroups</code> array created.<br/>
     * @return newly created block and added to the array group object
     */
    @FIXVersion(introduced = "4.3")
    public DistribInstsGroup addDistribInstsGroup() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method deletes a {@link DistribInstsGroup} object from the existing array of <code>distribInstsGroups</code>
     * and shrink the static array with 1 place.<br/>
     * If the array does not have the index position then a null object will be returned.)<br/>
     * This method will also update <code>noDistribInsts</code> field to the proper value.<br/>
     * @param index position in array to be deleted starting at 0
     * @return deleted block object
     */
    @FIXVersion(introduced = "4.3")
    public DistribInstsGroup deleteDistribInstsGroup(int index) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Deletes all the {@link DistribInstsGroup} objects from the <code>distribInstsGroups</code> array
     * (sets the array to 0 length)<br/>
     * This method will also update <code>noDistribInsts</code> field and set it to null.<br/>
     * @return number of elements in array cleared
     */
    @FIXVersion(introduced = "4.3")
    public int clearDistribInstsGroups() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
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
            TagEncoder.encode(bao, TagNum.RegistAcctType, registAcctType);
            TagEncoder.encode(bao, TagNum.TaxAdvantageType, taxAdvantageType);
            if (ownershipType != null) {
                TagEncoder.encode(bao, TagNum.OwnershipType, ownershipType.getValue());
            }
            if (noRegistDtls != null) {
                TagEncoder.encode(bao, TagNum.NoRegistDtls, noRegistDtls);
                if (registDtlsGroups != null && registDtlsGroups.length == noRegistDtls.intValue()) {
                    for (int i = 0; i < noRegistDtls.intValue(); i++) {
                        if (registDtlsGroups[i] != null) {
                            bao.write(registDtlsGroups[i].encode(MsgSecureType.ALL_FIELDS));
                        }
                    }
                } else {
                    String error = "RgstDtlsGroups field has been set but there is no data or the number of groups does not match.";
                    LOGGER.severe(error);
                    throw new BadFormatMsgException(SessionRejectReason.IncorrectCountForGroups, getHeader().getMsgType(),
                            TagNum.NoRegistDtls.getValue(), error);
                }
            }
            if (noDistribInsts != null) {
                TagEncoder.encode(bao, TagNum.NoDistribInsts, noDistribInsts);
                if (distribInstsGroups != null && distribInstsGroups.length == noDistribInsts.intValue()) {
                    for (int i = 0; i < noDistribInsts.intValue(); i++) {
                        if (distribInstsGroups[i] != null) {
                            bao.write(distribInstsGroups[i].encode(MsgSecureType.ALL_FIELDS));
                        }
                    }
                } else {
                    String error = "DistribInstsGroup field has been set but there is no data or the number of groups does not match.";
                    LOGGER.severe(error);
                    throw new BadFormatMsgException(SessionRejectReason.IncorrectCountForGroups, getHeader().getMsgType(),
                            TagNum.NoDistribInsts.getValue(), error);
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

            case RegistAcctType:
                registAcctType = new String(tag.value, sessionCharset);
                break;

            case TaxAdvantageType:
                taxAdvantageType = Integer.valueOf(new String(tag.value, sessionCharset));
                break;

            case OwnershipType:
                ownershipType = OwnershipType.valueFor(new String(tag.value, sessionCharset).charAt(0));
                break;

            case NoRegistDtls:
                noRegistDtls = new Integer(new String(tag.value, getSessionCharset()));
                break;

            case NoDistribInsts:
                noDistribInsts = new Integer(new String(tag.value, sessionCharset));
                break;

            default:
                String error = "Tag value [" + tag.tagNum + "] not present in [RegistrationInstructionsMsg] fields.";
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
        StringBuilder b = new StringBuilder("{RegistrationInstructionsMsg=");
        b.append(header != null ? header.toString() : "");
        b.append(System.getProperty("line.separator")).append("{Body=");
        printTagValue(b, TagNum.RegistID, registID);
        printTagValue(b, TagNum.RegistTransType, registTransType);
        printTagValue(b, TagNum.RegistRefID, registRefID);
        printTagValue(b, TagNum.ClOrdID, clOrdID);
        printTagValue(b, parties);
        printTagValue(b, TagNum.Account, account);
        printTagValue(b, TagNum.AcctIDSource, acctIDSource);
        printTagValue(b, TagNum.RegistAcctType, registAcctType);
        printTagValue(b, TagNum.TaxAdvantageType, taxAdvantageType);
        printTagValue(b, TagNum.OwnershipType, ownershipType);
        printTagValue(b, TagNum.NoRegistDtls, noRegistDtls);
        printTagValue(b, registDtlsGroups);
        printTagValue(b, TagNum.NoDistribInsts, noDistribInsts);
        printTagValue(b, distribInstsGroups);
        b.append("}");
        b.append(trailer != null ? trailer.toString() : "").append("}");

        return b.toString();
    }

    // </editor-fold>
}
