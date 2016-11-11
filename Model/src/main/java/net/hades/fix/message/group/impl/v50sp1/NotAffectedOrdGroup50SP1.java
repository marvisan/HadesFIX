/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * AffectedOrdGroup50SP1.java
 *
 * $Id: NotAffectedOrdGroup50SP1.java,v 1.1 2011-05-06 09:02:56 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v50sp1;

import net.hades.fix.message.group.NotAffectedOrdGroup;

import java.util.HashSet;
import java.util.Set;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import net.hades.fix.message.FragmentContext;

/**
 * FIX 5.0SP1 implementation of AffectedOrdGroup group.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 05/05/2011, 11:39:24 AM
 */
@XmlRootElement(name="NotAffectedOrdersGrp")
@XmlType
@XmlAccessorType(XmlAccessType.NONE)
public class NotAffectedOrdGroup50SP1 extends NotAffectedOrdGroup {

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

    public NotAffectedOrdGroup50SP1() {
    }

    public NotAffectedOrdGroup50SP1(FragmentContext context) {
        super(context);
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Public Methods">

    @Override
    public Set<Integer> getFragmentAllTags() {
        return ALL_TAGS;
    }

    @XmlAttribute(name = "NotAffOrigClOrdID")
    @Override
    public String getNotAffOrigClOrdID() {
        return notAffOrigClOrdID;
    }

    @Override
    public void setNotAffOrigClOrdID(String notAffOrigClOrdID) {
        this.notAffOrigClOrdID = notAffOrigClOrdID;
    }

    @XmlAttribute(name = "NotAffectedOrderID")
    @Override
    public String getNotAffectedOrderID() {
        return notAffectedOrderID;
    }

    @Override
    public void setNotAffectedOrderID(String notAffectedOrderID) {
        this.notAffectedOrderID = notAffectedOrderID;
    }

    // ACCESSORS
    //////////////////////////////////////////

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

    @Override
    protected String getUnsupportedTagMessage() {
        return "This tag is not supported in [NotAffectedOrdGroup] group version [5.0SP1].";
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Package Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Private Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Inner Classes">
    // </editor-fold>
}
