/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * Stipulations50SP2.java
 *
 * $Id: Stipulations50SP2.java,v 1.8 2011-04-14 23:44:49 vrotaru Exp $
 */
package net.hades.fix.message.comp.impl.v50sp2;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.comp.Stipulations;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.group.StipulationsGroup;
import net.hades.fix.message.group.impl.v50sp2.StipulationsGroup50SP2;
import net.hades.fix.message.struct.Tag;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.xml.bind.annotation.XmlTransient;

/**
 * FIX version 5.0SP2 implementation of Stipulations component.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.8 $
 * @created 15/02/2009, 5:05:06 PM
 */
@XmlTransient
public class Stipulations50SP2 extends Stipulations {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> START_COMP_TAGS;

    protected static final Set<Integer> ALL_TAGS;

    protected static final Set<Integer> STIPS_GROUP_TAGS = new StipulationsGroup50SP2().getFragmentAllTags();

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">

     static {
        ALL_TAGS = new HashSet<Integer>(TAGS);
        ALL_TAGS.addAll(STIPS_GROUP_TAGS);
        START_COMP_TAGS = new HashSet<Integer>(STIPS_GROUP_TAGS);
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public Stipulations50SP2() {
        super();
    }

    public Stipulations50SP2(FragmentContext context) {
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
    public Integer getNoStipulations() {
        return noStipulations;
    }

    @Override
    public void setNoStipulations(Integer noStipulations) {
        this.noStipulations = noStipulations;
        if (noStipulations != null) {
            stipulationsGroups = new StipulationsGroup[noStipulations.intValue()];
            for (int i = 0; i < stipulationsGroups.length; i++) {
                stipulationsGroups[i] = new StipulationsGroup50SP2(new FragmentContext(sessionCharset, messageEncoding, validateRequired));
            }
        }
    }

    @Override
    public StipulationsGroup[] getStipulationsGroups() {
        return stipulationsGroups;
    }

    @Override
    public StipulationsGroup addStipulationsGroup() {

        StipulationsGroup group = new StipulationsGroup50SP2(new FragmentContext(sessionCharset, messageEncoding, validateRequired));
        List<StipulationsGroup> groups = new ArrayList<StipulationsGroup>();
        if (stipulationsGroups != null && stipulationsGroups.length > 0) {
            groups = new ArrayList<StipulationsGroup>(Arrays.asList(stipulationsGroups));
        }
        groups.add(group);
        stipulationsGroups = groups.toArray(new StipulationsGroup[groups.size()]);
        noStipulations = new Integer(stipulationsGroups.length);

        return group;
    }

    public void setStipulationsGroups(StipulationsGroup[] stipulationsGroups) {
        this.stipulationsGroups = stipulationsGroups;
        if (stipulationsGroups != null) {
            noStipulations = new Integer(stipulationsGroups.length);
        }
    }

    @Override
    public StipulationsGroup deleteStipulationsGroup(int index) {
        StipulationsGroup result = null;
        if (stipulationsGroups != null && stipulationsGroups.length > 0 && stipulationsGroups.length > index) {
            List<StipulationsGroup> groups = new ArrayList<StipulationsGroup>(Arrays.asList(stipulationsGroups));
            result = groups.remove(index);
            stipulationsGroups = groups.toArray(new StipulationsGroup[groups.size()]);
            if (stipulationsGroups.length > 0) {
                noStipulations = new Integer(stipulationsGroups.length);
            } else {
                stipulationsGroups = null;
                noStipulations = null;
            }
        }

        return result;
    }

    @Override
    public int clearStipulationsGroups() {
        int result = 0;
        if (stipulationsGroups != null && stipulationsGroups.length > 0) {
            result = stipulationsGroups.length;
            stipulationsGroups = null;
            noStipulations = null;
        }

        return result;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

    @Override
    protected void setFragmentCompTagValue(Tag tag, ByteBuffer message)
    throws BadFormatMsgException, InvalidMsgException, TagNotPresentException {
        if (STIPS_GROUP_TAGS.contains(tag.tagNum)) {
            if (noStipulations != null && noStipulations.intValue() > 0) {
                message.reset();
                stipulationsGroups = new StipulationsGroup[noStipulations.intValue()];
                FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, validateRequired);
                for (int i = 0; i < noStipulations.intValue(); i++) {
                    StipulationsGroup group = new StipulationsGroup50SP2(context);
                    group.decode(message);
                    stipulationsGroups[i] = group;
                }
            }
        }
    }

    @Override
    protected String getUnsupportedTagMessage() {
        return "This tag is not supported in [Stipulations] component version [5.0SP2].";
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
