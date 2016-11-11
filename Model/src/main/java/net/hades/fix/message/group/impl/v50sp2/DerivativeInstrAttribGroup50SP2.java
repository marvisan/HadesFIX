/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*  
 * DerivativeInstrAttribGroup50SP1.java
 *
 * $Id: DerivativeInstrAttribGroup50SP2.java,v 1.1 2011-09-27 08:57:27 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v50sp2;

import net.hades.fix.message.FragmentContext;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import net.hades.fix.message.group.DerivativeInstrAttribGroup;
import net.hades.fix.message.type.InstrAttribType;

/**
 * FIX 5.0SP2 implementation of DerivativeInstrAttribGroup group.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 02/01/2009, 1:36:28 PM
 */
@XmlRootElement(name = "Attrb")
@XmlType
@XmlAccessorType(XmlAccessType.NONE)
public class DerivativeInstrAttribGroup50SP2 extends DerivativeInstrAttribGroup {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">
    
    public DerivativeInstrAttribGroup50SP2() {
    }

    public DerivativeInstrAttribGroup50SP2(FragmentContext context) {
        super(context);
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Public Methods">

    // ACCESSOR METHODS
    //////////////////////////////////////////

    @XmlAttribute(name = "Typ")
    @Override
    public InstrAttribType getDerivativeInstrAttribType() {
        return derivativeInstrAttribType;
    }

    @Override
    public void setDerivativeInstrAttribType(InstrAttribType derivativeInstrAttribType) {
        this.derivativeInstrAttribType = derivativeInstrAttribType;
    }

    @XmlAttribute(name = "Val")
    @Override
    public String getDerivativeInstrAttribValue() {
        return derivativeInstrAttribValue;
    }

    @Override
    public void setDerivativeInstrAttribValue(String derivativeInstrAttribValue) {
        this.derivativeInstrAttribValue = derivativeInstrAttribValue;
    }
    
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

    @Override
    protected String getUnsupportedTagMessage() {
        return "This tag is not supported in [DerivativeInstrAttribGroup] component release [5.0SP2].";
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Package Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Private Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Inner Classes">
    // </editor-fold>
}
