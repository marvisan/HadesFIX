/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * TradingSessionRules50SP1.java
 *
 * $Id: TradingSessionRules50SP1.java,v 1.2 2011-04-20 00:32:34 vrotaru Exp $
 */
package net.hades.fix.message.comp.impl.v50sp1;

import net.hades.fix.message.group.impl.v50sp1.ExecInstRuleGroup50SP1;
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

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.comp.TradingSessionRules;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.group.ExecInstRuleGroup;
import net.hades.fix.message.group.MDFeedTypeGroup;
import net.hades.fix.message.group.MatchRuleGroup;
import net.hades.fix.message.group.OrdTypeRuleGroup;
import net.hades.fix.message.group.TimeInForceRuleGroup;
import net.hades.fix.message.group.impl.v50sp1.MDFeedTypeGroup50SP1;
import net.hades.fix.message.group.impl.v50sp1.MatchRuleGroup50SP1;
import net.hades.fix.message.group.impl.v50sp1.OrdTypeRuleGroup50SP1;
import net.hades.fix.message.group.impl.v50sp1.TimeInForceRuleGroup50SP1;

/**
 * FIX 5.0SP1 implementation of TradingSessionRules component.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 * @created 02/01/2009, 12:07:02 PM
 */
@XmlRootElement(name="TrdgSesRules")
@XmlType(propOrder = {"ordTypeRuleGroups", "timeInForceRuleGroups", "execInstRuleGroups", "matchRuleGroups", "MDFeedTypeGroups"})
@XmlAccessorType(XmlAccessType.NONE)
public class TradingSessionRules50SP1 extends TradingSessionRules {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> START_COMP_TAGS;

    protected static final Set<Integer> ALL_TAGS;

    protected static final Set<Integer> ORD_TYPE_RULE_GROUP_TAGS = new OrdTypeRuleGroup50SP1().getFragmentAllTags();
    protected static final Set<Integer> TIME_IN_FORCE_RULE_GROUP_TAGS = new TimeInForceRuleGroup50SP1().getFragmentAllTags();
    protected static final Set<Integer> EXEC_INST_RULE_GROUP_TAGS = new ExecInstRuleGroup50SP1().getFragmentAllTags();
    protected static final Set<Integer> MATCH_RULE_GROUP_TAGS = new MatchRuleGroup50SP1().getFragmentAllTags();
    protected static final Set<Integer> MD_FEED_TYPE_GROUP_TAGS = new MDFeedTypeGroup50SP1().getFragmentAllTags();

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">

    static {
        ALL_TAGS = new HashSet<Integer>(TAGS);
        ALL_TAGS.addAll(ORD_TYPE_RULE_GROUP_TAGS);
        ALL_TAGS.addAll(TIME_IN_FORCE_RULE_GROUP_TAGS);
        ALL_TAGS.addAll(EXEC_INST_RULE_GROUP_TAGS);
        ALL_TAGS.addAll(MATCH_RULE_GROUP_TAGS);
        ALL_TAGS.addAll(MD_FEED_TYPE_GROUP_TAGS);
        START_COMP_TAGS = new HashSet<Integer>(ORD_TYPE_RULE_GROUP_TAGS);
        START_COMP_TAGS.addAll(TIME_IN_FORCE_RULE_GROUP_TAGS);
        START_COMP_TAGS.addAll(EXEC_INST_RULE_GROUP_TAGS);
        START_COMP_TAGS.addAll(MATCH_RULE_GROUP_TAGS);
        START_COMP_TAGS.addAll(MD_FEED_TYPE_GROUP_TAGS);
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public TradingSessionRules50SP1() {
        super();
    }

    public TradingSessionRules50SP1(FragmentContext context) {
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
    public Integer getNoOrdTypeRules() {
        return noOrdTypeRules;
    }

    @Override
    public void setNoOrdTypeRules(Integer noOrdTypeRules) {
        this.noOrdTypeRules = noOrdTypeRules;
        if (noOrdTypeRules != null) {
            ordTypeRuleGroups = new OrdTypeRuleGroup[noOrdTypeRules.intValue()];
            for (int i = 0; i < ordTypeRuleGroups.length; i++) {
                ordTypeRuleGroups[i] = new OrdTypeRuleGroup50SP1(new FragmentContext(sessionCharset, messageEncoding, validateRequired));
            }
        }
    }

    @XmlElementRef
    @Override
    public OrdTypeRuleGroup[] getOrdTypeRuleGroups() {
        return ordTypeRuleGroups;
    }

    public void setOrdTypeRuleGroups(OrdTypeRuleGroup[] ordTypeRuleGroups) {
        this.ordTypeRuleGroups = ordTypeRuleGroups;
        if (ordTypeRuleGroups != null) {
            noOrdTypeRules = new Integer(ordTypeRuleGroups.length);
        }
    }

    @Override
    public OrdTypeRuleGroup addOrdTypeRuleGroup() {
        OrdTypeRuleGroup group = new OrdTypeRuleGroup50SP1(new FragmentContext(sessionCharset, messageEncoding, validateRequired));
        List<OrdTypeRuleGroup> groups = new ArrayList<OrdTypeRuleGroup>();
        if (ordTypeRuleGroups != null && ordTypeRuleGroups.length > 0) {
            groups = new ArrayList<OrdTypeRuleGroup>(Arrays.asList(ordTypeRuleGroups));
        }
        groups.add(group);
        ordTypeRuleGroups = groups.toArray(new OrdTypeRuleGroup[groups.size()]);
        noOrdTypeRules = new Integer(ordTypeRuleGroups.length);

        return group;
    }

    @Override
   public OrdTypeRuleGroup deleteOrdTypeRuleGroup(int index) {
        OrdTypeRuleGroup result = null;
        if (ordTypeRuleGroups != null && ordTypeRuleGroups.length > 0 && ordTypeRuleGroups.length > index) {
            List<OrdTypeRuleGroup> groups = new ArrayList<OrdTypeRuleGroup>(Arrays.asList(ordTypeRuleGroups));
            result = groups.remove(index);
            ordTypeRuleGroups = groups.toArray(new OrdTypeRuleGroup[groups.size()]);
            if (ordTypeRuleGroups.length > 0) {
                noOrdTypeRules = new Integer(ordTypeRuleGroups.length);
            } else {
                ordTypeRuleGroups = null;
                noOrdTypeRules = null;
            }
        }

        return result;
    }

    @Override
    public int clearOrdTypeRuleGroups() {
        int result = 0;
        if (ordTypeRuleGroups != null && ordTypeRuleGroups.length > 0) {
            result = ordTypeRuleGroups.length;
            ordTypeRuleGroups = null;
            noOrdTypeRules = null;
        }

        return result;
    }

    @Override
    public Integer getNoTimeInForceRules() {
        return noTimeInForceRules;
    }

    @Override
    public void setNoTimeInForceRules(Integer noTimeInForceRules) {
        this.noTimeInForceRules = noTimeInForceRules;
        if (noTimeInForceRules != null) {
            timeInForceRuleGroups = new TimeInForceRuleGroup[noTimeInForceRules.intValue()];
            for (int i = 0; i < timeInForceRuleGroups.length; i++) {
                timeInForceRuleGroups[i] = new TimeInForceRuleGroup50SP1(new FragmentContext(sessionCharset, messageEncoding, validateRequired));
            }
        }
    }

    @XmlElementRef
    @Override
    public TimeInForceRuleGroup[] getTimeInForceRuleGroups() {
        return timeInForceRuleGroups;
    }

    public void setTimeInForceRuleGroups(TimeInForceRuleGroup[] timeInForceRuleGroups) {
        this.timeInForceRuleGroups = timeInForceRuleGroups;
        if (timeInForceRuleGroups != null) {
            noTimeInForceRules = new Integer(timeInForceRuleGroups.length);
        }
    }

    @Override
    public TimeInForceRuleGroup addTimeInForceRuleGroup() {
        TimeInForceRuleGroup group = new TimeInForceRuleGroup50SP1(new FragmentContext(sessionCharset, messageEncoding, validateRequired));
        List<TimeInForceRuleGroup> groups = new ArrayList<TimeInForceRuleGroup>();
        if (timeInForceRuleGroups != null && timeInForceRuleGroups.length > 0) {
            groups = new ArrayList<TimeInForceRuleGroup>(Arrays.asList(timeInForceRuleGroups));
        }
        groups.add(group);
        timeInForceRuleGroups = groups.toArray(new TimeInForceRuleGroup[groups.size()]);
        noTimeInForceRules = new Integer(timeInForceRuleGroups.length);

        return group;
    }

    @Override
    public TimeInForceRuleGroup deleteTimeInForceRuleGroup(int index) {
        TimeInForceRuleGroup result = null;
        if (timeInForceRuleGroups != null && timeInForceRuleGroups.length > 0 && timeInForceRuleGroups.length > index) {
            List<TimeInForceRuleGroup> groups = new ArrayList<TimeInForceRuleGroup>(Arrays.asList(timeInForceRuleGroups));
            result = groups.remove(index);
            timeInForceRuleGroups = groups.toArray(new TimeInForceRuleGroup[groups.size()]);
            if (timeInForceRuleGroups.length > 0) {
                noTimeInForceRules = new Integer(timeInForceRuleGroups.length);
            } else {
                timeInForceRuleGroups = null;
                noTimeInForceRules = null;
            }
        }

        return result;
    }

    @Override
    public int clearTimeInForceRuleGroups() {
        int result = 0;
        if (timeInForceRuleGroups != null && timeInForceRuleGroups.length > 0) {
            result = timeInForceRuleGroups.length;
            timeInForceRuleGroups = null;
            noTimeInForceRules = null;
        }

        return result;
    }

    @Override
    public Integer getNoExecInstRules() {
        return noExecInstRules;
    }

    public void setExecInstRuleGroups(ExecInstRuleGroup[] execInstRuleGroups) {
        this.execInstRuleGroups = execInstRuleGroups;
        if (execInstRuleGroups != null) {
            noExecInstRules = new Integer(execInstRuleGroups.length);
        }
    }

    @Override
    public void setNoExecInstRules(Integer noExecInstRules) {
        this.noExecInstRules = noExecInstRules;
        if (noExecInstRules != null) {
            execInstRuleGroups = new ExecInstRuleGroup[noExecInstRules.intValue()];
            for (int i = 0; i < execInstRuleGroups.length; i++) {
                execInstRuleGroups[i] = new ExecInstRuleGroup50SP1(new FragmentContext(sessionCharset, messageEncoding, validateRequired));
            }
        }
    }

    @XmlElementRef
    @Override
    public ExecInstRuleGroup[] getExecInstRuleGroups() {
        return execInstRuleGroups;
    }

    @Override
    public ExecInstRuleGroup addExecInstRuleGroup() {
        ExecInstRuleGroup group = new ExecInstRuleGroup50SP1(new FragmentContext(sessionCharset, messageEncoding, validateRequired));
        List<ExecInstRuleGroup> groups = new ArrayList<ExecInstRuleGroup>();
        if (execInstRuleGroups != null && execInstRuleGroups.length > 0) {
            groups = new ArrayList<ExecInstRuleGroup>(Arrays.asList(execInstRuleGroups));
        }
        groups.add(group);
        execInstRuleGroups = groups.toArray(new ExecInstRuleGroup[groups.size()]);
        noExecInstRules = new Integer(execInstRuleGroups.length);

        return group;
    }

    @Override
    public ExecInstRuleGroup deleteExecInstRuleGroup(int index) {
        ExecInstRuleGroup result = null;
        if (execInstRuleGroups != null && execInstRuleGroups.length > 0 && execInstRuleGroups.length > index) {
            List<ExecInstRuleGroup> groups = new ArrayList<ExecInstRuleGroup>(Arrays.asList(execInstRuleGroups));
            result = groups.remove(index);
            execInstRuleGroups = groups.toArray(new ExecInstRuleGroup[groups.size()]);
            if (execInstRuleGroups.length > 0) {
                noExecInstRules = new Integer(execInstRuleGroups.length);
            } else {
                execInstRuleGroups = null;
                noExecInstRules = null;
            }
        }

        return result;
    }

    @Override
    public int clearExecInstRuleGroups() {
        int result = 0;
        if (execInstRuleGroups != null && execInstRuleGroups.length > 0) {
            result = execInstRuleGroups.length;
            execInstRuleGroups = null;
            noExecInstRules = null;
        }

        return result;
    }

    @Override
    public Integer getNoMatchRules() {
        return noMatchRules;
    }

    public void setMatchRuleGroups(MatchRuleGroup[] matchRuleGroups) {
        this.matchRuleGroups = matchRuleGroups;
        if (matchRuleGroups != null) {
            noMatchRules = new Integer(matchRuleGroups.length);
        }
    }

    @Override
    public void setNoMatchRules(Integer noMatchRules) {
        this.noMatchRules = noMatchRules;
        if (noMatchRules != null) {
            matchRuleGroups = new MatchRuleGroup[noMatchRules.intValue()];
            for (int i = 0; i < matchRuleGroups.length; i++) {
                matchRuleGroups[i] = new MatchRuleGroup50SP1(new FragmentContext(sessionCharset, messageEncoding, validateRequired));
            }
        }
    }

    @XmlElementRef
    @Override
    public MatchRuleGroup[] getMatchRuleGroups() {
        return matchRuleGroups;
    }

    @Override
    public MatchRuleGroup addMatchRuleGroup() {
        MatchRuleGroup group = new MatchRuleGroup50SP1(new FragmentContext(sessionCharset, messageEncoding, validateRequired));
        List<MatchRuleGroup> groups = new ArrayList<MatchRuleGroup>();
        if (matchRuleGroups != null && matchRuleGroups.length > 0) {
            groups = new ArrayList<MatchRuleGroup>(Arrays.asList(matchRuleGroups));
        }
        groups.add(group);
        matchRuleGroups = groups.toArray(new MatchRuleGroup[groups.size()]);
        noMatchRules = new Integer(matchRuleGroups.length);

        return group;
    }

    @Override
    public MatchRuleGroup deleteMatchRuleGroup(int index) {
        MatchRuleGroup result = null;
        if (matchRuleGroups != null && matchRuleGroups.length > 0 && matchRuleGroups.length > index) {
            List<MatchRuleGroup> groups = new ArrayList<MatchRuleGroup>(Arrays.asList(matchRuleGroups));
            result = groups.remove(index);
            matchRuleGroups = groups.toArray(new MatchRuleGroup[groups.size()]);
            if (matchRuleGroups.length > 0) {
                noMatchRules = new Integer(matchRuleGroups.length);
            } else {
                matchRuleGroups = null;
                noMatchRules = null;
            }
        }

        return result;
    }

    @Override
    public int clearMatchRuleGroups() {
        int result = 0;
        if (matchRuleGroups != null && matchRuleGroups.length > 0) {
            result = matchRuleGroups.length;
            matchRuleGroups = null;
            noMatchRules = null;
        }

        return result;
    }

    @Override
    public Integer getNoMDFeedTypes() {
        return noMDFeedTypes;
    }

    @Override
    public void setNoMDFeedTypes(Integer noMDFeedTypes) {
        this.noMDFeedTypes = noMDFeedTypes;
        if (noMDFeedTypes != null) {
            MDFeedTypeGroup = new MDFeedTypeGroup[noMDFeedTypes.intValue()];
            for (int i = 0; i < MDFeedTypeGroup.length; i++) {
                MDFeedTypeGroup[i] = new MDFeedTypeGroup50SP1(new FragmentContext(sessionCharset, messageEncoding, validateRequired));
            }
        }
    }

    @XmlElementRef
    @Override
    public MDFeedTypeGroup[] getMDFeedTypeGroups() {
        return MDFeedTypeGroup;
    }

    public void setMDFeedTypeGroups(MDFeedTypeGroup[] MDFeedTypeGroup) {
        this.MDFeedTypeGroup = MDFeedTypeGroup;
        if (MDFeedTypeGroup != null) {
            noMDFeedTypes = new Integer(MDFeedTypeGroup.length);
        }
    }

    @Override
    public MDFeedTypeGroup addMDFeedTypeGroup() {
        MDFeedTypeGroup group = new MDFeedTypeGroup50SP1(new FragmentContext(sessionCharset, messageEncoding, validateRequired));
        List<MDFeedTypeGroup> groups = new ArrayList<MDFeedTypeGroup>();
        if (MDFeedTypeGroup != null && MDFeedTypeGroup.length > 0) {
            groups = new ArrayList<MDFeedTypeGroup>(Arrays.asList(MDFeedTypeGroup));
        }
        groups.add(group);
        MDFeedTypeGroup = groups.toArray(new MDFeedTypeGroup[groups.size()]);
        noMDFeedTypes = new Integer(MDFeedTypeGroup.length);

        return group;
    }

    @Override
    public MDFeedTypeGroup deleteMDFeedTypeGroup(int index) {
        MDFeedTypeGroup result = null;
        if (MDFeedTypeGroup != null && MDFeedTypeGroup.length > 0 && MDFeedTypeGroup.length > index) {
            List<MDFeedTypeGroup> groups = new ArrayList<MDFeedTypeGroup>(Arrays.asList(MDFeedTypeGroup));
            result = groups.remove(index);
            MDFeedTypeGroup = groups.toArray(new MDFeedTypeGroup[groups.size()]);
            if (MDFeedTypeGroup.length > 0) {
                noMDFeedTypes = new Integer(MDFeedTypeGroup.length);
            } else {
                MDFeedTypeGroup = null;
                noMDFeedTypes = null;
            }
        }

        return result;
    }

    @Override
    public int clearMDFeedTypeGroups() {
        int result = 0;
        if (MDFeedTypeGroup != null && MDFeedTypeGroup.length > 0) {
            result = MDFeedTypeGroup.length;
            MDFeedTypeGroup = null;
            noMDFeedTypes = null;
        }

        return result;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

    @Override
    protected void setFragmentCompTagValue(Tag tag, ByteBuffer message)
    throws BadFormatMsgException, InvalidMsgException, TagNotPresentException {
        FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, validateRequired);
        if (noOrdTypeRules != null && noOrdTypeRules.intValue() > 0) {
            if (ORD_TYPE_RULE_GROUP_TAGS.contains(tag.tagNum)) {
                message.reset();
                ordTypeRuleGroups = new OrdTypeRuleGroup[noOrdTypeRules.intValue()];
                for (int i = 0; i < noOrdTypeRules.intValue(); i++) {
                    OrdTypeRuleGroup group = new OrdTypeRuleGroup50SP1(context);
                    group.decode(message);
                    ordTypeRuleGroups[i] = group;
                }
            }
        }
        if (noTimeInForceRules != null && noTimeInForceRules.intValue() > 0) {
            if (TIME_IN_FORCE_RULE_GROUP_TAGS.contains(tag.tagNum)) {
                message.reset();
                timeInForceRuleGroups = new TimeInForceRuleGroup[noTimeInForceRules.intValue()];
                for (int i = 0; i < noTimeInForceRules.intValue(); i++) {
                    TimeInForceRuleGroup group = new TimeInForceRuleGroup50SP1(context);
                    group.decode(message);
                    timeInForceRuleGroups[i] = group;
                }
            }
        }
        if (noExecInstRules != null && noExecInstRules.intValue() > 0) {
            if (EXEC_INST_RULE_GROUP_TAGS.contains(tag.tagNum)) {
                message.reset();
                execInstRuleGroups = new ExecInstRuleGroup[noExecInstRules.intValue()];
                for (int i = 0; i < noExecInstRules.intValue(); i++) {
                    ExecInstRuleGroup group = new ExecInstRuleGroup50SP1(context);
                    group.decode(message);
                    execInstRuleGroups[i] = group;
                }
            }
        }
        if (noMatchRules != null && noMatchRules.intValue() > 0) {
            if (MATCH_RULE_GROUP_TAGS.contains(tag.tagNum)) {
                message.reset();
                matchRuleGroups = new MatchRuleGroup[noMatchRules.intValue()];
                for (int i = 0; i < noMatchRules.intValue(); i++) {
                    MatchRuleGroup group = new MatchRuleGroup50SP1(context);
                    group.decode(message);
                    matchRuleGroups[i] = group;
                }
            }
        }
        if (noMDFeedTypes != null && noMDFeedTypes.intValue() > 0) {
            if (MD_FEED_TYPE_GROUP_TAGS.contains(tag.tagNum)) {
                message.reset();
                MDFeedTypeGroup = new MDFeedTypeGroup[noMDFeedTypes.intValue()];
                for (int i = 0; i < noMDFeedTypes.intValue(); i++) {
                    MDFeedTypeGroup group = new MDFeedTypeGroup50SP1(context);
                    group.decode(message);
                    MDFeedTypeGroup[i] = group;
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
        return "This tag is not supported in [TradingSessionRules] component release [5.0SP1].";
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Package Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Private Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Inner Classes">
    // </editor-fold>
}
