/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */
package net.hades.fix.sample.message;

import net.hades.fix.sample.message.group.CustomNewGroup;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import net.hades.fix.message.FIXFragment;
import net.hades.fix.message.FIXMsg;
import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.Header;
import net.hades.fix.message.MsgSecureType;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.struct.Tag;
import net.hades.fix.message.type.ApplVerID;
import net.hades.fix.message.type.BeginString;
import net.hades.fix.message.type.SessionRejectReason;
import net.hades.fix.message.type.TagNum;
import net.hades.fix.message.util.BooleanConverter;
import net.hades.fix.message.util.DateConverter;
import net.hades.fix.message.util.MsgUtil;
import net.hades.fix.message.util.TagEncoder;
import net.hades.fix.message.xml.codec.jaxb.adapter.FixBooleanAdapter;
import net.hades.fix.message.xml.codec.jaxb.adapter.FixUTCDateTimeAdapter;
import net.hades.fix.sample.message.type.CustTagNum;

import java.util.logging.Level;

@XmlRootElement(name = "CstmMsg")
@XmlType(propOrder = {"header", "custNewGroups"})
@XmlAccessorType(XmlAccessType.NONE)
public class CustomNewMsg extends FIXMsg {

    public static final String MSG_TYPE = "AZ";

    protected static final Set<Integer> START_COMP_TAGS;

    protected static final Set<Integer> ALL_TAGS;

    protected static final Set<Integer> TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
        CustTagNum.CustNewMsgIntTag.getValue(),
        CustTagNum.CustNewMsgStringTag.getValue(),
        CustTagNum.CustNewMsgDateTag.getValue(),
        CustTagNum.CustNewMsgBoolTag.getValue(),
        CustTagNum.CustNewGroupTagLen.getValue()
    }));

    protected static final Set<Integer> START_DATA_TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
        CustTagNum.CustNewMsgDataLenTag.getValue()
    }));

    protected static final Set<Integer> CUST_NEW_MSG_GROUP_TAGS = new CustomNewGroup().getFragmentAllTags();

    private static final long serialVersionUID = 1L;

    // CustTagNum.CustNewMsgIntTag
    @XmlAttribute(name = "CustNewMsgInt")
    protected Integer custNewMsgInt;

    // CustTagNum.CustNewMsgStringTag
    @XmlAttribute(name = "CustNewMsgString")
    protected String custNewMsgString;

    // CustTagNum.CustNewMsgDateTag
    @XmlAttribute(name = "CustNewMsgDate")
    @XmlJavaTypeAdapter(FixUTCDateTimeAdapter.class)
    protected Date custNewMsgDate;

    // CustTagNum.CustNewMsgBoolTag
    @XmlAttribute(name = "CustNewMsgBool")
    @XmlJavaTypeAdapter(FixBooleanAdapter.class)
    protected Boolean custNewMsgBool;

    // CustTagNum.CustNewMsgDataLenTag
    protected Integer custNewMsgDataLen;

    // CustTagNum.CustNewMsgDataTag
    protected byte[] custNewMsgData;

    // CustTagNum.CustNewGroupTagLen
    protected Integer noCustNewGroups;

    @XmlElementRef
    protected CustomNewGroup[] custNewGroups;

    static {
        ALL_TAGS = new HashSet<Integer>(TAGS);
        ALL_TAGS.addAll(CUST_NEW_MSG_GROUP_TAGS);
        ALL_TAGS.addAll(START_DATA_TAGS);
        START_COMP_TAGS = new HashSet<Integer>(CUST_NEW_MSG_GROUP_TAGS);
    }

    public CustomNewMsg() {
        super();
    }

    public CustomNewMsg(Header header, ByteBuffer rawMsg)
        throws InvalidMsgException, TagNotPresentException, BadFormatMsgException {
        super(header, rawMsg);
    }

    public CustomNewMsg(BeginString beginString) throws InvalidMsgException {
        super(MSG_TYPE, beginString);
    }

    public CustomNewMsg(BeginString beginString, ApplVerID applVerID) throws InvalidMsgException {
        super(MSG_TYPE, beginString, applVerID);
    }

    // THIS METHOD IS NEEDED BY THE UNDERLYING HadesFIX FIXML FRAMEWORK
    @Override
    public void copyFixmlData(FIXFragment fragment) {
        CustomNewMsg fixml = (CustomNewMsg) fragment;
        if (fixml.getCustNewMsgInt() != null) {
            custNewMsgInt = fixml.getCustNewMsgInt();
        }
        if (fixml.getCustNewMsgString() != null) {
            custNewMsgString = fixml.getCustNewMsgString();
        }
        if (fixml.getCustNewMsgBool() != null) {
            custNewMsgBool = fixml.getCustNewMsgBool();
        }
        if (fixml.getCustNewMsgDate() != null) {
            custNewMsgDate = fixml.getCustNewMsgDate();
        }
        if (fixml.getCustNewGroups() != null && fixml.getCustNewGroups().length > 0) {
            setCustNewGroups(fixml.getCustNewGroups());
        }
    }

    @XmlElementRef
    @Override
    public Header getHeader() {
        return header;
    }

    @Override
    public void setHeader(Header header) {
        this.header = header;
    }
    @Override
    public Set<Integer> getFragmentTags() {
        return TAGS;
    }

    @Override
    public Set<Integer> getFragmentAllTags() {
        return ALL_TAGS;
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
    protected void validateRequiredTags() throws TagNotPresentException {
        if (custNewMsgInt == null) {
            throw new TagNotPresentException("Tag [" + CustTagNum.CustNewMsgIntTag + "] is missing from " +
                    "the message.");
        }
    }

    @Override
    protected void setFragmentTagValue(Tag tag) throws BadFormatMsgException {
        CustTagNum tagNum = CustTagNum.fromString(tag.tagNum);
        switch (tagNum) {
            case CustNewMsgIntTag:
                custNewMsgInt = new Integer(new String(tag.value, sessionCharset));
                break;

            case CustNewMsgStringTag:
                custNewMsgString = new String(tag.value, sessionCharset);
                break;

            case CustNewMsgDateTag:
                custNewMsgDate = DateConverter.parseString(new String(tag.value, sessionCharset));
                break;

            case CustNewMsgBoolTag:
                custNewMsgBool = BooleanConverter.parse(new String(tag.value, sessionCharset));
                break;

            case CustNewGroupTagLen:
                noCustNewGroups = new Integer(new String(tag.value, sessionCharset));
                break;

            default:
                String error = "Tag value [" + tag.tagNum + "] not present in CustomNewMsg message.";
                LOGGER.severe(error);
                throw new BadFormatMsgException(SessionRejectReason.InvalidTagNumber, getHeader().getMsgType(),
                        tag.tagNum, error);
        }
    }

    @Override
    protected void setFragmentCompTagValue(Tag tag, ByteBuffer message) throws BadFormatMsgException, InvalidMsgException, TagNotPresentException {
        FragmentContext context = new FragmentContext(sessionCharset, messageEncoding);
        if (CUST_NEW_MSG_GROUP_TAGS.contains(tag.tagNum)) {
            if (noCustNewGroups != null && noCustNewGroups.intValue() > 0) {
                message.reset();
                custNewGroups = new CustomNewGroup[noCustNewGroups.intValue()];
                for (int i = 0; i < noCustNewGroups.intValue(); i++) {
                    CustomNewGroup group = new CustomNewGroup(context);
                    group.decode(message);
                    custNewGroups[i] = group;
                }
            }
        }
    }

    @Override
    protected ByteBuffer setFragmentDataTagValue(Tag tag, ByteBuffer message) throws BadFormatMsgException {
        ByteBuffer result = message;
        if (tag.tagNum == CustTagNum.CustNewMsgDataLenTag.getValue()) {
            try {
                custNewMsgDataLen = new Integer(new String(tag.value, getSessionCharset()));
            } catch (NumberFormatException ex) {
                String error = "Tag [CustNewMsgDataLenTag] requires an integer value. The value set was [" + tag.value + "].";
                LOGGER.log(Level.SEVERE, "{0} Error was: {1}", new Object[]{error, ex.toString()});
                throw new BadFormatMsgException(SessionRejectReason.IncorrectDataFormat, getHeader().getMsgType(),
                        TagNum.EncodedTextLen.getValue(), error);
            }
            Tag  dataTag = MsgUtil.getNextTag(message, custNewMsgDataLen.intValue());
            custNewMsgData = dataTag.value;
        }

        return result;
    }

    @Override
    protected Set<Integer> getFragmentSecuredTags() {
        return new HashSet<Integer>();
    }

    @Override
    protected byte[] encodeFragmentAll() throws TagNotPresentException, BadFormatMsgException {
        validateRequiredTags();
        byte[] result = new byte[0];
        ByteArrayOutputStream bao = new ByteArrayOutputStream();
        try {
            TagEncoder.encode(bao, CustTagNum.CustNewMsgIntTag.getValue(), custNewMsgInt);
            TagEncoder.encode(bao, CustTagNum.CustNewMsgStringTag.getValue(), custNewMsgString);
            TagEncoder.encodeUtcTimestamp(bao, CustTagNum.CustNewMsgDateTag.getValue(), custNewMsgDate);
            TagEncoder.encode(bao, CustTagNum.CustNewMsgBoolTag.getValue(), custNewMsgBool);
            if (custNewMsgDataLen != null && custNewMsgDataLen.intValue() > 0) {
                if (custNewMsgData != null && custNewMsgData.length > 0) {
                    custNewMsgDataLen = new Integer(custNewMsgData.length);
                    TagEncoder.encode(bao, CustTagNum.CustNewMsgDataLenTag.getValue(), custNewMsgDataLen);
                    TagEncoder.encode(bao, CustTagNum.CustNewMsgDataTag.getValue(), custNewMsgData);
                }
            }
            if (noCustNewGroups != null) {
                TagEncoder.encode(bao, CustTagNum.CustNewGroupTagLen.getValue(), noCustNewGroups);
                if (custNewGroups != null && custNewGroups.length == noCustNewGroups.intValue()) {
                    for (int i = 0; i < noCustNewGroups.intValue(); i++) {
                        if (custNewGroups[i] != null) {
                            bao.write(custNewGroups[i].encode(MsgSecureType.ALL_FIELDS));
                        }
                    }
                } else {
                    String error = "CustNewGroups field has been set but there is no data or the number of groups does not match.";
                    LOGGER.severe(error);
                    throw new BadFormatMsgException(SessionRejectReason.IncorrectCountForGroups, getHeader().getMsgType(),
                            CustTagNum.CustNewGroupTagLen.getValue(), error);
                }
            }
            result = bao.toByteArray();
        } catch (IOException ex) {
            String error = "Error writing to the byte array.";
            LOGGER.log(Level.SEVERE, "{0} Error was : {1}", new Object[]{error, ex.toString()});
            throw new BadFormatMsgException(error, ex);
        }

        return result;
    }

    @Override
    protected byte[] encodeFragmentSecured(boolean secured) throws TagNotPresentException, BadFormatMsgException {
        return new byte[0];
    }

    public CustomNewGroup[] getCustNewGroups() {
        return custNewGroups;
    }

    public void setCustNewGroups(CustomNewGroup[] custNewGroups) {
        this.custNewGroups = custNewGroups;
        if (custNewGroups != null && custNewGroups.length > 0) {
            noCustNewGroups = new Integer(custNewGroups.length);
        }
    }

    public byte[] getCustNewMsgData() {
        return custNewMsgData;
    }

    public void setCustNewMsgData(byte[] custNewMsgData) {
        this.custNewMsgData = custNewMsgData;
    }

    public Integer getCustNewMsgDataLen() {
        return custNewMsgDataLen;
    }

    public void setCustNewMsgDataLen(Integer custNewMsgDataLen) {
        this.custNewMsgDataLen = custNewMsgDataLen;
    }

    public Date getCustNewMsgDate() {
        return custNewMsgDate;
    }

    public void setCustNewMsgDate(Date custNewMsgDate) {
        this.custNewMsgDate = custNewMsgDate;
    }

    public Integer getCustNewMsgInt() {
        return custNewMsgInt;
    }

    public void setCustNewMsgInt(Integer custNewMsgInt) {
        this.custNewMsgInt = custNewMsgInt;
    }

    public String getCustNewMsgString() {
        return custNewMsgString;
    }

    public void setCustNewMsgString(String custNewMsgString) {
        this.custNewMsgString = custNewMsgString;
    }

    public Integer getNoCustNewGroups() {
        return noCustNewGroups;
    }

    public void setNoCustNewGroups(Integer noCustNewGroups) {
        this.noCustNewGroups = noCustNewGroups;
    }

    public Boolean getCustNewMsgBool() {
        return custNewMsgBool;
    }

    public void setCustNewMsgBool(Boolean custNewMsgBool) {
        this.custNewMsgBool = custNewMsgBool;
    }

}
