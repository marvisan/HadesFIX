
/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * HeaderT11_50SP2.java
 *
 * $Id: HeaderT11_50SP2.java,v 1.12 2011-04-14 23:44:29 vrotaru Exp $
 */
package net.hades.fix.message.impl.v50sp2;

import net.hades.fix.message.struct.Tag;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import net.hades.fix.message.FIXMsg;
import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.group.HopsGroup;
import net.hades.fix.message.group.impl.v50sp2.HopsGroup50SP2;
import net.hades.fix.message.impl.fixt11.HeaderFIXT11;
import net.hades.fix.message.type.ApplVerID;

/**
 * Standard header for FIX version FIXT 1.1 and application version 5.0SP1.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.12 $
 * @created 12/08/2008, 20:25:51
 */
@XmlRootElement(name="Hdr")
@XmlType(propOrder={"hopsGroups"})
@XmlAccessorType(XmlAccessType.NONE)
public class HeaderT11_50SP2 extends HeaderFIXT11 {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public HeaderT11_50SP2() {
        super();
        applVerID = ApplVerID.FIX50SP2;
    }
    
    public HeaderT11_50SP2(FragmentContext context) {
        super(context);
        applVerID = ApplVerID.FIX50SP2;
        messageEncoding = Charset.forName(FIXMsg.DEFAULT_CHARACTER_SET);
    }
    
    public HeaderT11_50SP2(String msgType, FragmentContext context) {
        super(msgType, context);
        applVerID = ApplVerID.FIX50SP2;
        messageEncoding = Charset.forName(FIXMsg.DEFAULT_CHARACTER_SET);
    }
    
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Public Methods">

    // ACCESSOR METHODS
    //////////////////////////////////////////

    @Override
    public void setNoHops(Integer noEvents) {
        this.noHops = noEvents;
        if (noHops != null) {
            hopsGroups = new HopsGroup[noHops.intValue()];
            for (int i = 0; i < hopsGroups.length; i++) {
                hopsGroups[i] = new HopsGroup50SP2(new FragmentContext(sessionCharset, messageEncoding, validateRequired));
            }
        }
    }

    @Override
    public HopsGroup addHopsGroup() {

        HopsGroup group = new HopsGroup50SP2(new FragmentContext(sessionCharset, messageEncoding, validateRequired));
        List<HopsGroup> groups = new ArrayList<HopsGroup>();
        if (hopsGroups != null && hopsGroups.length > 0) {
            groups = new ArrayList<HopsGroup>(Arrays.asList(hopsGroups));
        }
        groups.add(group);
        hopsGroups = groups.toArray(new HopsGroup[groups.size()]);
        noHops = new Integer(hopsGroups.length);

        return group;
    }

    @XmlElementRef
    @Override
    public HopsGroup[] getHopsGroups() {
        return hopsGroups;
    }

    @Override
    public void setHopsGroups(HopsGroup[] hopsGroups) {
        this.hopsGroups = hopsGroups;
        if (hopsGroups != null) {
            noHops = new Integer(hopsGroups.length);
        }
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

    @Override
    protected void setFragmentCompTagValue(Tag tag, ByteBuffer message)
    throws BadFormatMsgException, InvalidMsgException, TagNotPresentException {
        if (noHops != null && noHops.intValue() > 0) {
            if (HOPS_GROUP_TAGS.contains(tag.tagNum)) {
                message.reset();
                hopsGroups = new HopsGroup[noHops.intValue()];
                FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, validateRequired);
                for (int i = 0; i < noHops.intValue(); i++) {
                    HopsGroup group = new HopsGroup50SP2(context);
                    group.decode(message);
                    hopsGroups[i] = group;
                }
            }
        }
    }

    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Package Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Private Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Inner Classes">
    // </editor-fold>
}
