/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * Stipulations44.java
 *
 * $Id: Stipulations44.java,v 1.11 2011-04-14 23:44:58 vrotaru Exp $
 */
package net.hades.fix.message.comp.impl.v44;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.comp.Stipulations;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.group.StipulationsGroup;
import net.hades.fix.message.struct.Tag;
import net.hades.fix.message.type.SessionRejectReason;
import net.hades.fix.message.type.TagNum;
import net.hades.fix.message.util.MsgUtil;
import net.hades.fix.message.util.TagEncoder;

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

import net.hades.fix.message.group.impl.v44.StipulationsGroup44;

/**
 * FIX version 4.4 implementation of Stipulations component.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.11 $
 * @created 15/02/2009, 4:53:05 PM
 */
@XmlTransient
public class Stipulations44 extends Stipulations {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> START_COMP_TAGS;

    protected static final Set<Integer> ALL_TAGS;

    protected static final Set<Integer> STIPS_GROUP_TAGS = new StipulationsGroup44().getFragmentAllTags();

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">

     static {
        ALL_TAGS = new HashSet<Integer>(TAGS);
        ALL_TAGS.addAll(STIPS_GROUP_TAGS);
        START_COMP_TAGS = new HashSet<Integer>(STIPS_GROUP_TAGS);
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">

    protected Set<Integer> STANDARD_SECURED_TAGS = ALL_TAGS;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public Stipulations44() {
        super();
        SECURED_TAGS = STANDARD_SECURED_TAGS;
    }

    public Stipulations44(FragmentContext context) {
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
    public Integer getNoStipulations() {
        return noStipulations;
    }

    @Override
    public void setNoStipulations(Integer noStipulations) {
        this.noStipulations = noStipulations;
        if (noStipulations != null) {
            stipulationsGroups = new StipulationsGroup[noStipulations.intValue()];
            for (int i = 0; i < stipulationsGroups.length; i++) {
                stipulationsGroups[i] = new StipulationsGroup44(new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired));
            }
        }
    }

    @Override
    public StipulationsGroup[] getStipulationsGroups() {
        return stipulationsGroups;
    }

    @Override
    public StipulationsGroup addStipulationsGroup() {

        StipulationsGroup group = new StipulationsGroup44(new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired));
        List<StipulationsGroup> groups = new ArrayList<StipulationsGroup>();
        if (stipulationsGroups != null && stipulationsGroups.length > 0) {
            groups = new ArrayList<StipulationsGroup>(Arrays.asList(stipulationsGroups));
        }
        groups.add(group);
        stipulationsGroups = groups.toArray(new StipulationsGroup[groups.size()]);
        noStipulations = new Integer(stipulationsGroups.length);

        return group;
    }

    public void setStipulationsGroups(StipulationsGroup[] stipulationsGroups) {
        this.stipulationsGroups = stipulationsGroups;
        if (stipulationsGroups != null) {
            noStipulations = new Integer(stipulationsGroups.length);
        }
    }

    @Override
    public StipulationsGroup deleteStipulationsGroup(int index) {
        StipulationsGroup result = null;
        if (stipulationsGroups != null && stipulationsGroups.length > 0 && stipulationsGroups.length > index) {
            List<StipulationsGroup> groups = new ArrayList<StipulationsGroup>(Arrays.asList(stipulationsGroups));
            result = groups.remove(index);
            stipulationsGroups = groups.toArray(new StipulationsGroup[groups.size()]);
            if (stipulationsGroups.length > 0) {
                noStipulations = new Integer(stipulationsGroups.length);
            } else {
                stipulationsGroups = null;
                noStipulations = null;
            }
        }

        return result;
    }

    @Override
    public int clearStipulationsGroups() {
        int result = 0;
        if (stipulationsGroups != null && stipulationsGroups.length > 0) {
            result = stipulationsGroups.length;
            stipulationsGroups = null;
            noStipulations = null;
        }

        return result;
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
            if (noStipulations != null && MsgUtil.isTagInList(TagNum.NoStipulations, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.NoStipulations, noStipulations);
                if (noStipulations != null && stipulationsGroups != null && stipulationsGroups.length == noStipulations.intValue()) {
                    for (int i = 0; i < noStipulations.intValue(); i++) {
                        if (stipulationsGroups[i] != null) {
                            bao.write(stipulationsGroups[i].encode(getMsgSecureTypeForFlag(secured)));
                        }
                    }
                } else {
                    String error = "StipulationsGroup field has been set but there is no data or the number of groups does not match.";
                    LOGGER.severe(error);
                    throw new BadFormatMsgException(SessionRejectReason.IncorrectCountForGroups, TagNum.NoStipulations.getValue(), error);
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
        if (STIPS_GROUP_TAGS.contains(tag.tagNum)) {
            if (noStipulations != null && noStipulations.intValue() > 0) {
                message.reset();
                stipulationsGroups = new StipulationsGroup[noStipulations.intValue()];
                FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired);
                for (int i = 0; i < noStipulations.intValue(); i++) {
                    StipulationsGroup group = new StipulationsGroup44(context);
                    group.decode(message);
                    stipulationsGroups[i] = group;
                }
            }
        }
    }

    @Override
    protected String getUnsupportedTagMessage() {
        return "This tag is not supported in [Stipulations] component version [4.4].";
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
