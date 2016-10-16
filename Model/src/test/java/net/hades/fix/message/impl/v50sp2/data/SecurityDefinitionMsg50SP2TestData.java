/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * SecurityDefinitionMsg50SP2TestData.java
 *
 * $Id: SecurityDefinitionMsg50SP2TestData.java,v 1.2 2011-10-29 09:42:20 vrotaru Exp $
 */
package net.hades.fix.message.impl.v50sp2.data;

import java.io.UnsupportedEncodingException;
import java.util.Calendar;

import static org.junit.Assert.*;

import net.hades.fix.TestUtils;
import net.hades.fix.message.MsgTest;
import net.hades.fix.message.SecurityDefinitionMsg;
import net.hades.fix.message.comp.impl.v44.InstrumentExtension44TestData;
import net.hades.fix.message.comp.impl.v50.SpreadOrBenchmarkCurveData50TestData;
import net.hades.fix.message.comp.impl.v50.Stipulations50TestData;
import net.hades.fix.message.comp.impl.v50.YieldData50TestData;
import net.hades.fix.message.comp.impl.v50sp2.Instrument50SP2TestData;
import net.hades.fix.message.comp.impl.v50sp2.InstrumentLeg50SP2TestData;
import net.hades.fix.message.comp.impl.v50sp2.UnderlyingInstrument50SP2TestData;
import net.hades.fix.message.group.impl.v50sp1.MarketSegmentGroup50SP1TestData;
import net.hades.fix.message.type.CorporateAction;
import net.hades.fix.message.type.Currency;
import net.hades.fix.message.type.SecurityResponseType;

/**
 * Test utility for SecurityDefinitionMsg50SP2 message class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 * @created 11/05/2009, 12:08:30 PM
 */
public class SecurityDefinitionMsg50SP2TestData extends MsgTest {

    private static final SecurityDefinitionMsg50SP2TestData INSTANCE;

    static {
        INSTANCE = new SecurityDefinitionMsg50SP2TestData();
    }

    public static SecurityDefinitionMsg50SP2TestData getInstance() {
        return INSTANCE;
    }

    public void populate(SecurityDefinitionMsg msg) throws UnsupportedEncodingException {
        TestUtils.populate50HeaderAll(msg);
        Calendar cal = Calendar.getInstance();
        msg.setSecurityReportID(44555);
        cal.set(2010, 3, 14, 12, 13, 13);
        msg.setClearingBusinessDate(cal.getTime());
        msg.setSecurityReqID("REQ_11111");
        msg.setSecurityResponseID("RSP888777");
        msg.setSecurityResponseType(SecurityResponseType.AcceptSecProposalAsIs);
        msg.setCorporateAction(CorporateAction.SpecialAction);

        msg.setInstrument();
        Instrument50SP2TestData.getInstance().populate(msg.getInstrument());

        msg.setInstrumentExtension();
        InstrumentExtension44TestData.getInstance().populate(msg.getInstrumentExtension());

        msg.setNoUnderlyings(new Integer(2));
        UnderlyingInstrument50SP2TestData.getInstance().populate1(msg.getUnderlyingInstruments()[0]);
        UnderlyingInstrument50SP2TestData.getInstance().populate2(msg.getUnderlyingInstruments()[1]);
       
        msg.setCurrency(Currency.AustralianDollar);
        msg.setText("text");
        msg.setEncodedTextLen(new Integer(8));
        byte[] encodedText = new byte[] {(byte) 18, (byte) 32, (byte) 43, (byte) 95,
            (byte) 177, (byte) 198, (byte) 224, (byte) 253};
        msg.setEncodedText(encodedText);

        msg.setStipulations();
        Stipulations50TestData.getInstance().populate(msg.getStipulations());

        msg.setNoLegs(2);
        InstrumentLeg50SP2TestData.getInstance().populate1(msg.getInstrumentLegs()[0]);
        InstrumentLeg50SP2TestData.getInstance().populate2(msg.getInstrumentLegs()[1]);

        msg.setSpreadOrBenchmarkCurveData();
        SpreadOrBenchmarkCurveData50TestData.getInstance().populate(msg.getSpreadOrBenchmarkCurveData());

        msg.setYieldData();
        YieldData50TestData.getInstance().populate(msg.getYieldData());

        msg.setNoMarketSegments(2);
        MarketSegmentGroup50SP1TestData.getInstance().populate1(msg.getMarketSegmentGroups()[0]);
        MarketSegmentGroup50SP1TestData.getInstance().populate2(msg.getMarketSegmentGroups()[1]);

        cal.set(2011, 6, 11, 12, 35, 45);
        msg.setTransactTime(cal.getTime());
    }

    public void check(SecurityDefinitionMsg expected, SecurityDefinitionMsg actual) throws Exception {
        assertEquals(expected.getSecurityReqID(), actual.getSecurityReqID());
        assertDateEquals(expected.getClearingBusinessDate(), actual.getClearingBusinessDate());
        assertEquals(expected.getSecurityResponseID(), actual.getSecurityResponseID());
        assertEquals(expected.getSecurityResponseType(), actual.getSecurityResponseType());
        assertEquals(expected.getCorporateAction(), actual.getCorporateAction());

        Instrument50SP2TestData.getInstance().check(expected.getInstrument(), actual.getInstrument());

        InstrumentExtension44TestData.getInstance().check(expected.getInstrumentExtension(), actual.getInstrumentExtension());

        assertEquals(expected.getNoUnderlyings(), actual.getNoUnderlyings());
        UnderlyingInstrument50SP2TestData.getInstance().check(expected.getUnderlyingInstruments()[0], actual.getUnderlyingInstruments()[0]);
        UnderlyingInstrument50SP2TestData.getInstance().check(expected.getUnderlyingInstruments()[1], actual.getUnderlyingInstruments()[1]);
        
        assertEquals(expected.getCurrency(), actual.getCurrency());
        assertEquals(expected.getText(), actual.getText());
        assertEquals(expected.getEncodedTextLen().intValue(), actual.getEncodedTextLen().intValue());
        assertArrayEquals(expected.getEncodedText(), actual.getEncodedText());

        Stipulations50TestData.getInstance().check(expected.getStipulations(), actual.getStipulations());

        assertEquals(expected.getNoLegs(), actual.getNoLegs());
        InstrumentLeg50SP2TestData.getInstance().check(expected.getInstrumentLegs()[0], actual.getInstrumentLegs()[0]);
        InstrumentLeg50SP2TestData.getInstance().check(expected.getInstrumentLegs()[1], actual.getInstrumentLegs()[1]);

        SpreadOrBenchmarkCurveData50TestData.getInstance().check(expected.getSpreadOrBenchmarkCurveData(), actual.getSpreadOrBenchmarkCurveData());

        YieldData50TestData.getInstance().check(expected.getYieldData(), actual.getYieldData());

        assertEquals(expected.getNoMarketSegments(), actual.getNoMarketSegments());
        MarketSegmentGroup50SP1TestData.getInstance().check(expected.getMarketSegmentGroups()[0], actual.getMarketSegmentGroups()[0]);
        MarketSegmentGroup50SP1TestData.getInstance().check(expected.getMarketSegmentGroups()[1], actual.getMarketSegmentGroups()[1]);

        assertUTCTimestampEquals(expected.getTransactTime(), actual.getTransactTime(), false);
    }
}
