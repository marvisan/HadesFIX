/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * LegOrdGroup44.java
 *
 * $Id: LegOrdGroup44.java,v 1.3 2011-09-09 08:05:13 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v44;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.comp.InstrumentLeg;
import net.hades.fix.message.comp.impl.v44.InstrumentLeg44;
import net.hades.fix.message.comp.impl.v44.LegStipulations44;
import net.hades.fix.message.comp.impl.v44.NestedParties44;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.group.LegAllocGroup;
import net.hades.fix.message.group.LegStipulationsGroup;
import net.hades.fix.message.struct.Tag;
import net.hades.fix.message.util.MsgUtil;

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

import net.hades.fix.message.comp.LegStipulations;
import net.hades.fix.message.comp.NestedParties;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.group.LegOrdGroup;
import net.hades.fix.message.group.NestedPartyGroup;
import net.hades.fix.message.type.CoveredOrUncovered;
import net.hades.fix.message.type.LegSwapType;
import net.hades.fix.message.type.PositionEffect;
import net.hades.fix.message.type.SessionRejectReason;
import net.hades.fix.message.type.TagNum;
import net.hades.fix.message.util.TagEncoder;
import net.hades.fix.message.xml.codec.jaxb.adapter.FixDateAdapter;

/**
 * FIX 4.4 implementation of LegOrdGroup group.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.3 $
 * @created 29/04/2009, 6:46:57 PM
 */
@XmlRootElement(name = "Ord")
@XmlType(propOrder = {"instrumentLeg", "legStipulationsGroups", "legAllocGroups", "nestedPartyIDGroups"})
@XmlAccessorType(XmlAccessType.NONE)
public class LegOrdGroup44 extends LegOrdGroup {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> TAGS_V44 = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.LegQty.getValue(),
        TagNum.LegSwapType.getValue(),
        TagNum.LegAllocID.getValue(),
        TagNum.NoLegAllocs.getValue(),
        TagNum.LegPositionEffect.getValue(),
        TagNum.LegCoveredOrUncovered.getValue(),
        TagNum.LegRefID.getValue(),
        TagNum.LegPrice.getValue(),
        TagNum.LegSettlType.getValue(),
        TagNum.LegSettlDate.getValue(),
        TagNum.LegOrderQty.getValue(),
        TagNum.LegVolatility.getValue(),
        TagNum.LegDividendYield.getValue(),
        TagNum.LegCurrencyRatio.getValue(),
        TagNum.LegExecInst.getValue()
    }));

    protected static final Set<Integer> ALL_TAGS;

    protected static final Set<Integer> START_COMP_TAGS;

    protected static final Set<Integer> INSTRUMENT_LEG_COMP_TAGS = new InstrumentLeg44().getFragmentAllTags();
    protected static final Set<Integer> LEG_STIPULATIONS_COMP_TAGS = new LegStipulations44().getFragmentAllTags();
    protected static final Set<Integer> LEG_ALLOC_GROUP_TAGS = new LegAllocGroup44().getFragmentAllTags();
    protected static final Set<Integer> NESTED_PARTIES_COMP_TAGS = new NestedParties44().getFragmentAllTags();

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">

    static {
        ALL_TAGS = new HashSet<Integer>(TAGS_V44);
        ALL_TAGS.addAll(INSTRUMENT_LEG_COMP_TAGS);
        ALL_TAGS.addAll(LEG_STIPULATIONS_COMP_TAGS);
        ALL_TAGS.addAll(LEG_ALLOC_GROUP_TAGS);
        ALL_TAGS.addAll(NESTED_PARTIES_COMP_TAGS);
        START_COMP_TAGS = new HashSet<Integer>(INSTRUMENT_LEG_COMP_TAGS);
        START_COMP_TAGS.addAll(LEG_STIPULATIONS_COMP_TAGS);
        START_COMP_TAGS.addAll(LEG_ALLOC_GROUP_TAGS);
        START_COMP_TAGS.addAll(NESTED_PARTIES_COMP_TAGS);
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">

    protected Set<Integer> STANDARD_SECURED_TAGS = ALL_TAGS;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public LegOrdGroup44() {
        super();
        SECURED_TAGS = STANDARD_SECURED_TAGS;
    }

    public LegOrdGroup44(FragmentContext context) {
        super(context);
        SECURED_TAGS = STANDARD_SECURED_TAGS;
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
        FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired);
        instrumentLeg = new InstrumentLeg44(context);
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
        FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired);
        this.legStipulations = new LegStipulations44(context);
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
            ((LegStipulations44) legStipulations).setLegStipulationsGroups(legStipulationsGroups);
        }
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
                legAllocGroups[i] = new LegAllocGroup44(new FragmentContext(sessionCharset, messageEncoding, validateRequired));
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
        LegAllocGroup group = new LegAllocGroup44(new FragmentContext(sessionCharset, messageEncoding, validateRequired));
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
    public NestedParties getNestedParties() {
        return nestedParties;
    }

    @Override
    public void setNestedParties() {
        FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired);
        this.nestedParties = new NestedParties44(context);
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
            ((NestedParties44) nestedParties).setNestedPartyIDGroups(nestedPartyIDGroups);
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

    @XmlAttribute(name = "Px")
    @Override
    public Double getLegPrice() {
        return legPrice;
    }

    @Override
    public void setLegPrice(Double legPrice) {
        this.legPrice = legPrice;
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

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

    @Override
    protected byte[] encodeFragmentSecured(boolean secured) throws TagNotPresentException, BadFormatMsgException {
        byte[] result = new byte[0];
        ByteArrayOutputStream bao = new ByteArrayOutputStream();

        try {
            bao.write(instrumentLeg.encode(getMsgSecureTypeForFlag(secured)));
            if (MsgUtil.isTagInList(TagNum.LegQty, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.LegQty, legQty);
            }
            if (legSwapType != null && MsgUtil.isTagInList(TagNum.LegSwapType, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.LegSwapType, legSwapType.getValue());
            }
            if (legStipulations != null) {
                bao.write(legStipulations.encode(getMsgSecureTypeForFlag(secured)));
            }
            if (noLegAllocs != null && MsgUtil.isTagInList(TagNum.NoLegAllocs, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.NoLegAllocs, noLegAllocs);
                if (legAllocGroups != null && legAllocGroups.length == noLegAllocs.intValue()) {
                    for (int i = 0; i < noLegAllocs.intValue(); i++) {
                        if (legAllocGroups[i] != null) {
                            bao.write(legAllocGroups[i].encode(getMsgSecureTypeForFlag(secured)));
                        }
                    }
                } else {
                    String error = "LegAllocGroups field has been set but there is no data or the number of groups does not match.";
                    LOGGER.severe(error);
                    throw new BadFormatMsgException(SessionRejectReason.IncorrectCountForGroups, TagNum.NoLegAllocs.getValue(), error);
                }
            }
            if (legPositionEffect != null && MsgUtil.isTagInList(TagNum.LegPositionEffect, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.LegPositionEffect, legPositionEffect.getValue());
            }
            if (legCoveredOrUncovered != null && MsgUtil.isTagInList(TagNum.LegCoveredOrUncovered, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.LegCoveredOrUncovered, legCoveredOrUncovered.getValue());
            }
            if (nestedParties != null) {
                bao.write(nestedParties.encode(getMsgSecureTypeForFlag(secured)));
            }
            if (MsgUtil.isTagInList(TagNum.LegRefID, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.LegRefID, legRefID);
            }
            if (MsgUtil.isTagInList(TagNum.LegPrice, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.LegPrice, legPrice);
            }
            if (MsgUtil.isTagInList(TagNum.LegSettlType, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.LegSettlType, legSettlType);
            }
            if (MsgUtil.isTagInList(TagNum.LegSettlDate, SECURED_TAGS, secured)) {
                TagEncoder.encodeDate(bao, TagNum.LegSettlDate, legSettlDate);
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
        if (INSTRUMENT_LEG_COMP_TAGS.contains(tag.tagNum)) {
            if (instrumentLeg == null) {
                instrumentLeg = new InstrumentLeg44(context);
            }
            instrumentLeg.decode(tag, message);
        }
        if (LEG_STIPULATIONS_COMP_TAGS.contains(tag.tagNum)) {
            if (legStipulations == null) {
                legStipulations = new LegStipulations44(context);
            }
            legStipulations.decode(tag, message);
        }
        if (LEG_ALLOC_GROUP_TAGS.contains(tag.tagNum)) {
            if (noLegAllocs != null && noLegAllocs.intValue() > 0) {
                message.reset();
                legAllocGroups = new LegAllocGroup44[noLegAllocs.intValue()];
                for (int i = 0; i < noLegAllocs.intValue(); i++) {
                    LegAllocGroup group = new LegAllocGroup44(context);
                    group.decode(message);
                    legAllocGroups[i] = group;
                }
            }
        }
        if (NESTED_PARTIES_COMP_TAGS.contains(tag.tagNum)) {
            if (nestedParties == null) {
                nestedParties = new NestedParties44(context);
            }
            nestedParties.decode(tag, message);
        }
    }

    @Override
    protected String getUnsupportedTagMessage() {
        return "This tag is not supported in [LegOrdGroup] group version [4.4].";
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
