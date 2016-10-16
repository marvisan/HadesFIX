/*
 *   Copyright (c) 2006-2008 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * AllocAckGroup50SP2TestData.java
 *
 * $Id: AllocAckGroup50SP2TestData.java,v 1.1 2011-02-17 09:21:28 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v50sp2;

import java.io.UnsupportedEncodingException;

import static org.junit.Assert.*;

import net.hades.fix.message.MsgTest;
import net.hades.fix.message.comp.impl.v50.NestedParties50TestData;
import net.hades.fix.message.comp.impl.v50sp2.NestedParties50SP2TestData;
import net.hades.fix.message.group.AllocAckGroup;
import net.hades.fix.message.type.AcctIDSource;
import net.hades.fix.message.type.AllocPositionEffect;
import net.hades.fix.message.type.IndividualAllocRejCode;
import net.hades.fix.message.type.IndividualAllocType;

/**
 * Test utility for AllocAckGroup50SP2 group class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 31/03/2009, 9:12:46 AM
 */
public class AllocAckGroup50SP2TestData extends MsgTest {

    private static final AllocAckGroup50SP2TestData INSTANCE;

    static {
        INSTANCE = new AllocAckGroup50SP2TestData();
    }

    public static AllocAckGroup50SP2TestData getInstance() {
        return INSTANCE;
    }

    public void populate1(AllocAckGroup grp) throws UnsupportedEncodingException {
        grp.setAllocAccount("ACCT384444");
        grp.setAllocAcctIDSource(AcctIDSource.SID);
        grp.setAllocPrice(26.33d);
        grp.setAllocPositionEffect(AllocPositionEffect.Close);
        grp.setIndividualAllocID("AALOC22222");
        grp.setIndividualAllocRejCode(IndividualAllocRejCode.MismatchedData);

        grp.setNestedParties();
        NestedParties50SP2TestData.getInstance().populate(grp.getNestedParties());

        grp.setAllocText("ALLOC1");
        grp.setEncodedAllocTextLen(new Integer(8));
        byte[] encodedText = new byte[] {(byte) 18, (byte) 32, (byte) 43, (byte) 95,
            (byte) 177, (byte) 198, (byte) 224, (byte) 253};
        grp.setEncodedAllocText(encodedText);
        grp.setSecondaryIndividualAllocID("SECALL99999");
        grp.setAllocCustomerCapacity("2");
        grp.setIndividualAllocType(IndividualAllocType.SubAllocate);
        grp.setAllocQty(34.50d);
    }

    public void populate2(AllocAckGroup grp) throws UnsupportedEncodingException {
        grp.setAllocAccount("ACCT3855555");
        grp.setAllocAcctIDSource(AcctIDSource.BIC);
        grp.setAllocPrice(26.77d);
        grp.setIndividualAllocID("AALOC44444");
        grp.setIndividualAllocRejCode(IndividualAllocRejCode.MismatchedData);

        grp.setNestedParties();
        NestedParties50TestData.getInstance().populate(grp.getNestedParties());

        grp.setAllocText("ALLOC2");
        grp.setEncodedAllocTextLen(new Integer(8));
        byte[] encodedText = new byte[] {(byte) 18, (byte) 44, (byte) 43, (byte) 95,
            (byte) 177, (byte) 21, (byte) 66, (byte) 253};
        grp.setEncodedAllocText(encodedText);
        grp.setSecondaryIndividualAllocID("SECALL88888");
        grp.setAllocCustomerCapacity("3");
        grp.setIndividualAllocType(IndividualAllocType.ThirdPartyAllocation);
        grp.setAllocQty(44.50d);
    }

    public void check(AllocAckGroup expected, AllocAckGroup actual) throws Exception {
        assertEquals(expected.getAllocAccount(), actual.getAllocAccount());
        assertEquals(expected.getAllocAcctIDSource(), actual.getAllocAcctIDSource());
        assertEquals(expected.getAllocPrice(), actual.getAllocPrice());
        assertEquals(expected.getAllocPositionEffect(), actual.getAllocPositionEffect());
        assertEquals(expected.getIndividualAllocID(), actual.getIndividualAllocID());
        assertEquals(expected.getIndividualAllocRejCode(), actual.getIndividualAllocRejCode());

        NestedParties50SP2TestData.getInstance().check(expected.getNestedParties(), actual.getNestedParties());

        assertEquals(expected.getAllocText(), actual.getAllocText());
        assertEquals(expected.getEncodedAllocTextLen().intValue(), actual.getEncodedAllocTextLen().intValue());
        assertArrayEquals(expected.getEncodedAllocText(), actual.getEncodedAllocText());
        assertEquals(expected.getSecondaryIndividualAllocID(), actual.getSecondaryIndividualAllocID());
        assertEquals(expected.getAllocCustomerCapacity(), actual.getAllocCustomerCapacity());
        assertEquals(expected.getIndividualAllocType(), actual.getIndividualAllocType());
        assertEquals(expected.getAllocQty(), actual.getAllocQty());
    }
}
