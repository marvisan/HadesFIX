/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * MDEntryTypeGroup50SP1.java
 *
 * $Id: MDEntryTypeGroup50SP1.java,v 1.2 2010-02-04 10:11:08 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v50sp1;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.xml.codec.jaxb.adapter.FixCharacterAdapter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import net.hades.fix.message.group.MDEntryTypeGroup;

/**
 * FIX 5.0SP1 implementation of MDEntryTypeGroup group.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 * @created 19/02/2009, 8:31:39 PM
 */
@XmlRootElement(name="Req")
@XmlType
@XmlAccessorType(XmlAccessType.NONE)
public class MDEntryTypeGroup50SP1 extends MDEntryTypeGroup {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = -5789319925240255334L;
    
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public MDEntryTypeGroup50SP1() {
    }

    public MDEntryTypeGroup50SP1(FragmentContext context) {
        super(context);
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Public Methods">

    @XmlAttribute(name = "Typ")
    @XmlJavaTypeAdapter(FixCharacterAdapter.class)
    @Override
    public Character getMdEntryType() {
        return mdEntryType;
    }

    @Override
    public void setMdEntryType(Character mdEntryType) {
        this.mdEntryType = mdEntryType;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Package Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Private Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Inner Classes">
    // </editor-fold>
}
