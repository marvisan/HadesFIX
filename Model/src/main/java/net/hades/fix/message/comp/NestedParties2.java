/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * NestedParties2.java
 *
 * $Id: NestedParties2.java,v 1.1 2010-12-22 09:30:31 vrotaru Exp $
 */
package net.hades.fix.message.comp;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.anno.FIXVersion;
import net.hades.fix.message.group.Nested2PartyGroup;
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
 * The NestedParties2 component block is identical to the NestedParties Block.
 * It is used in other component blocks and repeating groups when nesting
 * will take place resulting in multiple occurrences of the Parties block
 * within a single FIX message. Use of NestedParties2 under these conditions
 * avoids multiple references to the NestedParties block within the same message
 * which is not allowed in FIX tag/value syntax.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 06/04/2009, 3:16:06 PM
 */
@XmlAccessorType(XmlAccessType.NONE)
public abstract class NestedParties2 extends Component {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.NoNested2PartyIDs.getValue()
    }));

    protected static final Set<Integer> REQUIRED_TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.NoNested2PartyIDs.getValue()
    }));

    protected static final Set<Integer> START_DATA_TAGS = null;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">

    /**
     * TagNum = 756. Starting with 4.4 version.
     */
    protected Integer noNested2PartyIDs;

    /**
     * Starting with 4.4 version.
     */
    protected Nested2PartyGroup[] nested2PartyGroups;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public NestedParties2() {
        super();
    }

    public NestedParties2(FragmentContext context) {
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
    @TagNumRef(tagNum = TagNum.NoNested2PartyIDs)
    public Integer getNoNested2PartyIDs() {
        return noNested2PartyIDs;
    }

    /**
     * This method sets the number of {@link Nested2PartyGroup} groups. It will also create an array
     * of {@link Nested2PartyGroup} objects and set the <code>noNested2PartyIDs</code> field with this array.
     * The created objects inside the array need to be populated with data for encoding.<br/>
     * If there where already objects in <code>nested2PartyGroups</code> array they will be discarded.<br/>
     * @param noNested2PartyIDs field value
     */
    @FIXVersion(introduced = "4.4")
    @TagNumRef(tagNum = TagNum.NoNested2PartyIDs)
    public void setNoNested2PartyIDs(Integer noNested2PartyIDs) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter for {@link Nested2PartyGroup} array of groups.
     * @return field array value
     */
    @FIXVersion(introduced="4.4")
    public Nested2PartyGroup[] getNested2PartyIDGroups() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method adds a {@link Nested2PartyGroup} object to the existing array of <code>nested2PartyGroups</code>
     * and expands the static array with 1 place.<br/>
     * This method will also update <code>noNested2PartyIDs</code> field to the proper value.<br/>
     * Note: If the <code>setNoNested2PartyIDs</code> method has been called there will already be a number of objects in the
     * <code>nested2PartyGroups</code> array created.<br/>
     * @return newly created block and added to the array group object
     */
    @FIXVersion(introduced="4.4")
    public Nested2PartyGroup addNested2PartyGroup() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method deletes a {@link Nested2PartyGroup} object from the existing array of <code>nested2PartyGroups</code>
     * and shrink the static array with 1 place.<br/>
     * If the array does not have the index position then a null object will be returned.)<br/>
     * This method will also update <code>noNested2PartyIDs</code> field to the proper value.<br/>
     * @param index position in array to be deleted starting at 0
     * @return deleted block object
     */
    @FIXVersion(introduced="4.4")
    public Nested2PartyGroup deleteNested2PartyGroup(int index) {
        Nested2PartyGroup result = null;
        if (nested2PartyGroups != null && nested2PartyGroups.length > 0 && nested2PartyGroups.length > index) {
            List<Nested2PartyGroup> groups = new ArrayList<Nested2PartyGroup>(Arrays.asList(nested2PartyGroups));
            result = groups.remove(index);
            nested2PartyGroups = groups.toArray(new Nested2PartyGroup[groups.size()]);
            if (nested2PartyGroups.length > 0) {
                noNested2PartyIDs = new Integer(nested2PartyGroups.length);
            } else {
                nested2PartyGroups = null;
                noNested2PartyIDs = null;
            }
        }

        return result;
    }

    /**
     * Deletes all the {@link Nested2PartyGroup} objects from the <code>nested2PartyGroups</code> array
     * (sets the array to 0 length)<br/>
     * This method will also update <code>noNested2PartyIDs</code> field and set it to null.<br/>
     * @return number of elements in array cleared
     */
    @FIXVersion(introduced="4.4")
    public int clearNested2PartyGroups() {
        int result = 0;
        if (nested2PartyGroups != null && nested2PartyGroups.length > 0) {
            result = nested2PartyGroups.length;
            nested2PartyGroups = null;
            noNested2PartyIDs = null;
        }

        return result;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

    @Override
    protected int getFirstTag() {
        return TagNum.NoNested2PartyIDs.getValue();
    }

    @Override
    protected void validateRequiredTags() throws TagNotPresentException {
        StringBuilder errorMsg = new StringBuilder("Tag value(s) for");
        boolean hasMissingTag = false;
         if (noNested2PartyIDs == null) {
            errorMsg.append(" [NoNested2PartyIDs]");
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
            TagEncoder.encode(bao, TagNum.NoNested2PartyIDs, noNested2PartyIDs);
            if (nested2PartyGroups != null && nested2PartyGroups.length == noNested2PartyIDs.intValue()) {
                for (int i = 0; i < noNested2PartyIDs.intValue(); i++) {
                    if (nested2PartyGroups[i] != null) {
                        bao.write(nested2PartyGroups[i].encode(MsgSecureType.ALL_FIELDS));
                    }
                }
            } else {
                String error = "Nested2PartyGroup field has been set but there is no data or the number of groups does not match.";
                LOGGER.severe(error);
                throw new BadFormatMsgException(SessionRejectReason.IncorrectCountForGroups, TagNum.NoNested2PartyIDs.getValue(), error);
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
            case NoNested2PartyIDs:
                noNested2PartyIDs = new Integer(new String(tag.value, sessionCharset));
                break;

            default:
                String error = "Tag value [" + tag.tagNum + "] not present in [NestedParties2] fields.";
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
        b.append("{NestedParties2=");
        printTagValue(b, TagNum.NoNested2PartyIDs, noNested2PartyIDs);
        printTagValue(b, nested2PartyGroups);
        b.append("}");

        return b.toString();
    }

    // </editor-fold>
}
