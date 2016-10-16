/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * PegInstructions44.java
 *
 * $Id: PegInstructions44.java,v 1.1 2010-12-05 08:13:28 vrotaru Exp $
 */
package net.hades.fix.message.comp.impl.v44;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.comp.PegInstructions;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.struct.Tag;
import net.hades.fix.message.type.PegLimitType;
import net.hades.fix.message.type.PegMoveType;
import net.hades.fix.message.type.PegOffsetType;
import net.hades.fix.message.type.TagNum;
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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import net.hades.fix.message.type.PegRoundDirection;
import net.hades.fix.message.type.PegScope;
import net.hades.fix.message.util.TagEncoder;

/**
 * FIX 4.4 implementation of PegInstructions component.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 11/02/2009, 8:31:52 PM
 */
@XmlRootElement(name="PegInstr")
@XmlType
@XmlAccessorType(XmlAccessType.NONE)
public class PegInstructions44 extends PegInstructions {

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

    public PegInstructions44() {
        SECURED_TAGS = STANDARD_SECURED_TAGS;
    }

    public PegInstructions44(FragmentContext context) {
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

    @XmlAttribute(name = "OfstVal")
    @Override
    public Double getPegOffsetValue() {
        return pegOffsetValue;
    }

    @Override
    public void setPegOffsetValue(Double pegOffsetValue) {
        this.pegOffsetValue = pegOffsetValue;
    }

    @XmlAttribute(name = "MoveTyp")
    @Override
    public PegMoveType getPegMoveType() {
        return pegMoveType;
    }

    @Override
    public void setPegMoveType(PegMoveType pegMoveType) {
        this.pegMoveType = pegMoveType;
    }

    @XmlAttribute(name = "OfstTyp")
    @Override
    public PegOffsetType getPegOffsetType() {
        return pegOffsetType;
    }

    @Override
    public void setPegOffsetType(PegOffsetType pegOffsetType) {
        this.pegOffsetType = pegOffsetType;
    }

    @XmlAttribute(name = "LmtTyp")
    @Override
    public PegLimitType getPegLimitType() {
        return pegLimitType;
    }

    @Override
    public void setPegLimitType(PegLimitType pegLimitType) {
        this.pegLimitType = pegLimitType;
    }

    @XmlAttribute(name = "RndDir")
    @Override
    public PegRoundDirection getPegRoundDirection() {
        return pegRoundDirection;
    }

    @Override
    public void setPegRoundDirection(PegRoundDirection pegRoundDirection) {
        this.pegRoundDirection = pegRoundDirection;
    }

    @XmlAttribute(name = "Scope")
    @Override
    public PegScope getPegScope() {
        return pegScope;
    }

    @Override
    public void setPegScope(PegScope pegScope) {
        this.pegScope = pegScope;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

    @Override
    protected byte[] encodeFragmentSecured(boolean secured) throws TagNotPresentException, BadFormatMsgException {
        byte[] result = new byte[0];
        ByteArrayOutputStream bao = new ByteArrayOutputStream();

        try {
            if (MsgUtil.isTagInList(TagNum.PegOffsetValue, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.PegOffsetValue, pegOffsetValue);
            }
            if (pegMoveType != null && MsgUtil.isTagInList(TagNum.PegMoveType, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.PegMoveType, pegMoveType.getValue());
            }
            if (pegOffsetType != null && MsgUtil.isTagInList(TagNum.PegOffsetType, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.PegOffsetType, pegOffsetType.getValue());
            }
            if (pegLimitType != null && MsgUtil.isTagInList(TagNum.PegLimitType, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.PegLimitType, pegLimitType.getValue());
            }
            if (pegRoundDirection != null && MsgUtil.isTagInList(TagNum.PegRoundDirection, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.PegRoundDirection, pegRoundDirection.getValue());
            }
            if (pegScope != null && MsgUtil.isTagInList(TagNum.PegScope, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.PegScope, pegScope.getValue());
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
        return "This tag is not supported in [PegInstructions] component version [4.4].";
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
