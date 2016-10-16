/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * InstrumentExtension44.java
 *
 * $Id: InstrumentExtension44.java,v 1.3 2011-04-14 23:44:59 vrotaru Exp $
 */
package net.hades.fix.message.comp.impl.v44;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.comp.InstrumentExtension;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.struct.Tag;
import net.hades.fix.message.type.DeliveryForm;
import net.hades.fix.message.type.SessionRejectReason;
import net.hades.fix.message.type.TagNum;
import net.hades.fix.message.util.MsgUtil;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.group.InstrmtAttribGroup;
import net.hades.fix.message.group.impl.v44.InstrmtAttribGroup44;
import net.hades.fix.message.util.TagEncoder;

/**
 * InstrumentExtension FIX version 4.4 implementation.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.3 $
 * @created 23/11/2008, 10:21:01 AM
 */
@XmlRootElement(name="InstrmtExt")
@XmlType(propOrder = {"instrmtAttribGroups"})
@XmlAccessorType(XmlAccessType.NONE)
public class InstrumentExtension44 extends InstrumentExtension {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> START_COMP_TAGS;

    protected static final Set<Integer> ALL_TAGS;

    protected static final Set<Integer> INSTRMT_ATTRIB_GROUP_TAGS = new InstrmtAttribGroup44().getFragmentAllTags();

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">

    static {
        ALL_TAGS = new HashSet<Integer>(TAGS);
        ALL_TAGS.addAll(INSTRMT_ATTRIB_GROUP_TAGS);
        START_COMP_TAGS = new HashSet<Integer>(INSTRMT_ATTRIB_GROUP_TAGS);
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">

    protected Set<Integer> STANDARD_SECURED_TAGS = ALL_TAGS;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public InstrumentExtension44() {
        super();
        SECURED_TAGS = STANDARD_SECURED_TAGS;
    }

    public InstrumentExtension44(FragmentContext context) {
        super(context);
        SECURED_TAGS = STANDARD_SECURED_TAGS;
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
                instrmtAttribGroups[i] = new InstrmtAttribGroup44(new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired));
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
        InstrmtAttribGroup group = new InstrmtAttribGroup44(new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired));
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
    protected byte[] encodeFragmentSecured(boolean secured) throws TagNotPresentException, BadFormatMsgException {
        byte[] result = new byte[0];
        ByteArrayOutputStream bao = new ByteArrayOutputStream();
        try {
            if (deliveryForm != null && MsgUtil.isTagInList(TagNum.DeliveryForm, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.DeliveryForm, deliveryForm.getValue());
            }
            if (MsgUtil.isTagInList(TagNum.PctAtRisk, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.PctAtRisk, pctAtRisk);
            }
            if (noInstrAttrib != null && MsgUtil.isTagInList(TagNum.NoInstrAttrib, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.NoInstrAttrib, noInstrAttrib);
                if (instrmtAttribGroups != null && instrmtAttribGroups.length == noInstrAttrib.intValue()) {
                    for (int i = 0; i < noInstrAttrib.intValue(); i++) {
                        if (instrmtAttribGroups[i] != null) {
                            bao.write(instrmtAttribGroups[i].encode(getMsgSecureTypeForFlag(secured)));
                        }
                    }
                } else {
                    String error = "InstrmtAttribGroups field has been set but there is no data or the number of groups does not match.";
                    LOGGER.severe(error);
                    throw new BadFormatMsgException(SessionRejectReason.IncorrectCountForGroups, TagNum.NoInstrAttrib.getValue(), error);
                }
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
    protected void setFragmentCompTagValue(Tag tag, ByteBuffer message)
    throws BadFormatMsgException, InvalidMsgException, TagNotPresentException {
        if (noInstrAttrib != null && noInstrAttrib.intValue() > 0) {
            if (INSTRMT_ATTRIB_GROUP_TAGS.contains(tag.tagNum)) {
                message.reset();
                instrmtAttribGroups = new InstrmtAttribGroup[noInstrAttrib.intValue()];
                FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired);
                for (int i = 0; i < noInstrAttrib.intValue(); i++) {
                    InstrmtAttribGroup group = new InstrmtAttribGroup44(context);
                    group.decode(message);
                    instrmtAttribGroups[i] = group;
                }
            }
        }
    }

    @Override
    protected String getUnsupportedTagMessage() {
        return "This tag is not supported in [InstrumentExtension] component version [4.4].";
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
