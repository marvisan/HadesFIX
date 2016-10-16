/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * RegistrationInstructionsResponseMsg44Test.java
 *
 * $Id: RegistrationInstructionsResponseMsg44Test.java,v 1.1 2011-10-29 02:54:35 vrotaru Exp $
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
import net.hades.fix.message.RegistrationInstructionsResponseMsg;
import net.hades.fix.message.builder.FIXMsgBuilder;
import net.hades.fix.message.impl.v44.data.RegistrationInstructionsResponseMsg44TestData;
import net.hades.fix.message.type.BeginString;
import net.hades.fix.message.type.MsgType;

/**
 * Test suite for FIX 4.3 RegistrationInstructionsResponseMsg class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 01/05/2009, 12:18:17 PM
 */
public class RegistrationInstructionsResponseMsg44Test extends MsgTest  {

    public RegistrationInstructionsResponseMsg44Test() {
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
        RegistrationInstructionsResponseMsg msg = (RegistrationInstructionsResponseMsg) FIXMsgBuilder.build(MsgType.RegistrationInstructionsResponse.getValue(), BeginString.FIX_4_4);
        RegistrationInstructionsResponseMsg44TestData.getInstance().populate(msg);
        String encoded = new String(msg.encode(), DEFAULT_CHARACTER_SET);
        System.out.println("encoded-->" + encoded);

        RegistrationInstructionsResponseMsg dmsg = (RegistrationInstructionsResponseMsg) FIXMsgBuilder.build(encoded.getBytes(DEFAULT_CHARACTER_SET));
        dmsg.decode();
        RegistrationInstructionsResponseMsg44TestData.getInstance().check(msg, dmsg);
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
            RegistrationInstructionsResponseMsg msg = (RegistrationInstructionsResponseMsg) FIXMsgBuilder.build(MsgType.RegistrationInstructionsResponse.getValue(), BeginString.FIX_4_4);
            RegistrationInstructionsResponseMsg44TestData.getInstance().populate(msg);
            String encoded = new String(msg.encode(), DEFAULT_CHARACTER_SET);
            System.out.println("encoded-->" + encoded);

            RegistrationInstructionsResponseMsg dmsg = (RegistrationInstructionsResponseMsg) FIXMsgBuilder.build(encoded.getBytes(DEFAULT_CHARACTER_SET));
            dmsg.decode();
            RegistrationInstructionsResponseMsg44TestData.getInstance().check(msg, dmsg);
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
        RegistrationInstructionsResponseMsg msg = null;
        try {
            msg = (RegistrationInstructionsResponseMsg) FIXMsgBuilder.build(MsgType.RegistrationInstructionsResponse.getValue(), BeginString.FIX_4_4);
        } catch (Exception ex) {
            fail("Error building message");
        }
    }

    /**
     * Test of encode setter method, of class RegistrationInstructionsResponseMsg with unsupported tag.
     */
    @Test
    public void testSetUnsupportedMsgTag() {
        System.out.println("-->testSetUnsupportedMsgTag");
        RegistrationInstructionsResponseMsg msg = null;
        try {
            msg = (RegistrationInstructionsResponseMsg) FIXMsgBuilder.build(MsgType.RegistrationInstructionsResponse.getValue(), BeginString.FIX_4_4);
        } catch (Exception ex) {
            fail("Error building message");
        }
    }

    // NEGATIVE TEST CASES
    /////////////////////////////////////////

    /**
     * Test of encode method, of class RegistrationInstructionsResponseMsg with missing MDIncGroups data.
     */
    @Test
    public void testEncodeMissingRequired() {
        System.out.println("-->testEncodeMissingRequired");
        try {
            RegistrationInstructionsResponseMsg msg = (RegistrationInstructionsResponseMsg) FIXMsgBuilder.build(MsgType.RegistrationInstructionsResponse.getValue(), BeginString.FIX_4_4);
            TestUtils.populate44HeaderAll(msg);
            msg.encode();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            assertEquals("Tag value(s) for [RegistID] [RegistTransType] [RegistRefID] [RegistStatus] is missing.", ex.getMessage());
        }
    }

    // UTILITY MESSAGES
    /////////////////////////////////////////

    private void checkUnsupportedException(Exception ex) {
        assertEquals("This tag is not supported in [RegistrationInstructionsResponseMsg] message version [4.4].", ex.getMessage());
    }
}
