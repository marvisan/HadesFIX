/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * FIXMLMsgCodecContext.java
 *
 * $Id: FIXMLMsgCodecContext.java,v 1.2 2011-05-21 23:53:24 vrotaru Exp $
 */
package net.hades.fix.message.builder;

import java.io.Serializable;

/**
 * Holder for optional context configuration data for a FIXML codec instance.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 * @created 10/06/2009, 9:09:18 AM
 */
public class FIXMLMsgCodecContext implements Serializable {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">

    private boolean printableXML;

    private boolean validateEncodedXML;

    private boolean validateDecodedXML;

    private boolean abortOnFailedValidation;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public FIXMLMsgCodecContext() {
    }

    public FIXMLMsgCodecContext(boolean printableXML, boolean validateEncodedXML,
        boolean validateDecodedXML, boolean abortOnFailedValidation) {
        this.printableXML = printableXML;
        this.validateEncodedXML = validateEncodedXML;
        this.validateDecodedXML = validateDecodedXML;
        this.abortOnFailedValidation = abortOnFailedValidation;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Public Methods">

    public boolean isAbortOnFailedValidation() {
        return abortOnFailedValidation;
    }

    public void setAbortOnFailedValidation(boolean abortOnFailedValidation) {
        this.abortOnFailedValidation = abortOnFailedValidation;
    }

    public boolean isPrintableXML() {
        return printableXML;
    }

    public void setPrintableXML(boolean printableXML) {
        this.printableXML = printableXML;
    }

    public boolean isValidateDecodedXML() {
        return validateDecodedXML;
    }

    public void setValidateDecodedXML(boolean validateDecodedXML) {
        this.validateDecodedXML = validateDecodedXML;
    }

    public boolean isValidateEncodedXML() {
        return validateEncodedXML;
    }

    public void setValidateEncodedXML(boolean validateEncodedXML) {
        this.validateEncodedXML = validateEncodedXML;
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
