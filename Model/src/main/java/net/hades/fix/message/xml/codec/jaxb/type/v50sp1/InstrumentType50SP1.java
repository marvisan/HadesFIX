/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * InstrumentType50SP1.java
 *
 * $Id: InstrumentType50SP1.java,v 1.2 2010-02-04 10:11:10 vrotaru Exp $
 */
package net.hades.fix.message.xml.codec.jaxb.type.v50sp1;

import net.hades.fix.message.comp.impl.v50sp1.Instrument50SP1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Wrapper class for Instrument component used in FIXML.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 * @created 21/06/2009, 3:23:23 PM
 */
@XmlRootElement(name = "Inst")
@XmlType
@XmlAccessorType(XmlAccessType.NONE)
public class InstrumentType50SP1 {

    // <editor-fold defaultstate="collapsed" desc="Constants">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">

    @XmlElement(name = "Instrmt")
    private Instrument50SP1 instrument;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public InstrumentType50SP1() {
    }

    public InstrumentType50SP1(Instrument50SP1 instrument) {
        this.instrument = instrument;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Public Methods">

    public Instrument50SP1 getInstrument() {
        return instrument;
    }

    public void setInstrument(Instrument50SP1 instrument) {
        this.instrument = instrument;
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
