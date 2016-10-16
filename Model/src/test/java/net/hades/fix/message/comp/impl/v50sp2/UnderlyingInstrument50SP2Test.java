/*
 *   Copyright (c) 2006-2008 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * UnderlyingInstrument50SP2Test.java
 *
 * $Id: UnderlyingInstrument50SP2Test.java,v 1.2 2009-11-21 09:57:21 vrotaru Exp $
 */
package net.hades.fix.message.comp.impl.v50sp2;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import net.hades.fix.message.comp.UnderlyingInstrument;
import net.hades.fix.message.comp.impl.v50sp2.UnderlyingInstrument50SP2;

/**
 * Test suite for UnderlyingInstrument50SP2 class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 * @created 11/01/2009, 10:41:25 AM
 */
public class UnderlyingInstrument50SP2Test {

    public UnderlyingInstrument50SP2Test() {
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
     * Test of setUnderlyingStipulations method, of class UnderlyingInstrument50SP2.
     */
    @Test
    public void testSetUnderlyingStipulations() {
        UnderlyingInstrument comp = new UnderlyingInstrument50SP2();
        assertNull(comp.getUnderlyingStipulations());
        comp.setUnderlyingStipulations();
        assertNotNull(comp.getUnderlyingStipulations());
    }

    /**
     * Test of clearUnderlyingStipulations method, of class UnderlyingInstrument50SP2.
     */
    @Test
    public void testClearUnderlyingStipulations() {
        UnderlyingInstrument comp = new UnderlyingInstrument50SP2();
        assertNull(comp.getUnderlyingStipulations());
        comp.setUnderlyingStipulations();
        assertNotNull(comp.getUnderlyingStipulations());
        comp.clearUnderlyingStipulations();
        assertNull(comp.getUnderlyingStipulations());
    }

    /**
     * Test of setUndlyInstrumentParties method, of class UnderlyingInstrument50.
     */
    @Test
    public void testSetUndlyInstrumentParties() {
        UnderlyingInstrument comp = new UnderlyingInstrument50SP2();
        assertNull(comp.getUndlyInstrumentParties());
        comp.setUndlyInstrumentParties();
        assertNotNull(comp.getUndlyInstrumentParties());
    }

    /**
     * Test of clearUndlyInstrumentParties method, of class UnderlyingInstrument50.
     */
    @Test
    public void testClearUndlyInstrumentParties() {
        UnderlyingInstrument comp = new UnderlyingInstrument50SP2();
        assertNull(comp.getUndlyInstrumentParties());
        comp.setUndlyInstrumentParties();
        assertNotNull(comp.getUndlyInstrumentParties());
        comp.clearUndlyInstrumentParties();
        assertNull(comp.getUndlyInstrumentParties());
    }
}