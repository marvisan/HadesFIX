package net.hades.fix.sample.message.group;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.group.Group;
import net.hades.fix.message.struct.Tag;
import net.hades.fix.message.type.SessionRejectReason;
import net.hades.fix.message.util.TagEncoder;
import net.hades.fix.sample.message.type.CustTagNum;

@XmlRootElement(name = "CstmGrp")
@XmlType
@XmlAccessorType(XmlAccessType.NONE)
public class CustomNewGroup extends Group {

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> START_COMP_TAGS = null;

    protected static final Set<Integer> ALL_TAGS;

    protected static final Set<Integer> TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
        CustTagNum.CustNewGroupTagType.getValue(),
        CustTagNum.CustNewGroupTagName.getValue()
    }));

    protected static final Set<Integer> START_DATA_TAGS = null;

    static {
        ALL_TAGS = new HashSet<Integer>(TAGS);
    }

    @XmlAttribute(name = "GrpTyp")
    protected Integer grpType;

    @XmlAttribute(name = "GrpName")
    protected String grpName;

    public CustomNewGroup() {
    }

    public CustomNewGroup(FragmentContext context) {
        super(context);
    }

    @Override
    public Set<Integer> getFragmentTags() {
        return TAGS;
    }

    @Override
    public Set<Integer> getFragmentAllTags() {
        return ALL_TAGS;
    }

    public String getGrpName() {
        return grpName;
    }

    public void setGrpName(String grpName) {
        this.grpName = grpName;
    }

    public Integer getGrpType() {
        return grpType;
    }

    public void setGrpType(Integer grpType) {
        this.grpType = grpType;
    }

    @Override
    protected int getFirstTag() {
        return CustTagNum.CustNewGroupTagType.getValue();
    }

    @Override
    protected void validateRequiredTags() throws TagNotPresentException {
        if (grpType == null) {
            throw new TagNotPresentException("Tag [" + CustTagNum.CustNewGroupTagType + "] is missing from " +
                    "the message.");
        }
    }

    @Override
    protected void setFragmentTagValue(Tag tag) throws BadFormatMsgException {
        CustTagNum tagNum = CustTagNum.fromString(tag.tagNum);
        switch (tagNum) {
            case CustNewGroupTagType:
                grpType = new Integer(new String(tag.value, sessionCharset));
                break;

            case CustNewGroupTagName:
                grpName = new String(tag.value, sessionCharset);
                break;

            default:
                String error = "Tag value [" + tag.tagNum + "] not present in CustomNewGroup group.";
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
        return null;
    }

    @Override
    protected byte[] encodeFragmentAll() throws TagNotPresentException, BadFormatMsgException {
        validateRequiredTags();
        byte[] result = new byte[0];
        ByteArrayOutputStream bao = new ByteArrayOutputStream();
        try {
            TagEncoder.encode(bao, CustTagNum.CustNewGroupTagType.getValue(), grpType);
            TagEncoder.encode(bao, CustTagNum.CustNewGroupTagName.getValue(), grpName);
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

    @Override
    protected String getUnsupportedTagMessage() {
        return "Tag not supported in this version";
    }

    @Override
    public String toString() {
        StringBuilder b = new StringBuilder(System.getProperty("line.separator"));
        b.append("{CustomNewGroup=");
        printTagValue(b, CustTagNum.CustNewGroupTagType.toString(), grpType);
        printTagValue(b, CustTagNum.CustNewGroupTagName.toString(), grpName);
        b.append("}");

        return b.toString();
    }
}
