/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * ListStrikePriceMsg50.java
 *
 * $Id: ListStrikePriceMsg50.java,v 1.1 2011-04-15 04:37:44 vrotaru Exp $
 */
package net.hades.fix.message.impl.v50;

import net.hades.fix.message.FIXFragment;
import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.Header;
import net.hades.fix.message.ListStrikePriceMsg;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.struct.Tag;
import net.hades.fix.message.type.ApplVerID;
import net.hades.fix.message.type.BeginString;
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

import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.group.InstrmtStrikePriceGroup;
import net.hades.fix.message.group.impl.v50.InstrmtStrikePriceGroup50;

/**
 * FIX version 5.0 ListStrikePriceMsg implementation.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 21/04/2009, 9:32:41 AM
 */
@XmlRootElement(name="ListStrkPx")
@XmlType(propOrder = {"header", "instrmtStrikePriceGroups"})
@XmlAccessorType(XmlAccessType.NONE)
public class ListStrikePriceMsg50 extends ListStrikePriceMsg {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> INSTRMT_STRK_PX_GROUP_TAGS = new InstrmtStrikePriceGroup50().getFragmentAllTags();

    protected static final Set<Integer> START_COMP_TAGS;

    protected static final Set<Integer> ALL_TAGS;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">

    static {
        ALL_TAGS = new HashSet<Integer>(TAGS);
        ALL_TAGS.addAll(INSTRMT_STRK_PX_GROUP_TAGS);
        START_COMP_TAGS = new HashSet<Integer>(INSTRMT_STRK_PX_GROUP_TAGS);
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public ListStrikePriceMsg50() {
        super();
    }

    public ListStrikePriceMsg50(Header header, ByteBuffer rawMsg)
    throws InvalidMsgException, TagNotPresentException, BadFormatMsgException {
        super(header, rawMsg);
    }

    public ListStrikePriceMsg50(BeginString beginString) throws InvalidMsgException {
        super(beginString);
    }

    public ListStrikePriceMsg50(BeginString beginString, ApplVerID applVerID) throws InvalidMsgException {
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
        ListStrikePriceMsg50 fixml = (ListStrikePriceMsg50) fragment;
        if (fixml.getListID() != null) {
            listID = fixml.getListID();
        }
        if (fixml.getTotNoStrikes() != null) {
            totNoStrikes = fixml.getTotNoStrikes();
        }
        if (fixml.getLastFragment() != null) {
            lastFragment = fixml.getLastFragment();
        }
        if (fixml.getInstrmtStrikePriceGroups() != null && fixml.getInstrmtStrikePriceGroups().length > 0) {
            setInstrmtStrikePriceGroups(fixml.getInstrmtStrikePriceGroups());
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

    @XmlAttribute(name = "ID")
    @Override
    public String getListID() {
        return listID;
    }

    @Override
    public void setListID(String listID) {
        this.listID = listID;
    }

    @XmlAttribute(name = "TotNoStrks")
    @Override
    public Integer getTotNoStrikes() {
        return totNoStrikes;
    }

    @Override
    public void setTotNoStrikes(Integer totNoStrikes) {
        this.totNoStrikes = totNoStrikes;
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
    public void setNoStrikes(Integer noStrikes) {
        this.noStrikes = noStrikes;
        if (noStrikes != null) {
            instrmtStrikePriceGroups = new InstrmtStrikePriceGroup[noStrikes.intValue()];
            for (int i = 0; i < instrmtStrikePriceGroups.length; i++) {
                instrmtStrikePriceGroups[i] = new InstrmtStrikePriceGroup50(new FragmentContext(sessionCharset, messageEncoding, validateRequired));
            }
        }
    }

    @XmlElementRef
    @Override
    public InstrmtStrikePriceGroup[] getInstrmtStrikePriceGroups() {
        return instrmtStrikePriceGroups;
    }

    public void setInstrmtStrikePriceGroups(InstrmtStrikePriceGroup[] instrmtStrikePriceGroups) {
        this.instrmtStrikePriceGroups = instrmtStrikePriceGroups;
        if (instrmtStrikePriceGroups != null) {
            noStrikes = new Integer(instrmtStrikePriceGroups.length);
        }
    }

    @Override
    public InstrmtStrikePriceGroup addInstrmtStrikePriceGroup() {
        InstrmtStrikePriceGroup group = new InstrmtStrikePriceGroup50(new FragmentContext(sessionCharset, messageEncoding, validateRequired));
        List<InstrmtStrikePriceGroup> groups = new ArrayList<InstrmtStrikePriceGroup>();
        if (instrmtStrikePriceGroups != null && instrmtStrikePriceGroups.length > 0) {
            groups = new ArrayList<InstrmtStrikePriceGroup>(Arrays.asList(instrmtStrikePriceGroups));
        }
        groups.add(group);
        instrmtStrikePriceGroups = groups.toArray(new InstrmtStrikePriceGroup[groups.size()]);
        noStrikes = new Integer(instrmtStrikePriceGroups.length);

        return group;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

    @Override
    protected void setFragmentCompTagValue(Tag tag, ByteBuffer message)
    throws BadFormatMsgException, InvalidMsgException, TagNotPresentException {
        FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, validateRequired);
        if (INSTRMT_STRK_PX_GROUP_TAGS.contains(tag.tagNum)) {
            if (noStrikes != null && noStrikes.intValue() > 0) {
                message.reset();
                instrmtStrikePriceGroups = new InstrmtStrikePriceGroup[noStrikes.intValue()];
                for (int i = 0; i < noStrikes.intValue(); i++) {
                    InstrmtStrikePriceGroup group = new InstrmtStrikePriceGroup50(context);
                    group.decode(message);
                    instrmtStrikePriceGroups[i] = group;
                }
            }
        }
    }

    @Override
    protected String getUnsupportedTagMessage() {
        return "This tag is not supported in [ListStrikePriceMsg] message version [5.0].";
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
