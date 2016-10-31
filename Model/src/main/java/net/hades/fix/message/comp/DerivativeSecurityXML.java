/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * SecurityXML.java
 *
 * $Id: DerivativeSecurityXML.java,v 1.1 2011-09-19 08:15:44 vrotaru Exp $
 */
package net.hades.fix.message.comp;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.anno.FIXVersion;
import net.hades.fix.message.anno.TagNumRef;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.BadParameterException;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
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

import net.hades.fix.message.util.TagEncoder;

/**
 * The SecurityXML component is used for carrying security description or definition in an
 * XML format. See "Specifying an FpML product specification from within the FIX Instrument
 * Block" for more information on using this component block with FpML as a guideline.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 03/12/2008, 7:18:37 PM
 */
@XmlAccessorType(XmlAccessType.NONE)
public abstract class DerivativeSecurityXML extends Component {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.DerivativeSecurityXMLSchema.getValue()
   }));

    protected static final Set<Integer> START_DATA_TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.DerivativeSecurityXMLLen.getValue()
   }));

    protected static final Set<Integer> START_COMP_TAGS = null;

    protected static final Set<Integer> REQUIRED_TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.DerivativeSecurityXMLLen.getValue()
   }));

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">

    /**
     * TagNum = 1282. Starting with 5.0SP1 version.
     */
    protected Integer derivativeSecurityXMLLen;

    /**
     * TagNum = 1283. Starting with 5.0SP1 version.
     */
    protected byte[] derivativeSecurityXML;

    /**
     * TagNum = 1284. Starting with 5.0SP1 version.
     */
    protected String derivativeSecurityXMLSchema;

    protected Element derivativeSecurityXMLDoc;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public DerivativeSecurityXML() {
        super();
    }

    public DerivativeSecurityXML(FragmentContext context) {
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
    @TagNumRef(tagNum = TagNum.DerivativeSecurityXMLLen)
    public Integer getDerivativeSecurityXMLLen() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field setter.
     * @param derivativeSecurityXMLLen field value
     */
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.DerivativeSecurityXMLLen)
    public void setDerivativeSecurityXMLLen(Integer derivativeSecurityXMLLen) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.DerivativeSecurityXML)
    public byte[] getDerivativeSecurityXML() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field setter.
     * @param derivativeSecurityXML field value
     */
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.DerivativeSecurityXML)
    public void setDerivativeSecurityXML(byte[] derivativeSecurityXML) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.DerivativeSecurityXMLSchema)
    public String getDerivativeSecurityXMLSchema() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field setter.
     * @param derivativeSecurityXMLSchema field value
     */
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.DerivativeSecurityXMLSchema)
    public void setDerivativeSecurityXMLSchema(String derivativeSecurityXMLSchema) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Used in FIXML to set the instrument XML data. The field is automatically
     * populated when <code>setSecurityXML</code> method is called.
     * @return root XML document
     */
    @FIXVersion(introduced = "5.0SP1")
    public Element getDerivativeSecurityXMLDoc() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    @FIXVersion(introduced = "5.0SP1")
    public void setDerivativeSecurityXMLDoc(Element derivativeSecurityXMLDoc) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

    @Override
    protected int getFirstTag() {
        return TagNum.DerivativeSecurityXMLLen.getValue();
    }

    @Override
    protected void validateRequiredTags() throws TagNotPresentException {
        StringBuilder errorMsg = new StringBuilder("Tag value(s) for");
        boolean hasMissingTag = false;
         if (derivativeSecurityXMLLen == null) {
            errorMsg.append(" [derivativeSecurityXMLLen]");
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
            if (derivativeSecurityXMLLen != null && derivativeSecurityXMLLen.intValue() > 0) {
                if (derivativeSecurityXML != null && derivativeSecurityXML.length > 0) {
                    derivativeSecurityXMLLen = new Integer(derivativeSecurityXML.length);
                    TagEncoder.encode(bao, TagNum.DerivativeSecurityXMLLen, derivativeSecurityXMLLen);
                    TagEncoder.encode(bao, TagNum.DerivativeSecurityXML, derivativeSecurityXML);
                }
            }
            TagEncoder.encode(bao, TagNum.DerivativeSecurityXMLSchema, derivativeSecurityXMLSchema, sessionCharset);
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
            case DerivativeSecurityXMLSchema:
                derivativeSecurityXMLSchema = new String(tag.value, sessionCharset);
                break;
                
            default:
                String error = "Tag value [" + tag.tagNum + "] not present in [DerivativeSecurityXML] fields.";
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
        if (tag.tagNum == TagNum.DerivativeSecurityXMLLen.getValue()) {
            try {
                derivativeSecurityXMLLen = new Integer(new String(tag.value, sessionCharset));
            } catch (NumberFormatException ex) {
                String error = "Tag [DerivativeSecurityXMLLen] requires an integer value. The value set was [" + tag.value + "].";
                LOGGER.log(Level.SEVERE, "{0} Error was: {1}", new Object[] { error, ex.toString() });
                throw new BadFormatMsgException(SessionRejectReason.IncorrectDataFormat, TagNum.SecurityXMLLen.getValue(), error);
            }
            Tag  dataTag = MsgUtil.getNextTag(message, derivativeSecurityXMLLen.intValue());
            derivativeSecurityXML = dataTag.value;
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
        b.append("{DerivativeSecurityXML=");
        printTagValue(b, TagNum.DerivativeSecurityXMLLen, derivativeSecurityXMLLen);
        printTagValue(b, TagNum.DerivativeSecurityXML, derivativeSecurityXML);
        printTagValue(b, TagNum.DerivativeSecurityXMLSchema, derivativeSecurityXMLSchema);
        b.append("}");

        return b.toString();
    }

    // </editor-fold>
}
