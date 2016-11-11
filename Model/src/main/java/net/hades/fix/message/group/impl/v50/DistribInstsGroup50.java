/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * DistribInstsGroup50.java
 *
 * $Id: DistribInstsGroup50.java,v 1.2 2011-10-29 01:31:23 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v50;

import java.util.HashSet;
import java.util.Set;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.group.DistribInstsGroup;
import net.hades.fix.message.type.Currency;
import net.hades.fix.message.type.DistribPaymentMethod;

/**
 * FIX 5.0 implementation of DistribInsts group.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 * @created 05/04/2009, 11:39:24 AM
 */
@XmlRootElement(name="RgDtlInst")
@XmlType
@XmlAccessorType(XmlAccessType.NONE)
public class DistribInstsGroup50 extends DistribInstsGroup {

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
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public DistribInstsGroup50() {
    }

    public DistribInstsGroup50(FragmentContext context) {
        super(context);
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
    protected String getUnsupportedTagMessage() {
        return "This tag is not supported in [DistribInstsGroup] group version [5.0].";
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Package Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Private Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Inner Classes">
    // </editor-fold>
}
