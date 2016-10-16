/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */
package net.hades.fix.sample.message;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.*;
import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import net.hades.fix.message.*;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.group.AltMDSourceGroup;
import net.hades.fix.message.group.impl.v44.AltMDSourceGroup44;
import net.hades.fix.message.struct.Tag;
import net.hades.fix.message.type.*;
import net.hades.fix.message.util.BooleanConverter;
import net.hades.fix.message.util.DateConverter;
import net.hades.fix.message.util.MsgUtil;
import net.hades.fix.message.util.TagEncoder;
import net.hades.fix.message.xml.codec.jaxb.adapter.FixBooleanAdapter;
import net.hades.fix.message.xml.codec.jaxb.adapter.FixUTCDateTimeAdapter;
import net.hades.fix.sample.message.group.CustomNewGroup;
import net.hades.fix.sample.message.type.CustTagNum;

@XmlRootElement(name = "MktDataReqRej")
@XmlType(propOrder = {"header", "altMDSourceGroups", "custNewGroups"})
@XmlAccessorType(XmlAccessType.NONE)
public class MarketDataRequestRejectCustomMsg extends MarketDataRequestRejectMsg {

    public static final String MSG_TYPE = "AZ";

    protected static final Set<Integer> START_COMP_TAGS;

    protected static final Set<Integer> ALL_TAGS;

    protected static final Set<Integer> TAGS_CUSTOM = new HashSet<Integer>(Arrays.asList(new Integer[]{
            CustTagNum.CustNewMsgIntTag.getValue(),
            CustTagNum.CustNewMsgStringTag.getValue(),
            CustTagNum.CustNewMsgDateTag.getValue(),
            CustTagNum.CustNewMsgBoolTag.getValue(),
            CustTagNum.CustNewGroupTagLen.getValue()
    }));

    protected static final Set<Integer> START_DATA_TAGS_CUSTOM = new HashSet<Integer>(Arrays.asList(new Integer[]{
            CustTagNum.CustNewMsgDataLenTag.getValue()
    }));

    protected static final Set<Integer> CUST_NEW_MSG_GROUP_TAGS = new CustomNewGroup().getFragmentAllTags();
    protected static final Set<Integer> ALT_MD_SOURCE_GROUP_TAGS = new AltMDSourceGroup44().getFragmentAllTags();

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

    @XmlAttribute(name = "CustNewMsgDataLenTag")
    // CustTagNum.CustNewMsgDataLenTag
    protected Integer custNewMsgDataLen;

    @XmlAttribute(name = "CustNewMsgDataTag")
    // CustTagNum.CustNewMsgDataTag
    protected byte[] custNewMsgData;

    // CustTagNum.CustNewGroupTagLen
    protected Integer noCustNewGroups;

    @XmlElementRef
    protected CustomNewGroup[] custNewGroups;

    static {
        START_DATA_TAGS_CUSTOM.addAll(START_DATA_TAGS);
        TAGS_CUSTOM.addAll(TAGS);
        START_COMP_TAGS = new HashSet<Integer>(ALT_MD_SOURCE_GROUP_TAGS);
        START_COMP_TAGS.addAll(CUST_NEW_MSG_GROUP_TAGS);
        ALL_TAGS = new HashSet<Integer>(TAGS_CUSTOM);
        ALL_TAGS.addAll(START_COMP_TAGS);
        ALL_TAGS.addAll(START_DATA_TAGS_CUSTOM);

    }

    public MarketDataRequestRejectCustomMsg(MarketDataRequestRejectMsg v) {
        super();
        setMdReqID(v.getMdReqID());
        setMdReqRejReason(v.getMdReqRejReason());
        setNoAltMDSource(v.getNoAltMDSource());
        setAltMDSourceGroups(v.getAltMDSourceGroups());
        setEncodedTextLen(v.getEncodedTextLen());
        setEncodedText(v.getEncodedText());
    }

    public MarketDataRequestRejectCustomMsg() {
        super();
    }

    public MarketDataRequestRejectCustomMsg(Header header, ByteBuffer rawMsg)
            throws InvalidMsgException, TagNotPresentException, BadFormatMsgException {
        super(header, rawMsg);
    }

    public MarketDataRequestRejectCustomMsg(BeginString beginString) throws InvalidMsgException {
        super(beginString);
    }

    public MarketDataRequestRejectCustomMsg(BeginString beginString, ApplVerID applVerID) throws InvalidMsgException {
        super(beginString, applVerID);
    }

    // THIS METHOD IS NEEDED BY THE UNDERLYING HadesFIX FIXML FRAMEWORK
    @Override
    public void copyFixmlData(FIXFragment fragment) {
        MarketDataRequestRejectCustomMsg fixml = (MarketDataRequestRejectCustomMsg) fragment;
        if (fixml.getMdReqID() != null) {
            mdReqID = fixml.getMdReqID();
        }
        if (fixml.getMdReqRejReason() != null) {
            mdReqRejReason = fixml.getMdReqRejReason();
        }
        if (fixml.getAltMDSourceGroups() != null && fixml.getAltMDSourceGroups().length > 0) {
            setAltMDSourceGroups(fixml.getAltMDSourceGroups());
        }
        if (fixml.getText() != null) {
            text = fixml.getText();
        }
        if (fixml.getEncodedTextLen() != null) {
            encodedTextLen = fixml.getEncodedTextLen();
            encodedText = fixml.getEncodedText();
        }
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
        super.copyFixmlData(fragment);
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

    @XmlAttribute(name = "ReqID")
    @Override
    public String getMdReqID() {
        return mdReqID;
    }

    @Override
    public void setMdReqID(String mdReqID) {
        this.mdReqID = mdReqID;
    }

    @XmlAttribute(name = "ReqRejResn")
    @Override
    public MDReqRejReason getMdReqRejReason() {
        return mdReqRejReason;
    }

    @Override
    public void setMdReqRejReason(MDReqRejReason mdReqRejReason) {
        this.mdReqRejReason = mdReqRejReason;
    }

    @XmlAttribute(name = "Txt")
    @Override
    public String getText() {
        return text;
    }

    @Override
    public void setText(String text) {
        this.text = text;
    }

    @XmlAttribute(name = "EncTxtLen")
    @Override
    public Integer getEncodedTextLen() {
        return encodedTextLen;
    }

    @Override
    public void setEncodedTextLen(Integer encodedTextLen) {
        this.encodedTextLen = encodedTextLen;
    }

    @XmlAttribute(name = "EncTxt")
    @Override
    public byte[] getEncodedText() {
        return encodedText;
    }

    @Override
    public void setEncodedText(byte[] encodedText) {
        this.encodedText = encodedText;
        if (encodedTextLen == null) {
            encodedTextLen = new Integer(encodedText.length);
        }
    }

    @Override
    public Set<Integer> getFragmentTags() {
        return TAGS_CUSTOM;
    }

    @Override
    public Set<Integer> getFragmentAllTags() {
        return ALL_TAGS;
    }

    @Override
    protected Set<Integer> getFragmentDataTags() {
        return START_DATA_TAGS_CUSTOM;
    }

    @Override
    protected Set<Integer> getFragmentCompTags() {
        return START_COMP_TAGS;
    }

    @Override
    protected void setFragmentTagValue(Tag tag) throws BadFormatMsgException {
        CustTagNum tagNum = CustTagNum.fromString(tag.tagNum);
        if (tagNum != null) {
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
                    String error = "Tag value [" + tag.tagNum + "] not present in MarketDataRequestReject.";
                    LOGGER.severe(error);
                    throw new BadFormatMsgException(SessionRejectReason.InvalidTagNumber, getHeader().getMsgType(),
                            tag.tagNum, error);
            }
        } else {
            super.setFragmentTagValue(tag);
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
        if (ALT_MD_SOURCE_GROUP_TAGS.contains(tag.tagNum)) {
            if (noAltMDSource != null && noAltMDSource.intValue() > 0) {
                message.reset();
                altMDSourceGroups = new AltMDSourceGroup[noAltMDSource.intValue()];
                for (int i = 0; i < noAltMDSource.intValue(); i++) {
                    AltMDSourceGroup group = new AltMDSourceGroup44(context);
                    group.decode(message);
                    altMDSourceGroups[i] = group;
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
                LOGGER.severe(error + " Error was: " + ex.toString());
                throw new BadFormatMsgException(SessionRejectReason.IncorrectDataFormat, getHeader().getMsgType(),
                        CustTagNum.CustNewMsgDataLenTag.getValue(), error);
            }
            Tag dataTag = MsgUtil.getNextTag(message, custNewMsgDataLen.intValue());
            custNewMsgData = dataTag.value;
        } else {
            super.setFragmentDataTagValue(tag, message);
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
            bao.write(super.encodeFragmentAll());
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
            LOGGER.severe(error + " Error was : " + ex.toString());
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

    @Override
    public Integer getNoAltMDSource() {
        return noAltMDSource;
    }

    @Override
    public void setNoAltMDSource(Integer noAltMDSource) {
        this.noAltMDSource = noAltMDSource;
        if (noAltMDSource != null) {
            altMDSourceGroups = new AltMDSourceGroup[noAltMDSource.intValue()];
            FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter);
            for (int i = 0; i < altMDSourceGroups.length; i++) {
                altMDSourceGroups[i] = new AltMDSourceGroup44(context);
            }
        }
    }

    @XmlElementRef
    @Override
    public AltMDSourceGroup[] getAltMDSourceGroups() {
        return altMDSourceGroups;
    }

    public void setAltMDSourceGroups(AltMDSourceGroup[] altMDSourceGroups) {
        this.altMDSourceGroups = altMDSourceGroups;
        if (altMDSourceGroups != null) {
            noAltMDSource = new Integer(altMDSourceGroups.length);
        }
    }

    @Override
    public AltMDSourceGroup addAltMDSourceGroup() {
        AltMDSourceGroup group = new AltMDSourceGroup44(new FragmentContext(sessionCharset, messageEncoding));
        List<AltMDSourceGroup> groups = new ArrayList<AltMDSourceGroup>();
        if (altMDSourceGroups != null && altMDSourceGroups.length > 0) {
            groups = new ArrayList<AltMDSourceGroup>(Arrays.asList(altMDSourceGroups));
        }
        groups.add(group);
        altMDSourceGroups = groups.toArray(new AltMDSourceGroup[groups.size()]);
        noAltMDSource = new Integer(altMDSourceGroups.length);

        return group;
    }

    @Override
    public AltMDSourceGroup deleteAltMDSourceGroup(int index) {
        AltMDSourceGroup result = null;
        if (altMDSourceGroups != null && altMDSourceGroups.length > 0 && altMDSourceGroups.length > index) {
            List<AltMDSourceGroup> groups = new ArrayList<AltMDSourceGroup>(Arrays.asList(altMDSourceGroups));
            result = groups.remove(index);
            altMDSourceGroups = groups.toArray(new AltMDSourceGroup[groups.size()]);
            if (altMDSourceGroups.length > 0) {
                noAltMDSource = new Integer(altMDSourceGroups.length);
            } else {
                altMDSourceGroups = null;
                noAltMDSource = null;
            }
        }

        return result;
    }

    @Override
    public int clearAltMDSourceGroups() {
        int result = 0;
        if (altMDSourceGroups != null && altMDSourceGroups.length > 0) {
            result = altMDSourceGroups.length;
            altMDSourceGroups = null;
            noAltMDSource = null;
        }

        return result;
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

    @Override
    public String toString() {
        StringBuilder b = new StringBuilder("{MarketDataRequestRejectCustomMsg=");
        b.append(header.toString());
        b.append(System.getProperty("line.separator")).append("{Body=");
        printTagValue(b, TagNum.MDReqID, mdReqID);
        printTagValue(b, TagNum.MDReqRejReason, mdReqRejReason);
        printTagValue(b, parties);
        printTagValue(b, TagNum.NoAltMDSource, noAltMDSource);
        printTagValue(b, altMDSourceGroups);
        printTagValue(b, TagNum.Text, text);
        printTagValue(b, TagNum.EncodedTextLen, encodedTextLen);
        printTagValue(b, TagNum.EncodedText, encodedText);
        printTagValue(b, CustTagNum.CustNewMsgIntTag.toString(), custNewMsgInt);
        printTagValue(b, CustTagNum.CustNewMsgStringTag.toString(), custNewMsgString);
        printUTCDateTimeTagValue(b, CustTagNum.CustNewMsgDateTag.toString(), custNewMsgDate);
        printTagValue(b, CustTagNum.CustNewMsgDataLenTag.toString(), custNewMsgDataLen);
        printTagValue(b, CustTagNum.CustNewMsgDataTag.toString(), custNewMsgData);
        printTagValue(b, CustTagNum.CustNewGroupTagLen.toString(), noCustNewGroups);
        printTagValue(b, custNewGroups);
        b.append("}");
        b.append(trailer != null ? trailer.toString() : "").append("}");

        return b.toString();
    }
}
