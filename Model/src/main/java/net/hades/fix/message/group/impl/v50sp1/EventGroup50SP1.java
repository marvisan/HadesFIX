/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * EventGroup50SP1.java
 *
 * $Id: EventGroup50SP1.java,v 1.4 2011-04-17 09:30:46 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v50sp1;

import net.hades.fix.message.FragmentContext;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import net.hades.fix.message.group.EventGroup;
import net.hades.fix.message.xml.codec.jaxb.adapter.FixDateAdapter;

/**
 * FIX 5.0SP1 implementation of EventGroup group.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.4 $
 * @created 02/01/2009, 1:36:28 PM
 */
@XmlRootElement(name = "Evnt")
@XmlType
@XmlAccessorType(XmlAccessType.NONE)
public class EventGroup50SP1 extends EventGroup {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">
    
    public EventGroup50SP1() {
    }

    public EventGroup50SP1(FragmentContext context) {
        super(context);
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Public Methods">

    // ACCESSOR METHODS
    //////////////////////////////////////////

    @XmlAttribute(name = "EventTyp")
    @Override
    public Integer getEventType() {
        return eventType;
    }

    @Override
    public void setEventType(Integer eventType) {
        this.eventType = eventType;
    }

    @XmlAttribute(name = "Dt")
    @XmlJavaTypeAdapter(FixDateAdapter.class)
    @Override
    public Date getEventDate() {
        return eventDate;
    }

    @Override
    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
    }

    @XmlAttribute(name = "Px")
    @Override
    public Double getEventPx() {
        return eventPx;
    }

    @Override
    public void setEventPx(Double eventPx) {
        this.eventPx = eventPx;
    }

    @XmlAttribute(name = "Txt")
    @Override
    public String getEventText() {
        return eventText;
    }

    @Override
    public void setEventText(String evenText) {
        this.eventText = evenText;
    }
    
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

    @Override
    protected String getUnsupportedTagMessage() {
        return "This tag is not supported in [EventGroup] component release [5.0SP1].";
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Package Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Private Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Inner Classes">
    // </editor-fold>
}
