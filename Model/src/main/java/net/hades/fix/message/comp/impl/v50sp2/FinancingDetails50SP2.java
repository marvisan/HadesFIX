/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * FinancingDetails50SP2.java
 *
 * $Id: FinancingDetails50SP2.java,v 1.7 2010-02-25 08:37:43 vrotaru Exp $
 */
package net.hades.fix.message.comp.impl.v50sp2;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.type.Currency;

import java.util.Date;
import java.util.Set;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import net.hades.fix.message.comp.FinancingDetails;
import net.hades.fix.message.type.DeliveryType;
import net.hades.fix.message.type.TerminationType;
import net.hades.fix.message.xml.codec.jaxb.adapter.FixDateAdapter;

/**
 * FIX version 5.0SP1 implementation of FinancingDetails component.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.7 $
 * @created 14/02/2009, 2:55:05 PM
 */
@XmlRootElement(name="FinDetls")
@XmlType
@XmlAccessorType(XmlAccessType.NONE)
public class FinancingDetails50SP2 extends FinancingDetails {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">

    protected Set<Integer> STANDARD_SECURED_TAGS = TAGS;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public FinancingDetails50SP2() {
        super();
    }

    public FinancingDetails50SP2(FragmentContext context) {
        super(context);
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Public Methods">

    @Override
    public Set<Integer> getFragmentAllTags() {
        return TAGS;
    }

    // ACCESSOR METHODS
    //////////////////////////////////////////

    @XmlAttribute(name = "AgmtDesc")
    @Override
    public String getAgreementDesc() {
        return agreementDesc;
    }

    @Override
    public void setAgreementDesc(String agreementDesc) {
        this.agreementDesc = agreementDesc;
    }

    @XmlAttribute(name = "AgmtID")
    @Override
    public String getAgreementID() {
        return agreementID;
    }

    @Override
    public void setAgreementID(String agreementID) {
        this.agreementID = agreementID;
    }

    @XmlAttribute(name = "AgmtDt")
    @XmlJavaTypeAdapter(FixDateAdapter.class)
    @Override
    public Date getAgreementDate() {
        return agreementDate;
    }

    @Override
    public void setAgreementDate(Date agreementDate) {
        this.agreementDate = agreementDate;
    }

    @XmlAttribute(name = "AgmtCcy")
    @Override
    public Currency getAgreementCurrency() {
        return agreementCurrency;
    }

    @Override
    public void setAgreementCurrency(Currency agreementCurrency) {
        this.agreementCurrency = agreementCurrency;
    }

    @XmlAttribute(name = "TrmTyp")
    @Override
    public TerminationType getTerminationType() {
        return terminationType;
    }

    @Override
    public void setTerminationType(TerminationType terminationType) {
        this.terminationType = terminationType;
    }

    @XmlAttribute(name = "StartDt")
    @XmlJavaTypeAdapter(FixDateAdapter.class)
    @Override
    public Date getStartDate() {
        return startDate;
    }

    @Override
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    @XmlAttribute(name = "EndDt")
    @XmlJavaTypeAdapter(FixDateAdapter.class)
    @Override
    public Date getEndDate() {
        return endDate;
    }

    @Override
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @XmlAttribute(name = "DlvryTyp")
    @Override
    public DeliveryType getDeliveryType() {
        return deliveryType;
    }

    @Override
    public void setDeliveryType(DeliveryType deliveryType) {
        this.deliveryType = deliveryType;
    }

    @XmlAttribute(name = "MgnRatio")
    @Override
    public Double getMarginRatio() {
        return marginRatio;
    }

    @Override
    public void setMarginRatio(Double marginRatio) {
        this.marginRatio = marginRatio;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

    @Override
    protected String getUnsupportedTagMessage() {
        return "This tag is not supported in [FinancingDetails] component version [5.0SP2].";
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Package Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Private Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Inner Classes">
    // </editor-fold>
}
