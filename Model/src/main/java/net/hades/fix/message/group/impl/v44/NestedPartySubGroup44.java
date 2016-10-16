/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * NestedPartysSubGroup44.java
 *
 * $Id: NestedPartySubGroup44.java,v 1.7 2011-02-10 10:02:15 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v44;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.group.NestedPartySubGroup;
import net.hades.fix.message.util.MsgUtil;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Set;
import java.util.logging.Level;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.type.PartySubIDType;
import net.hades.fix.message.type.TagNum;
import net.hades.fix.message.util.TagEncoder;

/**
 * FIX 4.4 implementation of NestedPartysSubGroup group.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.7 $
 * @created 06/04/2009, 2:37:46 PM
 */
@XmlRootElement(name="Sub")
@XmlType
@XmlAccessorType(XmlAccessType.NONE)
public class NestedPartySubGroup44 extends NestedPartySubGroup {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">

    protected Set<Integer> STANDARD_SECURED_TAGS = TAGS;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public NestedPartySubGroup44() {
        SECURED_TAGS = STANDARD_SECURED_TAGS;
    }

    public NestedPartySubGroup44(FragmentContext context) {
        super(context);
        SECURED_TAGS = STANDARD_SECURED_TAGS;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Public Methods">

    // ACCESSOR METHODS
    //////////////////////////////////////////

    @XmlAttribute(name = "ID")
    @Override
    public String getNestedPartySubID() {
        return nestedPartySubID;
    }

    @Override
    public void setNestedPartySubID(String nestedPartySubID) {
        this.nestedPartySubID = nestedPartySubID;
    }

    @XmlAttribute(name = "Typ")
    @Override
    public PartySubIDType getNestedPartySubIDType() {
        return nestedPartySubIDType;
    }

    @Override
    public void setNestedPartySubIDType(PartySubIDType nestedPartySubIDType) {
        this.nestedPartySubIDType = nestedPartySubIDType;
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
            if (MsgUtil.isTagInList(TagNum.NestedPartySubID, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.NestedPartySubID, nestedPartySubID);
            }
            if (nestedPartySubIDType != null&& MsgUtil.isTagInList(TagNum.NestedPartySubIDType, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.NestedPartySubIDType, nestedPartySubIDType.getValue());
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
        return "This tag is not supported in [NestedPartySubGroup] group version [4.4].";
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Package Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Private Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Inner Classes">
    // </editor-fold>
}
