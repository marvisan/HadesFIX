/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * InstrumentParties50SP1.java
 *
 * $Id: InstrumentParties50SP1.java,v 1.8 2011-04-14 23:44:47 vrotaru Exp $
 */
package net.hades.fix.message.comp.impl.v50sp1;

import net.hades.fix.message.struct.Tag;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.xml.bind.annotation.XmlTransient;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.comp.InstrumentParties;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.group.InstrumentPartyGroup;
import net.hades.fix.message.group.impl.v50sp1.InstrumentPartyGroup50SP1;

/**
 * FIX 5.0SP1 implementation of InstrumentParties component.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.8 $
 * @created 02/01/2009, 12:07:02 PM
 */
@XmlTransient
public class InstrumentParties50SP1 extends InstrumentParties {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> START_COMP_TAGS;

    protected static final Set<Integer> ALL_TAGS;

    protected static final Set<Integer> INSTRUMENT_PARTY_GROUP_TAGS = new InstrumentPartyGroup50SP1().getFragmentAllTags();

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">

    static {
        ALL_TAGS = new HashSet<Integer>(TAGS);
        ALL_TAGS.addAll(INSTRUMENT_PARTY_GROUP_TAGS);
        START_COMP_TAGS = new HashSet<Integer>(INSTRUMENT_PARTY_GROUP_TAGS);
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public InstrumentParties50SP1() {
        super();
    }

    public InstrumentParties50SP1(FragmentContext context) {
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
    public Integer getNoInstrumentParties() {
        return noInstrumentParties;
    }

    @Override
    public void setNoInstrumentParties(Integer noInstrumentParties) {
        this.noInstrumentParties = noInstrumentParties;
        if (noInstrumentParties != null) {
            instrumentPartyGroups = new InstrumentPartyGroup[noInstrumentParties.intValue()];
            for (int i = 0; i < instrumentPartyGroups.length; i++) {
                instrumentPartyGroups[i] = new InstrumentPartyGroup50SP1(new FragmentContext(sessionCharset, messageEncoding, validateRequired));
            }
        }
    }

    @Override
    public InstrumentPartyGroup[] getInstrumentPartyGroups() {
        return instrumentPartyGroups;
    }
    
    public void setInstrumentPartyGroups(InstrumentPartyGroup[] instrumentPartyGroups) {
        this.instrumentPartyGroups = instrumentPartyGroups;
        if (instrumentPartyGroups != null) {
            noInstrumentParties = new Integer(instrumentPartyGroups.length);
        }
    }

    @Override
    public InstrumentPartyGroup addInstrumentPartyGroup() {

        InstrumentPartyGroup group = new InstrumentPartyGroup50SP1(new FragmentContext(sessionCharset, messageEncoding, validateRequired));
        List<InstrumentPartyGroup> groups = new ArrayList<InstrumentPartyGroup>();
        if (instrumentPartyGroups != null && instrumentPartyGroups.length > 0) {
            groups = new ArrayList<InstrumentPartyGroup>(Arrays.asList(instrumentPartyGroups));
        }
        groups.add(group);
        instrumentPartyGroups = groups.toArray(new InstrumentPartyGroup[groups.size()]);
        noInstrumentParties = new Integer(instrumentPartyGroups.length);

        return group;
    }

    @Override
    public InstrumentPartyGroup deleteInstrumentPartyGroup(int index) {

        InstrumentPartyGroup result = null;

        if (instrumentPartyGroups != null && instrumentPartyGroups.length > 0 && instrumentPartyGroups.length > index) {
            List<InstrumentPartyGroup> groups = new ArrayList<InstrumentPartyGroup>(Arrays.asList(instrumentPartyGroups));
            result = groups.remove(index);
            instrumentPartyGroups = groups.toArray(new InstrumentPartyGroup[groups.size()]);
            if (instrumentPartyGroups.length > 0) {
                noInstrumentParties = new Integer(instrumentPartyGroups.length);
            } else {
                instrumentPartyGroups = null;
                noInstrumentParties = null;
            }
        }

        return result;
    }

    @Override
    public int clearInstrumentPartyGroup() {

        int result = 0;
        if (instrumentPartyGroups != null && instrumentPartyGroups.length > 0) {
            result = instrumentPartyGroups.length;
            instrumentPartyGroups = null;
            noInstrumentParties = null;
        }

        return result;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

    @Override
    protected void setFragmentCompTagValue(Tag tag, ByteBuffer message)
    throws BadFormatMsgException, InvalidMsgException, TagNotPresentException {
        if (noInstrumentParties != null && noInstrumentParties.intValue() > 0) {
            if (INSTRUMENT_PARTY_GROUP_TAGS.contains(tag.tagNum)) {
                message.reset();
                instrumentPartyGroups = new InstrumentPartyGroup[noInstrumentParties.intValue()];
                FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, validateRequired);
                for (int i = 0; i < noInstrumentParties.intValue(); i++) {
                    InstrumentPartyGroup group = new InstrumentPartyGroup50SP1(context);
                    group.decode(message);
                    instrumentPartyGroups[i] = group;
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
        return "This tag is not supported in InstrumentParties component release [5.0].";
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Package Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Private Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Inner Classes">
    // </editor-fold>
}
