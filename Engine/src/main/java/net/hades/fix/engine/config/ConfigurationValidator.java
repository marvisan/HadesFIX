/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * ConfigurationValidator.java
 *
 * $Id: ConfigurationValidator.java,v 1.1 2011-03-28 04:38:37 vrotaru Exp $
 */
package net.hades.fix.engine.config;

import java.util.Map;

import net.hades.fix.engine.config.model.HadesInstanceInfo;
import net.hades.fix.engine.exception.ConfigurationException;
import net.hades.fix.engine.validator.Validator;
import net.hades.fix.engine.validator.ValidatorType;

/**
 * Validates up-front configuration data.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 */
public class ConfigurationValidator {

    private ConfigurationValidator() {
    }

    public static void validateConfiguration(HadesInstanceInfo configuration) throws ConfigurationException {
        Validator validator = Validator.getValidator(ValidatorType.Scheduler);
        Map<String, String> errors = validator.validate(configuration);
        validator = Validator.getValidator(ValidatorType.Stream, errors);
        errors = validator.validate(configuration);
        if (errors != null && errors.size() > 0) {
            throw new ConfigurationException(errors);
        }
    }
}
