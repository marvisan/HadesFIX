/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * NewsRefGroup50SP2.java
 *
 * $Id: NewsRefGroup50SP2.java,v 1.5 2010-02-04 10:11:05 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v50sp2;

import net.hades.fix.message.group.NewsRefGroup;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.type.TagNum;

/**
 * FIX 4.4 implementation of PartyGroup group.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.5 $
 * @created 12/02/2009, 7:22:35 PM
 */
@XmlRootElement(name="Refs")
@XmlType
@XmlAccessorType(XmlAccessType.NONE)
public class NewsRefGroup50SP2 extends NewsRefGroup {
    
    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = -5058931037178589872L;

    protected static final Set<Integer> TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.NewsRefID.getValue(),
        TagNum.NewsRefType.getValue()
    }));
    
    protected static final Set<Integer> ALL_TAGS;

    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Static Block">
    
    static {
        ALL_TAGS = new HashSet<Integer>(TAGS);
    }
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Attributes">
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Constructors">
    
    public NewsRefGroup50SP2() {
    }
    
    public NewsRefGroup50SP2(FragmentContext context) {
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
        return ALL_TAGS;
    }

    // ACCESSOR METHODS
    //////////////////////////////////////////
    
    @XmlAttribute(name = "RefID")
    @Override
    public String getNewsRefID() {
        return newsRefID;
    }

    @Override
    public void setNewsRefID(String newsRefID) {
        this.newsRefID = newsRefID;
    }

    @XmlAttribute(name = "RefTyp")
    @Override
    public Integer getNewsRefType() {
        return newsRefType;
    }

    @Override
    public void setNewsRefType(Integer newsRefType) {
        this.newsRefType = newsRefType;
    }
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Protected Methods">
    
    @Override
    protected String getUnsupportedTagMessage() {
        return "This tag is not supported in [NewsRefGroup] group version [5.0SP2].";
    }
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Package Methods">
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Private Methods">
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Inner Classes">
    // </editor-fold>
}
