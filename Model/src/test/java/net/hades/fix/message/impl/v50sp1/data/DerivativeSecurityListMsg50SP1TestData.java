/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * DerivativeSecurityListMsg50SP1TestData.java
 *
 * $Id: DerivativeSecurityListMsg50SP1TestData.java,v 1.1 2011-09-28 08:10:20 vrotaru Exp $
 */
package net.hades.fix.message.impl.v50sp1.data;

import java.io.UnsupportedEncodingException;

import static org.junit.Assert.*;

import net.hades.fix.TestUtils;
import net.hades.fix.message.MsgTest;
import net.hades.fix.message.DerivativeSecurityListMsg;
import net.hades.fix.message.comp.impl.v50sp1.ApplicationSequenceControl50SP1TestData;
import net.hades.fix.message.comp.impl.v50sp1.DerivativeSecurityDefinition50SP1TestData;
import net.hades.fix.message.comp.impl.v50sp1.UnderlyingInstrument50SP1TestData;
import net.hades.fix.message.group.impl.v50sp1.DerivSecListGroup50SP1TestData;
import net.hades.fix.message.type.SecurityRequestResult;

/**
 * Test utility for DerivativeSecurityListMsg50SP1 message class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 11/05/2009, 12:08:30 PM
 */
public class DerivativeSecurityListMsg50SP1TestData extends MsgTest {

    private static final DerivativeSecurityListMsg50SP1TestData INSTANCE;

    static {
        INSTANCE = new DerivativeSecurityListMsg50SP1TestData();
    }

    public static DerivativeSecurityListMsg50SP1TestData getInstance() {
        return INSTANCE;
    }

    public void populate(DerivativeSecurityListMsg msg) throws UnsupportedEncodingException {
        TestUtils.populate50HeaderAll(msg);
        msg.setApplicationSequenceControl();
        ApplicationSequenceControl50SP1TestData.getInstance().populate(msg.getApplicationSequenceControl());
        
        msg.setSecurityReqID("REQ_11111");
        msg.setSecurityResponseID("RESP_33344");
        msg.setSecurityRequestResult(SecurityRequestResult.ValidRequest);
        
        msg.setUnderlyingInstrument();
        UnderlyingInstrument50SP1TestData.getInstance().populate1(msg.getUnderlyingInstrument());
        
        msg.setDerivativeSecurityDefinition();
        DerivativeSecurityDefinition50SP1TestData.getInstance().populate(msg.getDerivativeSecurityDefinition());
        
        msg.setTotNoRelatedSym(2);
        msg.setLastFragment(Boolean.FALSE);

        msg.setNoRelatedSyms(2);
        DerivSecListGroup50SP1TestData.getInstance().populate1(msg.getDerivSecListGroups()[0]);
        DerivSecListGroup50SP1TestData.getInstance().populate2(msg.getDerivSecListGroups()[1]);
    }

    public void check(DerivativeSecurityListMsg expected, DerivativeSecurityListMsg actual) throws Exception {
        ApplicationSequenceControl50SP1TestData.getInstance().check(expected.getApplicationSequenceControl(), actual.getApplicationSequenceControl());
        
        assertEquals(expected.getSecurityReqID(), actual.getSecurityReqID());
        assertEquals(expected.getSecurityResponseID(), actual.getSecurityResponseID());
        assertEquals(expected.getSecurityRequestResult(), actual.getSecurityRequestResult());
        
        assertEquals(expected.getTotNoRelatedSym(), actual.getTotNoRelatedSym());
        assertEquals(expected.getLastFragment(), actual.getLastFragment());
        
        UnderlyingInstrument50SP1TestData.getInstance().check(expected.getUnderlyingInstrument(), actual.getUnderlyingInstrument());
        
        DerivativeSecurityDefinition50SP1TestData.getInstance().check(expected.getDerivativeSecurityDefinition(), actual.getDerivativeSecurityDefinition());

        assertEquals(expected.getNoRelatedSyms(), actual.getNoRelatedSyms());
        DerivSecListGroup50SP1TestData.getInstance().check(expected.getDerivSecListGroups()[0], actual.getDerivSecListGroups()[0]);
        DerivSecListGroup50SP1TestData.getInstance().check(expected.getDerivSecListGroups()[1], actual.getDerivSecListGroups()[1]);
    }
}
