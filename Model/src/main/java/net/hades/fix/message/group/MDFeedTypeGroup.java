/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * MDFeedTypeGroup.java
 *
 * $Id: MDFeedTypeGroup.java,v 1.3 2011-04-20 00:32:34 vrotaru Exp $
 */
package net.hades.fix.message.group;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.anno.FIXVersion;
import net.hades.fix.message.anno.TagNumRef;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.struct.Tag;
import net.hades.fix.message.type.MDBookType;
import net.hades.fix.message.type.MarketDepth;
import net.hades.fix.message.type.SessionRejectReason;
import net.hades.fix.message.type.TagNum;
import net.hades.fix.message.util.TagEncoder;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

/**
 * Market data feed types group.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.3 $
 * @created 05/04/2009, 11:10:10 AM
 */
@XmlAccessorType(XmlAccessType.NONE)
public abstract class MDFeedTypeGroup extends Group {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.MDFeedType.getValue(),
        TagNum.MarketDepth.getValue(),
        TagNum.MDBookType.getValue()
    }));

    protected static final Set<Integer> START_DATA_TAGS = null;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">

    // ACCESSORS
    //////////////////////////////////////////


    /**
     * TagNum = 1022. Starting with 5.0SP1 version.
     */
    protected String MDFeedType;

    /**
     * TagNum = 264. Starting with 5.0SP1 version.
     */
    protected MarketDepth marketDepth;

    /**
     * TagNum = 264. Starting with 5.0SP1 version.
     */
    protected net.hades.fix.message.type.MDBookType MDBookType;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public MDFeedTypeGroup() {
    }

    public MDFeedTypeGroup(FragmentContext context) {
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
    @TagNumRef(tagNum=TagNum.MDBookType)
    public MDBookType getMDBookType() {
        return MDBookType;
    }

    /**
     * Message field setter.
     * @param MDBookType field value
     */
    @FIXVersion(introduced="5.0SP1")
    @TagNumRef(tagNum=TagNum.MDBookType)
    public void setMDBookType(MDBookType MDBookType) {
        this.MDBookType = MDBookType;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0SP1")
    @TagNumRef(tagNum=TagNum.MarketDepth)
    public MarketDepth getMarketDepth() {
        return marketDepth;
    }

    /**
     * Message field setter.
     * @param marketDepth field value
     */
    @FIXVersion(introduced="5.0SP1")
    @TagNumRef(tagNum=TagNum.MarketDepth)
    public void setMarketDepth(MarketDepth marketDepth) {
        this.marketDepth = marketDepth;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0SP1")
    @TagNumRef(tagNum=TagNum.MDFeedType)
    public String getMDFeedType() {
        return MDFeedType;
    }

    /**
     * Message field setter.
     * @param MDFeedType field value
     */
    @FIXVersion(introduced="5.0SP1")
    @TagNumRef(tagNum=TagNum.MDFeedType)
    public void setMDFeedType(String MDFeedType) {
        this.MDFeedType = MDFeedType;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">
    
    @Override
    protected int getFirstTag() {
        return TagNum.MDFeedType.getValue();
    }

    @Override
    protected void validateRequiredTags() throws TagNotPresentException {
        StringBuilder errorMsg = new StringBuilder("Tag value(s) for");
        boolean hasMissingTag = false;
        if (MDFeedType == null) {
            errorMsg.append(" [MDFeedType]");
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
            TagEncoder.encode(bao, TagNum.MDFeedType, MDFeedType);
            if (MDBookType != null) {
                TagEncoder.encode(bao, TagNum.MDBookType, MDBookType.getValue());
            }
            if (marketDepth != null) {
                TagEncoder.encode(bao, TagNum.MarketDepth, marketDepth.getValue());
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
            case MDBookType:
                MDBookType = MDBookType.valueFor(Integer.valueOf(new String(tag.value, sessionCharset)));
                break;

            case MarketDepth:
                marketDepth = MarketDepth.valueFor(Integer.valueOf(new String(tag.value, sessionCharset)));
                break;

            case MDFeedType:
                MDFeedType = new String(tag.value, sessionCharset);
                break;

            default:
                String error = "Tag value [" + tag.tagNum + "] not present in [MDFeedTypeGroup] fields.";
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
        b.append("{MDFeedTypeGroup=");
        printTagValue(b, TagNum.MDBookType, MDBookType);
        printTagValue(b, TagNum.MarketDepth, marketDepth);
        printTagValue(b, TagNum.MDFeedType, MDFeedType);
        b.append("}");

        return b.toString();
    }

    // </editor-fold>
}
