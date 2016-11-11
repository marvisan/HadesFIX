/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * FixUTCDateTimeAdapter.java
 *
 * $Id: FixUTCDateTimeAdapter.java,v 1.1 2009-07-06 03:18:44 vrotaru Exp $
 */
package net.hades.fix.message.xml.codec.jaxb.adapter;

import java.util.Date;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import net.hades.fix.message.util.DateConverter;
import net.hades.fix.message.util.format.DateFormatter;

/**
 * Adapts a Date value to a FIX UTC timestamp string send over wire.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 20/05/2009, 6:28:43 PM
 */
public class FixUTCDateTimeAdapter extends XmlAdapter<String, Date> {

    @Override
    public Date unmarshal(String value) throws Exception {
        return value == null ? null : DateConverter.parseString(value);
    }

    @Override
    public String marshal(Date value) throws Exception {
        return value == null ? null : DateConverter.formatUTCDate(DateFormatter.getISODateTimeFormat(), value);
    }
}
