/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * InstrmtLegExecGroup50SP2.java
 *
 * $Id: InstrmtLegExecGroup50SP2.java,v 1.2 2011-04-14 23:44:32 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v50sp2;

import net.hades.fix.message.struct.Tag;

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
import net.hades.fix.message.comp.InstrumentLeg;
import net.hades.fix.message.comp.LegStipulations;
import net.hades.fix.message.comp.NestedParties3;
import net.hades.fix.message.comp.impl.v50sp2.InstrumentLeg50SP2;
import net.hades.fix.message.comp.impl.v50sp2.LegStipulations50SP2;
import net.hades.fix.message.comp.impl.v50sp2.NestedParties350SP2;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.group.InstrmtLegExecGroup;
import net.hades.fix.message.group.LegAllocGroup;
import net.hades.fix.message.group.LegStipulationsGroup;
import net.hades.fix.message.group.Nested3PartyGroup;
import net.hades.fix.message.type.CoveredOrUncovered;
import net.hades.fix.message.type.Currency;
import net.hades.fix.message.type.LegSwapType;
import net.hades.fix.message.type.PositionEffect;
import net.hades.fix.message.xml.codec.jaxb.adapter.FixDateAdapter;


/**
 * FIX 5.0SP2 implementation of InstrmtLegExecGroup group.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 * @created 29/04/2009, 6:46:57 PM
 */
@XmlRootElement(name = "Exec")
@XmlType(propOrder = {"instrumentLeg", "legStipulationsGroups", "legAllocGroups", "nested3PartyIDGroups"})
@XmlAccessorType(XmlAccessType.NONE)
public class InstrmtLegExecGroup50SP2 extends InstrmtLegExecGroup {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> ALL_TAGS;

    protected static final Set<Integer> START_COMP_TAGS;

    protected static final Set<Integer> INSTRUMENT_LEG_COMP_TAGS = new InstrumentLeg50SP2().getFragmentAllTags();
    protected static final Set<Integer> LEG_STIPULATIONS_COMP_TAGS = new LegStipulations50SP2().getFragmentAllTags();
    protected static final Set<Integer> LEG_ALLOC_GROUP_TAGS = new LegAllocGroup50SP2().getFragmentAllTags();
    protected static final Set<Integer> NESTED_PARTIES3_COMP_TAGS = new NestedParties350SP2().getFragmentAllTags();

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">

    static {
        ALL_TAGS = new HashSet<Integer>(TAGS);
        ALL_TAGS.addAll(INSTRUMENT_LEG_COMP_TAGS);
        ALL_TAGS.addAll(LEG_STIPULATIONS_COMP_TAGS);
        ALL_TAGS.addAll(LEG_ALLOC_GROUP_TAGS);
        ALL_TAGS.addAll(NESTED_PARTIES3_COMP_TAGS);
        START_COMP_TAGS = new HashSet<Integer>(INSTRUMENT_LEG_COMP_TAGS);
        START_COMP_TAGS.addAll(LEG_STIPULATIONS_COMP_TAGS);
        START_COMP_TAGS.addAll(LEG_ALLOC_GROUP_TAGS);
        START_COMP_TAGS.addAll(NESTED_PARTIES3_COMP_TAGS);
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public InstrmtLegExecGroup50SP2() {
        super();
    }

    public InstrmtLegExecGroup50SP2(FragmentContext context) {
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
        FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, validateRequired);
        instrumentLeg = new InstrumentLeg50SP2(context);
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

    @Override
    public LegStipulations getLegStipulations() {
        return legStipulations;
    }

    @Override
    public void setLegStipulations() {
        FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, validateRequired);
        this.legStipulations = new LegStipulations50SP2(context);
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
            ((LegStipulations50SP2) legStipulations).setLegStipulationsGroups(legStipulationsGroups);
        }
    }

    @XmlAttribute(name = "LegAllocID")
    @Override
    public String getLegAllocID() {
        return legAllocID;
    }

    @Override
    public void setLegAllocID(String legAllocID) {
        this.legAllocID = legAllocID;
    }

    @Override
    public Integer getNoLegAllocs() {
        return noLegAllocs;
    }

    @Override
    public void setNoLegAllocs(Integer noLegAllocs) {
        this.noLegAllocs = noLegAllocs;
        if (noLegAllocs != null) {
            legAllocGroups = new LegAllocGroup[noLegAllocs.intValue()];
            for (int i = 0; i < legAllocGroups.length; i++) {
                legAllocGroups[i] = new LegAllocGroup50SP2(new FragmentContext(sessionCharset, messageEncoding, validateRequired));
            }
        }
    }

    @XmlElementRef
    @Override
    public LegAllocGroup[] getLegAllocGroups() {
        return legAllocGroups;
    }

    public void setLegAllocGroups(LegAllocGroup[] legAllocGroups) {
        this.legAllocGroups = legAllocGroups;
        if (legAllocGroups != null) {
            noLegAllocs = new Integer(legAllocGroups.length);
        }
    }

    @Override
    public LegAllocGroup addLegAllocGroup() {
        LegAllocGroup group = new LegAllocGroup50SP2(new FragmentContext(sessionCharset, messageEncoding, validateRequired));
        List<LegAllocGroup> groups = new ArrayList<LegAllocGroup>();
        if (legAllocGroups != null && legAllocGroups.length > 0) {
            groups = new ArrayList<LegAllocGroup>(Arrays.asList(legAllocGroups));
        }
        groups.add(group);
        legAllocGroups = groups.toArray(new LegAllocGroup[groups.size()]);
        noLegAllocs = new Integer(legAllocGroups.length);

        return group;
    }

    @Override
    public LegAllocGroup deleteLegAllocGroup(int index) {
        LegAllocGroup result = null;
        if (legAllocGroups != null && legAllocGroups.length > 0 && legAllocGroups.length > index) {
            List<LegAllocGroup> groups = new ArrayList<LegAllocGroup>(Arrays.asList(legAllocGroups));
            result = groups.remove(index);
            legAllocGroups = groups.toArray(new LegAllocGroup[groups.size()]);
            if (legAllocGroups.length > 0) {
                noLegAllocs = new Integer(legAllocGroups.length);
            } else {
                legAllocGroups = null;
                noLegAllocs = null;
            }
        }

        return result;
    }

    @Override
    public int clearLegAllocGroups() {
        int result = 0;
        if (legAllocGroups != null && legAllocGroups.length > 0) {
            result = legAllocGroups.length;
            legAllocGroups = null;
            noLegAllocs = null;
        }

        return result;
    }

    @XmlAttribute(name = "PosEfct")
    @Override
    public PositionEffect getLegPositionEffect() {
        return legPositionEffect;
    }

    @Override
    public void setLegPositionEffect(PositionEffect legPositionEffect) {
        this.legPositionEffect = legPositionEffect;
    }

    @XmlAttribute(name = "Cover")
    @Override
    public CoveredOrUncovered getLegCoveredOrUncovered() {
        return legCoveredOrUncovered;
    }

    @Override
    public void setLegCoveredOrUncovered(CoveredOrUncovered legCoveredOrUncovered) {
        this.legCoveredOrUncovered = legCoveredOrUncovered;
    }

    @Override
    public NestedParties3 getNestedParties3() {
        return nestedParties3;
    }

    @Override
    public void setNestedParties3() {
        FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, validateRequired);
        this.nestedParties3 = new NestedParties350SP2(context);
    }

    @Override
    public void clearNestedParties3() {
        this.nestedParties3 = null;
    }

    @XmlElementRef
    public Nested3PartyGroup[] getNested3PartyIDGroups() {
        return nestedParties3 == null ? null : nestedParties3.getNested3PartyIDGroups();
    }

    public void setNested3PartyIDGroups(Nested3PartyGroup[] nested3PartyIDGroups) {
        if (nested3PartyIDGroups != null) {
            if (nestedParties3 == null) {
                setNestedParties3();
            }
            ((NestedParties350SP2) nestedParties3).setNested3PartyIDGroups(nested3PartyIDGroups);
        }
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

    @XmlAttribute(name = "LastPx")
    @Override
    public Double getLegLastPx() {
        return legLastPx;
    }

    @Override
    public void setLegLastPx(Double legLastPx) {
        this.legLastPx = legLastPx;
    }

    @XmlAttribute(name = "SettlCcy")
    @Override
    public Currency getLegSettlCurrency() {
        return legSettlCurrency;
    }

    @Override
    public void setLegSettlCurrency(Currency legSettlCurrency) {
        this.legSettlCurrency = legSettlCurrency;
    }

    @XmlAttribute(name = "LegLastFwdPnts")
    @Override
    public Double getLegLastForwardPoints() {
        return legLastForwardPoints;
    }

    @Override
    public void setLegLastForwardPoints(Double legLastForwardPoints) {
        this.legLastForwardPoints = legLastForwardPoints;
    }

    @XmlAttribute(name = "LegCalcCcyLastQty")
    @Override
    public Double getLegCalculatedCcyLastQty() {
        return legCalculatedCcyLastQty;
    }

    @Override
    public void setLegCalculatedCcyLastQty(Double legCalculatedCcyLastQty) {
        this.legCalculatedCcyLastQty = legCalculatedCcyLastQty;
    }

    @XmlAttribute(name = "LegGrossTrdAmt")
    @Override
    public Double getLegGrossTradeAmt() {
        return legGrossTradeAmt;
    }

    @Override
    public void setLegGrossTradeAmt(Double legGrossTradeAmt) {
        this.legGrossTradeAmt = legGrossTradeAmt;
    }

    @XmlAttribute(name = "LegVolatility")
    @Override
    public Double getLegVolatility() {
        return legVolatility;
    }

    @Override
    public void setLegVolatility(Double legVolatility) {
        this.legVolatility = legVolatility;
    }

    @XmlAttribute(name = "LegDividendYield")
    @Override
    public Double getLegDividendYield() {
        return legDividendYield;
    }

    @Override
    public void setLegDividendYield(Double legDividendYield) {
        this.legDividendYield = legDividendYield;
    }

    @XmlAttribute(name = "LegCurrencyRatio")
    @Override
    public Double getLegCurrencyRatio() {
        return legCurrencyRatio;
    }

    @Override
    public void setLegCurrencyRatio(Double legCurrencyRatio) {
        this.legCurrencyRatio = legCurrencyRatio;
    }

    @XmlAttribute(name = "LegExecInst")
    @Override
    public String getLegExecInst() {
        return legExecInst;
    }

    @Override
    public void setLegExecInst(String legExecInst) {
        this.legExecInst = legExecInst;
    }

    @XmlAttribute(name = "LastQty")
    @Override
    public Double getLegLastQty() {
        return legLastQty;
    }

    @Override
    public void setLegLastQty(Double legLastQty) {
        this.legLastQty = legLastQty;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

    @Override
    protected void setFragmentCompTagValue(Tag tag, ByteBuffer message)
    throws BadFormatMsgException, InvalidMsgException, TagNotPresentException {
        FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, validateRequired);
        if (INSTRUMENT_LEG_COMP_TAGS.contains(tag.tagNum)) {
            if (instrumentLeg == null) {
                instrumentLeg = new InstrumentLeg50SP2(context);
            }
            instrumentLeg.decode(tag, message);
        }
        if (LEG_STIPULATIONS_COMP_TAGS.contains(tag.tagNum)) {
            if (legStipulations == null) {
                legStipulations = new LegStipulations50SP2(context);
            }
            legStipulations.decode(tag, message);
        }
        if (LEG_ALLOC_GROUP_TAGS.contains(tag.tagNum)) {
            if (noLegAllocs != null && noLegAllocs.intValue() > 0) {
                message.reset();
                legAllocGroups = new LegAllocGroup50SP2[noLegAllocs.intValue()];
                for (int i = 0; i < noLegAllocs.intValue(); i++) {
                    LegAllocGroup group = new LegAllocGroup50SP2(context);
                    group.decode(message);
                    legAllocGroups[i] = group;
                }
            }
        }
        if (NESTED_PARTIES3_COMP_TAGS.contains(tag.tagNum)) {
            if (nestedParties3 == null) {
                nestedParties3 = new NestedParties350SP2(context);
            }
            nestedParties3.decode(tag, message);
        }
        
    }

    @Override
    protected String getUnsupportedTagMessage() {
        return "This tag is not supported in [InstrmtLegExecGroup] group version [5.0SP2].";
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
