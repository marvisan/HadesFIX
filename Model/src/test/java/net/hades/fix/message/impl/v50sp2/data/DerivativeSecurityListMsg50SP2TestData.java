/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * DerivativeSecurityListMsg50SP2TestData.java
 *
 * $Id: DerivativeSecurityListMsg50SP2TestData.java,v 1.1 2011-09-28 08:10:21 vrotaru Exp $
 */
package net.hades.fix.message.impl.v50sp2.data;

import java.io.UnsupportedEncodingException;
import java.util.Calendar;

import static org.junit.Assert.*;

import net.hades.fix.TestUtils;
import net.hades.fix.message.DerivativeSecurityListMsg;
import net.hades.fix.message.MsgTest;
import net.hades.fix.message.comp.impl.v50sp2.ApplicationSequenceControl50SP2TestData;
import net.hades.fix.message.comp.impl.v50sp2.DerivativeSecurityDefinition50SP2TestData;
import net.hades.fix.message.comp.impl.v50sp2.UnderlyingInstrument50SP2TestData;
import net.hades.fix.message.group.impl.v50sp2.DerivSecListGroup50SP2TestData;
import net.hades.fix.message.type.SecurityRequestResult;

/**
 * Test utility for DerivativeSecurityListMsg50SP2 message class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 11/05/2009, 12:08:30 PM
 */
public class DerivativeSecurityListMsg50SP2TestData extends MsgTest {

    private static final DerivativeSecurityListMsg50SP2TestData INSTANCE;

    static {
        INSTANCE = new DerivativeSecurityListMsg50SP2TestData();
    }

    public static DerivativeSecurityListMsg50SP2TestData getInstance() {
        return INSTANCE;
    }

    public void populate(DerivativeSecurityListMsg msg) throws UnsupportedEncodingException {
        TestUtils.populate50HeaderAll(msg);
        Calendar cal = Calendar.getInstance();
        
        msg.setApplicationSequenceControl();
        ApplicationSequenceControl50SP2TestData.getInstance().populate(msg.getApplicationSequenceControl());
        
        msg.setSecurityReportID(234);
        msg.setSecurityReqID("REQ_11111");
        msg.setSecurityResponseID("RESP_33344");
        msg.setSecurityRequestResult(SecurityRequestResult.ValidRequest);
        cal.set(2011, 6, 11, 12, 35, 45);
        msg.setClearingBusinessDate(cal.getTime());
        
        msg.setUnderlyingInstrument();
        UnderlyingInstrument50SP2TestData.getInstance().populate1(msg.getUnderlyingInstrument());
        
        msg.setDerivativeSecurityDefinition();
        DerivativeSecurityDefinition50SP2TestData.getInstance().populate(msg.getDerivativeSecurityDefinition());
        
        cal.set(2011, 6, 11, 12, 35, 45);
        msg.setTransactTime(cal.getTime());
        
        msg.setTotNoRelatedSym(2);
        msg.setLastFragment(Boolean.FALSE);

        msg.setNoRelatedSyms(2);
        DerivSecListGroup50SP2TestData.getInstance().populate1(msg.getDerivSecListGroups()[0]);
        DerivSecListGroup50SP2TestData.getInstance().populate2(msg.getDerivSecListGroups()[1]);
    }

    public void check(DerivativeSecurityListMsg expected, DerivativeSecurityListMsg actual) throws Exception {
        ApplicationSequenceControl50SP2TestData.getInstance().check(expected.getApplicationSequenceControl(), actual.getApplicationSequenceControl());
        
        assertEquals(expected.getSecurityReportID(), actual.getSecurityReportID());
        assertEquals(expected.getSecurityReqID(), actual.getSecurityReqID());
        assertEquals(expected.getSecurityResponseID(), actual.getSecurityResponseID());
        assertEquals(expected.getSecurityRequestResult(), actual.getSecurityRequestResult());
        assertDateEquals(expected.getClearingBusinessDate(), actual.getClearingBusinessDate());
        
        assertEquals(expected.getTotNoRelatedSym(), actual.getTotNoRelatedSym());
        assertEquals(expected.getLastFragment(), actual.getLastFragment());
        
        UnderlyingInstrument50SP2TestData.getInstance().check(expected.getUnderlyingInstrument(), actual.getUnderlyingInstrument());
        
        DerivativeSecurityDefinition50SP2TestData.getInstance().check(expected.getDerivativeSecurityDefinition(), actual.getDerivativeSecurityDefinition());
        
        assertUTCTimestampEquals(expected.getTransactTime(), actual.getTransactTime(), false);

        assertEquals(expected.getNoRelatedSyms(), actual.getNoRelatedSyms());
        DerivSecListGroup50SP2TestData.getInstance().check(expected.getDerivSecListGroups()[0], actual.getDerivSecListGroups()[0]);
        DerivSecListGroup50SP2TestData.getInstance().check(expected.getDerivSecListGroups()[1], actual.getDerivSecListGroups()[1]);
    }
}
