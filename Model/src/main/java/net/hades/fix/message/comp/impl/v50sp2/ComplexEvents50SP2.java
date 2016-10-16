/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * ComplexEventTimes50SP2.java
 *
 * $Id: ComplexEvents50SP2.java,v 1.6 2011-04-14 23:44:49 vrotaru Exp $
 */
package net.hades.fix.message.comp.impl.v50sp2;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.comp.ComplexEventDates;
import net.hades.fix.message.group.ComplexEventDateGroup;
import net.hades.fix.message.struct.Tag;

import java.nio.ByteBuffer;
import java.util.HashSet;
import java.util.Set;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import net.hades.fix.message.comp.ComplexEvents;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.type.ComplexEventCondition;
import net.hades.fix.message.type.ComplexEventPriceBoundaryMethod;
import net.hades.fix.message.type.ComplexEventPriceTimeType;
import net.hades.fix.message.type.ComplexEventType;

/**
 * FIX 5.0SP2 implementation of ComplexEvents component.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.6 $
 * @created 04/06/2009, 11:16:20 AM
 */
@XmlRootElement(name="CmplxEvnt")
@XmlType(propOrder = {"complexEventDateGroups"})
@XmlAccessorType(XmlAccessType.NONE)
public class ComplexEvents50SP2 extends ComplexEvents {
    
    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = -7308568620103219698L;

    protected static final Set<Integer> START_COMP_TAGS;

    protected static final Set<Integer> ALL_TAGS;

    protected static final Set<Integer> COMPLEX_EVENT_DATES_COMP_TAGS = new ComplexEventDates50SP2().getFragmentAllTags();

    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Static Block">

    static {
        ALL_TAGS = new HashSet<Integer>(TAGS);
        ALL_TAGS.addAll(COMPLEX_EVENT_DATES_COMP_TAGS);
        START_COMP_TAGS = new HashSet<Integer>(COMPLEX_EVENT_DATES_COMP_TAGS);
    }

    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Attributes">
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Constructors">
    
    public ComplexEvents50SP2() {
        super();
    }
    
    public ComplexEvents50SP2(FragmentContext context) {
        super(context);
    }
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Public Methods">

    @Override
    public Set<Integer> getFragmentAllTags() {
        return ALL_TAGS;
    }

    // ACCESSOR METHODS
    //////////////////////////////////////////

    @XmlAttribute(name = "Typ")
    @Override
    public ComplexEventType getComplexEventType() {
        return complexEventType;
    }

    @Override
    public void setComplexEventType(ComplexEventType complexEventType) {
        this.complexEventType = complexEventType;
    }

    @XmlAttribute(name = "OptPayAmt")
    @Override
    public Double getComplexOptPayoutAmount() {
        return complexOptPayoutAmount;
    }

    @Override
    public void setComplexOptPayoutAmount(Double complexOptPayoutAmount) {
        this.complexOptPayoutAmount = complexOptPayoutAmount;
    }

    @XmlAttribute(name = "Px")
    @Override
    public Double getComplexEventPrice() {
        return complexEventPrice;
    }

    @Override
    public void setComplexEventPrice(Double complexEventPrice) {
        this.complexEventPrice = complexEventPrice;
    }

    @XmlAttribute(name = "PxBndryMeth")
    @Override
    public ComplexEventPriceBoundaryMethod getComplexEventPriceBoundaryMethod() {
        return complexEventPriceBoundaryMethod;
    }

    @Override
    public void setComplexEventPriceBoundaryMethod(ComplexEventPriceBoundaryMethod complexEventPriceBoundaryMethod) {
        this.complexEventPriceBoundaryMethod = complexEventPriceBoundaryMethod;
    }

    @XmlAttribute(name = "PxBndryPrcsn")
    @Override
    public Double getComplexEventPriceBoundaryPrecision() {
        return complexEventPriceBoundaryPrecision;
    }

    @Override
    public void setComplexEventPriceBoundaryPrecision(Double complexEventPriceBoundaryPrecision) {
        this.complexEventPriceBoundaryPrecision = complexEventPriceBoundaryPrecision;
    }

    @XmlAttribute(name = "PxTmTyp")
    @Override
    public ComplexEventPriceTimeType getComplexEventPriceTimeType() {
        return complexEventPriceTimeType;
    }

    @Override
    public void setComplexEventPriceTimeType(ComplexEventPriceTimeType complexEventPriceTimeType) {
        this.complexEventPriceTimeType = complexEventPriceTimeType;
    }

    @XmlAttribute(name = "Cond")
    @Override
    public ComplexEventCondition getComplexEventCondition() {
        return complexEventCondition;
    }

    @Override
    public void setComplexEventCondition(ComplexEventCondition complexEventCondition) {
        this.complexEventCondition = complexEventCondition;
    }

    @Override
    public ComplexEventDates getComplexEventDates() {
        return complexEventDates;
    }

    @Override
    public void setComplexEventDates() {
        FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, validateRequired);
        complexEventDates = new ComplexEventDates50SP2(context);
    }

    @Override
    public void clearComplexEventDates() {
        this.complexEventDates = null;
    }

    @XmlElementRef
    public ComplexEventDateGroup[] getComplexEventDateGroups() {
        return complexEventDates == null ? null : complexEventDates.getComplexEventDateGroups();
    }

    public void setComplexEventDateGroups(ComplexEventDateGroup[] complexEventDateGroups) {
        if (complexEventDateGroups != null) {
            if (complexEventDates == null) {
                setComplexEventDates();
            }
            ((ComplexEventDates50SP2) complexEventDates).setComplexEventDateGroups(complexEventDateGroups);
        }
    }
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Protected Methods">
    
    @Override
    protected void setFragmentCompTagValue(Tag tag, ByteBuffer message)
        throws BadFormatMsgException, InvalidMsgException, TagNotPresentException {

        if (COMPLEX_EVENT_DATES_COMP_TAGS.contains(tag.tagNum)) {
            FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, validateRequired);
            if (complexEventDates == null) {
                complexEventDates = new ComplexEventDates50SP2(context);
            }
            complexEventDates.decode(tag, message);
        }
    }

    @Override
    protected Set<Integer> getFragmentCompTags() {
        return START_COMP_TAGS;
    }

    @Override
    protected String getUnsupportedTagMessage() {
        return "This tag is not supported in [ComplexEvents] group version [5.0SP2].";
    }
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Package Methods">
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Private Methods">
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Inner Classes">
    // </editor-fold>
}
