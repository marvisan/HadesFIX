/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * FIXMLMsgCodecTest.java
 *
 * $Id: FIXMLMsgCodecTest.java,v 1.2 2010-01-24 05:01:20 vrotaru Exp $
 */
package net.hades.fix.message.builder;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import net.hades.fix.TestUtils;
import net.hades.fix.message.FIXMsg;
import net.hades.fix.message.IOIMsg;
import net.hades.fix.message.impl.v44.data.IOIMsg44TestData;

import net.hades.fix.message.type.BeginString;
import net.hades.fix.message.type.MsgType;

/**
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 * @created 10/06/2009, 7:00:58 PM
 */
public class FIXMLMsgCodecTest {

    public FIXMLMsgCodecTest() {
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

    /*
     * Test of marshall method, of class FIXMLMsgCodec.
     */
    @Test
    public void testMarshallNoContext() throws Exception {
        System.out.println("marshallNoContext");
        IOIMsg msg = (IOIMsg) buildFixMessage();
        FIXMLMsgCodec codec = FIXMLMsgCodec.newInstance();
        String fixml = codec.marshall(msg);
        System.out.println("fixml-->" + fixml);
        assertNotNull(fixml);
    }

    /*
     * Test of marshall method, of class FIXMLMsgCodec.
     */
    @Test
    public void testMarshallContext() throws Exception {
        System.out.println("marshallContext");
        IOIMsg msg = (IOIMsg) buildFixMessage();
        FIXMLMsgCodecContext ctx = new FIXMLMsgCodecContext(true, true, true, true);
        FIXMLMsgCodec codec = FIXMLMsgCodec.newInstance(ctx);
        String fixml = codec.marshall(msg);
        System.out.println("fixml-->" + fixml);
        assertNotNull(fixml);
    }

    /*
     * Test of unmarshall method, of class FIXMLMsgCodec.
     */
    @Test
    public void testUnmarshall() throws Exception {
        System.out.println("unmarshall");
        IOIMsg msg = (IOIMsg) buildFixMessage();
        FIXMLMsgCodecContext ctx = new FIXMLMsgCodecContext(true, true, true, true);
        FIXMLMsgCodec codec = FIXMLMsgCodec.newInstance(ctx);
        String fixml = codec.marshall(msg);
        FIXMsg result = codec.unmarshall(fixml);
        assertNotNull(result);
    }

    private FIXMsg buildFixMessage() throws Exception {
        IOIMsg msg = (IOIMsg) FIXMsgBuilder.build(MsgType.IndicationOfInterest.getValue(), BeginString.FIX_4_4);
        TestUtils.populate44HeaderAll(msg);
        IOIMsg44TestData.getInstance().populate(msg);
        return msg;
    }
}