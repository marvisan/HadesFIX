/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * TradingSessionRules.java
 *
 * $Id: TradingSessionRules.java,v 1.1 2011-04-19 12:13:40 vrotaru Exp $
 */
package net.hades.fix.message.comp;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.MsgSecureType;
import net.hades.fix.message.anno.FIXVersion;
import net.hades.fix.message.anno.TagNumRef;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.group.ExecInstRuleGroup;
import net.hades.fix.message.group.OrdTypeRuleGroup;
import net.hades.fix.message.group.TimeInForceRuleGroup;
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

import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.group.MDFeedTypeGroup;
import net.hades.fix.message.group.MatchRuleGroup;
import net.hades.fix.message.util.TagEncoder;

/**
 * Trading session rules component.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 07/12/2008, 12:48:37 PM
 */
@XmlAccessorType(XmlAccessType.NONE)
public abstract class TradingSessionRules extends Component {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.NoOrdTypeRules.getValue(),
        TagNum.NoTimeInForceRules.getValue(),
        TagNum.NoExecInstRules.getValue(),
        TagNum.NoMatchRules.getValue(),
        TagNum.NoMDFeedTypes.getValue()
    }));

    protected static final Set<Integer> START_DATA_TAGS = null;

    protected static final Set<Integer> REQUIRED_TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.NoOrdTypeRules.getValue()
    }));

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">

    /**
     * TagNum = 1237. Starting with 5.0SP1 version.
     */
    protected Integer noOrdTypeRules;

    /**
     * Starting with 5.0SP1 version.
     */
    protected OrdTypeRuleGroup[] ordTypeRuleGroups;

    /**
     * TagNum = 1239. Starting with 5.0SP1 version.
     */
    protected Integer noTimeInForceRules;

    /**
     * Starting with 5.0SP1 version.
     */
    protected TimeInForceRuleGroup[] timeInForceRuleGroups;

    /**
     * TagNum = 1232. Starting with 5.0SP1 version.
     */
    protected Integer noExecInstRules;

    /**
     * Starting with 5.0SP1 version.
     */
    protected ExecInstRuleGroup[] execInstRuleGroups;

    /**
     * TagNum = 1235. Starting with 5.0SP1 version.
     */
    protected Integer noMatchRules;

    /**
     * Starting with 5.0SP1 version.
     */
    protected MatchRuleGroup[] matchRuleGroups;

    /**
     * TagNum = 1141. Starting with 5.0SP1 version.
     */
    protected Integer noMDFeedTypes;

    /**
     * Starting with 5.0SP1 version.
     */
    protected MDFeedTypeGroup[] MDFeedTypeGroup;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public TradingSessionRules() {
        super();
    }

    public TradingSessionRules(FragmentContext context) {
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
    @TagNumRef(tagNum = TagNum.NoOrdTypeRules)
    public Integer getNoOrdTypeRules() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method sets the number of {@link OrdTypeRuleGroup} groups. It will also create an array
     * of {@link OrdTypeRuleGroup} objects and set the <code>ordTypeRuleGroups</code> field with this array.
     * The created objects inside the array need to be populated with data for encoding.<br/>
     * If there where already objects in <code>ordTypeRuleGroups</code> array they will be discarded.<br/>
     * @param noOrdTypeRules field value
     */
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.NoOrdTypeRules)
    public void setNoOrdTypeRules(Integer noOrdTypeRules) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter for {@link OrdTypeRuleGroup} array of groups.
     * @return field array value
     */
    @FIXVersion(introduced="5.0SP1")
    public OrdTypeRuleGroup[] getOrdTypeRuleGroups() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method adds a {@link OrdTypeRuleGroup} object to the existing array of <code>ordTypeRuleGroups</code>
     * and expands the static array with 1 place.<br/>
     * This method will also update <code>noOrdTypeRules</code> field to the proper value.<br/>
     * Note: If the <code>setNoOrdTypeRules</code> method has been called there will already be a number of objects in the
     * <code>noOrdTypeRules</code> array created.<br/>
     * @return newly created block and added to the array group object
     */
    @FIXVersion(introduced="5.0SP1")
    public OrdTypeRuleGroup addOrdTypeRuleGroup() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method deletes a {@link OrdTypeRuleGroup} object from the existing array of <code>ordTypeRuleGroups</code>
     * and shrink the static array with 1 place.<br/>
     * If the array does not have the index position then a null object will be returned.)<br/>
     * This method will also update <code>noOrdTypeRules</code> field to the proper value.<br/>
     * @param index position in array to be deleted starting at 0
     * @return deleted block object
     */
    @FIXVersion(introduced="5.0SP1")
    public OrdTypeRuleGroup deleteOrdTypeRuleGroup(int index) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Deletes all the {@link OrdTypeRuleGroup} objects from the <code>ordTypeRuleGroups</code> array
     * (sets the array to 0 length)<br/>
     * This method will also update <code>noOrdTypeRules</code> field and set it to null.<br/>
     * @return number of elements in array cleared
     */
    @FIXVersion(introduced="5.0SP1")
    public int clearOrdTypeRuleGroups() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.NoTimeInForceRules)
    public Integer getNoTimeInForceRules() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method sets the number of {@link TimeInForceRuleGroup} groups. It will also create an array
     * of {@link TimeInForceRuleGroup} objects and set the <code>timeInForceRuleGroups</code> field with this array.
     * The created objects inside the array need to be populated with data for encoding.<br/>
     * If there where already objects in <code>timeInForceRuleGroups</code> array they will be discarded.<br/>
     * @param noTimeInForceRules field value
     */
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.NoTimeInForceRules)
    public void setNoTimeInForceRules(Integer noTimeInForceRules) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter for {@link TimeInForceRuleGroup} array of groups.
     * @return field array value
     */
    @FIXVersion(introduced="5.0SP1")
    public TimeInForceRuleGroup[] getTimeInForceRuleGroups() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method adds a {@link TimeInForceRuleGroup} object to the existing array of <code>timeInForceRuleGroups</code>
     * and expands the static array with 1 place.<br/>
     * This method will also update <code>noTimeInForceRules</code> field to the proper value.<br/>
     * Note: If the <code>setNoTimeInForceRules</code> method has been called there will already be a number of objects in the
     * <code>noTimeInForceRules</code> array created.<br/>
     * @return newly created block and added to the array group object
     */
    @FIXVersion(introduced="5.0SP1")
    public TimeInForceRuleGroup addTimeInForceRuleGroup() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method deletes a {@link TimeInForceRuleGroup} object from the existing array of <code>timeInForceRuleGroups</code>
     * and shrink the static array with 1 place.<br/>
     * If the array does not have the index position then a null object will be returned.)<br/>
     * This method will also update <code>noTimeInForceRules</code> field to the proper value.<br/>
     * @param index position in array to be deleted starting at 0
     * @return deleted block object
     */
    @FIXVersion(introduced="5.0SP1")
    public TimeInForceRuleGroup deleteTimeInForceRuleGroup(int index) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Deletes all the {@link TimeInForceRuleGroup} objects from the <code>TimeInForceRuleGroups</code> array
     * (sets the array to 0 length)<br/>
     * This method will also update <code>noTimeInForceRules</code> field and set it to null.<br/>
     * @return number of elements in array cleared
     */
    @FIXVersion(introduced="5.0SP1")
    public int clearTimeInForceRuleGroups() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.NoExecInstRules)
    public Integer getNoExecInstRules() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method sets the number of {@link ExecInstRuleGroup} groups. It will also create an array
     * of {@link ExecInstRuleGroup} objects and set the <code>execInstRuleGroups</code> field with this array.
     * The created objects inside the array need to be populated with data for encoding.<br/>
     * If there where already objects in <code>execInstRuleGroups</code> array they will be discarded.<br/>
     * @param noExecInstRules field value
     */
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.NoExecInstRules)
    public void setNoExecInstRules(Integer noExecInstRules) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter for {@link ExecInstRuleGroup} array of groups.
     * @return field array value
     */
    @FIXVersion(introduced="5.0SP1")
    public ExecInstRuleGroup[] getExecInstRuleGroups() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method adds a {@link ExecInstRuleGroup} object to the existing array of <code>execInstRuleGroups</code>
     * and expands the static array with 1 place.<br/>
     * This method will also update <code>noExecInstRules</code> field to the proper value.<br/>
     * Note: If the <code>setNoExecInstRules</code> method has been called there will already be a number of objects in the
     * <code>noExecInstRules</code> array created.<br/>
     * @return newly created block and added to the array group object
     */
    @FIXVersion(introduced="5.0SP1")
    public ExecInstRuleGroup addExecInstRuleGroup() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method deletes a {@link ExecInstRuleGroup} object from the existing array of <code>execInstRuleGroups</code>
     * and shrink the static array with 1 place.<br/>
     * If the array does not have the index position then a null object will be returned.)<br/>
     * This method will also update <code>noExecInstRules</code> field to the proper value.<br/>
     * @param index position in array to be deleted starting at 0
     * @return deleted block object
     */
    @FIXVersion(introduced="5.0SP1")
    public ExecInstRuleGroup deleteExecInstRuleGroup(int index) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Deletes all the {@link ExecInstRuleGroup} objects from the <code>ExecInstRuleGroups</code> array
     * (sets the array to 0 length)<br/>
     * This method will also update <code>noExecInstRules</code> field and set it to null.<br/>
     * @return number of elements in array cleared
     */
    @FIXVersion(introduced="5.0SP1")
    public int clearExecInstRuleGroups() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.NoMatchRules)
    public Integer getNoMatchRules() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method sets the number of {@link MatchRuleGroup} groups. It will also create an array
     * of {@link MatchRuleGroup} objects and set the <code>matchRuleGroups</code> field with this array.
     * The created objects inside the array need to be populated with data for encoding.<br/>
     * If there where already objects in <code>matchRuleGroups</code> array they will be discarded.<br/>
     * @param noMatchRules field value
     */
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.NoMatchRules)
    public void setNoMatchRules(Integer noMatchRules) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter for {@link MatchRuleGroup} array of groups.
     * @return field array value
     */
    @FIXVersion(introduced="5.0SP1")
    public MatchRuleGroup[] getMatchRuleGroups() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method adds a {@link MatchRuleGroup} object to the existing array of <code>matchRuleGroups</code>
     * and expands the static array with 1 place.<br/>
     * This method will also update <code>noTimeInForceRules</code> field to the proper value.<br/>
     * Note: If the <code>setNoMatchRules</code> method has been called there will already be a number of objects in the
     * <code>noMatchRules</code> array created.<br/>
     * @return newly created block and added to the array group object
     */
    @FIXVersion(introduced="5.0SP1")
    public MatchRuleGroup addMatchRuleGroup() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method deletes a {@link MatchRuleGroup} object from the existing array of <code>matchRuleGroups</code>
     * and shrink the static array with 1 place.<br/>
     * If the array does not have the index position then a null object will be returned.)<br/>
     * This method will also update <code>noMatchRules</code> field to the proper value.<br/>
     * @param index position in array to be deleted starting at 0
     * @return deleted block object
     */
    @FIXVersion(introduced="5.0SP1")
    public MatchRuleGroup deleteMatchRuleGroup(int index) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Deletes all the {@link MatchRuleGroup} objects from the <code>MatchRuleGroups</code> array
     * (sets the array to 0 length)<br/>
     * This method will also update <code>noMatchRules</code> field and set it to null.<br/>
     * @return number of elements in array cleared
     */
    @FIXVersion(introduced="5.0SP1")
    public int clearMatchRuleGroups() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.NoMDFeedTypes)
    public Integer getNoMDFeedTypes() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method sets the number of {@link MDFeedTypeGroup} groups. It will also create an array
     * of {@link MDFeedTypeGroup} objects and set the <code>MDFeedTypeGroup</code> field with this array.
     * The created objects inside the array need to be populated with data for encoding.<br/>
     * If there where already objects in <code>MDFeedTypeGroup</code> array they will be discarded.<br/>
     * @param noMDFeedTypes field value
     */
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.NoMDFeedTypes)
    public void setNoMDFeedTypes(Integer noMDFeedTypes) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter for {@link MDFeedTypeGroup} array of groups.
     * @return field array value
     */
    @FIXVersion(introduced="5.0SP1")
    public MDFeedTypeGroup[] getMDFeedTypeGroups() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method adds a {@link MDFeedTypeGroup} object to the existing array of <code>MDFeedTypeGroup</code>
     * and expands the static array with 1 place.<br/>
     * This method will also update <code>noTimeInForceRules</code> field to the proper value.<br/>
     * Note: If the <code>setNoMDFeedTypes</code> method has been called there will already be a number of objects in the
     * <code>noMDFeedTypes</code> array created.<br/>
     * @return newly created block and added to the array group object
     */
    @FIXVersion(introduced="5.0SP1")
    public MDFeedTypeGroup addMDFeedTypeGroup() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method deletes a {@link MDFeedTypeGroup} object from the existing array of <code>MDFeedTypeGroup</code>
     * and shrink the static array with 1 place.<br/>
     * If the array does not have the index position then a null object will be returned.)<br/>
     * This method will also update <code>noMDFeedTypes</code> field to the proper value.<br/>
     * @param index position in array to be deleted starting at 0
     * @return deleted block object
     */
    @FIXVersion(introduced="5.0SP1")
    public MDFeedTypeGroup deleteMDFeedTypeGroup(int index) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Deletes all the {@link MDFeedTypeGroup} objects from the <code>MDFeedTypeGroups</code> array
     * (sets the array to 0 length)<br/>
     * This method will also update <code>noMDFeedTypes</code> field and set it to null.<br/>
     * @return number of elements in array cleared
     */
    @FIXVersion(introduced="5.0SP1")
    public int clearMDFeedTypeGroups() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

    @Override
    protected int getFirstTag() {
        return TagNum.NoOrdTypeRules.getValue();
    }

    @Override
    protected void validateRequiredTags() throws TagNotPresentException {
        StringBuilder errorMsg = new StringBuilder("Tag value(s) for");
        boolean hasMissingTag = false;
         if (noOrdTypeRules == null) {
            errorMsg.append(" [NoOrdTypeRules]");
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
            if (noOrdTypeRules != null && ordTypeRuleGroups != null && ordTypeRuleGroups.length == noOrdTypeRules.intValue()) {
                TagEncoder.encode(bao, TagNum.NoOrdTypeRules, noOrdTypeRules);
                for (int i = 0; i < noOrdTypeRules.intValue(); i++) {
                    if (ordTypeRuleGroups[i] != null) {
                        bao.write(ordTypeRuleGroups[i].encode(MsgSecureType.ALL_FIELDS));
                    }
                }
            } else {
                String error = "OrdTypeRuleGroups field has been set but there is no data or the number of groups does not match.";
                LOGGER.severe(error);
                throw new BadFormatMsgException(SessionRejectReason.IncorrectCountForGroups, TagNum.NoOrdTypeRules.getValue(), error);
            }
            if (noTimeInForceRules != null && timeInForceRuleGroups != null && timeInForceRuleGroups.length == noTimeInForceRules.intValue()) {
                TagEncoder.encode(bao, TagNum.NoTimeInForceRules, noTimeInForceRules);
                for (int i = 0; i < noTimeInForceRules.intValue(); i++) {
                    if (timeInForceRuleGroups[i] != null) {
                        bao.write(timeInForceRuleGroups[i].encode(MsgSecureType.ALL_FIELDS));
                    }
                }
            } else {
                String error = "TimeInForceRuleGroups field has been set but there is no data or the number of groups does not match.";
                LOGGER.severe(error);
                throw new BadFormatMsgException(SessionRejectReason.IncorrectCountForGroups, TagNum.NoTimeInForceRules.getValue(), error);
            }
            if (noExecInstRules != null && execInstRuleGroups != null && execInstRuleGroups.length == noExecInstRules.intValue()) {
                TagEncoder.encode(bao, TagNum.NoExecInstRules, noExecInstRules);
                for (int i = 0; i < noExecInstRules.intValue(); i++) {
                    if (execInstRuleGroups[i] != null) {
                        bao.write(execInstRuleGroups[i].encode(MsgSecureType.ALL_FIELDS));
                    }
                }
            } else {
                String error = "ExecInstRuleGroups field has been set but there is no data or the number of groups does not match.";
                LOGGER.severe(error);
                throw new BadFormatMsgException(SessionRejectReason.IncorrectCountForGroups, TagNum.NoExecInstRules.getValue(), error);
            }
            if (noMatchRules != null && matchRuleGroups != null && matchRuleGroups.length == noMatchRules.intValue()) {
                TagEncoder.encode(bao, TagNum.NoMatchRules, noMatchRules);
                for (int i = 0; i < noMatchRules.intValue(); i++) {
                    if (matchRuleGroups[i] != null) {
                        bao.write(matchRuleGroups[i].encode(MsgSecureType.ALL_FIELDS));
                    }
                }
            } else {
                String error = "MatchRuleGroups field has been set but there is no data or the number of groups does not match.";
                LOGGER.severe(error);
                throw new BadFormatMsgException(SessionRejectReason.IncorrectCountForGroups, TagNum.NoMatchRules.getValue(), error);
            }
            if (noMDFeedTypes != null && MDFeedTypeGroup != null && MDFeedTypeGroup.length == noMDFeedTypes.intValue()) {
                TagEncoder.encode(bao, TagNum.NoMDFeedTypes, noMDFeedTypes);
                for (int i = 0; i < noMDFeedTypes.intValue(); i++) {
                    if (MDFeedTypeGroup[i] != null) {
                        bao.write(MDFeedTypeGroup[i].encode(MsgSecureType.ALL_FIELDS));
                    }
                }
            } else {
                String error = "MDFeedTypeGroups field has been set but there is no data or the number of groups does not match.";
                LOGGER.severe(error);
                throw new BadFormatMsgException(SessionRejectReason.IncorrectCountForGroups, TagNum.NoMDFeedTypes.getValue(), error);
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
            case NoOrdTypeRules:
                noOrdTypeRules = new Integer(new String(tag.value, sessionCharset));
                break;

            case NoTimeInForceRules:
                noTimeInForceRules = new Integer(new String(tag.value, sessionCharset));
                break;

            case NoExecInstRules:
                noExecInstRules = new Integer(new String(tag.value, sessionCharset));
                break;

            case NoMatchRules:
                noMatchRules = new Integer(new String(tag.value, sessionCharset));
                break;

            case NoMDFeedTypes:
                noMDFeedTypes = new Integer(new String(tag.value, sessionCharset));
                break;

            default:
                String error = "Tag value [" + tag.tagNum + "] not present in [TradingSessionRules] fields.";
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
        b.append("{TradingSessionRules=");
        printTagValue(b, TagNum.NoOrdTypeRules, noOrdTypeRules);
        printTagValue(b, ordTypeRuleGroups);
        printTagValue(b, TagNum.NoTimeInForceRules, noTimeInForceRules);
        printTagValue(b, timeInForceRuleGroups);
        printTagValue(b, TagNum.NoExecInstRules, noExecInstRules);
        printTagValue(b, execInstRuleGroups);
        printTagValue(b, TagNum.NoMatchRules, noMatchRules);
        printTagValue(b, matchRuleGroups);
        printTagValue(b, TagNum.NoMDFeedTypes, noMDFeedTypes);
        printTagValue(b, MDFeedTypeGroup);
        b.append("}");

        return b.toString();
    }

    // </editor-fold>
}
