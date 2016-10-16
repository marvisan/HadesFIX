/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */
package net.hades.fix.sample.message;

import net.hades.fix.message.FIXMsg;
import net.hades.fix.message.builder.FIXMsgBuilder;
import net.hades.fix.message.config.SessionContext;
import net.hades.fix.message.config.SessionContextKey;
import net.hades.fix.message.config.ThreadData;
import net.hades.fix.message.type.BeginString;
import net.hades.fix.message.type.MDReqRejReason;
import net.hades.fix.message.type.MsgType;
import net.hades.fix.sample.message.group.CustomNewGroup;

import java.nio.charset.Charset;
import java.util.Calendar;

import org.junit.*;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class MarketDataRequestRejectCustomMsgTest {

    public MarketDataRequestRejectCustomMsgTest() {
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

//        SessionContext ctx = ThreadData.getSessionContext();
//        ctx.setValue(SessionContextKey.VALIDATE_REQUIRED, Boolean.TRUE);
//        ctx.setValue(SessionContextKey.SESSION_CHARACTER_SET, Charset.forName(FIXMsg.DEFAULT_CHARACTER_SET));

        MarketDataRequestRejectCustomMsg expected = (MarketDataRequestRejectCustomMsg) FIXMsgBuilder.build(MsgType.MarketDataRequestReject.getValue(),
                BeginString.FIX_4_4);
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

        expected.setMdReqID("AAA564567");
        // AltMDSourceID
        expected.setNoAltMDSource(2);
        expected.getAltMDSourceGroups()[0].setAltMDSourceID("Source 1");
        expected.getAltMDSourceGroups()[1].setAltMDSourceID("Source 2");

        expected.setMdReqRejReason(MDReqRejReason.UnknownSymbol);
        expected.setText("I want these shares!");
        expected.setEncodedTextLen(new Integer(8));
        byte[] textDataExp = new byte[] {(byte) 19, (byte) 34, (byte) 45, (byte) 97,
            (byte) 178, (byte) 200, (byte) 225, (byte) 254};
        expected.setEncodedText(textDataExp);

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
        System.out.println("object-->" + expected.toString());

        String encoded = new String(expected.encode(), FIXMsg.DEFAULT_CHARACTER_SET);
        System.out.println("encoded-->" + encoded);

        MarketDataRequestRejectCustomMsg actual = (MarketDataRequestRejectCustomMsg) FIXMsgBuilder.build(encoded.getBytes(FIXMsg.DEFAULT_CHARACTER_SET));
        assertEquals(expected.getMdReqID(), actual.getMdReqID());
        // AltMDSourceID
        assertEquals(expected.getNoAltMDSource(), actual.getNoAltMDSource());
        assertEquals(expected.getAltMDSourceGroups()[0].getAltMDSourceID(), actual.getAltMDSourceGroups()[0].getAltMDSourceID());
        assertEquals(expected.getAltMDSourceGroups()[1].getAltMDSourceID(), actual.getAltMDSourceGroups()[1].getAltMDSourceID());

        assertEquals(expected.getMdReqRejReason(), actual.getMdReqRejReason());
        assertEquals(expected.getText(), actual.getText());
        assertEquals(expected.getEncodedTextLen().intValue(), actual.getEncodedTextLen().intValue());
        assertArrayEquals(expected.getEncodedText(), actual.getEncodedText());

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
