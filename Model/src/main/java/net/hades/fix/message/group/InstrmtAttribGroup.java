/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * InstrmtAttribGroup.java
 *
 * $Id: InstrmtAttribGroup.java,v 1.1 2011-02-13 04:40:44 vrotaru Exp $
 */
package net.hades.fix.message.group;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.anno.FIXVersion;
import net.hades.fix.message.anno.TagNumRef;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.struct.Tag;
import net.hades.fix.message.type.InstrAttribType;
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

import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.util.TagEncoder;

/**
 * Instrument attributes.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 29/04/2009, 6:41:55 PM
 */
@XmlAccessorType(XmlAccessType.NONE)
public abstract class InstrmtAttribGroup extends Group {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.InstrAttribType.getValue(),
        TagNum.InstrAttribValue.getValue()
    }));

    protected static final Set<Integer> START_COMP_TAGS = null;

    protected static final Set<Integer> START_DATA_TAGS = null;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">

    /**
     * TagNum = 871. Starting with 4.4 version.
     */
    protected InstrAttribType instrAttribType;

    /**
     * TagNum = 872. Starting with 5.0SP1 version.
     */
    protected String instrAttribValue;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public InstrmtAttribGroup() {
    }

    public InstrmtAttribGroup(FragmentContext context) {
        super(context);
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Public Methods">

    @Override
    public int getFirstTag() {
        return TagNum.InstrAttribType.getValue();
    }

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
    @FIXVersion(introduced = "4.4")
    @TagNumRef(tagNum = TagNum.InstrAttribType)
    public InstrAttribType getInstrAttribType() {
        return instrAttribType;
    }

    /**
     * Message field setter.
     * @param instrAttribType field value
     */
    @FIXVersion(introduced = "4.4")
    @TagNumRef(tagNum = TagNum.InstrAttribType)
    public void setInstrAttribType(InstrAttribType instrAttribType) {
        this.instrAttribType = instrAttribType;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.4")
    @TagNumRef(tagNum = TagNum.InstrAttribValue)
    public String getInstrAttribValue() {
        return instrAttribValue;
    }

    /**
     * Message field setter.
     * @param instrAttribValue field value
     */
    @FIXVersion(introduced = "4.4")
    @TagNumRef(tagNum = TagNum.InstrAttribValue)
    public void setInstrAttribValue(String instrAttribValue) {
        this.instrAttribValue = instrAttribValue;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

    @Override
    protected void validateRequiredTags() throws TagNotPresentException {
        StringBuilder errorMsg = new StringBuilder("Tag value(s) for");
        boolean hasMissingTag = false;
         if (instrAttribType == null) {
            errorMsg.append(" [InstrAttribType]");
            hasMissingTag = true;
        }
        errorMsg.append(" is missing.");
        if (hasMissingTag) {
            throw new TagNotPresentException(errorMsg.toString());
        }
    }

    @Override
    protected byte[] encodeFragmentAll() throws TagNotPresentException, BadFormatMsgException {
        if (validateRequired) {
            validateRequiredTags();
        }

        byte[] result = new byte[0];
        ByteArrayOutputStream bao = new ByteArrayOutputStream();
        try {
            if (instrAttribType != null) {
                TagEncoder.encode(bao, TagNum.InstrAttribType, instrAttribType.getValue());
            }
            TagEncoder.encode(bao, TagNum.InstrAttribValue, instrAttribValue);
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
            case InstrAttribType:
                instrAttribType = InstrAttribType.valueFor(Integer.valueOf(new String(tag.value, sessionCharset)));
                break;

            case InstrAttribValue:
                instrAttribValue = new String(tag.value, sessionCharset);
                break;

            default:
                String error = "Tag value [" + tag.tagNum + "] not present in [InstrmtAttribGroup] fields.";
                LOGGER.severe(error);
                throw new BadFormatMsgException(SessionRejectReason.InvalidTagNumber, tag.tagNum, error);
        }
    }

    @Override
    protected ByteBuffer setFragmentDataTagValue(Tag tag, ByteBuffer message) throws BadFormatMsgException {
        return message;
    }

    @Override
    protected Set<Integer> getFragmentCompTags() {
        return START_COMP_TAGS;
    }

    @Override
    protected Set<Integer> getFragmentDataTags() {
        return START_DATA_TAGS;
    }

    @Override
    protected void setFragmentCompTagValue(Tag tag, ByteBuffer message)
    throws BadFormatMsgException, InvalidMsgException, TagNotPresentException {
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
        b.append("{InstrmtAttribGroup=");
        printTagValue(b, TagNum.InstrAttribType, instrAttribType);
        printTagValue(b, TagNum.InstrAttribValue, instrAttribValue);
        b.append("}");

        return b.toString();
    }

    // </editor-fold>
}
