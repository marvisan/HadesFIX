/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * FillExecGroup50SP1.java
 *
 * $Id: FillExecGroup50SP1.java,v 1.2 2011-04-14 23:44:53 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v50sp1;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.comp.NestedParties4;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.group.FillExecGroup;
import net.hades.fix.message.struct.Tag;
import net.hades.fix.message.type.FillLiquidityInd;

import java.nio.ByteBuffer;
import java.util.HashSet;
import java.util.Set;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import net.hades.fix.message.comp.impl.v50sp1.NestedParties450SP1;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.group.Nested4PartyGroup;

/**
 * FIX 5.0SP1 implementation of FillExecGroup group.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 * @created 29/04/2009, 6:46:57 PM
 */
@XmlRootElement(name = "FillsGrp")
@XmlType(propOrder = {"nested4PartyIDGroups"})
@XmlAccessorType(XmlAccessType.NONE)
public class FillExecGroup50SP1 extends FillExecGroup {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> ALL_TAGS;

    protected static final Set<Integer> START_COMP_TAGS;

    protected static final Set<Integer> NESTED_PARTIES4_COMP_TAGS = new NestedParties450SP1().getFragmentAllTags();

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">

    static {
        ALL_TAGS = new HashSet<Integer>(TAGS);
        ALL_TAGS.addAll(NESTED_PARTIES4_COMP_TAGS);
        START_COMP_TAGS = new HashSet<Integer>(NESTED_PARTIES4_COMP_TAGS);
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public FillExecGroup50SP1() {
        super();
    }

    public FillExecGroup50SP1(FragmentContext context) {
        super(context);
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Public Methods">

    @Override
    public Set<Integer> getFragmentAllTags() {
        return ALL_TAGS;
    }

    // ACCESSOR METHODS
    //////////////////////////////////////////

    @XmlAttribute(name = "FillExecID")
    @Override
    public String getFillExecID() {
        return fillExecID;
    }

    @Override
    public void setFillExecID(String fillExecID) {
        this.fillExecID = fillExecID;
    }

    @XmlAttribute(name = "FillPx")
    @Override
    public Double getFillPx() {
        return fillPx;
    }

    @Override
    public void setFillPx(Double fillPx) {
        this.fillPx = fillPx;
    }

    @XmlAttribute(name = "FillQty")
    @Override
    public Double getFillQty() {
        return fillQty;
    }

    @Override
    public void setFillQty(Double fillQty) {
        this.fillQty = fillQty;
    }

    @XmlAttribute(name = "LqdtyInd")
    @Override
    public FillLiquidityInd getFillLiquidityInd() {
        return fillLiquidityInd;
    }

    @Override
    public void setFillLiquidityInd(FillLiquidityInd fillLiquidityInd) {
        this.fillLiquidityInd = fillLiquidityInd;
    }

    @Override
    public NestedParties4 getNestedParties4() {
        return nestedParties4;
    }

    @Override
    public void setNestedParties4() {
        this.nestedParties4 = new NestedParties450SP1(new FragmentContext(sessionCharset, messageEncoding, validateRequired));
    }

    @Override
    public void clearNestedParties4() {
        this.nestedParties4 = null;
    }

    @XmlElementRef
    public Nested4PartyGroup[] getNested4PartyIDGroups() {
        return nestedParties4 == null ? null : nestedParties4.getNested4PartyIDGroups();
    }

    public void setNested4PartyIDGroups(Nested4PartyGroup[] nested4PartyIDGroups) {
        if (nested4PartyIDGroups != null) {
            if (nestedParties4 == null) {
                setNestedParties4();
            }
            ((NestedParties450SP1) nestedParties4).setNested4PartyIDGroups(nested4PartyIDGroups);
        }
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

    @Override
    protected void setFragmentCompTagValue(Tag tag, ByteBuffer message)
    throws BadFormatMsgException, InvalidMsgException, TagNotPresentException {
        if (NESTED_PARTIES4_COMP_TAGS.contains(tag.tagNum)) {
            if (nestedParties4 == null) {
                nestedParties4 = new NestedParties450SP1(new FragmentContext(sessionCharset, messageEncoding, validateRequired));
            }
            nestedParties4.decode(tag, message);
        }
    }

    @Override
    protected String getUnsupportedTagMessage() {
        return "This tag is not supported in [FillExecGroup] group version [5.0SP1].";
    }

    @Override
    protected Set<Integer> getFragmentCompTags() {
        return START_COMP_TAGS;
    }

    @Override
    protected Set<Integer> getFragmentSecuredTags() {
        return SECURED_TAGS;
    }
    
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Package Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Private Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Inner Classes">
    // </editor-fold>
}
