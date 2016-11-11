/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * SingleBooleanAttrSimpleType.java
 *
 * $Id: SingleBooleanAttrSimpleType.java,v 1.1 2009-07-06 03:18:50 vrotaru Exp $
 */
package net.hades.fix.message.xml.codec.jaxb.type;

import net.hades.fix.message.xml.codec.jaxb.adapter.FixBooleanAdapter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 * Super class for all the single boolean attribute entities.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 22/05/2009, 9:22:39 AM
 */
@XmlAccessorType(XmlAccessType.NONE)
public abstract class SingleBooleanAttrSimpleType {

    @XmlAttribute(name = "Value")
    @XmlJavaTypeAdapter(FixBooleanAdapter.class)
    protected Boolean value;

    public SingleBooleanAttrSimpleType() {
    }

    public SingleBooleanAttrSimpleType(Boolean value) {
        this.value = value;
    }

    public Boolean getValue() {
        return value;
    }

    public void setValue(Boolean value) {
        this.value = value;
    }
}
