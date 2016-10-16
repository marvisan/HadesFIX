/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * NewsMsg50.java
 *
 * $Id: NewsMsg50.java,v 1.11 2011-04-14 23:44:40 vrotaru Exp $
 */
package net.hades.fix.message.impl.v50;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.Header;
import net.hades.fix.message.NewsMsg;
import net.hades.fix.message.comp.Instrument;
import net.hades.fix.message.comp.InstrumentLeg;
import net.hades.fix.message.comp.impl.v50.UnderlyingInstrument50;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.struct.Tag;
import net.hades.fix.message.type.ApplVerID;

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

import net.hades.fix.message.FIXFragment;
import net.hades.fix.message.comp.UnderlyingInstrument;
import net.hades.fix.message.comp.impl.v50.Instrument50;
import net.hades.fix.message.comp.impl.v50.InstrumentLeg50;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.group.LinesOfTextGroup;
import net.hades.fix.message.group.RoutingIDGroup;
import net.hades.fix.message.group.impl.v50.LinesOfTextGroup50;
import net.hades.fix.message.group.impl.v50.RoutingIDGroup50;
import net.hades.fix.message.type.BeginString;
import net.hades.fix.message.type.Urgency;
import net.hades.fix.message.xml.codec.jaxb.type.v50.InstrumentType50;

/**
 * FIX version 5.0 NewsMsg implementation.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.11 $
 * @created 23/03/2009, 7:15:41 PM
 */
@XmlRootElement(name="News")
@XmlType(propOrder = {"header", "routingIDGroups", "instrumentsType", "instrumentLegs",
    "underlyingInstruments", "linesOfTextGroups"})
@XmlAccessorType(XmlAccessType.NONE)
public class NewsMsg50 extends NewsMsg {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> START_COMP_TAGS;

    protected static final Set<Integer> ALL_TAGS;

    protected static final Set<Integer> LINES_OF_TEXT_GROUP_TAGS = new LinesOfTextGroup50().getFragmentAllTags();
    protected static final Set<Integer> ROUTING_ID_GROUP_TAGS = new RoutingIDGroup50().getFragmentAllTags();
    protected static final Set<Integer> INSTRUMENT_COMP_TAGS = new Instrument50().getFragmentAllTags();
    protected static final Set<Integer> INSTRUMENT_LEG_COMP_TAGS = new InstrumentLeg50().getFragmentAllTags();
    protected static final Set<Integer> UNDLY_INSTRUMENT_COMP_TAGS = new UnderlyingInstrument50().getFragmentAllTags();

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">

    static {
        ALL_TAGS = new HashSet<Integer>(TAGS);
        ALL_TAGS.addAll(START_DATA_TAGS);
        ALL_TAGS.addAll(LINES_OF_TEXT_GROUP_TAGS);
        ALL_TAGS.addAll(ROUTING_ID_GROUP_TAGS);
        ALL_TAGS.addAll(INSTRUMENT_COMP_TAGS);
        ALL_TAGS.addAll(INSTRUMENT_LEG_COMP_TAGS);
        ALL_TAGS.addAll(UNDLY_INSTRUMENT_COMP_TAGS);
        START_COMP_TAGS = new HashSet<Integer>(LINES_OF_TEXT_GROUP_TAGS);
        START_COMP_TAGS.addAll(ROUTING_ID_GROUP_TAGS);
        START_COMP_TAGS.addAll(INSTRUMENT_COMP_TAGS);
        START_COMP_TAGS.addAll(INSTRUMENT_LEG_COMP_TAGS);
        START_COMP_TAGS.addAll(UNDLY_INSTRUMENT_COMP_TAGS);
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">

    private InstrumentType50[] instrumentsType;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public NewsMsg50() {
        super();
    }

    public NewsMsg50(Header header, ByteBuffer rawMsg)
    throws InvalidMsgException, TagNotPresentException, BadFormatMsgException {
        super(header, rawMsg);
    }

    public NewsMsg50(BeginString beginString) throws InvalidMsgException {
        super(beginString);
    }

    public NewsMsg50(BeginString beginString, ApplVerID applVerID) throws InvalidMsgException {
        super(beginString, applVerID);
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Public Methods">

    @Override
    public Set<Integer> getFragmentAllTags() {
        return ALL_TAGS;
    }

    @Override
    public void copyFixmlData(FIXFragment fragment) {
        NewsMsg50 fixml = (NewsMsg50) fragment;
        if (fixml.getOrigTime() != null) {
            origTime = fixml.getOrigTime();
        }
        if (fixml.getUrgency() != null) {
            urgency = fixml.getUrgency();
        }
        if (fixml.getHeadline() != null) {
            headline = fixml.getHeadline();
        }
        if (fixml.getEncodedHeadline() != null) {
            encodedHeadlineLen = fixml.getEncodedHeadlineLen();
            encodedHeadline = fixml.getEncodedHeadline();
        }
        if (fixml.getRoutingIDGroups() != null && fixml.getRoutingIDGroups().length > 0) {
            setRoutingIDGroups (fixml.getRoutingIDGroups());
        }
        if (fixml.getInstrumentsType() != null && fixml.getInstrumentsType().length > 0) {
            setInstrumentsType(fixml.getInstrumentsType());
        }
        if (fixml.getInstrumentLegs() != null && fixml.getInstrumentLegs().length > 0) {
            setInstrumentLegs(fixml.getInstrumentLegs());
        }
        if (fixml.getUnderlyingInstruments() != null && fixml.getUnderlyingInstruments().length > 0) {
            setUnderlyingInstruments(fixml.getUnderlyingInstruments());
        }
        if (fixml.getLinesOfTextGroups() != null && fixml.getLinesOfTextGroups().length > 0) {
            setLinesOfTextGroups(fixml.getLinesOfTextGroups());
        }
        if (fixml.getUrlLink() != null) {
            urlLink = fixml.getUrlLink();
        }
        if (fixml.getRawDataLength() != null) {
            rawDataLength = fixml.getRawDataLength();
            rawData = fixml.getRawData();
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

    @XmlAttribute(name = "OrigTm")
    @Override
    public Date getOrigTime() {
        return origTime;
    }

    @Override
    public void setOrigTime(Date origTime) {
        this.origTime = origTime;
    }

    @XmlAttribute(name = "Urgency")
    @Override
    public Urgency getUrgency() {
        return urgency;
    }

    @Override
    public void setUrgency(Urgency urgency) {
        this.urgency = urgency;
    }

    @XmlAttribute(name = "Headline")
    @Override
    public String getHeadline() {
        return headline;
    }

    @Override
    public void setHeadline(String headline) {
        this.headline = headline;
    }

    @XmlAttribute(name = "EncHeadlineLen")
    @Override
    public Integer getEncodedHeadlineLen() {
        return encodedHeadlineLen;
    }

    @Override
    public void setEncodedHeadlineLen(Integer encodedHeadlineLen) {
        this.encodedHeadlineLen = encodedHeadlineLen;
    }

    @XmlAttribute(name = "EncHeadline")
    @Override
    public byte[] getEncodedHeadline() {
        return encodedHeadline;
    }

    @Override
    public void setEncodedHeadline(byte[] encodedHeadline) {
        this.encodedHeadline = encodedHeadline;
        if (encodedHeadlineLen == null) {
            encodedHeadlineLen = new Integer(encodedHeadline.length);
        }
    }

    @Override
    public Integer getNoRoutingIDs() {
        return noRoutingIDs;
    }

    @Override
    public void setNoRoutingIDs(Integer noRoutingIDs) {
        this.noRoutingIDs = noRoutingIDs;
        if (noRoutingIDs != null) {
            routingIDGroups = new RoutingIDGroup[noRoutingIDs.intValue()];
            FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, validateRequired);
            for (int i = 0; i < routingIDGroups.length; i++) {
                routingIDGroups[i] = new RoutingIDGroup50(context);
            }
        }
    }

    @XmlElementRef
    @Override
    public RoutingIDGroup[] getRoutingIDGroups() {
        return routingIDGroups;
    }

    public void setRoutingIDGroups(RoutingIDGroup[] routingIDGroups) {
        this.routingIDGroups = routingIDGroups;
        if (routingIDGroups != null) {
            noRoutingIDs = new Integer(routingIDGroups.length);
        }
    }

    @Override
    public RoutingIDGroup addRoutingIDGroup() {

        RoutingIDGroup group = new RoutingIDGroup50(new FragmentContext(sessionCharset, messageEncoding, validateRequired));
        List<RoutingIDGroup> groups = new ArrayList<RoutingIDGroup>();
        if (routingIDGroups != null && routingIDGroups.length > 0) {
            groups = new ArrayList<RoutingIDGroup>(Arrays.asList(routingIDGroups));
        }
        groups.add(group);
        routingIDGroups = groups.toArray(new RoutingIDGroup[groups.size()]);
        noRoutingIDs = new Integer(routingIDGroups.length);

        return group;
    }

    @Override
    public RoutingIDGroup deleteRoutingIDGroup(int index) {
       RoutingIDGroup result = null;

        if (routingIDGroups != null && routingIDGroups.length > 0 && routingIDGroups.length > index) {
            List<RoutingIDGroup> groups = new ArrayList<RoutingIDGroup>(Arrays.asList(routingIDGroups));
            result = groups.remove(index);
            routingIDGroups = groups.toArray(new RoutingIDGroup[groups.size()]);
            if (routingIDGroups.length > 0) {
                noRoutingIDs = new Integer(routingIDGroups.length);
            } else {
                routingIDGroups = null;
                noRoutingIDs = null;
            }
        }

        return result;
    }

    @Override
    public int clearRoutingIDGroups() {
        int result = 0;
        if (routingIDGroups != null && routingIDGroups.length > 0) {
            result = routingIDGroups.length;
            routingIDGroups = null;
            noRoutingIDs = null;
        }

        return result;
    }

    @Override
    public Integer getNoRelatedSyms() {
        return noRelatedSyms;
    }

    @Override
    public void setNoRelatedSyms(Integer noRelatedSyms) {
        this.noRelatedSyms = noRelatedSyms;
        if (noRelatedSyms != null) {
            instruments = new Instrument[noRelatedSyms.intValue()];
            FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, validateRequired);
            for (int i = 0; i < instruments.length; i++) {
                instruments[i] = new Instrument50(context);
            }
        }
    }

    @Override
    public Instrument[] getInstruments() {
        return instruments;
    }

    public void setInstruments(Instrument[] instruments) {
        this.instruments = instruments;
        if (instruments != null) {
            noRelatedSyms = new Integer(instruments.length);
        }
    }

    @Override
    public Instrument addInstrument() {
        Instrument group = new Instrument50(new FragmentContext(sessionCharset, messageEncoding, validateRequired));
        List<Instrument> groups = new ArrayList<Instrument>();
        if (instruments != null && instruments.length > 0) {
            groups = new ArrayList<Instrument>(Arrays.asList(instruments));
        }
        groups.add(group);
        instruments = groups.toArray(new Instrument[groups.size()]);
        noRelatedSyms = new Integer(instruments.length);

        return group;
    }

    @Override
    public Instrument deleteInstrument(int index) {
        Instrument result = null;

        if (instruments != null && instruments.length > 0 && instruments.length > index) {
            List<Instrument> groups = new ArrayList<Instrument>(Arrays.asList(instruments));
            result = groups.remove(index);
            instruments = groups.toArray(new Instrument[groups.size()]);
            if (instruments.length > 0) {
                noRelatedSyms = new Integer(instruments.length);
            } else {
                instruments = null;
                noRelatedSyms = null;
            }
        }

        return result;
    }

    @Override
    public int clearInstruments() {
        int result = 0;
        if (instruments != null && instruments.length > 0) {
            result = instruments.length;
            instruments = null;
            noRelatedSyms = null;
        }

        return result;
    }

    @XmlElementRef
    public InstrumentType50[] getInstrumentsType() {
        if (instruments != null && instruments.length > 0) {
            instrumentsType = new InstrumentType50[instruments.length];
            for (int i = 0; i < instruments.length; i++) {
                instrumentsType[i] = new InstrumentType50((Instrument50) instruments[i]);
            }
        }

        return instrumentsType;
    }

    public void setInstrumentsType(InstrumentType50[] instrumentsType) {
        this.instrumentsType = instrumentsType;
        if (instrumentsType != null && instrumentsType.length > 0) {
            instruments = new Instrument[instrumentsType.length];
            for (int i = 0; i < instrumentsType.length; i++) {
                instruments[i] = instrumentsType[i].getInstrument();
            }
            noRelatedSyms = new Integer(instrumentsType.length);
        }
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
    public Integer getNoLinesOfText() {
        return noLinesOfText;
    }

    @Override
    public void setNoLinesOfText(Integer noLinesOfText) {
        this.noLinesOfText = noLinesOfText;
        if (noLinesOfText != null) {
            linesOfTextGroups = new LinesOfTextGroup[noLinesOfText.intValue()];
            FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, validateRequired);
            for (int i = 0; i < linesOfTextGroups.length; i++) {
                linesOfTextGroups[i] = new LinesOfTextGroup50(context);
            }
        }
    }

    @XmlElementRef
    @Override
    public LinesOfTextGroup[] getLinesOfTextGroups() {
        return linesOfTextGroups;
    }

    public void setLinesOfTextGroups(LinesOfTextGroup[] linesOfTextGroups) {
        this.linesOfTextGroups = linesOfTextGroups;
        if (linesOfTextGroups != null) {
            noLinesOfText = new Integer(linesOfTextGroups.length);
        }
    }

    @Override
    public LinesOfTextGroup addLinesOfTextGroup() {
        LinesOfTextGroup group = new LinesOfTextGroup50(new FragmentContext(sessionCharset, messageEncoding, validateRequired));
        List<LinesOfTextGroup> groups = new ArrayList<LinesOfTextGroup>();
        if (linesOfTextGroups != null && linesOfTextGroups.length > 0) {
            groups = new ArrayList<LinesOfTextGroup>(Arrays.asList(linesOfTextGroups));
        }
        groups.add(group);
        linesOfTextGroups = groups.toArray(new LinesOfTextGroup[groups.size()]);
        noLinesOfText = new Integer(linesOfTextGroups.length);

        return group;
    }

    @Override
    public LinesOfTextGroup deleteLinesOfTextGroup(int index) {
        LinesOfTextGroup result = null;
        if (linesOfTextGroups != null && linesOfTextGroups.length > 0 && linesOfTextGroups.length > index) {
            List<LinesOfTextGroup> groups = new ArrayList<LinesOfTextGroup>(Arrays.asList(linesOfTextGroups));
            result = groups.remove(index);
            linesOfTextGroups = groups.toArray(new LinesOfTextGroup[groups.size()]);
            if (linesOfTextGroups.length > 0) {
                noLinesOfText = new Integer(linesOfTextGroups.length);
            } else {
                linesOfTextGroups = null;
                noLinesOfText = null;
            }
        }

        return result;
    }

    @Override
    public int clearLinesOfTextGroups() {
        int result = 0;
        if (linesOfTextGroups != null && linesOfTextGroups.length > 0) {
            result = linesOfTextGroups.length;
            linesOfTextGroups = null;
            noLinesOfText = null;
        }

        return result;
    }

    @XmlAttribute(name = "URL")
    @Override
    public String getUrlLink() {
        return urlLink;
    }

    @Override
    public void setUrlLink(String urlLink) {
        this.urlLink = urlLink;
    }

    @XmlAttribute(name = "RawDataLength")
    @Override
    public Integer getRawDataLength() {
        return rawDataLength;
    }

    @Override
    public void setRawDataLength(Integer rawDataLength) {
        this.rawDataLength = rawDataLength;
    }

    @XmlAttribute(name = "RawData")
    @Override
    public byte[] getRawData() {
        return rawData;
    }

    @Override
    public void setRawData(byte[] rawData) {
        this.rawData = rawData;
        if (rawDataLength == null) {
            rawDataLength = new Integer(rawData.length);
        }
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">
    
    @Override
    protected void setFragmentCompTagValue(Tag tag, ByteBuffer message)
    throws BadFormatMsgException, InvalidMsgException, TagNotPresentException {

        FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, validateRequired);
        if (ROUTING_ID_GROUP_TAGS.contains(tag.tagNum)) {
            if (noRoutingIDs != null && noRoutingIDs.intValue() > 0) {
                message.reset();
                routingIDGroups = new RoutingIDGroup[noRoutingIDs.intValue()];
                for (int i = 0; i < noRoutingIDs.intValue(); i++) {
                    RoutingIDGroup group = new RoutingIDGroup50(context);
                    group.decode(message);
                    routingIDGroups[i] = group;
                }
            }
        }
        if (INSTRUMENT_COMP_TAGS.contains(tag.tagNum)) {
            if (noRelatedSyms != null && noRelatedSyms.intValue() > 0) {
                message.reset();
                instruments = new Instrument[noRelatedSyms.intValue()];
                for (int i = 0; i < noRelatedSyms.intValue(); i++) {
                    Instrument group = new Instrument50(context);
                    group.decode(message);
                    instruments[i] = group;
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
        if (UNDLY_INSTRUMENT_COMP_TAGS.contains(tag.tagNum)) {
            if (noUnderlyings != null && noUnderlyings.intValue() > 0) {
                message.reset();
                underlyingInstruments = new UnderlyingInstrument[noUnderlyings.intValue()];
                for (int i = 0; i < noUnderlyings.intValue(); i++) {
                    UnderlyingInstrument group = new UnderlyingInstrument50(context);
                    group.decode(message);
                    underlyingInstruments[i] = group;
                }
            }
        }
        if (LINES_OF_TEXT_GROUP_TAGS.contains(tag.tagNum)) {
            if (noLinesOfText != null && noLinesOfText.intValue() > 0) {
                message.reset();
                linesOfTextGroups = new LinesOfTextGroup[noLinesOfText.intValue()];
                for (int i = 0; i < noLinesOfText.intValue(); i++) {
                    LinesOfTextGroup group = new LinesOfTextGroup50(context);
                    group.decode(message);
                    linesOfTextGroups[i] = group;
                }
            }
        }
    }

    @Override
    protected String getUnsupportedTagMessage() {
        return "This tag is not supported in [NewsMsg] message version [5.0].";
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
