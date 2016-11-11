/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * PosUndInstrmtGroup50SP2.java
 *
 * $Id: PartyGroup44.java,v 1.12 2011-04-14 23:44:45 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v50sp2;

import net.hades.fix.message.group.UnderlyingAmountGroup;
import net.hades.fix.message.struct.Tag;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.comp.UnderlyingInstrument;
import net.hades.fix.message.comp.impl.v50sp2.UnderlyingInstrument50SP2;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.group.PosUndInstrmtGroup;
import net.hades.fix.message.type.SettlPriceType;
import net.hades.fix.message.type.TagNum;
import net.hades.fix.message.util.MsgUtil;
import net.hades.fix.message.util.TagEncoder;

/**
 * FIX 5.0SP2 implementation of PosUndInstrmtGroup group.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.12 $
 * @created 12/12/2011, 7:22:35 PM
 */
@XmlRootElement(name="PosUnd")
@XmlType(propOrder={"underlyingInstrument", "underlyingAmountGroups"})
@XmlAccessorType(XmlAccessType.NONE)
public class PosUndInstrmtGroup50SP2 extends PosUndInstrmtGroup {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> START_COMP_TAGS;

    protected static final Set<Integer> ALL_TAGS;

    protected static final Set<Integer> UND_INSTR_COMP_TAGS = new UnderlyingInstrument50SP2().getFragmentAllTags();
    protected static final Set<Integer> UND_AMT_GROUP_TAGS = new UnderlyingAmountGroup50SP2().getFragmentAllTags();

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">

    static {
        ALL_TAGS = new HashSet<Integer>(TAGS);
        ALL_TAGS.addAll(UND_INSTR_COMP_TAGS);
        ALL_TAGS.addAll(UND_AMT_GROUP_TAGS);
        START_COMP_TAGS = new HashSet<Integer>(UND_INSTR_COMP_TAGS);
        START_COMP_TAGS.addAll(UND_AMT_GROUP_TAGS);
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public PosUndInstrmtGroup50SP2() {
    }

    public PosUndInstrmtGroup50SP2(FragmentContext context) {
        super(context);
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Public Methods">

    @Override
    public Set<Integer> getFragmentAllTags() {
        return ALL_TAGS;
    }

    // ACCESSOR METHODS
    //////////////////////////////////////////

    @XmlElementRef
    @Override
    public UnderlyingInstrument getUnderlyingInstrument() {
        return underlyingInstrument;
    }

    public void setUnderlyingInstrument(UnderlyingInstrument underlyingInstrument) {
        this.underlyingInstrument = underlyingInstrument;
    }

    @Override
    public void setUnderlyingInstrument() {
        FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, validateRequired);
        this.underlyingInstrument = new UnderlyingInstrument50SP2(context);
    }

    @Override
    public void clearUnderlyingInstrument() {
        this.underlyingInstrument = null;
    }

    @XmlAttribute(name = "UndSetPx")
    @Override
    public Double getUnderlyingSettlPrice() {
        return underlyingSettlPrice;
    }

    @Override
    public void setUnderlyingSettlPrice(Double underlyingSettlPrice) {
        this.underlyingSettlPrice = underlyingSettlPrice;
    }

    @XmlAttribute(name = "UndSetPxTyp")
    @Override
    public SettlPriceType getUnderlyingSettlPriceType() {
        return underlyingSettlPriceType;
    }
    
    @Override
    public void setUnderlyingSettlPriceType(SettlPriceType underlyingSettlPriceType) {
        this.underlyingSettlPriceType = underlyingSettlPriceType;
    }

    @XmlAttribute(name = "UndlyDlvAmt")
    @Override
    public Double getUnderlyingDeliveryAmount() {
        return underlyingDeliveryAmount;
    }

    @Override
    public void setUnderlyingDeliveryAmount(Double underlyingDeliveryAmount) {
        this.underlyingDeliveryAmount = underlyingDeliveryAmount;
    }

    @Override
    public Integer getNoUnderlyingAmounts() {
        return noUnderlyingAmounts;
    }

    @Override
    public void setNoUnderlyingAmounts(Integer noUnderlyingAmounts) {
        this.noUnderlyingAmounts = noUnderlyingAmounts;
        if (noUnderlyingAmounts != null) {
            underlyingAmountGroups = new UnderlyingAmountGroup[noUnderlyingAmounts.intValue()];
            for (int i = 0; i < underlyingAmountGroups.length; i++) {
                underlyingAmountGroups[i] = new UnderlyingAmountGroup50SP2(new FragmentContext(sessionCharset, messageEncoding, validateRequired));
            }
        }
    }

    @XmlElementRef
    @Override
    public UnderlyingAmountGroup[] getUnderlyingAmountGroups() {
        return underlyingAmountGroups;
    }

    public void setUnderlyingAmountGroups(UnderlyingAmountGroup[] underlyingAmountGroups) {
        this.underlyingAmountGroups = underlyingAmountGroups;
        if (underlyingAmountGroups != null) {
            noUnderlyingAmounts = new Integer(underlyingAmountGroups.length);
        }
    }

    @Override
    public UnderlyingAmountGroup addUnderlyingAmountGroup() {
        UnderlyingAmountGroup group = new UnderlyingAmountGroup50SP2(new FragmentContext(sessionCharset, messageEncoding, validateRequired));
        List<UnderlyingAmountGroup> groups = new ArrayList<UnderlyingAmountGroup>();
        if (underlyingAmountGroups != null && underlyingAmountGroups.length > 0) {
            groups = new ArrayList<UnderlyingAmountGroup>(Arrays.asList(underlyingAmountGroups));
        }
        groups.add(group);
        underlyingAmountGroups = groups.toArray(new UnderlyingAmountGroup[groups.size()]);
        noUnderlyingAmounts = new Integer(underlyingAmountGroups.length);

        return group;
    }

    @Override
    public UnderlyingAmountGroup deleteUnderlyingAmountGroup(int index) {
        UnderlyingAmountGroup result = null;

        if (underlyingAmountGroups != null && underlyingAmountGroups.length > 0 && underlyingAmountGroups.length > index) {
            List<UnderlyingAmountGroup> groups = new ArrayList<UnderlyingAmountGroup>(Arrays.asList(underlyingAmountGroups));
            result = groups.remove(index);
            underlyingAmountGroups = groups.toArray(new UnderlyingAmountGroup[groups.size()]);
            if (underlyingAmountGroups.length > 0) {
                noUnderlyingAmounts = new Integer(underlyingAmountGroups.length);
            } else {
                underlyingAmountGroups = null;
                noUnderlyingAmounts = null;
            }
        }

        return result;
    }

    @Override
    public int clearUnderlyingAmountGroups() {

        int result = 0;
        if (underlyingAmountGroups != null && underlyingAmountGroups.length > 0) {
            result = underlyingAmountGroups.length;
            underlyingAmountGroups = null;
            noUnderlyingAmounts = null;
        }

        return result;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

    @Override
    protected byte[] encodeFragmentSecured(boolean secured) throws TagNotPresentException, BadFormatMsgException {
        byte[] result = new byte[0];
        ByteArrayOutputStream bao = new ByteArrayOutputStream();

        try {
            if (underlyingInstrument != null) {
                bao.write(underlyingInstrument.encode(getMsgSecureTypeForFlag(secured)));
            }
            if (MsgUtil.isTagInList(TagNum.UnderlyingSettlPrice, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.UnderlyingSettlPrice, underlyingSettlPrice);
            }
            if (underlyingSettlPriceType != null && MsgUtil.isTagInList(TagNum.UnderlyingSettlPriceType, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.UnderlyingSettlPriceType, underlyingSettlPriceType.getValue());
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
    protected void setFragmentCompTagValue(Tag tag, ByteBuffer message)
    throws BadFormatMsgException, InvalidMsgException, TagNotPresentException {
        FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, validateRequired);
        if (UND_INSTR_COMP_TAGS.contains(tag.tagNum)) {
            if (underlyingInstrument == null) {
                underlyingInstrument = new UnderlyingInstrument50SP2(context);
            }
            message.reset();
            underlyingInstrument.decode(message);
        }
        if (UND_AMT_GROUP_TAGS.contains(tag.tagNum)) {
            if (noUnderlyingAmounts != null && noUnderlyingAmounts.intValue() > 0) {
                message.reset();
                if (underlyingAmountGroups == null) {
                    underlyingAmountGroups = new UnderlyingAmountGroup[noUnderlyingAmounts.intValue()];
                }
                for (int i = 0; i < underlyingAmountGroups.length; i++) {
                    UnderlyingAmountGroup group = new UnderlyingAmountGroup50SP2(context);
                    group.decode(message);
                    underlyingAmountGroups[i] = group;
                }
            }
        }
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
        return "This tag is not supported in [PosUndInstrmtGroup] group version [5.0SP2].";
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Package Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Private Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Inner Classes">
    // </editor-fold>
}
