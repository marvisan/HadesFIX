/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * CrossOrderCancelRequestMsg44.java
 *
 * $Id: CrossOrderCancelRequestMsg44.java,v 1.1 2011-05-21 23:53:23 vrotaru Exp $
 */
package net.hades.fix.message.impl.v44;

import net.hades.fix.message.CrossOrderCancelRequestMsg;
import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.comp.Instrument;
import net.hades.fix.message.comp.impl.v44.InstrumentLeg44;
import net.hades.fix.message.comp.impl.v44.UnderlyingInstrument44;
import net.hades.fix.message.struct.Tag;
import net.hades.fix.message.type.ApplVerID;
import net.hades.fix.message.type.CrossPrioritization;
import net.hades.fix.message.type.CrossType;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
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
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import net.hades.fix.message.FIXFragment;
import net.hades.fix.message.Header;
import net.hades.fix.message.comp.InstrumentLeg;
import net.hades.fix.message.comp.UnderlyingInstrument;
import net.hades.fix.message.comp.impl.v44.Instrument44;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.group.SideCrossOrdCxlGroup;
import net.hades.fix.message.group.impl.v44.SideCrossOrdCxlGroup44;
import net.hades.fix.message.type.BeginString;
import net.hades.fix.message.type.SessionRejectReason;
import net.hades.fix.message.type.TagNum;
import net.hades.fix.message.util.MsgUtil;
import net.hades.fix.message.util.TagEncoder;
import net.hades.fix.message.xml.codec.jaxb.adapter.FixDateTimeAdapter;

/**
 * FIX version 4.4 CrossOrderCancelRequestMsg implementation.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 21/05/2011, 9:32:41 AM
 */
@XmlRootElement(name="CrssOrdCxlReq")
@XmlType(propOrder = {"header", "sideCrossOrdCxlGroups", "instrument", "underlyingInstruments", "instrumentLegs"})
@XmlAccessorType(XmlAccessType.NONE)
public class CrossOrderCancelRequestMsg44 extends CrossOrderCancelRequestMsg {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> SIDE_CROSS_ORD_MOD_GROUP_TAGS = new SideCrossOrdCxlGroup44().getFragmentAllTags();
    protected static final Set<Integer> INSTRUMENT_COMP_TAGS = new Instrument44().getFragmentAllTags();
    protected static final Set<Integer> UNDERLYING_INSTRUMENT_COMP_TAGS = new UnderlyingInstrument44().getFragmentAllTags();
    protected static final Set<Integer> INSTRUMENT_LEG_COMP_TAGS = new InstrumentLeg44().getFragmentAllTags();

    protected static final Set<Integer> START_COMP_TAGS;

    protected static final Set<Integer> ALL_TAGS;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">

    static {
        ALL_TAGS = new HashSet<Integer>(TAGS);
        ALL_TAGS.addAll(SIDE_CROSS_ORD_MOD_GROUP_TAGS);
        ALL_TAGS.addAll(INSTRUMENT_COMP_TAGS);
        ALL_TAGS.addAll(UNDERLYING_INSTRUMENT_COMP_TAGS);
        ALL_TAGS.addAll(INSTRUMENT_LEG_COMP_TAGS);
        START_COMP_TAGS = new HashSet<Integer>(SIDE_CROSS_ORD_MOD_GROUP_TAGS);
        START_COMP_TAGS.addAll(INSTRUMENT_COMP_TAGS);
        START_COMP_TAGS.addAll(UNDERLYING_INSTRUMENT_COMP_TAGS);
        START_COMP_TAGS.addAll(INSTRUMENT_LEG_COMP_TAGS);
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">

    protected Set<Integer> STANDARD_SECURED_TAGS = TAGS;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public CrossOrderCancelRequestMsg44() {
        super();
    }

    public CrossOrderCancelRequestMsg44(Header header, ByteBuffer rawMsg)
    throws InvalidMsgException, TagNotPresentException, BadFormatMsgException {
        super(header, rawMsg);
        SECURED_TAGS = STANDARD_SECURED_TAGS;
    }

    public CrossOrderCancelRequestMsg44(BeginString beginString) throws InvalidMsgException {
        super(beginString);
        SECURED_TAGS = STANDARD_SECURED_TAGS;
    }

    public CrossOrderCancelRequestMsg44(BeginString beginString, ApplVerID applVerID) throws InvalidMsgException {
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

    @Override
    public void copyFixmlData(FIXFragment fragment) {
        CrossOrderCancelRequestMsg44 fixml = (CrossOrderCancelRequestMsg44) fragment;
        if (fixml.getOrderID() != null) {
            orderID = fixml.getOrderID();
        }
        if (fixml.getCrossID() != null) {
            crossID = fixml.getCrossID();
        }
        if (fixml.getOrigCrossID() != null) {
            origCrossID = fixml.getOrigCrossID();
        }
        if (fixml.getCrossType() != null) {
            crossType = fixml.getCrossType();
        }
        if (fixml.getCrossPrioritization() != null) {
            crossPrioritization = fixml.getCrossPrioritization();
        }
        if (fixml.getSideCrossOrdCxlGroups() != null && fixml.getSideCrossOrdCxlGroups().length > 0) {
            setSideCrossOrdCxlGroups(fixml.getSideCrossOrdCxlGroups());
        }
        if (fixml.getInstrument() != null) {
            setInstrument(fixml.getInstrument());
        }
        if (fixml.getUnderlyingInstruments() != null && fixml.getUnderlyingInstruments().length > 0) {
            setUnderlyingInstruments(fixml.getUnderlyingInstruments());
        }
        if (fixml.getInstrumentLegs() != null && fixml.getInstrumentLegs().length > 0) {
            setInstrumentLegs(fixml.getInstrumentLegs());
        }
        if (fixml.getTransactTime() != null) {
            transactTime = fixml.getTransactTime();
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

    @XmlAttribute(name = "OrdID")
    @Override
    public String getOrderID() {
        return orderID;
    }

    @Override
    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    @XmlAttribute(name = "ID")
    @Override
    public String getCrossID() {
        return crossID;
    }

    @Override
    public void setCrossID(String crossID) {
        this.crossID = crossID;
    }

    @XmlAttribute(name = "OrigID")
    @Override
    public String getOrigCrossID() {
        return origCrossID;
    }

    @Override
    public void setOrigCrossID(String origCrossID) {
        this.origCrossID = origCrossID;
    }

    @XmlAttribute(name = "Typ")
    @Override
    public CrossType getCrossType() {
        return crossType;
    }

    @Override
    public void setCrossType(CrossType crossType) {
        this.crossType = crossType;
    }

    @XmlAttribute(name = "Priorty")
    @Override
    public CrossPrioritization getCrossPrioritization() {
        return crossPrioritization;
    }

    @Override
    public void setCrossPrioritization(CrossPrioritization crossPrioritization) {
        this.crossPrioritization = crossPrioritization;
    }

    @Override
    public Integer getNoSides() {
        return noSides;
    }

    @Override
    public void setNoSides(Integer noSides) {
        this.noSides = noSides;
        if (noSides != null) {
            sideCrossOrdCxlGroups = new SideCrossOrdCxlGroup[noSides.intValue()];
            for (int i = 0; i < sideCrossOrdCxlGroups.length; i++) {
                sideCrossOrdCxlGroups[i] = new SideCrossOrdCxlGroup44(new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired));
            }
        }
    }

    @XmlElementRef
    @Override
    public SideCrossOrdCxlGroup[] getSideCrossOrdCxlGroups() {
        return sideCrossOrdCxlGroups;
    }

    public void setSideCrossOrdCxlGroups(SideCrossOrdCxlGroup[] sideCrossOrdCxlGroups) {
        this.sideCrossOrdCxlGroups = sideCrossOrdCxlGroups;
        if (sideCrossOrdCxlGroups != null) {
            noSides = new Integer(sideCrossOrdCxlGroups.length);
        }
    }

    @Override
    public SideCrossOrdCxlGroup addSideCrossOrdCxlGroup() {
        SideCrossOrdCxlGroup group = new SideCrossOrdCxlGroup44(new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired));
        List<SideCrossOrdCxlGroup> groups = new ArrayList<SideCrossOrdCxlGroup>();
        if (sideCrossOrdCxlGroups != null && sideCrossOrdCxlGroups.length > 0) {
            groups = new ArrayList<SideCrossOrdCxlGroup>(Arrays.asList(sideCrossOrdCxlGroups));
        }
        groups.add(group);
        sideCrossOrdCxlGroups = groups.toArray(new SideCrossOrdCxlGroup[groups.size()]);
        noSides = new Integer(sideCrossOrdCxlGroups.length);

        return group;
    }

    @Override
    public SideCrossOrdCxlGroup deleteSideCrossOrdCxlGroup(int index) {
        SideCrossOrdCxlGroup result = null;
        if (sideCrossOrdCxlGroups != null && sideCrossOrdCxlGroups.length > 0 && sideCrossOrdCxlGroups.length > index) {
            List<SideCrossOrdCxlGroup> groups = new ArrayList<SideCrossOrdCxlGroup>(Arrays.asList(sideCrossOrdCxlGroups));
            result = groups.remove(index);
            sideCrossOrdCxlGroups = groups.toArray(new SideCrossOrdCxlGroup[groups.size()]);
            if (sideCrossOrdCxlGroups.length > 0) {
                noSides = new Integer(sideCrossOrdCxlGroups.length);
            } else {
                sideCrossOrdCxlGroups = null;
                noSides = null;
            }
        }

        return result;
    }

    @Override
    public int clearSideCrossOrdCxlGroups() {
        int result = 0;
        if (sideCrossOrdCxlGroups != null && sideCrossOrdCxlGroups.length > 0) {
            result = sideCrossOrdCxlGroups.length;
            sideCrossOrdCxlGroups = null;
            noSides = null;
        }

        return result;
    }

    @XmlElementRef
    @Override
    public Instrument getInstrument() {
        return instrument;
    }

    @Override
    public void setInstrument() {
        FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired);
        this.instrument = new Instrument44(context);
    }

    public void setInstrument(Instrument instrument) {
        this.instrument = instrument;
    }
    
    @Override
    public void clearInstrument() {
        this.instrument = null;
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
            FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired);
            for (int i = 0; i < underlyingInstruments.length; i++) {
                underlyingInstruments[i] = new UnderlyingInstrument44(context);
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
        UnderlyingInstrument group = new UnderlyingInstrument44(new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired));
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
            FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired);
            for (int i = 0; i < instrumentLegs.length; i++) {
                instrumentLegs[i] = new InstrumentLeg44(context);
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
        InstrumentLeg group = new InstrumentLeg44(new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired));
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

    @XmlAttribute(name = "TxnTm")
    @XmlJavaTypeAdapter(FixDateTimeAdapter.class)
    @Override
    public Date getTransactTime() {
        return transactTime;
    }

    @Override
    public void setTransactTime(Date transactTime) {
        this.transactTime = transactTime;
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
            if (MsgUtil.isTagInList(TagNum.CrossID, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.CrossID, crossID);
            }
            if (MsgUtil.isTagInList(TagNum.OrigCrossID, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.OrigCrossID, origCrossID);
            }
            if (crossType != null && MsgUtil.isTagInList(TagNum.CrossType, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.CrossType, crossType.getValue());
            }
            if (crossPrioritization != null && MsgUtil.isTagInList(TagNum.CrossPrioritization, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.CrossPrioritization, crossPrioritization.getValue());
            }
            if (noSides != null && MsgUtil.isTagInList(TagNum.NoSides, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.NoSides, noSides);
                if (sideCrossOrdCxlGroups != null && sideCrossOrdCxlGroups.length == noSides.intValue()) {
                    for (int i = 0; i < noSides.intValue(); i++) {
                        if (sideCrossOrdCxlGroups[i] != null) {
                            bao.write(sideCrossOrdCxlGroups[i].encode(getMsgSecureTypeForFlag(secured)));
                        }
                    }
                } else {
                    String error = "SideCrossOrdCxlGroups field has been set but there is no data or the number of groups does not match.";
                    LOGGER.severe(error);
                    throw new BadFormatMsgException(SessionRejectReason.IncorrectCountForGroups, getHeader().getMsgType(),
                            TagNum.NoSides.getValue(), error);
                }
            }
            if (instrument != null) {
                bao.write(instrument.encode(getMsgSecureTypeForFlag(secured)));
            }
            if (noUnderlyings != null && MsgUtil.isTagInList(TagNum.NoUnderlyings, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.NoUnderlyings, noUnderlyings);
                if (underlyingInstruments != null && underlyingInstruments.length == noUnderlyings.intValue()) {
                    for (int i = 0; i < noUnderlyings.intValue(); i++) {
                        if (underlyingInstruments[i] != null) {
                            bao.write(underlyingInstruments[i].encode(getMsgSecureTypeForFlag(secured)));
                        }
                    }
                } else {
                    String error = "UnderlyingInstrument field has been set but there is no data or the number of groups does not match.";
                    LOGGER.severe(error);
                    throw new BadFormatMsgException(SessionRejectReason.IncorrectCountForGroups, getHeader().getMsgType(),
                            TagNum.NoUnderlyings.getValue(), error);
                }
            }
            if (noLegs != null && MsgUtil.isTagInList(TagNum.NoLegs, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.NoLegs, noLegs);
                if (instrumentLegs != null && instrumentLegs.length == noLegs.intValue()) {
                    for (int i = 0; i < noLegs.intValue(); i++) {
                        if (instrumentLegs[i] != null) {
                            bao.write(instrumentLegs[i].encode(getMsgSecureTypeForFlag(secured)));
                        }
                    }
                } else {
                    String error = "InstrumentLeg field has been set but there is no data or the number of groups does not match.";
                    LOGGER.severe(error);
                    throw new BadFormatMsgException(SessionRejectReason.IncorrectCountForGroups, getHeader().getMsgType(),
                            TagNum.NoLegs.getValue(), error);
                }
            }
            if (MsgUtil.isTagInList(TagNum.TransactTime, SECURED_TAGS, secured)) {
                TagEncoder.encodeUtcTimestamp(bao, TagNum.TransactTime, transactTime);
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
        if (SIDE_CROSS_ORD_MOD_GROUP_TAGS.contains(tag.tagNum)) {
            if (noSides != null && noSides.intValue() > 0) {
                message.reset();
                sideCrossOrdCxlGroups = new SideCrossOrdCxlGroup[noSides.intValue()];
                for (int i = 0; i < noSides.intValue(); i++) {
                    SideCrossOrdCxlGroup component = new SideCrossOrdCxlGroup44(context);
                    component.decode(message);
                    sideCrossOrdCxlGroups[i] = component;
                }
            }
        }
        if (INSTRUMENT_COMP_TAGS.contains(tag.tagNum)) {
            if (instrument == null) {
                instrument = new Instrument44(context);
            }
            instrument.decode(tag, message);
        }
        if (UNDERLYING_INSTRUMENT_COMP_TAGS.contains(tag.tagNum)) {
            if (noUnderlyings != null && noUnderlyings.intValue() > 0) {
                message.reset();
                underlyingInstruments = new UnderlyingInstrument[noUnderlyings.intValue()];
                for (int i = 0; i < noUnderlyings.intValue(); i++) {
                    UnderlyingInstrument component = new UnderlyingInstrument44(context);
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
                    InstrumentLeg group = new InstrumentLeg44(context);
                    group.decode(message);
                    instrumentLegs[i] = group;
                }
            }
        }
    }

    @Override
    protected String getUnsupportedTagMessage() {
        return "This tag is not supported in [CrossOrderCancelRequestMsg] message version [4.4].";
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
