/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * MDSecSizeGroup.java
 *
 * $Id: MDSecSizeGroup.java,v 1.10 2010-11-23 10:20:17 vrotaru Exp $
 */
package net.hades.fix.message.group;

import net.hades.fix.message.anno.FIXVersion;
import net.hades.fix.message.struct.Tag;

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
import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.type.SessionRejectReason;
import net.hades.fix.message.type.TagNum;
import net.hades.fix.message.util.TagEncoder;

/**
 * Market data security size group data.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.10 $
 * @created 08/10/2009, 11:10:10 AM
 */
@XmlAccessorType(XmlAccessType.NONE)
public abstract class MDSecSizeGroup extends Group {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.MDSecSizeType.getValue(),
        TagNum.MDSecSize.getValue()
    }));

    protected static final Set<Integer> ALL_TAGS;

    protected static final Set<Integer> START_COMP_TAGS = null;

    protected static final Set<Integer> START_DATA_TAGS  = null;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">

    static {
        ALL_TAGS = new HashSet<Integer>(TAGS);
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">

    /**
     * TagNum = 1178. Starting with 5.0SP1 version.
     */
    protected Integer mdSecSizeType;

    /**
     * TagNum = 1179. Starting with 5.0SP1 version.
     */
    protected Double mdSecSize;
    
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public MDSecSizeGroup() {
    }

    public MDSecSizeGroup(FragmentContext context) {
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

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.MDSecSizeType)
    public Integer getMdSecSizeType() {
        return mdSecSizeType;
    }

    /**
     * Message field setter.
     * @param mdSecSizeType field value
     */
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.MDSecSizeType)
    public void setMdSecSizeType(Integer mdSecSizeType) {
        this.mdSecSizeType = mdSecSizeType;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.MDSecSize)
    public Double getMdSecSize() {
        return mdSecSize;
    }

    /**
     * Message field setter.
     * @param mdSecSize field value
     */
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.MDSecSize)
    public void setMdSecSize(Double mdSecSize) {
        this.mdSecSize = mdSecSize;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">
    
    @Override
    protected int getFirstTag() {
        return TagNum.MDSecSizeType.getValue();
    }

    @Override
    protected void validateRequiredTags() throws TagNotPresentException {
        StringBuilder errorMsg = new StringBuilder("Tag value(s) for");
        boolean hasMissingTag = false;
        if (mdSecSizeType == null) {
            errorMsg.append(" [MDSecSizeType]");
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
            TagEncoder.encode(bao, TagNum.MDSecSizeType, mdSecSizeType);
            TagEncoder.encode(bao, TagNum.MDSecSize, mdSecSize);

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
    protected void setFragmentCompTagValue(Tag tag, ByteBuffer message)
    throws BadFormatMsgException, InvalidMsgException, TagNotPresentException {
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

    @Override
    protected void setFragmentTagValue(Tag tag) throws BadFormatMsgException {
        TagNum tagNum = TagNum.fromString(tag.tagNum);
        switch (tagNum) {
            case MDSecSizeType:
                mdSecSizeType = new Integer(new String(tag.value));
                break;

            case MDSecSize:
                mdSecSize = new Double(new String(tag.value));
                break;

            default:
                String error = "Tag value [" + tag.tagNum + "] not present in [MDSecSizeGroup] fields.";
                LOGGER.severe(error);
                throw new BadFormatMsgException(SessionRejectReason.InvalidTagNumber, tag.tagNum, error);
        }
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
        b.append("{MDSecSizeGroup=");
        printTagValue(b, TagNum.MDSecSizeType, mdSecSizeType);
        printTagValue(b, TagNum.MDSecSize,getMdSecSize());
        b.append("}");

        return b.toString();
    }

    // </editor-fold>
}
