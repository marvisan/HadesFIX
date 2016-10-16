/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * ListExecuteMsg40Test.java
 *
 * $Id: ListExecuteMsg40Test.java,v 1.1 2011-02-05 08:52:45 vrotaru Exp $
 */
package net.hades.fix.message.impl.v40;

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
 * Test suite for FIX 4.0 ListExecuteMsg class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 01/05/2009, 12:18:17 PM
 */
public class ListExecuteMsg40Test extends MsgTest  {

    public ListExecuteMsg40Test() {
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
        ListExecuteMsg msg = (ListExecuteMsg) FIXMsgBuilder.build(MsgType.ListExecute.getValue(), BeginString.FIX_4_0);
        TestUtils.populate40HeaderAll(msg);
        msg.setListID("LST564567");
        msg.setWaveNo("WAVE-635454");
        msg.setText("some text");
        String encoded = new String(msg.encode(), DEFAULT_CHARACTER_SET);
        System.out.println("encoded-->" + encoded);

        ListExecuteMsg dmsg = (ListExecuteMsg) FIXMsgBuilder.build(encoded.getBytes(DEFAULT_CHARACTER_SET));
        dmsg.decode();
        assertEquals(msg.getListID(), dmsg.getListID());
        assertEquals(msg.getWaveNo(), dmsg.getWaveNo());
        assertEquals(msg.getText(), dmsg.getText());
    }

    /**
     * Test of encode method, of secured message.
     * @throws Exception
     */
    @Test
    public void b4_testEncDecSecureAll() throws Exception {
        System.out.println("-->testEncDecSecureAll");
        setSecuredDataDES();
        try {
            ListExecuteMsg msg = (ListExecuteMsg) FIXMsgBuilder.build(MsgType.ListExecute.getValue(), BeginString.FIX_4_0);
            TestUtils.populate40HeaderAll(msg);
            msg.setListID("LST564567");
            msg.setWaveNo("WAVE-635454");
            msg.setText("some text");
            String encoded = new String(msg.encode(), DEFAULT_CHARACTER_SET);
            System.out.println("encoded-->" + encoded);

            ListExecuteMsg dmsg = (ListExecuteMsg) FIXMsgBuilder.build(encoded.getBytes(DEFAULT_CHARACTER_SET));
            dmsg.decode();
            assertEquals(msg.getListID(), dmsg.getListID());
            assertEquals(msg.getWaveNo(), dmsg.getWaveNo());
            assertEquals(msg.getText(), dmsg.getText());
        } finally {
            unsetSecuredData();
        }
    }

    /**
     * Test of encode getter method, of class QuoteRequestMsg with unsupported tag.
     */
    @Test
    public void testGetUnsupportedMsgTag() {
        System.out.println("-->testGetUnsupportedMsgTag");
        ListExecuteMsg msg = null;
        try {
            msg = (ListExecuteMsg) FIXMsgBuilder.build(MsgType.ListExecute.getValue(), BeginString.FIX_4_0);
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
            msg = (ListExecuteMsg) FIXMsgBuilder.build(MsgType.ListExecute.getValue(), BeginString.FIX_4_0);
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
            ListExecuteMsg msg = (ListExecuteMsg) FIXMsgBuilder.build(MsgType.ListExecute.getValue(), BeginString.FIX_4_0);
            TestUtils.populate40HeaderAll(msg);
            msg.encode();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            assertEquals("Tag value(s) for [ListID] is missing.", ex.getMessage());
        }
    }

    // UTILITY MESSAGES
    /////////////////////////////////////////

    private void checkUnsupportedException(Exception ex) {
        assertEquals("This tag is not supported in [ListExecuteMsg] message version [4.0].", ex.getMessage());
    }
}
