/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * StrategyParametersGroup50.java
 *
 * $Id: StrategyParametersGroup50.java,v 1.2 2010-12-12 09:13:10 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v50;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.group.StrategyParametersGroup;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import net.hades.fix.message.type.StrategyParameterType;

/**
 * FIX 5.0 implementation of StrategyParametersGroup.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 * @created 28/12/2008, 11:57:16 AM
 */
@XmlRootElement(name="StrpPrmGrp")
@XmlType
@XmlAccessorType(XmlAccessType.NONE)
public class StrategyParametersGroup50 extends StrategyParametersGroup {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public StrategyParametersGroup50() {
    }

    public StrategyParametersGroup50(FragmentContext context) {
        super(context);
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Public Methods">

    @XmlAttribute(name = "StrtPrmNme")
    @Override
    public String getStrategyParameterName() {
        return strategyParameterName;
    }

    @Override
    public void setStrategyParameterName(String strategyParameterName) {
        this.strategyParameterName = strategyParameterName;
    }

    @XmlAttribute(name = "StrtPrmTyp")
    @Override
    public StrategyParameterType getStrategyParameterType() {
        return strategyParameterType;
    }

    @Override
    public void setStrategyParameterType(StrategyParameterType strategyParameterType) {
        this.strategyParameterType = strategyParameterType;
    }

    @XmlAttribute(name = "StrtPrmVal")
    @Override
    public String getStrategyParameterValue() {
        return strategyParameterValue;
    }

    @Override
    public void setStrategyParameterValue(String strategyParameterValue) {
        this.strategyParameterValue = strategyParameterValue;
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
