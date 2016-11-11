/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * NestedPartyGroup43Test.java
 *
 * $Id: NestedPartyGroup43Test.java,v 1.1 2009-07-06 03:19:15 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v43;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import net.hades.fix.message.group.NestedPartyGroup;
import net.hades.fix.message.group.impl.v43.NestedPartyGroup43;

/**
 * Test suite for NestedPartyGroup43 class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 13/04/2009, 3:28:17 PM
 */
public class NestedPartyGroup43Test {

    public NestedPartyGroup43Test() {
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
     * Test of encode getter method, of class NestedPartyGroup with unsupported tag.
     */
    @Test
    public void testGetUnsupportedTag() {
        System.out.println("-->testGetUnsupportedTag");
        NestedPartyGroup comp = new NestedPartyGroup43();
        try {
            comp.getNoNestedPartySubIDs();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            checkUnsupportedUnderlyingInstrumentException(ex);
        }
        try {
            comp.getNstdPtysSubGroups();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            checkUnsupportedUnderlyingInstrumentException(ex);
        }
    }

    /**
     * Test of encode setter method, of class NestedPartyGroup with unsupported tag.
     */
    @Test
    public void testSetUnsupportedTag() {
        System.out.println("-->testSetUnsupportedTag");
        NestedPartyGroup comp = new NestedPartyGroup43();
        try {
            comp.setNoNestedPartySubIDs(new Integer(2));
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            checkUnsupportedUnderlyingInstrumentException(ex);
        }
        try {
            comp.addNstdPtysSubGroup();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            checkUnsupportedUnderlyingInstrumentException(ex);
        }
        try {
            comp.deleteNstdPtysSubGroup(1);
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            checkUnsupportedUnderlyingInstrumentException(ex);
        }
        try {
            comp.clearNstdPtysSubGroups();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            checkUnsupportedUnderlyingInstrumentException(ex);
        }
    }

    private void checkUnsupportedUnderlyingInstrumentException(Exception ex) {
        assertEquals("This tag is not supported in [NestedPartyGroup] group version [4.3].", ex.getMessage());
    }

}
