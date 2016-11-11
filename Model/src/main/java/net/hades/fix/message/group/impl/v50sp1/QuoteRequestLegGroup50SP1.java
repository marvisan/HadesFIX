/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * QuoteRequestLegGroup50SP1.java
 *
 * $Id: QuoteRequestLegGroup50SP1.java,v 1.9 2011-04-14 23:44:51 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v50sp1;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.comp.impl.v50sp1.LegStipulations50SP1;
import net.hades.fix.message.group.LegStipulationsGroup;
import net.hades.fix.message.struct.Tag;
import net.hades.fix.message.type.LegSwapType;

import java.nio.ByteBuffer;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import net.hades.fix.message.comp.InstrumentLeg;
import net.hades.fix.message.comp.LegBenchmarkCurveData;
import net.hades.fix.message.comp.LegStipulations;
import net.hades.fix.message.comp.NestedParties;
import net.hades.fix.message.comp.impl.v50sp1.InstrumentLeg50SP1;
import net.hades.fix.message.comp.impl.v50sp1.LegBenchmarkCurveData50SP1;
import net.hades.fix.message.comp.impl.v50sp1.NestedParties50SP1;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.group.NestedPartyGroup;
import net.hades.fix.message.group.QuoteRequestLegGroup;
import net.hades.fix.message.xml.codec.jaxb.adapter.FixDateAdapter;

/**
 * FIX 5.0SP1 implementation of QuoteRequestLegGroup group.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.9 $
 * @created 06/04/2009, 12:16:48 PM
 */
@XmlRootElement(name="Leg")
@XmlType(propOrder = {"instrumentLeg", "legStipulationsGroups", "nestedPartyIDGroups", "legBenchmarkCurveData"})
@XmlAccessorType(XmlAccessType.NONE)
public class QuoteRequestLegGroup50SP1 extends QuoteRequestLegGroup {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> ALL_TAGS;

    protected static final Set<Integer> START_COMP_TAGS;

    protected static final Set<Integer> INSTRUMENT_LEG_COMP_TAGS = new InstrumentLeg50SP1().getFragmentAllTags();
    protected static final Set<Integer> LEG_STIPULATIONS_COMP_TAGS = new LegStipulations50SP1().getFragmentAllTags();
    protected static final Set<Integer> NESTED_PARTIES_COMP_TAGS = new NestedParties50SP1().getFragmentAllTags();
    protected static final Set<Integer> LEG_BENCHMARK_CURVE_COMP_TAGS = new LegBenchmarkCurveData50SP1().getFragmentAllTags();

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">

     static {
        ALL_TAGS = new HashSet<Integer>(TAGS);
        ALL_TAGS.addAll(INSTRUMENT_LEG_COMP_TAGS);
        ALL_TAGS.addAll(LEG_STIPULATIONS_COMP_TAGS);
        ALL_TAGS.addAll(NESTED_PARTIES_COMP_TAGS);
        ALL_TAGS.addAll(LEG_BENCHMARK_CURVE_COMP_TAGS);
        START_COMP_TAGS = new HashSet<Integer>(INSTRUMENT_LEG_COMP_TAGS);
        START_COMP_TAGS.addAll(LEG_STIPULATIONS_COMP_TAGS);
        START_COMP_TAGS.addAll(NESTED_PARTIES_COMP_TAGS);
        START_COMP_TAGS.addAll(LEG_BENCHMARK_CURVE_COMP_TAGS);
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public QuoteRequestLegGroup50SP1() {
        super();
    }

    public QuoteRequestLegGroup50SP1(FragmentContext context) {
        super(context);
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Public Methods">

    @Override
    public Set<Integer> getFragmentAllTags() {
        return ALL_TAGS;
    }

    // ACCESSOR METHODS
    //////////////////////////////////////////

    @XmlElementRef
    @Override
    public InstrumentLeg getInstrumentLeg() {
        return instrumentLeg;
    }

    @Override
    public void setInstrumentLeg() {
        instrumentLeg = new InstrumentLeg50SP1(new FragmentContext(sessionCharset, messageEncoding, validateRequired));
    }

    @Override
    public void clearInstrumentLeg() {
        instrumentLeg = null;
    }

    public void setInstrumentLeg(InstrumentLeg instrumentLeg) {
        this.instrumentLeg = instrumentLeg;
    }

    @XmlAttribute(name = "Qty")
    @Override
    public Double getLegQty() {
        return legQty;
    }

    @Override
    public void setLegQty(Double legQty) {
        this.legQty = legQty;
    }

    @XmlAttribute(name = "OrdQty")
    @Override
    public Double getLegOrderQty() {
        return legOrderQty;
    }

    @Override
    public void setLegOrderQty(Double legOrderQty) {
        this.legOrderQty = legOrderQty;
    }

    @XmlAttribute(name = "SwapTyp")
    @Override
    public LegSwapType getLegSwapType() {
        return legSwapType;
    }

    @Override
    public void setLegSwapType(LegSwapType legSwapType) {
        this.legSwapType = legSwapType;
    }

    @XmlAttribute(name = "SettlTyp")
    @Override
    public String getLegSettlType() {
        return legSettlType;
    }

    @Override
    public void setLegSettlType(String legSettlType) {
        this.legSettlType = legSettlType;
    }

    @XmlAttribute(name = "SettlDt")
    @XmlJavaTypeAdapter(FixDateAdapter.class)
    @Override
    public Date getLegSettlDate() {
        return legSettlDate;
    }

    @Override
    public void setLegSettlDate(Date legSettlDate) {
        this.legSettlDate = legSettlDate;
    }

    @Override
    public LegStipulations getLegStipulations() {
        return legStipulations;
    }

    @Override
    public void setLegStipulations() {
        this.legStipulations = new LegStipulations50SP1(new FragmentContext(sessionCharset, messageEncoding, validateRequired));
    }

    @Override
    public void clearLegStipulations() {
        this.legStipulations = null;
    }

    @XmlElementRef
    public LegStipulationsGroup[] getLegStipulationsGroups() {
        return legStipulations == null ? null : legStipulations.getLegStipulationsGroups();
    }

    public void setLegStipulationsGroups(LegStipulationsGroup[] legStipulationsGroups) {
        if (legStipulationsGroups != null) {
            if (legStipulations == null) {
                setLegStipulations();
            }
            ((LegStipulations50SP1) legStipulations).setLegStipulationsGroups(legStipulationsGroups);
        }
    }

    @Override
    public NestedParties getNestedParties() {
        return nestedParties;
    }

    @Override
    public void setNestedParties() {
        this.nestedParties = new NestedParties50SP1(new FragmentContext(sessionCharset, messageEncoding, validateRequired));
    }

    @Override
    public void clearNestedParties() {
        this.nestedParties = null;
    }

    @XmlElementRef
    public NestedPartyGroup[] getNestedPartyIDGroups() {
        return nestedParties == null ? null : nestedParties.getNestedPartyIDGroups();
    }

    public void setNestedPartyIDGroups(NestedPartyGroup[] nestedPartyIDGroups) {
        if (nestedPartyIDGroups != null) {
            if (nestedParties == null) {
                setNestedParties();
            }
            ((NestedParties50SP1) nestedParties).setNestedPartyIDGroups(nestedPartyIDGroups);
        }
    }

    @XmlElementRef
    @Override
    public LegBenchmarkCurveData getLegBenchmarkCurveData() {
        return legBenchmarkCurveData;
    }

    @Override
    public void setLegBenchmarkCurveData() {
        this.legBenchmarkCurveData = new LegBenchmarkCurveData50SP1(new FragmentContext(sessionCharset, messageEncoding, validateRequired));
    }

    @Override
    public void clearLegBenchmarkCurveData() {
        this.legBenchmarkCurveData = null;
    }

    public void setLegBenchmarkCurveData(LegBenchmarkCurveData legBenchmarkCurveData) {
        this.legBenchmarkCurveData = legBenchmarkCurveData;
    }

    @XmlAttribute(name = "RefID")
    @Override
    public String getLegRefID() {
        return legRefID;
    }

    @Override
    public void setLegRefID(String legRefID) {
        this.legRefID = legRefID;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

    @Override
    protected void setFragmentCompTagValue(Tag tag, ByteBuffer message)
    throws BadFormatMsgException, InvalidMsgException, TagNotPresentException {
        
        if (INSTRUMENT_LEG_COMP_TAGS.contains(tag.tagNum)) {
            if (instrumentLeg == null) {
                instrumentLeg = new InstrumentLeg50SP1(new FragmentContext(sessionCharset, messageEncoding, validateRequired));
            }
            instrumentLeg.decode(tag, message);
        }
        if (LEG_STIPULATIONS_COMP_TAGS.contains(tag.tagNum)) {
            if (legStipulations == null) {
                legStipulations = new LegStipulations50SP1(new FragmentContext(sessionCharset, messageEncoding, validateRequired));
            }
            legStipulations.decode(tag, message);
        }
        if (NESTED_PARTIES_COMP_TAGS.contains(tag.tagNum)) {
            if (nestedParties == null) {
                nestedParties = new NestedParties50SP1(new FragmentContext(sessionCharset, messageEncoding, validateRequired));
            }
            nestedParties.decode(tag, message);
        }
        if (LEG_BENCHMARK_CURVE_COMP_TAGS.contains(tag.tagNum)) {
            if (legBenchmarkCurveData == null) {
                legBenchmarkCurveData = new LegBenchmarkCurveData50SP1(new FragmentContext(sessionCharset, messageEncoding, validateRequired));
            }
            legBenchmarkCurveData.decode(tag, message);
        }
    }

    @Override
    protected String getUnsupportedTagMessage() {
        return "This tag is not supported in [LegQuoteSymbolGroup] component version [5.0SP1].";
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
