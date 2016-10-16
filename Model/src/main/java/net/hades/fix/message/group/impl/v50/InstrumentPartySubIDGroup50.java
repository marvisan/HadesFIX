/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * InstrumentPartySubIDGroup50.java
 *
 * $Id: InstrumentPartySubIDGroup50.java,v 1.3 2010-02-04 10:11:07 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v50;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.group.InstrumentPartySubIDGroup;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import net.hades.fix.message.type.PartySubIDType;

/**
 * FIX 5.0 implementation of InstrumentPartySubIDGroup group.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.3 $
 * @created 02/01/2009, 4:00:54 PM
 */
@XmlRootElement(name = "Sub")
@XmlType
@XmlAccessorType(XmlAccessType.NONE)
public class InstrumentPartySubIDGroup50 extends InstrumentPartySubIDGroup {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = -7822457522613978081L;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public InstrumentPartySubIDGroup50() {
    }

    public InstrumentPartySubIDGroup50(FragmentContext context) {
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
        return "This tag is not supported in [InstrumentPartySubIDGroup] component version [5.0].";
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Package Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Private Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Inner Classes">
    // </editor-fold>
}
