/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * SecurityStatusMsg50.java
 *
 * $Id: SecurityStatusMsg50.java,v 1.1 2011-04-21 09:45:46 vrotaru Exp $
 */
package net.hades.fix.message.impl.v50;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.comp.Instrument;
import net.hades.fix.message.struct.Tag;
import net.hades.fix.message.type.ApplVerID;
import net.hades.fix.message.type.Currency;
import net.hades.fix.message.type.SecurityTradingStatus;
import net.hades.fix.message.xml.codec.jaxb.adapter.FixUTCDateTimeAdapter;

import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.ArrayList;
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

import net.hades.fix.message.FIXFragment;
import net.hades.fix.message.Header;
import net.hades.fix.message.SecurityStatusMsg;
import net.hades.fix.message.comp.InstrumentExtension;
import net.hades.fix.message.comp.InstrumentLeg;
import net.hades.fix.message.comp.UnderlyingInstrument;
import net.hades.fix.message.comp.impl.v50.Instrument50;
import net.hades.fix.message.comp.impl.v50.InstrumentExtension50;
import net.hades.fix.message.comp.impl.v50.InstrumentLeg50;
import net.hades.fix.message.comp.impl.v50.UnderlyingInstrument50;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.type.Adjustment;
import net.hades.fix.message.type.BeginString;
import net.hades.fix.message.type.CorporateAction;
import net.hades.fix.message.type.HaltReason;
import net.hades.fix.message.xml.codec.jaxb.adapter.FixBooleanAdapter;

/**
 * FIX version 5.0 SecurityStatusMsg implementation.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 21/04/2009, 9:32:41 AM
 */
@XmlRootElement(name="SecStat")
@XmlType(propOrder = {"header", "instrument", "instrumentExtension", "underlyingInstruments", "instrumentLegs"})
@XmlAccessorType(XmlAccessType.NONE)
public class SecurityStatusMsg50 extends SecurityStatusMsg {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> INSTRUMENT_COMP_TAGS = new Instrument50().getFragmentAllTags();
    protected static final Set<Integer> INSTRUMENT_EXTENSION_COMP_TAGS = new InstrumentExtension50().getFragmentAllTags();
    protected static final Set<Integer> UNDERLYING_INSTRUMENT_COMP_TAGS = new UnderlyingInstrument50().getFragmentAllTags();
    protected static final Set<Integer> INSTRUMENT_LEG_COMP_TAGS = new InstrumentLeg50().getFragmentAllTags();

    protected static final Set<Integer> START_COMP_TAGS;

    protected static final Set<Integer> ALL_TAGS;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">

    static {
        ALL_TAGS = new HashSet<Integer>(TAGS);
        ALL_TAGS.addAll(START_DATA_TAGS);
        ALL_TAGS.addAll(INSTRUMENT_COMP_TAGS);
        ALL_TAGS.addAll(INSTRUMENT_EXTENSION_COMP_TAGS);
        ALL_TAGS.addAll(UNDERLYING_INSTRUMENT_COMP_TAGS);
        ALL_TAGS.addAll(INSTRUMENT_LEG_COMP_TAGS);
        START_COMP_TAGS = new HashSet<Integer>(INSTRUMENT_COMP_TAGS);
        START_COMP_TAGS.addAll(INSTRUMENT_EXTENSION_COMP_TAGS);
        START_COMP_TAGS.addAll(UNDERLYING_INSTRUMENT_COMP_TAGS);
        START_COMP_TAGS.addAll(INSTRUMENT_LEG_COMP_TAGS);
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public SecurityStatusMsg50() {
        super();
    }

    public SecurityStatusMsg50(Header header, ByteBuffer rawMsg)
    throws InvalidMsgException, TagNotPresentException, BadFormatMsgException {
        super(header, rawMsg);
    }

    public SecurityStatusMsg50(BeginString beginString) throws InvalidMsgException {
        super(beginString);
    }

    public SecurityStatusMsg50(BeginString beginString, ApplVerID applVerID) throws InvalidMsgException {
        super(beginString, applVerID);
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

    @Override
    public void copyFixmlData(FIXFragment fragment) {
        SecurityStatusMsg50 fixml = (SecurityStatusMsg50) fragment;
        if (fixml.getSecurityStatusReqID() != null) {
            securityStatusReqID = fixml.getSecurityStatusReqID();
        }
        if (fixml.getInstrument() != null) {
            setInstrument(fixml.getInstrument());
        }
        if (fixml.getInstrumentExtension() != null) {
            setInstrumentExtension(fixml.getInstrumentExtension());
        }
        if (fixml.getUnderlyingInstruments() != null && fixml.getUnderlyingInstruments().length > 0) {
            setUnderlyingInstruments(fixml.getUnderlyingInstruments());
        }
        if (fixml.getInstrumentLegs() != null && fixml.getInstrumentLegs().length > 0) {
            setInstrumentLegs(fixml.getInstrumentLegs());
        }
        if (fixml.getCurrency() != null) {
            currency = fixml.getCurrency();
        }
        if (fixml.getMarketID() != null) {
            marketID = fixml.getMarketID();
        }
        if (fixml.getMarketSegmentID() != null) {
            marketSegmentID = fixml.getMarketSegmentID();
        }
        if (fixml.getTradingSessionID() != null) {
            tradingSessionID = fixml.getTradingSessionID();
        }
        if (fixml.getTradingSessionSubID() != null) {
            tradingSessionSubID = fixml.getTradingSessionSubID();
        }
        if (fixml.getUnsolicitedIndicator() != null) {
            unsolicitedIndicator = fixml.getUnsolicitedIndicator();
        }
        if (fixml.getSecurityTradingStatus() != null) {
            securityTradingStatus = fixml.getSecurityTradingStatus();
        }
        if (fixml.getFinancialStatus() != null) {
            financialStatus = fixml.getFinancialStatus();
        }
        if (fixml.getCorporateAction() != null) {
            corporateAction = fixml.getCorporateAction();
        }
        if (fixml.getHaltReason() != null) {
            haltReason = fixml.getHaltReason();
        }
        if (fixml.getInViewOfCommon() != null) {
            inViewOfCommon = fixml.getInViewOfCommon();
        }
        if (fixml.getDueToRelated() != null) {
            dueToRelated = fixml.getDueToRelated();
        }
        if (fixml.getBuyVolume() != null) {
            buyVolume = fixml.getBuyVolume();
        }
        if (fixml.getSellVolume() != null) {
            sellVolume = fixml.getSellVolume();
        }
        if (fixml.getHighPx() != null) {
            highPx = fixml.getHighPx();
        }
        if (fixml.getLowPx() != null) {
            lowPx = fixml.getLowPx();
        }
        if (fixml.getLastPx() != null) {
            lastPx = fixml.getLastPx();
        }
        if (fixml.getTransactTime() != null) {
            transactTime = fixml.getTransactTime();
        }
        if (fixml.getAdjustment() != null) {
            adjustment = fixml.getAdjustment();
        }
        if (fixml.getFirstPx() != null) {
            firstPx = fixml.getFirstPx();
        }
        if (fixml.getText() != null) {
            text = fixml.getText();
        }
        if (fixml.getEncodedTextLen() != null) {
            encodedTextLen = fixml.getEncodedTextLen();
            encodedText = fixml.getEncodedText();
        }
    }

    // ACCESSOR METHODS
    //////////////////////////////////////////

    @XmlElementRef
    @Override
    public Header getHeader() {
        return header;
    }

    @Override
    public void setHeader(Header header) {
        this.header = header;
    }

    @XmlAttribute(name = "StatReqID")
    @Override
    public String getSecurityStatusReqID() {
        return securityStatusReqID;
    }

    @Override
    public void setSecurityStatusReqID(String securityStatusReqID) {
        this.securityStatusReqID = securityStatusReqID;
    }

    @XmlElementRef
    @Override
    public Instrument getInstrument() {
        return instrument;
    }

    @Override
    public void setInstrument() {
        FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, validateRequired);
        this.instrument = new Instrument50(context);
    }

    @Override
    public void clearInstrument() {
        this.instrument = null;
    }

    public void setInstrument(Instrument instrument) {
        this.instrument = instrument;
    }

    @XmlElementRef
    @Override
    public InstrumentExtension getInstrumentExtension() {
        return instrumentExtension;
    }

    @Override
    public void setInstrumentExtension() {
        FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, validateRequired);
        this.instrumentExtension = new InstrumentExtension50(context);
    }

    @Override
    public void clearInstrumentExtension() {
        this.instrumentExtension = null;
    }

    public void setInstrumentExtension(InstrumentExtension instrumentExtension) {
        this.instrumentExtension = instrumentExtension;
    }

    @Override
    public Integer getNoUnderlyings() {
        return noUnderlyings;
    }

    @Override
    public void setNoUnderlyings(Integer noUnderlyings) {
        this.noUnderlyings = noUnderlyings;
        if (noUnderlyings != null) {
            underlyingInstruments = new UnderlyingInstrument[noUnderlyings.intValue()];
            FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, validateRequired);
            for (int i = 0; i < underlyingInstruments.length; i++) {
                underlyingInstruments[i] = new UnderlyingInstrument50(context);
            }
        }
    }

    @XmlElementRef
    @Override
    public UnderlyingInstrument[] getUnderlyingInstruments() {
        return underlyingInstruments;
    }

    public void setUnderlyingInstruments(UnderlyingInstrument[] underlyingInstruments) {
        this.underlyingInstruments = underlyingInstruments;
        if (underlyingInstruments != null) {
            noUnderlyings = new Integer(underlyingInstruments.length);
        }
    }

    @Override
    public UnderlyingInstrument addUnderlyingInstrument() {
        UnderlyingInstrument group = new UnderlyingInstrument50(new FragmentContext(sessionCharset, messageEncoding, validateRequired));
        List<UnderlyingInstrument> groups = new ArrayList<UnderlyingInstrument>();
        if (underlyingInstruments != null && underlyingInstruments.length > 0) {
            groups = new ArrayList<UnderlyingInstrument>(Arrays.asList(underlyingInstruments));
        }
        groups.add(group);
        underlyingInstruments = groups.toArray(new UnderlyingInstrument[groups.size()]);
        noUnderlyings = new Integer(underlyingInstruments.length);

        return group;
    }

    @Override
    public UnderlyingInstrument deleteUnderlyingInstrument(int index) {
        UnderlyingInstrument result = null;
        if (underlyingInstruments != null && underlyingInstruments.length > 0 && underlyingInstruments.length > index) {
            List<UnderlyingInstrument> groups = new ArrayList<UnderlyingInstrument>(Arrays.asList(underlyingInstruments));
            result = groups.remove(index);
            underlyingInstruments = groups.toArray(new UnderlyingInstrument[groups.size()]);
            if (underlyingInstruments.length > 0) {
                noUnderlyings = new Integer(underlyingInstruments.length);
            } else {
                underlyingInstruments = null;
                noUnderlyings = null;
            }
        }

        return result;
    }

    @Override
    public int clearUnderlyingInstruments() {
        int result = 0;
        if (underlyingInstruments != null && underlyingInstruments.length > 0) {
            result = underlyingInstruments.length;
            underlyingInstruments = null;
            noUnderlyings = null;
        }

        return result;
    }

    @Override
    public Integer getNoLegs() {
        return noLegs;
    }

    @Override
    public void setNoLegs(Integer noLegs) {
        this.noLegs = noLegs;
        if (noLegs != null) {
            instrumentLegs = new InstrumentLeg[noLegs.intValue()];
            FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, validateRequired);
            for (int i = 0; i < instrumentLegs.length; i++) {
                instrumentLegs[i] = new InstrumentLeg50(context);
            }
        }
    }

    @XmlElementRef
    @Override
    public InstrumentLeg[] getInstrumentLegs() {
        return instrumentLegs;
    }

    public void setInstrumentLegs(InstrumentLeg[] instrumentLegs) {
        this.instrumentLegs = instrumentLegs;
        if (instrumentLegs != null) {
            noLegs = new Integer(instrumentLegs.length);
        }
    }

    @Override
    public InstrumentLeg addInstrumentLeg() {
        InstrumentLeg group = new InstrumentLeg50(new FragmentContext(sessionCharset, messageEncoding, validateRequired));
        List<InstrumentLeg> groups = new ArrayList<InstrumentLeg>();
        if (instrumentLegs != null && instrumentLegs.length > 0) {
            groups = new ArrayList<InstrumentLeg>(Arrays.asList(instrumentLegs));
        }
        groups.add(group);
        instrumentLegs = groups.toArray(new InstrumentLeg[groups.size()]);
        noLegs = new Integer(instrumentLegs.length);

        return group;
    }

    @Override
    public InstrumentLeg deleteInstrumentLeg(int index) {
        InstrumentLeg result = null;

        if (instrumentLegs != null && instrumentLegs.length > 0 && instrumentLegs.length > index) {
            List<InstrumentLeg> groups = new ArrayList<InstrumentLeg>(Arrays.asList(instrumentLegs));
            result = groups.remove(index);
            instrumentLegs = groups.toArray(new InstrumentLeg[groups.size()]);
            if (instrumentLegs.length > 0) {
                noLegs = new Integer(instrumentLegs.length);
            } else {
                instrumentLegs = null;
                noLegs = null;
            }
        }

        return result;
    }

    @Override
    public int clearInstrumentLegs() {
        int result = 0;
        if (instrumentLegs != null && instrumentLegs.length > 0) {
            result = instrumentLegs.length;
            instrumentLegs = null;
            noLegs = null;
        }

        return result;
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

    @XmlAttribute(name = "MktID")
    @Override
    public String getMarketID() {
        return marketID;
    }

    @Override
    public void setMarketID(String marketID) {
        this.marketID = marketID;
    }

    @XmlAttribute(name = "MktSegID")
    @Override
    public String getMarketSegmentID() {
        return marketSegmentID;
    }

    @Override
    public void setMarketSegmentID(String marketSegmentID) {
        this.marketSegmentID = marketSegmentID;
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

    @XmlAttribute(name = "Unsol")
    @XmlJavaTypeAdapter(FixBooleanAdapter.class)
    @Override
    public Boolean getUnsolicitedIndicator() {
        return unsolicitedIndicator;
    }

    @Override
    public void setUnsolicitedIndicator(Boolean unsolicitedIndicator) {
        this.unsolicitedIndicator = unsolicitedIndicator;
    }

    @XmlAttribute(name = "TrdgStat")
    @Override
    public SecurityTradingStatus getSecurityTradingStatus() {
        return securityTradingStatus;
    }

    @Override
    public void setSecurityTradingStatus(SecurityTradingStatus securityTradingStatus) {
        this.securityTradingStatus = securityTradingStatus;
    }

    @XmlAttribute(name = "FinclStat")
    @Override
    public String getFinancialStatus() {
        return financialStatus;
    }

    @Override
    public void setFinancialStatus(String financialStatus) {
        this.financialStatus = financialStatus;
    }

    @XmlAttribute(name = "CorpActn")
    @Override
    public CorporateAction getCorporateAction() {
        return corporateAction;
    }

    @Override
    public void setCorporateAction(CorporateAction corporateAction) {
        this.corporateAction = corporateAction;
    }

    @XmlAttribute(name = "HaltRsn")
    @Override
    public HaltReason getHaltReason() {
        return haltReason;
    }

    @Override
    public void setHaltReason(HaltReason haltReason) {
        this.haltReason = haltReason;
    }

    @XmlAttribute(name = "InViewOfCmn")
    @XmlJavaTypeAdapter(FixBooleanAdapter.class)
    @Override
    public Boolean getInViewOfCommon() {
        return inViewOfCommon;
    }

    @Override
    public void setInViewOfCommon(Boolean inViewOfCommon) {
        this.inViewOfCommon = inViewOfCommon;
    }

    @XmlAttribute(name = "DueToReltd")
    @XmlJavaTypeAdapter(FixBooleanAdapter.class)
    @Override
    public Boolean getDueToRelated() {
        return dueToRelated;
    }

    @Override
    public void setDueToRelated(Boolean dueToRelated) {
        this.dueToRelated = dueToRelated;
    }

    @XmlAttribute(name = "BuyVol")
    @Override
    public Double getBuyVolume() {
        return buyVolume;
    }

    @Override
    public void setBuyVolume(Double buyVolume) {
        this.buyVolume = buyVolume;
    }

    @XmlAttribute(name = "SellVol")
    @Override
    public Double getSellVolume() {
        return sellVolume;
    }

    @Override
    public void setSellVolume(Double sellVolume) {
        this.sellVolume = sellVolume;
    }

    @XmlAttribute(name = "HighPx")
    @Override
    public Double getHighPx() {
        return highPx;
    }

    @Override
    public void setHighPx(Double highPx) {
        this.highPx = highPx;
    }

    @XmlAttribute(name = "LowPx")
    @Override
    public Double getLowPx() {
        return lowPx;
    }

    @Override
    public void setLowPx(Double lowPx) {
        this.lowPx = lowPx;
    }

    @XmlAttribute(name = "LastPx")
    @Override
    public Double getLastPx() {
        return lastPx;
    }

    @Override
    public void setLastPx(Double lastPx) {
        this.lastPx = lastPx;
    }

    @XmlAttribute(name = "TxnTm")
    @XmlJavaTypeAdapter(FixUTCDateTimeAdapter.class)
    @Override
    public Date getTransactTime() {
        return transactTime;
    }

    @Override
    public void setTransactTime(Date transactTime) {
        this.transactTime = transactTime;
    }

    @XmlAttribute(name = "Adjmt")
    @Override
    public Adjustment getAdjustment() {
        return adjustment;
    }

    @Override
    public void setAdjustment(Adjustment adjustment) {
        this.adjustment = adjustment;
    }

    @XmlAttribute(name = "FirstPx")
    @Override
    public Double getFirstPx() {
        return firstPx;
    }

    @Override
    public void setFirstPx(Double firstPx) {
        this.firstPx = firstPx;
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
                instrument = new Instrument50(context);
            }
            instrument.decode(tag, message);
        }
        if (INSTRUMENT_EXTENSION_COMP_TAGS.contains(tag.tagNum)) {
            if (instrumentExtension == null) {
                instrumentExtension = new InstrumentExtension50(context);
            }
            instrumentExtension.decode(tag, message);
        }
        if (UNDERLYING_INSTRUMENT_COMP_TAGS.contains(tag.tagNum)) {
            if (noUnderlyings != null && noUnderlyings.intValue() > 0) {
                message.reset();
                underlyingInstruments = new UnderlyingInstrument[noUnderlyings.intValue()];
                for (int i = 0; i < noUnderlyings.intValue(); i++) {
                    UnderlyingInstrument component = new UnderlyingInstrument50(context);
                    component.decode(message);
                    underlyingInstruments[i] = component;
                }
            }
        }
        if (INSTRUMENT_LEG_COMP_TAGS.contains(tag.tagNum)) {
            if (noLegs != null && noLegs.intValue() > 0) {
                message.reset();
                instrumentLegs = new InstrumentLeg[noLegs.intValue()];
                for (int i = 0; i < noLegs.intValue(); i++) {
                    InstrumentLeg group = new InstrumentLeg50(context);
                    group.decode(message);
                    instrumentLegs[i] = group;
                }
            }
        }
    }

    @Override
    protected String getUnsupportedTagMessage() {
        return "This tag is not supported in [SecurityStatusMsg] message version [5.0].";
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
