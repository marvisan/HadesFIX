/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * SecListGroup43.java
 *
 * $Id: SecListGroup43.java,v 1.2 2011-04-29 03:11:05 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v43;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.comp.Instrument;
import net.hades.fix.message.comp.impl.v43.Instrument43;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.group.SecListGroup;
import net.hades.fix.message.struct.Tag;
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

import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.group.InstrmtLegSecListGroup;
import net.hades.fix.message.util.TagEncoder;

/**
 * FIX 4.3 implementation of SecListGroup group.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 * @created 29/04/2011, 11:39:24 AM
 */
public class SecListGroup43 extends SecListGroup {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> TAGS_V43 = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.Currency.getValue(),
        TagNum.NoLegs.getValue(),
        TagNum.RoundLot.getValue(),
        TagNum.MinTradeVol.getValue(),
        TagNum.TradingSessionID.getValue(),
        TagNum.TradingSessionSubID.getValue(),
        TagNum.Text.getValue()
    }));

    protected static final Set<Integer> START_COMP_TAGS;

    protected static final Set<Integer> ALL_TAGS;

    protected static final Set<Integer> INSTRUMENT_COMP_TAGS = new Instrument43().getFragmentAllTags();
    protected static final Set<Integer> INSTRMNT_LEG_SEC_LIST_GROUP_TAGS = new InstrmtLegSecListGroup43().getFragmentAllTags();
 
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">

    static {
        ALL_TAGS = new HashSet<Integer>(TAGS_V43);
        ALL_TAGS.addAll(START_DATA_TAGS);
        ALL_TAGS.addAll(INSTRUMENT_COMP_TAGS);
        ALL_TAGS.addAll(INSTRMNT_LEG_SEC_LIST_GROUP_TAGS);
        START_COMP_TAGS = new HashSet<Integer>(INSTRUMENT_COMP_TAGS);
        START_COMP_TAGS.addAll(INSTRMNT_LEG_SEC_LIST_GROUP_TAGS);
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">

    protected Set<Integer> STANDARD_SECURED_TAGS = ALL_TAGS;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public SecListGroup43() {
        SECURED_TAGS = STANDARD_SECURED_TAGS;
    }

    public SecListGroup43(FragmentContext context) {
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
    public Instrument getInstrument() {
        return instrument;
    }

    @Override
    public void setInstrument() {
        FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired);
        instrument = new Instrument43(context);
    }

    public void setInstrument(Instrument instrument) {
        this.instrument = instrument;
    }

    @Override
    public void clearInstrument() {
        this.instrument = null;
    }

    @Override
    public Integer getNoLegs() {
        return noLegs;
    }

    @Override
    public void setNoLegs(Integer noLegs) {
        this.noLegs = noLegs;
        if (noLegs != null) {
            instrmtLegSecListGroups = new InstrmtLegSecListGroup[noLegs.intValue()];
            FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired);
            for (int i = 0; i < instrmtLegSecListGroups.length; i++) {
                instrmtLegSecListGroups[i] = new InstrmtLegSecListGroup43(context);
            }
        }
    }

    @Override
    public InstrmtLegSecListGroup[] getInstrmtLegSecListGroups() {
        return instrmtLegSecListGroups;
    }

    public void setInstrmtLegSecListGroups(InstrmtLegSecListGroup[] instrmtLegSecListGroups) {
        this.instrmtLegSecListGroups = instrmtLegSecListGroups;
        if (instrmtLegSecListGroups != null) {
            noLegs = instrmtLegSecListGroups.length;
        }
    }

    @Override
     public InstrmtLegSecListGroup addInstrmtLegSecListGroup() {
        InstrmtLegSecListGroup group = new InstrmtLegSecListGroup43(new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired));
        List<InstrmtLegSecListGroup> groups = new ArrayList<InstrmtLegSecListGroup>();
        if (instrmtLegSecListGroups != null && instrmtLegSecListGroups.length > 0) {
            groups = new ArrayList<InstrmtLegSecListGroup>(Arrays.asList(instrmtLegSecListGroups));
        }
        groups.add(group);
        instrmtLegSecListGroups = groups.toArray(new InstrmtLegSecListGroup[groups.size()]);
        noLegs = new Integer(instrmtLegSecListGroups.length);

        return group;
    }

    @Override
    public InstrmtLegSecListGroup deleteInstrmtLegSecListGroup(int index) {
        InstrmtLegSecListGroup result = null;
        if (instrmtLegSecListGroups != null && instrmtLegSecListGroups.length > 0 && instrmtLegSecListGroups.length > index) {
            List<InstrmtLegSecListGroup> groups = new ArrayList<InstrmtLegSecListGroup>(Arrays.asList(instrmtLegSecListGroups));
            result = groups.remove(index);
            instrmtLegSecListGroups = groups.toArray(new InstrmtLegSecListGroup[groups.size()]);
            if (instrmtLegSecListGroups.length > 0) {
                noLegs = new Integer(instrmtLegSecListGroups.length);
            } else {
                instrmtLegSecListGroups = null;
                noLegs = null;
            }
        }

        return result;
    }

    @Override
    public int clearInstrmtLegSecListGroups() {
        int result = 0;
        if (instrmtLegSecListGroups != null && instrmtLegSecListGroups.length > 0) {
            result = instrmtLegSecListGroups.length;
            instrmtLegSecListGroups = null;
            noLegs = null;
        }

        return result;
    }

    @Override
    public Double getRoundLot() {
        return roundLot;
    }

    @Override
    public void setRoundLot(Double roundLot) {
        this.roundLot = roundLot;
    }

    @Override
    public Double getMinTradeVol() {
        return minTradeVol;
    }

    @Override
    public void setMinTradeVol(Double minTradeVol) {
        this.minTradeVol = minTradeVol;
    }

    @Override
    public String getTradingSessionID() {
        return tradingSessionID;
    }

    @Override
    public void setTradingSessionID(String tradingSessionID) {
        this.tradingSessionID = tradingSessionID;
    }

    @Override
    public String getTradingSessionSubID() {
        return tradingSessionSubID;
    }

    @Override
    public void setTradingSessionSubID(String tradingSessionSubID) {
        this.tradingSessionSubID = tradingSessionSubID;
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
            if (instrument != null) {
                bao.write(instrument.encode(getMsgSecureTypeForFlag(secured)));
            }
            if (currency != null && MsgUtil.isTagInList(TagNum.Currency, SECURED_TAGS, secured)) {
                 TagEncoder.encode(bao, TagNum.Currency, currency.getValue());
            }
            if (noLegs != null && MsgUtil.isTagInList(TagNum.NoLegs, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.NoLegs, noLegs);
                if (instrmtLegSecListGroups != null && instrmtLegSecListGroups.length == noLegs.intValue()) {
                    for (int i = 0; i < noLegs.intValue(); i++) {
                        if (instrmtLegSecListGroups[i] != null) {
                            bao.write(instrmtLegSecListGroups[i].encode(getMsgSecureTypeForFlag(secured)));
                        }
                    }
                } else {
                    String error = "InstrmtLegSecListGroup field has been set but there is no data or the number of groups does not match.";
                    LOGGER.severe(error);
                    throw new BadFormatMsgException(SessionRejectReason.IncorrectCountForGroups, TagNum.NoLegs.getValue(), error);
                }
            }
            if (MsgUtil.isTagInList(TagNum.RoundLot, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.RoundLot, roundLot);
            }
            if (MsgUtil.isTagInList(TagNum.MinTradeVol, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.MinTradeVol, minTradeVol);
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

        FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired);
        if (INSTRUMENT_COMP_TAGS.contains(tag.tagNum)) {
            if (instrument == null) {
                instrument = new Instrument43(context);
            }
            instrument.decode(tag, message);
        }
        if (INSTRMNT_LEG_SEC_LIST_GROUP_TAGS.contains(tag.tagNum)) {
            if (noLegs != null && noLegs.intValue() > 0) {
                message.reset();
                instrmtLegSecListGroups = new InstrmtLegSecListGroup[noLegs.intValue()];
                for (int i = 0; i < noLegs.intValue(); i++) {
                    InstrmtLegSecListGroup component = new InstrmtLegSecListGroup43(context);
                    component.decode(message);
                    instrmtLegSecListGroups[i] = component;
                }
            }
        }
    }

    @Override
    protected String getUnsupportedTagMessage() {
        return "This tag is not supported in [SecListGroup] group version [4.3].";
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
