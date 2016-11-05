/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

package net.hades.fix.engine.validator;

import java.util.Map;

import net.hades.fix.engine.config.model.HadesInstanceInfo;

/**
 * Stream configuration validator.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 */
public class StreamValidator extends Validator {

    public StreamValidator(Map<String, String> errors) {
        super(errors);
    }

    public StreamValidator() {
        super();
    }

    @Override
    public Map<String, String> validate(Object data) {
        HadesInstanceInfo configuration = (HadesInstanceInfo) data;
        return errors;
    }

}
