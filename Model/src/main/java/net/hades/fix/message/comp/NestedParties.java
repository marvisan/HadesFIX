/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * NestedParties.java
 *
 * $Id: NestedParties.java,v 1.9 2011-02-10 10:02:15 vrotaru Exp $
 */
package net.hades.fix.message.comp;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.MsgSecureType;
import net.hades.fix.message.anno.FIXVersion;
import net.hades.fix.message.anno.TagNumRef;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.struct.Tag;
import net.hades.fix.message.type.SessionRejectReason;
import net.hades.fix.message.type.TagNum;

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

import net.hades.fix.message.group.NestedPartyGroup;
import net.hades.fix.message.util.TagEncoder;

/**
 * The NestedParties component block is identical to the Parties Block.
 * It is used in other component blocks and repeating groups when nesting
 * will take place resulting in multiple occurrences of the Parties block
 * within a single FIX message.. Use of NestedParties under these conditions
 * avoids multiple references to the Parties block within the same message
 * which is not allowed in FIX tag/value syntax.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.9 $
 * @created 06/04/2009, 3:16:06 PM
 */
@XmlAccessorType(XmlAccessType.NONE)
public abstract class NestedParties extends Component {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.NoNestedPartyIDs.getValue()
    }));

    protected static final Set<Integer> REQUIRED_TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.NoNestedPartyIDs.getValue()
    }));

    protected static final Set<Integer> START_DATA_TAGS = null;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">

    /**
     * TagNum = 539. Starting with 4.3 version.
     */
    protected Integer noNestedPartyIDs;

    /**
     * Starting with 4.3 version.
     */
    protected NestedPartyGroup[] nestedPartyGroups;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public NestedParties() {
        super();
    }

    public NestedParties(FragmentContext context) {
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
    @FIXVersion(introduced = "4.3")
    @TagNumRef(tagNum = TagNum.NoNestedPartyIDs)
    public Integer getNoNestedPartyIDs() {
        return noNestedPartyIDs;
    }

    /**
     * This method sets the number of {@link NestedPartyGroup} groups. It will also create an array
     * of {@link NestedPartyGroup} objects and set the <code>noNestedPartyIDs</code> field with this array.
     * The created objects inside the array need to be populated with data for encoding.<br/>
     * If there where already objects in <code>nestedPartyGroups</code> array they will be discarded.<br/>
     * @param noNestedPartyIDs field value
     */
    @FIXVersion(introduced = "4.3")
    @TagNumRef(tagNum = TagNum.NoNestedPartyIDs)
    public void setNoNestedPartyIDs(Integer noNestedPartyIDs) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter for {@link NestedPartyGroup} array of groups.
     * @return field array value
     */
    @FIXVersion(introduced="4.3")
    public NestedPartyGroup[] getNestedPartyIDGroups() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method adds a {@link NestedPartyGroup} object to the existing array of <code>nestedPartyGroups</code>
     * and expands the static array with 1 place.<br/>
     * This method will also update <code>noNestedPartyIDs</code> field to the proper value.<br/>
     * Note: If the <code>setNoNestedPartyIDs</code> method has been called there will already be a number of objects in the
     * <code>nestedPartyGroups</code> array created.<br/>
     * @return newly created block and added to the array group object
     */
    @FIXVersion(introduced="4.3")
    public NestedPartyGroup addNestedPartyGroup() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method deletes a {@link NestedPartyGroup} object from the existing array of <code>nestedPartyGroups</code>
     * and shrink the static array with 1 place.<br/>
     * If the array does not have the index position then a null object will be returned.)<br/>
     * This method will also update <code>noNestedPartyIDs</code> field to the proper value.<br/>
     * @param index position in array to be deleted starting at 0
     * @return deleted block object
     */
    @FIXVersion(introduced="4.3")
    public NestedPartyGroup deleteNestedPartyGroup(int index) {

        NestedPartyGroup result = null;

        if (nestedPartyGroups != null && nestedPartyGroups.length > 0 && nestedPartyGroups.length > index) {
            List<NestedPartyGroup> groups = new ArrayList<NestedPartyGroup>(Arrays.asList(nestedPartyGroups));
            result = groups.remove(index);
            nestedPartyGroups = groups.toArray(new NestedPartyGroup[groups.size()]);
            if (nestedPartyGroups.length > 0) {
                noNestedPartyIDs = new Integer(nestedPartyGroups.length);
            } else {
                nestedPartyGroups = null;
                noNestedPartyIDs = null;
            }
        }

        return result;
    }

    /**
     * Deletes all the {@link NestedPartyGroup} objects from the <code>nestedPartyGroups</code> array
     * (sets the array to 0 length)<br/>
     * This method will also update <code>noNestedPartyIDs</code> field and set it to null.<br/>
     * @return number of elements in array cleared
     */
    @FIXVersion(introduced="4.3")
    public int clearNestedPartyGroups() {

        int result = 0;
        if (nestedPartyGroups != null && nestedPartyGroups.length > 0) {
            result = nestedPartyGroups.length;
            nestedPartyGroups = null;
            noNestedPartyIDs = null;
        }

        return result;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

    @Override
    protected int getFirstTag() {
        return TagNum.NoNestedPartyIDs.getValue();
    }

    @Override
    protected void validateRequiredTags() throws TagNotPresentException {
        StringBuilder errorMsg = new StringBuilder("Tag value(s) for");
        boolean hasMissingTag = false;
         if (noNestedPartyIDs == null) {
            errorMsg.append(" [NoNestedPartyIDs]");
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
            TagEncoder.encode(bao, TagNum.NoNestedPartyIDs, noNestedPartyIDs);
            if (nestedPartyGroups != null && nestedPartyGroups.length == noNestedPartyIDs.intValue()) {
                for (int i = 0; i < noNestedPartyIDs.intValue(); i++) {
                    if (nestedPartyGroups[i] != null) {
                        bao.write(nestedPartyGroups[i].encode(MsgSecureType.ALL_FIELDS));
                    }
                }
            } else {
                String error = "NestedPartyGroup field has been set but there is no data or the number of groups does not match.";
                LOGGER.severe(error);
                throw new BadFormatMsgException(SessionRejectReason.IncorrectCountForGroups, TagNum.NoNestedPartyIDs.getValue(), error);
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
            case NoNestedPartyIDs:
                noNestedPartyIDs = new Integer(new String(tag.value, sessionCharset));
                break;

            default:
                String error = "Tag value [" + tag.tagNum + "] not present in [NestedParties] fields.";
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
        b.append("{NestedParties=");
        printTagValue(b, TagNum.NoNestedPartyIDs, noNestedPartyIDs);
        printTagValue(b, nestedPartyGroups);
        b.append("}");

        return b.toString();
    }

    // </editor-fold>
}
