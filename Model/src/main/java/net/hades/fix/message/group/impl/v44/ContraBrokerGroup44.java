/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * ContraBrokerGroup44.java
 *
 * $Id: ContraBrokerGroup44.java,v 1.1 2010-12-22 09:30:31 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v44;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.group.ContraBrokerGroup;
import net.hades.fix.message.type.TagNum;
import net.hades.fix.message.util.MsgUtil;
import net.hades.fix.message.util.TagEncoder;
import net.hades.fix.message.xml.codec.jaxb.adapter.FixUTCDateTimeAdapter;

/**
 * FIX 4.4 implementation of ContraBrokerGroup group.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 05/04/2009, 11:39:24 AM
 */
@XmlRootElement(name="Contra")
@XmlType
@XmlAccessorType(XmlAccessType.NONE)
public class ContraBrokerGroup44 extends ContraBrokerGroup {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> ALL_TAGS;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">

    static {
        ALL_TAGS = new HashSet<Integer>(TAGS);
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">
    
    protected Set<Integer> STANDARD_SECURED_TAGS = TAGS;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public ContraBrokerGroup44() {
        SECURED_TAGS = STANDARD_SECURED_TAGS;
    }

    public ContraBrokerGroup44(FragmentContext context) {
        super(context);
        SECURED_TAGS = STANDARD_SECURED_TAGS;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Public Methods">

    @Override
    public Set<Integer> getFragmentAllTags() {
        return ALL_TAGS;
    }

    @XmlAttribute(name = "CntraBrkr")
    @Override
    public String getContraBroker() {
        return contraBroker;
    }

    @Override
    public void setContraBroker(String contraBroker) {
        this.contraBroker = contraBroker;
    }

    @XmlAttribute(name = "CntraTrdr")
    @Override
    public String getContraTrader() {
        return contraTrader;
    }

    @Override
    public void setContraTrader(String contraTrader) {
        this.contraTrader = contraTrader;
    }

    @XmlAttribute(name = "CntraTrdQty")
    @Override
    public Double getContraTradeQty() {
        return contraTradeQty;
    }

    @Override
    public void setContraTradeQty(Double contraTradeQty) {
        this.contraTradeQty = contraTradeQty;
    }

    @XmlAttribute(name = "CntraTrdTm")
    @XmlJavaTypeAdapter(FixUTCDateTimeAdapter.class)
    @Override
    public Date getContraTradeTime() {
        return contraTradeTime;
    }

    @Override
    public void setContraTradeTime(Date contraTradeTime) {
        this.contraTradeTime = contraTradeTime;
    }

    @XmlAttribute(name = "CntraLegRefID")
    @Override
    public String getContraLegRefID() {
        return contraLegRefID;
    }

    @Override
    public void setContraLegRefID(String contraLegRefID) {
        this.contraLegRefID = contraLegRefID;
    }

    // ACCESSORS
    //////////////////////////////////////////

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

    @Override
    protected byte[] encodeFragmentSecured(boolean secured) throws TagNotPresentException, BadFormatMsgException {
        if (validateRequired) {
            validateRequiredTags();
        }
        byte[] result = new byte[0];
        ByteArrayOutputStream bao = new ByteArrayOutputStream();
        try {
            if (MsgUtil.isTagInList(TagNum.ContraBroker, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.ContraBroker, contraBroker);
            }
            if (MsgUtil.isTagInList(TagNum.ContraTrader, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.ContraTrader, contraTrader);
            }
            if (MsgUtil.isTagInList(TagNum.ContraTradeQty, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.ContraTradeQty, contraTradeQty);
            }
            if (MsgUtil.isTagInList(TagNum.ContraTradeTime, SECURED_TAGS, secured)) {
                TagEncoder.encodeUtcTimestamp(bao, TagNum.ContraTradeTime, contraTradeTime);
            }
            if (MsgUtil.isTagInList(TagNum.ContraLegRefID, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.ContraLegRefID, contraLegRefID);
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
    protected String getUnsupportedTagMessage() {
        return "This tag is not supported in [ContraBrokerGroup] group version [4.4].";
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Package Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Private Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Inner Classes">
    // </editor-fold>
}
