/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * EmailMsg43.java
 *
 * $Id: EmailMsg43.java,v 1.10 2011-04-14 23:44:33 vrotaru Exp $
 */
package net.hades.fix.message.impl.v43;

import net.hades.fix.message.EmailMsg;
import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.Header;
import net.hades.fix.message.comp.Instrument;
import net.hades.fix.message.comp.impl.v43.Instrument43;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.group.LinesOfTextGroup;
import net.hades.fix.message.group.RoutingIDGroup;
import net.hades.fix.message.group.impl.v43.LinesOfTextGroup43;
import net.hades.fix.message.group.impl.v43.RoutingIDGroup43;
import net.hades.fix.message.struct.Tag;
import net.hades.fix.message.util.MsgUtil;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import net.hades.fix.message.type.ApplVerID;
import net.hades.fix.message.type.BeginString;
import net.hades.fix.message.type.EmailType;
import net.hades.fix.message.type.SessionRejectReason;
import net.hades.fix.message.type.TagNum;
import net.hades.fix.message.util.TagEncoder;
import java.util.logging.Level;

/**
 * FIX version 4.3 EmailMsg implementation.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.10 $
 * @created 01/04/2009, 8:41:14 AM
 */
public class EmailMsg43 extends EmailMsg {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = -5936783321053970244L;

    protected static final Set<Integer> START_COMP_TAGS;

    protected static final Set<Integer> ALL_TAGS;
    
    protected static final Set<Integer> LINES_OF_TEXT_GROUP_TAGS = new LinesOfTextGroup43().getFragmentAllTags();
    protected static final Set<Integer> ROUTING_ID_GROUP_TAGS = new RoutingIDGroup43().getFragmentAllTags();
    protected static final Set<Integer> INSTRUMENT_COMP_TAGS = new Instrument43().getFragmentAllTags();

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">

    static {
        ALL_TAGS = new HashSet<Integer>(TAGS);
        ALL_TAGS.addAll(START_DATA_TAGS);
        ALL_TAGS.addAll(LINES_OF_TEXT_GROUP_TAGS);
        ALL_TAGS.addAll(ROUTING_ID_GROUP_TAGS);
        ALL_TAGS.addAll(INSTRUMENT_COMP_TAGS);
        START_COMP_TAGS = new HashSet<Integer>(LINES_OF_TEXT_GROUP_TAGS);
        START_COMP_TAGS.addAll(ROUTING_ID_GROUP_TAGS);
        START_COMP_TAGS.addAll(INSTRUMENT_COMP_TAGS);
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">

    protected Set<Integer> STANDARD_SECURED_TAGS = ALL_TAGS;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public EmailMsg43() {
        super();
    }

    public EmailMsg43(Header header, ByteBuffer rawMsg)
    throws InvalidMsgException, TagNotPresentException, BadFormatMsgException {
        super(header, rawMsg);
        SECURED_TAGS = STANDARD_SECURED_TAGS;
    }

    public EmailMsg43(BeginString beginString) throws InvalidMsgException {
        super(beginString);
        SECURED_TAGS = STANDARD_SECURED_TAGS;
    }

    public EmailMsg43(BeginString beginString, ApplVerID applVerID) throws InvalidMsgException {
        super(beginString, applVerID);
        SECURED_TAGS = STANDARD_SECURED_TAGS;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Public Methods">

    @Override
    public Set<Integer> getFragmentAllTags() {
        return ALL_TAGS;
    }

    // ACCESSOR METHODS
    //////////////////////////////////////////

    @Override
    public String getEmailThreadID() {
        return emailThreadID;
    }

    @Override
    public void setEmailThreadID(String emailThreadID) {
        this.emailThreadID = emailThreadID;
    }

    @Override
    public EmailType getEmailType() {
        return emailType;
    }

    @Override
    public void setEmailType(EmailType emailType) {
        this.emailType = emailType;
    }

    @Override
    public Date getOrigTime() {
        return origTime;
    }

    @Override
    public void setOrigTime(Date origTime) {
        this.origTime = origTime;
    }

    @Override
    public String getSubject() {
        return subject;
    }

    @Override
    public void setSubject(String subject) {
        this.subject = subject;
    }

    @Override
    public Integer getEncodedSubjectLen() {
        return encodedSubjectLen;
    }

    @Override
    public void setEncodedSubjectLen(Integer encodedSubjectLen) {
        this.encodedSubjectLen = encodedSubjectLen;
    }

    @Override
    public byte[] getEncodedSubject() {
        return encodedSubject;
    }

    @Override
    public void setEncodedSubject(byte[] encodedSubject) {
        this.encodedSubject = encodedSubject;
        if (encodedSubjectLen == null) {
            encodedSubjectLen = new Integer(encodedSubject.length);
        }
    }

    @Override
    public Integer getNoRoutingIDs() {
        return noRoutingIDs;
    }

    @Override
    public void setNoRoutingIDs(Integer noRoutingIDs) {
        this.noRoutingIDs = noRoutingIDs;
        if (noRoutingIDs != null) {
            routingIDGroups = new RoutingIDGroup[noRoutingIDs.intValue()];
            FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired);
            for (int i = 0; i < routingIDGroups.length; i++) {
                routingIDGroups[i] = new RoutingIDGroup43(context);
            }
        }
    }

    @Override
    public RoutingIDGroup[] getRoutingIDGroups() {
        return routingIDGroups;
    }

    @Override
    public RoutingIDGroup addRoutingIDGroup() {

        RoutingIDGroup group = new RoutingIDGroup43(new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired));
        List<RoutingIDGroup> groups = new ArrayList<RoutingIDGroup>();
        if (routingIDGroups != null && routingIDGroups.length > 0) {
            groups = new ArrayList<RoutingIDGroup>(Arrays.asList(routingIDGroups));
        }
        groups.add(group);
        routingIDGroups = groups.toArray(new RoutingIDGroup[groups.size()]);
        noRoutingIDs = new Integer(routingIDGroups.length);

        return group;
    }

    @Override
    public RoutingIDGroup deleteRoutingIDGroup(int index) {
       RoutingIDGroup result = null;

        if (routingIDGroups != null && routingIDGroups.length > 0 && routingIDGroups.length > index) {
            List<RoutingIDGroup> groups = new ArrayList<RoutingIDGroup>(Arrays.asList(routingIDGroups));
            result = groups.remove(index);
            routingIDGroups = groups.toArray(new RoutingIDGroup[groups.size()]);
            if (routingIDGroups.length > 0) {
                noRoutingIDs = new Integer(routingIDGroups.length);
            } else {
                routingIDGroups = null;
                noRoutingIDs = null;
            }
        }

        return result;
    }

    @Override
    public int clearRoutingIDGroups() {
        int result = 0;
        if (routingIDGroups != null && routingIDGroups.length > 0) {
            result = routingIDGroups.length;
            routingIDGroups = null;
            noRoutingIDs = null;
        }

        return result;
    }

    @Override
    public Integer getNoRelatedSyms() {
        return noRelatedSyms;
    }

    @Override
    public void setNoRelatedSyms(Integer noRelatedSyms) {
        this.noRelatedSyms = noRelatedSyms;
        if (noRelatedSyms != null) {
            instruments = new Instrument[noRelatedSyms.intValue()];
            FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired);
            for (int i = 0; i < instruments.length; i++) {
                instruments[i] = new Instrument43(context);
            }
        }
    }

    @Override
    public Instrument[] getInstruments() {
        return instruments;
    }

    @Override
    public Instrument addInstrument() {
        Instrument group = new Instrument43(new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired));
        List<Instrument> groups = new ArrayList<Instrument>();
        if (instruments != null && instruments.length > 0) {
            groups = new ArrayList<Instrument>(Arrays.asList(instruments));
        }
        groups.add(group);
        instruments = groups.toArray(new Instrument[groups.size()]);
        noRelatedSyms = new Integer(instruments.length);

        return group;
    }

    @Override
    public Instrument deleteInstrument(int index) {
        Instrument result = null;

        if (instruments != null && instruments.length > 0 && instruments.length > index) {
            List<Instrument> groups = new ArrayList<Instrument>(Arrays.asList(instruments));
            result = groups.remove(index);
            instruments = groups.toArray(new Instrument[groups.size()]);
            if (instruments.length > 0) {
                noRelatedSyms = new Integer(instruments.length);
            } else {
                instruments = null;
                noRelatedSyms = null;
            }
        }

        return result;
    }

    @Override
    public int clearInstruments() {
        int result = 0;
        if (instruments != null && instruments.length > 0) {
            result = instruments.length;
            instruments = null;
            noRelatedSyms = null;
        }

        return result;
    }

    @Override
    public String getOrderID() {
        return orderID;
    }

    @Override
    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    @Override
    public String getClOrdID() {
        return clOrdID;
    }

    @Override
    public void setClOrdID(String clOrdID) {
        this.clOrdID = clOrdID;
    }

    @Override
    public Integer getNoLinesOfText() {
        return noLinesOfText;
    }

    @Override
    public void setNoLinesOfText(Integer noLinesOfText) {
        this.noLinesOfText = noLinesOfText;
        if (noLinesOfText != null) {
            linesOfTextGroups = new LinesOfTextGroup[noLinesOfText.intValue()];
            FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired);
            for (int i = 0; i < linesOfTextGroups.length; i++) {
                linesOfTextGroups[i] = new LinesOfTextGroup43(context);
            }
        }
    }

    @Override
    public LinesOfTextGroup[] getLinesOfTextGroups() {
        return linesOfTextGroups;
    }

    @Override
    public LinesOfTextGroup addLinesOfTextGroup() {
        LinesOfTextGroup group = new LinesOfTextGroup43(new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired));
        List<LinesOfTextGroup> groups = new ArrayList<LinesOfTextGroup>();
        if (linesOfTextGroups != null && linesOfTextGroups.length > 0) {
            groups = new ArrayList<LinesOfTextGroup>(Arrays.asList(linesOfTextGroups));
        }
        groups.add(group);
        linesOfTextGroups = groups.toArray(new LinesOfTextGroup[groups.size()]);
        noLinesOfText = new Integer(linesOfTextGroups.length);

        return group;
    }

    @Override
    public LinesOfTextGroup deleteLinesOfTextGroup(int index) {
        LinesOfTextGroup result = null;
        if (linesOfTextGroups != null && linesOfTextGroups.length > 0 && linesOfTextGroups.length > index) {
            List<LinesOfTextGroup> groups = new ArrayList<LinesOfTextGroup>(Arrays.asList(linesOfTextGroups));
            result = groups.remove(index);
            linesOfTextGroups = groups.toArray(new LinesOfTextGroup[groups.size()]);
            if (linesOfTextGroups.length > 0) {
                noLinesOfText = new Integer(linesOfTextGroups.length);
            } else {
                linesOfTextGroups = null;
                noLinesOfText = null;
            }
        }

        return result;
    }

    @Override
    public int clearLinesOfTextGroups() {
        int result = 0;
        if (linesOfTextGroups != null && linesOfTextGroups.length > 0) {
            result = linesOfTextGroups.length;
            linesOfTextGroups = null;
            noLinesOfText = null;
        }

        return result;
    }

    @Override
    public Integer getRawDataLength() {
        return rawDataLength;
    }

    @Override
    public void setRawDataLength(Integer rawDataLength) {
        this.rawDataLength = rawDataLength;
    }

    @Override
    public byte[] getRawData() {
        return rawData;
    }

    @Override
    public void setRawData(byte[] rawData) {
        this.rawData = rawData;
        if (rawDataLength == null) {
            rawDataLength = new Integer(rawData.length);
        }
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

    @Override
    protected byte[] encodeFragmentSecured(boolean secured) throws TagNotPresentException, BadFormatMsgException {

        byte[] result = new byte[0];
        ByteArrayOutputStream bao = new ByteArrayOutputStream();
        try {
            if (MsgUtil.isTagInList(TagNum.EmailThreadID, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.EmailThreadID, emailThreadID);
            }
            if (MsgUtil.isTagInList(TagNum.EmailType, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.EmailType, emailType.getValue());
            }
            if (MsgUtil.isTagInList(TagNum.OrigTime, SECURED_TAGS, secured)) {
                TagEncoder.encodeUtcTimestamp(bao, TagNum.OrigTime, origTime);
            }
            if (MsgUtil.isTagInList(TagNum.Subject, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.Subject, subject);
            }
            if (encodedSubjectLen != null && encodedSubjectLen.intValue() > 0 && MsgUtil.isTagInList(TagNum.EncodedSubjectLen, SECURED_TAGS, secured)) {
                if (encodedSubject != null && encodedSubject.length > 0) {
                    encodedSubjectLen = new Integer(encodedSubject.length);
                    TagEncoder.encode(bao, TagNum.EncodedSubjectLen, encodedSubjectLen);
                    TagEncoder.encode(bao, TagNum.EncodedSubject, encodedSubject);
                }
            }
            if (noRoutingIDs != null  && MsgUtil.isTagInList(TagNum.NoRoutingIDs, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.NoRoutingIDs, noRoutingIDs);
                if (routingIDGroups != null && routingIDGroups.length == noRoutingIDs.intValue()) {
                    for (int i = 0; i < noRoutingIDs.intValue(); i++) {
                        if (routingIDGroups[i] != null) {
                            bao.write(routingIDGroups[i].encode(getMsgSecureTypeForFlag(secured)));
                        }
                    }
                } else {
                    String error = "RoutingIDGroup field has been set but there is no data or the number of groups does not match.";
                    LOGGER.severe(error);
                    throw new BadFormatMsgException(SessionRejectReason.IncorrectCountForGroups, getHeader().getMsgType(),
                            TagNum.NoRoutingIDs.getValue(), error);
                }
            }
            if (noRelatedSyms != null && MsgUtil.isTagInList(TagNum.NoRelatedSym, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.NoRelatedSym, noRelatedSyms);
                if (instruments != null && instruments.length == noRelatedSyms.intValue()) {
                    for (int i = 0; i < noRelatedSyms.intValue(); i++) {
                        if (instruments[i] != null) {
                            bao.write(instruments[i].encode(getMsgSecureTypeForFlag(secured)));
                        }
                    }
                } else {
                    String error = "Instrument field has been set but there is no data or the number of groups does not match.";
                    LOGGER.severe(error);
                    throw new BadFormatMsgException(SessionRejectReason.IncorrectCountForGroups, getHeader().getMsgType(),
                            TagNum.NoRelatedSym.getValue(), error);
                }
            }
            if (MsgUtil.isTagInList(TagNum.OrderID, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.OrderID, orderID);
            }
            if (MsgUtil.isTagInList(TagNum.ClOrdID, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.ClOrdID, clOrdID);
            }
            if (noLinesOfText != null && MsgUtil.isTagInList(TagNum.NoLinesOfText, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.NoLinesOfText, noLinesOfText);
                if (linesOfTextGroups != null && linesOfTextGroups.length == noLinesOfText.intValue()) {
                    for (int i = 0; i < noLinesOfText.intValue(); i++) {
                        if (linesOfTextGroups[i] != null) {
                            bao.write(linesOfTextGroups[i].encode(getMsgSecureTypeForFlag(secured)));
                        }
                    }
                } else {
                    String error = "LinesOfTextGroups field has been set but there is no data or the number of groups does not match.";
                    LOGGER.severe(error);
                    throw new BadFormatMsgException(SessionRejectReason.IncorrectCountForGroups, getHeader().getMsgType(),
                            TagNum.NoLinesOfText.getValue(), error);
                }
            }
            if (rawDataLength != null && rawDataLength.intValue() > 0 && MsgUtil.isTagInList(TagNum.RawDataLength, SECURED_TAGS, secured)) {
                if (rawData != null && rawData.length > 0) {
                    rawDataLength = new Integer(rawData.length);
                    TagEncoder.encode(bao, TagNum.RawDataLength, rawDataLength);
                    TagEncoder.encode(bao, TagNum.RawData, rawData);
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
        if (ROUTING_ID_GROUP_TAGS.contains(tag.tagNum)) {
            if (noRoutingIDs != null && noRoutingIDs.intValue() > 0) {
                message.reset();
                routingIDGroups = new RoutingIDGroup[noRoutingIDs.intValue()];
                for (int i = 0; i < noRoutingIDs.intValue(); i++) {
                    RoutingIDGroup group = new RoutingIDGroup43(context);
                    group.decode(message);
                    routingIDGroups[i] = group;
                }
            }
        }
        if (INSTRUMENT_COMP_TAGS.contains(tag.tagNum)) {
            if (noRelatedSyms != null && noRelatedSyms.intValue() > 0) {
                message.reset();
                instruments = new Instrument[noRelatedSyms.intValue()];
                for (int i = 0; i < noRelatedSyms.intValue(); i++) {
                    Instrument group = new Instrument43(context);
                    group.decode(message);
                    instruments[i] = group;
                }
            }
        }
        if (LINES_OF_TEXT_GROUP_TAGS.contains(tag.tagNum)) {
            if (noLinesOfText != null && noLinesOfText.intValue() > 0) {
                message.reset();
                linesOfTextGroups = new LinesOfTextGroup[noLinesOfText.intValue()];
                for (int i = 0; i < noLinesOfText.intValue(); i++) {
                    LinesOfTextGroup group = new LinesOfTextGroup43(context);
                    group.decode(message);
                    linesOfTextGroups[i] = group;
                }
            }
        }
    }

    @Override
    protected String getUnsupportedTagMessage() {
        return "This tag is not supported in [EmailMsg] message version [4.3].";
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
