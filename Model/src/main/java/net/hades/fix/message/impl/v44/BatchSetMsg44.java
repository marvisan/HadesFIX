/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * BatchSetMsg44.java
 *
 * $Id: BatchSetMsg44.java,v 1.1 2011-04-27 23:28:23 vrotaru Exp $
 */
package net.hades.fix.message.impl.v44;

import net.hades.fix.message.Header;
import net.hades.fix.message.comp.Batch;
import net.hades.fix.message.comp.impl.v44.Batch44;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.type.ApplVerID;
import net.hades.fix.message.type.BeginString;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.xml.bind.annotation.XmlTransient;

import net.hades.fix.message.BatchSetMsg;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.TagNotPresentException;

/**
 * FIX 4.4 implementation of the BatchSetMsg.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 27/04/2011
 */
@XmlTransient
public class BatchSetMsg44 extends BatchSetMsg {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public BatchSetMsg44() {
        super();
    }

    public BatchSetMsg44(Header header, ByteBuffer rawMsg)
    throws InvalidMsgException, TagNotPresentException, BadFormatMsgException {
        super(header, rawMsg);
    }

    public BatchSetMsg44(BeginString beginString) throws InvalidMsgException {
        super(beginString);
    }

    public BatchSetMsg44(BeginString beginString, ApplVerID applVerID) throws InvalidMsgException {
        super(beginString, applVerID);
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Public Methods">

    @Override
    public void setNoBatches(Integer noBatches) {
        this.noBatches = noBatches;
        if (noBatches != null) {
            batches = new Batch[noBatches.intValue()];
            for (int i = 0; i < batches.length; i++) {
                batches[i] = new Batch44();
            }
        }
    }

    @Override
    public Batch[] getBatches() {
        return batches;
    }

    @Override
    public void setBatches(Batch[] batches) {
        this.batches = batches;
        if (batches != null) {
            noBatches = new Integer(batches.length);
        }
    }

    @Override
    public Batch addBatch() {
        Batch batch = new Batch44();
        List<Batch> msgs = new ArrayList<Batch>();
        if (batches != null && batches.length > 0) {
            msgs = new ArrayList<Batch>(Arrays.asList(batches));
        }
        msgs.add(batch);
        batches = msgs.toArray(new Batch[msgs.size()]);
        noBatches = new Integer(batches.length);

        return batch;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

    @Override
    protected String getUnsupportedTagMessage() {
        return "This tag is not supported in [BatchSet] component version [4.4].";
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Package Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Private Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Inner Classes">
    // </editor-fold>
}
