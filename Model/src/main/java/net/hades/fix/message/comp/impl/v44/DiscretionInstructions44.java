/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * DiscretionInstructions44.java
 *
 * $Id: DiscretionInstructions44.java,v 1.1 2010-12-05 08:13:28 vrotaru Exp $
 */
package net.hades.fix.message.comp.impl.v44;

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
import net.hades.fix.message.comp.DiscretionInstructions;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.type.DiscretionInst;
import net.hades.fix.message.type.DiscretionLimitType;
import net.hades.fix.message.type.DiscretionMoveType;
import net.hades.fix.message.type.DiscretionOffsetType;
import net.hades.fix.message.type.DiscretionRoundDirection;
import net.hades.fix.message.type.DiscretionScope;
import net.hades.fix.message.type.TagNum;
import net.hades.fix.message.util.MsgUtil;
import net.hades.fix.message.util.TagEncoder;

/**
 * FIX 4.4 implementation of DiscretionInstructions component.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 11/02/2009, 8:31:52 PM
 */
@XmlRootElement(name="DiscInstr")
@XmlType
@XmlAccessorType(XmlAccessType.NONE)
public class DiscretionInstructions44 extends DiscretionInstructions {

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

    public DiscretionInstructions44() {
        SECURED_TAGS = STANDARD_SECURED_TAGS;
    }

    public DiscretionInstructions44(FragmentContext context) {
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

    @XmlAttribute(name = "DsctnInst")
    @Override
    public DiscretionInst getDiscretionInst() {
        return discretionInst;
    }

    @Override
    public void setDiscretionInst(DiscretionInst discretionInst) {
        this.discretionInst = discretionInst;
    }

    @XmlAttribute(name = "OfstValu")
    @Override
    public Double getDiscretionOffsetValue() {
        return discretionOffsetValue;
    }

    @Override
    public void setDiscretionOffsetValue(Double discretionOffsetValue) {
        this.discretionOffsetValue = discretionOffsetValue;
    }

    @XmlAttribute(name = "MoveTyp")
    @Override
    public DiscretionMoveType getDiscretionMoveType() {
        return discretionMoveType;
    }

    @Override
    public void setDiscretionMoveType(DiscretionMoveType discretionMoveType) {
        this.discretionMoveType = discretionMoveType;
    }

    @XmlAttribute(name = "OfstTyp")
    @Override
    public DiscretionOffsetType getDiscretionOffsetType() {
        return discretionOffsetType;
    }

    @Override
    public void setDiscretionOffsetType(DiscretionOffsetType discretionOffsetType) {
        this.discretionOffsetType = discretionOffsetType;
    }

    @XmlAttribute(name = "LimitTyp")
    @Override
    public DiscretionLimitType getDiscretionLimitType() {
        return discretionLimitType;
    }

    @Override
    public void setDiscretionLimitType(DiscretionLimitType discretionLimitType) {
        this.discretionLimitType = discretionLimitType;
    }

    @XmlAttribute(name = "RndDir")
    @Override
    public DiscretionRoundDirection getDiscretionRoundDirection() {
        return discretionRoundDirection;
    }

    @Override
    public void setDiscretionRoundDirection(DiscretionRoundDirection discretionRoundDirection) {
        this.discretionRoundDirection = discretionRoundDirection;
    }

    @XmlAttribute(name = "Scope")
    @Override
    public DiscretionScope getDiscretionScope() {
        return discretionScope;
    }

    @Override
    public void setDiscretionScope(DiscretionScope discretionScope) {
        this.discretionScope = discretionScope;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

    @Override
    protected byte[] encodeFragmentSecured(boolean secured) throws TagNotPresentException, BadFormatMsgException {
        byte[] result = new byte[0];
        ByteArrayOutputStream bao = new ByteArrayOutputStream();

        try {
            if (discretionInst != null && MsgUtil.isTagInList(TagNum.DiscretionInst, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.DiscretionInst, discretionInst.getValue());
            }
            if (MsgUtil.isTagInList(TagNum.DiscretionOffsetValue, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.DiscretionOffsetValue, discretionOffsetValue);
            }
            if (discretionMoveType != null && MsgUtil.isTagInList(TagNum.DiscretionMoveType, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.DiscretionMoveType, discretionMoveType.getValue());
            }
            if (discretionOffsetType != null && MsgUtil.isTagInList(TagNum.DiscretionOffsetType, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.DiscretionOffsetType, discretionOffsetType.getValue());
            }
            if (discretionLimitType != null && MsgUtil.isTagInList(TagNum.DiscretionLimitType, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.DiscretionLimitType, discretionLimitType.getValue());
            }
            if (discretionRoundDirection != null && MsgUtil.isTagInList(TagNum.DiscretionRoundDirection, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.DiscretionRoundDirection, discretionRoundDirection.getValue());
            }
            if (discretionScope != null && MsgUtil.isTagInList(TagNum.DiscretionScope, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.DiscretionScope, discretionScope.getValue());
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
    protected String getUnsupportedTagMessage() {
        return "This tag is not supported in [DiscretionInstructions] component version [4.4].";
    }

    @Override
    protected Set<Integer> getFragmentCompTags() {
        return START_COMP_TAGS;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Package Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Private Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Inner Classes">
    // </editor-fold>
}
