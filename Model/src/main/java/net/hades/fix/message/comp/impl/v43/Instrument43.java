/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * Instrument43.java
 *
 * $Id: Instrument43.java,v 1.11 2011-04-14 23:45:01 vrotaru Exp $
 */
package net.hades.fix.message.comp.impl.v43;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.comp.Instrument;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.group.SecurityAltIDGroup;
import net.hades.fix.message.struct.Tag;
import net.hades.fix.message.type.SessionRejectReason;
import net.hades.fix.message.type.TagNum;
import net.hades.fix.message.util.MsgUtil;
import net.hades.fix.message.xml.codec.jaxb.type.SingleStringAttrSimpleType;
import net.hades.fix.message.xml.codec.jaxb.type.v43.SecurityAltIDListType43;
import net.hades.fix.message.xml.codec.jaxb.type.v43.SecurityTypeType43;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import net.hades.fix.message.MsgSecureType;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.group.impl.v43.SecurityAltIDGroup43;
import net.hades.fix.message.type.Product;
import net.hades.fix.message.util.TagEncoder;
import net.hades.fix.message.xml.codec.jaxb.adapter.FixDateAdapter;

/**
 * FIX version 4.3 Instrument component data implementation.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.11 $
 * @created 28/10/2008, 8:43:40 PM
 */
@XmlRootElement(name="Instrument")
@XmlType(propOrder = {"symbol", "symbolSfx", "securityID", "idSourceType", "securityAltIDListType",
    "productType", "cfiCode", "securityTypeType", "maturityMonthYear", "maturityDate", "instrRegistry",
    "countryOfIssue", "stateOrProvinceOfIssue", "localeOfIssue", "securityExchType", "issuer",
    "encodedIssuerGroupType", "securityDesc", "encodedSecDescGroupType"})
@XmlAccessorType(XmlAccessType.NONE)
public class Instrument43 extends Instrument {

    // <editor-fold defaultstate="collapsed" desc="Constants">
    
    private static final long serialVersionUID = 1L;
    
    protected static final Set<Integer> START_COMP_TAGS;

    protected static final Set<Integer> ALL_TAGS;

    protected static final Set<Integer> SEC_ALT_ID_GROUP_TAGS = new SecurityAltIDGroup43().getFragmentAllTags();

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">

    static {
        ALL_TAGS = new HashSet<Integer>(TAGS);
        ALL_TAGS.addAll(START_DATA_TAGS);
        ALL_TAGS.addAll(SEC_ALT_ID_GROUP_TAGS);
        START_COMP_TAGS = new HashSet<Integer>(SEC_ALT_ID_GROUP_TAGS);
    }
    
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">
    
    protected Set<Integer> STANDARD_SECURED_TAGS = ALL_TAGS;

    @XmlElement(name = "SecurityIDSource")
    private IDSourceType idSourceType;

    @XmlElement(name = "SecurityType")
    private SecurityTypeType43 securityTypeType;

    @XmlElement(name = "SecurityAltIDList")
    private SecurityAltIDListType43 securityAltIDListType;

    @XmlElement(name = "Product")
    private ProductType productType;

    @XmlElement(name = "SecurityExchange")
    private SecurityExchType securityExchType;

    @XmlElement(name = "EncodedIssuerGroup")
    private EncodedIssuerGroupType encodedIssuerGroupType;

    @XmlElement(name = "EncodedSecurityDescGroup")
    private EncodedSecDescGroupType encodedSecDescGroupType;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">
    
    public Instrument43() {
        super();
        SECURED_TAGS = STANDARD_SECURED_TAGS;
    }
    
    public Instrument43(FragmentContext context) {
        super(context);
        SECURED_TAGS = STANDARD_SECURED_TAGS;
    }
    
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Public Methods">

    @Override
    public Set<Integer> getFragmentAllTags() {
        return ALL_TAGS;
    }

    // ACCESSOR METHODS
    //////////////////////////////////////////

    @XmlElement(name = "CFICode")
    @Override
    public String getCfiCode() {
        return cfiCode;
    }

    @Override
    public void setCfiCode(String cfiCode) {
        this.cfiCode = cfiCode;
    }

    @Override
    public Double getContractMultiplier() {
        if (securityTypeType != null) {
            if (securityTypeType.getElement() instanceof SecurityTypeType43.AgencyType) {
                return ((SecurityTypeType43.AgencyType) securityTypeType.getElement()).getContractMultiplier();
            } else if (securityTypeType.getElement() instanceof SecurityTypeType43.CorporateType) {
                return ((SecurityTypeType43.CorporateType) securityTypeType.getElement()).getContractMultiplier();
            } else if (securityTypeType.getElement() instanceof SecurityTypeType43.GovernmentType) {
                return ((SecurityTypeType43.GovernmentType) securityTypeType.getElement()).getContractMultiplier();
            } else if (securityTypeType.getElement() instanceof SecurityTypeType43.LoanType) {
                return ((SecurityTypeType43.LoanType) securityTypeType.getElement()).getContractMultiplier();
            } else if (securityTypeType.getElement() instanceof SecurityTypeType43.MoneyMarketType) {
                return ((SecurityTypeType43.MoneyMarketType) securityTypeType.getElement()).getContractMultiplier();
            } else if (securityTypeType.getElement() instanceof SecurityTypeType43.MortgageType) {
                return ((SecurityTypeType43.MortgageType) securityTypeType.getElement()).getContractMultiplier();
            } else if (securityTypeType.getElement() instanceof SecurityTypeType43.MunicipalType) {
                return ((SecurityTypeType43.MunicipalType) securityTypeType.getElement()).getContractMultiplier();
            } else {
                return contractMultiplier;
            }
        } else {
            return contractMultiplier;
        }
    }

    @Override
    public void setContractMultiplier(Double contractMultiplier) {
        this.contractMultiplier = contractMultiplier;
        if (securityTypeType != null && securityTypeType.getElement() instanceof SecurityTypeType43.AgencyType) {
            ((SecurityTypeType43.AgencyType) securityTypeType.getElement()).setContractMultiplier(contractMultiplier);
        } else if (securityTypeType != null && securityTypeType.getElement() instanceof SecurityTypeType43.CorporateType) {
            ((SecurityTypeType43.CorporateType) securityTypeType.getElement()).setContractMultiplier(contractMultiplier);
        } else if (securityTypeType != null && securityTypeType.getElement() instanceof SecurityTypeType43.GovernmentType) {
            ((SecurityTypeType43.GovernmentType) securityTypeType.getElement()).setContractMultiplier(contractMultiplier);
        } else if (securityTypeType != null && securityTypeType.getElement() instanceof SecurityTypeType43.LoanType) {
            ((SecurityTypeType43.LoanType) securityTypeType.getElement()).setContractMultiplier(contractMultiplier);
        } else if (securityTypeType != null && securityTypeType.getElement() instanceof SecurityTypeType43.MoneyMarketType) {
            ((SecurityTypeType43.MoneyMarketType) securityTypeType.getElement()).setContractMultiplier(contractMultiplier);
        } else if (securityTypeType != null && securityTypeType.getElement() instanceof SecurityTypeType43.MortgageType) {
            ((SecurityTypeType43.MortgageType) securityTypeType.getElement()).setContractMultiplier(contractMultiplier);
        } else if (securityTypeType != null && securityTypeType.getElement() instanceof SecurityTypeType43.MunicipalType) {
            ((SecurityTypeType43.MunicipalType) securityTypeType.getElement()).setContractMultiplier(contractMultiplier);
        }
    }

    @XmlElement(name = "CountryOfIssue")
    @Override
    public String getCountryOfIssue() {
        return countryOfIssue;
    }

    @Override
    public void setCountryOfIssue(String countryOfIssue) {
        this.countryOfIssue = countryOfIssue;
    }

    @Override
    public Date getCouponPaymentDate() {
        if (securityTypeType != null) {
            if (securityTypeType.getElement() instanceof SecurityTypeType43.CorporateType) {
                return ((SecurityTypeType43.CorporateType) securityTypeType.getElement()).getCouponPaymentDate();
            } else {
                return couponPaymentDate;
            }
        } else {
            return couponPaymentDate;
        }
    }

    @Override
    public void setCouponPaymentDate(Date couponPaymentDate) {
        this.couponPaymentDate = couponPaymentDate;
        if (securityTypeType != null && securityTypeType.getElement() instanceof SecurityTypeType43.CorporateType) {
            ((SecurityTypeType43.CorporateType) securityTypeType.getElement()).setCouponPaymentDate(couponPaymentDate);
        }
    }

    @Override
    public Double getCouponRate() {
        if (securityTypeType != null) {
            if (securityTypeType.getElement() instanceof SecurityTypeType43.AgencyType) {
                return ((SecurityTypeType43.AgencyType) securityTypeType.getElement()).getCouponRate();
            } else if (securityTypeType.getElement() instanceof SecurityTypeType43.CorporateType) {
                return ((SecurityTypeType43.CorporateType) securityTypeType.getElement()).getCouponRate();
            } else if (securityTypeType.getElement() instanceof SecurityTypeType43.GovernmentType) {
                return ((SecurityTypeType43.GovernmentType) securityTypeType.getElement()).getCouponRate();
            } else if (securityTypeType.getElement() instanceof SecurityTypeType43.LoanType) {
                return ((SecurityTypeType43.LoanType) securityTypeType.getElement()).getCouponRate();
            } else if (securityTypeType.getElement() instanceof SecurityTypeType43.MoneyMarketType) {
                return ((SecurityTypeType43.MoneyMarketType) securityTypeType.getElement()).getCouponRate();
            } else if (securityTypeType.getElement() instanceof SecurityTypeType43.MortgageType) {
                return ((SecurityTypeType43.MortgageType) securityTypeType.getElement()).getCouponRate();
            } else if (securityTypeType.getElement() instanceof SecurityTypeType43.MunicipalType) {
                return ((SecurityTypeType43.MunicipalType) securityTypeType.getElement()).getCouponRate();
            } else {
                return couponRate;
            }
        } else {
            return couponRate;
        }

    }

    @Override
    public void setCouponRate(Double couponRate) {
        this.couponRate = couponRate;
        if (securityTypeType != null && securityTypeType.getElement() instanceof SecurityTypeType43.AgencyType) {
            ((SecurityTypeType43.AgencyType) securityTypeType.getElement()).setCouponRate(couponRate);
        } else if (securityTypeType != null && securityTypeType.getElement() instanceof SecurityTypeType43.CorporateType) {
            ((SecurityTypeType43.CorporateType) securityTypeType.getElement()).setCouponRate(couponRate);
        } else if (securityTypeType != null && securityTypeType.getElement() instanceof SecurityTypeType43.GovernmentType) {
            ((SecurityTypeType43.GovernmentType) securityTypeType.getElement()).setCouponRate(couponRate);
        } else if (securityTypeType != null && securityTypeType.getElement() instanceof SecurityTypeType43.LoanType) {
            ((SecurityTypeType43.LoanType) securityTypeType.getElement()).setCouponRate(couponRate);
        } else if (securityTypeType != null && securityTypeType.getElement() instanceof SecurityTypeType43.MoneyMarketType) {
            ((SecurityTypeType43.MoneyMarketType) securityTypeType.getElement()).setCouponRate(couponRate);
        } else if (securityTypeType != null && securityTypeType.getElement() instanceof SecurityTypeType43.MortgageType) {
            ((SecurityTypeType43.MortgageType) securityTypeType.getElement()).setCouponRate(couponRate);
        } else if (securityTypeType != null && securityTypeType.getElement() instanceof SecurityTypeType43.MunicipalType) {
            ((SecurityTypeType43.MunicipalType) securityTypeType.getElement()).setCouponRate(couponRate);
        }
    }

    @Override
    public String getCreditRating() {
        return creditRating;
    }

    @Override
    public void setCreditRating(String creditRating) {
        this.creditRating = creditRating;
    }

    @Override
    public byte[] getEncodedIssuer() {
        if (encodedIssuerGroupType != null && encodedIssuerGroupType.getEncodedIssuerLen() != null) {
            return encodedIssuerGroupType.getEncodedIssuer();
        } else {
            return encodedIssuer;
        }
    }

    @Override
    public void setEncodedIssuer(byte[] encodedIssuer) {
        this.encodedIssuer = encodedIssuer;
        if (encodedIssuerLen == null) {
            encodedIssuerLen = new Integer(encodedIssuer.length);
        }
        if (encodedIssuerGroupType == null) {
            encodedIssuerGroupType = new EncodedIssuerGroupType();
        }
        encodedIssuerGroupType.setEncodedIssuer(encodedIssuer);
    }

    @Override
    public Integer getEncodedIssuerLen() {
        if (encodedIssuerGroupType != null && encodedIssuerGroupType.getEncodedIssuerLen() != null) {
            return encodedIssuerGroupType.getEncodedIssuerLen();
        } else {
            return encodedIssuerLen;
        }
    }

    @Override
    public void setEncodedIssuerLen(Integer encodedIssuerLen) {
        this.encodedIssuerLen = encodedIssuerLen;
        if (encodedIssuerGroupType == null) {
            encodedIssuerGroupType = new EncodedIssuerGroupType();
        }
        encodedIssuerGroupType.setEncodedIssuerLen(encodedIssuerLen);
    }

    @Override
    public byte[] getEncodedSecurityDesc() {
        if (encodedSecDescGroupType != null && encodedSecDescGroupType.getEncodedSecurityDescLen() != null) {
            return encodedSecDescGroupType.getEncodedSecurityDesc();
        } else {
            return encodedSecurityDesc;
        }
    }

    @Override
    public void setEncodedSecurityDesc(byte[] encodedSecurityDesc) {
        this.encodedSecurityDesc = encodedSecurityDesc;
        if (encodedSecurityDescLen == null) {
            encodedSecurityDescLen = new Integer(encodedSecurityDesc.length);
        }
        if (encodedSecDescGroupType == null) {
            encodedSecDescGroupType = new EncodedSecDescGroupType();
        }
        encodedSecDescGroupType.setEncodedSecurityDesc(encodedSecurityDesc);
    }

    @Override
    public Integer getEncodedSecurityDescLen() {
        if (encodedSecDescGroupType != null && encodedSecDescGroupType.getEncodedSecurityDescLen() != null) {
            return encodedSecDescGroupType.getEncodedSecurityDescLen();
        } else {
            return encodedSecurityDescLen;
        }
    }

    @Override
    public void setEncodedSecurityDescLen(Integer encodedSecurityDescLen) {
        this.encodedSecurityDescLen = encodedSecurityDescLen;
        if (encodedSecDescGroupType == null) {
            encodedSecDescGroupType = new EncodedSecDescGroupType();
        }
        encodedSecDescGroupType.setEncodedSecurityDescLen(encodedSecurityDescLen);
    }

    @Override
    public Double getFactor() {
        if (securityTypeType != null) {
            if (securityTypeType.getElement() instanceof SecurityTypeType43.GovernmentType) {
                return ((SecurityTypeType43.GovernmentType) securityTypeType.getElement()).getFactor();
            } else if (securityTypeType.getElement() instanceof SecurityTypeType43.MortgageType) {
                return ((SecurityTypeType43.MortgageType) securityTypeType.getElement()).getFactor();
            } else {
                return factor;
            }
        } else {
            return factor;
        }
    }

    @Override
    public void setFactor(Double factor) {
        this.factor = factor;
        if (securityTypeType != null && securityTypeType.getElement() instanceof SecurityTypeType43.GovernmentType) {
            ((SecurityTypeType43.GovernmentType) securityTypeType.getElement()).setFactor(factor);
        } else if (securityTypeType != null && securityTypeType.getElement() instanceof SecurityTypeType43.MortgageType) {
            ((SecurityTypeType43.MortgageType) securityTypeType.getElement()).setFactor(factor);
        }
    }

    @XmlElement(name = "InstrRegistry")
    @Override
    public String getInstrRegistry() {
        return instrRegistry;
    }

    @Override
    public void setInstrRegistry(String instrRegistry) {
        this.instrRegistry = instrRegistry;
    }

    @Override
    public Date getIssueDate() {
        if (securityTypeType != null) {
            if (securityTypeType.getElement() instanceof SecurityTypeType43.AgencyType) {
                return ((SecurityTypeType43.AgencyType) securityTypeType.getElement()).getIssueDate();
            } else if (securityTypeType.getElement() instanceof SecurityTypeType43.CorporateType) {
                return ((SecurityTypeType43.CorporateType) securityTypeType.getElement()).getIssueDate();
            } else if (securityTypeType.getElement() instanceof SecurityTypeType43.GovernmentType) {
                return ((SecurityTypeType43.GovernmentType) securityTypeType.getElement()).getIssueDate();
            } else if (securityTypeType.getElement() instanceof SecurityTypeType43.LoanType) {
                return ((SecurityTypeType43.LoanType) securityTypeType.getElement()).getIssueDate();
            } else if (securityTypeType.getElement() instanceof SecurityTypeType43.MoneyMarketType) {
                return ((SecurityTypeType43.MoneyMarketType) securityTypeType.getElement()).getIssueDate();
            } else if (securityTypeType.getElement() instanceof SecurityTypeType43.MortgageType) {
                return ((SecurityTypeType43.MortgageType) securityTypeType.getElement()).getIssueDate();
            } else if (securityTypeType.getElement() instanceof SecurityTypeType43.MunicipalType) {
                return ((SecurityTypeType43.MunicipalType) securityTypeType.getElement()).getIssueDate();
            } else {
                return issueDate;
            }
        } else {
            return issueDate;
        }
    }

    @Override
    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
        if (securityTypeType != null && securityTypeType.getElement() instanceof SecurityTypeType43.AgencyType) {
            ((SecurityTypeType43.AgencyType) securityTypeType.getElement()).setIssueDate(issueDate);
        } else if (securityTypeType != null && securityTypeType.getElement() instanceof SecurityTypeType43.CorporateType) {
            ((SecurityTypeType43.CorporateType) securityTypeType.getElement()).setIssueDate(issueDate);
        } else if (securityTypeType != null && securityTypeType.getElement() instanceof SecurityTypeType43.GovernmentType) {
            ((SecurityTypeType43.GovernmentType) securityTypeType.getElement()).setIssueDate(issueDate);
        } else if (securityTypeType != null && securityTypeType.getElement() instanceof SecurityTypeType43.LoanType) {
            ((SecurityTypeType43.LoanType) securityTypeType.getElement()).setIssueDate(issueDate);
        } else if (securityTypeType != null && securityTypeType.getElement() instanceof SecurityTypeType43.MoneyMarketType) {
            ((SecurityTypeType43.MoneyMarketType) securityTypeType.getElement()).setIssueDate(issueDate);
        } else if (securityTypeType != null && securityTypeType.getElement() instanceof SecurityTypeType43.MortgageType) {
            ((SecurityTypeType43.MortgageType) securityTypeType.getElement()).setIssueDate(issueDate);
        } else if (securityTypeType != null && securityTypeType.getElement() instanceof SecurityTypeType43.MunicipalType) {
            ((SecurityTypeType43.MunicipalType) securityTypeType.getElement()).setIssueDate(issueDate);
        }
    }

    @XmlElement(name = "Issuer")
    @Override
    public String getIssuer() {
        return issuer;
    }

    @Override
    public void setIssuer(String issuer) {
        this.issuer = issuer;
    }

    @XmlElement(name = "LocaleOfIssue")
    @Override
    public String getLocaleOfIssue() {
        return localeOfIssue;
    }

    @Override
    public void setLocaleOfIssue(String localeOfIssue) {
        this.localeOfIssue = localeOfIssue;
    }

    @XmlElement(name = "MaturityDate")
    @XmlJavaTypeAdapter(FixDateAdapter.class)
    @Override
    public Date getMaturityDate() {
        return maturityDate;
    }

    @Override
    public void setMaturityDate(Date maturityDate) {
        this.maturityDate = maturityDate;
    }

    @XmlElement(name = "MaturityMonthYear")
    @Override
    public String getMaturityMonthYear() {
        return maturityMonthYear;
    }

    @Override
    public void setMaturityMonthYear(String maturityMonthYear) {
        this.maturityMonthYear = maturityMonthYear;
    }

    @Override
    public Character getOptAttribute() {
        if (securityTypeType != null) {
            if (securityTypeType.getElement() instanceof SecurityTypeType43.OptionType) {
                return ((SecurityTypeType43.OptionType) securityTypeType.getElement()).getOptAttribute();
            } else {
                return optAttribute;
            }
        } else {
            return optAttribute;
        }
    }

    @Override
    public void setOptAttribute(Character optAttribute) {
        this.optAttribute = optAttribute;
        if (securityTypeType != null && securityTypeType.getElement() instanceof SecurityTypeType43.OptionType) {
            ((SecurityTypeType43.OptionType) securityTypeType.getElement()).setOptAttribute(optAttribute);
        }
    }

    @Override
    public Product getProduct() {
        return product;
    }

    @Override
    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public Date getRedemptionDate() {
        if (securityTypeType != null) {
            if (securityTypeType.getElement() instanceof SecurityTypeType43.AgencyType) {
                return ((SecurityTypeType43.AgencyType) securityTypeType.getElement()).getRedemptionDate();
            } else if (securityTypeType.getElement() instanceof SecurityTypeType43.CorporateType) {
                return ((SecurityTypeType43.CorporateType) securityTypeType.getElement()).getRedemptionDate();
            } else if (securityTypeType.getElement() instanceof SecurityTypeType43.GovernmentType) {
                return ((SecurityTypeType43.GovernmentType) securityTypeType.getElement()).getRedemptionDate();
            } else if (securityTypeType.getElement() instanceof SecurityTypeType43.LoanType) {
                return ((SecurityTypeType43.LoanType) securityTypeType.getElement()).getRedemptionDate();
            } else if (securityTypeType.getElement() instanceof SecurityTypeType43.MoneyMarketType) {
                return ((SecurityTypeType43.MoneyMarketType) securityTypeType.getElement()).getRedemptionDate();
            } else if (securityTypeType.getElement() instanceof SecurityTypeType43.MortgageType) {
                return ((SecurityTypeType43.MortgageType) securityTypeType.getElement()).getRedemptionDate();
            } else if (securityTypeType.getElement() instanceof SecurityTypeType43.MunicipalType) {
                return ((SecurityTypeType43.MunicipalType) securityTypeType.getElement()).getRedemptionDate();
            } else {
                return redemptionDate;
            }
        } else {
            return redemptionDate;
        }
    }

    @Override
    public void setRedemptionDate(Date redemptionDate) {
        this.redemptionDate = redemptionDate;
        if (securityTypeType != null && securityTypeType.getElement() instanceof SecurityTypeType43.AgencyType) {
            ((SecurityTypeType43.AgencyType) securityTypeType.getElement()).setRedemptionDate(redemptionDate);
        } else if (securityTypeType != null && securityTypeType.getElement() instanceof SecurityTypeType43.CorporateType) {
            ((SecurityTypeType43.CorporateType) securityTypeType.getElement()).setRedemptionDate(redemptionDate);
        } else if (securityTypeType != null && securityTypeType.getElement() instanceof SecurityTypeType43.GovernmentType) {
            ((SecurityTypeType43.GovernmentType) securityTypeType.getElement()).setRedemptionDate(redemptionDate);
        } else if (securityTypeType != null && securityTypeType.getElement() instanceof SecurityTypeType43.LoanType) {
            ((SecurityTypeType43.LoanType) securityTypeType.getElement()).setRedemptionDate(redemptionDate);
        } else if (securityTypeType != null && securityTypeType.getElement() instanceof SecurityTypeType43.MoneyMarketType) {
            ((SecurityTypeType43.MoneyMarketType) securityTypeType.getElement()).setRedemptionDate(redemptionDate);
        } else if (securityTypeType != null && securityTypeType.getElement() instanceof SecurityTypeType43.MortgageType) {
            ((SecurityTypeType43.MortgageType) securityTypeType.getElement()).setRedemptionDate(redemptionDate);
        } else if (securityTypeType != null && securityTypeType.getElement() instanceof SecurityTypeType43.MunicipalType) {
            ((SecurityTypeType43.MunicipalType) securityTypeType.getElement()).setRedemptionDate(redemptionDate);
        }
    }

    @Override
    public String getRepoCollateralSecurityType() {
        if (securityTypeType != null) {
            if (securityTypeType.getElement() instanceof SecurityTypeType43.MoneyMarketType) {
                if (((SecurityTypeType43.MoneyMarketType) securityTypeType.getElement()).getRepoCollateralSecurityTypeType() != null) {
                    return ((SecurityTypeType43.MoneyMarketType) securityTypeType.getElement()).getRepoCollateralSecurityType();
                } else {
                    return repoCollateralSecurityType;
                }
            } else {
                return repoCollateralSecurityType;
            }
        } else {
            return repoCollateralSecurityType;
        }
    }

    @Override
    public void setRepoCollateralSecurityType(String repoCollateralSecurityType) {
        this.repoCollateralSecurityType = repoCollateralSecurityType;
        if (securityTypeType != null && securityTypeType.getElement() instanceof SecurityTypeType43.MoneyMarketType) {
            ((SecurityTypeType43.MoneyMarketType) securityTypeType.getElement()).setRepoCollateralSecurityType(repoCollateralSecurityType);
        }
    }

    @Override
    public Double getRepurchaseRate() {
        if (securityTypeType != null) {
            if (securityTypeType.getElement() instanceof SecurityTypeType43.MoneyMarketType) {
                return ((SecurityTypeType43.MoneyMarketType) securityTypeType.getElement()).getRepurchaseRate();
            } else {
                return repurchaseRate;
            }
        } else {
            return repurchaseRate;
        }
    }

    @Override
    public void setRepurchaseRate(Double repurchaseRate) {
        this.repurchaseRate = repurchaseRate;
        if (securityTypeType != null && securityTypeType.getElement() instanceof SecurityTypeType43.MoneyMarketType) {
            ((SecurityTypeType43.MoneyMarketType) securityTypeType.getElement()).setRepurchaseRate(repurchaseRate);
        }
    }

    @Override
    public Integer getRepurchaseTerm() {
         if (securityTypeType != null) {
            if (securityTypeType.getElement() instanceof SecurityTypeType43.MoneyMarketType) {
                return ((SecurityTypeType43.MoneyMarketType) securityTypeType.getElement()).getRepurchaseTerm();
            } else {
                return repurchaseTerm;
            }
        } else {
            return repurchaseTerm;
        }
    }

    @Override
    public void setRepurchaseTerm(Integer repurchaseTerm) {
        this.repurchaseTerm = repurchaseTerm;
        if (securityTypeType != null && securityTypeType.getElement() instanceof SecurityTypeType43.MoneyMarketType) {
            ((SecurityTypeType43.MoneyMarketType) securityTypeType.getElement()).setRepurchaseTerm(repurchaseTerm);
        }
    }

    @Override
    public Integer getNoSecurityAltID() {
        if (securityAltIDListType != null && securityAltIDListType.getNoSecurityAltID() != null) {
            return securityAltIDListType.getNoSecurityAltID();
        } else {
            return noSecurityAltID;
        }
    }

    @Override
    public void setNoSecurityAltID(Integer noSecurityAltID) {
        this.noSecurityAltID = noSecurityAltID;
        if (noSecurityAltID != null) {
            securityAltIDGroups = new SecurityAltIDGroup[noSecurityAltID.intValue()];
            for (int i = 0; i < securityAltIDGroups.length; i++) {
                securityAltIDGroups[i] = new SecurityAltIDGroup43(new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired));
            }
            populateSecurityAltIDListType(noSecurityAltID, securityAltIDGroups);
        }
    }

    @Override
    public SecurityAltIDGroup[] getSecurityAltIDGroups() {
        if (securityAltIDListType != null && securityAltIDListType.getNoSecurityAltID() != null) {
            return securityAltIDListType.getSecurityAltIDGroups();
        } else {
            return securityAltIDGroups;
        }
    }

    @Override
    public SecurityAltIDGroup addSecurityAltIDGroup() {
        
        SecurityAltIDGroup group = new SecurityAltIDGroup43(new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired));
        List<SecurityAltIDGroup> groups = new ArrayList<SecurityAltIDGroup>();
        if (securityAltIDGroups != null && securityAltIDGroups.length > 0) {
            groups = new ArrayList<SecurityAltIDGroup>(Arrays.asList(securityAltIDGroups));
        }
        groups.add(group);
        securityAltIDGroups = groups.toArray(new SecurityAltIDGroup[groups.size()]);
        noSecurityAltID = new Integer(securityAltIDGroups.length);
        populateSecurityAltIDListType(noSecurityAltID, securityAltIDGroups);
        
        return group;
    }
    
    @Override
    public SecurityAltIDGroup deleteSecurityAltIDGroup(int index) {
        
        SecurityAltIDGroup result = null;

        if (securityAltIDGroups != null && securityAltIDGroups.length > 0 && securityAltIDGroups.length > index) {
            List<SecurityAltIDGroup> groups = new ArrayList<SecurityAltIDGroup>(Arrays.asList(securityAltIDGroups));
            result = groups.remove(index);
            securityAltIDGroups = groups.toArray(new SecurityAltIDGroup[groups.size()]);
            if (securityAltIDGroups.length > 0) {
                noSecurityAltID = new Integer(securityAltIDGroups.length);
            } else {
                securityAltIDGroups = null;
                noSecurityAltID = null;
            }
            populateSecurityAltIDListType(noSecurityAltID, securityAltIDGroups);
        }
        
        return result;
    }

    @Override
    public int clearSecurityAltIDGroups() {
        
        int result = 0;
        if (securityAltIDGroups != null && securityAltIDGroups.length > 0) {
            result = securityAltIDGroups.length;
            securityAltIDGroups = null;
            noSecurityAltID = null;
            populateSecurityAltIDListType(noSecurityAltID, securityAltIDGroups);
        }
        
        return result;
    }

    @XmlElement(name = "SecurityDesc")
    @Override
    public String getSecurityDesc() {
        return securityDesc;
    }

    @Override
    public void setSecurityDesc(String securityDesc) {
        this.securityDesc = securityDesc;
    }

    @Override
    public String getSecurityExchange() {
        if (securityExchType != null && securityExchType.getValue() != null) {
            return securityExchType.getValue();
        } else {
            return securityExchange;
        }
    }

    @Override
    public void setSecurityExchange(String securityExchange) {
        this.securityExchange = securityExchange;
        if (securityExchType == null) {
            securityExchType = new SecurityExchType();
        }
        securityExchType.setValue(securityExchange);
    }

    @XmlElement(name = "SecurityID")
    @Override
    public String getSecurityID() {
        return securityID;
    }

    @Override
    public void setSecurityID(String securityID) {
        this.securityID = securityID;
    }

    @Override
    public String getSecurityIDSource() {
        if (idSourceType != null && idSourceType.getValue() != null) {
            return idSourceType.getValue();
        } else {
            return securityIDSource;
        }
    }

    @Override
    public void setSecurityIDSource(String securityIDSource) {
        this.securityIDSource = securityIDSource;
        if (idSourceType == null) {
            idSourceType = new IDSourceType();
        }
        idSourceType.setValue(securityIDSource);
    }

    @Override
    public String getSecurityType() {
        if (securityTypeType != null && securityTypeType.getValue() != null) {
            return securityTypeType.getValue();
        } else {
            return securityType;
        }
    }

    @Override
    public void setSecurityType(String securityType) {
        this.securityType = securityType;
        if (securityTypeType == null) {
            securityTypeType = new SecurityTypeType43();
        }
        securityTypeType.setValue(securityType);
    }

    @XmlElement(name = "StateOrProvinceOfIssue")
    @Override
    public String getStateOrProvinceOfIssue() {
        return stateOrProvinceOfIssue;
    }

    @Override
    public void setStateOrProvinceOfIssue(String stateOrProvinceOfIssue) {
        this.stateOrProvinceOfIssue = stateOrProvinceOfIssue;
    }

    @Override
    public Double getStrikePrice() {
        if (securityTypeType != null) {
            if (securityTypeType.getElement() instanceof SecurityTypeType43.OptionType) {
                return ((SecurityTypeType43.OptionType) securityTypeType.getElement()).getStrikePrice();
            } else {
                return strikePrice;
            }
        } else {
            return strikePrice;
        }
    }

    @Override
    public void setStrikePrice(Double strikePrice) {
        this.strikePrice = strikePrice;
        if (securityTypeType != null && securityTypeType.getElement() instanceof SecurityTypeType43.OptionType) {
            ((SecurityTypeType43.OptionType) securityTypeType.getElement()).setStrikePrice(strikePrice);
        }
    }

    @XmlElement(name = "Symbol", required = true)
    @Override
    public String getSymbol() {
        return symbol;
    }

    @Override
    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    @XmlElement(name = "SymbolSfx")
    @Override
    public String getSymbolSfx() {
        return symbolSfx;
    }

    @Override
    public void setSymbolSfx(String symbolSfx) {
        this.symbolSfx = symbolSfx;
    }

    public IDSourceType getIdSourceType() {
        return idSourceType;
    }

    public void setIdSourceType(IDSourceType idSourceType) {
        this.idSourceType = idSourceType;
    }

    public SecurityTypeType43 getSecurityTypeType() {
        return securityTypeType;
    }

    public void setSecurityTypeType(SecurityTypeType43 securityTypeType) {
        this.securityTypeType = securityTypeType;
    }

    public SecurityAltIDListType43 getSecurityAltIDListType() {
        return securityAltIDListType;
    }

    public void setSecurityAltIDListType(SecurityAltIDListType43 securityAltIDListType) {
        this.securityAltIDListType = securityAltIDListType;
    }

    public ProductType getProductType() {
        return productType;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
    }

    public SecurityExchType getSecurityExchType() {
        return securityExchType;
    }

    public void setSecurityExchType(SecurityExchType securityExchType) {
        this.securityExchType = securityExchType;
    }

    public EncodedIssuerGroupType getEncodedIssuerGroupType() {
        return encodedIssuerGroupType;
    }

    public void setEncodedIssuerGroupType(EncodedIssuerGroupType encodedIssuerGroupType) {
        this.encodedIssuerGroupType = encodedIssuerGroupType;
    }

    public EncodedSecDescGroupType getEncodedSecDescGroupType() {
        return encodedSecDescGroupType;
    }

    public void setEncodedSecDescGroupType(EncodedSecDescGroupType encodedSecDescGroupType) {
        this.encodedSecDescGroupType = encodedSecDescGroupType;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

    @Override
    protected byte[] encodeFragmentAll() throws TagNotPresentException, BadFormatMsgException {
        if (validateRequired) {
            validateRequiredTags();
        }
        byte[] result = new byte[0];
        ByteArrayOutputStream bao = new ByteArrayOutputStream();
        
        try {
            TagEncoder.encode(bao, TagNum.Symbol, symbol, sessionCharset);
            TagEncoder.encode(bao, TagNum.SymbolSfx, symbolSfx, sessionCharset);
            TagEncoder.encode(bao, TagNum.SecurityID, securityID, sessionCharset);
            TagEncoder.encode(bao, TagNum.SecurityIDSource, securityIDSource, sessionCharset);
            if (noSecurityAltID != null && noSecurityAltID.intValue() > 0) {
                TagEncoder.encode(bao, TagNum.NoSecurityAltID, noSecurityAltID);
                if (securityAltIDGroups != null && securityAltIDGroups.length == noSecurityAltID.intValue()) {
                    for (int i = 0; i < noSecurityAltID.intValue(); i++) {
                        if (securityAltIDGroups[i] != null) {
                            bao.write(securityAltIDGroups[i].encode(MsgSecureType.ALL_FIELDS));
                        }
                    }
                } else {
                    String error = "SecurityAltIDGroups field has been set but there is no data or the number of groups does not match.";
                    LOGGER.severe(error);
                    throw new BadFormatMsgException(SessionRejectReason.IncorrectCountForGroups, TagNum.NoSecurityAltID.getValue(), error);
                }
            }
            if (product != null) {
                TagEncoder.encode(bao, TagNum.Product, product.getValue());
            }
            TagEncoder.encode(bao, TagNum.CFICode, cfiCode, sessionCharset);
            TagEncoder.encode(bao, TagNum.SecurityType, securityType);
            TagEncoder.encode(bao, TagNum.MaturityMonthYear, maturityMonthYear, sessionCharset);
            TagEncoder.encodeDate(bao, TagNum.MaturityDate, maturityDate);
            TagEncoder.encodeUtcDate(bao, TagNum.CouponPaymentDate, couponPaymentDate);
            TagEncoder.encodeUtcDate(bao, TagNum.IssueDate, issueDate);
            TagEncoder.encode(bao, TagNum.RepoCollateralSecurityType, repoCollateralSecurityType);
            TagEncoder.encode(bao, TagNum.RepurchaseTerm, repurchaseTerm);
            TagEncoder.encode(bao, TagNum.RepurchaseRate, repurchaseRate);
            TagEncoder.encode(bao, TagNum.Factor, factor);
            TagEncoder.encode(bao, TagNum.CreditRating, creditRating, sessionCharset);
            TagEncoder.encode(bao, TagNum.InstrRegistry, instrRegistry, sessionCharset);
            TagEncoder.encode(bao, TagNum.CountryOfIssue, countryOfIssue, sessionCharset);
            TagEncoder.encode(bao, TagNum.StateOrProvinceOfIssue, stateOrProvinceOfIssue, sessionCharset);
            TagEncoder.encode(bao, TagNum.LocaleOfIssue, localeOfIssue, sessionCharset);
            TagEncoder.encodeUtcDate(bao, TagNum.RedemptionDate, redemptionDate);
            TagEncoder.encode(bao, TagNum.StrikePrice, strikePrice);
            TagEncoder.encode(bao, TagNum.OptAttribute, optAttribute);
            TagEncoder.encode(bao, TagNum.ContractMultiplier, contractMultiplier);
            TagEncoder.encode(bao, TagNum.CouponRate, couponRate);
            TagEncoder.encode(bao, TagNum.SecurityExchange, securityExchange, sessionCharset);
            TagEncoder.encode(bao, TagNum.Issuer, issuer, sessionCharset);
            if (encodedIssuerLen != null && encodedIssuerLen.intValue() > 0) {
                if (encodedIssuer != null && encodedIssuer.length > 0) {
                    encodedIssuerLen = new Integer(encodedIssuer.length);
                    TagEncoder.encode(bao, TagNum.EncodedIssuerLen, encodedIssuerLen);
                    TagEncoder.encode(bao, TagNum.EncodedIssuer, encodedIssuer);
                }
            }
            TagEncoder.encode(bao, TagNum.SecurityDesc, securityDesc, sessionCharset);
            if (encodedSecurityDescLen != null && encodedSecurityDescLen.intValue() > 0) {
                if (encodedSecurityDesc != null && encodedSecurityDesc.length > 0) {
                    encodedSecurityDescLen = new Integer(encodedSecurityDesc.length);
                    TagEncoder.encode(bao, TagNum.EncodedSecurityDescLen, encodedSecurityDescLen);
                    TagEncoder.encode(bao, TagNum.EncodedSecurityDesc, encodedSecurityDesc);
                }
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
        byte[] result = new byte[0];
        ByteArrayOutputStream bao = new ByteArrayOutputStream();
        
        try {
            if (MsgUtil.isTagInList(TagNum.Symbol, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.Symbol, symbol, sessionCharset);
            }
            if (MsgUtil.isTagInList(TagNum.SymbolSfx, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.SymbolSfx, symbolSfx, sessionCharset);
            }
            if (MsgUtil.isTagInList(TagNum.SecurityID, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.SecurityID, securityID, sessionCharset);
            }
            if (MsgUtil.isTagInList(TagNum.SecurityIDSource, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.SecurityIDSource, securityIDSource, sessionCharset);
            }
            if (noSecurityAltID != null && noSecurityAltID.intValue() > 0 && MsgUtil.isTagInList(TagNum.NoSecurityAltID, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.NoSecurityAltID, noSecurityAltID);
                if (securityAltIDGroups != null && securityAltIDGroups.length == noSecurityAltID.intValue()) {
                    for (int i = 0; i < noSecurityAltID.intValue(); i++) {
                        if (securityAltIDGroups[i] != null) {
                            bao.write(securityAltIDGroups[i].encode(getMsgSecureTypeForFlag(secured)));
                        }
                    }
                } else {
                    String error = "SecurityAltIDGroups field has been set but there is no data or the number of groups does not match.";
                    LOGGER.severe(error);
                    throw new BadFormatMsgException(SessionRejectReason.IncorrectCountForGroups, TagNum.NoSecurityAltID.getValue(), error);
                }
            }
            if (product != null && MsgUtil.isTagInList(TagNum.Product, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.Product, product.getValue());
            }
            TagEncoder.encode(bao, TagNum.CFICode, cfiCode, sessionCharset);
            if (MsgUtil.isTagInList(TagNum.SecurityType, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.SecurityType, securityType);
            }
            if (MsgUtil.isTagInList(TagNum.MaturityMonthYear, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.MaturityMonthYear, maturityMonthYear, sessionCharset);
            }
            if (MsgUtil.isTagInList(TagNum.MaturityDate, SECURED_TAGS, secured)) {
                TagEncoder.encodeDate(bao, TagNum.MaturityDate, maturityDate);
            }
            if (MsgUtil.isTagInList(TagNum.CouponPaymentDate, SECURED_TAGS, secured)) {
                TagEncoder.encodeUtcDate(bao, TagNum.CouponPaymentDate, couponPaymentDate);
            }
            if (MsgUtil.isTagInList(TagNum.IssueDate, SECURED_TAGS, secured)) {
                TagEncoder.encodeUtcDate(bao, TagNum.IssueDate, issueDate);
            }
            if (MsgUtil.isTagInList(TagNum.RepoCollateralSecurityType, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.RepoCollateralSecurityType, repoCollateralSecurityType);
            }
            if (MsgUtil.isTagInList(TagNum.RepurchaseTerm, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.RepurchaseTerm, repurchaseTerm);
            }
            if (MsgUtil.isTagInList(TagNum.RepurchaseRate, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.RepurchaseRate, repurchaseRate);
            }
            if (MsgUtil.isTagInList(TagNum.Factor, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.Factor, factor);
            }
            if (MsgUtil.isTagInList(TagNum.CreditRating, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.CreditRating, creditRating, sessionCharset);
            }
            if (MsgUtil.isTagInList(TagNum.InstrRegistry, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.InstrRegistry, instrRegistry, sessionCharset);
            }
            if (MsgUtil.isTagInList(TagNum.CountryOfIssue, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.CountryOfIssue, countryOfIssue, sessionCharset);
            }
            if (MsgUtil.isTagInList(TagNum.StateOrProvinceOfIssue, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.StateOrProvinceOfIssue, stateOrProvinceOfIssue, sessionCharset);
            }
            if (MsgUtil.isTagInList(TagNum.LocaleOfIssue, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.LocaleOfIssue, localeOfIssue, sessionCharset);
            }
            if (MsgUtil.isTagInList(TagNum.RedemptionDate, SECURED_TAGS, secured)) {
                TagEncoder.encodeUtcDate(bao, TagNum.RedemptionDate, redemptionDate);
            }
            if (MsgUtil.isTagInList(TagNum.StrikePrice, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.StrikePrice, strikePrice);
            }
            if (MsgUtil.isTagInList(TagNum.OptAttribute, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.OptAttribute, optAttribute);
            }
            if (MsgUtil.isTagInList(TagNum.ContractMultiplier, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.ContractMultiplier, contractMultiplier);
            }
            if (MsgUtil.isTagInList(TagNum.CouponRate, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.CouponRate, couponRate);
            }
            if (MsgUtil.isTagInList(TagNum.SecurityExchange, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.SecurityExchange, securityExchange, sessionCharset);
            }
            if (MsgUtil.isTagInList(TagNum.Issuer, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.Issuer, issuer, sessionCharset);
            }
            if (encodedIssuerLen != null && encodedIssuerLen.intValue() > 0 && MsgUtil.isTagInList(TagNum.EncodedIssuerLen, SECURED_TAGS, secured)) {
                if (encodedIssuer != null && encodedIssuer.length > 0) {
                    encodedIssuerLen = new Integer(encodedIssuer.length);
                    TagEncoder.encode(bao, TagNum.EncodedIssuerLen, encodedIssuerLen);
                    TagEncoder.encode(bao, TagNum.EncodedIssuer, encodedIssuer);
                }
            }
            if (MsgUtil.isTagInList(TagNum.SecurityDesc, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.SecurityDesc, securityDesc, sessionCharset);
            }
            if (encodedSecurityDescLen != null && encodedSecurityDescLen.intValue() > 0 && MsgUtil.isTagInList(TagNum.EncodedSecurityDescLen, SECURED_TAGS, secured)) {
                if (encodedSecurityDesc != null && encodedSecurityDesc.length > 0) {
                    encodedSecurityDescLen = new Integer(encodedSecurityDesc.length);
                    TagEncoder.encode(bao, TagNum.EncodedSecurityDescLen, encodedSecurityDescLen);
                    TagEncoder.encode(bao, TagNum.EncodedSecurityDesc, encodedSecurityDesc);
                }
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
    protected void setFragmentCompTagValue(Tag tag, ByteBuffer message)
    throws BadFormatMsgException, InvalidMsgException, TagNotPresentException {
        if (SEC_ALT_ID_GROUP_TAGS.contains(tag.tagNum)) {
            if (noSecurityAltID != null && noSecurityAltID.intValue() > 0) {
                message.reset();
                securityAltIDGroups = new SecurityAltIDGroup[noSecurityAltID.intValue()];
                FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired);
                for (int i = 0; i < noSecurityAltID.intValue(); i++) {
                    SecurityAltIDGroup group = new SecurityAltIDGroup43(context);
                    group.decode(message);
                    securityAltIDGroups[i] = group;
                }
            }
        }
    }

    @Override
    protected String getUnsupportedTagMessage() {
        return "This tag is not supported in [Instrument] component version [4.3].";
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

    private void populateSecurityAltIDListType(Integer noSecurityAltID, SecurityAltIDGroup[] securityAltIDGroups) {
        if (noSecurityAltID != null) {
            if (securityAltIDListType == null) {
                securityAltIDListType = new SecurityAltIDListType43();
            }
            securityAltIDListType.setNoSecurityAltID(noSecurityAltID);
            securityAltIDListType.setSecurityAltIDGroups(securityAltIDGroups);
        } else {
            securityAltIDListType = null;
        }
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Inner Classes">

    @XmlRootElement(name = "SecurityIDSource")
    @XmlType(name = "IDSource43")
    @XmlAccessorType(XmlAccessType.NONE)
    public static class IDSourceType extends SingleStringAttrSimpleType {
    }

    @XmlRootElement(name = "Product")
    @XmlType
    @XmlAccessorType(XmlAccessType.NONE)
    public static class ProductType {

        @XmlAttribute(name = "Value", required = true)
        private Product value;

        public Product getValue() {
            return value;
        }

        public void setValue(Product value) {
            this.value = value;
        }
    }

    @XmlRootElement(name = "SecurityExchange")
    @XmlType(name = "SecurityExchType43")
    @XmlAccessorType(XmlAccessType.NONE)
    public static class SecurityExchType extends SingleStringAttrSimpleType {
    }

    @XmlRootElement(name = "EncodedIssuerGroup")
    @XmlType(name = "EncodedIssuerGroup43", propOrder = {"encodedIssuerLen", "encodedIssuer"})
    @XmlAccessorType(XmlAccessType.NONE)
    public static class EncodedIssuerGroupType {

        @XmlElement(name = "EncodedIssuerLen")
        private Integer encodedIssuerLen;

        @XmlElement(name = "EncodedIssuer")
        private byte[] encodedIssuer;

        public byte[] getEncodedIssuer() {
            return encodedIssuer;
        }

        public void setEncodedIssuer(byte[] encodedIssuer) {
            this.encodedIssuer = encodedIssuer;
        }

        public Integer getEncodedIssuerLen() {
            return encodedIssuerLen;
        }

        public void setEncodedIssuerLen(Integer encodedIssuerLen) {
            this.encodedIssuerLen = encodedIssuerLen;
        }
    }

    @XmlRootElement(name = "EncodedSecurityDescGroup")
    @XmlType(name = "EncodedSecurityDescGroup", propOrder = {"encodedSecurityDescLen", "encodedSecurityDesc"})
    @XmlAccessorType(XmlAccessType.NONE)
    public static class EncodedSecDescGroupType {

        @XmlElement(name = "EncodedSecurityDescLen")
        private Integer encodedSecurityDescLen;

        @XmlElement(name = "EncodedSecurityDesc")
        private byte[] encodedSecurityDesc;

        public byte[] getEncodedSecurityDesc() {
            return encodedSecurityDesc;
        }

        public void setEncodedSecurityDesc(byte[] encodedSecurityDesc) {
            this.encodedSecurityDesc = encodedSecurityDesc;
        }

        public Integer getEncodedSecurityDescLen() {
            return encodedSecurityDescLen;
        }

        public void setEncodedSecurityDescLen(Integer encodedSecurityDescLen) {
            this.encodedSecurityDescLen = encodedSecurityDescLen;
        }
    }

    // </editor-fold>
}
