/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * RootParties50SP1.java
 *
 * $Id: RootParties50SP1.java,v 1.7 2011-04-14 23:44:47 vrotaru Exp $
 */
package net.hades.fix.message.comp.impl.v50sp1;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.comp.RootParties;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.struct.Tag;
import net.hades.fix.message.type.TagNum;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.xml.bind.annotation.XmlTransient;

import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.group.RootPartyGroup;
import net.hades.fix.message.group.impl.v50sp1.RootPartyGroup50SP1;

/**
 * FIX version 5.0SP1 implementation of RootParties component.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.7 $
 * @created 08/04/2009, 2:53:26 PM
 */
@XmlTransient
public class RootParties50SP1 extends RootParties {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.NoRootPartyIDs.getValue()
    }));

    protected static final Set<Integer> START_DATA_TAGS = null;

    protected static final Set<Integer> START_COMP_TAGS;

    protected static final Set<Integer> REQUIRED_TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.NoRootPartyIDs.getValue()
    }));

    protected static final Set<Integer> ALL_TAGS;

    protected static final Set<Integer> ROOT_PARTY_GROUP_TAGS = new RootPartyGroup50SP1().getFragmentAllTags();

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">

    static {
        ALL_TAGS = new HashSet<Integer>(TAGS);
        ALL_TAGS.addAll(ROOT_PARTY_GROUP_TAGS);
        START_COMP_TAGS = new HashSet<Integer>(ROOT_PARTY_GROUP_TAGS);
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public RootParties50SP1() {
        super();
    }

    public RootParties50SP1(FragmentContext context) {
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
    public void setNoRootPartyIDs(Integer noRootPartyIDs) {
        this.noRootPartyIDs = noRootPartyIDs;
        if (noRootPartyIDs != null) {
            rootPartyGroups = new RootPartyGroup[noRootPartyIDs.intValue()];
            for (int i = 0; i < rootPartyGroups.length; i++) {
                rootPartyGroups[i] = new RootPartyGroup50SP1(new FragmentContext(sessionCharset, messageEncoding, validateRequired));
            }
        }
    }

    @Override
    public RootPartyGroup addRootPartyGroup() {
        RootPartyGroup group = new RootPartyGroup50SP1(new FragmentContext(sessionCharset, messageEncoding, validateRequired));
        List<RootPartyGroup> groups = new ArrayList<RootPartyGroup>();
        if (rootPartyGroups != null && rootPartyGroups.length > 0) {
            groups = new ArrayList<RootPartyGroup>(Arrays.asList(rootPartyGroups));
        }
        groups.add(group);
        rootPartyGroups = groups.toArray(new RootPartyGroup[groups.size()]);
        noRootPartyIDs = new Integer(rootPartyGroups.length);

        return group;
    }

    public void setRootPartyIDGroups(RootPartyGroup[] rootPartyIDGroups) {
        this.rootPartyGroups = rootPartyIDGroups;
        if (rootPartyGroups != null) {
            noRootPartyIDs = new Integer(rootPartyIDGroups.length);
        }
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

    @Override
    protected void setFragmentCompTagValue(Tag tag, ByteBuffer message)
    throws BadFormatMsgException, InvalidMsgException, TagNotPresentException {
        if (ROOT_PARTY_GROUP_TAGS.contains(tag.tagNum)) {
            if (noRootPartyIDs != null && noRootPartyIDs.intValue() > 0) {
                FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, validateRequired);
                message.reset();
                if (rootPartyGroups == null) {
                    rootPartyGroups = new RootPartyGroup[noRootPartyIDs.intValue()];
                }
                for (int i = 0; i < rootPartyGroups.length; i++) {
                    RootPartyGroup group = new RootPartyGroup50SP1(context);
                    group.decode(message);
                    rootPartyGroups[i] = group;
                }
            }
        }
    }

    @Override
    protected String getUnsupportedTagMessage() {
        return "This tag is not supported in [RootParties] component version [5.0SP1].";
    }

    @Override
    protected Set<Integer> getFragmentDataTags() {
        return START_DATA_TAGS;
    }

    @Override
    protected Set<Integer> getFragmentCompTags() {
        return START_COMP_TAGS;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Package Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Private Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Inner Classes">
    // </editor-fold>
}
