/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * InstrumentPtysSubGrp50SP2.java
 *
 * $Id: InstrumentPtysSubGrp50SP2.java,v 1.7 2011-04-14 23:44:49 vrotaru Exp $
 */
package net.hades.fix.message.comp.impl.v50sp2;

import net.hades.fix.message.struct.Tag;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.xml.bind.annotation.XmlTransient;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.comp.InstrumentPtysSubGrp;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.group.InstrumentPartySubIDGroup;
import net.hades.fix.message.group.impl.v50sp2.InstrumentPartySubIDGroup50SP2;

/**
 * FIX 5.0SP2 implementation of InstrumentPtysSubGrp component.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.7 $
 * @created 04/06/2009, 11:16:20 AM
 */
@XmlTransient
public class InstrumentPtysSubGrp50SP2 extends InstrumentPtysSubGrp {
    
    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> START_COMP_TAGS;
    
    protected static final Set<Integer> ALL_TAGS;
    
    protected static final Set<Integer> INSTRUMENT_PARTY_SUB_ID_GROUP_TAGS = new InstrumentPartySubIDGroup50SP2().getFragmentAllTags();
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Static Block">
    
    static {
        ALL_TAGS = new HashSet<Integer>(TAGS);
        ALL_TAGS.addAll(INSTRUMENT_PARTY_SUB_ID_GROUP_TAGS);
        START_COMP_TAGS = new HashSet<Integer>(INSTRUMENT_PARTY_SUB_ID_GROUP_TAGS);
    }
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Attributes">
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Constructors">
    
    public InstrumentPtysSubGrp50SP2() {
        super();
    }
    
    public InstrumentPtysSubGrp50SP2(FragmentContext context) {
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
    public Integer getNoInstrumentPartySubIDs() {
        return noInstrumentPartySubIDs;
    }

    @Override
    public void setNoInstrumentPartySubIDs(Integer noInstrumentPartySubIDs) {
        this.noInstrumentPartySubIDs = noInstrumentPartySubIDs;
        if (noInstrumentPartySubIDs != null) {
            instrumentPartySubIDGroups = new InstrumentPartySubIDGroup[noInstrumentPartySubIDs.intValue()];
            for (int i = 0; i < instrumentPartySubIDGroups.length; i++) {
                instrumentPartySubIDGroups[i] = new InstrumentPartySubIDGroup50SP2(new FragmentContext(sessionCharset, messageEncoding, validateRequired));
            }
        }
    }
    
    @Override
    public InstrumentPartySubIDGroup[] getInstrumentPartySubIDGroups() {
        return instrumentPartySubIDGroups;
    }

    public void setInstrumentPartySubIDGroups(InstrumentPartySubIDGroup[] instrumentPartySubIDGroups) {
        this.instrumentPartySubIDGroups = instrumentPartySubIDGroups;
        if (instrumentPartySubIDGroups != null) {
            noInstrumentPartySubIDs = new Integer(instrumentPartySubIDGroups.length);
        }
    }

    @Override
    public InstrumentPartySubIDGroup addInstrumentPartySubIDGroup() {

        InstrumentPartySubIDGroup group = new InstrumentPartySubIDGroup50SP2(new FragmentContext(sessionCharset, messageEncoding, validateRequired));
        List<InstrumentPartySubIDGroup> groups = new ArrayList<InstrumentPartySubIDGroup>();
        if (instrumentPartySubIDGroups != null && instrumentPartySubIDGroups.length > 0) {
            groups = new ArrayList<InstrumentPartySubIDGroup>(Arrays.asList(instrumentPartySubIDGroups));
        }
        groups.add(group);
        instrumentPartySubIDGroups = groups.toArray(new InstrumentPartySubIDGroup[groups.size()]);
        noInstrumentPartySubIDs = new Integer(instrumentPartySubIDGroups.length);

        return group;
    }

    @Override
    public InstrumentPartySubIDGroup deleteInstrumentPartySubIDGroup(int index) {

        InstrumentPartySubIDGroup result = null;
        if (instrumentPartySubIDGroups != null && instrumentPartySubIDGroups.length > 0 && instrumentPartySubIDGroups.length > index) {
            List<InstrumentPartySubIDGroup> groups = new ArrayList<InstrumentPartySubIDGroup>(Arrays.asList(instrumentPartySubIDGroups));
            result = groups.remove(index);
            instrumentPartySubIDGroups = groups.toArray(new InstrumentPartySubIDGroup[groups.size()]);
            if (instrumentPartySubIDGroups.length > 0) {
                noInstrumentPartySubIDs = new Integer(instrumentPartySubIDGroups.length);
            } else {
                instrumentPartySubIDGroups = null;
                noInstrumentPartySubIDs = null;
            }
        }

        return result;
    }

    @Override
    public int clearInstrumentPartySubIDGroups() {
        int result = 0;
        if (instrumentPartySubIDGroups != null && instrumentPartySubIDGroups.length > 0) {
            result = instrumentPartySubIDGroups.length;
            instrumentPartySubIDGroups = null;
            noInstrumentPartySubIDs = null;
        }

        return result;
    }
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Protected Methods">
    
    @Override
    protected void setFragmentCompTagValue(Tag tag, ByteBuffer message)
        throws BadFormatMsgException, InvalidMsgException, TagNotPresentException {
        if (noInstrumentPartySubIDs != null && noInstrumentPartySubIDs.intValue() > 0) {
            if (INSTRUMENT_PARTY_SUB_ID_GROUP_TAGS.contains(tag.tagNum)) {
                message.reset();
                instrumentPartySubIDGroups = new InstrumentPartySubIDGroup[noInstrumentPartySubIDs.intValue()];
                FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, validateRequired);
                for (int i = 0; i < noInstrumentPartySubIDs.intValue(); i++) {
                    InstrumentPartySubIDGroup group = new InstrumentPartySubIDGroup50SP2(context);
                    group.decode(message);
                    instrumentPartySubIDGroups[i] = group;
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
        return "This tag is not supported in [InstrumentPtysSubGrp] group version [5.0SP2].";
    }
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Package Methods">
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Private Methods">
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Inner Classes">
    // </editor-fold>
}
