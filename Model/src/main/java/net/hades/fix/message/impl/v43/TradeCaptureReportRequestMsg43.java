/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * TradeCaptureReportRequestMsg43.java
 *
 * $Id: TradeCaptureReportRequestMsg43.java,v 1.1 2011-10-08 08:42:54 vrotaru Exp $
 */
package net.hades.fix.message.impl.v43;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.Header;
import net.hades.fix.message.TradeCaptureReportRequestMsg;
import net.hades.fix.message.comp.Instrument;
import net.hades.fix.message.comp.Parties;
import net.hades.fix.message.comp.impl.v43.Instrument43;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.group.impl.v43.TrdCapDtGroup43;
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
import net.hades.fix.message.group.TrdCapDtGroup;
import net.hades.fix.message.util.TagEncoder;

/**
 * FIX version 4.3 TradeCaptureReportRequestMsg implementation.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 21/04/2009, 9:32:41 AM
 */
public class TradeCaptureReportRequestMsg43 extends TradeCaptureReportRequestMsg {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> PARTIES_COMP_TAGS = new Parties43().getFragmentAllTags();
    protected static final Set<Integer> INSTRUMENT_COMP_TAGS = new Instrument43().getFragmentAllTags();
    protected static final Set<Integer> TRD_CAP_DATE_GROUP_TAGS = new TrdCapDtGroup43().getFragmentAllTags();

    protected static final Set<Integer> START_COMP_TAGS;

    protected static final Set<Integer> ALL_TAGS;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">

    static {
        ALL_TAGS = new HashSet<Integer>(TAGS);
        ALL_TAGS.addAll(START_DATA_TAGS);
        ALL_TAGS.addAll(PARTIES_COMP_TAGS);
        ALL_TAGS.addAll(INSTRUMENT_COMP_TAGS);
        ALL_TAGS.addAll(TRD_CAP_DATE_GROUP_TAGS);
        START_COMP_TAGS = new HashSet<Integer>(PARTIES_COMP_TAGS);
        START_COMP_TAGS.addAll(INSTRUMENT_COMP_TAGS);
        START_COMP_TAGS.addAll(TRD_CAP_DATE_GROUP_TAGS);
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">

    protected Set<Integer> STANDARD_SECURED_TAGS = TAGS;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public TradeCaptureReportRequestMsg43() {
        super();
    }

    public TradeCaptureReportRequestMsg43(Header header, ByteBuffer rawMsg)
    throws InvalidMsgException, TagNotPresentException, BadFormatMsgException {
        super(header, rawMsg);
        SECURED_TAGS = STANDARD_SECURED_TAGS;
    }

    public TradeCaptureReportRequestMsg43(BeginString beginString) throws InvalidMsgException {
        super(beginString);
        SECURED_TAGS = STANDARD_SECURED_TAGS;
    }

    public TradeCaptureReportRequestMsg43(BeginString beginString, ApplVerID applVerID) throws InvalidMsgException {
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
    public Instrument getInstrument() {
        return instrument;
    }

    @Override
    public void setInstrument() {
        FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired);
        this.instrument = new Instrument43(context);
    }

    @Override
    public void clearInstrument() {
        this.instrument = null;
    }

    public void setInstrument(Instrument instrument) {
        this.instrument = instrument;
    }

    @Override
    public Integer getNoDates() {
        return noDates;
    }

    @Override
    public void setNoDates(Integer noDates) {
        this.noDates = noDates;
        if (noDates != null) {
            trdCapDtGroups = new TrdCapDtGroup[noDates.intValue()];
            FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired);
            for (int i = 0; i < trdCapDtGroups.length; i++) {
                trdCapDtGroups[i] = new TrdCapDtGroup43(context);
            }
        }
    }

    @Override
    public TrdCapDtGroup[] getTrdCapDtGroups() {
        return trdCapDtGroups;
    }
    
    public void setTrdCapDtGroups(TrdCapDtGroup[] trdCapDtGroups) {
        this.trdCapDtGroups = trdCapDtGroups;
        if (trdCapDtGroups != null) {
            noDates = new Integer(trdCapDtGroups.length);
        }
    }

    @Override
    public TrdCapDtGroup addTrdCapDtGroup() {
        TrdCapDtGroup group = new TrdCapDtGroup43(new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired));
        List<TrdCapDtGroup> groups = new ArrayList<TrdCapDtGroup>();
        if (trdCapDtGroups != null && trdCapDtGroups.length > 0) {
            groups = new ArrayList<TrdCapDtGroup>(Arrays.asList(trdCapDtGroups));
        }
        groups.add(group);
        trdCapDtGroups = groups.toArray(new TrdCapDtGroup[groups.size()]);
        noDates = new Integer(trdCapDtGroups.length);

        return group;
    }
    
    @Override
    public TrdCapDtGroup deleteTrdCapDtGroup(int index) {
        TrdCapDtGroup result = null;
        if (trdCapDtGroups != null && trdCapDtGroups.length > 0 && trdCapDtGroups.length > index) {
            List<TrdCapDtGroup> groups = new ArrayList<TrdCapDtGroup>(Arrays.asList(trdCapDtGroups));
            result = groups.remove(index);
            trdCapDtGroups = groups.toArray(new TrdCapDtGroup[groups.size()]);
            if (trdCapDtGroups.length > 0) {
                noDates = new Integer(trdCapDtGroups.length);
            } else {
                trdCapDtGroups = null;
                noDates = null;
            }
        }

        return result;
    }
    
    @Override
    public int clearTrdCapDtGroups() {
        int result = 0;
        if (trdCapDtGroups != null && trdCapDtGroups.length > 0) {
            result = trdCapDtGroups.length;
            trdCapDtGroups = null;
            noDates = null;
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
            if (MsgUtil.isTagInList(TagNum.TradeRequestID, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.TradeRequestID, tradeRequestID);
            }
            if (tradeRequestType != null && MsgUtil.isTagInList(TagNum.TradeRequestType, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.TradeRequestType, tradeRequestType.getValue());
            }
            if (subscriptionRequestType != null && MsgUtil.isTagInList(TagNum.SubscriptionRequestType, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.SubscriptionRequestType, subscriptionRequestType.getValue());
            }
            if (MsgUtil.isTagInList(TagNum.ExecID, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.ExecID, execID);
            }
            if (MsgUtil.isTagInList(TagNum.OrderID, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.OrderID, orderID);
            }
            if (MsgUtil.isTagInList(TagNum.ClOrdID, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.ClOrdID, clOrdID);
            }
            if (matchStatus != null && MsgUtil.isTagInList(TagNum.MatchStatus, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.MatchStatus, matchStatus.getValue());
            }
            if (parties != null) {
                bao.write(parties.encode(getMsgSecureTypeForFlag(secured)));
            }
            if (instrument != null) {
                bao.write(instrument.encode(getMsgSecureTypeForFlag(secured)));
            }
            if (noDates != null && MsgUtil.isTagInList(TagNum.NoDates, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.NoDates, noDates);
                if (trdCapDtGroups != null && trdCapDtGroups.length == noDates.intValue()) {
                    for (int i = 0; i < noDates.intValue(); i++) {
                        if (trdCapDtGroups[i] != null) {
                            bao.write(trdCapDtGroups[i].encode(getMsgSecureTypeForFlag(secured)));
                        }
                    }
                } else {
                    String error = "TrdCapDtGroups field has been set but there is no data or the number of groups does not match.";
                    LOGGER.severe(error);
                    throw new BadFormatMsgException(SessionRejectReason.IncorrectCountForGroups, getHeader().getMsgType(),
                            TagNum.NoDates.getValue(), error);
                }
            }
            if (side != null && MsgUtil.isTagInList(TagNum.Side, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.Side, side.getValue());
            }
            if (MsgUtil.isTagInList(TagNum.TradeInputSource, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.TradeInputSource, tradeInputSource);
            }
            if (MsgUtil.isTagInList(TagNum.TradeInputDevice, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.TradeInputDevice, tradeInputDevice);
            }
            if (MsgUtil.isTagInList(TagNum.Text, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.Text, text);
            }
            if (encodedTextLen != null && encodedTextLen.intValue() > 0 && MsgUtil.isTagInList(TagNum.EncodedTextLen, SECURED_TAGS, secured)) {
                if (encodedText != null && encodedText.length > 0) {
                    encodedTextLen = new Integer(encodedText.length);
                    TagEncoder.encode(bao, TagNum.EncodedTextLen, encodedTextLen);
                    TagEncoder.encode(bao, TagNum.EncodedText, encodedText);
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
        if (INSTRUMENT_COMP_TAGS.contains(tag.tagNum)) {
            if (instrument == null) {
                instrument = new Instrument43(context);
            }
            instrument.decode(tag, message);
        }
        if (TRD_CAP_DATE_GROUP_TAGS.contains(tag.tagNum)) {
            if (noDates != null && noDates.intValue() > 0) {
                message.reset();
                trdCapDtGroups = new TrdCapDtGroup[noDates.intValue()];
                for (int i = 0; i < noDates.intValue(); i++) {
                    TrdCapDtGroup group = new TrdCapDtGroup43(context);
                    group.decode(message);
                    trdCapDtGroups[i] = group;
                }
            }
        }
    }

    @Override
    protected String getUnsupportedTagMessage() {
        return "This tag is not supported in [TradeCaptureReportRequestMsg] message version [4.3].";
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
