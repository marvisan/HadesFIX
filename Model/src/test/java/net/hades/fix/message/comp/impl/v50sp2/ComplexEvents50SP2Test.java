/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * ComplexEvents50SP2Test.java
 *
 * $Id: ComplexEvents50SP2Test.java,v 1.2 2009-11-21 09:57:20 vrotaru Exp $
 */
package net.hades.fix.message.comp.impl.v50sp2;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import net.hades.fix.message.comp.ComplexEvents;
import net.hades.fix.message.comp.impl.v50sp2.ComplexEvents50SP2;

/**
 * Test suite for ComplexEvents50SP2 class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 * @created 11/01/2009, 11:32:14 AM
 */
public class ComplexEvents50SP2Test {

    public ComplexEvents50SP2Test() {
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
     * Test of setSecurityXML method, of class Instrument50SP2.
     */
    @Test
    public void testSetComplexEventDates() {
        ComplexEvents comp = new ComplexEvents50SP2();
        assertNull(comp.getComplexEventDates());
        comp.setComplexEventDates();
        assertNotNull(comp.getComplexEventDates());
    }

    /**
     * Test of clearSecurityXML method, of class Instrument50SP2.
     */
    @Test
    public void testClearComplexEventDates() {
        ComplexEvents comp = new ComplexEvents50SP2();
        assertNull(comp.getComplexEventDates());
        comp.setComplexEventDates();
        assertNotNull(comp.getComplexEventDates());
        comp.clearComplexEventDates();
        assertNull(comp.getComplexEventDates());
    }
}