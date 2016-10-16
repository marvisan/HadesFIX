/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * LotTypeRuleGroup50SP2.java
 *
 * $Id: LotTypeRuleGroup50SP2.java,v 1.1 2011-04-17 09:30:45 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v50sp2;

import java.util.HashSet;
import java.util.Set;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.group.LotTypeRuleGroup;
import net.hades.fix.message.type.LotType;

/**
 * FIX 5.0SP2 implementation of LotTypeRuleGroup group.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 05/04/2009, 11:39:24 AM
 */
@XmlRootElement(name="LotTypeRules")
@XmlType
@XmlAccessorType(XmlAccessType.NONE)
public class LotTypeRuleGroup50SP2 extends LotTypeRuleGroup {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> START_COMP_TAGS = null;

    protected static final Set<Integer> ALL_TAGS;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">

    static {
        ALL_TAGS = new HashSet<Integer>(TAGS);
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public LotTypeRuleGroup50SP2() {
    }

    public LotTypeRuleGroup50SP2(FragmentContext context) {
        super(context);
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Public Methods">

    @Override
    public Set<Integer> getFragmentAllTags() {
        return ALL_TAGS;
    }

    // ACCESSORS
    //////////////////////////////////////////

    @XmlAttribute(name = "LotTyp")
    @Override
    public LotType getLotType() {
        return lotType;
    }

    @Override
    public void setLotType(LotType lotType) {
        this.lotType = lotType;
    }

    @XmlAttribute(name = "MinLotSz")
    @Override
    public Double getMinLotSize() {
        return minLotSize;
    }

    @Override
    public void setMinLotSize(Double minLotSize) {
        this.minLotSize = minLotSize;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

    @Override
    protected String getUnsupportedTagMessage() {
        return "This tag is not supported in [LotTypeRuleGroup] group version [5.0SP2].";
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
