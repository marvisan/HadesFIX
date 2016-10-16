/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * ErrorCode.java
 *
 * $Id: ErrorCode.java,v 1.1 2011-03-28 04:38:38 vrotaru Exp $
 */
package net.hades.fix.engine.validator;

import java.util.HashMap;
import java.util.Map;

/**
 * Error codes used for validation.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 */
public enum ErrorCode {

    // Scheduler
    DuplicateTask                                   ("001"),
    NonAllowedChar                                  ("002"),
    MissingHourField                                ("003"),
    MissingMinuteField                             ("004"),
    MissingDayOfMonthField                          ("005"),
    MissingMonthField                               ("006"),
    MissingYearField                                ("007"),
    BadDateFormat                                   ("008"),

    // Stream
    NonUniqueProducerFlow                           ("100");

    private static final long serialVersionUID = 1L;

    private String value;

    private static final Map<String, ErrorCode> stringToEnum = new HashMap<String, ErrorCode>();

    static {
        for (ErrorCode tag : values()) {
            stringToEnum.put(String.valueOf(tag.getValue()), tag);
        }
    }

    /** Creates a new instance of ErrorCode */
    ErrorCode(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static ErrorCode valueFor(char value) {
        return stringToEnum.get(String.valueOf(value));
    }

}
