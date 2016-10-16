/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * PegInstructions.java
 *
 * $Id: PegInstructions.java,v 1.1 2010-12-05 08:13:27 vrotaru Exp $
 */
package net.hades.fix.message.comp;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.anno.FIXVersion;
import net.hades.fix.message.anno.TagNumRef;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.struct.Tag;

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
import net.hades.fix.message.type.PegLimitType;
import net.hades.fix.message.type.PegMoveType;
import net.hades.fix.message.type.PegOffsetType;
import net.hades.fix.message.type.PegPriceType;
import net.hades.fix.message.type.PegRoundDirection;
import net.hades.fix.message.type.PegScope;
import net.hades.fix.message.type.SessionRejectReason;
import net.hades.fix.message.type.TagNum;
import net.hades.fix.message.util.TagEncoder;

/**
 * The Peg Instructions component block is used to tie the price of a security to a market event such as opening price,
 * mid-price, best price. The Peg Instructions block may also be used to tie the price to the behavior of a related
 * security.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 14/02/2009, 2:52:41 PM
 */
@XmlAccessorType(XmlAccessType.NONE)
public abstract class PegInstructions extends Component {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.PegOffsetValue.getValue(),
        TagNum.PegPriceType.getValue(),
        TagNum.PegMoveType.getValue(),
        TagNum.PegOffsetType.getValue(),
        TagNum.PegLimitType.getValue(),
        TagNum.PegRoundDirection.getValue(),
        TagNum.PegScope.getValue(),
        TagNum.PegSecurityIDSource.getValue(),
        TagNum.PegSecurityID.getValue(),
        TagNum.PegSymbol.getValue(),
        TagNum.PegSecurityDesc.getValue()
    }));

    protected static final Set<Integer> REQUIRED_TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.PegOffsetValue.getValue()
    }));

    protected static final Set<Integer> START_DATA_TAGS = null;

    protected static final Set<Integer> START_COMP_TAGS = null;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">

    /**
     * TagNum = 211. Starting with 4.4 version.
     */
    protected Double pegOffsetValue;

    /**
     * TagNum = 1094. Starting with 5.0 version.
     */
    protected PegPriceType pegPriceType;

    /**
     * TagNum = 835. Starting with 4.4 version.
     */
    protected PegMoveType pegMoveType;

    /**
     * TagNum = 836. Starting with 4.4 version.
     */
    protected PegOffsetType pegOffsetType;

    /**
     * TagNum = 837. Starting with 4.4 version.
     */
    protected PegLimitType pegLimitType;

    /**
     * TagNum = 838. Starting with 4.4 version.
     */
    protected PegRoundDirection pegRoundDirection;

     /**
     * TagNum = 840. Starting with 4.4 version.
     */
    protected PegScope pegScope;

    /**
     * TagNum = 1096. Starting with 5.0 version.
     */
    protected String pegSecurityIDSource;

    /**
     * TagNum = 1097. Starting with 5.0 version.
     */
    protected String pegSecurityID;

    /**
     * TagNum = 1098. Starting with 5.0 version.
     */
    protected String pegSymbol;

    /**
     * TagNum = 1099. Starting with 5.0 version.
     */
    protected String pegSecurityDesc;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public PegInstructions() {
    }

    public PegInstructions(FragmentContext context) {
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
    @TagNumRef(tagNum = TagNum.PegOffsetValue)
    public Double getPegOffsetValue() {
        return pegOffsetValue;
    }

    /**
     * Message field setter.
     * @param pegOffsetValue field value
     */
    @FIXVersion(introduced = "4.4")
    @TagNumRef(tagNum = TagNum.PegOffsetValue)
    public void setPegOffsetValue(Double pegOffsetValue) {
        this.pegOffsetValue = pegOffsetValue;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "5.0")
    @TagNumRef(tagNum = TagNum.PegPriceType)
    public PegPriceType getPegPriceType() {
        return pegPriceType;
    }

    /**
     * Message field setter.
     * @param pegPriceType field value
     */
    @FIXVersion(introduced = "5.0")
    @TagNumRef(tagNum = TagNum.PegPriceType)
    public void setPegPriceType(PegPriceType pegPriceType) {
        this.pegPriceType = pegPriceType;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.4")
    @TagNumRef(tagNum = TagNum.PegMoveType)
    public PegMoveType getPegMoveType() {
        return pegMoveType;
    }

    /**
     * Message field setter.
     * @param pegMoveType field value
     */
    @FIXVersion(introduced = "4.4")
    @TagNumRef(tagNum = TagNum.PegMoveType)
    public void setPegMoveType(PegMoveType pegMoveType) {
        this.pegMoveType = pegMoveType;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.4")
    @TagNumRef(tagNum = TagNum.PegOffsetType)
    public PegOffsetType getPegOffsetType() {
        return pegOffsetType;
    }

    /**
     * Message field setter.
     * @param pegOffsetType field value
     */
    @FIXVersion(introduced = "4.4")
    @TagNumRef(tagNum = TagNum.PegOffsetType)
    public void setPegOffsetType(PegOffsetType pegOffsetType) {
        this.pegOffsetType = pegOffsetType;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.4")
    @TagNumRef(tagNum = TagNum.PegLimitType)
    public PegLimitType getPegLimitType() {
        return pegLimitType;
    }

    /**
     * Message field setter.
     * @param pegLimitType field value
     */
    @FIXVersion(introduced = "4.4")
    @TagNumRef(tagNum = TagNum.PegLimitType)
    public void setPegLimitType(PegLimitType pegLimitType) {
        this.pegLimitType = pegLimitType;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.4")
    @TagNumRef(tagNum = TagNum.PegRoundDirection)
    public PegRoundDirection getPegRoundDirection() {
        return pegRoundDirection;
    }

    /**
     * Message field setter.
     * @param pegRoundDirection field value
     */
    @FIXVersion(introduced = "4.4")
    @TagNumRef(tagNum = TagNum.PegRoundDirection)
    public void setPegRoundDirection(PegRoundDirection pegRoundDirection) {
        this.pegRoundDirection = pegRoundDirection;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.4")
    @TagNumRef(tagNum = TagNum.PegScope)
    public PegScope getPegScope() {
        return pegScope;
    }

    /**
     * Message field setter.
     * @param pegScope field value
     */
    @FIXVersion(introduced = "4.4")
    @TagNumRef(tagNum = TagNum.PegScope)
    public void setPegScope(PegScope pegScope) {
        this.pegScope = pegScope;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "5.0")
    @TagNumRef(tagNum = TagNum.PegSecurityIDSource)
    public String getPegSecurityIDSource() {
        return pegSecurityIDSource;
    }

    /**
     * Message field setter.
     * @param pegSecurityIDSource field value
     */
    @FIXVersion(introduced = "5.0")
    @TagNumRef(tagNum = TagNum.PegSecurityIDSource)
    public void setPegSecurityIDSource(String pegSecurityIDSource) {
        this.pegSecurityIDSource = pegSecurityIDSource;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "5.0")
    @TagNumRef(tagNum = TagNum.PegSecurityID)
    public String getPegSecurityID() {
        return pegSecurityID;
    }

    /**
     * Message field setter.
     * @param pegSecurityID field value
     */
    @FIXVersion(introduced = "5.0")
    @TagNumRef(tagNum = TagNum.PegSecurityID)
    public void setPegSecurityID(String pegSecurityID) {
        this.pegSecurityID = pegSecurityID;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "5.0")
    @TagNumRef(tagNum = TagNum.PegSymbol)
    public String getPegSymbol() {
        return pegSymbol;
    }

    /**
     * Message field setter.
     * @param pegSymbol field value
     */
    @FIXVersion(introduced = "5.0")
    @TagNumRef(tagNum = TagNum.PegSymbol)
    public void setPegSymbol(String pegSymbol) {
        this.pegSymbol = pegSymbol;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "5.0")
    @TagNumRef(tagNum = TagNum.PegSecurityDesc)
    public String getPegSecurityDesc() {
        return pegSecurityDesc;
    }

    /**
     * Message field setter.
     * @param pegSecurityDesc field value
     */
    @FIXVersion(introduced = "5.0")
    @TagNumRef(tagNum = TagNum.PegSecurityDesc)
    public void setPegSecurityDesc(String pegSecurityDesc) {
        this.pegSecurityDesc = pegSecurityDesc;
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
            TagEncoder.encode(bao, TagNum.PegOffsetValue, pegOffsetValue);
            if (pegPriceType != null) {
                TagEncoder.encode(bao, TagNum.PegPriceType, pegPriceType.getValue());
            }
            if (pegMoveType != null) {
                TagEncoder.encode(bao, TagNum.PegMoveType, pegMoveType.getValue());
            }
            if (pegOffsetType != null) {
                TagEncoder.encode(bao, TagNum.PegOffsetType, pegOffsetType.getValue());
            }
            if (pegLimitType != null) {
                TagEncoder.encode(bao, TagNum.PegLimitType, pegLimitType.getValue());
            }
            if (pegRoundDirection != null) {
                TagEncoder.encode(bao, TagNum.PegRoundDirection, pegRoundDirection.getValue());
            }
            if (pegScope != null) {
                TagEncoder.encode(bao, TagNum.PegScope, pegScope.getValue());
            }
            TagEncoder.encode(bao, TagNum.PegSecurityIDSource, pegSecurityIDSource);
            TagEncoder.encode(bao, TagNum.PegSecurityID, pegSecurityID);
            TagEncoder.encode(bao, TagNum.PegSymbol, pegSymbol);
            TagEncoder.encode(bao, TagNum.PegSecurityDesc, pegSecurityDesc);
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

            case PegOffsetValue:
                pegOffsetValue = Double.valueOf(new String(tag.value, sessionCharset));
                break;

            case PegPriceType:
                pegPriceType = PegPriceType.valueFor(Integer.valueOf(new String(tag.value, sessionCharset)));
                break;

            case PegMoveType:
                pegMoveType = PegMoveType.valueFor(Integer.valueOf(new String(tag.value, sessionCharset)));
                break;

            case PegOffsetType:
                pegOffsetType = PegOffsetType.valueFor(Integer.valueOf(new String(tag.value, sessionCharset)));
                break;

            case PegLimitType:
                pegLimitType = PegLimitType.valueFor(Integer.valueOf(new String(tag.value, sessionCharset)));
                break;

             case PegRoundDirection:
                pegRoundDirection = PegRoundDirection.valueFor(Integer.valueOf(new String(tag.value, sessionCharset)));
                break;

            case PegScope:
                pegScope = PegScope.valueFor(Integer.valueOf(new String(tag.value, sessionCharset)));
                break;

            case PegSecurityIDSource:
                pegSecurityIDSource = new String(tag.value, sessionCharset);
                break;

            case PegSecurityID:
                pegSecurityID = new String(tag.value, sessionCharset);
                break;

            case PegSymbol:
                pegSymbol = new String(tag.value, sessionCharset);
                break;

            case PegSecurityDesc:
                pegSecurityDesc = new String(tag.value, sessionCharset);
                break;

            default:
                String error = "Tag value [" + tag.tagNum + "] not present in [PegInstructions] fields.";
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
        b.append("{PegInstructions=");
        printTagValue(b, TagNum.PegOffsetValue, pegOffsetValue);
        printTagValue(b, TagNum.PegPriceType, pegPriceType);
        printTagValue(b, TagNum.PegMoveType, pegMoveType);
        printTagValue(b, TagNum.PegOffsetType, pegOffsetType);
        printTagValue(b, TagNum.PegLimitType, pegLimitType);
        printTagValue(b, TagNum.PegRoundDirection, pegRoundDirection);
        printTagValue(b, TagNum.PegScope, pegScope);
        printTagValue(b, TagNum.PegSecurityIDSource, pegSecurityIDSource);
        printTagValue(b, TagNum.PegSecurityID, pegSecurityID);
        printTagValue(b, TagNum.PegSymbol, pegSymbol);
        printTagValue(b, TagNum.PegSecurityDesc, pegSecurityDesc);
        b.append("}");

        return b.toString();
    }

    // </editor-fold>
}
