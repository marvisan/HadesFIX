/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * IOIQualifierGroup50SP2.java
 *
 * $Id: IOIQualifierGroup50SP2.java,v 1.4 2010-02-25 08:37:33 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v50sp2;

import net.hades.fix.message.FragmentContext;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import net.hades.fix.message.group.IOIQualifierGroup;
import net.hades.fix.message.xml.codec.jaxb.adapter.FixCharacterAdapter;

/**
 * FIX 5.0SP2 implementation of IOIQualifierGroup group.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.4 $
 * @created 19/02/2009, 8:41:13 PM
 */
@XmlRootElement(name="Qual")
@XmlType
@XmlAccessorType(XmlAccessType.NONE)
public class IOIQualifierGroup50SP2 extends IOIQualifierGroup {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public IOIQualifierGroup50SP2() {
    }

    public IOIQualifierGroup50SP2(FragmentContext context) {
        super(context);
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Public Methods">

    @XmlAttribute(name = "Qual")
    @XmlJavaTypeAdapter(FixCharacterAdapter.class)
    @Override
    public Character getIoiQualifier() {
        return ioiQualifier;
    }

    @Override
    public void setIoiQualifier(Character ioiQualifier) {
        this.ioiQualifier = ioiQualifier;
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
