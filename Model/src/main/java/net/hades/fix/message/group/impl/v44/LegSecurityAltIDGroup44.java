/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * LegSecurityAltIDGroup44.java
 *
 * $Id: LegSecurityAltIDGroup44.java,v 1.4 2010-08-25 05:30:25 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v44;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.type.TagNum;
import net.hades.fix.message.util.MsgUtil;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.logging.Level;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.group.LegSecurityAltIDGroup;
import net.hades.fix.message.util.TagEncoder;

/**
 * FIX 4.4 implementation of LegSecurityAltIDGroup group.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.4 $
 * @created 02/01/2009, 4:12:30 PM
 */
@XmlRootElement(name = "LegAID")
@XmlType
@XmlAccessorType(XmlAccessType.NONE)
public class LegSecurityAltIDGroup44 extends LegSecurityAltIDGroup {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = -7848848642421131947L;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public LegSecurityAltIDGroup44() {
        SECURED_TAGS = STANDARD_SECURED_TAGS;
    }

    public LegSecurityAltIDGroup44(FragmentContext context) {
        super(context);
        SECURED_TAGS = STANDARD_SECURED_TAGS;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Public Methods">

    @XmlAttribute(name = "SecAltID")
    @Override
    public String getLegSecurityAltID() {
        return legSecurityAltID;
    }

    @Override
    public void setLegSecurityAltID(String legSecurityAltID) {
        this.legSecurityAltID = legSecurityAltID;
    }

    @XmlAttribute(name = "SecAltIDSrc")
    @Override
    public String getLegSecurityAltIDSource() {
        return legSecurityAltIDSource;
    }

    @Override
    public void setLegSecurityAltIDSource(String legSecurityAltIDSource) {
        this.legSecurityAltIDSource = legSecurityAltIDSource;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

    @Override
    protected byte[] encodeFragmentSecured(boolean secured) throws TagNotPresentException, BadFormatMsgException {
        byte[] result = new byte[0];
        ByteArrayOutputStream bao = new ByteArrayOutputStream();

        try {
            if (MsgUtil.isTagInList(TagNum.LegSecurityAltID, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.LegSecurityAltID, legSecurityAltID);
            }
            if (MsgUtil.isTagInList(TagNum.LegSecurityAltIDSource, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.LegSecurityAltIDSource, legSecurityAltIDSource);
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
        return "This tag is not supported in [LegSecurityAltIDGroup] component release [4.4].";
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Package Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Private Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Inner Classes">
    // </editor-fold>
}
