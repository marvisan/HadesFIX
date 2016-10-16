/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * UndlyInstrumentPartySubIDGroup50SP1.java
 *
 * $Id: UndlyInstrumentPartySubIDGroup50SP1.java,v 1.6 2010-02-25 08:37:43 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v50sp1;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.group.UndlyInstrumentPartySubIDGroup;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import net.hades.fix.message.type.PartySubIDType;
import net.hades.fix.message.type.TagNum;

/**
 * FIX 5.0SP1 implementation of UndlyInstrumentPartySubIDGroup.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.6 $
 * @created 02/01/2009, 10:56:28 AM
 */
@XmlRootElement(name="Sub")
@XmlType
@XmlAccessorType(XmlAccessType.NONE)
public class UndlyInstrumentPartySubIDGroup50SP1 extends UndlyInstrumentPartySubIDGroup {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.UndlyInstrumentPartySubID.getValue(),
        TagNum.UndlyInstrumentPartySubIDType.getValue()
    }));

    protected static final Set<Integer> START_DATA_TAGS = null;

    protected static final Set<Integer> START_COMP_TAGS = null;

    protected static final Set<Integer> ALL_TAGS;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">

    static {
        ALL_TAGS = new HashSet<Integer>(TAGS);
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">

    protected Set<Integer> STANDARD_SECURED_TAGS = TAGS;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public UndlyInstrumentPartySubIDGroup50SP1() {
    }

    public UndlyInstrumentPartySubIDGroup50SP1(FragmentContext context) {
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
    public String getUndlyInstrumentPartySubID() {
        return undlyInstrumentPartySubID;
    }

    @Override
    public void setUndlyInstrumentPartySubID(String undlyInstrumentPartySubID) {
        this.undlyInstrumentPartySubID = undlyInstrumentPartySubID;
    }

    @XmlAttribute(name = "Typ")
    @Override
    public PartySubIDType getUndlyInstrumentPartySubIDType() {
        return undlyInstrumentPartySubIDType;
    }

    @Override
    public void setUndlyInstrumentPartySubIDType(PartySubIDType undlyInstrumentPartySubIDType) {
        this.undlyInstrumentPartySubIDType = undlyInstrumentPartySubIDType;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

    @Override
    protected Set<Integer> getFragmentDataTags() {
        return START_DATA_TAGS;
    }

    @Override
    protected Set<Integer> getFragmentCompTags() {
        return START_COMP_TAGS;
    }

    @Override
    protected String getUnsupportedTagMessage() {
        return "This tag is not supported in [UndlyInstrumentPartySubIDGroup] component version [5.0SP1].";
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Package Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Private Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Inner Classes">
    // </editor-fold>
}
