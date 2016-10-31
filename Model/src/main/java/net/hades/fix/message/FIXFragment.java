/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * Fragment.java
 *
 * $Id: FIXFragment.java,v 1.8 2011-04-14 23:44:47 vrotaru Exp $
 */
package net.hades.fix.message;

import net.hades.fix.message.crypt.Crypter;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.struct.Tag;
import net.hades.fix.message.type.TagNum;
import net.hades.fix.message.util.DateConverter;
import net.hades.fix.message.util.MsgUtil;
import net.hades.fix.message.util.format.DateFormatter;

import java.io.Serializable;
import java.nio.charset.Charset;
import java.nio.ByteBuffer;
import java.util.Date;
import java.util.logging.Logger;
import java.util.Set;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import net.hades.fix.message.comp.Component;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.util.NumberConverter;
import net.hades.fix.message.util.codec.Base64Codec;

/**
 * Generic decode and encode methods used by the header, trailer, component, group and message body.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.8 $
 * @created 12/11/2008, 7:15:25 PM
 */
@XmlAccessorType(XmlAccessType.NONE)
public abstract class FIXFragment implements Serializable {

    private static final long serialVersionUID = 1L;
    
    protected final Logger LOGGER = Logger.getLogger(this.getClass().getName());

    protected Set<Integer> SECURED_TAGS;
    
    protected Charset sessionCharset;
    
    protected boolean encryptionRequired;

    protected Crypter crypter;

    protected boolean validateRequired;

    protected Charset messageEncoding;

    public FIXFragment() {
        this.sessionCharset = Charset.forName(FIXMsg.DEFAULT_CHARACTER_SET);
    }
    
    public FIXFragment(FragmentContext context) {
        this.sessionCharset = context.getSessionCharset();
        this.encryptionRequired = context.isEncryptionRequired();
        this.crypter = context.getCrypter();
        this.validateRequired = context.isValidateRequired();
    }

    public ByteBuffer decode(Tag tag, ByteBuffer message)
    throws BadFormatMsgException, InvalidMsgException, TagNotPresentException {
        ByteBuffer result = message;
        if (MsgUtil.isTagInList(tag.tagNum, getFragmentTags())) {
            setFragmentTagValue(tag);
        } else if (MsgUtil.isTagInList(tag.tagNum, getFragmentDataTags())) {
            result = setFragmentDataTagValue(tag, message);
        } else if (MsgUtil.isTagInList(tag.tagNum, getFragmentCompTags())) {
            setFragmentCompTagValue(tag, message);
        } else {
            setFragmentTagValue(tag);
        }

        return result;
    }
    
    public byte[] encode(MsgSecureType msgEncType) throws TagNotPresentException, BadFormatMsgException {
        if (validateRequired) {
            validateRequiredTags();
        }
        byte[] result = null;
        switch (msgEncType) {
            case SECURED_FIELDS:
                result = encodeFragmentSecured(true);
                break;
                
            case UNSECURED_FIELDS:
                result = encodeFragmentSecured(false);
                break;
                
            case ALL_FIELDS:
                result = encodeFragmentAll();
                break;
                
            default:
                throw new RuntimeException("Unknown message decoding type [" + msgEncType + "]");
        }
        
        return result;
    }

    public void setValidateRequired(boolean validateRequired) {
        this.validateRequired = validateRequired;
    }
    
    public void setSecuredTags(Set<Integer> securedTags) {
        SECURED_TAGS = securedTags;
    }
    
    public Charset getSessionCharset() {
        return sessionCharset;
    }
    
    public boolean isEncryptionRequired() {
        return encryptionRequired;
    }

    /**
     * This method must be implemented by all the fragments that are used in
     * FIXMl protocol exhange. It will overwrite all the FIXML fragment value
     * that are found in the XML message.
     * @param fragment fragment to copy from
     */
    public void copyFixmlData(FIXFragment fragment) {
        // does nothing
    }

    // ABSTRACT INTERFACE
    //////////////////////////////////////////

    /**
     * Gets the array of tags in the version specific message fragment. These are only the simple attributes
     * representing simple types.
     * @return tags in the version specific message part.
     */
    public abstract Set<Integer> getFragmentTags();

    /**
     * Gets the array of tags in the version specific message fragment including all
     * the tags of the embeded groups and components. The tags returned by this method will
     * contain the tags returned by the <i>getFragmentTags()</i>.
     * @return tags in the version specific message part.
     */
    public abstract Set<Integer> getFragmentAllTags();

    /**
     * Validates the required tags in the message part. Must be implemented by every version of a 
     * FIX message fragment that needs validation.
     * @throws TagNotPresentException required tag does not exists error
     */
    protected abstract void validateRequiredTags() throws TagNotPresentException;
    
    /**
     * Sets the specific version FIX message part value. Must be implemented
     * by every version of FIX message fragment.
     * @param tag tag value tobe set
     * @throws BadFormatMsgException a received tag does not exist in the implementation
     */
    protected abstract void setFragmentTagValue(Tag tag) throws BadFormatMsgException;
    
    /**
     * Sets the specific version message part group. The tag will contain the number
     * of repetitions for the following group. The reading of the groups from the 
     * message buffer will advance the message pointer to the end of the group.
     * Must be implemented by every version of FIX message fragment.
     * @param tag tag containing the number of repetitions
     * @param message message to read groups from
     * @throws BadFormatMsgException a received tag does not exist in the implementation
     * @throws InvalidMsgException unrecoverable error
     * @throws TagNotPresentException required tags are missing
     */
    protected abstract void setFragmentCompTagValue(Tag tag, ByteBuffer message) 
    throws BadFormatMsgException, InvalidMsgException, TagNotPresentException;
           
    /**
     * Sets the specific version data group. The tag will contain the number
     * of repetitions for the following data field. The reading of the data from the 
     * message buffer will advance the message pointer to the end of the data field.
     * Must be implemented by every version of FIX message fragment.
     * @param tag tag containing the number of repetitions
     * @param message message to read binary data from
     * @return the message buffer modified if case
     * @throws BadFormatMsgException error in message tag values
     */
    protected abstract ByteBuffer setFragmentDataTagValue(Tag tag, ByteBuffer message) throws BadFormatMsgException;
    
    /**
     * Gets the array of tags that represent starts of binary data fields (field length)
     * in the version specific message part.
     * @return tags that represent starts of binary data 
     */
    protected abstract Set<Integer> getFragmentDataTags();
    
    /**
     * Gets the array of tags that represent starts of components/groups data fields 
     * in the version specific message. This fragments can be either Groups or Components.
     * @return tags that represent starts of group data
     */
    protected abstract Set<Integer> getFragmentCompTags();
    
    /**
     * Gets the array of tags in the version specific message part that can be part
     * of the secured data section.
     * @return tags in the version specific message part.
     */
    protected abstract Set<Integer> getFragmentSecuredTags();

    /**
     * Encode all the fields if the message must not be encrypted.
     * @return encoded fields
     * @throws TagNotPresentException required tag is not present in data field
     * @throws BadFormatMsgException bad data in field
     */
    protected abstract byte[] encodeFragmentAll() throws TagNotPresentException, BadFormatMsgException ;

    /**
     * Encode a message body whose parameters are already set and are part
     * of secured data fragment.
     * @param secured flag indicating that the encoding to be done is for secured or unsecured fields
     * @return encoded message
     * @throws TagNotPresentException required value missing error
     * @throws BadFormatMsgException could not encode the message error
     */
    protected abstract byte[] encodeFragmentSecured(boolean secured) throws TagNotPresentException, BadFormatMsgException;

    /**
     * Provides a tailored message for an unsupported tag for a specific version.
     * @return error message
     */
    protected abstract String getUnsupportedTagMessage();

    // UTILITY METHODS
    //////////////////////////////////////////
    
    protected void printTagValue(StringBuilder b, TagNum tag, String value) {
        if (value != null) {
            b.append("[").append(tag.toString()).append("#").append(tag.getValue()).append("=").append(value).append("],");
        }
    }

    protected void printTagValue(StringBuilder b, TagNum tag, Character value) {
        if (value != null) {
            b.append("[").append(tag.toString()).append("#").append(tag.getValue()).append("=").append(value.toString()).append("],");
        }
    }

    protected void printTagValue(StringBuilder b, TagNum tag, Integer value) {
        if (value != null) {
            b.append("[").append(tag.toString()).append("#").append(tag.getValue()).append("=").append(value.toString()).append("],");
        }
    }

    protected void printTagValue(StringBuilder b, TagNum tag, Double value) {
        if (value != null) {
            b.append("[").append(tag.toString()).append("#").append(tag.getValue()).append("=").append(NumberConverter.formatNumber(value)).append("],");
        }
    }

    protected void printTagValue(StringBuilder b, TagNum tag, Boolean value) {
        if (value != null) {
            b.append("[").append(tag.toString()).append("#").append(tag.getValue()).append("=").append(value.toString()).append("],");
        }
    }

    protected void printTagValue(StringBuilder b, TagNum tag, byte[] value) {
        if (value != null) {
            b.append("[").append(tag.toString()).append("#").append(tag.getValue()).append("=").append(new String(value)).append("],");
        }
    }

    protected void printBase64TagValue(StringBuilder b, TagNum tag, byte[] value) {
        if (value != null && value.length > 0) {
            b.append("[").append(tag.toString()).append("#").append(tag.getValue()).append("=").append(new String(Base64Codec.encode(value))).append("],");
        }
    }

    protected void printTagValue(StringBuilder b, TagNum tag, Enum<?> value) {
        if (value != null) {
            b.append("[").append(tag.toString()).append("#").append(tag.getValue()).append("=").append(value.toString()).append("],");
        }
    }

    protected void printTagValue(StringBuilder b, Component value) {
        if (value != null) {
            b.append(value.toString()).append(System.getProperty("line.separator"));
        }
    }

    protected void printTagValue(StringBuilder b, Component[] value) {
        if (value != null) {
            for (Component c : value) {
                b.append(c.toString());
            }
            b.append(System.getProperty("line.separator"));
        }
    }

    protected void printDateTimeTagValue(StringBuilder b, TagNum tag, Date value) {
        if (value != null) {
            try {
                printTagValue(b, tag, DateConverter.formatDate(DateFormatter.getFixTSFormat(), value));
            } catch (BadFormatMsgException ex) {
                b.append("Error formating date : ").append(tag).append("=").append(value.toString());
            }
        }
    }

    protected void printDateTagValue(StringBuilder b, TagNum tag, Date value) {
        if (value != null) {
            try {
                printTagValue(b, tag, DateConverter.formatDate(DateFormatter.getFixDateFormat(), value));
            } catch (BadFormatMsgException ex) {
                b.append("Error formating date : ").append(tag).append("=").append(value.toString());
            }
        }
    }

    protected void printTimeTagValue(StringBuilder b, TagNum tag, Date value) {
        if (value != null) {
            try {
                printTagValue(b, tag, DateConverter.formatDate(DateFormatter.getFixTimeFormat(), value));
            } catch (BadFormatMsgException ex) {
                b.append("Error formating date : ").append(tag).append("=").append(value.toString());
            }
        }
    }

    protected void printUTCDateTimeTagValue(StringBuilder b, TagNum tag, Date value) {
        if (value != null) {
            try {
                printTagValue(b, tag, DateConverter.formatUTCDate(DateFormatter.getFixTSFormat(), value));
            } catch (BadFormatMsgException ex) {
                b.append("Error formating date : ").append(tag).append("=").append(value.toString());
            }
        }
    }

    protected void printUTCTimeTagValue(StringBuilder b, TagNum tag, Date value) {
        if (value != null) {
            try {
                printTagValue(b, tag, DateConverter.formatUTCDate(DateFormatter.getFixTimeFormat(), value));
            } catch (BadFormatMsgException ex) {
                b.append("Error formating date : ").append(tag).append("=").append(value.toString());
            }
        }
    }

    protected void printTagValue(StringBuilder b, String tag, String value) {
        if (value != null) {
            b.append("[").append(tag).append("#").append(tag).append("=").append(value).append("],");
        }
    }

    protected void printTagValue(StringBuilder b, String tag, Character value) {
        if (value != null) {
            b.append("[").append(tag).append("#").append(tag).append("=").append(value.toString()).append("],");
        }
    }

    protected void printTagValue(StringBuilder b, String tag, Integer value) {
        if (value != null) {
            b.append("[").append(tag).append("#").append(tag).append("=").append(value.toString()).append("],");
        }
    }

    protected void printTagValue(StringBuilder b, String tag, Double value) {
        if (value != null) {
            b.append("[").append(tag).append("#").append(tag).append("=").append(NumberConverter.formatNumber(value)).append("],");
        }
    }

    protected void printTagValue(StringBuilder b, String tag, Boolean value) {
        if (value != null) {
            b.append("[").append(tag).append("#").append(tag).append("=").append(value.toString()).append("],");
        }
    }

    protected void printTagValue(StringBuilder b, String tag, byte[] value) {
        if (value != null) {
            b.append("[").append(tag).append("#").append(tag).append("=").append(new String(value)).append("],");
        }
    }

    protected void printBase64TagValue(StringBuilder b, String tag, byte[] value) {
        if (value != null && value.length > 0) {
            b.append("[").append(tag).append("#").append(tag).append("=").append(new String(Base64Codec.encode(value))).append("],");
        }
    }

    protected void printTagValue(StringBuilder b, String tag, Enum<?> value) {
        if (value != null) {
            b.append("[").append(tag).append("#").append(tag).append("=").append(value.toString()).append("],");
        }
    }

    protected void printDateTimeTagValue(StringBuilder b, String tag, Date value) {
        if (value != null) {
            try {
                printTagValue(b, tag, DateConverter.formatDate(DateFormatter.getFixTSFormat(), value));
            } catch (BadFormatMsgException ex) {
                b.append("Error formatting date : ").append(tag).append("=").append(value.toString());
            }
        }
    }

    protected void printDateTagValue(StringBuilder b, String tag, Date value) {
        if (value != null) {
            try {
                printTagValue(b, tag, DateConverter.formatDate(DateFormatter.getFixDateFormat(), value));
            } catch (BadFormatMsgException ex) {
                b.append("Error formatting date : ").append(tag).append("=").append(value.toString());
            }
        }
    }

    protected void printTimeTagValue(StringBuilder b, String tag, Date value) {
        if (value != null) {
            try {
                printTagValue(b, tag, DateConverter.formatDate(DateFormatter.getFixTimeFormat(), value));
            } catch (BadFormatMsgException ex) {
                b.append("Error formating date : ").append(tag).append("=").append(value.toString());
            }
        }
    }

    protected void printUTCDateTimeTagValue(StringBuilder b, String tag, Date value) {
        if (value != null) {
            try {
                printTagValue(b, tag, DateConverter.formatUTCDate(DateFormatter.getFixTSFormat(), value));
            } catch (BadFormatMsgException ex) {
                b.append("Error formating date : ").append(tag).append("=").append(value.toString());
            }
        }
    }

    protected void printUTCTimeTagValue(StringBuilder b, String tag, Date value) {
        if (value != null) {
            try {
                printTagValue(b, tag, DateConverter.formatUTCDate(DateFormatter.getFixTimeFormat(), value));
            } catch (BadFormatMsgException ex) {
                b.append("Error formating date : ").append(tag).append("=").append(value.toString());
            }
        }
    }

    protected MsgSecureType getMsgSecureTypeForFlag(boolean secure) {
        if (secure) {
            return MsgSecureType.SECURED_FIELDS;
        } else {
            return MsgSecureType.UNSECURED_FIELDS;
        }
    }
}
