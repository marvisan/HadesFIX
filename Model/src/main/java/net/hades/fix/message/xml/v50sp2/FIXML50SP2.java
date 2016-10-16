/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * FIXML50SP2.java
 *
 * $Id: FIXML50SP2.java,v 1.3 2011-04-27 23:28:24 vrotaru Exp $
 */
package net.hades.fix.message.xml.v50sp2;

import net.hades.fix.message.FIXMsg;
import net.hades.fix.message.comp.Batch;
import net.hades.fix.message.xml.FIXML;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import net.hades.fix.message.BatchSetMsg;

/**
 * Wrapper JAXB class for a FIXML message in Fix versions 4.4 and above.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.3 $
 * @created 27/05/2009, 3:47:36 PM
 */
@XmlRootElement(name = "FIXML")
@XmlType(propOrder = {"message", "batches"})
@XmlAccessorType(XmlAccessType.NONE)
public class FIXML50SP2 implements FIXML {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    @XmlAttribute(name="v")
    public static final String FIX_VERSION = "5.0";

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">

    @XmlElementRef(required=false)
    private FIXMsg message;

    @XmlElementRef(required=false)
    private Batch[] batches;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public FIXML50SP2() {
    }

    public FIXML50SP2(FIXMsg message) {
        if (message instanceof BatchSetMsg) {
            this.batches = ((BatchSetMsg) message).getBatches();
        } else {
            this.message = message;
        }
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Public Methods">

    @Override
    public FIXMsg getMessage() {
        return message;
    }

    public void setMessage(FIXMsg message) {
        this.message = message;
    }

    @Override
    public Batch[] getBatches() {
        return batches;
    }

    public void setBatches(Batch[] batches) {
        this.batches = batches;
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
