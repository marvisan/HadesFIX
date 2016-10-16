/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * AdvTransTypeType.java
 *
 * $Id: AdvTransTypeType.java,v 1.1 2009-07-06 03:18:50 vrotaru Exp $
 */
package net.hades.fix.message.xml.codec.jaxb.type;

import net.hades.fix.message.type.AdvTransType;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementRefs;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * FIXML JAXB type for AdvTransType tag.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 22/05/2009, 8:53:16 AM
 */
@XmlRootElement(name = "AdvTransType")
@XmlType(propOrder = {"element"})
@XmlAccessorType(XmlAccessType.NONE)
public class AdvTransTypeType {

    @XmlElementRefs({
        @XmlElementRef(type = AdvNewType.class),
        @XmlElementRef(type = AdvReplaceType.class),
        @XmlElementRef(type = AdvCancelType.class)
    })
    private AdvCancelReplaceType element;

    public AdvCancelReplaceType getElement() {
        return element;
    }

    public void setElement(AdvCancelReplaceType element) {
        this.element = element;
    }

    public AdvTransType getValue() {
        return element == null ? null : AdvTransType.valueFor(element.getValue());
    }

    public void setValue(AdvTransType value, String advRefID) {
        if (value.equals(AdvTransType.New)) {
            element = new AdvNewType(value.getValue(), null);
        } else if (value.equals(AdvTransType.Replace)) {
            element = new AdvReplaceType(value.getValue(), advRefID);
        } else if (value.equals(AdvTransType.Cancel)) {
            element = new AdvCancelType(value.getValue(), advRefID);
        }
    }

    @XmlRootElement(name = "AdvNew")
    @XmlType
    @XmlAccessorType(XmlAccessType.NONE)
    public static class AdvNewType extends AdvCancelReplaceType {

        public AdvNewType() {
        }

        public AdvNewType(String value, String advRefID) {
            super(value, advRefID);
        }
    }

    @XmlRootElement(name = "AdvCancel")
    @XmlType
    @XmlAccessorType(XmlAccessType.NONE)
    public static class AdvCancelType extends AdvCancelReplaceType {

        public AdvCancelType() {
        }

        public AdvCancelType(String value, String advRefID) {
            super(value, advRefID);
        }
    }

    @XmlRootElement(name = "AdvReplace")
    @XmlType
    @XmlAccessorType(XmlAccessType.NONE)
    public static class AdvReplaceType extends AdvCancelReplaceType {

        public AdvReplaceType() {
        }

        public AdvReplaceType(String value, String advRefID) {
            super(value, advRefID);
        }
    }
}
