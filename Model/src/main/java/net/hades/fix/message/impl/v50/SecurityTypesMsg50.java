/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * SecurityTypesMsg50.java
 *
 * $Id: SecurityTypesMsg50.java,v 1.1 2011-04-27 01:09:58 vrotaru Exp $
 */
package net.hades.fix.message.impl.v50;

import net.hades.fix.message.FIXFragment;
import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.Header;
import net.hades.fix.message.SecurityTypesMsg;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.group.SecTypesGroup;
import net.hades.fix.message.group.impl.v50.SecTypesGroup50;
import net.hades.fix.message.struct.Tag;
import net.hades.fix.message.type.ApplVerID;
import net.hades.fix.message.type.BeginString;
import net.hades.fix.message.type.SecurityResponseType;
import net.hades.fix.message.type.SubscriptionRequestType;
import net.hades.fix.message.xml.codec.jaxb.adapter.FixBooleanAdapter;

import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.ArrayList;
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

/**
 * FIX version 5.0 SecurityTypesMsg implementation.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 */
@XmlRootElement(name="SecTyps")
@XmlType(propOrder = {"header", "secTypesGroups"})
@XmlAccessorType(XmlAccessType.NONE)
public class SecurityTypesMsg50 extends SecurityTypesMsg {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> SEC_TYPES_GROUP_TAGS = new SecTypesGroup50().getFragmentAllTags();

    protected static final Set<Integer> START_COMP_TAGS;

    protected static final Set<Integer> ALL_TAGS;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">

    static {
        ALL_TAGS = new HashSet<Integer>(TAGS);
        ALL_TAGS.addAll(START_DATA_TAGS);
        ALL_TAGS.addAll(SEC_TYPES_GROUP_TAGS);
        START_COMP_TAGS = new HashSet<Integer>(SEC_TYPES_GROUP_TAGS);
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public SecurityTypesMsg50() {
        super();
    }

    public SecurityTypesMsg50(Header header, ByteBuffer rawMsg)
    throws InvalidMsgException, TagNotPresentException, BadFormatMsgException {
        super(header, rawMsg);
    }

    public SecurityTypesMsg50(BeginString beginString) throws InvalidMsgException {
        super(beginString);
    }

    public SecurityTypesMsg50(BeginString beginString, ApplVerID applVerID) throws InvalidMsgException {
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
        SecurityTypesMsg50 fixml = (SecurityTypesMsg50) fragment;
        if (fixml.getSecurityReqID() != null) {
            securityReqID = fixml.getSecurityReqID();
        }
        if (fixml.getSecurityResponseID() != null) {
            securityResponseID = fixml.getSecurityResponseID();
        }
        if (fixml.getSecurityResponseType() != null) {
            securityResponseType = fixml.getSecurityResponseType();
        }
        if (fixml.getTotNoSecurityTypes() != null) {
            totNoSecurityTypes = fixml.getTotNoSecurityTypes();
        }
        if (fixml.getLastFragment() != null) {
            lastFragment = fixml.getLastFragment();
        }
        if (fixml.getSecTypesGroups() != null && fixml.getSecTypesGroups().length > 0) {
            setSecTypesGroups(fixml.getSecTypesGroups());
        }
        if (fixml.getText() != null) {
            text = fixml.getText();
        }
        if (fixml.getEncodedTextLen() != null) {
            encodedTextLen = fixml.getEncodedTextLen();
            encodedText = fixml.getEncodedText();
        }
        if (fixml.getTradingSessionID() != null) {
            tradingSessionID = fixml.getTradingSessionID();
        }
        if (fixml.getTradingSessionSubID() != null) {
            tradingSessionSubID = fixml.getTradingSessionSubID();
        }
        if (fixml.getSubscriptionRequestType() != null) {
            subscriptionRequestType = fixml.getSubscriptionRequestType();
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

    @XmlAttribute(name = "RspTyp")
    @Override
    public SecurityResponseType getSecurityResponseType() {
        return securityResponseType;
    }

    @Override
    public void setSecurityResponseType(SecurityResponseType securityResponseType) {
        this.securityResponseType = securityResponseType;
    }

    @XmlAttribute(name = "TotNoSecTyps")
    @Override
    public Integer getTotNoSecurityTypes() {
        return totNoSecurityTypes;
    }

    @Override
    public void setTotNoSecurityTypes(Integer totNoSecurityTypes) {
        this.totNoSecurityTypes = totNoSecurityTypes;
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
    public Integer getNoSecurityTypes() {
        return noSecurityTypes;
    }

    @Override
    public void setNoSecurityTypes(Integer noSecurityTypes) {
        this.noSecurityTypes = noSecurityTypes;
        if (noSecurityTypes != null) {
            secTypesGroups = new SecTypesGroup[noSecurityTypes.intValue()];
            FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, validateRequired);
            for (int i = 0; i < secTypesGroups.length; i++) {
                secTypesGroups[i] = new SecTypesGroup50(context);
            }
        }
    }

    @XmlElementRef
    @Override
    public SecTypesGroup[] getSecTypesGroups() {
        return secTypesGroups;
    }

    public void setSecTypesGroups(SecTypesGroup[] secTypesGroups) {
        this.secTypesGroups = secTypesGroups;
        if (secTypesGroups != null) {
            noSecurityTypes = new Integer(secTypesGroups.length);
        }
    }

    @Override
    public SecTypesGroup addSecTypesGroup() {
        SecTypesGroup group = new SecTypesGroup50(new FragmentContext(sessionCharset, messageEncoding, validateRequired));
        List<SecTypesGroup> groups = new ArrayList<SecTypesGroup>();
        if (secTypesGroups != null && secTypesGroups.length > 0) {
            groups = new ArrayList<SecTypesGroup>(Arrays.asList(secTypesGroups));
        }
        groups.add(group);
        secTypesGroups = groups.toArray(new SecTypesGroup[groups.size()]);
        noSecurityTypes = new Integer(secTypesGroups.length);

        return group;
    }

    @Override
    public SecTypesGroup deleteSecTypesGroup(int index) {
        SecTypesGroup result = null;
        if (secTypesGroups != null && secTypesGroups.length > 0 && secTypesGroups.length > index) {
            List<SecTypesGroup> groups = new ArrayList<SecTypesGroup>(Arrays.asList(secTypesGroups));
            result = groups.remove(index);
            secTypesGroups = groups.toArray(new SecTypesGroup[groups.size()]);
            if (secTypesGroups.length > 0) {
                noSecurityTypes = new Integer(secTypesGroups.length);
            } else {
                secTypesGroups = null;
                noSecurityTypes = null;
            }
        }

        return result;
    }

    @Override
    public int clearSecTypesGroups() {
        int result = 0;
        if (secTypesGroups != null && secTypesGroups.length > 0) {
            result = secTypesGroups.length;
            secTypesGroups = null;
            noSecurityTypes = null;
        }

        return result;
    }

    @XmlAttribute(name = "Txt")
    @Override
    public String getText() {
        return text;
    }

    @Override
    public void setText(String text) {
        this.text = text;
    }

    @XmlAttribute(name = "EncTxtLen")
    @Override
    public Integer getEncodedTextLen() {
        return encodedTextLen;
    }

    @Override
    public void setEncodedTextLen(Integer encodedTextLen) {
        this.encodedTextLen = encodedTextLen;
    }

    @XmlAttribute(name = "EncTxt")
    @Override
    public byte[] getEncodedText() {
        return encodedText;
    }

    @Override
    public void setEncodedText(byte[] encodedText) {
        this.encodedText = encodedText;
        if (encodedTextLen == null) {
            encodedTextLen = new Integer(encodedText.length);
        }
    }

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

    @XmlAttribute(name = "SubReqTyp")
    @Override
    public SubscriptionRequestType getSubscriptionRequestType() {
        return subscriptionRequestType;
    }

    @Override
    public void setSubscriptionRequestType(SubscriptionRequestType subscriptionRequestType) {
        this.subscriptionRequestType = subscriptionRequestType;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

    @Override
    protected void setFragmentCompTagValue(Tag tag, ByteBuffer message)
    throws BadFormatMsgException, InvalidMsgException, TagNotPresentException {
        FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, validateRequired);
        if (SEC_TYPES_GROUP_TAGS.contains(tag.tagNum)) {
            if (noSecurityTypes != null && noSecurityTypes.intValue() > 0) {
                message.reset();
                secTypesGroups = new SecTypesGroup[noSecurityTypes.intValue()];
                for (int i = 0; i < noSecurityTypes.intValue(); i++) {
                    SecTypesGroup component = new SecTypesGroup50(context);
                    component.decode(message);
                    secTypesGroups[i] = component;
                }
            }
        }
    }

    @Override
    protected String getUnsupportedTagMessage() {
        return "This tag is not supported in [SecurityTypesMsg] message version [5.0].";
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
