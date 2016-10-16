/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * SettlPartySubGroup50SP2.java
 *
 * $Id: SettlPartySubGroup50SP2.java,v 1.1 2011-02-10 10:02:14 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v50sp2;

import net.hades.fix.message.FragmentContext;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import net.hades.fix.message.group.SettlPartySubGroup;
import net.hades.fix.message.type.PartySubIDType;

/**
 * FIX 5.0SP2 implementation of SettlPartySubGroup group.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 11/02/2009, 8:46:04 PM
 */
@XmlRootElement(name="Sub")
@XmlType
@XmlAccessorType(XmlAccessType.NONE)
public class SettlPartySubGroup50SP2 extends SettlPartySubGroup {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public SettlPartySubGroup50SP2() {
    }

    public SettlPartySubGroup50SP2(FragmentContext context) {
        super(context);
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Public Methods">

    // ACCESSOR METHODS
    //////////////////////////////////////////

    @XmlAttribute(name = "ID")
    @Override
    public String getSettlPartySubID() {
        return settlPartySubID;
    }

    @Override
    public void setSettlPartySubID(String settlPartySubID) {
        this.settlPartySubID = settlPartySubID;
    }

    @XmlAttribute(name = "Typ")
    @Override
    public PartySubIDType getSettlPartySubIDType() {
        return settlPartySubIDType;
    }

    @Override
    public void setSettlPartySubIDType(PartySubIDType settlPartySubIDType) {
        this.settlPartySubIDType = settlPartySubIDType;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

    @Override
    protected String getUnsupportedTagMessage() {
        return "This tag is not supported in [SettlPartySubGroup] group version [5.0SP2].";
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Package Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Private Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Inner Classes">
    // </editor-fold>
}
