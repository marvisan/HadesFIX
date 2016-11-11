/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * InstrumentExtension.java
 *
 * $Id: InstrumentExtension.java,v 1.3 2011-04-29 03:11:03 vrotaru Exp $
 */
package net.hades.fix.message.comp;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.anno.FIXVersion;
import net.hades.fix.message.struct.Tag;
import net.hades.fix.message.type.DeliveryForm;
import net.hades.fix.message.type.SessionRejectReason;
import net.hades.fix.message.type.TagNum;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import net.hades.fix.message.anno.TagNumRef;
import net.hades.fix.message.MsgSecureType;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.group.InstrmtAttribGroup;
import net.hades.fix.message.util.TagEncoder;

/**
 * Instrument parties component.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.3 $
 * @created 07/12/2008, 12:48:37 PM
 */
@XmlAccessorType(XmlAccessType.NONE)
public abstract class InstrumentExtension extends Component {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.DeliveryForm.getValue(),
        TagNum.PctAtRisk.getValue(),
        TagNum.NoInstrAttrib.getValue()
    }));

    protected static final Set<Integer> START_DATA_TAGS = null;

    protected static final Set<Integer> REQUIRED_TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
    }));

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">

    /**
     * TagNum = 668. Starting with 4.4 version.
     */
    protected DeliveryForm deliveryForm;

    /**
     * TagNum = 869. Starting with 4.4 version.
     */
    protected Double pctAtRisk;

    /**
     * TagNum = 870. Starting with 4.4 version.
     */
    protected Integer noInstrAttrib;

    /**
     *  Starting with 4.4 version.
     */
    protected InstrmtAttribGroup[] instrmtAttribGroups;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public InstrumentExtension() {
        super();
    }

    public InstrumentExtension(FragmentContext context) {
        super(context);
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Public Methods">

    @Override
    public Set<Integer> getFragmentTags() {
        return TAGS;
    }

    // ACCESSOR METHODS
    //////////////////////////////////////////

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.DeliveryForm)
    public DeliveryForm getDeliveryForm() {
        return deliveryForm;
    }

    /**
     * Message field setter.
     * @param deliveryForm field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.DeliveryForm)
    public void setDeliveryForm(DeliveryForm deliveryForm) {
        this.deliveryForm = deliveryForm;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.PctAtRisk)
    public Double getPctAtRisk() {
        return pctAtRisk;
    }

    /**
     * Message field setter.
     * @param pctAtRisk field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.PctAtRisk)
    public void setPctAtRisk(Double pctAtRisk) {
        this.pctAtRisk = pctAtRisk;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.4")
    @TagNumRef(tagNum = TagNum.NoInstrAttrib)
    public Integer getNoInstrAttrib() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method sets the number of {@link InstrmtAttribGroup} groups. It will also create an array
     * of {@link InstrmtAttribGroup} objects and set the <code>instrmtAttribGroups</code> field with this array.
     * The created objects inside the array need to be populated with data for encoding.<br/>
     * If there where already objects in <code>instrmtAttribGroups</code> array they will be discarded.<br/>
     * @param noInstrAttrib field value
     */
    @FIXVersion(introduced = "4.4")
    @TagNumRef(tagNum = TagNum.NoInstrAttrib)
    public void setNoInstrAttrib(Integer noInstrAttrib) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter for {@link InstrmtAttribGroup} array of groups.
     * @return field array value
     */
    @FIXVersion(introduced="4.4")
    public InstrmtAttribGroup[] getInstrmtAttribGroups() {
        return instrmtAttribGroups;
    }

    /**
     * This method adds a {@link InstrmtAttribGroup} object to the existing array of <code>instrumentPartyGroups</code>
     * and expands the static array with 1 place.<br/>
     * This method will also update <code>noInstrumentParties</code> field to the proper value.<br/>
     * Note: If the <code>setNoInstrumentParties</code> method has been called there will already be a number of objects in the
     * <code>noInstrumentParties</code> array created.<br/>
     * @return newly created block and added to the array group object
     */
    @FIXVersion(introduced="4.4")
    public InstrmtAttribGroup addInstrmtAttribGroup() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method deletes a {@link InstrmtAttribGroup} object from the existing array of <code>instrumentPartyGroups</code>
     * and shrink the static array with 1 place.<br/>
     * If the array does not have the index position then a null object will be returned.)<br/>
     * This method will also update <code>noInstrumentParties</code> field to the proper value.<br/>
     * @param index position in array to be deleted starting at 0
     * @return deleted block object
     */
    @FIXVersion(introduced="4.4")
    public InstrmtAttribGroup deleteInstrmtAttribGroup(int index) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Deletes all the {@link InstrmtAttribGroup} objects from the <code>instrumentPartyGroups</code> array
     * (sets the array to 0 length)<br/>
     * This method will also update <code>noInstrumentParties</code> field and set it to null.<br/>
     * @return number of elements in array cleared
     */
    @FIXVersion(introduced="4.4")
    public int clearInstrmtAttribGroup() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

    @Override
    protected int getFirstTag() {
        return TagNum.DeliveryForm.getValue();
    }

    @Override
    protected void validateRequiredTags() throws TagNotPresentException {
    }

    @Override
    protected byte[] encodeFragmentAll() throws TagNotPresentException, BadFormatMsgException {
        if (validateRequired) {
            validateRequiredTags();
        }
        byte[] result = new byte[0];
        ByteArrayOutputStream bao = new ByteArrayOutputStream();

        try {
            if (deliveryForm != null) {
                TagEncoder.encode(bao, TagNum.DeliveryForm, deliveryForm.getValue());
            }
            TagEncoder.encode(bao, TagNum.PctAtRisk, pctAtRisk);
            TagEncoder.encode(bao, TagNum.NoInstrAttrib, noInstrAttrib);
            if (noInstrAttrib != null) {
                TagEncoder.encode(bao, TagNum.NoInstrAttrib, noInstrAttrib);
                if (instrmtAttribGroups != null && instrmtAttribGroups.length == noInstrAttrib.intValue()) {
                    for (int i = 0; i < noInstrAttrib.intValue(); i++) {
                        if (instrmtAttribGroups[i] != null) {
                            bao.write(instrmtAttribGroups[i].encode(MsgSecureType.ALL_FIELDS));
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
            case DeliveryForm:
                deliveryForm = DeliveryForm.valueFor(new Integer(new String(tag.value, sessionCharset)));
                break;

            case PctAtRisk:
                pctAtRisk = new Double(new String(tag.value, sessionCharset));
                break;

            case NoInstrAttrib:
                noInstrAttrib = new Integer(new String(tag.value, sessionCharset));
                break;

            default:
                String error = "Tag value [" + tag.tagNum + "] not present in [InstrumentExtension] fields.";
                LOGGER.severe(error);
                throw new BadFormatMsgException(SessionRejectReason.InvalidTagNumber, tag.tagNum, error);
        }
    }

    @Override
    protected ByteBuffer setFragmentDataTagValue(Tag tag, ByteBuffer message) throws BadFormatMsgException {
        return message;
    }

    @Override
    protected Set<Integer> getFragmentDataTags() {
        return START_DATA_TAGS;
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
        b.append("{InstrumentExtension=");
        printTagValue(b, TagNum.DeliveryForm, deliveryForm);
        printTagValue(b, TagNum.PctAtRisk, pctAtRisk);
        printTagValue(b, TagNum.NoInstrAttrib, noInstrAttrib);
        printTagValue(b, instrmtAttribGroups);
        b.append("}");

        return b.toString();
    }

    // </editor-fold>
}
