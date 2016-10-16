/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * MarketDataRequestRejectMsg44Test.java
 *
 * $Id: CustomNewMsg44Test.java,v 1.1 2010-02-10 10:06:09 vrotaru Exp $
 */
package net.hades.fix.sample.message;

import java.nio.charset.Charset;
import java.util.Calendar;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import net.hades.fix.message.builder.FIXMsgBuilder;
import net.hades.fix.message.type.BeginString;

import net.hades.fix.sample.message.group.CustomNewGroup;

/**
 * Test suite for FIX 4.4 MarketDataRequestRejectMsg class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 01/05/2009, 12:18:17 PM
 */
public class CustomNewMsg44Test {

    public CustomNewMsg44Test() {
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
     * Test of encode method, of secured message.
     * @throws Exception
     */
    @Test
    public void b3_testEncodeDecodeAll() throws Exception {
        System.out.println("-->testEncodeDecodeAll");
        CustomNewMsg expected = (CustomNewMsg) FIXMsgBuilder.build(CustomNewMsg.MSG_TYPE, BeginString.FIX_4_4);
        expected.getHeader().setOnBehalfOfCompID("Company on behalf name");
        expected.getHeader().setDeliverToCompID("Deliver to this company");
        expected.getHeader().setSenderSubID("Sender sub identifier");
        expected.getHeader().setSenderLocationID("Sender location identifier");
        expected.getHeader().setTargetSubID("Target sub identifier");
        expected.getHeader().setTargetLocationID("Target location identifier");
        expected.getHeader().setOnBehalfOfSubID("On behalf of sub identifier");
        expected.getHeader().setOnBehalfOfLocationID("On behalf of location identifier");
        expected.getHeader().setDeliverToSubID("Deliver to this company");
        expected.getHeader().setDeliverToLocationID("Deliver to this location");
        expected.getHeader().setPossDupFlag(Boolean.TRUE);
        expected.getHeader().setPossResend(Boolean.FALSE);
        expected.getHeader().setSenderCompID("Marvisan");
        expected.getHeader().setTargetCompID("IB");
        expected.getHeader().setMsgSeqNum(10);
        expected.getHeader().setMessageEncoding(Charset.forName("UTF-8"));
        String xmlData = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><doc></doc>";
        expected.getHeader().setXmlData(xmlData.getBytes());
        expected.getHeader().setXmlDataLen(new Integer(expected.getHeader().getXmlData().length));
        expected.getHeader().setLastMsgSeqNumProcessed(new Integer(9));
        expected.getHeader().setLastMsgSeqNumProcessed(new Integer(9));
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_MONTH, 23);
        cal.set(Calendar.MONTH, 6);
        cal.set(Calendar.YEAR, 2008);
        cal.set(Calendar.HOUR_OF_DAY, 21);
        cal.set(Calendar.MINUTE, 45);
        cal.set(Calendar.SECOND, 15);
        cal.set(Calendar.MILLISECOND, 0);
        expected.getHeader().setSendingTime(cal.getTime());
        cal.add(Calendar.HOUR_OF_DAY, -2);
        expected.getHeader().setOrigSendingTime(cal.getTime());
        expected.getHeader().setNoHops(1);
        expected.getHeader().getHopsGroups()[0].setHopCompID("Hop No 1");
        expected.getHeader().getHopsGroups()[0].setHopSendingTime(cal.getTime());
        expected.getHeader().getHopsGroups()[0].setHopRefID(3);
        cal.add(Calendar.HOUR_OF_DAY, -1);
        expected.getHeader().addHopsGroup();
        expected.getHeader().getHopsGroups()[1].setHopCompID("Hop No 2");
        expected.getHeader().getHopsGroups()[1].setHopSendingTime(cal.getTime());
        expected.getHeader().getHopsGroups()[1].setHopRefID(4);

        expected.setCustNewMsgInt(5);
        expected.setCustNewMsgString("test string");
        expected.setCustNewMsgBool(Boolean.TRUE);
        Calendar c = Calendar.getInstance();
        c.set(2010, 3, 13, 13, 24, 16);
        expected.setCustNewMsgDataLen(6);
        byte[] encText = new byte[] {(byte) 55, (byte) 56, (byte) 68, (byte) 50,
            (byte) 61, (byte) 80};
        expected.setCustNewMsgData(encText);
        CustomNewGroup grp1 = new CustomNewGroup();
        grp1.setGrpType(1);
        grp1.setGrpName("name 1");
        CustomNewGroup grp2 = new CustomNewGroup();
        grp2.setGrpType(2);
        grp2.setGrpName("name 2");
        expected.setCustNewGroups(new CustomNewGroup[] {grp1, grp2});

        String encoded = new String(expected.encode());
        System.out.println("encoded-->" + encoded);

        CustomNewMsg actual = (CustomNewMsg) FIXMsgBuilder.build(encoded.getBytes());
        assertEquals(expected.getCustNewMsgInt(), actual.getCustNewMsgInt());
        assertEquals(expected.getCustNewMsgString(), actual.getCustNewMsgString());
        assertEquals(expected.getCustNewMsgBool(), actual.getCustNewMsgBool());
        assertEquals(expected.getCustNewMsgDate(), actual.getCustNewMsgDate());
        assertEquals(expected.getCustNewMsgDataLen(), actual.getCustNewMsgDataLen());
        assertArrayEquals(expected.getCustNewMsgData(), actual.getCustNewMsgData());
        assertEquals(expected.getNoCustNewGroups(), actual.getNoCustNewGroups());
        for (int i = 0; i < actual.getNoCustNewGroups().intValue(); i++) {
            assertEquals(expected.getCustNewGroups()[i].getGrpType(), actual.getCustNewGroups()[i].getGrpType());
            assertEquals(expected.getCustNewGroups()[i].getGrpName(), actual.getCustNewGroups()[i].getGrpName());
        }
    }
}
