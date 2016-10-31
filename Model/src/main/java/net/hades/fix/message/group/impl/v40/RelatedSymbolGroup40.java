/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * RelatedSymbolGroup40.java
 *
 * $Id: RelatedSymbolGroup40.java,v 1.5 2010-02-03 09:19:09 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v40;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.group.RelatedSymbolGroup;
import net.hades.fix.message.struct.Tag;
import net.hades.fix.message.type.SessionRejectReason;
import net.hades.fix.message.type.TagNum;

/**
 * FIX 4.0 implementation of RelatedSymbolGroup group.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.5 $
 * @created 25/03/2009, 8:17:25 PM
 */
public class RelatedSymbolGroup40 extends RelatedSymbolGroup {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = -6580927435865932245L;

    protected static final Set<Integer> TAGS_V40 = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.RelatdSym.getValue()
    }));

    protected static final Set<Integer> START_COMP_TAGS = null;

    protected static final Set<Integer> ALL_TAGS;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">

    static {
        ALL_TAGS = new HashSet<Integer>(TAGS_V40);
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">

    protected Set<Integer> STANDARD_SECURED_TAGS = TAGS_V40;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public RelatedSymbolGroup40() {
        SECURED_TAGS = STANDARD_SECURED_TAGS;
    }

    public RelatedSymbolGroup40(FragmentContext context) {
        super(context);
        SECURED_TAGS = STANDARD_SECURED_TAGS;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Public Methods">

    @Override
    public Set<Integer> getFragmentTags() {
        return TAGS_V40;
    }

    @Override
    public Set<Integer> getFragmentAllTags() {
        return ALL_TAGS;
    }

    // ACCESSORS
    //////////////////////////////////////////

    @Override
    public String getRelatedSym() {
        return relatedSym;
    }

    @Override
    public void setRelatedSym(String relatedSym) {
        this.relatedSym = relatedSym;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">
    
    @Override
    protected void setFragmentTagValue(Tag tag) throws BadFormatMsgException {
        TagNum tagNum = TagNum.fromString(tag.tagNum);
        switch (tagNum) {
            case RelatdSym:
                relatedSym = new String(tag.value, sessionCharset);
                break;
                
            default:
                String error = "Tag value [" + tag.tagNum + "] not present in [RelatedSymbolGroup] fields.";
                LOGGER.severe(error);
                throw new BadFormatMsgException(SessionRejectReason.InvalidTagNumber, tag.tagNum, error);
        }
    }

    @Override
    protected String getUnsupportedTagMessage() {
        return "This tag is not supported in [RelatedSymbolGroup] group version [4.0].";
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
