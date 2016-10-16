/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * TradingSessionGroup42.java
 *
 * $Id: TradingSessionGroup42.java,v 1.3 2010-12-22 09:30:31 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v42;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.group.TradingSessionGroup;
import net.hades.fix.message.type.TagNum;
import net.hades.fix.message.util.MsgUtil;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;

import net.hades.fix.message.util.TagEncoder;

/**
 * FIX 4.2 implementation of TradingSessionGroup group.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.3 $
 * @created 05/04/2009, 11:39:24 AM
 */
public class TradingSessionGroup42 extends TradingSessionGroup {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> TAGS_V42 = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.TradingSessionID.getValue(),
        TagNum.TradingSessionSubID.getValue()
    }));

    protected static final Set<Integer> START_COMP_TAGS = null;

    protected static final Set<Integer> ALL_TAGS;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">

    static {
        ALL_TAGS = new HashSet<Integer>(TAGS);
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">
    
    protected Set<Integer> STANDARD_SECURED_TAGS = TAGS_V42;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public TradingSessionGroup42() {
        SECURED_TAGS = STANDARD_SECURED_TAGS;
    }

    public TradingSessionGroup42(FragmentContext context) {
        super(context);
        SECURED_TAGS = STANDARD_SECURED_TAGS;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Public Methods">

    @Override
    public Set<Integer> getFragmentAllTags() {
        return ALL_TAGS;
    }

    // ACCESSORS
    //////////////////////////////////////////

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
            if (MsgUtil.isTagInList(TagNum.TradingSessionID, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.TradingSessionID, tradingSessionID);
            }
            if (MsgUtil.isTagInList(TagNum.TradingSessionSubID, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.TradingSessionSubID, tradingSessionSubID);
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
    protected String getUnsupportedTagMessage() {
        return "This tag is not supported in [TradingSessionGroup] group version [4.2].";
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
