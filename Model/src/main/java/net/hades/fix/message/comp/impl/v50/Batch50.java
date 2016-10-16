/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * Batch50.java
 *
 * $Id: Batch50.java,v 1.1 2011-04-27 23:28:24 vrotaru Exp $
 */
package net.hades.fix.message.comp.impl.v50;

import net.hades.fix.message.Header;
import net.hades.fix.message.impl.v50.HeaderT11_50;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import net.hades.fix.message.FIXMsg;
import net.hades.fix.message.comp.Batch;

/**
 * FIX 5.0 implementation of batch.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 27/04/2011
 */
@XmlRootElement(name="Batch")
@XmlType(propOrder = {"header", "messages"})
@XmlAccessorType(XmlAccessType.NONE)
public class Batch50 extends Batch {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Public Methods">

    @XmlElementRef
    @Override
    public Header getHeader() {
        return header;
    }

    @Override
    public void setHeader() {
        this.header = new HeaderT11_50();
    }

    public void setHeader(Header header) {
        this.header = header;
    }

    @XmlAttribute(name = "ID")
    @Override
    public String getID() {
        return ID;
    }

    @Override
    public void setID(String ID) {
        this.ID = ID;
    }

    @XmlAttribute(name = "TotMsg")
    @Override
    public Integer getTotMsg() {
        return totMsg;
    }

    @XmlElementRef
    @Override
    public FIXMsg[] getMessages() {
        return messages;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

    @Override
    protected String getUnsupportedTagMessage() {
        return "This tag is not supported in [Batch] component version [5.0].";
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Package Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Private Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Inner Classes">
    // </editor-fold>
}
