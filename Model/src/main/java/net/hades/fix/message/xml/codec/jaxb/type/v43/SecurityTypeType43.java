/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * SecurityTypeType.java
 *
 * $Id: SecurityTypeType43.java,v 1.1 2009-07-06 03:19:19 vrotaru Exp $
 */
package net.hades.fix.message.xml.codec.jaxb.type.v43;

import net.hades.fix.message.xml.codec.jaxb.type.SingleStringAttrSimpleType;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementRefs;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import net.hades.fix.message.xml.codec.jaxb.adapter.FixDateAdapter;
import net.hades.fix.message.xml.codec.jaxb.adapter.FixNumberAdapter;
import net.hades.fix.message.xml.codec.jaxb.type.MaturityType;

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
public class SecurityTypeType43 {

    // <editor-fold defaultstate="collapsed" desc="Constants">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">

    @XmlElementRefs({
        @XmlElementRef(type = AgencyType.class),
        @XmlElementRef(type = CorporateType.class),
        @XmlElementRef(type = EquityType.class),
        @XmlElementRef(type = ForeignExchangeType.class),
        @XmlElementRef(type = FutureType.class),
        @XmlElementRef(type = GovernmentType.class),
        @XmlElementRef(type = LoanType.class),
        @XmlElementRef(type = MoneyMarketType.class),
        @XmlElementRef(type = MortgageType.class),
        @XmlElementRef(type = MunicipalType.class),
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
        if (value.equals("FAC") || value.equals("FADN") || value.equals("PEF")) {
            element = new AgencyType(value);
        } else if (value.equals("CORP") || value.equals("CPP") || value.equals("CB") ||
            value.equals("DUAL") || value.equals("XLINKD") || value.equals("STRUCT") ||
            value.equals("YANK")) {
            element = new CorporateType(value);
        } else if (value.equals("CS") || value.equals("PS")) {
            element = new EquityType(value);
        } else if (value.equals("BRADY") || value.equals("SOV") || value.equals("TBOND") ||
            value.equals("TINT") || value.equals("TIPS") || value.equals("TCAL") ||
            value.equals("TPRN") || value.equals("UST") || value.equals("USTB")) {
            element = new GovernmentType(value);
        } else if (value.equals("TERM") || value.equals("RVLV") || value.equals("RVLVTRM") ||
            value.equals("BRIDGE") || value.equals("LOFC") || value.equals("SWING") ||
            value.equals("DINP") || value.equals("DEFLTED") || value.equals("WITHDRN") ||
            value.equals("REPLACD") || value.equals("MATURED") || value.equals("AMENDED") ||
            value.equals("RETIRED")) {
            element = new LoanType(value);
        } else if (value.equals("BA") || value.equals("BN") || value.equals("BOX") ||
            value.equals("CD") || value.equals("CL") || value.equals("CP") ||
            value.equals("DN") || value.equals("LQN") || value.equals("MTN") ||
            value.equals("ONITE") || value.equals("PN") || value.equals("PZFJ") ||
            value.equals("RP") || value.equals("RVRP") || value.equals("STN") ||
            value.equals("TD") || value.equals("XCN")) {
            element = new MoneyMarketType(value);
        } else if (value.equals("POOL") || value.equals("ABS") || value.equals("CMBS") ||
            value.equals("CMO") || value.equals("IET") || value.equals("MBS") ||
            value.equals("MIO") || value.equals("MPO") || value.equals("MPP") ||
            value.equals("MPT") || value.equals("TBA")) {
            element = new MortgageType(value);
        } else if (value.equals("AN") || value.equals("COFO") || value.equals("COFP") ||
            value.equals("GO") || value.equals("MT") || value.equals("RAN") ||
            value.equals("REV") || value.equals("SPCLA") || value.equals("SPCLO") ||
            value.equals("SPCLT") || value.equals("TAN") || value.equals("TAXA") ||
            value.equals("TECP") || value.equals("TRAN") || value.equals("VRDN") ||
            value.equals("WAR")) {
            element = new MunicipalType(value);
        } else if (value.equals("MF")) {
            element = new MutualFundType(value);
        } else if (value.equals("FOR")) {
            element = new ForeignExchangeType(value);
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
    @XmlType(name = "EquityType43")
    @XmlAccessorType(XmlAccessType.NONE)
    public static class EquityType extends SingleStringAttrSimpleType {

        public EquityType() {
        }

        public EquityType(String value) {
            super(value);
        }
    }

    @XmlRootElement(name = "ForeignExchange")
    @XmlType(name = "ForeignExchange43")
    @XmlAccessorType(XmlAccessType.NONE)
    public static class ForeignExchangeType extends SingleStringAttrSimpleType {

        public ForeignExchangeType() {
        }

        public ForeignExchangeType(String value) {
            super(value);
        }
    }

    @XmlRootElement(name = "Future")
    @XmlType(name = "Future43")
    @XmlAccessorType(XmlAccessType.NONE)
    public static class FutureType extends SingleStringAttrSimpleType {

        @XmlElement(name = "Maturity")
        private MaturityType maturityType;

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
    @XmlType(name = "MutualFund43")
    @XmlAccessorType(XmlAccessType.NONE)
    public static class MutualFundType extends SingleStringAttrSimpleType {

        public MutualFundType() {
        }

        public MutualFundType(String value) {
            super(value);
        }
    }

    @XmlRootElement(name = "Option")
    @XmlType(name = "Option43", propOrder = {"strikePrice", "optAttribute"})
    @XmlAccessorType(XmlAccessType.NONE)
    public static class OptionType extends SingleStringAttrSimpleType {

        @XmlElement(name = "StrikePrice", required = true)
        @XmlJavaTypeAdapter(FixNumberAdapter.class)
        private Double strikePrice;

        @XmlElement(name = "OptAttribute", required = true)
        private Character optAttribute;

        public OptionType() {
        }

        public OptionType(String value) {
            super(value);
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
    @XmlType(name = "Warrant43")
    @XmlAccessorType(XmlAccessType.NONE)
    public static class WarrantType extends SingleStringAttrSimpleType {

        public WarrantType() {
        }

        public WarrantType(String value) {
            super(value);
        }
    }

    @XmlRootElement(name = "Agency")
    @XmlType(propOrder = {"issueDate", "redemptionDate", "contractMultiplier", "couponRate"})
    @XmlAccessorType(XmlAccessType.NONE)
    public static class AgencyType extends SingleStringAttrSimpleType {

        @XmlElement(name = "IssueDate")
        @XmlJavaTypeAdapter(FixDateAdapter.class)
        private Date issueDate;

        @XmlElement(name = "RedemptionDate")
        @XmlJavaTypeAdapter(FixDateAdapter.class)
        private Date redemptionDate;

        @XmlElement(name = "ContractMultiplier")
        private Double contractMultiplier;

        @XmlElement(name = "CouponRate")
        private Double couponRate;

        public AgencyType() {
        }

        public AgencyType(String value) {
            super(value);
        }

        public Date getIssueDate() {
            return issueDate;
        }

        public void setIssueDate(Date issueDate) {
            this.issueDate = issueDate;
        }

        public Date getRedemptionDate() {
            return redemptionDate;
        }

        public void setRedemptionDate(Date redemptionDate) {
            this.redemptionDate = redemptionDate;
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

    @XmlRootElement(name = "Corporate")
    @XmlType(propOrder = {"couponPaymentDate", "issueDate", "redemptionDate", "contractMultiplier", "couponRate"})
    @XmlAccessorType(XmlAccessType.NONE)
    public static class CorporateType extends SingleStringAttrSimpleType {

        @XmlElement(name = "CouponPaymentDate")
        @XmlJavaTypeAdapter(FixDateAdapter.class)
        private Date couponPaymentDate;

        @XmlElement(name = "IssueDate")
        @XmlJavaTypeAdapter(FixDateAdapter.class)
        private Date issueDate;

        @XmlElement(name = "RedemptionDate")
        @XmlJavaTypeAdapter(FixDateAdapter.class)
        private Date redemptionDate;

        @XmlElement(name = "ContractMultiplier")
        private Double contractMultiplier;

        @XmlElement(name = "CouponRate")
        private Double couponRate;

        public CorporateType() {
        }

        public CorporateType(String value) {
            super(value);
        }

        public Date getIssueDate() {
            return issueDate;
        }

        public void setIssueDate(Date issueDate) {
            this.issueDate = issueDate;
        }

        public Date getRedemptionDate() {
            return redemptionDate;
        }

        public void setRedemptionDate(Date redemptionDate) {
            this.redemptionDate = redemptionDate;
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

        public Date getCouponPaymentDate() {
            return couponPaymentDate;
        }

        public void setCouponPaymentDate(Date couponPaymentDate) {
            this.couponPaymentDate = couponPaymentDate;
        }
    }

    @XmlRootElement(name = "Government")
    @XmlType(propOrder = {"issueDate", "factor", "redemptionDate", "contractMultiplier", "couponRate"})
    @XmlAccessorType(XmlAccessType.NONE)
    public static class GovernmentType extends SingleStringAttrSimpleType {

        @XmlElement(name = "IssueDate")
        @XmlJavaTypeAdapter(FixDateAdapter.class)
        private Date issueDate;

        @XmlElement(name = "Factor")
        @XmlJavaTypeAdapter(FixNumberAdapter.class)
        private Double factor;

        @XmlElement(name = "RedemptionDate")
        @XmlJavaTypeAdapter(FixDateAdapter.class)
        private Date redemptionDate;

        @XmlElement(name = "ContractMultiplier")
        private Double contractMultiplier;

        @XmlElement(name = "CouponRate")
        private Double couponRate;

        public GovernmentType() {
        }

        public GovernmentType(String value) {
            super(value);
        }

        public Date getIssueDate() {
            return issueDate;
        }

        public void setIssueDate(Date issueDate) {
            this.issueDate = issueDate;
        }

        public Double getFactor() {
            return factor;
        }

        public void setFactor(Double factor) {
            this.factor = factor;
        }

        public Date getRedemptionDate() {
            return redemptionDate;
        }

        public void setRedemptionDate(Date redemptionDate) {
            this.redemptionDate = redemptionDate;
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

    @XmlRootElement(name = "Loan")
    @XmlType(propOrder = {"issueDate", "redemptionDate", "contractMultiplier", "couponRate"})
    @XmlAccessorType(XmlAccessType.NONE)
    public static class LoanType extends SingleStringAttrSimpleType {

        @XmlElement(name = "IssueDate")
        @XmlJavaTypeAdapter(FixDateAdapter.class)
        private Date issueDate;

        @XmlElement(name = "RedemptionDate")
        @XmlJavaTypeAdapter(FixDateAdapter.class)
        private Date redemptionDate;

        @XmlElement(name = "ContractMultiplier")
        private Double contractMultiplier;

        @XmlElement(name = "CouponRate")
        private Double couponRate;

        public LoanType() {
        }

        public LoanType(String value) {
            super(value);
        }

        public Date getIssueDate() {
            return issueDate;
        }

        public void setIssueDate(Date issueDate) {
            this.issueDate = issueDate;
        }

        public Date getRedemptionDate() {
            return redemptionDate;
        }

        public void setRedemptionDate(Date redemptionDate) {
            this.redemptionDate = redemptionDate;
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

    @XmlRootElement(name = "MoneyMarket")
    @XmlType(propOrder = {"issueDate", "repoCollateralSecurityTypeType", "repurchaseTerm", "repurchaseRate",
        "redemptionDate","contractMultiplier", "couponRate"})
    @XmlAccessorType(XmlAccessType.NONE)
    public static class MoneyMarketType extends SingleStringAttrSimpleType {

        @XmlElement(name = "IssueDate")
        @XmlJavaTypeAdapter(FixDateAdapter.class)
        private Date issueDate;

        @XmlElement(name = "RepoCollateralSecurityType")
        private RepoCollateralSecurityTypeType repoCollateralSecurityTypeType;

        @XmlElement(name = "RepurchaseTerm")
        private Integer repurchaseTerm;

        @XmlElement(name = "RepurchaseRate")
        private Double repurchaseRate;

        @XmlElement(name = "RedemptionDate")
        @XmlJavaTypeAdapter(FixDateAdapter.class)
        private Date redemptionDate;

        @XmlElement(name = "ContractMultiplier")
        private Double contractMultiplier;

        @XmlElement(name = "CouponRate")
        private Double couponRate;

        public MoneyMarketType() {
        }

        public MoneyMarketType(String value) {
            super(value);
        }

        public Date getIssueDate() {
            return issueDate;
        }

        public void setIssueDate(Date issueDate) {
            this.issueDate = issueDate;
        }

        public RepoCollateralSecurityTypeType getRepoCollateralSecurityTypeType() {
            return repoCollateralSecurityTypeType;
        }

        public void setRepoCollateralSecurityTypeType(RepoCollateralSecurityTypeType repoCollateralSecurityTypeType) {
            this.repoCollateralSecurityTypeType = repoCollateralSecurityTypeType;
        }

        public String getRepoCollateralSecurityType() {
            if (repoCollateralSecurityTypeType != null) {
                return repoCollateralSecurityTypeType.getValue();
            } else {
                return null;
            }
        }

        public void setRepoCollateralSecurityType(String repoCollateralSecurityType) {
            if (repoCollateralSecurityTypeType == null) {
                repoCollateralSecurityTypeType = new RepoCollateralSecurityTypeType();
            }
            repoCollateralSecurityTypeType.setValue(repoCollateralSecurityType);
        }

        public Integer getRepurchaseTerm() {
            return repurchaseTerm;
        }

        public void setRepurchaseTerm(Integer repurchaseTerm) {
            this.repurchaseTerm = repurchaseTerm;
        }

        public Double getRepurchaseRate() {
            return repurchaseRate;
        }

        public void setRepurchaseRate(Double repurchaseRate) {
            this.repurchaseRate = repurchaseRate;
        }

        public Date getRedemptionDate() {
            return redemptionDate;
        }

        public void setRedemptionDate(Date redemptionDate) {
            this.redemptionDate = redemptionDate;
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

    @XmlRootElement(name = "Mortgage")
    @XmlType(propOrder = {"issueDate", "factor", "redemptionDate", "contractMultiplier", "couponRate"})
    @XmlAccessorType(XmlAccessType.NONE)
    public static class MortgageType extends SingleStringAttrSimpleType {

        @XmlElement(name = "IssueDate")
        @XmlJavaTypeAdapter(FixDateAdapter.class)
        private Date issueDate;

        @XmlElement(name = "Factor")
        @XmlJavaTypeAdapter(FixNumberAdapter.class)
        private Double factor;

        @XmlElement(name = "RedemptionDate")
        @XmlJavaTypeAdapter(FixDateAdapter.class)
        private Date redemptionDate;

        @XmlElement(name = "ContractMultiplier")
        private Double contractMultiplier;

        @XmlElement(name = "CouponRate")
        private Double couponRate;

        public MortgageType() {
        }

        public MortgageType(String value) {
            super(value);
        }

        public Date getIssueDate() {
            return issueDate;
        }

        public void setIssueDate(Date issueDate) {
            this.issueDate = issueDate;
        }

        public Double getFactor() {
            return factor;
        }

        public void setFactor(Double factor) {
            this.factor = factor;
        }

        public Date getRedemptionDate() {
            return redemptionDate;
        }

        public void setRedemptionDate(Date redemptionDate) {
            this.redemptionDate = redemptionDate;
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

    @XmlRootElement(name = "Municipal")
    @XmlType(propOrder = {"issueDate", "redemptionDate", "contractMultiplier", "couponRate"})
    @XmlAccessorType(XmlAccessType.NONE)
    public static class MunicipalType extends SingleStringAttrSimpleType {

        @XmlElement(name = "IssueDate")
        @XmlJavaTypeAdapter(FixDateAdapter.class)
        private Date issueDate;

        @XmlElement(name = "RedemptionDate")
        @XmlJavaTypeAdapter(FixDateAdapter.class)
        private Date redemptionDate;

        @XmlElement(name = "ContractMultiplier")
        private Double contractMultiplier;

        @XmlElement(name = "CouponRate")
        private Double couponRate;

        public MunicipalType() {
        }

        public MunicipalType(String value) {
            super(value);
        }

        public Date getIssueDate() {
            return issueDate;
        }

        public void setIssueDate(Date issueDate) {
            this.issueDate = issueDate;
        }

        public Date getRedemptionDate() {
            return redemptionDate;
        }

        public void setRedemptionDate(Date redemptionDate) {
            this.redemptionDate = redemptionDate;
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

    @XmlRootElement(name = "RepoCollateralSecurityType")
    @XmlType
    @XmlAccessorType(XmlAccessType.NONE)
    public static class RepoCollateralSecurityTypeType extends SingleStringAttrSimpleType {
    }

    // </editor-fold>
}
