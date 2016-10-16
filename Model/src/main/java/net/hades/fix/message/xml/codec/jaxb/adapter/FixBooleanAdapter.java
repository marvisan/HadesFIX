/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * FixBooleanAdapter.java
 *
 * $Id: FixBooleanAdapter.java,v 1.1 2009-07-06 03:18:44 vrotaru Exp $
 */
package net.hades.fix.message.xml.codec.jaxb.adapter;

import net.hades.fix.message.util.BooleanConverter;

import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * Adapts a boolean value to a FIX string send over wire.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 */
public class FixBooleanAdapter extends XmlAdapter<String, Boolean> {

    @Override
    public Boolean unmarshal(String value) throws Exception {
        return value == null ? null : BooleanConverter.parse(value);
    }

    @Override
    public String marshal(Boolean value) throws Exception {
        return value == null ? null : BooleanConverter.formatYN(value.booleanValue());
    }
}
