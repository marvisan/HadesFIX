/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * BaseTradingRules50SP1.java
 *
 * $Id: BaseTradingRules50SP1.java,v 1.2 2011-04-20 00:32:34 vrotaru Exp $
 */
package net.hades.fix.message.comp.impl.v50sp1;

import net.hades.fix.message.struct.Tag;
import net.hades.fix.message.type.ImpliedMarketIndicator;
import net.hades.fix.message.type.PriceType;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.comp.BaseTradingRules;
import net.hades.fix.message.comp.PriceLimits;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.group.LotTypeRuleGroup;
import net.hades.fix.message.group.TickRuleGroup;
import net.hades.fix.message.group.impl.v50sp1.LotTypeRuleGroup50SP1;
import net.hades.fix.message.group.impl.v50sp1.TickRuleGroup50SP1;
import net.hades.fix.message.type.Currency;
import net.hades.fix.message.type.ExpirationCycle;
import net.hades.fix.message.type.MultilegModel;
import net.hades.fix.message.type.MultilegPriceMethod;

/**
 * FIX 5.0SP1 implementation of BaseTradingRules component.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 * @created 02/01/2009, 12:07:02 PM
 */
@XmlRootElement(name="BaseTrdgRules")
@XmlType(propOrder = {"tickRuleGroups", "lotTypeRuleGroups", "priceLimits"})
@XmlAccessorType(XmlAccessType.NONE)
public class BaseTradingRules50SP1 extends BaseTradingRules {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> START_COMP_TAGS;

    protected static final Set<Integer> ALL_TAGS;

    protected static final Set<Integer> TICK_RULE_GROUP_TAGS = new TickRuleGroup50SP1().getFragmentAllTags();
    protected static final Set<Integer> LOT_TYPE_RULE_GROUP_TAGS = new LotTypeRuleGroup50SP1().getFragmentAllTags();
    protected static final Set<Integer> PRICE_LIMITS_COMP_TAGS = new PriceLimits50SP1().getFragmentAllTags();

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">

    static {
        ALL_TAGS = new HashSet<Integer>(TAGS);
        ALL_TAGS.addAll(TICK_RULE_GROUP_TAGS);
        ALL_TAGS.addAll(LOT_TYPE_RULE_GROUP_TAGS);
        ALL_TAGS.addAll(PRICE_LIMITS_COMP_TAGS);
        START_COMP_TAGS = new HashSet<Integer>(TICK_RULE_GROUP_TAGS);
        START_COMP_TAGS.addAll(LOT_TYPE_RULE_GROUP_TAGS);
        START_COMP_TAGS.addAll(PRICE_LIMITS_COMP_TAGS);
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public BaseTradingRules50SP1() {
        super();
    }

    public BaseTradingRules50SP1(FragmentContext context) {
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

    @Override
    public Integer getNoTickRules() {
        return noTickRules;
    }

    @Override
    public void setNoTickRules(Integer noTickRules) {
        this.noTickRules = noTickRules;
        if (noTickRules != null) {
            tickRuleGroups = new TickRuleGroup[noTickRules.intValue()];
            for (int i = 0; i < tickRuleGroups.length; i++) {
                tickRuleGroups[i] = new TickRuleGroup50SP1(new FragmentContext(sessionCharset, messageEncoding, validateRequired));
            }
        }
    }

    @XmlElementRef
    @Override
    public TickRuleGroup[] getTickRuleGroups() {
        return tickRuleGroups;
    }
    
    public void setTickRuleGroups(TickRuleGroup[] tickRuleGroups) {
        this.tickRuleGroups = tickRuleGroups;
        if (tickRuleGroups != null) {
            noTickRules = new Integer(tickRuleGroups.length);
        }
    }

    @Override
    public TickRuleGroup addTickRuleGroup() {
        TickRuleGroup group = new TickRuleGroup50SP1(new FragmentContext(sessionCharset, messageEncoding, validateRequired));
        List<TickRuleGroup> groups = new ArrayList<TickRuleGroup>();
        if (tickRuleGroups != null && tickRuleGroups.length > 0) {
            groups = new ArrayList<TickRuleGroup>(Arrays.asList(tickRuleGroups));
        }
        groups.add(group);
        tickRuleGroups = groups.toArray(new TickRuleGroup[groups.size()]);
        noTickRules = new Integer(tickRuleGroups.length);

        return group;
    }

    @Override
    public TickRuleGroup deleteTickRuleGroup(int index) {
        TickRuleGroup result = null;
        if (tickRuleGroups != null && tickRuleGroups.length > 0 && tickRuleGroups.length > index) {
            List<TickRuleGroup> groups = new ArrayList<TickRuleGroup>(Arrays.asList(tickRuleGroups));
            result = groups.remove(index);
            tickRuleGroups = groups.toArray(new TickRuleGroup[groups.size()]);
            if (tickRuleGroups.length > 0) {
                noTickRules = new Integer(tickRuleGroups.length);
            } else {
                tickRuleGroups = null;
                noTickRules = null;
            }
        }

        return result;
    }

    @Override
    public int clearTickRuleGroups() {
        int result = 0;
        if (tickRuleGroups != null && tickRuleGroups.length > 0) {
            result = tickRuleGroups.length;
            tickRuleGroups = null;
            noTickRules = null;
        }

        return result;
    }

    @Override
    public Integer getNoLotTypeRules() {
        return noLotTypeRules;
    }

    @Override
    public void setNoLotTypeRules(Integer noLotTypeRules) {
        this.noLotTypeRules = noLotTypeRules;
        if (noLotTypeRules != null) {
            lotTypeRuleGroups = new LotTypeRuleGroup[noLotTypeRules.intValue()];
            for (int i = 0; i < lotTypeRuleGroups.length; i++) {
                lotTypeRuleGroups[i] = new LotTypeRuleGroup50SP1(new FragmentContext(sessionCharset, messageEncoding, validateRequired));
            }
        }
    }

    @XmlElementRef
    @Override
    public LotTypeRuleGroup[] getLotTypeRuleGroups() {
        return lotTypeRuleGroups;
    }

    public void setLotTypeRuleGroups(LotTypeRuleGroup[] lotTypeRuleGroups) {
        this.lotTypeRuleGroups = lotTypeRuleGroups;
        if (lotTypeRuleGroups != null) {
            noLotTypeRules = new Integer(lotTypeRuleGroups.length);
        }
    }

    @Override
    public LotTypeRuleGroup addLotTypeRuleGroup() {
        LotTypeRuleGroup group = new LotTypeRuleGroup50SP1(new FragmentContext(sessionCharset, messageEncoding, validateRequired));
        List<LotTypeRuleGroup> groups = new ArrayList<LotTypeRuleGroup>();
        if (lotTypeRuleGroups != null && lotTypeRuleGroups.length > 0) {
            groups = new ArrayList<LotTypeRuleGroup>(Arrays.asList(lotTypeRuleGroups));
        }
        groups.add(group);
        lotTypeRuleGroups = groups.toArray(new LotTypeRuleGroup[groups.size()]);
        noLotTypeRules = new Integer(lotTypeRuleGroups.length);

        return group;
    }

    @Override
    public LotTypeRuleGroup deleteLotTypeRuleGroup(int index) {
        LotTypeRuleGroup result = null;
        if (lotTypeRuleGroups != null && lotTypeRuleGroups.length > 0 && lotTypeRuleGroups.length > index) {
            List<LotTypeRuleGroup> groups = new ArrayList<LotTypeRuleGroup>(Arrays.asList(lotTypeRuleGroups));
            result = groups.remove(index);
            lotTypeRuleGroups = groups.toArray(new LotTypeRuleGroup[groups.size()]);
            if (lotTypeRuleGroups.length > 0) {
                noLotTypeRules = new Integer(lotTypeRuleGroups.length);
            } else {
                lotTypeRuleGroups = null;
                noLotTypeRules = null;
            }
        }

        return result;
    }

    @Override
    public int clearLotTypeRuleGroups() {
        int result = 0;
        if (lotTypeRuleGroups != null && lotTypeRuleGroups.length > 0) {
            result = lotTypeRuleGroups.length;
            lotTypeRuleGroups = null;
            noLotTypeRules = null;
        }

        return result;
    }

    @XmlElementRef
    @Override
    public PriceLimits getPriceLimits() {
        return priceLimits;
    }

    @Override
    public void setPriceLimits() {
        this.priceLimits = new PriceLimits50SP1(new FragmentContext(sessionCharset, messageEncoding, validateRequired));
    }
    
    public void setPriceLimits(PriceLimits priceLimits) {
        this.priceLimits = priceLimits;
    }

    @Override
    public void clearPriceLimits() {
        this.priceLimits =null;
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

    @XmlAttribute(name = "MinTrdVol")
    @Override
    public Double getMinTradeVol() {
        return minTradeVol;
    }

    @Override
    public void setMinTradeVol(Double minTradeVol) {
        this.minTradeVol = minTradeVol;
    }

    @XmlAttribute(name = "MaxTrdVol")
    @Override
    public Double getMaxTradeVol() {
        return maxTradeVol;
    }

    @Override
    public void setMaxTradeVol(Double maxTradeVol) {
        this.maxTradeVol = maxTradeVol;
    }

    @XmlAttribute(name = "MxPxVar")
    @Override
    public Double getMaxPriceVariation() {
        return maxPriceVariation;
    }

    @Override
    public void setMaxPriceVariation(Double maxPriceVariation) {
        this.maxPriceVariation = maxPriceVariation;
    }

    @XmlAttribute(name = "ImpldMktInd")
    @Override
    public ImpliedMarketIndicator getImpliedMarketIndicator() {
        return impliedMarketIndicator;
    }

    @Override
    public void setImpliedMarketIndicator(ImpliedMarketIndicator impliedMarketIndicator) {
        this.impliedMarketIndicator = impliedMarketIndicator;
    }

    @XmlAttribute(name = "TrdCcy")
    @Override
    public Currency getTradingCurrency() {
        return tradingCurrency;
    }

    @Override
    public void setTradingCurrency(Currency tradingCurrency) {
        this.tradingCurrency = tradingCurrency;
    }

    @XmlAttribute(name = "RndLot")
    @Override
    public Double getRoundLot() {
        return roundLot;
    }

    @Override
    public void setRoundLot(Double roundLot) {
        this.roundLot = roundLot;
    }

    @XmlAttribute(name = "MlegModel")
    @Override
    public MultilegModel getMultilegModel() {
        return multilegModel;
    }

    @Override
    public void setMultilegModel(MultilegModel multilegModel) {
        this.multilegModel = multilegModel;
    }

    @XmlAttribute(name = "MlegPxMeth")
    @Override
    public MultilegPriceMethod getMultilegPriceMethod() {
        return multilegPriceMethod;
    }

    @Override
    public void setMultilegPriceMethod(MultilegPriceMethod multilegPriceMethod) {
        this.multilegPriceMethod = multilegPriceMethod;
    }

    @XmlAttribute(name = "PxTyp")
    @Override
    public PriceType getPriceType() {
        return priceType;
    }

    @Override
    public void setPriceType(PriceType priceType) {
        this.priceType = priceType;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

    @Override
    protected void setFragmentCompTagValue(Tag tag, ByteBuffer message)
    throws BadFormatMsgException, InvalidMsgException, TagNotPresentException {
        FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, validateRequired);
        if (noTickRules != null && noTickRules.intValue() > 0) {
            if (TICK_RULE_GROUP_TAGS.contains(tag.tagNum)) {
                message.reset();
                tickRuleGroups = new TickRuleGroup[noTickRules.intValue()];
                for (int i = 0; i < noTickRules.intValue(); i++) {
                    TickRuleGroup group = new TickRuleGroup50SP1(context);
                    group.decode(message);
                    tickRuleGroups[i] = group;
                }
            }
        }
        if (noLotTypeRules != null && noLotTypeRules.intValue() > 0) {
            if (LOT_TYPE_RULE_GROUP_TAGS.contains(tag.tagNum)) {
                message.reset();
                lotTypeRuleGroups = new LotTypeRuleGroup[noLotTypeRules.intValue()];
                for (int i = 0; i < noLotTypeRules.intValue(); i++) {
                    LotTypeRuleGroup group = new LotTypeRuleGroup50SP1(context);
                    group.decode(message);
                    lotTypeRuleGroups[i] = group;
                }
            }
        }
        if (PRICE_LIMITS_COMP_TAGS.contains(tag.tagNum)) {
            if (priceLimits == null) {
                priceLimits = new PriceLimits50SP1(context);
            }
            priceLimits.decode(tag, message);
        }
    }

    @Override
    protected Set<Integer> getFragmentCompTags() {
        return START_COMP_TAGS;
    }

    @Override
    protected String getUnsupportedTagMessage() {
        return "This tag is not supported in [BaseTradingRules] component release [5.0SP1].";
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Package Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Private Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Inner Classes">
    // </editor-fold>
}
