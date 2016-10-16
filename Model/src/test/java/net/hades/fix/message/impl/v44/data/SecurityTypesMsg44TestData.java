/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * SecurityTypesMsg44TestData.java
 *
 * $Id: SecurityTypesMsg44TestData.java,v 1.1 2011-04-27 01:09:59 vrotaru Exp $
 */
package net.hades.fix.message.impl.v44.data;

import java.io.UnsupportedEncodingException;

import static org.junit.Assert.*;

import net.hades.fix.TestUtils;
import net.hades.fix.message.MsgTest;
import net.hades.fix.message.SecurityTypesMsg;
import net.hades.fix.message.group.impl.v43.SecTypesGroup43TestData;
import net.hades.fix.message.group.impl.v44.SecTypesGroup44TestData;
import net.hades.fix.message.type.SecurityResponseType;
import net.hades.fix.message.type.SubscriptionRequestType;
import net.hades.fix.message.type.TradingSessionID;
import net.hades.fix.message.type.TradingSessionSubID;

/**
 * Test utility for SecurityTypesMsg44 message class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 11/05/2009, 12:08:30 PM
 */
public class SecurityTypesMsg44TestData extends MsgTest {

    private static final SecurityTypesMsg44TestData INSTANCE;

    static {
        INSTANCE = new SecurityTypesMsg44TestData();
    }

    public static SecurityTypesMsg44TestData getInstance() {
        return INSTANCE;
    }

    public void populate(SecurityTypesMsg msg) throws UnsupportedEncodingException {
        TestUtils.populate44HeaderAll(msg);
        msg.setSecurityReqID("REQ_11111");
        msg.setSecurityResponseID("RSP888777");
        msg.setSecurityResponseType(SecurityResponseType.AcceptSecProposalAsIs);
        msg.setTotNoSecurityTypes(5);
        msg.setLastFragment(Boolean.FALSE);

        msg.setNoSecurityTypes(2);
        SecTypesGroup43TestData.getInstance().populate1(msg.getSecTypesGroups()[0]);
        SecTypesGroup43TestData.getInstance().populate2(msg.getSecTypesGroups()[1]);

        msg.setText("text");
        msg.setEncodedTextLen(new Integer(8));
        byte[] encodedText = new byte[] {(byte) 18, (byte) 32, (byte) 43, (byte) 95,
            (byte) 177, (byte) 198, (byte) 224, (byte) 253};
        msg.setEncodedText(encodedText);
        msg.setTradingSessionID(TradingSessionID.Day.getValue());
        msg.setTradingSessionSubID(TradingSessionSubID.Intraday.getValue());
        msg.setSubscriptionRequestType(SubscriptionRequestType.Subscribe);
    }

    public void check(SecurityTypesMsg expected, SecurityTypesMsg actual) throws Exception {
        assertEquals(expected.getSecurityReqID(), actual.getSecurityReqID());
        assertEquals(expected.getSecurityResponseID(), actual.getSecurityResponseID());
        assertEquals(expected.getSecurityResponseType(), actual.getSecurityResponseType());
        assertEquals(expected.getTotNoSecurityTypes(), actual.getTotNoSecurityTypes());
        assertEquals(expected.getLastFragment(), actual.getLastFragment());

        assertEquals(expected.getNoSecurityTypes(), actual.getNoSecurityTypes());
        SecTypesGroup44TestData.getInstance().check(expected.getSecTypesGroups()[0], actual.getSecTypesGroups()[0]);
        SecTypesGroup44TestData.getInstance().check(expected.getSecTypesGroups()[1], actual.getSecTypesGroups()[1]);

        assertEquals(expected.getText(), actual.getText());
        assertEquals(expected.getEncodedTextLen().intValue(), actual.getEncodedTextLen().intValue());
        assertArrayEquals(expected.getEncodedText(), actual.getEncodedText());
        assertEquals(expected.getTradingSessionID(), actual.getTradingSessionID());
        assertEquals(expected.getTradingSessionSubID(), actual.getTradingSessionSubID());
        assertEquals(expected.getSubscriptionRequestType(), actual.getSubscriptionRequestType());
    }
}
