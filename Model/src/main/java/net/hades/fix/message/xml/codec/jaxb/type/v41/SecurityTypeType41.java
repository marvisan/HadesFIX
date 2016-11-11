/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * SecurityTypeType.java
 *
 * $Id: SecurityTypeType41.java,v 1.1 2009-07-06 03:19:19 vrotaru Exp $
 */
package net.hades.fix.message.xml.codec.jaxb.type.v41;

import net.hades.fix.message.xml.codec.jaxb.adapter.FixNumberAdapter;
import net.hades.fix.message.xml.codec.jaxb.type.SingleStringAttrSimpleType;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementRefs;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import net.hades.fix.message.xml.codec.jaxb.type.MaturityType;
import net.hades.fix.message.xml.codec.jaxb.type.PutOrCallType;

/**
 * FIXML JAXB type for SecurityType tag.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 22/05/2009, 8:53:16 AM
 */
@XmlRootElement(name = "SecurityType")
@XmlType(propOrder = {"element"})
@XmlAccessorType(XmlAccessType.NONE)
public class SecurityTypeType41 {

    // <editor-fold defaultstate="collapsed" desc="Constants">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">

    @XmlElementRefs({
        @XmlElementRef(type = EquityType.class),
        @XmlElementRef(type = FixedIncomeType.class),
        @XmlElementRef(type = ForeignExchangeType.class),
        @XmlElementRef(type = FutureType.class),
        @XmlElementRef(type = MutualFundType.class),
        @XmlElementRef(type = OptionType.class),
        @XmlElementRef(type = WarrantType.class)
    })
    private SingleStringAttrSimpleType element;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Public Methods">

    public SingleStringAttrSimpleType getElement() {
        return element;
    }

    public void setElement(SingleStringAttrSimpleType element) {
        this.element = element;
    }

    public String getValue() {
        return element == null ? null : element.getValue();
    }

    public void setValue(String value) {
        if (value.equals("CS") || value.equals("PS")) {
            element = new EquityType(value);
        } else if (value.equals("MF")) {
            element = new MutualFundType(value);
        } else if (value.equals("FOR")) {
            element = new ForeignExchangeType(value);
        } else if (value.equals("BA") || value.equals("CD") || value.equals("CMO") ||
            value.equals("COPR") || value.equals("CP") || value.equals("CPP") ||
            value.equals("FHA") || value.equals("FHL") || value.equals("FN") ||
            value.equals("GN") || value.equals("GOVT") || value.equals("IET") ||
            value.equals("MPO") || value.equals("MPP") || value.equals("MPT") ||
            value.equals("MUNI") || value.equals("RP") || value.equals("RVRP") ||
            value.equals("SL") || value.equals("TD") || value.equals("USTB") ||
            value.equals("ZOO")) {
            element = new FixedIncomeType(value);
        } else if (value.equals("WAR")) {
            element = new WarrantType(value);
        } else if (value.equals("OPT")) {
            element = new OptionType(value);
        } else if (value.equals("FUT")) {
            element = new FutureType(value);
        }
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Package Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Private Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Inner Classes">

    @XmlRootElement(name = "Equity")
    @XmlType(name = "EquityType41")
    @XmlAccessorType(XmlAccessType.NONE)
    public static class EquityType extends SingleStringAttrSimpleType {

        public EquityType() {
        }

        public EquityType(String value) {
            super(value);
        }
    }

    @XmlRootElement(name = "FixedIncome")
    @XmlType
    @XmlAccessorType(XmlAccessType.NONE)
    public static class FixedIncomeType extends SingleStringAttrSimpleType {

        @XmlElement(name = "ContractMultiplier")
        private Double contractMultiplier;

        @XmlElement(name = "CouponRate")
        private Double couponRate;

        public FixedIncomeType() {
        }

        public FixedIncomeType(String value) {
            super(value);
        }

        public Double getContractMultiplier() {
            return contractMultiplier;
        }

        public void setContractMultiplier(Double contractMultiplier) {
            this.contractMultiplier = contractMultiplier;
        }

        public Double getCouponRate() {
            return couponRate;
        }

        public void setCouponRate(Double couponRate) {
            this.couponRate = couponRate;
        }
    }

    @XmlRootElement(name = "ForeignExchange")
    @XmlType(name = "ForeignExchange41")
    @XmlAccessorType(XmlAccessType.NONE)
    public static class ForeignExchangeType extends SingleStringAttrSimpleType {

        public ForeignExchangeType() {
        }

        public ForeignExchangeType(String value) {
            super(value);
        }
    }

    @XmlRootElement(name = "Future")
    @XmlType(name = "Future41")
    @XmlAccessorType(XmlAccessType.NONE)
    public static class FutureType extends SingleStringAttrSimpleType {

        @XmlElement(name = "Maturity")
        private MaturityType maturityType = new MaturityType();

        public FutureType() {
        }

        public FutureType(String value) {
            super(value);
        }

        public MaturityType getMaturityType() {
            return maturityType;
        }

        public void setMaturityType(MaturityType maturityType) {
            this.maturityType = maturityType;
        }
    }

    @XmlRootElement(name = "MutualFund")
    @XmlType(name = "MutualFund41")
    @XmlAccessorType(XmlAccessType.NONE)
    public static class MutualFundType extends SingleStringAttrSimpleType {

        public MutualFundType() {
        }

        public MutualFundType(String value) {
            super(value);
        }
    }

    @XmlRootElement(name = "Option")
    @XmlType(name = "Option41", propOrder = {"putCallType", "maturityType", "strikePrice", "optAttribute"})
    @XmlAccessorType(XmlAccessType.NONE)
    public static class OptionType extends SingleStringAttrSimpleType {

        @XmlElement(name = "PutCall", required = true)
        private PutOrCallType putCallType = new PutOrCallType();

        @XmlElement(name = "Maturity", required = true)
        private MaturityType maturityType = new MaturityType();

        @XmlElement(name = "StrikePx", required = true)
        @XmlJavaTypeAdapter(FixNumberAdapter.class)
        private Double strikePrice;

        @XmlElement(name = "OptAttribute", required = true)
        private Character optAttribute;

        public OptionType() {
        }

        public OptionType(String value) {
            super(value);
        }

        public PutOrCallType getPutCallType() {
            return putCallType;
        }

        public void setPutCallType(PutOrCallType putCallType) {
            this.putCallType = putCallType;
        }

        public MaturityType getMaturityType() {
            return maturityType;
        }

        public void setMaturityType(MaturityType maturityType) {
            this.maturityType = maturityType;
        }

        public Double getStrikePrice() {
            return strikePrice;
        }

        public void setStrikePrice(Double strikePrice) {
            this.strikePrice = strikePrice;
        }

        public Character getOptAttribute() {
            return optAttribute;
        }

        public void setOptAttribute(Character optAttribute) {
            this.optAttribute = optAttribute;
        }
    }

    @XmlRootElement(name = "Warrant")
    @XmlType(name = "Warrant41")
    @XmlAccessorType(XmlAccessType.NONE)
    public static class WarrantType extends SingleStringAttrSimpleType {

        public WarrantType() {
        }

        public WarrantType(String value) {
            super(value);
        }
    }
    // </editor-fold>
}
