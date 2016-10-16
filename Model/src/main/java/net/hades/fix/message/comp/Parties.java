/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * Parties.java
 *
 * $Id: Parties.java,v 1.10 2011-02-13 06:52:37 vrotaru Exp $
 */
package net.hades.fix.message.comp;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.anno.FIXVersion;
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
import net.hades.fix.message.group.PartyGroup;
import net.hades.fix.message.type.TagNum;
import net.hades.fix.message.util.TagEncoder;

/**
 * The Parties component block is used to identify and convey information on the entities
 * both central and peripheral to the financial transaction represented by the FIX message
 * containing the Parties Block. The Parties block allows many different types of entites
 * to be expressed through use of the PartyRole field and identifies the source of the
 * PartyID through the the PartyIDSource.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.10 $
 * @created 11/02/2009, 8:29:09 PM
 */
@XmlAccessorType(XmlAccessType.NONE)
public abstract class Parties extends Component {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.NoPartyIDs.getValue()
    }));

    protected static final Set<Integer> START_DATA_TAGS = null;

    protected static final Set<Integer> REQUIRED_TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.NoPartyIDs.getValue()
    }));

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">

    /**
     * TagNum = 453. Starting with 5.0 version.
     */
    protected Integer noPartyIDs;

    /**
     * Starting with 5.0 version.
     */
    protected PartyGroup[] partyIDGroups;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public Parties() {
        super();
    }

    public Parties(FragmentContext context) {
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
    @FIXVersion(introduced = "5.0")
    @TagNumRef(tagNum = TagNum.NoPartyIDs)
    public Integer getNoPartyIDs() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method sets the number of {@link PartyGroup} groups. It will also create an array
     * of {@link PartyGroup} objects and set the <code>noPartyIDs</code> field with this array.
     * The created objects inside the array need to be populated with data for encoding.<br/>
     * If there where already objects in <code>partyIDGroups</code> array they will be discarded.<br/>
     * @param noPartyIDs field value
     */
    @FIXVersion(introduced = "5.0")
    @TagNumRef(tagNum = TagNum.NoPartyIDs)
    public void setNoPartyIDs(Integer noPartyIDs) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter for {@link PartyGroup} array of groups.
     * @return field array value
     */
    @FIXVersion(introduced = "5.0")
    public PartyGroup[] getPartyIDGroups() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }
    
    /**
     * This method adds a {@link PartyGroup} object to the existing array of <code>partyIDGroups</code>
     * and expands the static array with 1 place.<br/>
     * This method will also update <code>noPartyIDs</code> field to the proper value.<br/>
     * Note: If the <code>setNoPartyIDs</code> method has been called there will already be a number of objects in the
     * <code>partyIDGroups</code> array created.<br/>
     * @return newly created block and added to the array group object
     */
    @FIXVersion(introduced="5.0")
    public PartyGroup addPartyIDGroup() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method deletes a {@link PartyGroup} object from the existing array of <code>partyIDGroups</code>
     * and shrink the static array with 1 place.<br/>
     * If the array does not have the index position then a null object will be returned.)<br/>
     * This method will also update <code>noPartyIDs</code> field to the proper value.<br/>
     * @param index position in array to be deleted starting at 0
     * @return deleted block object
     */
    @FIXVersion(introduced="5.0")
    public PartyGroup deletePartyIDGroup(int index) {

        PartyGroup result = null;

        if (partyIDGroups != null && partyIDGroups.length > 0 && partyIDGroups.length > index) {
            List<PartyGroup> groups = new ArrayList<PartyGroup>(Arrays.asList(partyIDGroups));
            result = groups.remove(index);
            partyIDGroups = groups.toArray(new PartyGroup[groups.size()]);
            if (partyIDGroups.length > 0) {
                noPartyIDs = new Integer(partyIDGroups.length);
            } else {
                partyIDGroups = null;
                noPartyIDs = null;
            }
        }

        return result;
    }

    /**
     * Deletes all the {@link PartyGroup} objects from the <code>partyIDGroups</code> array
     * (sets the array to 0 length)<br/>
     * This method will also update <code>noPartyIDs</code> field and set it to null.<br/>
     * @return number of elements in array cleared
     */
    @FIXVersion(introduced="5.0")
    public int clearPartyIDGroup() {

        int result = 0;
        if (partyIDGroups != null && partyIDGroups.length > 0) {
            result = partyIDGroups.length;
            partyIDGroups = null;
            noPartyIDs = null;
        }

        return result;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

    @Override
    protected int getFirstTag() {
        return TagNum.NoPartyIDs.getValue();
    }

    @Override
    protected void validateRequiredTags() throws TagNotPresentException {
        StringBuilder errorMsg = new StringBuilder("Tag value(s) for");
        boolean hasMissingTag = false;
         if (noPartyIDs == null) {
            errorMsg.append(" [NoPartyIDs]");
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
            TagEncoder.encode(bao, TagNum.NoPartyIDs, noPartyIDs);
            if (partyIDGroups != null && partyIDGroups.length == noPartyIDs.intValue()) {
                for (int i = 0; i < noPartyIDs.intValue(); i++) {
                    if (partyIDGroups[i] != null) {
                        bao.write(partyIDGroups[i].encode(MsgSecureType.ALL_FIELDS));
                    }
                }
            } else {
                String error = "PartyIDGroup field has been set but there is no data or the number of groups does not match.";
                LOGGER.severe(error);
                throw new BadFormatMsgException(SessionRejectReason.IncorrectCountForGroups, TagNum.NoUnderlyings.getValue(), error);
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
            case NoPartyIDs:
                noPartyIDs = new Integer(new String(tag.value, sessionCharset));
                break;

            default:
                String error = "Tag value [" + tag.tagNum + "] not present in [Parties] fields.";
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
        b.append("{Parties=");
        printTagValue(b, TagNum.NoPartyIDs, noPartyIDs);
        printTagValue(b, partyIDGroups);
        b.append("}");

        return b.toString();
    }

    // </editor-fold>
}
