/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * PutOrCallType.java
 *
 * $Id: PutOrCallType.java,v 1.1 2009-07-06 03:18:50 vrotaru Exp $
 */
package net.hades.fix.message.xml.codec.jaxb.type;

import net.hades.fix.message.type.PutOrCall;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * FIXML JAXB type for PutOrCall tag.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 22/05/2009, 6:22:53 PM
 */
@XmlRootElement(name = "AdvSide")
@XmlType
@XmlAccessorType(XmlAccessType.NONE)
public class PutOrCallType {

    @XmlAttribute(name = "Value")
    private PutOrCall value;

    public PutOrCall getValue() {
        return value;
    }

    public void setValue(PutOrCall value) {
        this.value = value;
    }
}