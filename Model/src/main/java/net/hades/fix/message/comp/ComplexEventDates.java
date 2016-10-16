/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * ComplexEventTimes.java
 *
 * $Id: ComplexEventDates.java,v 1.10 2010-11-23 10:20:19 vrotaru Exp $
 */
package net.hades.fix.message.comp;

import net.hades.fix.message.anno.FIXVersion;
import net.hades.fix.message.group.ComplexEventDateGroup;
import net.hades.fix.message.struct.Tag;

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

import net.hades.fix.message.anno.TagNumRef;
import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.MsgSecureType;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.group.impl.v50sp2.ComplexEventDateGroup50SP2;
import net.hades.fix.message.type.SessionRejectReason;
import net.hades.fix.message.type.TagNum;
import net.hades.fix.message.util.TagEncoder;

/**
 * Complex event dates component.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.10 $
 * @created 04/06/2009, 10:29:22 AM
 */
@XmlAccessorType(XmlAccessType.NONE)
public abstract class ComplexEventDates extends Component {
    
    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.NoComplexEventDates.getValue()
    }));
    
    protected static final Set<Integer> START_DATA_TAGS = null;
    
    protected static final Set<Integer> START_COMP_TAGS;
    
    protected static final Set<Integer> ALL_TAGS;
    
    protected static final Set<Integer> COMPLEX_EVENT_DATE_GROUP_TAGS = new ComplexEventDateGroup50SP2().getFragmentAllTags();
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Static Block">
    
    static {
        ALL_TAGS = new HashSet<Integer>(TAGS);
        ALL_TAGS.addAll(COMPLEX_EVENT_DATE_GROUP_TAGS);
        START_COMP_TAGS = new HashSet<Integer>(COMPLEX_EVENT_DATE_GROUP_TAGS);
    }

    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Attributes">
    
    /**
     * TagNum = 1491. Starting with 5.0SP2 version.
     */
    protected Integer noComplexEventDates;
    
    /**
     * Starting with 5.0SP2 version.
     */
    protected ComplexEventDateGroup[] complexEventDateGroups;
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public ComplexEventDates() {
        super();
    }

    public ComplexEventDates(FragmentContext context) {
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
    @TagNumRef(tagNum = TagNum.NoComplexEventDates)
    public Integer getNoComplexEventDates() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field setter.
     * @param noComplexEventDates field value
     */
    @FIXVersion(introduced = "5.0SP2")
    @TagNumRef(tagNum = TagNum.NoComplexEventDates)
    public void setNoComplexEventDates(Integer noComplexEventDates) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "5.0SP2")
    public ComplexEventDateGroup[] getComplexEventDateGroups() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method adds a {@link ComplexEventDateGroup} object to the existing array of <code>complexEventDateGroups</code>
     * and expands the static array with 1 place.<br/>
     * This method will also update <code>noComplexEventDates</code> field to the proper value.<br/>
     * Note: If the <code>complexEventDateGroups</code> method has been called there will already be a number of objects in the
     * <code>complexEventDateGroups</code> array created.<br/>
     * @return newly created block and added to the array group object
     */
    @FIXVersion(introduced="5.0SP2")
    public ComplexEventDateGroup addComplexEventDateGroup() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }
    
    /**
     * This method deletes a {@link ComplexEventDateGroup} object from the existing array of <code>complexEventDateGroups</code>
     * and shrink the static array with 1 place.<br/>
     * If the array does not have the index position then a null object will be returned.)<br/>
     * This method will also update <code>noComplexEventDates</code> field to the proper value.<br/>
     * @param index position in array to be deleted starting at 0
     * @return deleted block object
     */
    @FIXVersion(introduced="5.0SP2")
    public ComplexEventDateGroup deleteComplexEventDateGroup(int index) {
        ComplexEventDateGroup result = null;
        if (complexEventDateGroups != null && complexEventDateGroups.length > 0 && complexEventDateGroups.length > index) {
            List<ComplexEventDateGroup> groups = new ArrayList<ComplexEventDateGroup>(Arrays.asList(complexEventDateGroups));
            result = groups.remove(index);
            complexEventDateGroups = groups.toArray(new ComplexEventDateGroup[groups.size()]);
            if (complexEventDateGroups.length > 0) {
                noComplexEventDates = new Integer(complexEventDateGroups.length);
            } else {
                complexEventDateGroups = null;
                noComplexEventDates = null;
            }
        }
        
        return result;
    }
    
    /**
     * Deletes all the {@link ComplexEventDateGroup} objects from the <code>complexEventDateGroups</code> array
     * (sets the array to 0 length)<br/>
     * This method will also update <code>noComplexEventDates</code> field and set it to null.<br/>
     * @return number of elements in array cleared
     */
    @FIXVersion(introduced="5.0SP2")
    public int clearComplexEventDateGroups() {
        int result = 0;
        if (complexEventDateGroups != null && complexEventDateGroups.length > 0) {
            result = complexEventDateGroups.length;
            complexEventDateGroups = null;
            noComplexEventDates = null;
        }
        
        return result;
    }
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Protected Methods">
    
    @Override
    protected int getFirstTag() {
        return TagNum.NoComplexEventDates.getValue();
    }
    
    @Override
    protected void validateRequiredTags() throws TagNotPresentException {
        StringBuilder errorMsg = new StringBuilder("Tag value(s) for");
        boolean hasMissingTag = false;
        if (noComplexEventDates == null) {
            errorMsg.append(" [NoComplexEventDates]");
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
            if (noComplexEventDates != null) {
                TagEncoder.encode(bao, TagNum.NoComplexEventDates, noComplexEventDates);
                if (complexEventDateGroups != null && complexEventDateGroups.length == noComplexEventDates.intValue()) {
                    for (int i = 0; i < noComplexEventDates.intValue(); i++) {
                        if (complexEventDateGroups[i] != null) {
                            bao.write(complexEventDateGroups[i].encode(MsgSecureType.ALL_FIELDS));
                        }
                    }
                } else {
                    String error = "ComplexEventDateGroup field has been set but there is no data or the number of groups does not match.";
                    LOGGER.severe(error);
                    throw new BadFormatMsgException(SessionRejectReason.IncorrectCountForGroups, TagNum.NoComplexEventDates.getValue(), error);
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
            case NoComplexEventDates:
                noComplexEventDates = new Integer(new String(tag.value, sessionCharset));
                break;
                
            default:
                String error = "Tag value [" + tag.tagNum + "] not present in [ComplexEventDates] fields.";
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
        b.append("{ComplexEventDates=");
        printTagValue(b, TagNum.NoComplexEventDates, noComplexEventDates);
        printTagValue(b, complexEventDateGroups);
        b.append("}");

        return b.toString();
    }
    
    // </editor-fold>
    
}
