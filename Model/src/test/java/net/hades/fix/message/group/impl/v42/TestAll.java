/*
 *   Copyright (c) 2006-2008 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * TestAll.java
 *
 * $Id: TestAll.java,v 1.6 2010-01-14 09:07:10 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v42;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Runs all the message Test Cases.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.6 $
 * @created 01/11/2008, 6:28:48 PM
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
    MDFullGroup42Test.class,
    MDIncGroup42Test.class,
    MDReqGroup42Test.class,
    QuoteCancelGroup42Test.class,
    QuoteEntryAckGroup42Test.class,
    QuoteEntryGroup42Test.class,
    QuoteRelatedSymbolGroup42Test.class,
    QuoteSetAckGroup42Test.class,
    QuoteSetGroup42Test.class
})
public class TestAll {
}
