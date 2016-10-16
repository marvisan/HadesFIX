/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * TradingSessionRuleGroup50SP1.java
 *
 * $Id: TradingSessionRuleGroup50SP1.java,v 1.2 2011-04-20 00:32:35 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v50sp1;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.comp.impl.v50sp1.TradingSessionRules50SP1;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.group.TradingSessionRuleGroup;
import net.hades.fix.message.struct.Tag;

import java.nio.ByteBuffer;
import java.util.HashSet;
import java.util.Set;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import net.hades.fix.message.comp.TradingSessionRules;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.TagNotPresentException;

/**
 * FIX 5.0SP1 implementation of TradingSessionRuleGroup group.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 * @created 05/04/2009, 11:39:24 AM
 */
@XmlRootElement(name="TrdgSesRulesGrp")
@XmlType
@XmlAccessorType(XmlAccessType.NONE)
public class TradingSessionRuleGroup50SP1 extends TradingSessionRuleGroup {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> START_COMP_TAGS;

    protected static final Set<Integer> TRADING_SESSION_RULES_COMP_TAGS = new TradingSessionRules50SP1().getFragmentAllTags();

    protected static final Set<Integer> ALL_TAGS;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">

    static {
        ALL_TAGS = new HashSet<Integer>(TAGS);
        ALL_TAGS.addAll(TRADING_SESSION_RULES_COMP_TAGS);
        START_COMP_TAGS = new HashSet<Integer>(TRADING_SESSION_RULES_COMP_TAGS);
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public TradingSessionRuleGroup50SP1() {
    }

    public TradingSessionRuleGroup50SP1(FragmentContext context) {
        super(context);
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Public Methods">

    @Override
    public Set<Integer> getFragmentAllTags() {
        return ALL_TAGS;
    }

    // ACCESSORS
    //////////////////////////////////////////

    @XmlAttribute(name = "SesID")
    @Override
    public String getTradingSessionID() {
        return tradingSessionID;
    }

    @Override
    public void setTradingSessionID(String tradingSessionID) {
        this.tradingSessionID = tradingSessionID;
    }

    @XmlAttribute(name = "SesSub")
    @Override
    public String getTradingSessionSubID() {
        return tradingSessionSubID;
    }

    @Override
    public void setTradingSessionSubID(String tradingSessionSubID) {
        this.tradingSessionSubID = tradingSessionSubID;
    }

    @XmlElementRef
    @Override
    public TradingSessionRules getTradingSessionRules() {
        return tradingSessionRules;
    }

    @Override
    public void setTradingSessionRules() {
        this.tradingSessionRules = new TradingSessionRules50SP1(new FragmentContext(sessionCharset, messageEncoding, validateRequired));
    }

    public void setTradingSessionRules(TradingSessionRules tradingSessionRules) {
        this.tradingSessionRules = tradingSessionRules;
    }

    @Override
    public void clearTradingSessionRules() {
        this.tradingSessionRules = null;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

    @Override
    protected void setFragmentCompTagValue(Tag tag, ByteBuffer message)
    throws BadFormatMsgException, InvalidMsgException, TagNotPresentException {
        FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, validateRequired);
        if (TRADING_SESSION_RULES_COMP_TAGS.contains(tag.tagNum)) {
            if (tradingSessionRules == null) {
                tradingSessionRules = new TradingSessionRules50SP1(context);
            }
            tradingSessionRules.decode(tag, message);
        }
    }

    @Override
    protected String getUnsupportedTagMessage() {
        return "This tag is not supported in [TradingSessionRuleGroup] group version [5.0SP1].";
    }

    @Override
    protected Set<Integer> getFragmentCompTags() {
        return START_COMP_TAGS;
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
