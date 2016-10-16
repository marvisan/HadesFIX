/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * DerivativeSecurityListMsg50SP1.java
 *
 * $Id: DerivativeSecurityListMsg50SP1.java,v 1.1 2011-09-27 08:57:27 vrotaru Exp $
 */
package net.hades.fix.message.impl.v50sp1;

import net.hades.fix.message.DerivativeSecurityListMsg;
import net.hades.fix.message.FIXFragment;
import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.Header;
import net.hades.fix.message.comp.ApplicationSequenceControl;
import net.hades.fix.message.comp.DerivativeSecurityDefinition;
import net.hades.fix.message.comp.UnderlyingInstrument;
import net.hades.fix.message.comp.impl.v50sp1.ApplicationSequenceControl50SP1;
import net.hades.fix.message.comp.impl.v50sp1.DerivativeSecurityDefinition50SP1;
import net.hades.fix.message.comp.impl.v50sp1.UnderlyingInstrument50SP1;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.group.DerivSecListGroup;
import net.hades.fix.message.struct.Tag;
import net.hades.fix.message.type.ApplVerID;
import net.hades.fix.message.type.BeginString;
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

import net.hades.fix.message.group.impl.v50sp1.DerivSecListGroup50SP1;

/**
 * FIX version 5.0SP1 DerivativeSecurityListMsg implementation.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 28/04/2011, 9:32:41 AM
 */
@XmlRootElement(name="DerivSecList")
@XmlType(propOrder = {"header", "applicationSequenceControl", "underlyingInstrument", "derivativeSecurityDefinition", "derivSecListGroups"})
@XmlAccessorType(XmlAccessType.NONE)
public class DerivativeSecurityListMsg50SP1 extends DerivativeSecurityListMsg {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> APPL_SEQ_CONTROL_COMP_TAGS = new ApplicationSequenceControl50SP1().getFragmentAllTags();
    protected static final Set<Integer> UNDERLYING_INSTRUMENT_COMP_TAGS = new UnderlyingInstrument50SP1().getFragmentAllTags();
    protected static final Set<Integer> DERIV_SEC_DEF_COMP_TAGS = new DerivativeSecurityDefinition50SP1().getFragmentAllTags();
    protected static final Set<Integer> DERIV_SEC_LIST_GROUP_TAGS = new DerivSecListGroup50SP1().getFragmentAllTags();

    protected static final Set<Integer> START_COMP_TAGS;

    protected static final Set<Integer> ALL_TAGS;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">

    static {
        ALL_TAGS = new HashSet<Integer>(TAGS);
        ALL_TAGS.addAll(APPL_SEQ_CONTROL_COMP_TAGS);
        ALL_TAGS.addAll(UNDERLYING_INSTRUMENT_COMP_TAGS);
        ALL_TAGS.addAll(DERIV_SEC_DEF_COMP_TAGS);
        ALL_TAGS.addAll(DERIV_SEC_LIST_GROUP_TAGS);
        START_COMP_TAGS = new HashSet<Integer>(UNDERLYING_INSTRUMENT_COMP_TAGS);
        START_COMP_TAGS.addAll(APPL_SEQ_CONTROL_COMP_TAGS);
        START_COMP_TAGS.addAll(DERIV_SEC_DEF_COMP_TAGS);
        START_COMP_TAGS.addAll(DERIV_SEC_LIST_GROUP_TAGS);
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public DerivativeSecurityListMsg50SP1() {
        super();
    }

    public DerivativeSecurityListMsg50SP1(Header header, ByteBuffer rawMsg)
    throws InvalidMsgException, TagNotPresentException, BadFormatMsgException {
        super(header, rawMsg);
    }

    public DerivativeSecurityListMsg50SP1(BeginString beginString) throws InvalidMsgException {
        super(beginString);
    }

    public DerivativeSecurityListMsg50SP1(BeginString beginString, ApplVerID applVerID) throws InvalidMsgException {
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
        DerivativeSecurityListMsg50SP1 fixml = (DerivativeSecurityListMsg50SP1) fragment;
        if (fixml.getApplicationSequenceControl() != null) {
            setApplicationSequenceControl(fixml.getApplicationSequenceControl());
        }
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
        if (fixml.getDerivativeSecurityDefinition() != null) {
            setDerivativeSecurityDefinition(fixml.getDerivativeSecurityDefinition());
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

    @XmlElementRef
    @Override
    public ApplicationSequenceControl getApplicationSequenceControl() {
        return applicationSequenceControl;
    }

    @Override
    public void setApplicationSequenceControl() {
        this.applicationSequenceControl = new ApplicationSequenceControl50SP1(new FragmentContext(sessionCharset, messageEncoding, validateRequired));
    }

    public void setApplicationSequenceControl(ApplicationSequenceControl applicationSequenceControl) {
        this.applicationSequenceControl = applicationSequenceControl;
    }

    @Override
    public void clearApplicationSequenceControl() {
        this.applicationSequenceControl = null;
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
        underlyingInstrument = new UnderlyingInstrument50SP1(new FragmentContext(sessionCharset, messageEncoding, validateRequired));
    }
    
    @Override
    public void clearUnderlyingInstrument() {
        underlyingInstrument = null;
    }
    
    public void setUnderlyingInstrument(UnderlyingInstrument underlyingInstrument) {
        this.underlyingInstrument = underlyingInstrument;
    }

    @XmlElementRef
    @Override
    public DerivativeSecurityDefinition getDerivativeSecurityDefinition() {
        return derivativeSecurityDefinition;
    }
    
    @Override
    public void setDerivativeSecurityDefinition() {
        this.derivativeSecurityDefinition = new DerivativeSecurityDefinition50SP1(new FragmentContext(sessionCharset, messageEncoding, validateRequired));
    }
    
    @Override
    public void clearDerivativeSecurityDefinition() {
        this.derivativeSecurityDefinition = null;
    }

    public void setDerivativeSecurityDefinition(DerivativeSecurityDefinition derivativeSecurityDefinition) {
        this.derivativeSecurityDefinition = derivativeSecurityDefinition;
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
                derivSecListGroups[i] = new DerivSecListGroup50SP1(context);
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
        DerivSecListGroup group = new DerivSecListGroup50SP1(new FragmentContext(sessionCharset, messageEncoding, validateRequired));
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
        if (APPL_SEQ_CONTROL_COMP_TAGS.contains(tag.tagNum)) {
            if (applicationSequenceControl == null) {
                applicationSequenceControl = new ApplicationSequenceControl50SP1(context);
            }
            applicationSequenceControl.decode(tag, message);
        }
        if (UNDERLYING_INSTRUMENT_COMP_TAGS.contains(tag.tagNum)) {
            if (underlyingInstrument == null) {
                underlyingInstrument = new UnderlyingInstrument50SP1(context);
            }
            underlyingInstrument.decode(tag, message);
        }
        if (DERIV_SEC_DEF_COMP_TAGS.contains(tag.tagNum)) {
            if (derivativeSecurityDefinition == null) {
                derivativeSecurityDefinition = new DerivativeSecurityDefinition50SP1(context);
            }
            derivativeSecurityDefinition.decode(tag, message);
        }
        if (DERIV_SEC_LIST_GROUP_TAGS.contains(tag.tagNum)) {
            if (noRelatedSyms != null && noRelatedSyms.intValue() > 0) {
                message.reset();
                derivSecListGroups = new DerivSecListGroup[noRelatedSyms.intValue()];
                for (int i = 0; i < noRelatedSyms.intValue(); i++) {
                    DerivSecListGroup group = new DerivSecListGroup50SP1(context);
                    group.decode(message);
                    derivSecListGroups[i] = group;
                }
            }
        }
    }

    @Override
    protected String getUnsupportedTagMessage() {
        return "This tag is not supported in [DerivativeSecurityListMsg] message version [5.0SP1].";
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
