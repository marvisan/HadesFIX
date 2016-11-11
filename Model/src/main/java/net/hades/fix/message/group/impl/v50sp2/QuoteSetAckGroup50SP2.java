/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * QuoteSetAckGroup50SP2.java
 *
 * $Id: QuoteSetAckGroup50SP2.java,v 1.8 2011-04-14 23:44:30 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v50sp2;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.group.QuoteSetAckGroup;
import net.hades.fix.message.struct.Tag;
import net.hades.fix.message.xml.codec.jaxb.adapter.FixBooleanAdapter;

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

import net.hades.fix.message.comp.UnderlyingInstrument;
import net.hades.fix.message.comp.impl.v50sp2.UnderlyingInstrument50SP2;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.group.QuoteEntryAckGroup;

/**
 * FIX 5.0SP2 implementation of QuoteSetAckGroup group.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.8 $
 * @created 12/02/2009, 7:22:35 PM
 */
@XmlRootElement(name="QuotSetAck")
@XmlType(propOrder={"underlyingInstrument", "quoteEntryAckGroups"})
@XmlAccessorType(XmlAccessType.NONE)
public class QuoteSetAckGroup50SP2 extends QuoteSetAckGroup {
    
    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = -7932087393456449145L;
    
    protected static final Set<Integer> START_COMP_TAGS;
    
    protected static final Set<Integer> ALL_TAGS;
    
    protected static final Set<Integer> QUOTE_ENTRY_ACK_GROUP_TAGS = new QuoteEntryAckGroup50SP2().getFragmentAllTags();
    protected static final Set<Integer> UND_INSTR_COMP_TAGS = new UnderlyingInstrument50SP2().getFragmentAllTags();
    
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
    
    public QuoteSetAckGroup50SP2() {
        SECURED_TAGS = STANDARD_SECURED_TAGS;
    }
    
    public QuoteSetAckGroup50SP2(FragmentContext context) {
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
    
    @XmlAttribute(name = "SetID")
    @Override
    public String getQuoteSetID() {
        return quoteSetID;
    }

    @Override
    public void setQuoteSetID(String quoteSetID) {
        this.quoteSetID = quoteSetID;
    }

    @XmlElementRef
    @Override
    public UnderlyingInstrument getUnderlyingInstrument() {
        return underlyingInstrument;
    }

    public void setUnderlyingInstrument(UnderlyingInstrument underlyingInstrument) {
        this.underlyingInstrument = underlyingInstrument;
    }

    @Override
    public void setUnderlyingInstrument() {
        this.underlyingInstrument = new UnderlyingInstrument50SP2(new FragmentContext(sessionCharset, messageEncoding, validateRequired));
    }

    @Override
    public void clearUnderlyingInstrument() {
        this.underlyingInstrument = null;
    }

    @XmlAttribute(name = "ValidTil")
    @Override
    public Date getQuoteSetValidUntilTime() {
        return quoteSetValidUntilTime;
    }

    @Override
    public void setQuoteSetValidUntilTime(Date quoteSetValidUntilTime) {
        this.quoteSetValidUntilTime = quoteSetValidUntilTime;
    }

    @XmlAttribute(name = "TotNoQuotEntries")
    @Override
    public Integer getTotNoQuoteEntries() {
        return totNoQuoteEntries;
    }

    @Override
    public void setTotNoQuoteEntries(Integer totNoQuoteEntries) {
        this.totNoQuoteEntries = totNoQuoteEntries;
    }

    @XmlAttribute(name = "TotNoCxldQts")
    @Override
    public Integer getTotNoCxldQuotes() {
        return totNoCxldQuotes;
    }

    @Override
    public void setTotNoCxldQuotes(Integer totNoCxldQuotes) {
        this.totNoCxldQuotes = totNoCxldQuotes;
    }

    @XmlAttribute(name = "TotNoAccQts")
    @Override
    public Integer getTotNoAccQuotes() {
        return totNoAccQuotes;
    }

    @Override
    public void setTotNoAccQuotes(Integer totNoAccQuotes) {
        this.totNoAccQuotes = totNoAccQuotes;
    }

    @XmlAttribute(name = "TotNoRejQts")
    @Override
    public Integer getTotNoRejQuotes() {
        return totNoRejQuotes;
    }

    @Override
    public void setTotNoRejQuotes(Integer totNoRejQuotes) {
        this.totNoRejQuotes = totNoRejQuotes;
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
    public Integer getNoQuoteEntries() {
        return noQuoteEntries;
    }

    @Override
    public void setNoQuoteEntries(Integer noQuoteEntries) {
        this.noQuoteEntries = noQuoteEntries;
        if (noQuoteEntries != null) {
            FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, validateRequired);
            quoteEntryAckGroups = new QuoteEntryAckGroup[noQuoteEntries.intValue()];
            for (int i = 0; i < quoteEntryAckGroups.length; i++) {
                quoteEntryAckGroups[i] = new QuoteEntryAckGroup50SP2(context);
            }
        }
    }

    @XmlElementRef
    @Override
    public QuoteEntryAckGroup[] getQuoteEntryAckGroups() {
        return quoteEntryAckGroups;
    }

    public void setQuoteEntryAckGroups(QuoteEntryAckGroup[] quoteEntryAckGroups) {
        this.quoteEntryAckGroups = quoteEntryAckGroups;
    }

    @Override
    public QuoteEntryAckGroup addQuoteEntryAckGroup() {
        QuoteEntryAckGroup group = new QuoteEntryAckGroup50SP2(new FragmentContext(sessionCharset, messageEncoding, validateRequired));
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
    protected void setFragmentCompTagValue(Tag tag, ByteBuffer message)
        throws BadFormatMsgException, InvalidMsgException, TagNotPresentException {
        FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, validateRequired);
        if (QUOTE_ENTRY_ACK_GROUP_TAGS.contains(tag.tagNum)) {
            if (noQuoteEntries != null && noQuoteEntries.intValue() > 0) {
                message.reset();
                if (quoteEntryAckGroups == null) {
                    quoteEntryAckGroups = new QuoteEntryAckGroup[noQuoteEntries.intValue()];
                }
                for (int i = 0; i < quoteEntryAckGroups.length; i++) {
                    QuoteEntryAckGroup group = new QuoteEntryAckGroup50SP2(context);
                    group.decode(message);
                    quoteEntryAckGroups[i] = group;
                }
            }
        }
        if (UND_INSTR_COMP_TAGS.contains(tag.tagNum)) {
            if (underlyingInstrument == null) {
                underlyingInstrument = new UnderlyingInstrument50SP2(context);
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
        return "This tag is not supported in [QuoteSetAckGroup] group version [5.0SP2].";
    }
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Package Methods">
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Private Methods">
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Inner Classes">
    // </editor-fold>
}
