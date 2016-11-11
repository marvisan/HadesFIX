/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * SecurityXML.java
 *
 * $Id: SecurityXML.java,v 1.10 2011-09-19 08:15:44 vrotaru Exp $
 */
package net.hades.fix.message.comp;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.anno.FIXVersion;
import net.hades.fix.message.exception.BadParameterException;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.struct.Tag;
import net.hades.fix.message.type.SessionRejectReason;
import net.hades.fix.message.type.TagNum;
import net.hades.fix.message.util.MsgUtil;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.InputSource;

import net.hades.fix.message.anno.TagNumRef;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.util.TagEncoder;

/**
 * The SecurityXML component is used for carrying security description or definition in an
 * XML format. See "Specifying an FpML product specification from within the FIX Instrument
 * Block" for more information on using this component block with FpML as a guideline.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.10 $
 * @created 03/12/2008, 7:18:37 PM
 */
@XmlAccessorType(XmlAccessType.NONE)
public abstract class SecurityXML extends Component {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.SecurityXMLSchema.getValue()
   }));

    protected static final Set<Integer> START_DATA_TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.SecurityXMLLen.getValue()
   }));

    protected static final Set<Integer> START_COMP_TAGS = null;

    protected static final Set<Integer> REQUIRED_TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.SecurityXMLLen.getValue()
   }));

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">

    /**
     * TagNum = 1184. Starting with 5.0SP1 version.
     */
    protected Integer securityXMLLen;

    /**
     * TagNum = 1185. Starting with 5.0SP1 version.
     */
    protected byte[] securityXML;

    /**
     * TagNum = 1186. Starting with 5.0SP1 version.
     */
    protected String securityXMLSchema;

    protected Element securityXMLDoc;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public SecurityXML() {
        super();
    }

    public SecurityXML(FragmentContext context) {
        super(context);
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Public Methods">

    @Override
    public Set<Integer> getFragmentTags() {
        return TAGS;
    }

    // ACCESSOR METHODS
    //////////////////////////////////////////

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.SecurityXMLLen)
    public Integer getSecurityXMLLen() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field setter.
     * @param securityXMLLen field value
     */
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.SecurityXMLLen)
    public void setSecurityXMLLen(Integer securityXMLLen) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.SecurityXML)
    public byte[] getSecurityXML() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field setter.
     * @param securityXML field value
     */
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.SecurityXML)
    public void setSecurityXML(byte[] securityXML) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.SecurityXMLSchema)
    public String getSecurityXMLSchema() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field setter.
     * @param securityXMLSchema field value
     */
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.SecurityXMLSchema)
    public void setSecurityXMLSchema(String securityXMLSchema) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Used in FIXML to set the instrument XML data. The field is automatically
     * populated when <code>setSecurityXML</code> method is called.
     * @return root XML document
     */
    @FIXVersion(introduced = "5.0SP1")
    public Element getSecurityXMLDoc() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    @FIXVersion(introduced = "5.0SP1")
    public void setSecurityXMLDoc(Element securityXMLDoc) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

    @Override
    protected int getFirstTag() {
        return TagNum.SecurityXMLLen.getValue();
    }

    @Override
    protected void validateRequiredTags() throws TagNotPresentException {
        StringBuilder errorMsg = new StringBuilder("Tag value(s) for");
        boolean hasMissingTag = false;
         if (securityXMLLen == null) {
            errorMsg.append(" [SecurityXMLLen]");
            hasMissingTag = true;
        }
        errorMsg.append(" is missing.");
        if (hasMissingTag) {
            throw new TagNotPresentException(errorMsg.toString());
        }
    }

    @Override
    protected byte[] encodeFragmentAll() throws TagNotPresentException, BadFormatMsgException {
        if (validateRequired) {
            validateRequiredTags();
        }
        byte[] result = new byte[0];
        ByteArrayOutputStream bao = new ByteArrayOutputStream();

        try {
            if (securityXMLLen != null && securityXMLLen.intValue() > 0) {
                if (securityXML != null && securityXML.length > 0) {
                    securityXMLLen = new Integer(securityXML.length);
                    TagEncoder.encode(bao, TagNum.SecurityXMLLen, securityXMLLen);
                    TagEncoder.encode(bao, TagNum.SecurityXML, securityXML);
                }
            }
            TagEncoder.encode(bao, TagNum.SecurityXMLSchema, securityXMLSchema, sessionCharset);
            result = bao.toByteArray();
        } catch (IOException ex) {
            String error = "Error writing to the byte array.";
            LOGGER.log(Level.SEVERE, "{0} Error was : {1}", new Object[] { error, ex.toString() });
            throw new BadFormatMsgException(error, ex);
        }

        return result;
    }

    @Override
    protected byte[] encodeFragmentSecured(boolean secured) throws TagNotPresentException, BadFormatMsgException {
        if (secured) {
            return new byte[0];
        } else {
            return encodeFragmentAll();
        }
    }

    @Override
    protected void setFragmentTagValue(Tag tag) throws BadFormatMsgException {
        TagNum tagNum = TagNum.fromString(tag.tagNum);
        switch (tagNum) {
            case SecurityXMLSchema:
                securityXMLSchema = new String(tag.value, sessionCharset);
                break;
                
            default:
                String error = "Tag value [" + tag.tagNum + "] not present in [SecurityXML] fields.";
                LOGGER.severe(error);
                throw new BadFormatMsgException(SessionRejectReason.InvalidTagNumber, tag.tagNum, error);
        }
    }

    @Override
    protected void setFragmentCompTagValue(Tag tag, ByteBuffer message)
    throws BadFormatMsgException, InvalidMsgException, TagNotPresentException {
    }

    @Override
    protected ByteBuffer setFragmentDataTagValue(Tag tag, ByteBuffer message) throws BadFormatMsgException {

        ByteBuffer result = message;
        if (tag.tagNum == TagNum.SecurityXMLLen.getValue()) {
            try {
                securityXMLLen = new Integer(new String(tag.value, sessionCharset));
            } catch (NumberFormatException ex) {
                String error = "Tag [SecurityXMLLen] requires an integer value. The value set was [" + tag.value + "].";
                LOGGER.log(Level.SEVERE, "{0} Error was: {1}", new Object[] { error, ex.toString() });
                throw new BadFormatMsgException(SessionRejectReason.IncorrectDataFormat, TagNum.SecurityXMLLen.getValue(), error);
            }
            Tag  dataTag = MsgUtil.getNextTag(message, securityXMLLen.intValue());
            securityXML = dataTag.value;
        }

        return result;
    }

    @Override
    protected Set<Integer> getFragmentSecuredTags() {
        return SECURED_TAGS;
    }

    @Override
    protected Set<Integer> getFragmentDataTags() {
        return START_DATA_TAGS;
    }

    @Override
    protected Set<Integer> getFragmentCompTags() {
        return START_COMP_TAGS;
    }

    protected Element setXMLDocument(byte[] securityXML) {
        Element result = null;
        try {
            ByteArrayInputStream bis = new ByteArrayInputStream(securityXML);
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(new InputSource(bis));
            result = document.getDocumentElement();
        } catch (Exception ex) {
            String error = "The document [" + new String(securityXML) + "] is not a valid XML document.";
            LOGGER.log(Level.SEVERE, "{0} Error was: {1}", new Object[] { error, ex.toString() });
            throw new BadParameterException(error, ex);
        }

        return result;
    }

    protected byte[] setXMLByteArray(Element securityXMLDoc) {
        byte[] result = null;
        try {
            Source source = new DOMSource(securityXMLDoc);
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            Result baDoc = new StreamResult(out);
            TransformerFactory factory = TransformerFactory.newInstance();
            Transformer transformer = factory.newTransformer();
            transformer.transform(source, baDoc);
            result = out.toByteArray();
        } catch (Exception ex) {
            String error = "Could not process the received DOM data.";
            LOGGER.log(Level.SEVERE, "{0} Error was: {1}", new Object[] { error, ex.toString() });
            throw new BadParameterException(error, ex);
        }

        return result;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Package Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Private Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Inner Classes">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="toString()">

    @Override
    public String toString() {
        StringBuilder b = new StringBuilder(System.getProperty("line.separator"));
        b.append("{SecurityXML=");
        printTagValue(b, TagNum.SecurityXMLLen, securityXMLLen);
        printTagValue(b, TagNum.SecurityXML, securityXML);
        printTagValue(b, TagNum.SecurityXMLSchema, securityXMLSchema);
        b.append("}");

        return b.toString();
    }

    // </editor-fold>
}
