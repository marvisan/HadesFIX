/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * InstrmtAttribGroup50.java
 *
 * $Id: InstrmtAttribGroup50.java,v 1.1 2011-02-13 04:40:44 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v50;

import java.util.HashSet;
import java.util.Set;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.group.InstrmtAttribGroup;
import net.hades.fix.message.type.InstrAttribType;

/**
 * FIX 5.0 implementation of InstrmtAttribGroup group.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 29/04/2009, 6:46:57 PM
 */
@XmlRootElement(name = "Attrb")
@XmlType
@XmlAccessorType(XmlAccessType.NONE)
public class InstrmtAttribGroup50 extends InstrmtAttribGroup {

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

    public InstrmtAttribGroup50() {
        super();
    }

    public InstrmtAttribGroup50(FragmentContext context) {
        super(context);
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Public Methods">

    @Override
    public Set<Integer> getFragmentAllTags() {
        return ALL_TAGS;
    }

    @Override
    public Set<Integer> getFragmentTags() {
        return TAGS;
    }

    // ACCESSOR METHODS
    //////////////////////////////////////////

    @XmlAttribute(name = "Typ")
    @Override
    public InstrAttribType getInstrAttribType() {
        return instrAttribType;
    }

    @Override
    public void setInstrAttribType(InstrAttribType instrAttribType) {
        this.instrAttribType = instrAttribType;
    }

    @XmlAttribute(name = "Val")
    @Override
    public String getInstrAttribValue() {
        return instrAttribValue;
    }

    @Override
    public void setInstrAttribValue(String instrAttribValue) {
        this.instrAttribValue = instrAttribValue;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

    @Override
    protected String getUnsupportedTagMessage() {
        return "This tag is not supported in [InstrmtAttribGroup] group version [5.0].";
    }

    @Override
    protected Set<Integer> getFragmentSecuredTags() {
        return SECURED_TAGS;
    }
    
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Package Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Private Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Inner Classes">
    // </editor-fold>
}
