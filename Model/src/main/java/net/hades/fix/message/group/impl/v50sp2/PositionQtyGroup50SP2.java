/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * PositionQtyGroup50SP2.java
 *
 * $Id: PosAmtGroup44.java,v 1.1 2011-10-21 10:30:59 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v50sp2;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.comp.impl.v50sp2.NestedParties50SP2;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.group.PositionQtyGroup;
import net.hades.fix.message.struct.Tag;

import java.nio.ByteBuffer;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import net.hades.fix.message.comp.NestedParties;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.group.NestedPartyGroup;
import net.hades.fix.message.type.PosQtyStatus;
import net.hades.fix.message.type.PosType;
import net.hades.fix.message.xml.codec.jaxb.adapter.FixDateAdapter;

/**
 * FIX 5.0SP2* implementation of PositionQtyGroup group.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 05/12/2011, 11:39:24 AM
 */
@XmlRootElement(name="Qty")
@XmlType(propOrder = {"nestedPartyIDGroups"})
@XmlAccessorType(XmlAccessType.NONE)
public class PositionQtyGroup50SP2 extends PositionQtyGroup {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> ALL_TAGS;
    
    protected static final Set<Integer> START_COMP_TAGS;

    protected static final Set<Integer> NESTED_PARTIES_COMP_TAGS = new NestedParties50SP2().getFragmentAllTags();

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">

    static {
        ALL_TAGS = new HashSet<Integer>(TAGS);
        ALL_TAGS.addAll(NESTED_PARTIES_COMP_TAGS);
        START_COMP_TAGS = new HashSet<Integer>(NESTED_PARTIES_COMP_TAGS);
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public PositionQtyGroup50SP2() {
    }

    public PositionQtyGroup50SP2(FragmentContext context) {
        super(context);
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Public Methods">

    @Override
    public Set<Integer> getFragmentAllTags() {
        return ALL_TAGS;
    }

    @XmlAttribute(name = "Typ")
    @Override
    public PosType getPosType() {
        return posType;
    }
    
    @Override
    public void setPosType(PosType posType) {
        this.posType = posType;
    }

    @XmlAttribute(name = "Long")
    @Override
    public Double getLongQty() {
        return longQty;
    }

    @Override
    public void setLongQty(Double longQty) {
        this.longQty = longQty;
    }

    @XmlAttribute(name = "Short")
    @Override
    public Double getShortQty() {
        return shortQty;
    }

    @Override
    public void setShortQty(Double shortQty) {
        this.shortQty = shortQty;
    }

    @XmlAttribute(name = "Stat")
    @Override
    public PosQtyStatus getPosQtyStatus() {
        return posQtyStatus;
    }

    @Override
    public void setPosQtyStatus(PosQtyStatus posQtyStatus) {
        this.posQtyStatus = posQtyStatus;
    }

    @XmlAttribute(name = "QtyDt")
    @XmlJavaTypeAdapter(FixDateAdapter.class)
    @Override
    public Date getQuantityDate() {
        return quantityDate;
    }

    @Override
    public void setQuantityDate(Date quantityDate) {
        this.quantityDate = quantityDate;
    }

    @Override
    public NestedParties getNestedParties() {
        return nestedParties;
    }

    @Override
    public void setNestedParties() {
        FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, validateRequired);
        this.nestedParties = new NestedParties50SP2(context);
    }

    @Override
    public void clearNestedParties() {
        this.nestedParties = null;
    }

    @XmlElementRef
    public NestedPartyGroup[] getNestedPartyIDGroups() {
        return nestedParties == null ? null : nestedParties.getNestedPartyIDGroups();
    }

    public void setNestedPartyIDGroups(NestedPartyGroup[] nestedPartyIDGroups) {
        if (nestedPartyIDGroups != null) {
            if (nestedParties == null) {
                setNestedParties();
            }
            ((NestedParties50SP2) nestedParties).setNestedPartyIDGroups(nestedPartyIDGroups);
        }
    }

    // ACCESSORS
    //////////////////////////////////////////

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

    @Override
    protected void setFragmentCompTagValue(Tag tag, ByteBuffer message)
    throws BadFormatMsgException, InvalidMsgException, TagNotPresentException {
        FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, validateRequired);
        if (NESTED_PARTIES_COMP_TAGS.contains(tag.tagNum)) {
            if (nestedParties == null) {
                nestedParties = new NestedParties50SP2(context);
            }
            nestedParties.decode(tag, message);
        }
    }

    @Override
    protected Set<Integer> getFragmentCompTags() {
        return START_COMP_TAGS;
    }

    @Override
    protected String getUnsupportedTagMessage() {
        return "This tag is not supported in [PositionQtyGroup] group version [5.0SP2].";
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Package Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Private Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Inner Classes">
    // </editor-fold>
}
