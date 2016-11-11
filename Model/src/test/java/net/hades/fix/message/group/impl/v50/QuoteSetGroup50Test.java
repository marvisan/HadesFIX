/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * QuoteSetGroup50Test.java
 *
 * $Id: QuoteSetGroup50Test.java,v 1.2 2009-11-21 09:57:18 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v50;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import net.hades.fix.message.group.QuoteSetGroup;
import net.hades.fix.message.group.impl.v50.QuoteSetGroup50;

/**
 * Test suite for QuoteSetGroup50 class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 * @created 11/04/2009, 11:30:15 AM
 */
public class QuoteSetGroup50Test {


    public QuoteSetGroup50Test() {
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
     * Test of methods operating on QuoteEntry groups.
     */
    @Test
    public void testQuoteEntries() {
        QuoteSetGroup group = new QuoteSetGroup50();
        group.setNoQuoteEntries(new Integer(2));
        assertEquals(group.getQuoteEntryGroups().length, 2);
        group.addQuoteEntryGroup();
        assertEquals(group.getQuoteEntryGroups().length, 3);
        assertEquals(group.getNoQuoteEntries().intValue(), 3);
        group.deleteQuoteEntryGroup(1);
        assertEquals(group.getQuoteEntryGroups().length, 2);
        assertEquals(group.getNoQuoteEntries().intValue(), 2);
        group.clearQuoteEntryGroups();
        assertNull(group.getNoQuoteEntries());
        assertNull(group.getQuoteEntryGroups());
    }

    private void checkUnsupportedException(Exception ex) {
        assertEquals("This tag is not supported in [QuoteSetGroup] group version [5.0].", ex.getMessage());
    }
}
