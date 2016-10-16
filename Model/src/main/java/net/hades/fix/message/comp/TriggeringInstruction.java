/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * TriggeringInstruction.java
 *
 * $Id: TriggeringInstruction.java,v 1.1 2010-12-05 08:13:27 vrotaru Exp $
 */
package net.hades.fix.message.comp;

import net.hades.fix.message.anno.FIXVersion;
import net.hades.fix.message.struct.Tag;
import net.hades.fix.message.type.TriggerAction;
import net.hades.fix.message.type.TriggerPriceDirection;
import net.hades.fix.message.type.TriggerPriceType;

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
import net.hades.fix.message.type.OrdType;
import net.hades.fix.message.type.SessionRejectReason;
import net.hades.fix.message.type.TagNum;
import net.hades.fix.message.type.TriggerPriceTypeScope;
import net.hades.fix.message.type.TriggerType;
import net.hades.fix.message.util.TagEncoder;

/**
 * The TriggeringInstruction component block specifies the conditions
 * under which an order will be triggered by related market events as
 * well as the behavior of the order in the market once it is triggered.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 14/02/2009, 2:52:41 PM
 */
@XmlAccessorType(XmlAccessType.NONE)
public abstract class TriggeringInstruction extends Component {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.TriggerType.getValue(),
        TagNum.TriggerAction.getValue(),
        TagNum.TriggerPrice.getValue(),
        TagNum.TriggerSymbol.getValue(),
        TagNum.TriggerSecurityID.getValue(),
        TagNum.TriggerSecurityIDSource.getValue(),
        TagNum.TriggerSecurityDesc.getValue(),
        TagNum.TriggerPriceType.getValue(),
        TagNum.TriggerPriceTypeScope.getValue(),
        TagNum.TriggerPriceDirection.getValue(),
        TagNum.TriggerNewPrice.getValue(),
        TagNum.TriggerOrderType.getValue(),
        TagNum.TriggerNewQty.getValue(),
        TagNum.TriggerTradingSessionID.getValue(),
        TagNum.TriggerTradingSessionSubID.getValue()
    }));

    protected static final Set<Integer> REQUIRED_TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.DisplayQty.getValue()
    }));

    protected static final Set<Integer> START_DATA_TAGS = null;

    protected static final Set<Integer> START_COMP_TAGS = null;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">

    /**
     * TagNum = 1100. Starting with 5.0 version.
     */
    protected TriggerType triggerType;

    /**
     * TagNum = 1101. Starting with 5.0 version.
     */
    protected TriggerAction triggerAction;

    /**
     * TagNum = 1102. Starting with 5.0 version.
     */
    protected Double triggerPrice;

    /**
     * TagNum = 1103. Starting with 5.0 version.
     */
    protected String triggerSymbol;

    /**
     * TagNum = 1104. Starting with 5.0 version.
     */
    protected String triggerSecurityID;

    /**
     * TagNum = 1105. Starting with 5.0 version.
     */
    protected String triggerSecurityIDSource;

    /**
     * TagNum = 1106. Starting with 5.0 version.
     */
    protected String triggerSecurityDesc;

    /**
     * TagNum = 1107. Starting with 5.0 version.
     */
    protected TriggerPriceType triggerPriceType;

    /**
     * TagNum = 1108. Starting with 5.0 version.
     */
    protected TriggerPriceTypeScope triggerPriceTypeScope;

    /**
     * TagNum = 1109. Starting with 5.0 version.
     */
    protected TriggerPriceDirection triggerPriceDirection;

    /**
     * TagNum = 1110. Starting with 5.0 version.
     */
    protected Double triggerNewPrice;

    /**
     * TagNum = 1111. Starting with 5.0 version.
     */
    protected OrdType triggerOrderType;

    /**
     * TagNum = 1112. Starting with 5.0 version.
     */
    protected Double triggerNewQty;

    /**
     * TagNum = 1113. Starting with 5.0 version.
     */
    protected String triggerTradingSessionID;

    /**
     * TagNum = 1113. Starting with 5.0 version.
     */
    protected String triggerTradingSessionSubID;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public TriggeringInstruction() {
    }

    public TriggeringInstruction(FragmentContext context) {
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
    @FIXVersion(introduced = "5.0")
    @TagNumRef(tagNum = TagNum.TriggerType)
    public TriggerType getTriggerType() {
        return triggerType;
    }

    /**
     * Message field setter.
     * @param triggerType field value
     */
    @FIXVersion(introduced = "5.0")
    @TagNumRef(tagNum = TagNum.TriggerType)
    public void setTriggerType(TriggerType triggerType) {
        this.triggerType = triggerType;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "5.0")
    @TagNumRef(tagNum = TagNum.TriggerAction)
    public TriggerAction getTriggerAction() {
        return triggerAction;
    }

    /**
     * Message field setter.
     * @param triggerAction field value
     */
    @FIXVersion(introduced = "5.0")
    @TagNumRef(tagNum = TagNum.TriggerAction)
    public void setTriggerAction(TriggerAction triggerAction) {
        this.triggerAction = triggerAction;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "5.0")
    @TagNumRef(tagNum = TagNum.TriggerPrice)
    public Double getTriggerPrice() {
        return triggerPrice;
    }

    /**
     * Message field setter.
     * @param triggerPrice field value
     */
    @FIXVersion(introduced = "5.0")
    @TagNumRef(tagNum = TagNum.TriggerPrice)
    public void setTriggerPrice(Double triggerPrice) {
        this.triggerPrice = triggerPrice;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "5.0")
    @TagNumRef(tagNum = TagNum.TriggerSymbol)
    public String getTriggerSymbol() {
        return triggerSymbol;
    }

    /**
     * Message field setter.
     * @param triggerSymbol field value
     */
    @FIXVersion(introduced = "5.0")
    @TagNumRef(tagNum = TagNum.TriggerSymbol)
    public void setTriggerSymbol(String triggerSymbol) {
        this.triggerSymbol = triggerSymbol;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "5.0")
    @TagNumRef(tagNum = TagNum.TriggerSecurityID)
    public String getTriggerSecurityID() {
        return triggerSecurityID;
    }

    /**
     * Message field setter.
     * @param triggerSecurityID field value
     */
    @FIXVersion(introduced = "5.0")
    @TagNumRef(tagNum = TagNum.TriggerSecurityID)
    public void setTriggerSecurityID(String triggerSecurityID) {
        this.triggerSecurityID = triggerSecurityID;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "5.0")
    @TagNumRef(tagNum = TagNum.TriggerSecurityIDSource)
    public String getTriggerSecurityIDSource() {
        return triggerSecurityIDSource;
    }

    /**
     * Message field setter.
     * @param triggerSecurityIDSource field value
     */
    @FIXVersion(introduced = "5.0")
    @TagNumRef(tagNum = TagNum.TriggerSecurityIDSource)
    public void setTriggerSecurityIDSource(String triggerSecurityIDSource) {
        this.triggerSecurityIDSource = triggerSecurityIDSource;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "5.0")
    @TagNumRef(tagNum = TagNum.TriggerSecurityDesc)
    public String getTriggerSecurityDesc() {
        return triggerSecurityDesc;
    }

    /**
     * Message field setter.
     * @param triggerSecurityDesc field value
     */
    @FIXVersion(introduced = "5.0")
    @TagNumRef(tagNum = TagNum.TriggerSecurityDesc)
    public void setTriggerSecurityDesc(String triggerSecurityDesc) {
        this.triggerSecurityDesc = triggerSecurityDesc;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "5.0")
    @TagNumRef(tagNum = TagNum.TriggerPriceType)
    public TriggerPriceType getTriggerPriceType() {
        return triggerPriceType;
    }

    /**
     * Message field setter.
     * @param triggerPriceType field value
     */
    @FIXVersion(introduced = "5.0")
    @TagNumRef(tagNum = TagNum.TriggerPriceType)
    public void setTriggerPriceType(TriggerPriceType triggerPriceType) {
        this.triggerPriceType = triggerPriceType;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "5.0")
    @TagNumRef(tagNum = TagNum.TriggerPriceTypeScope)
    public TriggerPriceTypeScope getTriggerPriceTypeScope() {
        return triggerPriceTypeScope;
    }

    /**
     * Message field setter.
     * @param triggerPriceTypeScope field value
     */
    @FIXVersion(introduced = "5.0")
    @TagNumRef(tagNum = TagNum.TriggerPriceTypeScope)
    public void setTriggerPriceTypeScope(TriggerPriceTypeScope triggerPriceTypeScope) {
        this.triggerPriceTypeScope = triggerPriceTypeScope;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "5.0")
    @TagNumRef(tagNum = TagNum.TriggerPriceDirection)
    public TriggerPriceDirection getTriggerPriceDirection() {
        return triggerPriceDirection;
    }

    /**
     * Message field setter.
     * @param triggerPriceDirection field value
     */
    @FIXVersion(introduced = "5.0")
    @TagNumRef(tagNum = TagNum.TriggerPriceDirection)
    public void setTriggerPriceDirection(TriggerPriceDirection triggerPriceDirection) {
        this.triggerPriceDirection = triggerPriceDirection;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "5.0")
    @TagNumRef(tagNum = TagNum.TriggerNewPrice)
    public Double getTriggerNewPrice() {
        return triggerNewPrice;
    }

    /**
     * Message field setter.
     * @param triggerNewPrice field value
     */
    @FIXVersion(introduced = "5.0")
    @TagNumRef(tagNum = TagNum.TriggerNewPrice)
    public void setTriggerNewPrice(Double triggerNewPrice) {
        this.triggerNewPrice = triggerNewPrice;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "5.0")
    @TagNumRef(tagNum = TagNum.TriggerOrderType)
    public OrdType getTriggerOrderType() {
        return triggerOrderType;
    }

    /**
     * Message field setter.
     * @param triggerOrderType field value
     */
    @FIXVersion(introduced = "5.0")
    @TagNumRef(tagNum = TagNum.TriggerOrderType)
    public void setTriggerOrderType(OrdType triggerOrderType) {
        this.triggerOrderType = triggerOrderType;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "5.0")
    @TagNumRef(tagNum = TagNum.TriggerNewQty)
    public Double getTriggerNewQty() {
        return triggerNewQty;
    }

    /**
     * Message field setter.
     * @param triggerNewQty field value
     */
    @FIXVersion(introduced = "5.0")
    @TagNumRef(tagNum = TagNum.TriggerNewQty)
    public void setTriggerNewQty(Double triggerNewQty) {
        this.triggerNewQty = triggerNewQty;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "5.0")
    @TagNumRef(tagNum = TagNum.TriggerTradingSessionID)
    public String getTriggerTradingSessionID() {
        return triggerTradingSessionID;
    }

    /**
     * Message field setter.
     * @param triggerTradingSessionID field value
     */
    @FIXVersion(introduced = "5.0")
    @TagNumRef(tagNum = TagNum.TriggerTradingSessionID)
    public void setTriggerTradingSessionID(String triggerTradingSessionID) {
        this.triggerTradingSessionID = triggerTradingSessionID;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "5.0")
    @TagNumRef(tagNum = TagNum.TriggerTradingSessionSubID)
    public String getTriggerTradingSessionSubID() {
        return triggerTradingSessionSubID;
    }

    /**
     * Message field setter.
     * @param triggerTradingSessionSubID field value
     */
    @FIXVersion(introduced = "5.0")
    @TagNumRef(tagNum = TagNum.TriggerTradingSessionSubID)
    public void setTriggerTradingSessionSubID(String triggerTradingSessionSubID) {
        this.triggerTradingSessionSubID = triggerTradingSessionSubID;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">
    
    @Override
    protected int getFirstTag() {
        return TagNum.TriggerType.getValue();
    }

    @Override
    protected void validateRequiredTags() throws TagNotPresentException {
        StringBuilder errorMsg = new StringBuilder("Tag value(s) for");
        boolean hasMissingTag = false;
         if (triggerType == null) {
            errorMsg.append(" [TriggerType]");
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
            if (triggerType != null) {
                TagEncoder.encode(bao, TagNum.TriggerType, triggerType.getValue());
            }
            if (triggerAction != null) {
                TagEncoder.encode(bao, TagNum.TriggerAction, triggerAction.getValue());
            }
            TagEncoder.encode(bao, TagNum.TriggerPrice, triggerPrice);
            TagEncoder.encode(bao, TagNum.TriggerSymbol, triggerSymbol);
            TagEncoder.encode(bao, TagNum.TriggerSecurityID, triggerSecurityID);
            TagEncoder.encode(bao, TagNum.TriggerSecurityIDSource, triggerSecurityIDSource);
            TagEncoder.encode(bao, TagNum.TriggerSecurityDesc, triggerSecurityDesc);
            if (triggerPriceType != null) {
                TagEncoder.encode(bao, TagNum.TriggerPriceType, triggerPriceType.getValue());
            }
            if (triggerPriceTypeScope != null) {
                TagEncoder.encode(bao, TagNum.TriggerPriceTypeScope, triggerPriceTypeScope.getValue());
            }
            if (triggerPriceDirection != null) {
                TagEncoder.encode(bao, TagNum.TriggerPriceDirection, triggerPriceDirection.getValue());
            }
            TagEncoder.encode(bao, TagNum.TriggerNewPrice, triggerNewPrice);
            if (triggerOrderType != null) {
                TagEncoder.encode(bao, TagNum.TriggerOrderType, triggerOrderType.getValue());
            }
            TagEncoder.encode(bao, TagNum.TriggerNewQty, triggerNewQty);
            TagEncoder.encode(bao, TagNum.TriggerTradingSessionID, triggerTradingSessionID);
            TagEncoder.encode(bao, TagNum.TriggerTradingSessionSubID, triggerTradingSessionSubID);

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
            case TriggerType:
                triggerType = TriggerType.valueFor(new String(tag.value, sessionCharset).charAt(0));
                break;

            case TriggerAction:
                triggerAction = TriggerAction.valueFor(new String(tag.value, sessionCharset).charAt(0));
                break;

            case TriggerPrice:
                triggerPrice = Double.valueOf(new String(tag.value, sessionCharset));
                break;

            case TriggerSymbol:
                triggerSymbol = new String(tag.value, sessionCharset);
                break;

            case TriggerSecurityID:
                triggerSecurityID = new String(tag.value, sessionCharset);
                break;

            case TriggerSecurityIDSource:
                triggerSecurityIDSource = new String(tag.value, sessionCharset);
                break;

            case TriggerSecurityDesc:
                triggerSecurityDesc = new String(tag.value, sessionCharset);
                break;

            case TriggerPriceType:
                triggerPriceType = TriggerPriceType.valueFor(new String(tag.value, sessionCharset).charAt(0));
                break;

            case TriggerPriceTypeScope:
                triggerPriceTypeScope = TriggerPriceTypeScope.valueFor(new String(tag.value, sessionCharset).charAt(0));
                break;

            case TriggerPriceDirection:
                triggerPriceDirection = TriggerPriceDirection.valueFor(new String(tag.value, sessionCharset).charAt(0));
                break;

            case TriggerNewPrice:
                triggerNewPrice = Double.valueOf(new String(tag.value, sessionCharset));
                break;

            case TriggerOrderType:
                triggerOrderType = OrdType.valueFor(new String(tag.value, sessionCharset).charAt(0));
                break;

            case TriggerNewQty:
                triggerNewQty = Double.valueOf(new String(tag.value, sessionCharset));
                break;

            case TriggerTradingSessionID:
                triggerTradingSessionID = new String(tag.value, sessionCharset);
                break;

            case TriggerTradingSessionSubID:
                triggerTradingSessionSubID = new String(tag.value, sessionCharset);
                break;

            default:
                String error = "Tag value [" + tag.tagNum + "] not present in [DisplayInstruction] fields.";
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
        b.append("{TriggeringInstruction=");
        printTagValue(b, TagNum.TriggerType, triggerType);
        printTagValue(b, TagNum.TriggerAction, triggerAction);
        printTagValue(b, TagNum.TriggerPrice, triggerPrice);
        printTagValue(b, TagNum.TriggerSymbol, triggerSymbol);
        printTagValue(b, TagNum.TriggerSecurityID, triggerSecurityID);
        printTagValue(b, TagNum.TriggerSecurityIDSource, triggerSecurityIDSource);
        printTagValue(b, TagNum.TriggerSecurityDesc, triggerSecurityDesc);
        printTagValue(b, TagNum.TriggerPriceType, triggerPriceType);
        printTagValue(b, TagNum.TriggerPriceTypeScope, triggerPriceTypeScope);
        printTagValue(b, TagNum.TriggerPriceDirection, triggerPriceDirection);
        printTagValue(b, TagNum.TriggerNewPrice, triggerNewPrice);
        printTagValue(b, TagNum.TriggerOrderType, triggerOrderType);
        printTagValue(b, TagNum.TriggerNewQty, triggerNewQty);
        printTagValue(b, TagNum.TriggerTradingSessionID, triggerTradingSessionID);
        printTagValue(b, TagNum.TriggerTradingSessionSubID, triggerTradingSessionSubID);
        
        b.append("}");

        return b.toString();
    }

    // </editor-fold>
}
