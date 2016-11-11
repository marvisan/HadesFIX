/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * ApplicationSequenceControl50SP2TestData.java
 *
 * $Id: ApplicationSequenceControl50SP2TestData.java,v 1.1 2009-07-06 03:19:10 vrotaru Exp $
 */
package net.hades.fix.message.comp.impl.v50sp2;

import static org.junit.Assert.*;

import net.hades.fix.message.MsgTest;
import net.hades.fix.message.comp.ApplicationSequenceControl;

/**
 * Test utility for ApplicationSequenceControl50SP2 component class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 26/02/2009, 7:56:44 PM
 */
public class ApplicationSequenceControl50SP2TestData extends MsgTest {

    private static final ApplicationSequenceControl50SP2TestData INSTANCE;

    static {
        INSTANCE = new ApplicationSequenceControl50SP2TestData();
    }

    public static ApplicationSequenceControl50SP2TestData getInstance() {
        return INSTANCE;
    }

    public void populate(ApplicationSequenceControl comp) {
        comp.setApplID("AppID");
        comp.setApplSeqNum(new Integer(33));
        comp.setApplLastSeqNum(new Integer(32));
        comp.setApplResendFlag(Boolean.TRUE);
    }

    public void check(ApplicationSequenceControl expected, ApplicationSequenceControl actual) throws Exception {
        assertEquals(expected.getApplID(), actual.getApplID());
        assertEquals(expected.getApplSeqNum().intValue(), actual.getApplSeqNum().intValue());
        assertEquals(expected.getApplLastSeqNum().intValue(), actual.getApplLastSeqNum().intValue());
        assertEquals(expected.getApplResendFlag().booleanValue(), actual.getApplResendFlag().booleanValue());
    }
}
