/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * TestAll.java
 *
 * $Id: TestAll.java,v 1.2 2009-08-03 09:41:42 vrotaru Exp $
 */
package net.hades.fix.message.util;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Runs al the util Test Cases.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 * @created 01/11/2008, 6:41:12 PM
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
    CryptUtilTest.class,
    DateConverterTest.class,
    MsgUtilTest.class,
    NumberConverterTest.class,
    net.hades.fix.message.util.codec.TestAll.class
})
public class TestAll {
}
