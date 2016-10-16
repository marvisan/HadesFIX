/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * PartySubGroup50.java
 *
 * $Id: PartySubGroup50.java,v 1.3 2010-02-04 10:11:07 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v50;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.group.PartySubGroup;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import net.hades.fix.message.type.PartySubIDType;

/**
 * FIX 5.0 implementation of PartySubGroup group.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.3 $
 * @created 11/02/2009, 8:46:04 PM
 */
@XmlRootElement(name="Sub")
@XmlType
@XmlAccessorType(XmlAccessType.NONE)
public class PartySubGroup50 extends PartySubGroup {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = -5389893617426706321L;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public PartySubGroup50() {
    }

    public PartySubGroup50(FragmentContext context) {
        super(context);
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Public Methods">

    // ACCESSOR METHODS
    //////////////////////////////////////////

    @XmlAttribute(name = "ID")
    @Override
    public String getPartySubID() {
        return partySubID;
    }

    @Override
    public void setPartySubID(String partySubID) {
        this.partySubID = partySubID;
    }

    @XmlAttribute(name = "Typ")
    @Override
    public PartySubIDType getPartySubIDType() {
        return partySubIDType;
    }

    @Override
    public void setPartySubIDType(PartySubIDType partySubIDType) {
        this.partySubIDType = partySubIDType;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

    @Override
    protected String getUnsupportedTagMessage() {
        return "This tag is not supported in [PartySubGroup] group version [5.0].";
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Package Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Private Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Inner Classes">
    // </editor-fold>
}
