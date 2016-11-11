/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * InstrumentExtension50SP1.java
 *
 * $Id: InstrumentExtension50SP1.java,v 1.3 2011-04-14 23:44:47 vrotaru Exp $
 */
package net.hades.fix.message.comp.impl.v50sp1;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.comp.InstrumentExtension;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.group.InstrmtAttribGroup;
import net.hades.fix.message.group.impl.v50sp1.InstrmtAttribGroup50SP1;
import net.hades.fix.message.struct.Tag;
import net.hades.fix.message.type.DeliveryForm;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * InstrumentExtension FIX version 5.0SP1 implementation.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.3 $
 * @created 23/11/2008, 10:21:01 AM
 */
@XmlRootElement(name="InstrmtExt")
@XmlType(propOrder = {"instrmtAttribGroups"})
@XmlAccessorType(XmlAccessType.NONE)
public class InstrumentExtension50SP1 extends InstrumentExtension {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> START_COMP_TAGS;

    protected static final Set<Integer> ALL_TAGS;

    protected static final Set<Integer> INSTRMT_ATTRIB_GROUP_TAGS = new InstrmtAttribGroup50SP1().getFragmentAllTags();

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">

    static {
        ALL_TAGS = new HashSet<Integer>(TAGS);
        ALL_TAGS.addAll(INSTRMT_ATTRIB_GROUP_TAGS);
        START_COMP_TAGS = new HashSet<Integer>(INSTRMT_ATTRIB_GROUP_TAGS);
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public InstrumentExtension50SP1() {
        super();
    }

    public InstrumentExtension50SP1(FragmentContext context) {
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

    @XmlAttribute(name = "DlvryForm")
    @Override
    public DeliveryForm getDeliveryForm() {
        return deliveryForm;
    }

    @Override
    public void setDeliveryForm(DeliveryForm deliveryForm) {
        this.deliveryForm = deliveryForm;
    }

    @XmlAttribute(name = "PctAtRisk")
    @Override
    public Double getPctAtRisk() {
        return pctAtRisk;
    }

    @Override
    public void setPctAtRisk(Double pctAtRisk) {
        this.pctAtRisk = pctAtRisk;
    }

    @Override
    public Integer getNoInstrAttrib() {
        return noInstrAttrib;
    }

    @Override
    public void setNoInstrAttrib(Integer noInstrAttrib) {
        this.noInstrAttrib = noInstrAttrib;
        if (noInstrAttrib != null) {
            instrmtAttribGroups = new InstrmtAttribGroup[noInstrAttrib.intValue()];
            for (int i = 0; i < instrmtAttribGroups.length; i++) {
                instrmtAttribGroups[i] = new InstrmtAttribGroup50SP1(new FragmentContext(sessionCharset, messageEncoding, validateRequired));
            }
        }
    }

    @XmlElementRef
    @Override
    public InstrmtAttribGroup[] getInstrmtAttribGroups() {
        return instrmtAttribGroups;
    }

    public void setInstrmtAttribGroups(InstrmtAttribGroup[] instrmtAttribGroups) {
        this.instrmtAttribGroups = instrmtAttribGroups;
         if (instrmtAttribGroups != null) {
            noInstrAttrib = instrmtAttribGroups.length;
        }
    }

    @Override
    public InstrmtAttribGroup addInstrmtAttribGroup() {
        InstrmtAttribGroup group = new InstrmtAttribGroup50SP1(new FragmentContext(sessionCharset, messageEncoding, validateRequired));
        List<InstrmtAttribGroup> groups = new ArrayList<InstrmtAttribGroup>();
        if (instrmtAttribGroups != null && instrmtAttribGroups.length > 0) {
            groups = new ArrayList<InstrmtAttribGroup>(Arrays.asList(instrmtAttribGroups));
        }
        groups.add(group);
        instrmtAttribGroups = groups.toArray(new InstrmtAttribGroup[groups.size()]);
        noInstrAttrib = new Integer(instrmtAttribGroups.length);

        return group;
    }

    @Override
    public InstrmtAttribGroup deleteInstrmtAttribGroup(int index) {
        InstrmtAttribGroup result = null;
        if (instrmtAttribGroups != null && instrmtAttribGroups.length > 0 && instrmtAttribGroups.length > index) {
            List<InstrmtAttribGroup> groups = new ArrayList<InstrmtAttribGroup>(Arrays.asList(instrmtAttribGroups));
            result = groups.remove(index);
            instrmtAttribGroups = groups.toArray(new InstrmtAttribGroup[groups.size()]);
            if (instrmtAttribGroups.length > 0) {
                noInstrAttrib = new Integer(instrmtAttribGroups.length);
            } else {
                instrmtAttribGroups = null;
                noInstrAttrib = null;
            }
        }

        return result;
    }

    @Override
    public int clearInstrmtAttribGroup() {
        int result = 0;
        if (instrmtAttribGroups != null && instrmtAttribGroups.length > 0) {
            result = instrmtAttribGroups.length;
            instrmtAttribGroups = null;
            noInstrAttrib = null;
        }

        return result;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

    @Override
    protected void setFragmentCompTagValue(Tag tag, ByteBuffer message)
    throws BadFormatMsgException, InvalidMsgException, TagNotPresentException {
        if (noInstrAttrib != null && noInstrAttrib.intValue() > 0) {
            if (INSTRMT_ATTRIB_GROUP_TAGS.contains(tag.tagNum)) {
                message.reset();
                instrmtAttribGroups = new InstrmtAttribGroup[noInstrAttrib.intValue()];
                FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, validateRequired);
                for (int i = 0; i < noInstrAttrib.intValue(); i++) {
                    InstrmtAttribGroup group = new InstrmtAttribGroup50SP1(context);
                    group.decode(message);
                    instrmtAttribGroups[i] = group;
                }
            }
        }
    }

    @Override
    protected String getUnsupportedTagMessage() {
        return "This tag is not supported in [InstrumentExtension] component version [5.0SP1].";
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
}
