/*
 *   Copyright (c) 2006-2008 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * PartyRole.java
 *
 * $Id: PartyRole.java,v 1.4 2010-02-25 08:37:26 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Identifies the type or role of the PartyID.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.4 $
 * @created 07/12/2008, 1:09:09 PM
 */
@XmlType
@XmlEnum(Integer.class)
public enum PartyRole {

    @XmlEnumValue("1") ExecutingFirm                                    (1),
    @XmlEnumValue("2") BrokerOfCredit                                   (2),
    @XmlEnumValue("3") ClientID                                         (3),
    @XmlEnumValue("4") ClearingFirm                                     (4),
    @XmlEnumValue("5") InvestorID                                       (5),
    @XmlEnumValue("6") IntroducingFirm                                  (6),
    @XmlEnumValue("7") EnteringFirm                                     (7),
    @XmlEnumValue("8") LendingFirm                                      (8),
    @XmlEnumValue("9") FundManagerClientID                              (9),
    @XmlEnumValue("10") SettlementLocation                              (10),
    @XmlEnumValue("11") OrderOriginationTrader                          (11),
    @XmlEnumValue("12") ExecutingTrader                                 (12),
    @XmlEnumValue("13") OrderOriginationFirm                            (13),
    @XmlEnumValue("14") GiveupClearingFirm                              (14),
    @XmlEnumValue("15") CorrespondantClearingFirm                       (15),
    @XmlEnumValue("16") ExecutingSystem                                 (16),
    @XmlEnumValue("17") ContraFirm                                      (17),
    @XmlEnumValue("18") ContraClearingFirm                              (18),
    @XmlEnumValue("19") SponsoringFirm                                  (19),
    @XmlEnumValue("20") UnderlyingContraFirm                            (20),
    @XmlEnumValue("21") ClearingOrganization                            (21),
    @XmlEnumValue("22") Exchange                                        (22),
    @XmlEnumValue("24") CustomerAccount                                 (24),
    @XmlEnumValue("25") CorrespondentClearingOrganization               (25),
    @XmlEnumValue("26") CorrespondentBroker                             (26),
    @XmlEnumValue("27") BuyerSeller                                     (27),
    @XmlEnumValue("28") Custodian                                       (28),
    @XmlEnumValue("29") Intermediary                                    (29),
    @XmlEnumValue("30") Agent                                           (30),
    @XmlEnumValue("31") Subcustodian                                    (31),
    @XmlEnumValue("32") Beneficiary                                     (32),
    @XmlEnumValue("33") InterestedParty                                 (33),
    @XmlEnumValue("34") RegulatoryBody                                  (34),
    @XmlEnumValue("35") LiquidityProvider                               (35),
    @XmlEnumValue("36") EnteringTrader                                  (36),
    @XmlEnumValue("37") ContraTrader                                    (37),
    @XmlEnumValue("38") PositionAccount                                 (38),
    @XmlEnumValue("39") ContraInvestorID                                (39),
    @XmlEnumValue("40") TransferToFirm                                  (40),
    @XmlEnumValue("42") ContraPositionAccount                           (41),
    @XmlEnumValue("42") ContraExchange                                  (42),
    @XmlEnumValue("43") InternalCarryAccount                            (43),
    @XmlEnumValue("44") OrderEntryOperatorID                            (44),
    @XmlEnumValue("45") SecondaryAccountNumber                          (45),
    @XmlEnumValue("46") ForiegnFirm                                     (46),
    @XmlEnumValue("47") ThirdPartyAllocationFirm                        (47),
    @XmlEnumValue("48") ClaimingAccount                                 (48),
    @XmlEnumValue("49") AssetManager                                    (49),
    @XmlEnumValue("50") PledgorAccount                                  (50),
    @XmlEnumValue("51") PledgeeAccount                                  (51),
    @XmlEnumValue("52") LargeTraderReportableAccount                    (52),
    @XmlEnumValue("53") TraderMnemonic                                  (53),
    @XmlEnumValue("54") SenderLocation                                  (54),
    @XmlEnumValue("55") SessionID                                       (55),
    @XmlEnumValue("56") AcceptableCounterparty                          (56),
    @XmlEnumValue("57") UnacceptableCounterparty                        (57),
    @XmlEnumValue("58") EnteringUnit                                    (58),
    @XmlEnumValue("59") ExecutingUnit                                   (59),
    @XmlEnumValue("60") IntroducingBroker                               (60),
    @XmlEnumValue("61") QuoteOriginator                                 (61),
    @XmlEnumValue("62") ReportOriginator                                (62),
    @XmlEnumValue("63") SystematicInternaliser                          (63),
    @XmlEnumValue("64") MultilateralTradingFacility                     (64),
    @XmlEnumValue("65") RegulatedMarket                                 (65),
    @XmlEnumValue("66") MarketMaker                                     (66),
    @XmlEnumValue("67") InvestmentFirm                                  (67),
    @XmlEnumValue("68") HostCompetentAuthority                          (68),
    @XmlEnumValue("69") HomeCompetentAuthority                          (69),
    @XmlEnumValue("70") CompetentAuthorityLiquidity                     (70),
    @XmlEnumValue("71") CompetentAuthorityTransactionVenue              (71),
    @XmlEnumValue("72") ReportingIntermediary                           (72),
    @XmlEnumValue("73") ExecutionVenue                                  (73),
    @XmlEnumValue("74") MarketDataEntryOriginator                       (74),
    @XmlEnumValue("75") LocationID                                      (75),
    @XmlEnumValue("76") DeskID                                          (76),
    @XmlEnumValue("77") MarketDataMarket                                (77),
    @XmlEnumValue("78") AllocationEntity                                (78),
    @XmlEnumValue("79") PrimeBrokerGeneralTradeServices                 (79),
    @XmlEnumValue("80") StepOutFirm                                     (80),
    @XmlEnumValue("81") BrokerClearingID                                (81);

    private static final long serialVersionUID = 1L;

    private int value;

    private static final Map<String, PartyRole> stringToEnum = new HashMap<String, PartyRole>();

    static {
        for (PartyRole tag : values()) {
            stringToEnum.put(String.valueOf(tag.getValue()), tag);
        }
    }

    /** Creates a new instance of PartyRole */
    PartyRole(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static PartyRole valueFor(int value) {
        return stringToEnum.get(String.valueOf(value));
    }
}
