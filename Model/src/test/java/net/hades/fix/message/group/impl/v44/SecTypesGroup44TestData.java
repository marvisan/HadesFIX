/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * SecTypesGroup44TestData.java
 *
 * $Id: SecTypesGroup44TestData.java,v 1.1 2011-04-27 01:09:59 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v44;

import java.io.UnsupportedEncodingException;

import static org.junit.Assert.*;

import net.hades.fix.message.MsgTest;
import net.hades.fix.message.group.SecTypesGroup;
import net.hades.fix.message.type.Product;
import net.hades.fix.message.type.SecurityType;

/**
 * Test utility for SecTypesGroup44 group class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 31/03/2009, 9:12:46 AM
 */
public class SecTypesGroup44TestData extends MsgTest {

    private static final SecTypesGroup44TestData INSTANCE;

    static {
        INSTANCE = new SecTypesGroup44TestData();
    }

    public static SecTypesGroup44TestData getInstance() {
        return INSTANCE;
    }

    public void populate1(SecTypesGroup grp) throws UnsupportedEncodingException {
        grp.setSecurityType(SecurityType.AmendedRestated.getValue());
        grp.setSecuritySubType("FX");
        grp.setProduct(Product.CURRENCY);
        grp.setCfiCode("CODE_001122");
    }

    public void populate2(SecTypesGroup grp) throws UnsupportedEncodingException {
        grp.setSecurityType(SecurityType.BankNotes.getValue());
        grp.setSecuritySubType("SHARES");
        grp.setProduct(Product.EQUITY);
        grp.setCfiCode("CODE_334455");
    }

    public void check(SecTypesGroup expected, SecTypesGroup actual) throws Exception {
        assertEquals(expected.getSecurityType(), actual.getSecurityType());
        assertEquals(expected.getSecuritySubType(), actual.getSecuritySubType());
        assertEquals(expected.getProduct(), actual.getProduct());
        assertEquals(expected.getCfiCode(), actual.getCfiCode());
    }
}
