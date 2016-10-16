/*
 *   Copyright (c) 2006-2012 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * ValidationErrorHandler.java
 *
 * $Id: ValidationErrorHandler.java,v 1.1 2009-07-06 03:19:15 vrotaru Exp $
 */
package net.hades.fix.admin.gui.config;

import javax.xml.bind.ValidationEvent;
import javax.xml.bind.util.ValidationEventCollector;

/**
 * Custom XNL validation error handler.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 19/02/2012, 1:39:59 PM
 */
public class ValidationErrorHandler extends ValidationEventCollector {

    // <editor-fold defaultstate="collapsed" desc="Constants">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">

    private boolean abortOnError;

    private StringBuilder fatals = new StringBuilder();

    private StringBuilder errors = new StringBuilder();

    private StringBuilder warnings = new StringBuilder();
    
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public ValidationErrorHandler(boolean abortOnError) {
        this.abortOnError = abortOnError;
        fatals = new StringBuilder();
        errors = new StringBuilder();
        warnings = new StringBuilder();
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Public Methods">

    @Override
    public boolean handleEvent(ValidationEvent event) {
        boolean result = true;
        if (event.getSeverity() == ValidationEvent.FATAL_ERROR) {
            String error = "FATAL - Line : " + (event.getLocator() != null ? event.getLocator().getLineNumber() : "N/A") +
                ", Message : " + event.getMessage() + "\n";
            fatals.append(error);
            if (abortOnError) {
                result = false;
            }
        } else if (event.getSeverity() == ValidationEvent.ERROR) {
            String error = "ERROR - Line : " + (event.getLocator() != null ? event.getLocator().getLineNumber() : "N/A") +
                ", Message : " + event.getMessage() + "\n";
            errors.append(error);
            if (abortOnError) {
                result = false;
            }
        } else if (event.getSeverity() == ValidationEvent.WARNING) {
            String error = "WARNING - Line : " + (event.getLocator() != null ? event.getLocator().getLineNumber() : "N/A") +
                ", Message : " + event.getMessage() + "\n";
            warnings.append(error);
        }

        return result;
    }

    public XMLValidationResult getValidationResult() {
        return new XMLValidationResult(fatals.toString(), errors.toString(), warnings.toString());
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
