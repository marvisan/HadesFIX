/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * SingleIntegerAttrSimpleType.java
 *
 * $Id: SingleIntegerAttrSimpleType.java,v 1.1 2009-07-06 03:18:50 vrotaru Exp $
 */
package net.hades.fix.message.xml.codec.jaxb.type;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;


/**
 * Super class for all the single integer attribute entities.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 26/05/2009, 12:50:43 PM
 */
@XmlAccessorType(XmlAccessType.NONE)
public class SingleIntegerAttrSimpleType {

    @XmlAttribute(name = "Value")
    protected Integer value;

    public SingleIntegerAttrSimpleType() {
    }

    public SingleIntegerAttrSimpleType(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

}
