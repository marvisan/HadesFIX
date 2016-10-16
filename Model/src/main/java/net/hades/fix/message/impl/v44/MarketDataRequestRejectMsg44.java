/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * MarketDataRequestRejectMsg44.java
 *
 * $Id: MarketDataRequestRejectMsg44.java,v 1.10 2011-04-14 23:44:39 vrotaru Exp $
 */
package net.hades.fix.message.impl.v44;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.Header;
import net.hades.fix.message.MarketDataRequestRejectMsg;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.group.impl.v44.AltMDSourceGroup44;
import net.hades.fix.message.struct.Tag;
import net.hades.fix.message.type.ApplVerID;
import net.hades.fix.message.type.MDReqRejReason;
import net.hades.fix.message.type.SessionRejectReason;
import net.hades.fix.message.type.TagNum;
import net.hades.fix.message.util.MsgUtil;

import java.io.ByteArrayOutputStream;
import java.nio.ByteBuffer;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;

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
import net.hades.fix.message.type.BeginString;
import net.hades.fix.message.util.TagEncoder;

/**
 * FIX version 4.4 MarketDataRequestRejectMsg implementation.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.10 $
 * @created 01/04/2009, 8:41:14 AM
 */
@XmlRootElement(name="MktDataReqRej")
@XmlType(propOrder = {"header", "altMDSourceGroups"})
@XmlAccessorType(XmlAccessType.NONE)
public class MarketDataRequestRejectMsg44 extends MarketDataRequestRejectMsg {
    
    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = -570648070408397548L;

    protected static final Set<Integer> START_COMP_TAGS;
    
    protected static final Set<Integer> ALL_TAGS;

    protected static final Set<Integer> ALT_MD_SOURCE_GROUP_TAGS = new AltMDSourceGroup44().getFragmentAllTags();
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Static Block">
    
    static {
        ALL_TAGS = new HashSet<Integer>(TAGS);
        ALL_TAGS.addAll(ALT_MD_SOURCE_GROUP_TAGS);
        ALL_TAGS.addAll(START_DATA_TAGS);
        START_COMP_TAGS = new HashSet<Integer>(ALT_MD_SOURCE_GROUP_TAGS);
    }
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Attributes">

    protected Set<Integer> STANDARD_SECURED_TAGS = ALL_TAGS;
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Constructors">
    
    public MarketDataRequestRejectMsg44() {
        super();
        SECURED_TAGS = STANDARD_SECURED_TAGS;
    }
    
    public MarketDataRequestRejectMsg44(Header header, ByteBuffer rawMsg)
        throws InvalidMsgException, TagNotPresentException, BadFormatMsgException {
        super(header, rawMsg);
        SECURED_TAGS = STANDARD_SECURED_TAGS;
    }

    public MarketDataRequestRejectMsg44(BeginString beginString) throws InvalidMsgException {
        super(beginString);
        SECURED_TAGS = STANDARD_SECURED_TAGS;
    }
    
    public MarketDataRequestRejectMsg44(BeginString beginString, ApplVerID applVerID) throws InvalidMsgException {
        super(beginString, applVerID);
        SECURED_TAGS = STANDARD_SECURED_TAGS;
    }
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Public Methods">

    @Override
    public Set<Integer> getFragmentAllTags() {
        return ALL_TAGS;
    }

    @Override
    public void copyFixmlData(FIXFragment fragment) {
        MarketDataRequestRejectMsg44 fixml = (MarketDataRequestRejectMsg44) fragment;
        if (fixml.getMdReqID() != null) {
            mdReqID = fixml.getMdReqID();
        }
        if (fixml.getMdReqRejReason() != null) {
            mdReqRejReason = fixml.getMdReqRejReason();
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
    public Integer getNoAltMDSource() {
        return noAltMDSource;
    }

    @Override
    public void setNoAltMDSource(Integer noAltMDSource) {
        this.noAltMDSource = noAltMDSource;
        if (noAltMDSource != null) {
            altMDSourceGroups = new AltMDSourceGroup[noAltMDSource.intValue()];
            FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired);
            for (int i = 0; i < altMDSourceGroups.length; i++) {
                altMDSourceGroups[i] = new AltMDSourceGroup44(context);
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
        AltMDSourceGroup group = new AltMDSourceGroup44(new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired));
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
    protected byte[] encodeFragmentSecured(boolean secured) throws TagNotPresentException, BadFormatMsgException {
        byte[] result = new byte[0];
        ByteArrayOutputStream bao = new ByteArrayOutputStream();
        try {
            if (MsgUtil.isTagInList(TagNum.MDReqID, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.MDReqID, mdReqID);
            }
            if (mdReqRejReason != null && MsgUtil.isTagInList(TagNum.MDReqRejReason, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.MDReqRejReason, mdReqRejReason.getValue());
            }
            if (noAltMDSource != null) {
                if (MsgUtil.isTagInList(TagNum.NoAltMDSource, SECURED_TAGS, secured)) {
                    TagEncoder.encode(bao, TagNum.NoAltMDSource, noAltMDSource);
                    if (altMDSourceGroups != null && altMDSourceGroups.length == noAltMDSource.intValue()) {
                        for (int i = 0; i < noAltMDSource.intValue(); i++) {
                            if (altMDSourceGroups[i] != null) {
                                bao.write(altMDSourceGroups[i].encode(getMsgSecureTypeForFlag(secured)));
                            }
                        }
                    } else {
                        String error = "AltMDSourceGroups field has been set but there is no data or the number of groups does not match.";
                        LOGGER.severe(error);
                        throw new BadFormatMsgException(SessionRejectReason.IncorrectCountForGroups, TagNum.NoAltMDSource.getValue(), error);
                    }
                }
            }
            if (MsgUtil.isTagInList(TagNum.Text, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.Text, text);
            }
            if (encodedTextLen != null && encodedTextLen.intValue() > 0 && MsgUtil.isTagInList(TagNum.EncodedTextLen, SECURED_TAGS, secured)) {
                if (encodedText != null && encodedText.length > 0) {
                    encodedTextLen = new Integer(encodedText.length);
                    TagEncoder.encode(bao, TagNum.EncodedTextLen, encodedTextLen);
                    TagEncoder.encode(bao, TagNum.EncodedText, encodedText);
                }
            }
            result = bao.toByteArray();
        } catch (IOException ex) {
            String error = "Error writing to the byte array.";
            LOGGER.log(Level.SEVERE, "{0} Error was : {1}", new Object[] { error, ex.toString() });
            throw new BadFormatMsgException(error, ex);
        }

        return result;
    }

    @Override
    protected void setFragmentCompTagValue(Tag tag, ByteBuffer message)
        throws BadFormatMsgException, InvalidMsgException, TagNotPresentException {
        FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired);
        if (ALT_MD_SOURCE_GROUP_TAGS.contains(tag.tagNum)) {
            if (noAltMDSource != null && noAltMDSource.intValue() > 0) {
                message.reset();
                altMDSourceGroups = new AltMDSourceGroup[noAltMDSource.intValue()];
                for (int i = 0; i < noAltMDSource.intValue(); i++) {
                    AltMDSourceGroup group = new AltMDSourceGroup44(context);
                    group.decode(message);
                    altMDSourceGroups[i] = group;
                }
            }
        }
    }

    @Override
    protected String getUnsupportedTagMessage() {
        return "This tag is not supported in [MarketDataRequestRejectMsg] message version [4.4].";
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
