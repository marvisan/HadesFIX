/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * CurrencyType.java
 *
 * $Id: CurrencyType.java,v 1.1 2009-07-06 03:18:50 vrotaru Exp $
 */
package net.hades.fix.message.xml.codec.jaxb.type;

import net.hades.fix.message.type.Currency;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * FIXML JAXB type for Currency tag.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 22/05/2009, 8:32:57 AM
 */
@XmlRootElement(name = "Currency")
@XmlType
@XmlAccessorType(XmlAccessType.NONE)
public class CurrencyType {

    @XmlAttribute(name = "Value")
    private Currency value;

    public Currency getValue() {
        return value;
    }

    public void setValue(Currency value) {
        this.value = value;
    }

}