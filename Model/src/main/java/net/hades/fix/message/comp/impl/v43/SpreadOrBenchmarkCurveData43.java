/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * SpreadOrBenchmarkCurveData43.java
 *
 * $Id: SpreadOrBenchmarkCurveData43.java,v 1.8 2011-04-16 07:38:25 vrotaru Exp $
 */
package net.hades.fix.message.comp.impl.v43;

import net.hades.fix.message.comp.SpreadOrBenchmarkCurveData;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Set;
import java.util.logging.Level;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.type.TagNum;
import net.hades.fix.message.util.MsgUtil;
import net.hades.fix.message.util.TagEncoder;

/**
 * FIX version 4.3 SpreadOrBenchmarkCurveData component data implementation.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.8 $
 * @created 16/02/2009, 7:27:35 PM
 */
public class SpreadOrBenchmarkCurveData43 extends SpreadOrBenchmarkCurveData {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">

    protected Set<Integer> STANDARD_SECURED_TAGS = TAGS;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public SpreadOrBenchmarkCurveData43() {
        super();
        SECURED_TAGS = STANDARD_SECURED_TAGS;
    }

    public SpreadOrBenchmarkCurveData43(FragmentContext context) {
        super(context);
        SECURED_TAGS = STANDARD_SECURED_TAGS;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Public Methods">

    // ACCESSOR METHODS
    //////////////////////////////////////////

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

    @Override
    protected byte[] encodeFragmentSecured(boolean secured) throws TagNotPresentException, BadFormatMsgException {
        byte[] result = new byte[0];
        ByteArrayOutputStream bao = new ByteArrayOutputStream();

        try {
            if (MsgUtil.isTagInList(TagNum.Spread, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.Spread, spread);
            }
            if (benchmarkCurveCurrency != null && MsgUtil.isTagInList(TagNum.BenchmarkCurveCurrency, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.BenchmarkCurveCurrency, benchmarkCurveCurrency.getValue());
            }
            if (benchmarkCurveName != null && MsgUtil.isTagInList(TagNum.BenchmarkCurveName, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.BenchmarkCurveName, benchmarkCurveName.getValue());
            }
            if (MsgUtil.isTagInList(TagNum.BenchmarkCurvePoint, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.BenchmarkCurvePoint, benchmarkCurvePoint);
            }
            result = bao.toByteArray();
        } catch (IOException ex) {
            String error = "Error writing to the byte array.";
            LOGGER.log(Level.SEVERE, "{0} Error was : {1}", new Object[] { error, ex.toString() });
            throw new BadFormatMsgException(error, ex);
        }

        return result;
    }

    @Override
    protected String getUnsupportedTagMessage() {
        return "This tag is not supported in [SpreadOrBenchmarkCurveData] component version [4.3].";
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Package Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Private Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Inner Classes">
    // </editor-fold>
}
