/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * RegistrationInstructionsMsg43.java
 *
 * $Id: RegistrationInstructionsMsg43.java,v 1.2 2011-10-29 01:31:23 vrotaru Exp $
 */
package net.hades.fix.message.impl.v43;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.Header;
import net.hades.fix.message.RegistrationInstructionsMsg;
import net.hades.fix.message.comp.Parties;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.group.DistribInstsGroup;
import net.hades.fix.message.group.impl.v43.RgstDtlsGroup43;
import net.hades.fix.message.struct.Tag;
import net.hades.fix.message.type.ApplVerID;
import net.hades.fix.message.type.BeginString;
import net.hades.fix.message.type.SessionRejectReason;
import net.hades.fix.message.type.TagNum;
import net.hades.fix.message.util.MsgUtil;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;

import net.hades.fix.message.comp.impl.v43.Parties43;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.group.RgstDtlsGroup;
import net.hades.fix.message.group.impl.v43.DistribInstsGroup43;
import net.hades.fix.message.util.TagEncoder;

/**
 * FIX version 4.3 RegistrationInstructionsMsg implementation.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 * @created 28/04/2011, 9:32:41 AM
 */
public class RegistrationInstructionsMsg43 extends RegistrationInstructionsMsg {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> PARTIES_COMP_TAGS = new Parties43().getFragmentAllTags();
    protected static final Set<Integer> RGST_DTLS_GROUP_TAGS = new RgstDtlsGroup43().getFragmentAllTags();
    protected static final Set<Integer> DISTRIB_INSTS_GROUP_TAGS = new DistribInstsGroup43().getFragmentAllTags();

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

    public RegistrationInstructionsMsg43() {
        super();
    }

    public RegistrationInstructionsMsg43(Header header, ByteBuffer rawMsg)
    throws InvalidMsgException, TagNotPresentException, BadFormatMsgException {
        super(header, rawMsg);
        SECURED_TAGS = STANDARD_SECURED_TAGS;
    }

    public RegistrationInstructionsMsg43(BeginString beginString) throws InvalidMsgException {
        super(beginString);
        SECURED_TAGS = STANDARD_SECURED_TAGS;
    }

    public RegistrationInstructionsMsg43(BeginString beginString, ApplVerID applVerID) throws InvalidMsgException {
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

    // ACCESSOR METHODS
    //////////////////////////////////////////

    @Override
    public Parties getParties() {
        return parties;
    }

    @Override
    public void setParties() {
        FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired);
        this.parties = new Parties43(context);
    }

    @Override
    public void clearParties() {
        this.parties = null;
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
                registDtlsGroups[i] = new RgstDtlsGroup43(context);
            }
        }
    }

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
        RgstDtlsGroup group = new RgstDtlsGroup43(new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired));
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
                distribInstsGroups[i] = new DistribInstsGroup43(context);
            }
        }
    }

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
        DistribInstsGroup group = new DistribInstsGroup43(new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired));
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
                parties = new Parties43(context);
            }
            parties.decode(tag, message);
        }
        if (RGST_DTLS_GROUP_TAGS.contains(tag.tagNum)) {
            if (noRegistDtls != null && noRegistDtls.intValue() > 0) {
                message.reset();
                registDtlsGroups = new RgstDtlsGroup[noRegistDtls.intValue()];
                for (int i = 0; i < noRegistDtls.intValue(); i++) {
                    RgstDtlsGroup group = new RgstDtlsGroup43(context);
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
                    DistribInstsGroup component = new DistribInstsGroup43(context);
                    component.decode(message);
                    distribInstsGroups[i] = component;
                }
            }
        }
    }

    @Override
    protected String getUnsupportedTagMessage() {
        return "This tag is not supported in [RegistrationInstructionsMsg] message version [4.3].";
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
