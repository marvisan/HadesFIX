/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * DerivativeSecurityDefinition.java
 *
 * $Id: DerivativeSecurityDefinition.java,v 1.1 2011-09-27 08:57:26 vrotaru Exp $
 */
package net.hades.fix.message.comp;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.anno.FIXVersion;
import net.hades.fix.message.group.DerivativeInstrAttribGroup;
import net.hades.fix.message.struct.Tag;
import net.hades.fix.message.type.SessionRejectReason;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import net.hades.fix.message.anno.TagNumRef;
import net.hades.fix.message.MsgSecureType;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.group.MarketSegmentGroup;
import net.hades.fix.message.type.TagNum;
import net.hades.fix.message.util.TagEncoder;

/**
 * Derivative security definition component.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 07/12/2008, 12:48:37 PM
 */
@XmlAccessorType(XmlAccessType.NONE)
public abstract class DerivativeSecurityDefinition extends Component {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.NoDerivativeInstrAttrib.getValue(),
        TagNum.NoMarketSegments.getValue()
    }));

    protected static final Set<Integer> START_DATA_TAGS = null;

    protected static final Set<Integer> REQUIRED_TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.DerivativeSymbol.getValue()
    }));

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">

    /**
     * Starting with 5.0SP1 version.
     */
    protected DerivativeInstrument derivativeInstrument;

    /**
     * TagNum = 1311. Starting with 5.0SP1 version.
     */
    protected Integer noDerivativeInstrAttrib;

    /**
     * Starting with 5.0SP1 version.
     */
    protected DerivativeInstrAttribGroup[] derivativeInstrAttribGroups;

    /**
     * TagNum = 1310. Starting with 5.0SP1 version.
     */
    protected Integer noMarketSegments;

    /**
     * Starting with 5.0SP1 version.
     */
    protected MarketSegmentGroup[] marketSegmentGroups;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public DerivativeSecurityDefinition() {
        super();
    }

    public DerivativeSecurityDefinition(FragmentContext context) {
        super(context);
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Public Methods">

    @Override
    public Set<Integer> getFragmentTags() {
        return TAGS;
    }

    // ACCESSOR METHODS
    //////////////////////////////////////////

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "5.0SP1")
    public DerivativeInstrument getDerivativeInstrument() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field setter.
     */
    @FIXVersion(introduced = "5.0SP1")
    public void setDerivativeInstrument() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }
    
    /**
     * Message field clear.
     */
    @FIXVersion(introduced = "5.0SP1")
    public void clearDerivativeInstrument() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.NoDerivativeInstrAttrib)
    public Integer getNoDerivativeInstrAttrib() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method sets the number of {@link DerivativeInstrAttribGroup} groups. It will also create an array
     * of {@link DerivativeInstrAttribGroup} objects and set the <code>derivativeInstrAttribGroups</code> field with this array.
     * The created objects inside the array need to be populated with data for encoding.<br/>
     * If there where already objects in <code>derivativeInstrAttribGroups</code> array they will be discarded.<br/>
     * @param noDerivativeInstrAttrib field value
     */
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.NoDerivativeInstrAttrib)
    public void setNoDerivativeInstrAttrib(Integer noDerivativeInstrAttrib) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter for {@link DerivativeInstrAttribGroup} array of groups.
     * @return field array value
     */
    @FIXVersion(introduced="5.0SP1")
    public DerivativeInstrAttribGroup[] getDerivativeInstrAttribGroups() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method adds a {@link DerivativeInstrAttribGroup} object to the existing array of <code>derivativeInstrAttribGroups</code>
     * and expands the static array with 1 place.<br/>
     * This method will also update <code>noDerivativeInstrAttrib</code> field to the proper value.<br/>
     * Note: If the <code>setNoDerivativeInstrAttrib</code> method has been called there will already be a number of objects in the
     * <code>noDerivativeInstrAttrib</code> array created.<br/>
     * @return newly created block and added to the array group object
     */
    @FIXVersion(introduced="5.0SP1")
    public DerivativeInstrAttribGroup addDerivativeInstrAttribGroup() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method deletes a {@link DerivativeInstrAttribGroup} object from the existing array of <code>derivativeInstrAttribGroups</code>
     * and shrink the static array with 1 place.<br/>
     * If the array does not have the index position then a null object will be returned.)<br/>
     * This method will also update <code>noDerivativeInstrAttrib</code> field to the proper value.<br/>
     * @param index position in array to be deleted starting at 0
     * @return deleted block object
     */
    @FIXVersion(introduced="5.0SP1")
    public DerivativeInstrAttribGroup deleteDerivativeInstrAttribGroup(int index) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Deletes all the {@link DerivativeInstrAttribGroup} objects from the <code>derivativeInstrAttribGroups</code> array
     * (sets the array to 0 length)<br/>
     * This method will also update <code>noDerivativeInstrAttrib</code> field and set it to null.<br/>
     * @return number of elements in array cleared
     */
    @FIXVersion(introduced="5.0SP1")
    public int clearDerivativeInstrAttribGroups() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.NoMarketSegments)
    public Integer getNoMarketSegments() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method sets the number of {@link MarketSegmentGroup} groups. It will also create an array
     * of {@link MarketSegmentGroup} objects and set the <code>marketSegmentGroups</code> field with this array.
     * The created objects inside the array need to be populated with data for encoding.<br/>
     * If there where already objects in <code>marketSegmentGroups</code> array they will be discarded.<br/>
     * @param noMarketSegments field value
     */
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.NoMarketSegments)
    public void setNoMarketSegments(Integer noMarketSegments) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter for {@link MarketSegmentGroup} array of groups.
     * @return field array value
     */
    @FIXVersion(introduced="5.0SP1")
    public MarketSegmentGroup[] getMarketSegmentGroups() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method adds a {@link MarketSegmentGroup} object to the existing array of <code>marketSegmentGroups</code>
     * and expands the static array with 1 place.<br/>
     * This method will also update <code>noMarketSegments</code> field to the proper value.<br/>
     * Note: If the <code>setNoTimeInForceRules</code> method has been called there will already be a number of objects in the
     * <code>noMarketSegments</code> array created.<br/>
     * @return newly created block and added to the array group object
     */
    @FIXVersion(introduced="5.0SP1")
    public MarketSegmentGroup addMarketSegmentGroup() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method deletes a {@link MarketSegmentGroup} object from the existing array of <code>marketSegmentGroups</code>
     * and shrink the static array with 1 place.<br/>
     * If the array does not have the index position then a null object will be returned.)<br/>
     * This method will also update <code>noMarketSegments</code> field to the proper value.<br/>
     * @param index position in array to be deleted starting at 0
     * @return deleted block object
     */
    @FIXVersion(introduced="5.0SP1")
    public MarketSegmentGroup deleteMarketSegmentGroup(int index) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Deletes all the {@link MarketSegmentGroup} objects from the <code>MarketSegmentGroups</code> array
     * (sets the array to 0 length)<br/>
     * This method will also update <code>noMarketSegments</code> field and set it to null.<br/>
     * @return number of elements in array cleared
     */
    @FIXVersion(introduced="5.0SP1")
    public int clearMarketSegmentGroups() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

    @Override
    protected int getFirstTag() {
        return TagNum.DerivativeSymbol.getValue();
    }

    @Override
    protected void validateRequiredTags() throws TagNotPresentException {
        StringBuilder errorMsg = new StringBuilder("Tag value(s) for");
        boolean hasMissingTag = false;
         if (derivativeInstrument == null || derivativeInstrument.getDerivativeSymbol() == null) {
            errorMsg.append(" [DerivativeSymbol]");
            hasMissingTag = true;
        }
        errorMsg.append(" is missing.");
        if (hasMissingTag) {
            throw new TagNotPresentException(errorMsg.toString());
        }
    }

    @Override
    protected byte[] encodeFragmentAll() throws TagNotPresentException, BadFormatMsgException {
        if (validateRequired) {
            validateRequiredTags();
        }
        byte[] result = new byte[0];
        ByteArrayOutputStream bao = new ByteArrayOutputStream();

        try {
            if (derivativeInstrument != null) {
                bao.write(derivativeInstrument.encode(MsgSecureType.ALL_FIELDS));
            }
            if (noDerivativeInstrAttrib != null && derivativeInstrAttribGroups != null && derivativeInstrAttribGroups.length == noDerivativeInstrAttrib.intValue()) {
                TagEncoder.encode(bao, TagNum.NoDerivativeInstrAttrib, noDerivativeInstrAttrib);
                for (int i = 0; i < noDerivativeInstrAttrib.intValue(); i++) {
                    if (derivativeInstrAttribGroups[i] != null) {
                        bao.write(derivativeInstrAttribGroups[i].encode(MsgSecureType.ALL_FIELDS));
                    }
                }
            } else {
                String error = "DerivativeInstrAttribGroups field has been set but there is no data or the number of groups does not match.";
                LOGGER.severe(error);
                throw new BadFormatMsgException(SessionRejectReason.IncorrectCountForGroups, TagNum.NoOrdTypeRules.getValue(), error);
            }
            if (noMarketSegments != null && marketSegmentGroups != null && marketSegmentGroups.length == noMarketSegments.intValue()) {
                TagEncoder.encode(bao, TagNum.NoMarketSegments, noMarketSegments);
                for (int i = 0; i < noMarketSegments.intValue(); i++) {
                    if (marketSegmentGroups[i] != null) {
                        bao.write(marketSegmentGroups[i].encode(MsgSecureType.ALL_FIELDS));
                    }
                }
            } else {
                String error = "MarketSegmentGroups field has been set but there is no data or the number of groups does not match.";
                LOGGER.severe(error);
                throw new BadFormatMsgException(SessionRejectReason.IncorrectCountForGroups, TagNum.NoTimeInForceRules.getValue(), error);
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
            case NoDerivativeInstrAttrib:
                noDerivativeInstrAttrib = new Integer(new String(tag.value, sessionCharset));
                break;

            case NoMarketSegments:
                noMarketSegments = new Integer(new String(tag.value, sessionCharset));
                break;

            default:
                String error = "Tag value [" + tag.tagNum + "] not present in [DerivativeSecurityDefinition] fields.";
                LOGGER.severe(error);
                throw new BadFormatMsgException(SessionRejectReason.InvalidTagNumber, tag.tagNum, error);
        }
    }

    @Override
    protected ByteBuffer setFragmentDataTagValue(Tag tag, ByteBuffer message) throws BadFormatMsgException {
        return message;
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
        b.append("{DerivativeSecurityDefinition=");
        printTagValue(b, derivativeInstrument);
        printTagValue(b, TagNum.NoDerivativeInstrAttrib, noDerivativeInstrAttrib);
        printTagValue(b, derivativeInstrAttribGroups);
        printTagValue(b, TagNum.NoMarketSegments, noMarketSegments);
        printTagValue(b, marketSegmentGroups);
        b.append("}");

        return b.toString();
    }

    // </editor-fold>
}
