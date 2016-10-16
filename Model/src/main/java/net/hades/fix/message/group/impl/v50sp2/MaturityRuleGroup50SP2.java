/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * MaturityRuleGroup50SP2.java
 *
 * $Id: MaturityRuleGroup50SP2.java,v 1.1 2011-04-17 09:30:45 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v50sp2;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.group.MaturityRuleGroup;
import net.hades.fix.message.type.MaturityMonthYearIncrementUnits;

import java.util.HashSet;
import java.util.Set;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import net.hades.fix.message.type.MaturityMonthYearFormat;

/**
 * FIX 5.0SP2 implementation of MaturityRuleGroup group.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 05/04/2009, 11:39:24 AM
 */
@XmlRootElement(name="MatRules")
@XmlType
@XmlAccessorType(XmlAccessType.NONE)
public class MaturityRuleGroup50SP2 extends MaturityRuleGroup {

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

    public MaturityRuleGroup50SP2() {
    }

    public MaturityRuleGroup50SP2(FragmentContext context) {
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

    @XmlAttribute(name = "MatRuleID")
    @Override
    public String getMaturityRuleID() {
        return maturityRuleID;
    }

    @Override
    public void setMaturityRuleID(String maturityRuleID) {
        this.maturityRuleID = maturityRuleID;
    }

    @XmlAttribute(name = "MMYFmt")
    @Override
    public MaturityMonthYearFormat getMaturityMonthYearFormat() {
        return maturityMonthYearFormat;
    }

    @Override
    public void setMaturityMonthYearFormat(MaturityMonthYearFormat maturityMonthYearFormat) {
        this.maturityMonthYearFormat = maturityMonthYearFormat;
    }

    @XmlAttribute(name = "MMYIncrUnits")
    @Override
    public MaturityMonthYearIncrementUnits getMaturityMonthYearIncrementUnits() {
        return maturityMonthYearIncrementUnits;
    }

    @Override
    public void setMaturityMonthYearIncrementUnits(MaturityMonthYearIncrementUnits maturityMonthYearIncrementUnits) {
        this.maturityMonthYearIncrementUnits = maturityMonthYearIncrementUnits;
    }

    @XmlAttribute(name = "StartMMY")
    @Override
    public String getStartMaturityMonthYear() {
        return startMaturityMonthYear;
    }

    @Override
    public void setStartMaturityMonthYear(String startMaturityMonthYear) {
        this.startMaturityMonthYear = startMaturityMonthYear;
    }

    @XmlAttribute(name = "EndMMY")
    @Override
    public String getEndMaturityMonthYear() {
        return endMaturityMonthYear;
    }

    @Override
    public void setEndMaturityMonthYear(String endMaturityMonthYear) {
        this.endMaturityMonthYear = endMaturityMonthYear;
    }

    @XmlAttribute(name = "MMYIncr")
    @Override
    public Integer getMaturityMonthYearIncrement() {
        return maturityMonthYearIncrement;
    }

    @Override
    public void setMaturityMonthYearIncrement(Integer maturityMonthYearIncrement) {
        this.maturityMonthYearIncrement = maturityMonthYearIncrement;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

    @Override
    protected String getUnsupportedTagMessage() {
        return "This tag is not supported in [MaturityRuleGroup] group version [5.0SP2].";
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
