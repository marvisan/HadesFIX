/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * SecurityType.java
 *
 * $Id: SecurityType.java,v 1.5 2010-02-25 08:37:27 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;

/**
 * Type of security.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.5 $
 * @created 14/12/2008, 4:57:58 PM
 */
public enum SecurityType {

    /**
     * Deprecated FIX 4.4. Use TNOTE.
     */
    USTreasuryNote                          ("UST"),
    /**
     * Deprecated FIX 4.4. Use TBILL.
     */
    USTreasuryBillOld                       ("USTB"),
    //Agency
    EuroSupranationalCoupons                ("EUSUPRA"),
    FederalAgencyCoupon                     ("FAC"),
    FederalAgencyDiscountNote               ("FADN"),
    PrivateExportFunding                    ("PEF"),
    USDSupranationalCoupons                 ("SUPRA"),
    // Corporate
    CorporateBond                           ("CORP"),
    CorporatePrivatePlacement               ("CPP"),
    ConvertibleBond                         ("CB"),
    DualCurrency                            ("DUAL"),
    EuroCorporateBond                       ("EUCORP"),
    EuroCorporateFloatingRateNotes          ("EUFRN"),
    USCorporateFloatingRateNotes            ("FRN"),
    IndexedLinked                           ("XLINKD"),
    StructuredNotes                         ("STRUCT"),
    YankeeCorporateBondCurrency             ("YANK"),
    // Currency
    ForeignExchangeContractDerivatives      ("FOR"),
    // Derivatives
    CreditDefaultSwap                       ("CDS"),
    Future                                  ("FUT"),
    Option                                  ("OPT"),
    OptionsOnFutures                        ("OOF"),
    OptionsOnPhysical                       ("OOP"),
    InterestRateSwap                        ("IRS"),
    OptionsOnComboEquity                    ("OOC"),
    // Equity
    CommonStock                             ("CS"),
    PreferredStockFinancing                 ("PS"),
    // Financing
    Repurchase                              ("REPO"),
    Forward                                 ("FORWARD"),
    BuySellback                             ("BUYSELL"),
    SecuritiesLoan                          ("SECLOAN"),
    SecuritiesPledgeGovernment              ("SECPLEDGE"),
    // Government
    BradyBond                               ("BRADY"),
    CanadianTreasuryNotes                   ("CAN"),
    CanadianTreasuryBills                   ("CTB"),
    EuroSovereigns                          ("EUSOV"),
    CanadianProvincialBonds                 ("PROV"),
    NonUSTreasuryBill                       ("TB"),
    USTreasuryBond                          ("TBOND"),
    InterestStripFromAnyBondOrNote          ("TINT"),
    USTreasuryBill                          ("TBILL"),
    TreasuryInflationProtectedSecurities    ("TIPS"),
    PrincipalStripOfACallableBond           ("TCAL"),
    PrincipalStripFromANonCallableBond      ("TPRN"),
    USTreasuryNoteLoan                      ("TNOTE"),
    // Loan
    TermLoan                                ("TERM"),
    RevolverLoan                            ("RVLV"),
    RevolverTermLoan                        ("RVLVTRM"),
    BridgeLoan                              ("BRIDGE"),
    LetterOfCredit                          ("LOFC"),
    SwingLineFacility                       ("SWING"),
    DebtorInPossession                      ("DINP"),
    Defaulted                               ("DEFLTED"),
    Withdrawn                               ("WITHDRN"),
    Replaced                                ("REPLACD"),
    Matured                                 ("MATURED"),
    AmendedRestated                         ("AMENDED"),
    RetiredMoneyMarket                      ("RETIRED"),
    // Money Market
    BankersAcceptance                       ("BA"),
    BankDepositoryNote                      ("BDN"),
    BankNotes                               ("BN"),
    BillOfExchanges                         ("BOX"),
    CanadianMoneyMarkets                    ("CAMM"),
    CertificateOfDeposit                    ("CD"),
    CallLoans                               ("CL"),
    CommercialPaper                         ("CP"),
    DepositNotes                            ("DN"),
    EuroCertificateOfDeposit                ("EUCD"),
    EuroCommercialPaper                     ("EUCP"),
    LiquidityNote                           ("LQN"),
    MediumTermNotes                         ("MTN"),
    Overnight                               ("ONITE"),
    PromissoryNote                          ("PN"),
    ShortTermLoanNote                       ("STN"),
    PlazosFijos                             ("PZFJ"),
    SecuredLiquidityNote                    ("SLQN"),
    TimeDeposit                             ("TD"),
    TermLiquidityNote                       ("TLQN"),
    ExtendedCommNote                        ("XCN"),
    YankeeCertificateOfDepositMortgage      ("YCD"),
    // Mortgage
    AssetBackedSecurities                   ("ABS"),
    CanadianMortgageBonds                   ("CMB"),
    CorpMortgageBackedSecurities            ("CMBS"),
    CollateralizedMortgageObligation        ("CMO"),
    IOETTEMortgage                          ("IET"),
    MortgageBackedSecurities                ("MBS"),
    MortgageInterestOnly                    ("MIO"),
    MortgagePrincipalOnly                   ("MPO"),
    MortgagePrivatePlacement                ("MPP"),
    MiscellaneousPassThrough                ("MPT"),
    Pfandbriefe                             ("PFAND"),
    ToBeAnnouncedMunicipal                  ("TBA"),
    // Municipal
    OtherAnticipationNotes                  ("AN"),
    CertificateOfObligation                 ("COFO"),
    CertificateOfParticipation              ("COFP"),
    GeneralObligationBonds                  ("GO"),
    MandatoryTender                         ("MT"),
    RevenueAnticipationNote                 ("RAN"),
    RevenueBonds                            ("REV"),
    SpecialAssessment                       ("SPCLA"),
    SpecialObligation                       ("SPCLO"),
    SpecialTax                              ("SPCLT"),
    TaxAnticipationNote                     ("TAN"),
    TaxAllocation                           ("TAXA"),
    TaxExemptCommercialPaper                ("TECP"),
    TaxableMunicipalCP                      ("TMCP"),
    TaxRevenueAnticipationNote              ("TRAN"),
    VariableRateDemandNote                  ("VRDN"),
    Warrant                                 ("WAR"),
    // Other
    MutualFund                              ("MF"),
    MultilegInstrument                      ("MLEG"),
    NoSecurityType                          ("NONE"),
    WildcardEntry                           ("?"),
    Cash                                    ("CASH");

    private static final long serialVersionUID = 1L;

    private String value;

    private static final Map<String, SecurityType> stringToEnum = new HashMap<String, SecurityType>();

    static {
        for (SecurityType tag : values()) {
            stringToEnum.put(tag.getValue(), tag);
        }
    }

    /** Creates a new instance of SecurityType */
    SecurityType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static SecurityType valueFor(String value) {
        return stringToEnum.get(value);
    }
}
