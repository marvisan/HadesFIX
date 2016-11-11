/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * CrossOrderCancelRequestMsg43.java
 *
 * $Id: CrossOrderCancelRequestMsg43.java,v 1.1 2011-05-21 23:53:23 vrotaru Exp $
 */
package net.hades.fix.message.impl.v43;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;


import net.hades.fix.message.CrossOrderCancelRequestMsg;
import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.Header;
import net.hades.fix.message.comp.Instrument;
import net.hades.fix.message.comp.impl.v43.Instrument43;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.group.SideCrossOrdCxlGroup;
import net.hades.fix.message.group.impl.v43.SideCrossOrdCxlGroup43;
import net.hades.fix.message.struct.Tag;
import net.hades.fix.message.type.ApplVerID;
import net.hades.fix.message.type.BeginString;
import net.hades.fix.message.type.SessionRejectReason;
import net.hades.fix.message.type.TagNum;
import net.hades.fix.message.util.MsgUtil;
import net.hades.fix.message.util.TagEncoder;

/**
 * FIX version 4.3 CrossOrderCancelRequestMsg implementation.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 21/05/2011, 9:32:41 AM
 */
public class CrossOrderCancelRequestMsg43 extends CrossOrderCancelRequestMsg {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> TAGS_V43 = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.OrderID.getValue(),
        TagNum.CrossID.getValue(),
        TagNum.OrigCrossID.getValue(),
        TagNum.HostCrossID.getValue(),
        TagNum.CrossType.getValue(),
        TagNum.CrossPrioritization.getValue(),
        TagNum.NoSides.getValue(),
        TagNum.TransactTime.getValue()
    }));

    protected static final Set<Integer> SIDE_CROSS_ORD_MOD_GROUP_TAGS = new SideCrossOrdCxlGroup43().getFragmentAllTags();
    protected static final Set<Integer> INSTRUMENT_COMP_TAGS = new Instrument43().getFragmentAllTags();

    protected static final Set<Integer> START_COMP_TAGS;

    protected static final Set<Integer> ALL_TAGS;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">

    static {
        ALL_TAGS = new HashSet<Integer>(TAGS_V43);
        ALL_TAGS.addAll(SIDE_CROSS_ORD_MOD_GROUP_TAGS);
        ALL_TAGS.addAll(INSTRUMENT_COMP_TAGS);
        START_COMP_TAGS = new HashSet<Integer>(SIDE_CROSS_ORD_MOD_GROUP_TAGS);
        START_COMP_TAGS.addAll(INSTRUMENT_COMP_TAGS);
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">

    protected Set<Integer> STANDARD_SECURED_TAGS = TAGS_V43;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public CrossOrderCancelRequestMsg43() {
        super();
    }

    public CrossOrderCancelRequestMsg43(Header header, ByteBuffer rawMsg)
    throws InvalidMsgException, TagNotPresentException, BadFormatMsgException {
        super(header, rawMsg);
        SECURED_TAGS = STANDARD_SECURED_TAGS;
    }

    public CrossOrderCancelRequestMsg43(BeginString beginString) throws InvalidMsgException {
        super(beginString);
        SECURED_TAGS = STANDARD_SECURED_TAGS;
    }

    public CrossOrderCancelRequestMsg43(BeginString beginString, ApplVerID applVerID) throws InvalidMsgException {
        super(beginString, applVerID);
        SECURED_TAGS = STANDARD_SECURED_TAGS;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Public Methods">

    @Override
    public Set<Integer> getFragmentTags() {
        return TAGS_V43;
    }

    @Override
    public Set<Integer> getFragmentAllTags() {
        return ALL_TAGS;
    }

    // ACCESSOR METHODS
    //////////////////////////////////////////

    @Override
    public Integer getNoSides() {
        return noSides;
    }

    @Override
    public void setNoSides(Integer noSides) {
        this.noSides = noSides;
        if (noSides != null) {
            sideCrossOrdCxlGroups = new SideCrossOrdCxlGroup[noSides.intValue()];
            for (int i = 0; i < sideCrossOrdCxlGroups.length; i++) {
                sideCrossOrdCxlGroups[i] = new SideCrossOrdCxlGroup43(new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired));
            }
        }
    }

    @Override
    public SideCrossOrdCxlGroup[] getSideCrossOrdCxlGroups() {
        return sideCrossOrdCxlGroups;
    }

    public void setSideCrossOrdCxlGroups(SideCrossOrdCxlGroup[] sideCrossOrdCxlGroups) {
        this.sideCrossOrdCxlGroups = sideCrossOrdCxlGroups;
        if (sideCrossOrdCxlGroups != null) {
            noSides = new Integer(sideCrossOrdCxlGroups.length);
        }
    }

    @Override
    public SideCrossOrdCxlGroup addSideCrossOrdCxlGroup() {
        SideCrossOrdCxlGroup group = new SideCrossOrdCxlGroup43(new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired));
        List<SideCrossOrdCxlGroup> groups = new ArrayList<SideCrossOrdCxlGroup>();
        if (sideCrossOrdCxlGroups != null && sideCrossOrdCxlGroups.length > 0) {
            groups = new ArrayList<SideCrossOrdCxlGroup>(Arrays.asList(sideCrossOrdCxlGroups));
        }
        groups.add(group);
        sideCrossOrdCxlGroups = groups.toArray(new SideCrossOrdCxlGroup[groups.size()]);
        noSides = new Integer(sideCrossOrdCxlGroups.length);

        return group;
    }

    @Override
    public SideCrossOrdCxlGroup deleteSideCrossOrdCxlGroup(int index) {
        SideCrossOrdCxlGroup result = null;
        if (sideCrossOrdCxlGroups != null && sideCrossOrdCxlGroups.length > 0 && sideCrossOrdCxlGroups.length > index) {
            List<SideCrossOrdCxlGroup> groups = new ArrayList<SideCrossOrdCxlGroup>(Arrays.asList(sideCrossOrdCxlGroups));
            result = groups.remove(index);
            sideCrossOrdCxlGroups = groups.toArray(new SideCrossOrdCxlGroup[groups.size()]);
            if (sideCrossOrdCxlGroups.length > 0) {
                noSides = new Integer(sideCrossOrdCxlGroups.length);
            } else {
                sideCrossOrdCxlGroups = null;
                noSides = null;
            }
        }

        return result;
    }

    @Override
    public int clearSideCrossOrdCxlGroups() {
        int result = 0;
        if (sideCrossOrdCxlGroups != null && sideCrossOrdCxlGroups.length > 0) {
            result = sideCrossOrdCxlGroups.length;
            sideCrossOrdCxlGroups = null;
            noSides = null;
        }

        return result;
    }

    @Override
    public Instrument getInstrument() {
        return instrument;
    }

    @Override
    public void setInstrument() {
        FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired);
        this.instrument = new Instrument43(context);
    }

    public void setInstrument(Instrument instrument) {
        this.instrument = instrument;
    }
    
    @Override
    public void clearInstrument() {
        this.instrument = null;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

    @Override
    protected byte[] encodeFragmentSecured(boolean secured) throws TagNotPresentException, BadFormatMsgException {
        byte[] result = new byte[0];
        ByteArrayOutputStream bao = new ByteArrayOutputStream();
        try {
            if (MsgUtil.isTagInList(TagNum.OrderID, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.OrderID, orderID);
            }
            if (MsgUtil.isTagInList(TagNum.CrossID, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.CrossID, crossID);
            }
            if (MsgUtil.isTagInList(TagNum.OrigCrossID, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.OrigCrossID, origCrossID);
            }
            if (crossType != null && MsgUtil.isTagInList(TagNum.CrossType, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.CrossType, crossType.getValue());
            }
            if (crossPrioritization != null && MsgUtil.isTagInList(TagNum.CrossPrioritization, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.CrossPrioritization, crossPrioritization.getValue());
            }
            if (noSides != null && MsgUtil.isTagInList(TagNum.NoSides, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.NoSides, noSides);
                if (sideCrossOrdCxlGroups != null && sideCrossOrdCxlGroups.length == noSides.intValue()) {
                    for (int i = 0; i < noSides.intValue(); i++) {
                        if (sideCrossOrdCxlGroups[i] != null) {
                            bao.write(sideCrossOrdCxlGroups[i].encode(getMsgSecureTypeForFlag(secured)));
                        }
                    }
                } else {
                    String error = "SideCrossOrdCxlGroups field has been set but there is no data or the number of groups does not match.";
                    LOGGER.severe(error);
                    throw new BadFormatMsgException(SessionRejectReason.IncorrectCountForGroups, getHeader().getMsgType(),
                            TagNum.NoSides.getValue(), error);
                }
            }
            if (instrument != null) {
                bao.write(instrument.encode(getMsgSecureTypeForFlag(secured)));
            }
            if (MsgUtil.isTagInList(TagNum.TransactTime, SECURED_TAGS, secured)) {
                TagEncoder.encodeUtcTimestamp(bao, TagNum.TransactTime, transactTime);
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
        if (SIDE_CROSS_ORD_MOD_GROUP_TAGS.contains(tag.tagNum)) {
            if (noSides != null && noSides.intValue() > 0) {
                message.reset();
                sideCrossOrdCxlGroups = new SideCrossOrdCxlGroup[noSides.intValue()];
                for (int i = 0; i < noSides.intValue(); i++) {
                    SideCrossOrdCxlGroup component = new SideCrossOrdCxlGroup43(context);
                    component.decode(message);
                    sideCrossOrdCxlGroups[i] = component;
                }
            }
        }
        if (INSTRUMENT_COMP_TAGS.contains(tag.tagNum)) {
            if (instrument == null) {
                instrument = new Instrument43(context);
            }
            instrument.decode(tag, message);
        }
    }

    @Override
    protected String getUnsupportedTagMessage() {
        return "This tag is not supported in [CrossOrderCancelRequestMsg] message version [4.3].";
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
