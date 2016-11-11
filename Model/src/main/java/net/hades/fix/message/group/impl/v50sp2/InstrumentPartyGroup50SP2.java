/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * InstrumentPartyGroup50SP2.java
 *
 * $Id: InstrumentPartyGroup50SP2.java,v 1.7 2011-04-14 23:44:31 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v50sp2;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.comp.InstrumentPtysSubGrp;
import net.hades.fix.message.comp.impl.v50sp2.InstrumentPtysSubGrp50SP2;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.group.InstrumentPartyGroup;
import net.hades.fix.message.group.InstrumentPartySubIDGroup;
import net.hades.fix.message.struct.Tag;
import net.hades.fix.message.type.PartyIDSource;
import net.hades.fix.message.type.PartyRole;
import net.hades.fix.message.type.TagNum;

import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import net.hades.fix.message.exception.BadFormatMsgException;

/**
 * FIX 5.0SP2 implementation of InstrumentPartyGroup group.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.7 $
 * @created 02/01/2009, 3:52:05 PM
 */
@XmlRootElement(name = "Pty")
@XmlType
@XmlAccessorType(XmlAccessType.NONE)
public class InstrumentPartyGroup50SP2 extends InstrumentPartyGroup {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = -3445043693761901175L;

    protected static final Set<Integer> TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.InstrumentPartyID.getValue(),
        TagNum.InstrumentPartyIDSource.getValue(),
        TagNum.InstrumentPartyRole.getValue()
    }));

    protected static final Set<Integer> START_COMP_TAGS;

    protected static final Set<Integer> ALL_TAGS;

    protected static final Set<Integer> INSTRUMENT_PARTY_SUB_ID_GRP_TAGS = new InstrumentPtysSubGrp50SP2().getFragmentAllTags();

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">

    static {
        ALL_TAGS = new HashSet<Integer>(TAGS);
        ALL_TAGS.addAll(INSTRUMENT_PARTY_SUB_ID_GRP_TAGS);
        START_COMP_TAGS = new HashSet<Integer>(INSTRUMENT_PARTY_SUB_ID_GRP_TAGS);
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public InstrumentPartyGroup50SP2() {
    }

    public InstrumentPartyGroup50SP2(FragmentContext context) {
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

    @XmlAttribute(name = "ID")
    @Override
    public String getInstrumentPartyID() {
        return instrumentPartyID;
    }

    @Override
    public void setInstrumentPartyID(String instrumentPartyID) {
        this.instrumentPartyID = instrumentPartyID;
    }

    @XmlAttribute(name = "Src")
    @Override
    public PartyIDSource getInstrumentPartyIDSource() {
        return instrumentPartyIDSource;
    }

    @Override
    public void setInstrumentPartyIDSource(PartyIDSource instrumentPartyIDSource) {
        this.instrumentPartyIDSource = instrumentPartyIDSource;
    }

    @XmlAttribute(name = "R")
    @Override
    public PartyRole getInstrumentPartyRole() {
        return instrumentPartyRole;
    }

    @Override
    public void setInstrumentPartyRole(PartyRole instrumentPartyRole) {
        this.instrumentPartyRole = instrumentPartyRole;
    }

    @Override
    public InstrumentPtysSubGrp getInstrumentPtysSubGrp() {
        return instrumentPtysSubGrp;
    }

    @Override
    public void setInstrumentPtysSubGrp() {
        FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, validateRequired);
        this.instrumentPtysSubGrp = new InstrumentPtysSubGrp50SP2(context);
    }

    @Override
    public void clearInstrumentPtysSubGrp() {
        this.instrumentPtysSubGrp = null;
    }

    @XmlElementRef
    public InstrumentPartySubIDGroup[] getInstrPtysSubGroups() {
        return instrumentPtysSubGrp == null ? null : instrumentPtysSubGrp.getInstrumentPartySubIDGroups();
    }

    public void setInstrPtysSubGroups(InstrumentPartySubIDGroup[] instrPtysSubGroups) {
        if (instrPtysSubGroups != null) {
            if (instrumentPtysSubGrp == null) {
                setInstrumentPtysSubGrp();
            }
            ((InstrumentPtysSubGrp50SP2) instrumentPtysSubGrp).setInstrumentPartySubIDGroups(instrPtysSubGroups);
        }
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

    @Override
    protected void setFragmentCompTagValue(Tag tag, ByteBuffer message)
    throws BadFormatMsgException, InvalidMsgException, TagNotPresentException {
        if (INSTRUMENT_PARTY_SUB_ID_GRP_TAGS.contains(tag.tagNum)) {
            if (instrumentPtysSubGrp == null) {
                FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, validateRequired);
                instrumentPtysSubGrp = new InstrumentPtysSubGrp50SP2(context);
            }
            instrumentPtysSubGrp.decode(tag, message);
        }
    }

    @Override
    protected Set<Integer> getFragmentCompTags() {
        return START_COMP_TAGS;
    }

    @Override
    protected String getUnsupportedTagMessage() {
        return "This tag is not supported in [InstrumentPartyGroup] component version [5.0SP2].";
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Package Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Private Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Inner Classes">
    // </editor-fold>
}
