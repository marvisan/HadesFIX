/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * BatchSetMsg.java
 *
 * $Id: BatchSetMsg.java,v 1.1 2011-04-27 23:28:24 vrotaru Exp $
 */
package net.hades.fix.message;

import net.hades.fix.message.anno.FIXVersion;
import net.hades.fix.message.comp.Batch;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.struct.Tag;
import net.hades.fix.message.type.ApplVerID;
import net.hades.fix.message.type.BeginString;
import net.hades.fix.message.type.MsgType;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

/**
 * Container with batches that will be serialized as XML. This class CANNOT be serialized as a FIX message.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 27/04/2011
 */
@XmlAccessorType(XmlAccessType.NONE)
public class BatchSetMsg extends FIXMsg {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">

    protected Integer noBatches;

    protected Batch[] batches;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public BatchSetMsg() {
        super();
    }

    public BatchSetMsg(Header header, ByteBuffer rawMsg)
    throws InvalidMsgException, TagNotPresentException, BadFormatMsgException {
        super(header, rawMsg);
    }

    public BatchSetMsg(BeginString beginString) throws InvalidMsgException {
        super(MsgType.BatchSet.getValue(), beginString);
    }

    public BatchSetMsg(BeginString beginString, ApplVerID applVerID) throws InvalidMsgException {
        super(MsgType.BatchSet.getValue(), beginString, applVerID);
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Public Methods">

    /**
     * Message field getter.
     * @return field value
     */
    public Integer getNoBatches() {
        return noBatches;
    }

    /**
     * This method sets the number of {@link Batch} batches. It will also create an array
     * of {@link Batch} objects and set the <code>batches</code> field with this array.
     * The created objects inside the array need to be populated with data for encoding.<br/>
     * If there where already objects in <code>batches</code> array they will be discarded.<br/>
     * @param noBatches field value
     */
    public void setNoBatches(Integer noBatches) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter for {@link Batch} array of groups.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    public Batch[] getBatches() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Setter for the batches.
     * @param batches
     */
    @FIXVersion(introduced="4.4")
    public void setBatches(Batch[] batches) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method adds a {@link Batch} object to the existing array of <code>batches</code>
     * and expands the static array with 1 place.<br/>
     * This method will also update <code>noBatches</code> field to the proper value.<br/>
     * Note: If the <code>setnoBatches</code> method has been called there will already be a number of objects in the
     * <code>batches</code> array created.<br/>
     * @return newly created block and added to the array group object
     */
    @FIXVersion(introduced = "4.4")
    public Batch addBatch() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method deletes a {@link Batch} object from the existing array of <code>batches</code>
     * and shrink the static array with 1 place.<br/>
     * If the array does not have the index position then a null object will be returned.)<br/>
     * This method will also update <code>noBatches</code> field to the proper value.<br/>
     * @param index position in array to be deleted starting at 0
     * @return deleted block object
     */
    @FIXVersion(introduced = "4.4")
    public Batch deleteBatch(int index) {
        Batch result = null;
        if (batches != null && batches.length > 0 && batches.length > index) {
            List<Batch> msgs = new ArrayList<Batch>(Arrays.asList(batches));
            result = msgs.remove(index);
            batches = msgs.toArray(new Batch[msgs.size()]);
            if (batches.length > 0) {
                noBatches = new Integer(batches.length);
            } else {
                batches = null;
                noBatches = null;
            }
        }

        return result;
    }

    /**
     * Deletes all the {@link Batch} objects from the <code>batches</code> array
     * (sets the array to 0 length)<br/>
     * This method will also update <code>noBatches</code> field and set it to null.<br/>
     * @return number of elements in array cleared
     */
    @FIXVersion(introduced = "4.4")
    public int clearBatches() {
        int result = 0;
        if (batches != null && batches.length > 0) {
            result = batches.length;
            batches = null;
            noBatches = null;
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
    protected void validateRequiredTags() throws TagNotPresentException {
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
