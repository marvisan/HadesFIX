/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * TaxAdvantageType.java
 *
 * $Id: TaxAdvantageType.java,v 1.3 2010-01-14 09:06:47 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;

/**
 * Code identifying the type of tax exempt account.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.3 $
 * @created 22/09/2009, 9:25:41 PM
 */
public enum TaxAdvantageType {

    None                                (0),
    MaxiISA                             (1),
    TESSA                               (2),
    MiniCashISA                         (3),
    MiniStocksISA                       (4),
    MiniInsuranceISA                    (5),
    CurrentYearPayment                  (6),
    PriorYearPayment                    (7),
    AssetTransfer                       (8),
    EmployeePriorYear                   (9),
    EmployeeCurrentYear                 (10),
    EmployerPriorYear                   (11),
    EmployerCurrentYear                 (12),
    NonFundPrototypeIRA                 (13),
    NonFundQualifiedPlan                (14),
    DefinedContributionPlan             (15),
    IndivRetirementAcct                 (16),
    IndivRetirementAcctRollover         (17),
    KEOGH                               (18),
    ProfitSharingPlan                   (19),
    K401                                (20),
    SelfDirectedIRA                     (21),
    B403                                (22),
    US457                               (23),
    RothIRAFundPrototype                (24),
    RothIRANonPrototype                 (25),
    RothConvIRAFundPrototype            (26),
    RothConvIRANonPrototype             (27),
    EducationIRAFundPrototype           (28),
    EducationIRANonPrototype            (29),
    Other                               (999);

    private static final long serialVersionUID = -5306313203829253991L;

    private int value;

    private static final Map<String, TaxAdvantageType> stringToEnum = new HashMap<String, TaxAdvantageType>();

    static {
        for (TaxAdvantageType tag : values()) {
            stringToEnum.put(String.valueOf(tag.getValue()), tag);
        }
    }

    /** Creates a new instance of TaxAdvantageType */
    TaxAdvantageType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static TaxAdvantageType valueFor(int value) {
        return stringToEnum.get(String.valueOf(value));
    }
}
