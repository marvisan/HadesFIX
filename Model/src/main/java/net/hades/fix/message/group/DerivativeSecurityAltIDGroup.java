/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * DerivativeSecurityAltIDGroup.java
 *
 * $Id: DerivativeSecurityAltIDGroup.java,v 1.1 2011-09-19 08:15:45 vrotaru Exp $
 */
package net.hades.fix.message.group;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.anno.FIXVersion;
import net.hades.fix.message.anno.TagNumRef;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.struct.Tag;
import net.hades.fix.message.type.SessionRejectReason;
import net.hades.fix.message.type.TagNum;
import net.hades.fix.message.util.TagEncoder;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

/**
 * Derivative Security ID group.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 28/10/2008, 7:22:22 PM
 */
@XmlAccessorType(XmlAccessType.NONE)
public abstract class DerivativeSecurityAltIDGroup extends Group {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.DerivativeSecurityAltID.getValue(),
        TagNum.DerivativeSecurityAltIDSource.getValue()
    }));

    protected static final Set<Integer> START_DATA_TAGS = null;

    protected static final Set<Integer> START_COMP_TAGS = null;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">
    
    protected Set<Integer> STANDARD_SECURED_TAGS = TAGS;
    
    /** 
     * TagNum = 1219. 
     */
    protected String derivativeSecurityAltID;
    
    /** 
     * TagNum = 1220. 
     */
    protected String derivativeSecurityAltIDSource;
    
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">
    
    public DerivativeSecurityAltIDGroup() {
    }
    
    public DerivativeSecurityAltIDGroup(FragmentContext context) {
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
        return TAGS;
    }

    // ACCESSOR METHODS
    //////////////////////////////////////////
    
    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.DerivativeSecurityAltID, required=true)
    public String getDerivativeSecurityAltID() {
        return derivativeSecurityAltID;
    }

    /**
     * Message field setter.
     * @param derivativeSecurityAltID field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.DerivativeSecurityAltID, required=true)
    public void setDerivativeSecurityAltID(String derivativeSecurityAltID) {
        this.derivativeSecurityAltID = derivativeSecurityAltID;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.DerivativeSecurityAltIDSource)
    public String getDerivativeSecurityAltIDSource() {
        return derivativeSecurityAltIDSource;
    }

    /**
     * Message field setter.
     * @param derivativeSecurityAltIDSource field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.DerivativeSecurityAltIDSource)
    public void setDerivativeSecurityAltIDSource(String derivativeSecurityAltIDSource) {
        this.derivativeSecurityAltIDSource = derivativeSecurityAltIDSource;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

    @Override
    protected int getFirstTag() {
        return TagNum.DerivativeSecurityAltID.getValue();
    }

    @Override
    protected void validateRequiredTags() throws TagNotPresentException {
        StringBuilder errorMsg = new StringBuilder("Tag value(s) for");
        boolean hasMissingTag = false;
        if (derivativeSecurityAltID == null || derivativeSecurityAltID.trim().isEmpty()) {
            errorMsg.append(" [DerivativeSecurityAltID]");
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
            TagEncoder.encode(bao, TagNum.DerivativeSecurityAltID, derivativeSecurityAltID);
            TagEncoder.encode(bao, TagNum.DerivativeSecurityAltIDSource, derivativeSecurityAltIDSource);
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
            case DerivativeSecurityAltID:
                derivativeSecurityAltID = new String(tag.value, sessionCharset);
                break;

            case DerivativeSecurityAltIDSource:
                derivativeSecurityAltIDSource = new String(tag.value, sessionCharset);
                break;
                
            default:
                String error = "Tag value [" + tag.tagNum + "] not present in [DerivativeSecurityAltIDGroup] fields.";
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
        b.append("{DerivativeSecurityAltIDGroup=");
        printTagValue(b, TagNum.DerivativeSecurityAltID, derivativeSecurityAltID);
        printTagValue(b, TagNum.DerivativeSecurityAltIDSource, derivativeSecurityAltIDSource);
        b.append("}");

        return b.toString();
    }

    // </editor-fold>
}
