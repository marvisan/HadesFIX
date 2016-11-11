/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * BidResponseMsg.java
 *
 * $Id: BidResponseMsg.java,v 1.2 2011-04-28 10:07:43 vrotaru Exp $
 */
package net.hades.fix.message;

import net.hades.fix.message.anno.FIXVersion;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.struct.Tag;
import net.hades.fix.message.type.ApplVerID;
import net.hades.fix.message.type.MsgType;
import net.hades.fix.message.type.SessionRejectReason;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import net.hades.fix.message.anno.TagNumRef;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.group.BidRespComponentGroup;
import net.hades.fix.message.type.BeginString;
import net.hades.fix.message.type.TagNum;
import net.hades.fix.message.util.TagEncoder;

/**
 * <p>Extracted from FIX protocol documentation <a href="http://www.fixprotocol.org/">FIX Protocol</a></p>
 * The Bid Response message can be used in one of two ways depending on which market conventions are being
 * followed.<br/>
 * In the “Non disclosed” convention the Bid Response message can be used to supply a bid based on the sector,
 * country, index and liquidity information contained within the corresponding bid request message.
 * In the “Disclosed” convention the Bid Response message can be used to supply bids based on the List Order
 * Detail messages sent in advance of the corresponding Bid Request message.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 * @created 16/01/2011, 9:25:04 AM
 */
@XmlAccessorType(XmlAccessType.NONE)
public abstract class BidResponseMsg extends FIXMsg {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.BidID.getValue(),
        TagNum.ClientBidID.getValue(),
        TagNum.NoBidComponents.getValue()
    }));

    protected static final Set<Integer> START_DATA_TAGS = null;

    protected static final Set<Integer> REQUIRED_TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
    }));

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">

    /**
     * TagNum = 390. Starting with 4.2 version.
     */
    protected String bidID;

    /**
     * TagNum = 391 REQUIRED. Starting with 4.2 version.
     */
    protected String clientBidID;

    /**
     * TagNum = 420. Starting with 4.2 version.
     */
    protected Integer noBidComponents;
   
    /**
     * Starting with 4.2 version.
     */
    protected BidRespComponentGroup[] bidComponentGroups;
    
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public BidResponseMsg() {
        super();
    }

    public BidResponseMsg(Header header, ByteBuffer rawMsg)
    throws InvalidMsgException, TagNotPresentException, BadFormatMsgException {
        super(header, rawMsg);
    }

    public BidResponseMsg(BeginString beginString) throws InvalidMsgException {
        super(MsgType.BidResponse.getValue(), beginString);
    }

    public BidResponseMsg(BeginString beginString, ApplVerID applVerID) throws InvalidMsgException {
        super(MsgType.BidResponse.getValue(), beginString, applVerID);
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
    @TagNumRef(tagNum=TagNum.BidID)
    public String getBidID() {
        return bidID;
    }

    /**
     * Message field setter.
     * @param bidID field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.BidID)
    public void setBidID(String bidID) {
        this.bidID = bidID;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.ClientBidID, required=true)
    public String getClientBidID() {
        return clientBidID;
    }

    /**
     * Message field setter.
     * @param clientBidID field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.ClientBidID, required=true)
    public void setClientBidID(String clientBidID) {
        this.clientBidID = clientBidID;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.NoBidComponents)
    public Integer getNoBidComponents() {
        return noBidComponents;
    }

    /**
     * This method sets the number of {@link BidRespComponentGroup} components. It will also create an array
     * of {@link BidRespComponentGroup} objects and set the <code>bidComponentGroups</code>
     * field with this array.
     * The created objects inside the array need to be populated with data for encoding.<br/>
     * If there where already objects in <code>bidComponentGroups</code> array they will be discarded.<br/>
     * @param noBidComponents number of objects
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.NoBidComponents)
    public void setNoBidComponents(Integer noBidComponents) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter for {@link BidRespComponentGroup} array of groups.
     * @return field array value
     */
    @FIXVersion(introduced = "4.2")
    public BidRespComponentGroup[] getBidComponentGroups() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method adds a {@link BidRespComponentGroup} object to the existing array of <code>bidComponentGroups</code>
     * and expands the static array with 1 place.<br/>
     * This method will also update <code>noBidComponents</code> field to the proper value.<br/>
     * Note: If the <code>setNoBidComponents</code> method has been called there will already be a number of objects in the
     * <code>bidComponentGroups</code> array created.<br/>
     * @return newly created block and added to the array group object
     */
    @FIXVersion(introduced = "4.2")
    public BidRespComponentGroup addBidComponentGroup() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method deletes a {@link BidRespComponentGroup} object from the existing array of <code>bidComponentGroups</code>
     * and shrink the static array with 1 place.<br/>
     * If the array does not have the index position then a null object will be returned.)<br/>
     * This method will also update <code>noBidComponents</code> field to the proper value.<br/>
     * @param index position in array to be deleted starting at 0
     * @return deleted block object
     */
    @FIXVersion(introduced = "4.2")
    public BidRespComponentGroup deleteBidComponentGroup(int index) {
        BidRespComponentGroup result = null;
        if (bidComponentGroups != null && bidComponentGroups.length > 0 && bidComponentGroups.length > index) {
            List<BidRespComponentGroup> groups = new ArrayList<BidRespComponentGroup>(Arrays.asList(bidComponentGroups));
            result = groups.remove(index);
            bidComponentGroups = groups.toArray(new BidRespComponentGroup[groups.size()]);
            if (bidComponentGroups.length > 0) {
                noBidComponents = new Integer(bidComponentGroups.length);
            } else {
                bidComponentGroups = null;
                noBidComponents = null;
            }
        }

        return result;
    }

    /**
     * Deletes all the {@link BidRespComponentGroup} objects from the <code>bidComponentGroups</code> array
     * (sets the array to 0 length)<br/>
     * This method will also update <code>noBidComponents</code> field and set it to null.<br/>
     * @return number of elements in array cleared
     */
    @FIXVersion(introduced = "4.2")
    public int clearBidComponentGroups() {
        int result = 0;
        if (bidComponentGroups != null && bidComponentGroups.length > 0) {
            result = bidComponentGroups.length;
            bidComponentGroups = null;
            noBidComponents = null;
        }

        return result;

    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

    @Override
    protected void validateRequiredTags() throws TagNotPresentException {
        StringBuilder errorMsg = new StringBuilder("Tag value(s) for");
        boolean hasMissingTag = false;

        if (noBidComponents == null) {
            errorMsg.append(" [NoBidComponents]");
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
            TagEncoder.encode(bao, TagNum.BidID, bidID);
            TagEncoder.encode(bao, TagNum.ClientBidID, clientBidID);
            if (noBidComponents != null) {
                TagEncoder.encode(bao, TagNum.NoBidComponents, noBidComponents);
                if (bidComponentGroups != null && bidComponentGroups.length == noBidComponents.intValue()) {
                    for (int i = 0; i < noBidComponents.intValue(); i++) {
                        if (bidComponentGroups[i] != null) {
                            bao.write(bidComponentGroups[i].encode(MsgSecureType.ALL_FIELDS));
                        }
                    }
                } else {
                    String error = "BidRespComponentGroups field has been set but there is no data or the number of groups does not match.";
                    LOGGER.severe(error);
                    throw new BadFormatMsgException(SessionRejectReason.IncorrectCountForGroups, getHeader().getMsgType(),
                            TagNum.NoBidComponents.getValue(), error);
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
            case BidID:
                bidID = new String(tag.value, sessionCharset);
                break;

            case ClientBidID:
                clientBidID = new String(tag.value, sessionCharset);
                break;

            case NoBidComponents:
                noBidComponents = new Integer(new String(tag.value, sessionCharset));
                break;

            default:
                String error = "Tag value [" + tag.tagNum + "] not present in [BidResponseMsg] fields.";
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
        StringBuilder b = new StringBuilder("{BidResponseMsg=");
        b.append(header != null ? header.toString() : "");
        b.append(System.getProperty("line.separator")).append("{Body=");
        printTagValue(b, TagNum.BidID, bidID);
        printTagValue(b, TagNum.ClientBidID, clientBidID);
        printTagValue(b, TagNum.NoBidComponents, noBidComponents);
        printTagValue(b, bidComponentGroups);
        b.append("}");
        b.append(trailer != null ? trailer.toString() : "").append("}");

        return b.toString();
    }

    // </editor-fold>
}
