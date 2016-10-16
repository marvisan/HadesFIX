/*
 *   Copyright (c) 2006-2008 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * SettlInstructionsData44TestData.java
 *
 * $Id: SettlInstructionsData44TestData.java,v 1.1 2011-02-16 11:24:33 vrotaru Exp $
 */
package net.hades.fix.message.comp.impl.v44;

import static org.junit.Assert.*;

import net.hades.fix.message.MsgTest;
import net.hades.fix.message.comp.SettlInstructionsData;
import net.hades.fix.message.type.DlvyInstType;
import net.hades.fix.message.type.PartyIDSource;
import net.hades.fix.message.type.PartyRole;
import net.hades.fix.message.type.PartySubIDType;
import net.hades.fix.message.type.SettlDeliveryType;
import net.hades.fix.message.type.SettlInstSource;
import net.hades.fix.message.type.StandInstDbType;

/**
 * Test utility for SettlInstructionsData44 component class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 14/03/2009, 2:01:26 PM
 */
public class SettlInstructionsData44TestData extends MsgTest {

    private static final SettlInstructionsData44TestData INSTANCE;

    static {
        INSTANCE = new SettlInstructionsData44TestData();
    }

    public static SettlInstructionsData44TestData getInstance() {
        return INSTANCE;
    }

    public void populate(SettlInstructionsData comp) {
        comp.setSettlDeliveryType(SettlDeliveryType.TriParty);
        comp.setStandInstDbType(StandInstDbType.DTC_SID);
        comp.setStandInstDbName("DBS1");
        comp.setStandInstDbID("IDS");
        comp.setNoDlvyInst(2);
        comp.getDeliveryInstructionGroups()[0].setSettlInstSource(SettlInstSource.Investor);
        comp.getDeliveryInstructionGroups()[0].setDlvyInstType(DlvyInstType.Cash);

        comp.getDeliveryInstructionGroups()[0].setNoSettlPartyIDs(2);
        comp.getDeliveryInstructionGroups()[0].getSettlPartyGroups()[0].setSettlPartyID("SPID 1");
        comp.getDeliveryInstructionGroups()[0].getSettlPartyGroups()[0].setSettlPartyIDSource(PartyIDSource.Proprietary);
        comp.getDeliveryInstructionGroups()[0].getSettlPartyGroups()[0].setSettlPartyRole(PartyRole.RegulatoryBody);
        comp.getDeliveryInstructionGroups()[0].getSettlPartyGroups()[0].setNoSettlPartySubIDs(2);
        comp.getDeliveryInstructionGroups()[0].getSettlPartyGroups()[0].getSettlPartySubIDGroups()[0].setSettlPartySubID("SUB ID 1");
        comp.getDeliveryInstructionGroups()[0].getSettlPartyGroups()[0].getSettlPartySubIDGroups()[0].setSettlPartySubIDType(PartySubIDType.Application);
        comp.getDeliveryInstructionGroups()[0].getSettlPartyGroups()[0].getSettlPartySubIDGroups()[1].setSettlPartySubID("SUB ID 2");
        comp.getDeliveryInstructionGroups()[0].getSettlPartyGroups()[0].getSettlPartySubIDGroups()[1].setSettlPartySubIDType(PartySubIDType.BIC);
        
        comp.getDeliveryInstructionGroups()[0].getSettlPartyGroups()[1].setSettlPartyID("SPID 2");
        comp.getDeliveryInstructionGroups()[0].getSettlPartyGroups()[1].setSettlPartyIDSource(PartyIDSource.CSD);
        comp.getDeliveryInstructionGroups()[0].getSettlPartyGroups()[1].setSettlPartyRole(PartyRole.Agent);
        comp.getDeliveryInstructionGroups()[0].getSettlPartyGroups()[1].setNoSettlPartySubIDs(2);
        comp.getDeliveryInstructionGroups()[0].getSettlPartyGroups()[1].getSettlPartySubIDGroups()[0].setSettlPartySubID("SUB ID 3");
        comp.getDeliveryInstructionGroups()[0].getSettlPartyGroups()[1].getSettlPartySubIDGroups()[0].setSettlPartySubIDType(PartySubIDType.CashAccountName);
        comp.getDeliveryInstructionGroups()[0].getSettlPartyGroups()[1].getSettlPartySubIDGroups()[1].setSettlPartySubID("SUB ID 4");
        comp.getDeliveryInstructionGroups()[0].getSettlPartyGroups()[1].getSettlPartySubIDGroups()[1].setSettlPartySubIDType(PartySubIDType.System);

        comp.getDeliveryInstructionGroups()[1].setSettlInstSource(SettlInstSource.InstitutionInstructions);
        comp.getDeliveryInstructionGroups()[1].setDlvyInstType(DlvyInstType.Securities);

        comp.getDeliveryInstructionGroups()[1].setNoSettlPartyIDs(2);
        comp.getDeliveryInstructionGroups()[1].getSettlPartyGroups()[0].setSettlPartyID("SPID 3");
        comp.getDeliveryInstructionGroups()[1].getSettlPartyGroups()[0].setSettlPartyIDSource(PartyIDSource.BIC);
        comp.getDeliveryInstructionGroups()[1].getSettlPartyGroups()[0].setSettlPartyRole(PartyRole.ExecutingFirm);
        comp.getDeliveryInstructionGroups()[1].getSettlPartyGroups()[0].setNoSettlPartySubIDs(2);
        comp.getDeliveryInstructionGroups()[1].getSettlPartyGroups()[0].getSettlPartySubIDGroups()[0].setSettlPartySubID("SUB ID 10");
        comp.getDeliveryInstructionGroups()[1].getSettlPartyGroups()[0].getSettlPartySubIDGroups()[0].setSettlPartySubIDType(PartySubIDType.CashAccountName);
        comp.getDeliveryInstructionGroups()[1].getSettlPartyGroups()[0].getSettlPartySubIDGroups()[1].setSettlPartySubID("SUB ID 20");
        comp.getDeliveryInstructionGroups()[1].getSettlPartyGroups()[0].getSettlPartySubIDGroups()[1].setSettlPartySubIDType(PartySubIDType.BIC);

        comp.getDeliveryInstructionGroups()[1].getSettlPartyGroups()[1].setSettlPartyID("SPID 2");
        comp.getDeliveryInstructionGroups()[1].getSettlPartyGroups()[1].setSettlPartyIDSource(PartyIDSource.TaiwaneseTradingAcct);
        comp.getDeliveryInstructionGroups()[1].getSettlPartyGroups()[1].setSettlPartyRole(PartyRole.AssetManager);
        comp.getDeliveryInstructionGroups()[1].getSettlPartyGroups()[1].setNoSettlPartySubIDs(2);
        comp.getDeliveryInstructionGroups()[1].getSettlPartyGroups()[1].getSettlPartySubIDGroups()[0].setSettlPartySubID("SUB ID 30");
        comp.getDeliveryInstructionGroups()[1].getSettlPartyGroups()[1].getSettlPartySubIDGroups()[0].setSettlPartySubIDType(PartySubIDType.CashAccountName);
        comp.getDeliveryInstructionGroups()[1].getSettlPartyGroups()[1].getSettlPartySubIDGroups()[1].setSettlPartySubID("SUB ID 40");
        comp.getDeliveryInstructionGroups()[1].getSettlPartyGroups()[1].getSettlPartySubIDGroups()[1].setSettlPartySubIDType(PartySubIDType.SecuritiesAccountNumber);
    }

    public void check(SettlInstructionsData expected, SettlInstructionsData actual) throws Exception {
        assertEquals(expected.getSettlDeliveryType(), actual.getSettlDeliveryType());
        assertEquals(expected.getStandInstDbType(), actual.getStandInstDbType());
        assertEquals(expected.getStandInstDbName(), actual.getStandInstDbName());
        assertEquals(expected.getStandInstDbID(), actual.getStandInstDbID());
        assertEquals(expected.getNoDlvyInst(), actual.getNoDlvyInst());

        for (int i = 0; i < actual.getNoDlvyInst().intValue(); i++) {
            assertEquals(expected.getDeliveryInstructionGroups()[i].getSettlInstSource(), actual.getDeliveryInstructionGroups()[i].getSettlInstSource());
            assertEquals(expected.getDeliveryInstructionGroups()[i].getDlvyInstType(), actual.getDeliveryInstructionGroups()[i].getDlvyInstType());
            assertEquals(expected.getDeliveryInstructionGroups()[i].getNoSettlPartyIDs(), actual.getDeliveryInstructionGroups()[i].getNoSettlPartyIDs());
            for (int j = 0; j < expected.getDeliveryInstructionGroups()[i].getNoSettlPartyIDs().intValue(); j++) {
                assertEquals(expected.getDeliveryInstructionGroups()[i].getSettlPartyGroups()[j].getSettlPartyID(),
                        actual.getDeliveryInstructionGroups()[i].getSettlPartyGroups()[j].getSettlPartyID());
                assertEquals(expected.getDeliveryInstructionGroups()[i].getSettlPartyGroups()[j].getSettlPartyIDSource(),
                        actual.getDeliveryInstructionGroups()[i].getSettlPartyGroups()[j].getSettlPartyIDSource());
                assertEquals(expected.getDeliveryInstructionGroups()[i].getSettlPartyGroups()[j].getSettlPartyRole(),
                        actual.getDeliveryInstructionGroups()[i].getSettlPartyGroups()[j].getSettlPartyRole());
                assertEquals(expected.getDeliveryInstructionGroups()[i].getSettlPartyGroups()[j].getNoSettlPartySubIDs(),
                        actual.getDeliveryInstructionGroups()[i].getSettlPartyGroups()[j].getNoSettlPartySubIDs());
                for (int k = 0; k < expected.getDeliveryInstructionGroups()[1].getSettlPartyGroups()[j].getNoSettlPartySubIDs().intValue(); k++) {
                    assertEquals(expected.getDeliveryInstructionGroups()[i].getSettlPartyGroups()[j].getSettlPartySubIDGroups()[k].getSettlPartySubID(),
                        actual.getDeliveryInstructionGroups()[i].getSettlPartyGroups()[j].getSettlPartySubIDGroups()[k].getSettlPartySubID());
                    assertEquals(expected.getDeliveryInstructionGroups()[i].getSettlPartyGroups()[j].getSettlPartySubIDGroups()[k].getSettlPartySubIDType(),
                        actual.getDeliveryInstructionGroups()[i].getSettlPartyGroups()[j].getSettlPartySubIDGroups()[k].getSettlPartySubIDType());
                }
            }
        }
    }
}
