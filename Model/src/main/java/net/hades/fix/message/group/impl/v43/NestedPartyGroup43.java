/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * NestedPartyGroup43.java
 *
 * $Id: NestedPartyGroup43.java,v 1.9 2011-01-12 11:33:59 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v43;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.type.TagNum;
import net.hades.fix.message.util.MsgUtil;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;

import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.group.NestedPartyGroup;
import net.hades.fix.message.util.TagEncoder;

/**
 * FIX 4.3 implementation of NestedPartyGroup group.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.9 $
 * @created 06/04/2009, 3:22:58 PM
 */
public class NestedPartyGroup43 extends NestedPartyGroup {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> TAGS_V43 = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.NestedPartyID.getValue(),
        TagNum.NestedPartyIDSource.getValue(),
        TagNum.NestedPartyRole.getValue(),
        TagNum.NestedPartySubID.getValue()
    }));

    protected static final Set<Integer> START_COMP_TAGS = null;

    protected static final Set<Integer> ALL_TAGS;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">

    static {
        ALL_TAGS = new HashSet<Integer>(TAGS_V43);
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">

    protected Set<Integer> STANDARD_SECURED_TAGS = TAGS_V43;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public NestedPartyGroup43() {
        SECURED_TAGS = STANDARD_SECURED_TAGS;
    }

    public NestedPartyGroup43(FragmentContext context) {
        super(context);
        SECURED_TAGS = STANDARD_SECURED_TAGS;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Public Methods">

    @Override
    public Set<Integer> getFragmentTags() {
        return TAGS_V43;
    }

    @Override
    public Set<Integer> getFragmentAllTags() {
        return ALL_TAGS;
    }

    // ACCESSOR METHODS
    //////////////////////////////////////////

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

    @Override
    protected byte[] encodeFragmentSecured(boolean secured) throws TagNotPresentException, BadFormatMsgException {
        byte[] result = new byte[0];
        ByteArrayOutputStream bao = new ByteArrayOutputStream();

        try {
            if (MsgUtil.isTagInList(TagNum.NestedPartyID, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.NestedPartyID, nestedPartyID);
            }
            if (nestedPartyIDSource != null && MsgUtil.isTagInList(TagNum.NestedPartyIDSource, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.NestedPartyIDSource, nestedPartyIDSource.getValue());
            }
            if (nestedPartyRole != null && MsgUtil.isTagInList(TagNum.NestedPartyRole, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.NestedPartyRole, nestedPartyRole.getValue());
            }
            if (MsgUtil.isTagInList(TagNum.NestedPartySubID, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.NestedPartySubID, nestedPartySubID);
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
    protected Set<Integer> getFragmentSecuredTags() {
        return SECURED_TAGS;
    }

    @Override
    protected Set<Integer> getFragmentCompTags() {
        return START_COMP_TAGS;
    }

    @Override
    protected String getUnsupportedTagMessage() {
        return "This tag is not supported in [NestedPartyGroup] group version [4.3].";
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Package Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Private Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Inner Classes">
    // </editor-fold>
}
