/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * SecurityXML50SP2.java
 *
 * $Id: SecurityXML50SP2.java,v 1.8 2011-09-19 08:15:45 vrotaru Exp $
 */
package net.hades.fix.message.comp.impl.v50sp2;

import net.hades.fix.message.FragmentContext;

import java.util.HashSet;
import java.util.Set;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.w3c.dom.Element;

import net.hades.fix.message.comp.SecurityXML;

/**
 * FIX 5.0SP2 implementation of SecurityXML component.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.8 $
 * @created 02/01/2009, 1:16:11 PM
 */
@XmlRootElement(name="SecXML")
@XmlType
@XmlAccessorType(XmlAccessType.NONE)
public class SecurityXML50SP2 extends SecurityXML {

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

    public SecurityXML50SP2() {
        super();
    }

    public SecurityXML50SP2(FragmentContext context) {
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
    public Integer getSecurityXMLLen() {
        return securityXMLLen;
    }

    @Override
    public void setSecurityXMLLen(Integer securityXMLLen) {
        this.securityXMLLen = securityXMLLen;
    }

    @Override
    public byte[] getSecurityXML() {
        return securityXML;
    }

    @Override
    public void setSecurityXML(byte[] securityXML) {
        this.securityXML = securityXML;
        if (securityXML != null) {
            if (securityXMLLen == null) {
                securityXMLLen = new Integer(securityXML.length);
            }
            securityXMLDoc = setXMLDocument(securityXML);
        }
    }

    @XmlAttribute(name = "Schema")
    @Override
    public String getSecurityXMLSchema() {
        return securityXMLSchema;
    }

    @Override
    public void setSecurityXMLSchema(String securityXMLSchema) {
        this.securityXMLSchema = securityXMLSchema;
    }

    @XmlAnyElement()
    @Override
    public Element getSecurityXMLDoc() {
        return securityXMLDoc;
    }

    @Override
    public void setSecurityXMLDoc(Element securityXMLDoc) {
        this.securityXMLDoc = securityXMLDoc;
        if (securityXMLDoc != null) {
            securityXML = setXMLByteArray(securityXMLDoc);
            if (securityXMLLen == null) {
                securityXMLLen = new Integer(securityXML.length);
            }
        }
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

    @Override
    protected String getUnsupportedTagMessage() {
        return "This tag is not supported in [SecurityXML] component version [5.0SP2].";
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Package Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Private Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Inner Classes">
    // </editor-fold>
}
