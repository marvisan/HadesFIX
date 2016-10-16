/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * LegStipulations50SP2.java
 *
 * $Id: LegStipulations50SP2.java,v 1.8 2011-04-14 23:44:49 vrotaru Exp $
 */
package net.hades.fix.message.comp.impl.v50sp2;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.group.LegStipulationsGroup;
import net.hades.fix.message.struct.Tag;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.xml.bind.annotation.XmlTransient;

import net.hades.fix.message.comp.LegStipulations;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.group.impl.v50sp2.LegStipulationsGroup50SP2;

/**
 * FIX version 5.0SP2 implementation of LegStipulations component.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.8 $
 * @created 15/02/2009, 6:47:28 PM
 */
@XmlTransient
public class LegStipulations50SP2 extends LegStipulations {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> START_COMP_TAGS;

    protected static final Set<Integer> ALL_TAGS;

    protected static final Set<Integer> LEG_STIPS_GROUP_TAGS = new LegStipulationsGroup50SP2().getFragmentAllTags();

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">

     static {
        ALL_TAGS = new HashSet<Integer>(TAGS);
        ALL_TAGS.addAll(LEG_STIPS_GROUP_TAGS);
        START_COMP_TAGS = new HashSet<Integer>(LEG_STIPS_GROUP_TAGS);
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public LegStipulations50SP2() {
        super();
    }

    public LegStipulations50SP2(FragmentContext context) {
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
    public LegStipulationsGroup[] getLegStipulationsGroups() {
        return legStipulationsGroups;
    }

    public void setLegStipulationsGroups(LegStipulationsGroup[] legStipulationsGroups) {
        this.legStipulationsGroups = legStipulationsGroups;
        if (legStipulationsGroups != null) {
            noLegStipulations = new Integer(legStipulationsGroups.length);
        }
    }

    @Override
    public void setNoLegStipulations(Integer noLegStipulations) {
        this.noLegStipulations = noLegStipulations;
        if (noLegStipulations != null) {
            legStipulationsGroups = new LegStipulationsGroup[noLegStipulations.intValue()];
            for (int i = 0; i < legStipulationsGroups.length; i++) {
                legStipulationsGroups[i] = new LegStipulationsGroup50SP2(new FragmentContext(sessionCharset, messageEncoding, validateRequired));
            }
        }
    }

    @Override
    public LegStipulationsGroup addLegStipulationsGroup() {

        LegStipulationsGroup group = new LegStipulationsGroup50SP2(new FragmentContext(sessionCharset, messageEncoding, validateRequired));
        List<LegStipulationsGroup> groups = new ArrayList<LegStipulationsGroup>();
        if (legStipulationsGroups != null && legStipulationsGroups.length > 0) {
            groups = new ArrayList<LegStipulationsGroup>(Arrays.asList(legStipulationsGroups));
        }
        groups.add(group);
        legStipulationsGroups = groups.toArray(new LegStipulationsGroup[groups.size()]);
        noLegStipulations = new Integer(legStipulationsGroups.length);

        return group;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

    @Override
    protected void setFragmentCompTagValue(Tag tag, ByteBuffer message)
    throws BadFormatMsgException, InvalidMsgException, TagNotPresentException {
        if (noLegStipulations != null && noLegStipulations.intValue() > 0) {
            if (LEG_STIPS_GROUP_TAGS.contains(tag.tagNum)) {
                message.reset();
                legStipulationsGroups = new LegStipulationsGroup[noLegStipulations.intValue()];
                FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, validateRequired);
                for (int i = 0; i < noLegStipulations.intValue(); i++) {
                    LegStipulationsGroup group = new LegStipulationsGroup50SP2(context);
                    group.decode(message);
                    legStipulationsGroups[i] = group;
                }
            }
        }
    }

    @Override
    protected String getUnsupportedTagMessage() {
        return "This tag is not supported in [LegStipulations] component version [5.0SP2].";
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
