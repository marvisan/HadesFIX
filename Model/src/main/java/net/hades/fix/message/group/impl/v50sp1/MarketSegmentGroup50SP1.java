/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * MarketSegmentGroup50SP1.java
 *
 * $Id: MarketSegmentGroup50SP1.java,v 1.2 2011-04-20 00:32:35 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v50sp1;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.comp.SecurityTradingRules;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.group.StrikeRuleGroup;
import net.hades.fix.message.struct.Tag;

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

import net.hades.fix.message.comp.impl.v50sp1.SecurityTradingRules50SP1;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.group.MarketSegmentGroup;

/**
 * FIX 5.0SP1 implementation of MarketSegmentGroup group.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 * @created 05/04/2009, 11:39:24 AM
 */
@XmlRootElement(name="MktSegGrp")
@XmlType(propOrder = {"securityTradingRules", "strikeRuleGroups"})
@XmlAccessorType(XmlAccessType.NONE)
public class MarketSegmentGroup50SP1 extends MarketSegmentGroup {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> START_COMP_TAGS;

    protected static final Set<Integer> SECURITY_TRADING_RULES_COMP_TAGS = new SecurityTradingRules50SP1().getFragmentAllTags();
    protected static final Set<Integer> STRIKE_RULE_GROUP_TAGS = new StrikeRuleGroup50SP1().getFragmentAllTags();

    protected static final Set<Integer> ALL_TAGS;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">

    static {
        ALL_TAGS = new HashSet<Integer>(TAGS);
        ALL_TAGS.addAll(SECURITY_TRADING_RULES_COMP_TAGS);
        ALL_TAGS.addAll(STRIKE_RULE_GROUP_TAGS);
        START_COMP_TAGS = new HashSet<Integer>(SECURITY_TRADING_RULES_COMP_TAGS);
        START_COMP_TAGS.addAll(STRIKE_RULE_GROUP_TAGS);
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public MarketSegmentGroup50SP1() {
    }

    public MarketSegmentGroup50SP1(FragmentContext context) {
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

    @XmlElementRef
    @Override
    public SecurityTradingRules getSecurityTradingRules() {
        return securityTradingRules;
    }

    @Override
    public void setSecurityTradingRules() {
        this.securityTradingRules = new SecurityTradingRules50SP1(new FragmentContext(sessionCharset, messageEncoding, validateRequired));
    }
    
    public void setSecurityTradingRules(SecurityTradingRules securityTradingRules) {
        this.securityTradingRules = securityTradingRules;
    }

    @Override
    public void clearSecurityTradingRules() {
        this.securityTradingRules = null;
    }

    @Override
    public Integer getNoStrikeRules() {
        return noStrikeRules;
    }

    @Override
    public void setNoStrikeRules(Integer noStrikeRules) {
        this.noStrikeRules = noStrikeRules;
        if (noStrikeRules != null) {
            strikeRuleGroups = new StrikeRuleGroup[noStrikeRules.intValue()];
            for (int i = 0; i < strikeRuleGroups.length; i++) {
                strikeRuleGroups[i] = new StrikeRuleGroup50SP1(new FragmentContext(sessionCharset, messageEncoding, validateRequired));
            }
        }
    }

    @XmlElementRef
    @Override
    public StrikeRuleGroup[] getStrikeRuleGroups() {
        return strikeRuleGroups;
    }

    public void setStrikeRuleGroups(StrikeRuleGroup[] strikeRuleGroups) {
        this.strikeRuleGroups = strikeRuleGroups;
        if (strikeRuleGroups != null) {
            noStrikeRules = new Integer(strikeRuleGroups.length);
        }
    }

    @Override
    public StrikeRuleGroup addStrikeRuleGroup() {
        StrikeRuleGroup group = new StrikeRuleGroup50SP1(new FragmentContext(sessionCharset, messageEncoding, validateRequired));
        List<StrikeRuleGroup> groups = new ArrayList<StrikeRuleGroup>();
        if (strikeRuleGroups != null && strikeRuleGroups.length > 0) {
            groups = new ArrayList<StrikeRuleGroup>(Arrays.asList(strikeRuleGroups));
        }
        groups.add(group);
        strikeRuleGroups = groups.toArray(new StrikeRuleGroup[groups.size()]);
        noStrikeRules = new Integer(strikeRuleGroups.length);

        return group;
    }

    @Override
    public StrikeRuleGroup deleteStrikeRuleGroup(int index) {
        StrikeRuleGroup result = null;
        if (strikeRuleGroups != null && strikeRuleGroups.length > 0 && strikeRuleGroups.length > index) {
            List<StrikeRuleGroup> groups = new ArrayList<StrikeRuleGroup>(Arrays.asList(strikeRuleGroups));
            result = groups.remove(index);
            strikeRuleGroups = groups.toArray(new StrikeRuleGroup[groups.size()]);
            if (strikeRuleGroups.length > 0) {
                noStrikeRules = new Integer(strikeRuleGroups.length);
            } else {
                strikeRuleGroups = null;
                noStrikeRules = null;
            }
        }

        return result;
    }

    @Override
    public int clearStrikeRuleGroups() {
        int result = 0;
        if (strikeRuleGroups != null && strikeRuleGroups.length > 0) {
            result = strikeRuleGroups.length;
            strikeRuleGroups = null;
            noStrikeRules = null;
        }

        return result;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

    @Override
    protected void setFragmentCompTagValue(Tag tag, ByteBuffer message)
    throws BadFormatMsgException, InvalidMsgException, TagNotPresentException {
        FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, validateRequired);
        if (SECURITY_TRADING_RULES_COMP_TAGS.contains(tag.tagNum)) {
            if (securityTradingRules == null) {
                securityTradingRules = new SecurityTradingRules50SP1(context);
            }
            securityTradingRules.decode(tag, message);
        }
        if (noStrikeRules != null && noStrikeRules.intValue() > 0) {
            if (STRIKE_RULE_GROUP_TAGS.contains(tag.tagNum)) {
                message.reset();
                strikeRuleGroups = new StrikeRuleGroup[noStrikeRules.intValue()];
                for (int i = 0; i < noStrikeRules.intValue(); i++) {
                    StrikeRuleGroup group = new StrikeRuleGroup50SP1(context);
                    group.decode(message);
                    strikeRuleGroups[i] = group;
                }
            }
        }
    }

    @Override
    protected String getUnsupportedTagMessage() {
        return "This tag is not supported in [MarketSegmentGroup] group version [5.0SP1].";
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
