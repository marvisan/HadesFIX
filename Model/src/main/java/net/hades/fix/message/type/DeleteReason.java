/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * DeleteReason.java
 *
 * $Id: DeleteReason.java,v 1.3 2010-01-14 09:06:47 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;
import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Type of reason for deletion.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.3 $
 * @created 02/09/2009, 8:54:33 PM
 */
@XmlType
@XmlEnum(String.class)
public enum DeleteReason {

    @XmlEnumValue("0") Cancellation     ('0'),
    @XmlEnumValue("1") Error            ('1');

    private static final long serialVersionUID = -8597537429529421698L;

    private char value;

    private static final Map<String, DeleteReason> stringToEnum = new HashMap<String, DeleteReason>();

    static {
        for (DeleteReason tag : values()) {
            stringToEnum.put(String.valueOf(tag.getValue()), tag);
        }
    }

    /** Creates a new instance of DeleteReason */
    DeleteReason(char value) {
        this.value = value;
    }

    public char getValue() {
        return value;
    }

    public static DeleteReason valueFor(char value) {
        return stringToEnum.get(String.valueOf(value));
    }
}
