/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * ContraBrokerGroup50SP1.java
 *
 * $Id: ContraBrokerGroup50SP1.java,v 1.1 2010-12-22 09:30:31 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v50sp1;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.xml.codec.jaxb.adapter.FixUTCDateTimeAdapter;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import net.hades.fix.message.group.ContraBrokerGroup;

/**
 * FIX 5.0SP1 implementation of ContraBrokerGroup group.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 05/04/2009, 11:39:24 AM
 */
@XmlRootElement(name="Contra")
@XmlType
@XmlAccessorType(XmlAccessType.NONE)
public class ContraBrokerGroup50SP1 extends ContraBrokerGroup {

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

    public ContraBrokerGroup50SP1() {
    }

    public ContraBrokerGroup50SP1(FragmentContext context) {
        super(context);
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Public Methods">

    @Override
    public Set<Integer> getFragmentAllTags() {
        return ALL_TAGS;
    }

    @XmlAttribute(name = "CntraBrkr")
    @Override
    public String getContraBroker() {
        return contraBroker;
    }

    @Override
    public void setContraBroker(String contraBroker) {
        this.contraBroker = contraBroker;
    }

    @XmlAttribute(name = "CntraTrdr")
    @Override
    public String getContraTrader() {
        return contraTrader;
    }

    @Override
    public void setContraTrader(String contraTrader) {
        this.contraTrader = contraTrader;
    }

    @XmlAttribute(name = "CntraTrdQty")
    @Override
    public Double getContraTradeQty() {
        return contraTradeQty;
    }

    @Override
    public void setContraTradeQty(Double contraTradeQty) {
        this.contraTradeQty = contraTradeQty;
    }

    @XmlAttribute(name = "CntraTrdTm")
    @XmlJavaTypeAdapter(FixUTCDateTimeAdapter.class)
    @Override
    public Date getContraTradeTime() {
        return contraTradeTime;
    }

    @Override
    public void setContraTradeTime(Date contraTradeTime) {
        this.contraTradeTime = contraTradeTime;
    }

    @XmlAttribute(name = "CntraLegRefID")
    @Override
    public String getContraLegRefID() {
        return contraLegRefID;
    }

    @Override
    public void setContraLegRefID(String contraLegRefID) {
        this.contraLegRefID = contraLegRefID;
    }

    // ACCESSORS
    //////////////////////////////////////////

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

    @Override
    protected String getUnsupportedTagMessage() {
        return "This tag is not supported in [ContraBrokerGroup] group version [5.0SP1].";
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Package Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Private Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Inner Classes">
    // </editor-fold>
}
