/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * SecurityAltIDGroup43.java
 *
 * $Id: SecurityAltIDGroup43.java,v 1.3 2010-08-25 05:30:26 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v43;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.group.SecurityAltIDGroup;
import net.hades.fix.message.type.TagNum;
import net.hades.fix.message.util.MsgUtil;
import net.hades.fix.message.xml.codec.jaxb.type.SingleStringAttrSimpleType;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.logging.Level;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.util.TagEncoder;

/**
 * FIX 4.3 implementation of SecurityAltIDGroup group.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.3 $
 * @created 02/01/2009, 4:26:53 PM
 */
@XmlRootElement(name = "SecurityAltIDGroup")
@XmlType(propOrder = {"securityAltID", "securityAltIDSourceType"})
@XmlAccessorType(XmlAccessType.NONE)
public class SecurityAltIDGroup43 extends SecurityAltIDGroup {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = -1132352197809644455L;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">

    @XmlElement(name = "SecurityAltIDSource")
    private SecurityAltIDSourceType securityAltIDSourceType;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public SecurityAltIDGroup43() {
        SECURED_TAGS = STANDARD_SECURED_TAGS;
    }

    public SecurityAltIDGroup43(FragmentContext context) {
        super(context);
        SECURED_TAGS = STANDARD_SECURED_TAGS;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Public Methods">

    // ACCESSOR METHODS
    //////////////////////////////////////////

    @XmlElement(name = "SecurityAltID")
    @Override
    public String getSecurityAltID() {
        return securityAltID;
    }

    @Override
    public void setSecurityAltID(String securityAltID) {
        this.securityAltID = securityAltID;
    }

    @Override
    public String getSecurityAltIDSource() {
        if (securityAltIDSourceType != null) {
            return securityAltIDSourceType.getValue();
        } else {
            return securityAltIDSource;
        }
    }

    @Override
    public void setSecurityAltIDSource(String securityAltIDSource) {
        this.securityAltIDSource = securityAltIDSource;
        if (securityAltIDSourceType == null) {
            securityAltIDSourceType = new SecurityAltIDSourceType();
        }
        securityAltIDSourceType.setValue(securityAltIDSource);
    }

    public SecurityAltIDSourceType getSecurityAltIDSourceType() {
        return securityAltIDSourceType;
    }

    public void setSecurityAltIDSourceType(SecurityAltIDSourceType securityAltIDSourceType) {
        this.securityAltIDSourceType = securityAltIDSourceType;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

    @Override
    protected byte[] encodeFragmentSecured(boolean secured) throws TagNotPresentException, BadFormatMsgException {
        byte[] result = new byte[0];
        ByteArrayOutputStream bao = new ByteArrayOutputStream();
        try {
            if (MsgUtil.isTagInList(TagNum.SecurityAltID, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.SecurityAltID, securityAltID);
            }
            if (MsgUtil.isTagInList(TagNum.SecurityAltIDSource, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.SecurityAltIDSource, securityAltIDSource);
            }
            result = bao.toByteArray();
        } catch (IOException ex) {
            String error = "Error writing to the byte array.";
            LOGGER.log(Level.SEVERE, "{0} Error was : {1}", new Object[] { error, ex.toString() });
            throw new BadFormatMsgException(error, ex);
        }

        return result;
    }

    @Override
    protected String getUnsupportedTagMessage() {
        return "This tag is not supported in [SecurityAltIDGroup] group version [4.3].";
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Package Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Private Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Inner Classes">

    @XmlRootElement(name = "SecurityAltIDSource")
    @XmlType
    @XmlAccessorType(XmlAccessType.NONE)
    public static class SecurityAltIDSourceType extends SingleStringAttrSimpleType {
    }

    // </editor-fold>
}
