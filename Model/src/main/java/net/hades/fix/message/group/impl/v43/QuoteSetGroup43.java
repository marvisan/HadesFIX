/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * QuoteSetGroup43.java
 *
 * $Id: QuoteSetGroup43.java,v 1.9 2011-04-14 23:44:48 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v43;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.comp.UnderlyingInstrument;
import net.hades.fix.message.comp.impl.v43.UnderlyingInstrument43;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.group.QuoteEntryGroup;
import net.hades.fix.message.group.QuoteSetGroup;
import net.hades.fix.message.struct.Tag;
import net.hades.fix.message.type.SessionRejectReason;
import net.hades.fix.message.type.TagNum;
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

import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.util.TagEncoder;
import java.util.logging.Level;

/**
 * FIX 4.3 implementation of QuoteSetGroup group.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.9 $
 * @created 12/02/2009, 7:22:35 PM
 */
public class QuoteSetGroup43 extends QuoteSetGroup {
    
    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = -7042668742005725783L;

    protected static final Set<Integer> START_COMP_TAGS;

    protected static final Set<Integer> ALL_TAGS;
    
    protected static final Set<Integer> QUOTE_ENTRY_GROUP_TAGS = new QuoteEntryGroup43().getFragmentAllTags();
    protected static final Set<Integer> UND_INSTR_COMP_TAGS = new UnderlyingInstrument43().getFragmentAllTags();
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Static Block">
    
    static {
        ALL_TAGS = new HashSet<Integer>(TAGS);
        ALL_TAGS.addAll(QUOTE_ENTRY_GROUP_TAGS);
        ALL_TAGS.addAll(UND_INSTR_COMP_TAGS);
        START_COMP_TAGS = new HashSet<Integer>(QUOTE_ENTRY_GROUP_TAGS);
        START_COMP_TAGS.addAll(UND_INSTR_COMP_TAGS);
    }
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Attributes">
    
    protected Set<Integer> STANDARD_SECURED_TAGS = TAGS;
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Constructors">
    
    public QuoteSetGroup43() {
        SECURED_TAGS = STANDARD_SECURED_TAGS;
    }
    
    public QuoteSetGroup43(FragmentContext context) {
        super(context);
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
    public String getQuoteSetID() {
        return quoteSetID;
    }

    @Override
    public void setQuoteSetID(String quoteSetID) {
        this.quoteSetID = quoteSetID;
    }

    @Override
    public UnderlyingInstrument getUnderlyingInstrument() {
        return underlyingInstrument;
    }

    @Override
    public void setUnderlyingInstrument() {
        FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired);
        this.underlyingInstrument = new UnderlyingInstrument43(context);
    }

    @Override
    public void clearUnderlyingInstrument() {
        this.underlyingInstrument = null;
    }

    @Override
    public Date getQuoteSetValidUntilTime() {
        return quoteSetValidUntilTime;
    }

    @Override
    public void setQuoteSetValidUntilTime(Date quoteSetValidUntilTime) {
        this.quoteSetValidUntilTime = quoteSetValidUntilTime;
    }

    @Override
    public Integer getTotNoQuoteEntries() {
        return totNoQuoteEntries;
    }

    @Override
    public void setTotNoQuoteEntries(Integer totNoQuoteEntries) {
        this.totNoQuoteEntries = totNoQuoteEntries;
    }

    @Override
    public Integer getNoQuoteEntries() {
        return noQuoteEntries;
    }

    @Override
    public void setNoQuoteEntries(Integer noQuoteEntries) {
        this.noQuoteEntries = noQuoteEntries;
        if (noQuoteEntries != null) {
            FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired);
            quoteEntryGroups = new QuoteEntryGroup[noQuoteEntries.intValue()];
            for (int i = 0; i < quoteEntryGroups.length; i++) {
                quoteEntryGroups[i] = new QuoteEntryGroup43(context);
            }
        }
    }

    @Override
    public QuoteEntryGroup[] getQuoteEntryGroups() {
        return quoteEntryGroups;
    }

    @Override
    public QuoteEntryGroup addQuoteEntryGroup() {
        QuoteEntryGroup group = new QuoteEntryGroup43(new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired));
        List<QuoteEntryGroup> groups = new ArrayList<QuoteEntryGroup>();
        if (quoteEntryGroups != null && quoteEntryGroups.length > 0) {
            groups = new ArrayList<QuoteEntryGroup>(Arrays.asList(quoteEntryGroups));
        }
        groups.add(group);
        quoteEntryGroups = groups.toArray(new QuoteEntryGroup[groups.size()]);
        noQuoteEntries = new Integer(quoteEntryGroups.length);

        return group;
    }

    @Override
    public QuoteEntryGroup deleteQuoteEntryGroup(int index) {
        QuoteEntryGroup result = null;
        if (quoteEntryGroups != null && quoteEntryGroups.length > 0 && quoteEntryGroups.length > index) {
            List<QuoteEntryGroup> groups = new ArrayList<QuoteEntryGroup>(Arrays.asList(quoteEntryGroups));
            result = groups.remove(index);
            quoteEntryGroups = groups.toArray(new QuoteEntryGroup[groups.size()]);
            if (quoteEntryGroups.length > 0) {
                noQuoteEntries = new Integer(quoteEntryGroups.length);
            } else {
                quoteEntryGroups = null;
                noQuoteEntries = null;
            }
        }

        return result;
    }

    @Override
    public int clearQuoteEntryGroups() {
        int result = 0;
        if (quoteEntryGroups != null && quoteEntryGroups.length > 0) {
            result = quoteEntryGroups.length;
            quoteEntryGroups = null;
            noQuoteEntries = null;
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
            if (MsgUtil.isTagInList(TagNum.QuoteSetID, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.QuoteSetID, quoteSetID);
            }
            if (underlyingInstrument != null) {
                bao.write(underlyingInstrument.encode(getMsgSecureTypeForFlag(secured)));
            }
            if (MsgUtil.isTagInList(TagNum.QuoteSetValidUntilTime, SECURED_TAGS, secured)) {
                TagEncoder.encodeUtcTimestamp(bao, TagNum.QuoteSetValidUntilTime, quoteSetValidUntilTime);
            }
            if (MsgUtil.isTagInList(TagNum.TotNoQuoteEntries, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.TotNoQuoteEntries, totNoQuoteEntries);
            }
            if (noQuoteEntries != null && MsgUtil.isTagInList(TagNum.NoQuoteEntries, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.NoQuoteEntries, noQuoteEntries);
                if (quoteEntryGroups != null && quoteEntryGroups.length == noQuoteEntries.intValue()) {
                    for (int i = 0; i < noQuoteEntries.intValue(); i++) {
                        if (quoteEntryGroups[i] != null) {
                            bao.write(quoteEntryGroups[i].encode(getMsgSecureTypeForFlag(secured)));
                        }
                    }
                } else {
                    String error = "QuoteEntryGroup field has been set but there is no data or the number of groups does not match.";
                    LOGGER.severe(error);
                    throw new BadFormatMsgException(SessionRejectReason.IncorrectCountForGroups, TagNum.NoQuoteEntries.getValue(), error);
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
        if (QUOTE_ENTRY_GROUP_TAGS.contains(tag.tagNum)) {
            if (noQuoteEntries != null && noQuoteEntries.intValue() > 0) {
                message.reset();
                if (quoteEntryGroups == null) {
                    quoteEntryGroups = new QuoteEntryGroup[noQuoteEntries.intValue()];
                }
                for (int i = 0; i < quoteEntryGroups.length; i++) {
                    QuoteEntryGroup group = new QuoteEntryGroup43(context);
                    group.decode(message);
                    quoteEntryGroups[i] = group;
                }
            }
        }
        if (UND_INSTR_COMP_TAGS.contains(tag.tagNum)) {
            if (underlyingInstrument == null) {
                underlyingInstrument = new UnderlyingInstrument43(context);
            }
            message.reset();
            underlyingInstrument.decode(message);
        }
    }
    
    @Override
    protected Set<Integer> getFragmentCompTags() {
        return START_COMP_TAGS;
    }
    
    @Override
    protected Set<Integer> getFragmentSecuredTags() {
        return SECURED_TAGS;
    }

    @Override
    protected String getUnsupportedTagMessage() {
        return "This tag is not supported in [QuoteSetGroup] group version [4.3].";
    }
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Package Methods">
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Private Methods">
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Inner Classes">
    // </editor-fold>
}
