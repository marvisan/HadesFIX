/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * RootParties.java
 *
 * $Id: RootParties.java,v 1.9 2011-05-02 04:04:19 vrotaru Exp $
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
import java.util.List;
import java.util.Set;
import java.util.logging.Level;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import net.hades.fix.message.anno.TagNumRef;
import net.hades.fix.message.MsgSecureType;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.group.RootPartyGroup;
import net.hades.fix.message.type.SessionRejectReason;
import net.hades.fix.message.type.TagNum;
import net.hades.fix.message.util.TagEncoder;

/**
 * The RootParties component block is a version of the Parties component block used
 * to provide root information regarding the owning and entering parties of a transaction.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.9 $
 * @created 06/04/2009, 3:16:06 PM
 */
@XmlAccessorType(XmlAccessType.NONE)
public abstract class RootParties extends Component {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">

    /**
     * TagNum = 1116. Starting with 5.0 version.
     */
    protected Integer noRootPartyIDs;

    /**
     * Starting with 5.0 version.
     */
    protected RootPartyGroup[] rootPartyGroups;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public RootParties() {
        super();
    }

    public RootParties(FragmentContext context) {
        super(context);
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Public Methods">

    // ACCESSOR METHODS
    //////////////////////////////////////////

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "5.0")
    @TagNumRef(tagNum = TagNum.NoRootPartyIDs)
    public Integer getNoRootPartyIDs() {
        return noRootPartyIDs;
    }

    /**
     * This method sets the number of {@link RootPartyGroup} groups. It will also create an array
     * of {@link RootPartyGroup} objects and set the <code>noRootPartyIDs</code> field with this array.
     * The created objects inside the array need to be populated with data for encoding.<br/>
     * If there where already objects in <code>rootPartyGroups</code> array they will be discarded.<br/>
     * @param noRootPartyIDs field value
     */
    @FIXVersion(introduced = "5.0")
    @TagNumRef(tagNum = TagNum.NoRootPartyIDs)
    public void setNoRootPartyIDs(Integer noRootPartyIDs) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter for {@link RootPartyGroup} array of groups.
     * @return field array value
     */
    @FIXVersion(introduced = "5.0")
    public RootPartyGroup[] getRootPartyIDGroups() {
        return rootPartyGroups;
    }

    /**
     * This method adds a {@link RootPartyGroup} object to the existing array of <code>rootPartyGroups</code>
     * and expands the static array with 1 place.<br/>
     * This method will also update <code>noRootPartyIDs</code> field to the proper value.<br/>
     * Note: If the <code>setNoRootPartyIDs</code> method has been called there will already be a number of objects in the
     * <code>rootPartyGroups</code> array created.<br/>
     * @return newly created block and added to the array group object
     */
    @FIXVersion(introduced="5.0")
    public RootPartyGroup addRootPartyGroup() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method deletes a {@link RootPartyGroup} object from the existing array of <code>rootPartyGroups</code>
     * and shrink the static array with 1 place.<br/>
     * If the array does not have the index position then a null object will be returned.)<br/>
     * This method will also update <code>noRootPartyIDs</code> field to the proper value.<br/>
     * @param index position in array to be deleted starting at 0
     * @return deleted block object
     */
    @FIXVersion(introduced="5.0")
    public RootPartyGroup deleteRootPartyGroup(int index) {
        RootPartyGroup result = null;
        if (rootPartyGroups != null && rootPartyGroups.length > 0 && rootPartyGroups.length > index) {
            List<RootPartyGroup> groups = new ArrayList<RootPartyGroup>(Arrays.asList(rootPartyGroups));
            result = groups.remove(index);
            rootPartyGroups = groups.toArray(new RootPartyGroup[groups.size()]);
            if (rootPartyGroups.length > 0) {
                noRootPartyIDs = new Integer(rootPartyGroups.length);
            } else {
                rootPartyGroups = null;
                noRootPartyIDs = null;
            }
        }

        return result;
    }

    /**
     * Deletes all the {@link RootPartyGroup} objects from the <code>rootPartyGroups</code> array
     * (sets the array to 0 length)<br/>
     * This method will also update <code>noRootPartyIDs</code> field and set it to null.<br/>
     * @return number of elements in array cleared
     */
    @FIXVersion(introduced="5.0")
    public int clearRootPartyGroups() {
        int result = 0;
        if (rootPartyGroups != null && rootPartyGroups.length > 0) {
            result = rootPartyGroups.length;
            rootPartyGroups = null;
            noRootPartyIDs = null;
        }

        return result;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

    @Override
    protected void validateRequiredTags() throws TagNotPresentException {
        StringBuilder errorMsg = new StringBuilder("Tag value(s) for");
        boolean hasMissingTag = false;
         if (noRootPartyIDs == null) {
            errorMsg.append(" [NoRootPartyIDs]");
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
            TagEncoder.encode(bao, TagNum.NoRootPartyIDs, noRootPartyIDs);
            if (rootPartyGroups != null && rootPartyGroups.length == noRootPartyIDs.intValue()) {
                for (int i = 0; i < noRootPartyIDs.intValue(); i++) {
                    if (rootPartyGroups[i] != null) {
                        bao.write(rootPartyGroups[i].encode(MsgSecureType.ALL_FIELDS));
                    }
                }
            } else {
                String error = "RootPartyGroup field has been set but there is no data or the number of groups does not match.";
                LOGGER.severe(error);
                throw new BadFormatMsgException(SessionRejectReason.IncorrectCountForGroups, TagNum.NoRootPartyIDs.getValue(), error);
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
            case NoRootPartyIDs:
                noRootPartyIDs = new Integer(new String(tag.value, sessionCharset));
                break;

            default:
                String error = "Tag value [" + tag.tagNum + "] not present in [RootParties] fields.";
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
    protected int getFirstTag() {
        return TagNum.NoRootPartyIDs.getValue();
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
        b.append("{RootParties=");
        printTagValue(b, TagNum.NoRootPartyIDs, noRootPartyIDs);
        printTagValue(b, rootPartyGroups);
        b.append("}");

        return b.toString();
    }

    // </editor-fold>
}
