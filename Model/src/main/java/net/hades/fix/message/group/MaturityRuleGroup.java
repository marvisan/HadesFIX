/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * MaturityRuleGroup.java
 *
 * $Id: MaturityRuleGroup.java,v 1.1 2011-04-17 09:30:49 vrotaru Exp $
 */
package net.hades.fix.message.group;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.anno.FIXVersion;
import net.hades.fix.message.struct.Tag;
import net.hades.fix.message.type.MaturityMonthYearIncrementUnits;
import net.hades.fix.message.type.SessionRejectReason;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import net.hades.fix.message.anno.TagNumRef;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.type.MaturityMonthYearFormat;
import net.hades.fix.message.type.TagNum;
import net.hades.fix.message.util.TagEncoder;

/**
 * Maturity rule group.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 05/04/2009, 11:10:10 AM
 */
@XmlAccessorType(XmlAccessType.NONE)
public abstract class MaturityRuleGroup extends Group {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.MaturityRuleID.getValue(),
        TagNum.MaturityMonthYearFormat.getValue(),
        TagNum.MaturityMonthYearIncrementUnits.getValue(),
        TagNum.StartMaturityMonthYear.getValue(),
        TagNum.EndMaturityMonthYear.getValue(),
        TagNum.MaturityMonthYearIncrement.getValue()
    }));

    protected static final Set<Integer> START_DATA_TAGS = null;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">

    // ACCESSORS
    //////////////////////////////////////////


    /**
     * TagNum = 1222. Starting with 5.0SP1 version.
     */
    protected String maturityRuleID;

    /**
     * TagNum = 1303. Starting with 5.0SP1 version.
     */
    protected MaturityMonthYearFormat maturityMonthYearFormat;

    /**
     * TagNum = 1302. Starting with 5.0SP1 version.
     */
    protected MaturityMonthYearIncrementUnits maturityMonthYearIncrementUnits;

    /**
     * TagNum = 1241. Starting with 5.0SP1 version.
     */
    protected String startMaturityMonthYear;

    /**
     * TagNum = 1226. Starting with 5.0SP1 version.
     */
    protected String endMaturityMonthYear;

    /**
     * TagNum = 1229. Starting with 5.0SP1 version.
     */
    protected Integer maturityMonthYearIncrement;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public MaturityRuleGroup() {
    }

    public MaturityRuleGroup(FragmentContext context) {
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
    @TagNumRef(tagNum=TagNum.MaturityRuleID)
    public String getMaturityRuleID() {
        return maturityRuleID;
    }

    /**
     * Message field setter.
     * @param maturityRuleID field value
     */
    @FIXVersion(introduced="5.0SP1")
    @TagNumRef(tagNum=TagNum.MaturityRuleID)
    public void setMaturityRuleID(String maturityRuleID) {
        this.maturityRuleID = maturityRuleID;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0SP1")
    @TagNumRef(tagNum=TagNum.MaturityMonthYearFormat)
    public MaturityMonthYearFormat getMaturityMonthYearFormat() {
        return maturityMonthYearFormat;
    }

    /**
     * Message field setter.
     * @param maturityMonthYearFormat field value
     */
    @FIXVersion(introduced="5.0SP1")
    @TagNumRef(tagNum=TagNum.MaturityMonthYearFormat)
    public void setMaturityMonthYearFormat(MaturityMonthYearFormat maturityMonthYearFormat) {
        this.maturityMonthYearFormat = maturityMonthYearFormat;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0SP1")
    @TagNumRef(tagNum=TagNum.MaturityMonthYearIncrementUnits)
    public MaturityMonthYearIncrementUnits getMaturityMonthYearIncrementUnits() {
        return maturityMonthYearIncrementUnits;
    }

    /**
     * Message field setter.
     * @param maturityMonthYearIncrementUnits field value
     */
    @FIXVersion(introduced="5.0SP1")
    @TagNumRef(tagNum=TagNum.MaturityMonthYearIncrementUnits)
    public void setMaturityMonthYearIncrementUnits(MaturityMonthYearIncrementUnits maturityMonthYearIncrementUnits) {
        this.maturityMonthYearIncrementUnits = maturityMonthYearIncrementUnits;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0SP1")
    @TagNumRef(tagNum=TagNum.StartMaturityMonthYear)
    public String getStartMaturityMonthYear() {
        return startMaturityMonthYear;
    }

    /**
     * Message field setter.
     * @param startMaturityMonthYear field value
     */
    @FIXVersion(introduced="5.0SP1")
    @TagNumRef(tagNum=TagNum.StartMaturityMonthYear)
    public void setStartMaturityMonthYear(String startMaturityMonthYear) {
        this.startMaturityMonthYear = startMaturityMonthYear;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0SP1")
    @TagNumRef(tagNum=TagNum.EndMaturityMonthYear)
    public String getEndMaturityMonthYear() {
        return endMaturityMonthYear;
    }

    /**
     * Message field setter.
     * @param endMaturityMonthYear field value
     */
    @FIXVersion(introduced="5.0SP1")
    @TagNumRef(tagNum=TagNum.EndMaturityMonthYear)
    public void setEndMaturityMonthYear(String endMaturityMonthYear) {
        this.endMaturityMonthYear = endMaturityMonthYear;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0SP1")
    @TagNumRef(tagNum=TagNum.MaturityMonthYearIncrement)
    public Integer getMaturityMonthYearIncrement() {
        return maturityMonthYearIncrement;
    }

    /**
     * Message field setter.
     * @param maturityMonthYearIncrement field value
     */
    @FIXVersion(introduced="5.0SP1")
    @TagNumRef(tagNum=TagNum.MaturityMonthYearIncrement)
    public void setMaturityMonthYearIncrement(Integer maturityMonthYearIncrement) {
        this.maturityMonthYearIncrement = maturityMonthYearIncrement;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">
    
    @Override
    protected int getFirstTag() {
        return TagNum.MaturityRuleID.getValue();
    }

    @Override
    protected void validateRequiredTags() throws TagNotPresentException {
        StringBuilder errorMsg = new StringBuilder("Tag value(s) for");
        boolean hasMissingTag = false;
        if (maturityRuleID == null || maturityRuleID.trim().isEmpty()) {
            errorMsg.append(" [MaturityRuleID]");
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
            TagEncoder.encode(bao, TagNum.MaturityRuleID, maturityRuleID);
            if (maturityMonthYearFormat != null) {
                TagEncoder.encode(bao, TagNum.MaturityMonthYearFormat, maturityMonthYearFormat.getValue());
            }
            if (maturityMonthYearIncrementUnits != null) {
                TagEncoder.encode(bao, TagNum.MaturityMonthYearIncrementUnits, maturityMonthYearIncrementUnits.getValue());
            }
            TagEncoder.encode(bao, TagNum.StartMaturityMonthYear, startMaturityMonthYear);
            TagEncoder.encode(bao, TagNum.EndMaturityMonthYear, endMaturityMonthYear);
            TagEncoder.encode(bao, TagNum.MaturityMonthYearIncrement, maturityMonthYearIncrement);

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
            case MaturityRuleID:
                maturityRuleID = new String(tag.value, sessionCharset);
                break;

            case MaturityMonthYearFormat:
                maturityMonthYearFormat = MaturityMonthYearFormat.valueFor(Integer.valueOf(new String(tag.value, sessionCharset)));
                break;

            case MaturityMonthYearIncrementUnits:
                maturityMonthYearIncrementUnits = MaturityMonthYearIncrementUnits.valueFor(Integer.valueOf(new String(tag.value, sessionCharset)));
                break;

            case StartMaturityMonthYear:
                startMaturityMonthYear = new String(tag.value, sessionCharset);
                break;

            case EndMaturityMonthYear:
                endMaturityMonthYear = new String(tag.value, sessionCharset);
                break;

            case MaturityMonthYearIncrement:
                maturityMonthYearIncrement = new Integer(new String(tag.value, sessionCharset));
                break;

            default:
                String error = "Tag value [" + tag.tagNum + "] not present in [MaturityRuleGroup] fields.";
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
        b.append("{MaturityRuleGroup=");
        printTagValue(b, TagNum.MaturityRuleID, maturityRuleID);
        printTagValue(b, TagNum.MaturityMonthYearFormat, maturityMonthYearFormat);
        printTagValue(b, TagNum.MaturityMonthYearIncrementUnits, maturityMonthYearIncrementUnits);
        printTagValue(b, TagNum.StartMaturityMonthYear, startMaturityMonthYear);
        printTagValue(b, TagNum.EndMaturityMonthYear, endMaturityMonthYear);
        printTagValue(b, TagNum.MaturityMonthYearIncrement, maturityMonthYearIncrement);
        b.append("}");

        return b.toString();
    }

    // </editor-fold>
}
