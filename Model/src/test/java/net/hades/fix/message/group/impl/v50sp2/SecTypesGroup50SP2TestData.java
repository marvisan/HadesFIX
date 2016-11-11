/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * SecTypesGroup50SP2TestData.java
 *
 * $Id: SecTypesGroup50SP2TestData.java,v 1.1 2011-04-27 01:09:59 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v50sp2;

import java.io.UnsupportedEncodingException;
import java.util.Calendar;

import static org.junit.Assert.*;

import net.hades.fix.message.MsgTest;
import net.hades.fix.message.group.SecTypesGroup;
import net.hades.fix.message.type.Product;
import net.hades.fix.message.type.SecurityType;

/**
 * Test utility for SecTypesGroup50SP2 group class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 31/03/2009, 9:12:46 AM
 */
public class SecTypesGroup50SP2TestData extends MsgTest {

    private static final SecTypesGroup50SP2TestData INSTANCE;

    static {
        INSTANCE = new SecTypesGroup50SP2TestData();
    }

    public static SecTypesGroup50SP2TestData getInstance() {
        return INSTANCE;
    }

    public void populate1(SecTypesGroup grp) throws UnsupportedEncodingException {
        Calendar cal = Calendar.getInstance();
        grp.setSecurityType(SecurityType.AmendedRestated.getValue());
        grp.setSecuritySubType("FX");
        grp.setProduct(Product.CURRENCY);
        grp.setCfiCode("CODE_001122");
        cal.set(2010, 3, 14, 12, 13, 13);
        grp.setTransactTime(cal.getTime());
    }

    public void populate2(SecTypesGroup grp) throws UnsupportedEncodingException {
        Calendar cal = Calendar.getInstance();
        grp.setSecurityType(SecurityType.BankNotes.getValue());
        grp.setSecuritySubType("SHARES");
        grp.setProduct(Product.EQUITY);
        grp.setCfiCode("CODE_334455");
        cal.set(2010, 3, 15, 14, 44, 27);
        grp.setTransactTime(cal.getTime());
    }

    public void check(SecTypesGroup expected, SecTypesGroup actual) throws Exception {
        assertEquals(expected.getSecurityType(), actual.getSecurityType());
        assertEquals(expected.getSecuritySubType(), actual.getSecuritySubType());
        assertEquals(expected.getProduct(), actual.getProduct());
        assertEquals(expected.getCfiCode(), actual.getCfiCode());
        assertUTCTimestampEquals(expected.getTransactTime(), actual.getTransactTime(), false);
    }
}
