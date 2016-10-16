/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * TriggeringInstruction50SP2.java
 *
 * $Id: TriggeringInstruction50SP2.java,v 1.1 2010-12-05 08:13:28 vrotaru Exp $
 */
package net.hades.fix.message.comp.impl.v50sp2;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.comp.TriggeringInstruction;
import net.hades.fix.message.type.TriggerAction;
import net.hades.fix.message.type.TriggerPriceDirection;
import net.hades.fix.message.type.TriggerPriceType;
import net.hades.fix.message.type.TriggerPriceTypeScope;

import java.util.Set;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import net.hades.fix.message.type.OrdType;
import net.hades.fix.message.type.TriggerType;

/**
 * FIX version 5.0SP1 implementation of DisplayInstruction component.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 14/02/2009, 2:55:05 PM
 */
@XmlRootElement(name="TrgrInstr")
@XmlType
@XmlAccessorType(XmlAccessType.NONE)
public class TriggeringInstruction50SP2 extends TriggeringInstruction {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">

    protected Set<Integer> STANDARD_SECURED_TAGS = TAGS;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public TriggeringInstruction50SP2() {
        super();
    }

    public TriggeringInstruction50SP2(FragmentContext context) {
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

    @XmlAttribute(name = "TrgrTyp")
    @Override
    public TriggerType getTriggerType() {
        return triggerType;
    }

    @Override
    public void setTriggerType(TriggerType triggerType) {
        this.triggerType = triggerType;
    }

    @XmlAttribute(name = "TrgrActn")
    @Override
    public TriggerAction getTriggerAction() {
        return triggerAction;
    }

    @Override
    public void setTriggerAction(TriggerAction triggerAction) {
        this.triggerAction = triggerAction;
    }

    @XmlAttribute(name = "TrgrPx")
    @Override
    public Double getTriggerPrice() {
        return triggerPrice;
    }

    @Override
    public void setTriggerPrice(Double triggerPrice) {
        this.triggerPrice = triggerPrice;
    }

    @XmlAttribute(name = "TrgrSym")
    @Override
    public String getTriggerSymbol() {
        return triggerSymbol;
    }

    @Override
    public void setTriggerSymbol(String triggerSymbol) {
        this.triggerSymbol = triggerSymbol;
    }

    @XmlAttribute(name = "TrgrSecID")
    @Override
    public String getTriggerSecurityID() {
        return triggerSecurityID;
    }

    @Override
    public void setTriggerSecurityID(String triggerSecurityID) {
        this.triggerSecurityID = triggerSecurityID;
    }

    @XmlAttribute(name = "TrgrSecIDSrc")
    @Override
    public String getTriggerSecurityIDSource() {
        return triggerSecurityIDSource;
    }

    @Override
    public void setTriggerSecurityIDSource(String triggerSecurityIDSource) {
        this.triggerSecurityIDSource = triggerSecurityIDSource;
    }

    @XmlAttribute(name = "TrgrSecDesc")
    @Override
    public String getTriggerSecurityDesc() {
        return triggerSecurityDesc;
    }

    @Override
    public void setTriggerSecurityDesc(String triggerSecurityDesc) {
        this.triggerSecurityDesc = triggerSecurityDesc;
    }

    @XmlAttribute(name = "TrgrPxTyp")
    @Override
    public TriggerPriceType getTriggerPriceType() {
        return triggerPriceType;
    }

    @Override
    public void setTriggerPriceType(TriggerPriceType triggerPriceType) {
        this.triggerPriceType = triggerPriceType;
    }

    @XmlAttribute(name = "TrgrPxTypScp")
    @Override
    public TriggerPriceTypeScope getTriggerPriceTypeScope() {
        return triggerPriceTypeScope;
    }

    @Override
    public void setTriggerPriceTypeScope(TriggerPriceTypeScope triggerPriceTypeScope) {
        this.triggerPriceTypeScope = triggerPriceTypeScope;
    }

    @XmlAttribute(name = "TrgrPxDir")
    @Override
    public TriggerPriceDirection getTriggerPriceDirection() {
        return triggerPriceDirection;
    }

    @Override
    public void setTriggerPriceDirection(TriggerPriceDirection triggerPriceDirection) {
        this.triggerPriceDirection = triggerPriceDirection;
    }

    @XmlAttribute(name = "TrgrNewPx")
    @Override
    public Double getTriggerNewPrice() {
        return triggerNewPrice;
    }

    @Override
    public void setTriggerNewPrice(Double triggerNewPrice) {
        this.triggerNewPrice = triggerNewPrice;
    }

    @XmlAttribute(name = "TrgrOrdTyp")
    @Override
    public OrdType getTriggerOrderType() {
        return triggerOrderType;
    }

    @Override
    public void setTriggerOrderType(OrdType triggerOrderType) {
        this.triggerOrderType = triggerOrderType;
    }

    @XmlAttribute(name = "TrgrNewQty")
    @Override
    public Double getTriggerNewQty() {
        return triggerNewQty;
    }

    @Override
    public void setTriggerNewQty(Double triggerNewQty) {
        this.triggerNewQty = triggerNewQty;
    }

    @XmlAttribute(name = "TrgrTrdSessID")
    @Override
    public String getTriggerTradingSessionID() {
        return triggerTradingSessionID;
    }

    @Override
    public void setTriggerTradingSessionID(String triggerTradingSessionID) {
        this.triggerTradingSessionID = triggerTradingSessionID;
    }

    @XmlAttribute(name = "TrgrTrdSessSubID")
    @Override
    public String getTriggerTradingSessionSubID() {
        return triggerTradingSessionSubID;
    }

    @Override
    public void setTriggerTradingSessionSubID(String triggerTradingSessionSubID) {
        this.triggerTradingSessionSubID = triggerTradingSessionSubID;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

    @Override
    protected String getUnsupportedTagMessage() {
        return "This tag is not supported in [TriggeringInstruction] component version [5.0SP2].";
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Package Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Private Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Inner Classes">
    // </editor-fold>
}
