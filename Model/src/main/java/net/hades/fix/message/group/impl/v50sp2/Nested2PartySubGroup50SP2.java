/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * Nested2PartySubGroup50SP2.java
 *
 * $Id: Nested2PartySubGroup50SP2.java,v 1.1 2010-12-22 09:30:32 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v50sp2;

import net.hades.fix.message.FragmentContext;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import net.hades.fix.message.group.Nested2PartySubGroup;
import net.hades.fix.message.type.PartySubIDType;

/**
 * FIX 5.0SP2 implementation of NestedPartysSubGroup group.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 06/04/2009, 2:37:46 PM
 */
@XmlRootElement(name="Sub")
@XmlType
@XmlAccessorType(XmlAccessType.NONE)
public class Nested2PartySubGroup50SP2 extends Nested2PartySubGroup {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public Nested2PartySubGroup50SP2() {
    }

    public Nested2PartySubGroup50SP2(FragmentContext context) {
        super(context);
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Public Methods">

    // ACCESSOR METHODS
    //////////////////////////////////////////

    @XmlAttribute(name = "ID")
    @Override
    public String getNested2PartySubID() {
        return nested2PartySubID;
    }

    @Override
    public void setNested2PartySubID(String nested2PartySubID) {
        this.nested2PartySubID = nested2PartySubID;
    }

    @XmlAttribute(name = "Typ")
    @Override
    public PartySubIDType getNested2PartySubIDType() {
        return nested2PartySubIDType;
    }

    @Override
    public void setNested2PartySubIDType(PartySubIDType nested2PartySubIDType) {
        this.nested2PartySubIDType = nested2PartySubIDType;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

    @Override
    protected String getUnsupportedTagMessage() {
        return "This tag is not supported in [Nested2PartySubGroup] group version [5.0SP2].";
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Package Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Private Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Inner Classes">
    // </editor-fold>
}
