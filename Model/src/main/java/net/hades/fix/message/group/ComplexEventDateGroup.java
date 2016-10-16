/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * ComplexEventTimeGroup.java
 *
 * $Id: ComplexEventDateGroup.java,v 1.9 2010-11-23 10:20:16 vrotaru Exp $
 */
package net.hades.fix.message.group;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import net.hades.fix.message.anno.FIXVersion;
import net.hades.fix.message.anno.TagNumRef;
import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.MsgSecureType;
import net.hades.fix.message.comp.ComplexEventTimes;
import net.hades.fix.message.comp.impl.v50sp2.ComplexEventTimes50SP2;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.struct.Tag;
import net.hades.fix.message.type.SessionRejectReason;
import net.hades.fix.message.type.TagNum;
import net.hades.fix.message.util.DateConverter;
import net.hades.fix.message.util.TagEncoder;

/**
 *  Complex event time group.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.9 $
 * @created 04/06/2009, 10:31:38 AM
 */
@XmlAccessorType(XmlAccessType.NONE)
public abstract class ComplexEventDateGroup extends Group {
    
    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = -3981895290792820587L;

    protected static final Set<Integer> TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.ComplexEventStartDate.getValue(),
        TagNum.ComplexEventEndDate.getValue()
    }));
    
    protected static final Set<Integer> START_DATA_TAGS = null;
    
    protected static final Set<Integer> START_COMP_TAGS;
    
    protected static final Set<Integer> ALL_TAGS;

    protected static final Set<Integer> COMPLEX_EVENT_TIME_COMP_TAGS = new ComplexEventTimes50SP2().getFragmentAllTags();
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Static Block">
    
    static {
        ALL_TAGS = new HashSet<Integer>(TAGS);
        ALL_TAGS.addAll(COMPLEX_EVENT_TIME_COMP_TAGS);
        START_COMP_TAGS = new HashSet<Integer>(COMPLEX_EVENT_TIME_COMP_TAGS);
    }
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Attributes">
    
    /**
     * TagNum = 1492. Starting with 5.0SP2 version.
     */
    protected Date complexEventStartDate;
    
    /**
     * TagNum = 1493. Starting with 5.0SP2 version.
     */
    protected Date complexEventEndDate;

    /**
     * Starting with 5.0SP2 version.
     */
    protected ComplexEventTimes complexEventTimes;
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Constructors">
    
    public ComplexEventDateGroup() {
    }
    
    public ComplexEventDateGroup(FragmentContext context) {
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
    @FIXVersion(introduced = "5.0SP2")
    @TagNumRef(tagNum = TagNum.ComplexEventStartDate)
    public Date getComplexEventStartDate() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field setter.
     * @param complexEventStartDate field value
     */
    @FIXVersion(introduced = "5.0SP2")
    @TagNumRef(tagNum = TagNum.ComplexEventStartDate)
    public void setComplexEventStartDate(Date complexEventStartDate) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "5.0SP2")
    @TagNumRef(tagNum = TagNum.ComplexEventEndDate)
    public Date getComplexEventEndDate() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field setter.
     * @param complexEventEndDate field value
     */
    @FIXVersion(introduced = "5.0SP2")
    @TagNumRef(tagNum = TagNum.ComplexEventEndDate)
    public void setComplexEventEndDate(Date complexEventEndDate) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "5.0SP2")
    public ComplexEventTimes getComplexEventTimes() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Sets the field to the proper component implementation.
     */
    @FIXVersion(introduced = "5.0SP2")
    @TagNumRef(tagNum = TagNum.ComplexEventEndDate)
    public void setComplexEventTimes() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Sets the field to null.
     */
    @FIXVersion(introduced = "5.0SP2")
    public void clearComplexEventTimes() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Protected Methods">
    
    @Override
    protected void validateRequiredTags() throws TagNotPresentException {
        StringBuilder errorMsg = new StringBuilder("Tag value(s) for");
        boolean hasMissingTag = false;
        if (complexEventStartDate == null) {
            errorMsg.append(" [ComplexEventStartDate]");
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
            TagEncoder.encodeUtcTimestamp(bao, TagNum.ComplexEventStartDate, complexEventStartDate);
            TagEncoder.encodeUtcTimestamp(bao, TagNum.ComplexEventEndDate, complexEventEndDate);
            if (complexEventTimes != null) {
                bao.write(complexEventTimes.encode(MsgSecureType.ALL_FIELDS));
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
            case ComplexEventStartDate:
                complexEventStartDate = DateConverter.parseString(new String(tag.value, sessionCharset));;
                break;
                
            case ComplexEventEndDate:
                complexEventEndDate = DateConverter.parseString(new String(tag.value, sessionCharset));;
                break;
                
            default:
                String error = "Tag value [" + tag.tagNum + "] not present in [ComplexEventDateGroup] fields.";
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
    
    @Override
    protected int getFirstTag() {
        return TagNum.ComplexEventStartDate.getValue();
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
        b.append("{ComplexEventDateGroup=");
        printUTCDateTimeTagValue(b, TagNum.ComplexEventStartDate, complexEventStartDate);
        printUTCDateTimeTagValue(b, TagNum.ComplexEventEndDate, complexEventEndDate);
        printTagValue(b, complexEventTimes);
        b.append("}");

        return b.toString();
    }
    
    // </editor-fold>
    
}
