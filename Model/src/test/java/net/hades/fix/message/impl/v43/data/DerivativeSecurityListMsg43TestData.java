/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * DerivativeDerivativeSecurityListMsg43TestData.java
 *
 * $Id: DerivativeSecurityListMsg43TestData.java,v 1.1 2011-09-28 08:10:22 vrotaru Exp $
 */
package net.hades.fix.message.impl.v43.data;

import java.io.UnsupportedEncodingException;

import static org.junit.Assert.*;

import net.hades.fix.TestUtils;
import net.hades.fix.message.MsgTest;
import net.hades.fix.message.DerivativeSecurityListMsg;
import net.hades.fix.message.comp.impl.v43.UnderlyingInstrument43TestData;
import net.hades.fix.message.group.impl.v43.DerivSecListGroup43TestData;
import net.hades.fix.message.type.SecurityRequestResult;

/**
 * Test utility for DerivativeSecurityListMsg43 message class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 11/05/2009, 12:08:30 PM
 */
public class DerivativeSecurityListMsg43TestData extends MsgTest {

    private static final DerivativeSecurityListMsg43TestData INSTANCE;

    static {
        INSTANCE = new DerivativeSecurityListMsg43TestData();
    }

    public static DerivativeSecurityListMsg43TestData getInstance() {
        return INSTANCE;
    }

    public void populate(DerivativeSecurityListMsg msg) throws UnsupportedEncodingException {
        TestUtils.populate43HeaderAll(msg);
        msg.setSecurityReqID("REQ_11111");
        msg.setSecurityResponseID("RESP_33344");
        msg.setSecurityRequestResult(SecurityRequestResult.ValidRequest);
        
        msg.setUnderlyingInstrument();
        UnderlyingInstrument43TestData.getInstance().populate1(msg.getUnderlyingInstrument());
        
        msg.setTotNoRelatedSym(2);

        msg.setNoRelatedSyms(2);
        DerivSecListGroup43TestData.getInstance().populate1(msg.getDerivSecListGroups()[0]);
        DerivSecListGroup43TestData.getInstance().populate2(msg.getDerivSecListGroups()[1]);
    }

    public void check(DerivativeSecurityListMsg expected, DerivativeSecurityListMsg actual) throws Exception {
        assertEquals(expected.getSecurityReqID(), actual.getSecurityReqID());
        assertEquals(expected.getSecurityResponseID(), actual.getSecurityResponseID());
        assertEquals(expected.getSecurityRequestResult(), actual.getSecurityRequestResult());
        
        assertEquals(expected.getTotNoRelatedSym(), actual.getTotNoRelatedSym());
        
        UnderlyingInstrument43TestData.getInstance().check(expected.getUnderlyingInstrument(), actual.getUnderlyingInstrument());

        assertEquals(expected.getNoRelatedSyms(), actual.getNoRelatedSyms());
        DerivSecListGroup43TestData.getInstance().check(expected.getDerivSecListGroups()[0], actual.getDerivSecListGroups()[0]);
        DerivSecListGroup43TestData.getInstance().check(expected.getDerivSecListGroups()[1], actual.getDerivSecListGroups()[1]);
    }
}
