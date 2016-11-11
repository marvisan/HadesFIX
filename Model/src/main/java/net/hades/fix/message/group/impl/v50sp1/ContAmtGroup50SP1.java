/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * ContAmtGroup50SP1.java
 *
 * $Id: ContAmtGroup50SP1.java,v 1.2 2011-01-12 11:33:57 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v50sp1;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.type.ContAmtType;
import net.hades.fix.message.type.Currency;

import java.util.HashSet;
import java.util.Set;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import net.hades.fix.message.group.ContAmtGroup;

/**
 * FIX 5.0SP1 implementation of ContAmtGroup group.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 * @created 05/04/2009, 11:39:24 AM
 */
@XmlRootElement(name="ContAmt")
@XmlType
@XmlAccessorType(XmlAccessType.NONE)
public class ContAmtGroup50SP1 extends ContAmtGroup {

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

    public ContAmtGroup50SP1() {
    }

    public ContAmtGroup50SP1(FragmentContext context) {
        super(context);
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Public Methods">

    @Override
    public Set<Integer> getFragmentAllTags() {
        return ALL_TAGS;
    }

    @XmlAttribute(name = "ContAmtTyp")
    @Override
    public ContAmtType getContAmtType() {
        return contAmtType;
    }

    @Override
    public void setContAmtType(ContAmtType contAmtType) {
        this.contAmtType = contAmtType;
    }

    @XmlAttribute(name = "ContAmtValu")
    @Override
    public Double getContAmtValue() {
        return contAmtValue;
    }

    @Override
    public void setContAmtValue(Double contAmtValue) {
        this.contAmtValue = contAmtValue;
    }

    @XmlAttribute(name = "ContAmtCurr")
    @Override
    public Currency getContAmtCurr() {
        return contAmtCurr;
    }

    @Override
    public void setContAmtCurr(Currency contAmtCurr) {
        this.contAmtCurr = contAmtCurr;
    }

    // ACCESSORS
    //////////////////////////////////////////

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

    @Override
    protected String getUnsupportedTagMessage() {
        return "This tag is not supported in [ContAmtGroup] group version [5.0SP1].";
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Package Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Private Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Inner Classes">
    // </editor-fold>
}
