/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * NewsRefGrp50SP2.java
 *
 * $Id: NewsRefGrp50SP2.java,v 1.7 2011-04-14 23:44:49 vrotaru Exp $
 */
package net.hades.fix.message.comp.impl.v50sp2;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.comp.NewsRefGrp;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.group.NewsRefGroup;
import net.hades.fix.message.group.impl.v50sp2.NewsRefGroup50SP2;
import net.hades.fix.message.struct.Tag;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.xml.bind.annotation.XmlTransient;

import net.hades.fix.message.exception.BadFormatMsgException;

/**
 * FIX 5.0SP2 implementation of NewsRefGrp component.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.7 $
 * @created 04/06/2009, 11:16:20 AM
 */
@XmlTransient
public class NewsRefGrp50SP2 extends NewsRefGrp {
    
    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> START_COMP_TAGS;
    
    protected static final Set<Integer> ALL_TAGS;
    
    protected static final Set<Integer> NEWS_REF_GROUP_TAGS = new NewsRefGroup50SP2().getFragmentAllTags();
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Static Block">
    
    static {
        ALL_TAGS = new HashSet<Integer>(TAGS);
        ALL_TAGS.addAll(NEWS_REF_GROUP_TAGS);
        START_COMP_TAGS = new HashSet<Integer>(NEWS_REF_GROUP_TAGS);
    }
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Attributes">
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Constructors">
    
    public NewsRefGrp50SP2() {
        super();
    }
    
    public NewsRefGrp50SP2(FragmentContext context) {
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
    
    @Override
    public Integer getNoNewsRefIDs() {
        return noNewsRefIDs;
    }
    
    @Override
    public void setNoNewsRefIDs(Integer noNewsRefIDs) {
        this.noNewsRefIDs = noNewsRefIDs;
        if (noNewsRefIDs != null) {
            newsRefGroups = new NewsRefGroup[noNewsRefIDs.intValue()];
            FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, validateRequired);
            for (int i = 0; i < newsRefGroups.length; i++) {
                newsRefGroups[i] = new NewsRefGroup50SP2(context);
            }
        }
    }
    
    @Override
    public NewsRefGroup[] getNewsRefGroups() {
        return newsRefGroups;
    }
    
    public void setNewsRefGroups(NewsRefGroup[] newsRefGroups) {
        this.newsRefGroups = newsRefGroups;
        if (newsRefGroups != null) {
            noNewsRefIDs = new Integer(newsRefGroups.length);
        }
    }

    @Override
    public NewsRefGroup addNewsRefGroup() {
        NewsRefGroup group = new NewsRefGroup50SP2(new FragmentContext(sessionCharset, messageEncoding, validateRequired));
        List<NewsRefGroup> groups = new ArrayList<NewsRefGroup>();
        if (newsRefGroups != null && newsRefGroups.length > 0) {
            groups = new ArrayList<NewsRefGroup>(Arrays.asList(newsRefGroups));
        }
        groups.add(group);
        newsRefGroups = groups.toArray(new NewsRefGroup[groups.size()]);
        noNewsRefIDs = new Integer(newsRefGroups.length);
        
        return group;
    }
    
    @Override
    public NewsRefGroup deleteNewsRefGroup(int index) {
        NewsRefGroup result = null;
        if (newsRefGroups != null && newsRefGroups.length > 0 && newsRefGroups.length > index) {
            List<NewsRefGroup> groups = new ArrayList<NewsRefGroup>(Arrays.asList(newsRefGroups));
            result = groups.remove(index);
            newsRefGroups = groups.toArray(new NewsRefGroup[groups.size()]);
            if (newsRefGroups.length > 0) {
                noNewsRefIDs = new Integer(newsRefGroups.length);
            } else {
                newsRefGroups = null;
                noNewsRefIDs = null;
            }
        }
        
        return result;
    }
    
    @Override
    public int clearNewsRefGroups() {
        int result = 0;
        if (newsRefGroups != null && newsRefGroups.length > 0) {
            result = newsRefGroups.length;
            newsRefGroups = null;
            noNewsRefIDs = null;
        }
        
        return result;
    }
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Protected Methods">
    
    @Override
    protected void setFragmentCompTagValue(Tag tag, ByteBuffer message)
        throws BadFormatMsgException, InvalidMsgException, TagNotPresentException {
        if (NEWS_REF_GROUP_TAGS.contains(tag.tagNum)) {
            if (noNewsRefIDs != null && noNewsRefIDs.intValue() > 0) {
                FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, validateRequired);
                message.reset();
                newsRefGroups = new NewsRefGroup[noNewsRefIDs.intValue()];
                for (int i = 0; i < noNewsRefIDs.intValue(); i++) {
                    NewsRefGroup component = new NewsRefGroup50SP2(context);
                    component.decode(message);
                    newsRefGroups[i] = component;
                }
            }
        }
    }
    
    @Override
    protected Set<Integer> getFragmentCompTags() {
        return START_COMP_TAGS;
    }
    
    @Override
    protected String getUnsupportedTagMessage() {
        return "This tag is not supported in [NewsRefGrp] group version [5.0SP2].";
    }
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Package Methods">
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Private Methods">
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Inner Classes">
    // </editor-fold>
}
