/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * PosAmtGroup50SP1.java
 *
 * $Id: PosAmtGroup50SP1.java,v 1.1 2011-02-10 10:02:15 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v50sp1;

import net.hades.fix.message.type.PosAmtType;

import java.util.HashSet;
import java.util.Set;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.group.PosAmtGroup;
import net.hades.fix.message.type.Currency;

/**
 * FIX 5.0SP1 implementation of PosAmtGroup group.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 05/04/2009, 11:39:24 AM
 */
@XmlRootElement(name="Amt")
@XmlType
@XmlAccessorType(XmlAccessType.NONE)
public class PosAmtGroup50SP1 extends PosAmtGroup {

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

    public PosAmtGroup50SP1() {
    }

    public PosAmtGroup50SP1(FragmentContext context) {
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
    public PosAmtType getPosAmtType() {
        return posAmtType;
    }

    @Override
    public void setPosAmtType(PosAmtType posAmtType) {
        this.posAmtType = posAmtType;
    }

    @XmlAttribute(name = "Amt")
    @Override
    public Double getPosAmt() {
        return posAmt;
    }

    @Override
    public void setPosAmt(Double posAmt) {
        this.posAmt = posAmt;
    }

    @XmlAttribute(name = "Ccy")
    @Override
    public Currency getPositionCurrency() {
        return positionCurrency;
    }

    @Override
    public void setPositionCurrency(Currency positionCurrency) {
        this.positionCurrency = positionCurrency;
    }

    // ACCESSORS
    //////////////////////////////////////////

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

    @Override
    protected String getUnsupportedTagMessage() {
        return "This tag is not supported in [PosAmtGroup] group version [5.0SP1].";
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Package Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Private Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Inner Classes">
    // </editor-fold>
}
