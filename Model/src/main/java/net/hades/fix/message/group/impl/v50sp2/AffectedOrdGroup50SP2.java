/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * AffectedOrdGroup50SP2.java
 *
 * $Id: AffectedOrdGroup50SP2.java,v 1.1 2011-05-06 09:02:57 vrotaru Exp $
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
import net.hades.fix.message.group.AffectedOrdGroup;

/**
 * FIX 5.0SP2 implementation of AffectedOrdGroup group.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 05/05/2011, 11:39:24 AM
 */
@XmlRootElement(name="AffectOrd")
@XmlType
@XmlAccessorType(XmlAccessType.NONE)
public class AffectedOrdGroup50SP2 extends AffectedOrdGroup {

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

    public AffectedOrdGroup50SP2() {
    }

    public AffectedOrdGroup50SP2(FragmentContext context) {
        super(context);
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Public Methods">

    @Override
    public Set<Integer> getFragmentAllTags() {
        return ALL_TAGS;
    }

    @XmlAttribute(name = "OrigID")
    @Override
    public String getOrigClOrdID() {
        return origClOrdID;
    }

    @Override
    public void setOrigClOrdID(String origClOrdID) {
        this.origClOrdID = origClOrdID;
    }

    @XmlAttribute(name = "AffctdOrdID")
    @Override
    public String getAffectedOrderID() {
        return affectedOrderID;
    }

    @Override
    public void setAffectedOrderID(String affectedOrderID) {
        this.affectedOrderID = affectedOrderID;
    }

    @XmlAttribute(name = "AffctdScndOrdID")
    @Override
    public String getAffectedSecondaryOrderID() {
        return affectedSecondaryOrderID;
    }

    @Override
    public void setAffectedSecondaryOrderID(String affectedSecondaryOrderID) {
        this.affectedSecondaryOrderID = affectedSecondaryOrderID;
    }

    // ACCESSORS
    //////////////////////////////////////////

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

    @Override
    protected String getUnsupportedTagMessage() {
        return "This tag is not supported in [AffectedOrdGroup] group version [5.0SP2].";
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Package Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Private Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Inner Classes">
    // </editor-fold>
}
