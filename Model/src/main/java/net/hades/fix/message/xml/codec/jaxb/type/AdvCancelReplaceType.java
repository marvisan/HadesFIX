/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * SingleAttrSingleElementType.java
 *
 * $Id: AdvCancelReplaceType.java,v 1.1 2009-07-06 03:18:50 vrotaru Exp $
 */
package net.hades.fix.message.xml.codec.jaxb.type;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 * Super class for all the single attribute entities.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 22/05/2009, 9:22:39 AM
 */
@XmlAccessorType(XmlAccessType.NONE)
public abstract class AdvCancelReplaceType extends SingleStringAttrSimpleType {

    @XmlElement(name="AdvRefID")
    protected String advRefID;

    public AdvCancelReplaceType() {
    }

    public AdvCancelReplaceType(String value, String advRefID) {
        this.value = value;
        this.advRefID = advRefID;
    }

    public String getAdvRefID() {
        return advRefID;
    }

    public void setAdvRefID(String advRefID) {
        this.advRefID = advRefID;
    }
}
