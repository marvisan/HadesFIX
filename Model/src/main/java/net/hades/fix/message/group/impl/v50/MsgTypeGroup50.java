/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * MsgTypeGroup50.java
 *
 * $Id: MsgTypeGroup50.java,v 1.1 2010-03-31 11:05:18 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v50;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.type.ApplVerID;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import net.hades.fix.message.group.MsgTypeGroup;
import net.hades.fix.message.type.MsgDirection;
import net.hades.fix.message.type.TagNum;

/**
 * MsgTypeGroup implementation for FIX 5.0 version.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 01/11/2008, 5:00:09 PM
 */
public class MsgTypeGroup50 extends MsgTypeGroup {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = -3534597495593882063L;
    
    protected static final Set<Integer> TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.RefMsgType.getValue(),
        TagNum.MsgDirection.getValue(),
        TagNum.RefApplVerID.getValue(),
        TagNum.RefCstmApplVerID.getValue()
    }));
    
    protected static final Set<Integer> START_DATA_TAGS = null;

    protected static final Set<Integer> START_COMP_TAGS = null;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">
    
    protected Set<Integer> STANDARD_SECURED_TAGS = TAGS;
    
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">
    
    public MsgTypeGroup50() {
        SECURED_TAGS = STANDARD_SECURED_TAGS;
    }
    
    public MsgTypeGroup50(FragmentContext context) {
        super(context);
        SECURED_TAGS = STANDARD_SECURED_TAGS;
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

    @Override
    public String getRefMsgType() {
        return refMsgType;
    }

    @Override
    public void setRefMsgType(String msgType) {
        this.refMsgType = msgType;
    }

    @Override
    public MsgDirection getMsgDirection() {
        return msgDirection;
    }

    @Override
    public void setMsgDirection(MsgDirection msgDirection) {
        this.msgDirection = msgDirection;
    }

    @Override
    public ApplVerID getRefApplVerID() {
        return refApplVerID;
    }

    @Override
    public void setRefApplVerID(ApplVerID refApplVerID) {
        this.refApplVerID = refApplVerID;
    }

    @Override
    public String getRefCstmApplVerID() {
        return refCstmApplVerID;
    }

    @Override
    public void setRefCstmApplVerID(String refCstmApplVerID) {
        this.refCstmApplVerID = refCstmApplVerID;
    }
    
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

    @Override
    protected String getUnsupportedTagMessage() {
        return "This tag is not supported in [MsgTypeGroup] group version [5.0].";
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
}
