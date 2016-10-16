/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * HopsGroupFIXT11.java
 *
 * $Id: HopsGroupFIXT11.java,v 1.3 2010-02-25 08:37:46 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.fixt11;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.group.HopsGroup;
import net.hades.fix.message.xml.codec.jaxb.adapter.FixUTCDateTimeAdapter;

import java.util.Date;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * FIXT1.1 implementation of HopsGroup group.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.3 $
 * @created 02/01/2009, 1:51:59 PM
 */
@XmlType
@XmlAccessorType(XmlAccessType.NONE)
public class HopsGroupFIXT11 extends HopsGroup {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public HopsGroupFIXT11() {
    }

    public HopsGroupFIXT11(FragmentContext context) {
        super(context);
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Public Methods">


    @XmlAttribute(name="ID")
    @Override
    public String getHopCompID() {
        return hopCompID;
    }

    @Override
    public void setHopCompID(String hopCompID) {
        this.hopCompID = hopCompID;
    }

    @XmlAttribute(name="Ref")
    @Override
    public Integer getHopRefID() {
        return hopRefID;
    }

    @Override
    public void setHopRefID(Integer hopRefID) {
        this.hopRefID = hopRefID;
    }

    @XmlAttribute(name="Snt")
    @XmlJavaTypeAdapter(FixUTCDateTimeAdapter.class)
    @Override
    public Date getHopSendingTime() {
        return hopSendingTime;
    }

    @Override
    public void setHopSendingTime(Date hopSendingTime) {
        this.hopSendingTime = hopSendingTime;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

    @Override
    protected String getUnsupportedTagMessage() {
        return "This tag is not supported in [HopsGroup] group version [T1.1].";
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Package Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Private Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Inner Classes">
    // </editor-fold>
}
