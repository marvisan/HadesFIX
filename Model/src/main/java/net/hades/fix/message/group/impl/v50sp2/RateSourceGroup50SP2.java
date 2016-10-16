/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * RateSourceGroup50SP2.java
 *
 * $Id: RateSourceGroup50SP2.java,v 1.7 2010-02-25 08:37:34 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v50sp2;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.type.RateSource;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import net.hades.fix.message.group.RateSourceGroup;
import net.hades.fix.message.type.RateSourceType;

/**
 * FIX 4.4 implementation of PartyGroup group.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.7 $
 * @created 12/02/2009, 7:22:35 PM
 */
@XmlRootElement(name="RtSrc")
@XmlType
@XmlAccessorType(XmlAccessType.NONE)
public class RateSourceGroup50SP2 extends RateSourceGroup {
    
    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Static Block">
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Attributes">
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Constructors">
    
    public RateSourceGroup50SP2() {
    }
    
    public RateSourceGroup50SP2(FragmentContext context) {
        super(context);
    }
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Public Methods">

    // ACCESSOR METHODS
    //////////////////////////////////////////

    @XmlAttribute(name = "RtSrc")
    @Override
    public RateSource getRateSource() {
        return rateSource;
    }

    @Override
    public void setRateSource(RateSource rateSource) {
        this.rateSource = rateSource;
    }

    @XmlAttribute(name = "RtSrcTyp")
    @Override
    public RateSourceType getRateSourceType() {
        return rateSourceType;
    }

    @Override
    public void setRateSourceType(RateSourceType rateSourceType) {
        this.rateSourceType = rateSourceType;
    }

    @XmlAttribute(name = "RefPg")
    @Override
    public String getReferencePage() {
        return referencePage;
    }

    @Override
    public void setReferencePage(String referencePage) {
        this.referencePage = referencePage;
    }
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Protected Methods">
    
    @Override
    protected String getUnsupportedTagMessage() {
        return "This tag is not supported in [RateSourceGroup] group version [5.0SP2].";
    }
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Package Methods">
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Private Methods">
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Inner Classes">
    // </editor-fold>
}
