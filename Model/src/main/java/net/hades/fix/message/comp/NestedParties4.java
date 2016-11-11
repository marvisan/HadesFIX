/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * NestedParties4.java
 *
 * $Id: NestedParties4.java,v 1.1 2010-12-22 09:30:31 vrotaru Exp $
 */
package net.hades.fix.message.comp;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.anno.FIXVersion;
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
import net.hades.fix.message.MsgSecureType;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.group.Nested4PartyGroup;
import net.hades.fix.message.type.SessionRejectReason;
import net.hades.fix.message.type.TagNum;
import net.hades.fix.message.util.TagEncoder;

/**
 * The NestedParties4 component block is identical to the NestedParties Block.
 * It is used in other component blocks and repeating groups when nesting
 * will take place resulting in multiple occurrences of the Parties block
 * within a single FIX message. Use of NestedParties4 under these conditions
 * avoids multiple references to the NestedParties block within the same message
 * which is not allowed in FIX tag/value syntax.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 06/04/2009, 4:16:06 PM
 */
@XmlAccessorType(XmlAccessType.NONE)
public abstract class NestedParties4 extends Component {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.NoNested4PartyIDs.getValue()
    }));

    protected static final Set<Integer> REQUIRED_TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.NoNested4PartyIDs.getValue()
    }));

    protected static final Set<Integer> START_DATA_TAGS = null;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">

    /**
     * TagNum = 1414. Starting with 5.0SP1 version.
     */
    protected Integer noNested4PartyIDs;

    /**
     * Starting with 5.0SP1 version.
     */
    protected Nested4PartyGroup[] nested4PartyGroups;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public NestedParties4() {
        super();
    }

    public NestedParties4(FragmentContext context) {
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
    @TagNumRef(tagNum = TagNum.NoNested4PartyIDs)
    public Integer getNoNested4PartyIDs() {
        return noNested4PartyIDs;
    }

    /**
     * This method sets the number of {@link Nested4PartyGroup} groups. It will also create an array
     * of {@link Nested4PartyGroup} objects and set the <code>noNested4PartyIDs</code> field with this array.
     * The created objects inside the array need to be populated with data for encoding.<br/>
     * If there where already objects in <code>nested4PartyGroups</code> array they will be discarded.<br/>
     * @param noNested4PartyIDs field value
     */
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.NoNested4PartyIDs)
    public void setNoNested4PartyIDs(Integer noNested4PartyIDs) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter for {@link Nested4PartyGroup} array of groups.
     * @return field array value
     */
    @FIXVersion(introduced="5.0SP1")
    public Nested4PartyGroup[] getNested4PartyIDGroups() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method adds a {@link Nested4PartyGroup} object to the existing array of <code>nested4PartyGroups</code>
     * and expands the static array with 1 place.<br/>
     * This method will also update <code>noNested4PartyIDs</code> field to the proper value.<br/>
     * Note: If the <code>setNoNested4PartyIDs</code> method has been called there will already be a number of objects in the
     * <code>nested4PartyGroups</code> array created.<br/>
     * @return newly created block and added to the array group object
     */
    @FIXVersion(introduced="5.0SP1")
    public Nested4PartyGroup addNested4PartyGroup() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method deletes a {@link Nested4PartyGroup} object from the existing array of <code>nested4PartyGroups</code>
     * and shrink the static array with 1 place.<br/>
     * If the array does not have the index position then a null object will be returned.)<br/>
     * This method will also update <code>noNested4PartyIDs</code> field to the proper value.<br/>
     * @param index position in array to be deleted starting at 0
     * @return deleted block object
     */
    @FIXVersion(introduced="5.0SP1")
    public Nested4PartyGroup deleteNested4PartyGroup(int index) {
        Nested4PartyGroup result = null;
        if (nested4PartyGroups != null && nested4PartyGroups.length > 0 && nested4PartyGroups.length > index) {
            List<Nested4PartyGroup> groups = new ArrayList<Nested4PartyGroup>(Arrays.asList(nested4PartyGroups));
            result = groups.remove(index);
            nested4PartyGroups = groups.toArray(new Nested4PartyGroup[groups.size()]);
            if (nested4PartyGroups.length > 0) {
                noNested4PartyIDs = new Integer(nested4PartyGroups.length);
            } else {
                nested4PartyGroups = null;
                noNested4PartyIDs = null;
            }
        }

        return result;
    }

    /**
     * Deletes all the {@link Nested4PartyGroup} objects from the <code>nested4PartyGroups</code> array
     * (sets the array to 0 length)<br/>
     * This method will also update <code>noNested4PartyIDs</code> field and set it to null.<br/>
     * @return number of elements in array cleared
     */
    @FIXVersion(introduced="5.0SP1")
    public int clearNested4PartyGroups() {
        int result = 0;
        if (nested4PartyGroups != null && nested4PartyGroups.length > 0) {
            result = nested4PartyGroups.length;
            nested4PartyGroups = null;
            noNested4PartyIDs = null;
        }

        return result;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

    @Override
    protected int getFirstTag() {
        return TagNum.NoNested4PartyIDs.getValue();
    }

    @Override
    protected void validateRequiredTags() throws TagNotPresentException {
        StringBuilder errorMsg = new StringBuilder("Tag value(s) for");
        boolean hasMissingTag = false;
         if (noNested4PartyIDs == null) {
            errorMsg.append(" [NoNested4PartyIDs]");
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
            TagEncoder.encode(bao, TagNum.NoNested4PartyIDs, noNested4PartyIDs);
            if (nested4PartyGroups != null && nested4PartyGroups.length == noNested4PartyIDs.intValue()) {
                for (int i = 0; i < noNested4PartyIDs.intValue(); i++) {
                    if (nested4PartyGroups[i] != null) {
                        bao.write(nested4PartyGroups[i].encode(MsgSecureType.ALL_FIELDS));
                    }
                }
            } else {
                String error = "Nested4PartyGroup field has been set but there is no data or the number of groups does not match.";
                LOGGER.severe(error);
                throw new BadFormatMsgException(SessionRejectReason.IncorrectCountForGroups, TagNum.NoNested4PartyIDs.getValue(), error);
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
            case NoNested4PartyIDs:
                noNested4PartyIDs = new Integer(new String(tag.value, sessionCharset));
                break;

            default:
                String error = "Tag value [" + tag.tagNum + "] not present in [NestedParties4] fields.";
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
        b.append("{NestedParties4=");
        printTagValue(b, TagNum.NoNested4PartyIDs, noNested4PartyIDs);
        printTagValue(b, nested4PartyGroups);
        b.append("}");

        return b.toString();
    }

    // </editor-fold>
}
