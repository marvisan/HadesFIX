/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * OrderMassCancelReportMsg43.java
 *
 * $Id: OrderMassCancelReportMsg43.java,v 1.2 2011-05-07 06:58:53 vrotaru Exp $
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

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.Header;
import net.hades.fix.message.OrderMassCancelReportMsg;
import net.hades.fix.message.comp.Instrument;
import net.hades.fix.message.comp.UnderlyingInstrument;
import net.hades.fix.message.comp.impl.v43.Instrument43;
import net.hades.fix.message.comp.impl.v43.UnderlyingInstrument43;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.group.AffectedOrdGroup;
import net.hades.fix.message.group.impl.v43.AffectedOrdGroup43;
import net.hades.fix.message.struct.Tag;
import net.hades.fix.message.type.ApplVerID;
import net.hades.fix.message.type.BeginString;
import net.hades.fix.message.type.SessionRejectReason;
import net.hades.fix.message.type.TagNum;
import net.hades.fix.message.util.MsgUtil;
import net.hades.fix.message.util.TagEncoder;

/**
 * FIX version 4.3 OrderMassCancelReportMsg implementation.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 * @created 01/05/2011, 9:32:41 AM
 */
public class OrderMassCancelReportMsg43 extends OrderMassCancelReportMsg {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> AFFECTED_ORD_GROUP_TAGS = new AffectedOrdGroup43().getFragmentAllTags();
    protected static final Set<Integer> INSTRUMENT_COMP_TAGS = new Instrument43().getFragmentAllTags();
    protected static final Set<Integer> UNDERLYING_INSTRUMENT_COMP_TAGS = new UnderlyingInstrument43().getFragmentAllTags();

    protected static final Set<Integer> START_COMP_TAGS;

    protected static final Set<Integer> ALL_TAGS;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">

    static {
        ALL_TAGS = new HashSet<Integer>(TAGS);
        ALL_TAGS.addAll(START_DATA_TAGS);
        ALL_TAGS.addAll(AFFECTED_ORD_GROUP_TAGS);
        ALL_TAGS.addAll(INSTRUMENT_COMP_TAGS);
        ALL_TAGS.addAll(UNDERLYING_INSTRUMENT_COMP_TAGS);
        START_COMP_TAGS = new HashSet<Integer>(AFFECTED_ORD_GROUP_TAGS);
        START_COMP_TAGS.addAll(INSTRUMENT_COMP_TAGS);
        START_COMP_TAGS.addAll(UNDERLYING_INSTRUMENT_COMP_TAGS);
        
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">

    protected Set<Integer> STANDARD_SECURED_TAGS = TAGS;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public OrderMassCancelReportMsg43() {
        super();
    }

    public OrderMassCancelReportMsg43(Header header, ByteBuffer rawMsg)
    throws InvalidMsgException, TagNotPresentException, BadFormatMsgException {
        super(header, rawMsg);
        SECURED_TAGS = STANDARD_SECURED_TAGS;
    }

    public OrderMassCancelReportMsg43(BeginString beginString) throws InvalidMsgException {
        super(beginString);
        SECURED_TAGS = STANDARD_SECURED_TAGS;
    }

    public OrderMassCancelReportMsg43(BeginString beginString, ApplVerID applVerID) throws InvalidMsgException {
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
    public Integer getNoAffectedOrders() {
        return noAffectedOrders;
    }

    @Override
    public void setNoAffectedOrders(Integer noAffectedOrders) {
        this.noAffectedOrders = noAffectedOrders;
        if (noAffectedOrders != null) {
            affectedOrdGroups = new AffectedOrdGroup[noAffectedOrders.intValue()];
            FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired);
            for (int i = 0; i < affectedOrdGroups.length; i++) {
                affectedOrdGroups[i] = new AffectedOrdGroup43(context);
            }
        }
    }

    @Override
    public AffectedOrdGroup[] getAffectedOrdGroups() {
        return affectedOrdGroups;
    }

    public void setAffectedOrdGroups(AffectedOrdGroup[] affectedOrdGroups) {
        this.affectedOrdGroups = affectedOrdGroups;
        if (affectedOrdGroups != null) {
            noAffectedOrders = new Integer(affectedOrdGroups.length);
        }
    }

    @Override
    public AffectedOrdGroup addAffectedOrdGroup() {
        AffectedOrdGroup group = new AffectedOrdGroup43(new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired));
        List<AffectedOrdGroup> groups = new ArrayList<AffectedOrdGroup>();
        if (affectedOrdGroups != null && affectedOrdGroups.length > 0) {
            groups = new ArrayList<AffectedOrdGroup>(Arrays.asList(affectedOrdGroups));
        }
        groups.add(group);
        affectedOrdGroups = groups.toArray(new AffectedOrdGroup[groups.size()]);
        noAffectedOrders = new Integer(affectedOrdGroups.length);

        return group;
    }

    @Override
    public AffectedOrdGroup deleteAffectedOrdGroup(int index) {
        AffectedOrdGroup result = null;
        if (affectedOrdGroups != null && affectedOrdGroups.length > 0 && affectedOrdGroups.length > index) {
            List<AffectedOrdGroup> groups = new ArrayList<AffectedOrdGroup>(Arrays.asList(affectedOrdGroups));
            result = groups.remove(index);
            affectedOrdGroups = groups.toArray(new AffectedOrdGroup[groups.size()]);
            if (affectedOrdGroups.length > 0) {
                noAffectedOrders = new Integer(affectedOrdGroups.length);
            } else {
                affectedOrdGroups = null;
                noAffectedOrders = null;
            }
        }

        return result;
    }

    @Override
    public int clearAffectedOrdGroups() {
        int result = 0;
        if (affectedOrdGroups != null && affectedOrdGroups.length > 0) {
            result = affectedOrdGroups.length;
            affectedOrdGroups = null;
            noAffectedOrders = null;
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

    @Override
    public void clearInstrument() {
        this.instrument = null;
    }

    public void setInstrument(Instrument instrument) {
        this.instrument = instrument;
    }

    @Override
    public UnderlyingInstrument getUnderlyingInstrument() {
        return underlyingInstrument;
    }

    @Override
    public void setUnderlyingInstrument() {
        FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired);
        this.underlyingInstrument = new UnderlyingInstrument43(context);
    }

    @Override
    public void clearUnderlyingInstrument() {
        this.underlyingInstrument = null;
    }

    public void setUnderlyingInstrument(UnderlyingInstrument underlyingInstrument) {
        this.underlyingInstrument = underlyingInstrument;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

    @Override
    protected void validateRequiredTags() throws TagNotPresentException {
        StringBuilder errorMsg = new StringBuilder("Tag value(s) for");
        boolean hasMissingTag = false;
        if (orderID == null || orderID.trim().isEmpty()) {
            errorMsg.append(" [OrderID]");
            hasMissingTag = true;
        }
        if (massCancelRequestType == null) {
            errorMsg.append(" [MassCancelRequestType]");
            hasMissingTag = true;
        }
        if (massCancelResponse == null) {
            errorMsg.append(" [MassCancelResponse]");
            hasMissingTag = true;
        }
        if (side == null) {
            errorMsg.append(" [Side]");
            hasMissingTag = true;
        }
        errorMsg.append(" is missing.");
        if (hasMissingTag) {
            throw new TagNotPresentException(errorMsg.toString());
        }
    }

    @Override
    protected byte[] encodeFragmentSecured(boolean secured) throws TagNotPresentException, BadFormatMsgException {
        byte[] result = new byte[0];
        ByteArrayOutputStream bao = new ByteArrayOutputStream();
        try {
            if (MsgUtil.isTagInList(TagNum.ClOrdID, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.ClOrdID, clOrdID);
            }
            if (MsgUtil.isTagInList(TagNum.SecondaryClOrdID, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.SecondaryClOrdID, secondaryClOrdID);
            }
            if (MsgUtil.isTagInList(TagNum.OrderID, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.OrderID, orderID);
            }
            if (MsgUtil.isTagInList(TagNum.SecondaryOrderID, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.SecondaryOrderID, secondaryOrderID);
            }
            if (massCancelRequestType != null && MsgUtil.isTagInList(TagNum.MassCancelRequestType, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.MassCancelRequestType, massCancelRequestType.getValue());
            }
            if (massCancelResponse != null && MsgUtil.isTagInList(TagNum.MassCancelResponse, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.MassCancelResponse, massCancelResponse.getValue());
            }
            if (massCancelRejectReason != null && MsgUtil.isTagInList(TagNum.MassCancelRejectReason, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.MassCancelRejectReason, massCancelRejectReason.getValue());
            }
            if (MsgUtil.isTagInList(TagNum.TotalAffectedOrders, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.TotalAffectedOrders, totalAffectedOrders);
            }
            if (noAffectedOrders != null && MsgUtil.isTagInList(TagNum.NoAffectedOrders, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.NoAffectedOrders, noAffectedOrders);
                if (affectedOrdGroups != null && affectedOrdGroups.length == noAffectedOrders.intValue()) {
                    for (int i = 0; i < noAffectedOrders.intValue(); i++) {
                        if (affectedOrdGroups[i] != null) {
                            bao.write(affectedOrdGroups[i].encode(getMsgSecureTypeForFlag(secured)));
                        }
                    }
                } else {
                    String error = "AffectedOrdGroups field has been set but there is no data or the number of groups does not match.";
                    LOGGER.severe(error);
                    throw new BadFormatMsgException(SessionRejectReason.IncorrectCountForGroups, getHeader().getMsgType(),
                            TagNum.NoAffectedOrders.getValue(), error);
                }
            }
            if (MsgUtil.isTagInList(TagNum.TradingSessionID, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.TradingSessionID, tradingSessionID);
            }
            if (MsgUtil.isTagInList(TagNum.TradingSessionSubID, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.TradingSessionSubID, tradingSessionSubID);
            }
            if (instrument != null) {
                bao.write(instrument.encode(getMsgSecureTypeForFlag(secured)));
            }
            if (underlyingInstrument != null) {
                bao.write(underlyingInstrument.encode(getMsgSecureTypeForFlag(secured)));
            }
            if (side != null && MsgUtil.isTagInList(TagNum.Side, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.Side, side.getValue());
            }
            if (MsgUtil.isTagInList(TagNum.TransactTime, SECURED_TAGS, secured)) {
                TagEncoder.encodeUtcTimestamp(bao, TagNum.TransactTime, transactTime);
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
        if (AFFECTED_ORD_GROUP_TAGS.contains(tag.tagNum)) {
            if (noAffectedOrders != null && noAffectedOrders.intValue() > 0) {
                message.reset();
                affectedOrdGroups = new AffectedOrdGroup[noAffectedOrders.intValue()];
                for (int i = 0; i < noAffectedOrders.intValue(); i++) {
                    AffectedOrdGroup component = new AffectedOrdGroup43(context);
                    component.decode(message);
                    affectedOrdGroups[i] = component;
                }
            }
        }
        if (INSTRUMENT_COMP_TAGS.contains(tag.tagNum)) {
            if (instrument == null) {
                instrument = new Instrument43(context);
            }
            instrument.decode(tag, message);
        }
        if (UNDERLYING_INSTRUMENT_COMP_TAGS.contains(tag.tagNum)) {
            if (underlyingInstrument == null) {
                underlyingInstrument = new UnderlyingInstrument43(context);
            }
            underlyingInstrument.decode(tag, message);
        }
    }

    @Override
    protected String getUnsupportedTagMessage() {
        return "This tag is not supported in [OrderMassCancelReportMsg] message version [4.3].";
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
