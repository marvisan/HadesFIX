/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * CrossOrderCancelRequestMsg.java
 *
 * $Id: CrossOrderCancelRequestMsg.java,v 1.1 2011-05-21 23:53:23 vrotaru Exp $
 */
package net.hades.fix.message;

import net.hades.fix.message.anno.FIXVersion;
import net.hades.fix.message.struct.Tag;
import net.hades.fix.message.type.CrossType;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import net.hades.fix.message.anno.TagNumRef;
import net.hades.fix.message.comp.Instrument;
import net.hades.fix.message.comp.InstrumentLeg;
import net.hades.fix.message.comp.RootParties;
import net.hades.fix.message.comp.UnderlyingInstrument;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.group.SideCrossOrdCxlGroup;
import net.hades.fix.message.type.ApplVerID;
import net.hades.fix.message.type.BeginString;
import net.hades.fix.message.type.CrossPrioritization;
import net.hades.fix.message.type.MsgType;
import net.hades.fix.message.type.SessionRejectReason;
import net.hades.fix.message.type.TagNum;
import net.hades.fix.message.util.DateConverter;
import net.hades.fix.message.util.TagEncoder;

/**
 * <p>Extracted from FIX protocol documentation <a href="http://www.fixprotocol.org/">FIX Protocol</a></p>
 * Used to fully cancel the remaining open quantity of a cross order.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 21/05/2011, 9:25:04 AM
 */
@XmlAccessorType(XmlAccessType.NONE)
public abstract class CrossOrderCancelRequestMsg extends FIXMsg {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.OrderID.getValue(),
        TagNum.CrossID.getValue(),
        TagNum.OrigCrossID.getValue(),
        TagNum.HostCrossID.getValue(),
        TagNum.CrossType.getValue(),
        TagNum.CrossPrioritization.getValue(),
        TagNum.NoSides.getValue(),
        TagNum.NoUnderlyings.getValue(),
        TagNum.NoLegs.getValue(),
        TagNum.TransactTime.getValue()
    }));

    protected static final Set<Integer> START_DATA_TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
    }));

    protected static final Set<Integer> REQUIRED_TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.CrossID.getValue(),
        TagNum.OrigCrossID.getValue(),
        TagNum.CrossType.getValue(),
        TagNum.CrossPrioritization.getValue(),
        TagNum.NoSides.getValue(),
        TagNum.TransactTime.getValue()
    }));

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">

    /**
     * TagNum = 37. Starting with 4.3 version.
     */
    protected String orderID;

    /**
     * TagNum = 548 REQUIRED. Starting with 4.3 version.
     */
    protected String crossID;

    /**
     * TagNum = 551 REQUIRED. Starting with 4.3 version.
     */
    protected String origCrossID;

    /**
     * TagNum = 961. Starting with 5.0 version.
     */
    protected String hostCrossID;

    /**
     * TagNum = 549 REQUIRED. Starting with 4.3 version.
     */
    protected CrossType crossType;

    /**
     * TagNum = 550 REQUIRED. Starting with 4.3 version.
     */
    protected CrossPrioritization crossPrioritization;
    
    /**
     * Starting with 5.0 version.
     */
    protected RootParties rootParties;

    /**
     * TagNum = 78 REQUIRED. Starting with 4.3 version.
     */
    protected Integer noSides;

    /**
     * Starting with 4.3 version.
     */
    protected SideCrossOrdCxlGroup[] sideCrossOrdCxlGroups;

    /**
     * Starting with 4.3 version.
     */
    protected Instrument instrument;

    /**
     * TagNum = 711. Starting with 4.4 version.
     */
    protected Integer noUnderlyings;

    /**
     * Starting with 4.4 version.
     */
    protected UnderlyingInstrument[] underlyingInstruments;

    /**
     * TagNum = 555. Starting with 4.4 version.
     */
    protected Integer noLegs;

    /**
     * Starting with 4.4 version.
     */
    protected InstrumentLeg[] instrumentLegs;

    /**
     * TagNum = 60 REQUIRED. Starting with 4.3 version.
     */
    protected Date transactTime;
    
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public CrossOrderCancelRequestMsg() {
        super();
    }

    public CrossOrderCancelRequestMsg(Header header, ByteBuffer rawMsg)
    throws InvalidMsgException, TagNotPresentException, BadFormatMsgException {
        super(header, rawMsg);
    }

    public CrossOrderCancelRequestMsg(BeginString beginString) throws InvalidMsgException {
        super(MsgType.CrossOrderCancelRequest.getValue(), beginString);
    }

    public CrossOrderCancelRequestMsg(BeginString beginString, ApplVerID applVerID) throws InvalidMsgException {
        super(MsgType.CrossOrderCancelRequest.getValue(), beginString, applVerID);
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
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.OrderID)
    public String getOrderID() {
        return orderID;
    }

    /**
     * Message field setter.
     * @param orderID field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.OrderID)
    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.CrossID, required=true)
    public String getCrossID() {
        return crossID;
    }

    /**
     * Message field setter.
     * @param crossID field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.CrossID, required=true)
    public void setCrossID(String crossID) {
        this.crossID = crossID;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.OrigCrossID, required=true)
    public String getOrigCrossID() {
        return origCrossID;
    }

    /**
     * Message field setter.
     * @param origCrossID field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.OrigCrossID, required=true)
    public void setOrigCrossID(String origCrossID) {
        this.origCrossID = origCrossID;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.HostCrossID)
    public String getHostCrossID() {
        return hostCrossID;
    }

    /**
     * Message field setter.
     * @param hostCrossID field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.HostCrossID)
    public void setHostCrossID(String hostCrossID) {
        this.hostCrossID = hostCrossID;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.CrossType, required=true)
    public CrossType getCrossType() {
        return crossType;
    }

    /**
     * Message field setter.
     * @param crossType field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.CrossType, required=true)
    public void setCrossType(CrossType crossType) {
        this.crossType = crossType;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.CrossPrioritization, required=true)
    public CrossPrioritization getCrossPrioritization() {
        return crossPrioritization;
    }

    /**
     * Message field setter.
     * @param crossPrioritization field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.CrossPrioritization, required=true)
    public void setCrossPrioritization(CrossPrioritization crossPrioritization) {
        this.crossPrioritization = crossPrioritization;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0")
    public RootParties getRootParties() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Sets the RootParties component class to the proper implementation.
     */
    @FIXVersion(introduced="5.0")
    public void setRootParties() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Sets the RootParties component class to null.
     */
    @FIXVersion(introduced="5.0")
    public void clearRootParties() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.NoSides)
    public Integer getNoSides() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method sets the number of {@link SideCrossOrdCxlGroup} groups. It will also create an array
     * of {@link SideCrossOrdCxlGroup} objects and set the <code>sideCrossOrdCxlGroups</code> field with this array.
     * The created objects inside the array need to be populated with data for encoding.<br/>
     * If there where already objects in <code>sideCrossOrdCxlGroups</code> array they will be discarded.<br/>
     * @param noSides field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.NoSides)
    public void setNoSides(Integer noSides) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter for {@link SideCrossOrdCxlGroup} array of groups.
     * @return field array value
     */
    @FIXVersion(introduced="4.3")
    public SideCrossOrdCxlGroup[] getSideCrossOrdCxlGroups() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method adds a {@link SideCrossOrdCxlGroup} object to the existing array of <code>sideCrossOrdCxlGroups</code>
     * and expands the static array with 1 place.<br/>
     * This method will also update <code>noSides</code> field to the proper value.<br/>
     * Note: If the <code>setNoSides</code> method has been called there will already be a number of objects in the
     * <code>sideCrossOrdCxlGroups</code> array created.<br/>
     * @return newly created block and added to the array group object
     */
    @FIXVersion(introduced="4.3")
    public SideCrossOrdCxlGroup addSideCrossOrdCxlGroup() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method deletes a {@link SideCrossOrdCxlGroup} object from the existing array of <code>sideCrossOrdCxlGroups</code>
     * and shrink the static array with 1 place.<br/>
     * If the array does not have the index position then a null object will be returned.)<br/>
     * This method will also update <code>noSides</code> field to the proper value.<br/>
     * @param index position in array to be deleted starting at 0
     * @return deleted block object
     */
    @FIXVersion(introduced="4.3")
    public SideCrossOrdCxlGroup deleteSideCrossOrdCxlGroup(int index) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Deletes all the {@link SideCrossOrdCxlGroup} objects from the <code>sideCrossOrdCxlGroups</code> array
     * (sets the array to 0 length)<br/>
     * This method will also update <code>noSides</code> field and set it to null.<br/>
     * @return number of elements in array cleared
     */
    @FIXVersion(introduced="4.3")
    public int clearSideCrossOrdCxlGroups() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.3")
    public Instrument getInstrument() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field setter.
     */
    @FIXVersion(introduced="4.3")
    public void setInstrument() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Sets the Instrument attribute to null.
     */
    @FIXVersion(introduced="4.3")
    public void clearInstrument() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.NoUnderlyings)
    public Integer getNoUnderlyings() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method sets the number of {@link UnderlyingInstrument} components. It will also create an array
     * of {@link UnderlyingInstrument} objects and set the <code>underlyingInstruments</code>
     * field with this array.
     * The created objects inside the array need to be populated with data for encoding.<br/>
     * If there where already objects in <code>underlyingInstruments</code> array they will be discarded.<br/>
     * @param noUnderlyings number of MsgTypeGroup objects
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.NoUnderlyings)
    public void setNoUnderlyings(Integer noUnderlyings) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter for {@link UnderlyingInstrument} array of groups.
     * @return field array value
     */
    @FIXVersion(introduced = "4.4")
    public UnderlyingInstrument[] getUnderlyingInstruments() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method adds a {@link UnderlyingInstrument} object to the existing array of <code>underlyingInstruments</code>
     * and expands the static array with 1 place.<br/>
     * This method will also update <code>noUnderlyings</code> field to the proper value.<br/>
     * Note: If the <code>setNoUnderlyings</code> method has been called there will already be a number of objects in the
     * <code>underlyingInstruments</code> array created.<br/>
     * @return newly created block and added to the array group object
     */
    @FIXVersion(introduced = "4.4")
    public UnderlyingInstrument addUnderlyingInstrument() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method deletes a {@link UnderlyingInstrument} object from the existing array of <code>underlyingInstruments</code>
     * and shrink the static array with 1 place.<br/>
     * If the array does not have the index position then a null object will be returned.)<br/>
     * This method will also update <code>noUnderlyings</code> field to the proper value.<br/>
     * @param index position in array to be deleted starting at 0
     * @return deleted block object
     */
    @FIXVersion(introduced = "4.4")
    public UnderlyingInstrument deleteUnderlyingInstrument(int index) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Deletes all the {@link UnderlyingInstrument} objects from the <code>underlyingInstruments</code> array
     * (sets the array to 0 length)<br/>
     * This method will also update <code>noUnderlyings</code> field and set it to null.<br/>
     * @return number of elements in array cleared
     */
    @FIXVersion(introduced = "4.4")
    public int clearUnderlyingInstruments() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.NoLegs)
    public Integer getNoLegs() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method sets the number of {@link InstrumentLeg} components. It will also create an array
     * of {@link InstrumentLeg} objects and set the <code>instrumentLegs</code> field with this array.
     * The created objects inside the array need to be populated with data for encoding.<br/>
     * If there where already objects in <code>instrumentLegs</code> array they will be discarded.<br/>
     * @param noLegs field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.NoLegs)
    public void setNoLegs(Integer noLegs) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter for {@link InstrumentLeg} array of groups.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    public InstrumentLeg[] getInstrumentLegs() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method adds a {@link InstrumentLeg} object to the existing array of <code>instrumentLegs</code>
     * and expands the static array with 1 place.<br/>
     * This method will also update <code>noLegs</code> field to the proper value.<br/>
     * Note: If the <code>setNoLegs</code> method has been called there will already be a number of objects in the
     * <code>instrumentLegs</code> array created.<br/>
     * @return newly created block and added to the array group object
     */
    @FIXVersion(introduced = "4.4")
    public InstrumentLeg addInstrumentLeg() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method deletes a {@link InstrumentLeg} object from the existing array of <code>instrumentLegs</code>
     * and shrink the static array with 1 place.<br/>
     * If the array does not have the index position then a null object will be returned.)<br/>
     * This method will also update <code>noLegs</code> field to the proper value.<br/>
     * @param index position in array to be deleted starting at 0
     * @return deleted block object
     */
    @FIXVersion(introduced = "4.4")
    public InstrumentLeg deleteInstrumentLeg(int index) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Deletes all the {@link InstrumentLeg} objects from the <code>instrumentLegs</code> array
     * (sets the array to 0 length)<br/>
     * This method will also update <code>noLegs</code> field and set it to null.<br/>
     * @return number of elements in array cleared
     */
    @FIXVersion(introduced = "4.4")
    public int clearInstrumentLegs() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.TransactTime, required=true)
    public Date getTransactTime() {
        return transactTime;
    }

    /**
     * Message field setter.
     * @param transactTime field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.TransactTime, required=true)
    public void setTransactTime(Date transactTime) {
        this.transactTime = transactTime;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

    @Override
    protected void validateRequiredTags() throws TagNotPresentException {
        StringBuilder errorMsg = new StringBuilder("Tag value(s) for");
        boolean hasMissingTag = false;
        if (crossID == null || crossID.trim().isEmpty()) {
            errorMsg.append(" [CrossID]");
            hasMissingTag = true;
        }
        if (origCrossID == null || origCrossID.trim().isEmpty()) {
            errorMsg.append(" [OrigCrossID]");
            hasMissingTag = true;
        }
        if (crossType == null) {
            errorMsg.append(" [CrossType]");
            hasMissingTag = true;
        }
        if (crossPrioritization == null) {
            errorMsg.append(" [CrossPrioritization]");
            hasMissingTag = true;
        }
        if (noSides == null || sideCrossOrdCxlGroups == null || noSides.intValue() == 0) {
            errorMsg.append(" [NoSides]");
            hasMissingTag = true;
        }
        if (instrument == null || instrument.getSymbol() == null) {
            errorMsg.append(" [Symbol]");
            hasMissingTag = true;
        }
        if (transactTime == null) {
            errorMsg.append(" [TransactTime]");
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
            TagEncoder.encode(bao, TagNum.OrderID, orderID);
            TagEncoder.encode(bao, TagNum.CrossID, crossID);
            TagEncoder.encode(bao, TagNum.OrigCrossID, origCrossID);
            TagEncoder.encode(bao, TagNum.HostCrossID, hostCrossID);
            if (crossType != null) {
                TagEncoder.encode(bao, TagNum.CrossType, crossType.getValue());
            }
            if (crossPrioritization != null) {
                TagEncoder.encode(bao, TagNum.CrossPrioritization, crossPrioritization.getValue());
            }
            if (rootParties != null) {
                bao.write(rootParties.encode(MsgSecureType.ALL_FIELDS));
            }
            if (noSides != null) {
                TagEncoder.encode(bao, TagNum.NoSides, noSides);
                if (sideCrossOrdCxlGroups != null && sideCrossOrdCxlGroups.length == noSides.intValue()) {
                    for (int i = 0; i < noSides.intValue(); i++) {
                        if (sideCrossOrdCxlGroups[i] != null) {
                            bao.write(sideCrossOrdCxlGroups[i].encode(MsgSecureType.ALL_FIELDS));
                        }
                    }
                } else {
                    String error = "SideCrossOrdCxlGroups field has been set but there is no data or the number of groups does not match.";
                    LOGGER.severe(error);
                    throw new BadFormatMsgException(SessionRejectReason.IncorrectCountForGroups, getHeader().getMsgType(),
                            TagNum.NoSides.getValue(), error);
                }
            }
            if (instrument != null) {
                bao.write(instrument.encode(MsgSecureType.ALL_FIELDS));
            }
            if (noUnderlyings != null) {
                TagEncoder.encode(bao, TagNum.NoUnderlyings, noUnderlyings);
                if (underlyingInstruments != null && underlyingInstruments.length == noUnderlyings.intValue()) {
                    for (int i = 0; i < noUnderlyings.intValue(); i++) {
                        if (underlyingInstruments[i] != null) {
                            bao.write(underlyingInstruments[i].encode(MsgSecureType.ALL_FIELDS));
                        }
                    }
                } else {
                    String error = "UnderlyingInstrument field has been set but there is no data or the number of groups does not match.";
                    LOGGER.severe(error);
                    throw new BadFormatMsgException(SessionRejectReason.IncorrectCountForGroups, getHeader().getMsgType(),
                            TagNum.NoUnderlyings.getValue(), error);
                }
            }
            if (noLegs != null) {
                TagEncoder.encode(bao, TagNum.NoLegs, noLegs);
                if (instrumentLegs != null && instrumentLegs.length == noLegs.intValue()) {
                    for (int i = 0; i < noLegs.intValue(); i++) {
                        if (instrumentLegs[i] != null) {
                            bao.write(instrumentLegs[i].encode(MsgSecureType.ALL_FIELDS));
                        }
                    }
                } else {
                    String error = "InstrumentLeg field has been set but there is no data or the number of groups does not match.";
                    LOGGER.severe(error);
                    throw new BadFormatMsgException(SessionRejectReason.IncorrectCountForGroups, getHeader().getMsgType(),
                            TagNum.NoLegs.getValue(), error);
                }
            }
            TagEncoder.encodeUtcTimestamp(bao, TagNum.TransactTime, transactTime);
 
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
            case OrderID:
                orderID = new String(tag.value, sessionCharset);
                break;

            case CrossID:
                crossID = new String(tag.value, sessionCharset);
                break;

            case OrigCrossID:
                origCrossID = new String(tag.value, sessionCharset);
                break;

            case HostCrossID:
                hostCrossID = new String(tag.value, sessionCharset);
                break;

            case CrossType:
                crossType = CrossType.valueFor(Integer.valueOf(new String(tag.value, sessionCharset)));
                break;

            case CrossPrioritization:
                crossPrioritization = CrossPrioritization.valueFor(Integer.valueOf(new String(tag.value, sessionCharset)));
                break;

            case NoSides:
                noSides = new Integer(new String(tag.value, sessionCharset));
                break;

            case NoUnderlyings:
                noUnderlyings = new Integer(new String(tag.value, sessionCharset));
                break;

            case NoLegs:
                noLegs = new Integer(new String(tag.value, getSessionCharset()));
                break;

            case TransactTime:
                transactTime = DateConverter.parseString(new String(tag.value, sessionCharset));
                break;

            default:
                String error = "Tag value [" + tag.tagNum + "] not present in [CrossOrderCancelRequestMsg] fields.";
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
        StringBuilder b = new StringBuilder("{CrossOrderCancelRequestMsg=");
        b.append(header != null ? header.toString() : "");
        b.append(System.getProperty("line.separator")).append("{Body=");
        printTagValue(b, TagNum.OrderID, orderID);
        printTagValue(b, TagNum.CrossID, crossID);
        printTagValue(b, TagNum.OrigCrossID, origCrossID);
        printTagValue(b, TagNum.HostCrossID, hostCrossID);
        printTagValue(b, TagNum.CrossType, crossType);
        printTagValue(b, TagNum.CrossPrioritization, crossPrioritization);
        printTagValue(b, rootParties);
        printTagValue(b, TagNum.NoSides, noSides);
        printTagValue(b, sideCrossOrdCxlGroups);
        printTagValue(b, instrument);
        printTagValue(b, TagNum.NoUnderlyings, noUnderlyings);
        printTagValue(b, underlyingInstruments);
        printTagValue(b, TagNum.NoLegs, noLegs);
        printTagValue(b, instrumentLegs);
        printUTCDateTimeTagValue(b, TagNum.TransactTime, transactTime);
        b.append("}");
        b.append(trailer != null ? trailer.toString() : "").append("}");

        return b.toString();
    }

    // </editor-fold>
}
