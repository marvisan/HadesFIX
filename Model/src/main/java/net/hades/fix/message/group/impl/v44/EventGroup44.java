/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * EventGroup44.java
 *
 * $Id: EventGroup44.java,v 1.6 2010-08-25 05:30:25 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v44;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.group.EventGroup;
import net.hades.fix.message.type.TagNum;
import net.hades.fix.message.util.MsgUtil;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Set;
import java.util.logging.Level;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.util.TagEncoder;
import net.hades.fix.message.xml.codec.jaxb.adapter.FixDateAdapter;

/**
 * FIX 4.4 implementation of EventGroup group.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.6 $
 * @created 02/01/2009, 2:02:50 PM
 */
@XmlRootElement(name = "Evnt")
@XmlType
@XmlAccessorType(XmlAccessType.NONE)
public class EventGroup44 extends EventGroup {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">

    protected Set<Integer> STANDARD_SECURED_TAGS = TAGS;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public EventGroup44() {
        SECURED_TAGS = STANDARD_SECURED_TAGS;
    }

    public EventGroup44(FragmentContext context) {
        super(context);
        SECURED_TAGS = STANDARD_SECURED_TAGS;
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
    protected byte[] encodeFragmentSecured(boolean secured) throws TagNotPresentException, BadFormatMsgException {

        byte[] result = new byte[0];
        ByteArrayOutputStream bao = new ByteArrayOutputStream();

        try {
            if (MsgUtil.isTagInList(TagNum.EventType, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.EventType, eventType);
            }
            if (MsgUtil.isTagInList(TagNum.EventDate, SECURED_TAGS, secured)) {
                TagEncoder.encodeDate(bao, TagNum.EventDate, eventDate);
            }
            if (MsgUtil.isTagInList(TagNum.EventPx, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.EventPx, eventPx);
            }
            if (MsgUtil.isTagInList(TagNum.EventText, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.EventText, eventText, sessionCharset);
            }
            result = bao.toByteArray();
        } catch (IOException ex) {
            String error = "Error writing to the byte array.";
            LOGGER.log(Level.SEVERE, "{0} Error was : {1}", new Object[] { error, ex.toString() });
            throw new BadFormatMsgException(error, ex);
        }

        return result;
    }

    @Override
    protected String getUnsupportedTagMessage() {
        return "This tag is not supported in [EventGroup] component release [4.4].";
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Package Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Private Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Inner Classes">
    // </editor-fold>
}
