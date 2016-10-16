/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * SecurityListMsg50SP2.java
 *
 * $Id: SecurityListMsg50SP2.java,v 1.2 2011-04-29 03:11:04 vrotaru Exp $
 */
package net.hades.fix.message.impl.v50sp2;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.struct.Tag;
import net.hades.fix.message.type.ApplVerID;
import net.hades.fix.message.type.SecurityListTypeSource;
import net.hades.fix.message.xml.codec.jaxb.adapter.FixUTCDateTimeAdapter;

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

import net.hades.fix.message.FIXFragment;
import net.hades.fix.message.Header;
import net.hades.fix.message.SecurityListMsg;
import net.hades.fix.message.comp.ApplicationSequenceControl;
import net.hades.fix.message.comp.impl.v50sp2.ApplicationSequenceControl50SP2;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.group.SecListGroup;
import net.hades.fix.message.group.impl.v50sp2.SecListGroup50SP2;
import net.hades.fix.message.type.BeginString;
import net.hades.fix.message.type.SecurityListType;
import net.hades.fix.message.type.SecurityRequestResult;
import net.hades.fix.message.xml.codec.jaxb.adapter.FixBooleanAdapter;
import net.hades.fix.message.xml.codec.jaxb.adapter.FixDateAdapter;

/**
 * FIX version 5.0SP2 SecurityListMsg implementation.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 * @created 28/04/2011, 9:32:41 AM
 */
@XmlRootElement(name="SecList")
@XmlType(propOrder = {"header", "applicationSequenceControl", "secListGroups"})
@XmlAccessorType(XmlAccessType.NONE)
public class SecurityListMsg50SP2 extends SecurityListMsg {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> APPL_SEQ_CONTROL_COMP_TAGS = new ApplicationSequenceControl50SP2().getFragmentAllTags();
    protected static final Set<Integer> SEC_LIST_GROUP_TAGS = new SecListGroup50SP2().getFragmentAllTags();

    protected static final Set<Integer> START_COMP_TAGS;

    protected static final Set<Integer> ALL_TAGS;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">

    static {
        ALL_TAGS = new HashSet<Integer>(TAGS);
        ALL_TAGS.addAll(START_DATA_TAGS);
        ALL_TAGS.addAll(SEC_LIST_GROUP_TAGS);
        ALL_TAGS.addAll(APPL_SEQ_CONTROL_COMP_TAGS);
        START_COMP_TAGS = new HashSet<Integer>(SEC_LIST_GROUP_TAGS);
        START_COMP_TAGS.addAll(APPL_SEQ_CONTROL_COMP_TAGS);
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public SecurityListMsg50SP2() {
        super();
    }

    public SecurityListMsg50SP2(Header header, ByteBuffer rawMsg)
    throws InvalidMsgException, TagNotPresentException, BadFormatMsgException {
        super(header, rawMsg);
    }

    public SecurityListMsg50SP2(BeginString beginString) throws InvalidMsgException {
        super(beginString);
    }

    public SecurityListMsg50SP2(BeginString beginString, ApplVerID applVerID) throws InvalidMsgException {
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
        SecurityListMsg50SP2 fixml = (SecurityListMsg50SP2) fragment;
        if (fixml.getApplicationSequenceControl() != null) {
            setApplicationSequenceControl(fixml.getApplicationSequenceControl());
        }
        if (fixml.getSecurityReportID() != null) {
            securityReportID = fixml.getSecurityReportID();
        }
        if (fixml.getClearingBusinessDate() != null) {
            clearingBusinessDate = fixml.getClearingBusinessDate();
        }
        if (fixml.getSecurityListID() != null) {
            securityListID = fixml.getSecurityListID();
        }
        if (fixml.getSecurityListRefID() != null) {
            securityListRefID = fixml.getSecurityListRefID();
        }
        if (fixml.getSecurityListDesc() != null) {
            securityListDesc = fixml.getSecurityListDesc();
        }
        if (fixml.getEncodedSecurityListDescLen() != null) {
            encodedSecurityListDescLen = fixml.getEncodedSecurityListDescLen();
            encodedSecurityListDesc = fixml.getEncodedSecurityListDesc();
        }
        if (fixml.getSecurityListType() != null) {
            securityListType = fixml.getSecurityListType();
        }
        if (fixml.getSecurityListTypeSource() != null) {
            securityListTypeSource = fixml.getSecurityListTypeSource();
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
        if (fixml.getTransactTime() != null) {
            transactTime = fixml.getTransactTime();
        }
        if (fixml.getTotNoRelatedSym() != null) {
            totNoRelatedSym = fixml.getTotNoRelatedSym();
        }
        if (fixml.getMarketID() != null) {
            marketID = fixml.getMarketID();
        }
        if (fixml.getMarketSegmentID() != null) {
            marketSegmentID = fixml.getMarketSegmentID();
        }
        if (fixml.getLastFragment() != null) {
            lastFragment = fixml.getLastFragment();
        }
        if (fixml.getSecListGroups() != null && fixml.getSecListGroups().length > 0) {
            setSecListGroups(fixml.getSecListGroups());
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
        this.applicationSequenceControl = new ApplicationSequenceControl50SP2(new FragmentContext(sessionCharset, messageEncoding, validateRequired));
    }

    public void setApplicationSequenceControl(ApplicationSequenceControl applicationSequenceControl) {
        this.applicationSequenceControl = applicationSequenceControl;
    }

    @Override
    public void clearApplicationSequenceControl() {
        this.applicationSequenceControl = null;
    }

    @XmlAttribute(name = "RptID")
    @Override
    public Integer getSecurityReportID() {
        return securityReportID;
    }

    @Override
    public void setSecurityReportID(Integer securityReportID) {
        this.securityReportID = securityReportID;
    }

    @XmlAttribute(name = "BizDt")
    @XmlJavaTypeAdapter(FixDateAdapter.class)
    @Override
    public Date getClearingBusinessDate() {
        return clearingBusinessDate;
    }

    @Override
    public void setClearingBusinessDate(Date clearingBusinessDate) {
        this.clearingBusinessDate = clearingBusinessDate;
    }

    @XmlAttribute(name = "ListID")
    @Override
    public String getSecurityListID() {
        return securityListID;
    }

    @Override
    public void setSecurityListID(String securityListID) {
        this.securityListID = securityListID;
    }

    @XmlAttribute(name = "ListRefID")
    @Override
    public String getSecurityListRefID() {
        return securityListRefID;
    }

    @Override
    public void setSecurityListRefID(String securityListRefID) {
        this.securityListRefID = securityListRefID;
    }

    @XmlAttribute(name = "ListDesc")
    @Override
    public String getSecurityListDesc() {
        return securityListDesc;
    }

    @Override
    public void setSecurityListDesc(String securityListDesc) {
        this.securityListDesc = securityListDesc;
    }

    @Override
    public Integer getEncodedSecurityListDescLen() {
        return encodedSecurityListDescLen;
    }

    @Override
    public void setEncodedSecurityListDescLen(Integer encodedSecurityListDescLen) {
        this.encodedSecurityListDescLen = encodedSecurityListDescLen;
    }

    @Override
    public byte[] getEncodedSecurityListDesc() {
        return encodedSecurityListDesc;
    }

    @Override
    public void setEncodedSecurityListDesc(byte[] encodedSecurityListDesc) {
        this.encodedSecurityListDesc = encodedSecurityListDesc;
        if (encodedSecurityListDescLen == null) {
            encodedSecurityListDescLen = new Integer(encodedSecurityListDesc.length);
        }
    }

    @XmlAttribute(name = "ListTyp")
    @Override
    public SecurityListType getSecurityListType() {
        return securityListType;
    }

    @Override
    public void setSecurityListType(SecurityListType securityListType) {
        this.securityListType = securityListType;
    }

    @XmlAttribute(name = "LstTypSrc")
    @Override
    public SecurityListTypeSource getSecurityListTypeSource() {
        return securityListTypeSource;
    }

    @Override
    public void setSecurityListTypeSource(SecurityListTypeSource securityListTypeSource) {
        this.securityListTypeSource = securityListTypeSource;
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

    @XmlAttribute(name = "TxnTm")
    @XmlJavaTypeAdapter(FixUTCDateTimeAdapter.class)
    @Override
    public Date getTransactTime() {
        return transactTime;
    }

    @Override
    public void setTransactTime(Date transactTime) {
        this.transactTime = transactTime;
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

    @XmlAttribute(name = "MktID")
    @Override
    public String getMarketID() {
        return marketID;
    }

    @Override
    public void setMarketID(String marketID) {
        this.marketID = marketID;
    }

    @XmlAttribute(name = "MktSegID")
    @Override
    public String getMarketSegmentID() {
        return marketSegmentID;
    }

    @Override
    public void setMarketSegmentID(String marketSegmentID) {
        this.marketSegmentID = marketSegmentID;
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
            secListGroups = new SecListGroup[noRelatedSyms.intValue()];
            FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, validateRequired);
            for (int i = 0; i < secListGroups.length; i++) {
                secListGroups[i] = new SecListGroup50SP2(context);
            }
        }
    }

    @XmlElementRef
    @Override
    public SecListGroup[] getSecListGroups() {
        return secListGroups;
    }

    public void setSecListGroups(SecListGroup[] secListGroups) {
        this.secListGroups = secListGroups;
        if (secListGroups != null) {
            noRelatedSyms = new Integer(secListGroups.length);
        }
    }

    @Override
    public SecListGroup addSecListGroup() {
        SecListGroup group = new SecListGroup50SP2(new FragmentContext(sessionCharset, messageEncoding, validateRequired));
        List<SecListGroup> groups = new ArrayList<SecListGroup>();
        if (secListGroups != null && secListGroups.length > 0) {
            groups = new ArrayList<SecListGroup>(Arrays.asList(secListGroups));
        }
        groups.add(group);
        secListGroups = groups.toArray(new SecListGroup[groups.size()]);
        noRelatedSyms = new Integer(secListGroups.length);

        return group;
    }

    @Override
    public SecListGroup deleteSecListGroup(int index) {
        SecListGroup result = null;
        if (secListGroups != null && secListGroups.length > 0 && secListGroups.length > index) {
            List<SecListGroup> groups = new ArrayList<SecListGroup>(Arrays.asList(secListGroups));
            result = groups.remove(index);
            secListGroups = groups.toArray(new SecListGroup[groups.size()]);
            if (secListGroups.length > 0) {
                noRelatedSyms = new Integer(secListGroups.length);
            } else {
                secListGroups = null;
                noRelatedSyms = null;
            }
        }

        return result;
    }

    @Override
    public int clearSecListGroup() {
        int result = 0;
        if (secListGroups != null && secListGroups.length > 0) {
            result = secListGroups.length;
            secListGroups = null;
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
                applicationSequenceControl = new ApplicationSequenceControl50SP2(context);
            }
            applicationSequenceControl.decode(tag, message);
        }
        if (SEC_LIST_GROUP_TAGS.contains(tag.tagNum)) {
            if (noRelatedSyms != null && noRelatedSyms.intValue() > 0) {
                message.reset();
                secListGroups = new SecListGroup[noRelatedSyms.intValue()];
                for (int i = 0; i < noRelatedSyms.intValue(); i++) {
                    SecListGroup group = new SecListGroup50SP2(context);
                    group.decode(message);
                    secListGroups[i] = group;
                }
            }
        }
    }

    @Override
    protected String getUnsupportedTagMessage() {
        return "This tag is not supported in [SecurityListMsg] message version [5.0SP2].";
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
