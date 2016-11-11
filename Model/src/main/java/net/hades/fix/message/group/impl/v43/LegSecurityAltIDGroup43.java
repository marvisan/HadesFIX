/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * LegSecurityAltIDGroup43.java
 *
 * $Id: LegSecurityAltIDGroup43.java,v 1.1 2011-01-03 07:28:48 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v43;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.logging.Level;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.group.LegSecurityAltIDGroup;
import net.hades.fix.message.type.TagNum;
import net.hades.fix.message.util.MsgUtil;
import net.hades.fix.message.util.TagEncoder;

/**
 * FIX 4.3 implementation of LegSecurityAltIDGroup group.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 02/01/2009, 4:12:30 PM
 */
public class LegSecurityAltIDGroup43 extends LegSecurityAltIDGroup {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public LegSecurityAltIDGroup43() {
        SECURED_TAGS = STANDARD_SECURED_TAGS;
    }

    public LegSecurityAltIDGroup43(FragmentContext context) {
        super(context);
        SECURED_TAGS = STANDARD_SECURED_TAGS;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Public Methods">

    @Override
    public String getLegSecurityAltID() {
        return legSecurityAltID;
    }

    @Override
    public void setLegSecurityAltID(String legSecurityAltID) {
        this.legSecurityAltID = legSecurityAltID;
    }

    @Override
    public String getLegSecurityAltIDSource() {
        return legSecurityAltIDSource;
    }

    @Override
    public void setLegSecurityAltIDSource(String legSecurityAltIDSource) {
        this.legSecurityAltIDSource = legSecurityAltIDSource;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

    @Override
    protected byte[] encodeFragmentSecured(boolean secured) throws TagNotPresentException, BadFormatMsgException {
        byte[] result = new byte[0];
        ByteArrayOutputStream bao = new ByteArrayOutputStream();

        try {
            if (MsgUtil.isTagInList(TagNum.LegSecurityAltID, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.LegSecurityAltID, legSecurityAltID);
            }
            if (MsgUtil.isTagInList(TagNum.LegSecurityAltIDSource, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.LegSecurityAltIDSource, legSecurityAltIDSource);
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
        return "This tag is not supported in [LegSecurityAltIDGroup] component release [4.3].";
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Package Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Private Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Inner Classes">
    // </editor-fold>
}
