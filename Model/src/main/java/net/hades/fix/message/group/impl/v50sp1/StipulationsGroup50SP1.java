/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * StipulationsGroup50SP1.java
 *
 * $Id: StipulationsGroup50SP1.java,v 1.4 2010-02-25 08:37:43 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v50sp1;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.group.StipulationsGroup;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * StipulationsGroup implementation for FIX version 5.0SP1.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.4 $
 * @created 15/02/2009, 11:18:58 AM
 */
@XmlRootElement(name="Stip")
@XmlType
@XmlAccessorType(XmlAccessType.NONE)
public class StipulationsGroup50SP1 extends StipulationsGroup {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public StipulationsGroup50SP1() {
    }

    public StipulationsGroup50SP1(FragmentContext context) {
        super(context);
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Public Methods">

    @XmlAttribute(name = "Typ")
    @Override
    public String getStipulationType() {
        return stipulationType;
    }

    @Override
    public void setStipulationType(String underlyingStipType) {
        this.stipulationType = underlyingStipType;
    }

    @XmlAttribute(name = "Val")
    @Override
    public String getStipulationValue() {
        return stipulationValue;
    }

    @Override
    public void setStipulationValue(String underlyingStipValue) {
        this.stipulationValue = underlyingStipValue;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

    @Override
    protected String getUnsupportedTagMessage() {
        return "This tag is not supported in [StipulationsGroup] group version [5.0SP1].";
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Package Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Private Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Inner Classes">
    // </editor-fold>
}
