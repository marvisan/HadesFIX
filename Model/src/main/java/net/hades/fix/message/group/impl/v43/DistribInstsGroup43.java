/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * DistribInstsGroup43.java
 *
 * $Id: DistribInstsGroup43.java,v 1.1 2011-10-27 09:16:59 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v43;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.group.DistribInstsGroup;
import net.hades.fix.message.type.TagNum;
import net.hades.fix.message.util.MsgUtil;
import net.hades.fix.message.util.TagEncoder;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;

/**
 * FIX 4.3 implementation of DistribInsts group.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 05/04/2009, 11:39:24 AM
 */
public class DistribInstsGroup43 extends DistribInstsGroup {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> ALL_TAGS;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">

    static {
        ALL_TAGS = new HashSet<Integer>(TAGS);
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">
    
    protected Set<Integer> STANDARD_SECURED_TAGS = TAGS;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public DistribInstsGroup43() {
        SECURED_TAGS = STANDARD_SECURED_TAGS;
    }

    public DistribInstsGroup43(FragmentContext context) {
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
            if (distribPaymentMethod != null && MsgUtil.isTagInList(TagNum.DistribPaymentMethod, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.DistribPaymentMethod, distribPaymentMethod.getValue());
            }
            if (MsgUtil.isTagInList(TagNum.DistribPercentage, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.DistribPercentage, distribPercentage);
            }
            if (cashDistribCurr != null && MsgUtil.isTagInList(TagNum.CashDistribCurr, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.CashDistribCurr, cashDistribCurr.getValue());
            }
            if (MsgUtil.isTagInList(TagNum.CashDistribAgentName, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.CashDistribAgentName, cashDistribAgentName);
            }
            if (MsgUtil.isTagInList(TagNum.CashDistribAgentCode, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.CashDistribAgentCode, cashDistribAgentCode);
            }
            if (MsgUtil.isTagInList(TagNum.CashDistribAgentAcctNumber, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.CashDistribAgentAcctNumber, cashDistribAgentAcctNumber);
            }
            if (MsgUtil.isTagInList(TagNum.CashDistribPayRef, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.CashDistribPayRef, cashDistribPayRef);
            }
            if (MsgUtil.isTagInList(TagNum.CashDistribAgentAcctName, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.CashDistribAgentAcctName, cashDistribAgentAcctName);
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
        return "This tag is not supported in [DistribInstsGroup] group version [4.3].";
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Package Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Private Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Inner Classes">
    // </editor-fold>
}
