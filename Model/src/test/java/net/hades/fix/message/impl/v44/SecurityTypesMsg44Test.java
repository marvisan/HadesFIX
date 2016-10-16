/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * SecurityTypesMsg44Test.java
 *
 * $Id: SecurityTypesMsg44Test.java,v 1.1 2011-04-27 01:09:57 vrotaru Exp $
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
import net.hades.fix.message.SecurityTypesMsg;
import net.hades.fix.message.builder.FIXMsgBuilder;
import net.hades.fix.message.impl.v44.data.SecurityTypesMsg44TestData;
import net.hades.fix.message.type.BeginString;
import net.hades.fix.message.type.MsgType;

/**
 * Test suite for FIX 4.4 SecurityTypesMsg class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 01/05/2009, 12:18:17 PM
 */
public class SecurityTypesMsg44Test extends MsgTest {

    public SecurityTypesMsg44Test() {
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
        SecurityTypesMsg msg = (SecurityTypesMsg) FIXMsgBuilder.build(MsgType.SecurityTypes.getValue(), BeginString.FIX_4_4);
        SecurityTypesMsg44TestData.getInstance().populate(msg);
        String encoded = new String(msg.encode(), DEFAULT_CHARACTER_SET);
        System.out.println("encoded-->" + encoded);

        SecurityTypesMsg dmsg = (SecurityTypesMsg) FIXMsgBuilder.build(encoded.getBytes(DEFAULT_CHARACTER_SET));
        dmsg.decode();
        SecurityTypesMsg44TestData.getInstance().check(msg, dmsg);
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
            SecurityTypesMsg msg = (SecurityTypesMsg) FIXMsgBuilder.build(MsgType.SecurityTypes.getValue(), BeginString.FIX_4_4);
            SecurityTypesMsg44TestData.getInstance().populate(msg);
            String encoded = new String(msg.encode(), DEFAULT_CHARACTER_SET);
            System.out.println("encoded-->" + encoded);

            SecurityTypesMsg dmsg = (SecurityTypesMsg) FIXMsgBuilder.build(encoded.getBytes(DEFAULT_CHARACTER_SET));
            dmsg.decode();
            SecurityTypesMsg44TestData.getInstance().check(msg, dmsg);
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
        SecurityTypesMsg msg = null;
        try {
            msg = (SecurityTypesMsg) FIXMsgBuilder.build(MsgType.SecurityTypes.getValue(), BeginString.FIX_4_4);
        } catch (Exception ex) {
            fail("Error building message");
        }

        try {
            msg.getApplicationSequenceControl();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            checkUnsupportedException(ex);
        }
    }

    /**
     * Test of encode setter method, of class SecurityTypesMsg with unsupported tag.
     */
    @Test
    public void testSetUnsupportedMsgTag() {
        System.out.println("-->testSetUnsupportedMsgTag");
        SecurityTypesMsg msg = null;
        try {
            msg = (SecurityTypesMsg) FIXMsgBuilder.build(MsgType.SecurityTypes.getValue(), BeginString.FIX_4_4);
        } catch (Exception ex) {
            fail("Error building message");
        }

        try {
            msg.setApplicationSequenceControl();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            checkUnsupportedException(ex);
        }
        try {
            msg.clearApplicationSequenceControl();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            checkUnsupportedException(ex);
        }
    }

    // NEGATIVE TEST CASES
    /////////////////////////////////////////

    /**
     * Test of encode method, of class SecurityTypesMsg with missing data.
     */
    @Test
    public void testEncodeMissingRequired() {
        System.out.println("-->testEncodeMissingRequired");
        try {
            SecurityTypesMsg msg = (SecurityTypesMsg) FIXMsgBuilder.build(MsgType.SecurityTypes.getValue(), BeginString.FIX_4_4);
            TestUtils.populate44HeaderAll(msg);
            msg.encode();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            assertEquals("Tag value(s) for [SecurityReqID] [SecurityResponseID] [SecurityResponseType] is missing.", ex.getMessage());
        }
    }

    // UTILITY MESSAGES
    /////////////////////////////////////////

    private void checkUnsupportedException(Exception ex) {
        assertEquals("This tag is not supported in [SecurityTypesMsg] message version [4.4].", ex.getMessage());
    }
}
