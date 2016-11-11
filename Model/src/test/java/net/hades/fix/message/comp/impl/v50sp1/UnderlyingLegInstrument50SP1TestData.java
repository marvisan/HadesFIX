/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * UnderlyingLegInstrument50TestData.java
 *
 * $Id: UnderlyingLegInstrument50SP1TestData.java,v 1.2 2011-10-29 09:42:12 vrotaru Exp $
 */
package net.hades.fix.message.comp.impl.v50sp1;

import java.io.UnsupportedEncodingException;
import java.util.Calendar;

import static org.junit.Assert.*;

import net.hades.fix.message.MsgTest;
import net.hades.fix.message.comp.UnderlyingLegInstrument;
import net.hades.fix.message.type.Exchange;
import net.hades.fix.message.type.PutOrCall;
import net.hades.fix.message.type.SecurityIDSource;
import net.hades.fix.message.type.SecurityType;

/**
 * Test utility for UnderlyingLegInstrument50SP1 group class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 * @created 11/04/2009, 10:08:38 AM
 */
public class UnderlyingLegInstrument50SP1TestData extends MsgTest {

    private static final UnderlyingLegInstrument50SP1TestData INSTANCE;

    static {
        INSTANCE = new UnderlyingLegInstrument50SP1TestData();
    }

    public static UnderlyingLegInstrument50SP1TestData getInstance() {
        return INSTANCE;
    }

    public void populate1(UnderlyingLegInstrument comp) throws UnsupportedEncodingException {
        Calendar cal = Calendar.getInstance();
        
        comp.setUnderlyingLegSymbol("MOT");
        comp.setUnderlyingLegSymbolSfx("MOT_QS");
        comp.setUnderlyingLegSecurityID("564335");
        comp.setUnderlyingLegSecurityIDSource(SecurityIDSource.CUSIP.getValue());
        
        comp.setNoUnderlyingLegSecurityAltID(2);
        comp.getUnderlyingLegSecurityAltIDGroups()[0].setUnderlyingLegSecurityAltID("MOTOROLA");
        comp.getUnderlyingLegSecurityAltIDGroups()[0].setUnderlyingLegSecurityAltIDSource(SecurityIDSource.ClearingHouse.getValue());
        comp.getUnderlyingLegSecurityAltIDGroups()[1].setUnderlyingLegSecurityAltID("MOTOR");
        comp.getUnderlyingLegSecurityAltIDGroups()[1].setUnderlyingLegSecurityAltIDSource(SecurityIDSource.Belgian.getValue());
        
        comp.setUnderlyingLegCFICode("CFI_MOT");
        comp.setUnderlyingLegSecurityType(SecurityType.BankNotes.getValue());
        comp.setUnderlyingLegSecuritySubType("cash");
        comp.setUnderlyingLegMaturityMonthYear("052011");
        cal.set(2011, 8, 12, 9, 17, 50);
        comp.setUnderlyingLegMaturityDate(cal.getTime());
        cal.set(2011, 8, 12, 8, 12, 50);
        comp.setUnderlyingLegMaturityTime(cal.getTime());
        comp.setUnderlyingLegStrikePrice(24.35d);
        comp.setUnderlyingLegOptAttribute('1');
        comp.setUnderlyingLegPutOrCall(PutOrCall.Call);
        comp.setUnderlyingLegSecurityExchange(Exchange.ACE_MARKET.getValue());
        comp.setUnderlyingLegSecurityDesc("some text 1");
    }

    public void populate2(UnderlyingLegInstrument comp) throws UnsupportedEncodingException {
        Calendar cal = Calendar.getInstance();
        
        comp.setUnderlyingLegSymbol("MSFT");
        comp.setUnderlyingLegSymbolSfx("MSFT_QS");
        comp.setUnderlyingLegSecurityID("533111");
        comp.setUnderlyingLegSecurityIDSource(SecurityIDSource.Belgian.getValue());
        
        comp.setNoUnderlyingLegSecurityAltID(2);
        comp.getUnderlyingLegSecurityAltIDGroups()[0].setUnderlyingLegSecurityAltID("MICROSOFT");
        comp.getUnderlyingLegSecurityAltIDGroups()[0].setUnderlyingLegSecurityAltIDSource(SecurityIDSource.CTA.getValue());
        comp.getUnderlyingLegSecurityAltIDGroups()[1].setUnderlyingLegSecurityAltID("MSFT_QS");
        comp.getUnderlyingLegSecurityAltIDGroups()[1].setUnderlyingLegSecurityAltIDSource(SecurityIDSource.ISDA_URL.getValue());
        
        comp.setUnderlyingLegCFICode("CFI_MSFT");
        comp.setUnderlyingLegSecurityType(SecurityType.Cash.getValue());
        comp.setUnderlyingLegSecuritySubType("more cash");
        comp.setUnderlyingLegMaturityMonthYear("062011");
        cal.set(2011, 8, 15, 9, 17, 50);
        comp.setUnderlyingLegMaturityDate(cal.getTime());
        cal.set(2011, 8, 17, 8, 12, 50);
        comp.setUnderlyingLegMaturityTime(cal.getTime());
        comp.setUnderlyingLegStrikePrice(24.75d);
        comp.setUnderlyingLegOptAttribute('2');
        comp.setUnderlyingLegPutOrCall(PutOrCall.Put);
        comp.setUnderlyingLegSecurityExchange(Exchange.ZIMBABWE_STOCK_EXCHANGE.getValue());
        comp.setUnderlyingLegSecurityDesc("some text 2");
    }

    public void check(UnderlyingLegInstrument expected, UnderlyingLegInstrument actual) throws Exception {
        assertEquals(expected.getUnderlyingLegSymbol(), actual.getUnderlyingLegSymbol());
        assertEquals(expected.getUnderlyingLegSymbolSfx(), actual.getUnderlyingLegSymbolSfx());
        assertEquals(expected.getUnderlyingLegSecurityID(), actual.getUnderlyingLegSecurityID());
        assertEquals(expected.getUnderlyingLegSecurityIDSource(), actual.getUnderlyingLegSecurityIDSource());

        assertEquals(expected.getUnderlyingLegSecurityAltIDGroups()[0].getUnderlyingLegSecurityAltID(), 
                actual.getUnderlyingLegSecurityAltIDGroups()[0].getUnderlyingLegSecurityAltID());
        assertEquals(expected.getUnderlyingLegSecurityAltIDGroups()[0].getUnderlyingLegSecurityAltIDSource(), 
                actual.getUnderlyingLegSecurityAltIDGroups()[0].getUnderlyingLegSecurityAltIDSource());
        assertEquals(expected.getUnderlyingLegSecurityAltIDGroups()[1].getUnderlyingLegSecurityAltID(), 
                actual.getUnderlyingLegSecurityAltIDGroups()[1].getUnderlyingLegSecurityAltID());
        assertEquals(expected.getUnderlyingLegSecurityAltIDGroups()[1].getUnderlyingLegSecurityAltIDSource(), 
                actual.getUnderlyingLegSecurityAltIDGroups()[1].getUnderlyingLegSecurityAltIDSource());
        
        assertEquals(expected.getUnderlyingLegCFICode(), actual.getUnderlyingLegCFICode());
        assertEquals(expected.getUnderlyingLegSecurityType(), actual.getUnderlyingLegSecurityType());
        assertEquals(expected.getUnderlyingLegSecuritySubType(), actual.getUnderlyingLegSecuritySubType());
        assertEquals(expected.getUnderlyingLegMaturityMonthYear(), actual.getUnderlyingLegMaturityMonthYear());
        assertDateEquals(expected.getUnderlyingLegMaturityDate(), actual.getUnderlyingLegMaturityDate());
//        assertUTCTimeEquals(expected.getUnderlyingLegMaturityTime(), actual.getUnderlyingLegMaturityTime(), false);
        assertEquals(expected.getUnderlyingLegStrikePrice(), actual.getUnderlyingLegStrikePrice());
        assertEquals(expected.getUnderlyingLegOptAttribute(), actual.getUnderlyingLegOptAttribute());
        assertEquals(expected.getUnderlyingLegPutOrCall(), actual.getUnderlyingLegPutOrCall());
        assertEquals(expected.getUnderlyingLegSecurityExchange(), actual.getUnderlyingLegSecurityExchange());
        assertEquals(expected.getUnderlyingLegSecurityDesc(), actual.getUnderlyingLegSecurityDesc());
    }
}
