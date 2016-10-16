/*
 *   Copyright (c) 2006-2012 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * ConfigurationException.java
 *
 * $Id: ConfigurationException.java,v 1.3 2011-03-28 04:38:38 vrotaru Exp $
 */
package net.hades.fix.admin.gui.config;

import java.util.Map;

/**
 * Admin console configuration error.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.3 $
 * @created 19/02/2012, 4:07:22 PM
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
