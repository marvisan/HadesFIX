/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * Component.java
 *
 * $Id: Component.java,v 1.10 2011-02-02 10:03:16 vrotaru Exp $
 */
package net.hades.fix.message.comp;

import net.hades.fix.message.FIXFragment;
import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.struct.Tag;
import net.hades.fix.message.util.MsgUtil;

import java.nio.ByteBuffer;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import net.hades.fix.message.exception.BadFormatMsgException;

/**
 * Defines the interface to be implemented by a FIX component block.
 * 
 * @author vrotaru
 * @version $Revision: 1.10 $
 * @created Sep 22, 2008, 2:23:23 PM
 */
@XmlAccessorType(XmlAccessType.NONE)
public abstract class Component extends FIXFragment {
        
    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public Component() {
    }
    
    public Component(FragmentContext context) {
        super(context);
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Public Methods">

    /**
     * This method decodes a group or repeating component ina FIX message. Note that non repeating component
     * do not have to follow any order in message according with the standard. As a result a component tags
     * might get intermingled with other message body tags.
     * @param message FIX message with the position set as the first field in the group
     * or repeating component
     * @throws net.hades.fix.message.exception.InvalidMsgException message is invalid
     * @throws net.hades.fix.message.exception.BadFormatMsgException bad format message
     * @throws net.hades.fix.message.exception.TagNotPresentException tag missing from the message
     */
    public void decode(ByteBuffer message) throws InvalidMsgException, BadFormatMsgException, TagNotPresentException {
        boolean firstTagDecoded = false;
        int firstTag = getFirstTag();
        while (message.hasRemaining()) {
            message.mark();
            Tag tag = MsgUtil.getNextTag(message);
            if (MsgUtil.isTagInList(tag.tagNum, getFragmentAllTags())) {
                if (tag.tagNum == firstTag) {
                    if (firstTagDecoded) {
                        message.reset();
                        break;
                    } else {
                        firstTagDecoded = true;
                    }
                }
            } else {
                message.reset();
                break;
            }
            decode(tag, message);
        }
        if (validateRequired) {
            validateRequiredTags();
        }
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

    /**
     * Returns the first tag in a repeating component/group.
     * @return first tag in a component
     */
    protected abstract int getFirstTag();

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Package Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Private Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Inner Classes">
    // </editor-fold>
}
