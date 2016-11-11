/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * DerivSecListGroup44.java
 *
 * $Id: DerivSecListGroup44.java,v 1.2 2011-09-28 08:10:21 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v44;

import net.hades.fix.message.comp.Instrument;
import net.hades.fix.message.group.InstrmtLegDerivSecListGroup;
import net.hades.fix.message.struct.Tag;
import net.hades.fix.message.type.Currency;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.comp.InstrumentExtension;
import net.hades.fix.message.comp.InstrumentLeg;
import net.hades.fix.message.comp.impl.v44.Instrument44;
import net.hades.fix.message.comp.impl.v44.InstrumentExtension44;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.group.DerivSecListGroup;
import net.hades.fix.message.type.ExpirationCycle;
import net.hades.fix.message.type.SessionRejectReason;
import net.hades.fix.message.type.TagNum;
import net.hades.fix.message.util.MsgUtil;
import net.hades.fix.message.util.TagEncoder;

/**
 * FIX 4.4 implementation of DerivSecListGroup group.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 * @created 29/04/2011, 11:39:24 AM
 */
@XmlRootElement(name="RelSym")
@XmlType(propOrder = {"instrument", "instrumentExtension", "instrumentLegs"})
@XmlAccessorType(XmlAccessType.NONE)
public class DerivSecListGroup44 extends DerivSecListGroup {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> TAGS_V44 = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.Currency.getValue(),
        TagNum.CorporateAction.getValue(),
        TagNum.ExpirationCycle.getValue(),
        TagNum.NoLegs.getValue(),
        TagNum.RelSymTransactTime.getValue(),
        TagNum.TradingSessionID.getValue(),
        TagNum.TradingSessionSubID.getValue(),
        TagNum.Text.getValue()
    }));

    protected static final Set<Integer> START_COMP_TAGS;

    protected static final Set<Integer> ALL_TAGS;

    protected static final Set<Integer> INSTRUMENT_COMP_TAGS = new Instrument44().getFragmentAllTags();
    protected static final Set<Integer> INSTRUMENT_EXTENSION_COMP_TAGS = new InstrumentExtension44().getFragmentAllTags();
    protected static final Set<Integer> INSTRMNT_LEG_SEC_LIST_GROUP_TAGS = new InstrmtLegDerivSecListGroup44().getFragmentAllTags();
 
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">

    static {
        ALL_TAGS = new HashSet<Integer>(TAGS_V44);
        ALL_TAGS.addAll(START_DATA_TAGS);
        ALL_TAGS.addAll(INSTRUMENT_COMP_TAGS);
        ALL_TAGS.addAll(INSTRUMENT_EXTENSION_COMP_TAGS);
        ALL_TAGS.addAll(INSTRMNT_LEG_SEC_LIST_GROUP_TAGS);
        START_COMP_TAGS = new HashSet<Integer>(INSTRUMENT_COMP_TAGS);
        START_COMP_TAGS.addAll(INSTRUMENT_EXTENSION_COMP_TAGS);
        START_COMP_TAGS.addAll(INSTRMNT_LEG_SEC_LIST_GROUP_TAGS);
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">

    protected Set<Integer> STANDARD_SECURED_TAGS = ALL_TAGS;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public DerivSecListGroup44() {
        SECURED_TAGS = STANDARD_SECURED_TAGS;
    }

    public DerivSecListGroup44(FragmentContext context) {
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

    @XmlElementRef
    @Override
    public Instrument getInstrument() {
        return instrument;
    }

    @Override
    public void setInstrument() {
        FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired);
        instrument = new Instrument44(context);
    }

    public void setInstrument(Instrument instrument) {
        this.instrument = instrument;
    }

    @Override
    public void clearInstrument() {
        this.instrument = null;
    }

    @XmlAttribute(name = "Ccy")
    @Override
    public Currency getCurrency() {
        return currency;
    }

    @Override
    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    @XmlAttribute(name = "ExpirationCycle")
    @Override
    public ExpirationCycle getExpirationCycle() {
        return expirationCycle;
    }

    @Override
    public void setExpirationCycle(ExpirationCycle expirationCycle) {
        this.expirationCycle = expirationCycle;
    }

    @XmlElementRef
    @Override
    public InstrumentExtension getInstrumentExtension() {
        return instrumentExtension;
    }

    @Override
    public void setInstrumentExtension() {
        FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired);
        this.instrumentExtension = new InstrumentExtension44(context);
    }

    @Override
    public void clearInstrumentExtension() {
        this.instrumentExtension = null;
    }

    public void setInstrumentExtension(InstrumentExtension instrumentExtension) {
        this.instrumentExtension = instrumentExtension;
    }

    @Override
    public Integer getNoLegs() {
        return noLegs;
    }

    @Override
    public void setNoLegs(Integer noLegs) {
        this.noLegs = noLegs;
        if (noLegs != null) {
            instrmtLegDerivSecListGroups = new InstrmtLegDerivSecListGroup[noLegs.intValue()];
            FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired);
            for (int i = 0; i < instrmtLegDerivSecListGroups.length; i++) {
                instrmtLegDerivSecListGroups[i] = new InstrmtLegDerivSecListGroup44(context);
            }
        }
    }

    @Override
    public InstrmtLegDerivSecListGroup[] getInstrmtLegDerivSecListGroups() {
        return instrmtLegDerivSecListGroups;
    }

    public void setInstrmtLegDerivSecListGroups(InstrmtLegDerivSecListGroup[] instrmtLegDerivSecListGroups) {
        this.instrmtLegDerivSecListGroups = instrmtLegDerivSecListGroups;
        if (instrmtLegDerivSecListGroups != null) {
            noLegs = instrmtLegDerivSecListGroups.length;
        }
    }

    @Override
     public InstrmtLegDerivSecListGroup addInstrmtLegDerivSecListGroup() {
        InstrmtLegDerivSecListGroup group = new InstrmtLegDerivSecListGroup44(new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired));
        List<InstrmtLegDerivSecListGroup> groups = new ArrayList<InstrmtLegDerivSecListGroup>();
        if (instrmtLegDerivSecListGroups != null && instrmtLegDerivSecListGroups.length > 0) {
            groups = new ArrayList<InstrmtLegDerivSecListGroup>(Arrays.asList(instrmtLegDerivSecListGroups));
        }
        groups.add(group);
        instrmtLegDerivSecListGroups = groups.toArray(new InstrmtLegDerivSecListGroup[groups.size()]);
        noLegs = new Integer(instrmtLegDerivSecListGroups.length);

        return group;
    }

    @Override
    public InstrmtLegDerivSecListGroup deleteInstrmtLegDerivSecListGroup(int index) {
        InstrmtLegDerivSecListGroup result = null;
        if (instrmtLegDerivSecListGroups != null && instrmtLegDerivSecListGroups.length > 0 && instrmtLegDerivSecListGroups.length > index) {
            List<InstrmtLegDerivSecListGroup> groups = new ArrayList<InstrmtLegDerivSecListGroup>(Arrays.asList(instrmtLegDerivSecListGroups));
            result = groups.remove(index);
            instrmtLegDerivSecListGroups = groups.toArray(new InstrmtLegDerivSecListGroup[groups.size()]);
            if (instrmtLegDerivSecListGroups.length > 0) {
                noLegs = new Integer(instrmtLegDerivSecListGroups.length);
            } else {
                instrmtLegDerivSecListGroups = null;
                noLegs = null;
            }
        }

        return result;
    }

    @Override
    public int clearInstrmtLegDerivSecListGroups() {
        int result = 0;
        if (instrmtLegDerivSecListGroups != null && instrmtLegDerivSecListGroups.length > 0) {
            result = instrmtLegDerivSecListGroups.length;
            instrmtLegDerivSecListGroups = null;
            noLegs = null;
        }

        return result;
    }
    
    @XmlElementRef
    public InstrumentLeg[] getInstrumentLegs() {
        if (instrmtLegDerivSecListGroups == null || instrmtLegDerivSecListGroups.length == 0) {
            return null;
        }
        List<InstrumentLeg> result = new ArrayList<InstrumentLeg>();
        for (InstrmtLegDerivSecListGroup grp :  instrmtLegDerivSecListGroups) {
            result.add(grp.getInstrumentLeg());
        }
        
        return result.toArray(new InstrumentLeg[result.size()]);
    }
    
    public void setInstrumentLegs(InstrumentLeg[] instrumentLegs) {
        
        if (instrumentLegs == null || instrumentLegs.length == 0) {
            noLegs = null;
            return;
        }
        FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired);
        instrmtLegDerivSecListGroups = new InstrmtLegDerivSecListGroup[instrumentLegs.length];
        for (int i = 0; i < instrmtLegDerivSecListGroups.length; i++) {
            instrmtLegDerivSecListGroups[i] =  new InstrmtLegDerivSecListGroup44(context);
            ((InstrmtLegDerivSecListGroup44)instrmtLegDerivSecListGroups[i]).setInstrumentLeg(instrumentLegs[i]);
        }
        noLegs = new Integer(instrumentLegs.length);
    }

    @XmlAttribute(name = "SesID")
    @Override
    public String getTradingSessionID() {
        return tradingSessionID;
    }

    @Override
    public void setTradingSessionID(String tradingSessionID) {
        this.tradingSessionID = tradingSessionID;
    }

    @XmlAttribute(name = "SesSub")
    @Override
    public String getTradingSessionSubID() {
        return tradingSessionSubID;
    }

    @Override
    public void setTradingSessionSubID(String tradingSessionSubID) {
        this.tradingSessionSubID = tradingSessionSubID;
    }

    @XmlAttribute(name = "Txt")
    @Override
    public String getText() {
        return text;
    }

    @Override
    public void setText(String text) {
        this.text = text;
    }

    @XmlAttribute(name = "EncTxtLen")
    @Override
    public Integer getEncodedTextLen() {
        return encodedTextLen;
    }

    @Override
    public void setEncodedTextLen(Integer encodedTextLen) {
        this.encodedTextLen = encodedTextLen;
    }

    @XmlAttribute(name = "EncTxt")
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
            if (expirationCycle != null && MsgUtil.isTagInList(TagNum.ExpirationCycle, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.ExpirationCycle, expirationCycle.getValue());
            }
            if (instrumentExtension != null) {
                bao.write(instrumentExtension.encode(getMsgSecureTypeForFlag(secured)));
            }
            if (noLegs != null && MsgUtil.isTagInList(TagNum.NoLegs, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.NoLegs, noLegs);
                if (instrmtLegDerivSecListGroups != null && instrmtLegDerivSecListGroups.length == noLegs.intValue()) {
                    for (int i = 0; i < noLegs.intValue(); i++) {
                        if (instrmtLegDerivSecListGroups[i] != null) {
                            bao.write(instrmtLegDerivSecListGroups[i].encode(getMsgSecureTypeForFlag(secured)));
                        }
                    }
                } else {
                    String error = "InstrmtLegDerivSecListGroup field has been set but there is no data or the number of groups does not match.";
                    LOGGER.severe(error);
                    throw new BadFormatMsgException(SessionRejectReason.IncorrectCountForGroups, TagNum.NoLegs.getValue(), error);
                }
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
                instrument = new Instrument44(context);
            }
            instrument.decode(tag, message);
        }
        if (INSTRUMENT_EXTENSION_COMP_TAGS.contains(tag.tagNum)) {
            if (instrumentExtension == null) {
                instrumentExtension = new InstrumentExtension44(context);
            }
            instrumentExtension.decode(tag, message);
        }
        if (INSTRMNT_LEG_SEC_LIST_GROUP_TAGS.contains(tag.tagNum)) {
            if (noLegs != null && noLegs.intValue() > 0) {
                message.reset();
                instrmtLegDerivSecListGroups = new InstrmtLegDerivSecListGroup[noLegs.intValue()];
                for (int i = 0; i < noLegs.intValue(); i++) {
                    InstrmtLegDerivSecListGroup component = new InstrmtLegDerivSecListGroup44(context);
                    component.decode(message);
                    instrmtLegDerivSecListGroups[i] = component;
                }
            }
        }
    }

    @Override
    protected String getUnsupportedTagMessage() {
        return "This tag is not supported in [DerivSecListGroup] group version [4.4].";
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
