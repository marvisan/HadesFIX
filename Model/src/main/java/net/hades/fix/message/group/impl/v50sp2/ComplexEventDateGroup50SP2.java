/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * ComplexEventTimeGroup50SP2.java
 *
 * $Id: ComplexEventDateGroup50SP2.java,v 1.5 2011-04-14 23:44:32 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v50sp2;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.comp.impl.v50sp2.ComplexEventTimes50SP2;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.group.ComplexEventDateGroup;
import net.hades.fix.message.group.ComplexEventTimeGroup;
import net.hades.fix.message.struct.Tag;
import net.hades.fix.message.xml.codec.jaxb.adapter.FixUTCDateTimeAdapter;

import java.nio.ByteBuffer;
import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import net.hades.fix.message.comp.ComplexEventTimes;

/**
 * FIX 5.0SP2 implementation of ComplexEventTimeGroup group.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.5 $
 * @created 04/06/2009, 10:46:01 AM
 */
@XmlRootElement(name = "EvntDts")
@XmlType
@XmlAccessorType(XmlAccessType.NONE)
public class ComplexEventDateGroup50SP2 extends ComplexEventDateGroup {
    
    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;
 
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Static Block">
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Attributes">
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Constructors">
    
    public ComplexEventDateGroup50SP2() {
    }
    
    public ComplexEventDateGroup50SP2(FragmentContext context) {
        super(context);
    }
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Public Methods">

    @XmlAttribute(name = "StartDt")
    @XmlJavaTypeAdapter(FixUTCDateTimeAdapter.class)
    @Override
    public Date getComplexEventStartDate() {
        return complexEventStartDate;
    }

    @Override
    public void setComplexEventStartDate(Date complexEventStartDate) {
        this.complexEventStartDate = complexEventStartDate;
    }

    @XmlAttribute(name = "EndDt")
    @XmlJavaTypeAdapter(FixUTCDateTimeAdapter.class)
    @Override
    public Date getComplexEventEndDate() {
        return complexEventEndDate;
    }

    @Override
    public void setComplexEventEndDate(Date complexEventEndDate) {
        this.complexEventEndDate = complexEventEndDate;
    }

    @Override
    public ComplexEventTimes getComplexEventTimes() {
        return complexEventTimes;
    }

    @Override
    public void setComplexEventTimes() {
        FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, validateRequired);
        this.complexEventTimes = new ComplexEventTimes50SP2(context);
    }

    @XmlElementRef
    public ComplexEventTimeGroup[] getComplexEventTimeGroups() {
        return complexEventTimes == null ? null : complexEventTimes.getComplexEventTimeGroups();
    }

    public void setComplexEventTimeGroups(ComplexEventTimeGroup[] complexEventTimeGroups) {
        if (complexEventTimeGroups != null) {
            if (complexEventTimes == null) {
                setComplexEventTimes();
            }
            ((ComplexEventTimes50SP2) complexEventTimes).setComplexEventTimeGroups(complexEventTimeGroups);
        }
    }

    @Override
    public void clearComplexEventTimes() {
        this.complexEventTimes = null;
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

    @Override
    protected void setFragmentCompTagValue(Tag tag, ByteBuffer message)
        throws BadFormatMsgException, InvalidMsgException, TagNotPresentException {
        if (COMPLEX_EVENT_TIME_COMP_TAGS.contains(tag.tagNum)) {
            FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, validateRequired);
            if (complexEventTimes == null) {
                complexEventTimes = new ComplexEventTimes50SP2(context);
            }
            complexEventTimes.decode(tag, message);
        }
    }

    @Override
    protected String getUnsupportedTagMessage() {
        return "This tag is not supported in [ComplexEventDateGroup] group version [5.0SP2].";
    }
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Package Methods">
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Private Methods">
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Inner Classes">
    // </editor-fold>
}
