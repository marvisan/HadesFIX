/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * DisplayInstruction.java
 *
 * $Id: DisplayInstruction.java,v 1.1 2010-12-05 08:13:28 vrotaru Exp $
 */
package net.hades.fix.message.comp;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.anno.FIXVersion;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.struct.Tag;
import net.hades.fix.message.type.SessionRejectReason;

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
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.type.DisplayMethod;
import net.hades.fix.message.type.DisplayWhen;
import net.hades.fix.message.type.TagNum;
import net.hades.fix.message.util.TagEncoder;

/**
 * The DisplayInstruction component block is used to convey instructions
 * on how a reserved order is to be handled in terms of when and how much
 * of the order quantity is to be displayed to the market.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 14/02/2009, 2:52:41 PM
 */
@XmlAccessorType(XmlAccessType.NONE)
public abstract class DisplayInstruction extends Component {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.DisplayQty.getValue(),
        TagNum.SecondaryDisplayQty.getValue(),
        TagNum.DisplayWhen.getValue(),
        TagNum.DisplayMethod.getValue(),
        TagNum.DisplayLowQty.getValue(),
        TagNum.DisplayHighQty.getValue(),
        TagNum.DisplayMinIncr.getValue(),
        TagNum.RefreshQty.getValue()
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
     * TagNum = 1138. Starting with 5.0 version.
     */
    protected Double displayQty;

    /**
     * TagNum = 1082. Starting with 5.0 version.
     */
    protected Double secondaryDisplayQty;

    /**
     * TagNum = 1083. Starting with 5.0 version.
     */
    protected DisplayWhen displayWhen;

    /**
     * TagNum = 1084. Starting with 5.0 version.
     */
    protected DisplayMethod displayMethod;

    /**
     * TagNum = 1085. Starting with 5.0 version.
     */
    protected Double displayLowQty;

    /**
     * TagNum = 1086. Starting with 5.0 version.
     */
    protected Double displayHighQty;

    /**
     * TagNum = 1087. Starting with 5.0 version.
     */
    protected Double displayMinIncr;

    /**
     * TagNum = 1088. Starting with 5.0 version.
     */
    protected Double refreshQty;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public DisplayInstruction() {
    }

    public DisplayInstruction(FragmentContext context) {
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
    @TagNumRef(tagNum = TagNum.DisplayQty)
    public Double getDisplayQty() {
        return displayQty;
    }

    /**
     * Message field setter.
     * @param displayQty field value
     */
    @FIXVersion(introduced = "5.0")
    @TagNumRef(tagNum = TagNum.DisplayQty)
    public void setDisplayQty(Double displayQty) {
        this.displayQty = displayQty;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "5.0")
    @TagNumRef(tagNum = TagNum.SecondaryDisplayQty)
    public Double getSecondaryDisplayQty() {
        return secondaryDisplayQty;
    }

    /**
     * Message field setter.
     * @param secondaryDisplayQty field value
     */
    @FIXVersion(introduced = "5.0")
    @TagNumRef(tagNum = TagNum.SecondaryDisplayQty)
    public void setSecondaryDisplayQty(Double secondaryDisplayQty) {
        this.secondaryDisplayQty = secondaryDisplayQty;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "5.0")
    @TagNumRef(tagNum = TagNum.DisplayWhen)
    public DisplayWhen getDisplayWhen() {
        return displayWhen;
    }

    /**
     * Message field setter.
     * @param displayWhen field value
     */
    @FIXVersion(introduced = "5.0")
    @TagNumRef(tagNum = TagNum.DisplayWhen)
    public void setDisplayWhen(DisplayWhen displayWhen) {
        this.displayWhen = displayWhen;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "5.0")
    @TagNumRef(tagNum = TagNum.DisplayMethod)
    public DisplayMethod getDisplayMethod() {
        return displayMethod;
    }

    /**
     * Message field setter.
     * @param displayMethod field value
     */
    @FIXVersion(introduced = "5.0")
    @TagNumRef(tagNum = TagNum.DisplayMethod)
    public void setDisplayMethod(DisplayMethod displayMethod) {
        this.displayMethod = displayMethod;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "5.0")
    @TagNumRef(tagNum = TagNum.DisplayLowQty)
    public Double getDisplayLowQty() {
        return displayLowQty;
    }

    /**
     * Message field setter.
     * @param displayLowQty field value
     */
    @FIXVersion(introduced = "5.0")
    @TagNumRef(tagNum = TagNum.DisplayLowQty)
    public void setDisplayLowQty(Double displayLowQty) {
        this.displayLowQty = displayLowQty;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "5.0")
    @TagNumRef(tagNum = TagNum.DisplayHighQty)
    public Double getDisplayHighQty() {
        return displayHighQty;
    }

    /**
     * Message field setter.
     * @param displayHighQty field value
     */
    @FIXVersion(introduced = "5.0")
    @TagNumRef(tagNum = TagNum.DisplayHighQty)
    public void setDisplayHighQty(Double displayHighQty) {
        this.displayHighQty = displayHighQty;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "5.0")
    @TagNumRef(tagNum = TagNum.DisplayMinIncr)
    public Double getDisplayMinIncr() {
        return displayMinIncr;
    }

    /**
     * Message field setter.
     * @param displayMinIncr field value
     */
    @FIXVersion(introduced = "5.0")
    @TagNumRef(tagNum = TagNum.DisplayMinIncr)
    public void setDisplayMinIncr(Double displayMinIncr) {
        this.displayMinIncr = displayMinIncr;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "5.0")
    @TagNumRef(tagNum = TagNum.RefreshQty)
    public Double getRefreshQty() {
        return refreshQty;
    }

    /**
     * Message field setter.
     * @param refreshQty field value
     */
    @FIXVersion(introduced = "5.0")
    @TagNumRef(tagNum = TagNum.RefreshQty)
    public void setRefreshQty(Double refreshQty) {
        this.refreshQty = refreshQty;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">
    
    @Override
    protected int getFirstTag() {
        return TagNum.DisplayQty.getValue();
    }

    @Override
    protected void validateRequiredTags() throws TagNotPresentException {
        StringBuilder errorMsg = new StringBuilder("Tag value(s) for");
        boolean hasMissingTag = false;
         if (displayQty == null) {
            errorMsg.append(" [DisplayQty]");
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
            TagEncoder.encode(bao, TagNum.DisplayQty, displayQty);
            TagEncoder.encode(bao, TagNum.SecondaryDisplayQty, secondaryDisplayQty);
            if (displayWhen != null) {
                TagEncoder.encode(bao, TagNum.DisplayWhen, displayWhen.getValue());
            }
            if (displayMethod != null) {
                TagEncoder.encode(bao, TagNum.DisplayMethod, displayMethod.getValue());
            }
            TagEncoder.encode(bao, TagNum.DisplayLowQty, displayLowQty);
            TagEncoder.encode(bao, TagNum.DisplayHighQty, displayHighQty);
            TagEncoder.encode(bao, TagNum.DisplayMinIncr, displayMinIncr);
            TagEncoder.encode(bao, TagNum.RefreshQty, refreshQty);

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
            case DisplayQty:
                displayQty = Double.valueOf(new String(tag.value, sessionCharset));
                break;

            case SecondaryDisplayQty:
                secondaryDisplayQty = Double.valueOf(new String(tag.value, sessionCharset));
                break;

            case DisplayWhen:
                displayWhen = DisplayWhen.valueFor(new String(tag.value, sessionCharset).charAt(0));
                break;

            case DisplayMethod:
                displayMethod = DisplayMethod.valueFor(new String(tag.value, sessionCharset).charAt(0));
                break;

            case DisplayLowQty:
                displayLowQty = Double.valueOf(new String(tag.value, sessionCharset));
                break;

            case DisplayHighQty:
                displayHighQty = Double.valueOf(new String(tag.value, sessionCharset));
                break;

            case DisplayMinIncr:
                displayMinIncr = Double.valueOf(new String(tag.value, sessionCharset));
                break;

            case RefreshQty:
                refreshQty = Double.valueOf(new String(tag.value, sessionCharset));
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
        b.append("{DisplayInstruction=");
        printTagValue(b, TagNum.DisplayQty, displayQty);
        printTagValue(b, TagNum.SecondaryDisplayQty, secondaryDisplayQty);
        printTagValue(b, TagNum.DisplayWhen, displayWhen);
        printTagValue(b, TagNum.DisplayMethod, displayMethod);
        printTagValue(b, TagNum.DisplayLowQty, displayLowQty);
        printTagValue(b, TagNum.DisplayHighQty, displayHighQty);
        printTagValue(b, TagNum.DisplayMinIncr, displayMinIncr);
        printTagValue(b, TagNum.RefreshQty, refreshQty);
        b.append("}");

        return b.toString();
    }

    // </editor-fold>
}
