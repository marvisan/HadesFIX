/*
 *   Copyright (c) 2006-2009 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * MassQuoteAckMsg44TestData.java
 *
 * $Id: MassQuoteAckMsg44TestData.java,v 1.1 2009-07-24 02:16:31 vrotaru Exp $
 */
package net.hades.fix.message.impl.v44.data;

import java.io.UnsupportedEncodingException;

import static org.junit.Assert.*;

import net.hades.fix.TestUtils;
import net.hades.fix.message.MassQuoteAckMsg;
import net.hades.fix.message.MsgTest;
import net.hades.fix.message.comp.impl.v44.Parties44TestData;
import net.hades.fix.message.group.impl.v44.QuoteSetAckGroup44TestData;
import net.hades.fix.message.type.AccountType;
import net.hades.fix.message.type.AcctIDSource;
import net.hades.fix.message.type.QuoteRejectReason;
import net.hades.fix.message.type.QuoteResponseLevel;
import net.hades.fix.message.type.QuoteStatus;
import net.hades.fix.message.type.QuoteType;

/**
 * Test utility for MassQuoteAckMsg44 message class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 11/05/2009, 12:08:30 PM
 */
public class MassQuoteAckMsg44TestData extends MsgTest {

    private static final MassQuoteAckMsg44TestData INSTANCE;

    static {
        INSTANCE = new MassQuoteAckMsg44TestData();
    }

    public static MassQuoteAckMsg44TestData getInstance() {
        return INSTANCE;
    }

    public void populate(quickfix.fix44.MassQuoteAcknowledgement msg) throws Exception {
        TestUtils.populateQuickFIX44HeaderAll(msg);
        msg.setString(quickfix.field.QuoteReqID.FIELD, "X162773883");
        msg.setString(quickfix.field.QuoteID.FIELD, "X162773883");
        msg.setInt(quickfix.field.QuoteStatus.FIELD, quickfix.field.QuoteStatus.ACCEPTED);
        msg.setInt(quickfix.field.QuoteRejectReason.FIELD, quickfix.field.QuoteRejectReason.DUPLICATE_QUOTE);
        msg.setInt(quickfix.field.QuoteResponseLevel.FIELD, quickfix.field.QuoteResponseLevel.ACKNOWLEDGE_EACH_QUOTE_MESSAGES);
        msg.setInt(quickfix.field.QuoteType.FIELD, quickfix.field.QuoteType.INDICATIVE);
        // Parties
        quickfix.fix44.component.Parties part = new quickfix.fix44.component.Parties();
        Parties44TestData.getInstance().populate(part);
        msg.set(part);

        msg.setString(quickfix.field.Account.FIELD, "353453454");
        msg.setInt(quickfix.field.AcctIDSource.FIELD, quickfix.field.AcctIDSource.BIC);
        msg.setInt(quickfix.field.AccountType.FIELD, quickfix.field.AccountType.FLOOR_TRADER);
        msg.setString(quickfix.field.Text.FIELD, "Text test");
        byte[] textDataExp = new byte[] {(byte) 19, (byte) 34, (byte) 45, (byte) 97,
            (byte) 178, (byte) 200, (byte) 225, (byte) 254};
        msg.setInt(quickfix.field.EncodedTextLen.FIELD, 8);
        msg.setString(quickfix.field.EncodedText.FIELD, new String(textDataExp, DEFAULT_CHARACTER_SET));
        // QuoteSetGroup
        msg.setInt(quickfix.field.NoQuoteSets.FIELD, 2);
        quickfix.fix44.MassQuoteAcknowledgement.NoQuoteSets grp1 = new quickfix.fix44.MassQuoteAcknowledgement.NoQuoteSets();
        QuoteSetAckGroup44TestData.getInstance().populate1(grp1);
        msg.addGroup(grp1);
        quickfix.fix44.MassQuoteAcknowledgement.NoQuoteSets grp2 = new quickfix.fix44.MassQuoteAcknowledgement.NoQuoteSets();
        QuoteSetAckGroup44TestData.getInstance().populate2(grp2);
        msg.addGroup(grp2);
    }

    public void populate(MassQuoteAckMsg msg) throws UnsupportedEncodingException {
        TestUtils.populate44HeaderAll(msg);
        msg.setQuoteReqID("AAA564567");
        msg.setQuoteID("X162773883");
        msg.setQuoteStatus(QuoteStatus.Accepted);
        msg.setQuoteRejectReason(QuoteRejectReason.DuplicateQuote.getValue());
        msg.setQuoteResponseLevel(QuoteResponseLevel.AckOnlyNegativeOrErroneous);
        msg.setQuoteType(QuoteType.Tradeable);
        // Parties
        msg.setParties();
        Parties44TestData.getInstance().populate(msg.getParties());

        msg.setAccount("837463783");
        msg.setAcctIDSource(AcctIDSource.SID);
        msg.setAccountType(AccountType.HouseTrader);
        msg.setText("Text test");
        msg.setEncodedTextLen(new Integer(8));
        byte[] encodedText = new byte[] {(byte) 18, (byte) 33, (byte) 44, (byte) 96,
            (byte) 177, (byte) 199, (byte) 224, (byte) 253};
        msg.setEncodedText(encodedText);
        // QuoteSetGroup
        msg.setNoQuoteSets(new Integer(2));
        QuoteSetAckGroup44TestData.getInstance().populate1(msg.getQuoteSetAckGroups()[0]);
        QuoteSetAckGroup44TestData.getInstance().populate2(msg.getQuoteSetAckGroups()[1]);
    }

    public void check(quickfix.fix44.MassQuoteAcknowledgement expected, MassQuoteAckMsg actual) throws Exception {
        assertEquals(expected.getString(quickfix.field.QuoteID.FIELD), actual.getQuoteID());
        assertEquals(expected.getString(quickfix.field.QuoteReqID.FIELD), actual.getQuoteReqID());
        assertEquals(expected.getInt(quickfix.field.QuoteStatus.FIELD), actual.getQuoteStatus().getValue());
        assertEquals(expected.getInt(quickfix.field.QuoteRejectReason.FIELD), actual.getQuoteRejectReason().intValue());
        assertEquals(expected.getInt(quickfix.field.QuoteResponseLevel.FIELD), actual.getQuoteResponseLevel().getValue());
        assertEquals(expected.getInt(quickfix.field.QuoteType.FIELD), actual.getQuoteType().getValue());
        assertEquals(expected.getString(quickfix.field.Account.FIELD), actual.getAccount());
        assertEquals(expected.getInt(quickfix.field.AcctIDSource.FIELD), actual.getAcctIDSource().getValue());
        assertEquals(expected.getInt(quickfix.field.AccountType.FIELD), actual.getAccountType().getValue());
        assertEquals(expected.getString(quickfix.field.Text.FIELD), actual.getText());
        assertEquals(expected.getInt(quickfix.field.EncodedTextLen.FIELD), actual.getEncodedTextLen().intValue());
        assertArrayEquals(expected.getString(quickfix.field.EncodedText.FIELD).getBytes(DEFAULT_CHARACTER_SET),
            actual.getEncodedText());
        // QuoteSetGroup
        assertEquals(expected.getInt(quickfix.field.NoQuoteSets.FIELD), actual.getNoQuoteSets().intValue());
        quickfix.fix44.MassQuoteAcknowledgement.NoQuoteSets grp1 = new quickfix.fix44.MassQuoteAcknowledgement.NoQuoteSets();
        expected.getGroup(1, grp1);
        QuoteSetAckGroup44TestData.getInstance().check(grp1, actual.getQuoteSetAckGroups()[0]);
        quickfix.fix44.MassQuoteAcknowledgement.NoQuoteSets grp2 = new quickfix.fix44.MassQuoteAcknowledgement.NoQuoteSets();
        expected.getGroup(2, grp2);
        QuoteSetAckGroup44TestData.getInstance().check(grp2, actual.getQuoteSetAckGroups()[1]);
    }

    public void check(MassQuoteAckMsg expected, quickfix.fix44.MassQuoteAcknowledgement actual) throws Exception {
        assertEquals(expected.getQuoteReqID(), actual.getString(quickfix.field.QuoteReqID.FIELD));
        assertEquals(expected.getQuoteID(), actual.getString(quickfix.field.QuoteID.FIELD));
        assertEquals(expected.getQuoteStatus().getValue(), actual.getInt(quickfix.field.QuoteStatus.FIELD));
        assertEquals(expected.getQuoteRejectReason().intValue(), actual.getInt(quickfix.field.QuoteRejectReason.FIELD));
        assertEquals(expected.getQuoteResponseLevel().getValue(), actual.getInt(quickfix.field.QuoteResponseLevel.FIELD));
        assertEquals(expected.getQuoteType().getValue(), actual.getInt(quickfix.field.QuoteType.FIELD));
        // Parties
        Parties44TestData.getInstance().check(expected.getParties(), actual.getParties());

        assertEquals(expected.getAccount(), actual.getString(quickfix.field.Account.FIELD));
        assertEquals(expected.getAcctIDSource().getValue(), actual.getInt(quickfix.field.AcctIDSource.FIELD));
        assertEquals(expected.getAccountType().getValue(), actual.getInt(quickfix.field.AccountType.FIELD));
        assertEquals(expected.getText(), actual.getString(quickfix.field.Text.FIELD));
        assertEquals(expected.getEncodedTextLen().intValue(), actual.getInt(quickfix.field.EncodedTextLen.FIELD));
        assertArrayEquals(expected.getEncodedText(), actual.getString(quickfix.field.EncodedText.FIELD).getBytes(DEFAULT_CHARACTER_SET));
        // QuoteSetGroup
        assertEquals(expected.getNoQuoteSets().intValue(), actual.getInt(quickfix.field.NoQuoteSets.FIELD));
        quickfix.fix44.MassQuoteAcknowledgement.NoQuoteSets grp1 = new quickfix.fix44.MassQuoteAcknowledgement.NoQuoteSets();
        actual.getGroup(1, grp1);
        QuoteSetAckGroup44TestData.getInstance().check(expected.getQuoteSetAckGroups()[0], grp1);
        quickfix.fix44.MassQuoteAcknowledgement.NoQuoteSets grp2 = new quickfix.fix44.MassQuoteAcknowledgement.NoQuoteSets();
        actual.getGroup(2, grp2);
        QuoteSetAckGroup44TestData.getInstance().check(expected.getQuoteSetAckGroups()[1], grp2);
    }

    public void check(MassQuoteAckMsg expected, MassQuoteAckMsg actual) throws Exception {
        assertEquals(expected.getQuoteReqID(), actual.getQuoteReqID());
        assertEquals(expected.getQuoteID(), actual.getQuoteID());
        assertEquals(expected.getQuoteStatus().getValue(), actual.getQuoteStatus().getValue());
        assertEquals(expected.getQuoteRejectReason().intValue(), actual.getQuoteRejectReason().intValue());
        assertEquals(expected.getQuoteResponseLevel().getValue(), actual.getQuoteResponseLevel().getValue());
        assertEquals(expected.getQuoteType().getValue(), actual.getQuoteType().getValue());
        // Parties
        Parties44TestData.getInstance().check(expected.getParties(), actual.getParties());
        
        assertEquals(expected.getAccount(), actual.getAccount());
        assertEquals(expected.getAcctIDSource().getValue(), actual.getAcctIDSource().getValue());
        assertEquals(expected.getAccountType().getValue(), actual.getAccountType().getValue());
        assertEquals(expected.getText(), actual.getText());
        assertEquals(expected.getEncodedTextLen().intValue(), actual.getEncodedTextLen().intValue());
        assertArrayEquals(expected.getEncodedText(), actual.getEncodedText());
        // QuoteSetGroup
        assertEquals(expected.getNoQuoteSets().intValue(), actual.getNoQuoteSets().intValue());
        QuoteSetAckGroup44TestData.getInstance().check(expected.getQuoteSetAckGroups()[0], actual.getQuoteSetAckGroups()[0]);
        QuoteSetAckGroup44TestData.getInstance().check(expected.getQuoteSetAckGroups()[1], actual.getQuoteSetAckGroups()[1]);
    }
}
