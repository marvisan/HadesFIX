/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * Event.java
 *
 * $Id: EventGroup.java,v 1.11 2011-09-19 08:15:45 vrotaru Exp $
 */
package net.hades.fix.message.group;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.anno.FIXVersion;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.struct.Tag;
import net.hades.fix.message.type.SessionRejectReason;
import net.hades.fix.message.type.TagNum;
import net.hades.fix.message.util.DateConverter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import net.hades.fix.message.anno.TagNumRef;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.util.TagEncoder;

/**
 * Event group.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.11 $
 * @created 06/12/2008, 12:12:02 PM
 */
@XmlAccessorType(XmlAccessType.NONE)
public abstract class EventGroup extends Group {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.EventType.getValue(),
        TagNum.EventDate.getValue(),
        TagNum.EventPx.getValue(),
        TagNum.EventText.getValue()
    }));

    protected static final Set<Integer> START_DATA_TAGS = null;

    protected static final Set<Integer> START_COMP_TAGS = null;

    protected static final Set<Integer> ALL_TAGS;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">

    static {
        ALL_TAGS = new HashSet<Integer>(TAGS);
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">

    /**
     * TagNum = 865. Starting with 5.0SP1 version.
     */
    protected Integer eventType;

    /**
     * TagNum = 866. Starting with 5.0SP1 version.
     */
    protected Date eventDate;

    /**
     * TagNum = 867. Starting with 5.0SP1 version.
     */
    protected Double eventPx;

    /** 
     * TagNum = 868. Starting with 5.0SP1 version.
     */
    protected String eventText;


    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public EventGroup() {
    }

    public EventGroup(FragmentContext context) {
        super(context);
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Public Methods">

    @Override
    public Set<Integer> getFragmentTags() {
        return TAGS;
    }

    @Override
    public Set<Integer> getFragmentAllTags() {
        return ALL_TAGS;
    }

    // ACCESSOR METHODS
    //////////////////////////////////////////

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.4")
    @TagNumRef(tagNum = TagNum.EventType)
    public Integer getEventType() {
        return eventType;
    }

    /**
     * Message field setter.
     * @param eventType field value
     */
    @FIXVersion(introduced = "4.4")
    @TagNumRef(tagNum = TagNum.EventType)
    public void setEventType(Integer eventType) {
        this.eventType = eventType;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.4")
    @TagNumRef(tagNum = TagNum.EventDate)
    public Date getEventDate() {
        return eventDate;
    }

    /**
     * Message field setter.
     * @param eventDate field value
     */
    @FIXVersion(introduced = "4.4")
    @TagNumRef(tagNum = TagNum.EventDate)
    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.4")
    @TagNumRef(tagNum = TagNum.EventPx)
    public Double getEventPx() {
        return eventPx;
    }

    /**
     * Message field setter.
     * @param eventPx field value
     */
    @FIXVersion(introduced = "4.4")
    @TagNumRef(tagNum = TagNum.EventPx)
    public void setEventPx(Double eventPx) {
        this.eventPx = eventPx;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.4")
    @TagNumRef(tagNum = TagNum.EventText)
    public String getEventText() {
        return eventText;
    }

    /**
     * Message field setter.
     * @param evenText field value
     */
    @FIXVersion(introduced = "4.4")
    @TagNumRef(tagNum = TagNum.EventText)
    public void setEventText(String evenText) {
        this.eventText = evenText;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

    @Override
    protected int getFirstTag() {
        return TagNum.EventType.getValue();
    }

    @Override
    protected void validateRequiredTags() throws TagNotPresentException {
        StringBuilder errorMsg = new StringBuilder("Tag value(s) for");
        boolean hasMissingTag = false;
        if (eventType == null) {
            errorMsg.append(" [EventType]");
            hasMissingTag = true;
        }
        errorMsg.append(" is missing.");
        if (hasMissingTag) {
            throw new TagNotPresentException(errorMsg.toString());
        }
    }

    @Override
    protected byte[] encodeFragmentAll() throws TagNotPresentException, BadFormatMsgException {
        if (validateRequired) {             validateRequiredTags();         }

        byte[] result = new byte[0];
        ByteArrayOutputStream bao = new ByteArrayOutputStream();

        try {
            TagEncoder.encode(bao, TagNum.EventType, eventType);
            TagEncoder.encodeDate(bao, TagNum.EventDate, eventDate);
            TagEncoder.encode(bao, TagNum.EventPx, eventPx);
            TagEncoder.encode(bao, TagNum.EventText, eventText, sessionCharset);
            result = bao.toByteArray();
        } catch (IOException ex) {
            String error = "Error writing to the byte array.";
            LOGGER.log(Level.SEVERE, "{0} Error was : {1}", new Object[] { error, ex.toString() });
            throw new BadFormatMsgException(error, ex);
        }

        return result;
    }

    @Override
    protected byte[] encodeFragmentSecured(boolean secured) throws TagNotPresentException, BadFormatMsgException {
        if (secured) {
            return new byte[0];
        } else {
            return encodeFragmentAll();
        }
    }

    @Override
    protected void setFragmentTagValue(Tag tag) throws BadFormatMsgException {
        TagNum tagNum = TagNum.fromString(tag.tagNum);
        switch (tagNum) {
            case EventType:
                eventType = new Integer(new String(tag.value, sessionCharset));
                break;

            case EventDate:
                eventDate = DateConverter.parseString(new String(tag.value, sessionCharset));
                break;

            case EventPx:
                eventPx = new Double(new String(tag.value, sessionCharset));
                break;

            case EventText:
                eventText = new String(tag.value, sessionCharset);
                break;

            default:
                String error = "Tag value [" + tag.tagNum + "] not present in [EventGroup] fields.";
                LOGGER.severe(error);
                throw new BadFormatMsgException(SessionRejectReason.InvalidTagNumber, tag.tagNum, error);
        }
    }

    @Override
    protected Set<Integer> getFragmentDataTags() {
        return START_DATA_TAGS;
    }

    @Override
    protected Set<Integer> getFragmentCompTags() {
        return START_COMP_TAGS;
    }

    @Override
    protected Set<Integer> getFragmentSecuredTags() {
        return SECURED_TAGS;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Package Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Private Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Inner Classes">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="toString()">

    @Override
    public String toString() {
        StringBuilder b = new StringBuilder(System.getProperty("line.separator"));
        b.append("{EventGroup=");
        printTagValue(b, TagNum.EventType, eventType);
        printDateTagValue(b, TagNum.EventDate, eventDate);
        printTagValue(b, TagNum.EventPx, eventPx);
        printTagValue(b, TagNum.EventText, eventText);
        b.append("}");

        return b.toString();
    }

    // </editor-fold>
}
