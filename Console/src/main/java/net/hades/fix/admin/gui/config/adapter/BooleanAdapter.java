/*
 *   Copyright (c) 2006-2012 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * FixBooleanAdapter.java
 *
 * $Id: BooleanAdapter.java,v 1.1 2010-01-22 09:04:42 vrotaru Exp $
 */
package net.hades.fix.admin.gui.config.adapter;

import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * Adapts a boolean value to a string string.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 20/05/2009, 3:48:07 PM
 */
public class BooleanAdapter extends XmlAdapter<String, Boolean> {

    @Override
    public Boolean unmarshal(String value) throws Exception {
        return value == null ? null : parse(value);
    }

    @Override
    public String marshal(Boolean value) throws Exception {
        return value == null ? null : format(value.booleanValue());
    }

    public static boolean parse(String value) {

        boolean result = false;

        if (value != null && value.trim().length() > 0) {
            result = value.equalsIgnoreCase("y") || value.equalsIgnoreCase("yes") || value.equalsIgnoreCase("true");
        }

        return result;
    }

    public static String format(boolean value) {
        if (value) {
            return "true";
        } else {
            return "false";
        }
    }
}
