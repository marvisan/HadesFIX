/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * RgstDtlsGroup50TestData.java
 *
 * $Id: RgstDtlsGroup50TestData.java,v 1.1 2011-10-29 01:31:24 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v50;

import java.io.UnsupportedEncodingException;
import java.util.Calendar;

import static org.junit.Assert.*;

import net.hades.fix.message.MsgTest;
import net.hades.fix.message.comp.impl.v50.NestedParties50TestData;
import net.hades.fix.message.group.RgstDtlsGroup;
import net.hades.fix.message.type.Country;
import net.hades.fix.message.type.OwnerType;

/**
 * Test utility for RgstDtlsGroup group class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 31/03/2009, 9:12:46 AM
 */
public class RgstDtlsGroup50TestData extends MsgTest {

    private static final RgstDtlsGroup50TestData INSTANCE;

    static {
        INSTANCE = new RgstDtlsGroup50TestData();
    }

    public static RgstDtlsGroup50TestData getInstance() {
        return INSTANCE;
    }

    public void populate1(RgstDtlsGroup grp) throws UnsupportedEncodingException {
        Calendar cal = Calendar.getInstance();
        
        grp.setRegistDtls("Detail1");
        grp.setRegistEmail("someone@mycompany.com");
        grp.setMailingDtls("mailing details here");
        grp.setMailingInst("send it fast");
        
        grp.setNestedParties();
        NestedParties50TestData.getInstance().populate(grp.getNestedParties());
        
        grp.setOwnerType(OwnerType.Nominee);
        cal.set(1998, 10, 11, 10, 23, 34);
        grp.setDateOfBirth(cal.getTime());
        grp.setInvestorCountryOfResidence(Country.Australia);
    }

    public void populate2(RgstDtlsGroup grp) throws UnsupportedEncodingException {
        Calendar cal = Calendar.getInstance();
        
        grp.setRegistDtls("Detail2");
        grp.setRegistEmail("someone1@mycompany.com");
        grp.setMailingDtls("mailing details 2 here");
        grp.setMailingInst("send it fast please");
        
        grp.setNestedParties();
        NestedParties50TestData.getInstance().populate(grp.getNestedParties());
        
        grp.setOwnerType(OwnerType.CorporateBody);
        cal.set(2000, 9, 2, 10, 23, 34);
        grp.setDateOfBirth(cal.getTime());
        grp.setInvestorCountryOfResidence(Country.Australia);
    }

    public void check(RgstDtlsGroup expected, RgstDtlsGroup actual) throws Exception {
        assertEquals(expected.getRegistDtls(), actual.getRegistDtls());
        assertEquals(expected.getRegistEmail(), actual.getRegistEmail());
        assertEquals(expected.getMailingDtls(), actual.getMailingDtls());
        assertEquals(expected.getMailingInst(), actual.getMailingInst());

        NestedParties50TestData.getInstance().check(expected.getNestedParties(), actual.getNestedParties());

        assertEquals(expected.getOwnerType(), actual.getOwnerType());
        assertDateEquals(expected.getDateOfBirth(), actual.getDateOfBirth());
        assertEquals(expected.getInvestorCountryOfResidence(), actual.getInvestorCountryOfResidence());
    }
}
