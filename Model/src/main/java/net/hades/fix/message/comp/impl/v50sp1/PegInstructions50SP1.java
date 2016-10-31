/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * PegInstructions50SP1.java
 *
 * $Id: PegInstructions50SP1.java,v 1.1 2010-12-05 08:13:28 vrotaru Exp $
 */
package net.hades.fix.message.comp.impl.v50sp1;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.struct.Tag;
import net.hades.fix.message.type.PegLimitType;
import net.hades.fix.message.type.PegMoveType;
import net.hades.fix.message.type.PegOffsetType;

import java.nio.ByteBuffer;
import java.util.HashSet;
import java.util.Set;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import net.hades.fix.message.comp.PegInstructions;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.type.PegPriceType;
import net.hades.fix.message.type.PegRoundDirection;
import net.hades.fix.message.type.PegScope;

/**
 * FIX 5.0SP1 implementation of PegInstructions component.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 11/02/2009, 8:31:52 PM
 */
@XmlRootElement(name="PegInstr")
@XmlType
@XmlAccessorType(XmlAccessType.NONE)
public class PegInstructions50SP1 extends PegInstructions {

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

    public PegInstructions50SP1() {
    }

    public PegInstructions50SP1(FragmentContext context) {
        super(context);
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

    @XmlAttribute(name = "PegPxTyp")
    @Override
    public PegPriceType getPegPriceType() {
        return pegPriceType;
    }

    @Override
    public void setPegPriceType(PegPriceType pegPriceType) {
        this.pegPriceType = pegPriceType;
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

    @XmlAttribute(name = "PegSecurityIDSource")
    @Override
    public String getPegSecurityIDSource() {
        return pegSecurityIDSource;
    }

    @Override
    public void setPegSecurityIDSource(String pegSecurityIDSource) {
        this.pegSecurityIDSource = pegSecurityIDSource;
    }

    @XmlAttribute(name = "PegSecID")
    @Override
    public String getPegSecurityID() {
        return pegSecurityID;
    }

    @Override
    public void setPegSecurityID(String pegSecurityID) {
        this.pegSecurityID = pegSecurityID;
    }

    @XmlAttribute(name = "PgSymbl")
    @Override
    public String getPegSymbol() {
        return pegSymbol;
    }

    @Override
    public void setPegSymbol(String pegSymbol) {
        this.pegSymbol = pegSymbol;
    }

    @XmlAttribute(name = "PegSecDesc")
    @Override
    public String getPegSecurityDesc() {
        return pegSecurityDesc;
    }

    @Override
    public void setPegSecurityDesc(String pegSecurityDesc) {
        this.pegSecurityDesc = pegSecurityDesc;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

    @Override
    protected void setFragmentCompTagValue(Tag tag, ByteBuffer message)
    throws BadFormatMsgException, InvalidMsgException, TagNotPresentException {
    }

    @Override
    protected String getUnsupportedTagMessage() {
        return "This tag is not supported in [PegInstructions] component version [5.0SP1].";
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
