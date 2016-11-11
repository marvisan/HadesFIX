/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * DerivativeSecurityListMsg50.java
 *
 * $Id: DerivativeSecurityListMsg50.java,v 1.1 2011-09-27 08:57:27 vrotaru Exp $
 */
package net.hades.fix.message.impl.v50;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.Header;
import net.hades.fix.message.comp.impl.v50.UnderlyingInstrument50;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.group.impl.v50.DerivSecListGroup50;
import net.hades.fix.message.struct.Tag;
import net.hades.fix.message.type.ApplVerID;
import net.hades.fix.message.type.SecurityRequestResult;
import net.hades.fix.message.xml.codec.jaxb.adapter.FixBooleanAdapter;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
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

import net.hades.fix.message.DerivativeSecurityListMsg;
import net.hades.fix.message.FIXFragment;
import net.hades.fix.message.comp.UnderlyingInstrument;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.group.DerivSecListGroup;
import net.hades.fix.message.type.BeginString;

/**
 * FIX version 5.0 DerivativeSecurityListMsg implementation.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 28/04/2011, 9:32:41 AM
 */
@XmlRootElement(name="DerivSecList")
@XmlType(propOrder = {"header", "underlyingInstrument", "derivSecListGroups"})
@XmlAccessorType(XmlAccessType.NONE)
public class DerivativeSecurityListMsg50 extends DerivativeSecurityListMsg {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> UNDERLYING_INSTRUMENT_COMP_TAGS = new UnderlyingInstrument50().getFragmentAllTags();
    protected static final Set<Integer> DERIV_SEC_LIST_GROUP_TAGS = new DerivSecListGroup50().getFragmentAllTags();

    protected static final Set<Integer> START_COMP_TAGS;

    protected static final Set<Integer> ALL_TAGS;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">

    static {
        ALL_TAGS = new HashSet<Integer>(TAGS);
        ALL_TAGS.addAll(UNDERLYING_INSTRUMENT_COMP_TAGS);
        ALL_TAGS.addAll(DERIV_SEC_LIST_GROUP_TAGS);
        START_COMP_TAGS = new HashSet<Integer>(UNDERLYING_INSTRUMENT_COMP_TAGS);
        START_COMP_TAGS.addAll(DERIV_SEC_LIST_GROUP_TAGS);
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public DerivativeSecurityListMsg50() {
        super();
    }

    public DerivativeSecurityListMsg50(Header header, ByteBuffer rawMsg)
    throws InvalidMsgException, TagNotPresentException, BadFormatMsgException {
        super(header, rawMsg);
    }

    public DerivativeSecurityListMsg50(BeginString beginString) throws InvalidMsgException {
        super(beginString);
    }

    public DerivativeSecurityListMsg50(BeginString beginString, ApplVerID applVerID) throws InvalidMsgException {
        super(beginString, applVerID);
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

    @Override
    public void copyFixmlData(FIXFragment fragment) {
        DerivativeSecurityListMsg50 fixml = (DerivativeSecurityListMsg50) fragment;
        if (fixml.getSecurityReqID() != null) {
            securityReqID = fixml.getSecurityReqID();
        }
        if (fixml.getSecurityResponseID() != null) {
            securityResponseID = fixml.getSecurityResponseID();
        }
        if (fixml.getSecurityRequestResult() != null) {
            securityRequestResult = fixml.getSecurityRequestResult();
        }
        if (fixml.getUnderlyingInstrument() != null) {
            setUnderlyingInstrument(fixml.getUnderlyingInstrument());
        }
        if (fixml.getTotNoRelatedSym() != null) {
            totNoRelatedSym = fixml.getTotNoRelatedSym();
        }
        if (fixml.getLastFragment() != null) {
            lastFragment = fixml.getLastFragment();
        }
        if (fixml.getDerivSecListGroups() != null && fixml.getDerivSecListGroups().length > 0) {
            setDerivSecListGroups(fixml.getDerivSecListGroups());
        }
    }

    // ACCESSOR METHODS
    //////////////////////////////////////////

    @XmlElementRef
    @Override
    public Header getHeader() {
        return header;
    }

    @Override
    public void setHeader(Header header) {
        this.header = header;
    }

    @XmlAttribute(name = "ReqID")
    @Override
    public String getSecurityReqID() {
        return securityReqID;
    }

    @Override
    public void setSecurityReqID(String securityReqID) {
        this.securityReqID = securityReqID;
    }

    @XmlAttribute(name = "RspID")
    @Override
    public String getSecurityResponseID() {
        return securityResponseID;
    }

    @Override
    public void setSecurityResponseID(String securityResponseID) {
        this.securityResponseID = securityResponseID;
    }

    @XmlAttribute(name = "ReqRslt")
    @Override
    public SecurityRequestResult getSecurityRequestResult() {
        return securityRequestResult;
    }

    @Override
    public void setSecurityRequestResult(SecurityRequestResult securityRequestResult) {
        this.securityRequestResult = securityRequestResult;
    }

    @XmlElementRef
    @Override
    public UnderlyingInstrument getUnderlyingInstrument() {
        return underlyingInstrument;
    }

    @Override
    public void setUnderlyingInstrument() {
        underlyingInstrument = new UnderlyingInstrument50(new FragmentContext(sessionCharset, messageEncoding, validateRequired));
    }
    
    @Override
    public void clearUnderlyingInstrument() {
        underlyingInstrument = null;
    }
    
    public void setUnderlyingInstrument(UnderlyingInstrument underlyingInstrument) {
        this.underlyingInstrument = underlyingInstrument;
    }

    @XmlAttribute(name = "TotNoReltdSym")
    @Override
    public Integer getTotNoRelatedSym() {
        return totNoRelatedSym;
    }

    @Override
    public void setTotNoRelatedSym(Integer totNoRelatedSym) {
        this.totNoRelatedSym = totNoRelatedSym;
    }

    @XmlAttribute(name = "LastFragment")
    @XmlJavaTypeAdapter(FixBooleanAdapter.class)
    @Override
    public Boolean getLastFragment() {
        return lastFragment;
    }

    @Override
    public void setLastFragment(Boolean lastFragment) {
        this.lastFragment = lastFragment;
    }

    @Override
    public Integer getNoRelatedSyms() {
        return noRelatedSyms;
    }

    @Override
    public void setNoRelatedSyms(Integer noRelatedSyms) {
        this.noRelatedSyms = noRelatedSyms;
        if (noRelatedSyms != null) {
            derivSecListGroups = new DerivSecListGroup[noRelatedSyms.intValue()];
            FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, validateRequired);
            for (int i = 0; i < derivSecListGroups.length; i++) {
                derivSecListGroups[i] = new DerivSecListGroup50(context);
            }
        }
    }

    @XmlElementRef
    @Override
    public DerivSecListGroup[] getDerivSecListGroups() {
        return derivSecListGroups;
    }

    public void setDerivSecListGroups(DerivSecListGroup[] derivSecListGroups) {
        this.derivSecListGroups = derivSecListGroups;
        if (derivSecListGroups != null) {
            noRelatedSyms = new Integer(derivSecListGroups.length);
        }
    }

    @Override
    public DerivSecListGroup addDerivSecListGroup() {
        DerivSecListGroup group = new DerivSecListGroup50(new FragmentContext(sessionCharset, messageEncoding, validateRequired));
        List<DerivSecListGroup> groups = new ArrayList<DerivSecListGroup>();
        if (derivSecListGroups != null && derivSecListGroups.length > 0) {
            groups = new ArrayList<DerivSecListGroup>(Arrays.asList(derivSecListGroups));
        }
        groups.add(group);
        derivSecListGroups = groups.toArray(new DerivSecListGroup[groups.size()]);
        noRelatedSyms = new Integer(derivSecListGroups.length);

        return group;
    }

    @Override
    public DerivSecListGroup deleteDerivSecListGroup(int index) {
        DerivSecListGroup result = null;
        if (derivSecListGroups != null && derivSecListGroups.length > 0 && derivSecListGroups.length > index) {
            List<DerivSecListGroup> groups = new ArrayList<DerivSecListGroup>(Arrays.asList(derivSecListGroups));
            result = groups.remove(index);
            derivSecListGroups = groups.toArray(new DerivSecListGroup[groups.size()]);
            if (derivSecListGroups.length > 0) {
                noRelatedSyms = new Integer(derivSecListGroups.length);
            } else {
                derivSecListGroups = null;
                noRelatedSyms = null;
            }
        }

        return result;
    }

    @Override
    public int clearDerivSecListGroup() {
        int result = 0;
        if (derivSecListGroups != null && derivSecListGroups.length > 0) {
            result = derivSecListGroups.length;
            derivSecListGroups = null;
            noRelatedSyms = null;
        }

        return result;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

    @Override
    protected void setFragmentCompTagValue(Tag tag, ByteBuffer message)
    throws BadFormatMsgException, InvalidMsgException, TagNotPresentException {
        FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, validateRequired);
        if (UNDERLYING_INSTRUMENT_COMP_TAGS.contains(tag.tagNum)) {
            if (underlyingInstrument == null) {
                underlyingInstrument = new UnderlyingInstrument50(context);
            }
            underlyingInstrument.decode(tag, message);
        }
        if (DERIV_SEC_LIST_GROUP_TAGS.contains(tag.tagNum)) {
            if (noRelatedSyms != null && noRelatedSyms.intValue() > 0) {
                message.reset();
                derivSecListGroups = new DerivSecListGroup[noRelatedSyms.intValue()];
                for (int i = 0; i < noRelatedSyms.intValue(); i++) {
                    DerivSecListGroup group = new DerivSecListGroup50(context);
                    group.decode(message);
                    derivSecListGroups[i] = group;
                }
            }
        }
    }

    @Override
    protected String getUnsupportedTagMessage() {
        return "This tag is not supported in [DerivativeSecurityListMsg] message version [5.0].";
    }

    @Override
    protected Set<Integer> getFragmentDataTags() {
        return START_DATA_TAGS;
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
