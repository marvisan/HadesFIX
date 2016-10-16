/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * DerivativeDerivativeSecurityListMsg43TestData.java
 *
 * $Id: DerivativeSecurityListMsg44TestData.java,v 1.1 2011-09-28 08:10:22 vrotaru Exp $
 */
package net.hades.fix.message.impl.v44.data;

import java.io.UnsupportedEncodingException;

import static org.junit.Assert.*;

import net.hades.fix.TestUtils;
import net.hades.fix.message.MsgTest;
import net.hades.fix.message.DerivativeSecurityListMsg;
import net.hades.fix.message.comp.impl.v44.UnderlyingInstrument44TestData;
import net.hades.fix.message.group.impl.v44.DerivSecListGroup44TestData;
import net.hades.fix.message.type.SecurityRequestResult;

/**
 * Test utility for DerivativeSecurityListMsg43 message class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 11/05/2009, 12:08:30 PM
 */
public class DerivativeSecurityListMsg44TestData extends MsgTest {

    private static final DerivativeSecurityListMsg44TestData INSTANCE;

    static {
        INSTANCE = new DerivativeSecurityListMsg44TestData();
    }

    public static DerivativeSecurityListMsg44TestData getInstance() {
        return INSTANCE;
    }

    public void populate(DerivativeSecurityListMsg msg) throws UnsupportedEncodingException {
        TestUtils.populate44HeaderAll(msg);
        msg.setSecurityReqID("REQ_11111");
        msg.setSecurityResponseID("RESP_33344");
        msg.setSecurityRequestResult(SecurityRequestResult.ValidRequest);
        
        msg.setUnderlyingInstrument();
        UnderlyingInstrument44TestData.getInstance().populate1(msg.getUnderlyingInstrument());
        
        msg.setTotNoRelatedSym(2);
        msg.setLastFragment(Boolean.FALSE);

        msg.setNoRelatedSyms(2);
        DerivSecListGroup44TestData.getInstance().populate1(msg.getDerivSecListGroups()[0]);
        DerivSecListGroup44TestData.getInstance().populate2(msg.getDerivSecListGroups()[1]);
    }

    public void check(DerivativeSecurityListMsg expected, DerivativeSecurityListMsg actual) throws Exception {
        assertEquals(expected.getSecurityReqID(), actual.getSecurityReqID());
        assertEquals(expected.getSecurityResponseID(), actual.getSecurityResponseID());
        assertEquals(expected.getSecurityRequestResult(), actual.getSecurityRequestResult());
        
        assertEquals(expected.getTotNoRelatedSym(), actual.getTotNoRelatedSym());
        assertEquals(expected.getLastFragment(), actual.getLastFragment());
        
        UnderlyingInstrument44TestData.getInstance().check(expected.getUnderlyingInstrument(), actual.getUnderlyingInstrument());

        assertEquals(expected.getNoRelatedSyms(), actual.getNoRelatedSyms());
        DerivSecListGroup44TestData.getInstance().check(expected.getDerivSecListGroups()[0], actual.getDerivSecListGroups()[0]);
        DerivSecListGroup44TestData.getInstance().check(expected.getDerivSecListGroups()[1], actual.getDerivSecListGroups()[1]);
    }
}
