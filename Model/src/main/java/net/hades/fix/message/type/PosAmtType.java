/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.6
 *               Use is subject to license terms.
 */

/*
 * PosAmtType.java
 *
 * $Id: PosAmtType.java,v 1.3 2010-01-14 09:06:46 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;
import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Type of Position amount.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.3 $
 * @created 27/09/2009, 4:25:42 PM
 */
@XmlType
@XmlEnum(String.class)
public enum PosAmtType {

    @XmlEnumValue("CASH")   CashAmount                          ("CASH"),
    @XmlEnumValue("CRES")   CashResidualAmount                  ("CRES"),
    @XmlEnumValue("FMTM")   FinalMarkToMarketAmount             ("FMTM"),
    @XmlEnumValue("IMTM")   IncrMarkToMarketAmount              ("IMTM"),
    @XmlEnumValue("PREM")   PremiumAmount                       ("PREM"),
    @XmlEnumValue("SMTM")   StartDayMarkToMarketAmount          ("SMTM"),
    @XmlEnumValue("TVAR")   TradeVariationAmount                ("TVAR"),
    @XmlEnumValue("VADJ")   ValueAdjustedAmount                 ("VADJ"),
    @XmlEnumValue("SETL")   SettlementValue                     ("SETL"),
    @XmlEnumValue("ICPN")   InitialTradeCouponAmount            ("ICPN"),
    @XmlEnumValue("ACPN")   AccruedCouponAmount                 ("ACPN"),
    @XmlEnumValue("CPN")    CouponAmount                        ("CPN"),
    @XmlEnumValue("IACPN")  IncrAccruedCoupon                   ("IACPN"),
    @XmlEnumValue("CMTM")   CollateralizedMarkToMarket          ("CMTM"),
    @XmlEnumValue("ICMTM")  IncrCollateralizedMarToMarket       ("ICMTM"),
    @XmlEnumValue("DLV")    CompensationAmount                  ("DLV"),
    @XmlEnumValue("BANK")   TotalBankedAmount                   ("BANK"),
    @XmlEnumValue("COLAT")  TotalCollateralizedAmount           ("COLAT");

    private static final long serialVersionUID = -5125598017453477798L;

    private String value;

    private static final Map<String, PosAmtType> stringToEnum = new HashMap<String, PosAmtType>();

    static {
        for (PosAmtType tag : values()) {
            stringToEnum.put(tag.getValue(), tag);
        }
    }

    /** Creates a new instance of PosAmtType. */
    PosAmtType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static PosAmtType valueFor(String value) {
        return stringToEnum.get(value);
    }
}
