/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.6
 *               Use is subject to license terms.
 */

/*
 * ExceptionUtil.java
 *
 * $Id: ExceptionUtil.java,v 1.1 2010-05-14 11:12:55 vrotaru Exp $
 */
package net.hades.fix.commons.exception;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Utility for exception handling.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 14/04/2010
 */
public class ExceptionUtil {

    // <editor-fold defaultstate="collapsed" desc="Constants">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    private ExceptionUtil() {
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Public Methods">

    public static String getStackTrace(Throwable ex) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw, true);
        pw.write("\n---------------------- EXCEPTION STACK TRACE ----------------------\n");
        ex.printStackTrace(pw);
        if (ex.getCause() != null) {
            pw.write("\n---------------------- CAUSE EXCEPTION STACK TRACE ----------------------\n");
            ex.getCause().printStackTrace(pw);
            pw.write("\n---------------------- CAUSE EXCEPTION STACK TRACE ----------------------\n");
        }
        pw.write("\n---------------------- EXCEPTION STACK TRACE ----------------------\n");
        pw.flush();
        sw.flush();

        return sw.toString();
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
