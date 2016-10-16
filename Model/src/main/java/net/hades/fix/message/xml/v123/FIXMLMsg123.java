/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * FIXMLMsg123.java
 *
 * $Id: FIXMLMsg123.java,v 1.2 2010-02-26 23:09:34 vrotaru Exp $
 */
package net.hades.fix.message.xml.v123;

import net.hades.fix.message.AdvertisementMsg;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementRefs;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import net.hades.fix.message.FIXMsg;
import net.hades.fix.message.FIXFragment;
import net.hades.fix.message.Header;

/**
 * Wrapper JAXB class for a FIXML message version 4.1, 4.2 and 4.3.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 * @created 20/05/2009, 1:45:35 PM
 */
@XmlRootElement(name="FIXMLMessage")
@XmlType(propOrder = {"header", "applicationMessage"})
@XmlAccessorType(XmlAccessType.NONE)
public class FIXMLMsg123 {

    // <editor-fold defaultstate="collapsed" desc="Constants">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">

    @XmlElementRef(type = Header.class)
    private FIXFragment header;

    @XmlElement(name="ApplicationMessage")
    private ApplicationMessage applicationMessage = new ApplicationMessage();

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public FIXMLMsg123() {
    }

    public FIXMLMsg123(Header header, FIXMsg message) {
        this.header = header;
        applicationMessage.setMessage(message);
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Public Methods">
    
    public Header getHeader() {
        return (Header) header;
    }

    public void setHeader(Header header) {
        this.header = header;
    }

    public ApplicationMessage getApplicationMessage() {
        return applicationMessage;
    }

    public void setApplicationMessage(ApplicationMessage applicationMessage) {
        this.applicationMessage = applicationMessage;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Package Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Private Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Inner Classes">

    @XmlRootElement(name = "ApplicationMessage")
    @XmlType(propOrder = {"message"})
    @XmlAccessorType(XmlAccessType.NONE)
    public static class ApplicationMessage {

        @XmlElementRefs({
            @XmlElementRef(type = AdvertisementMsg.class)
//            @XmlElementRef(type = IOIMsg.class),
//            @XmlElementRef(type = NewsMsg.class),
//            @XmlElementRef(type = EmailMsg.class),
//            @XmlElementRef(type = QuoteMsg.class)
        })
        private FIXMsg message;

        public FIXMsg getMessage() {
            return message;
        }

        public void setMessage(FIXMsg message) {
            this.message = message;
        }
    }

    // </editor-fold>
}
