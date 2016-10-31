/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * RelatedSymbolGroup41TestData.java
 *
 * $Id: RelatedSymbolGroup41TestData.java,v 1.1 2009-07-06 03:19:15 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v41;

import java.io.UnsupportedEncodingException;

import static org.junit.Assert.*;

import net.hades.fix.message.MsgTest;
import net.hades.fix.message.group.RelatedSymbolGroup;
import net.hades.fix.message.type.PutOrCall;
import net.hades.fix.message.type.SecurityType;

/**
 * Test utility for RelatedSymbolGroup group class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 31/03/2009, 9:12:46 AM
 */
public class RelatedSymbolGroup41TestData extends MsgTest {

    private static final RelatedSymbolGroup41TestData INSTANCE;

    static {
        INSTANCE = new RelatedSymbolGroup41TestData();
    }

    public static RelatedSymbolGroup41TestData getInstance() {
        return INSTANCE;
    }

    public void populate1(quickfix.fix41.News.NoRelatedSym msg) throws UnsupportedEncodingException {
        msg.setString(quickfix.field.RelatdSym.FIELD, "MOT");
        msg.setString(quickfix.field.SymbolSfx.FIELD, "MOT shares");
        msg.setString(quickfix.field.SecurityID.FIELD, "Sec ID");
        msg.setString(quickfix.field.IDSource.FIELD, "ID src");
        msg.setString(quickfix.field.SecurityType.FIELD, quickfix.field.SecurityType.OPTION);
        msg.setString(quickfix.field.MaturityMonthYear.FIELD, "032009");
        msg.setString(quickfix.field.MaturityDay.FIELD, "30");
        msg.setInt(quickfix.field.PutOrCall.FIELD, PutOrCall.Call.getValue());
        msg.setDouble(quickfix.field.StrikePrice.FIELD, 23.24);
        msg.setChar(quickfix.field.OptAttribute.FIELD, 'B');
        msg.setString(quickfix.field.SecurityExchange.FIELD, "CBOE");
        msg.setString(quickfix.field.Issuer.FIELD, "NYSE");
        msg.setString(quickfix.field.SecurityDesc.FIELD, "Some MOT shares");
    }

    public void populate2(quickfix.fix41.News.NoRelatedSym msg) throws UnsupportedEncodingException {
        msg.setString(quickfix.field.RelatdSym.FIELD, "JAVA");
        msg.setString(quickfix.field.SymbolSfx.FIELD, "JAVA shares");
        msg.setString(quickfix.field.SecurityID.FIELD, "Sec ID 2");
        msg.setString(quickfix.field.IDSource.FIELD, "ID src 22");
        msg.setString(quickfix.field.SecurityType.FIELD, quickfix.field.SecurityType.OPTIONS_ON_FUTURES);
        msg.setString(quickfix.field.MaturityMonthYear.FIELD, "042009");
        msg.setString(quickfix.field.MaturityDay.FIELD, "21");
        msg.setInt(quickfix.field.PutOrCall.FIELD, PutOrCall.Put.getValue());
        msg.setDouble(quickfix.field.StrikePrice.FIELD, 23.11);
        msg.setChar(quickfix.field.OptAttribute.FIELD, 'A');
        msg.setString(quickfix.field.SecurityExchange.FIELD, "LMX");
        msg.setString(quickfix.field.Issuer.FIELD, "CBOE");
        msg.setString(quickfix.field.SecurityDesc.FIELD, "Some JAVA shares");
    }

    public void populate1(quickfix.fix41.Email.NoRelatedSym msg) throws UnsupportedEncodingException {
        msg.setString(quickfix.field.RelatdSym.FIELD, "MOT");
        msg.setString(quickfix.field.SymbolSfx.FIELD, "MOT shares");
        msg.setString(quickfix.field.SecurityID.FIELD, "Sec ID");
        msg.setString(quickfix.field.IDSource.FIELD, "ID src");
        msg.setString(quickfix.field.SecurityType.FIELD, quickfix.field.SecurityType.OPTION);
        msg.setString(quickfix.field.MaturityMonthYear.FIELD, "032009");
        msg.setString(quickfix.field.MaturityDay.FIELD, "30");
        msg.setInt(quickfix.field.PutOrCall.FIELD, PutOrCall.Call.getValue());
        msg.setDouble(quickfix.field.StrikePrice.FIELD, 23.24);
        msg.setChar(quickfix.field.OptAttribute.FIELD, 'B');
        msg.setString(quickfix.field.SecurityExchange.FIELD, "CBOE");
        msg.setString(quickfix.field.Issuer.FIELD, "NYSE");
        msg.setString(quickfix.field.SecurityDesc.FIELD, "Some MOT shares");
    }

    public void populate2(quickfix.fix41.Email.NoRelatedSym msg) throws UnsupportedEncodingException {
        msg.setString(quickfix.field.RelatdSym.FIELD, "JAVA");
        msg.setString(quickfix.field.SymbolSfx.FIELD, "JAVA shares");
        msg.setString(quickfix.field.SecurityID.FIELD, "Sec ID 2");
        msg.setString(quickfix.field.IDSource.FIELD, "ID src 22");
        msg.setString(quickfix.field.SecurityType.FIELD, quickfix.field.SecurityType.OPTIONS_ON_FUTURES);
        msg.setString(quickfix.field.MaturityMonthYear.FIELD, "042009");
        msg.setString(quickfix.field.MaturityDay.FIELD, "21");
        msg.setInt(quickfix.field.PutOrCall.FIELD, PutOrCall.Put.getValue());
        msg.setDouble(quickfix.field.StrikePrice.FIELD, 23.11);
        msg.setChar(quickfix.field.OptAttribute.FIELD, 'A');
        msg.setString(quickfix.field.SecurityExchange.FIELD, "LMX");
        msg.setString(quickfix.field.Issuer.FIELD, "CBOE");
        msg.setString(quickfix.field.SecurityDesc.FIELD, "Some JAVA shares");
    }

    public void populate1(RelatedSymbolGroup msg) throws UnsupportedEncodingException {
        msg.setRelatedSym("MOT");
        msg.setSymbolSfx("MOT shares");
        msg.setSecurityID("Sec ID");
        msg.setSecurityIDSource("ID src");
        msg.setSecurityType(SecurityType.AmendedRestated);
        msg.setMaturityMonthYear("032009");
        msg.setMaturityDay(new Integer(12));
        msg.setPutOrCall(PutOrCall.Call);
        msg.setStrikePrice(new Double(23.24));
        msg.setOptAttribute(new Character('B'));
        msg.setSecurityExchange("CBOE");
        msg.setIssuer("NYSE");
        msg.setSecurityDesc("Some MOT shares");
    }

    public void populate2(RelatedSymbolGroup msg) throws UnsupportedEncodingException {
        msg.setRelatedSym("JAVA");
        msg.setSymbolSfx("JAVA shares");
        msg.setSecurityID("Sec ID 2");
        msg.setSecurityIDSource("ID src 2");
        msg.setSecurityType(SecurityType.AssetBackedSecurities);
        msg.setMaturityMonthYear("042009");
        msg.setMaturityDay(new Integer(14));
        msg.setPutOrCall(PutOrCall.Put);
        msg.setStrikePrice(new Double(23.14));
        msg.setOptAttribute(new Character('C'));
        msg.setSecurityExchange("LMX");
        msg.setIssuer("ASX");
        msg.setSecurityDesc("Some JAVA shares");
    }

    public void check(RelatedSymbolGroup expected, quickfix.fix41.News.NoRelatedSym actual) throws Exception {
        assertEquals(expected.getRelatedSym(), actual.getString(quickfix.field.RelatdSym.FIELD));
        assertEquals(expected.getSymbolSfx(), actual.getString(quickfix.field.SymbolSfx.FIELD));
        assertEquals(expected.getSecurityID(), actual.getString(quickfix.field.SecurityID.FIELD));
        assertEquals(expected.getSecurityIDSource(), actual.getString(quickfix.field.IDSource.FIELD));
        assertEquals(expected.getSecurityType().getValue(), actual.getString(quickfix.field.SecurityType.FIELD));
        assertEquals(expected.getMaturityMonthYear(), actual.getString(quickfix.field.MaturityMonthYear.FIELD));
        assertEquals(expected.getMaturityDay().toString(), actual.getString(quickfix.field.MaturityDay.FIELD));
        assertEquals(expected.getPutOrCall().getValue(), actual.getInt(quickfix.field.PutOrCall.FIELD));
        assertEquals(expected.getStrikePrice().doubleValue(), actual.getDouble(quickfix.field.StrikePrice.FIELD), 0.001);
        assertEquals(expected.getOptAttribute().charValue(), actual.getChar(quickfix.field.OptAttribute.FIELD));
        assertEquals(expected.getSecurityExchange(), actual.getString(quickfix.field.SecurityExchange.FIELD));
        assertEquals(expected.getIssuer(), actual.getString(quickfix.field.Issuer.FIELD));
        assertEquals(expected.getSecurityDesc(), actual.getString(quickfix.field.SecurityDesc.FIELD));
    }

    public void check(quickfix.fix41.News.NoRelatedSym expected, RelatedSymbolGroup actual) throws Exception {
        assertEquals(expected.getString(quickfix.field.RelatdSym.FIELD), actual.getRelatedSym());
        assertEquals(expected.getString(quickfix.field.SymbolSfx.FIELD), actual.getSymbolSfx());
        assertEquals(expected.getString(quickfix.field.SecurityID.FIELD), actual.getSecurityID());
        assertEquals(expected.getString(quickfix.field.IDSource.FIELD), actual.getSecurityIDSource());
        assertEquals(expected.getString(quickfix.field.SecurityType.FIELD), actual.getSecurityType().getValue());
        assertEquals(expected.getString(quickfix.field.MaturityMonthYear.FIELD), actual.getMaturityMonthYear());
        assertEquals(expected.getString(quickfix.field.MaturityDay.FIELD), actual.getMaturityDay().toString());
        assertEquals(expected.getInt(quickfix.field.PutOrCall.FIELD), actual.getPutOrCall().getValue());
        assertEquals(expected.getDouble(quickfix.field.StrikePrice.FIELD), actual.getStrikePrice().doubleValue(), 0.001);
        assertEquals(expected.getChar(quickfix.field.OptAttribute.FIELD), actual.getOptAttribute().charValue());
        assertEquals(expected.getString(quickfix.field.SecurityExchange.FIELD), actual.getSecurityExchange());
        assertEquals(expected.getString(quickfix.field.Issuer.FIELD), actual.getIssuer());
        assertEquals(expected.getString(quickfix.field.SecurityDesc.FIELD), actual.getSecurityDesc());
    }

    public void check(RelatedSymbolGroup expected, quickfix.fix41.Email.NoRelatedSym actual) throws Exception {
        assertEquals(expected.getRelatedSym(), actual.getString(quickfix.field.RelatdSym.FIELD));
        assertEquals(expected.getSymbolSfx(), actual.getString(quickfix.field.SymbolSfx.FIELD));
        assertEquals(expected.getSecurityID(), actual.getString(quickfix.field.SecurityID.FIELD));
        assertEquals(expected.getSecurityIDSource(), actual.getString(quickfix.field.IDSource.FIELD));
        assertEquals(expected.getSecurityType().getValue(), actual.getString(quickfix.field.SecurityType.FIELD));
        assertEquals(expected.getMaturityMonthYear(), actual.getString(quickfix.field.MaturityMonthYear.FIELD));
        assertEquals(expected.getMaturityDay().toString(), actual.getString(quickfix.field.MaturityDay.FIELD));
        assertEquals(expected.getPutOrCall().getValue(), actual.getInt(quickfix.field.PutOrCall.FIELD));
        assertEquals(expected.getStrikePrice().doubleValue(), actual.getDouble(quickfix.field.StrikePrice.FIELD), 0.001);
        assertEquals(expected.getOptAttribute().charValue(), actual.getChar(quickfix.field.OptAttribute.FIELD));
        assertEquals(expected.getSecurityExchange(), actual.getString(quickfix.field.SecurityExchange.FIELD));
        assertEquals(expected.getIssuer(), actual.getString(quickfix.field.Issuer.FIELD));
        assertEquals(expected.getSecurityDesc(), actual.getString(quickfix.field.SecurityDesc.FIELD));
    }

    public void check(quickfix.fix41.Email.NoRelatedSym expected, RelatedSymbolGroup actual) throws Exception {
        assertEquals(expected.getString(quickfix.field.RelatdSym.FIELD), actual.getRelatedSym());
        assertEquals(expected.getString(quickfix.field.SymbolSfx.FIELD), actual.getSymbolSfx());
        assertEquals(expected.getString(quickfix.field.SecurityID.FIELD), actual.getSecurityID());
        assertEquals(expected.getString(quickfix.field.IDSource.FIELD), actual.getSecurityIDSource());
        assertEquals(expected.getString(quickfix.field.SecurityType.FIELD), actual.getSecurityType().getValue());
        assertEquals(expected.getString(quickfix.field.MaturityMonthYear.FIELD), actual.getMaturityMonthYear());
        assertEquals(expected.getString(quickfix.field.MaturityDay.FIELD), actual.getMaturityDay().toString());
        assertEquals(expected.getInt(quickfix.field.PutOrCall.FIELD), actual.getPutOrCall().getValue());
        assertEquals(expected.getDouble(quickfix.field.StrikePrice.FIELD), actual.getStrikePrice().doubleValue(), 0.001);
        assertEquals(expected.getChar(quickfix.field.OptAttribute.FIELD), actual.getOptAttribute().charValue());
        assertEquals(expected.getString(quickfix.field.SecurityExchange.FIELD), actual.getSecurityExchange());
        assertEquals(expected.getString(quickfix.field.Issuer.FIELD), actual.getIssuer());
        assertEquals(expected.getString(quickfix.field.SecurityDesc.FIELD), actual.getSecurityDesc());
    }

    public void check(RelatedSymbolGroup expected, RelatedSymbolGroup actual) throws Exception {
        assertEquals(expected.getRelatedSym(), actual.getRelatedSym());
        assertEquals(expected.getSymbolSfx(), actual.getSymbolSfx());
        assertEquals(expected.getSecurityID(), actual.getSecurityID());
        assertEquals(expected.getSecurityIDSource(), actual.getSecurityIDSource());
        assertEquals(expected.getSecurityType().getValue(), actual.getSecurityType().getValue());
        assertEquals(expected.getMaturityMonthYear(), actual.getMaturityMonthYear());
        assertEquals(expected.getMaturityDay().toString(), actual.getMaturityDay().toString());
        assertEquals(expected.getPutOrCall().getValue(), actual.getPutOrCall().getValue());
        assertEquals(expected.getStrikePrice().doubleValue(), actual.getStrikePrice().doubleValue(), 0.001);
        assertEquals(expected.getOptAttribute().charValue(), actual.getOptAttribute().charValue());
        assertEquals(expected.getSecurityExchange(), actual.getSecurityExchange());
        assertEquals(expected.getIssuer(), actual.getIssuer());
        assertEquals(expected.getSecurityDesc(), actual.getSecurityDesc());
    }
}
