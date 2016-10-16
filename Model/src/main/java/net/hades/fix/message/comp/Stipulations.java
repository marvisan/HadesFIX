/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * Stipulations.java
 *
 * $Id: Stipulations.java,v 1.9 2011-05-02 04:04:20 vrotaru Exp $
 */
package net.hades.fix.message.comp;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.anno.FIXVersion;
import net.hades.fix.message.group.StipulationsGroup;
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
 * The Stipulations component block is used in Fixed Income to provide
 * additional information on a given security. These additional information
 * are usually not considered static data information.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.9 $
 * @created 15/02/2009, 11:38:01 AM
 */
@XmlAccessorType(XmlAccessType.NONE)
public abstract class Stipulations extends Component {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.NoStipulations.getValue()
    }));

    protected static final Set<Integer> REQUIRED_TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.NoStipulations.getValue()
    }));

    protected static final Set<Integer> START_DATA_TAGS = null;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">

    /**
     * TagNum = 232. Starting with 4.4 version.
     */
    protected Integer noStipulations;

    /**
     * Starting with 4.4 version.
     */
    protected StipulationsGroup[] stipulationsGroups;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public Stipulations() {
        super();
    }

    public Stipulations(FragmentContext context) {
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
    @TagNumRef(tagNum = TagNum.NoStipulations)
    public Integer getNoStipulations() {
        return noStipulations;
    }

    /**
     * This method sets the number of {@link StipulationsGroup} groups. It will also create an array
     * of {@link StipulationsGroup} objects and set the <code>noStipulations</code> field with this array.
     * The created objects inside the array need to be populated with data for encoding.<br/>
     * If there where already objects in <code>stipulationsGroups</code> array they will be discarded.<br/>
     * @param noStipulations field value
     */
    @FIXVersion(introduced = "4.4")
    @TagNumRef(tagNum = TagNum.NoStipulations)
    public void setNoStipulations(Integer noStipulations) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter for {@link StipulationsGroup} array of groups.
     * @return field array value
     */
    @FIXVersion(introduced="4.4")
    public StipulationsGroup[] getStipulationsGroups() {
        return stipulationsGroups;
    }

    /**
     * This method adds a {@link StipulationsGroup} object to the existing array of <code>stipulationsGroups</code>
     * and expands the static array with 1 place.<br/>
     * This method will also update <code>noStipulations</code> field to the proper value.<br/>
     * Note: If the <code>setNoStipulations</code> method has been called there will already be a number of objects in the
     * <code>stipulationsGroups</code> array created.<br/>
     * @return newly created block and added to the array group object
     */
    @FIXVersion(introduced="4.4")
    public StipulationsGroup addStipulationsGroup() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method deletes a {@link StipulationsGroup} object from the existing array of <code>stipulationsGroups</code>
     * and shrink the static array with 1 place.<br/>
     * If the array does not have the index position then a null object will be returned.)<br/>
     * This method will also update <code>noStipulations</code> field to the proper value.<br/>
     * @param index position in array to be deleted starting at 0
     * @return deleted block object
     */
    @FIXVersion(introduced="4.4")
    public StipulationsGroup deleteStipulationsGroup(int index) {
        StipulationsGroup result = null;
        if (stipulationsGroups != null && stipulationsGroups.length > 0 && stipulationsGroups.length > index) {
            List<StipulationsGroup> groups = new ArrayList<StipulationsGroup>(Arrays.asList(stipulationsGroups));
            result = groups.remove(index);
            stipulationsGroups = groups.toArray(new StipulationsGroup[groups.size()]);
            if (stipulationsGroups.length > 0) {
                noStipulations = new Integer(stipulationsGroups.length);
            } else {
                stipulationsGroups = null;
                noStipulations = null;
            }
        }

        return result;
    }

    /**
     * Deletes all the {@link StipulationsGroup} objects from the <code>stipulationsGroups</code> array
     * (sets the array to 0 length)<br/>
     * This method will also update <code>noStipulations</code> field and set it to null.<br/>
     * @return number of elements in array cleared
     */
    @FIXVersion(introduced="4.4")
    public int clearStipulationsGroups() {
        int result = 0;
        if (stipulationsGroups != null && stipulationsGroups.length > 0) {
            result = stipulationsGroups.length;
            stipulationsGroups = null;
            noStipulations = null;
        }

        return result;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

    @Override
    protected int getFirstTag() {
        return TagNum.NoStipulations.getValue();
    }

    @Override
    protected void validateRequiredTags() throws TagNotPresentException {
        StringBuilder errorMsg = new StringBuilder("Tag value(s) for");
        boolean hasMissingTag = false;
         if (noStipulations == null) {
            errorMsg.append(" [NoStipulations]");
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
            if (noStipulations != null) {
                TagEncoder.encode(bao, TagNum.NoStipulations, noStipulations);
                if (stipulationsGroups != null && stipulationsGroups.length == noStipulations.intValue()) {
                    for (int i = 0; i < noStipulations.intValue(); i++) {
                        if (stipulationsGroups[i] != null) {
                            bao.write(stipulationsGroups[i].encode(MsgSecureType.ALL_FIELDS));
                        }
                    }
                } else {
                    String error = "StipulationsGroup field has been set but there is no data or the number of groups does not match.";
                    LOGGER.severe(error);
                    throw new BadFormatMsgException(SessionRejectReason.IncorrectCountForGroups, TagNum.NoStipulations.getValue(), error);
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
            case NoStipulations:
                noStipulations = new Integer(new String(tag.value, sessionCharset));
                break;

            default:
                String error = "Tag value [" + tag.tagNum + "] not present in [StipulationsGroup] fields.";
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
        b.append("{Stipulations=");
        printTagValue(b, TagNum.NoStipulations, noStipulations);
        printTagValue(b, stipulationsGroups);
        b.append("}");

        return b.toString();
    }

    // </editor-fold>
}
