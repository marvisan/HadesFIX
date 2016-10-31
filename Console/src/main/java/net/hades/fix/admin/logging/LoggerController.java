/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * LoggerController.java
 *
 * $Id: LoggerController.java,v 1.1 2010-06-01 11:39:50 vrotaru Exp $
 */
package net.hades.fix.admin.logging;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.LogManager;

/**
 * Sets up the logging for the Admin console engine.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 01/06/2010
 */
public class LoggerController {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final String LOG_CONFIG_FILE_NAME = "hades-logging.properties";
    
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Public Methods">

    public static void initialise(String configDir) {
        LogManager lm = LogManager.getLogManager();
        File logConfig = new File(configDir + File.separator + LOG_CONFIG_FILE_NAME);
        if (logConfig.exists()) {
            // we have a dedicated log config file
            BufferedInputStream bis = null;
            try {
                bis = new BufferedInputStream(new FileInputStream(logConfig));
                lm.readConfiguration(bis);
            } catch (Exception ex) {
                System.out.println("Could not configure the logger from file [" + logConfig.getAbsolutePath()
                        + "]. Error was : " + ex.toString());
            } finally {
                try {
                    bis.close();
                } catch (IOException ex1) {
                    System.out.println("Could not close the input stream. Error was : " + ex1.toString());
                }
            }
        } else {
            try {
                lm.readConfiguration();
            } catch (Exception ex) {
                System.out.println("Could not configure the logger with default settings. Error was : " + ex.toString());
            }
        }
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
