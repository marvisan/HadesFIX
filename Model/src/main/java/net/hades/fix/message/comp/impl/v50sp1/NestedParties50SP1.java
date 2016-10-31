/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * NestedParties50SP1.java
 *
 * $Id: NestedParties50SP1.java,v 1.7 2011-04-14 23:44:47 vrotaru Exp $
 */
package net.hades.fix.message.comp.impl.v50sp1;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.struct.Tag;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.xml.bind.annotation.XmlTransient;

import net.hades.fix.message.comp.NestedParties;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.group.NestedPartyGroup;
import net.hades.fix.message.group.impl.v50sp1.NestedPartyGroup50SP1;

/**
 * FIX version 5.0SP1 implementation of NestedParties component.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.7 $
 * @created 07/04/2009, 8:40:36 AM
 */
@XmlTransient
public class NestedParties50SP1 extends NestedParties {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = -2183743492198799951L;

    protected static final Set<Integer> START_COMP_TAGS;

    protected static final Set<Integer> ALL_TAGS;

    protected static final Set<Integer> NESTED_PARTY_GROUP_TAGS = new NestedPartyGroup50SP1().getFragmentAllTags();

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">

    static {
        ALL_TAGS = new HashSet<Integer>(TAGS);
        ALL_TAGS.addAll(NESTED_PARTY_GROUP_TAGS);
        START_COMP_TAGS = new HashSet<Integer>(NESTED_PARTY_GROUP_TAGS);
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public NestedParties50SP1() {
        super();
    }

    public NestedParties50SP1(FragmentContext context) {
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
    public NestedPartyGroup[] getNestedPartyIDGroups() {
        return nestedPartyGroups;
    }

    public void setNestedPartyIDGroups(NestedPartyGroup[] nestedPartyGroups) {
        this.nestedPartyGroups = nestedPartyGroups;
        if (nestedPartyGroups != null) {
            noNestedPartyIDs = new Integer(nestedPartyGroups.length);
        }
    }

    @Override
    public void setNoNestedPartyIDs(Integer noNestedPartyIDs) {
        this.noNestedPartyIDs = noNestedPartyIDs;
        if (noNestedPartyIDs != null) {
            nestedPartyGroups = new NestedPartyGroup[noNestedPartyIDs.intValue()];
            for (int i = 0; i < nestedPartyGroups.length; i++) {
                nestedPartyGroups[i] = new NestedPartyGroup50SP1(new FragmentContext(sessionCharset, messageEncoding, validateRequired));
            }
        }
    }

    @Override
    public NestedPartyGroup addNestedPartyGroup() {
        NestedPartyGroup group = new NestedPartyGroup50SP1(new FragmentContext(sessionCharset, messageEncoding, validateRequired));
        List<NestedPartyGroup> groups = new ArrayList<NestedPartyGroup>();
        if (nestedPartyGroups != null && nestedPartyGroups.length > 0) {
            groups = new ArrayList<NestedPartyGroup>(Arrays.asList(nestedPartyGroups));
        }
        groups.add(group);
        nestedPartyGroups = groups.toArray(new NestedPartyGroup[groups.size()]);
        noNestedPartyIDs = new Integer(nestedPartyGroups.length);

        return group;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

    @Override
    protected void setFragmentCompTagValue(Tag tag, ByteBuffer message)
    throws BadFormatMsgException, InvalidMsgException, TagNotPresentException {
        if (NESTED_PARTY_GROUP_TAGS.contains(tag.tagNum)) {
            if (noNestedPartyIDs != null && noNestedPartyIDs.intValue() > 0) {
                FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, validateRequired);
                message.reset();
                if (nestedPartyGroups == null) {
                    nestedPartyGroups = new NestedPartyGroup[noNestedPartyIDs.intValue()];
                }
                for (int i = 0; i < nestedPartyGroups.length; i++) {
                    NestedPartyGroup group = new NestedPartyGroup50SP1(context);
                    group.decode(message);
                    nestedPartyGroups[i] = group;
                }
            }
        }
    }

    @Override
    protected String getUnsupportedTagMessage() {
        return "This tag is not supported in [NestedParties] component version [5.0SP1].";
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
