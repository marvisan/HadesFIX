/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * DerivativeInstrumentPartySubIDGroup.java
 *
 * $Id: DerivativeInstrumentPartySubIDGroup.java,v 1.1 2011-09-19 08:15:46 vrotaru Exp $
 */
package net.hades.fix.message.group;

import net.hades.fix.message.anno.FIXVersion;
import net.hades.fix.message.struct.Tag;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
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
 * Derivative instrument party sub ID data.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 07/12/2008, 3:56:06 PM
 */
@XmlAccessorType(XmlAccessType.NONE)
public abstract class DerivativeInstrumentPartySubIDGroup extends Group {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.DerivativeInstrumentPartySubID.getValue(),
        TagNum.DerivativeInstrumentPartySubIDType.getValue()
    }));

    protected static final Set<Integer> START_DATA_TAGS = null;

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

    /**
     * TagNum = 1053. Starting with 5.0 version.
     */
    protected String derivativeInstrumentPartySubID;

    /**
     * TagNum = 1054. Starting with 5.0 version.
     */
    protected PartySubIDType derivativeInstrumentPartySubIDType;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public DerivativeInstrumentPartySubIDGroup() {
    }

    public DerivativeInstrumentPartySubIDGroup(FragmentContext context) {
        super(context);
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Public Methods">

    @Override
    public Set<Integer> getFragmentTags() {
        return TAGS;
    }

    @Override
    public Set<Integer> getFragmentAllTags() {
        return ALL_TAGS;
    }

    // ACCESSOR METHODS
    //////////////////////////////////////////

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "5.0")
    @TagNumRef(tagNum = TagNum.DerivativeInstrumentPartySubID)
    public String getDerivativeInstrumentPartySubID() {
        return derivativeInstrumentPartySubID;
    }

    /**
     * Message field setter.
     * @param derivativeInstrumentPartySubID field value
     */
    @FIXVersion(introduced = "5.0")
    @TagNumRef(tagNum = TagNum.DerivativeInstrumentPartySubID)
    public void setDerivativeInstrumentPartySubID(String derivativeInstrumentPartySubID) {
        this.derivativeInstrumentPartySubID = derivativeInstrumentPartySubID;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "5.0")
    @TagNumRef(tagNum = TagNum.DerivativeInstrumentPartySubIDType)
    public PartySubIDType getDerivativeInstrumentPartySubIDType() {
        return derivativeInstrumentPartySubIDType;
    }

    /**
     * Message field setter.
     * @param derivativeInstrumentPartySubIDType field value
     */
    @FIXVersion(introduced = "5.0")
    @TagNumRef(tagNum = TagNum.DerivativeInstrumentPartySubIDType)
    public void setDerivativeInstrumentPartySubIDType(PartySubIDType derivativeInstrumentPartySubIDType) {
        this.derivativeInstrumentPartySubIDType = derivativeInstrumentPartySubIDType;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

    @Override
    protected void validateRequiredTags() throws TagNotPresentException {
        StringBuilder errorMsg = new StringBuilder("Tag value(s) for");
        boolean hasMissingTag = false;
        if (derivativeInstrumentPartySubID == null || derivativeInstrumentPartySubID.trim().isEmpty()) {
            errorMsg.append(" [DerivativeInstrumentPartySubID]");
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
            TagEncoder.encode(bao, TagNum.DerivativeInstrumentPartySubID, derivativeInstrumentPartySubID);
            if (derivativeInstrumentPartySubIDType != null) {
                TagEncoder.encode(bao, TagNum.DerivativeInstrumentPartySubIDType, derivativeInstrumentPartySubIDType.getValue());
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
            case DerivativeInstrumentPartySubID:
                derivativeInstrumentPartySubID = new String(tag.value, sessionCharset);
                break;

            case DerivativeInstrumentPartySubIDType:
                derivativeInstrumentPartySubIDType = PartySubIDType.valueFor(Integer.valueOf(new String(tag.value, sessionCharset)));
                break;

            default:
                String error = "Tag value [" + tag.tagNum + "] not present in [DerivativeInstrumentPartySubIDGroup] fields.";
                LOGGER.severe(error);
                throw new BadFormatMsgException(SessionRejectReason.InvalidTagNumber, tag.tagNum, error);
        }
    }

    @Override
    protected Set<Integer> getFragmentDataTags() {
        return START_DATA_TAGS;
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
    protected int getFirstTag() {
        return TagNum.DerivativeInstrumentPartySubID.getValue();
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
        b.append("{DerivativeInstrumentPartySubIDGroup=");
        printTagValue(b, TagNum.DerivativeInstrumentPartySubID, derivativeInstrumentPartySubID);
        printTagValue(b, TagNum.DerivativeInstrumentPartySubIDType, derivativeInstrumentPartySubIDType);
        b.append("}");

        return b.toString();
    }

    // </editor-fold>
}
