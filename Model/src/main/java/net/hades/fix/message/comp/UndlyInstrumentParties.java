/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * UndlyInstrumentParties.java
 *
 * $Id: UndlyInstrumentParties.java,v 1.9 2010-11-23 10:20:19 vrotaru Exp $
 */
package net.hades.fix.message.comp;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.anno.FIXVersion;
import net.hades.fix.message.group.UndlyInstrumentPartyIDGroup;
import net.hades.fix.message.struct.Tag;
import net.hades.fix.message.type.SessionRejectReason;
import net.hades.fix.message.type.TagNum;

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
import net.hades.fix.message.util.TagEncoder;

/**
 * Underlying instrument parties component.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.9 $
 * @created 02/01/2009, 12:05:17 PM
 */
@XmlAccessorType(XmlAccessType.NONE)
public abstract class UndlyInstrumentParties extends Component {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.NoUndlyInstrumentParties.getValue()
    }));

    protected static final Set<Integer> START_DATA_TAGS = null;

    protected static final Set<Integer> REQUIRED_TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.NoUndlyInstrumentParties.getValue()
    }));

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">

    /**
     * TagNum = 1058. Starting with 5.0 version.
     */
    protected Integer noUndlyInstrumentParties;

    /**
     * Starting with 5.0 version.
     */
    protected UndlyInstrumentPartyIDGroup[] undlyInstrumentPartyIDGroups;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public UndlyInstrumentParties() {
        super();
    }

    public UndlyInstrumentParties(FragmentContext context) {
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
    @TagNumRef(tagNum = TagNum.NoUndlyInstrumentParties)
    public Integer getNoUndlyInstrumentParties() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method sets the number of {@link UndlyInstrumentPartyIDGroup} groups. It will also create an array
     * of {@link UndlyInstrumentPartyIDGroup} objects and set the <code>undlyInstrumentPartyIDGroups</code> field with this array.
     * The created objects inside the array need to be populated with data for encoding.<br/>
     * If there where already objects in <code>undlyInstrumentPartyIDGroups</code> array they will be discarded.<br/>
     * @param noUndlyInstrumentParties field value
     */
    @FIXVersion(introduced = "5.0")
    @TagNumRef(tagNum = TagNum.NoUndlyInstrumentParties)
    public void setNoUndlyInstrumentParties(Integer noUndlyInstrumentParties) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter for {@link UndlyInstrumentPartyIDGroup} array of groups.
     * @return field array value
     */
    @FIXVersion(introduced = "5.0")
    public UndlyInstrumentPartyIDGroup[] getUndlyInstrumentPartyIDGroups() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method adds a {@link UndlyInstrumentPartyIDGroup} object to the existing array of <code>undlyInstrumentPartyIDGroups</code>
     * and expands the static array with 1 place.<br/>
     * This method will also update <code>noUndlyInstrumentParties</code> field to the proper value.<br/>
     * Note: If the <code>setNoUndlyInstrumentParties</code> method has been called there will already be a number of objects in the
     * <code>undlyInstrumentPartyIDGroups</code> array created.<br/>
     * @return newly created block and added to the array group object
     */
    @FIXVersion(introduced="5.0")
    public UndlyInstrumentPartyIDGroup addUndlyInstrumentPartyIDGroup() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method deletes a {@link UndlyInstrumentPartyIDGroup} object from the existing array of <code>undlyInstrumentPartyIDGroups</code>
     * and shrink the static array with 1 place.<br/>
     * If the array does not have the index position then a null object will be returned.)<br/>
     * This method will also update <code>noUndlyInstrumentParties</code> field to the proper value.<br/>
     * @param index position in array to be deleted starting at 0
     * @return deleted block object
     */
    @FIXVersion(introduced="5.0")
    public UndlyInstrumentPartyIDGroup deleteUndlyInstrumentPartyIDGroup(int index) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Deletes all the {@link UndlyInstrumentPartyIDGroup} objects from the <code>undlyInstrumentPartyIDGroups</code> array
     * (sets the array to 0 length)<br/>
     * This method will also update <code>noUndlyInstrumentParties</code> field and set it to null.<br/>
     * @return number of elements in array cleared
     */
    @FIXVersion(introduced="5.0")
    public int clearUndlyInstrumentPartyIDGroup() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

    @Override
    protected int getFirstTag() {
        return TagNum.NoUndlyInstrumentParties.getValue();
    }

    @Override
    protected void validateRequiredTags() throws TagNotPresentException {
        StringBuilder errorMsg = new StringBuilder("Tag value(s) for");
        boolean hasMissingTag = false;
         if (noUndlyInstrumentParties == null) {
            errorMsg.append(" [NoUndlyInstrumentParties]");
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
            if (noUndlyInstrumentParties != null) {
                TagEncoder.encode(bao, TagNum.NoUndlyInstrumentParties, noUndlyInstrumentParties);
                if (undlyInstrumentPartyIDGroups != null && undlyInstrumentPartyIDGroups.length == noUndlyInstrumentParties.intValue()) {
                    for (int i = 0; i < noUndlyInstrumentParties.intValue(); i++) {
                        if (undlyInstrumentPartyIDGroups[i] != null) {
                            bao.write(undlyInstrumentPartyIDGroups[i].encode(MsgSecureType.ALL_FIELDS));
                        }
                    }
                } else {
                    String error = "UndlyInstrumentPartyIDGroup field has been set but there is no data or the number of groups does not match.";
                    LOGGER.severe(error);
                    throw new BadFormatMsgException(SessionRejectReason.IncorrectCountForGroups, TagNum.NoInstrumentParties.getValue(), error);
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
            case NoUndlyInstrumentParties:
                noUndlyInstrumentParties = new Integer(new String(tag.value, sessionCharset));
                break;

            default:
                String error = "Tag value [" + tag.tagNum + "] not present in [UndlyInstrumentParties] fields.";
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
        b.append("{UndlyInstrumentParties=");
        printTagValue(b, TagNum.NoUndlyInstrumentParties, noUndlyInstrumentParties);
        printTagValue(b, undlyInstrumentPartyIDGroups);
        b.append("}");

        return b.toString();
    }

    // </editor-fold>
}
