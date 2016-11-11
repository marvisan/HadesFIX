/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * UnderlyingLegInstrument50SP1.java
 *
 * $Id: UnderlyingLegInstrument50SP1.java,v 1.2 2011-10-25 08:29:22 vrotaru Exp $
 */
package net.hades.fix.message.comp.impl.v50sp1;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.comp.UnderlyingLegInstrument;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.group.UnderlyingLegSecurityAltIDGroup;
import net.hades.fix.message.group.impl.v50sp1.UnderlyingLegSecurityAltIDGroup50SP1;
import net.hades.fix.message.struct.Tag;
import net.hades.fix.message.type.PutOrCall;
import net.hades.fix.message.xml.codec.jaxb.adapter.FixCharacterAdapter;
import net.hades.fix.message.xml.codec.jaxb.adapter.FixUTCTimeAdapter;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import net.hades.fix.message.xml.codec.jaxb.adapter.FixDateAdapter;

/**
 * FIX 5.0SP1 implementation of UnderlyingLegInstrument component.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 * @created 16/12/2008, 7:28:06 PM
 */
@XmlRootElement(name="Instrmt")
@XmlType(propOrder = {"underlyingLegSecurityAltIDGroups"})
@XmlAccessorType(XmlAccessType.NONE)
public class UnderlyingLegInstrument50SP1 extends UnderlyingLegInstrument {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> START_COMP_TAGS;

    protected static final Set<Integer> ALL_TAGS;

    protected static final Set<Integer> UNDLY_LEG_SEC_ALT_ID_GROUP_TAGS = new UnderlyingLegSecurityAltIDGroup50SP1().getFragmentAllTags();

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">

    static {
        ALL_TAGS = new HashSet<Integer>(TAGS);
        ALL_TAGS.addAll(START_DATA_TAGS);
        ALL_TAGS.addAll(UNDLY_LEG_SEC_ALT_ID_GROUP_TAGS);
        START_COMP_TAGS = new HashSet<Integer>(UNDLY_LEG_SEC_ALT_ID_GROUP_TAGS);
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">
    
    public UnderlyingLegInstrument50SP1() {
        super();
    }

    public UnderlyingLegInstrument50SP1(FragmentContext context) {
        super(context);
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Public Methods">

    @Override
    public Set<Integer> getFragmentTags() {
        return TAGS;
    }

    @Override
    public Set<Integer> getFragmentAllTags() {
        return ALL_TAGS;
    }

    // ACCESSOR METHODS
    //////////////////////////////////////////

    @XmlAttribute(name = "Sym")
    @Override
    public String getUnderlyingLegSymbol() {
        return underlyingLegSymbol;
    }

    @Override
    public void setUnderlyingLegSymbol(String underlyingLegSymbol) {
        this.underlyingLegSymbol = underlyingLegSymbol;
    }

    @XmlAttribute(name = "Sfx")
    @Override
    public String getUnderlyingLegSymbolSfx() {
        return underlyingLegSymbolSfx;
    }

    @Override
    public void setUnderlyingLegSymbolSfx(String underlyingLegSymbolSfx) {
        this.underlyingLegSymbolSfx = underlyingLegSymbolSfx;
    }

    @XmlAttribute(name = "ID")
    @Override
    public String getUnderlyingLegSecurityID() {
        return underlyingLegSecurityID;
    }

    @Override
    public void setUnderlyingLegSecurityID(String underlyingLegSecurityID) {
        this.underlyingLegSecurityID = underlyingLegSecurityID;
    }

    @XmlAttribute(name = "Src")
    @Override
    public String getUnderlyingLegSecurityIDSource() {
        return underlyingLegSecurityIDSource;
    }

    @Override
    public void setUnderlyingLegSecurityIDSource(String underlyingLegSecurityIDSource) {
        this.underlyingLegSecurityIDSource = underlyingLegSecurityIDSource;
    }

    @Override
    public Integer getNoUnderlyingLegSecurityAltID() {
        return noUnderlyingLegSecurityAltID;
    }

    @Override
    public void setNoUnderlyingLegSecurityAltID(Integer noUnderlyingLegSecurityAltID) {
        this.noUnderlyingLegSecurityAltID = noUnderlyingLegSecurityAltID;
        if (noUnderlyingLegSecurityAltID != null) {
            underlyingLegSecurityAltIDGroups = new UnderlyingLegSecurityAltIDGroup[noUnderlyingLegSecurityAltID.intValue()];
            for (int i = 0; i < underlyingLegSecurityAltIDGroups.length; i++) {
                underlyingLegSecurityAltIDGroups[i] = new UnderlyingLegSecurityAltIDGroup50SP1(new FragmentContext(sessionCharset, messageEncoding, validateRequired));
            }
        }
    }

    @XmlElementRef
    @Override
    public UnderlyingLegSecurityAltIDGroup[] getUnderlyingLegSecurityAltIDGroups() {
        return underlyingLegSecurityAltIDGroups;
    }

    public void setUnderlyingLegSecurityAltIDGroups(UnderlyingLegSecurityAltIDGroup[] underlyingLegSecurityAltIDGroups) {
        this.underlyingLegSecurityAltIDGroups = underlyingLegSecurityAltIDGroups;
        if (underlyingLegSecurityAltIDGroups != null) {
            noUnderlyingLegSecurityAltID = new Integer(underlyingLegSecurityAltIDGroups.length);
        }
    }

    @Override
    public UnderlyingLegSecurityAltIDGroup addUnderlyingLegSecurityAltIDGroup() {
        UnderlyingLegSecurityAltIDGroup group = new UnderlyingLegSecurityAltIDGroup50SP1(new FragmentContext(sessionCharset, messageEncoding, validateRequired));
        List<UnderlyingLegSecurityAltIDGroup> groups = new ArrayList<UnderlyingLegSecurityAltIDGroup>();
        if (underlyingLegSecurityAltIDGroups != null && underlyingLegSecurityAltIDGroups.length > 0) {
            groups = new ArrayList<UnderlyingLegSecurityAltIDGroup>(Arrays.asList(underlyingLegSecurityAltIDGroups));
        }
        groups.add(group);
        underlyingLegSecurityAltIDGroups = groups.toArray(new UnderlyingLegSecurityAltIDGroup[groups.size()]);
        noUnderlyingLegSecurityAltID = new Integer(underlyingLegSecurityAltIDGroups.length);

        return group;
    }

    @Override
    public UnderlyingLegSecurityAltIDGroup deleteUnderlyingLegSecurityAltIDGroup(int index) {
        UnderlyingLegSecurityAltIDGroup result = null;

        if (underlyingLegSecurityAltIDGroups != null && underlyingLegSecurityAltIDGroups.length > 0 && underlyingLegSecurityAltIDGroups.length > index) {
            List<UnderlyingLegSecurityAltIDGroup> groups = new ArrayList<UnderlyingLegSecurityAltIDGroup>(Arrays.asList(underlyingLegSecurityAltIDGroups));
            result = groups.remove(index);
            underlyingLegSecurityAltIDGroups = groups.toArray(new UnderlyingLegSecurityAltIDGroup[groups.size()]);
            if (underlyingLegSecurityAltIDGroups.length > 0) {
                noUnderlyingLegSecurityAltID = new Integer(underlyingLegSecurityAltIDGroups.length);
            } else {
                underlyingLegSecurityAltIDGroups = null;
                noUnderlyingLegSecurityAltID = null;
            }
        }

        return result;
    }

    @Override
    public int clearUnderlyingLegSecurityAltIDGroups() {
        int result = 0;
        if (underlyingLegSecurityAltIDGroups != null && underlyingLegSecurityAltIDGroups.length > 0) {
            result = underlyingLegSecurityAltIDGroups.length;
            underlyingLegSecurityAltIDGroups = null;
            noUnderlyingLegSecurityAltID = null;
        }

        return result;
    }

    @XmlAttribute(name = "CFI")
    @Override
    public String getUnderlyingLegCFICode() {
        return underlyingLegCFICode;
    }

    @Override
    public void setUnderlyingLegCFICode(String underlyingLegCFICode) {
        this.underlyingLegCFICode = underlyingLegCFICode;
    }

    @XmlAttribute(name = "SecType")
    @Override
    public String getUnderlyingLegSecurityType() {
        return underlyingLegSecurityType;
    }

    @Override
    public void setUnderlyingLegSecurityType(String underlyingLegSecurityType) {
        this.underlyingLegSecurityType = underlyingLegSecurityType;
    }

    @XmlAttribute(name = "SubType")
    @Override
    public String getUnderlyingLegSecuritySubType() {
        return underlyingLegSecuritySubType;
    }

    @Override
    public void setUnderlyingLegSecuritySubType(String underlyingLegSecuritySubType) {
        this.underlyingLegSecuritySubType = underlyingLegSecuritySubType;
    }

    @XmlAttribute(name = "MMY")
    @Override
    public String getUnderlyingLegMaturityMonthYear() {
        return underlyingLegMaturityMonthYear;
    }

    @Override
    public void setUnderlyingLegMaturityMonthYear(String underlyingLegMaturityMonthYear) {
        this.underlyingLegMaturityMonthYear = underlyingLegMaturityMonthYear;
    }

    @XmlAttribute(name = "MatDt")
    @XmlJavaTypeAdapter(FixDateAdapter.class)
    @Override
    public Date getUnderlyingLegMaturityDate() {
        return underlyingLegMaturityDate;
    }

    @Override
    public void setUnderlyingLegMaturityDate(Date underlyingLegMaturityDate) {
        this.underlyingLegMaturityDate = underlyingLegMaturityDate;
    }

    @XmlAttribute(name = "MatTm")
    @XmlJavaTypeAdapter(FixUTCTimeAdapter.class)
    @Override
    public Date getUnderlyingLegMaturityTime() {
        return underlyingLegMaturityTime;
    }

    @Override
    public void setUnderlyingLegMaturityTime(Date underlyingLegMaturityTime) {
        this.underlyingLegMaturityTime = underlyingLegMaturityTime;
    }

    @XmlAttribute(name = "StrkPx")
    @Override
    public Double getUnderlyingLegStrikePrice() {
        return underlyingLegStrikePrice;
    }

    @Override
    public void setUnderlyingLegStrikePrice(Double underlyingLegStrikePrice) {
        this.underlyingLegStrikePrice = underlyingLegStrikePrice;
    }

    @XmlAttribute(name = "OptAt")
    @XmlJavaTypeAdapter(FixCharacterAdapter.class)
    @Override
    public Character getUnderlyingLegOptAttribute() {
        return underlyingLegOptAttribute;
    }

    @Override
    public void setUnderlyingLegOptAttribute(Character underlyingLegOptAttribute) {
        this.underlyingLegOptAttribute = underlyingLegOptAttribute;
    }

    @XmlAttribute(name = "PutCall")
    @Override
    public PutOrCall getUnderlyingLegPutOrCall() {
        return underlyingLegPutOrCall;
    }

    @Override
    public void setUnderlyingLegPutOrCall(PutOrCall underlyingLegPutOrCall) {
        this.underlyingLegPutOrCall = underlyingLegPutOrCall;
    }

    @XmlAttribute(name = "Exch")
    @Override
    public String getUnderlyingLegSecurityExchange() {
        return underlyingLegSecurityExchange;
    }

    @Override
    public void setUnderlyingLegSecurityExchange(String underlyingLegSecurityExchange) {
        this.underlyingLegSecurityExchange = underlyingLegSecurityExchange;
    }

    @XmlAttribute(name = "Desc")
    @Override
    public String getUnderlyingLegSecurityDesc() {
        return underlyingLegSecurityDesc;
    }

    @Override
    public void setUnderlyingLegSecurityDesc(String underlyingLegSecurityDesc) {
        this.underlyingLegSecurityDesc = underlyingLegSecurityDesc;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

    @Override
    protected void setFragmentCompTagValue(Tag tag, ByteBuffer message)
    throws BadFormatMsgException, InvalidMsgException, TagNotPresentException {
        FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, validateRequired);
        if (noUnderlyingLegSecurityAltID != null && noUnderlyingLegSecurityAltID.intValue() > 0) {
            if (UNDLY_LEG_SEC_ALT_ID_GROUP_TAGS.contains(tag.tagNum)) {
                message.reset();
                underlyingLegSecurityAltIDGroups = new UnderlyingLegSecurityAltIDGroup[noUnderlyingLegSecurityAltID.intValue()];
                for (int i = 0; i < noUnderlyingLegSecurityAltID.intValue(); i++) {
                    UnderlyingLegSecurityAltIDGroup group = new UnderlyingLegSecurityAltIDGroup50SP1(context);
                    group.decode(message);
                    underlyingLegSecurityAltIDGroups[i] = group;
                }
            }
        }
    }

    @Override
    protected String getUnsupportedTagMessage() {
        return "This tag is not supported in [UnderlyingLegInstrument] component version [5.0SP1].";
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
