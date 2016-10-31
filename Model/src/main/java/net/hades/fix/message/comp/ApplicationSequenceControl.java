/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * ApplicationSequenceControl.java
 *
 * $Id: ApplicationSequenceControl.java,v 1.9 2011-05-02 04:04:19 vrotaru Exp $
 */
package net.hades.fix.message.comp;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.anno.FIXVersion;
import net.hades.fix.message.anno.TagNumRef;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.struct.Tag;
import net.hades.fix.message.type.SessionRejectReason;
import net.hades.fix.message.type.TagNum;
import net.hades.fix.message.util.BooleanConverter;
import net.hades.fix.message.util.TagEncoder;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

/**
 * The ApplicationSequenceControl is used for application sequencing and recovery.
 * Consisting of {@link net.hades.fix.message.type.TagNum#ApplSeqNum} (1181), {@link net.hades.fix.message.type.TagNum#ApplID} (1180),
 * {@link net.hades.fix.message.type.TagNum#ApplLastSeqNum} (1350), and {@link net.hades.fix.message.type.TagNum#ApplResendFlag} (1352),
 * FIX application messages that carries this component block will be able to use
 * application level sequencing. ApplID, ApplSeqNum and ApplLastSeqNum fields identify
 * the application id, application sequence number and the previous application
 * sequence number (in case of intentional gaps) on each application message that
 * carries this block.<br/>
 * The {@link net.hades.fix.message.type.TagNum#ApplResendFlag} (1352) is used to indicate that messages are
 * being retransmitted as a result of an Application Message Request.<br/>
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.9 $
 * @created 11/02/2009, 7:07:32 PM
 */
@XmlAccessorType(XmlAccessType.NONE)
public abstract class ApplicationSequenceControl extends Component {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.ApplID.getValue(),
        TagNum.ApplSeqNum.getValue(),
        TagNum.ApplLastSeqNum.getValue(),
        TagNum.ApplResendFlag.getValue()
    }));

    protected static final Set<Integer> START_DATA_TAGS = null;

    protected static final Set<Integer> START_COMP_TAGS = null;

    protected static final Set<Integer> REQUIRED_TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.ApplID.getValue()
    }));

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">

    /**
     * TagNum = 1180. Starting with 5.0SP1 version.
     */
    protected String applID;

    /**
     * TagNum = 1181. Starting with 5.0SP1 version.
     */
    protected Integer applSeqNum;

    /**
     * TagNum = 1350. Starting with 5.0SP1 version.
     */
    protected Integer applLastSeqNum;

    /**
     * TagNum = 1352. Starting with 5.0SP1 version.
     */
    protected Boolean applResendFlag;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public ApplicationSequenceControl() {
        super();
    }

    public ApplicationSequenceControl(FragmentContext context) {
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
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.ApplID)
    public String getApplID() {
        return applID;
    }

    /**
     * Message field setter.
     * @param applID field value
     */
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.ApplID)
    public void setApplID(String applID) {
        this.applID = applID;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.ApplSeqNum)
    public Integer getApplSeqNum() {
        return applSeqNum;
    }

    /**
     * Message field setter.
     * @param applSeqNum field value
     */
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.ApplSeqNum)
    public void setApplSeqNum(Integer applSeqNum) {
        this.applSeqNum = applSeqNum;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.ApplLastSeqNum)
    public Integer getApplLastSeqNum() {
        return applLastSeqNum;
    }

    /**
     * Message field setter.
     * @param applLastSeqNum field value
     */
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.ApplLastSeqNum)
    public void setApplLastSeqNum(Integer applLastSeqNum) {
        this.applLastSeqNum = applLastSeqNum;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.ApplResendFlag)
    public Boolean getApplResendFlag() {
        return applResendFlag;
    }

    /**
     * Message field setter.
     * @param applResendFlag field value
     */
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.ApplResendFlag)
    public void setApplResendFlag(Boolean applResendFlag) {
        this.applResendFlag = applResendFlag;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

    @Override
    protected int getFirstTag() {
        return TagNum.ApplID.getValue();
    }

    @Override
    protected void validateRequiredTags() throws TagNotPresentException {
        StringBuilder errorMsg = new StringBuilder("Tag value(s) for");
        boolean hasMissingTag = false;
         if (applID == null) {
            errorMsg.append(" [ApplID]");
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
            TagEncoder.encode(bao, TagNum.ApplID, applID);
            TagEncoder.encode(bao, TagNum.ApplSeqNum, applSeqNum);
            TagEncoder.encode(bao, TagNum.ApplLastSeqNum, applLastSeqNum);
            TagEncoder.encode(bao, TagNum.ApplResendFlag, applResendFlag);
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
            case ApplID:
                applID = new String(tag.value, sessionCharset);
                break;

            case ApplSeqNum:
                applSeqNum = new Integer(new String(tag.value, sessionCharset));
                break;

            case ApplLastSeqNum:
                applLastSeqNum = new Integer(new String(tag.value, sessionCharset));
                break;

            case ApplResendFlag:
                applResendFlag = BooleanConverter.parse(new String(tag.value, sessionCharset));
                break;

            default:
                String error = "Tag value [" + tag.tagNum + "] not present in [ApplicationSequenceControl] fields.";
                LOGGER.severe(error);
                throw new BadFormatMsgException(SessionRejectReason.InvalidTagNumber, tag.tagNum, error);
        }
    }

    @Override
    protected void setFragmentCompTagValue(Tag tag, ByteBuffer message)
    throws BadFormatMsgException, InvalidMsgException, TagNotPresentException {
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

    // <editor-fold defaultstate="collapsed" desc="toString()">

    @Override
    public String toString() {
        StringBuilder b = new StringBuilder(System.getProperty("line.separator"));
        b.append("{ApplicationSequenceControl=");
        printTagValue(b, TagNum.ApplID, applID);
        printTagValue(b, TagNum.ApplSeqNum, applSeqNum);
        printTagValue(b, TagNum.ApplLastSeqNum, applLastSeqNum);
        printTagValue(b, TagNum.ApplResendFlag, applResendFlag);
        b.append("}");

        return b.toString();
    }

    // </editor-fold>
}
