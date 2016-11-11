/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * TickRuleGroup.java
 *
 * $Id: TickRuleGroup.java,v 1.2 2011-04-19 12:13:35 vrotaru Exp $
 */
package net.hades.fix.message.group;

import net.hades.fix.message.FragmentContext;
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
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.type.SessionRejectReason;
import net.hades.fix.message.type.TagNum;
import net.hades.fix.message.type.TickRuleType;
import net.hades.fix.message.util.TagEncoder;

/**
 * Tick rule group.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 * @created 05/04/2009, 11:10:10 AM
 */
@XmlAccessorType(XmlAccessType.NONE)
public abstract class TickRuleGroup extends Group {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.StartTickPriceRange.getValue(),
        TagNum.EndTickPriceRange.getValue(),
        TagNum.TickIncrement.getValue(),
        TagNum.TickRuleType.getValue()
    }));

    protected static final Set<Integer> START_DATA_TAGS = null;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">

    // ACCESSORS
    //////////////////////////////////////////

    /**
     * TagNum = 1206. Starting with 5.0SP1 version.
     */
    protected Double startTickPriceRange;

    /**
     * TagNum = 1207. Starting with 5.0SP1 version.
     */
    protected Double endTickPriceRange;

    /**
     * TagNum = 1208. Starting with 5.0SP1 version.
     */
    protected Double tickIncrement;

    /**
     * TagNum = 1209. Starting with 5.0SP1 version.
     */
    protected TickRuleType tickRuleType;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public TickRuleGroup() {
    }

    public TickRuleGroup(FragmentContext context) {
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
    @TagNumRef(tagNum=TagNum.StartTickPriceRange)
    public Double getStartTickPriceRange() {
        return startTickPriceRange;
    }

    /**
     * Message field setter.
     * @param startTickPriceRange field value
     */
    @FIXVersion(introduced="5.0SP1")
    @TagNumRef(tagNum=TagNum.StartTickPriceRange)
    public void setStartTickPriceRange(Double startTickPriceRange) {
        this.startTickPriceRange = startTickPriceRange;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0SP1")
    @TagNumRef(tagNum=TagNum.EndTickPriceRange)
    public Double getEndTickPriceRange() {
        return endTickPriceRange;
    }

    /**
     * Message field setter.
     * @param endTickPriceRange field value
     */
    @FIXVersion(introduced="5.0SP1")
    @TagNumRef(tagNum=TagNum.EndTickPriceRange)
    public void setEndTickPriceRange(Double endTickPriceRange) {
        this.endTickPriceRange = endTickPriceRange;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0SP1")
    @TagNumRef(tagNum=TagNum.TickIncrement)
    public Double getTickIncrement() {
        return tickIncrement;
    }

    /**
     * Message field setter.
     * @param tickIncrement field value
     */
    @FIXVersion(introduced="5.0SP1")
    @TagNumRef(tagNum=TagNum.TickIncrement)
    public void setTickIncrement(Double tickIncrement) {
        this.tickIncrement = tickIncrement;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0SP1")
    @TagNumRef(tagNum=TagNum.TickRuleType)
    public TickRuleType getTickRuleType() {
        return tickRuleType;
    }

    /**
     * Message field setter.
     * @param tickRuleType field value
     */
    @FIXVersion(introduced="5.0SP1")
    @TagNumRef(tagNum=TagNum.TickRuleType)
    public void setTickRuleType(TickRuleType tickRuleType) {
        this.tickRuleType = tickRuleType;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">
    
    @Override
    protected int getFirstTag() {
        return TagNum.StartTickPriceRange.getValue();
    }

    @Override
    protected void validateRequiredTags() throws TagNotPresentException {
        StringBuilder errorMsg = new StringBuilder("Tag value(s) for");
        boolean hasMissingTag = false;
        if (startTickPriceRange == null) {
            errorMsg.append(" [StartTickPriceRange]");
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
            TagEncoder.encode(bao, TagNum.StartTickPriceRange, startTickPriceRange);
            TagEncoder.encode(bao, TagNum.EndTickPriceRange, endTickPriceRange);
            TagEncoder.encode(bao, TagNum.TickIncrement, tickIncrement);
            if (tickRuleType != null) {
                TagEncoder.encode(bao, TagNum.TickRuleType, tickRuleType.getValue());
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
            case StartTickPriceRange:
                startTickPriceRange = new Double(new String(tag.value, sessionCharset));
                break;

            case EndTickPriceRange:
                endTickPriceRange = new Double(new String(tag.value, sessionCharset));
                break;

            case TickIncrement:
                tickIncrement = new Double(new String(tag.value, sessionCharset));
                break;

            case TickRuleType:
                tickRuleType = TickRuleType.valueFor(Integer.valueOf(new String(tag.value, sessionCharset)));
                break;

            default:
                String error = "Tag value [" + tag.tagNum + "] not present in [TickRuleGroup] fields.";
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
        b.append("{TickRuleGroup=");
        printTagValue(b, TagNum.StartTickPriceRange, startTickPriceRange);
        printTagValue(b, TagNum.EndTickPriceRange, endTickPriceRange);
        printTagValue(b, TagNum.TickIncrement, tickIncrement);
        printTagValue(b, TagNum.TickRuleType, tickRuleType);
        b.append("}");

        return b.toString();
    }

    // </editor-fold>
}
