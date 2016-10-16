/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * DistribInstsGroup44.java
 *
 * $Id: DistribInstsGroup44.java,v 1.2 2011-10-29 01:31:21 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v44;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.group.DistribInstsGroup;
import net.hades.fix.message.type.Currency;
import net.hades.fix.message.type.DistribPaymentMethod;
import net.hades.fix.message.type.TagNum;
import net.hades.fix.message.util.MsgUtil;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.util.TagEncoder;

/**
 * FIX 4.4 implementation of DistribInsts group.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 * @created 05/04/2009, 11:39:24 AM
 */
@XmlRootElement(name="RgDtlInst")
@XmlType
@XmlAccessorType(XmlAccessType.NONE)
public class DistribInstsGroup44 extends DistribInstsGroup {

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

    public DistribInstsGroup44() {
        SECURED_TAGS = STANDARD_SECURED_TAGS;
    }

    public DistribInstsGroup44(FragmentContext context) {
        super(context);
        SECURED_TAGS = STANDARD_SECURED_TAGS;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Public Methods">

    @Override
    public Set<Integer> getFragmentAllTags() {
        return ALL_TAGS;
    }

    @XmlAttribute(name = "DistribPmtMethod")
    @Override
    public DistribPaymentMethod getDistribPaymentMethod() {
        return distribPaymentMethod;
    }

    @Override
    public void setDistribPaymentMethod(DistribPaymentMethod distribPaymentMethod) {
        this.distribPaymentMethod = distribPaymentMethod;
    }

    @XmlAttribute(name = "DistribPctage")
    @Override
    public Double getDistribPercentage() {
        return distribPercentage;
    }

    @Override
    public void setDistribPercentage(Double distribPercentage) {
        this.distribPercentage = distribPercentage;
    }

    @XmlAttribute(name = "CshDistribCurr")
    @Override
    public Currency getCashDistribCurr() {
        return cashDistribCurr;
    }

    @Override
    public void setCashDistribCurr(Currency cashDistribCurr) {
        this.cashDistribCurr = cashDistribCurr;
    }

    @XmlAttribute(name = "CshDistribAgentName")
    @Override
    public String getCashDistribAgentName() {
        return cashDistribAgentName;
    }

    @Override
    public void setCashDistribAgentName(String cashDistribAgentName) {
        this.cashDistribAgentName = cashDistribAgentName;
    }

    @XmlAttribute(name = "CshDistribAgentCode")
    @Override
    public String getCashDistribAgentCode() {
        return cashDistribAgentCode;
    }

    @Override
    public void setCashDistribAgentCode(String cashDistribAgentCode) {
        this.cashDistribAgentCode = cashDistribAgentCode;
    }

    @XmlAttribute(name = "CshDistribAgentAcctNum")
    @Override
    public String getCashDistribAgentAcctNumber() {
        return cashDistribAgentAcctNumber;
    }

    @Override
    public void setCashDistribAgentAcctNumber(String cashDistribAgentAcctNumber) {
        this.cashDistribAgentAcctNumber = cashDistribAgentAcctNumber;
    }

    @XmlAttribute(name = "CshDistribPayRef")
    @Override
    public String getCashDistribPayRef() {
        return cashDistribPayRef;
    }

    @Override
    public void setCashDistribPayRef(String cashDistribPayRef) {
        this.cashDistribPayRef = cashDistribPayRef;
    }

    @XmlAttribute(name = "CshDistribAgentAcctName")
    @Override
    public String getCashDistribAgentAcctName() {
        return cashDistribAgentAcctName;
    }

    @Override
    public void setCashDistribAgentAcctName(String cashDistribAgentAcctName) {
        this.cashDistribAgentAcctName = cashDistribAgentAcctName;
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
            if (distribPaymentMethod != null && MsgUtil.isTagInList(TagNum.DistribPaymentMethod, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.DistribPaymentMethod, distribPaymentMethod.getValue());
            }
            if (MsgUtil.isTagInList(TagNum.DistribPercentage, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.DistribPercentage, distribPercentage);
            }
            if (cashDistribCurr != null && MsgUtil.isTagInList(TagNum.CashDistribCurr, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.CashDistribCurr, cashDistribCurr.getValue());
            }
            if (MsgUtil.isTagInList(TagNum.CashDistribAgentName, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.CashDistribAgentName, cashDistribAgentName);
            }
            if (MsgUtil.isTagInList(TagNum.CashDistribAgentCode, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.CashDistribAgentCode, cashDistribAgentCode);
            }
            if (MsgUtil.isTagInList(TagNum.CashDistribAgentAcctNumber, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.CashDistribAgentAcctNumber, cashDistribAgentAcctNumber);
            }
            if (MsgUtil.isTagInList(TagNum.CashDistribPayRef, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.CashDistribPayRef, cashDistribPayRef);
            }
            if (MsgUtil.isTagInList(TagNum.CashDistribAgentAcctName, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.CashDistribAgentAcctName, cashDistribAgentAcctName);
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
        return "This tag is not supported in [DistribInstsGroup] group version [4.4].";
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Package Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Private Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Inner Classes">
    // </editor-fold>
}
