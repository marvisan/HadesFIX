/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * TradingSessionStatusMsg42.java
 *
 * $Id: TradingSessionStatusMsg42.java,v 1.1 2011-04-23 00:19:06 vrotaru Exp $
 */
package net.hades.fix.message.impl.v42;

import net.hades.fix.message.struct.Tag;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;

import net.hades.fix.message.Header;
import net.hades.fix.message.TradingSessionStatusMsg;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.type.ApplVerID;
import net.hades.fix.message.type.BeginString;
import net.hades.fix.message.type.TagNum;
import net.hades.fix.message.util.MsgUtil;
import net.hades.fix.message.util.TagEncoder;

/**
 * FIX version 4.2 TradingSessionStatusMsg implementation.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 21/04/2009, 9:32:41 AM
 */
public class TradingSessionStatusMsg42 extends TradingSessionStatusMsg {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> START_COMP_TAGS = null;

    protected static final Set<Integer> ALL_TAGS;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">

    static {
        ALL_TAGS = new HashSet<Integer>(TAGS);
        ALL_TAGS.addAll(START_DATA_TAGS);
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">

    protected Set<Integer> STANDARD_SECURED_TAGS = TAGS;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public TradingSessionStatusMsg42() {
        super();
    }

    public TradingSessionStatusMsg42(Header header, ByteBuffer rawMsg)
    throws InvalidMsgException, TagNotPresentException, BadFormatMsgException {
        super(header, rawMsg);
        SECURED_TAGS = STANDARD_SECURED_TAGS;
    }

    public TradingSessionStatusMsg42(BeginString beginString) throws InvalidMsgException {
        super(beginString);
        SECURED_TAGS = STANDARD_SECURED_TAGS;
    }

    public TradingSessionStatusMsg42(BeginString beginString, ApplVerID applVerID) throws InvalidMsgException {
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

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

    @Override
    protected byte[] encodeFragmentSecured(boolean secured) throws TagNotPresentException, BadFormatMsgException {
        byte[] result = new byte[0];
        ByteArrayOutputStream bao = new ByteArrayOutputStream();
        try {
            if (MsgUtil.isTagInList(TagNum.TradSesReqID, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.TradSesReqID, tradSesReqID);
            }
            if (MsgUtil.isTagInList(TagNum.TradingSessionID, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.TradingSessionID, tradingSessionID);
            }
            if (tradSesMethod != null && MsgUtil.isTagInList(TagNum.TradSesMethod, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.TradSesMethod, tradSesMethod.getValue());
            }
            if (tradSesMode != null && MsgUtil.isTagInList(TagNum.TradSesMode, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.TradSesMode, tradSesMode.getValue());
            }
            if (MsgUtil.isTagInList(TagNum.UnsolicitedIndicator, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.UnsolicitedIndicator, unsolicitedIndicator);
            }
            if (tradSesStatus != null && MsgUtil.isTagInList(TagNum.TradSesStatus, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.TradSesStatus, tradSesStatus.getValue());
            }
            if (MsgUtil.isTagInList(TagNum.TradSesStartTime, SECURED_TAGS, secured)) {
                TagEncoder.encodeUtcTimestamp(bao, TagNum.TradSesStartTime, tradSesStartTime);
            }
            if (MsgUtil.isTagInList(TagNum.TradSesOpenTime, SECURED_TAGS, secured)) {
                TagEncoder.encodeUtcTimestamp(bao, TagNum.TradSesOpenTime, tradSesOpenTime);
            }
            if (MsgUtil.isTagInList(TagNum.TradSesPreCloseTime, SECURED_TAGS, secured)) {
                TagEncoder.encodeUtcTimestamp(bao, TagNum.TradSesPreCloseTime, tradSesPreCloseTime);
            }
            if (MsgUtil.isTagInList(TagNum.TradSesCloseTime, SECURED_TAGS, secured)) {
                TagEncoder.encodeUtcTimestamp(bao, TagNum.TradSesCloseTime, tradSesCloseTime);
            }
            if (MsgUtil.isTagInList(TagNum.TradSesEndTime, SECURED_TAGS, secured)) {
                TagEncoder.encodeUtcTimestamp(bao, TagNum.TradSesEndTime, tradSesEndTime);
            }
            if (MsgUtil.isTagInList(TagNum.TotalVolumeTraded, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.TotalVolumeTraded, totalVolumeTraded);
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
            if (instrument != null) {
                bao.write(instrument.encode(getMsgSecureTypeForFlag(secured)));
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
        return "This tag is not supported in [TradingSessionStatusMsg] message version [4.2].";
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
