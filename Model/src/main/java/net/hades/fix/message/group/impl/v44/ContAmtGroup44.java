/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * ContAmtGroup44.java
 *
 * $Id: ContAmtGroup44.java,v 1.2 2011-01-12 11:33:57 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v44;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.type.ContAmtType;
import net.hades.fix.message.type.Currency;
import net.hades.fix.message.type.TagNum;
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
import net.hades.fix.message.group.ContAmtGroup;
import net.hades.fix.message.util.TagEncoder;

/**
 * FIX 4.4 implementation of ContAmtGroup group.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 * @created 05/04/2009, 11:39:24 AM
 */
@XmlRootElement(name="ContAmt")
@XmlType
@XmlAccessorType(XmlAccessType.NONE)
public class ContAmtGroup44 extends ContAmtGroup {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

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

    public ContAmtGroup44() {
        SECURED_TAGS = STANDARD_SECURED_TAGS;
    }

    public ContAmtGroup44(FragmentContext context) {
        super(context);
        SECURED_TAGS = STANDARD_SECURED_TAGS;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Public Methods">

    @Override
    public Set<Integer> getFragmentAllTags() {
        return ALL_TAGS;
    }

    @XmlAttribute(name = "ContAmtTyp")
    @Override
    public ContAmtType getContAmtType() {
        return contAmtType;
    }

    @Override
    public void setContAmtType(ContAmtType contAmtType) {
        this.contAmtType = contAmtType;
    }

    @XmlAttribute(name = "ContAmtValu")
    @Override
    public Double getContAmtValue() {
        return contAmtValue;
    }

    @Override
    public void setContAmtValue(Double contAmtValue) {
        this.contAmtValue = contAmtValue;
    }

    @XmlAttribute(name = "ContAmtCurr")
    @Override
    public Currency getContAmtCurr() {
        return contAmtCurr;
    }

    @Override
    public void setContAmtCurr(Currency contAmtCurr) {
        this.contAmtCurr = contAmtCurr;
    }

    // ACCESSORS
    //////////////////////////////////////////

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
            if (contAmtType != null && MsgUtil.isTagInList(TagNum.ContAmtType, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.ContAmtType, contAmtType.getValue());
            }
            if (MsgUtil.isTagInList(TagNum.ContAmtValue, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.ContAmtValue, contAmtValue);
            }
            if (contAmtCurr != null && MsgUtil.isTagInList(TagNum.ContAmtCurr, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.ContAmtCurr, contAmtCurr.getValue());
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
        return "This tag is not supported in [ContAmtGroup] group version [4.4].";
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Package Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Private Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Inner Classes">
    // </editor-fold>
}
