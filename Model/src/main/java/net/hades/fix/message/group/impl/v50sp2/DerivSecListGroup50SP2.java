/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * DerivSecListGroup50SP2.java
 *
 * $Id: DerivSecListGroup50SP2.java,v 1.2 2011-09-28 08:10:20 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v50sp2;

import net.hades.fix.message.group.InstrmtLegDerivSecListGroup;
import net.hades.fix.message.struct.Tag;
import net.hades.fix.message.xml.codec.jaxb.adapter.FixUTCDateTimeAdapter;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.comp.Instrument;
import net.hades.fix.message.comp.InstrumentExtension;
import net.hades.fix.message.comp.InstrumentLeg;
import net.hades.fix.message.comp.SecondaryPriceLimits;
import net.hades.fix.message.comp.impl.v50sp2.Instrument50SP2;
import net.hades.fix.message.comp.impl.v50sp2.InstrumentExtension50SP2;
import net.hades.fix.message.comp.impl.v50sp2.SecondaryPriceLimits50SP2;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.group.DerivSecListGroup;
import net.hades.fix.message.type.Currency;

/**
 * FIX 5.0SP2 implementation of DerivSecListGroup group.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 * @created 29/04/2011, 11:39:24 AM
 */
@XmlRootElement(name="RelSym")
@XmlType(propOrder = {"instrument", "secondaryPriceLimits", "instrumentExtension", "instrumentLegs"})
@XmlAccessorType(XmlAccessType.NONE)
public class DerivSecListGroup50SP2 extends DerivSecListGroup {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> START_COMP_TAGS;

    protected static final Set<Integer> ALL_TAGS;

    protected static final Set<Integer> INSTRUMENT_COMP_TAGS = new Instrument50SP2().getFragmentAllTags();
    protected static final Set<Integer> SEC_PRICE_LIMITS_COMP_TAGS = new SecondaryPriceLimits50SP2().getFragmentAllTags();
    protected static final Set<Integer> INSTRUMENT_EXTENSION_COMP_TAGS = new InstrumentExtension50SP2().getFragmentAllTags();
    protected static final Set<Integer> INSTRMNT_LEG_SEC_LIST_GROUP_TAGS = new InstrmtLegDerivSecListGroup50SP2().getFragmentAllTags();
 
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">

    static {
        ALL_TAGS = new HashSet<Integer>(TAGS);
        ALL_TAGS.addAll(START_DATA_TAGS);
        ALL_TAGS.addAll(INSTRUMENT_COMP_TAGS);
        ALL_TAGS.addAll(INSTRUMENT_EXTENSION_COMP_TAGS);
        ALL_TAGS.addAll(SEC_PRICE_LIMITS_COMP_TAGS);
        ALL_TAGS.addAll(INSTRMNT_LEG_SEC_LIST_GROUP_TAGS);
        START_COMP_TAGS = new HashSet<Integer>(INSTRUMENT_COMP_TAGS);
        START_COMP_TAGS.addAll(SEC_PRICE_LIMITS_COMP_TAGS);
        START_COMP_TAGS.addAll(INSTRUMENT_EXTENSION_COMP_TAGS);
        START_COMP_TAGS.addAll(INSTRMNT_LEG_SEC_LIST_GROUP_TAGS);
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public DerivSecListGroup50SP2() {
    }

    public DerivSecListGroup50SP2(FragmentContext context) {
        super(context);
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
        FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, validateRequired);
        instrument = new Instrument50SP2(context);
    }

    public void setInstrument(Instrument instrument) {
        this.instrument = instrument;
    }

    @Override
    public void clearInstrument() {
        this.instrument = null;
    }

    @XmlElementRef
    @Override
    public SecondaryPriceLimits getSecondaryPriceLimits() {
        return secondaryPriceLimits;
    }
    
    @Override
    public void setSecondaryPriceLimits() {
        FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, validateRequired);
        this.secondaryPriceLimits = new SecondaryPriceLimits50SP2(context);
    }
    
    @Override
    public void clearSecondaryPriceLimits() {
        this.secondaryPriceLimits = null;
    }

    public void setSecondaryPriceLimits(SecondaryPriceLimits secondaryPriceLimits) {
        this.secondaryPriceLimits = secondaryPriceLimits;
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

    @XmlAttribute(name = "CorpActn")
    @Override
    public String getCorporateAction() {
        return corporateAction;
    }

    @Override
    public void setCorporateAction(String corporateAction) {
        this.corporateAction = corporateAction;
    }

    @XmlElementRef
    @Override
    public InstrumentExtension getInstrumentExtension() {
        return instrumentExtension;
    }

    @Override
    public void setInstrumentExtension() {
        FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, validateRequired);
        this.instrumentExtension = new InstrumentExtension50SP2(context);
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
            FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, validateRequired);
            for (int i = 0; i < instrmtLegDerivSecListGroups.length; i++) {
                instrmtLegDerivSecListGroups[i] = new InstrmtLegDerivSecListGroup50SP2(context);
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
        InstrmtLegDerivSecListGroup group = new InstrmtLegDerivSecListGroup50SP2(new FragmentContext(sessionCharset, messageEncoding, validateRequired));
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
            instrmtLegDerivSecListGroups[i] =  new InstrmtLegDerivSecListGroup50SP2(context);
            ((InstrmtLegDerivSecListGroup50SP2)instrmtLegDerivSecListGroups[i]).setInstrumentLeg(instrumentLegs[i]);
        }
        noLegs = new Integer(instrumentLegs.length);
    }

    @XmlAttribute(name = "TxnTm")
    @XmlJavaTypeAdapter(FixUTCDateTimeAdapter.class)
    @Override
    public Date getRelSymTransactTime() {
        return relSymTransactTime;
    }

    @Override
    public void setRelSymTransactTime(Date relSymTransactTime) {
        this.relSymTransactTime = relSymTransactTime;
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
    protected void setFragmentCompTagValue(Tag tag, ByteBuffer message)
    throws BadFormatMsgException, InvalidMsgException, TagNotPresentException {

        FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, validateRequired);
        if (INSTRUMENT_COMP_TAGS.contains(tag.tagNum)) {
            if (instrument == null) {
                instrument = new Instrument50SP2(context);
            }
            instrument.decode(tag, message);
        }
        if (SEC_PRICE_LIMITS_COMP_TAGS.contains(tag.tagNum)) {
            if (secondaryPriceLimits == null) {
                secondaryPriceLimits = new SecondaryPriceLimits50SP2(context);
            }
            secondaryPriceLimits.decode(tag, message);
        }
        if (INSTRUMENT_EXTENSION_COMP_TAGS.contains(tag.tagNum)) {
            if (instrumentExtension == null) {
                instrumentExtension = new InstrumentExtension50SP2(context);
            }
            instrumentExtension.decode(tag, message);
        }
        if (INSTRMNT_LEG_SEC_LIST_GROUP_TAGS.contains(tag.tagNum)) {
            if (noLegs != null && noLegs.intValue() > 0) {
                message.reset();
                instrmtLegDerivSecListGroups = new InstrmtLegDerivSecListGroup[noLegs.intValue()];
                for (int i = 0; i < noLegs.intValue(); i++) {
                    InstrmtLegDerivSecListGroup component = new InstrmtLegDerivSecListGroup50SP2(context);
                    component.decode(message);
                    instrmtLegDerivSecListGroups[i] = component;
                }
            }
        }
    }

    @Override
    protected String getUnsupportedTagMessage() {
        return "This tag is not supported in [DerivSecListGroup] group version [5.0SP2].";
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
