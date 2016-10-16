/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * BidRequestMsg43.java
 *
 * $Id: BidRequestMsg43.java,v 1.3 2011-04-14 23:44:33 vrotaru Exp $
 */
package net.hades.fix.message.impl.v43;

import net.hades.fix.message.BidRequestMsg;
import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.Header;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.group.BidReqComponentGroup;
import net.hades.fix.message.struct.Tag;
import net.hades.fix.message.type.ApplVerID;
import net.hades.fix.message.type.SessionRejectReason;
import net.hades.fix.message.type.TagNum;
import net.hades.fix.message.util.MsgUtil;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;

import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.group.BidReqDescriptorGroup;
import net.hades.fix.message.group.impl.v43.BidReqComponentGroup43;
import net.hades.fix.message.group.impl.v43.BidReqDescriptorGroup43;
import net.hades.fix.message.type.BeginString;
import net.hades.fix.message.util.TagEncoder;

/**
 * FIX version 4.3 BidRequestMsg implementation.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.3 $
 * @created 21/04/2009, 9:32:41 AM
 */
public class BidRequestMsg43 extends BidRequestMsg {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> BID_DESCR_GROUP_TAGS = new BidReqDescriptorGroup43().getFragmentAllTags();
    protected static final Set<Integer> BID_COMP_GROUP_TAGS = new BidReqComponentGroup43().getFragmentAllTags();

    protected static final Set<Integer> START_COMP_TAGS;

    protected static final Set<Integer> ALL_TAGS;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">

    static {
        ALL_TAGS = new HashSet<Integer>(TAGS);
        ALL_TAGS.addAll(START_DATA_TAGS);
        ALL_TAGS.addAll(BID_DESCR_GROUP_TAGS);
        ALL_TAGS.addAll(BID_COMP_GROUP_TAGS);
        START_COMP_TAGS = new HashSet<Integer>(BID_DESCR_GROUP_TAGS);
        START_COMP_TAGS.addAll(BID_COMP_GROUP_TAGS);
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">

    protected Set<Integer> STANDARD_SECURED_TAGS = TAGS;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public BidRequestMsg43() {
        super();
    }

    public BidRequestMsg43(Header header, ByteBuffer rawMsg)
    throws InvalidMsgException, TagNotPresentException, BadFormatMsgException {
        super(header, rawMsg);
        SECURED_TAGS = STANDARD_SECURED_TAGS;
    }

    public BidRequestMsg43(BeginString beginString) throws InvalidMsgException {
        super(beginString);
        SECURED_TAGS = STANDARD_SECURED_TAGS;
    }

    public BidRequestMsg43(BeginString beginString, ApplVerID applVerID) throws InvalidMsgException {
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
    public void setNoBidDescriptors(Integer noBidDescriptors) {
        this.noBidDescriptors = noBidDescriptors;
        if (noBidDescriptors != null) {
            bidDescriptorGroups = new BidReqDescriptorGroup[noBidDescriptors.intValue()];
            FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired);
            for (int i = 0; i < bidDescriptorGroups.length; i++) {
                bidDescriptorGroups[i] = new BidReqDescriptorGroup43(context);
            }
        }
    }

    @Override
    public BidReqDescriptorGroup[] getBidDescriptorGroups() {
        return bidDescriptorGroups;
    }

    public void setBidDescriptorGroups(BidReqDescriptorGroup[] bidDescriptorGroups) {
        this.bidDescriptorGroups = bidDescriptorGroups;
    }

    @Override
    public BidReqDescriptorGroup addBidDescriptorGroup() {
        BidReqDescriptorGroup group = new BidReqDescriptorGroup43(new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired));
        List<BidReqDescriptorGroup> groups = new ArrayList<BidReqDescriptorGroup>();
        if (bidDescriptorGroups != null && bidDescriptorGroups.length > 0) {
            groups = new ArrayList<BidReqDescriptorGroup>(Arrays.asList(bidDescriptorGroups));
        }
        groups.add(group);
        bidDescriptorGroups = groups.toArray(new BidReqDescriptorGroup[groups.size()]);
        noBidDescriptors = new Integer(bidDescriptorGroups.length);

        return group;
    }

    @Override
    public void setNoBidComponents(Integer noBidComponents) {
        this.noBidComponents = noBidComponents;
        if (noBidComponents != null) {
            bidComponentGroups = new BidReqComponentGroup[noBidComponents.intValue()];
            FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired);
            for (int i = 0; i < bidComponentGroups.length; i++) {
                bidComponentGroups[i] = new BidReqComponentGroup43(context);
            }
        }
    }

    @Override
    public BidReqComponentGroup[] getBidComponentGroups() {
        return bidComponentGroups;
    }

    public void setBidComponentGroups(BidReqComponentGroup[] bidComponentGroups) {
        this.bidComponentGroups = bidComponentGroups;
    }

    @Override
    public BidReqComponentGroup addBidComponentGroup() {
        BidReqComponentGroup group = new BidReqComponentGroup43(new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired));
        List<BidReqComponentGroup> groups = new ArrayList<BidReqComponentGroup>();
        if (bidComponentGroups != null && bidComponentGroups.length > 0) {
            groups = new ArrayList<BidReqComponentGroup>(Arrays.asList(bidComponentGroups));
        }
        groups.add(group);
        bidComponentGroups = groups.toArray(new BidReqComponentGroup[groups.size()]);
        noBidComponents = new Integer(bidComponentGroups.length);

        return group;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

    @Override
    protected byte[] encodeFragmentSecured(boolean secured) throws TagNotPresentException, BadFormatMsgException {
        byte[] result = new byte[0];
        ByteArrayOutputStream bao = new ByteArrayOutputStream();
        try {
            if (MsgUtil.isTagInList(TagNum.BidID, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.BidID, bidID);
            }
            if (MsgUtil.isTagInList(TagNum.ClientBidID, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.ClientBidID, clientBidID);
            }
            if (bidRequestTransType != null && MsgUtil.isTagInList(TagNum.BidRequestTransType, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.BidRequestTransType, bidRequestTransType.getValue());
            }
            if (MsgUtil.isTagInList(TagNum.ListName, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.ListName, listName);
            }
            if (MsgUtil.isTagInList(TagNum.TotNoRelatedSym, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.TotNoRelatedSym, totNoRelatedSym);
            }
            if (bidType != null && MsgUtil.isTagInList(TagNum.BidType, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.BidType, bidType.getValue());
            }
            if (MsgUtil.isTagInList(TagNum.NumTickets, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.NumTickets, numTickets);
            }
            if (currency != null && MsgUtil.isTagInList(TagNum.Currency, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.Currency, currency.getValue());
            }
            if (MsgUtil.isTagInList(TagNum.SideValue1, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.SideValue1, sideValue1);
            }
            if (MsgUtil.isTagInList(TagNum.SideValue2, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.SideValue2, sideValue2);
            }
            if (noBidDescriptors != null && MsgUtil.isTagInList(TagNum.NoBidDescriptors, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.NoBidDescriptors, noBidDescriptors);
                if (bidDescriptorGroups != null && bidDescriptorGroups.length == noBidDescriptors.intValue()) {
                    for (int i = 0; i < noBidDescriptors.intValue(); i++) {
                        if (bidDescriptorGroups[i] != null) {
                            bao.write(bidDescriptorGroups[i].encode(getMsgSecureTypeForFlag(secured)));
                        }
                    }
                } else {
                    String error = "BidDescriptorGroups field has been set but there is no data or the number of groups does not match.";
                    LOGGER.severe(error);
                    throw new BadFormatMsgException(SessionRejectReason.IncorrectCountForGroups, getHeader().getMsgType(),
                            TagNum.NoBidDescriptors.getValue(), error);
                }
            }
            if (noBidComponents != null && MsgUtil.isTagInList(TagNum.NoBidComponents, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.NoBidComponents, noBidComponents);
                if (bidComponentGroups != null && bidComponentGroups.length == noBidComponents.intValue()) {
                    for (int i = 0; i < noBidComponents.intValue(); i++) {
                        if (bidComponentGroups[i] != null) {
                            bao.write(bidComponentGroups[i].encode(getMsgSecureTypeForFlag(secured)));
                        }
                    }
                } else {
                    String error = "BidComponentGroups field has been set but there is no data or the number of groups does not match.";
                    LOGGER.severe(error);
                    throw new BadFormatMsgException(SessionRejectReason.IncorrectCountForGroups, getHeader().getMsgType(),
                            TagNum.NoBidComponents.getValue(), error);
                }
            }
            if (liquidityIndType != null && MsgUtil.isTagInList(TagNum.LiquidityIndType, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.LiquidityIndType, liquidityIndType.getValue());
            }
            if (MsgUtil.isTagInList(TagNum.WtAverageLiquidity, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.WtAverageLiquidity, wtAverageLiquidity);
            }
            if (MsgUtil.isTagInList(TagNum.ExchangeForPhysical, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.ExchangeForPhysical, exchangeForPhysical);
            }
            if (MsgUtil.isTagInList(TagNum.OutMainCntryUIndex, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.OutMainCntryUIndex, outMainCntryUIndex);
            }
            if (MsgUtil.isTagInList(TagNum.CrossPercent, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.CrossPercent, crossPercent);
            }
            if (progRptReqs != null && MsgUtil.isTagInList(TagNum.ProgRptReqs, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.ProgRptReqs, progRptReqs.getValue());
            }
            if (MsgUtil.isTagInList(TagNum.ProgPeriodInterval, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.ProgPeriodInterval, progPeriodInterval);
            }
            if (incTaxInd != null && MsgUtil.isTagInList(TagNum.IncTaxInd, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.IncTaxInd, incTaxInd.getValue());
            }
            if (MsgUtil.isTagInList(TagNum.ForexReq, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.ForexReq, forexReq);
            }
            if (MsgUtil.isTagInList(TagNum.NumBidders, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.NumBidders, numBidders);
            }
            if (MsgUtil.isTagInList(TagNum.TradeDate, SECURED_TAGS, secured)) {
                TagEncoder.encodeTimestamp(bao, TagNum.TradeDate, tradeDate);
            }
            if (bidTradeType != null && MsgUtil.isTagInList(TagNum.BidTradeType, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.BidTradeType, bidTradeType.getValue());
            }
            if (basisPxType != null && MsgUtil.isTagInList(TagNum.BasisPxType, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.BasisPxType, basisPxType.getValue());
            }
            if (MsgUtil.isTagInList(TagNum.StrikeTime, SECURED_TAGS, secured)) {
                TagEncoder.encodeUtcTimestamp(bao, TagNum.StrikeTime, strikeTime);
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
        if (BID_DESCR_GROUP_TAGS.contains(tag.tagNum)) {
            if (noBidDescriptors != null && noBidDescriptors.intValue() > 0) {
                message.reset();
                bidDescriptorGroups = new BidReqDescriptorGroup[noBidDescriptors.intValue()];
                for (int i = 0; i < noBidDescriptors.intValue(); i++) {
                    BidReqDescriptorGroup component = new BidReqDescriptorGroup43(context);
                    component.decode(message);
                    bidDescriptorGroups[i] = component;
                }
            }
        }
        if (BID_COMP_GROUP_TAGS.contains(tag.tagNum)) {
            if (noBidComponents != null && noBidComponents.intValue() > 0) {
                message.reset();
                bidComponentGroups = new BidReqComponentGroup[noBidComponents.intValue()];
                for (int i = 0; i < noBidComponents.intValue(); i++) {
                    BidReqComponentGroup component = new BidReqComponentGroup43(context);
                    component.decode(message);
                    bidComponentGroups[i] = component;
                }
            }
        }
    }

    @Override
    protected String getUnsupportedTagMessage() {
        return "This tag is not supported in [BidRequestMsg] message version [4.3].";
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
