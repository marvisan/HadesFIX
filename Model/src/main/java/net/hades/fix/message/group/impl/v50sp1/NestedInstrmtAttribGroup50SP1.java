/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * NestedInstrmtAttribGroup50SP1.java
 *
 * $Id: NestedInstrmtAttribGroup50SP1.java,v 1.1 2011-04-17 09:30:46 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v50sp1;

import net.hades.fix.message.group.NestedInstrmtAttribGroup;

import java.util.HashSet;
import java.util.Set;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.type.InstrAttribType;

/**
 * FIX 5.0SP1 implementation of NestedInstrmtAttribGroup group.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 29/04/2009, 6:46:57 PM
 */
@XmlRootElement(name = "Attrb")
@XmlType
@XmlAccessorType(XmlAccessType.NONE)
public class NestedInstrmtAttribGroup50SP1 extends NestedInstrmtAttribGroup {

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

    public NestedInstrmtAttribGroup50SP1() {
        super();
    }

    public NestedInstrmtAttribGroup50SP1(FragmentContext context) {
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
    public InstrAttribType getNestedInstrAttribType() {
        return nestedInstrAttribType;
    }

    @Override
    public void setNestedInstrAttribType(InstrAttribType nestedInstrAttribType) {
        this.nestedInstrAttribType = nestedInstrAttribType;
    }

    @XmlAttribute(name = "Val")
    @Override
    public String getNestedInstrAttribValue() {
        return nestedInstrAttribValue;
    }

    @Override
    public void setNestedInstrAttribValue(String nestedInstrAttribValue) {
        this.nestedInstrAttribValue = nestedInstrAttribValue;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

    @Override
    protected String getUnsupportedTagMessage() {
        return "This tag is not supported in [NestedInstrmtAttribGroup] group version [5.0SP1].";
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
