/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * NestedParties350SP2.java
 *
 * $Id: NestedParties350SP2.java,v 1.2 2011-04-14 23:44:49 vrotaru Exp $
 */
package net.hades.fix.message.comp.impl.v50sp2;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.comp.NestedParties3;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.group.Nested3PartyGroup;
import net.hades.fix.message.group.impl.v50sp2.Nested3PartyGroup50SP2;
import net.hades.fix.message.struct.Tag;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.xml.bind.annotation.XmlTransient;

/**
 * FIX version 5.0SP2 implementation of NestedParties3 component.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 * @created 07/04/3009, 8:40:36 AM
 */
@XmlTransient
public class NestedParties350SP2 extends NestedParties3 {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> START_COMP_TAGS;

    protected static final Set<Integer> ALL_TAGS;

    protected static final Set<Integer> NESTED3_PARTY_GROUP_TAGS = new Nested3PartyGroup50SP2().getFragmentAllTags();

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">

    static {
        ALL_TAGS = new HashSet<Integer>(TAGS);
        ALL_TAGS.addAll(NESTED3_PARTY_GROUP_TAGS);
        START_COMP_TAGS = new HashSet<Integer>(NESTED3_PARTY_GROUP_TAGS);
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public NestedParties350SP2() {
        super();
    }

    public NestedParties350SP2(FragmentContext context) {
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
    public Nested3PartyGroup[] getNested3PartyIDGroups() {
        return nested3PartyGroups;
    }

    public void setNested3PartyIDGroups(Nested3PartyGroup[] nested3PartyGroups) {
        this.nested3PartyGroups = nested3PartyGroups;
        if (nested3PartyGroups != null) {
            noNested3PartyIDs = new Integer(nested3PartyGroups.length);
        }
    }

    @Override
    public void setNoNested3PartyIDs(Integer noNested3PartyIDs) {
        this.noNested3PartyIDs = noNested3PartyIDs;
        if (noNested3PartyIDs != null) {
            nested3PartyGroups = new Nested3PartyGroup[noNested3PartyIDs.intValue()];
            for (int i = 0; i < nested3PartyGroups.length; i++) {
                nested3PartyGroups[i] = new Nested3PartyGroup50SP2(new FragmentContext(sessionCharset, messageEncoding, validateRequired));
            }
        }
    }

    @Override
    public Nested3PartyGroup addNested3PartyGroup() {
        Nested3PartyGroup group = new Nested3PartyGroup50SP2(new FragmentContext(sessionCharset, messageEncoding, validateRequired));
        List<Nested3PartyGroup> groups = new ArrayList<Nested3PartyGroup>();
        if (nested3PartyGroups != null && nested3PartyGroups.length > 0) {
            groups = new ArrayList<Nested3PartyGroup>(Arrays.asList(nested3PartyGroups));
        }
        groups.add(group);
        nested3PartyGroups = groups.toArray(new Nested3PartyGroup[groups.size()]);
        noNested3PartyIDs = new Integer(nested3PartyGroups.length);

        return group;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

    @Override
    protected void setFragmentCompTagValue(Tag tag, ByteBuffer message)
    throws BadFormatMsgException, InvalidMsgException, TagNotPresentException {
        if (NESTED3_PARTY_GROUP_TAGS.contains(tag.tagNum)) {
            if (noNested3PartyIDs != null && noNested3PartyIDs.intValue() > 0) {
                FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, validateRequired);
                message.reset();
                if (nested3PartyGroups == null) {
                    nested3PartyGroups = new Nested3PartyGroup50SP2[noNested3PartyIDs.intValue()];
                }
                for (int i = 0; i < nested3PartyGroups.length; i++) {
                    Nested3PartyGroup group = new Nested3PartyGroup50SP2(context);
                    group.decode(message);
                    nested3PartyGroups[i] = group;
                }
            }
        }
    }

    @Override
    protected String getUnsupportedTagMessage() {
        return "This tag is not supported in [NestedParties3] component version [5.0SP3].";
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
