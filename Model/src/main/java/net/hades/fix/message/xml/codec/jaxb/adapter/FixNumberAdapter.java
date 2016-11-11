/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * FixNumberAdapter.java
 *
 * $Id: FixNumberAdapter.java,v 1.1 2009-07-06 03:18:44 vrotaru Exp $
 */
package net.hades.fix.message.xml.codec.jaxb.adapter;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import net.hades.fix.message.util.NumberConverter;

/**
 * Adapts a double value to a FIX string send over wire.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 20/05/2009, 7:10:46 PM
 */
public class FixNumberAdapter extends XmlAdapter<String, Double> {

    @Override
    public Double unmarshal(String value) throws Exception {
        return value == null ? null : NumberConverter.parseString(value);
    }

    @Override
    public String marshal(Double value) throws Exception {
        return value == null ? null : NumberConverter.formatNumber(value);
    }
}
