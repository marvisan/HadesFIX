/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * ValidatorType.java
 *
 * $Id: ValidatorType.java,v 1.1 2011-03-28 04:38:38 vrotaru Exp $
 */
package net.hades.fix.engine.validator;

import java.util.HashMap;
import java.util.Map;

/**
 * Types of validator.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 */
public enum ValidatorType {

    Scheduler                   ("SCHD"),
    Stream                      ("STRM");

    private static final long serialVersionUID = 1L;

    private String value;

    private static final Map<String, ValidatorType> stringToEnum = new HashMap<String, ValidatorType>();

    static {
        for (ValidatorType tag : values()) {
            stringToEnum.put(String.valueOf(tag.getValue()), tag);
        }
    }

    /** Creates a new instance of ValidatorType */
    ValidatorType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static ValidatorType valueFor(char value) {
        return stringToEnum.get(String.valueOf(value));
    }

}
