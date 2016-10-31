/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.6
 *               Use is subject to license terms.
 */

/*
 * ListExecuteMsg50SP2Test.java
 *
 * $Id: ListExecuteMsg50SP2Test.java,v 1.1 2011-02-05 08:52:44 vrotaru Exp $
 */
package net.hades.fix.message.impl.v50sp2;

import java.util.Calendar;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import net.hades.fix.TestUtils;
import net.hades.fix.message.MsgTest;
import net.hades.fix.message.ListExecuteMsg;
import net.hades.fix.message.builder.FIXMsgBuilder;
import net.hades.fix.message.type.BeginString;
import net.hades.fix.message.type.MsgType;

/**
 * Test suite for FIX 5.0SP2 ListExecuteMsg class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 01/05/2009, 12:18:17 PM
 */
public class ListExecuteMsg50SP2Test extends MsgTest  {

    public ListExecuteMsg50SP2Test() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
        TestUtils.enableValidation();
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of encode method, of secured message.
     * @throws Exception
     */
    @Test
    public void b3_testEncodeDecodeAll() throws Exception {
        System.out.println("-->testEncodeDecodeAll");
        ListExecuteMsg msg = (ListExecuteMsg) FIXMsgBuilder.build(MsgType.ListExecute.getValue(), BeginString.FIX_5_0SP2);
        TestUtils.populate50HeaderAll(msg);
        Calendar cal = Calendar.getInstance();
        msg.setListID("LST564567");
        msg.setClientBidID("CLIBID837474");
        msg.setBidID("BID98374634");
        cal.set(2010, 3, 14, 12, 13, 13);
        msg.setTransactTime(cal.getTime());
        msg.setWaveNo("WAVE-635454");
        msg.setText("some text");
        msg.setEncodedTextLen(new Integer(8));
        byte[] encodedText = new byte[] {(byte) 18, (byte) 33, (byte) 44, (byte) 96,
            (byte) 177, (byte) 199, (byte) 224, (byte) 253};
        msg.setEncodedText(encodedText);
        String encoded = new String(msg.encode(), DEFAULT_CHARACTER_SET);
        System.out.println("encoded-->" + encoded);

        ListExecuteMsg dmsg = (ListExecuteMsg) FIXMsgBuilder.build(encoded.getBytes(DEFAULT_CHARACTER_SET));
        dmsg.decode();
        assertEquals(msg.getListID(), dmsg.getListID());
        assertEquals(msg.getClientBidID(), dmsg.getClientBidID());
        assertEquals(msg.getBidID(), dmsg.getBidID());
        assertUTCTimestampEquals(msg.getTransactTime(), dmsg.getTransactTime(), false);
        assertEquals(msg.getWaveNo(), dmsg.getWaveNo());
        assertEquals(msg.getText(), dmsg.getText());
        assertEquals(msg.getEncodedTextLen(), dmsg.getEncodedTextLen());
        assertArrayEquals(msg.getEncodedText(), dmsg.getEncodedText());
    }

    /**
     * Test of encode getter method, of class QuoteRequestMsg with unsupported tag.
     */
    @Test
    public void testGetUnsupportedMsgTag() {
        System.out.println("-->testGetUnsupportedMsgTag");
        ListExecuteMsg msg = null;
        try {
            msg = (ListExecuteMsg) FIXMsgBuilder.build(MsgType.ListExecute.getValue(), BeginString.FIX_5_0SP2);
        } catch (Exception ex) {
            fail("Error building message");
        }
    }

    /**
     * Test of encode setter method, of class ListExecuteMsg with unsupported tag.
     */
    @Test
    public void testSetUnsupportedMsgTag() {
        System.out.println("-->testSetUnsupportedMsgTag");
        ListExecuteMsg msg = null;
        try {
            msg = (ListExecuteMsg) FIXMsgBuilder.build(MsgType.ListExecute.getValue(), BeginString.FIX_5_0SP2);
        } catch (Exception ex) {
            fail("Error building message");
        }
    }

    // NEGATIVE TEST CASES
    /////////////////////////////////////////

    /**
     * Test of encode method, of class ListExecuteMsg with missing MDIncGroups data.
     */
    @Test
    public void testEncodeMissingRequired() {
        System.out.println("-->testEncodeMissingRequired");
        try {
            ListExecuteMsg msg = (ListExecuteMsg) FIXMsgBuilder.build(MsgType.ListExecute.getValue(), BeginString.FIX_5_0SP2);
            TestUtils.populate40HeaderAll(msg);
            msg.encode();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            assertEquals("Tag value(s) for [ListID] [TransactTime] is missing.", ex.getMessage());
        }
    }

    // UTILITY MESSAGES
    /////////////////////////////////////////

    private void checkUnsupportedException(Exception ex) {
        assertEquals("This tag is not supported in [ListExecuteMsg] message version [5.0SP2].", ex.getMessage());
    }
}
