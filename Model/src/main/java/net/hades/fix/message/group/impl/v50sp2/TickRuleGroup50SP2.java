/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * TickRuleGroup50SP2.java
 *
 * $Id: TickRuleGroup50SP2.java,v 1.1 2011-04-17 09:30:45 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v50sp2;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.group.TickRuleGroup;

import java.util.HashSet;
import java.util.Set;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import net.hades.fix.message.type.TickRuleType;

/**
 * FIX 5.0SP2 implementation of TickRuleGroup group.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 05/04/2009, 11:39:24 AM
 */
@XmlRootElement(name="TickRules")
@XmlType
@XmlAccessorType(XmlAccessType.NONE)
public class TickRuleGroup50SP2 extends TickRuleGroup {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> START_COMP_TAGS = null;

    protected static final Set<Integer> ALL_TAGS;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">

    static {
        ALL_TAGS = new HashSet<Integer>(TAGS);
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public TickRuleGroup50SP2() {
    }

    public TickRuleGroup50SP2(FragmentContext context) {
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

    @XmlAttribute(name = "StartTickPxRng")
    @Override
    public Double getStartTickPriceRange() {
        return startTickPriceRange;
    }

    @Override
    public void setStartTickPriceRange(Double startTickPriceRange) {
        this.startTickPriceRange = startTickPriceRange;
    }

    @XmlAttribute(name = "EndTickPxRng")
    @Override
    public Double getEndTickPriceRange() {
        return endTickPriceRange;
    }

    @Override
    public void setEndTickPriceRange(Double endTickPriceRange) {
        this.endTickPriceRange = endTickPriceRange;
    }

    @XmlAttribute(name = "TickIncr")
    @Override
    public Double getTickIncrement() {
        return tickIncrement;
    }

    @Override
    public void setTickIncrement(Double tickIncrement) {
        this.tickIncrement = tickIncrement;
    }

    @XmlAttribute(name = "TickRuleTyp")
    @Override
    public TickRuleType getTickRuleType() {
        return tickRuleType;
    }

    @Override
    public void setTickRuleType(TickRuleType tickRuleType) {
        this.tickRuleType = tickRuleType;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

    @Override
    protected String getUnsupportedTagMessage() {
        return "This tag is not supported in [TickRuleGroup] group version [5.0SP2].";
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
