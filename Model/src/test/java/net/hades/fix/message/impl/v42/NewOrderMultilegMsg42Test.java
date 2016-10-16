/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * NewOrderMultilegMsg42Test.java
 *
 * $Id: NewOrderMultilegMsg42Test.java,v 1.1 2011-09-08 08:54:48 vrotaru Exp $
 */
package net.hades.fix.message.impl.v42;

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
 * Test suite for FIX 4.2 NewOrderMultilegMsg class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 12/05/2011, 12:18:17 PM
 */
public class NewOrderMultilegMsg42Test extends MsgTest  {

    public NewOrderMultilegMsg42Test() {
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
            FIXMsgBuilder.build(MsgType.NewOrderMultileg.getValue(), BeginString.FIX_4_2);
            fail("Exception expected");
        } catch (InvalidMsgException ex) {
            checkUnsupportedException(ex);
        }
    }

    @Test
    public void testEncodeFIXT() throws Exception {
        System.out.println("-->testEncodeFIXT");
        try {
            FIXMsgBuilder.build(MsgType.NewOrderMultileg.getValue(), BeginString.FIXT_1_1, ApplVerID.FIX42);
            fail("Exception expected");
        } catch (InvalidMsgException ex) {
            checkUnsupportedExceptionFIXT(ex);
        }
    }

    // UTILITY MESSAGES
    /////////////////////////////////////////

    private void checkUnsupportedException(Exception ex) {
        assertEquals("Message [NewOrderMultileg] is not supported in version [FIX.4.2].", ex.getMessage());
    }

    private void checkUnsupportedExceptionFIXT(Exception ex) {
        assertEquals("Message [NewOrderMultileg] is not supported in version [FIXT.1.1/FIX42].", ex.getMessage());
    }
}
