/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * MarketDataSnapshotMsg43.java
 *
 * $Id: MarketDataSnapshotMsg43.java,v 1.6 2011-04-14 23:44:34 vrotaru Exp $
 */
package net.hades.fix.message.impl.v43;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.comp.Instrument;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.struct.Tag;
import net.hades.fix.message.type.ApplVerID;
import net.hades.fix.message.type.SessionRejectReason;
import net.hades.fix.message.util.MsgUtil;

import java.io.ByteArrayOutputStream;
import java.nio.ByteBuffer;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.xml.bind.annotation.XmlTransient;

import net.hades.fix.message.Header;
import net.hades.fix.message.MarketDataSnapshotMsg;
import net.hades.fix.message.comp.impl.v43.Instrument43;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.group.MDFullGroup;
import net.hades.fix.message.group.impl.v43.MDFullGroup43;
import net.hades.fix.message.type.BeginString;
import net.hades.fix.message.type.TagNum;
import net.hades.fix.message.util.TagEncoder;

/**
 * FIX version 4.3 MarketDataSnapshottMsg implementation.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.6 $
 * @created 01/04/2009, 8:41:14 AM
 */
@XmlTransient
public class MarketDataSnapshotMsg43 extends MarketDataSnapshotMsg {
    
    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = -1458734101586167513L;

    protected static final Set<Integer> START_COMP_TAGS;
    
    protected static final Set<Integer> ALL_TAGS;

    protected static final Set<Integer> MD_FULL_GROUP_TAGS = new MDFullGroup43().getFragmentAllTags();
    protected static final Set<Integer> INSTRUMENT_COMP_TAGS = new Instrument43().getFragmentAllTags();

    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Static Block">
    
    static {
        ALL_TAGS = new HashSet<Integer>(TAGS);
        ALL_TAGS.addAll(START_DATA_TAGS);
        ALL_TAGS.addAll(MD_FULL_GROUP_TAGS);
        ALL_TAGS.addAll(INSTRUMENT_COMP_TAGS);
        START_COMP_TAGS = new HashSet<Integer>(MD_FULL_GROUP_TAGS);
        START_COMP_TAGS.addAll(INSTRUMENT_COMP_TAGS);
    }
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Attributes">

    protected Set<Integer> STANDARD_SECURED_TAGS = ALL_TAGS;
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Constructors">
    
    public MarketDataSnapshotMsg43() {
        super();
        SECURED_TAGS = STANDARD_SECURED_TAGS;
    }
    
    public MarketDataSnapshotMsg43(Header header, ByteBuffer rawMsg)
        throws InvalidMsgException, TagNotPresentException, BadFormatMsgException {
        super(header, rawMsg);
        SECURED_TAGS = STANDARD_SECURED_TAGS;
        FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired);
        instrument = new Instrument43(context);
    }

    public MarketDataSnapshotMsg43(BeginString beginString) throws InvalidMsgException {
        super(beginString);
        SECURED_TAGS = STANDARD_SECURED_TAGS;
        FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired);
        instrument = new Instrument43(context);
    }
    
    public MarketDataSnapshotMsg43(BeginString beginString, ApplVerID applVerID) throws InvalidMsgException {
        super(beginString, applVerID);
        SECURED_TAGS = STANDARD_SECURED_TAGS;
        FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired);
        instrument = new Instrument43(context);
    }
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Public Methods">

    @Override
    public Set<Integer> getFragmentAllTags() {
        return ALL_TAGS;
    }

    // ACCESSOR METHODS
    //////////////////////////////////////////

    @Override
    public Instrument getInstrument() {
        return instrument;
    }

    @Override
    public void setInstrument() {
        FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired);
        instrument = new Instrument43(context);
    }

    @Override
    public void clearInstrument() {
        this.instrument = null;
    }

    @Override
    public Integer getNoMDEntries() {
        return noMDEntries;
    }

    @Override
    public void setNoMDEntries(Integer noMDEntries) {
        this.noMDEntries = noMDEntries;
        if (noMDEntries != null) {
            mdFullGroups = new MDFullGroup[noMDEntries.intValue()];
            FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired);
            for (int i = 0; i < mdFullGroups.length; i++) {
                mdFullGroups[i] = new MDFullGroup43(context);
            }
        }
    }

    @Override
    public MDFullGroup[] getMdFullGroups() {
        return mdFullGroups;
    }

    @Override
    public MDFullGroup addMdFullGroup() {
        MDFullGroup group = new MDFullGroup43(new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired));
        List<MDFullGroup> groups = new ArrayList<MDFullGroup>();
        if (mdFullGroups != null && mdFullGroups.length > 0) {
            groups = new ArrayList<MDFullGroup>(Arrays.asList(mdFullGroups));
        }
        groups.add(group);
        mdFullGroups = groups.toArray(new MDFullGroup[groups.size()]);
        noMDEntries = new Integer(mdFullGroups.length);

        return group;
    }

    @Override
    public MDFullGroup deleteMdFullGroup(int index) {
        MDFullGroup result = null;
        if (mdFullGroups != null && mdFullGroups.length > 0 && mdFullGroups.length > index) {
            List<MDFullGroup> groups = new ArrayList<MDFullGroup>(Arrays.asList(mdFullGroups));
            result = groups.remove(index);
            mdFullGroups = groups.toArray(new MDFullGroup[groups.size()]);
            if (mdFullGroups.length > 0) {
                noMDEntries = new Integer(mdFullGroups.length);
            } else {
                mdFullGroups = null;
                noMDEntries = null;
            }
        }

        return result;
    }

    @Override
    public int clearMdFullGroups() {
        int result = 0;
        if (mdFullGroups != null && mdFullGroups.length > 0) {
            result = mdFullGroups.length;
            mdFullGroups = null;
            noMDEntries = null;
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
            if (MsgUtil.isTagInList(TagNum.MDReqID, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.MDReqID, mdReqID);
            }
            bao.write(instrument.encode(getMsgSecureTypeForFlag(secured)));
            if (MsgUtil.isTagInList(TagNum.FinancialStatus, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.FinancialStatus, financialStatus);
            }
            if (MsgUtil.isTagInList(TagNum.CorporateAction, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.CorporateAction, corporateAction);
            }
            if (MsgUtil.isTagInList(TagNum.TotalVolumeTraded, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.TotalVolumeTraded, totalVolumeTraded);
            }
            if (MsgUtil.isTagInList(TagNum.TotalVolumeTradedDate, SECURED_TAGS, secured)) {
                TagEncoder.encodeUtcDate(bao, TagNum.TotalVolumeTradedDate, totalVolumeTradedDate);
            }
            if (MsgUtil.isTagInList(TagNum.TotalVolumeTradedTime, SECURED_TAGS, secured)) {
                TagEncoder.encodeUTCTime(bao, TagNum.TotalVolumeTradedTime, totalVolumeTradedTime);
            }
            if (MsgUtil.isTagInList(TagNum.NetChgPrevDay, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.NetChgPrevDay, netChgPrevDay);
            }
            if (noMDEntries != null && MsgUtil.isTagInList(TagNum.NoMDEntries, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.NoMDEntries, noMDEntries);
                if (mdFullGroups != null && mdFullGroups.length == noMDEntries.intValue()) {
                    for (int i = 0; i < noMDEntries.intValue(); i++) {
                        if (mdFullGroups[i] != null) {
                            bao.write(mdFullGroups[i].encode(getMsgSecureTypeForFlag(secured)));
                        }
                    }
                } else {
                    String error = "MDFullGroup field has been set but there is no data or the number of groups does not match.";
                    LOGGER.severe(error);
                    throw new BadFormatMsgException(SessionRejectReason.IncorrectCountForGroups, getHeader().getMsgType(),
                            TagNum.NoMDEntries.getValue(), error);
                }
            }

            result = bao.toByteArray();
        } catch (IOException ex) {
            String error = "Error writing to the byte array.";
            LOGGER.severe(error + " Error was : " + ex.toString());
            throw new BadFormatMsgException(error, ex);
        }

        return result;
    }

    @Override
    protected void setFragmentCompTagValue(Tag tag, ByteBuffer message)
        throws BadFormatMsgException, InvalidMsgException, TagNotPresentException {
        FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired);
        if (INSTRUMENT_COMP_TAGS.contains(tag.tagNum)) {
            if (instrument == null) {
                instrument = new Instrument43(context);
            }
            instrument.decode(tag, message);
        }
        if (MD_FULL_GROUP_TAGS.contains(tag.tagNum)) {
            if (noMDEntries != null && noMDEntries.intValue() > 0) {
                message.reset();
                mdFullGroups = new MDFullGroup[noMDEntries.intValue()];
                for (int i = 0; i < noMDEntries.intValue(); i++) {
                    MDFullGroup component = new MDFullGroup43(context);
                    component.decode(message);
                    mdFullGroups[i] = component;
                }
            }
        }
    }

    @Override
    protected String getUnsupportedTagMessage() {
        return "This tag is not supported in [MarketDataSnapshotMsg] message version [4.3].";
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
