/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * TrdRegTimestampsGroup44.java
 *
 * $Id: TrdRegTimestampsGroup44.java,v 1.2 2010-12-12 09:13:08 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v44;

import net.hades.fix.message.FragmentContext;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.group.TrdRegTimestampsGroup;
import net.hades.fix.message.type.TagNum;
import net.hades.fix.message.type.TrdRegTimestampType;
import net.hades.fix.message.util.MsgUtil;
import net.hades.fix.message.util.TagEncoder;

/**
 * FIX 5.0 implementation of TrdRegTimestampsGroup group.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 * @created 12/02/2009, 7:22:35 PM
 */
@XmlRootElement(name="TrdRegTS")
@XmlType
@XmlAccessorType(XmlAccessType.NONE)
public class TrdRegTimestampsGroup44 extends TrdRegTimestampsGroup {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> START_COMP_TAGS = null;

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

    public TrdRegTimestampsGroup44() {
        SECURED_TAGS = STANDARD_SECURED_TAGS;
    }

    public TrdRegTimestampsGroup44(FragmentContext context) {
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

    @XmlAttribute(name = "TS")
    @Override
    public Date getTrdRegTimestamp() {
        return trdRegTimestamp;
    }

    @Override
    public void setTrdRegTimestamp(Date trdRegTimestamp) {
        this.trdRegTimestamp = trdRegTimestamp;
    }

    @XmlAttribute(name = "Typ")
    @Override
    public TrdRegTimestampType getTrdRegTimestampType() {
        return trdRegTimestampType;
    }

    @Override
    public void setTrdRegTimestampType(TrdRegTimestampType trdRegTimestampType) {
        this.trdRegTimestampType = trdRegTimestampType;
    }

    @XmlAttribute(name = "Src")
    @Override
    public String getTrdRegTimestampOrigin() {
        return trdRegTimestampOrigin;
    }

    @Override
    public void setTrdRegTimestampOrigin(String trdRegTimestampOrigin) {
        this.trdRegTimestampOrigin = trdRegTimestampOrigin;
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
            if (MsgUtil.isTagInList(TagNum.TrdRegTimestamp, SECURED_TAGS, secured)) {
                TagEncoder.encodeUtcTimestamp(bao, TagNum.TrdRegTimestamp, trdRegTimestamp);
            }
            if (trdRegTimestampType != null && MsgUtil.isTagInList(TagNum.TrdRegTimestampType, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.TrdRegTimestampType, trdRegTimestampType.getValue());
            }
            if (MsgUtil.isTagInList(TagNum.TrdRegTimestampOrigin, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.TrdRegTimestampOrigin, trdRegTimestampOrigin);
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
    protected Set<Integer> getFragmentCompTags() {
        return START_COMP_TAGS;
    }

    @Override
    protected Set<Integer> getFragmentSecuredTags() {
        return SECURED_TAGS;
    }

    @Override
    protected String getUnsupportedTagMessage() {
        return "This tag is not supported in [TrdRegTimestampsGroup] group version [4.4].";
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Package Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Private Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Inner Classes">
    // </editor-fold>
}
