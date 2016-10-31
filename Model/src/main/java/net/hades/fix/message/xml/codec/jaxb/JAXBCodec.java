/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * JAXBCodec.java
 */
package net.hades.fix.message.xml.codec.jaxb;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import net.hades.fix.message.BatchSetMsg;
import net.hades.fix.message.FIXMsg;
import net.hades.fix.message.Header;
import net.hades.fix.message.config.SessionContext;
import net.hades.fix.message.config.SessionContextKey;
import net.hades.fix.message.config.ThreadData;
import net.hades.fix.message.exception.FixmlDecodingException;
import net.hades.fix.message.exception.FixmlEncodingException;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.impl.v44.BatchSetMsg44;
import net.hades.fix.message.type.ApplVerID;
import net.hades.fix.message.util.MsgUtil;
import net.hades.fix.message.xml.FIXML;
import net.hades.fix.message.xml.codec.FixmlCodec;
import net.hades.fix.message.xml.codec.ValidationErrorHandler;
import net.hades.fix.message.xml.codec.XMLValidationResult;
import net.hades.fix.message.xml.v123.FIXML123;
import net.hades.fix.message.xml.v123.FIXMLMsg123;
import net.hades.fix.message.xml.v44.FIXML44;
import net.hades.fix.message.xml.v50.FIXML50;
import net.hades.fix.message.xml.v50sp1.FIXML50SP1;
import net.hades.fix.message.xml.v50sp2.FIXML50SP2;

/**
 * JAXB implementation of the FIXML codec.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 */
public class JAXBCodec implements FixmlCodec {

    public static final String JAXB_PACKAGES_FILE_NAME = "HadesJAXBPackages.properties";

    private static final String JAXB_PACKAGES_COMMON = "net.hades.fix.message" +
            ":net.hades.fix.message.type";

    private static String JAXB_PACKAGES_V41 = JAXB_PACKAGES_COMMON +
            ":net.hades.fix.message.impl.v41" +
            ":net.hades.fix.message.xml.v123" +
            ":net.hades.fix.message.xml.codec.jaxb.type" +
            ":net.hades.fix.message.xml.codec.jaxb.type.v41";

    private static String JAXB_PACKAGES_V42 = JAXB_PACKAGES_COMMON +
            ":net.hades.fix.message.xml.v123" +
            ":net.hades.fix.message.xml.codec.jaxb.type" +
            ":net.hades.fix.message.impl.v42";

    private static String JAXB_PACKAGES_V43 = JAXB_PACKAGES_COMMON +
            ":net.hades.fix.message.impl.v43" +
            ":net.hades.fix.message.xml.v123" +
            ":net.hades.fix.message.xml.codec.jaxb.type" +
            ":net.hades.fix.message.xml.codec.jaxb.type.v43" +
            ":net.hades.fix.message.group.impl.v43" +
            ":net.hades.fix.message.comp.impl.v43";

    private static String JAXB_PACKAGES_V44 = JAXB_PACKAGES_COMMON +
            ":net.hades.fix.message.impl.v44" +
            ":net.hades.fix.message.xml.v44" +
            ":net.hades.fix.message.group.impl.v44" +
            ":net.hades.fix.message.comp.impl.v44" +
            ":net.hades.fix.message.xml.v44";

    private static String JAXB_PACKAGES_V50 = JAXB_PACKAGES_COMMON +
            ":net.hades.fix.message.impl.v50" +
            ":net.hades.fix.message.xml.v50" +
            ":net.hades.fix.message.group.impl.v50" +
            ":net.hades.fix.message.comp.impl.v50";

    private static String JAXB_PACKAGES_V50SP1 = JAXB_PACKAGES_COMMON +
            ":net.hades.fix.message.impl.v50sp1" +
            ":net.hades.fix.message.xml.v50sp1" +
            ":net.hades.fix.message.group.impl.v50sp1" +
            ":net.hades.fix.message.comp.impl.v50sp1";

    private static String JAXB_PACKAGES_V50SP2 = JAXB_PACKAGES_COMMON +
            ":net.hades.fix.message.impl.v50sp2" +
            ":net.hades.fix.message.xml.v50sp2" +
            ":net.hades.fix.message.group.impl.v50sp2" +
            ":net.hades.fix.message.comp.impl.v50sp2";

    public static final String FIXML_SCHEMA_V41 = "schema/fixml/v41/fixmlmain.xsd";
    public static final String FIXML_SCHEMA_V42 = "schema/fixml/v42/fixml4.2.xsd";
    public static final String FIXML_SCHEMA_V43 = "schema/fixml/v43/fixml4.3.xsd";
    public static final String FIXML_SCHEMA_V44 = "schema/fixml/v44/fixml-main-4-4.xsd";
    public static final String FIXML_SCHEMA_V50 = "schema/fixml/v50/fixml-main-5-0.xsd";
    public static final String FIXML_SCHEMA_V50SP1 = "schema/fixml/v50sp1/fixml-main-5-0-SP1.xsd";
    public static final String FIXML_SCHEMA_V50SP2 = "schema/fixml/v50sp2/fixml-main-5-0-SP2.xsd";

    public static final String FIXML_NAMESPACE_V44 = "http://www.fixprotocol.org/FIXML-4-4";
    public static final String FIXML_NAMESPACE_V50 = "http://www.fixprotocol.org/FIXML-5-0";
    public static final String FIXML_NAMESPACE_V50SP1 = "http://www.fixprotocol.org/FIXML-5-0-SP1";
    public static final String FIXML_NAMESPACE_V50SP2 = "http://www.fixprotocol.org/FIXML-5-0-SP2";

    private static final Map<ApplVerID, JAXBContext> JAXB_CONTEXT_MAP = new EnumMap<>(ApplVerID.class);

    protected final Logger LOGGER = Logger.getLogger(this.getClass().getName());

    private volatile boolean initialised;

    private boolean printableFormatting;

    private boolean validateIncoming;

    private boolean validateOutgoing;

    private boolean validationThrowingError;

    public JAXBCodec() {
        setCustomJaxbPackages();
    }

    @Override
    public String marshall(FIXMsg message) throws FixmlEncodingException {
        ByteArrayOutputStream bao = new ByteArrayOutputStream();
        ValidationErrorHandler errorHandler = null;
        try {
            ApplVerID applVerID = MsgUtil.getMsgFixVersion(message.getHeader().getBeginString(), getSafeApplVerID(message.getHeader()));
            JAXBContext context = getCachedJAXBContext(applVerID);
            Marshaller marshaller = context.createMarshaller();
            if (validateOutgoing) {
                errorHandler = new ValidationErrorHandler(validationThrowingError);
                marshaller.setEventHandler(errorHandler);
                marshaller.setSchema(createFixmlSchema(applVerID));
            }
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, printableFormatting);

            if (ApplVerID.FIX41.equals(applVerID) || ApplVerID.FIX42.equals(applVerID) || ApplVerID.FIX43.equals(applVerID)) {
                // For FIX version 4.1, 4,2, 4.3 we have this message format
                FIXML123 fixmlMsg = new FIXML123(getFixmlVersion(applVerID));
                fixmlMsg.addFIXMLMsg(new FIXMLMsg123(message.getHeader(), message));
                marshaller.marshal(fixmlMsg, bao);
            } else if (ApplVerID.FIX44.equals(applVerID)) {
                // For FIX version 4.4 we have this message format
                FIXML44 fixmlMsg = new FIXML44(message);
                marshaller.marshal(fixmlMsg, bao);
            } else if (ApplVerID.FIX50.equals(applVerID)) {
                // For FIX version 5.0 we have this message format
                FIXML50 fixmlMsg = new FIXML50(message);
                marshaller.marshal(fixmlMsg, bao);
            } else if (ApplVerID.FIX50SP1.equals(applVerID)) {
                // For FIX version 5.0SP1 w}e have this message format
                FIXML50SP1 fixmlMsg = new FIXML50SP1(message);
                marshaller.marshal(fixmlMsg, bao);
            } else if (ApplVerID.FIX50SP2.equals(applVerID)) {
                // For FIX version 5.0SP1 w}e have this message format
                FIXML50SP2 fixmlMsg = new FIXML50SP2(message);
                marshaller.marshal(fixmlMsg, bao);
            } else {
                String error = "FIXML not supported in FIX version [" + applVerID.toString() + "].";
                LOGGER.severe(error);
                throw new FixmlEncodingException(error);
            }
        } catch (Exception ex) {
            String error = "Error marshalling message \n" + message.toString();
            LOGGER.log(Level.SEVERE, "{0} Error was : {1}", new Object[]{error, ex.toString()});
            throw new FixmlEncodingException(error, ex);
        } finally {
            logValidationMessages(errorHandler);
        }

        return bao.toString();
    }

    @Override
    public FIXMsg unmarshall(String fixml, FIXMsg message) throws FixmlDecodingException {
        ByteArrayInputStream bai = new ByteArrayInputStream(fixml.getBytes());
        ValidationErrorHandler errorHandler = null;
        FIXML fixmlMsg = null;
        try {
            ApplVerID applVerID = detectFixVersion(fixml);
            JAXBContext context = getCachedJAXBContext(applVerID);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            if (validateIncoming) {
                errorHandler = new ValidationErrorHandler(validationThrowingError);
                unmarshaller.setEventHandler(errorHandler);
                unmarshaller.setSchema(createFixmlSchema(applVerID));
            }
            fixmlMsg = (FIXML) unmarshaller.unmarshal(bai);
            if (ApplVerID.FIX41.equals(applVerID) || ApplVerID.FIX42.equals(applVerID) || ApplVerID.FIX43.equals(applVerID)) {
                if (fixmlMsg != null && fixmlMsg.getMessage() != null) {
                    if (message == null) {
                        message = fixmlMsg.getMessage();
                    } else {
                        if (fixmlMsg.getMessage().getHeader() != null) {
                            message.getHeader().copyFixmlData(fixmlMsg.getMessage().getHeader());
                        } else {
                            LOGGER.log(Level.WARNING, "No Header data has been unmarshalled for XML data : {0}", fixml);
                        }
                        if (fixmlMsg.getMessage() != null && fixmlMsg.getMessage() != null) {
                            message.copyFixmlData(fixmlMsg.getMessage());
                        } else {
                            LOGGER.log(Level.WARNING, "No ApplicationMessage has been unmarshalled for XML data : {0}", fixml);
                        }
                    }
                } else {
                    LOGGER.log(Level.WARNING, "No FIX message has been unmarshalled for XML data : {0}", fixml);
                }
            } else if (MsgUtil.compare(applVerID, ApplVerID.FIX44) >= 0) {
                if (fixmlMsg != null) {
                    if (message == null) {
                        if (fixmlMsg.getMessage() != null) {
                            message = fixmlMsg.getMessage();
                        } else if (fixmlMsg.getBatches() != null) {
                            message = new BatchSetMsg44();
                            ((BatchSetMsg) message).setBatches(fixmlMsg.getBatches());
                        }
                    } else {
                        if (fixmlMsg.getMessage() != null) {
                            if (fixmlMsg.getMessage().getHeader() != null) {
                                message.getHeader().copyFixmlData(fixmlMsg.getMessage().getHeader());
                            } else {
                                LOGGER.log(Level.FINEST, "No Header data has been unmarshalled for XML data : {0}", fixml);
                            }
                            message.copyFixmlData(fixmlMsg.getMessage());
                        } else if (fixmlMsg.getBatches() != null) {
                            ((BatchSetMsg) message).setBatches(fixmlMsg.getBatches());
                        }
                    }
                } else {
                    LOGGER.log(Level.WARNING, "No FIX message has been unmarshalled for XML data : {0}", fixml);
                }
            }
        } catch (Exception ex) {
            String error = "Error unmarshalling message \n" +
                    (message != null ? message.toString() : (fixmlMsg != null ? fixmlMsg.getMessage().toString() : "null"));
            LOGGER.log(Level.SEVERE, "{0} Error was : {1}", new Object[]{error, ex.toString()});
            throw new FixmlDecodingException(error, ex);
        } finally {
            logValidationMessages(errorHandler);
        }

        return message;
    }

    @Override
    public void setPrintableFormatting(boolean printableFormatting) {
        this.printableFormatting = printableFormatting;
    }

    @Override
    public boolean isPrintableFormatting() {
        return printableFormatting;
    }

    @Override
    public boolean isValidateIncoming() {
        return validateIncoming;
    }

    @Override
    public void setValidateIncoming(boolean validateIncoming) {
        this.validateIncoming = validateIncoming;
    }

    @Override
    public boolean isValidateOutgoing() {
        return validateOutgoing;
    }

    @Override
    public void setValidateOutgoing(boolean validateOutgoing) {
        this.validateOutgoing = validateOutgoing;
    }

    @Override
    public boolean isValidationThrowingError() {
        return validationThrowingError;
    }

    @Override
    public void setValidationThrowingError(boolean validationThrowingError) {
        this.validationThrowingError = validationThrowingError;
    }

    private JAXBContext getCachedJAXBContext(ApplVerID applVerID) throws InvalidMsgException, JAXBException {
        JAXBContext ctx = JAXB_CONTEXT_MAP.get(applVerID);
        if (ctx == null) {
            ctx = JAXBContext.newInstance(getJAXBPackagesForFixVersion(applVerID), Thread.currentThread().getContextClassLoader());
            JAXB_CONTEXT_MAP.put(applVerID, ctx);
        }
        return ctx;
    }

    private String getJAXBPackagesForFixVersion(ApplVerID fixVersion) throws InvalidMsgException {
        String result;
        switch (fixVersion) {
            case FIX41:
                result = JAXB_PACKAGES_V41;
                break;

            case FIX42:
                result = JAXB_PACKAGES_V42;
                break;

            case FIX43:
                result = JAXB_PACKAGES_V43;
                break;

            case FIX44:
                result = JAXB_PACKAGES_V44;
                break;

            case FIX50:
                result = JAXB_PACKAGES_V50;
                break;

            case FIX50SP1:
                result = JAXB_PACKAGES_V50SP1;
                break;

            case FIX50SP2:
                result = JAXB_PACKAGES_V50SP2;
                break;

            default:
                throw new InvalidMsgException("Unsupported FIXML message version [" + fixVersion.getValue() + "].");
        }
        return result;
    }

    private String getFixmlVersion(ApplVerID applVerID) throws InvalidMsgException {
        switch (applVerID) {
            case FIX41:
                return "4.1";

            case FIX42:
                return "4.2";

            case FIX43:
                return "4.3";

            case FIX44:
                return "4.4";

            default:
                String error = "FIXML not supported in FIX version [" + applVerID.getValue() + "].";
                LOGGER.severe(error);
                throw new InvalidMsgException(error);
        }
    }

    private Schema createFixmlSchema(ApplVerID applVerID) throws Exception {
        SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        return factory.newSchema(ClassLoader.getSystemResource(getSchemaLocation(applVerID)));
    }

    private ApplVerID detectFixVersion(String fixml) throws InvalidMsgException {
        ApplVerID result;

        int indexVers = fixml.indexOf("FIXVersion=");
        if (indexVers > 0) {
            String version = fixml.substring(indexVers + 12, indexVers + 15);
            if ("4.1".equals(version)) {
                result = ApplVerID.FIX41;
            } else if ("4.2".equals(version)) {
                result = ApplVerID.FIX42;
            } else if ("4.3".equals(version)) {
                result = ApplVerID.FIX43;
            } else {
                throw new InvalidMsgException("Unsupported FIXML message version [" + version + "].");
            }
        } else if (fixml.indexOf(FIXML_NAMESPACE_V44) > 0) {
            result = ApplVerID.FIX44;
        } else if (fixml.indexOf(FIXML_NAMESPACE_V50SP1) > 0) {
            result = ApplVerID.FIX50SP1;
        } else if (fixml.indexOf(FIXML_NAMESPACE_V50SP2) > 0) {
            result = ApplVerID.FIX50SP2;
        } else if (fixml.indexOf(FIXML_NAMESPACE_V50) > 0) {
            result = ApplVerID.FIX50;
        } else {
            throw new InvalidMsgException("Could not unmarshall FIXML message as there is no FIXVersion attribute " +
                    "or namespace set on the message.");
        }

        return result;
    }

    private String getSchemaLocation(ApplVerID applVerID) throws InvalidMsgException {

        switch (applVerID) {
            case FIX40:
                return null;

            case FIX41:
                return FIXML_SCHEMA_V41;

            case FIX42:
                return FIXML_SCHEMA_V42;

            case FIX43:
                return FIXML_SCHEMA_V43;

            case FIX44:
                return FIXML_SCHEMA_V44;

            case FIX50:
                return FIXML_SCHEMA_V50;

            case FIX50SP1:
                return FIXML_SCHEMA_V50SP1;

            case FIX50SP2:
                return FIXML_SCHEMA_V50SP2;

            default:
                throw new InvalidMsgException("Unsupported FIXML version [" + applVerID.getValue() + "].");
        }
    }

    private void logValidationMessages(ValidationErrorHandler errorHandler) {
        if (errorHandler != null) {
            XMLValidationResult validationResult = errorHandler.getValidationResult();
            if (validationResult.hasErrors()) {
                LOGGER.severe("FIXML validation errors : \n");
                LOGGER.severe(validationResult.getFatals());
                LOGGER.severe(validationResult.getErrors());
            }
            if (validationResult.hasWarnings()) {
                LOGGER.info("FIXML validation warnings : \n");
                LOGGER.info(validationResult.getWarnings());
            }
        }
    }

    private ApplVerID getSafeApplVerID(Header header) {
        ApplVerID result = null;
        try {
            result = header.getApplVerID();
            if (result == null) {
                // not in header then get it from session
                result = getSessionApplVerID();
            }
        } catch (UnsupportedOperationException ex) {
            // unsupported then leave it null
        }

        return result;
    }

    private ApplVerID getSessionApplVerID() {
        ApplVerID result = null;
        SessionContext context = ThreadData.getSessionContext();
        if (context == null) {
            LOGGER.warning("Session context data has not been set by the current FIX session.");
        } else {
            if (context.getValue(SessionContextKey.DEFAULT_APPL_VER_ID) == null) {
                LOGGER.warning("Session default ApplVerID not set.");
            } else {
                result = (ApplVerID) context.getValue(SessionContextKey.DEFAULT_APPL_VER_ID);
            }
        }

        return result;
    }

    private Map<String, String> loadJAXBCustomPackages() {
        Properties props = new Properties();
        InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(JAXB_PACKAGES_FILE_NAME);
        try {
            props.load(is);
        } catch (IOException ex) {
            LOGGER.info("No custom JAXB packages configured on the classpath.");
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException ex) {
                    LOGGER.log(Level.WARNING, "Could not close input stream for [{0}] file.", JAXB_PACKAGES_FILE_NAME);
                }
            }
        }
        Map<String, String> jaxbPackages = new LinkedHashMap<>();
        Set<String> customMsgs = props.stringPropertyNames();
        for (String customMsg : customMsgs) {
            jaxbPackages.put(customMsg, props.getProperty(customMsg));
        }

        return jaxbPackages;
    }

    private void setCustomJaxbPackages() {
        if (!initialised) {
            Map<String, String> jaxbPackages = loadJAXBCustomPackages();
            for (String version : jaxbPackages.keySet()) {
                ApplVerID applVer = ApplVerID.valueFor(version);
                if (applVer == null) {
                    String error = "Invalid version key set in the custom JAXB packages file [" + JAXB_PACKAGES_FILE_NAME
                            + "]. Unknown value was [" + version + "].";
                    LOGGER.severe(error);
                    throw new IllegalArgumentException(error);
                }
                if (applVer.equals(ApplVerID.FIX41)) {
                    JAXB_PACKAGES_V41 = JAXB_PACKAGES_V41 + ":" + jaxbPackages.get(version);
                } else if (applVer.equals(ApplVerID.FIX42)) {
                    JAXB_PACKAGES_V42 = JAXB_PACKAGES_V42 + ":" + jaxbPackages.get(version);
                } else if (applVer.equals(ApplVerID.FIX43)) {
                    JAXB_PACKAGES_V43 = JAXB_PACKAGES_V43 + ":" + jaxbPackages.get(version);
                } else if (applVer.equals(ApplVerID.FIX44)) {
                    JAXB_PACKAGES_V44 = JAXB_PACKAGES_V44  + ":" + jaxbPackages.get(version);
                } else if (applVer.equals(ApplVerID.FIX50)) {
                    JAXB_PACKAGES_V50 = JAXB_PACKAGES_V50 + ":" + jaxbPackages.get(version);
                } else if (applVer.equals(ApplVerID.FIX50SP1)) {
                    JAXB_PACKAGES_V50SP1 = JAXB_PACKAGES_V50SP1 + ":" + jaxbPackages.get(version);
                } else if (applVer.equals(ApplVerID.FIX50SP2)) {
                    JAXB_PACKAGES_V50SP2 = JAXB_PACKAGES_V50SP2 + ":" + jaxbPackages.get(version);
                }
            }
        }
        initialised = true;
    }
}
