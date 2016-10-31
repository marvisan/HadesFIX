/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * TestAll.java
 *
 * $Id: TestAll.java,v 1.7 2010-01-14 09:07:10 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v50;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Runs all the message Test Cases.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.7 $
 * @created 01/11/2008, 6:28:48 PM
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
    MDFullGroup50Test.class,
    MDIncGroup50Test.class,
    QuoteEntryAckGroup50Test.class,
    QuoteSetAckGroup50Test.class,
    QuoteSetGroup50Test.class
})
public class TestAll {
}
