/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * QuoteSetAckGroup43.java
 *
 * $Id: QuoteSetAckGroup43.java,v 1.7 2011-04-14 23:44:48 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v43;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.group.QuoteSetAckGroup;
import net.hades.fix.message.struct.Tag;
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

import net.hades.fix.message.comp.UnderlyingInstrument;
import net.hades.fix.message.comp.impl.v43.UnderlyingInstrument43;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.group.QuoteEntryAckGroup;
import net.hades.fix.message.util.TagEncoder;
import java.util.logging.Level;

/**
 * FIX 4.3 implementation of QuoteSetAckGroup group.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.7 $
 * @created 12/02/2009, 7:22:35 PM
 */
public class QuoteSetAckGroup43 extends QuoteSetAckGroup {
    
    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = -8784227251019675026L;

    protected static final Set<Integer> START_COMP_TAGS;

    protected static final Set<Integer> ALL_TAGS;
    
    protected static final Set<Integer> QUOTE_ENTRY_ACK_GROUP_TAGS = new QuoteEntryAckGroup43().getFragmentAllTags();
    protected static final Set<Integer> UND_INSTR_COMP_TAGS = new UnderlyingInstrument43().getFragmentAllTags();
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Static Block">
    
    static {
        ALL_TAGS = new HashSet<Integer>(TAGS);
        ALL_TAGS.addAll(QUOTE_ENTRY_ACK_GROUP_TAGS);
        ALL_TAGS.addAll(UND_INSTR_COMP_TAGS);
        START_COMP_TAGS = new HashSet<Integer>(QUOTE_ENTRY_ACK_GROUP_TAGS);
        START_COMP_TAGS.addAll(UND_INSTR_COMP_TAGS);
    }
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Attributes">
    
    protected Set<Integer> STANDARD_SECURED_TAGS = TAGS;
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Constructors">
    
    public QuoteSetAckGroup43() {
        SECURED_TAGS = STANDARD_SECURED_TAGS;
    }
    
    public QuoteSetAckGroup43(FragmentContext context) {
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
            quoteEntryAckGroups = new QuoteEntryAckGroup[noQuoteEntries.intValue()];
            for (int i = 0; i < quoteEntryAckGroups.length; i++) {
                quoteEntryAckGroups[i] = new QuoteEntryAckGroup43(context);
            }
        }
    }

    @Override
    public QuoteEntryAckGroup[] getQuoteEntryAckGroups() {
        return quoteEntryAckGroups;
    }

    @Override
    public QuoteEntryAckGroup addQuoteEntryAckGroup() {
        QuoteEntryAckGroup group = new QuoteEntryAckGroup43(new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired));
        List<QuoteEntryAckGroup> groups = new ArrayList<QuoteEntryAckGroup>();
        if (quoteEntryAckGroups != null && quoteEntryAckGroups.length > 0) {
            groups = new ArrayList<QuoteEntryAckGroup>(Arrays.asList(quoteEntryAckGroups));
        }
        groups.add(group);
        quoteEntryAckGroups = groups.toArray(new QuoteEntryAckGroup[groups.size()]);
        noQuoteEntries = new Integer(quoteEntryAckGroups.length);

        return group;
    }

    @Override
    public QuoteEntryAckGroup deleteQuoteEntryAckGroup(int index) {
        QuoteEntryAckGroup result = null;
        if (quoteEntryAckGroups != null && quoteEntryAckGroups.length > 0 && quoteEntryAckGroups.length > index) {
            List<QuoteEntryAckGroup> groups = new ArrayList<QuoteEntryAckGroup>(Arrays.asList(quoteEntryAckGroups));
            result = groups.remove(index);
            quoteEntryAckGroups = groups.toArray(new QuoteEntryAckGroup[groups.size()]);
            if (quoteEntryAckGroups.length > 0) {
                noQuoteEntries = new Integer(quoteEntryAckGroups.length);
            } else {
                quoteEntryAckGroups = null;
                noQuoteEntries = null;
            }
        }

        return result;
    }

    @Override
    public int clearQuoteEntryAckGroups() {
        int result = 0;
        if (quoteEntryAckGroups != null && quoteEntryAckGroups.length > 0) {
            result = quoteEntryAckGroups.length;
            quoteEntryAckGroups = null;
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
            if (MsgUtil.isTagInList(TagNum.TotNoQuoteEntries, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.TotNoQuoteEntries, totNoQuoteEntries);
            }
            if (noQuoteEntries != null && MsgUtil.isTagInList(TagNum.NoQuoteEntries, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.NoQuoteEntries, noQuoteEntries);
                if (quoteEntryAckGroups != null && quoteEntryAckGroups.length == noQuoteEntries.intValue()) {
                    for (int i = 0; i < noQuoteEntries.intValue(); i++) {
                        if (quoteEntryAckGroups[i] != null) {
                            bao.write(quoteEntryAckGroups[i].encode(getMsgSecureTypeForFlag(secured)));
                        }
                    }
                } else {
                    String error = "QuoteEntryAckGroup field has been set but there is no data or the number of groups does not match.";
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
        if (QUOTE_ENTRY_ACK_GROUP_TAGS.contains(tag.tagNum)) {
            if (noQuoteEntries != null && noQuoteEntries.intValue() > 0) {
                message.reset();
                if (quoteEntryAckGroups == null) {
                    quoteEntryAckGroups = new QuoteEntryAckGroup[noQuoteEntries.intValue()];
                }
                for (int i = 0; i < quoteEntryAckGroups.length; i++) {
                    QuoteEntryAckGroup group = new QuoteEntryAckGroup43(context);
                    group.decode(message);
                    quoteEntryAckGroups[i] = group;
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
        return "This tag is not supported in [QuoteSetAckGroup] group version [4.3].";
    }
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Package Methods">
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Private Methods">
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Inner Classes">
    // </editor-fold>
}
