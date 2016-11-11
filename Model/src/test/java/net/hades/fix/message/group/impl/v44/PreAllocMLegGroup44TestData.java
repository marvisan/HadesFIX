/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * PreAllocMLegGroup44TestData.java
 *
 * $Id: PreAllocMLegGroup44TestData.java,v 1.2 2011-10-29 09:42:09 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v44;

import java.io.UnsupportedEncodingException;

import static org.junit.Assert.*;

import net.hades.fix.message.MsgTest;
import net.hades.fix.message.comp.impl.v44.NestedParties344TestData;
import net.hades.fix.message.group.PreAllocMLegGroup;
import net.hades.fix.message.type.AcctIDSource;
import net.hades.fix.message.type.Currency;

/**
 * Test utility for PreAllocMLegGroup43 group class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 * @created 11/04/2009, 10:08:38 AM
 */
public class PreAllocMLegGroup44TestData extends MsgTest {

    private static final PreAllocMLegGroup44TestData INSTANCE;

    static {
        INSTANCE = new PreAllocMLegGroup44TestData();
    }

    public static PreAllocMLegGroup44TestData getInstance() {
        return INSTANCE;
    }

    public void populate1(PreAllocMLegGroup group) throws UnsupportedEncodingException {
        group.setAllocAccount("53465465475");
        group.setAllocAcctIDSource(AcctIDSource.SID);
        group.setAllocSettlCurrency(Currency.UnitedStatesDollar);
        group.setIndividualAllocID("DD846555");
        
        group.setNestedParties3();
        NestedParties344TestData.getInstance().populate(group.getNestedParties3());
        
        group.setAllocQty(34.0d);
    }

    public void populate2(PreAllocMLegGroup group) throws UnsupportedEncodingException {
        group.setAllocAccount("92773553");
        group.setAllocAcctIDSource(AcctIDSource.BIC);
        group.setAllocSettlCurrency(Currency.AustralianDollar);
        group.setIndividualAllocID("AA846555");
        
        group.setNestedParties3();
        NestedParties344TestData.getInstance().populate(group.getNestedParties3());
        
        group.setAllocQty(14.0d);
    }

    public void check(PreAllocMLegGroup expected, PreAllocMLegGroup actual) throws Exception {
        assertEquals(expected.getAllocAccount(), actual.getAllocAccount());
        assertEquals(expected.getAllocAcctIDSource(), actual.getAllocAcctIDSource());
        assertEquals(expected.getAllocQty(), actual.getAllocQty());
        assertEquals(expected.getAllocQty(), actual.getAllocQty());
        
        NestedParties344TestData.getInstance().check(expected.getNestedParties3(), actual.getNestedParties3());
    }
}
