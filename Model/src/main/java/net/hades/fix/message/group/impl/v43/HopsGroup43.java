/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * HopsGroup43.java
 *
 * $Id: HopsGroup43.java,v 1.4 2010-08-25 05:30:26 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v43;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.group.HopsGroup;
import net.hades.fix.message.type.TagNum;
import net.hades.fix.message.util.MsgUtil;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Set;
import java.util.logging.Level;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.util.TagEncoder;
import net.hades.fix.message.xml.codec.jaxb.adapter.FixDateTimeAdapter;

/**
 * FIX 4.3 implementation of HopsGroup group.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.4 $
 * @created 02/01/2009, 1:45:28 PM
 */
@XmlRootElement(name="HopGroup")
@XmlType(propOrder={"hopCompID", "hopSendingTime", "hopRefID"})
@XmlAccessorType(XmlAccessType.NONE)
public class HopsGroup43 extends HopsGroup {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = -2028278292215376368L;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">

    protected Set<Integer> STANDARD_SECURED_TAGS = TAGS;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public HopsGroup43() {
        SECURED_TAGS = STANDARD_SECURED_TAGS;
    }

    public HopsGroup43(FragmentContext context) {
        super(context);
        SECURED_TAGS = STANDARD_SECURED_TAGS;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Public Methods">

    @XmlElement(name="HopCompID")
    @Override
    public String getHopCompID() {
        return hopCompID;
    }

    @Override
    public void setHopCompID(String hopCompID) {
        this.hopCompID = hopCompID;
    }

    @XmlElement(name="HopRefID")
    @Override
    public Integer getHopRefID() {
        return hopRefID;
    }

    @Override
    public void setHopRefID(Integer hopRefID) {
        this.hopRefID = hopRefID;
    }

    @XmlElement(name="HopSendingTime")
    @XmlJavaTypeAdapter(FixDateTimeAdapter.class)
    @Override
    public Date getHopSendingTime() {
        return hopSendingTime;
    }

    @Override
    public void setHopSendingTime(Date hopSendingTime) {
        this.hopSendingTime = hopSendingTime;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

    @Override
    protected byte[] encodeFragmentSecured(boolean secured) throws TagNotPresentException, BadFormatMsgException {
        byte[] result = new byte[0];
        ByteArrayOutputStream bao = new ByteArrayOutputStream();

        try {
            if (MsgUtil.isTagInList(TagNum.HopCompID, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.HopCompID, hopCompID);
            }
            if (MsgUtil.isTagInList(TagNum.HopSendingTime, SECURED_TAGS, secured)) {
                TagEncoder.encodeUtcTimestamp(bao, TagNum.HopSendingTime, hopSendingTime);
            }
            if (MsgUtil.isTagInList(TagNum.HopRefID, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.HopRefID, hopRefID);
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
        return "This tag is not supported in [HopsGroup] group version [4.3].";
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Package Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Private Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Inner Classes">
    // </editor-fold>
}
