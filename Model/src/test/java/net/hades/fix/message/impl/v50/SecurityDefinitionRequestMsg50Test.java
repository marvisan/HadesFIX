/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * SecurityDefinitionRequestMsg50Test.java
 *
 * $Id: SecurityDefinitionRequestMsg50Test.java,v 1.1 2011-04-16 09:39:03 vrotaru Exp $
 */
package net.hades.fix.message.impl.v50;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import net.hades.fix.TestUtils;
import net.hades.fix.message.MsgTest;
import net.hades.fix.message.SecurityDefinitionRequestMsg;
import net.hades.fix.message.builder.FIXMsgBuilder;
import net.hades.fix.message.impl.v50.data.SecurityDefinitionRequestMsg50TestData;
import net.hades.fix.message.type.BeginString;
import net.hades.fix.message.type.MsgType;

/**
 * Test suite for FIX 5.0 SecurityDefinitionRequestMsg class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 01/05/2009, 12:18:17 PM
 */
public class SecurityDefinitionRequestMsg50Test extends MsgTest  {

    public SecurityDefinitionRequestMsg50Test() {
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
        SecurityDefinitionRequestMsg msg = (SecurityDefinitionRequestMsg) FIXMsgBuilder.build(MsgType.SecurityDefinitionRequest.getValue(), BeginString.FIX_5_0);
        SecurityDefinitionRequestMsg50TestData.getInstance().populate(msg);
        String encoded = new String(msg.encode(), DEFAULT_CHARACTER_SET);
        System.out.println("encoded-->" + encoded);

        SecurityDefinitionRequestMsg dmsg = (SecurityDefinitionRequestMsg) FIXMsgBuilder.build(encoded.getBytes(DEFAULT_CHARACTER_SET));
        dmsg.decode();
        SecurityDefinitionRequestMsg50TestData.getInstance().check(msg, dmsg);
    }

    /**
     * Test of encode getter method, of class QuoteRequestMsg with unsupported tag.
     */
    @Test
    public void testGetUnsupportedMsgTag() {
        System.out.println("-->testGetUnsupportedMsgTag");
        SecurityDefinitionRequestMsg msg = null;
        try {
            msg = (SecurityDefinitionRequestMsg) FIXMsgBuilder.build(MsgType.SecurityDefinitionRequest.getValue(), BeginString.FIX_5_0);
        } catch (Exception ex) {
            fail("Error building message");
        }

        try {
            msg.getStipulations();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            checkUnsupportedException(ex);
        }
        try {
            msg.getNoRelatedSyms();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            checkUnsupportedException(ex);
        }
        try {
            msg.getUndlySecurityGroups();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            checkUnsupportedException(ex);
        }
        try {
            msg.getSpreadOrBenchmarkCurveData();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            checkUnsupportedException(ex);
        }
        try {
            msg.getYieldData();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            checkUnsupportedException(ex);
        }
    }

    /**
     * Test of encode setter method, of class SecurityDefinitionRequestMsg with unsupported tag.
     */
    @Test
    public void testSetUnsupportedMsgTag() {
        System.out.println("-->testSetUnsupportedMsgTag");
        SecurityDefinitionRequestMsg msg = null;
        try {
            msg = (SecurityDefinitionRequestMsg) FIXMsgBuilder.build(MsgType.SecurityDefinitionRequest.getValue(), BeginString.FIX_5_0);
        } catch (Exception ex) {
            fail("Error building message");
        }

        try {
            msg.setStipulations();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            checkUnsupportedException(ex);
        }
        try {
            msg.setNoRelatedSyms(null);
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            checkUnsupportedException(ex);
        }
        try {
            msg.addUndlySecurityGroup();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            checkUnsupportedException(ex);
        }
        try {
            msg.deleteUndlySecurityGroup(1);
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            checkUnsupportedException(ex);
        }
        try {
            msg.clearUndlySecurityGroup();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            checkUnsupportedException(ex);
        }
        try {
            msg.setSpreadOrBenchmarkCurveData();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            checkUnsupportedException(ex);
        }
        try {
            msg.setYieldData();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            checkUnsupportedException(ex);
        }
    }

    // NEGATIVE TEST CASES
    /////////////////////////////////////////

    /**
     * Test of encode method, of class SecurityDefinitionRequestMsg with missing MDIncGroups data.
     */
    @Test
    public void testEncodeMissingRequired() {
        System.out.println("-->testEncodeMissingRequired");
        try {
            SecurityDefinitionRequestMsg msg = (SecurityDefinitionRequestMsg) FIXMsgBuilder.build(MsgType.SecurityDefinitionRequest.getValue(), BeginString.FIX_5_0);
            TestUtils.populate44HeaderAll(msg);
            msg.encode();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            assertEquals("Tag value(s) for [SecurityReqID] [SecurityRequestType] is missing.", ex.getMessage());
        }
    }

    // UTILITY MESSAGES
    /////////////////////////////////////////

    private void checkUnsupportedException(Exception ex) {
        assertEquals("This tag is not supported in [SecurityDefinitionRequestMsg] message version [5.0].", ex.getMessage());
    }
}
