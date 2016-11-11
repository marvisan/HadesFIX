/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * UnderlyingStipulations44.java
 *
 * $Id: UnderlyingStipulations44.java,v 1.11 2011-04-14 23:44:58 vrotaru Exp $
 */
package net.hades.fix.message.comp.impl.v44;

import net.hades.fix.message.struct.Tag;

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

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.comp.UnderlyingStipulations;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.group.UnderlyingStipsGroup;
import net.hades.fix.message.group.impl.v44.UnderlyingStipsGroup44;
import net.hades.fix.message.type.SessionRejectReason;
import net.hades.fix.message.type.TagNum;
import net.hades.fix.message.util.MsgUtil;
import net.hades.fix.message.util.TagEncoder;

/**
 * FIX version 4.4 implementation of UnderlyingStipulation component.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.11 $
 * @created 28/12/2008, 11:29:46 AM
 */
@XmlTransient
public class UnderlyingStipulations44 extends UnderlyingStipulations {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> START_COMP_TAGS;

    protected static final Set<Integer> ALL_TAGS;

    protected static final Set<Integer> UNDLY_STIPS_GROUP_TAGS = new UnderlyingStipsGroup44().getFragmentAllTags();

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">

     static {
        ALL_TAGS = new HashSet<Integer>(TAGS);
        ALL_TAGS.addAll(UNDLY_STIPS_GROUP_TAGS);
        START_COMP_TAGS = new HashSet<Integer>(UNDLY_STIPS_GROUP_TAGS);
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">

     protected Set<Integer> STANDARD_SECURED_TAGS = ALL_TAGS;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public UnderlyingStipulations44() {
        super();
        SECURED_TAGS = STANDARD_SECURED_TAGS;
    }

    public UnderlyingStipulations44(FragmentContext context) {
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
    public Integer getNoUnderlyingStips() {
        return noUnderlyingStips;
    }

    @Override
    public void setNoUnderlyingStips(Integer noUnderlyingStips) {
        this.noUnderlyingStips = noUnderlyingStips;
        if (noUnderlyingStips != null) {
            underlyingStipsGroups = new UnderlyingStipsGroup[noUnderlyingStips.intValue()];
            for (int i = 0; i < underlyingStipsGroups.length; i++) {
                underlyingStipsGroups[i] = new UnderlyingStipsGroup44(new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired));
            }
        }
    }

    @Override
    public UnderlyingStipsGroup[] getUnderlyingStipsGroups() {
        return underlyingStipsGroups;
    }

    @Override
    public void setUnderlyingStipsGroups(UnderlyingStipsGroup[] underlyingStipsGroups) {
        this.underlyingStipsGroups = underlyingStipsGroups;
        if (underlyingStipsGroups != null) {
            noUnderlyingStips = new Integer(underlyingStipsGroups.length);
        }

    }

    @Override
    public UnderlyingStipsGroup addUnderlyingStipsGroup() {

        UnderlyingStipsGroup group = new UnderlyingStipsGroup44(new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired));
        List<UnderlyingStipsGroup> groups = new ArrayList<UnderlyingStipsGroup>();
        if (underlyingStipsGroups != null && underlyingStipsGroups.length > 0) {
            groups = new ArrayList<UnderlyingStipsGroup>(Arrays.asList(underlyingStipsGroups));
        }
        groups.add(group);
        underlyingStipsGroups = groups.toArray(new UnderlyingStipsGroup[groups.size()]);
        noUnderlyingStips = new Integer(underlyingStipsGroups.length);

        return group;
    }

    @Override
    public UnderlyingStipsGroup deleteUnderlyingStipsGroup(int index) {
        UnderlyingStipsGroup result = null;

        if (underlyingStipsGroups != null && underlyingStipsGroups.length > 0 && underlyingStipsGroups.length > index) {
            List<UnderlyingStipsGroup> groups = new ArrayList<UnderlyingStipsGroup>(Arrays.asList(underlyingStipsGroups));
            result = groups.remove(index);
            underlyingStipsGroups = groups.toArray(new UnderlyingStipsGroup[groups.size()]);
            if (underlyingStipsGroups.length > 0) {
                noUnderlyingStips = new Integer(underlyingStipsGroups.length);
            } else {
                underlyingStipsGroups = null;
                noUnderlyingStips = null;
            }
        }

        return result;
    }

    @Override
    public int clearUnderlyingStipsGroups() {
        int result = 0;
        if (underlyingStipsGroups != null && underlyingStipsGroups.length > 0) {
            result = underlyingStipsGroups.length;
            underlyingStipsGroups = null;
            noUnderlyingStips = null;
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
            if (noUnderlyingStips != null && MsgUtil.isTagInList(TagNum.NoUnderlyingStips, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.NoUnderlyingStips, noUnderlyingStips);
                if (underlyingStipsGroups != null && underlyingStipsGroups.length == noUnderlyingStips.intValue()) {
                    for (int i = 0; i < noUnderlyingStips.intValue(); i++) {
                        if (underlyingStipsGroups[i] != null) {
                            bao.write(underlyingStipsGroups[i].encode(getMsgSecureTypeForFlag(secured)));
                        }
                    }
                } else {
                    String error = "UnderlyingStipsGroup field has been set but there is no data or the number of groups does not match.";
                    LOGGER.severe(error);
                    throw new BadFormatMsgException(SessionRejectReason.IncorrectCountForGroups, TagNum.NoUnderlyingStips.getValue(), error);
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
        if (noUnderlyingStips != null && noUnderlyingStips.intValue() > 0) {
            if (UNDLY_STIPS_GROUP_TAGS.contains(tag.tagNum)) {
                message.reset();
                underlyingStipsGroups = new UnderlyingStipsGroup[noUnderlyingStips.intValue()];
                FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired);
                for (int i = 0; i < noUnderlyingStips.intValue(); i++) {
                    UnderlyingStipsGroup group = new UnderlyingStipsGroup44(context);
                    group.decode(message);
                    underlyingStipsGroups[i] = group;
                }
            }
        }
    }

    @Override
    protected String getUnsupportedTagMessage() {
        return "This tag is not supported in [UnderlyingStipulations] component version [4.4].";
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
