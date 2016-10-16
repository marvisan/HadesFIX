/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * BidRequestMsg41Test.java
 *
 * $Id: ListStrikePriceMsg41Test.java,v 1.1 2011-04-15 04:37:42 vrotaru Exp $
 */
package net.hades.fix.message.impl.v41;

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
 * Test suite for FIX 4.1 ListStrikePriceMsg class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 01/05/2009, 12:18:17 PM
 */
public class ListStrikePriceMsg41Test extends MsgTest  {

    public ListStrikePriceMsg41Test() {
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
            FIXMsgBuilder.build(MsgType.ListStrikePrice.getValue(), BeginString.FIX_4_1);
            fail("Exception expected");
        } catch (InvalidMsgException ex) {
            checkUnsupportedException(ex);
        }
    }

    @Test
    public void testEncodeFIXT() throws Exception {
        System.out.println("-->testEncodeFIXT");
        try {
            FIXMsgBuilder.build(MsgType.ListStrikePrice.getValue(), BeginString.FIXT_1_1, ApplVerID.FIX41);
            fail("Exception expected");
        } catch (InvalidMsgException ex) {
            checkUnsupportedExceptionFIXT(ex);
        }
    }

    // UTILITY MESSAGES
    /////////////////////////////////////////

    private void checkUnsupportedException(Exception ex) {
        assertEquals("Message [ListStrikePrice] is not supported in version [FIX.4.1].", ex.getMessage());
    }

    private void checkUnsupportedExceptionFIXT(Exception ex) {
        assertEquals("Message [ListStrikePrice] is not supported in version [FIXT.1.1/FIX41].", ex.getMessage());
    }
}
