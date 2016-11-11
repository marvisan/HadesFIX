/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * TrdRegTimestamps50TestData.java
 *
 * $Id: StrategyParametersGroup50TestData.java,v 1.1 2010-12-12 09:13:08 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v50;

import java.io.UnsupportedEncodingException;

import static org.junit.Assert.*;

import net.hades.fix.message.MsgTest;
import net.hades.fix.message.group.StrategyParametersGroup;
import net.hades.fix.message.type.StrategyParameterType;

/**
 * Test utility for TrdRegTimestamps50 group class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 11/04/2009, 10:08:38 AM
 */
public class StrategyParametersGroup50TestData extends MsgTest {

    private static final StrategyParametersGroup50TestData INSTANCE;

    static {
        INSTANCE = new StrategyParametersGroup50TestData();
    }

    public static StrategyParametersGroup50TestData getInstance() {
        return INSTANCE;
    }

    public void populate1(StrategyParametersGroup group) throws UnsupportedEncodingException {
        group.setStrategyParameterName("param 1");
        group.setStrategyParameterType(StrategyParameterType.StringType);
        group.setStrategyParameterValue("test 1");
    }

    public void populate2(StrategyParametersGroup group) throws UnsupportedEncodingException {
        group.setStrategyParameterName("param 2");
        group.setStrategyParameterType(StrategyParameterType.Currency);
        group.setStrategyParameterValue("AUD");
    }

    public void check(StrategyParametersGroup expected, StrategyParametersGroup actual) throws Exception {
        assertEquals(expected.getStrategyParameterName(), actual.getStrategyParameterName());
        assertEquals(expected.getStrategyParameterType(), actual.getStrategyParameterType());
        assertEquals(expected.getStrategyParameterValue(), actual.getStrategyParameterValue());
    }
}
