/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * UndlyInstrumentParties50SP2.java
 *
 * $Id: UndlyInstrumentParties50SP2.java,v 1.7 2011-04-14 23:44:49 vrotaru Exp $
 */
package net.hades.fix.message.comp.impl.v50sp2;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.comp.UndlyInstrumentParties;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.group.UndlyInstrumentPartyIDGroup;
import net.hades.fix.message.group.impl.v50sp2.UndlyInstrumentPartyIDGroup50SP2;
import net.hades.fix.message.struct.Tag;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.xml.bind.annotation.XmlTransient;

import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.TagNotPresentException;

/**
 * FIX 5.0SP2 implementation of UndlyInstrumentParties component.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.7 $
 * @created 02/01/2009, 12:15:23 PM
 */
@XmlTransient
public class UndlyInstrumentParties50SP2 extends UndlyInstrumentParties {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = -4059808623192899738L;

    protected static final Set<Integer> START_COMP_TAGS;

    protected static final Set<Integer> ALL_TAGS;

    protected static final Set<Integer> UNDLY_INSTR_PARTY_ID_GROUP_TAGS = new UndlyInstrumentPartyIDGroup50SP2().getFragmentAllTags();
    
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">

    static {
        ALL_TAGS = new HashSet<Integer>(TAGS);
        ALL_TAGS.addAll(UNDLY_INSTR_PARTY_ID_GROUP_TAGS);
        START_COMP_TAGS = new HashSet<Integer>(UNDLY_INSTR_PARTY_ID_GROUP_TAGS);
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public UndlyInstrumentParties50SP2() {
        super();
    }

    public UndlyInstrumentParties50SP2(FragmentContext context) {
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
    public Integer getNoUndlyInstrumentParties() {
        return noUndlyInstrumentParties;
    }

    @Override
    public void setNoUndlyInstrumentParties(Integer noUndlyInstrumentParties) {
        this.noUndlyInstrumentParties = noUndlyInstrumentParties;
        if (noUndlyInstrumentParties != null) {
            undlyInstrumentPartyIDGroups = new UndlyInstrumentPartyIDGroup[noUndlyInstrumentParties.intValue()];
            for (int i = 0; i < undlyInstrumentPartyIDGroups.length; i++) {
                undlyInstrumentPartyIDGroups[i] = new UndlyInstrumentPartyIDGroup50SP2(new FragmentContext(sessionCharset, messageEncoding, validateRequired));
            }
        }
    }

    @Override
    public UndlyInstrumentPartyIDGroup[] getUndlyInstrumentPartyIDGroups() {
        return undlyInstrumentPartyIDGroups;
    }

    public void setUndlyInstrumentPartyIDGroups(UndlyInstrumentPartyIDGroup[] undlyInstrumentPartyIDGroups) {
        this.undlyInstrumentPartyIDGroups = undlyInstrumentPartyIDGroups;
    }

    @Override
    public UndlyInstrumentPartyIDGroup addUndlyInstrumentPartyIDGroup() {

        UndlyInstrumentPartyIDGroup group = new UndlyInstrumentPartyIDGroup50SP2(new FragmentContext(sessionCharset, messageEncoding, validateRequired));
        List<UndlyInstrumentPartyIDGroup> groups = new ArrayList<UndlyInstrumentPartyIDGroup>();
        if (undlyInstrumentPartyIDGroups != null && undlyInstrumentPartyIDGroups.length > 0) {
            groups = new ArrayList<UndlyInstrumentPartyIDGroup>(Arrays.asList(undlyInstrumentPartyIDGroups));
        }
        groups.add(group);
        undlyInstrumentPartyIDGroups = groups.toArray(new UndlyInstrumentPartyIDGroup[groups.size()]);
        noUndlyInstrumentParties = new Integer(undlyInstrumentPartyIDGroups.length);

        return group;
    }

    @Override
    public UndlyInstrumentPartyIDGroup deleteUndlyInstrumentPartyIDGroup(int index) {

        UndlyInstrumentPartyIDGroup result = null;

        if (undlyInstrumentPartyIDGroups != null && undlyInstrumentPartyIDGroups.length > 0 && undlyInstrumentPartyIDGroups.length > index) {
            List<UndlyInstrumentPartyIDGroup> groups = new ArrayList<UndlyInstrumentPartyIDGroup>(Arrays.asList(undlyInstrumentPartyIDGroups));
            result = groups.remove(index);
            undlyInstrumentPartyIDGroups = groups.toArray(new UndlyInstrumentPartyIDGroup[groups.size()]);
            if (undlyInstrumentPartyIDGroups.length > 0) {
                noUndlyInstrumentParties = new Integer(undlyInstrumentPartyIDGroups.length);
            } else {
                undlyInstrumentPartyIDGroups = null;
                noUndlyInstrumentParties = null;
            }
        }

        return result;
    }

    @Override
    public int clearUndlyInstrumentPartyIDGroup() {

        int result = 0;
        if (undlyInstrumentPartyIDGroups != null && undlyInstrumentPartyIDGroups.length > 0) {
            result = undlyInstrumentPartyIDGroups.length;
            undlyInstrumentPartyIDGroups = null;
            noUndlyInstrumentParties = null;
        }

        return result;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

    @Override
    protected void setFragmentCompTagValue(Tag tag, ByteBuffer message)
    throws BadFormatMsgException, InvalidMsgException, TagNotPresentException {
        if (noUndlyInstrumentParties != null && noUndlyInstrumentParties.intValue() > 0) {
            if (UNDLY_INSTR_PARTY_ID_GROUP_TAGS.contains(tag.tagNum)) {
                message.reset();
                undlyInstrumentPartyIDGroups = new UndlyInstrumentPartyIDGroup[noUndlyInstrumentParties.intValue()];
                FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, validateRequired);
                for (int i = 0; i < noUndlyInstrumentParties.intValue(); i++) {
                    UndlyInstrumentPartyIDGroup group = new UndlyInstrumentPartyIDGroup50SP2(context);
                    group.decode(message);
                    undlyInstrumentPartyIDGroups[i] = group;
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
        return "This tag is not supported in [UndlyInstrumentParties] component version [5.0SP2].";
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Package Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Private Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Inner Classes">
    // </editor-fold>
}
