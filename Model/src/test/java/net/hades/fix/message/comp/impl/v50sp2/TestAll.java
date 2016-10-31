/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * TestAll.java
 *
 * $Id: TestAll.java,v 1.2 2009-11-21 09:57:20 vrotaru Exp $
 */
package net.hades.fix.message.comp.impl.v50sp2;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Runs all the message Test Cases.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 * @created 01/11/2008, 6:28:48 PM
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
    ComplexEvents50SP2Test.class,
    ComplexEventDates50SP2Test.class,
    Instrument50SP2Test.class,
    TargetParties50SP2Test.class,
    UnderlyingInstrument50SP2Test.class
})
public class TestAll {
}
