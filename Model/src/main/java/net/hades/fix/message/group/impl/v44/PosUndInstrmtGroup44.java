/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * PosUndInstrmtGroup44.java
 *
 * $Id: PartyGroup44.java,v 1.12 2011-04-14 23:44:45 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v44;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.comp.impl.v44.UnderlyingInstrument44;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.struct.Tag;
import net.hades.fix.message.util.MsgUtil;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import net.hades.fix.message.comp.UnderlyingInstrument;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.group.PosUndInstrmtGroup;
import net.hades.fix.message.type.SettlPriceType;
import net.hades.fix.message.type.TagNum;
import net.hades.fix.message.util.TagEncoder;

/**
 * FIX 4.4 implementation of PosUndInstrmtGroup group.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.12 $
 * @created 12/12/2011, 7:22:35 PM
 */
@XmlRootElement(name="PosUnd")
@XmlType(propOrder={"underlyingInstrument"})
@XmlAccessorType(XmlAccessType.NONE)
public class PosUndInstrmtGroup44 extends PosUndInstrmtGroup {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> START_COMP_TAGS;

    protected static final Set<Integer> ALL_TAGS;

    protected static final Set<Integer> UND_INSTR_COMP_TAGS = new UnderlyingInstrument44().getFragmentAllTags();

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">

    static {
        ALL_TAGS = new HashSet<Integer>(TAGS);
        ALL_TAGS.addAll(UND_INSTR_COMP_TAGS);
        START_COMP_TAGS = new HashSet<Integer>(UND_INSTR_COMP_TAGS);
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">

    protected Set<Integer> STANDARD_SECURED_TAGS = ALL_TAGS;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public PosUndInstrmtGroup44() {
        SECURED_TAGS = STANDARD_SECURED_TAGS;
    }

    public PosUndInstrmtGroup44(FragmentContext context) {
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

    @XmlElementRef
    @Override
    public UnderlyingInstrument getUnderlyingInstrument() {
        return underlyingInstrument;
    }

    public void setUnderlyingInstrument(UnderlyingInstrument underlyingInstrument) {
        this.underlyingInstrument = underlyingInstrument;
    }

    @Override
    public void setUnderlyingInstrument() {
        FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired);
        this.underlyingInstrument = new UnderlyingInstrument44(context);
    }

    @Override
    public void clearUnderlyingInstrument() {
        this.underlyingInstrument = null;
    }

    @XmlAttribute(name = "UndSetPx")
    @Override
    public Double getUnderlyingSettlPrice() {
        return underlyingSettlPrice;
    }

    @Override
    public void setUnderlyingSettlPrice(Double underlyingSettlPrice) {
        this.underlyingSettlPrice = underlyingSettlPrice;
    }

    @XmlAttribute(name = "UndSetPxTyp")
    @Override
    public SettlPriceType getUnderlyingSettlPriceType() {
        return underlyingSettlPriceType;
    }
    
    @Override
    public void setUnderlyingSettlPriceType(SettlPriceType underlyingSettlPriceType) {
        this.underlyingSettlPriceType = underlyingSettlPriceType;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

    @Override
    protected byte[] encodeFragmentSecured(boolean secured) throws TagNotPresentException, BadFormatMsgException {
        byte[] result = new byte[0];
        ByteArrayOutputStream bao = new ByteArrayOutputStream();

        try {
            if (underlyingInstrument != null) {
                bao.write(underlyingInstrument.encode(getMsgSecureTypeForFlag(secured)));
            }
            if (MsgUtil.isTagInList(TagNum.UnderlyingSettlPrice, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.UnderlyingSettlPrice, underlyingSettlPrice);
            }
            if (underlyingSettlPriceType != null && MsgUtil.isTagInList(TagNum.UnderlyingSettlPriceType, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.UnderlyingSettlPriceType, underlyingSettlPriceType.getValue());
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
        FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired);
        if (UND_INSTR_COMP_TAGS.contains(tag.tagNum)) {
            if (underlyingInstrument == null) {
                underlyingInstrument = new UnderlyingInstrument44(context);
            }
            message.reset();
            underlyingInstrument.decode(message);
        }
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
        return "This tag is not supported in [PosUndInstrmtGroup] group version [4.4].";
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Package Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Private Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Inner Classes">
    // </editor-fold>
}
