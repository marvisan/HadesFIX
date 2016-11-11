/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * ContraBrokerGroup.java
 *
 * $Id: ContraBrokerGroup.java,v 1.1 2010-12-22 09:30:32 vrotaru Exp $
 */
package net.hades.fix.message.group;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.anno.FIXVersion;
import net.hades.fix.message.anno.TagNumRef;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.struct.Tag;
import net.hades.fix.message.type.SessionRejectReason;
import net.hades.fix.message.type.TagNum;
import net.hades.fix.message.util.DateConverter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import net.hades.fix.message.util.TagEncoder;

/**
 * Contra broker group.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 05/04/2009, 11:10:10 AM
 */
@XmlAccessorType(XmlAccessType.NONE)
public abstract class ContraBrokerGroup extends Group {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.ContraBroker.getValue(),
        TagNum.ContraTrader.getValue(),
        TagNum.ContraTradeQty.getValue(),
        TagNum.ContraTradeTime.getValue(),
        TagNum.ContraLegRefID.getValue()
    }));

    protected static final Set<Integer> START_COMP_TAGS = null;

    protected static final Set<Integer> START_DATA_TAGS = null;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">

    // ACCESSORS
    //////////////////////////////////////////


    /**
     * TagNum = 375. Starting with 4.2 version.
     */
    protected String contraBroker;

    /**
     * TagNum = 337. Starting with 4.2 version.
     */
    protected String contraTrader;
    
    /**
     * TagNum = 437. Starting with 4.2 version.
     */
    protected Double contraTradeQty;

    /**
     * TagNum = 438. Starting with 4.2 version.
     */
    protected Date contraTradeTime;

    /**
     * TagNum = 655. Starting with 4.3 version.
     */
    protected String contraLegRefID;
    
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public ContraBrokerGroup() {
    }

    public ContraBrokerGroup(FragmentContext context) {
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
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.ContraBroker)
    public String getContraBroker() {
        return contraBroker;
    }

    /**
     * Message field setter.
     * @param contraBroker field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.ContraBroker)
    public void setContraBroker(String contraBroker) {
        this.contraBroker = contraBroker;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.ContraTrader)
     public String getContraTrader() {
        return contraTrader;
    }

    /**
     * Message field setter.
     * @param contraTrader field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.ContraTrader)
    public void setContraTrader(String contraTrader) {
        this.contraTrader = contraTrader;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.ContraTradeQty)
    public Double getContraTradeQty() {
        return contraTradeQty;
    }

    /**
     * Message field setter.
     * @param contraTradeQty field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.ContraTradeQty)
    public void setContraTradeQty(Double contraTradeQty) {
        this.contraTradeQty = contraTradeQty;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.ContraTradeTime)
    public Date getContraTradeTime() {
        return contraTradeTime;
    }

    /**
     * Message field setter.
     * @param contraTradeTime field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.ContraTradeTime)
    public void setContraTradeTime(Date contraTradeTime) {
        this.contraTradeTime = contraTradeTime;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.ContraLegRefID)
    public String getContraLegRefID() {
        return contraLegRefID;
    }

    /**
     * Message field setter.
     * @param contraLegRefID field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.ContraLegRefID)
    public void setContraLegRefID(String contraLegRefID) {
        this.contraLegRefID = contraLegRefID;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">
    
    @Override
    protected int getFirstTag() {
        return TagNum.ContraBroker.getValue();
    }

    @Override
    protected void validateRequiredTags() throws TagNotPresentException {
        StringBuilder errorMsg = new StringBuilder("Tag value(s) for");
        boolean hasMissingTag = false;
        if (contraBroker == null || contraBroker.trim().isEmpty()) {
            errorMsg.append(" [ContraBroker]");
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
            TagEncoder.encode(bao, TagNum.ContraBroker, contraBroker);
            TagEncoder.encode(bao, TagNum.ContraTrader, contraTrader);
            TagEncoder.encode(bao, TagNum.ContraTradeQty, contraTradeQty);
            TagEncoder.encodeUtcTimestamp(bao, TagNum.ContraTradeTime, contraTradeTime);
            TagEncoder.encode(bao, TagNum.ContraLegRefID, contraLegRefID);
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
            case ContraBroker:
                contraBroker = new String(tag.value, sessionCharset);
                break;

            case ContraTrader:
                contraTrader = new String(tag.value, sessionCharset);
                break;

            case ContraTradeQty:
                contraTradeQty = Double.valueOf(new String(tag.value, sessionCharset));
                break;

            case ContraTradeTime:
                contraTradeTime = DateConverter.parseString(new String(tag.value, sessionCharset));
                break;

            case ContraLegRefID:
                contraLegRefID = new String(tag.value, sessionCharset);
                break;

            default:
                String error = "Tag value [" + tag.tagNum + "] not present in [ContraBrokerGroup] fields.";
                LOGGER.severe(error);
                throw new BadFormatMsgException(SessionRejectReason.InvalidTagNumber, tag.tagNum, error);
        }
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
        b.append("{ContraBrokerGroup=");
        printTagValue(b, TagNum.ContraBroker, contraBroker);
        printTagValue(b, TagNum.ContraTrader, contraTrader);
        printTagValue(b, TagNum.ContraTradeQty, contraTradeQty);
        printUTCDateTimeTagValue(b, TagNum.ContraTradeTime, contraTradeTime);
        printTagValue(b, TagNum.ContraLegRefID, contraLegRefID);
        b.append("}");

        return b.toString();
    }

    // </editor-fold>
}
