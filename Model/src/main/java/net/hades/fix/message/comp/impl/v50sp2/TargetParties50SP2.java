/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * TargetParties50SP2.java
 *
 * $Id: TargetParties50SP2.java,v 1.7 2011-04-14 23:44:50 vrotaru Exp $
 */
package net.hades.fix.message.comp.impl.v50sp2;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.comp.TargetParties;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.group.TargetPartyGroup;
import net.hades.fix.message.group.impl.v50sp2.TargetPartyGroup50SP2;
import net.hades.fix.message.struct.Tag;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.xml.bind.annotation.XmlTransient;

/**
 * FIX 5.0SP2 implementation of TargetParties component.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.7 $
 * @created 04/06/2009, 11:16:20 AM
 */
@XmlTransient
public class TargetParties50SP2 extends TargetParties {
    
    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = -1265288994714476066L;

    protected static final Set<Integer> START_COMP_TAGS;
    
    protected static final Set<Integer> ALL_TAGS;
    
    protected static final Set<Integer> TARGET_PARTY_GROUP_TAGS = new TargetPartyGroup50SP2().getFragmentAllTags();
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Static Block">
    
    static {
        ALL_TAGS = new HashSet<Integer>(TAGS);
        ALL_TAGS.addAll(TARGET_PARTY_GROUP_TAGS);
        START_COMP_TAGS = new HashSet<Integer>(TARGET_PARTY_GROUP_TAGS);
    }
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Attributes">
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Constructors">
    
    public TargetParties50SP2() {
        super();
    }
    
    public TargetParties50SP2(FragmentContext context) {
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
    public Integer getNoTargetPartyIDs() {
        return noTargetPartyIDs;
    }
    
    @Override
    public void setNoTargetPartyIDs(Integer noTargetPartyIDs) {
        this.noTargetPartyIDs = noTargetPartyIDs;
        if (noTargetPartyIDs != null) {
            targetPartyGroups = new TargetPartyGroup[noTargetPartyIDs.intValue()];
            FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, validateRequired);
            for (int i = 0; i < targetPartyGroups.length; i++) {
                targetPartyGroups[i] = new TargetPartyGroup50SP2(context);
            }
        }
    }
    
    @Override
    public TargetPartyGroup[] getTargetPartyGroups() {
        return targetPartyGroups;
    }
    
    public void setTargetPartyGroups(TargetPartyGroup[] targetPartyGroups) {
        this.targetPartyGroups = targetPartyGroups;
        if (targetPartyGroups != null) {
            noTargetPartyIDs = new Integer(targetPartyGroups.length);
        }
    }
    
    @Override
    public TargetPartyGroup addTargetPartyGroup() {
        TargetPartyGroup group = new TargetPartyGroup50SP2(new FragmentContext(sessionCharset, messageEncoding, validateRequired));
        List<TargetPartyGroup> groups = new ArrayList<TargetPartyGroup>();
        if (targetPartyGroups != null && targetPartyGroups.length > 0) {
            groups = new ArrayList<TargetPartyGroup>(Arrays.asList(targetPartyGroups));
        }
        groups.add(group);
        targetPartyGroups = groups.toArray(new TargetPartyGroup[groups.size()]);
        noTargetPartyIDs = new Integer(targetPartyGroups.length);
        
        return group;
    }
    
    @Override
    public TargetPartyGroup deleteTargetPartyGroup(int index) {
        TargetPartyGroup result = null;
        if (targetPartyGroups != null && targetPartyGroups.length > 0 && targetPartyGroups.length > index) {
            List<TargetPartyGroup> groups = new ArrayList<TargetPartyGroup>(Arrays.asList(targetPartyGroups));
            result = groups.remove(index);
            targetPartyGroups = groups.toArray(new TargetPartyGroup[groups.size()]);
            if (targetPartyGroups.length > 0) {
                noTargetPartyIDs = new Integer(targetPartyGroups.length);
            } else {
                targetPartyGroups = null;
                noTargetPartyIDs = null;
            }
        }
        
        return result;
    }
    
    @Override
    public int clearTargetPartyGroups() {
        int result = 0;
        if (targetPartyGroups != null && targetPartyGroups.length > 0) {
            result = targetPartyGroups.length;
            targetPartyGroups = null;
            noTargetPartyIDs = null;
        }
        
        return result;
    }
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Protected Methods">
    
    @Override
    protected void setFragmentCompTagValue(Tag tag, ByteBuffer message)
        throws BadFormatMsgException, InvalidMsgException, TagNotPresentException {
        
        if (TARGET_PARTY_GROUP_TAGS.contains(tag.tagNum)) {
            if (noTargetPartyIDs != null && noTargetPartyIDs.intValue() > 0) {
                FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, validateRequired);
                message.reset();
                targetPartyGroups = new TargetPartyGroup[noTargetPartyIDs.intValue()];
                for (int i = 0; i < noTargetPartyIDs.intValue(); i++) {
                    TargetPartyGroup component = new TargetPartyGroup50SP2(context);
                    component.decode(message);
                    targetPartyGroups[i] = component;
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
        return "This tag is not supported in [TargetParties] group version [5.0SP2].";
    }
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Package Methods">
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Private Methods">
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Inner Classes">
    // </editor-fold>
}
