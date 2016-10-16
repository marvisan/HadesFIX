/*
 *   Copyright (c) 2006-2008 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * StipulationType.java
 *
 * $Id: StipulationType.java,v 1.4 2010-02-25 08:37:30 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;

/**
 * Type of Stipulation.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.4 $
 * @created 28/12/2008, 11:40:27 AM
 */
public enum StipulationType {

    /**
     * Code <code>AMT</code>.
     */
    AlternativeMinimumTax                               ("AMT"),
    /**
     * Code <code>AUTOREINV</code>.
     */
    AutoReinvestment                                    ("AUTOREINV"),
    /**
     * Code <code>BANKQUAL</code>.
     */
    BankQualified                                       ("BANKQUAL"),
    /**
     * Code <code>BGNCON</code>.
     */
    BargainConditions                                   ("BGNCON"),
    /**
     * Code <code>COUPON</code>.
     */
    CouponRange                                         ("COUPON"),
    /**
     * Code <code>CURRENCY</code>.
     */
    ISOCurrencyCode                                     ("CURRENCY"),
    /**
     * Code <code>CUSTOMDATE</code>.
     */
    CustomStartEndDate                                  ("CUSTOMDATE"),
    /**
     * Code <code>GEOG</code>.
     */
    GeographicsAndPctRange                              ("GEOG"),
    /**
     * Code <code>HAIRCUT</code>.
     */
    ValuationDiscount                                   ("HAIRCUT"),
    /**
     * Code <code>INSURED</code>.
     */
    Insured                                             ("INSURED"),
    /**
     * Code <code>ISSUE</code>.
     */
    YearMonthOfIssue                                    ("ISSUE"),
    /**
     * Code <code>ISSUER</code>.
     */
    IssuersTicker                                       ("ISSUER"),
    /**
     * Code <code>ISSUESIZE</code>.
     */
    IssueSizeRange                                      ("ISSUESIZE"),
    /**
     * Code <code>LOOKBACK</code>.
     */
    LookbackDays                                        ("LOOKBACK"),
    /**
     * Code <code>LOT</code>.
     */
    ExplicitLotIdentifier                               ("LOT"),
    /**
     * Code <code>LOTVAR</code>.
     */
    LotVariance                                         ("LOTVAR"),
    /**
     * Code <code>MAT</code>.
     */
    MaturityYearAndMonth                                ("MAT"),
    /**
     * Code <code>MATURITY</code>.
     */
    MaturityRange                                       ("MATURITY"),
    /**
     * Code <code>MAXSUBS</code>.
     */
    MaximumSubstitutions                                ("MAXSUBS"),
    /**
     * Code <code>MINDNOM</code>.
     */
    MinimumDenomination                                 ("MINDNOM"),
    /**
     * Code <code>MININCR</code>.
     */
    MinimumIncrement                                    ("MININCR"),
    /**
     * Code <code>MINQTY</code>.
     */
    MinimumQuantity                                     ("MINQTY"),
    /**
     * Code <code>PAYFREQ</code>.
     */
    PaymentFrequency                                    ("PAYFREQ"),
    /**
     * Code <code>PIECES</code>.
     */
    NumberOfPieces                                      ("PIECES"),
    /**
     * Code <code>PMAX</code>.
     */
    PoolsMaximum                                        ("PMAX"),
    /**
     * Code <code>PPL</code>.
     */
    PoolsPerLot                                         ("PPL"),
    /**
     * Code <code>PPM</code>.
     */
    PoolsPerMillion                                     ("PPM"),
    /**
     * Code <code>PPT</code>.
     */
    PoolsPerTrade                                       ("PPT"),
    /**
     * Code <code>PRICE</code>.
     */
    PriceRange                                          ("PRICE"),
    /**
     * Code <code>PRICEFREQ</code>.
     */
    PricingFrequency                                    ("PRICEFREQ"),
    /**
     * Code <code>PROD</code>.
     */
    ProductionYear                                      ("PROD"),
    /**
     * Code <code>PROTECT</code>.
     */
    CallProtection                                      ("PROTECT"),
    /**
     * Code <code>PURPOSE</code>.
     */
    Purpose                                             ("PURPOSE"),
    /**
     * Code <code>PXSOURCE</code>.
     */
    BenchmarkPriceSource                                ("PXSOURCE"),
    /**
     * Code <code>RATING</code>.
     */
    RatingSourceAndRange                                ("RATING"),
    /**
     * Code <code>REDEMPTION</code>.
     */
    TypeOfRedemption                                    ("REDEMPTION"),
    /**
     * Code <code>RESTRICTED</code>.
     */
    Restricted                                          ("RESTRICTED"),
    /**
     * Code <code>SECTOR</code>.
     */
    MarketSector                                        ("SECTOR"),
    /**
     * Code <code>SECTYPE</code>.
     */
    SecurityType                                        ("SECTYPE"),
    /**
     * Code <code>STRUCT</code>.
     */
    Structure                                           ("STRUCT"),
    /**
     * Code <code>SUBSFREQ</code>.
     */
    SubstitutionsFrequency                              ("SUBSFREQ"),
    /**
     * Code <code>SUBSLEFT</code>.
     */
    SubstitutionsLeft                                   ("SUBSLEFT"),
    /**
     * Code <code>TEXT</code>.
     */
    FreeformText                                        ("TEXT"),
    /**
     * Code <code>TRDVAR</code>.
     */
    TradeVariance                                       ("TRDVAR"),
    /**
     * Code <code>WAC</code>.
     */
    WeightedAverageCoupon                               ("WAC"),
    /**
     * Code <code>WAL</code>.
     */
    WeightedAverageLifeCoupon                           ("WAL"),
    /**
     * Code <code>WALA</code>.
     */
    WeightedAverageLoanAge                              ("WALA"),
    /**
     * Code <code>WAM</code>.
     */
    WeightedAverageMaturity                             ("WAM"),
    /**
     * Code <code>WHOLE</code>.
     */
    WholePool                                           ("WHOLE"),
    /**
     * Code <code>YIELD</code>.
     */
    YieldRange                                          ("YIELD"),
    /**
     * Code <code>AVFICO</code>.
     */
    AverageFICOScore                                    ("AVFICO"),
    /**
     * Code <code>AVSIZE</code>.
     */
    AverageLoanSize                                     ("AVSIZE"),
    /**
     * Code <code>MAXBAL</code>.
     */
    MaximumLoanBalance                                  ("MAXBAL"),
    /**
     * Code <code>POOL</code>.
     */
    PoolIdentifier                                      ("POOL"),
    /**
     * Code <code>ROLLTYPE</code>.
     */
    TypeOfRollTrade                                     ("ROLLTYPE"),
    /**
     * Code <code>REFTRADE</code>.
     */
    ReferenceToTrade                                    ("REFTRADE"),
    /**
     * Code <code>REFPRIN</code>.
     */
    PrincipalOfTrade                                    ("REFPRIN"),
    /**
     * Code <code>REFINT</code>.
     */
    InterestOfTrade                                     ("REFINT"),
    /**
     * Code <code>AVAILQTY</code>.
     */
    AvailableOfferQuantity                              ("AVAILQTY"),
    /**
     * Code <code>BROKERCREDIT</code>.
     */
    BrokerSalesCredit                                   ("BROKERCREDIT"),
    /**
     * Code <code>INTERNALPX</code>.
     */
    InternalOfferPrice                                  ("INTERNALPX"),
    /**
     * Code <code>INTERNALQTY</code>.
     */
    InternalOfferQuantity                               ("INTERNALQTY"),
    /**
     * Code <code>LEAVEQTY</code>.
     */
    MinimumResidualOfferQuantity                        ("LEAVEQTY"),
    /**
     * Code <code>MAXORDQTY</code>.
     */
    MaximumOrderSize                                    ("MAXORDQTY"),
    /**
     * Code <code>ORDRINCR</code>.
     */
    OrderQuantityIncrement                              ("ORDRINCR"),
    /**
     * Code <code>PRIMARY</code>.
     */
    PrimaryOrSecondaryMarket                            ("PRIMARY"),
    /**
     * Code <code>SALESCREDITOVR</code>.
     */
    BrokerSalesCreditOverride                           ("SALESCREDITOVR"),
    /**
     * Code <code>TRADERCREDIT</code>.
     */
    TraderCredit                                        ("TRADERCREDIT"),
    /**
     * Code <code>DISCOUNT</code>.
     */
    DiscountRate                                        ("DISCOUNT"),
    /**
     * Code <code>YTM</code>.
     */
    YieldToMaturity                                     ("YTM"),
    /**
     * Code <code>ABS</code>.
     */
    AbsolutePrepaymentSpeed                             ("ABS"),
    /**
     * Code <code>CPP</code>.
     */
    ConstantPrepaymentPenalty                           ("CPP"),
    /**
     * Code <code>CPR</code>.
     */
    ConstantPrepaymentRate                              ("CPR"),
    /**
     * Code <code>CPY</code>.
     */
    ConstantPrepaymentYield                             ("CPY"),
    /**
     * Code <code>HEP</code>.
     */
    HomeEquityPrepayment                                ("HEP"),
    /**
     * Code <code>MHP</code>.
     */
    ManufacturedHousingPrepayment                       ("MHP"),
    /**
     * Code <code>MPR</code>.
     */
    MonthlyPrepaymentRate                               ("MPR"),
    /**
     * Code <code>PPC</code>.
     */
    PercentOfProspectusPrepayment                       ("PPC"),
    /**
     * Code <code>PSA</code>.
     */
    PercentOfBMAPrepayment                              ("PSA"),
    /**
     * Code <code>SMM</code>.
     */
    SingleMonthlyMortality                              ("SMM");

    private static final long serialVersionUID = 1L;

    private String value;

    private static final Map<String, StipulationType> stringToEnum = new HashMap<String, StipulationType>();

    static {
        for (StipulationType tag : values()) {
            stringToEnum.put(tag.getValue(), tag);
        }
    }

    /** Creates a new instance of SecurityStatus. */
    StipulationType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static StipulationType valueFor(String value) {
        return stringToEnum.get(value);
    }
}
