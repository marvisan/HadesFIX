/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */
package net.hades.fix.engine.validator;

import java.text.DecimalFormat;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Abstract class and factory for a validator.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 */
public abstract class Validator {

    protected Map<String, String> errors;

    protected Validator() {
        errors = new LinkedHashMap<>();
    }

    public Validator(Map<String, String> errors) {
        this.errors = errors;
    }

    public static Validator getValidator(ValidatorType validatorType) {
        return getValidator(validatorType, null);
    }

    public static Validator getValidator(ValidatorType validatorType, Map<String, String> errors) {
        Validator result;
        switch (validatorType) {
            case Scheduler:
                result = errors != null ? new SchedulerValidator(errors) : new SchedulerValidator();
                break;

            case Stream:
                result = errors != null ? new StreamValidator(errors) : new StreamValidator();
                break;

            default:
                throw new RuntimeException("Validator [" + validatorType.toString() + "] unimplemented.");
        }

        return result;
    }

    // ABSTRACT INTERFACE
    //////////////////////////////////////

    public abstract Map<String, String> validate(Object data);

    protected String getErrorKey(ValidatorType validatorType, ErrorCode errorCode, int noOccur) {
        return validatorType.getValue() + errorCode.getValue() + new DecimalFormat("000").format(noOccur);
    }
}
