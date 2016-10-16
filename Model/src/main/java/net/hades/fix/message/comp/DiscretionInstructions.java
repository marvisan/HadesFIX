/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * DiscretionInstructions.java
 *
 * $Id: DiscretionInstructions.java,v 1.1 2010-12-05 08:13:27 vrotaru Exp $
 */
package net.hades.fix.message.comp;

import net.hades.fix.message.anno.FIXVersion;
import net.hades.fix.message.struct.Tag;
import net.hades.fix.message.type.DiscretionLimitType;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import net.hades.fix.message.anno.TagNumRef;
import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.type.DiscretionInst;
import net.hades.fix.message.type.DiscretionMoveType;
import net.hades.fix.message.type.DiscretionOffsetType;
import net.hades.fix.message.type.DiscretionRoundDirection;
import net.hades.fix.message.type.DiscretionScope;
import net.hades.fix.message.type.SessionRejectReason;
import net.hades.fix.message.type.TagNum;
import net.hades.fix.message.util.TagEncoder;

/**
 * The presence of DiscretionInstructions component block on an order indicates that the trader
 * wishes to display one price but will accept trades at another price.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 14/02/2009, 2:52:41 PM
 */
@XmlAccessorType(XmlAccessType.NONE)
public abstract class DiscretionInstructions extends Component {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.DiscretionInst.getValue(),
        TagNum.DiscretionOffsetValue.getValue(),
        TagNum.DiscretionMoveType.getValue(),
        TagNum.DiscretionOffsetType.getValue(),
        TagNum.DiscretionLimitType.getValue(),
        TagNum.DiscretionRoundDirection.getValue(),
        TagNum.DiscretionScope.getValue()
    }));

    protected static final Set<Integer> REQUIRED_TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.DiscretionInst.getValue()
    }));

    protected static final Set<Integer> START_DATA_TAGS = null;

    protected static final Set<Integer> START_COMP_TAGS = null;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">

    /**
     * TagNum = 388. Starting with 4.4 version.
     */
    protected DiscretionInst discretionInst;

    /**
     * TagNum = 389. Starting with 4.4 version.
     */
    protected Double discretionOffsetValue;

    /**
     * TagNum = 841. Starting with 4.4 version.
     */
    protected DiscretionMoveType discretionMoveType;

    /**
     * TagNum = 842. Starting with 4.4 version.
     */
    protected DiscretionOffsetType discretionOffsetType;

    /**
     * TagNum = 843. Starting with 4.4 version.
     */
    protected DiscretionLimitType discretionLimitType;

    /**
     * TagNum = 844. Starting with 4.4 version.
     */
    protected DiscretionRoundDirection discretionRoundDirection;

    /**
     * TagNum = 846. Starting with 4.4 version.
     */
    protected DiscretionScope discretionScope;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public DiscretionInstructions() {
    }

    public DiscretionInstructions(FragmentContext context) {
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
    @TagNumRef(tagNum = TagNum.DiscretionInst)
    public DiscretionInst getDiscretionInst() {
        return discretionInst;
    }

    /**
     * Message field setter.
     * @param discretionInst field value
     */
    @FIXVersion(introduced = "4.4")
    @TagNumRef(tagNum = TagNum.DiscretionInst)
    public void setDiscretionInst(DiscretionInst discretionInst) {
        this.discretionInst = discretionInst;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.4")
    @TagNumRef(tagNum = TagNum.DiscretionOffsetValue)
    public Double getDiscretionOffsetValue() {
        return discretionOffsetValue;
    }

    /**
     * Message field setter.
     * @param discretionOffsetValue field value
     */
    @FIXVersion(introduced = "4.4")
    @TagNumRef(tagNum = TagNum.DiscretionOffsetValue)
    public void setDiscretionOffsetValue(Double discretionOffsetValue) {
        this.discretionOffsetValue = discretionOffsetValue;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.4")
    @TagNumRef(tagNum = TagNum.DiscretionMoveType)
    public DiscretionMoveType getDiscretionMoveType() {
        return discretionMoveType;
    }

    /**
     * Message field setter.
     * @param discretionMoveType field value
     */
    @FIXVersion(introduced = "4.4")
    @TagNumRef(tagNum = TagNum.DiscretionMoveType)
    public void setDiscretionMoveType(DiscretionMoveType discretionMoveType) {
        this.discretionMoveType = discretionMoveType;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.4")
    @TagNumRef(tagNum = TagNum.DiscretionOffsetType)
    public DiscretionOffsetType getDiscretionOffsetType() {
        return discretionOffsetType;
    }

    /**
     * Message field setter.
     * @param discretionOffsetType field value
     */
    @FIXVersion(introduced = "4.4")
    @TagNumRef(tagNum = TagNum.DiscretionOffsetType)
    public void setDiscretionOffsetType(DiscretionOffsetType discretionOffsetType) {
        this.discretionOffsetType = discretionOffsetType;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.4")
    @TagNumRef(tagNum = TagNum.DiscretionLimitType)
    public DiscretionLimitType getDiscretionLimitType() {
        return discretionLimitType;
    }

    /**
     * Message field setter.
     * @param discretionLimitType field value
     */
    @FIXVersion(introduced = "4.4")
    @TagNumRef(tagNum = TagNum.DiscretionLimitType)
    public void setDiscretionLimitType(DiscretionLimitType discretionLimitType) {
        this.discretionLimitType = discretionLimitType;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.4")
    @TagNumRef(tagNum = TagNum.DiscretionRoundDirection)
    public DiscretionRoundDirection getDiscretionRoundDirection() {
        return discretionRoundDirection;
    }

    /**
     * Message field setter.
     * @param discretionRoundDirection field value
     */
    @FIXVersion(introduced = "4.4")
    @TagNumRef(tagNum = TagNum.DiscretionRoundDirection)
    public void setDiscretionRoundDirection(DiscretionRoundDirection discretionRoundDirection) {
        this.discretionRoundDirection = discretionRoundDirection;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.4")
    @TagNumRef(tagNum = TagNum.DiscretionScope)
    public DiscretionScope getDiscretionScope() {
        return discretionScope;
    }

    /**
     * Message field setter.
     * @param discretionScope field value
     */
    @FIXVersion(introduced = "4.4")
    @TagNumRef(tagNum = TagNum.DiscretionScope)
    public void setDiscretionScope(DiscretionScope discretionScope) {
        this.discretionScope = discretionScope;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">
    
    @Override
    protected int getFirstTag() {
        return TagNum.TriggerType.getValue();
    }

    @Override
    protected void validateRequiredTags() throws TagNotPresentException {
    }

    @Override
    protected byte[] encodeFragmentAll() throws TagNotPresentException, BadFormatMsgException {
        if (validateRequired) {
            validateRequiredTags();
        }
        byte[] result = new byte[0];
        ByteArrayOutputStream bao = new ByteArrayOutputStream();

        try {
            if (discretionInst != null) {
                TagEncoder.encode(bao, TagNum.DiscretionInst, discretionInst.getValue());
            }
            TagEncoder.encode(bao, TagNum.DiscretionOffsetValue, discretionOffsetValue);
            if (discretionMoveType != null) {
                TagEncoder.encode(bao, TagNum.DiscretionMoveType, discretionMoveType.getValue());
            }
            if (discretionOffsetType != null) {
                TagEncoder.encode(bao, TagNum.DiscretionOffsetType, discretionOffsetType.getValue());
            }
            if (discretionLimitType != null) {
                TagEncoder.encode(bao, TagNum.DiscretionLimitType, discretionLimitType.getValue());
            }
            if (discretionRoundDirection != null) {
                TagEncoder.encode(bao, TagNum.DiscretionRoundDirection, discretionRoundDirection.getValue());
            }
            if (discretionScope != null) {
                TagEncoder.encode(bao, TagNum.DiscretionScope, discretionScope.getValue());
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
            case DiscretionInst:
                discretionInst = DiscretionInst.valueFor(new String(tag.value, sessionCharset));
                break;

            case DiscretionOffsetValue:
                discretionOffsetValue = Double.valueOf(new String(tag.value, sessionCharset));
                break;

            case DiscretionMoveType:
                discretionMoveType = DiscretionMoveType.valueFor(Integer.valueOf(new String(tag.value, sessionCharset)));
                break;

            case DiscretionOffsetType:
                discretionOffsetType = DiscretionOffsetType.valueFor(Integer.valueOf(new String(tag.value, sessionCharset)));
                break;

            case DiscretionLimitType:
                discretionLimitType = DiscretionLimitType.valueFor(Integer.valueOf(new String(tag.value, sessionCharset)));
                break;

            case DiscretionRoundDirection:
                discretionRoundDirection = DiscretionRoundDirection.valueFor(Integer.valueOf(new String(tag.value, sessionCharset)));
                break;

             case DiscretionScope:
                discretionScope = discretionScope.valueFor(Integer.valueOf(new String(tag.value, sessionCharset)));
                break;

            default:
                String error = "Tag value [" + tag.tagNum + "] not present in [DiscretionInstructions] fields.";
                LOGGER.severe(error);
                throw new BadFormatMsgException(SessionRejectReason.InvalidTagNumber, tag.tagNum, error);
        }
    }

    @Override
    protected void setFragmentCompTagValue(Tag tag, ByteBuffer message)
    throws BadFormatMsgException, InvalidMsgException, TagNotPresentException {
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
        b.append("{DiscretionInstructions=");
        printTagValue(b, TagNum.DiscretionInst, discretionInst);
        printTagValue(b, TagNum.DiscretionOffsetValue, discretionOffsetValue);
        printTagValue(b, TagNum.DiscretionMoveType, discretionMoveType);
        printTagValue(b, TagNum.DiscretionOffsetType, discretionOffsetType);
        printTagValue(b, TagNum.DiscretionLimitType, discretionLimitType);
        printTagValue(b, TagNum.DiscretionRoundDirection, discretionRoundDirection);
        printTagValue(b, TagNum.DiscretionScope, discretionScope);
        b.append("}");

        return b.toString();
    }

    // </editor-fold>
}
