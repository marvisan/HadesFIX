/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * SecurityListMsg50SP1TestData.java
 *
 * $Id: SecurityListMsg50SP1TestData.java,v 1.1 2011-04-29 03:11:05 vrotaru Exp $
 */
package net.hades.fix.message.impl.v50sp1.data;

import java.io.UnsupportedEncodingException;
import java.util.Calendar;

import static org.junit.Assert.*;

import net.hades.fix.TestUtils;
import net.hades.fix.message.MsgTest;
import net.hades.fix.message.SecurityListMsg;
import net.hades.fix.message.comp.impl.v50sp1.ApplicationSequenceControl50SP1TestData;
import net.hades.fix.message.group.impl.v50sp1.SecListGroup50SP1TestData;
import net.hades.fix.message.type.SecurityRequestResult;

/**
 * Test utility for SecurityListMsg50SP1 message class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 11/05/2009, 12:08:30 PM
 */
public class SecurityListMsg50SP1TestData extends MsgTest {

    private static final SecurityListMsg50SP1TestData INSTANCE;

    static {
        INSTANCE = new SecurityListMsg50SP1TestData();
    }

    public static SecurityListMsg50SP1TestData getInstance() {
        return INSTANCE;
    }

    public void populate(SecurityListMsg msg) throws UnsupportedEncodingException {
        TestUtils.populate50HeaderAll(msg);
        msg.setApplicationSequenceControl();
        ApplicationSequenceControl50SP1TestData.getInstance().populate(msg.getApplicationSequenceControl());

        Calendar cal = Calendar.getInstance();
        msg.setSecurityReportID(23456);
        cal.set(2011, 6, 11, 12, 35, 45);
        msg.setClearingBusinessDate(cal.getTime());
        msg.setSecurityReqID("REQ_11111");
        msg.setSecurityResponseID("RSP888777");
        msg.setSecurityRequestResult(SecurityRequestResult.ValidRequest);
        msg.setTotNoRelatedSym(34);
        msg.setMarketID("CBOT");
        msg.setMarketSegmentID("Commodities");
        msg.setLastFragment(Boolean.FALSE);

        msg.setNoRelatedSyms(2);
        SecListGroup50SP1TestData.getInstance().populate1(msg.getSecListGroups()[0]);
        SecListGroup50SP1TestData.getInstance().populate2(msg.getSecListGroups()[1]);
    }

    public void check(SecurityListMsg expected, SecurityListMsg actual) throws Exception {
        ApplicationSequenceControl50SP1TestData.getInstance().check(expected.getApplicationSequenceControl(), actual.getApplicationSequenceControl());
        
        assertEquals(expected.getSecurityReportID(), actual.getSecurityReportID());
        assertDateEquals(expected.getClearingBusinessDate(), actual.getClearingBusinessDate());
        assertEquals(expected.getSecurityReqID(), actual.getSecurityReqID());
        assertEquals(expected.getSecurityResponseID(), actual.getSecurityResponseID());
        assertEquals(expected.getSecurityRequestResult(), actual.getSecurityRequestResult());
        assertEquals(expected.getTotNoRelatedSym(), actual.getTotNoRelatedSym());
        assertEquals(expected.getMarketID(), actual.getMarketID());
        assertEquals(expected.getMarketSegmentID(), actual.getMarketSegmentID());
        assertEquals(expected.getLastFragment(), actual.getLastFragment());

        assertEquals(expected.getNoRelatedSyms(), actual.getNoRelatedSyms());
        SecListGroup50SP1TestData.getInstance().check(expected.getSecListGroups()[0], actual.getSecListGroups()[0]);
        SecListGroup50SP1TestData.getInstance().check(expected.getSecListGroups()[1], actual.getSecListGroups()[1]);
    }
}
