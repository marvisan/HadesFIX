/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * BidRespComponentGroup42.java
 *
 * $Id: BidRespComponentGroup42.java,v 1.1 2011-04-14 11:44:48 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v42;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.struct.Tag;
import net.hades.fix.message.util.MsgUtil;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;

import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.group.BidRespComponentGroup;
import net.hades.fix.message.type.CommType;
import net.hades.fix.message.type.TagNum;
import net.hades.fix.message.util.TagEncoder;

/**
 * FIX 4.3 implementation of BidReqComponentGroup group.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 05/04/2009, 11:39:24 AM
 */
public class BidRespComponentGroup42 extends BidRespComponentGroup {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> TAGS_V42 = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.Commission.getValue(),
        TagNum.CommType.getValue(),
        TagNum.ListID.getValue(),
        TagNum.Country.getValue(),
        TagNum.Side.getValue(),
        TagNum.Price.getValue(),
        TagNum.PriceType.getValue(),
        TagNum.FairValue.getValue(),
        TagNum.NetGrossInd.getValue(),
        TagNum.SettlType.getValue(),
        TagNum.SettlDate.getValue(),
        TagNum.TradingSessionID.getValue(),
        TagNum.TradingSessionSubID.getValue(),
        TagNum.Text.getValue()
    }));

    protected static final Set<Integer> START_COMP_TAGS = null;

    protected static final Set<Integer> ALL_TAGS;
 
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">

    static {
        ALL_TAGS = new HashSet<Integer>(TAGS_V42);
        ALL_TAGS.addAll(START_DATA_TAGS);
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">

    protected Set<Integer> STANDARD_SECURED_TAGS = ALL_TAGS;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public BidRespComponentGroup42() {
        SECURED_TAGS = STANDARD_SECURED_TAGS;
    }

    public BidRespComponentGroup42(FragmentContext context) {
        super(context);
        SECURED_TAGS = STANDARD_SECURED_TAGS;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Public Methods">

    @Override
    public Set<Integer> getFragmentAllTags() {
        return ALL_TAGS;
    }

    // ACCESSORS
    //////////////////////////////////////////

    @Override
    public Double getCommission() {
        return commission;
    }

    @Override
    public void setCommission(Double commission) {
        this.commission = commission;
    }

    @Override
    public CommType getCommType() {
        return commType;
    }

    @Override
    public void setCommType(CommType commType) {
        this.commType = commType;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

    @Override
    protected byte[] encodeFragmentSecured(boolean secured) throws TagNotPresentException, BadFormatMsgException {
        if (validateRequired) {
            validateRequiredTags();
        }
        byte[] result = new byte[0];
        ByteArrayOutputStream bao = new ByteArrayOutputStream();
        try {
            if (MsgUtil.isTagInList(TagNum.Commission, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.Commission, commission);
            }
            if (commType != null && MsgUtil.isTagInList(TagNum.CommType, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.CommType, commType.getValue());
            }
            if (MsgUtil.isTagInList(TagNum.ListID, SECURED_TAGS, secured)) {
                 TagEncoder.encode(bao, TagNum.ListID, listID);
            }
            if (country != null && MsgUtil.isTagInList(TagNum.Country, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.Country, country.getValue());
            }
            if (side != null && MsgUtil.isTagInList(TagNum.Side, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.Side, side.getValue());
            }
            if (MsgUtil.isTagInList(TagNum.Price, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.Price, price);
            }
            if (priceType != null && MsgUtil.isTagInList(TagNum.PriceType, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.PriceType, priceType.getValue());
            }
            if (MsgUtil.isTagInList(TagNum.FairValue, SECURED_TAGS, secured)) {
                 TagEncoder.encode(bao, TagNum.FairValue, fairValue);
            }
            if (netGrossInd != null && MsgUtil.isTagInList(TagNum.NetGrossInd, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.NetGrossInd, netGrossInd.getValue());
            }
            if (MsgUtil.isTagInList(TagNum.SettlType, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.SettlType, settlType);
            }
            if (MsgUtil.isTagInList(TagNum.SettlDate, SECURED_TAGS, secured)) {
                TagEncoder.encodeDate(bao, TagNum.SettlDate, settlDate);
            }
            if (MsgUtil.isTagInList(TagNum.TradingSessionID, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.TradingSessionID, tradingSessionID);
            }
            if (MsgUtil.isTagInList(TagNum.TradingSessionSubID, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.TradingSessionSubID, tradingSessionSubID);
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
    }

    @Override
    protected String getUnsupportedTagMessage() {
        return "This tag is not supported in [BidRespComponentGroup] group version [4.2].";
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
