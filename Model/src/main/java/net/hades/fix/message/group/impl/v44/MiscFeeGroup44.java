/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * MiscFeeGroup44.java
 *
 * $Id: MiscFeeGroup44.java,v 1.1 2011-01-09 07:27:41 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v44;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.type.Currency;
import net.hades.fix.message.type.MiscFeeType;
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
import net.hades.fix.message.group.MiscFeeGroup;
import net.hades.fix.message.type.MiscFeeBasis;
import net.hades.fix.message.util.TagEncoder;

/**
 * FIX 4.4 implementation of MiscFeeGroup group.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 05/04/2009, 11:39:24 AM
 */
@XmlRootElement(name="MiscFees")
@XmlType
@XmlAccessorType(XmlAccessType.NONE)
public class MiscFeeGroup44 extends MiscFeeGroup {

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

    public MiscFeeGroup44() {
        SECURED_TAGS = STANDARD_SECURED_TAGS;
    }

    public MiscFeeGroup44(FragmentContext context) {
        super(context);
        SECURED_TAGS = STANDARD_SECURED_TAGS;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Public Methods">

    @Override
    public Set<Integer> getFragmentAllTags() {
        return ALL_TAGS;
    }

    @XmlAttribute(name = "Amt")
    @Override
    public Double getMiscFeeAmt() {
        return miscFeeAmt;
    }

    @Override
    public void setMiscFeeAmt(Double miscFeeAmt) {
        this.miscFeeAmt = miscFeeAmt;
    }

    @XmlAttribute(name = "Curr")
    @Override
    public Currency getMiscFeeCurr() {
        return miscFeeCurr;
    }

    @Override
    public void setMiscFeeCurr(Currency miscFeeCurr) {
        this.miscFeeCurr = miscFeeCurr;
    }

    @XmlAttribute(name = "Typ")
    @Override
    public MiscFeeType getMiscFeeType() {
        return miscFeeType;
    }

    @Override
    public void setMiscFeeType(MiscFeeType miscFeeType) {
        this.miscFeeType = miscFeeType;
    }

    @XmlAttribute(name = "Basis")
    @Override
    public MiscFeeBasis getMiscFeeBasis() {
        return miscFeeBasis;
    }

    @Override
    public void setMiscFeeBasis(MiscFeeBasis miscFeeBasis) {
        this.miscFeeBasis = miscFeeBasis;
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
            if (MsgUtil.isTagInList(TagNum.MiscFeeAmt, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.MiscFeeAmt, miscFeeAmt);
            }
            if (miscFeeCurr != null && MsgUtil.isTagInList(TagNum.MiscFeeCurr, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.MiscFeeCurr, miscFeeCurr.getValue());
            }
            if (miscFeeType != null && MsgUtil.isTagInList(TagNum.MiscFeeType, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.MiscFeeType, miscFeeType.getValue());
            }
            if (miscFeeBasis != null && MsgUtil.isTagInList(TagNum.MiscFeeBasis, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.MiscFeeBasis, miscFeeBasis.getValue());
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
        return "This tag is not supported in [MiscFeeGroup] group version [4.4].";
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Package Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Private Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Inner Classes">
    // </editor-fold>
}
