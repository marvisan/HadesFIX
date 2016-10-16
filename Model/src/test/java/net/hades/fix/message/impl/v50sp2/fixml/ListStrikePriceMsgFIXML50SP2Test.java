/*
 *   Copyright (c) 2006-2008 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * ListStrikePriceMsgFIXML50SP2Test.java
 *
 * $Id: ListStrikePriceMsgFIXML50SP2Test.java,v 1.1 2011-04-15 04:37:43 vrotaru Exp $
 */
package net.hades.fix.message.impl.v50sp2.fixml;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import net.hades.fix.TestUtils;
import net.hades.fix.message.MsgTest;
import net.hades.fix.message.ListStrikePriceMsg;
import net.hades.fix.message.XMLValidationResult;
import net.hades.fix.message.builder.FIXMsgBuilder;
import net.hades.fix.message.group.impl.v50sp2.InstrmtStrikePriceGroup50SP2TestData;
import net.hades.fix.message.impl.v50sp2.data.ListStrikePriceMsg50SP2TestData;
import net.hades.fix.message.type.BeginString;
import net.hades.fix.message.type.MsgType;

/**
 * Test suite for ListStrikePriceMsg50SP2 class FIXML implementation.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 20/10/2008, 20:57:03
 */
public class ListStrikePriceMsgFIXML50SP2Test extends MsgTest {

    public ListStrikePriceMsgFIXML50SP2Test() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of encode method, of class ListStrikePriceMsg for FIXML 4.4.
     * @throws Exception
     */
    @Test
    public void testMarshallUnmarshallFixmlReq() throws Exception {
        System.out.println("-->testMarshallUnmarshallFixmlReq");
        setPrintableValidatingFixml();
        try {
            ListStrikePriceMsg msg = (ListStrikePriceMsg) FIXMsgBuilder.build(MsgType.ListStrikePrice.getValue(), BeginString.FIX_5_0SP2);
            TestUtils.populate50HeaderAll(msg);
            msg.setListID("LST564567");
            msg.setTotNoStrikes(3);
            msg.setNoStrikes(1);
            InstrmtStrikePriceGroup50SP2TestData.getInstance().populate1(msg.getInstrmtStrikePriceGroups()[0]);
            String fixml = msg.toFixml();
            System.out.println("fixml-->" + fixml);
            XMLValidationResult result = validateXMLAgainstXSD(fixml, FIXML_SCHEMA_V50SP2);
            if (result.hasErrors()) {
                System.out.println("\nERRORS:\n");
                System.out.println(result.getFatals());
                System.out.println(result.getErrors());
            }
            System.out.println(result.getWarnings());
            assertFalse(result.hasErrors());

            ListStrikePriceMsg dmsg = (ListStrikePriceMsg) FIXMsgBuilder.build(MsgType.ListStrikePrice.getValue(), BeginString.FIX_5_0SP2);
            dmsg.fromFixml(fixml);
            assertEquals(msg.getListID(), dmsg.getListID());
            assertEquals(msg.getTotNoStrikes(), dmsg.getTotNoStrikes());
            assertEquals(msg.getNoStrikes(), dmsg.getNoStrikes());
        } finally {
            unsetPrintableFixml();
        }
    }

    /**
     * Test of encode method, of class ListStrikePriceMsg for FIXML 4.4.
     * @throws Exception
     */
    @Test
    public void testMarshallUnmarshallFixml() throws Exception {
        System.out.println("-->testMarshallUnmarshallFixml");
        setPrintableValidatingFixml();
        try {
            ListStrikePriceMsg msg = (ListStrikePriceMsg) FIXMsgBuilder.build(MsgType.ListStrikePrice.getValue(), BeginString.FIX_5_0SP2);
            TestUtils.populate50HeaderAll(msg);
            ListStrikePriceMsg50SP2TestData.getInstance().populate(msg);
            String fixml = msg.toFixml();
            System.out.println("fixml-->" + fixml);
            XMLValidationResult result = validateXMLAgainstXSD(fixml, FIXML_SCHEMA_V50SP2);
            if (result.hasErrors()) {
                System.out.println("\nERRORS:\n");
                System.out.println(result.getFatals());
                System.out.println(result.getErrors());
            }
            System.out.println(result.getWarnings());
            assertFalse(result.hasErrors());

            ListStrikePriceMsg dmsg = (ListStrikePriceMsg) FIXMsgBuilder.build(MsgType.ListStrikePrice.getValue(), BeginString.FIX_5_0SP2);
            dmsg.fromFixml(fixml);
            ListStrikePriceMsg50SP2TestData.getInstance().check(msg, dmsg);
        } finally {
            unsetPrintableFixml();
        }
    }

    // NEGATIVE TEST CASES
    /////////////////////////////////////////
    
    // UTILITY MESSAGES
    /////////////////////////////////////////

}