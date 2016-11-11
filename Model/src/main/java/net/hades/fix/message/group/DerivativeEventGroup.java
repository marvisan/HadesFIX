/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * DerivativeEventGroup.java
 *
 * $Id: DerivativeEventGroup.java,v 1.1 2011-09-19 08:15:45 vrotaru Exp $
 */
package net.hades.fix.message.group;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.anno.FIXVersion;
import net.hades.fix.message.anno.TagNumRef;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.struct.Tag;
import net.hades.fix.message.type.SessionRejectReason;
import net.hades.fix.message.type.TagNum;
import net.hades.fix.message.util.DateConverter;
import net.hades.fix.message.util.TagEncoder;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

/**
 * Derivative event group.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 06/12/2008, 12:12:02 PM
 */
@XmlAccessorType(XmlAccessType.NONE)
public abstract class DerivativeEventGroup extends Group {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.DerivativeEventType.getValue(),
        TagNum.DerivativeEventDate.getValue(),
        TagNum.DerivativeEventPx.getValue(),
        TagNum.DerivativeEventText.getValue()
    }));

    protected static final Set<Integer> START_DATA_TAGS = null;

    protected static final Set<Integer> START_COMP_TAGS = null;

    protected static final Set<Integer> ALL_TAGS;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">

    static {
        ALL_TAGS = new HashSet<Integer>(TAGS);
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">

    /**
     * TagNum = 1287. Starting with 5.0SP1 version.
     */
    protected Integer derivativeEventType;

    /**
     * TagNum = 1288. Starting with 5.0SP1 version.
     */
    protected Date derivativeEventDate;

    /**
     * TagNum = 1289. Starting with 5.0SP1 version.
     */
    protected Date derivativeEventTime;

    /**
     * TagNum = 1290. Starting with 5.0SP1 version.
     */
    protected Double derivativeEventPx;

    /** 
     * TagNum = 1291. Starting with 5.0SP1 version.
     */
    protected String derivativeEventText;


    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public DerivativeEventGroup() {
    }

    public DerivativeEventGroup(FragmentContext context) {
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

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.DerivativeEventType)
    public Integer getDerivativeEventType() {
        return derivativeEventType;
    }

    /**
     * Message field setter.
     * @param derivativeEventType field value
     */
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.DerivativeEventType)
    public void setDerivativeEventType(Integer derivativeEventType) {
        this.derivativeEventType = derivativeEventType;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.DerivativeEventDate)
    public Date getDerivativeEventDate() {
        return derivativeEventDate;
    }

    /**
     * Message field setter.
     * @param derivativeEventDate field value
     */
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.DerivativeEventDate)
    public void setDerivativeEventDate(Date derivativeEventDate) {
        this.derivativeEventDate = derivativeEventDate;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.DerivativeEventTime)
    public Date getDerivativeEventTime() {
        return derivativeEventTime;
    }

    /**
     * Message field setter.
     * @param derivativeEventTime field value
     */
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.DerivativeEventTime)
    public void setDerivativeEventTime(Date derivativeEventTime) {
        this.derivativeEventTime = derivativeEventTime;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.DerivativeEventPx)
    public Double getDerivativeEventPx() {
        return derivativeEventPx;
    }

    /**
     * Message field setter.
     * @param derivativeEventPx field value
     */
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.DerivativeEventPx)
    public void setDerivativeEventPx(Double derivativeEventPx) {
        this.derivativeEventPx = derivativeEventPx;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.DerivativeEventText)
    public String getDerivativeEventText() {
        return derivativeEventText;
    }

    /**
     * Message field setter.
     * @param evenText field value
     */
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.DerivativeEventText)
    public void setDerivativeEventText(String evenText) {
        this.derivativeEventText = evenText;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

    @Override
    protected int getFirstTag() {
        return TagNum.DerivativeEventType.getValue();
    }

    @Override
    protected void validateRequiredTags() throws TagNotPresentException {
        StringBuilder errorMsg = new StringBuilder("Tag value(s) for");
        boolean hasMissingTag = false;
        if (derivativeEventType == null) {
            errorMsg.append(" [DerivativeEventType]");
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
            TagEncoder.encode(bao, TagNum.DerivativeEventType, derivativeEventType);
            TagEncoder.encodeDate(bao, TagNum.DerivativeEventDate, derivativeEventDate);
            TagEncoder.encodeUtcTimestamp(bao, TagNum.DerivativeEventTime, derivativeEventTime);
            TagEncoder.encode(bao, TagNum.DerivativeEventPx, derivativeEventPx);
            TagEncoder.encode(bao, TagNum.DerivativeEventText, derivativeEventText, sessionCharset);
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
            case DerivativeEventType:
                derivativeEventType = new Integer(new String(tag.value, sessionCharset));
                break;

            case DerivativeEventDate:
                derivativeEventDate = DateConverter.parseString(new String(tag.value, sessionCharset));
                break;

            case DerivativeEventTime:
                derivativeEventTime = DateConverter.parseString(new String(tag.value, sessionCharset));
                break;
                
            case DerivativeEventPx:
                derivativeEventPx = new Double(new String(tag.value, sessionCharset));
                break;

            case DerivativeEventText:
                derivativeEventText = new String(tag.value, sessionCharset);
                break;

            default:
                String error = "Tag value [" + tag.tagNum + "] not present in [DerivativeEventGroup] fields.";
                LOGGER.severe(error);
                throw new BadFormatMsgException(SessionRejectReason.InvalidTagNumber, tag.tagNum, error);
        }
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
        b.append("{DerivativeEventGroup=");
        printTagValue(b, TagNum.DerivativeEventType, derivativeEventType);
        printDateTagValue(b, TagNum.DerivativeEventDate, derivativeEventDate);
        printDateTagValue(b, TagNum.DerivativeEventTime, derivativeEventTime);
        printTagValue(b, TagNum.DerivativeEventPx, derivativeEventPx);
        printTagValue(b, TagNum.DerivativeEventText, derivativeEventText);
        b.append("}");

        return b.toString();
    }

    // </editor-fold>
}
