/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * SecurityTradingRules.java
 *
 * $Id: SecurityTradingRules.java,v 1.2 2011-04-20 00:32:34 vrotaru Exp $
 */
package net.hades.fix.message.comp;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.anno.FIXVersion;
import net.hades.fix.message.group.NestedInstrmtAttribGroup;
import net.hades.fix.message.group.TradingSessionRuleGroup;
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
import net.hades.fix.message.type.TagNum;
import net.hades.fix.message.util.TagEncoder;

/**
 * Security trading rules component.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 * @created 07/12/2008, 12:48:37 PM
 */
@XmlAccessorType(XmlAccessType.NONE)
public abstract class SecurityTradingRules extends Component {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.NoTradingSessionRules.getValue(),
        TagNum.NoNestedInstrAttrib.getValue()
    }));

    protected static final Set<Integer> START_DATA_TAGS = null;

    protected static final Set<Integer> REQUIRED_TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.NoOrdTypeRules.getValue()
    }));

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">

    /**
     * Starting with 5.0SP1 version.
     */
    protected BaseTradingRules baseTradingRules;

    /**
     * TagNum = 1237. Starting with 5.0SP1 version.
     */
    protected Integer noTradingSessionRules;

    /**
     * Starting with 5.0SP1 version.
     */
    protected TradingSessionRuleGroup[] tradingSessionRuleGroups;

    /**
     * TagNum = 1312. Starting with 5.0SP1 version.
     */
    protected Integer noNestedInstrAttrib;

    /**
     * Starting with 5.0SP1 version.
     */
    protected NestedInstrmtAttribGroup[] nestedInstrmtAttribGroups;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public SecurityTradingRules() {
        super();
    }

    public SecurityTradingRules(FragmentContext context) {
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
    public BaseTradingRules getBaseTradingRules() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Sets the <code>baseTradingRules</code> to the proper implementation.
     */
    public void setBaseTradingRules() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Sets the <code>baseTradingRules</code> to null.
     */
    public void clearBaseTradingRules() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.NoTradingSessionRules)
    public Integer getNoTradingSessionRules() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method sets the number of {@link TradingSessionRuleGroup} groups. It will also create an array
     * of {@link TradingSessionRuleGroup} objects and set the <code>tradingSessionRuleGroups</code> field with this array.
     * The created objects inside the array need to be populated with data for encoding.<br/>
     * If there where already objects in <code>tradingSessionRuleGroups</code> array they will be discarded.<br/>
     * @param noTradingSessionRules field value
     */
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.NoTradingSessionRules)
    public void setNoTradingSessionRules(Integer noTradingSessionRules) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter for {@link TradingSessionRuleGroup} array of groups.
     * @return field array value
     */
    @FIXVersion(introduced="5.0SP1")
    public TradingSessionRuleGroup[] getTradingSessionRuleGroups() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method adds a {@link TradingSessionRuleGroup} object to the existing array of <code>tradingSessionRuleGroups</code>
     * and expands the static array with 1 place.<br/>
     * This method will also update <code>noTradingSessionRules</code> field to the proper value.<br/>
     * Note: If the <code>setNoTradingSessionRules</code> method has been called there will already be a number of objects in the
     * <code>noTradingSessionRules</code> array created.<br/>
     * @return newly created block and added to the array group object
     */
    @FIXVersion(introduced="5.0SP1")
    public TradingSessionRuleGroup addTradingSessionRuleGroup() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method deletes a {@link TradingSessionRuleGroup} object from the existing array of <code>tradingSessionRuleGroups</code>
     * and shrink the static array with 1 place.<br/>
     * If the array does not have the index position then a null object will be returned.)<br/>
     * This method will also update <code>noTradingSessionRules</code> field to the proper value.<br/>
     * @param index position in array to be deleted starting at 0
     * @return deleted block object
     */
    @FIXVersion(introduced="5.0SP1")
    public TradingSessionRuleGroup deleteTradingSessionRuleGroup(int index) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Deletes all the {@link TradingSessionRuleGroup} objects from the <code>tradingSessionRuleGroups</code> array
     * (sets the array to 0 length)<br/>
     * This method will also update <code>noTradingSessionRules</code> field and set it to null.<br/>
     * @return number of elements in array cleared
     */
    @FIXVersion(introduced="5.0SP1")
    public int clearTradingSessionRuleGroups() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.NoNestedInstrAttrib)
    public Integer getNoNestedInstrAttrib() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method sets the number of {@link NestedInstrmtAttribGroup} groups. It will also create an array
     * of {@link NestedInstrmtAttribGroup} objects and set the <code>nestedInstrmtAttribGroups</code> field with this array.
     * The created objects inside the array need to be populated with data for encoding.<br/>
     * If there where already objects in <code>nestedInstrmtAttribGroups</code> array they will be discarded.<br/>
     * @param noNestedInstrAttrib field value
     */
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.NoNestedInstrAttrib)
    public void setNoNestedInstrAttrib(Integer noNestedInstrAttrib) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter for {@link NestedInstrmtAttribGroup} array of groups.
     * @return field array value
     */
    @FIXVersion(introduced="5.0SP1")
    public NestedInstrmtAttribGroup[] getNestedInstrmtAttribGroups() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method adds a {@link NestedInstrmtAttribGroup} object to the existing array of <code>nestedInstrmtAttribGroups</code>
     * and expands the static array with 1 place.<br/>
     * This method will also update <code>noNestedInstrAttrib</code> field to the proper value.<br/>
     * Note: If the <code>setNoTimeInForceRules</code> method has been called there will already be a number of objects in the
     * <code>noNestedInstrAttrib</code> array created.<br/>
     * @return newly created block and added to the array group object
     */
    @FIXVersion(introduced="5.0SP1")
    public NestedInstrmtAttribGroup addNestedInstrmtAttribGroup() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method deletes a {@link NestedInstrmtAttribGroup} object from the existing array of <code>nestedInstrmtAttribGroups</code>
     * and shrink the static array with 1 place.<br/>
     * If the array does not have the index position then a null object will be returned.)<br/>
     * This method will also update <code>noNestedInstrAttrib</code> field to the proper value.<br/>
     * @param index position in array to be deleted starting at 0
     * @return deleted block object
     */
    @FIXVersion(introduced="5.0SP1")
    public NestedInstrmtAttribGroup deleteNestedInstrmtAttribGroup(int index) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Deletes all the {@link NestedInstrmtAttribGroup} objects from the <code>NestedInstrmtAttribGroups</code> array
     * (sets the array to 0 length)<br/>
     * This method will also update <code>noNestedInstrAttrib</code> field and set it to null.<br/>
     * @return number of elements in array cleared
     */
    @FIXVersion(introduced="5.0SP1")
    public int clearNestedInstrmtAttribGroups() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

    @Override
    protected int getFirstTag() {
        return TagNum.NoTickRules.getValue();
    }

    @Override
    protected void validateRequiredTags() throws TagNotPresentException {
        StringBuilder errorMsg = new StringBuilder("Tag value(s) for");
        boolean hasMissingTag = false;
         if (baseTradingRules == null || baseTradingRules.getNoTickRules() == null) {
            errorMsg.append(" [NoTickRules]");
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
            bao.write(baseTradingRules.encode(MsgSecureType.ALL_FIELDS));
            if (noTradingSessionRules != null && tradingSessionRuleGroups != null && tradingSessionRuleGroups.length == noTradingSessionRules.intValue()) {
                TagEncoder.encode(bao, TagNum.NoTradingSessionRules, noTradingSessionRules);
                for (int i = 0; i < noTradingSessionRules.intValue(); i++) {
                    if (tradingSessionRuleGroups[i] != null) {
                        bao.write(tradingSessionRuleGroups[i].encode(MsgSecureType.ALL_FIELDS));
                    }
                }
            } else {
                String error = "TradingSessionRuleGroups field has been set but there is no data or the number of groups does not match.";
                LOGGER.severe(error);
                throw new BadFormatMsgException(SessionRejectReason.IncorrectCountForGroups, TagNum.NoOrdTypeRules.getValue(), error);
            }
            if (noNestedInstrAttrib != null && nestedInstrmtAttribGroups != null && nestedInstrmtAttribGroups.length == noNestedInstrAttrib.intValue()) {
                TagEncoder.encode(bao, TagNum.NoNestedInstrAttrib, noNestedInstrAttrib);
                for (int i = 0; i < noNestedInstrAttrib.intValue(); i++) {
                    if (nestedInstrmtAttribGroups[i] != null) {
                        bao.write(nestedInstrmtAttribGroups[i].encode(MsgSecureType.ALL_FIELDS));
                    }
                }
            } else {
                String error = "NestedInstrmtAttribGroups field has been set but there is no data or the number of groups does not match.";
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
            case NoTradingSessionRules:
                noTradingSessionRules = new Integer(new String(tag.value, sessionCharset));
                break;

            case NoNestedInstrAttrib:
                noNestedInstrAttrib = new Integer(new String(tag.value, sessionCharset));
                break;

            default:
                String error = "Tag value [" + tag.tagNum + "] not present in [SecurityTradingRules] fields.";
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
        b.append("{SecurityTradingRules=");
        printTagValue(b, baseTradingRules);
        printTagValue(b, TagNum.NoTradingSessionRules, noTradingSessionRules);
        printTagValue(b, tradingSessionRuleGroups);
        printTagValue(b, TagNum.NoNestedInstrAttrib, noNestedInstrAttrib);
        printTagValue(b, nestedInstrmtAttribGroups);
        b.append("}");

        return b.toString();
    }

    // </editor-fold>
}
