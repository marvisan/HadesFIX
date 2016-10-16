/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * PosAmtGroup50.java
 *
 * $Id: PosAmtGroup44.java,v 1.1 2011-10-21 10:30:59 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v44;

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

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.group.PosAmtGroup;
import net.hades.fix.message.type.PosAmtType;
import net.hades.fix.message.type.TagNum;
import net.hades.fix.message.util.MsgUtil;
import net.hades.fix.message.util.TagEncoder;

/**
 * FIX 5.0 implementation of PosAmtGroup group.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 05/04/2009, 11:39:24 AM
 */
@XmlRootElement(name="Amt")
@XmlType
@XmlAccessorType(XmlAccessType.NONE)
public class PosAmtGroup44 extends PosAmtGroup {

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
    
    protected Set<Integer> STANDARD_SECURED_TAGS = TAGS;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public PosAmtGroup44() {
        SECURED_TAGS = STANDARD_SECURED_TAGS;
    }

    public PosAmtGroup44(FragmentContext context) {
        super(context);
        SECURED_TAGS = STANDARD_SECURED_TAGS;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Public Methods">

    @Override
    public Set<Integer> getFragmentAllTags() {
        return ALL_TAGS;
    }

    @XmlAttribute(name = "Typ")
    @Override
    public PosAmtType getPosAmtType() {
        return posAmtType;
    }

    @Override
    public void setPosAmtType(PosAmtType posAmtType) {
        this.posAmtType = posAmtType;
    }

    @XmlAttribute(name = "Amt")
    @Override
    public Double getPosAmt() {
        return posAmt;
    }

    @Override
    public void setPosAmt(Double posAmt) {
        this.posAmt = posAmt;
    }

    // ACCESSORS
    //////////////////////////////////////////

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

    @Override
    protected byte[] encodeFragmentSecured(boolean secured) throws TagNotPresentException, BadFormatMsgException {
        byte[] result = new byte[0];
        ByteArrayOutputStream bao = new ByteArrayOutputStream();

        try {
            if (posAmtType != null && MsgUtil.isTagInList(TagNum.PosAmtType, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.PosAmtType, posAmtType.getValue());
            }
            if (MsgUtil.isTagInList(TagNum.PosAmt, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.PosAmt, posAmt);
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
        return "This tag is not supported in [PosAmtGroup] group version [4.4].";
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Package Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Private Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Inner Classes">
    // </editor-fold>
}
