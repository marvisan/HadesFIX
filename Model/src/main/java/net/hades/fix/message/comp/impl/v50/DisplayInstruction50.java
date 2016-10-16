/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * DisplayInstruction50.java
 *
 * $Id: DisplayInstruction50.java,v 1.1 2010-12-05 08:13:27 vrotaru Exp $
 */
package net.hades.fix.message.comp.impl.v50;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.comp.DisplayInstruction;
import net.hades.fix.message.type.DisplayMethod;

import java.util.Set;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import net.hades.fix.message.type.DisplayWhen;

/**
 * FIX version 5.0 implementation of DisplayInstruction component.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 14/02/2009, 2:55:05 PM
 */
@XmlRootElement(name="DsplyInstr")
@XmlType
@XmlAccessorType(XmlAccessType.NONE)
public class DisplayInstruction50 extends DisplayInstruction {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">

    protected Set<Integer> STANDARD_SECURED_TAGS = TAGS;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public DisplayInstruction50() {
        super();
    }

    public DisplayInstruction50(FragmentContext context) {
        super(context);
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Public Methods">

    @Override
    public Set<Integer> getFragmentAllTags() {
        return TAGS;
    }

    // ACCESSOR METHODS
    //////////////////////////////////////////

    @XmlAttribute(name = "DisplayQty")
    @Override
    public Double getDisplayQty() {
        return displayQty;
    }

    @Override
    public void setDisplayQty(Double displayQty) {
        this.displayQty = displayQty;
    }

    @XmlAttribute(name = "SecDspQty")
    @Override
    public Double getSecondaryDisplayQty() {
        return secondaryDisplayQty;
    }

    @Override
    public void setSecondaryDisplayQty(Double secondaryDisplayQty) {
        this.secondaryDisplayQty = secondaryDisplayQty;
    }

    @XmlAttribute(name = "DspWhn")
    @Override
    public DisplayWhen getDisplayWhen() {
        return displayWhen;
    }

    @Override
    public void setDisplayWhen(DisplayWhen displayWhen) {
        this.displayWhen = displayWhen;
    }

    @XmlAttribute(name = "DspMthd")
    @Override
    public DisplayMethod getDisplayMethod() {
        return displayMethod;
    }

    @Override
    public void setDisplayMethod(DisplayMethod displayMethod) {
        this.displayMethod = displayMethod;
    }

    @XmlAttribute(name = "DsplLwQty")
    @Override
    public Double getDisplayLowQty() {
        return displayLowQty;
    }

    @Override
    public void setDisplayLowQty(Double displayLowQty) {
        this.displayLowQty = displayLowQty;
    }

    @XmlAttribute(name = "DisplayHighQty")
    @Override
    public Double getDisplayHighQty() {
        return displayHighQty;
    }

    @Override
    public void setDisplayHighQty(Double displayHighQty) {
        this.displayHighQty = displayHighQty;
    }

    @XmlAttribute(name = "DspMinIncr")
    @Override
    public Double getDisplayMinIncr() {
        return displayMinIncr;
    }

    @Override
    public void setDisplayMinIncr(Double displayMinIncr) {
        this.displayMinIncr = displayMinIncr;
    }

    @XmlAttribute(name = "RfrshQty")
    @Override
    public Double getRefreshQty() {
        return refreshQty;
    }

    @Override
    public void setRefreshQty(Double refreshQty) {
        this.refreshQty = refreshQty;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

    @Override
    protected String getUnsupportedTagMessage() {
        return "This tag is not supported in [DisplayInstruction] component version [5.0].";
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Package Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Private Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Inner Classes">
    // </editor-fold>
}
