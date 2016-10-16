/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * TrdRegTimestampsGroup50SP2.java
 *
 * $Id: TrdRegTimestampsGroup50SP2.java,v 1.1 2010-12-05 08:13:28 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v50sp2;

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

import net.hades.fix.message.group.TrdRegTimestampsGroup;

/**
 * FIX 5.0SP2 implementation of TrdRegTimestampsGroup group.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 12/02/2009, 7:22:35 PM
 */
@XmlRootElement(name="TrdRegTS")
@XmlType
@XmlAccessorType(XmlAccessType.NONE)
public class TrdRegTimestampsGroup50SP2 extends TrdRegTimestampsGroup {

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

    public TrdRegTimestampsGroup50SP2() {
    }

    public TrdRegTimestampsGroup50SP2(FragmentContext context) {
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
    public Date getTrdRegTimestamp() {
        return trdRegTimestamp;
    }

    @Override
    public void setTrdRegTimestamp(Date trdRegTimestamp) {
        this.trdRegTimestamp = trdRegTimestamp;
    }

    @XmlAttribute(name = "Typ")
    @Override
    public TrdRegTimestampType getTrdRegTimestampType() {
        return trdRegTimestampType;
    }

    @Override
    public void setTrdRegTimestampType(TrdRegTimestampType trdRegTimestampType) {
        this.trdRegTimestampType = trdRegTimestampType;
    }

    @XmlAttribute(name = "Src")
    @Override
    public String getTrdRegTimestampOrigin() {
        return trdRegTimestampOrigin;
    }

    @Override
    public void setTrdRegTimestampOrigin(String trdRegTimestampOrigin) {
        this.trdRegTimestampOrigin = trdRegTimestampOrigin;
    }

    @XmlAttribute(name = "DskTyp")
    @Override
    public String getDeskType() {
        return deskType;
    }
    
    @Override
    public void setDeskType(String deskType) {
        this.deskType = deskType;
    }

    @XmlAttribute(name = "DskTypSrc")
    @Override
    public Integer getDeskTypeSource() {
        return deskTypeSource;
    }

    @Override
    public void setDeskTypeSource(Integer deskTypeSource) {
        this.deskTypeSource = deskTypeSource;
    }

    @XmlAttribute(name = "DskOrdHndlInst")
    @Override
    public String getDeskOrderHandlingInst() {
        return deskOrderHandlingInst;
    }

    @Override
    public void setDeskOrderHandlingInst(String deskOrderHandlingInst) {
        this.deskOrderHandlingInst = deskOrderHandlingInst;
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
        return "This tag is not supported in [TrdRegTimestampsGroup] group version [5.0SP2].";
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Package Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Private Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Inner Classes">
    // </editor-fold>
}
