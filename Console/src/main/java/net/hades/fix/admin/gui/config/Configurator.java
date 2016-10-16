/*
 *   Copyright (c) 2006-2012 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * Configurator.java
 *
 * $Id: Configurator.java,v 1.9 2010-08-25 05:30:58 vrotaru Exp $
 */
package net.hades.fix.admin.gui.config;

import net.hades.fix.admin.gui.config.model.AdminConsoleConfigInfo;
import net.hades.fix.admin.logging.LoggerController;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;


/**
 * Reads admin console  configuration file and sets the main configuration class with data.
 * It also validates missing configuration data.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.9 $
 */
public class Configurator {

    private static final Logger LOGGER = Logger.getLogger(Configurator.class.getName());

    public static final String CONFIG_FILE_ENV_PROP_NAME = "hades.admin.config.file";
    public static final String DEFAULT_CONFIG_FILE_NAME = "HadesAdminConfig.xml";

    private static final String CONFIG_CLASSES_PACKAGE = "net.hades.fix.admin.gui.config.model";
    private static final String CONFIG_SCHEMA_LOCATION = "HadesAdminConsoleConfig.xsd";

    private static String configDir;
    private static File configFile;

    private static JAXBContext jaxbContext;

    private Configurator() {
    }

    public static AdminConsoleConfigInfo readConfiguration() throws ConfigurationException {

        try {
            configFile = findConfigFile();
            return parseConfigFile(configFile);
        } catch (ConfigurationException ex) {
            AdminConsoleConfigInfo newConfigFile = new AdminConsoleConfigInfo(ex.getMessage());
            configFile = new File(System.getProperty("user.dir") + "/" + DEFAULT_CONFIG_FILE_NAME);
            FileWriter writer;
            try {
                writer = new FileWriter(configFile);
                writer.write(marshallConfig(newConfigFile));
                writer.close();
            } catch (IOException ex1) {
                String error = "Error creating a new configuration file  [" + configFile.getAbsolutePath() + "].";
                LOGGER.log(Level.SEVERE, "{0} Error was : {1}", new Object[]{error, ex1.toString()});
                throw new ConfigurationException(error, ex);
            }
            return newConfigFile;
        }
    }

    public static void saveConfiguration(AdminConsoleConfigInfo configuration) throws ConfigurationException {
        BufferedOutputStream bos = null;
        try {
            bos = new BufferedOutputStream(new FileOutputStream(configFile));
            bos.write(marshallConfig(configuration).getBytes());
        } catch (IOException ex) {
            String error = "Error saving configuration data in config file  [" + configFile.getAbsolutePath() + "].";
            LOGGER.log(Level.SEVERE, "{0} Error was : {1}", new Object[]{error, ex.toString()});
            throw new ConfigurationException(error, ex);
        } finally {
            try {
                if (bos != null) {
                    bos.close();
                }
            } catch (IOException ex) {
                LOGGER.log(Level.SEVERE, "Could not close the configuration file. Error was : {1}", new Object[]{ex.toString()});
            }
        }
    }

    public static String getConfigDir() {
        return configDir;
    }

    private static File findConfigFile() throws ConfigurationException {
        String configFileStr = System.getProperty(CONFIG_FILE_ENV_PROP_NAME);
        if (configFileStr == null) {
            String error = "Configuration file set is not set. Please set the [hades.admin.config.file] system property.";
            LOGGER.severe(error);
            throw new ConfigurationException(error);
        }
        configFile = new File(configFileStr);
        if (!configFile.isFile()) {
            String error = "System property [hades.admin.config.file] value [" + configFile.getAbsolutePath() + "] is not a file.";
            LOGGER.severe(error);
            throw new ConfigurationException(error);
        }
        configDir = new File(configFile.getAbsolutePath()).getParent();
        LoggerController.initialise(configDir);

        return configFile;
    }

    private static AdminConsoleConfigInfo parseConfigFile(File configFile) throws ConfigurationException {
        return (AdminConsoleConfigInfo) unmarshallConfigFile(configFile);
    }

    private static String marshallConfig(Object config) throws ConfigurationException {
        ByteArrayOutputStream bao = new ByteArrayOutputStream();
        ValidationErrorHandler errorHandler = null;
        try {
            JAXBContext context = getCachedJAXBContext();
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            errorHandler = new ValidationErrorHandler(false);
            marshaller.setEventHandler(errorHandler);
            marshaller.setSchema(getConfigSchema());
            marshaller.marshal(config, bao);
        } catch (Exception ex) {
            String error = "Error marshalling  [" + config.toString() + "].";
            LOGGER.log(Level.SEVERE, "{0} Error was : {1}", new Object[]{error, ex.toString()});
            throw new ConfigurationException(error, ex);
        } finally {
            logValidationMessages(errorHandler);
        }

        return bao.toString();
    }

    private static Object unmarshallConfigFile(File configFile) throws ConfigurationException {
        BufferedInputStream bis;
        ValidationErrorHandler errorHandler = null;
        Object configData = null;
        try {
            bis = new BufferedInputStream(new FileInputStream(configFile));
            JAXBContext context = getCachedJAXBContext();
            Unmarshaller unmarshaller = context.createUnmarshaller();
            errorHandler = new ValidationErrorHandler(false);
            unmarshaller.setEventHandler(errorHandler);
            unmarshaller.setSchema(getConfigSchema());
            configData = unmarshaller.unmarshal(bis);
        } catch (Exception ex) {
            String error = "Error unmarshalling config file [" + configFile.getAbsolutePath() + "].";
            LOGGER.log(Level.SEVERE, "{0} Error was : {1}", new Object[]{error, ex.toString()});
            throw new ConfigurationException(error, ex);
        } finally {
            logValidationMessages(errorHandler);
        }

        return configData;
    }

    private static Schema getConfigSchema() throws Exception {
        SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        return factory.newSchema(ClassLoader.getSystemResource(CONFIG_SCHEMA_LOCATION));
    }

    private static JAXBContext getCachedJAXBContext() throws JAXBException {
        if (jaxbContext == null) {
            jaxbContext = JAXBContext.newInstance(CONFIG_CLASSES_PACKAGE, ClassLoader.getSystemClassLoader());
        }
        return jaxbContext;
    }

    private static void logValidationMessages(ValidationErrorHandler errorHandler) {
        if (errorHandler != null) {
            XMLValidationResult validationResult = errorHandler.getValidationResult();
            if (validationResult.hasErrors()) {
                LOGGER.severe("Config validation errors : \n");
                LOGGER.severe(validationResult.getFatals());
                LOGGER.severe(validationResult.getErrors());
            }
            if (validationResult.hasWarnings()) {
                LOGGER.info("Config validation warnings : \n");
                LOGGER.info(validationResult.getWarnings());
            }
        }
    }
}
