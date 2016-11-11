/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * XMLValidationResult.java
 *
 * $Id: XMLValidationResult.java,v 1.1 2009-07-06 03:19:15 vrotaru Exp $
 */
package net.hades.fix.admin.gui.config;

/**
 * Container with validation results.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 25/05/2009, 2:14:15 PM
 */
public class XMLValidationResult {

    // <editor-fold defaultstate="collapsed" desc="Constants">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">

    private String fatals;

    private String errors;

    private String warnings;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public XMLValidationResult(String f, String e, String w) {
        fatals = f;
        errors = e;
        warnings = w;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Public Methods">

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
        if ((fatals != null && !fatals.trim().isEmpty()) ||
            (errors != null && !errors.trim().isEmpty())) {
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

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Package Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Private Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Inner Classes">
    // </editor-fold>
}
