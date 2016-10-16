/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * TargetParties.java
 *
 * $Id: TargetParties.java,v 1.9 2011-05-02 04:04:19 vrotaru Exp $
 */
package net.hades.fix.message.comp;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.anno.FIXVersion;
import net.hades.fix.message.group.TargetPartyGroup;
import net.hades.fix.message.struct.Tag;
import net.hades.fix.message.type.SessionRejectReason;

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
import net.hades.fix.message.type.TagNum;
import net.hades.fix.message.util.TagEncoder;

/**
 * TargetParties groups component.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.9 $
 * @created 04/06/2009, 10:29:22 AM
 */
@XmlAccessorType(XmlAccessType.NONE)
public abstract class TargetParties extends Component {
    
    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.NoTargetPartyIDs.getValue()
    }));
    
    protected static final Set<Integer> START_DATA_TAGS = null;
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Static Block">
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Attributes">
    
    /**
     * TagNum = 1461. Starting with 5.0SP2 version.
     */
    protected Integer noTargetPartyIDs;

    /**
     * Starting with 5.0SP2 version.
     */
    protected TargetPartyGroup[] targetPartyGroups;
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Constructors">
    
    public TargetParties() {
        super();
    }
    
    public TargetParties(FragmentContext context) {
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
    @FIXVersion(introduced="5.0SP2")
    @TagNumRef(tagNum=TagNum.NoTargetPartyIDs)
    public Integer getNoTargetPartyIDs() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }
    
    /**
     * This method sets the number of {@link TargetPartyGroup} groups. It will also create an array
     * of {@link TargetPartyGroup} objects and set the <code>targetPartyGroups</code> field with this array.
     * The created objects inside the array need to be populated with data for encoding.<br/>
     * If there where already objects in <code>targetPartyGroups</code> array they will be discarded.<br/>
     * @param noLegs field value
     */
    @FIXVersion(introduced="5.0SP2")
    @TagNumRef(tagNum=TagNum.NoTargetPartyIDs)
    public void setNoTargetPartyIDs(Integer noLegs) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }
    
    /**
     * Message field getter for {@link TargetPartyGroup} array of groups.
     * @return field array value
     */
    @FIXVersion(introduced = "5.0SP2")
    public TargetPartyGroup[] getTargetPartyGroups() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }
    
    /**
     * This method adds a {@link TargetPartyGroup} object to the existing array of <code>targetPartyGroups</code>
     * and expands the static array with 1 place.<br/>
     * This method will also update <code>noLegs</code> field to the proper value.<br/>
     * Note: If the <code>setNoLegs</code> method has been called there will already be a number of objects in the
     * <code>targetPartyGroups</code> array created.<br/>
     * @return newly created block and added to the array group object
     */
    @FIXVersion(introduced = "5.0SP2")
    public TargetPartyGroup addTargetPartyGroup() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }
    
    /**
     * This method deletes a {@link TargetPartyGroup} object from the existing array of <code>targetPartyGroups</code>
     * and shrink the static array with 1 place.<br/>
     * If the array does not have the index position then a null object will be returned.)<br/>
     * This method will also update <code>noLegs</code> field to the proper value.<br/>
     * @param index position in array to be deleted starting at 0
     * @return deleted block object
     */
    @FIXVersion(introduced = "5.0SP2")
    public TargetPartyGroup deleteTargetPartyGroup(int index) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }
    
    /**
     * Deletes all the {@link TargetPartyGroup} objects from the <code>targetPartyGroups</code> array
     * (sets the array to 0 length)<br/>
     * This method will also update <code>noLegs</code> field and set it to null.<br/>
     * @return number of elements in array cleared
     */
    @FIXVersion(introduced = "5.0SP2")
    public int clearTargetPartyGroups() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Protected Methods">
    
    @Override
    protected int getFirstTag() {
        return TagNum.NoLegs.getValue();
    }
    
    @Override
    protected void validateRequiredTags() throws TagNotPresentException {
        StringBuilder errorMsg = new StringBuilder("Tag value(s) for");
        boolean hasMissingTag = false;
        if (noTargetPartyIDs == null) {
            errorMsg.append(" [NoTargetPartyIDs]");
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
            if (noTargetPartyIDs != null && noTargetPartyIDs.intValue() > 0) {
                TagEncoder.encode(bao, TagNum.NoTargetPartyIDs, noTargetPartyIDs);
                if (targetPartyGroups != null && targetPartyGroups.length == noTargetPartyIDs.intValue()) {
                    for (TargetPartyGroup comp : targetPartyGroups) {
                        bao.write(comp.encode(MsgSecureType.ALL_FIELDS));
                    }
                } else {
                    String error = "TargetPartyGroup field has been set but there is no data or the number of components does not match.";
                    LOGGER.severe(error);
                    throw new BadFormatMsgException(SessionRejectReason.IncorrectCountForGroups, TagNum.NoTargetPartyIDs.getValue(), error);
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
            case NoTargetPartyIDs:
                noTargetPartyIDs = new Integer(new String(tag.value, sessionCharset));
                break;
                
            default:
                String error = "Tag value [" + tag.tagNum + "] not present in [TargetParties] fields.";
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
        b.append("{TargetParties=");
        printTagValue(b, TagNum.NoTargetPartyIDs, noTargetPartyIDs);
        printTagValue(b, targetPartyGroups);
        b.append("}");
        
        return b.toString();
    }
    
    // </editor-fold>
    
}
