/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * IntegerToString.java
 *
 * $Id$
 */
package net.hades.fix.admin.gui.config.converter;

import org.jdesktop.beansbinding.Converter;

/**
 * Safe converter of a String to Integer.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision$
 */
public class IntegerToString extends Converter<Integer, String> {

    @Override
    public String convertForward(Integer arg) {
        return String.valueOf(arg);
    }

    @Override
    public Integer convertReverse(String arg) {
        int value;
        try {
            value = (arg == null) ? 0 : Integer.parseInt(arg);
        } catch (NumberFormatException ex) {
            value = 0;
        }
        
        return value;
    }
}
