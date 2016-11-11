/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * SecurityListMsg50SP2TestData.java
 *
 * $Id: SecurityListMsg50SP2TestData.java,v 1.1 2011-04-29 03:11:03 vrotaru Exp $
 */
package net.hades.fix.message.impl.v50sp2.data;

import java.io.UnsupportedEncodingException;
import java.util.Calendar;

import static org.junit.Assert.*;

import net.hades.fix.TestUtils;
import net.hades.fix.message.MsgTest;
import net.hades.fix.message.SecurityListMsg;
import net.hades.fix.message.comp.impl.v50sp2.ApplicationSequenceControl50SP2TestData;
import net.hades.fix.message.group.impl.v50sp2.SecListGroup50SP2TestData;
import net.hades.fix.message.type.SecurityListType;
import net.hades.fix.message.type.SecurityListTypeSource;
import net.hades.fix.message.type.SecurityRequestResult;

/**
 * Test utility for SecurityListMsg50SP2 message class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 11/05/2009, 12:08:30 PM
 */
public class SecurityListMsg50SP2TestData extends MsgTest {

    private static final SecurityListMsg50SP2TestData INSTANCE;

    static {
        INSTANCE = new SecurityListMsg50SP2TestData();
    }

    public static SecurityListMsg50SP2TestData getInstance() {
        return INSTANCE;
    }

    public void populate(SecurityListMsg msg) throws UnsupportedEncodingException {
        TestUtils.populate50HeaderAll(msg);
        Calendar cal = Calendar.getInstance();

        msg.setApplicationSequenceControl();
        ApplicationSequenceControl50SP2TestData.getInstance().populate(msg.getApplicationSequenceControl());

        msg.setSecurityReportID(23456);
        cal.set(2011, 6, 11, 12, 35, 45);
        msg.setClearingBusinessDate(cal.getTime());
        msg.setSecurityListID("LIST_889933");
        msg.setSecurityListRefID("LIST_REF_998877");
        msg.setSecurityListDesc("Some list description");
        msg.setEncodedSecurityListDescLen(new Integer(8));
        byte[] encodedText = new byte[] {(byte) 18, (byte) 32, (byte) 43, (byte) 95,
            (byte) 177, (byte) 198, (byte) 224, (byte) 253};
        msg.setEncodedSecurityListDesc(encodedText);
        msg.setSecurityListType(SecurityListType.TradingList);
        msg.setSecurityListTypeSource(SecurityListTypeSource.NAICS);
        msg.setSecurityReqID("REQ_11111");
        msg.setSecurityResponseID("RSP888777");
        msg.setSecurityRequestResult(SecurityRequestResult.ValidRequest);
        cal.set(2011, 6, 11, 12, 35, 45);
        msg.setTransactTime(cal.getTime());
        msg.setTotNoRelatedSym(34);
        msg.setMarketID("CBOT");
        msg.setMarketSegmentID("Commodities");
        msg.setLastFragment(Boolean.FALSE);

        msg.setNoRelatedSyms(2);
        SecListGroup50SP2TestData.getInstance().populate1(msg.getSecListGroups()[0]);
        SecListGroup50SP2TestData.getInstance().populate2(msg.getSecListGroups()[1]);
    }

    public void check(SecurityListMsg expected, SecurityListMsg actual, boolean isFixml) throws Exception {
        ApplicationSequenceControl50SP2TestData.getInstance().check(expected.getApplicationSequenceControl(), actual.getApplicationSequenceControl());
        
        assertEquals(expected.getSecurityReportID(), actual.getSecurityReportID());
        assertDateEquals(expected.getClearingBusinessDate(), actual.getClearingBusinessDate());
        assertEquals(expected.getSecurityListID(), actual.getSecurityListID());
        assertEquals(expected.getSecurityListRefID(), actual.getSecurityListRefID());
        assertEquals(expected.getSecurityListDesc(), actual.getSecurityListDesc());
        if (!isFixml) {
            assertEquals(expected.getEncodedSecurityListDescLen().intValue(), actual.getEncodedSecurityListDescLen().intValue());
            assertArrayEquals(expected.getEncodedSecurityListDesc(), actual.getEncodedSecurityListDesc());
        }
        assertEquals(expected.getSecurityListType(), actual.getSecurityListType());
        assertEquals(expected.getSecurityListTypeSource(), actual.getSecurityListTypeSource());
        assertEquals(expected.getSecurityReqID(), actual.getSecurityReqID());
        assertEquals(expected.getSecurityResponseID(), actual.getSecurityResponseID());
        assertEquals(expected.getSecurityRequestResult(), actual.getSecurityRequestResult());
        assertUTCTimestampEquals(expected.getTransactTime(), actual.getTransactTime(), false);
        assertEquals(expected.getTotNoRelatedSym(), actual.getTotNoRelatedSym());
        assertEquals(expected.getMarketID(), actual.getMarketID());
        assertEquals(expected.getMarketSegmentID(), actual.getMarketSegmentID());
        assertEquals(expected.getLastFragment(), actual.getLastFragment());

        assertEquals(expected.getNoRelatedSyms(), actual.getNoRelatedSyms());
        SecListGroup50SP2TestData.getInstance().check(expected.getSecListGroups()[0], actual.getSecListGroups()[0]);
        SecListGroup50SP2TestData.getInstance().check(expected.getSecListGroups()[1], actual.getSecListGroups()[1]);
    }
}
