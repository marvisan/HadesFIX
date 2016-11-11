/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * DerivativeInstrumentParties50SP1.java
 *
 * $Id: DerivativeInstrumentParties50SP1.java,v 1.1 2011-09-19 08:15:45 vrotaru Exp $
 */
package net.hades.fix.message.comp.impl.v50sp1;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.group.DerivativeInstrumentPartyGroup;
import net.hades.fix.message.group.impl.v50sp1.DerivativeInstrumentPartyGroup50SP1;
import net.hades.fix.message.struct.Tag;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.xml.bind.annotation.XmlTransient;

import net.hades.fix.message.comp.DerivativeInstrumentParties;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.TagNotPresentException;

/**
 * FIX 5.0SP1 implementation of DerivativeInstrumentParties component.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 02/01/2009, 12:07:02 PM
 */
@XmlTransient
public class DerivativeInstrumentParties50SP1 extends DerivativeInstrumentParties {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> START_COMP_TAGS;

    protected static final Set<Integer> ALL_TAGS;

    protected static final Set<Integer> INSTRUMENT_PARTY_GROUP_TAGS = new DerivativeInstrumentPartyGroup50SP1().getFragmentAllTags();

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">

    static {
        ALL_TAGS = new HashSet<Integer>(TAGS);
        ALL_TAGS.addAll(INSTRUMENT_PARTY_GROUP_TAGS);
        START_COMP_TAGS = new HashSet<Integer>(INSTRUMENT_PARTY_GROUP_TAGS);
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public DerivativeInstrumentParties50SP1() {
        super();
    }

    public DerivativeInstrumentParties50SP1(FragmentContext context) {
        super(context);
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
    public Integer getNoDerivativeInstrumentParties() {
        return noDerivativeInstrumentParties;
    }

    @Override
    public void setNoDerivativeInstrumentParties(Integer noDerivativeInstrumentParties) {
        this.noDerivativeInstrumentParties = noDerivativeInstrumentParties;
        if (noDerivativeInstrumentParties != null) {
            derivativeInstrumentPartyGroups = new DerivativeInstrumentPartyGroup[noDerivativeInstrumentParties.intValue()];
            for (int i = 0; i < derivativeInstrumentPartyGroups.length; i++) {
                derivativeInstrumentPartyGroups[i] = new DerivativeInstrumentPartyGroup50SP1(new FragmentContext(sessionCharset, messageEncoding, validateRequired));
            }
        }
    }

    @Override
    public DerivativeInstrumentPartyGroup[] getDerivativeInstrumentPartyGroups() {
        return derivativeInstrumentPartyGroups;
    }
    
    public void setDerivativeInstrumentPartyGroups(DerivativeInstrumentPartyGroup[] derivativeInstrumentPartyGroups) {
        this.derivativeInstrumentPartyGroups = derivativeInstrumentPartyGroups;
        if (derivativeInstrumentPartyGroups != null) {
            noDerivativeInstrumentParties = new Integer(derivativeInstrumentPartyGroups.length);
        }
    }

    @Override
    public DerivativeInstrumentPartyGroup addDerivativeInstrumentPartyGroup() {

        DerivativeInstrumentPartyGroup group = new DerivativeInstrumentPartyGroup50SP1(new FragmentContext(sessionCharset, messageEncoding, validateRequired));
        List<DerivativeInstrumentPartyGroup> groups = new ArrayList<DerivativeInstrumentPartyGroup>();
        if (derivativeInstrumentPartyGroups != null && derivativeInstrumentPartyGroups.length > 0) {
            groups = new ArrayList<DerivativeInstrumentPartyGroup>(Arrays.asList(derivativeInstrumentPartyGroups));
        }
        groups.add(group);
        derivativeInstrumentPartyGroups = groups.toArray(new DerivativeInstrumentPartyGroup[groups.size()]);
        noDerivativeInstrumentParties = new Integer(derivativeInstrumentPartyGroups.length);

        return group;
    }

    @Override
    public DerivativeInstrumentPartyGroup deleteDerivativeInstrumentPartyGroup(int index) {

        DerivativeInstrumentPartyGroup result = null;

        if (derivativeInstrumentPartyGroups != null && derivativeInstrumentPartyGroups.length > 0 && derivativeInstrumentPartyGroups.length > index) {
            List<DerivativeInstrumentPartyGroup> groups = new ArrayList<DerivativeInstrumentPartyGroup>(Arrays.asList(derivativeInstrumentPartyGroups));
            result = groups.remove(index);
            derivativeInstrumentPartyGroups = groups.toArray(new DerivativeInstrumentPartyGroup[groups.size()]);
            if (derivativeInstrumentPartyGroups.length > 0) {
                noDerivativeInstrumentParties = new Integer(derivativeInstrumentPartyGroups.length);
            } else {
                derivativeInstrumentPartyGroups = null;
                noDerivativeInstrumentParties = null;
            }
        }

        return result;
    }

    @Override
    public int clearDerivativeInstrumentPartyGroup() {

        int result = 0;
        if (derivativeInstrumentPartyGroups != null && derivativeInstrumentPartyGroups.length > 0) {
            result = derivativeInstrumentPartyGroups.length;
            derivativeInstrumentPartyGroups = null;
            noDerivativeInstrumentParties = null;
        }

        return result;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

    @Override
    protected void setFragmentCompTagValue(Tag tag, ByteBuffer message)
    throws BadFormatMsgException, InvalidMsgException, TagNotPresentException {
        if (noDerivativeInstrumentParties != null && noDerivativeInstrumentParties.intValue() > 0) {
            if (INSTRUMENT_PARTY_GROUP_TAGS.contains(tag.tagNum)) {
                message.reset();
                derivativeInstrumentPartyGroups = new DerivativeInstrumentPartyGroup[noDerivativeInstrumentParties.intValue()];
                FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, validateRequired);
                for (int i = 0; i < noDerivativeInstrumentParties.intValue(); i++) {
                    DerivativeInstrumentPartyGroup group = new DerivativeInstrumentPartyGroup50SP1(context);
                    group.decode(message);
                    derivativeInstrumentPartyGroups[i] = group;
                }
            }
        }
    }

    @Override
    protected Set<Integer> getFragmentCompTags() {
        return START_COMP_TAGS;
    }

    @Override
    protected String getUnsupportedTagMessage() {
        return "This tag is not supported in DerivativeInstrumentParties component release [5.0].";
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Package Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Private Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Inner Classes">
    // </editor-fold>
}
