/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * TrdRepIndicatorsGroup50SP2.java
 *
 * $Id: TrdRepIndicatorsGroup50SP2.java,v 1.2 2011-10-25 08:29:22 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v50sp2;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.group.TrdRepIndicatorsGroup;
import net.hades.fix.message.type.PartyRole;
import net.hades.fix.message.xml.codec.jaxb.adapter.FixBooleanAdapter;

import java.util.HashSet;
import java.util.Set;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 * FIX 5.0SP2 implementation of TrdRepIndicatorsGroup group.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 * @created 05/04/2009, 11:39:24 AM
 */
@XmlRootElement(name="TrdRepIndicatorsGrp")
@XmlType
@XmlAccessorType(XmlAccessType.NONE)
public class TrdRepIndicatorsGroup50SP2 extends TrdRepIndicatorsGroup {

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

    public TrdRepIndicatorsGroup50SP2() {
    }

    public TrdRepIndicatorsGroup50SP2(FragmentContext context) {
        super(context);
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Public Methods">

    @Override
    public Set<Integer> getFragmentAllTags() {
        return ALL_TAGS;
    }

    @XmlAttribute(name = "PtyRole")
    @Override
    public PartyRole getTrdRepPartyRole() {
        return trdRepPartyRole;
    }

    @Override
    public void setTrdRepPartyRole(PartyRole trdRepPartyRole) {
        this.trdRepPartyRole = trdRepPartyRole;
    }

    @XmlAttribute(name = "TrdRepInd")
    @XmlJavaTypeAdapter(FixBooleanAdapter.class)
    @Override
    public Boolean getTrdRepIndicator() {
        return trdRepIndicator;
    }

    @Override
    public void setTrdRepIndicator(Boolean trdRepIndicator) {
        this.trdRepIndicator = trdRepIndicator;
    }

    // ACCESSORS
    //////////////////////////////////////////

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

    @Override
    protected String getUnsupportedTagMessage() {
        return "This tag is not supported in [TrdRepIndicatorsGroup] group version [5.0SP2].";
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Package Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Private Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Inner Classes">
    // </editor-fold>
}
