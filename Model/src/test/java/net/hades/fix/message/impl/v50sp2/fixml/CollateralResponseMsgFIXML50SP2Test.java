/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * CollateralResponseMsgFIXML50SP2Test.java
 *
 * $Id: CollateralResponseMsgFIXML44Test.java,v 1.1 2011-02-16 11:24:36 vrotaru Exp $
 */
package net.hades.fix.message.impl.v50sp2.fixml;

import java.util.Date;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import net.hades.fix.TestUtils;
import net.hades.fix.message.MsgTest;
import net.hades.fix.message.CollateralResponseMsg;
import net.hades.fix.message.XMLValidationResult;
import net.hades.fix.message.builder.FIXMsgBuilder;
import net.hades.fix.message.impl.v50sp2.data.CollateralResponseMsg50SP2TestData;
import net.hades.fix.message.type.BeginString;
import net.hades.fix.message.type.CollAsgnRespType;
import net.hades.fix.message.type.MsgType;

/**
 * Test suite for CollateralResponseMsg50SP2 class FIXML implementation.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 19/12/2011, 20:57:03
 */
public class CollateralResponseMsgFIXML50SP2Test extends MsgTest {

    public CollateralResponseMsgFIXML50SP2Test() {
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
     * Test of encode method, of class CollateralResponseMsg for FIXML.
     * @throws Exception
     */
    @Test
    public void testMarshallUnmarshallFixmlReq() throws Exception {
        System.out.println("-->testMarshallUnmarshallFixmlReq");
        setPrintableValidatingFixml();
        try {
            CollateralResponseMsg msg = (CollateralResponseMsg) FIXMsgBuilder.build(MsgType.CollateralResponse.getValue(), BeginString.FIX_5_0SP2);
            TestUtils.populate50HeaderAll(msg);
            msg.setCollRespID("COLL_RESP_8888");
            msg.setCollAsgnRespType(CollAsgnRespType.Accepted);
            msg.setTransactTime(new Date());
            
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

            CollateralResponseMsg dmsg = (CollateralResponseMsg) FIXMsgBuilder.build(MsgType.CollateralResponse.getValue(), BeginString.FIX_5_0SP2);
            dmsg.fromFixml(fixml);
            assertEquals(msg.getCollRespID(), dmsg.getCollRespID());
            assertEquals(msg.getCollAsgnRespType(), dmsg.getCollAsgnRespType());
            assertUTCTimestampEquals(msg.getTransactTime(), dmsg.getTransactTime(), false);
        } finally {
            unsetPrintableFixml();
        }
    }

    /**
     * Test of encode method, of class CollateralResponseMsg for FIXML.
     * @throws Exception
     */
    @Test
    public void testMarshallUnmarshallFixml() throws Exception {
        System.out.println("-->testMarshallUnmarshallFixml");
        setPrintableValidatingFixml();
        try {
            CollateralResponseMsg msg = (CollateralResponseMsg) FIXMsgBuilder.build(MsgType.CollateralResponse.getValue(), BeginString.FIX_5_0SP2);
            CollateralResponseMsg50SP2TestData.getInstance().populate(msg);
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

            CollateralResponseMsg dmsg = (CollateralResponseMsg) FIXMsgBuilder.build(MsgType.CollateralResponse.getValue(), BeginString.FIX_5_0SP2);
            dmsg.fromFixml(fixml);
            CollateralResponseMsg50SP2TestData.getInstance().check(msg, dmsg);
        } finally {
            unsetPrintableFixml();
        }
    }

    // NEGATIVE TEST CASES
    /////////////////////////////////////////
    
    // UTILITY MESSAGES
    /////////////////////////////////////////

}