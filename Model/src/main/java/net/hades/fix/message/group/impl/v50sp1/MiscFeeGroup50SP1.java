/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * MiscFeeGroup50SP1.java
 *
 * $Id: MiscFeeGroup50SP1.java,v 1.1 2011-01-09 07:27:41 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v50sp1;

import net.hades.fix.message.type.MiscFeeType;

import java.util.HashSet;
import java.util.Set;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.group.MiscFeeGroup;
import net.hades.fix.message.type.Currency;
import net.hades.fix.message.type.MiscFeeBasis;

/**
 * FIX 5.0SP1 implementation of MiscFeeGroup group.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 05/04/2009, 11:39:24 AM
 */
@XmlRootElement(name="MiscFees")
@XmlType
@XmlAccessorType(XmlAccessType.NONE)
public class MiscFeeGroup50SP1 extends MiscFeeGroup {

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
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public MiscFeeGroup50SP1() {
    }

    public MiscFeeGroup50SP1(FragmentContext context) {
        super(context);
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
    protected String getUnsupportedTagMessage() {
        return "This tag is not supported in [MiscFeeGroup] group version [5.0SP1].";
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Package Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Private Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Inner Classes">
    // </editor-fold>
}
