/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * RegistrationInstructionsMsg44.java
 *
 * $Id: RegistrationInstructionsMsg44.java,v 1.2 2011-10-29 01:31:21 vrotaru Exp $
 */
package net.hades.fix.message.impl.v44;

import net.hades.fix.message.struct.Tag;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
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
import net.hades.fix.message.RegistrationInstructionsMsg;
import net.hades.fix.message.comp.Parties;
import net.hades.fix.message.comp.impl.v44.Parties44;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.group.DistribInstsGroup;
import net.hades.fix.message.group.PartyGroup;
import net.hades.fix.message.group.RgstDtlsGroup;
import net.hades.fix.message.group.impl.v44.DistribInstsGroup44;
import net.hades.fix.message.group.impl.v44.RgstDtlsGroup44;
import net.hades.fix.message.type.AcctIDSource;
import net.hades.fix.message.type.ApplVerID;
import net.hades.fix.message.type.BeginString;
import net.hades.fix.message.type.OwnershipType;
import net.hades.fix.message.type.RegistTransType;
import net.hades.fix.message.type.SessionRejectReason;
import net.hades.fix.message.type.TagNum;
import net.hades.fix.message.util.MsgUtil;
import net.hades.fix.message.util.TagEncoder;

/**
 * FIX version 4.4 RegistrationInstructionsMsg implementation.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 * @created 28/04/2011, 9:32:41 AM
 */
@XmlRootElement(name="RgstInstrctns")
@XmlType(propOrder = {"header", "partyIDGroups", "rgstDtlsGroups", "distribInstsGroups"})
@XmlAccessorType(XmlAccessType.NONE)
public class RegistrationInstructionsMsg44 extends RegistrationInstructionsMsg {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> PARTIES_COMP_TAGS = new Parties44().getFragmentAllTags();
    protected static final Set<Integer> RGST_DTLS_GROUP_TAGS = new RgstDtlsGroup44().getFragmentAllTags();
    protected static final Set<Integer> DISTRIB_INSTS_GROUP_TAGS = new DistribInstsGroup44().getFragmentAllTags();

    protected static final Set<Integer> START_COMP_TAGS;

    protected static final Set<Integer> ALL_TAGS;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">

    static {
        ALL_TAGS = new HashSet<Integer>(TAGS);
        ALL_TAGS.addAll(PARTIES_COMP_TAGS);
        ALL_TAGS.addAll(RGST_DTLS_GROUP_TAGS);
        ALL_TAGS.addAll(DISTRIB_INSTS_GROUP_TAGS);
        START_COMP_TAGS = new HashSet<Integer>(PARTIES_COMP_TAGS);
        START_COMP_TAGS.addAll(RGST_DTLS_GROUP_TAGS);
        START_COMP_TAGS.addAll(DISTRIB_INSTS_GROUP_TAGS);
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">

    protected Set<Integer> STANDARD_SECURED_TAGS = TAGS;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public RegistrationInstructionsMsg44() {
        super();
    }

    public RegistrationInstructionsMsg44(Header header, ByteBuffer rawMsg)
    throws InvalidMsgException, TagNotPresentException, BadFormatMsgException {
        super(header, rawMsg);
        SECURED_TAGS = STANDARD_SECURED_TAGS;
    }

    public RegistrationInstructionsMsg44(BeginString beginString) throws InvalidMsgException {
        super(beginString);
        SECURED_TAGS = STANDARD_SECURED_TAGS;
    }

    public RegistrationInstructionsMsg44(BeginString beginString, ApplVerID applVerID) throws InvalidMsgException {
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
        RegistrationInstructionsMsg44 fixml = (RegistrationInstructionsMsg44) fragment;
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
        if (fixml.getRegistAcctType() != null) {
            registAcctType = fixml.getRegistAcctType();
        }
        if (fixml.getTaxAdvantageType() != null) {
            taxAdvantageType = fixml.getTaxAdvantageType();
        }
        if (fixml.getOwnershipType() != null) {
            ownershipType = fixml.getOwnershipType();
        }
        if (fixml.getRgstDtlsGroups() != null && fixml.getRgstDtlsGroups().length > 0) {
            setRgstDtlsGroups(fixml.getRgstDtlsGroups());
        }
        if (fixml.getDistribInstsGroups() != null && fixml.getDistribInstsGroups().length > 0) {
            setDistribInstsGroups(fixml.getDistribInstsGroups());
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

    @XmlAttribute(name = "AcctTyp")
    @Override
    public String getRegistAcctType() {
        return registAcctType;
    }

    @Override
    public void setRegistAcctType(String registAcctType) {
        this.registAcctType = registAcctType;
    }

    @XmlAttribute(name = "TaxAdvantageTyp")
    @Override
    public Integer getTaxAdvantageType() {
        return taxAdvantageType;
    }

    @Override
    public void setTaxAdvantageType(Integer taxAdvantageType) {
        this.taxAdvantageType = taxAdvantageType;
    }

    @XmlAttribute(name = "OwnershipTyp")
    @Override
    public OwnershipType getOwnershipType() {
        return ownershipType;
    }

    @Override
    public void setOwnershipType(OwnershipType ownershipType) {
        this.ownershipType = ownershipType;
    }


    @Override
    public Integer getNoRegistDtls() {
        return noRegistDtls;
    }

    @Override
    public void setNoRegistDtls(Integer noRegistDtls) {
        this.noRegistDtls = noRegistDtls;
        if (noRegistDtls != null) {
            registDtlsGroups = new RgstDtlsGroup[noRegistDtls.intValue()];
            FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired);
            for (int i = 0; i < registDtlsGroups.length; i++) {
                registDtlsGroups[i] = new RgstDtlsGroup44(context);
            }
        }
    }

    @XmlElementRef
    @Override
    public RgstDtlsGroup[] getRgstDtlsGroups() {
        return registDtlsGroups;
    }

    public void setRgstDtlsGroups(RgstDtlsGroup[] registDtlsGroups) {
        this.registDtlsGroups = registDtlsGroups;
        if (registDtlsGroups != null) {
            noRegistDtls = new Integer(registDtlsGroups.length);
        }
    }

    @Override
    public RgstDtlsGroup addRgstDtlsGroup() {
        RgstDtlsGroup group = new RgstDtlsGroup44(new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired));
        List<RgstDtlsGroup> groups = new ArrayList<RgstDtlsGroup>();
        if (registDtlsGroups != null && registDtlsGroups.length > 0) {
            groups = new ArrayList<RgstDtlsGroup>(Arrays.asList(registDtlsGroups));
        }
        groups.add(group);
        registDtlsGroups = groups.toArray(new RgstDtlsGroup[groups.size()]);
        noRegistDtls = new Integer(registDtlsGroups.length);

        return group;
    }

    @Override
    public RgstDtlsGroup deleteRgstDtlsGroup(int index) {
        RgstDtlsGroup result = null;
        if (registDtlsGroups != null && registDtlsGroups.length > 0 && registDtlsGroups.length > index) {
            List<RgstDtlsGroup> groups = new ArrayList<RgstDtlsGroup>(Arrays.asList(registDtlsGroups));
            result = groups.remove(index);
            registDtlsGroups = groups.toArray(new RgstDtlsGroup[groups.size()]);
            if (registDtlsGroups.length > 0) {
                noRegistDtls = new Integer(registDtlsGroups.length);
            } else {
                registDtlsGroups = null;
                noRegistDtls = null;
            }
        }

        return result;
    }

    @Override
    public int clearRgstDtlsGroup() {
        int result = 0;
        if (registDtlsGroups != null && registDtlsGroups.length > 0) {
            result = registDtlsGroups.length;
            registDtlsGroups = null;
            noRegistDtls = null;
        }

        return result;
    }

    @Override
    public Integer getNoDistribInsts() {
        return noDistribInsts;
    }

    @Override
    public void setNoDistribInsts(Integer noDistribInsts) {
        this.noDistribInsts = noDistribInsts;
        if (noDistribInsts != null) {
            distribInstsGroups = new DistribInstsGroup[noDistribInsts.intValue()];
            FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired);
            for (int i = 0; i < distribInstsGroups.length; i++) {
                distribInstsGroups[i] = new DistribInstsGroup44(context);
            }
        }
    }

    @XmlElementRef
    @Override
    public DistribInstsGroup[] getDistribInstsGroups() {
        return distribInstsGroups;
    }

    public void setDistribInstsGroups(DistribInstsGroup[] distribInstsGroups) {
        this.distribInstsGroups = distribInstsGroups;
        if (distribInstsGroups != null) {
            noDistribInsts = new Integer(distribInstsGroups.length);
        }
    }

    @Override
    public DistribInstsGroup addDistribInstsGroup() {
        DistribInstsGroup group = new DistribInstsGroup44(new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired));
        List<DistribInstsGroup> groups = new ArrayList<DistribInstsGroup>();
        if (distribInstsGroups != null && distribInstsGroups.length > 0) {
            groups = new ArrayList<DistribInstsGroup>(Arrays.asList(distribInstsGroups));
        }
        groups.add(group);
        distribInstsGroups = groups.toArray(new DistribInstsGroup[groups.size()]);
        noDistribInsts = new Integer(distribInstsGroups.length);

        return group;
    }

    @Override
    public DistribInstsGroup deleteDistribInstsGroup(int index) {
        DistribInstsGroup result = null;
        if (distribInstsGroups != null && distribInstsGroups.length > 0 && distribInstsGroups.length > index) {
            List<DistribInstsGroup> groups = new ArrayList<DistribInstsGroup>(Arrays.asList(distribInstsGroups));
            result = groups.remove(index);
            distribInstsGroups = groups.toArray(new DistribInstsGroup[groups.size()]);
            if (distribInstsGroups.length > 0) {
                noDistribInsts = new Integer(distribInstsGroups.length);
            } else {
                distribInstsGroups = null;
                noDistribInsts = null;
            }
        }

        return result;
    }

    @Override
    public int clearDistribInstsGroups() {
        int result = 0;
        if (distribInstsGroups != null && distribInstsGroups.length > 0) {
            result = distribInstsGroups.length;
            distribInstsGroups = null;
            noDistribInsts = null;
        }

        return result;
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
            if (MsgUtil.isTagInList(TagNum.RegistAcctType, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.RegistAcctType, registAcctType);
            }
            if (MsgUtil.isTagInList(TagNum.TaxAdvantageType, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.TaxAdvantageType, taxAdvantageType);
            }
            if (ownershipType != null && MsgUtil.isTagInList(TagNum.OwnershipType, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.OwnershipType, ownershipType.getValue());
            }
            if (noRegistDtls != null && MsgUtil.isTagInList(TagNum.NoRegistDtls, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.NoRegistDtls, noRegistDtls);
                if (registDtlsGroups != null && registDtlsGroups.length == noRegistDtls.intValue()) {
                    for (int i = 0; i < noRegistDtls.intValue(); i++) {
                        if (registDtlsGroups[i] != null) {
                            bao.write(registDtlsGroups[i].encode(getMsgSecureTypeForFlag(secured)));
                        }
                    }
                } else {
                    String error = "RgstDtlsGroups field has been set but there is no data or the number of groups does not match.";
                    LOGGER.severe(error);
                    throw new BadFormatMsgException(SessionRejectReason.IncorrectCountForGroups, getHeader().getMsgType(),
                            TagNum.NoLegs.getValue(), error);
                }
            }
            if (noDistribInsts != null && MsgUtil.isTagInList(TagNum.NoDistribInsts, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.NoDistribInsts, noDistribInsts);
                if (distribInstsGroups != null && distribInstsGroups.length == noDistribInsts.intValue()) {
                    for (int i = 0; i < noDistribInsts.intValue(); i++) {
                        if (distribInstsGroups[i] != null) {
                            bao.write(distribInstsGroups[i].encode(getMsgSecureTypeForFlag(secured)));
                        }
                    }
                } else {
                    String error = "DistribInstsGroup field has been set but there is no data or the number of groups does not match.";
                    LOGGER.severe(error);
                    throw new BadFormatMsgException(SessionRejectReason.IncorrectCountForGroups, getHeader().getMsgType(),
                            TagNum.NoUnderlyings.getValue(), error);
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
    protected void setFragmentCompTagValue(Tag tag, ByteBuffer message)
    throws BadFormatMsgException, InvalidMsgException, TagNotPresentException {
        FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired);
        if (PARTIES_COMP_TAGS.contains(tag.tagNum)) {
            if (parties == null) {
                parties = new Parties44(context);
            }
            parties.decode(tag, message);
        }
        if (RGST_DTLS_GROUP_TAGS.contains(tag.tagNum)) {
            if (noRegistDtls != null && noRegistDtls.intValue() > 0) {
                message.reset();
                registDtlsGroups = new RgstDtlsGroup[noRegistDtls.intValue()];
                for (int i = 0; i < noRegistDtls.intValue(); i++) {
                    RgstDtlsGroup group = new RgstDtlsGroup44(context);
                    group.decode(message);
                    registDtlsGroups[i] = group;
                }
            }
        }
        if (DISTRIB_INSTS_GROUP_TAGS.contains(tag.tagNum)) {
            if (noDistribInsts != null && noDistribInsts.intValue() > 0) {
                message.reset();
                distribInstsGroups = new DistribInstsGroup[noDistribInsts.intValue()];
                for (int i = 0; i < noDistribInsts.intValue(); i++) {
                    DistribInstsGroup component = new DistribInstsGroup44(context);
                    component.decode(message);
                    distribInstsGroups[i] = component;
                }
            }
        }
    }

    @Override
    protected String getUnsupportedTagMessage() {
        return "This tag is not supported in [RegistrationInstructionsMsg] message version [4.4].";
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
