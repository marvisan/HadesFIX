/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * Nested3PartySubGroup50SP2.java
 *
 * $Id: Nested3PartySubGroup50SP2.java,v 1.1 2010-12-22 09:30:32 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v50sp2;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.group.Nested3PartySubGroup;
import net.hades.fix.message.type.PartySubIDType;

/**
 * FIX 5.0 SP2 implementation of NestedPartysSubGroup group.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 06/04/2009, 2:37:46 PM
 */
@XmlRootElement(name="Sub")
@XmlType
@XmlAccessorType(XmlAccessType.NONE)
public class Nested3PartySubGroup50SP2 extends Nested3PartySubGroup {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public Nested3PartySubGroup50SP2() {
    }

    public Nested3PartySubGroup50SP2(FragmentContext context) {
        super(context);
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Public Methods">

    // ACCESSOR METHODS
    //////////////////////////////////////////

    @XmlAttribute(name = "ID")
    @Override
    public String getNested3PartySubID() {
        return nested3PartySubID;
    }

    @Override
    public void setNested3PartySubID(String nested3PartySubID) {
        this.nested3PartySubID = nested3PartySubID;
    }

    @XmlAttribute(name = "Typ")
    @Override
    public PartySubIDType getNested3PartySubIDType() {
        return nested3PartySubIDType;
    }

    @Override
    public void setNested3PartySubIDType(PartySubIDType nested3PartySubIDType) {
        this.nested3PartySubIDType = nested3PartySubIDType;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

    @Override
    protected String getUnsupportedTagMessage() {
        return "This tag is not supported in [Nested3PartySubGroup] group version [5.0SP2].";
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Package Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Private Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Inner Classes">
    // </editor-fold>
}
