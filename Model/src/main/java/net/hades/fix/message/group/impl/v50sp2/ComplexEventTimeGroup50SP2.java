/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * ComplexEventTimeGroup50SP2.java
 *
 * $Id: ComplexEventTimeGroup50SP2.java,v 1.3 2010-02-04 10:11:06 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v50sp2;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.group.ComplexEventTimeGroup;
import net.hades.fix.message.xml.codec.jaxb.adapter.FixUTCTimeAdapter;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 * FIX 5.0SP2 implementation of ComplexEventTimeGroup group.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.3 $
 * @created 04/06/2009, 10:46:01 AM
 */
@XmlRootElement(name = "EvntTms")
@XmlType
@XmlAccessorType(XmlAccessType.NONE)
public class ComplexEventTimeGroup50SP2 extends ComplexEventTimeGroup {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = -38064956645965903L;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public ComplexEventTimeGroup50SP2() {
    }

    public ComplexEventTimeGroup50SP2(FragmentContext context) {
        super(context);
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Public Methods">

    @XmlAttribute(name = "StartTm")
    @XmlJavaTypeAdapter(FixUTCTimeAdapter.class)
    @Override
    public Date getComplexEventStartTime() {
        return complexEventStartTime;
    }

    @Override
    public void setComplexEventStartTime(Date complexEventStartTime) {
        this.complexEventStartTime = complexEventStartTime;
    }

    @XmlAttribute(name = "EndTm")
    @XmlJavaTypeAdapter(FixUTCTimeAdapter.class)
    @Override
    public Date getComplexEventEndTime() {
        return complexEventEndTime;
    }

    @Override
    public void setComplexEventEndTime(Date complexEventEndTime) {
        this.complexEventEndTime = complexEventEndTime;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

    @Override
    protected String getUnsupportedTagMessage() {
        return "This tag is not supported in [ComplexEventTimeGroup] group version [5.0SP2].";
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Package Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Private Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Inner Classes">
    // </editor-fold>
}
