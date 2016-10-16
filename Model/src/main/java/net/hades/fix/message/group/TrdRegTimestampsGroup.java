/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * TrdRegTimestampsGroup.java
 *
 * $Id: TrdRegTimestampsGroup.java,v 1.2 2010-12-12 09:13:11 vrotaru Exp $
 */
package net.hades.fix.message.group;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.anno.FIXVersion;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.struct.Tag;
import net.hades.fix.message.type.SessionRejectReason;
import net.hades.fix.message.type.TagNum;
import net.hades.fix.message.type.TrdRegTimestampType;

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

import net.hades.fix.message.anno.TagNumRef;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.util.DateConverter;
import net.hades.fix.message.util.TagEncoder;

/**
 * The TrdRegTimestamps component block is used to express timestamps for an order or trade
 * that are required by regulatory agencies These timesteamps are used to identify the
 * timeframes for when an order or trade is received on the floor, received and executed
 * by the broker, etc.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 * @created 12/02/2009, 7:08:50 PM
 */
@XmlAccessorType(XmlAccessType.NONE)
public abstract class TrdRegTimestampsGroup extends Group {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.TrdRegTimestamp.getValue(),
        TagNum.TrdRegTimestampType.getValue(),
        TagNum.TrdRegTimestampOrigin.getValue(),
        TagNum.DeskType.getValue(),
        TagNum.DeskTypeSource.getValue(),
        TagNum.DeskOrderHandlingInst.getValue()
    }));

    protected static final Set<Integer> START_DATA_TAGS = null;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">

    /**
     * TagNum = 769. Starting with 5.0 version.
     */
    protected Date trdRegTimestamp;

    /**
     * TagNum = 770. Starting with 5.0 version.
     */
    protected TrdRegTimestampType trdRegTimestampType;

    /**
     * TagNum = 771. Starting with 5.0 version.
     */
    protected String trdRegTimestampOrigin;

    /**
     * TagNum = 1033. Starting with 5.0 version.
     */
    protected String deskType;

    /**
     * TagNum = 1034. Starting with 5.0 version.
     */
    protected Integer deskTypeSource;

    /**
     * TagNum = 1035. Starting with 5.0 version.
     */
    protected String deskOrderHandlingInst;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public TrdRegTimestampsGroup() {
    }

    public TrdRegTimestampsGroup(FragmentContext context) {
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
    @TagNumRef(tagNum = TagNum.TrdRegTimestamp)
    public Date getTrdRegTimestamp() {
        return trdRegTimestamp;
    }

    /**
     * Message field setter.
     * @param trdRegTimestamp field value
     */
    @FIXVersion(introduced = "5.0")
    @TagNumRef(tagNum = TagNum.TrdRegTimestamp)
    public void setTrdRegTimestamp(Date trdRegTimestamp) {
        this.trdRegTimestamp = trdRegTimestamp;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "5.0")
    @TagNumRef(tagNum = TagNum.TrdRegTimestampType)
    public TrdRegTimestampType getTrdRegTimestampType() {
        return trdRegTimestampType;
    }

    /**
     * Message field setter.
     * @param trdRegTimestampType field value
     */
    @FIXVersion(introduced = "5.0")
    @TagNumRef(tagNum = TagNum.TrdRegTimestampType)
    public void setTrdRegTimestampType(TrdRegTimestampType trdRegTimestampType) {
        this.trdRegTimestampType = trdRegTimestampType;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "5.0")
    @TagNumRef(tagNum = TagNum.TrdRegTimestampOrigin)
    public String getTrdRegTimestampOrigin() {
        return trdRegTimestampOrigin;
    }

    /**
     * Message field setter.
     * @param trdRegTimestampOrigin field value
     */
    @FIXVersion(introduced = "5.0")
    @TagNumRef(tagNum = TagNum.TrdRegTimestampOrigin)
    public void setTrdRegTimestampOrigin(String trdRegTimestampOrigin) {
        this.trdRegTimestampOrigin = trdRegTimestampOrigin;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "5.0")
    @TagNumRef(tagNum = TagNum.DeskType)
    public String getDeskType() {
        return deskType;
    }

    /**
     * Message field setter.
     * @param deskType field value
     */
    @FIXVersion(introduced = "5.0")
    @TagNumRef(tagNum = TagNum.DeskType)
    public void setDeskType(String deskType) {
        this.deskType = deskType;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "5.0")
    @TagNumRef(tagNum = TagNum.DeskTypeSource)
    public Integer getDeskTypeSource() {
        return deskTypeSource;
    }

    /**
     * Message field setter.
     * @param deskTypeSource field value
     */
    @FIXVersion(introduced = "5.0")
    @TagNumRef(tagNum = TagNum.DeskTypeSource)
    public void setDeskTypeSource(Integer deskTypeSource) {
        this.deskTypeSource = deskTypeSource;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "5.0")
    @TagNumRef(tagNum = TagNum.DeskOrderHandlingInst)
    public String getDeskOrderHandlingInst() {
        return deskOrderHandlingInst;
    }

    /**
     * Message field setter.
     * @param deskOrderHandlingInst field value
     */
    @FIXVersion(introduced = "5.0")
    @TagNumRef(tagNum = TagNum.DeskOrderHandlingInst)
    public void setDeskOrderHandlingInst(String deskOrderHandlingInst) {
        this.deskOrderHandlingInst = deskOrderHandlingInst;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

    @Override
    protected int getFirstTag() {
        return TagNum.TrdRegTimestamp.getValue();
    }

    @Override
    protected void validateRequiredTags() throws TagNotPresentException {
        StringBuilder errorMsg = new StringBuilder("Tag value(s) for");
        boolean hasMissingTag = false;
        if (trdRegTimestamp == null) {
            errorMsg.append(" [TrdRegTimestamp]");
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
            if (trdRegTimestamp != null) {
                TagEncoder.encodeUtcTimestamp(bao, TagNum.TrdRegTimestamp, trdRegTimestamp);
            }
            if (trdRegTimestampType != null) {
                TagEncoder.encode(bao, TagNum.TrdRegTimestampType, trdRegTimestampType.getValue());
            }
            TagEncoder.encode(bao, TagNum.TrdRegTimestampOrigin, trdRegTimestampOrigin);
            TagEncoder.encode(bao, TagNum.DeskType, deskType);
            TagEncoder.encode(bao, TagNum.DeskTypeSource, deskTypeSource);
            TagEncoder.encode(bao, TagNum.DeskOrderHandlingInst, deskOrderHandlingInst);

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
            case TrdRegTimestamp:
                trdRegTimestamp = DateConverter.parseString(new String(tag.value, sessionCharset));
                break;

            case TrdRegTimestampType:
                trdRegTimestampType = TrdRegTimestampType.valueFor(Integer.valueOf(new String(tag.value, sessionCharset)));
                break;

            case TrdRegTimestampOrigin:
                trdRegTimestampOrigin = new String(tag.value, sessionCharset);
                break;

            case DeskType:
                deskType = new String(tag.value, sessionCharset);
                break;

            case DeskTypeSource:
                deskTypeSource = Integer.valueOf(new String(tag.value, sessionCharset));
                break;

            case DeskOrderHandlingInst:
                deskOrderHandlingInst = new String(tag.value, sessionCharset);
                break;

            default:
                String error = "Tag value [" + tag.tagNum + "] not present in [TrdRegTimestampsGroup] fields.";
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
        b.append("{TrdRegTimestampsGroup=");
        printUTCDateTimeTagValue(b, TagNum.TrdRegTimestamp, trdRegTimestamp);
        printTagValue(b, TagNum.TrdRegTimestampType, trdRegTimestampType);
        printTagValue(b, TagNum.TrdRegTimestampOrigin, trdRegTimestampOrigin);
        printTagValue(b, TagNum.DeskType, deskType);
        printTagValue(b, TagNum.DeskTypeSource, deskTypeSource);
        printTagValue(b, TagNum.DeskOrderHandlingInst, deskOrderHandlingInst);

        b.append("}");

        return b.toString();
    }

    // </editor-fold>

}
