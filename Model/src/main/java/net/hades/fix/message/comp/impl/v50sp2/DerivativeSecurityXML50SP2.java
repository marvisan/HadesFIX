/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * DerivativeSecurityXML50SP2.java
 *
 * $Id: DerivativeSecurityXML50SP2.java,v 1.1 2011-09-19 08:15:45 vrotaru Exp $
 */
package net.hades.fix.message.comp.impl.v50sp2;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.comp.DerivativeSecurityXML;

import java.util.HashSet;
import java.util.Set;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.w3c.dom.Element;

/**
 * FIX 5.0SP2 implementation of DerivativeSecurityXML component.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 02/01/2009, 1:16:11 PM
 */
@XmlRootElement(name="SecXML")
@XmlType
@XmlAccessorType(XmlAccessType.NONE)
public class DerivativeSecurityXML50SP2 extends DerivativeSecurityXML {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> ALL_TAGS;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">

    static {
        ALL_TAGS = new HashSet<Integer>(TAGS);
        ALL_TAGS.addAll(START_DATA_TAGS);
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public DerivativeSecurityXML50SP2() {
        super();
    }

    public DerivativeSecurityXML50SP2(FragmentContext context) {
        super(context);
    }

   // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Public Methods">

    @Override
    public Set<Integer> getFragmentAllTags() {
        return ALL_TAGS;
    }

    // ACCESSOR METHODS
    //////////////////////////////////////////

    @Override
    public Integer getDerivativeSecurityXMLLen() {
        return derivativeSecurityXMLLen;
    }

    @Override
    public void setDerivativeSecurityXMLLen(Integer derivativeSecurityXMLLen) {
        this.derivativeSecurityXMLLen = derivativeSecurityXMLLen;
    }

    @Override
    public byte[] getDerivativeSecurityXML() {
        return derivativeSecurityXML;
    }

    @Override
    public void setDerivativeSecurityXML(byte[] derivativeSecurityXML) {
        this.derivativeSecurityXML = derivativeSecurityXML;
        if (derivativeSecurityXML != null) {
            if (derivativeSecurityXMLLen == null) {
                derivativeSecurityXMLLen = new Integer(derivativeSecurityXML.length);
            }
            derivativeSecurityXMLDoc = setXMLDocument(derivativeSecurityXML);
        }
    }

    @XmlAttribute(name = "Schema")
    @Override
    public String getDerivativeSecurityXMLSchema() {
        return derivativeSecurityXMLSchema;
    }

    @Override
    public void setDerivativeSecurityXMLSchema(String derivativeSecurityXMLSchema) {
        this.derivativeSecurityXMLSchema = derivativeSecurityXMLSchema;
    }

    @XmlAnyElement()
    @Override
    public Element getDerivativeSecurityXMLDoc() {
        return derivativeSecurityXMLDoc;
    }

    @Override
    public void setDerivativeSecurityXMLDoc(Element derivativeSecurityXMLDoc) {
        this.derivativeSecurityXMLDoc = derivativeSecurityXMLDoc;
        if (derivativeSecurityXMLDoc != null) {
            derivativeSecurityXML = setXMLByteArray(derivativeSecurityXMLDoc);
            if (derivativeSecurityXMLLen == null) {
                derivativeSecurityXMLLen = new Integer(derivativeSecurityXML.length);
            }
        }
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

    @Override
    protected String getUnsupportedTagMessage() {
        return "This tag is not supported in [DerivativeSecurityXML] component version [5.0SP2].";
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Package Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Private Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Inner Classes">
    // </editor-fold>
}
