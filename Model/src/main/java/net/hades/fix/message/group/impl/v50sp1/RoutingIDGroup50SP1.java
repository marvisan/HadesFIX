/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * RoutingIDGroup50SP1.java
 *
 * $Id: RoutingIDGroup50SP1.java,v 1.4 2010-02-25 08:37:44 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v50sp1;

import net.hades.fix.message.type.RoutingType;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.group.RoutingIDGroup;

/**
 * FIX 5.0SP1 implementation of RoutingIDGroup group.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.4 $
 * @created 21/02/2009, 5:01:35 PM
 */
@XmlRootElement(name="Rtg")
@XmlType
@XmlAccessorType(XmlAccessType.NONE)
public class RoutingIDGroup50SP1 extends RoutingIDGroup {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public RoutingIDGroup50SP1() {
    }

    public RoutingIDGroup50SP1(FragmentContext context) {
        super(context);
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Public Methods">

    @XmlAttribute(name = "RtgTyp")
    @Override
    public RoutingType getRoutingType() {
        return routingType;
    }

    @Override
    public void setRoutingType(RoutingType routingType) {
        this.routingType = routingType;
    }

    @XmlAttribute(name = "RtgID")
    @Override
    public String getRoutingID() {
        return routingID;
    }

    @Override
    public void setRoutingID(String routingID) {
        this.routingID = routingID;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Package Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Private Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Inner Classes">
    // </editor-fold>
}
