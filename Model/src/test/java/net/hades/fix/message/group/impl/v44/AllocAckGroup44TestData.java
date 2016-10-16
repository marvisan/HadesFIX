/*
 *   Copyright (c) 2006-2008 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * AllocAckGroup44TestData.java
 *
 * $Id: AllocAckGroup44TestData.java,v 1.1 2011-02-17 09:21:29 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v44;

import java.io.UnsupportedEncodingException;

import static org.junit.Assert.*;

import net.hades.fix.message.MsgTest;
import net.hades.fix.message.group.AllocAckGroup;
import net.hades.fix.message.type.AcctIDSource;
import net.hades.fix.message.type.IndividualAllocRejCode;

/**
 * Test utility for AllocAckGroup44 group class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 31/03/2009, 9:12:46 AM
 */
public class AllocAckGroup44TestData extends MsgTest {

    private static final AllocAckGroup44TestData INSTANCE;

    static {
        INSTANCE = new AllocAckGroup44TestData();
    }

    public static AllocAckGroup44TestData getInstance() {
        return INSTANCE;
    }

    public void populate1(AllocAckGroup grp) throws UnsupportedEncodingException {
        grp.setAllocAccount("ACCT384444");
        grp.setAllocAcctIDSource(AcctIDSource.SID);
        grp.setAllocPrice(26.33d);
        grp.setIndividualAllocID("AALOC22222");
        grp.setIndividualAllocRejCode(IndividualAllocRejCode.MismatchedData);
        grp.setAllocText("ALLOC1");
        grp.setEncodedAllocTextLen(new Integer(8));
        byte[] encodedText = new byte[] {(byte) 18, (byte) 32, (byte) 43, (byte) 95,
            (byte) 177, (byte) 198, (byte) 224, (byte) 253};
        grp.setEncodedAllocText(encodedText);
    }

    public void populate2(AllocAckGroup grp) throws UnsupportedEncodingException {
        grp.setAllocAccount("ACCT3855555");
        grp.setAllocAcctIDSource(AcctIDSource.BIC);
        grp.setAllocPrice(26.77d);
        grp.setIndividualAllocID("AALOC44444");
        grp.setIndividualAllocRejCode(IndividualAllocRejCode.MismatchedData);
        grp.setAllocText("ALLOC2");
        grp.setEncodedAllocTextLen(new Integer(8));
        byte[] encodedText = new byte[] {(byte) 18, (byte) 44, (byte) 43, (byte) 95,
            (byte) 177, (byte) 21, (byte) 66, (byte) 253};
        grp.setEncodedAllocText(encodedText);
    }

    public void check(AllocAckGroup expected, AllocAckGroup actual) throws Exception {
        assertEquals(expected.getAllocAccount(), actual.getAllocAccount());
        assertEquals(expected.getAllocAcctIDSource(), actual.getAllocAcctIDSource());
        assertEquals(expected.getAllocPrice(), actual.getAllocPrice());
        assertEquals(expected.getIndividualAllocID(), actual.getIndividualAllocID());
        assertEquals(expected.getIndividualAllocRejCode(), actual.getIndividualAllocRejCode());
        assertEquals(expected.getAllocText(), actual.getAllocText());
        assertEquals(expected.getEncodedAllocTextLen().intValue(), actual.getEncodedAllocTextLen().intValue());
        assertArrayEquals(expected.getEncodedAllocText(), actual.getEncodedAllocText());
    }
}
