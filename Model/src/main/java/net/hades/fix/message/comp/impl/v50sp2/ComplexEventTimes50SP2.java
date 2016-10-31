/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * ComplexEventTimes50SP2.java
 *
 * $Id: ComplexEventTimes50SP2.java,v 1.4 2011-04-14 23:44:49 vrotaru Exp $
 */
package net.hades.fix.message.comp.impl.v50sp2;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.group.ComplexEventTimeGroup;
import net.hades.fix.message.group.impl.v50sp2.ComplexEventTimeGroup50SP2;
import net.hades.fix.message.struct.Tag;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.xml.bind.annotation.XmlTransient;

import net.hades.fix.message.comp.ComplexEventTimes;

/**
 * FIX 5.0SP2 implementation of ComplexEventTimes component.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.4 $
 * @created 04/06/2009, 11:16:20 AM
 */
@XmlTransient
public class ComplexEventTimes50SP2 extends ComplexEventTimes {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public ComplexEventTimes50SP2() {
        super();
    }

    public ComplexEventTimes50SP2(FragmentContext context) {
        super(context);
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Public Methods">

    @Override
    public Integer getNoComplexEventTimes() {
        return noComplexEventTimes;
    }

    @Override
    public void setNoComplexEventTimes(Integer noComplexEventTimes) {
        this.noComplexEventTimes = noComplexEventTimes;
        if (noComplexEventTimes != null) {
            complexEventTimeGroups = new ComplexEventTimeGroup[noComplexEventTimes.intValue()];
            for (int i = 0; i < complexEventTimeGroups.length; i++) {
                complexEventTimeGroups[i] = new ComplexEventTimeGroup50SP2(new FragmentContext(sessionCharset, messageEncoding, validateRequired));
            }
        }
    }

    @Override
    public ComplexEventTimeGroup[] getComplexEventTimeGroups() {
        return complexEventTimeGroups;
    }

    public void setComplexEventTimeGroups(ComplexEventTimeGroup[] complexEventTimeGroups) {
        this.complexEventTimeGroups = complexEventTimeGroups;
        if (complexEventTimeGroups != null) {
            noComplexEventTimes = new Integer(complexEventTimeGroups.length);
        }
    }

    @Override
    public ComplexEventTimeGroup addComplexEventTimeGroup() {
        ComplexEventTimeGroup group = new ComplexEventTimeGroup50SP2(new FragmentContext(sessionCharset, messageEncoding, validateRequired));
        List<ComplexEventTimeGroup> groups = new ArrayList<ComplexEventTimeGroup>();
        if (complexEventTimeGroups != null && complexEventTimeGroups.length > 0) {
            groups = new ArrayList<ComplexEventTimeGroup>(Arrays.asList(complexEventTimeGroups));
        }
        groups.add(group);
        complexEventTimeGroups = groups.toArray(new ComplexEventTimeGroup[groups.size()]);
        noComplexEventTimes = new Integer(complexEventTimeGroups.length);

        return group;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

    @Override
    protected void setFragmentCompTagValue(Tag tag, ByteBuffer message)
    throws BadFormatMsgException, InvalidMsgException, TagNotPresentException {

        if (COMPLEX_EVENT_TIME_GROUP_TAGS.contains(tag.tagNum)) {
            if (noComplexEventTimes != null && noComplexEventTimes.intValue() > 0) {
                FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, validateRequired);
                message.reset();
                if (complexEventTimeGroups == null) {
                    complexEventTimeGroups = new ComplexEventTimeGroup[noComplexEventTimes.intValue()];
                }
                for (int i = 0; i < complexEventTimeGroups.length; i++) {
                    ComplexEventTimeGroup group = new ComplexEventTimeGroup50SP2(context);
                    group.decode(message);
                    complexEventTimeGroups[i] = group;
                }
            }
        }
    }

    @Override
    protected String getUnsupportedTagMessage() {
        return "This tag is not supported in [ComplexEventTimes] group version [5.0SP2].";
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Package Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Private Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Inner Classes">
    // </editor-fold>
}
