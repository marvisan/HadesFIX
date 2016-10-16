/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * NewsMsg42.java
 *
 * $Id: NewsMsg42.java,v 1.10 2011-04-14 23:44:39 vrotaru Exp $
 */
package net.hades.fix.message.impl.v42;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.Header;
import net.hades.fix.message.NewsMsg;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.group.RelatedSymbolGroup;
import net.hades.fix.message.group.RoutingIDGroup;
import net.hades.fix.message.group.impl.v42.LinesOfTextGroup42;
import net.hades.fix.message.group.impl.v42.RelatedSymbolGroup42;
import net.hades.fix.message.group.impl.v42.RoutingIDGroup42;
import net.hades.fix.message.struct.Tag;
import net.hades.fix.message.type.ApplVerID;
import net.hades.fix.message.type.BeginString;
import net.hades.fix.message.type.SessionRejectReason;
import net.hades.fix.message.type.TagNum;
import net.hades.fix.message.util.MsgUtil;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import net.hades.fix.message.group.LinesOfTextGroup;
import net.hades.fix.message.util.TagEncoder;
import java.util.logging.Level;

/**
 * FIX version 4.2 NewsMsg implementation.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.10 $
 * @created 23/03/2009, 7:15:41 PM
 */
public class NewsMsg42 extends NewsMsg {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = -4050296401119905197L;

    protected static final Set<Integer> START_COMP_TAGS;

    protected static final Set<Integer> ALL_TAGS;

    protected static final Set<Integer> LINES_OF_TEXT_GROUP_TAGS = new LinesOfTextGroup42().getFragmentTags();
    protected static final Set<Integer> ROUTING_ID_GROUP_TAGS = new RoutingIDGroup42().getFragmentTags();
    protected static final Set<Integer> RELATED_SYM_GROUP_TAGS = new RelatedSymbolGroup42().getFragmentTags();

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">

    static {
        ALL_TAGS = new HashSet<Integer>(TAGS);
        ALL_TAGS.addAll(START_DATA_TAGS);
        ALL_TAGS.addAll(LINES_OF_TEXT_GROUP_TAGS);
        ALL_TAGS.addAll(ROUTING_ID_GROUP_TAGS);
        ALL_TAGS.addAll(RELATED_SYM_GROUP_TAGS);
        START_COMP_TAGS = new HashSet<Integer>(LINES_OF_TEXT_GROUP_TAGS);
        START_COMP_TAGS.addAll(ROUTING_ID_GROUP_TAGS);
        START_COMP_TAGS.addAll(RELATED_SYM_GROUP_TAGS);
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">

    protected Set<Integer> STANDARD_SECURED_TAGS = ALL_TAGS;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public NewsMsg42() {
        super();
    }

    public NewsMsg42(Header header, ByteBuffer rawMsg)
    throws InvalidMsgException, TagNotPresentException, BadFormatMsgException {
        super(header, rawMsg);
        SECURED_TAGS = STANDARD_SECURED_TAGS;
    }

    public NewsMsg42(BeginString beginString) throws InvalidMsgException {
        super(beginString);
        SECURED_TAGS = STANDARD_SECURED_TAGS;
    }

    public NewsMsg42(BeginString beginString, ApplVerID applVerID) throws InvalidMsgException {
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
                routingIDGroups[i] = new RoutingIDGroup42(context);
            }
        }
    }

    @Override
    public RoutingIDGroup[] getRoutingIDGroups() {
        return routingIDGroups;
    }

    @Override
    public RoutingIDGroup addRoutingIDGroup() {

        RoutingIDGroup group = new RoutingIDGroup42(new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired));
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
            relatedSymGroups = new RelatedSymbolGroup[noRelatedSyms.intValue()];
            FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired);
            for (int i = 0; i < relatedSymGroups.length; i++) {
                relatedSymGroups[i] = new RelatedSymbolGroup42(context);
            }
        }
    }

    @Override
    public RelatedSymbolGroup[] getRelatedSymGroups() {
        return relatedSymGroups;
    }

    @Override
    public RelatedSymbolGroup addRelatedSymGroup() {

        RelatedSymbolGroup group = new RelatedSymbolGroup42(new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired));
        List<RelatedSymbolGroup> groups = new ArrayList<RelatedSymbolGroup>();
        if (relatedSymGroups != null && relatedSymGroups.length > 0) {
            groups = new ArrayList<RelatedSymbolGroup>(Arrays.asList(relatedSymGroups));
        }
        groups.add(group);
        relatedSymGroups = groups.toArray(new RelatedSymbolGroup[groups.size()]);
        noRelatedSyms = new Integer(relatedSymGroups.length);

        return group;
    }

    @Override
    public RelatedSymbolGroup deleteRelatedSymGroup(int index) {
        RelatedSymbolGroup result = null;

        if (relatedSymGroups != null && relatedSymGroups.length > 0 && relatedSymGroups.length > index) {
            List<RelatedSymbolGroup> groups = new ArrayList<RelatedSymbolGroup>(Arrays.asList(relatedSymGroups));
            result = groups.remove(index);
            relatedSymGroups = groups.toArray(new RelatedSymbolGroup[groups.size()]);
            if (relatedSymGroups.length > 0) {
                noRelatedSyms = new Integer(relatedSymGroups.length);
            } else {
                relatedSymGroups = null;
                noRelatedSyms = null;
            }
        }

        return result;
    }

    @Override
    public int clearRelatedSymGroups() {
        int result = 0;
        if (relatedSymGroups != null && relatedSymGroups.length > 0) {
            result = relatedSymGroups.length;
            relatedSymGroups = null;
            noRelatedSyms = null;
        }

        return result;
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
                linesOfTextGroups[i] = new LinesOfTextGroup42(context);
            }
        }
    }

    @Override
    public LinesOfTextGroup[] getLinesOfTextGroups() {
        return linesOfTextGroups;
    }

    @Override
    public LinesOfTextGroup addLinesOfTextGroup() {
        LinesOfTextGroup group = new LinesOfTextGroup42(new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired));
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

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

    @Override
    protected byte[] encodeFragmentSecured(boolean secured) throws TagNotPresentException, BadFormatMsgException {

        byte[] result = new byte[0];
        ByteArrayOutputStream bao = new ByteArrayOutputStream();
        try {
            if (MsgUtil.isTagInList(TagNum.OrigTime, SECURED_TAGS, secured)) {
                TagEncoder.encodeUtcTimestamp(bao, TagNum.OrigTime, origTime);
            }
            if (urgency != null && MsgUtil.isTagInList(TagNum.Urgency, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.Urgency, urgency.getValue());
            }
            if (headline != null && MsgUtil.isTagInList(TagNum.Headline, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.Headline, headline);
            }
            if (encodedHeadlineLen != null && encodedHeadlineLen.intValue() > 0 && MsgUtil.isTagInList(TagNum.EncodedHeadlineLen, SECURED_TAGS, secured)) {
                if (encodedHeadline != null && encodedHeadline.length > 0) {
                    encodedHeadlineLen = new Integer(encodedHeadline.length);
                    TagEncoder.encode(bao, TagNum.EncodedHeadlineLen, encodedHeadlineLen);
                    TagEncoder.encode(bao, TagNum.EncodedHeadline, encodedHeadline);
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
            if (noRelatedSyms != null && relatedSymGroups != null) {
                TagEncoder.encode(bao, TagNum.NoRelatedSym, noRelatedSyms);
                if (relatedSymGroups != null && relatedSymGroups.length == noRelatedSyms.intValue()) {
                    for (int i = 0; i < noRelatedSyms.intValue(); i++) {
                        if (relatedSymGroups[i] != null) {
                            bao.write(relatedSymGroups[i].encode(getMsgSecureTypeForFlag(secured)));
                        }
                    }
                } else {
                    String error = "RelatedSymGroup field has been set but there is no data or the number of groups does not match.";
                    LOGGER.severe(error);
                    throw new BadFormatMsgException(SessionRejectReason.IncorrectCountForGroups, getHeader().getMsgType(),
                            TagNum.NoRelatedSym.getValue(), error);
                }
            }
            if (noRelatedSyms != null && instruments != null && MsgUtil.isTagInList(TagNum.NoRelatedSym, SECURED_TAGS, secured)) {
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
            if (MsgUtil.isTagInList(TagNum.URLLink, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.URLLink, urlLink);
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
                    RoutingIDGroup group = new RoutingIDGroup42(context);
                    group.decode(message);
                    routingIDGroups[i] = group;
                }
            }
        }
        if (RELATED_SYM_GROUP_TAGS.contains(tag.tagNum)) {
            if (noRelatedSyms != null && noRelatedSyms.intValue() > 0) {
                message.reset();
                relatedSymGroups = new RelatedSymbolGroup[noRelatedSyms.intValue()];
                for (int i = 0; i < noRelatedSyms.intValue(); i++) {
                    RelatedSymbolGroup group = new RelatedSymbolGroup42(context);
                    group.decode(message);
                    relatedSymGroups[i] = group;
                }
            }
        }
        if (LINES_OF_TEXT_GROUP_TAGS.contains(tag.tagNum)) {
            if (noLinesOfText != null && noLinesOfText.intValue() > 0) {
                message.reset();
                linesOfTextGroups = new LinesOfTextGroup[noLinesOfText.intValue()];
                for (int i = 0; i < noLinesOfText.intValue(); i++) {
                    LinesOfTextGroup group = new LinesOfTextGroup42(context);
                    group.decode(message);
                    linesOfTextGroups[i] = group;
                }
            }
        }
    }

    @Override
    protected String getUnsupportedTagMessage() {
        return "This tag is not supported in [NewsMsg] message version [4.2].";
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
