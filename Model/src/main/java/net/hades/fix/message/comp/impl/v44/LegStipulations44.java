/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * LegStipulations44.java
 *
 * $Id: LegStipulations44.java,v 1.11 2011-04-14 23:44:59 vrotaru Exp $
 */
package net.hades.fix.message.comp.impl.v44;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.group.LegStipulationsGroup;
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
import java.util.logging.Level;

import javax.xml.bind.annotation.XmlTransient;

import net.hades.fix.message.comp.LegStipulations;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.group.impl.v44.LegStipulationsGroup44;
import net.hades.fix.message.util.TagEncoder;

/**
 * FIX version 4.4 implementation of LegStipulations component.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.11 $
 * @created 15/02/2009, 6:39:27 PM
 */
@XmlTransient
public class LegStipulations44 extends LegStipulations {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> START_COMP_TAGS;

    protected static final Set<Integer> ALL_TAGS;

    protected static final Set<Integer> LEG_STIPS_GROUP_TAGS = new LegStipulationsGroup44().getFragmentAllTags();

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">

     static {
        ALL_TAGS = new HashSet<Integer>(TAGS);
        ALL_TAGS.addAll(LEG_STIPS_GROUP_TAGS);
        START_COMP_TAGS = new HashSet<Integer>(LEG_STIPS_GROUP_TAGS);
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">

    protected Set<Integer> STANDARD_SECURED_TAGS = ALL_TAGS;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public LegStipulations44() {
        super();
        SECURED_TAGS = STANDARD_SECURED_TAGS;
    }

    public LegStipulations44(FragmentContext context) {
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
    public LegStipulationsGroup[] getLegStipulationsGroups() {
        return legStipulationsGroups;
    }

    public void setLegStipulationsGroups(LegStipulationsGroup[] legStipulationsGroups) {
        this.legStipulationsGroups = legStipulationsGroups;
        if (legStipulationsGroups != null) {
            noLegStipulations = new Integer(legStipulationsGroups.length);
        }
    }

    @Override
    public void setNoLegStipulations(Integer noLegStipulations) {
        this.noLegStipulations = noLegStipulations;
        if (noLegStipulations != null) {
            legStipulationsGroups = new LegStipulationsGroup[noLegStipulations.intValue()];
            for (int i = 0; i < legStipulationsGroups.length; i++) {
                legStipulationsGroups[i] = new LegStipulationsGroup44(new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired));
            }
        }
    }

    @Override
    public LegStipulationsGroup addLegStipulationsGroup() {

        LegStipulationsGroup group = new LegStipulationsGroup44(new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired));
        List<LegStipulationsGroup> groups = new ArrayList<LegStipulationsGroup>();
        if (legStipulationsGroups != null && legStipulationsGroups.length > 0) {
            groups = new ArrayList<LegStipulationsGroup>(Arrays.asList(legStipulationsGroups));
        }
        groups.add(group);
        legStipulationsGroups = groups.toArray(new LegStipulationsGroup[groups.size()]);
        noLegStipulations = new Integer(legStipulationsGroups.length);

        return group;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

    @Override
    protected byte[] encodeFragmentSecured(boolean secured) throws TagNotPresentException, BadFormatMsgException {
        if (validateRequired) {
            validateRequiredTags();
        }
        byte[] result = new byte[0];
        ByteArrayOutputStream bao = new ByteArrayOutputStream();

        try {
            if (MsgUtil.isTagInList(TagNum.NoLegStipulations, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.NoLegStipulations, noLegStipulations);
                if (noLegStipulations != null && legStipulationsGroups != null && legStipulationsGroups.length == noLegStipulations.intValue()) {
                    for (int i = 0; i < noLegStipulations.intValue(); i++) {
                        if (legStipulationsGroups[i] != null) {
                            bao.write(legStipulationsGroups[i].encode(getMsgSecureTypeForFlag(secured)));
                        }
                    }
                } else {
                    String error = "LegStipulationsGroup field has been set but there is no data or the number of groups does not match.";
                    LOGGER.severe(error);
                    throw new BadFormatMsgException(SessionRejectReason.IncorrectCountForGroups, TagNum.NoLegStipulations.getValue(), error);
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
        if (noLegStipulations != null && noLegStipulations.intValue() > 0) {
            if (LEG_STIPS_GROUP_TAGS.contains(tag.tagNum)) {
                message.reset();
                legStipulationsGroups = new LegStipulationsGroup[noLegStipulations.intValue()];
                FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired);
                for (int i = 0; i < noLegStipulations.intValue(); i++) {
                    LegStipulationsGroup group = new LegStipulationsGroup44(context);
                    group.decode(message);
                    legStipulationsGroups[i] = group;
                }
            }
        }
    }

    @Override
    protected String getUnsupportedTagMessage() {
        return "This tag is not supported in [LegStipulations] component version [4.4].";
    }

    @Override
    protected Set<Integer> getFragmentCompTags() {
        return START_COMP_TAGS;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Package Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Private Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Inner Classes">
    // </editor-fold>
}
