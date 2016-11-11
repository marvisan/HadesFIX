/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * DerivativeInstrumentPartySubIDGroup50SP2.java
 *
 * $Id: DerivativeInstrumentPartySubIDGroup50SP2.java,v 1.1 2011-09-19 08:15:45 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v50sp2;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.group.DerivativeInstrumentPartySubIDGroup;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import net.hades.fix.message.type.PartySubIDType;

/**
 * FIX 5.0SP2 implementation of DerivativeInstrumentPartySubIDGroup group.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 02/01/2009, 4:00:54 PM
 */
@XmlRootElement(name = "Sub")
@XmlType
@XmlAccessorType(XmlAccessType.NONE)
public class DerivativeInstrumentPartySubIDGroup50SP2 extends DerivativeInstrumentPartySubIDGroup {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public DerivativeInstrumentPartySubIDGroup50SP2() {
    }

    public DerivativeInstrumentPartySubIDGroup50SP2(FragmentContext context) {
        super(context);
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Public Methods">

    @XmlAttribute(name = "ID")
    @Override
    public String getDerivativeInstrumentPartySubID() {
        return derivativeInstrumentPartySubID;
    }

    @Override
    public void setDerivativeInstrumentPartySubID(String derivativeInstrumentPartySubID) {
        this.derivativeInstrumentPartySubID = derivativeInstrumentPartySubID;
    }

    @XmlAttribute(name = "Typ")
    @Override
    public PartySubIDType getDerivativeInstrumentPartySubIDType() {
        return derivativeInstrumentPartySubIDType;
    }

    @Override
    public void setDerivativeInstrumentPartySubIDType(PartySubIDType derivativeInstrumentPartySubIDType) {
        this.derivativeInstrumentPartySubIDType = derivativeInstrumentPartySubIDType;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

    @Override
    protected String getUnsupportedTagMessage() {
        return "This tag is not supported in [DerivativeInstrumentPartySubIDGroup] component version [5.0SP2].";
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Package Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Private Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Inner Classes">
    // </editor-fold>
}
