/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * DerivativeSecurityDefinition50SP2.java
 *
 * $Id: DerivativeSecurityDefinition50SP2.java,v 1.1 2011-09-27 08:57:26 vrotaru Exp $
 */
package net.hades.fix.message.comp.impl.v50sp2;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.comp.DerivativeInstrument;
import net.hades.fix.message.comp.DerivativeSecurityDefinition;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.group.DerivativeInstrAttribGroup;
import net.hades.fix.message.group.MarketSegmentGroup;
import net.hades.fix.message.group.impl.v50sp2.DerivativeInstrAttribGroup50SP2;
import net.hades.fix.message.group.impl.v50sp2.MarketSegmentGroup50SP2;
import net.hades.fix.message.struct.Tag;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * FIX 5.0SP2 implementation of DerivativeSecurityDefinition component.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 02/01/2009, 12:07:02 PM
 */
@XmlRootElement(name="DerivSecDef")
@XmlType(propOrder = {"derivativeInstrument", "derivativeInstrAttribGroups", "marketSegmentGroups"})
@XmlAccessorType(XmlAccessType.NONE)
public class DerivativeSecurityDefinition50SP2 extends DerivativeSecurityDefinition {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> START_COMP_TAGS;

    protected static final Set<Integer> ALL_TAGS;

    protected static final Set<Integer> DERIVATIVE_INSTRUMENT_COMP_TAGS = new DerivativeInstrument50SP2().getFragmentAllTags();
    protected static final Set<Integer> DERIV_INSTR_ATTRIB_GROUP_TAGS = new DerivativeInstrAttribGroup50SP2().getFragmentAllTags();
    protected static final Set<Integer> MARKET_SEGMENT_GROUP_TAGS = new MarketSegmentGroup50SP2().getFragmentAllTags();

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">

    static {
        ALL_TAGS = new HashSet<Integer>(TAGS);
        ALL_TAGS.addAll(DERIVATIVE_INSTRUMENT_COMP_TAGS);
        ALL_TAGS.addAll(DERIV_INSTR_ATTRIB_GROUP_TAGS);
        ALL_TAGS.addAll(MARKET_SEGMENT_GROUP_TAGS);
        START_COMP_TAGS = new HashSet<Integer>(DERIVATIVE_INSTRUMENT_COMP_TAGS);
        START_COMP_TAGS.addAll(DERIV_INSTR_ATTRIB_GROUP_TAGS);
        START_COMP_TAGS.addAll(MARKET_SEGMENT_GROUP_TAGS);
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public DerivativeSecurityDefinition50SP2() {
        super();
    }

    public DerivativeSecurityDefinition50SP2(FragmentContext context) {
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

    @XmlElementRef
    @Override
    public DerivativeInstrument getDerivativeInstrument() {
        return derivativeInstrument;
    }

    @Override
    public void setDerivativeInstrument() {
        derivativeInstrument = new DerivativeInstrument50SP2(new FragmentContext(sessionCharset, messageEncoding, validateRequired));
    }
    
    @Override
    public void clearDerivativeInstrument() {
        this.derivativeInstrument = null;
    }
    
    public void setDerivativeInstrument(DerivativeInstrument derivativeInstrument) {
        this.derivativeInstrument = derivativeInstrument;
    }

    @Override
    public Integer getNoDerivativeInstrAttrib() {
        return noDerivativeInstrAttrib;
    }

    @Override
    public void setNoDerivativeInstrAttrib(Integer noDerivativeInstrAttrib) {
        this.noDerivativeInstrAttrib = noDerivativeInstrAttrib;
        if (noDerivativeInstrAttrib != null) {
            derivativeInstrAttribGroups = new DerivativeInstrAttribGroup[noDerivativeInstrAttrib.intValue()];
            for (int i = 0; i < derivativeInstrAttribGroups.length; i++) {
                derivativeInstrAttribGroups[i] = new DerivativeInstrAttribGroup50SP2(new FragmentContext(sessionCharset, messageEncoding, validateRequired));
            }
        }
    }

    @XmlElementRef
    @Override
    public DerivativeInstrAttribGroup[] getDerivativeInstrAttribGroups() {
        return derivativeInstrAttribGroups;
    }

    public void setDerivativeInstrAttribGroups(DerivativeInstrAttribGroup[] derivativeInstrAttribGroups) {
        this.derivativeInstrAttribGroups = derivativeInstrAttribGroups;
        if (derivativeInstrAttribGroups != null) {
            noDerivativeInstrAttrib = new Integer(derivativeInstrAttribGroups.length);
        }
    }

    @Override
    public DerivativeInstrAttribGroup addDerivativeInstrAttribGroup() {
        DerivativeInstrAttribGroup group = new DerivativeInstrAttribGroup50SP2(new FragmentContext(sessionCharset, messageEncoding, validateRequired));
        List<DerivativeInstrAttribGroup> groups = new ArrayList<DerivativeInstrAttribGroup>();
        if (derivativeInstrAttribGroups != null && derivativeInstrAttribGroups.length > 0) {
            groups = new ArrayList<DerivativeInstrAttribGroup>(Arrays.asList(derivativeInstrAttribGroups));
        }
        groups.add(group);
        derivativeInstrAttribGroups = groups.toArray(new DerivativeInstrAttribGroup[groups.size()]);
        noDerivativeInstrAttrib = new Integer(derivativeInstrAttribGroups.length);

        return group;
    }

    @Override
    public DerivativeInstrAttribGroup deleteDerivativeInstrAttribGroup(int index) {
        DerivativeInstrAttribGroup result = null;
        if (derivativeInstrAttribGroups != null && derivativeInstrAttribGroups.length > 0 && derivativeInstrAttribGroups.length > index) {
            List<DerivativeInstrAttribGroup> groups = new ArrayList<DerivativeInstrAttribGroup>(Arrays.asList(derivativeInstrAttribGroups));
            result = groups.remove(index);
            derivativeInstrAttribGroups = groups.toArray(new DerivativeInstrAttribGroup[groups.size()]);
            if (derivativeInstrAttribGroups.length > 0) {
                noDerivativeInstrAttrib = new Integer(derivativeInstrAttribGroups.length);
            } else {
                derivativeInstrAttribGroups = null;
                noDerivativeInstrAttrib = null;
            }
        }

        return result;
    }

    @Override
    public int clearDerivativeInstrAttribGroups() {
        int result = 0;
        if (derivativeInstrAttribGroups != null && derivativeInstrAttribGroups.length > 0) {
            result = derivativeInstrAttribGroups.length;
            derivativeInstrAttribGroups = null;
            noDerivativeInstrAttrib = null;
        }

        return result;
    }

    @Override
    public Integer getNoMarketSegments() {
        return noMarketSegments;
    }

    @Override
    public void setNoMarketSegments(Integer noMarketSegments) {
        this.noMarketSegments = noMarketSegments;
        if (noMarketSegments != null) {
            marketSegmentGroups = new MarketSegmentGroup[noMarketSegments.intValue()];
            for (int i = 0; i < marketSegmentGroups.length; i++) {
                marketSegmentGroups[i] = new MarketSegmentGroup50SP2(new FragmentContext(sessionCharset, messageEncoding, validateRequired));
            }
        }
    }

    @XmlElementRef
    @Override
    public MarketSegmentGroup[] getMarketSegmentGroups() {
        return marketSegmentGroups;
    }

    public void setMarketSegmentGroups(MarketSegmentGroup[] marketSegmentGroups) {
        this.marketSegmentGroups = marketSegmentGroups;
        if (marketSegmentGroups != null) {
            noMarketSegments = new Integer(marketSegmentGroups.length);
        }
    }

    @Override
    public MarketSegmentGroup addMarketSegmentGroup() {
        MarketSegmentGroup group = new MarketSegmentGroup50SP2(new FragmentContext(sessionCharset, messageEncoding, validateRequired));
        List<MarketSegmentGroup> groups = new ArrayList<MarketSegmentGroup>();
        if (marketSegmentGroups != null && marketSegmentGroups.length > 0) {
            groups = new ArrayList<MarketSegmentGroup>(Arrays.asList(marketSegmentGroups));
        }
        groups.add(group);
        marketSegmentGroups = groups.toArray(new MarketSegmentGroup[groups.size()]);
        noMarketSegments = new Integer(marketSegmentGroups.length);

        return group;
    }

    @Override
    public MarketSegmentGroup deleteMarketSegmentGroup(int index) {
        MarketSegmentGroup result = null;
        if (marketSegmentGroups != null && marketSegmentGroups.length > 0 && marketSegmentGroups.length > index) {
            List<MarketSegmentGroup> groups = new ArrayList<MarketSegmentGroup>(Arrays.asList(marketSegmentGroups));
            result = groups.remove(index);
            marketSegmentGroups = groups.toArray(new MarketSegmentGroup[groups.size()]);
            if (marketSegmentGroups.length > 0) {
                noMarketSegments = new Integer(marketSegmentGroups.length);
            } else {
                marketSegmentGroups = null;
                noMarketSegments = null;
            }
        }

        return result;
    }

    @Override
    public int clearMarketSegmentGroups() {
        int result = 0;
        if (marketSegmentGroups != null && marketSegmentGroups.length > 0) {
            result = marketSegmentGroups.length;
            marketSegmentGroups = null;
            noMarketSegments = null;
        }

        return result;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

    @Override
    protected void setFragmentCompTagValue(Tag tag, ByteBuffer message)
    throws BadFormatMsgException, InvalidMsgException, TagNotPresentException {
        FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, validateRequired);
        if (DERIVATIVE_INSTRUMENT_COMP_TAGS.contains(tag.tagNum)) {
            if (derivativeInstrument == null) {
                derivativeInstrument = new DerivativeInstrument50SP2(context);
            }
            derivativeInstrument.decode(tag, message);
        }
        if (noDerivativeInstrAttrib != null && noDerivativeInstrAttrib.intValue() > 0) {
            if (DERIV_INSTR_ATTRIB_GROUP_TAGS.contains(tag.tagNum)) {
                message.reset();
                derivativeInstrAttribGroups = new DerivativeInstrAttribGroup[noDerivativeInstrAttrib.intValue()];
                for (int i = 0; i < noDerivativeInstrAttrib.intValue(); i++) {
                    DerivativeInstrAttribGroup group = new DerivativeInstrAttribGroup50SP2(context);
                    group.decode(message);
                    derivativeInstrAttribGroups[i] = group;
                }
            }
        }
        if (noMarketSegments != null && noMarketSegments.intValue() > 0) {
            if (MARKET_SEGMENT_GROUP_TAGS.contains(tag.tagNum)) {
                message.reset();
                marketSegmentGroups = new MarketSegmentGroup[noMarketSegments.intValue()];
                for (int i = 0; i < noMarketSegments.intValue(); i++) {
                    MarketSegmentGroup group = new MarketSegmentGroup50SP2(context);
                    group.decode(message);
                    marketSegmentGroups[i] = group;
                }
            }
        }
    }

    @Override
    protected Set<Integer> getFragmentCompTags() {
        return START_COMP_TAGS;
    }

    @Override
    protected String getUnsupportedTagMessage() {
        return "This tag is not supported in [DerivativeSecurityDefinition] component release [5.0SP2].";
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Package Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Private Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Inner Classes">
    // </editor-fold>
}
