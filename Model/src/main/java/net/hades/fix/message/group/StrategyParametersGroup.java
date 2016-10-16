/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * StrategyParametersGroup.java
 *
 * $Id: StrategyParametersGroup.java,v 1.2 2010-12-12 09:13:11 vrotaru Exp $
 */
package net.hades.fix.message.group;

import net.hades.fix.message.anno.FIXVersion;
import net.hades.fix.message.struct.Tag;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import net.hades.fix.message.anno.TagNumRef;
import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.type.SessionRejectReason;
import net.hades.fix.message.type.StrategyParameterType;
import net.hades.fix.message.type.TagNum;
import net.hades.fix.message.util.TagEncoder;

/**
 * Strategy parameters group.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 * @created 28/12/2008, 11:34:25 AM
 */
@XmlAccessorType(XmlAccessType.NONE)
public abstract class StrategyParametersGroup extends Group {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.StrategyParameterName.getValue(),
        TagNum.StrategyParameterType.getValue(),
        TagNum.StrategyParameterValue.getValue()
    }));

    protected static final Set<Integer> START_DATA_TAGS = null;

    protected static final Set<Integer> START_COMP_TAGS = null;

    protected static final Set<Integer> ALL_TAGS;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">

    static {
        ALL_TAGS = new HashSet<Integer>(TAGS);
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">

    protected Set<Integer> STANDARD_SECURED_TAGS = TAGS;

     /**
     * TagNum = 958. Starting with 5.0 version.
     */
    protected String strategyParameterName;

    /**
     * TagNum = 959. Starting with 5.0 version.
     */
    protected StrategyParameterType strategyParameterType;

    /**
     * TagNum = 960. Starting with 5.0 version.
     */
    protected String strategyParameterValue;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public StrategyParametersGroup() {
    }

    public StrategyParametersGroup(FragmentContext context) {
        super(context);
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Public Methods">

    @Override
    public Set<Integer> getFragmentTags() {
        return TAGS;
    }

    @Override
    public Set<Integer> getFragmentAllTags() {
        return ALL_TAGS;
    }

    // ACCESSOR METHODS
    //////////////////////////////////////////

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "5.0")
    @TagNumRef(tagNum = TagNum.StrategyParameterName)
    public String getStrategyParameterName() {
        return strategyParameterName;
    }

    /**
     * Message field setter.
     * @param strategyParameterName field value
     */
    @FIXVersion(introduced = "5.0")
    @TagNumRef(tagNum = TagNum.StrategyParameterName)
    public void setStrategyParameterName(String strategyParameterName) {
        this.strategyParameterName = strategyParameterName;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "5.0")
    @TagNumRef(tagNum = TagNum.StrategyParameterType)
    public StrategyParameterType getStrategyParameterType() {
        return strategyParameterType;
    }

    /**
     * Message field setter.
     * @param strategyParameterType field value
     */
    @FIXVersion(introduced = "5.0")
    @TagNumRef(tagNum = TagNum.StrategyParameterType)
    public void setStrategyParameterType(StrategyParameterType strategyParameterType) {
        this.strategyParameterType = strategyParameterType;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "5.0")
    @TagNumRef(tagNum = TagNum.StrategyParameterValue)
    public String getStrategyParameterValue() {
        return strategyParameterValue;
    }

    /**
     * Message field setter.
     * @param strategyParameterValue field value
     */
    @FIXVersion(introduced = "5.0")
    @TagNumRef(tagNum = TagNum.StrategyParameterValue)
    public void setStrategyParameterValue(String strategyParameterValue) {
        this.strategyParameterValue = strategyParameterValue;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

    @Override
    protected int getFirstTag() {
        return TagNum.StrategyParameterName.getValue();
    }

    @Override
    protected void validateRequiredTags() throws TagNotPresentException {
        StringBuilder errorMsg = new StringBuilder("Tag value(s) for");
        boolean hasMissingTag = false;
        if (strategyParameterName == null) {
            errorMsg.append(" [StrategyParameterName]");
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
            TagEncoder.encode(bao, TagNum.StrategyParameterName, strategyParameterName);
            if (strategyParameterType != null) {
                TagEncoder.encode(bao, TagNum.StrategyParameterType, strategyParameterType.getValue());
            }
            TagEncoder.encode(bao, TagNum.StrategyParameterValue, strategyParameterValue);
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
            case StrategyParameterName:
                strategyParameterName = new String(tag.value, sessionCharset);
                break;

            case StrategyParameterType:
                strategyParameterType = StrategyParameterType.valueFor(Integer.valueOf(new String(tag.value, sessionCharset)));
                break;

            case StrategyParameterValue:
                strategyParameterValue = new String(tag.value, sessionCharset);
                break;

            default:
                String error = "Tag value [" + tag.tagNum + "] not present in [StrategyParametersGroup] fields.";
                LOGGER.severe(error);
                throw new BadFormatMsgException(SessionRejectReason.InvalidTagNumber, tag.tagNum, error);
        }
    }

    @Override
    protected String getUnsupportedTagMessage() {
        throw new UnsupportedOperationException("No need for error message in group [StrategyParametersGroup].");
    }

    @Override
    protected Set<Integer> getFragmentDataTags() {
        return START_DATA_TAGS;
    }

    @Override
    protected Set<Integer> getFragmentCompTags() {
        return START_COMP_TAGS;
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
        b.append("{StrategyParametersGroup=");
        printTagValue(b, TagNum.StrategyParameterName, strategyParameterName);
        printTagValue(b, TagNum.StrategyParameterType, strategyParameterType);
        printTagValue(b, TagNum.StrategyParameterValue, strategyParameterValue);
        b.append("}");

        return b.toString();
    }

    // </editor-fold>
}
