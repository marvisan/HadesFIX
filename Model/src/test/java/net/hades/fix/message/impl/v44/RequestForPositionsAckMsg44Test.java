/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * RequestForPositionsAckMsg44Test.java
 *
 * $Id: RequestForPositionsAckMsg44Test.java,v 1.1 2011-02-16 11:24:33 vrotaru Exp $
 */
package net.hades.fix.message.impl.v44;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import net.hades.fix.TestUtils;
import net.hades.fix.message.MsgTest;
import net.hades.fix.message.RequestForPositionsAckMsg;
import net.hades.fix.message.builder.FIXMsgBuilder;
import net.hades.fix.message.impl.v44.data.RequestForPositionsAckMsg44TestData;
import net.hades.fix.message.type.BeginString;
import net.hades.fix.message.type.MsgType;

/**
 * Test suite for FIX 4.4 RequestForPositionsAckMsg class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 20/12/2011, 12:18:17 PM
 */
public class RequestForPositionsAckMsg44Test extends MsgTest  {

    public RequestForPositionsAckMsg44Test() {
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
        RequestForPositionsAckMsg msg = (RequestForPositionsAckMsg) FIXMsgBuilder.build(MsgType.RequestForPositionsAck.getValue(), BeginString.FIX_4_4);
        RequestForPositionsAckMsg44TestData.getInstance().populate(msg);
        String encoded = new String(msg.encode(), DEFAULT_CHARACTER_SET);
        System.out.println("encoded-->" + encoded);

        RequestForPositionsAckMsg dmsg = (RequestForPositionsAckMsg) FIXMsgBuilder.build(encoded.getBytes(DEFAULT_CHARACTER_SET));
        dmsg.decode();
        RequestForPositionsAckMsg44TestData.getInstance().check(msg, dmsg);
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
            RequestForPositionsAckMsg msg = (RequestForPositionsAckMsg) FIXMsgBuilder.build(MsgType.RequestForPositionsAck.getValue(), BeginString.FIX_4_4);
            RequestForPositionsAckMsg44TestData.getInstance().populate(msg);
            String encoded = new String(msg.encode(), DEFAULT_CHARACTER_SET);
            System.out.println("encoded-->" + encoded);

            RequestForPositionsAckMsg dmsg = (RequestForPositionsAckMsg) FIXMsgBuilder.build(encoded.getBytes(DEFAULT_CHARACTER_SET));
            dmsg.decode();
            RequestForPositionsAckMsg44TestData.getInstance().check(msg, dmsg);
        } finally {
            unsetSecuredData();
        }
    }

    /**
     * Test of encode getter method, of class RequestForPositionsAckMsg with unsupported tag.
     */
    @Test
    public void testGetUnsupportedMsgTag() {
        System.out.println("-->testGetUnsupportedMsgTag");
        RequestForPositionsAckMsg msg = null;
        try {
            msg = (RequestForPositionsAckMsg) FIXMsgBuilder.build(MsgType.RequestForPositionsAck.getValue(), BeginString.FIX_4_4);
        } catch (Exception ex) {
            fail("Error building message");
        }
    }

    /**
     * Test of encode setter method, of class RequestForPositionsAckMsg with unsupported tag.
     */
    @Test
    public void testSetUnsupportedMsgTag() {
        System.out.println("-->testSetUnsupportedMsgTag");
        RequestForPositionsAckMsg msg = null;
        try {
            msg = (RequestForPositionsAckMsg) FIXMsgBuilder.build(MsgType.RequestForPositionsAck.getValue(), BeginString.FIX_4_4);
        } catch (Exception ex) {
            fail("Error building message");
        }
    }

    // NEGATIVE TEST CASES
    /////////////////////////////////////////

    /**
     * Test of encode method, of class RequestForPositionsAckMsg with missing MDIncGroups data.
     */
    @Test
    public void testEncodeMissingRequired() {
        System.out.println("-->testEncodeMissingRequired");
        try {
            RequestForPositionsAckMsg msg = (RequestForPositionsAckMsg) FIXMsgBuilder.build(MsgType.RequestForPositionsAck.getValue(), BeginString.FIX_4_4);
            TestUtils.populate44HeaderAll(msg);
            msg.encode();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            assertEquals("Tag value(s) for [PosMaintRptID] [PosReqResult] [PosReqStatus] [Parties] [Account] [AccountType] is missing.", ex.getMessage());
        }
    }

    // UTILITY MESSAGES
    /////////////////////////////////////////

    private void checkUnsupportedException(Exception ex) {
        assertEquals("This tag is not supported in [RequestForPositionsAckMsg] message version [4.4].", ex.getMessage());
    }
}
