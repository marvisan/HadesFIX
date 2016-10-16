/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * MassQuoteAckMsg42TestData.java
 *
 * $Id: MassQuoteAckMsg42TestData.java,v 1.1 2009-07-22 10:38:38 vrotaru Exp $
 */
package net.hades.fix.message.impl.v42.data;

import java.io.UnsupportedEncodingException;

import static org.junit.Assert.*;

import net.hades.fix.TestUtils;
import net.hades.fix.message.MassQuoteAckMsg;
import net.hades.fix.message.MsgTest;
import net.hades.fix.message.group.impl.v42.QuoteSetAckGroup42TestData;
import net.hades.fix.message.type.QuoteRejectReason;
import net.hades.fix.message.type.QuoteResponseLevel;
import net.hades.fix.message.type.QuoteStatus;
import net.hades.fix.message.type.TradingSessionID;

/**
 * Test utility for MassQuoteAckMsg42 message class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 11/05/2009, 12:08:30 PM
 */
public class MassQuoteAckMsg42TestData extends MsgTest {

    private static final MassQuoteAckMsg42TestData INSTANCE;

    static {
        INSTANCE = new MassQuoteAckMsg42TestData();
    }

    public static MassQuoteAckMsg42TestData getInstance() {
        return INSTANCE;
    }

    public void populate(quickfix.fix42.QuoteAcknowledgement msg) throws Exception {
        TestUtils.populateQuickFIX42HeaderAll(msg);
        msg.setString(quickfix.field.QuoteReqID.FIELD, "X162773883");
        msg.setString(quickfix.field.QuoteID.FIELD, "X162773883");
        msg.setInt(quickfix.field.QuoteStatus.FIELD, quickfix.field.QuoteStatus.ACCEPTED);
        msg.setInt(quickfix.field.QuoteRejectReason.FIELD, quickfix.field.QuoteRejectReason.DUPLICATE_QUOTE);
        msg.setInt(quickfix.field.QuoteResponseLevel.FIELD, quickfix.field.QuoteResponseLevel.ACKNOWLEDGE_EACH_QUOTE_MESSAGES);
        msg.setString(quickfix.field.TradingSessionID.FIELD, TradingSessionID.AfterHours.getValue());
        msg.setString(quickfix.field.Text.FIELD, "Text test");
        // QuoteSetGroup
        msg.setInt(quickfix.field.NoQuoteSets.FIELD, 2);
        quickfix.fix42.QuoteAcknowledgement.NoQuoteSets grp1 = new quickfix.fix42.QuoteAcknowledgement.NoQuoteSets();
        QuoteSetAckGroup42TestData.getInstance().populate1(grp1);
        msg.addGroup(grp1);
        quickfix.fix42.QuoteAcknowledgement.NoQuoteSets grp2 = new quickfix.fix42.QuoteAcknowledgement.NoQuoteSets();
        QuoteSetAckGroup42TestData.getInstance().populate2(grp2);
        msg.addGroup(grp2);
    }

    public void populate(MassQuoteAckMsg msg) throws UnsupportedEncodingException {
        TestUtils.populate42HeaderAll(msg);
        msg.setQuoteReqID("AAA564567");
        msg.setQuoteID("X162773883");
        msg.setQuoteStatus(QuoteStatus.Accepted);
        msg.setQuoteRejectReason(QuoteRejectReason.DuplicateQuote.getValue());
        msg.setQuoteResponseLevel(QuoteResponseLevel.AckOnlyNegativeOrErroneous);
        msg.setTradingSessionID(TradingSessionID.AfterHours.getValue());
        msg.setText("Text test");
        // QuoteSetGroup
        msg.setNoQuoteSets(new Integer(2));
        QuoteSetAckGroup42TestData.getInstance().populate1(msg.getQuoteSetAckGroups()[0]);
        QuoteSetAckGroup42TestData.getInstance().populate2(msg.getQuoteSetAckGroups()[1]);
    }

    public void check(quickfix.fix42.QuoteAcknowledgement expected, MassQuoteAckMsg actual) throws Exception {
        assertEquals(expected.getString(quickfix.field.QuoteID.FIELD), actual.getQuoteID());
        assertEquals(expected.getString(quickfix.field.QuoteReqID.FIELD), actual.getQuoteReqID());
        assertEquals(expected.getInt(quickfix.field.QuoteStatus.FIELD), actual.getQuoteStatus().getValue());
        assertEquals(expected.getInt(quickfix.field.QuoteRejectReason.FIELD), actual.getQuoteRejectReason().intValue());
        assertEquals(expected.getInt(quickfix.field.QuoteResponseLevel.FIELD), actual.getQuoteResponseLevel().getValue());
        assertEquals(expected.getString(quickfix.field.TradingSessionID.FIELD), actual.getTradingSessionID());
        assertEquals(expected.getString(quickfix.field.Text.FIELD), actual.getText());
        // QuoteSetGroup
        assertEquals(expected.getInt(quickfix.field.NoQuoteSets.FIELD), actual.getNoQuoteSets().intValue());
        quickfix.fix42.QuoteAcknowledgement.NoQuoteSets grp1 = new quickfix.fix42.QuoteAcknowledgement.NoQuoteSets();
        expected.getGroup(1, grp1);
        QuoteSetAckGroup42TestData.getInstance().check(grp1, actual.getQuoteSetAckGroups()[0]);
        quickfix.fix42.QuoteAcknowledgement.NoQuoteSets grp2 = new quickfix.fix42.QuoteAcknowledgement.NoQuoteSets();
        expected.getGroup(2, grp2);
        QuoteSetAckGroup42TestData.getInstance().check(grp2, actual.getQuoteSetAckGroups()[1]);
    }

    public void check(MassQuoteAckMsg expected, quickfix.fix42.QuoteAcknowledgement actual) throws Exception {
        assertEquals(expected.getQuoteReqID(), actual.getString(quickfix.field.QuoteReqID.FIELD));
        assertEquals(expected.getQuoteID(), actual.getString(quickfix.field.QuoteID.FIELD));
        assertEquals(expected.getQuoteStatus().getValue(), actual.getInt(quickfix.field.QuoteStatus.FIELD));
        assertEquals(expected.getQuoteRejectReason().intValue(), actual.getInt(quickfix.field.QuoteRejectReason.FIELD));
        assertEquals(expected.getQuoteResponseLevel().getValue(), actual.getInt(quickfix.field.QuoteResponseLevel.FIELD));
        assertEquals(expected.getTradingSessionID(), actual.getString(quickfix.field.TradingSessionID.FIELD));
        assertEquals(expected.getText(), actual.getString(quickfix.field.Text.FIELD));
        // QuoteSetGroup
        assertEquals(expected.getNoQuoteSets().intValue(), actual.getInt(quickfix.field.NoQuoteSets.FIELD));
        quickfix.fix42.QuoteAcknowledgement.NoQuoteSets grp1 = new quickfix.fix42.QuoteAcknowledgement.NoQuoteSets();
        actual.getGroup(1, grp1);
        QuoteSetAckGroup42TestData.getInstance().check(expected.getQuoteSetAckGroups()[0], grp1);
        quickfix.fix42.QuoteAcknowledgement.NoQuoteSets grp2 = new quickfix.fix42.QuoteAcknowledgement.NoQuoteSets();
        actual.getGroup(2, grp2);
        QuoteSetAckGroup42TestData.getInstance().check(expected.getQuoteSetAckGroups()[1], grp2);
    }

    public void check(MassQuoteAckMsg expected, MassQuoteAckMsg actual) throws Exception {
        assertEquals(expected.getQuoteReqID(), actual.getQuoteReqID());
        assertEquals(expected.getQuoteID(), actual.getQuoteID());
        assertEquals(expected.getQuoteStatus().getValue(), actual.getQuoteStatus().getValue());
        assertEquals(expected.getQuoteRejectReason().intValue(), actual.getQuoteRejectReason().intValue());
        assertEquals(expected.getQuoteResponseLevel().getValue(), actual.getQuoteResponseLevel().getValue());
        assertEquals(expected.getTradingSessionID(), actual.getTradingSessionID());
        assertEquals(expected.getText(), actual.getText());
        // QuoteSetGroup
        assertEquals(expected.getNoQuoteSets().intValue(), actual.getNoQuoteSets().intValue());
        QuoteSetAckGroup42TestData.getInstance().check(expected.getQuoteSetAckGroups()[0], actual.getQuoteSetAckGroups()[0]);
        QuoteSetAckGroup42TestData.getInstance().check(expected.getQuoteSetAckGroups()[1], actual.getQuoteSetAckGroups()[1]);
    }
}
