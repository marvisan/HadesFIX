/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * LegStipulations.java
 *
 * $Id: LegStipulations.java,v 1.9 2010-11-23 10:20:20 vrotaru Exp $
 */
package net.hades.fix.message.comp;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.MsgSecureType;
import net.hades.fix.message.anno.FIXVersion;
import net.hades.fix.message.group.LegStipulationsGroup;
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

import net.hades.fix.message.anno.TagNumRef;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.util.TagEncoder;

/**
 * The LegStipulations component block has the same usage as the Stipulations
 * component block, but for a leg instrument in a multi-legged security.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.9 $
 * @created 15/02/2009, 6:30:15 PM
 */
@XmlAccessorType(XmlAccessType.NONE)
public abstract class LegStipulations extends Component {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.NoLegStipulations.getValue()
    }));

    protected static final Set<Integer> REQUIRED_TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.NoLegStipulations.getValue()
    }));

    protected static final Set<Integer> START_DATA_TAGS = null;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">

    /**
     * TagNum = 683. Starting with 4.4 version.
     */
    protected Integer noLegStipulations;

    /**
     * Starting with 4.4 version.
     */
    protected LegStipulationsGroup[] legStipulationsGroups;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public LegStipulations() {
        super();
    }

    public LegStipulations(FragmentContext context) {
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
    @TagNumRef(tagNum = TagNum.NoLegStipulations)
    public Integer getNoLegStipulations() {
        return noLegStipulations;
    }

    /**
     * This method sets the number of {@link LegStipulationsGroup} groups. It will also create an array
     * of {@link LegStipulationsGroup} objects and set the <code>noLegStipulations</code> field with this array.
     * The created objects inside the array need to be populated with data for encoding.<br/>
     * If there where already objects in <code>legStipulationsGroups</code> array they will be discarded.<br/>
     * @param noLegStipulations field value
     */
    @FIXVersion(introduced = "4.4")
    @TagNumRef(tagNum = TagNum.NoLegStipulations)
    public void setNoLegStipulations(Integer noLegStipulations) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter for {@link LegStipulationsGroup} array of groups.
     * @return field array value
     */
    @FIXVersion(introduced="4.4")
    public LegStipulationsGroup[] getLegStipulationsGroups() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method adds a {@link LegStipulationsGroup} object to the existing array of <code>legStipulationsGroups</code>
     * and expands the static array with 1 place.<br/>
     * This method will also update <code>noLegStipulations</code> field to the proper value.<br/>
     * Note: If the <code>setNoLegStipulations</code> method has been called there will already be a number of objects in the
     * <code>legStipulationsGroups</code> array created.<br/>
     * @return newly created block and added to the array group object
     */
    @FIXVersion(introduced="4.4")
    public LegStipulationsGroup addLegStipulationsGroup() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method deletes a {@link LegStipulationsGroup} object from the existing array of <code>legStipulationsGroups</code>
     * and shrink the static array with 1 place.<br/>
     * If the array does not have the index position then a null object will be returned.)<br/>
     * This method will also update <code>noLegStipulations</code> field to the proper value.<br/>
     * @param index position in array to be deleted starting at 0
     * @return deleted block object
     */
    @FIXVersion(introduced="4.4")
    public LegStipulationsGroup deleteLegStipulationsGroup(int index) {

        LegStipulationsGroup result = null;

        if (legStipulationsGroups != null && legStipulationsGroups.length > 0 && legStipulationsGroups.length > index) {
            List<LegStipulationsGroup> groups = new ArrayList<LegStipulationsGroup>(Arrays.asList(legStipulationsGroups));
            result = groups.remove(index);
            legStipulationsGroups = groups.toArray(new LegStipulationsGroup[groups.size()]);
            if (legStipulationsGroups.length > 0) {
                noLegStipulations = new Integer(legStipulationsGroups.length);
            } else {
                legStipulationsGroups = null;
                noLegStipulations = null;
            }
        }

        return result;
    }

    /**
     * Deletes all the {@link LegStipulationsGroup} objects from the <code>legStipulationsGroups</code> array
     * (sets the array to 0 length)<br/>
     * This method will also update <code>noLegStipulations</code> field and set it to null.<br/>
     * @return number of elements in array cleared
     */
    @FIXVersion(introduced="4.4")
    public int clearLegStipulationsGroups() {

        int result = 0;
        if (legStipulationsGroups != null && legStipulationsGroups.length > 0) {
            result = legStipulationsGroups.length;
            legStipulationsGroups = null;
            noLegStipulations = null;
        }

        return result;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

    @Override
    protected int getFirstTag() {
        return TagNum.NoLegStipulations.getValue();
    }

    @Override
    protected void validateRequiredTags() throws TagNotPresentException {
        StringBuilder errorMsg = new StringBuilder("Tag value(s) for");
        boolean hasMissingTag = false;
         if (noLegStipulations == null) {
            errorMsg.append(" [NoLegStipulations]");
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
            if (noLegStipulations != null && legStipulationsGroups != null && legStipulationsGroups.length == noLegStipulations.intValue()) {
                TagEncoder.encode(bao, TagNum.NoLegStipulations, noLegStipulations);
                for (int i = 0; i < noLegStipulations.intValue(); i++) {
                    if (legStipulationsGroups[i] != null) {
                        bao.write(legStipulationsGroups[i].encode(MsgSecureType.ALL_FIELDS));
                    }
                }
            } else {
                String error = "StipulationsGroup field has been set but there is no data or the number of groups does not match.";
                LOGGER.severe(error);
                throw new BadFormatMsgException(SessionRejectReason.IncorrectCountForGroups, TagNum.NoLegStipulations.getValue(), error);
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
            case NoLegStipulations:
                noLegStipulations = new Integer(new String(tag.value, sessionCharset));
                break;

            default:
                String error = "Tag value [" + tag.tagNum + "] not present in [LegStipulationsGroup] fields.";
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
        b.append("{LegStipulations=");
        printTagValue(b, TagNum.NoLegStipulations, noLegStipulations);
        printTagValue(b, legStipulationsGroups);
        b.append("}");

        return b.toString();
    }

    // </editor-fold>
}
