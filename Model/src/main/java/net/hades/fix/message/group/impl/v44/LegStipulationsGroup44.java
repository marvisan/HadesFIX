/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * LegStipulationsGroup44.java
 *
 * $Id: LegStipulationsGroup44.java,v 1.6 2010-11-23 10:20:19 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v44;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.group.LegStipulationsGroup;
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
import net.hades.fix.message.util.TagEncoder;

/**
 * FIX 4.4 implementation of LegStipulationsGroup group.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.6 $
 * @created 15/02/2009, 6:23:35 PM
 */
@XmlRootElement(name="Stip")
@XmlType
@XmlAccessorType(XmlAccessType.NONE)
public class LegStipulationsGroup44 extends LegStipulationsGroup {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public LegStipulationsGroup44() {
        SECURED_TAGS = STANDARD_SECURED_TAGS;
    }

    public LegStipulationsGroup44(FragmentContext context) {
        super(context);
        SECURED_TAGS = STANDARD_SECURED_TAGS;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Public Methods">

    @XmlAttribute(name = "StipTyp")
    @Override
    public String getLegStipulationType() {
        return legStipulationType;
    }

    @Override
    public void setLegStipulationType(String legStipulationType) {
        this.legStipulationType = legStipulationType;
    }

    @XmlAttribute(name = "StipVal")
    @Override
    public String getLegStipulationValue() {
        return legStipulationValue;
    }

    @Override
    public void setLegStipulationValue(String legStipulationValue) {
        this.legStipulationValue = legStipulationValue;
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
            if (MsgUtil.isTagInList(TagNum.LegStipulationType, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.LegStipulationType, legStipulationType);
            }
            if (MsgUtil.isTagInList(TagNum.LegStipulationValue, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.LegStipulationValue, legStipulationValue);
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
        return "This tag is not supported in [LegStipulationsGroup] group release [4.4].";
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Package Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Private Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Inner Classes">
    // </editor-fold>
}
