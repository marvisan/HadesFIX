/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * NewOrderSingleMsg.java
 *
 * $Id: ListStrikePriceMsg.java,v 1.2 2011-04-28 10:07:43 vrotaru Exp $
 */
package net.hades.fix.message;

import net.hades.fix.message.anno.FIXVersion;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.struct.Tag;
import net.hades.fix.message.type.ApplVerID;
import net.hades.fix.message.type.MsgType;
import net.hades.fix.message.type.SessionRejectReason;
import net.hades.fix.message.type.TagNum;
import net.hades.fix.message.util.BooleanConverter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import net.hades.fix.message.anno.TagNumRef;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.group.InstrmtStrikePriceGroup;
import net.hades.fix.message.type.BeginString;
import net.hades.fix.message.util.TagEncoder;


/**
 * <p>Extracted from FIX protocol documentation <a href="http://www.fixprotocol.org/">FIX Protocol</a></p>
 * The strike price message is used to exchange strike price information for principal trades. It can also be used to
 * exchange reference prices for agency trades.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 * @created 21/04/2009, 9:25:04 AM
 */
@XmlAccessorType(XmlAccessType.NONE)
public abstract class ListStrikePriceMsg extends FIXMsg {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.ListID.getValue(),
        TagNum.TotNoStrikes.getValue(),
        TagNum.LastFragment.getValue(),
        TagNum.NoStrikes.getValue()
    }));

    protected static final Set<Integer> START_DATA_TAGS = null;

    protected static final Set<Integer> REQUIRED_TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.ListID.getValue(),
        TagNum.TotNoStrikes.getValue(),
        TagNum.NoStrikes.getValue()
    }));

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">
    
    /**
     * TagNum = 66 REQUIRED. Starting with 4.2 version.
     */
    protected String listID;

    /**
     * TagNum = 422 REQUIRED. Starting with 4.2 version.
     */
    protected Integer totNoStrikes;

    /**
     * TagNum = 893. Starting with 4.4 version.
     */
    protected Boolean lastFragment;

    /**
     * TagNum = 428 REQUIRED. Starting with 4.2 version.
     */
    protected Integer noStrikes;

    /**
     * Starting with 4.0 version.
     */
    protected InstrmtStrikePriceGroup[] instrmtStrikePriceGroups;
    
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public ListStrikePriceMsg() {
        super();
    }

    public ListStrikePriceMsg(Header header, ByteBuffer rawMsg)
    throws InvalidMsgException, TagNotPresentException, BadFormatMsgException {
        super(header, rawMsg);
    }

    public ListStrikePriceMsg(BeginString beginString) throws InvalidMsgException {
        super(MsgType.ListStrikePrice.getValue(), beginString);
    }

    public ListStrikePriceMsg(BeginString beginString, ApplVerID applVerID) throws InvalidMsgException {
        super(MsgType.ListStrikePrice.getValue(), beginString, applVerID);
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
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.ListID, required = true)
    public String getListID() {
        return listID;
    }

    /**
     * Message field setter.
     * @param listID field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.ListID, required = true)
    public void setListID(String listID) {
        this.listID = listID;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.TotNoStrikes, required = true)
    public Integer getTotNoStrikes() {
        return totNoStrikes;
    }

    /**
     * Message field setter.
     * @param totNoStrikes field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.TotNoStrikes, required = true)
    public void setTotNoStrikes(Integer totNoStrikes) {
        this.totNoStrikes = totNoStrikes;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.LastFragment)
    public Boolean getLastFragment() {
        return lastFragment;
    }

    /**
     * Message field setter.
     * @param lastFragment field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.LastFragment)
    public void setLastFragment(Boolean lastFragment) {
        this.lastFragment = lastFragment;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.NoStrikes)
    public Integer getNoStrikes() {
        return noStrikes;
    }

    /**
     * This method sets the number of {@link InstrmtStrikePriceGroup} groups. It will also create an array
     * of {@link InstrmtStrikePriceGroup} objects and set the <code>instrmtStrikePriceGroups</code> field with this array.
     * The created objects inside the array need to be populated with data for encoding.<br/>
     * If there where already objects in <code>instrmtStrikePriceGroups</code> array they will be discarded.<br/>
     * @param noStrikes field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.NoStrikes)
    public void setNoStrikes(Integer noStrikes) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter for {@link InstrmtStrikePriceGroup} array of groups.
     * @return field array value
     */
    @FIXVersion(introduced="4.2")
    public InstrmtStrikePriceGroup[] getInstrmtStrikePriceGroups() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method adds a {@link InstrmtStrikePriceGroup} object to the existing array of <code>instrmtStrikePriceGroups</code>
     * and expands the static array with 1 place.<br/>
     * This method will also update <code>noStrikes</code> field to the proper value.<br/>
     * Note: If the <code>setNoStrikes</code> method has been called there will already be a number of objects in the
     * <code>InstrmtStrikePriceGroups</code> array created.<br/>
     * @return newly created block and added to the array group object
     */
    @FIXVersion(introduced="4.2")
    public InstrmtStrikePriceGroup addInstrmtStrikePriceGroup() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method deletes a {@link InstrmtStrikePriceGroup} object from the existing array of <code>InstrmtStrikePriceGroups</code>
     * and shrink the static array with 1 place.<br/>
     * If the array does not have the index position then a null object will be returned.)<br/>
     * This method will also update <code>noStrikes</code> field to the proper value.<br/>
     * @param index position in array to be deleted starting at 0
     * @return deleted block object
     */
    @FIXVersion(introduced="4.2")
    public InstrmtStrikePriceGroup deleteInstrmtStrikePriceGroup(int index) {
        InstrmtStrikePriceGroup result = null;
        if (instrmtStrikePriceGroups != null && instrmtStrikePriceGroups.length > 0 && instrmtStrikePriceGroups.length > index) {
            List<InstrmtStrikePriceGroup> groups = new ArrayList<InstrmtStrikePriceGroup>(Arrays.asList(instrmtStrikePriceGroups));
            result = groups.remove(index);
            instrmtStrikePriceGroups = groups.toArray(new InstrmtStrikePriceGroup[groups.size()]);
            if (instrmtStrikePriceGroups.length > 0) {
                noStrikes = new Integer(instrmtStrikePriceGroups.length);
            } else {
                instrmtStrikePriceGroups = null;
                noStrikes = null;
            }
        }

        return result;
    }

    /**
     * Deletes all the {@link InstrmtStrikePriceGroup} objects from the <code>InstrmtStrikePriceGroups</code> array
     * (sets the array to 0 length)<br/>
     * This method will also update <code>noStrikes</code> field and set it to null.<br/>
     * @return number of elements in array cleared
     */
    @FIXVersion(introduced="4.2")
    public int clearInstrmtStrikePriceGroups() {
        int result = 0;
        if (instrmtStrikePriceGroups != null && instrmtStrikePriceGroups.length > 0) {
            result = instrmtStrikePriceGroups.length;
            instrmtStrikePriceGroups = null;
            noStrikes = null;
        }

        return result;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

    @Override
    protected void validateRequiredTags() throws TagNotPresentException {
        StringBuilder errorMsg = new StringBuilder("Tag value(s) for");
        boolean hasMissingTag = false;
        if (listID == null || listID.trim().isEmpty()) {
            errorMsg.append(" [ListID]");
            hasMissingTag = true;
        }
        if (totNoStrikes == null) {
            errorMsg.append(" [TotNoStrikes]");
            hasMissingTag = true;
        }
        if (noStrikes == null) {
            errorMsg.append(" [NoStrikes]");
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
            TagEncoder.encode(bao, TagNum.ListID, listID);
            TagEncoder.encode(bao, TagNum.TotNoStrikes, totNoStrikes);
            TagEncoder.encode(bao, TagNum.LastFragment, lastFragment);
            if (noStrikes != null) {
                TagEncoder.encode(bao, TagNum.NoStrikes, noStrikes);
                if (instrmtStrikePriceGroups != null && instrmtStrikePriceGroups.length == noStrikes.intValue()) {
                    for (int i = 0; i < noStrikes.intValue(); i++) {
                        if (instrmtStrikePriceGroups[i] != null) {
                            bao.write(instrmtStrikePriceGroups[i].encode(MsgSecureType.ALL_FIELDS));
                        }
                    }
                } else {
                    String error = "InstrmtStrikePriceGroups field has been set but there is no data or the number of groups does not match.";
                    LOGGER.severe(error);
                    throw new BadFormatMsgException(SessionRejectReason.IncorrectCountForGroups, getHeader().getMsgType(),
                            TagNum.NoStrikes.getValue(), error);
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
            case ListID:
                listID = new String(tag.value, sessionCharset);
                break;

            case TotNoStrikes:
                totNoStrikes = new Integer(new String(tag.value, sessionCharset));
                break;

            case LastFragment:
                lastFragment = BooleanConverter.parse(new String(tag.value, sessionCharset));
                break;

            case NoStrikes:
                noStrikes = new Integer(new String(tag.value, sessionCharset));
                break;

            default:
                String error = "Tag value [" + tag.tagNum + "] not present in [ListStrikePriceMsg] fields.";
                LOGGER.severe(error);
                throw new BadFormatMsgException(SessionRejectReason.InvalidTagNumber, getHeader().getMsgType(),
                        tag.tagNum, error);
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
        StringBuilder b = new StringBuilder("{ListStrikePriceMsg=");
        b.append(header != null ? header.toString() : "");
        b.append(System.getProperty("line.separator")).append("{Body=");
        printTagValue(b, TagNum.ListID, listID);
        printTagValue(b, TagNum.TotNoStrikes, totNoStrikes);
        printTagValue(b, TagNum.LastFragment, lastFragment);
        printTagValue(b, TagNum.NoStrikes, noStrikes);
        printTagValue(b, instrmtStrikePriceGroups);
        b.append("}");
        b.append(trailer != null ? trailer.toString() : "").append("}");

        return b.toString();
    }

    // </editor-fold>
}
