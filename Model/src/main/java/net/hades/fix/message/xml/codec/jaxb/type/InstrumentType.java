/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * InstrumentType.java
 *
 * $Id: InstrumentType.java,v 1.1 2009-07-06 03:18:50 vrotaru Exp $
 */
package net.hades.fix.message.xml.codec.jaxb.type;

import net.hades.fix.message.type.PutOrCall;
import net.hades.fix.message.xml.codec.jaxb.type.v41.SecurityTypeType41;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * FIXML JAXB type for Instrument tag.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 22/05/2009, 8:36:04 AM
 */
@XmlRootElement(name = "Instrument")
@XmlType(propOrder = {"symbol", "symbolSfx", "securityID", "idSourceType", "securityTypeType", "securityExchType",
    "issuer", "encodedIssuerGroupType", "securityDesc", "encodedSecDescGroupType"})
@XmlAccessorType(XmlAccessType.NONE)
public class InstrumentType {

    // <editor-fold defaultstate="collapsed" desc="Constants">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">
    @XmlElement(name = "Symbol", required = true)
    private String symbol;

    @XmlElement(name = "SymbolSfx")
    private String symbolSfx;

    @XmlElement(name = "SecurityID")
    private String securityID;

    @XmlElement(name = "Issuer")
    private String issuer;

    @XmlElement(name = "SecurityDesc")
    private String securityDesc;

    @XmlElement(name = "IDSource")
    private IDSourceType idSourceType;

    @XmlElement(name = "SecurityType")
    private SecurityTypeType41 securityTypeType;

    @XmlElement(name = "SecurityExch")
    private SecurityExchType securityExchType;

    @XmlElement(name = "EncodedIssuerGroup")
    private EncodedIssuerGroupType encodedIssuerGroupType;

    @XmlElement(name = "EncodedSecDescGroup")
    private EncodedSecDescGroupType encodedSecDescGroupType;
    
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Public Methods">
    public String getIssuer() {
        return issuer;
    }

    public void setIssuer(String issuer) {
        this.issuer = issuer;
    }

    public String getSecurityDesc() {
        return securityDesc;
    }

    public void setSecurityDesc(String securityDesc) {
        this.securityDesc = securityDesc;
    }

    public String getSecurityExchange() {
        return securityExchType == null ? null : securityExchType.getValue();
    }

    public void setSecurityExchange(String securityExchange) {
        if (securityExchType == null) {
            securityExchType = new SecurityExchType();
        }
        securityExchType.setValue(securityExchange);
    }

    public String getSecurityID() {
        return securityID;
    }

    public void setSecurityID(String securityID) {
        this.securityID = securityID;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbolSfx() {
        return symbolSfx;
    }

    public void setSymbolSfx(String symbolSfx) {
        this.symbolSfx = symbolSfx;
    }

    public String getIdSource() {
        return idSourceType.getValue();
    }

    public void setIdSource(String idSource) {
        if (idSourceType == null) {
            idSourceType = new IDSourceType();
        }
        idSourceType.setValue(idSource);
    }

    public String getSecurityType() {
        return securityTypeType == null ? null : securityTypeType.getValue();
    }

    public void setSecurityType(String securityType) {
        if (securityTypeType == null) {
            securityTypeType = new SecurityTypeType41();
        }
        securityTypeType.setValue(securityType);
    }

    public PutOrCall getPutOrCall() {
        PutOrCall result = null;
        if (securityTypeType != null && securityTypeType.getElement() instanceof SecurityTypeType41.OptionType) {
            if (((SecurityTypeType41.OptionType) securityTypeType.getElement()).getPutCallType() != null) {
                result = ((SecurityTypeType41.OptionType) securityTypeType.getElement()).getPutCallType().getValue();
            }
        }
        return result;
    }

    public void setPutOrCall(PutOrCall putOrCall) {
        if (securityTypeType != null && securityTypeType.getElement() instanceof SecurityTypeType41.OptionType) {
            ((SecurityTypeType41.OptionType) securityTypeType.getElement()).getPutCallType().setValue(putOrCall);
        }
    }

    public String getMaturityMonthYear() {
        String result = null;
        if (securityTypeType != null && securityTypeType.getElement() instanceof SecurityTypeType41.OptionType) {
            if (((SecurityTypeType41.OptionType) securityTypeType.getElement()).getMaturityType() != null) {
                result = ((SecurityTypeType41.OptionType) securityTypeType.getElement()).getMaturityType().getMonthYear();
            }
        }
        if (securityTypeType != null && securityTypeType.getElement() instanceof SecurityTypeType41.FutureType) {
            if (((SecurityTypeType41.FutureType) securityTypeType.getElement()).getMaturityType() != null) {
                result = ((SecurityTypeType41.FutureType) securityTypeType.getElement()).getMaturityType().getMonthYear();
            }
        }
        return result;
    }

    public void setMaturityMonthYear(String maturityMonthYear) {
        if (securityTypeType != null && securityTypeType.getElement() instanceof SecurityTypeType41.OptionType) {
            ((SecurityTypeType41.OptionType) securityTypeType.getElement()).getMaturityType().setMonthYear(maturityMonthYear);
        }
        if (securityTypeType != null && securityTypeType.getElement() instanceof SecurityTypeType41.FutureType) {
            ((SecurityTypeType41.FutureType) securityTypeType.getElement()).getMaturityType().setMonthYear(maturityMonthYear);
        }
    }

    public Integer getMaturityDay() {
        Integer result = null;
        if (securityTypeType != null && securityTypeType.getElement() instanceof SecurityTypeType41.OptionType) {
            if (((SecurityTypeType41.OptionType) securityTypeType.getElement()).getMaturityType() != null) {
                result = ((SecurityTypeType41.OptionType) securityTypeType.getElement()).getMaturityType().getDay();
            }
        }
        if (securityTypeType != null && securityTypeType.getElement() instanceof SecurityTypeType41.FutureType) {
            if (((SecurityTypeType41.FutureType) securityTypeType.getElement()).getMaturityType() != null) {
                result = ((SecurityTypeType41.FutureType) securityTypeType.getElement()).getMaturityType().getDay();
            }
        }
        return result;
    }

    public void setMaturityDay(Integer maturityDay) {
        if (securityTypeType != null && securityTypeType.getElement() instanceof SecurityTypeType41.OptionType) {
            ((SecurityTypeType41.OptionType) securityTypeType.getElement()).getMaturityType().setDay(maturityDay);
        }
        if (securityTypeType != null && securityTypeType.getElement() instanceof SecurityTypeType41.FutureType) {
            ((SecurityTypeType41.FutureType) securityTypeType.getElement()).getMaturityType().setDay(maturityDay);
        }
    }

    public Double getContractMultiplier() {
        Double result = null;
        if (securityTypeType != null && securityTypeType.getElement() instanceof SecurityTypeType41.FixedIncomeType) {
            if ((SecurityTypeType41.FixedIncomeType) securityTypeType.getElement() != null) {
                result = ((SecurityTypeType41.FixedIncomeType) securityTypeType.getElement()).getContractMultiplier();
            }
        }
        return result;
    }

    public void setContractMultiplier(Double contractMultiplier) {
        if (securityTypeType != null && securityTypeType.getElement() instanceof SecurityTypeType41.FixedIncomeType) {
            ((SecurityTypeType41.FixedIncomeType) securityTypeType.getElement()).setContractMultiplier(contractMultiplier);
        }
    }

    public Double getCouponRate() {
        Double result = null;
        if (securityTypeType != null && securityTypeType.getElement() instanceof SecurityTypeType41.FixedIncomeType) {
            if ((SecurityTypeType41.FixedIncomeType) securityTypeType.getElement() != null) {
                result = ((SecurityTypeType41.FixedIncomeType) securityTypeType.getElement()).getCouponRate();
            }
        }
        return result;
    }

    public void setCouponRate(Double couponRate) {
        if (securityTypeType != null && securityTypeType.getElement() instanceof SecurityTypeType41.FixedIncomeType) {
            ((SecurityTypeType41.FixedIncomeType) securityTypeType.getElement()).setCouponRate(couponRate);
        }
    }

    public Double getStrikePrice() {
        Double result = null;
        if (securityTypeType != null && securityTypeType.getElement() instanceof SecurityTypeType41.OptionType) {
            result = ((SecurityTypeType41.OptionType) securityTypeType.getElement()).getStrikePrice();
        }
        return result;
    }

    public void setStrikePrice(Double strikePrice) {
        if (securityTypeType != null && securityTypeType.getElement() instanceof SecurityTypeType41.OptionType) {
            ((SecurityTypeType41.OptionType) securityTypeType.getElement()).setStrikePrice(strikePrice);
        }
    }

    public Character getOptAttribute() {
        Character result = null;
        if (securityTypeType != null && securityTypeType.getElement() instanceof SecurityTypeType41.OptionType) {
            result = ((SecurityTypeType41.OptionType) securityTypeType.getElement()).getOptAttribute();
        }
        return result;
    }

    public void setOptAttribute(Character optAttribute) {
        if (securityTypeType != null && securityTypeType.getElement() instanceof SecurityTypeType41.OptionType) {
            ((SecurityTypeType41.OptionType) securityTypeType.getElement()).setOptAttribute(optAttribute);
        }
    }

    public Integer getEncodedIssuerLen() {
        return encodedIssuerGroupType == null ? null : encodedIssuerGroupType.getEncodedIssuerLen();
    }

    public void setEncodedIssuerLen(Integer encodedIssuerLen) {
        if (encodedIssuerGroupType == null) {
            encodedIssuerGroupType = new EncodedIssuerGroupType();
        }
        encodedIssuerGroupType.setEncodedIssuerLen(encodedIssuerLen);
    }

    public byte[] getEncodedIssuer() {
        return encodedIssuerGroupType == null ? null : encodedIssuerGroupType.getEncodedIssuer();
    }

    public void setEncodedIssuer(byte[] encodedIssuer) {
        if (encodedIssuerGroupType == null) {
            encodedIssuerGroupType = new EncodedIssuerGroupType();
        }
        encodedIssuerGroupType.setEncodedIssuer(encodedIssuer);
    }

    public Integer getEncodedSecurityDescLen() {
        return encodedSecDescGroupType == null ? null : encodedSecDescGroupType.getEncodedSecurityDescLen();
    }

    public void setEncodedSecurityDescLen(Integer encodedSecurityDescLen) {
        if (encodedSecDescGroupType == null) {
            encodedSecDescGroupType = new EncodedSecDescGroupType();
        }
        encodedSecDescGroupType.setEncodedSecurityDescLen(encodedSecurityDescLen);
    }

    public byte[] getEncodedSecurityDesc() {
        return encodedSecDescGroupType == null ? null : encodedSecDescGroupType.getEncodedSecurityDesc();
    }

    public void setEncodedSecurityDesc(byte[] encodedSecurityDesc) {
        if (encodedSecDescGroupType == null) {
            encodedSecDescGroupType = new EncodedSecDescGroupType();
        }
        encodedSecDescGroupType.setEncodedSecurityDesc(encodedSecurityDesc);
    }

    public IDSourceType getIdSourceType() {
        return idSourceType;
    }

    public void setIdSourceType(IDSourceType idSourceType) {
        this.idSourceType = idSourceType;
    }

    public SecurityTypeType41 getSecurityTypeType() {
        return securityTypeType;
    }

    public void setSecurityTypeType(SecurityTypeType41 securityTypeType) {
        this.securityTypeType = securityTypeType;
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
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Package Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Private Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Inner Classes">
    
    @XmlRootElement(name = "IDSource")
    @XmlType(name = "IDSource41")
    @XmlAccessorType(XmlAccessType.NONE)
    public static class IDSourceType extends SingleStringAttrSimpleType {
    }

    @XmlRootElement(name = "SecurityExch")
    @XmlType(name = "SecurityExchType41")
    @XmlAccessorType(XmlAccessType.NONE)
    public static class SecurityExchType extends SingleStringAttrSimpleType {
    }

    @XmlRootElement(name = "EncodedIssuerGroup")
    @XmlType(name = "EncodedIssuerGroup41", propOrder = {"encodedIssuerLen", "encodedIssuer"})
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

    @XmlRootElement(name = "EncodedSecDescGroup")
    @XmlType(name = "EncodedSecDescGroup41", propOrder = {"encodedSecurityDescLen", "encodedSecurityDesc"})
    @XmlAccessorType(XmlAccessType.NONE)
    public static class EncodedSecDescGroupType {

        @XmlElement(name = "EncodedSecDescLen")
        private Integer encodedSecurityDescLen;

        @XmlElement(name = "EncodedSecDesc")
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
