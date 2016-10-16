/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*  
 * DerivativeEventGroup50SP1.java
 *
 * $Id: DerivativeEventGroup50SP1.java,v 1.1 2011-09-19 08:15:45 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v50sp1;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.xml.codec.jaxb.adapter.FixDateAdapter;
import net.hades.fix.message.xml.codec.jaxb.adapter.FixUTCDateTimeAdapter;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import net.hades.fix.message.group.DerivativeEventGroup;

/**
 * FIX 5.0SP1 implementation of DerivativeEventGroup group.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 02/01/2009, 1:36:28 PM
 */
@XmlRootElement(name = "Evnt")
@XmlType
@XmlAccessorType(XmlAccessType.NONE)
public class DerivativeEventGroup50SP1 extends DerivativeEventGroup {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">
    
    public DerivativeEventGroup50SP1() {
    }

    public DerivativeEventGroup50SP1(FragmentContext context) {
        super(context);
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Public Methods">

    // ACCESSOR METHODS
    //////////////////////////////////////////

    @XmlAttribute(name = "EventTyp")
    @Override
    public Integer getDerivativeEventType() {
        return derivativeEventType;
    }

    @Override
    public void setDerivativeEventType(Integer derivativeEventType) {
        this.derivativeEventType = derivativeEventType;
    }

    @XmlAttribute(name = "Dt")
    @XmlJavaTypeAdapter(FixDateAdapter.class)
    @Override
    public Date getDerivativeEventDate() {
        return derivativeEventDate;
    }

    @Override
    public void setDerivativeEventDate(Date derivativeEventDate) {
        this.derivativeEventDate = derivativeEventDate;
    }

    @XmlAttribute(name = "Tm")
    @XmlJavaTypeAdapter(FixUTCDateTimeAdapter.class)
    @Override
    public Date getDerivativeEventTime() {
        return derivativeEventTime;
    }

    @Override
    public void setDerivativeEventTime(Date derivativeEventTime) {
        this.derivativeEventTime = derivativeEventTime;
    }

    @XmlAttribute(name = "Px")
    @Override
    public Double getDerivativeEventPx() {
        return derivativeEventPx;
    }

    @Override
    public void setDerivativeEventPx(Double derivativeEventPx) {
        this.derivativeEventPx = derivativeEventPx;
    }

    @XmlAttribute(name = "Txt")
    @Override
    public String getDerivativeEventText() {
        return derivativeEventText;
    }

    @Override
    public void setDerivativeEventText(String derivativeEventText) {
        this.derivativeEventText = derivativeEventText;
    }
    
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

    @Override
    protected String getUnsupportedTagMessage() {
        return "This tag is not supported in [DerivativeEventGroup] component release [5.0SP1].";
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Package Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Private Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Inner Classes">
    // </editor-fold>
}
