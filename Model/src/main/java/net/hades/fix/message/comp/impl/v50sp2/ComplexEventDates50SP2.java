/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * ComplexEventTimes50SP2.java
 *
 * $Id: ComplexEventDates50SP2.java,v 1.4 2011-04-14 23:44:49 vrotaru Exp $
 */
package net.hades.fix.message.comp.impl.v50sp2;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.comp.ComplexEventDates;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.group.ComplexEventDateGroup;
import net.hades.fix.message.struct.Tag;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.xml.bind.annotation.XmlTransient;

import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.group.impl.v50sp2.ComplexEventDateGroup50SP2;

/**
 * FIX 5.0SP2 implementation of ComplexEventDates component.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.4 $
 * @created 04/06/2009, 11:16:20 AM
 */
@XmlTransient
public class ComplexEventDates50SP2 extends ComplexEventDates {
    
    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Static Block">
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Attributes">
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public ComplexEventDates50SP2() {
        super();
    }

    public ComplexEventDates50SP2(FragmentContext context) {
        super(context);
    }

    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Public Methods">

    @Override
    public Integer getNoComplexEventDates() {
        return noComplexEventDates;
    }

    @Override
    public void setNoComplexEventDates(Integer noComplexEventDates) {
        this.noComplexEventDates = noComplexEventDates;
        if (noComplexEventDates != null) {
            complexEventDateGroups = new ComplexEventDateGroup[noComplexEventDates.intValue()];
            for (int i = 0; i < complexEventDateGroups.length; i++) {
                complexEventDateGroups[i] = new ComplexEventDateGroup50SP2(new FragmentContext(sessionCharset, messageEncoding, validateRequired));
            }
        }
    }

    @Override
    public ComplexEventDateGroup[] getComplexEventDateGroups() {
        return complexEventDateGroups;
    }
    
    public void setComplexEventDateGroups(ComplexEventDateGroup[] complexEventDateGroups) {
        this.complexEventDateGroups = complexEventDateGroups;
        if (complexEventDateGroups != null) {
            noComplexEventDates = new Integer(complexEventDateGroups.length);
        }
    }
    
    @Override
    public ComplexEventDateGroup addComplexEventDateGroup() {
        ComplexEventDateGroup group = new ComplexEventDateGroup50SP2(new FragmentContext(sessionCharset, messageEncoding, validateRequired));
        List<ComplexEventDateGroup> groups = new ArrayList<ComplexEventDateGroup>();
        if (complexEventDateGroups != null && complexEventDateGroups.length > 0) {
            groups = new ArrayList<ComplexEventDateGroup>(Arrays.asList(complexEventDateGroups));
        }
        groups.add(group);
        complexEventDateGroups = groups.toArray(new ComplexEventDateGroup[groups.size()]);
        noComplexEventDates = new Integer(complexEventDateGroups.length);
        
        return group;
    }
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Protected Methods">
    
    @Override
    protected void setFragmentCompTagValue(Tag tag, ByteBuffer message)
        throws BadFormatMsgException, InvalidMsgException, TagNotPresentException {
        
        if (COMPLEX_EVENT_DATE_GROUP_TAGS.contains(tag.tagNum)) {
            if (noComplexEventDates != null && noComplexEventDates.intValue() > 0) {
                FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, validateRequired);
                message.reset();
                if (complexEventDateGroups == null) {
                    complexEventDateGroups = new ComplexEventDateGroup[noComplexEventDates.intValue()];
                }
                for (int i = 0; i < complexEventDateGroups.length; i++) {
                    ComplexEventDateGroup group = new ComplexEventDateGroup50SP2(context);
                    group.decode(message);
                    complexEventDateGroups[i] = group;
                }
            }
        }
    }
    
    @Override
    protected String getUnsupportedTagMessage() {
        return "This tag is not supported in [ComplexEventDates] group version [5.0SP2].";
    }
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Package Methods">
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Private Methods">
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Inner Classes">
    // </editor-fold>
}
