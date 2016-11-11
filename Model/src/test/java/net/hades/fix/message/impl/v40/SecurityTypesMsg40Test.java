/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * SecurityTypesMsg40Test.java
 *
 * $Id: SecurityTypesMsg40Test.java,v 1.1 2011-04-27 01:09:57 vrotaru Exp $
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
import net.hades.fix.message.builder.FIXMsgBuilder;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.type.ApplVerID;
import net.hades.fix.message.type.BeginString;
import net.hades.fix.message.type.MsgType;

/**
 * Test suite for FIX 4.0 SecurityTypesMsg class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 01/05/2009, 12:18:17 PM
 */
public class SecurityTypesMsg40Test extends MsgTest  {

    public SecurityTypesMsg40Test() {
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

    @Test
    public void testEncode() throws Exception {
        System.out.println("-->testEncode");
        try {
            FIXMsgBuilder.build(MsgType.SecurityTypes.getValue(), BeginString.FIX_4_0);
            fail("Exception expected");
        } catch (InvalidMsgException ex) {
            checkUnsupportedException(ex);
        }
    }

    @Test
    public void testEncodeFIXT() throws Exception {
        System.out.println("-->testEncodeFIXT");
        try {
            FIXMsgBuilder.build(MsgType.SecurityTypes.getValue(), BeginString.FIXT_1_1, ApplVerID.FIX40);
            fail("Exception expected");
        } catch (InvalidMsgException ex) {
            checkUnsupportedExceptionFIXT(ex);
        }
    }

    // UTILITY MESSAGES
    /////////////////////////////////////////

    private void checkUnsupportedException(Exception ex) {
        assertEquals("Message [SecurityTypes] is not supported in version [FIX.4.0].", ex.getMessage());
    }

    private void checkUnsupportedExceptionFIXT(Exception ex) {
        assertEquals("Message [SecurityTypes] is not supported in version [FIXT.1.1/FIX40].", ex.getMessage());
    }
}
