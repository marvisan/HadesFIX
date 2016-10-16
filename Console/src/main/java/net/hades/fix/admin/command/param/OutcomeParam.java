/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * OutcomeParam.java
 *
 * $Id: OutcomeParam.java,v 1.1 2011-03-17 07:36:17 vrotaru Exp $
 */
package net.hades.fix.admin.command.param;

import java.util.HashMap;
import java.util.Map;

/**
 * Parameters for the outcome result.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 17/03/2011
 */
public enum OutcomeParam {

    Outcome                 ("outcome"),
    ErrMsg                  ("errMsg");

    private static final long serialVersionUID = 1L;

    private String value;

    private static final Map<String, OutcomeParam> stringToEnum = new HashMap<String, OutcomeParam>();

    static {
        for (OutcomeParam tag : values()) {
            stringToEnum.put(tag.getValue(), tag);
        }
    }

    /** Creates a new instance of OutcomeParam */
    OutcomeParam(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static OutcomeParam valueFor(String value) {
        return stringToEnum.get(value);
    }
}
