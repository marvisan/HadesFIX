/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * SecTypesGroup44.java
 *
 * $Id: SecTypesGroup44.java,v 1.1 2011-04-27 01:09:57 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v44;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.group.SecTypesGroup;
import net.hades.fix.message.type.Product;
import net.hades.fix.message.type.TagNum;
import net.hades.fix.message.util.MsgUtil;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Set;
import java.util.logging.Level;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.util.TagEncoder;

/**
 * FIX 4.4 implementation of SecTypesGroup group.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 21/02/2009, 4:58:16 PM
 */
@XmlRootElement(name="SecT")
@XmlType
@XmlAccessorType(XmlAccessType.NONE)
public class SecTypesGroup44 extends SecTypesGroup {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">

    protected Set<Integer> STANDARD_SECURED_TAGS = TAGS;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public SecTypesGroup44() {
        SECURED_TAGS = STANDARD_SECURED_TAGS;
    }

    public SecTypesGroup44(FragmentContext context) {
        super(context);
        SECURED_TAGS = STANDARD_SECURED_TAGS;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Public Methods">

    @XmlAttribute(name = "SecTyp")
    @Override
    public String getSecurityType() {
        return securityType;
    }

    @Override
    public void setSecurityType(String securityType) {
        this.securityType = securityType;
    }

    @XmlAttribute(name = "SubTyp")
    @Override
    public String getSecuritySubType() {
        return securitySubType;
    }

    @Override
    public void setSecuritySubType(String securitySubType) {
        this.securitySubType = securitySubType;
    }

    @XmlAttribute(name = "Prod")
    @Override
    public Product getProduct() {
        return product;
    }

    @Override
    public void setProduct(Product product) {
        this.product = product;
    }

    @XmlAttribute(name = "CFI")
    @Override
    public String getCfiCode() {
        return cfiCode;
    }

    @Override
    public void setCfiCode(String cfiCode) {
        this.cfiCode = cfiCode;
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
            if (MsgUtil.isTagInList(TagNum.SecurityType, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.SecurityType, securityType);
            }
            if (MsgUtil.isTagInList(TagNum.SecuritySubType, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.SecuritySubType, securitySubType);
            }
            if (product != null && MsgUtil.isTagInList(TagNum.Product, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.Product, product.getValue());
            }
            if (MsgUtil.isTagInList(TagNum.CFICode, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.CFICode, cfiCode, sessionCharset);
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
        return "This tag is not supported in [SecTypesGroup] group version [4.4].";
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Package Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Private Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Inner Classes">
    // </editor-fold>
}
