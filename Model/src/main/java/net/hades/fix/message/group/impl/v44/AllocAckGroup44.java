/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * AllocAckGroup44.java
 *
 * $Id: AllocAckGroup44.java,v 1.1 2011-02-13 04:40:41 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v44;

import net.hades.fix.message.struct.Tag;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.group.AllocAckGroup;
import net.hades.fix.message.type.AcctIDSource;
import net.hades.fix.message.type.IndividualAllocRejCode;
import net.hades.fix.message.type.TagNum;
import net.hades.fix.message.util.MsgUtil;
import net.hades.fix.message.util.TagEncoder;

/**
 * FIX 4.4 implementation of AllocAckGroup group.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 12/02/2009, 7:22:35 PM
 */
@XmlRootElement(name="AllocAck")
@XmlType
@XmlAccessorType(XmlAccessType.NONE)
public class AllocAckGroup44 extends AllocAckGroup {
    
    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> ALL_TAGS;

    protected static final Set<Integer> START_COMP_TAGS = null;

    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Static Block">
    
    static {
        ALL_TAGS = new HashSet<Integer>(TAGS);
        ALL_TAGS.addAll(START_DATA_TAGS);
    }
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Attributes">
    
    protected Set<Integer> STANDARD_SECURED_TAGS = ALL_TAGS;
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Constructors">
    
    public AllocAckGroup44() {
        SECURED_TAGS = STANDARD_SECURED_TAGS;
    }
    
    public AllocAckGroup44(FragmentContext context) {
        super(context);
        SECURED_TAGS = STANDARD_SECURED_TAGS;
    }
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Public Methods">

    @Override
    public Set<Integer> getFragmentTags() {
        return TAGS;
    }

    @Override
    public Set<Integer> getFragmentAllTags() {
        return ALL_TAGS;
    }

    // ACCESSOR METHODS
    //////////////////////////////////////////

    @XmlAttribute(name = "Acct")
    @Override
    public String getAllocAccount() {
        return allocAccount;
    }

    @Override
    public void setAllocAccount(String allocAccount) {
        this.allocAccount = allocAccount;
    }

    @XmlAttribute(name = "ActIDSrc")
    @Override
    public AcctIDSource getAllocAcctIDSource() {
        return allocAcctIDSource;
    }

    @Override
    public void setAllocAcctIDSource(AcctIDSource allocAcctIDSource) {
        this.allocAcctIDSource = allocAcctIDSource;
    }

    @XmlAttribute(name = "Px")
    @Override
    public Double getAllocPrice() {
        return allocPrice;
    }

    @Override
    public void setAllocPrice(Double allocPrice) {
        this.allocPrice = allocPrice;
    }

    @XmlAttribute(name = "IndAllocID")
    @Override
    public String getIndividualAllocID() {
        return individualAllocID;
    }

    @Override
    public void setIndividualAllocID(String individualAllocID) {
        this.individualAllocID = individualAllocID;
    }

    @XmlAttribute(name = "IndAllocRejCode")
    @Override
    public IndividualAllocRejCode getIndividualAllocRejCode() {
        return individualAllocRejCode;
    }

    @Override
    public void setIndividualAllocRejCode(IndividualAllocRejCode individualAllocRejCode) {
        this.individualAllocRejCode = individualAllocRejCode;
    }

    @XmlAttribute(name = "Txt")
    @Override
    public String getAllocText() {
        return allocText;
    }

    @Override
    public void setAllocText(String allocText) {
        this.allocText = allocText;
    }

    @XmlAttribute(name = "EncAllocTextLen")
    @Override
    public Integer getEncodedAllocTextLen() {
        return encodedAllocTextLen;
    }

    @Override
    public void setEncodedAllocTextLen(Integer encodedAllocTextLen) {
        this.encodedAllocTextLen = encodedAllocTextLen;
    }

    @XmlAttribute(name = "EncAllocText")
    @Override
    public byte[] getEncodedAllocText() {
        return encodedAllocText;
    }

    @Override
    public void setEncodedAllocText(byte[] encodedAllocText) {
        this.encodedAllocText = encodedAllocText;
        if (encodedAllocTextLen == null) {
            encodedAllocTextLen = new Integer(encodedAllocText.length);
        }
    }

    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

    @Override
    protected byte[] encodeFragmentSecured(boolean secured) throws TagNotPresentException, BadFormatMsgException {
        byte[] result = new byte[0];
        ByteArrayOutputStream bao = new ByteArrayOutputStream();
        
        try {
            if (MsgUtil.isTagInList(TagNum.AllocAccount, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.AllocAccount, allocAccount);
            }
            if (allocAcctIDSource != null && MsgUtil.isTagInList(TagNum.AllocAcctIDSource, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.AllocAcctIDSource, allocAcctIDSource.getValue());
            }
            if (MsgUtil.isTagInList(TagNum.AllocPrice, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.AllocPrice, allocPrice);
            }
            if (MsgUtil.isTagInList(TagNum.IndividualAllocID, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.IndividualAllocID, individualAllocID);
            }
            if (individualAllocRejCode != null && MsgUtil.isTagInList(TagNum.IndividualAllocRejCode, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.IndividualAllocRejCode, individualAllocRejCode.getValue());
            }
            if (MsgUtil.isTagInList(TagNum.AllocText, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.AllocText, allocText);
            }
            if (encodedAllocTextLen != null && encodedAllocTextLen.intValue() > 0 && MsgUtil.isTagInList(TagNum.EncodedAllocTextLen, SECURED_TAGS, secured)) {
                if (encodedAllocText != null && encodedAllocText.length > 0) {
                    encodedAllocTextLen = new Integer(encodedAllocText.length);
                    TagEncoder.encode(bao, TagNum.EncodedAllocTextLen, encodedAllocTextLen);
                    TagEncoder.encode(bao, TagNum.EncodedText, encodedAllocText);
                }
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
    protected void setFragmentCompTagValue(Tag tag, ByteBuffer message)
    throws BadFormatMsgException, InvalidMsgException, TagNotPresentException {
    }

    @Override
    protected Set<Integer> getFragmentCompTags() {
        return START_COMP_TAGS;
    }

    @Override
    protected Set<Integer> getFragmentSecuredTags() {
        return SECURED_TAGS;
    }
    
    @Override
    protected String getUnsupportedTagMessage() {
        return "This tag is not supported in [AllocAckGroup] group version [4.4].";
    }
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Package Methods">
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Private Methods">
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Inner Classes">
    // </editor-fold>
}
