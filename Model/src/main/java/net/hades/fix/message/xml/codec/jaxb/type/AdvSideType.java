/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * AdvSideType.java
 *
 * $Id: AdvSideType.java,v 1.1 2009-07-06 03:18:50 vrotaru Exp $
 */
package net.hades.fix.message.xml.codec.jaxb.type;

import net.hades.fix.message.type.AdvSide;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * FIXML JAXB type for AdvSide tag.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 22/05/2009, 8:34:30 AM
 */
@XmlRootElement(name = "AdvSide")
@XmlType
@XmlAccessorType(XmlAccessType.NONE)
public class AdvSideType {

    @XmlAttribute(name = "Value")
    private AdvSide value;

    public AdvSide getValue() {
        return value;
    }

    public void setValue(AdvSide value) {
        this.value = value;
    }
}