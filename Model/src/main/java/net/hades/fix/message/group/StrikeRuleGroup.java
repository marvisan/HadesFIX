/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * StrikeRuleGroup.java
 *
 * $Id: StrikeRuleGroup.java,v 1.2 2011-04-19 12:13:36 vrotaru Exp $
 */
package net.hades.fix.message.group;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.anno.FIXVersion;
import net.hades.fix.message.struct.Tag;
import net.hades.fix.message.type.SessionRejectReason;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
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
import net.hades.fix.message.type.StrikeExerciseStyle;
import net.hades.fix.message.type.TagNum;
import net.hades.fix.message.util.TagEncoder;

/**
 * Strike rule group.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 * @created 05/04/2009, 11:10:10 AM
 */
@XmlAccessorType(XmlAccessType.NONE)
public abstract class StrikeRuleGroup extends Group {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.StrikeRuleID.getValue(),
        TagNum.StartStrikePxRange.getValue(),
        TagNum.EndStrikePxRange.getValue(),
        TagNum.StrikeIncrement.getValue(),
        TagNum.StrikeExerciseStyle.getValue(),
        TagNum.NoMaturityRules.getValue()
    }));

    protected static final Set<Integer> START_DATA_TAGS = null;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">

    // ACCESSORS
    //////////////////////////////////////////

    /**
     * TagNum = 1223. Starting with 5.0SP1 version.
     */
    protected String strikeRuleID;

    /**
     * TagNum = 1202. Starting with 5.0SP1 version.
     */
    protected Double startStrikePxRange;

    /**
     * TagNum = 1203. Starting with 5.0SP1 version.
     */
    protected Double endStrikePxRange;

    /**
     * TagNum = 1304. Starting with 5.0SP1 version.
     */
    protected Double strikeIncrement;

    /**
     * TagNum = 1236. Starting with 5.0SP1 version.
     */
    protected StrikeExerciseStyle strikeExerciseStyle;

    /**
     * TagNum = 1204. Starting with 5.0SP1 version.
     */
    protected Integer noMaturityRules;

    /**
     * Starting with 5.0SP1 version.
     */
    protected MaturityRuleGroup[] maturityRuleGroups;


    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public StrikeRuleGroup() {
    }

    public StrikeRuleGroup(FragmentContext context) {
        super(context);
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Public Methods">

    @Override
    public Set<Integer> getFragmentTags() {
        return TAGS;
    }

    // ACCESSORS
    //////////////////////////////////////////

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0SP1")
    @TagNumRef(tagNum=TagNum.StrikeRuleID)
    public String getStrikeRuleID() {
        return strikeRuleID;
    }

    /**
     * Message field setter.
     * @param strikeRuleID field value
     */
    @FIXVersion(introduced="5.0SP1")
    @TagNumRef(tagNum=TagNum.StrikeRuleID)
    public void setStrikeRuleID(String strikeRuleID) {
        this.strikeRuleID = strikeRuleID;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0SP1")
    @TagNumRef(tagNum=TagNum.StartStrikePxRange)
    public Double getStartStrikePxRange() {
        return startStrikePxRange;
    }

    /**
     * Message field setter.
     * @param startStrikePxRange field value
     */
    @FIXVersion(introduced="5.0SP1")
    @TagNumRef(tagNum=TagNum.StartStrikePxRange)
    public void setStartStrikePxRange(Double startStrikePxRange) {
        this.startStrikePxRange = startStrikePxRange;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0SP1")
    @TagNumRef(tagNum=TagNum.EndStrikePxRange)
    public Double getEndStrikePxRange() {
        return endStrikePxRange;
    }

    /**
     * Message field setter.
     * @param endStrikePxRange field value
     */
    @FIXVersion(introduced="5.0SP1")
    @TagNumRef(tagNum=TagNum.EndStrikePxRange)
    public void setEndStrikePxRange(Double endStrikePxRange) {
        this.endStrikePxRange = endStrikePxRange;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0SP1")
    @TagNumRef(tagNum=TagNum.StrikeIncrement)
    public Double getStrikeIncrement() {
        return strikeIncrement;
    }

    /**
     * Message field setter.
     * @param strikeIncrement field value
     */
    @FIXVersion(introduced="5.0SP1")
    @TagNumRef(tagNum=TagNum.StrikeIncrement)
    public void setStrikeIncrement(Double strikeIncrement) {
        this.strikeIncrement = strikeIncrement;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0SP1")
    @TagNumRef(tagNum=TagNum.StrikeExerciseStyle)
    public StrikeExerciseStyle getStrikeExerciseStyle() {
        return strikeExerciseStyle;
    }

    /**
     * Message field setter.
     * @param strikeExerciseStyle field value
     */
    @FIXVersion(introduced="5.0SP1")
    @TagNumRef(tagNum=TagNum.StrikeExerciseStyle)
    public void setStrikeExerciseStyle(StrikeExerciseStyle strikeExerciseStyle) {
        this.strikeExerciseStyle = strikeExerciseStyle;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.NoMaturityRules)
    public Integer getNoMaturityRules() {
        return noMaturityRules;
    }

    /**
     * This method sets the number of {@link MaturityRuleGroup} groups. It will also create an array
     * of {@link MaturityRuleGroup} objects and set the <code>maturityRuleGroups</code> field with this array.
     * The created objects inside the array need to be populated with data for encoding.<br/>
     * If there where already objects in <code>M\maturityRuleGroups</code> array they will be discarded.<br/>
     * @param noMaturityRules field value
     */
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.NoMaturityRules)
    public void setNoMaturityRules(Integer noMaturityRules) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "5.0SP1")
    public MaturityRuleGroup[] getMaturityRuleGroups() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method adds a {@link MaturityRuleGroup} object to the existing array of
     * <code>maturityRuleGroups</code> and expands the static array with 1 place.<br/>
     * This method will also update <code>noMaturityRules</code> field to the proper value.<br/>
     * Note: If the <code>setNoMaturityRules</code> method has been called there will already be a number of objects in the
     * <code>noMaturityRules</code> array created.<br/>
     * @return newly created block and added to the array group object
     */
    @FIXVersion(introduced="5.0")
    public MaturityRuleGroup addMaturityRuleGroup() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method deletes a {@link MaturityRuleGroup} object from the existing array of
     * <code>maturityRuleGroups</code> and shrink the static array with 1 place.<br/>
     * If the array does not have the index position then a null object will be returned.)<br/>
     * This method will also update <code>noMaturityRules</code> field to the proper value.<br/>
     * @param index position in array to be deleted starting at 0
     * @return deleted block object
     */
    @FIXVersion(introduced="5.0")
    public MaturityRuleGroup deleteMaturityRuleGroup(int index) {
        MaturityRuleGroup result = null;
        if (maturityRuleGroups != null && maturityRuleGroups.length > 0 && maturityRuleGroups.length > index) {
            List<MaturityRuleGroup> groups = new ArrayList<MaturityRuleGroup>(Arrays.asList(maturityRuleGroups));
            result = groups.remove(index);
            maturityRuleGroups = groups.toArray(new MaturityRuleGroup[groups.size()]);
            if (maturityRuleGroups.length > 0) {
                noMaturityRules = new Integer(maturityRuleGroups.length);
            } else {
                maturityRuleGroups = null;
                noMaturityRules = null;
            }
        }

        return result;
    }

    /**
     * Deletes all the {@link MaturityRuleGroup} objects from the <code>maturityRuleGroups</code> array
     * (sets the array to 0 length)<br/>
     * This method will also update <code>noMaturityRules</code> field and set it to null.<br/>
     * @return number of elements in array cleared
     */
    @FIXVersion(introduced="5.0")
    public int clearMaturityRuleGroups() {
        int result = 0;
        if (maturityRuleGroups != null && maturityRuleGroups.length > 0) {
            result = maturityRuleGroups.length;
            maturityRuleGroups = null;
            noMaturityRules = null;
        }

        return result;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">
    
    @Override
    protected int getFirstTag() {
        return TagNum.StrikeRuleID.getValue();
    }

    @Override
    protected void validateRequiredTags() throws TagNotPresentException {
        StringBuilder errorMsg = new StringBuilder("Tag value(s) for");
        boolean hasMissingTag = false;
        if (strikeRuleID == null || strikeRuleID.trim().isEmpty()) {
            errorMsg.append(" [StrikeRuleID]");
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
            TagEncoder.encode(bao, TagNum.StrikeRuleID, strikeRuleID);
            TagEncoder.encode(bao, TagNum.StartStrikePxRange, startStrikePxRange);
            TagEncoder.encode(bao, TagNum.EndStrikePxRange, endStrikePxRange);
            TagEncoder.encode(bao, TagNum.StrikeIncrement, strikeIncrement);
            if (strikeExerciseStyle != null) {
                TagEncoder.encode(bao, TagNum.StrikeExerciseStyle, strikeExerciseStyle.getValue());
            }
            if (noMaturityRules != null && noMaturityRules.intValue() > 0) {
                TagEncoder.encode(bao, TagNum.NoMaturityRules, noMaturityRules);
                if (maturityRuleGroups != null && maturityRuleGroups.length == noMaturityRules.intValue()) {
                    for (int i = 0; i < noMaturityRules.intValue(); i++) {
                        if (maturityRuleGroups[i] != null) {
                            bao.write(maturityRuleGroups[i].encode(MsgSecureType.ALL_FIELDS));
                        }
                    }
                } else {
                    String error = "MaturityRuleGroups field has been set but there is no data or the number of groups does not match.";
                    LOGGER.severe(error);
                    throw new BadFormatMsgException(SessionRejectReason.IncorrectCountForGroups, TagNum.NoMaturityRules.getValue(), error);
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
            case StrikeRuleID:
                strikeRuleID = new String(tag.value, sessionCharset);
                break;

            case StartStrikePxRange:
                startStrikePxRange = new Double(new String(tag.value, sessionCharset));
                break;

            case EndStrikePxRange:
                endStrikePxRange = new Double(new String(tag.value, sessionCharset));
                break;

            case StrikeIncrement:
                strikeIncrement = new Double(new String(tag.value, sessionCharset));
                break;

            case StrikeExerciseStyle:
                strikeExerciseStyle = StrikeExerciseStyle.valueFor(Integer.valueOf(new String(tag.value, sessionCharset)));
                break;

            case NoMaturityRules:
                noMaturityRules = new Integer(new String(tag.value, sessionCharset));
                break;

            default:
                String error = "Tag value [" + tag.tagNum + "] not present in [StrikeRuleGroup] fields.";
                LOGGER.severe(error);
                throw new BadFormatMsgException(SessionRejectReason.InvalidTagNumber, tag.tagNum, error);
        }
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
        b.append("{StrikeRuleGroup=");
        printTagValue(b, TagNum.StrikeRuleID, strikeRuleID);
        printTagValue(b, TagNum.StartStrikePxRange, startStrikePxRange);
        printTagValue(b, TagNum.EndStrikePxRange, endStrikePxRange);
        printTagValue(b, TagNum.StrikeIncrement, strikeIncrement);
        printTagValue(b, TagNum.StrikeExerciseStyle, strikeExerciseStyle);
        printTagValue(b, TagNum.NoMaturityRules, noMaturityRules);
        printTagValue(b, maturityRuleGroups);
        b.append("}");

        return b.toString();
    }

    // </editor-fold>
}
