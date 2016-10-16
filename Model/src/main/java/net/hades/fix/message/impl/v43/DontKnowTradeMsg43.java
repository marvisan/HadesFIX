/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * DontKnowTradeMsg43.java
 *
 * $Id: DontKnowTradeMsg43.java,v 1.3 2011-04-14 23:44:34 vrotaru Exp $
 */
package net.hades.fix.message.impl.v43;

import net.hades.fix.message.DontKnowTradeMsg;
import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.Header;
import net.hades.fix.message.comp.Instrument;
import net.hades.fix.message.comp.OrderQtyData;
import net.hades.fix.message.comp.impl.v43.Instrument43;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.struct.Tag;
import net.hades.fix.message.type.ApplVerID;
import net.hades.fix.message.type.BeginString;
import net.hades.fix.message.type.TagNum;
import net.hades.fix.message.util.MsgUtil;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;

import net.hades.fix.message.comp.impl.v43.OrderQtyData43;
import net.hades.fix.message.util.TagEncoder;

/**
 * FIX version 4.3 DontKnowTradeMsg implementation.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.3 $
 * @created 21/04/2009, 9:32:41 AM
 */
public class DontKnowTradeMsg43 extends DontKnowTradeMsg {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> INSTRUMENT_COMP_TAGS = new Instrument43().getFragmentAllTags();
    protected static final Set<Integer> ORDER_QTY_DATA_COMP_TAGS = new OrderQtyData43().getFragmentAllTags();

    protected static final Set<Integer> START_COMP_TAGS;

    protected static final Set<Integer> ALL_TAGS;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">

    static {
        ALL_TAGS = new HashSet<Integer>(TAGS);
        ALL_TAGS.addAll(START_DATA_TAGS);
        ALL_TAGS.addAll(INSTRUMENT_COMP_TAGS);
        ALL_TAGS.addAll(ORDER_QTY_DATA_COMP_TAGS);
        START_COMP_TAGS = new HashSet<Integer>(INSTRUMENT_COMP_TAGS);
        START_COMP_TAGS.addAll(ORDER_QTY_DATA_COMP_TAGS);
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">

    protected Set<Integer> STANDARD_SECURED_TAGS = TAGS;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public DontKnowTradeMsg43() {
        super();
    }

    public DontKnowTradeMsg43(Header header, ByteBuffer rawMsg)
    throws InvalidMsgException, TagNotPresentException, BadFormatMsgException {
        super(header, rawMsg);
        SECURED_TAGS = STANDARD_SECURED_TAGS;
    }

    public DontKnowTradeMsg43(BeginString beginString) throws InvalidMsgException {
        super(beginString);
        SECURED_TAGS = STANDARD_SECURED_TAGS;
    }

    public DontKnowTradeMsg43(BeginString beginString, ApplVerID applVerID) throws InvalidMsgException {
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
    public OrderQtyData getOrderQtyData() {
        return orderQtyData;
    }

    @Override
    public void setOrderQtyData() {
        FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired);
        this.orderQtyData = new OrderQtyData43(context);
    }

    @Override
    public void clearOrderQtyData() {
        this.orderQtyData = null;
    }

    public void setOrderQtyData(OrderQtyData orderQtyData) {
        this.orderQtyData = orderQtyData;
    }

    @Override
    public Integer getEncodedTextLen() {
        return encodedTextLen;
    }

    @Override
    public void setEncodedTextLen(Integer encodedTextLen) {
        this.encodedTextLen = encodedTextLen;
    }

    @Override
    public byte[] getEncodedText() {
        return encodedText;
    }

    @Override
    public void setEncodedText(byte[] encodedText) {
        this.encodedText = encodedText;
        if (encodedTextLen == null) {
            encodedTextLen = new Integer(encodedText.length);
        }
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
            if (MsgUtil.isTagInList(TagNum.SecondaryOrderID, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.SecondaryOrderID, secondaryOrderID);
            }
            if (MsgUtil.isTagInList(TagNum.ExecID, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.ExecID, execID);
            }
            if (DKReason != null && MsgUtil.isTagInList(TagNum.DKReason, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.DKReason, DKReason.getValue());
            }
            if (instrument != null) {
                bao.write(instrument.encode(getMsgSecureTypeForFlag(secured)));
            }
            if (side != null && MsgUtil.isTagInList(TagNum.Side, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.Side, side.getValue());
            }
            if (orderQtyData != null) {
                bao.write(orderQtyData.encode(getMsgSecureTypeForFlag(secured)));
            }
            if (MsgUtil.isTagInList(TagNum.LastQty, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.LastQty, lastQty);
            }
            if (MsgUtil.isTagInList(TagNum.LastPx, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.LastPx, lastPx);
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
        if (INSTRUMENT_COMP_TAGS.contains(tag.tagNum)) {
            if (instrument == null) {
                instrument = new Instrument43(context);
            }
            instrument.decode(tag, message);
        }
        if (ORDER_QTY_DATA_COMP_TAGS.contains(tag.tagNum)) {
            if (orderQtyData == null) {
                orderQtyData = new OrderQtyData43(context);
            }
            orderQtyData.decode(tag, message);
        }
    }

    @Override
    protected String getUnsupportedTagMessage() {
        return "This tag is not supported in [DontKnowTradeMsg] message version [4.3].";
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
