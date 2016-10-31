/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * MDSecSizeGroup50SP1.java
 *
 * $Id: MDSecSizeGroup50SP1.java,v 1.4 2010-02-04 10:11:08 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v50sp1;

import net.hades.fix.message.group.MDSecSizeGroup;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import net.hades.fix.message.FragmentContext;

/**
 * FIX 5.0SP1 implementation of MDSecSizeGroup group.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.4 $
 * @created 05/04/2009, 11:39:24 AM
 */
@XmlRootElement(name="SecSizesGrp")
@XmlType
@XmlAccessorType(XmlAccessType.NONE)
public class MDSecSizeGroup50SP1 extends MDSecSizeGroup {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = -7860198830764130634L;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public MDSecSizeGroup50SP1() {
    }

    public MDSecSizeGroup50SP1(FragmentContext context) {
        super(context);
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Public Methods">

    // ACCESSORS
    //////////////////////////////////////////

    @XmlAttribute(name = "MDSecSizeType")
    @Override
    public Integer getMdSecSizeType() {
        return mdSecSizeType;
    }

    @Override
    public void setMdSecSizeType(Integer mdSecSizeType) {
        this.mdSecSizeType = mdSecSizeType;
    }

    @XmlAttribute(name = "MDSecSize")
    @Override
    public Double getMdSecSize() {
        return mdSecSize;
    }

    @Override
    public void setMdSecSize(Double mdSecSize) {
        this.mdSecSize = mdSecSize;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

    @Override
    protected String getUnsupportedTagMessage() {
        return "This tag is not supported in [MDSecSizeGroup] group version [5.0SP1].";
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Package Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Private Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Inner Classes">
    // </editor-fold>
}
