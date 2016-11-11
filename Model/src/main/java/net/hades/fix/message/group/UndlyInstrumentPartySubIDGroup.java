/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * UndlyInstrumentPartySubIDGroup.java
 *
 * $Id: UndlyInstrumentPartySubIDGroup.java,v 1.8 2010-11-23 10:20:17 vrotaru Exp $
 */
package net.hades.fix.message.group;

import net.hades.fix.message.anno.FIXVersion;
import net.hades.fix.message.struct.Tag;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Set;
import java.util.logging.Level;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import net.hades.fix.message.anno.TagNumRef;
import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.type.PartySubIDType;
import net.hades.fix.message.type.SessionRejectReason;
import net.hades.fix.message.type.TagNum;
import net.hades.fix.message.util.TagEncoder;

/**
 * Underlying instrument party sub ID group.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.8 $
 * @created 02/01/2009, 10:46:30 AM
 */
@XmlAccessorType(XmlAccessType.NONE)
public abstract class UndlyInstrumentPartySubIDGroup extends Group {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">

    /**
     * TagNum = 1063. Starting with 5.0 version.
     */
    protected String undlyInstrumentPartySubID;

    /**
     * TagNum = 1064. Starting with 5.0 version.
     */
    protected PartySubIDType undlyInstrumentPartySubIDType;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public UndlyInstrumentPartySubIDGroup() {
    }

    public UndlyInstrumentPartySubIDGroup(FragmentContext context) {
        super(context);
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Public Methods">

    // ACCESSOR METHODS
    //////////////////////////////////////////

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.UndlyInstrumentPartySubID)
    public String getUndlyInstrumentPartySubID() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field setter.
     * @param undlyInstrumentPartySubID field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.UndlyInstrumentPartySubID)
    public void setUndlyInstrumentPartySubID(String undlyInstrumentPartySubID) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.UndlyInstrumentPartySubIDType)
    public PartySubIDType getUndlyInstrumentPartySubIDType() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field setter.
     * @param undlyInstrumentPartySubIDType field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.UndlyInstrumentPartySubIDType)
    public void setUndlyInstrumentPartySubIDType(PartySubIDType undlyInstrumentPartySubIDType) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

    @Override
    protected int getFirstTag() {
        return TagNum.UndlyInstrumentPartySubID.getValue();
    }

    @Override
    protected void validateRequiredTags() throws TagNotPresentException {
        StringBuilder errorMsg = new StringBuilder("Tag value(s) for");
        boolean hasMissingTag = false;
        if (undlyInstrumentPartySubID == null) {
            errorMsg.append(" [UndlyInstrumentPartySubID]");
            hasMissingTag = true;
        }
        errorMsg.append(" is missing.");
        if (hasMissingTag) {
            throw new TagNotPresentException(errorMsg.toString());
        }
    }

    @Override
    protected byte[] encodeFragmentAll() throws TagNotPresentException, BadFormatMsgException {
        if (validateRequired) {             validateRequiredTags();         }

        byte[] result = new byte[0];
        ByteArrayOutputStream bao = new ByteArrayOutputStream();
        try {
            TagEncoder.encode(bao, TagNum.UndlyInstrumentPartySubID, undlyInstrumentPartySubID);
            if (undlyInstrumentPartySubIDType != null) {
                TagEncoder.encode(bao, TagNum.UndlyInstrumentPartySubIDType, undlyInstrumentPartySubIDType.getValue());
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
    protected byte[] encodeFragmentSecured(boolean secured) throws TagNotPresentException, BadFormatMsgException {
        if (secured) {
            return new byte[0];
        } else {
            return encodeFragmentAll();
        }
    }

    @Override
    protected void setFragmentTagValue(Tag tag) throws BadFormatMsgException {
        TagNum tagNum = TagNum.fromString(tag.tagNum);
        switch (tagNum) {
            case UndlyInstrumentPartySubID:
                undlyInstrumentPartySubID = new String(tag.value, sessionCharset);
                break;

            case UndlyInstrumentPartySubIDType:
                undlyInstrumentPartySubIDType = PartySubIDType.valueFor(Integer.valueOf(new String(tag.value, sessionCharset)));
                break;

            default:
                String error = "Tag value [" + tag.tagNum + "] not present in [UndlyInstrumentPartySubIDGroup] fields.";
                LOGGER.severe(error);
                throw new BadFormatMsgException(SessionRejectReason.InvalidTagNumber, tag.tagNum, error);
        }
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

    // <editor-fold defaultstate="collapsed" desc="toString()">

    @Override
    public String toString() {
        StringBuilder b = new StringBuilder(System.getProperty("line.separator"));
        b.append("{UndlyInstrumentPartySubIDGroup=");
        printTagValue(b, TagNum.UndlyInstrumentPartySubID, undlyInstrumentPartySubID);
        printTagValue(b, TagNum.UndlyInstrumentPartySubIDType, undlyInstrumentPartySubIDType);
        b.append("}");

        return b.toString();
    }

    // </editor-fold>
}
