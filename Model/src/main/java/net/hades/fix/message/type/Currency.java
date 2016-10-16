/*
 *   Copyright (c) 2006-2008 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * Currency.java
 *
 * $Id: Currency.java,v 1.5 2011-10-29 09:42:32 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Currency symbols used.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.5 $
 * @created 29/06/2008, 17:55:18
 */
@XmlType
@XmlEnum(String.class)
public enum Currency {

    @XmlEnumValue("AFN") Afghani                        ("AFN"),
    @XmlEnumValue("EUR") Euro                           ("EUR"),
    @XmlEnumValue("ALL") Lek                            ("ALL"),
    @XmlEnumValue("DZD") AlgerianDinar                  ("DZD"),
    @XmlEnumValue("USD") UnitedStatesDollar             ("USD"),
    @XmlEnumValue("AOA") Kwanza                         ("AOA"),
    @XmlEnumValue("XCD") EastCaribbeanDollar            ("XCD"),
    @XmlEnumValue("ARS") ArgentinePeso                  ("ARS"),
    @XmlEnumValue("AMD") ArmenianDram                   ("AMD"),
    @XmlEnumValue("AWG") ArubanFlorin                   ("AWG"),
    @XmlEnumValue("AUD") AustralianDollar               ("AUD"),
    @XmlEnumValue("AZN") AzerbaijanianManat             ("AZN"),
    @XmlEnumValue("BSD") BahamianDollar                 ("BSD"),
    @XmlEnumValue("BHD") BahrainiDinar                  ("BHD"),
    @XmlEnumValue("BDT") Taka                           ("BDT"),
    @XmlEnumValue("BBD") BarbadosDollar                 ("BBD"),
    @XmlEnumValue("BYR") BelarussianRuble               ("BYR"),
    @XmlEnumValue("BZD") BelizeDollar                   ("BZD"),
    @XmlEnumValue("XOF") CFAFrancBCEAO                  ("XOF"),
    @XmlEnumValue("BMD") BermudianDollar                ("BMD"),
    @XmlEnumValue("INR") IndianRupee                    ("INR"),
    @XmlEnumValue("BTN") Ngultrum                       ("BTN"),
    @XmlEnumValue("BOB") Boliviano                      ("BOB"),
    @XmlEnumValue("BOV") Mvdol                          ("BOV"),
    @XmlEnumValue("BAM") ConvertibleMark                ("BAM"),
    @XmlEnumValue("BWP") Pula                           ("BWP"),
    @XmlEnumValue("NOK") NorwegianKrone                 ("NOK"),
    @XmlEnumValue("BRL") BrazilianReal                  ("BRL"),
    @XmlEnumValue("BND") BruneiDollar                   ("BND"),
    @XmlEnumValue("BGN") BulgarianLev                   ("BGN"),
    @XmlEnumValue("BIF") BurundiFranc                   ("BIF"),
    @XmlEnumValue("KHR") Riel                           ("KHR"),
    @XmlEnumValue("XAF") CFAFrancBEAC                   ("XAF"),
    @XmlEnumValue("CAN") CanadianDollar                 ("CAN"),
    @XmlEnumValue("CVE") CapeVerdeEscudo                ("CVE"),
    @XmlEnumValue("KYD") CaymanIslandsDollar            ("KYD"),
    @XmlEnumValue("CLP") ChileanPeso                    ("CLP"),
    @XmlEnumValue("CLF") UnidadesDeFomento              ("CLF"),
    @XmlEnumValue("CNY") YuanRenminbi                   ("CNY"),
    @XmlEnumValue("COP") ColombianPeso                  ("COP"),
    @XmlEnumValue("COU") UnidadDeValorReal              ("COU"),
    @XmlEnumValue("KMF") ComoroFranc                    ("KMF"),
    @XmlEnumValue("CDF") CongoleseFranc                 ("CDF"),
    @XmlEnumValue("NZD") NewZealandDollar               ("NZD"),
    @XmlEnumValue("CRC") CostaRicanColon                ("CRC"),
    @XmlEnumValue("HRK") CroatianKuna                   ("HRK"),
    @XmlEnumValue("CUP") CubanPeso                      ("CUP"),
    @XmlEnumValue("CUC") PesoConvertible                ("CUC"),
    @XmlEnumValue("ANG") NetherlandsAntilleanGuilder	("ANG"),
    @XmlEnumValue("CZK") CzechKoruna                    ("CZK"),
    @XmlEnumValue("DKK") DanishKrone                    ("DKK"),
    @XmlEnumValue("DJF") DjiboutiFranc                  ("DJF"),
    @XmlEnumValue("DOP") DominicanPeso                  ("DOP"),
    @XmlEnumValue("EGP") EgyptianPound                  ("EGP"),
    @XmlEnumValue("SVC") ElSalvadorColon                ("SVC"),
    @XmlEnumValue("ERN") Nakfa                          ("ERN"),
    @XmlEnumValue("ETB") EthiopianBirr                  ("ETB"),
    @XmlEnumValue("FKP") FalklandIslandsPound           ("FKP"),
    @XmlEnumValue("FJD") FijiDollar                     ("FJD"),
    @XmlEnumValue("XPF") CFPFranc                       ("XPF"),
    @XmlEnumValue("GMD") Dalasi                         ("GMD"),
    @XmlEnumValue("GEL") Lari                           ("GEL"),
    @XmlEnumValue("GHS") Cedi                           ("GHS"),
    @XmlEnumValue("GIP") GibraltarPound                 ("GIP"),
    @XmlEnumValue("GTQ") Quetzal                        ("GTQ"),
    @XmlEnumValue("GBP") PoundSterling                  ("GBP"),
    @XmlEnumValue("GNF") GuineaFranc                    ("GNF"),
    @XmlEnumValue("GYD") GuyanaDollar                   ("GYD"),
    @XmlEnumValue("HTG") Gourde                         ("HTG"),
    @XmlEnumValue("HNL") Lempira                        ("HNL"),
    @XmlEnumValue("HKD") HongKongDollar                 ("HKD"),
    @XmlEnumValue("HUF") Forint                         ("HUF"),
    @XmlEnumValue("ISK") IcelandKrona                   ("ISK"),
    @XmlEnumValue("IDR") Rupiah                         ("IDR"),
    @XmlEnumValue("IRR") IranianRial                    ("IRR"),
    @XmlEnumValue("IQD") IraqiDinar                     ("IQD"),
    @XmlEnumValue("ILS") NewIsraeliSheqel               ("ILS"),
    @XmlEnumValue("JMD") JamaicanDollar                 ("JMD"),
    @XmlEnumValue("JPY") Yen                            ("JPY"),
    @XmlEnumValue("JOD") JordanianDinar                 ("JOD"),
    @XmlEnumValue("KZT") Tenge                          ("KZT"),
    @XmlEnumValue("KES") KenyanShilling                 ("KES"),
    @XmlEnumValue("KPW") NorthKoreanWon                 ("KPW"),
    @XmlEnumValue("KRW") Won                            ("KRW"),
    @XmlEnumValue("KWD") KuwaitiDinar                   ("KWD"),
    @XmlEnumValue("KGS") Som                            ("KGS"),
    @XmlEnumValue("LAK") Kip                            ("LAK"),
    @XmlEnumValue("LVL") LatvianLats                    ("LVL"),
    @XmlEnumValue("LBP") LebanesePound                  ("LBP"),
    @XmlEnumValue("LSL") Loti                           ("LSL"),
    @XmlEnumValue("ZAR") Rand                           ("ZAR"),
    @XmlEnumValue("LRD") LiberianDollar                 ("LRD"),
    @XmlEnumValue("LYD") LibyanDinar                    ("LYD"),
    @XmlEnumValue("CHF") SwissFranc                     ("CHF"),
    @XmlEnumValue("LTL") LithuanianLitas                ("LTL"),
    @XmlEnumValue("MOP") Pataca                         ("MOP"),
    @XmlEnumValue("MKD") Denar                          ("MKD"),
    @XmlEnumValue("MGA") MalagasyAriary                 ("MGA"),
    @XmlEnumValue("MWK") Kwacha                         ("MWK"),
    @XmlEnumValue("MYR") MalaysianRinggit               ("MYR"),
    @XmlEnumValue("MVR") Rufiyaa                        ("MVR"),
    @XmlEnumValue("MRO") Ouguiya                        ("MRO"),
    @XmlEnumValue("MUR") MauritiusRupee                 ("MUR"),
    @XmlEnumValue("MXN") MexicanPeso                    ("MXN"),
    @XmlEnumValue("MDL") MoldovanLeu                    ("MDL"),
    @XmlEnumValue("MNT") Tugrik                         ("MNT"),
    @XmlEnumValue("MAD") MoroccanDirham                 ("MAD"),
    @XmlEnumValue("MZN") Metical                        ("MZN"),
    @XmlEnumValue("MMK") Kyat                           ("MMK"),
    @XmlEnumValue("NAD") NamibiaDollar                  ("NAD"),
    @XmlEnumValue("NPR") NepaleseRupee                  ("NPR"),
    @XmlEnumValue("NIO") CordobaOro                     ("NIO"),
    @XmlEnumValue("NGN") Naira                          ("NGN"),
    @XmlEnumValue("OMR") RialOmani                      ("OMR"),
    @XmlEnumValue("PKR") PakistanRupee                  ("PKR"),
    @XmlEnumValue("PAB") Balboa                         ("PAB"),
    @XmlEnumValue("PGK") Kina                           ("PGK"),
    @XmlEnumValue("PYG") Guarani                        ("PYG"),
    @XmlEnumValue("PEN") NuevoSol                       ("PEN"),
    @XmlEnumValue("PHP") PhilippinePeso                 ("PHP"),
    @XmlEnumValue("PLN") Zloty                          ("PLN"),
    @XmlEnumValue("QAR") QatariRial                     ("QAR"),
    @XmlEnumValue("RON") Leu                            ("RON"),
    @XmlEnumValue("RUB") RussianRuble                   ("RUB"),
    @XmlEnumValue("RWF") RwandaFranc                    ("RWF"),
    @XmlEnumValue("SHP") SaintHelenaPound               ("SHP"),
    @XmlEnumValue("WST") Tala                           ("WST"),
    @XmlEnumValue("STD") Dobra                          ("STD"),
    @XmlEnumValue("SAR") SaudiRiyal                     ("SAR"),
    @XmlEnumValue("RSD") SerbianDinar                   ("RSD"),
    @XmlEnumValue("SCR") SeychellesRupee                ("SCR"),
    @XmlEnumValue("SLL") Leone                          ("SLL"),
    @XmlEnumValue("SGD") SingaporeDollar                ("SGD"),
    @XmlEnumValue("XSU") Sucre                          ("XSU"),
    @XmlEnumValue("SBD") SolomonIslandsDollar           ("SBD"),
    @XmlEnumValue("SOS") SomaliShilling                 ("SOS"),
    @XmlEnumValue("SSP") SouthSudanesePound             ("SSP"),
    @XmlEnumValue("LKR") SriLankaRupee                  ("LKR"),
    @XmlEnumValue("SDG") SudanesePound                  ("SDG"),
    @XmlEnumValue("SRD") SurinamDollar                  ("SRD"),
    @XmlEnumValue("SZL") Lilangeni                      ("SZL"),
    @XmlEnumValue("SEK") SwedishKrona                   ("SEK"),
    @XmlEnumValue("SYP") SyrianPound                    ("SYP"),
    @XmlEnumValue("TWD") NewTaiwanDollar                ("TWD"),
    @XmlEnumValue("TJS") Somoni                         ("TJS"),
    @XmlEnumValue("TZS") TanzanianShilling              ("TZS"),
    @XmlEnumValue("THB") Baht                           ("THB"),
    @XmlEnumValue("TOP") Paanga                         ("TOP"),
    @XmlEnumValue("TTD") TrinidadAndTobagoDollar	("TTD"),
    @XmlEnumValue("TND") TunisianDinar                  ("TND"),
    @XmlEnumValue("TRY") TurkishLira                    ("TRY"),
    @XmlEnumValue("TMT") NeManat                        ("TMT"),
    @XmlEnumValue("UGX") UgandaShilling                 ("UGX"),
    @XmlEnumValue("UAH") Hryvnia                        ("UAH"),
    @XmlEnumValue("AED") UAEDirham                      ("AED"),
    @XmlEnumValue("UYU") PesoUruguayo                   ("UYU"),
    @XmlEnumValue("UZS") UzbekistanSum                  ("UZS"),
    @XmlEnumValue("VUV") Vatu                           ("VUV"),
    @XmlEnumValue("VEF") BolivarFuerte                  ("VEF"),
    @XmlEnumValue("VND") Dong                           ("VND"),
    @XmlEnumValue("YER") YemeniRial                     ("YER"),
    @XmlEnumValue("ZMK") ZambianKwacha                  ("ZMK"),
    @XmlEnumValue("ZWL") ZimbabweDollar                 ("ZWL");

    private static final long serialVersionUID = 1L;
    
    private String value;

    private static final Map<String, Currency> stringToEnum = new HashMap<String, Currency>();

    static {
        for (Currency tag : values()) {
            stringToEnum.put(tag.getValue(), tag);
        }
    }

    /** Creates a new instance of Currency */
    Currency(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
    
    public static Currency valueFor(String value) {
        return stringToEnum.get(value);
    }
}
