/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * QuoteSetGroup50SP2.java
 *
 * $Id: QuoteSetGroup50SP2.java,v 1.10 2011-04-14 23:44:30 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v50sp2;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.group.QuoteEntryGroup;
import net.hades.fix.message.group.QuoteSetGroup;
import net.hades.fix.message.struct.Tag;

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
import net.hades.fix.message.xml.codec.jaxb.adapter.FixBooleanAdapter;

/**
 * FIX 5.0SP2 implementation of QuoteSetGroup group.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.10 $
 * @created 12/02/2009, 7:22:35 PM
 */
@XmlRootElement(name="QuotSet")
@XmlType(propOrder={"underlyingInstrument", "quoteEntryGroups"})
@XmlAccessorType(XmlAccessType.NONE)
public class QuoteSetGroup50SP2 extends QuoteSetGroup {
    
    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;
    
    protected static final Set<Integer> START_COMP_TAGS;

    protected static final Set<Integer> ALL_TAGS;
    
    protected static final Set<Integer> QUOTE_ENTRY_GROUP_TAGS = new QuoteEntryGroup50SP2().getFragmentAllTags();
    protected static final Set<Integer> UND_INSTR_COMP_TAGS = new UnderlyingInstrument50SP2().getFragmentAllTags();
    
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
    
    public QuoteSetGroup50SP2() {
        SECURED_TAGS = STANDARD_SECURED_TAGS;
    }
    
    public QuoteSetGroup50SP2(FragmentContext context) {
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
            quoteEntryGroups = new QuoteEntryGroup[noQuoteEntries.intValue()];
            for (int i = 0; i < quoteEntryGroups.length; i++) {
                quoteEntryGroups[i] = new QuoteEntryGroup50SP2(context);
            }
        }
    }

    @XmlElementRef
    @Override
    public QuoteEntryGroup[] getQuoteEntryGroups() {
        return quoteEntryGroups;
    }

    public void setQuoteEntryGroups(QuoteEntryGroup[] quoteEntryGroups) {
        this.quoteEntryGroups = quoteEntryGroups;
    }

    @Override
    public QuoteEntryGroup addQuoteEntryGroup() {
        QuoteEntryGroup group = new QuoteEntryGroup50SP2(new FragmentContext(sessionCharset, messageEncoding, validateRequired));
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
    protected void setFragmentCompTagValue(Tag tag, ByteBuffer message)
        throws BadFormatMsgException, InvalidMsgException, TagNotPresentException {
        FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, validateRequired);
        if (QUOTE_ENTRY_GROUP_TAGS.contains(tag.tagNum)) {
            if (noQuoteEntries != null && noQuoteEntries.intValue() > 0) {
                message.reset();
                if (quoteEntryGroups == null) {
                    quoteEntryGroups = new QuoteEntryGroup[noQuoteEntries.intValue()];
                }
                for (int i = 0; i < quoteEntryGroups.length; i++) {
                    QuoteEntryGroup group = new QuoteEntryGroup50SP2(context);
                    group.decode(message);
                    quoteEntryGroups[i] = group;
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
        return "This tag is not supported in [QuoteSetGroup] group version [5.0SP2].";
    }
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Package Methods">
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Private Methods">
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Inner Classes">
    // </editor-fold>
}
