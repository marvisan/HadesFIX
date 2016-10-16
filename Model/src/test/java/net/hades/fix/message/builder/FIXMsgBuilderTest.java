/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * FIXMsgBuilderTest.java
 *
 * $Id: FIXMsgBuilderTest.java,v 1.2 2011-04-23 03:55:16 vrotaru Exp $
 */
package net.hades.fix.message.builder;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import net.hades.fix.message.FIXMsg;
import net.hades.fix.message.builder.FIXMsgBuilder;
import net.hades.fix.message.type.ApplVerID;
import net.hades.fix.message.type.BeginString;

/**
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 * @created 24/01/2010, 3:41:32 PM
 */
public class FIXMsgBuilderTest {

    public FIXMsgBuilderTest() {
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
     * Test of build method, of class FIXMsgBuilder.
     */
    @Test
    public void testBuildCustome_pre5() throws Exception {
        System.out.println("build");
        System.setProperty("hades.model.build.file.location", "net.hades.fix.message.builder:net.hades.fix.message.builder.impl");
        String msgType = "YC";
        BeginString version = BeginString.FIX_4_1;
        ApplVerID applVerID = null;
        FIXMsg result = FIXMsgBuilder.build(msgType, version, applVerID);
        assertNull(result);
        msgType = "YA";
        result = FIXMsgBuilder.build(msgType, version, applVerID);
        assertNull(result);
        msgType = "YB";
        result = FIXMsgBuilder.build(msgType, version, applVerID);
        assertNull(result);
    }

}