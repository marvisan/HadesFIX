/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * RootPartySubGroup50.java
 *
 * $Id: RootPartySubGroup50.java,v 1.5 2011-02-02 10:03:15 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v50;

import net.hades.fix.message.FragmentContext;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import net.hades.fix.message.group.RootPartySubGroup;
import net.hades.fix.message.type.PartySubIDType;

/**
 * FIX 5.0 implementation of RootPartySubGroup group.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.5 $
 * @created 08/04/2009, 2:26:00 PM
 */
@XmlRootElement(name="RtSubPrtys")
@XmlType
@XmlAccessorType(XmlAccessType.NONE)
public class RootPartySubGroup50 extends RootPartySubGroup {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public RootPartySubGroup50() {
    }

    public RootPartySubGroup50(FragmentContext context) {
        super(context);
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Public Methods">

    // ACCESSOR METHODS
    //////////////////////////////////////////

    @XmlAttribute(name = "RtPtySubID")
    @Override
    public String getRootPartySubID() {
        return rootPartySubID;
    }

    @Override
    public void setRootPartySubID(String rootPartySubID) {
        this.rootPartySubID = rootPartySubID;
    }

    @XmlAttribute(name = "RtPtySubIDTyp")
    @Override
    public PartySubIDType getRootPartySubIDType() {
        return rootPartySubIDType;
    }

    @Override
    public void setRootPartySubIDType(PartySubIDType rootPartySubIDType) {
        this.rootPartySubIDType = rootPartySubIDType;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

    @Override
    protected String getUnsupportedTagMessage() {
        return "This tag is not supported in [RootPartySubGroup] group version [5.0].";
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Package Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Private Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Inner Classes">
    // </editor-fold>
}
