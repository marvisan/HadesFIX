/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * QuoteEntryAckGroup50SP1Test.java
 *
 * $Id: QuoteEntryAckGroup50SP1Test.java,v 1.2 2009-11-21 09:57:17 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v50sp1;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import net.hades.fix.message.group.QuoteEntryAckGroup;
import net.hades.fix.message.group.impl.v50sp1.QuoteEntryAckGroup50SP1;

/**
 * Test suite for QuoteEntryAckGroup50SP1 class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 * @created 11/04/2009, 11:30:15 AM
 */
public class QuoteEntryAckGroup50SP1Test {


    public QuoteEntryAckGroup50SP1Test() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of methods operating on InstrumentLeg groups.
     */
    @Test
    public void testInstrumentLegs() {
        QuoteEntryAckGroup group = new QuoteEntryAckGroup50SP1();
        group.setNoLegs(new Integer(2));
        assertEquals(group.getInstrumentLegs().length, 2);
        group.addInstrumentLeg();
        assertEquals(group.getInstrumentLegs().length, 3);
        assertEquals(group.getNoLegs().intValue(), 3);
        group.deleteInstrumentLeg(1);
        assertEquals(group.getInstrumentLegs().length, 2);
        assertEquals(group.getNoLegs().intValue(), 2);
        group.clearInstrumentLegs();
        assertNull(group.getNoLegs());
        assertNull(group.getInstrumentLegs());
    }

    private void checkUnsupportedException(Exception ex) {
        assertEquals("This tag is not supported in [QuoteEntryAckGroup] group version [5.0SP1].", ex.getMessage());
    }

}
