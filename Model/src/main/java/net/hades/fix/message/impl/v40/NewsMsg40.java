/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * NewsMsg40.java
 *
 * $Id: NewsMsg40.java,v 1.11 2011-04-14 23:45:01 vrotaru Exp $
 */
package net.hades.fix.message.impl.v40;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import net.hades.fix.message.Header;
import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.NewsMsg;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.group.LinesOfTextGroup;
import net.hades.fix.message.group.RelatedSymbolGroup;
import net.hades.fix.message.group.impl.v40.LinesOfTextGroup40;
import net.hades.fix.message.group.impl.v40.RelatedSymbolGroup40;
import net.hades.fix.message.struct.Tag;
import net.hades.fix.message.type.ApplVerID;
import net.hades.fix.message.type.BeginString;
import net.hades.fix.message.type.SessionRejectReason;
import net.hades.fix.message.type.TagNum;
import net.hades.fix.message.util.MsgUtil;
import net.hades.fix.message.util.TagEncoder;
import java.util.logging.Level;

/**
 * FIX version 4.0 NewsMsg implementation.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.11 $
 * @created 23/03/2009, 7:15:41 PM
 */
public class NewsMsg40 extends NewsMsg {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> START_COMP_TAGS;

    protected static final Set<Integer> REQUIRED_TAGS_V40 = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.NoLinesOfText.getValue()
    }));

    protected static final Set<Integer> ALL_TAGS;

    protected static final Set<Integer> LINES_OF_TEXT_GROUP_TAGS = new LinesOfTextGroup40().getFragmentAllTags();
    protected static final Set<Integer> RELATED_SYM_GROUP_TAGS = new RelatedSymbolGroup40().getFragmentAllTags();

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">

    static {
        ALL_TAGS = new HashSet<Integer>(TAGS);
        ALL_TAGS.addAll(START_DATA_TAGS);
        ALL_TAGS.addAll(LINES_OF_TEXT_GROUP_TAGS);
        ALL_TAGS.addAll(RELATED_SYM_GROUP_TAGS);
        START_COMP_TAGS = new HashSet<Integer>(LINES_OF_TEXT_GROUP_TAGS);
        START_COMP_TAGS.addAll(RELATED_SYM_GROUP_TAGS);
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">

    protected Set<Integer> STANDARD_SECURED_TAGS = TAGS;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public NewsMsg40(Header header, ByteBuffer rawMsg)
    throws InvalidMsgException, TagNotPresentException, BadFormatMsgException {
        super(header, rawMsg);
        SECURED_TAGS = STANDARD_SECURED_TAGS;
    }

    public NewsMsg40(BeginString beginString) throws InvalidMsgException {
        super(beginString);
        SECURED_TAGS = STANDARD_SECURED_TAGS;
    }

    public NewsMsg40(BeginString beginString, ApplVerID applVerID) throws InvalidMsgException {
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
    public RelatedSymbolGroup[] getRelatedSymGroups() {
        return relatedSymGroups;
    }

    @Override
    public RelatedSymbolGroup addRelatedSymGroup() {

        RelatedSymbolGroup group = new RelatedSymbolGroup40(new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired));
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
                linesOfTextGroups[i] = new LinesOfTextGroup40(context);
            }
        }
    }

    @Override
    public LinesOfTextGroup[] getLinesOfTextGroups() {
        return linesOfTextGroups;
    }

    @Override
    public LinesOfTextGroup addLinesOfTextGroup() {
        LinesOfTextGroup group = new LinesOfTextGroup40(new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired));
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
    protected void validateRequiredTags() throws TagNotPresentException {
        StringBuilder errorMsg = new StringBuilder("Tag value(s) for");
        boolean hasMissingTag = false;

        if (noLinesOfText == null) {
            errorMsg.append(" [NoLinesOfText]");
            hasMissingTag = true;
        }
        errorMsg.append(" is missing.");
        if (hasMissingTag) {
            throw new TagNotPresentException(errorMsg.toString());
        }
    }

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
            if (relatedSymGroups != null && relatedSymGroups.length > 0) {
                for (int i = 0; i < relatedSymGroups.length; i++) {
                    if (relatedSymGroups[i] != null) {
                        bao.write(relatedSymGroups[i].encode(getMsgSecureTypeForFlag(secured)));
                    }
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
        if (RELATED_SYM_GROUP_TAGS.contains(tag.tagNum)) {
            List<RelatedSymbolGroup> groups = null;
            if (relatedSymGroups == null) {
                groups = new ArrayList<RelatedSymbolGroup>();
            } else {
                groups = new ArrayList<RelatedSymbolGroup>(Arrays.asList(relatedSymGroups));
            }
            RelatedSymbolGroup group = new RelatedSymbolGroup40(context);
            group.decode(tag, message);
            groups.add(group);
            relatedSymGroups = groups.toArray(new RelatedSymbolGroup[groups.size()]);
        }
        if (LINES_OF_TEXT_GROUP_TAGS.contains(tag.tagNum)) {
            if (noLinesOfText != null && noLinesOfText.intValue() > 0) {
                message.reset();
                linesOfTextGroups = new LinesOfTextGroup[noLinesOfText.intValue()];
                for (int i = 0; i < noLinesOfText.intValue(); i++) {
                    LinesOfTextGroup group = new LinesOfTextGroup40(context);
                    group.decode(message);
                    linesOfTextGroups[i] = group;
                }
            }
        }
    }

    @Override
    protected String getUnsupportedTagMessage() {
        return "This tag is not supported in [NewsMsg] message version [4.0].";
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
