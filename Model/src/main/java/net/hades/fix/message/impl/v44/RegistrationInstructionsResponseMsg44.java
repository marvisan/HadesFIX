/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * RegistrationInstructionsResponseMsg44.java
 *
 * $Id: RegistrationInstructionsResponseMsg44.java,v 1.1 2011-10-29 02:16:41 vrotaru Exp $
 */
package net.hades.fix.message.impl.v44;

import net.hades.fix.message.struct.Tag;
import net.hades.fix.message.type.ApplVerID;
import net.hades.fix.message.type.RegistRejReasonCode;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import net.hades.fix.message.FIXFragment;
import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.Header;
import net.hades.fix.message.RegistrationInstructionsResponseMsg;
import net.hades.fix.message.comp.Parties;
import net.hades.fix.message.comp.impl.v44.Parties44;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.group.PartyGroup;
import net.hades.fix.message.type.AcctIDSource;
import net.hades.fix.message.type.BeginString;
import net.hades.fix.message.type.RegistStatus;
import net.hades.fix.message.type.RegistTransType;
import net.hades.fix.message.type.TagNum;
import net.hades.fix.message.util.MsgUtil;
import net.hades.fix.message.util.TagEncoder;

/**
 * FIX version 4.4 RegistrationInstructionsResponseMsg implementation.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 28/04/2011, 9:32:41 AM
 */
@XmlRootElement(name="RgstInstrctnsRsp")
@XmlType(propOrder = {"header", "partyIDGroups"})
@XmlAccessorType(XmlAccessType.NONE)
public class RegistrationInstructionsResponseMsg44 extends RegistrationInstructionsResponseMsg {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> PARTIES_COMP_TAGS = new Parties44().getFragmentAllTags();

    protected static final Set<Integer> START_COMP_TAGS;

    protected static final Set<Integer> ALL_TAGS;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">

    static {
        ALL_TAGS = new HashSet<Integer>(TAGS);
        ALL_TAGS.addAll(PARTIES_COMP_TAGS);
        START_COMP_TAGS = new HashSet<Integer>(PARTIES_COMP_TAGS);
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">

    protected Set<Integer> STANDARD_SECURED_TAGS = TAGS;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public RegistrationInstructionsResponseMsg44() {
        super();
    }

    public RegistrationInstructionsResponseMsg44(Header header, ByteBuffer rawMsg)
    throws InvalidMsgException, TagNotPresentException, BadFormatMsgException {
        super(header, rawMsg);
        SECURED_TAGS = STANDARD_SECURED_TAGS;
    }

    public RegistrationInstructionsResponseMsg44(BeginString beginString) throws InvalidMsgException {
        super(beginString);
        SECURED_TAGS = STANDARD_SECURED_TAGS;
    }

    public RegistrationInstructionsResponseMsg44(BeginString beginString, ApplVerID applVerID) throws InvalidMsgException {
        super(beginString, applVerID);
        SECURED_TAGS = STANDARD_SECURED_TAGS;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Public Methods">

    @Override
    public Set<Integer> getFragmentTags() {
        return TAGS;
    }

    @Override
    public Set<Integer> getFragmentAllTags() {
        return ALL_TAGS;
    }

    @Override
    public void copyFixmlData(FIXFragment fragment) {
        RegistrationInstructionsResponseMsg44 fixml = (RegistrationInstructionsResponseMsg44) fragment;
        if (fixml.getRegistID() != null) {
            registID = fixml.getRegistID();
        }
        if (fixml.getRegistTransType() != null) {
            registTransType = fixml.getRegistTransType();
        }
        if (fixml.getRegistRefID() != null) {
            registRefID = fixml.getRegistRefID();
        }
        if (fixml.getClOrdID() != null) {
            clOrdID = fixml.getClOrdID();
        }
        if (fixml.getParties() != null) {
            setParties(fixml.getParties());
        }
        if (fixml.getAccount() != null) {
            account = fixml.getAccount();
        }
        if (fixml.getAcctIDSource() != null) {
            acctIDSource = fixml.getAcctIDSource();
        }
        if (fixml.getRegistStatus() != null) {
            registStatus = fixml.getRegistStatus();
        }
        if (fixml.getRegistRejReasonCode() != null) {
            registRejReasonCode = fixml.getRegistRejReasonCode();
        }
        if (fixml.getRegistRejReasonText() != null) {
            registRejReasonText = fixml.getRegistRejReasonText();
        }
    }

    // ACCESSOR METHODS
    //////////////////////////////////////////

    @XmlElementRef
    @Override
    public Header getHeader() {
        return header;
    }

    @Override
    public void setHeader(Header header) {
        this.header = header;
    }

    @XmlAttribute(name = "ID")
    @Override
    public String getRegistID() {
        return registID;
    }

    @Override
    public void setRegistID(String registID) {
        this.registID = registID;
    }

    @XmlAttribute(name = "TransTyp")
    @Override
    public RegistTransType getRegistTransType() {
        return registTransType;
    }

    @Override
    public void setRegistTransType(RegistTransType registTransType) {
        this.registTransType = registTransType;
    }

    @XmlAttribute(name = "RefID")
    @Override
    public String getRegistRefID() {
        return registRefID;
    }

    @Override
    public void setRegistRefID(String registRefID) {
        this.registRefID = registRefID;
    }

    @XmlAttribute(name = "ClOrdID")
    @Override
    public String getClOrdID() {
        return clOrdID;
    }

    @Override
    public void setClOrdID(String clOrdID) {
        this.clOrdID = clOrdID;
    }

    @Override
    public Parties getParties() {
        return parties;
    }

    @Override
    public void setParties() {
        FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired);
        this.parties = new Parties44(context);
    }

    @Override
    public void clearParties() {
        this.parties = null;
    }

    public void setParties(Parties parties) {
        this.parties = parties;
    }

    @XmlElementRef
    public PartyGroup[] getPartyIDGroups() {
        return parties == null ? null : parties.getPartyIDGroups();
    }

    public void setPartyIDGroups(PartyGroup[] partyIDGroups) {
        if (partyIDGroups != null) {
            if (parties == null) {
                setParties();
            }
            ((Parties44) parties).setPartyIDGroups(partyIDGroups);
        }
    }

    @XmlAttribute(name = "Acct")
    @Override
    public String getAccount() {
        return account;
    }

    @Override
    public void setAccount(String account) {
        this.account = account;
    }

    @XmlAttribute(name = "AcctIDSrc")
    @Override
    public AcctIDSource getAcctIDSource() {
        return acctIDSource;
    }

    @Override
    public void setAcctIDSource(AcctIDSource acctIDSource) {
        this.acctIDSource = acctIDSource;
    }

    @XmlAttribute(name = "RegStat")
    @Override
    public RegistStatus getRegistStatus() {
        return registStatus;
    }

    @Override
    public void setRegistStatus(RegistStatus registStatus) {
        this.registStatus = registStatus;
    }

    @XmlAttribute(name = "RejRsnCd")
    @Override
    public RegistRejReasonCode getRegistRejReasonCode() {
        return registRejReasonCode;
    }

    @Override
    public void setRegistRejReasonCode(RegistRejReasonCode registRejReasonCode) {
        this.registRejReasonCode = registRejReasonCode;
    }

    @XmlAttribute(name = "Dtls")
    @Override
    public String getRegistRejReasonText() {
        return registRejReasonText;
    }

    @Override
    public void setRegistRejReasonText(String registRejReasonText) {
        this.registRejReasonText = registRejReasonText;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

    @Override
    protected byte[] encodeFragmentSecured(boolean secured) throws TagNotPresentException, BadFormatMsgException {
        byte[] result = new byte[0];
        ByteArrayOutputStream bao = new ByteArrayOutputStream();
        try {
            if (MsgUtil.isTagInList(TagNum.RegistID, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.RegistID, registID);
            }
            if (registTransType != null && MsgUtil.isTagInList(TagNum.RegistTransType, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.RegistTransType, registTransType.getValue());
            }
            if (MsgUtil.isTagInList(TagNum.RegistRefID, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.RegistRefID, registRefID);
            }
            if (MsgUtil.isTagInList(TagNum.ClOrdID, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.ClOrdID, clOrdID);
            }
            if (parties != null) {
                bao.write(parties.encode(getMsgSecureTypeForFlag(secured)));
            }
            if (MsgUtil.isTagInList(TagNum.Account, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.Account, account);
            }
            if (acctIDSource != null && MsgUtil.isTagInList(TagNum.AcctIDSource, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.AcctIDSource, acctIDSource.getValue());
            }
            if (registStatus != null && MsgUtil.isTagInList(TagNum.RegistStatus, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.RegistStatus, registStatus.getValue());
            }
            if (registRejReasonCode != null && MsgUtil.isTagInList(TagNum.RegistRejReasonCode, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.RegistRejReasonCode, registRejReasonCode.getValue());
            }
            if (MsgUtil.isTagInList(TagNum.RegistRejReasonText, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.RegistRejReasonText, registRejReasonText);
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
    protected void setFragmentCompTagValue(Tag tag, ByteBuffer message)
    throws BadFormatMsgException, InvalidMsgException, TagNotPresentException {
        FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired);
        if (PARTIES_COMP_TAGS.contains(tag.tagNum)) {
            if (parties == null) {
                parties = new Parties44(context);
            }
            parties.decode(tag, message);
        }
    }

    @Override
    protected String getUnsupportedTagMessage() {
        return "This tag is not supported in [RegistrationInstructionsResponseMsg] message version [4.4].";
    }

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

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Package Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Private Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Inner Classes">
    // </editor-fold>
}
