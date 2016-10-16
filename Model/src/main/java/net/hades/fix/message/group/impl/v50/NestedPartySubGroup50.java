/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * NestedPartysSubGroup50.java
 *
 * $Id: NestedPartySubGroup50.java,v 1.3 2010-02-04 10:11:07 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v50;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.group.NestedPartySubGroup;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import net.hades.fix.message.type.PartySubIDType;

/**
 * FIX 5.0 implementation of NestedPartysSubGroup group.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.3 $
 * @created 06/04/2009, 2:37:46 PM
 */
@XmlRootElement(name="Sub")
@XmlType
@XmlAccessorType(XmlAccessType.NONE)
public class NestedPartySubGroup50 extends NestedPartySubGroup {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = -1001404534308155884L;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public NestedPartySubGroup50() {
    }

    public NestedPartySubGroup50(FragmentContext context) {
        super(context);
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Public Methods">

    // ACCESSOR METHODS
    //////////////////////////////////////////

    @XmlAttribute(name = "ID")
    @Override
    public String getNestedPartySubID() {
        return nestedPartySubID;
    }

    @Override
    public void setNestedPartySubID(String nestedPartySubID) {
        this.nestedPartySubID = nestedPartySubID;
    }

    @XmlAttribute(name = "Typ")
    @Override
    public PartySubIDType getNestedPartySubIDType() {
        return nestedPartySubIDType;
    }

    @Override
    public void setNestedPartySubIDType(PartySubIDType nestedPartySubIDType) {
        this.nestedPartySubIDType = nestedPartySubIDType;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

    @Override
    protected String getUnsupportedTagMessage() {
        return "This tag is not supported in [NestedPartySubGroup] group version [5.0].";
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Package Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Private Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Inner Classes">
    // </editor-fold>
}
