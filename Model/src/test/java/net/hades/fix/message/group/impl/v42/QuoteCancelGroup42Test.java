/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * QuoteCancelGroup42Test.java
 *
 * $Id: QuoteCancelGroup42Test.java,v 1.2 2009-11-21 09:57:22 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v42;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import net.hades.fix.message.group.QuoteCancelGroup;
import net.hades.fix.message.group.impl.v42.QuoteCancelGroup42;

/**
 * Test suite for QuoteCancelGroup42 class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 * @created 11/04/2009, 11:30:15 AM
 */
public class QuoteCancelGroup42Test {


    public QuoteCancelGroup42Test() {
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
     * Test of encode getter method, of class QuoteCancelGroup with unsupported tag.
     */
    @Test
    public void testGetUnsupportedTag() {
        System.out.println("-->testGetUnsupportedTag");
        QuoteCancelGroup group = new QuoteCancelGroup42();
        try {
            group.getInstrument();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            checkUnsupportedException(ex);
        }
        try {
            group.getFinancingDetails();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            checkUnsupportedException(ex);
        }
        try {
            group.getNoUnderlyings();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            checkUnsupportedException(ex);
        }
        try {
            group.getUnderlyingInstruments();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            checkUnsupportedException(ex);
        }
        try {
            group.getNoLegs();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            checkUnsupportedException(ex);
        }
        try {
            group.getInstrumentLegs();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            checkUnsupportedException(ex);
        }
    }

    /**
     * Test of encode setter method, of class QuoteCancelGroup with unsupported tag.
     */
    @Test
    public void testSetUnsupportedTag() {
        System.out.println("-->testSetUnsupportedTag");
        QuoteCancelGroup group = new QuoteCancelGroup42();
        try {
            group.setFinancingDetails();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            checkUnsupportedException(ex);
        }
        try {
            group.setNoUnderlyings(new Integer(2));
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            checkUnsupportedException(ex);
        }
        try {
            group.addUnderlyingInstrument();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            checkUnsupportedException(ex);
        }
        try {
            group.deleteUnderlyingInstrument(1);
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            checkUnsupportedException(ex);
        }
        try {
            group.clearUnderlyingInstruments();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            checkUnsupportedException(ex);
        }
        try {
            group.setNoLegs(null);
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            checkUnsupportedException(ex);
        }
        try {
            group.addInstrumentLeg();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            checkUnsupportedException(ex);
        }
        try {
            group.deleteInstrumentLeg(1);
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            checkUnsupportedException(ex);
        }
        try {
            group.clearInstrumentLegs();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            checkUnsupportedException(ex);
        }
    }

    private void checkUnsupportedException(Exception ex) {
        assertEquals("This tag is not supported in [QuoteCancelGroup] group version [4.2].", ex.getMessage());
    }

}
