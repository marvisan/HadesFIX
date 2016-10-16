/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * SecurityTradingRules50SP2.java
 *
 * $Id: SecurityTradingRules50SP2.java,v 1.2 2011-04-20 00:32:34 vrotaru Exp $
 */
package net.hades.fix.message.comp.impl.v50sp2;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.comp.BaseTradingRules;
import net.hades.fix.message.comp.SecurityTradingRules;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.group.NestedInstrmtAttribGroup;
import net.hades.fix.message.group.TradingSessionRuleGroup;
import net.hades.fix.message.struct.Tag;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.group.impl.v50sp2.NestedInstrmtAttribGroup50SP2;
import net.hades.fix.message.group.impl.v50sp2.TradingSessionRuleGroup50SP2;

/**
 * FIX 5.0SP2 implementation of SecurityTradingRules component.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 * @created 02/01/2009, 12:07:02 PM
 */
@XmlRootElement(name="SecTrdgRules")
@XmlType(propOrder = {"baseTradingRules", "tradingSessionRuleGroups", "nestedInstrmtAttribGroups"})
@XmlAccessorType(XmlAccessType.NONE)
public class SecurityTradingRules50SP2 extends SecurityTradingRules {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> START_COMP_TAGS;

    protected static final Set<Integer> ALL_TAGS;

    protected static final Set<Integer> BASE_TRADING_RULES_COMP_TAGS = new BaseTradingRules50SP2().getFragmentAllTags();
    protected static final Set<Integer> TRAD_SESSION_RULE_GROUP_TAGS = new TradingSessionRuleGroup50SP2().getFragmentAllTags();
    protected static final Set<Integer> NESTED_INSTR_ATTR_GROUP_TAGS = new NestedInstrmtAttribGroup50SP2().getFragmentAllTags();

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">

    static {
        ALL_TAGS = new HashSet<Integer>(TAGS);
        ALL_TAGS.addAll(BASE_TRADING_RULES_COMP_TAGS);
        ALL_TAGS.addAll(TRAD_SESSION_RULE_GROUP_TAGS);
        ALL_TAGS.addAll(NESTED_INSTR_ATTR_GROUP_TAGS);
        START_COMP_TAGS = new HashSet<Integer>(BASE_TRADING_RULES_COMP_TAGS);
        START_COMP_TAGS.addAll(TRAD_SESSION_RULE_GROUP_TAGS);
        START_COMP_TAGS.addAll(NESTED_INSTR_ATTR_GROUP_TAGS);
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public SecurityTradingRules50SP2() {
        super();
    }

    public SecurityTradingRules50SP2(FragmentContext context) {
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
    public BaseTradingRules getBaseTradingRules() {
        return baseTradingRules;
    }

    @Override
    public void setBaseTradingRules() {
        this.baseTradingRules = new BaseTradingRules50SP2(new FragmentContext(sessionCharset, messageEncoding, validateRequired));
    }

    public void setBaseTradingRules(BaseTradingRules baseTradingRules) {
        this.baseTradingRules = baseTradingRules;
    }

    @Override
    public void clearBaseTradingRules() {
        this.baseTradingRules =null;
    }

    @Override
    public Integer getNoTradingSessionRules() {
        return noTradingSessionRules;
    }

    @Override
    public void setNoTradingSessionRules(Integer noTradingSessionRules) {
        this.noTradingSessionRules = noTradingSessionRules;
        if (noTradingSessionRules != null) {
            tradingSessionRuleGroups = new TradingSessionRuleGroup[noTradingSessionRules.intValue()];
            for (int i = 0; i < tradingSessionRuleGroups.length; i++) {
                tradingSessionRuleGroups[i] = new TradingSessionRuleGroup50SP2(new FragmentContext(sessionCharset, messageEncoding, validateRequired));
            }
        }
    }

    @XmlElementRef
    @Override
    public TradingSessionRuleGroup[] getTradingSessionRuleGroups() {
        return tradingSessionRuleGroups;
    }

    public void setTradingSessionRuleGroups(TradingSessionRuleGroup[] tradingSessionRuleGroups) {
        this.tradingSessionRuleGroups = tradingSessionRuleGroups;
        if (tradingSessionRuleGroups != null) {
            noTradingSessionRules = new Integer(tradingSessionRuleGroups.length);
        }
    }

    @Override
    public TradingSessionRuleGroup addTradingSessionRuleGroup() {
        TradingSessionRuleGroup group = new TradingSessionRuleGroup50SP2(new FragmentContext(sessionCharset, messageEncoding, validateRequired));
        List<TradingSessionRuleGroup> groups = new ArrayList<TradingSessionRuleGroup>();
        if (tradingSessionRuleGroups != null && tradingSessionRuleGroups.length > 0) {
            groups = new ArrayList<TradingSessionRuleGroup>(Arrays.asList(tradingSessionRuleGroups));
        }
        groups.add(group);
        tradingSessionRuleGroups = groups.toArray(new TradingSessionRuleGroup[groups.size()]);
        noTradingSessionRules = new Integer(tradingSessionRuleGroups.length);

        return group;
    }

    @Override
    public TradingSessionRuleGroup deleteTradingSessionRuleGroup(int index) {
        TradingSessionRuleGroup result = null;
        if (tradingSessionRuleGroups != null && tradingSessionRuleGroups.length > 0 && tradingSessionRuleGroups.length > index) {
            List<TradingSessionRuleGroup> groups = new ArrayList<TradingSessionRuleGroup>(Arrays.asList(tradingSessionRuleGroups));
            result = groups.remove(index);
            tradingSessionRuleGroups = groups.toArray(new TradingSessionRuleGroup[groups.size()]);
            if (tradingSessionRuleGroups.length > 0) {
                noTradingSessionRules = new Integer(tradingSessionRuleGroups.length);
            } else {
                tradingSessionRuleGroups = null;
                noTradingSessionRules = null;
            }
        }

        return result;
    }

    @Override
    public int clearTradingSessionRuleGroups() {
        int result = 0;
        if (tradingSessionRuleGroups != null && tradingSessionRuleGroups.length > 0) {
            result = tradingSessionRuleGroups.length;
            tradingSessionRuleGroups = null;
            noTradingSessionRules = null;
        }

        return result;
    }

    @Override
    public Integer getNoNestedInstrAttrib() {
        return noNestedInstrAttrib;
    }

    @Override
    public void setNoNestedInstrAttrib(Integer noNestedInstrAttrib) {
        this.noNestedInstrAttrib = noNestedInstrAttrib;
        if (noNestedInstrAttrib != null) {
            nestedInstrmtAttribGroups = new NestedInstrmtAttribGroup[noNestedInstrAttrib.intValue()];
            for (int i = 0; i < nestedInstrmtAttribGroups.length; i++) {
                nestedInstrmtAttribGroups[i] = new NestedInstrmtAttribGroup50SP2(new FragmentContext(sessionCharset, messageEncoding, validateRequired));
            }
        }
    }

    @XmlElementRef
    @Override
    public NestedInstrmtAttribGroup[] getNestedInstrmtAttribGroups() {
        return nestedInstrmtAttribGroups;
    }

    public void setNestedInstrmtAttribGroups(NestedInstrmtAttribGroup[] nestedInstrmtAttribGroups) {
        this.nestedInstrmtAttribGroups = nestedInstrmtAttribGroups;
        if (nestedInstrmtAttribGroups != null) {
            noNestedInstrAttrib = new Integer(nestedInstrmtAttribGroups.length);
        }
    }

    @Override
    public NestedInstrmtAttribGroup addNestedInstrmtAttribGroup() {
        NestedInstrmtAttribGroup group = new NestedInstrmtAttribGroup50SP2(new FragmentContext(sessionCharset, messageEncoding, validateRequired));
        List<NestedInstrmtAttribGroup> groups = new ArrayList<NestedInstrmtAttribGroup>();
        if (nestedInstrmtAttribGroups != null && nestedInstrmtAttribGroups.length > 0) {
            groups = new ArrayList<NestedInstrmtAttribGroup>(Arrays.asList(nestedInstrmtAttribGroups));
        }
        groups.add(group);
        nestedInstrmtAttribGroups = groups.toArray(new NestedInstrmtAttribGroup[groups.size()]);
        noNestedInstrAttrib = new Integer(nestedInstrmtAttribGroups.length);

        return group;
    }

    @Override
    public NestedInstrmtAttribGroup deleteNestedInstrmtAttribGroup(int index) {
        NestedInstrmtAttribGroup result = null;
        if (nestedInstrmtAttribGroups != null && nestedInstrmtAttribGroups.length > 0 && nestedInstrmtAttribGroups.length > index) {
            List<NestedInstrmtAttribGroup> groups = new ArrayList<NestedInstrmtAttribGroup>(Arrays.asList(nestedInstrmtAttribGroups));
            result = groups.remove(index);
            nestedInstrmtAttribGroups = groups.toArray(new NestedInstrmtAttribGroup[groups.size()]);
            if (nestedInstrmtAttribGroups.length > 0) {
                noNestedInstrAttrib = new Integer(nestedInstrmtAttribGroups.length);
            } else {
                nestedInstrmtAttribGroups = null;
                noNestedInstrAttrib = null;
            }
        }

        return result;
    }

    @Override
    public int clearNestedInstrmtAttribGroups() {
        int result = 0;
        if (nestedInstrmtAttribGroups != null && nestedInstrmtAttribGroups.length > 0) {
            result = nestedInstrmtAttribGroups.length;
            nestedInstrmtAttribGroups = null;
            noNestedInstrAttrib = null;
        }

        return result;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

    @Override
    protected void setFragmentCompTagValue(Tag tag, ByteBuffer message)
    throws BadFormatMsgException, InvalidMsgException, TagNotPresentException {
        FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, validateRequired);
        if (BASE_TRADING_RULES_COMP_TAGS.contains(tag.tagNum)) {
            if (baseTradingRules == null) {
                baseTradingRules = new BaseTradingRules50SP2(context);
            }
            baseTradingRules.decode(tag, message);
        }
        if (noTradingSessionRules != null && noTradingSessionRules.intValue() > 0) {
            if (TRAD_SESSION_RULE_GROUP_TAGS.contains(tag.tagNum)) {
                message.reset();
                tradingSessionRuleGroups = new TradingSessionRuleGroup[noTradingSessionRules.intValue()];
                for (int i = 0; i < noTradingSessionRules.intValue(); i++) {
                    TradingSessionRuleGroup group = new TradingSessionRuleGroup50SP2(context);
                    group.decode(message);
                    tradingSessionRuleGroups[i] = group;
                }
            }
        }
        if (noNestedInstrAttrib != null && noNestedInstrAttrib.intValue() > 0) {
            if (NESTED_INSTR_ATTR_GROUP_TAGS.contains(tag.tagNum)) {
                message.reset();
                nestedInstrmtAttribGroups = new NestedInstrmtAttribGroup[noNestedInstrAttrib.intValue()];
                for (int i = 0; i < noNestedInstrAttrib.intValue(); i++) {
                    NestedInstrmtAttribGroup group = new NestedInstrmtAttribGroup50SP2(context);
                    group.decode(message);
                    nestedInstrmtAttribGroups[i] = group;
                }
            }
        }
    }

    @Override
    protected Set<Integer> getFragmentCompTags() {
        return START_COMP_TAGS;
    }

    @Override
    protected String getUnsupportedTagMessage() {
        return "This tag is not supported in [SecurityTradingRules] component release [5.0SP1].";
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Package Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Private Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Inner Classes">
    // </editor-fold>
}
