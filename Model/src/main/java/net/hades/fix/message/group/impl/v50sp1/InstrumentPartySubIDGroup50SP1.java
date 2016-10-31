/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * InstrumentPartySubIDGroup50SP1.java
 *
 * $Id: InstrumentPartySubIDGroup50SP1.java,v 1.3 2010-02-04 10:11:08 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v50sp1;

import net.hades.fix.message.FragmentContext;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import net.hades.fix.message.group.InstrumentPartySubIDGroup;
import net.hades.fix.message.type.PartySubIDType;

/**
 * FIX 5.0SP1 implementation of InstrumentPartySubIDGroup group.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.3 $
 * @created 02/01/2009, 4:00:54 PM
 */
@XmlRootElement(name = "Sub")
@XmlType
@XmlAccessorType(XmlAccessType.NONE)
public class InstrumentPartySubIDGroup50SP1 extends InstrumentPartySubIDGroup {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = -7822457522613978081L;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public InstrumentPartySubIDGroup50SP1() {
    }

    public InstrumentPartySubIDGroup50SP1(FragmentContext context) {
        super(context);
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Public Methods">

    @XmlAttribute(name = "ID")
    @Override
    public String getInstrumentPartySubID() {
        return instrumentPartySubID;
    }

    @Override
    public void setInstrumentPartySubID(String instrumentPartySubID) {
        this.instrumentPartySubID = instrumentPartySubID;
    }

    @XmlAttribute(name = "Typ")
    @Override
    public PartySubIDType getInstrumentPartySubIDType() {
        return instrumentPartySubIDType;
    }

    @Override
    public void setInstrumentPartySubIDType(PartySubIDType instrumentPartySubIDType) {
        this.instrumentPartySubIDType = instrumentPartySubIDType;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

    @Override
    protected String getUnsupportedTagMessage() {
        return "This tag is not supported in [InstrumentPartySubIDGroup] component version [5.0SP1].";
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Package Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Private Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Inner Classes">
    // </editor-fold>
}
