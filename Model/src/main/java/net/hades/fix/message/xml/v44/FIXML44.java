/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * FIXML44.java
 *
 * $Id: FIXML44.java,v 1.3 2011-04-27 23:28:24 vrotaru Exp $
 */
package net.hades.fix.message.xml.v44;

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
public class FIXML44 implements FIXML {

    @XmlAttribute(name="v")
    public static final String FIX_VERSION = "4.4";

    @XmlElementRef(required=false)
    private FIXMsg message;

    @XmlElementRef(required=false)
    private Batch[] batches;

    public FIXML44() {
    }

    public FIXML44(FIXMsg message) {
        if (message instanceof BatchSetMsg) {
            this.batches = ((BatchSetMsg) message).getBatches();
        } else {
            this.message = message;
        }
    }

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
}
