/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * Batch.java
 *
 * $Id: Batch.java,v 1.1 2011-04-27 23:28:24 vrotaru Exp $
 */
package net.hades.fix.message.comp;

import net.hades.fix.message.FIXMsg;
import net.hades.fix.message.Header;
import net.hades.fix.message.anno.FIXVersion;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.struct.Tag;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import javax.xml.bind.annotation.XmlTransient;

import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.TagNotPresentException;


/**
 * Batch container for messages.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 27/04/2011
 */
@XmlTransient
public abstract class Batch extends Component {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">

    protected String ID;

    protected Header header;

    protected Integer totMsg;

    protected FIXMsg[] messages;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public Batch() {
        super();
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Public Methods">

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    public String getID() {
        return ID;
    }

    /**
     * Message field setter.
     * @param ID field value
     */
    @FIXVersion(introduced="4.4")
    public void setID(String ID) {
        this.ID = ID;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    public Header getHeader() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Sets the field to the proper class implementation.
     */
    @FIXVersion(introduced="4.4")
    public void setHeader() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Sets the field to null.
     */
    @FIXVersion(introduced="4.4")
    public void clearHeader() {
        this.header = null;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0")
    public Integer getTotMsg() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method sets the number of {@link FIXMsg} messages. It will also create an array
     * of {@link FIXMsg} objects and set the <code>messages</code> field with this array.
     * The created objects inside the array need to be populated with data for encoding.<br/>
     * If there where already objects in <code>messages</code> array they will be discarded.<br/>
     * @param totMsg field value
     */
    @FIXVersion(introduced="5.0")
    public void setTotMsg(Integer totMsg) {
        this.totMsg = totMsg;
        if (totMsg != null) {
            messages = new FIXMsg[totMsg.intValue()];
        }
    }

    /**
     * Message field getter for {@link FIXMsg} array of groups.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    public FIXMsg[] getMessages() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Setter for the messages.
     * @param messages
     */
    @FIXVersion(introduced="4.4")
    public void setMessages(FIXMsg[] messages) {
        this.messages = messages;
        if (messages != null) {
            totMsg = new Integer(messages.length);
        }
    }

    /**
     * This method adds a {@link FIXMsg} object to the existing array of <code>messages</code>
     * and expands the static array with 1 place.<br/>
     * This method will also update <code>totMsg</code> field to the proper value.<br/>
     * Note: If the <code>setTotMsg</code> method has been called there will already be a number of objects in the
     * <code>messages</code> array created.<br/>
     * @param message message to be added
     * @return newly created block and added to the array group object
     */
    @FIXVersion(introduced = "4.4")
    public FIXMsg addMessage(FIXMsg message) {
        List<FIXMsg> msgs = new ArrayList<FIXMsg>();
        if (messages != null && messages.length > 0) {
            msgs = new ArrayList<FIXMsg>(Arrays.asList(messages));
        }
        msgs.add(message);
        messages = msgs.toArray(new FIXMsg[msgs.size()]);
        totMsg = new Integer(messages.length);

        return message;
    }

    /**
     * This method deletes a {@link FIXMsg} object from the existing array of <code>messages</code>
     * and shrink the static array with 1 place.<br/>
     * If the array does not have the index position then a null object will be returned.)<br/>
     * This method will also update <code>totMsg</code> field to the proper value.<br/>
     * @param index position in array to be deleted starting at 0
     * @return deleted block object
     */
    @FIXVersion(introduced = "4.4")
    public FIXMsg deleteMessage(int index) {
        FIXMsg result = null;
        if (messages != null && messages.length > 0 && messages.length > index) {
            List<FIXMsg> msgs = new ArrayList<FIXMsg>(Arrays.asList(messages));
            result = msgs.remove(index);
            messages = msgs.toArray(new FIXMsg[msgs.size()]);
            if (messages.length > 0) {
                totMsg = new Integer(messages.length);
            } else {
                messages = null;
                totMsg = null;
            }
        }

        return result;
    }

    /**
     * Deletes all the {@link FIXMsg} objects from the <code>messages</code> array
     * (sets the array to 0 length)<br/>
     * This method will also update <code>totMsg</code> field and set it to null.<br/>
     * @return number of elements in array cleared
     */
    @FIXVersion(introduced = "4.4")
    public int clearMessages() {
        int result = 0;
        if (messages != null && messages.length > 0) {
            result = messages.length;
            messages = null;
            totMsg = null;
        }

        return result;
    }

    @Override
    public Set<Integer> getFragmentTags() {
        throw new UnsupportedOperationException("Supported only for FIXML messages.");
    }

    @Override
    public Set<Integer> getFragmentAllTags() {
        throw new UnsupportedOperationException("Supported only for FIXML messages.");
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

    @Override
    protected int getFirstTag() {
        throw new UnsupportedOperationException("Supported only for FIXML messages.");
    }

    @Override
    protected void validateRequiredTags() throws TagNotPresentException {
        throw new UnsupportedOperationException("Supported only for FIXML messages.");
    }

    @Override
    protected void setFragmentTagValue(Tag tag) throws BadFormatMsgException {
        throw new UnsupportedOperationException("Supported only for FIXML messages.");
    }

    @Override
    protected void setFragmentCompTagValue(Tag tag, ByteBuffer message) throws BadFormatMsgException, InvalidMsgException, TagNotPresentException {
        throw new UnsupportedOperationException("Supported only for FIXML messages.");
    }

    @Override
    protected ByteBuffer setFragmentDataTagValue(Tag tag, ByteBuffer message) throws BadFormatMsgException {
        throw new UnsupportedOperationException("Supported only for FIXML messages.");
    }

    @Override
    protected Set<Integer> getFragmentDataTags() {
        throw new UnsupportedOperationException("Supported only for FIXML messages.");
    }

    @Override
    protected Set<Integer> getFragmentCompTags() {
        throw new UnsupportedOperationException("Supported only for FIXML messages.");
    }

    @Override
    protected Set<Integer> getFragmentSecuredTags() {
        throw new UnsupportedOperationException("Supported only for FIXML messages.");
    }

    @Override
    protected byte[] encodeFragmentAll() throws TagNotPresentException, BadFormatMsgException {
        throw new UnsupportedOperationException("Supported only for FIXML messages.");
    }

    @Override
    protected byte[] encodeFragmentSecured(boolean secured) throws TagNotPresentException, BadFormatMsgException {
        throw new UnsupportedOperationException("Supported only for FIXML messages.");
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Package Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Private Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Inner Classes">
    // </editor-fold>
}
