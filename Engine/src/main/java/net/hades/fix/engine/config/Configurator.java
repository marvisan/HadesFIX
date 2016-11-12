/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */
package net.hades.fix.engine.config;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

import net.hades.fix.engine.config.model.HadesInstanceInfo;
import net.hades.fix.engine.logging.LoggerController;
import net.hades.fix.message.xml.codec.ValidationErrorHandler;
import net.hades.fix.message.xml.codec.XMLValidationResult;

/**
 * Read the configuration files and sets the main configuartion class with data.
 * It also validates missing configuration.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 */
public class Configurator {

    private static final Logger LOGGER = Logger.getLogger(Configurator.class.getName());

    public static final String CONFIG_FILE_ENV_PROP_NAME = "hades.engine.config.file";

    private static final String CONFIG_CLASSES_PACKAGE = "net.hades.fix.engine.config.model";
    private static final String CONFIG_SCHEMA_LOCATION = "schemas/HadesEngineConfig.xsd";

    private static String configDir;

    private static JAXBContext jaxbContext;

    private Configurator() {
    }

    public static HadesInstanceInfo readConfiguration() throws ConfigurationException {
        return parseConfigFile(findConfigFile());
    }

//    public static String marshallConfig(Object config) throws ConfigurationException {
//        ByteArrayOutputStream bao = new ByteArrayOutputStream();
//        ValidationErrorHandler errorHandler = null;
//        try {
//            JAXBContext context = getCachedJAXBContext();
//            Marshaller marshaller = context.createMarshaller();
//            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
//            errorHandler = new ValidationErrorHandler(false);
//            marshaller.setEventHandler(errorHandler);
//            marshaller.setSchema(getConfigSchema());
//            marshaller.marshal(config, bao);
//        } catch (Exception ex) {
//            String error = "Error marshalling  [" + config.toString() + "].";
//            LOGGER.log(Level.SEVERE, "{0} Error was : {1}", new Object[] { error, ex.toString() });
//            throw new ConfigurationException(error, ex);
//        } finally {
//            logValidationMessages(errorHandler);
//        }
//
//        return bao.toString();
//    }

    public static String getConfigDir() {
        return configDir;
    }

    private static File findConfigFile() throws ConfigurationException {
        String configFileStr = System.getProperty(CONFIG_FILE_ENV_PROP_NAME);
        if (configFileStr == null) {
            String error = "Configuration file set is not set. Please set the [hades.engine.config.file] system property.";
            LOGGER.severe(error);
            throw new ConfigurationException(error);
        }
        File configFile = new File(configFileStr);
        if (!configFile.isFile()) {
            String error = "System property [hades.engine.config.file] value [" + configFile.getAbsolutePath() +
                    "] is not a file.";
            LOGGER.severe(error);
            throw new ConfigurationException(error);
        }
        configDir = new File(configFile.getAbsolutePath()).getParent();
        LoggerController.initialise(configDir);
        
        return configFile;
    }

    private static HadesInstanceInfo parseConfigFile(File configFile) throws ConfigurationException {
        return (HadesInstanceInfo) unmarshallConfigFile(configFile);
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
            String error = "Error un-marshalling config file [" + configFile.getAbsolutePath() + "].";
            LOGGER.log(Level.SEVERE, "{0} Error was : {1}", new Object[] { error, ex.toString() });
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
