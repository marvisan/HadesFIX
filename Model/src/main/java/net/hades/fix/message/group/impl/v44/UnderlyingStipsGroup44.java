/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * UnderlyingStipsGroup50.java
 *
 * $Id: UnderlyingStipsGroup44.java,v 1.6 2010-11-23 10:20:19 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v44;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.logging.Level;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.group.UnderlyingStipsGroup;
import net.hades.fix.message.type.TagNum;
import net.hades.fix.message.util.MsgUtil;
import net.hades.fix.message.util.TagEncoder;

/**
 * FIX 4.4 implementation of UnderlyingStipsGroup.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.6 $
 * @created 28/12/2008, 11:57:16 AM
 */
@XmlRootElement(name="Stip")
@XmlType
@XmlAccessorType(XmlAccessType.NONE)
public class UnderlyingStipsGroup44 extends UnderlyingStipsGroup {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public UnderlyingStipsGroup44() {
        SECURED_TAGS = STANDARD_SECURED_TAGS;
    }

    public UnderlyingStipsGroup44(FragmentContext context) {
        super(context);
        SECURED_TAGS = STANDARD_SECURED_TAGS;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Public Methods">

    @XmlAttribute(name = "Typ")
    @Override
    public String getUnderlyingStipType() {
        return underlyingStipType;
    }

    @Override
    public void setUnderlyingStipType(String underlyingStipType) {
        this.underlyingStipType = underlyingStipType;
    }

    @XmlAttribute(name = "Val")
    @Override
    public String getUnderlyingStipValue() {
        return underlyingStipValue;
    }

    @Override
    public void setUnderlyingStipValue(String underlyingStipValue) {
        this.underlyingStipValue = underlyingStipValue;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

    @Override
    protected byte[] encodeFragmentSecured(boolean secured) throws TagNotPresentException, BadFormatMsgException {
        if (validateRequired) {
            validateRequiredTags();
        }
        byte[] result = new byte[0];
        ByteArrayOutputStream bao = new ByteArrayOutputStream();

        try {
            if (MsgUtil.isTagInList(TagNum.UnderlyingStipType, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.UnderlyingStipType, underlyingStipType);
            }
            if (MsgUtil.isTagInList(TagNum.UnderlyingStipValue, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.UnderlyingStipValue, underlyingStipValue);
            }
            result = bao.toByteArray();
        } catch (IOException ex) {
            String error = "Error writing to the byte array.";
            LOGGER.log(Level.SEVERE, "{0} Error was : {1}", new Object[] { error, ex.toString() });
            throw new BadFormatMsgException(error, ex);
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
}
