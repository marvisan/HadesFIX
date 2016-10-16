/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * FixCharacterAdapter.java
 *
 * $Id: FixCharacterAdapter.java,v 1.1 2009-07-06 03:18:44 vrotaru Exp $
 */
package net.hades.fix.message.xml.codec.jaxb.adapter;

import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * Adapts a character value to a FIX string send over wire.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 20/05/2009, 3:48:07 PM
 */
public class FixCharacterAdapter extends XmlAdapter<String, Character> {

    @Override
    public Character unmarshal(String value) throws Exception {
        return value == null ? null : Character.valueOf(value.charAt(0));
    }

    @Override
    public String marshal(Character value) throws Exception {
        return value == null ? null : value.toString();
    }
}
