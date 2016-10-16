/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * SecurityListMsg43TestData.java
 *
 * $Id: SecurityListMsg43TestData.java,v 1.1 2011-04-29 03:11:04 vrotaru Exp $
 */
package net.hades.fix.message.impl.v43.data;

import java.io.UnsupportedEncodingException;

import static org.junit.Assert.*;

import net.hades.fix.TestUtils;
import net.hades.fix.message.MsgTest;
import net.hades.fix.message.SecurityListMsg;
import net.hades.fix.message.group.impl.v43.SecListGroup43TestData;
import net.hades.fix.message.type.SecurityRequestResult;

/**
 * Test utility for SecurityListMsg43 message class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 11/05/2009, 12:08:30 PM
 */
public class SecurityListMsg43TestData extends MsgTest {

    private static final SecurityListMsg43TestData INSTANCE;

    static {
        INSTANCE = new SecurityListMsg43TestData();
    }

    public static SecurityListMsg43TestData getInstance() {
        return INSTANCE;
    }

    public void populate(SecurityListMsg msg) throws UnsupportedEncodingException {
        TestUtils.populate43HeaderAll(msg);
        msg.setSecurityReqID("REQ_11111");
        msg.setSecurityRequestResult(SecurityRequestResult.ValidRequest);

        msg.setNoRelatedSyms(2);
        SecListGroup43TestData.getInstance().populate1(msg.getSecListGroups()[0]);
        SecListGroup43TestData.getInstance().populate2(msg.getSecListGroups()[1]);
    }

    public void check(SecurityListMsg expected, SecurityListMsg actual) throws Exception {
        assertEquals(expected.getSecurityReqID(), actual.getSecurityReqID());
        assertEquals(expected.getSecurityRequestResult(), actual.getSecurityRequestResult());

        assertEquals(expected.getNoRelatedSyms(), actual.getNoRelatedSyms());
        SecListGroup43TestData.getInstance().check(expected.getSecListGroups()[0], actual.getSecListGroups()[0]);
        SecListGroup43TestData.getInstance().check(expected.getSecListGroups()[1], actual.getSecListGroups()[1]);
    }
}
