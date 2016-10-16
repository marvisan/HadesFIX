/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * InstrmtAttribGroup44.java
 *
 * $Id: InstrmtAttribGroup44.java,v 1.1 2011-02-13 04:40:41 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v44;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.group.InstrmtAttribGroup;
import net.hades.fix.message.type.InstrAttribType;
import net.hades.fix.message.type.TagNum;
import net.hades.fix.message.util.MsgUtil;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import net.hades.fix.message.util.TagEncoder;

/**
 * FIX 4.4 implementation of InstrmtAttribGroup group.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 29/04/2009, 6:46:57 PM
 */
@XmlRootElement(name = "Attrb")
@XmlType
@XmlAccessorType(XmlAccessType.NONE)
public class InstrmtAttribGroup44 extends InstrmtAttribGroup {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> ALL_TAGS;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">

    static {
        ALL_TAGS = new HashSet<Integer>(TAGS);
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">

    protected Set<Integer> STANDARD_SECURED_TAGS = ALL_TAGS;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public InstrmtAttribGroup44() {
        super();
        SECURED_TAGS = STANDARD_SECURED_TAGS;
    }

    public InstrmtAttribGroup44(FragmentContext context) {
        super(context);
        SECURED_TAGS = STANDARD_SECURED_TAGS;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Public Methods">

    @Override
    public Set<Integer> getFragmentAllTags() {
        return ALL_TAGS;
    }

    @Override
    public Set<Integer> getFragmentTags() {
        return TAGS;
    }

    // ACCESSOR METHODS
    //////////////////////////////////////////

    @XmlAttribute(name = "Typ")
    @Override
    public InstrAttribType getInstrAttribType() {
        return instrAttribType;
    }

    @Override
    public void setInstrAttribType(InstrAttribType instrAttribType) {
        this.instrAttribType = instrAttribType;
    }

    @XmlAttribute(name = "Val")
    @Override
    public String getInstrAttribValue() {
        return instrAttribValue;
    }

    @Override
    public void setInstrAttribValue(String instrAttribValue) {
        this.instrAttribValue = instrAttribValue;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

    @Override
    protected byte[] encodeFragmentSecured(boolean secured) throws TagNotPresentException, BadFormatMsgException {
        byte[] result = new byte[0];
        ByteArrayOutputStream bao = new ByteArrayOutputStream();

        try {
            if (instrAttribType != null && MsgUtil.isTagInList(TagNum.InstrAttribType, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.InstrAttribType, instrAttribType.getValue());
            }
            if (MsgUtil.isTagInList(TagNum.InstrAttribValue, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.InstrAttribValue, instrAttribValue);
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
        return "This tag is not supported in [InstrmtAttribGroup] group version [4.4].";
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
}
