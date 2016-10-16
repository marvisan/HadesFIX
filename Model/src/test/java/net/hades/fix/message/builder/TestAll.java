/*
 *   Copyright (c) 2006-2008 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * TestAll.java
 *
 * $Id: TestAll.java,v 1.1 2010-01-27 07:54:03 vrotaru Exp $
 */
package net.hades.fix.message.builder;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Runs all the message Test Cases.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 01/11/2008, 6:28:48 PM
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
    FIXMsgBuilderTest.class,
    FIXMLMsgCodecTest.class
})
public class TestAll {
}
