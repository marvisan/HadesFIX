/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * FIXMLMsg.java
 *
 * $Id: FIXML123.java,v 1.2 2011-04-27 23:28:23 vrotaru Exp $
 */
package net.hades.fix.message.xml.v123;

import net.hades.fix.message.FIXMsg;
import net.hades.fix.message.comp.Batch;
import net.hades.fix.message.xml.FIXML;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Wrapper JAXB class for a FIXML message in Fix versions 4.1, 4.2 and 4.3.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 * @created 20/05/2009, 1:45:35 PM
 */
@XmlRootElement(name = "FIXML")
@XmlType(propOrder = {"messages"})
@XmlAccessorType(XmlAccessType.NONE)
public class FIXML123 implements FIXML {

    // <editor-fold defaultstate="collapsed" desc="Constants">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">

    @XmlElement(name="FIXMLMessage")
    private FIXMLMsg123[] messages;

    @XmlAttribute(name="FIXVersion")
    private String fixVersion;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public FIXML123() {
        this.fixVersion = "4.1";
    }

    public FIXML123(String fixVersion) {
        this.fixVersion = fixVersion;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Public Methods">

    public FIXMLMsg123[] getMessages() {
        return messages;
    }

    @Override
    public Batch[] getBatches() {
        throw new UnsupportedOperationException("Not supported in these versions.");
    }

    public void setMessages(FIXMLMsg123[] messages) {
        this.messages = messages;
    }

    @Override
    public FIXMsg getMessage() {
        FIXMsg result = null;
        if (messages != null && messages.length > 0) {
            result = messages[0].getApplicationMessage().getMessage();
            result.setHeader(messages[0].getHeader());
        }

        return result;
    }

    public void addFIXMLMsg(FIXMLMsg123 message) {
        List<FIXMLMsg123> msgs = new ArrayList<FIXMLMsg123>();
        if (messages != null && messages.length > 0) {
            msgs = new ArrayList<FIXMLMsg123>(Arrays.asList(messages));
        }
        msgs.add(message);
        messages = msgs.toArray(new FIXMLMsg123[msgs.size()]);
    }

    public FIXMLMsg123 deleteFIXMLMsg(int index) {
        FIXMLMsg123 result = null;
        if (messages != null && messages.length > 0 && messages.length > index) {
            List<FIXMLMsg123> msgs = new ArrayList<FIXMLMsg123>(Arrays.asList(messages));
            result = msgs.remove(index);
            messages = msgs.toArray(new FIXMLMsg123[msgs.size()]);
            if (messages.length == 0) {
                messages = null;
            }
        }

        return result;
    }

    public void clearFIXMLMsg() {
        messages = null;
    }

    public String getFixVersion() {
        return fixVersion;
    }

    public void setFixVersion(String fixVersion) {
        this.fixVersion = fixVersion;
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
