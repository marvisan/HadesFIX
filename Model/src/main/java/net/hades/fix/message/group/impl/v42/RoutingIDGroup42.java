/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * RoutingIDGroup42.java
 *
 * $Id: RoutingIDGroup42.java,v 1.5 2010-11-23 10:20:18 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v42;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.group.RoutingIDGroup;
import net.hades.fix.message.type.RoutingType;
import net.hades.fix.message.type.TagNum;
import net.hades.fix.message.util.MsgUtil;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.logging.Level;

import net.hades.fix.message.util.TagEncoder;

/**
 * FIX 4.2 implementation of RoutingIDGroup group.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.5 $
 * @created 21/02/2009, 4:58:16 PM
 */
public class RoutingIDGroup42 extends RoutingIDGroup {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public RoutingIDGroup42() {
        SECURED_TAGS = STANDARD_SECURED_TAGS;
    }

    public RoutingIDGroup42(FragmentContext context) {
        super(context);
        SECURED_TAGS = STANDARD_SECURED_TAGS;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Public Methods">

    @Override
    public RoutingType getRoutingType() {
        return routingType;
    }

    @Override
    public void setRoutingType(RoutingType routingType) {
        this.routingType = routingType;
    }

    @Override
    public String getRoutingID() {
        return routingID;
    }

    @Override
    public void setRoutingID(String routingID) {
        this.routingID = routingID;
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
            if (MsgUtil.isTagInList(TagNum.RoutingType, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.RoutingType, routingType.getValue());
            }
            if (MsgUtil.isTagInList(TagNum.RoutingID, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.RoutingID, routingID);
            }
            result = bao.toByteArray();
        } catch (IOException ex) {
            String error = "Error writing to the byte array.";
            LOGGER.log(Level.SEVERE, "{0} Error was : {1}", new Object[] { error, ex.toString() });
            throw new BadFormatMsgException(error, ex);
        }

        return result;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Package Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Private Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Inner Classes">
    // </editor-fold>
}
