/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * FillExecGroup.java
 *
 * $Id: FillExecGroup.java,v 1.1 2011-01-09 07:27:41 vrotaru Exp $
 */
package net.hades.fix.message.group;

import net.hades.fix.message.anno.FIXVersion;
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

import net.hades.fix.message.anno.TagNumRef;
import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.MsgSecureType;
import net.hades.fix.message.comp.NestedParties4;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.type.FillLiquidityInd;
import net.hades.fix.message.type.SessionRejectReason;
import net.hades.fix.message.type.TagNum;
import net.hades.fix.message.util.TagEncoder;

/**
 * Fill execution group for Execution message.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 29/04/2009, 6:41:55 PM
 */
@XmlAccessorType(XmlAccessType.NONE)
public abstract class FillExecGroup extends Group {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.FillExecID.getValue(),
        TagNum.FillPx.getValue(),
        TagNum.FillQty.getValue(),
        TagNum.FillLiquidityInd.getValue()
    }));

    protected static final Set<Integer> START_DATA_TAGS = null;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">

    /**
     * TagNum = 1363. Starting with 5.0SP1 version.
     */
    protected String fillExecID;

    /**
     * TagNum = 1364. Starting with 5.0SP1 version.
     */
    protected Double fillPx;

    /**
     * TagNum = 1365. Starting with 5.0SP1 version.
     */
    protected Double fillQty;

    /**
     * TagNum = 1443. Starting with 5.0SP1 version.
     */
    protected FillLiquidityInd fillLiquidityInd;

    /**
     * Starting with 5.0SP1 version.
     */
    protected NestedParties4 nestedParties4;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public FillExecGroup() {
    }

    public FillExecGroup(FragmentContext context) {
        super(context);
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Public Methods">

    @Override
    public int getFirstTag() {
        return TagNum.FillExecID.getValue();
    }

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
    @TagNumRef(tagNum = TagNum.FillExecID)
    public String getFillExecID() {
        return fillExecID;
    }

    /**
     * Message field setter.
     * @param fillExecID field value
     */
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.FillExecID)
    public void setFillExecID(String fillExecID) {
        this.fillExecID = fillExecID;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.FillPx)
    public Double getFillPx() {
        return fillPx;
    }

    /**
     * Message field setter.
     * @param fillPx field value
     */
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.FillPx)
    public void setFillPx(Double fillPx) {
        this.fillPx = fillPx;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.FillQty)
    public Double getFillQty() {
        return fillQty;
    }

    /**
     * Message field setter.
     * @param fillQty field value
     */
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.FillQty)
    public void setFillQty(Double fillQty) {
        this.fillQty = fillQty;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.FillLiquidityInd)
    public FillLiquidityInd getFillLiquidityInd() {
        return fillLiquidityInd;
    }

    /**
     * Message field setter.
     * @param fillLiquidityInd field value
     */
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.FillLiquidityInd)
    public void setFillLiquidityInd(FillLiquidityInd fillLiquidityInd) {
        this.fillLiquidityInd = fillLiquidityInd;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "5.0SP1")
    public NestedParties4 getNestedParties4() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Sets the {@link NestedParties4} component if used in this message to the proper implementation.<br/>
     */
    @FIXVersion(introduced = "5.0SP1")
    public void setNestedParties4() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Sets the {@link NestedParties4} component to null.<br/>
     */
    @FIXVersion(introduced = "5.0SP1")
    public void clearNestedParties4() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

    @Override
    protected void validateRequiredTags() throws TagNotPresentException {
        StringBuilder errorMsg = new StringBuilder("Tag value(s) for");
        boolean hasMissingTag = false;
         if (fillExecID == null || fillExecID.trim().isEmpty()) {
            errorMsg.append(" [FillExecID]");
            hasMissingTag = true;
        }
        errorMsg.append(" is missing.");
        if (hasMissingTag) {
            throw new TagNotPresentException(errorMsg.toString());
        }
    }

    @Override
    protected byte[] encodeFragmentAll() throws TagNotPresentException, BadFormatMsgException {
        if (validateRequired) {             validateRequiredTags();         }

        byte[] result = new byte[0];
        ByteArrayOutputStream bao = new ByteArrayOutputStream();
        try {
            TagEncoder.encode(bao, TagNum.FillExecID, fillExecID);
            TagEncoder.encode(bao, TagNum.FillPx, fillPx);
            TagEncoder.encode(bao, TagNum.FillQty, fillQty);
            if (fillLiquidityInd != null) {
                TagEncoder.encode(bao, TagNum.FillLiquidityInd, fillLiquidityInd.getValue());
            }
            if (nestedParties4 != null) {
                bao.write(nestedParties4.encode(MsgSecureType.ALL_FIELDS));
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
            case FillExecID:
                fillExecID = new String(tag.value, sessionCharset);
                break;

            case FillPx:
                fillPx = new Double(new String(tag.value, sessionCharset));
                break;

            case FillQty:
                fillQty = new Double(new String(tag.value, sessionCharset));
                break;

            case FillLiquidityInd:
                fillLiquidityInd = FillLiquidityInd.valueFor(Integer.valueOf(new String(tag.value, sessionCharset)));
                break;

            default:
                String error = "Tag value [" + tag.tagNum + "] not present in [FillExecGroup] fields.";
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
        b.append("{FillExecGroup=");
        printTagValue(b, TagNum.FillExecID, fillExecID);
        printTagValue(b, TagNum.FillPx, fillPx);
        printTagValue(b, TagNum.FillQty, fillQty);
        printTagValue(b, TagNum.FillLiquidityInd, fillLiquidityInd);
        printTagValue(b, nestedParties4);
        b.append("}");

        return b.toString();
    }

    // </editor-fold>
}
