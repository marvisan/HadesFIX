/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * LegIOIGroup.java
 *
 * $Id: LegIOIGroup.java,v 1.9 2010-11-23 10:20:16 vrotaru Exp $
 */
package net.hades.fix.message.group;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.MsgSecureType;
import net.hades.fix.message.anno.FIXVersion;
import net.hades.fix.message.anno.TagNumRef;
import net.hades.fix.message.comp.InstrumentLeg;
import net.hades.fix.message.comp.LegStipulations;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.struct.Tag;
import net.hades.fix.message.type.IOIQty;
import net.hades.fix.message.type.SessionRejectReason;
import net.hades.fix.message.type.TagNum;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.util.TagEncoder;

/**
 * Group for an IOI leg values.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.9 $
 * @created 15/03/2009, 1:29:47 PM
 */
@XmlAccessorType(XmlAccessType.NONE)
public abstract class LegIOIGroup extends Group {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.LegIOIQty.getValue()
    }));

    protected static final Set<Integer> START_DATA_TAGS = null;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">

    protected Set<Integer> STANDARD_SECURED_TAGS = TAGS;

    /**
     * Starting with 4.4 version.
     */
    protected InstrumentLeg instrumentLeg;

    /**
     * TagNum = 682. Starting with 4.4 version.
     */
    protected IOIQty legIOIQty;

    /**
     * Starting with 4.4 version.
     */
    protected LegStipulations legStipulations;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public LegIOIGroup() {
    }

    public LegIOIGroup(FragmentContext context) {
        super(context);
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Public Methods">

    @Override
    public int getFirstTag() {
        return TagNum.LegSymbol.getValue();
    }

    // ACCESSOR METHODS
    //////////////////////////////////////////

    /**
     * Message field getter for {@link InstrumentLeg}.
     * @return field array value
     */
    @FIXVersion(introduced = "4.4")
    public InstrumentLeg getInstrumentLeg() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Sets the {@link InstrumentLeg} component if used in this message to the proper implementation.<br/>
     */
    @FIXVersion(introduced = "4.4")
    public void setInstrumentLeg() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }
    
    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.LegIOIQty)
    public IOIQty getLegIOIQty() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field setter.
     * @param legIOIQty field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.LegIOIQty)
    public void setLegIOIQty(IOIQty legIOIQty) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    public LegStipulations getLegStipulations() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Sets the {@link LegStipulations} component if used in this message to the proper implementation.
     * class.
     */
    @FIXVersion(introduced="4.4")
    public void setLegStipulations() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

    @Override
    protected void validateRequiredTags() throws TagNotPresentException {
        StringBuilder errorMsg = new StringBuilder("Tag value(s) for");
        boolean hasMissingTag = false;
        if (instrumentLeg == null || instrumentLeg.getLegSymbol() == null) {
            errorMsg.append(" [InstrumentLeg]");
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
            if (instrumentLeg != null) {
                bao.write(instrumentLeg.encode(MsgSecureType.ALL_FIELDS));
            }
            if (legIOIQty != null) {
                TagEncoder.encode(bao, TagNum.LegIOIQty, legIOIQty.getValue());
            }
            if (legStipulations != null) {
                bao.write(legStipulations.encode(MsgSecureType.ALL_FIELDS));
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
            case LegIOIQty:
                legIOIQty = IOIQty.valueFor(new String(tag.value, sessionCharset));
                break;

            default:
                String error = "Tag value [" + tag.tagNum + "] not present in [LegIOIGroup] fields.";
                LOGGER.severe(error);
                throw new BadFormatMsgException(SessionRejectReason.InvalidTagNumber, tag.tagNum, error);
        }
    }
    
    @Override
    protected Set<Integer> getFragmentDataTags() {
        return START_DATA_TAGS;
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
        b.append("{LegIOIGroup=");
        printTagValue(b, instrumentLeg);
        printTagValue(b, TagNum.LegIOIQty, legIOIQty);
        printTagValue(b, legStipulations);
        b.append("}");

        return b.toString();
    }

    // </editor-fold>
}
