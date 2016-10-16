/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * UnderlyingAmountGroup50SP1.java
 *
 * $Id: UnderlyingStipsGroup50.java,v 1.3 2010-02-04 10:11:07 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v50sp1;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.group.UnderlyingAmountGroup;
import net.hades.fix.message.xml.codec.jaxb.adapter.FixDateAdapter;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 * FIX 5.0SP1 implementation of UnderlyingAmountGroup.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.3 $
 * @created 11/12/2011, 11:57:16 AM
 */
@XmlRootElement(name="UndDlvAmt")
@XmlType
@XmlAccessorType(XmlAccessType.NONE)
public class UnderlyingAmountGroup50SP1 extends UnderlyingAmountGroup {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public UnderlyingAmountGroup50SP1() {
    }

    public UnderlyingAmountGroup50SP1(FragmentContext context) {
        super(context);
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Public Methods">

    @XmlAttribute(name = "PayAmt")
    @Override
    public Double getUnderlyingPayAmount() {
        return underlyingPayAmount;
    }

    @Override
    public void setUnderlyingPayAmount(Double underlyingPayAmount) {
        this.underlyingPayAmount = underlyingPayAmount;
    }

    @XmlAttribute(name = "ColAmt")
    @Override
    public Double getUnderlyingCollectAmount() {
        return underlyingCollectAmount;
    }

    @Override
    public void setUnderlyingCollectAmount(Double underlyingCollectAmount) {
        this.underlyingCollectAmount = underlyingCollectAmount;
    }

    @XmlAttribute(name = "StlDt")
    @XmlJavaTypeAdapter(FixDateAdapter.class)
    @Override
    public Date getUnderlyingSettlementDate() {
        return underlyingSettlementDate;
    }

    @Override
    public void setUnderlyingSettlementDate(Date underlyingSettlementDate) {
        this.underlyingSettlementDate = underlyingSettlementDate;
    }

    @XmlAttribute(name = "SetStat")
    @Override
    public String getUnderlyingSettlementStatus() {
        return underlyingSettlementStatus;
    }

    @Override
    public void setUnderlyingSettlementStatus(String underlyingSettlementStatus) {
        this.underlyingSettlementStatus = underlyingSettlementStatus;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Package Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Private Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Inner Classes">
    // </editor-fold>
}
