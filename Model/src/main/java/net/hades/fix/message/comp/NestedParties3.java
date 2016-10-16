/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * NestedParties3.java
 *
 * $Id: NestedParties3.java,v 1.2 2011-05-02 04:04:20 vrotaru Exp $
 */
package net.hades.fix.message.comp;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.anno.FIXVersion;
import net.hades.fix.message.group.Nested3PartyGroup;
import net.hades.fix.message.struct.Tag;
import net.hades.fix.message.type.SessionRejectReason;

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
import net.hades.fix.message.MsgSecureType;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.type.TagNum;
import net.hades.fix.message.util.TagEncoder;

/**
 * The NestedParties3 component block is identical to the NestedParties Block.
 * It is used in other component blocks and repeating groups when nesting
 * will take place resulting in multiple occurrences of the Parties block
 * within a single FIX message. Use of NestedParties3 under these conditions
 * avoids multiple references to the NestedParties block within the same message
 * which is not allowed in FIX tag/value syntax.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 * @created 06/04/2009, 3:16:06 PM
 */
@XmlAccessorType(XmlAccessType.NONE)
public abstract class NestedParties3 extends Component {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.NoNested3PartyIDs.getValue()
    }));

    protected static final Set<Integer> REQUIRED_TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.NoNested3PartyIDs.getValue()
    }));

    protected static final Set<Integer> START_DATA_TAGS = null;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">

    /**
     * TagNum = 948. Starting with 4.4 version.
     */
    protected Integer noNested3PartyIDs;

    /**
     * Starting with 4.4 version.
     */
    protected Nested3PartyGroup[] nested3PartyGroups;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public NestedParties3() {
        super();
    }

    public NestedParties3(FragmentContext context) {
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
    @FIXVersion(introduced = "4.4")
    @TagNumRef(tagNum = TagNum.NoNested3PartyIDs)
    public Integer getNoNested3PartyIDs() {
        return noNested3PartyIDs;
    }

    /**
     * This method sets the number of {@link Nested3PartyGroup} groups. It will also create an array
     * of {@link Nested3PartyGroup} objects and set the <code>noNested3PartyIDs</code> field with this array.
     * The created objects inside the array need to be populated with data for encoding.<br/>
     * If there where already objects in <code>nested3PartyGroups</code> array they will be discarded.<br/>
     * @param noNested3PartyIDs field value
     */
    @FIXVersion(introduced = "4.4")
    @TagNumRef(tagNum = TagNum.NoNested3PartyIDs)
    public void setNoNested3PartyIDs(Integer noNested3PartyIDs) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter for {@link Nested3PartyGroup} array of groups.
     * @return field array value
     */
    @FIXVersion(introduced="4.4")
    public Nested3PartyGroup[] getNested3PartyIDGroups() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method adds a {@link Nested3PartyGroup} object to the existing array of <code>nested3PartyGroups</code>
     * and expands the static array with 1 place.<br/>
     * This method will also update <code>noNested3PartyIDs</code> field to the proper value.<br/>
     * Note: If the <code>setNoNested3PartyIDs</code> method has been called there will already be a number of objects in the
     * <code>nested3PartyGroups</code> array created.<br/>
     * @return newly created block and added to the array group object
     */
    @FIXVersion(introduced="4.4")
    public Nested3PartyGroup addNested3PartyGroup() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method deletes a {@link Nested3PartyGroup} object from the existing array of <code>nested3PartyGroups</code>
     * and shrink the static array with 1 place.<br/>
     * If the array does not have the index position then a null object will be returned.)<br/>
     * This method will also update <code>noNested3PartyIDs</code> field to the proper value.<br/>
     * @param index position in array to be deleted starting at 0
     * @return deleted block object
     */
    @FIXVersion(introduced="4.4")
    public Nested3PartyGroup deleteNested3PartyGroup(int index) {
        Nested3PartyGroup result = null;
        if (nested3PartyGroups != null && nested3PartyGroups.length > 0 && nested3PartyGroups.length > index) {
            List<Nested3PartyGroup> groups = new ArrayList<Nested3PartyGroup>(Arrays.asList(nested3PartyGroups));
            result = groups.remove(index);
            nested3PartyGroups = groups.toArray(new Nested3PartyGroup[groups.size()]);
            if (nested3PartyGroups.length > 0) {
                noNested3PartyIDs = new Integer(nested3PartyGroups.length);
            } else {
                nested3PartyGroups = null;
                noNested3PartyIDs = null;
            }
        }

        return result;
    }

    /**
     * Deletes all the {@link Nested3PartyGroup} objects from the <code>nested3PartyGroups</code> array
     * (sets the array to 0 length)<br/>
     * This method will also update <code>noNested3PartyIDs</code> field and set it to null.<br/>
     * @return number of elements in array cleared
     */
    @FIXVersion(introduced="4.4")
    public int clearNested3PartyGroups() {
        int result = 0;
        if (nested3PartyGroups != null && nested3PartyGroups.length > 0) {
            result = nested3PartyGroups.length;
            nested3PartyGroups = null;
            noNested3PartyIDs = null;
        }

        return result;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

    @Override
    protected int getFirstTag() {
        return TagNum.NoNested3PartyIDs.getValue();
    }

    @Override
    protected void validateRequiredTags() throws TagNotPresentException {
        StringBuilder errorMsg = new StringBuilder("Tag value(s) for");
        boolean hasMissingTag = false;
         if (noNested3PartyIDs == null) {
            errorMsg.append(" [NoNested3PartyIDs]");
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
            TagEncoder.encode(bao, TagNum.NoNested3PartyIDs, noNested3PartyIDs);
            if (nested3PartyGroups != null && nested3PartyGroups.length == noNested3PartyIDs.intValue()) {
                for (int i = 0; i < noNested3PartyIDs.intValue(); i++) {
                    if (nested3PartyGroups[i] != null) {
                        bao.write(nested3PartyGroups[i].encode(MsgSecureType.ALL_FIELDS));
                    }
                }
            } else {
                String error = "Nested3PartyGroup field has been set but there is no data or the number of groups does not match.";
                LOGGER.severe(error);
                throw new BadFormatMsgException(SessionRejectReason.IncorrectCountForGroups, TagNum.NoNested3PartyIDs.getValue(), error);
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
            case NoNested3PartyIDs:
                noNested3PartyIDs = new Integer(new String(tag.value, sessionCharset));
                break;

            default:
                String error = "Tag value [" + tag.tagNum + "] not present in [NestedParties3] fields.";
                LOGGER.severe(error);
                throw new BadFormatMsgException(SessionRejectReason.InvalidTagNumber, tag.tagNum, error);
        }
    }

    @Override
    protected ByteBuffer setFragmentDataTagValue(Tag tag, ByteBuffer message) throws BadFormatMsgException {
        return message;
    }

    @Override
    protected Set<Integer> getFragmentSecuredTags() {
        return SECURED_TAGS;
    }

    @Override
    protected Set<Integer> getFragmentDataTags() {
        return START_DATA_TAGS;
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
        b.append("{NestedParties3=");
        printTagValue(b, TagNum.NoNested3PartyIDs, noNested3PartyIDs);
        printTagValue(b, nested3PartyGroups);
        b.append("}");

        return b.toString();
    }

    // </editor-fold>
}
