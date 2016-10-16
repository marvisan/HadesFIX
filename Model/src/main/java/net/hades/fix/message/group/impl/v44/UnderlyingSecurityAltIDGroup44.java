/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * UnderlyingSecurityAltIDGroup44.java
 *
 * $Id: UnderlyingSecurityAltIDGroup44.java,v 1.7 2010-08-25 05:30:24 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v44;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.util.MsgUtil;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.group.UnderlyingSecurityAltIDGroup;
import net.hades.fix.message.type.TagNum;
import net.hades.fix.message.util.TagEncoder;

/**
 * UnderlyingSecurityAltIDGroup for FIX version 4.4.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.7 $
 * @created 16/12/2008, 8:34:35 PM
 */
@XmlRootElement(name="UndAID")
@XmlType
@XmlAccessorType(XmlAccessType.NONE)
public class UnderlyingSecurityAltIDGroup44 extends UnderlyingSecurityAltIDGroup {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = -5142156657809389689L;

    protected static final Set<Integer> ALL_TAGS;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">

    static {
        ALL_TAGS = new HashSet<Integer>(TAGS);
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">

    protected Set<Integer> STANDARD_SECURED_TAGS = TAGS;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public UnderlyingSecurityAltIDGroup44() {
        SECURED_TAGS = STANDARD_SECURED_TAGS;
    }

    public UnderlyingSecurityAltIDGroup44(FragmentContext context) {
        super(context);
        SECURED_TAGS = STANDARD_SECURED_TAGS;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Public Methods">

    @Override
    public Set<Integer> getFragmentAllTags() {
        return ALL_TAGS;
    }

    // ACCESSOR METHODS
    //////////////////////////////////////////

    @XmlAttribute(name = "AltID")
    @Override
    public String getUnderlyingSecurityAltID() {
        return underlyingSecurityAltID;
    }

    @Override
    public void setUnderlyingSecurityAltID(String underlyingSecurityAltID) {
        this.underlyingSecurityAltID = underlyingSecurityAltID;
    }

    @XmlAttribute(name = "AltIDSrc")
    @Override
    public String getUnderlyingSecurityAltIDSource() {
        return underlyingSecurityAltIDSource;
    }

    @Override
    public void setUnderlyingSecurityAltIDSource(String underlyingSecurityAltIDSource) {
        this.underlyingSecurityAltIDSource = underlyingSecurityAltIDSource;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

    @Override
    protected byte[] encodeFragmentSecured(boolean secured) throws TagNotPresentException, BadFormatMsgException {

        byte[] result = new byte[0];
        ByteArrayOutputStream bao = new ByteArrayOutputStream();
        try {
            if (MsgUtil.isTagInList(TagNum.UnderlyingSecurityAltID, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.UnderlyingSecurityAltID, underlyingSecurityAltID);
            }
            if (MsgUtil.isTagInList(TagNum.UnderlyingSecurityAltID, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.UnderlyingSecurityAltIDSource, underlyingSecurityAltIDSource);
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
    protected Set<Integer> getFragmentSecuredTags() {
        return SECURED_TAGS;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Package Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Private Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Inner Classes">
    // </editor-fold>
}
