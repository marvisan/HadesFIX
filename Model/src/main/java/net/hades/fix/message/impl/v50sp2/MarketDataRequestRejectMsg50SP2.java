/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * MarketDataRequestRejectMsg50SP2.java
 *
 * $Id: MarketDataRequestRejectMsg50SP2.java,v 1.9 2011-04-14 23:44:29 vrotaru Exp $
 */
package net.hades.fix.message.impl.v50sp2;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.Header;
import net.hades.fix.message.MarketDataRequestRejectMsg;
import net.hades.fix.message.comp.Parties;
import net.hades.fix.message.comp.impl.v50sp2.Parties50SP2;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.struct.Tag;
import net.hades.fix.message.type.ApplVerID;
import net.hades.fix.message.type.BeginString;
import net.hades.fix.message.type.MDReqRejReason;

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

import net.hades.fix.message.FIXFragment;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.group.AltMDSourceGroup;
import net.hades.fix.message.group.PartyGroup;
import net.hades.fix.message.group.impl.v50sp2.AltMDSourceGroup50SP2;

/**
 * FIX version 5.0SP2 MarketDataRequestRejectMsg implementation.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.9 $
 * @created 01/04/2009, 8:41:14 AM
 */
@XmlRootElement(name="MktDataReqRej")
@XmlType(propOrder = {"header", "partyIDGroups", "altMDSourceGroups"})
@XmlAccessorType(XmlAccessType.NONE)
public class MarketDataRequestRejectMsg50SP2 extends MarketDataRequestRejectMsg {
    
    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = -7371200632249247143L;

    protected static final Set<Integer> START_COMP_TAGS;
    
    protected static final Set<Integer> ALL_TAGS;

    protected static final Set<Integer> ALT_MD_SOURCE_GROUP_TAGS = new AltMDSourceGroup50SP2().getFragmentAllTags();
    protected static final Set<Integer> PARTIES_COMP_TAGS = new Parties50SP2().getFragmentAllTags();
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Static Block">
    
    static {
        ALL_TAGS = new HashSet<Integer>(TAGS);
        ALL_TAGS.addAll(ALT_MD_SOURCE_GROUP_TAGS);
        ALL_TAGS.addAll(PARTIES_COMP_TAGS);
        ALL_TAGS.addAll(START_DATA_TAGS);
        START_COMP_TAGS = new HashSet<Integer>(ALT_MD_SOURCE_GROUP_TAGS);
        START_COMP_TAGS.addAll(PARTIES_COMP_TAGS);
    }
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Attributes">
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Constructors">
    
    public MarketDataRequestRejectMsg50SP2() {
        super();
    }
    
    public MarketDataRequestRejectMsg50SP2(Header header, ByteBuffer rawMsg)
        throws InvalidMsgException, TagNotPresentException, BadFormatMsgException {
        super(header, rawMsg);
    }

    public MarketDataRequestRejectMsg50SP2(BeginString beginString) throws InvalidMsgException {
        super(beginString);
    }
    
    public MarketDataRequestRejectMsg50SP2(BeginString beginString, ApplVerID applVerID) throws InvalidMsgException {
        super(beginString, applVerID);
    }
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Public Methods">

    @Override
    public Set<Integer> getFragmentAllTags() {
        return ALL_TAGS;
    }

    @Override
    public void copyFixmlData(FIXFragment fragment) {
        MarketDataRequestRejectMsg fixml = (MarketDataRequestRejectMsg50SP2) fragment;
        if (fixml.getMdReqID() != null) {
            mdReqID = fixml.getMdReqID();
        }
        if (fixml.getMdReqRejReason() != null) {
            mdReqRejReason = fixml.getMdReqRejReason();
        }
        if (fixml.getParties() != null) {
            parties = fixml.getParties();
        }
        if (fixml.getAltMDSourceGroups() != null && fixml.getAltMDSourceGroups().length > 0) {
            setAltMDSourceGroups(fixml.getAltMDSourceGroups());
        }
        if (fixml.getText() != null) {
            text = fixml.getText();
        }
        if (fixml.getEncodedTextLen() != null) {
            encodedTextLen = fixml.getEncodedTextLen();
            encodedText = fixml.getEncodedText();
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
    public String getMdReqID() {
        return mdReqID;
    }

    @Override
    public void setMdReqID(String mdReqID) {
        this.mdReqID = mdReqID;
    }

    @XmlAttribute(name = "ReqRejResn")
    @Override
    public MDReqRejReason getMdReqRejReason() {
        return mdReqRejReason;
    }

    @Override
    public void setMdReqRejReason(MDReqRejReason mdReqRejReason) {
        this.mdReqRejReason = mdReqRejReason;
    }

    @Override
    public Parties getParties() {
        return parties;
    }

    @Override
    public void setParties() {
        FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, validateRequired);
        this.parties = new Parties50SP2(context);
    }

    @Override
    public void clearParties() {
        this.parties = null;
    }

    @XmlElementRef
    public PartyGroup[] getPartyIDGroups() {
        return parties == null ? null : parties.getPartyIDGroups();
    }

    public void setPartyIDGroups(PartyGroup[] partyIDGroups) {
        if (partyIDGroups != null) {
            if (parties == null) {
                setParties();
            }
            ((Parties50SP2) parties).setPartyIDGroups(partyIDGroups);
        }
    }

    @Override
    public Integer getNoAltMDSource() {
        return noAltMDSource;
    }

    @Override
    public void setNoAltMDSource(Integer noAltMDSource) {
        this.noAltMDSource = noAltMDSource;
        if (noAltMDSource != null) {
            altMDSourceGroups = new AltMDSourceGroup[noAltMDSource.intValue()];
            FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, validateRequired);
            for (int i = 0; i < altMDSourceGroups.length; i++) {
                altMDSourceGroups[i] = new AltMDSourceGroup50SP2(context);
            }
        }
    }

    @XmlElementRef
    @Override
    public AltMDSourceGroup[] getAltMDSourceGroups() {
        return altMDSourceGroups;
    }

    public void setAltMDSourceGroups(AltMDSourceGroup[] altMDSourceGroups) {
        this.altMDSourceGroups = altMDSourceGroups;
        if (altMDSourceGroups != null) {
            noAltMDSource = new Integer(altMDSourceGroups.length);
        }
    }

    @Override
    public AltMDSourceGroup addAltMDSourceGroup() {
        AltMDSourceGroup group = new AltMDSourceGroup50SP2(new FragmentContext(sessionCharset, messageEncoding, validateRequired));
        List<AltMDSourceGroup> groups = new ArrayList<AltMDSourceGroup>();
        if (altMDSourceGroups != null && altMDSourceGroups.length > 0) {
            groups = new ArrayList<AltMDSourceGroup>(Arrays.asList(altMDSourceGroups));
        }
        groups.add(group);
        altMDSourceGroups = groups.toArray(new AltMDSourceGroup[groups.size()]);
        noAltMDSource = new Integer(altMDSourceGroups.length);

        return group;
    }

    @Override
    public AltMDSourceGroup deleteAltMDSourceGroup(int index) {
        AltMDSourceGroup result = null;
        if (altMDSourceGroups != null && altMDSourceGroups.length > 0 && altMDSourceGroups.length > index) {
            List<AltMDSourceGroup> groups = new ArrayList<AltMDSourceGroup>(Arrays.asList(altMDSourceGroups));
            result = groups.remove(index);
            altMDSourceGroups = groups.toArray(new AltMDSourceGroup[groups.size()]);
            if (altMDSourceGroups.length > 0) {
                noAltMDSource = new Integer(altMDSourceGroups.length);
            } else {
                altMDSourceGroups = null;
                noAltMDSource = null;
            }
        }

        return result;
    }

    @Override
    public int clearAltMDSourceGroups() {
        int result = 0;
        if (altMDSourceGroups != null && altMDSourceGroups.length > 0) {
            result = altMDSourceGroups.length;
            altMDSourceGroups = null;
            noAltMDSource = null;
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

    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

    @Override
    protected void setFragmentCompTagValue(Tag tag, ByteBuffer message)
        throws BadFormatMsgException, InvalidMsgException, TagNotPresentException {
        FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, validateRequired);
        if (ALT_MD_SOURCE_GROUP_TAGS.contains(tag.tagNum)) {
            if (noAltMDSource != null && noAltMDSource.intValue() > 0) {
                message.reset();
                altMDSourceGroups = new AltMDSourceGroup[noAltMDSource.intValue()];
                for (int i = 0; i < noAltMDSource.intValue(); i++) {
                    AltMDSourceGroup group = new AltMDSourceGroup50SP2(context);
                    group.decode(message);
                    altMDSourceGroups[i] = group;
                }
            }
        }
        if (PARTIES_COMP_TAGS.contains(tag.tagNum)) {
            if (parties == null) {
                parties = new Parties50SP2(context);
            }
            parties.decode(tag, message);
        }
    }

    @Override
    protected String getUnsupportedTagMessage() {
        return "This tag is not supported in [MarketDataRequestRejectMsg] message version [5.0SP2].";
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
