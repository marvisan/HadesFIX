/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */
package net.hades.fix.engine.config;

import java.util.Map;

/**
 * Process configuration error.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 */
public class ConfigurationException extends Exception {

    private static final long serialVersionUID = 1L;

    private Map<String, String> errors;

    public ConfigurationException(String message) {
        super(message);
    }

    public ConfigurationException(Map<String, String> errors) {
        super();
        this.errors = errors;
    }

    public ConfigurationException(String message, Throwable cause) {
        super(message, cause);
    }

    public Map<String, String> getErrors() {
        return errors;
    }
}
