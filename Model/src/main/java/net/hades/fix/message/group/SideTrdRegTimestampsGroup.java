/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * SideTrdRegTimestampsGroup.java
 *
 * $Id: SideTrdRegTimestampsGroup.java,v 1.1 2011-10-21 10:31:01 vrotaru Exp $
 */
package net.hades.fix.message.group;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.anno.FIXVersion;
import net.hades.fix.message.anno.TagNumRef;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.struct.Tag;
import net.hades.fix.message.type.SessionRejectReason;
import net.hades.fix.message.type.TagNum;
import net.hades.fix.message.type.TrdRegTimestampType;
import net.hades.fix.message.util.DateConverter;
import net.hades.fix.message.util.TagEncoder;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

/**
 * The SideTrdRegTimestamps group.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 12/02/2009, 7:08:50 PM
 */
@XmlAccessorType(XmlAccessType.NONE)
public abstract class SideTrdRegTimestampsGroup extends Group {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.SideTrdRegTimestamp.getValue(),
        TagNum.SideTrdRegTimestampType.getValue(),
        TagNum.SideTrdRegTimestampSrc.getValue()
    }));

    protected static final Set<Integer> START_DATA_TAGS = null;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">

    /**
     * TagNum = 769. Starting with 5.0 version.
     */
    protected Date sideTrdRegTimestamp;

    /**
     * TagNum = 770. Starting with 5.0 version.
     */
    protected TrdRegTimestampType sideTrdRegTimestampType;

    /**
     * TagNum = 771. Starting with 5.0 version.
     */
    protected String sideTrdRegTimestampSrc;


    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public SideTrdRegTimestampsGroup() {
    }

    public SideTrdRegTimestampsGroup(FragmentContext context) {
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
    @FIXVersion(introduced = "5.0")
    @TagNumRef(tagNum = TagNum.SideTrdRegTimestamp)
    public Date getSideTrdRegTimestamp() {
        return sideTrdRegTimestamp;
    }

    /**
     * Message field setter.
     * @param sideTrdRegTimestamp field value
     */
    @FIXVersion(introduced = "5.0")
    @TagNumRef(tagNum = TagNum.SideTrdRegTimestamp)
    public void setSideTrdRegTimestamp(Date sideTrdRegTimestamp) {
        this.sideTrdRegTimestamp = sideTrdRegTimestamp;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "5.0")
    @TagNumRef(tagNum = TagNum.SideTrdRegTimestampType)
    public TrdRegTimestampType getSideTrdRegTimestampType() {
        return sideTrdRegTimestampType;
    }

    /**
     * Message field setter.
     * @param sideTrdRegTimestampType field value
     */
    @FIXVersion(introduced = "5.0")
    @TagNumRef(tagNum = TagNum.SideTrdRegTimestampType)
    public void setSideTrdRegTimestampType(TrdRegTimestampType sideTrdRegTimestampType) {
        this.sideTrdRegTimestampType = sideTrdRegTimestampType;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "5.0")
    @TagNumRef(tagNum = TagNum.SideTrdRegTimestampSrc)
    public String getSideTrdRegTimestampSrc() {
        return sideTrdRegTimestampSrc;
    }

    /**
     * Message field setter.
     * @param sideTrdRegTimestampSrc field value
     */
    @FIXVersion(introduced = "5.0")
    @TagNumRef(tagNum = TagNum.SideTrdRegTimestampSrc)
    public void setSideTrdRegTimestampSrc(String sideTrdRegTimestampSrc) {
        this.sideTrdRegTimestampSrc = sideTrdRegTimestampSrc;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

    @Override
    protected int getFirstTag() {
        return TagNum.SideTrdRegTimestamp.getValue();
    }

    @Override
    protected void validateRequiredTags() throws TagNotPresentException {
        StringBuilder errorMsg = new StringBuilder("Tag value(s) for");
        boolean hasMissingTag = false;
        if (sideTrdRegTimestamp == null) {
            errorMsg.append(" [SideTrdRegTimestamp]");
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
            if (sideTrdRegTimestamp != null) {
                TagEncoder.encodeUtcTimestamp(bao, TagNum.SideTrdRegTimestamp, sideTrdRegTimestamp);
            }
            if (sideTrdRegTimestampType != null) {
                TagEncoder.encode(bao, TagNum.SideTrdRegTimestampType, sideTrdRegTimestampType.getValue());
            }
            TagEncoder.encode(bao, TagNum.SideTrdRegTimestampSrc, sideTrdRegTimestampSrc);

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
            case SideTrdRegTimestamp:
                sideTrdRegTimestamp = DateConverter.parseString(new String(tag.value, sessionCharset));
                break;

            case SideTrdRegTimestampType:
                sideTrdRegTimestampType = TrdRegTimestampType.valueFor(Integer.valueOf(new String(tag.value, sessionCharset)));
                break;

            case SideTrdRegTimestampSrc:
                sideTrdRegTimestampSrc = new String(tag.value, sessionCharset);
                break;

            default:
                String error = "Tag value [" + tag.tagNum + "] not present in [SideTrdRegTimestampsGroup] fields.";
                LOGGER.severe(error);
                throw new BadFormatMsgException(SessionRejectReason.InvalidTagNumber, tag.tagNum, error);
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
    protected Set<Integer> getFragmentSecuredTags() {
        return SECURED_TAGS;
    }

    /// </editor-fold>

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
        b.append("{SideTrdRegTimestampsGroup=");
        printUTCDateTimeTagValue(b, TagNum.SideTrdRegTimestamp, sideTrdRegTimestamp);
        printTagValue(b, TagNum.SideTrdRegTimestampType, sideTrdRegTimestampType);
        printTagValue(b, TagNum.SideTrdRegTimestampSrc, sideTrdRegTimestampSrc);

        b.append("}");

        return b.toString();
    }

    // </editor-fold>

}
