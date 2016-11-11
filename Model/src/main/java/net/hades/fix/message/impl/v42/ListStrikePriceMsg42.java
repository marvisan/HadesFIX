/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * ListStrikePriceMsg42.java
 *
 * $Id: ListStrikePriceMsg42.java,v 1.1 2011-04-15 04:37:43 vrotaru Exp $
 */
package net.hades.fix.message.impl.v42;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.Header;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.group.impl.v42.InstrmtStrikePriceGroup42;
import net.hades.fix.message.struct.Tag;
import net.hades.fix.message.type.ApplVerID;
import net.hades.fix.message.type.SessionRejectReason;
import net.hades.fix.message.type.TagNum;
import net.hades.fix.message.util.MsgUtil;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;

import net.hades.fix.message.ListStrikePriceMsg;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.group.InstrmtStrikePriceGroup;
import net.hades.fix.message.type.BeginString;
import net.hades.fix.message.util.TagEncoder;

/**
 * FIX version 4.2 ListStrikePriceMsg implementation.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 21/04/2009, 9:32:41 AM
 */
public class ListStrikePriceMsg42 extends ListStrikePriceMsg {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> INSTRMT_STRK_PX_GROUP_TAGS = new InstrmtStrikePriceGroup42().getFragmentAllTags();

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

    protected Set<Integer> STANDARD_SECURED_TAGS = TAGS;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public ListStrikePriceMsg42() {
        super();
    }

    public ListStrikePriceMsg42(Header header, ByteBuffer rawMsg)
    throws InvalidMsgException, TagNotPresentException, BadFormatMsgException {
        super(header, rawMsg);
        SECURED_TAGS = STANDARD_SECURED_TAGS;
    }

    public ListStrikePriceMsg42(BeginString beginString) throws InvalidMsgException {
        super(beginString);
        SECURED_TAGS = STANDARD_SECURED_TAGS;
    }

    public ListStrikePriceMsg42(BeginString beginString, ApplVerID applVerID) throws InvalidMsgException {
        super(beginString, applVerID);
        SECURED_TAGS = STANDARD_SECURED_TAGS;
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

    // ACCESSOR METHODS
    //////////////////////////////////////////

    @Override
    public void setNoStrikes(Integer noStrikes) {
        this.noStrikes = noStrikes;
        if (noStrikes != null) {
            instrmtStrikePriceGroups = new InstrmtStrikePriceGroup[noStrikes.intValue()];
            for (int i = 0; i < instrmtStrikePriceGroups.length; i++) {
                instrmtStrikePriceGroups[i] = new InstrmtStrikePriceGroup42(new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired));
            }
        }
    }

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
        InstrmtStrikePriceGroup group = new InstrmtStrikePriceGroup42(new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired));
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
    protected byte[] encodeFragmentSecured(boolean secured) throws TagNotPresentException, BadFormatMsgException {
        byte[] result = new byte[0];
        ByteArrayOutputStream bao = new ByteArrayOutputStream();
        try {
            if (MsgUtil.isTagInList(TagNum.ListID, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.ListID, listID);
            }
            if (MsgUtil.isTagInList(TagNum.TotNoStrikes, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.TotNoStrikes, totNoStrikes);
            }
            if (MsgUtil.isTagInList(TagNum.LastFragment, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.LastFragment, lastFragment);
            }
            if (noStrikes != null && MsgUtil.isTagInList(TagNum.NoStrikes, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.NoStrikes, noStrikes);
                if (instrmtStrikePriceGroups != null && instrmtStrikePriceGroups.length == noStrikes.intValue()) {
                    for (int i = 0; i < noStrikes.intValue(); i++) {
                        if (instrmtStrikePriceGroups[i] != null) {
                            bao.write(instrmtStrikePriceGroups[i].encode(getMsgSecureTypeForFlag(secured)));
                        }
                    }
                } else {
                    String error = "InstrmtStrikePriceGroups field has been set but there is no data or the number of groups does not match.";
                    LOGGER.severe(error);
                    throw new BadFormatMsgException(SessionRejectReason.IncorrectCountForGroups, getHeader().getMsgType(),
                            TagNum.NoStrikes.getValue(), error);
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
        if (INSTRMT_STRK_PX_GROUP_TAGS.contains(tag.tagNum)) {
            if (noStrikes != null && noStrikes.intValue() > 0) {
                message.reset();
                instrmtStrikePriceGroups = new InstrmtStrikePriceGroup[noStrikes.intValue()];
                for (int i = 0; i < noStrikes.intValue(); i++) {
                    InstrmtStrikePriceGroup group = new InstrmtStrikePriceGroup42(context);
                    group.decode(message);
                    instrmtStrikePriceGroups[i] = group;
                }
            }
        }
    }

    @Override
    protected String getUnsupportedTagMessage() {
        return "This tag is not supported in [ListStrikePriceMsg] message version [4.2].";
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
