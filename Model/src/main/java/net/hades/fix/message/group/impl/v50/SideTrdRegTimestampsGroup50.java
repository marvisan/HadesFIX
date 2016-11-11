/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * SideTrdRegTimestampsGroup50.java
 *
 * $Id: SideTrdRegTimestampsGroup50.java,v 1.1 2011-10-21 10:31:02 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v50;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.type.TrdRegTimestampType;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import net.hades.fix.message.group.SideTrdRegTimestampsGroup;

/**
 * FIX 5.0 implementation of SideTrdRegTimestampsGroup group.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 12/02/2009, 7:22:35 PM
 */
@XmlRootElement(name="TrdRegTS")
@XmlType
@XmlAccessorType(XmlAccessType.NONE)
public class SideTrdRegTimestampsGroup50 extends SideTrdRegTimestampsGroup {

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

    public SideTrdRegTimestampsGroup50() {
    }

    public SideTrdRegTimestampsGroup50(FragmentContext context) {
        super(context);
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Public Methods">

    @Override
    public Set<Integer> getFragmentAllTags() {
        return ALL_TAGS;
    }

    // ACCESSOR METHODS
    //////////////////////////////////////////

    @XmlAttribute(name = "TS")
    @Override
    public Date getSideTrdRegTimestamp() {
        return sideTrdRegTimestamp;
    }

    @Override
    public void setSideTrdRegTimestamp(Date sideTrdRegTimestamp) {
        this.sideTrdRegTimestamp = sideTrdRegTimestamp;
    }

    @XmlAttribute(name = "Typ")
    @Override
    public TrdRegTimestampType getSideTrdRegTimestampType() {
        return sideTrdRegTimestampType;
    }

    @Override
    public void setSideTrdRegTimestampType(TrdRegTimestampType sideTrdRegTimestampType) {
        this.sideTrdRegTimestampType = sideTrdRegTimestampType;
    }

    @XmlAttribute(name = "Src")
    @Override
    public String getSideTrdRegTimestampSrc() {
        return sideTrdRegTimestampSrc;
    }

    @Override
    public void setSideTrdRegTimestampSrc(String sideTrdRegTimestampSrc) {
        this.sideTrdRegTimestampSrc = sideTrdRegTimestampSrc;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

    @Override
    protected Set<Integer> getFragmentCompTags() {
        return START_COMP_TAGS;
    }

    @Override
    protected Set<Integer> getFragmentSecuredTags() {
        return SECURED_TAGS;
    }

    @Override
    protected String getUnsupportedTagMessage() {
        return "This tag is not supported in [SideTrdRegTimestampsGroup] group version [5.0].";
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Package Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Private Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Inner Classes">
    // </editor-fold>
}
