/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * SecurityListMsg44TestData.java
 *
 * $Id: SecurityListMsg44TestData.java,v 1.1 2011-04-29 03:11:04 vrotaru Exp $
 */
package net.hades.fix.message.impl.v44.data;

import java.io.UnsupportedEncodingException;

import static org.junit.Assert.*;

import net.hades.fix.TestUtils;
import net.hades.fix.message.MsgTest;
import net.hades.fix.message.SecurityListMsg;
import net.hades.fix.message.group.impl.v44.SecListGroup44TestData;
import net.hades.fix.message.type.SecurityRequestResult;

/**
 * Test utility for SecurityListMsg44 message class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 11/05/2009, 12:08:30 PM
 */
public class SecurityListMsg44TestData extends MsgTest {

    private static final SecurityListMsg44TestData INSTANCE;

    static {
        INSTANCE = new SecurityListMsg44TestData();
    }

    public static SecurityListMsg44TestData getInstance() {
        return INSTANCE;
    }

    public void populate(SecurityListMsg msg) throws UnsupportedEncodingException {
        TestUtils.populate44HeaderAll(msg);
        msg.setSecurityReqID("REQ_11111");
        msg.setSecurityResponseID("RSP888777");
        msg.setSecurityRequestResult(SecurityRequestResult.ValidRequest);
        msg.setTotNoRelatedSym(34);
        msg.setLastFragment(Boolean.FALSE);

        msg.setNoRelatedSyms(2);
        SecListGroup44TestData.getInstance().populate1(msg.getSecListGroups()[0]);
        SecListGroup44TestData.getInstance().populate2(msg.getSecListGroups()[1]);
    }

    public void check(SecurityListMsg expected, SecurityListMsg actual) throws Exception {
        assertEquals(expected.getSecurityReqID(), actual.getSecurityReqID());
        assertEquals(expected.getSecurityResponseID(), actual.getSecurityResponseID());
        assertEquals(expected.getSecurityRequestResult(), actual.getSecurityRequestResult());
        assertEquals(expected.getTotNoRelatedSym(), actual.getTotNoRelatedSym());
        assertEquals(expected.getLastFragment(), actual.getLastFragment());

        assertEquals(expected.getNoRelatedSyms(), actual.getNoRelatedSyms());
        SecListGroup44TestData.getInstance().check(expected.getSecListGroups()[0], actual.getSecListGroups()[0]);
        SecListGroup44TestData.getInstance().check(expected.getSecListGroups()[1], actual.getSecListGroups()[1]);
    }
}
