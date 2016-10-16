/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * CollInqQualGroup44TestData.java
 *
 * $Id: CollInqQualGroup44TestData.java,v 1.1 2011-02-16 11:24:33 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v44;

import java.io.UnsupportedEncodingException;

import static org.junit.Assert.*;

import net.hades.fix.message.MsgTest;
import net.hades.fix.message.group.CollInqQualGroup;
import net.hades.fix.message.type.CollInquiryQualifier;

/**
 * Test utility for CollInqQualGroup group class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 17/012/2011, 9:12:46 AM
 */
public class CollInqQualGroup44TestData extends MsgTest {

    private static final CollInqQualGroup44TestData INSTANCE;

    static {
        INSTANCE = new CollInqQualGroup44TestData();
    }

    public static CollInqQualGroup44TestData getInstance() {
        return INSTANCE;
    }

    public void populate1(CollInqQualGroup grp) throws UnsupportedEncodingException {
        grp.setCollInquiryQualifier(CollInquiryQualifier.FullyAssigned);
    }

    public void populate2(CollInqQualGroup grp) throws UnsupportedEncodingException {
        grp.setCollInquiryQualifier(CollInquiryQualifier.CollateralInstrument);
    }

    public void check(CollInqQualGroup expected, CollInqQualGroup actual) throws Exception {
        assertEquals(expected.getCollInquiryQualifier(), actual.getCollInquiryQualifier());
    }
}
