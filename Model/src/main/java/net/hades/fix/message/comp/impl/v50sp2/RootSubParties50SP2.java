/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * RootSubParties50SP2.java
 *
 * $Id: RootSubParties50SP2.java,v 1.7 2011-04-14 23:44:50 vrotaru Exp $
 */
package net.hades.fix.message.comp.impl.v50sp2;

import net.hades.fix.message.comp.RootSubParties;
import net.hades.fix.message.struct.Tag;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.xml.bind.annotation.XmlTransient;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.group.RootPartySubGroup;
import net.hades.fix.message.group.impl.v50sp2.RootPartySubGroup50SP2;

/**
 * FIX 5.0SP2 implementation of RootSubParties component.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.7 $
 * @created 04/06/2009, 11:16:20 AM
 */
@XmlTransient
public class RootSubParties50SP2 extends RootSubParties {
    
    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> START_COMP_TAGS;
    
    protected static final Set<Integer> ALL_TAGS;
    
    protected static final Set<Integer> ROOT_PARTY_SUB_GROUP_TAGS = new RootPartySubGroup50SP2().getFragmentAllTags();
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Static Block">
    
    static {
        ALL_TAGS = new HashSet<Integer>(TAGS);
        ALL_TAGS.addAll(ROOT_PARTY_SUB_GROUP_TAGS);
        START_COMP_TAGS = new HashSet<Integer>(ROOT_PARTY_SUB_GROUP_TAGS);
    }
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Attributes">
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Constructors">
    
    public RootSubParties50SP2() {
        super();
    }
    
    public RootSubParties50SP2(FragmentContext context) {
        super(context);
    }
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Public Methods">
    
    @Override
    public Set<Integer> getFragmentTags() {
        return TAGS;
    }

    @Override
    public Set<Integer> getFragmentAllTags() {
        return ALL_TAGS;
    }

    // ACCESSOR METHODS
    //////////////////////////////////////////
    

    @Override
    public Integer getNoRootPartySubIDs() {
        return noRootPartySubIDs;
    }

    @Override
    public void setNoRootPartySubIDs(Integer noRootPartySubIDs) {
        this.noRootPartySubIDs = noRootPartySubIDs;
        if (noRootPartySubIDs != null) {
            rootPartySubGroups = new RootPartySubGroup[noRootPartySubIDs.intValue()];
            for (int i = 0; i < rootPartySubGroups.length; i++) {
                rootPartySubGroups[i] = new RootPartySubGroup50SP2(new FragmentContext(sessionCharset, messageEncoding, validateRequired));
            }
        }
    }

    @Override
    public RootPartySubGroup[] getRootPartySubGroups() {
        return rootPartySubGroups;
    }

    public void setRootPartySubGroups(RootPartySubGroup[] rootPartySubGroups) {
        this.rootPartySubGroups = rootPartySubGroups;
        if (rootPartySubGroups != null) {
            noRootPartySubIDs = new Integer(rootPartySubGroups.length);
        }
    }

    @Override
    public RootPartySubGroup addRootPartySubGroup() {
        RootPartySubGroup group = new RootPartySubGroup50SP2(new FragmentContext(sessionCharset, messageEncoding, validateRequired));
        List<RootPartySubGroup> groups = new ArrayList<RootPartySubGroup>();
        if (rootPartySubGroups != null && rootPartySubGroups.length > 0) {
            groups = new ArrayList<RootPartySubGroup>(Arrays.asList(rootPartySubGroups));
        }
        groups.add(group);
        rootPartySubGroups = groups.toArray(new RootPartySubGroup[groups.size()]);
        noRootPartySubIDs = new Integer(rootPartySubGroups.length);

        return group;
    }

    @Override
    public RootPartySubGroup deleteRootPartySubGroup(int index) {
        RootPartySubGroup result = null;
        if (rootPartySubGroups != null && rootPartySubGroups.length > 0 && rootPartySubGroups.length > index) {
            List<RootPartySubGroup> groups = new ArrayList<RootPartySubGroup>(Arrays.asList(rootPartySubGroups));
            result = groups.remove(index);
            rootPartySubGroups = groups.toArray(new RootPartySubGroup[groups.size()]);
            if (rootPartySubGroups.length > 0) {
                noRootPartySubIDs = new Integer(rootPartySubGroups.length);
            } else {
                rootPartySubGroups = null;
                noRootPartySubIDs = null;
            }
        }

        return result;
    }

    @Override
    public int clearRootPartySubGroups() {
        int result = 0;
        if (rootPartySubGroups != null && rootPartySubGroups.length > 0) {
            result = rootPartySubGroups.length;
            rootPartySubGroups = null;
            noRootPartySubIDs = null;
        }

        return result;
    }
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Protected Methods">
    
    @Override
    protected void setFragmentCompTagValue(Tag tag, ByteBuffer message)
        throws BadFormatMsgException, InvalidMsgException, TagNotPresentException {
        if (ROOT_PARTY_SUB_GROUP_TAGS.contains(tag.tagNum)) {
            if (noRootPartySubIDs != null && noRootPartySubIDs.intValue() > 0) {
                FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, validateRequired);
                message.reset();
                if (rootPartySubGroups == null) {
                    rootPartySubGroups = new RootPartySubGroup[noRootPartySubIDs.intValue()];
                }
                for (int i = 0; i < rootPartySubGroups.length; i++) {
                    RootPartySubGroup group = new RootPartySubGroup50SP2(context);
                    group.decode(message);
                    rootPartySubGroups[i] = group;
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
        return "This tag is not supported in [RootSubParties] group version [5.0SP2].";
    }
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Package Methods">
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Private Methods">
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Inner Classes">
    // </editor-fold>
}
