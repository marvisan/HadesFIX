/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.6
 *               Use is subject to license terms.
 */

/*
 * MsgTest.java
 *
 * $Id: MsgTest.java,v 1.7 2011-04-04 09:37:11 vrotaru Exp $
 */
package net.hades.fix.message;

import java.io.ByteArrayInputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import java.text.SimpleDateFormat;
import javax.xml.validation.Validator;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import static org.junit.Assert.*;

import quickfix.ConfigError;
import quickfix.DataDictionary;

import net.hades.fix.message.config.SessionContext;
import net.hades.fix.message.config.SessionContextKey;
import net.hades.fix.message.config.ThreadData;
import net.hades.fix.message.crypt.CrypterData;
import net.hades.fix.message.crypt.CrypterSelector;
import net.hades.fix.message.exception.UnsupportedCrypterException;
import net.hades.fix.message.type.ApplVerID;
import net.hades.fix.message.type.EncryptMethod;

/**
 * Super class for all the test messages.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.7 $
 * @created 10/08/2008, 14:19:52
 */
public class MsgTest {
    
    public static final String DES_SECRET = "SecretPassword";
    
    public static final String DEFAULT_CHARACTER_SET = "ISO-8859-1";
    public static final Charset TEST_CHARSET = Charset.forName(DEFAULT_CHARACTER_SET);
    public static final FragmentContext TEST_FRAGMENT_CONTEXT = new FragmentContext(Charset.forName(DEFAULT_CHARACTER_SET),
            Charset.forName(DEFAULT_CHARACTER_SET));

    public static final String FIXML_SCHEMA_V41         = "schema/fixml/v41/fixmlmain.xsd";
    public static final String FIXML_SCHEMA_V42         = "schema/fixml/v42/fixml4.2.xsd";
    public static final String FIXML_SCHEMA_V43         = "schema/fixml/v43/fixml4.3.xsd";
    public static final String FIXML_SCHEMA_V44         = "schema/fixml/v44/fixml-main-4-4.xsd";
    public static final String FIXML_SCHEMA_V50         = "schema/fixml/v50/fixml-main-5-0.xsd";
    public static final String FIXML_SCHEMA_V50SP1      = "schema/fixml/v50sp1/fixml-main-5-0-SP1.xsd";
    public static final String FIXML_SCHEMA_V50SP2      = "schema/fixml/v50sp2/fixml-main-5-0-SP2.xsd";
    
    protected void setSecuredDataDES() throws UnsupportedCrypterException, UnsupportedEncodingException {
        byte[] secret = DES_SECRET.getBytes("UTF-8");
        CrypterData crypterData = new CrypterData(EncryptMethod.DES, secret);
        SessionContext context = ThreadData.getSessionContext();
        context.setValue(SessionContextKey.ENCRYPTION_DATA, crypterData);
        context.setValue(SessionContextKey.CRYPTER, CrypterSelector.createCrypter(crypterData));
    }
    
    protected void unsetSecuredData() {
        CrypterData crypterData = new CrypterData(EncryptMethod.None, null);
        SessionContext context = ThreadData.getSessionContext();
        context.setValue(SessionContextKey.ENCRYPTION_DATA, crypterData);
        context.setValue(SessionContextKey.CRYPTER, null);
    }

    protected void setPrintableValidatingFixml() throws UnsupportedCrypterException, UnsupportedEncodingException {
        SessionContext context = ThreadData.getSessionContext();
        context.setValue(SessionContextKey.PRINTABLE_FIXML, Boolean.TRUE);
        context.setValue(SessionContextKey.VALIDATE_INCOMING_FIXML, Boolean.TRUE);
        context.setValue(SessionContextKey.VALIDATE_OUTGOING_FIXML, Boolean.TRUE);
//        context.setValue(SessionContextKey.FIXML_VALIDATE_ABORT_ON_ERROR, Boolean.TRUE);
    }

    protected void setPrintableFixml() throws UnsupportedCrypterException, UnsupportedEncodingException {
        SessionContext context = ThreadData.getSessionContext();
        context.setValue(SessionContextKey.PRINTABLE_FIXML, Boolean.TRUE);
    }

    protected void setValidationRequired() throws UnsupportedCrypterException, UnsupportedEncodingException {
        SessionContext context = ThreadData.getSessionContext();
        context.setValue(SessionContextKey.VALIDATE_REQUIRED, Boolean.TRUE);
    }

    protected void setSessionApplVerID(ApplVerID applVerID) {
        SessionContext context = ThreadData.getSessionContext();
        context.setValue(SessionContextKey.DEFAULT_APPL_VER_ID, applVerID.getValue());
    }

    protected void unsetSessionData() {
        SessionContext context = ThreadData.getSessionContext();
        context.clear();
    }

    protected void unsetPrintableFixml() {
        SessionContext context = ThreadData.getSessionContext();
        context.setValue(SessionContextKey.PRINTABLE_FIXML, Boolean.FALSE);
        context.setValue(SessionContextKey.VALIDATE_INCOMING_FIXML, Boolean.FALSE);
        context.setValue(SessionContextKey.VALIDATE_OUTGOING_FIXML, Boolean.FALSE);
        context.setValue(SessionContextKey.FIXML_VALIDATE_ABORT_ON_ERROR, Boolean.FALSE);
    }
    
    protected void setFIXT11DefaultApplVersion() throws UnsupportedCrypterException, UnsupportedEncodingException {
        SessionContext context = ThreadData.getSessionContext();
        context.setValue(SessionContextKey.DEFAULT_APPL_VER_ID, ApplVerID.FIX50);
    }

    protected void assertDateEquals(Date expected, Date actual) {
        if (expected != null && actual != null) {
            Calendar expCal = Calendar.getInstance();
            expCal.setTime(expected);
            Calendar actCal = Calendar.getInstance();
            actCal.setTime(actual);
            assertEquals(expCal.get(Calendar.YEAR), actCal.get(Calendar.YEAR));
            assertEquals(expCal.get(Calendar.MONTH), actCal.get(Calendar.MONTH));
            assertEquals(expCal.get(Calendar.DAY_OF_MONTH), actCal.get(Calendar.DAY_OF_MONTH));
        } else if ((expected == null && actual != null) || (expected != null && actual == null)) {
            fail("One of the dates is null");
        }
    }

    protected void assertUTCDateEquals(Date expected, Date actual) {
        if (expected != null && actual != null) {
            Calendar expCal = Calendar.getInstance();
//            expCal.setTimeZone(TimeZone.getTimeZone("UTC"));
            expCal.setTime(expected);
            Calendar actCal = Calendar.getInstance();
            actCal.setTime(actual);
            assertEquals(expCal.get(Calendar.YEAR), actCal.get(Calendar.YEAR));
            assertEquals(expCal.get(Calendar.MONTH), actCal.get(Calendar.MONTH));
            assertEquals(expCal.get(Calendar.DAY_OF_MONTH), actCal.get(Calendar.DAY_OF_MONTH));
        } else if ((expected == null && actual != null) || (expected != null && actual == null)) {
            fail("One of the dates is null");
        }
    }

    protected void assertTimeEquals(Date expected, Date actual) {
        if (expected != null && actual != null) {
            Calendar expCal = Calendar.getInstance();
            expCal.setTime(expected);
            Calendar actCal = Calendar.getInstance();
            actCal.setTime(actual);
            assertEquals(expCal.get(Calendar.HOUR_OF_DAY), actCal.get(Calendar.HOUR_OF_DAY));
            assertEquals(expCal.get(Calendar.MINUTE), actCal.get(Calendar.MINUTE));
            assertEquals(expCal.get(Calendar.SECOND), actCal.get(Calendar.SECOND));
            assertEquals(expCal.get(Calendar.MILLISECOND), actCal.get(Calendar.MILLISECOND));
        } else if ((expected == null && actual != null) || (expected != null && actual == null)) {
            fail("One of the timestamps is null");
        }
    }

    protected void assertTimeEquals(Date expected, Date actual, boolean checkMillis) {
        if (expected != null && actual != null) {
//            Calendar expCal = Calendar.getInstance();
//            expCal.setTime(expected);
//            Calendar actCal = Calendar.getInstance();
//            actCal.setTime(actual);
//            assertEquals(expCal.get(Calendar.HOUR_OF_DAY), actCal.get(Calendar.HOUR_OF_DAY));
//            assertEquals(expCal.get(Calendar.MINUTE), actCal.get(Calendar.MINUTE));
//            assertEquals(expCal.get(Calendar.SECOND), actCal.get(Calendar.SECOND));
//            if (checkMillis) {
//                assertEquals(expCal.get(Calendar.MILLISECOND), actCal.get(Calendar.MILLISECOND));
//            }
            SimpleDateFormat formatter = null;
            if (checkMillis) {
                formatter = new SimpleDateFormat("HH:mm:ss.SSS");
            } else {
                formatter = new SimpleDateFormat("HH:mm:ss");
            }
            String expDateStr = formatter.format(expected);
            Date expDate = null;
            try {
                expDate = formatter.parse(expDateStr);
            } catch (ParseException ex) {
                fail("Bad time format");
            }
            Calendar expCal = Calendar.getInstance();
            expCal.setTime(expDate);

            Calendar actCal = Calendar.getInstance();
            actCal.setTime(actual);
            assertEquals(expCal.get(Calendar.HOUR_OF_DAY), actCal.get(Calendar.HOUR_OF_DAY));
            assertEquals(expCal.get(Calendar.MINUTE), actCal.get(Calendar.MINUTE));
            assertEquals(expCal.get(Calendar.SECOND), actCal.get(Calendar.SECOND));
            if (checkMillis) {
                assertEquals(expCal.get(Calendar.MILLISECOND), actCal.get(Calendar.MILLISECOND));
            }
        } else if ((expected == null && actual != null) || (expected != null && actual == null)) {
            fail("One of the timestamps is null");
        }
    }

    protected void assertISOUTCTimeEquals(Date expected, Date actual, boolean checkMillis) {
        if (expected != null && actual != null) {
            SimpleDateFormat formatter = null;
            if (checkMillis) {
                formatter = new SimpleDateFormat("HH:mm:ss.SSS");
            } else {
                formatter = new SimpleDateFormat("HH:mm:ss");
            }
            formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
            String expDateStr = formatter.format(expected);
            Date expDate = null;
            formatter.setTimeZone(TimeZone.getDefault());
            try {
                expDate = formatter.parse(expDateStr);
            } catch (ParseException ex) {
                fail("Bad time format");
            }
            Calendar expCal = Calendar.getInstance();
            expCal.setTime(expDate);

            Calendar actCal = Calendar.getInstance();
            actCal.setTime(actual);
            assertEquals(expCal.get(Calendar.HOUR_OF_DAY), actCal.get(Calendar.HOUR_OF_DAY));
            assertEquals(expCal.get(Calendar.MINUTE), actCal.get(Calendar.MINUTE));
            assertEquals(expCal.get(Calendar.SECOND), actCal.get(Calendar.SECOND));
            if (checkMillis) {
                assertEquals(expCal.get(Calendar.MILLISECOND), actCal.get(Calendar.MILLISECOND));
            }
        } else if ((expected == null && actual != null) || (expected != null && actual == null)) {
            fail("One of the timestamps is null");
        }
    }

    protected void assertUTCTimeEquals(Date expected, Date actual, boolean checkMillis) {
        if (expected != null && actual != null) {
            SimpleDateFormat formatter = null;
            if (checkMillis) {
                formatter = new SimpleDateFormat("HH:mm:ss.SSS");
            } else {
                formatter = new SimpleDateFormat("HH:mm:ss");
            }
            formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
            String expDateStr = formatter.format(expected);
            Date expDate = null;
            formatter.setTimeZone(TimeZone.getDefault());
            try {
                expDate = formatter.parse(expDateStr);
            } catch (ParseException ex) {
                fail("Bad time format");
            }
            Calendar expCal = Calendar.getInstance();
            expCal.setTime(expDate);

            Calendar actCal = Calendar.getInstance();
            actCal.setTime(actual);
            assertEquals(expCal.get(Calendar.HOUR_OF_DAY), actCal.get(Calendar.HOUR_OF_DAY));
            assertEquals(expCal.get(Calendar.MINUTE), actCal.get(Calendar.MINUTE));
            assertEquals(expCal.get(Calendar.SECOND), actCal.get(Calendar.SECOND));
            if (checkMillis) {
                assertEquals(expCal.get(Calendar.MILLISECOND), actCal.get(Calendar.MILLISECOND));
            }
        } else if ((expected == null && actual != null) || (expected != null && actual == null)) {
            fail("One of the timestamps is null");
        }
    }

    protected void assertTZTimeEquals(Date expected, Date actual, boolean checkMillis) {
        if (expected != null && actual != null) {
            SimpleDateFormat formatter = null;
            if (checkMillis) {
                formatter = new SimpleDateFormat("HH:mm:ss.SSSZ");
            } else {
                formatter = new SimpleDateFormat("HH:mm:ssZ");
            }
            String expDateStr = formatter.format(expected);
            Date expDate = null;
            try {
                expDate = formatter.parse(expDateStr);
            } catch (ParseException ex) {
                fail("Bad time format");
            }
            Calendar expCal = Calendar.getInstance();
            expCal.setTime(expDate);

            Calendar actCal = Calendar.getInstance();
            actCal.setTime(actual);
            assertEquals(expCal.get(Calendar.HOUR_OF_DAY), actCal.get(Calendar.HOUR_OF_DAY));
            assertEquals(expCal.get(Calendar.MINUTE), actCal.get(Calendar.MINUTE));
            assertEquals(expCal.get(Calendar.SECOND), actCal.get(Calendar.SECOND));
            if (checkMillis) {
                assertEquals(expCal.get(Calendar.MILLISECOND), actCal.get(Calendar.MILLISECOND));
            }
        } else if ((expected == null && actual != null) || (expected != null && actual == null)) {
            fail("One of the timestamps is null");
        }
    }

    protected void assertTimestampEquals(Date expected, Date actual) {
        if (expected != null && actual != null) {
            Calendar expCal = Calendar.getInstance();
            expCal.setTime(expected);
            Calendar actCal = Calendar.getInstance();
            actCal.setTime(actual);
            assertEquals(expCal.get(Calendar.YEAR), actCal.get(Calendar.YEAR));
            assertEquals(expCal.get(Calendar.MONTH), actCal.get(Calendar.MONTH));
            assertEquals(expCal.get(Calendar.DAY_OF_MONTH), actCal.get(Calendar.DAY_OF_MONTH));
            assertEquals(expCal.get(Calendar.HOUR_OF_DAY), actCal.get(Calendar.HOUR_OF_DAY));
            assertEquals(expCal.get(Calendar.MINUTE), actCal.get(Calendar.MINUTE));
            assertEquals(expCal.get(Calendar.SECOND), actCal.get(Calendar.SECOND));
        } else if ((expected == null && actual != null) || (expected != null && actual == null)) {
            fail("One of the timestamps is null");
        }
    }

    protected void assertTimestampEquals(Date expected, Date actual, boolean checkMillis) {
        if (expected != null && actual != null) {
            Calendar expCal = Calendar.getInstance();
            expCal.setTime(expected);
            Calendar actCal = Calendar.getInstance();
            actCal.setTime(actual);
            assertEquals(expCal.get(Calendar.YEAR), actCal.get(Calendar.YEAR));
            assertEquals(expCal.get(Calendar.MONTH), actCal.get(Calendar.MONTH));
            assertEquals(expCal.get(Calendar.DAY_OF_MONTH), actCal.get(Calendar.DAY_OF_MONTH));
            assertEquals(expCal.get(Calendar.HOUR_OF_DAY), actCal.get(Calendar.HOUR_OF_DAY));
            assertEquals(expCal.get(Calendar.MINUTE), actCal.get(Calendar.MINUTE));
            assertEquals(expCal.get(Calendar.SECOND), actCal.get(Calendar.SECOND));
            if (checkMillis) {
                assertEquals(expCal.get(Calendar.MILLISECOND), actCal.get(Calendar.MILLISECOND));
            }
        } else if ((expected == null && actual != null) || (expected != null && actual == null)) {
            fail("One of the timestamps is null");
        }
    }

    protected void assertUTCTimestampEquals(Date expected, Date actual, boolean checkMillis) {
        if (expected != null && actual != null) {
            try {
                if (dateEquals(expected, actual, checkMillis)) {
                    // FIXML
                    return;
                }
                SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss.SSS");
                formatter.setTimeZone(TimeZone.getDefault());
                String actDateStr = formatter.format(actual);
                formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
                Date actualDate = formatter.parse(actDateStr);
                Calendar actCal = Calendar.getInstance();
                actCal.setTime(actualDate);
                Calendar expCal = Calendar.getInstance();
                expCal.setTime(expected);
                assertEquals(expCal.get(Calendar.YEAR), actCal.get(Calendar.YEAR));
                assertEquals(expCal.get(Calendar.MONTH), actCal.get(Calendar.MONTH));
                assertEquals(expCal.get(Calendar.DAY_OF_MONTH), actCal.get(Calendar.DAY_OF_MONTH));
                assertEquals(expCal.get(Calendar.HOUR_OF_DAY), actCal.get(Calendar.HOUR_OF_DAY));
                assertEquals(expCal.get(Calendar.MINUTE), actCal.get(Calendar.MINUTE));
                assertEquals(expCal.get(Calendar.SECOND), actCal.get(Calendar.SECOND));
                if (checkMillis) {
                    assertEquals(expCal.get(Calendar.MILLISECOND), actCal.get(Calendar.MILLISECOND));
                }
            } catch (ParseException ex) {
                fail("Parse error : " + ex.toString());
            }
        } else if ((expected == null && actual != null) || (expected != null && actual == null)) {
            fail("One of the timestamps is null");
        }
    }
    
    protected String formatQFStringDate(Date date) {
        if (date == null) {
            return null;
        } else {
            return new SimpleDateFormat("yyyyMMdd").format(date);
        }
    }

    protected Date parseQFDateString(String date) {
        if (date == null) {
            return null;
        } else {
            try {
                return new SimpleDateFormat("yyyyMMdd").parse(date);
            } catch (ParseException ex) {
                return null;
            }
        }
    }

    protected DataDictionary getQF40DataDictionary() throws ConfigError {
        return new DataDictionary(this.getClass().getClassLoader().getResourceAsStream("FIX40.xml"));
    }

    protected DataDictionary getQF41DataDictionary() throws ConfigError {
        return new DataDictionary(this.getClass().getClassLoader().getResourceAsStream("FIX41.xml"));
    }

    protected DataDictionary getQF42DataDictionary() throws ConfigError {
        return new DataDictionary(this.getClass().getClassLoader().getResourceAsStream("FIX42.xml"));
    }

    protected DataDictionary getQF43DataDictionary() throws ConfigError {
        return new DataDictionary(this.getClass().getClassLoader().getResourceAsStream("FIX43.xml"));
    }

    protected DataDictionary getQF44DataDictionary() throws ConfigError {
        return new DataDictionary(this.getClass().getClassLoader().getResourceAsStream("FIX44.xml"));
    }

    protected DataDictionary getQF50DataDictionary() throws ConfigError {
        return new DataDictionary(this.getClass().getClassLoader().getResourceAsStream("FIX50.xml"));
    }

    protected DataDictionary getQFSessDataDictionary() throws ConfigError {
        return new DataDictionary(this.getClass().getClassLoader().getResourceAsStream("FIXT11.xml"));
    }

    protected XMLValidationResult validateXMLAgainstDTD(String xml, String dtd) {

        XMLErrorHandler handler = new XMLErrorHandler();
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setValidating(true);
            factory.setNamespaceAware(true);
            DocumentBuilder builder = factory.newDocumentBuilder();
            builder.setErrorHandler(handler);
            ByteArrayInputStream bis = new ByteArrayInputStream(xml.getBytes());
            Document xmlDocument = builder.parse(bis);
            DOMSource source = new DOMSource(xmlDocument);
            StreamResult result = new StreamResult(System.out);
            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer transformer = tf.newTransformer();
            transformer.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, dtd);
            transformer.transform(source, result);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return handler.getValidationResult();
    }

    protected XMLValidationResult validateXMLAgainstXSD(String xml, String dtd) {

        XMLValidationResult result = null;
        XMLErrorHandler handler = new XMLErrorHandler();
        try {
            ByteArrayInputStream bis = new ByteArrayInputStream(xml.getBytes());
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setNamespaceAware(true);
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(new InputSource(bis));

            SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = schemaFactory.newSchema(ClassLoader.getSystemResource(dtd));
            Validator validator = schema.newValidator();
            validator.setErrorHandler(handler);
            validator.validate(new DOMSource(document));
            result = handler.getValidationResult();
        } catch (Exception ex) {
            result = new XMLValidationResult("Runtime error : " + ex.toString(), "", "");
        }

        return result;
    }

    private boolean dateEquals(Date expected, Date actual, boolean checkMillis) {
        boolean result = true;
        Calendar expCal = Calendar.getInstance();
        expCal.setTime(expected);
        Calendar actCal = Calendar.getInstance();
        actCal.setTime(actual);
        if (expCal.get(Calendar.YEAR) != actCal.get(Calendar.YEAR)) {
            result = false;
        }
        if (expCal.get(Calendar.MONTH) != actCal.get(Calendar.MONTH)) {
            result = false;
        }
        if (expCal.get(Calendar.DAY_OF_MONTH) != actCal.get(Calendar.DAY_OF_MONTH)) {
            result = false;
        }
        if (expCal.get(Calendar.HOUR_OF_DAY) != actCal.get(Calendar.HOUR_OF_DAY)) {
            result = false;
        }
        if (expCal.get(Calendar.MINUTE) != actCal.get(Calendar.MINUTE)) {
            result = false;
        }
        if (expCal.get(Calendar.SECOND) != actCal.get(Calendar.SECOND)) {
            result = false;
        }
        if (checkMillis) {
            if (expCal.get(Calendar.MILLISECOND) != actCal.get(Calendar.MILLISECOND)) {
                result = false;
            }
        }

        return result;
    }

    private class XMLErrorHandler implements org.xml.sax.ErrorHandler {
        private StringBuilder fatals = new StringBuilder();
        private StringBuilder errors = new StringBuilder();
        private StringBuilder warnings = new StringBuilder();

        @Override
        public void fatalError(SAXParseException ex) throws SAXException {
            fatals.append("FATAL error at line [").append(ex.getLineNumber()).append("] - ").append(ex.getMessage()).append("\n");
        }

        @Override
        public void error(SAXParseException ex) throws SAXParseException {
            errors.append("ERROR at line [").append(ex.getLineNumber()).append("] - ").append(ex.getMessage()).append("\n");
        }

        @Override
        public void warning(SAXParseException ex) throws SAXParseException {
            warnings.append("WARNING at line [").append(ex.getLineNumber()).append("] - ").append(ex.getMessage()).append("\n");
        }

        public XMLValidationResult getValidationResult() {
            return new XMLValidationResult(fatals.toString(), errors.toString(), warnings.toString());
        }
    }
}
