/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.6
 *               Use is subject to license terms.
 */

/*
 * XMLValidationResult.java
 *
 * $Id: XMLValidationResult.java,v 1.1 2010-10-04 05:15:28 vrotaru Exp $
 */
package net.hades.fix.message;

/**
 * Validation result holder class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 10/08/2008, 14:19:52
 */
public class XMLValidationResult {

    private String fatals;

    private String errors;

    private String warnings;

    public XMLValidationResult(String f, String e, String w) {
        fatals = f;
        errors = e;
        warnings = w;
    }

    public String getErrors() {
        return errors;
    }

    public void setErrors(String errors) {
        this.errors = errors;
    }

    public String getFatals() {
        return fatals;
    }

    public void setFatals(String fatals) {
        this.fatals = fatals;
    }

    public String getWarnings() {
        return warnings;
    }

    public void setWarnings(String warnings) {
        this.warnings = warnings;
    }

    public boolean hasErrors() {
        boolean result = false;
        if ((fatals != null && !fatals.trim().isEmpty()) || (errors != null && !errors.trim().isEmpty())) {
            result = true;
        }
        return result;
    }

    public boolean hasWarnings() {
        boolean result = false;
        if (warnings != null && !warnings.trim().isEmpty()) {
            result = true;
        }
        return result;
    }
}
