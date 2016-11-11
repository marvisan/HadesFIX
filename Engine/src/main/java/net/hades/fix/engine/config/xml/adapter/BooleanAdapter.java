/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */
package net.hades.fix.engine.config.xml.adapter;

import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * Adapts a boolean value to a string string.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 */
public class BooleanAdapter extends XmlAdapter<String, Boolean> {

    @Override
    public Boolean unmarshal(String value) throws Exception {
        return value == null ? null : parse(value);
    }

    @Override
    public String marshal(Boolean value) throws Exception {
        return value == null ? null : format(value);
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
