/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * StrikeRuleGroup50SP2.java
 *
 * $Id: StrikeRuleGroup50SP2.java,v 1.2 2011-04-20 00:32:34 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v50sp2;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.group.MaturityRuleGroup;
import net.hades.fix.message.group.StrikeRuleGroup;
import net.hades.fix.message.struct.Tag;
import net.hades.fix.message.type.StrikeExerciseStyle;

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

import net.hades.fix.message.exception.BadFormatMsgException;

/**
 * FIX 5.0SP2 implementation of StrikeRuleGroup group.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 * @created 05/04/2009, 11:39:24 AM
 */
@XmlRootElement(name="StrkRules")
@XmlType
@XmlAccessorType(XmlAccessType.NONE)
public class StrikeRuleGroup50SP2 extends StrikeRuleGroup {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> MATURITY_RULE_GROUP_TAGS = new MaturityRuleGroup50SP2().getFragmentAllTags();

    protected static final Set<Integer> START_COMP_TAGS;

    protected static final Set<Integer> ALL_TAGS;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">

    static {
        ALL_TAGS = new HashSet<Integer>(TAGS);
        ALL_TAGS.addAll(MATURITY_RULE_GROUP_TAGS);
        START_COMP_TAGS = new HashSet<Integer>(MATURITY_RULE_GROUP_TAGS);
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public StrikeRuleGroup50SP2() {
    }

    public StrikeRuleGroup50SP2(FragmentContext context) {
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

    @XmlAttribute(name = "StrkRule")
    @Override
    public String getStrikeRuleID() {
        return strikeRuleID;
    }

    @Override
    public void setStrikeRuleID(String strikeRuleID) {
        this.strikeRuleID = strikeRuleID;
    }

    @XmlAttribute(name = "StartStrkPxRng")
    @Override
    public Double getStartStrikePxRange() {
        return startStrikePxRange;
    }

    @Override
    public void setStartStrikePxRange(Double startStrikePxRange) {
        this.startStrikePxRange = startStrikePxRange;
    }
    
    @XmlAttribute(name = "EndStrkPxRng")
    @Override
    public Double getEndStrikePxRange() {
        return endStrikePxRange;
    }

    @Override
    public void setEndStrikePxRange(Double endStrikePxRange) {
        this.endStrikePxRange = endStrikePxRange;
    }

    @XmlAttribute(name = "StrkIncr")
    @Override
    public Double getStrikeIncrement() {
        return strikeIncrement;
    }

    @Override
    public void setStrikeIncrement(Double strikeIncrement) {
        this.strikeIncrement = strikeIncrement;
    }

    @XmlAttribute(name = "StrkExrStyle")
    @Override
    public StrikeExerciseStyle getStrikeExerciseStyle() {
        return strikeExerciseStyle;
    }

    @Override
    public void setStrikeExerciseStyle(StrikeExerciseStyle strikeExerciseStyle) {
        this.strikeExerciseStyle = strikeExerciseStyle;
    }

    @Override
    public void setNoMaturityRules(Integer noMaturityRules) {
        this.noMaturityRules = noMaturityRules;
        if (noMaturityRules != null) {
            maturityRuleGroups = new MaturityRuleGroup[noMaturityRules.intValue()];
            for (int i = 0; i < maturityRuleGroups.length; i++) {
                maturityRuleGroups[i] = new MaturityRuleGroup50SP2(new FragmentContext(sessionCharset, messageEncoding, validateRequired));
            }
        }
    }

    @XmlElementRef
    @Override
    public MaturityRuleGroup[] getMaturityRuleGroups() {
        return maturityRuleGroups;
    }

    public void setMaturityRuleGroups(MaturityRuleGroup[] maturityRuleGroups) {
        this.maturityRuleGroups = maturityRuleGroups;
        if (maturityRuleGroups != null) {
            noMaturityRules = new Integer(maturityRuleGroups.length);
        }
    }

    @Override
    public MaturityRuleGroup addMaturityRuleGroup() {
        MaturityRuleGroup group = new MaturityRuleGroup50SP2(new FragmentContext(sessionCharset, messageEncoding, validateRequired));
        List<MaturityRuleGroup> groups = new ArrayList<MaturityRuleGroup>();
        if (maturityRuleGroups != null && maturityRuleGroups.length > 0) {
            groups = new ArrayList<MaturityRuleGroup>(Arrays.asList(maturityRuleGroups));
        }
        groups.add(group);
        maturityRuleGroups = groups.toArray(new MaturityRuleGroup[groups.size()]);
        noMaturityRules = new Integer(maturityRuleGroups.length);

        return group;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

    @Override
    protected void setFragmentCompTagValue(Tag tag, ByteBuffer message)
    throws BadFormatMsgException, InvalidMsgException, TagNotPresentException {
        if (noMaturityRules != null && noMaturityRules.intValue() > 0) {
            if (MATURITY_RULE_GROUP_TAGS.contains(tag.tagNum)) {
                message.reset();
                maturityRuleGroups = new MaturityRuleGroup[noMaturityRules.intValue()];
                FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, validateRequired);
                for (int i = 0; i < noMaturityRules.intValue(); i++) {
                    MaturityRuleGroup group = new MaturityRuleGroup50SP2(context);
                    group.decode(message);
                    maturityRuleGroups[i] = group;
                }
            }
        }
    }

    @Override
    protected String getUnsupportedTagMessage() {
        return "This tag is not supported in [StrikeRuleGroup] group version [5.0SP2].";
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
