/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * UnderlyingStipsGroup50.java
 *
 * $Id: UnderlyingStipsGroup50.java,v 1.3 2010-02-04 10:11:07 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v50;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.group.UnderlyingStipsGroup;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * FIX 5.0 implementation of UnderlyingStipsGroup.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.3 $
 * @created 28/12/2008, 11:57:16 AM
 */
@XmlRootElement(name="Stip")
@XmlType
@XmlAccessorType(XmlAccessType.NONE)
public class UnderlyingStipsGroup50 extends UnderlyingStipsGroup {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public UnderlyingStipsGroup50() {
    }

    public UnderlyingStipsGroup50(FragmentContext context) {
        super(context);
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Public Methods">

    @XmlAttribute(name = "Typ")
    @Override
    public String getUnderlyingStipType() {
        return underlyingStipType;
    }

    @Override
    public void setUnderlyingStipType(String underlyingStipType) {
        this.underlyingStipType = underlyingStipType;
    }

    @XmlAttribute(name = "Val")
    @Override
    public String getUnderlyingStipValue() {
        return underlyingStipValue;
    }

    @Override
    public void setUnderlyingStipValue(String underlyingStipValue) {
        this.underlyingStipValue = underlyingStipValue;
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
