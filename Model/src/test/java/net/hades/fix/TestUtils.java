/*
 *   Copyright (c) 2006-2008 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * TestUtils.java
 *
 * $Id: TestUtils.java,v 1.5 2011-04-27 23:28:24 vrotaru Exp $
 */
package net.hades.fix;

import java.nio.charset.Charset;
import java.util.Calendar;
import java.util.TimeZone;

import net.hades.fix.message.FIXMsg;
import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.Header;
import net.hades.fix.message.config.SessionContext;
import net.hades.fix.message.config.SessionContextKey;
import net.hades.fix.message.config.ThreadData;

/**
 * Utility class for test suite.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.5 $
 * @created 19/07/2008, 10:50:56
 */
public class TestUtils {

    //////////////////////////////////////////
    // CONSTANTS
    //////////////////////////////////////////
    
    public static final String QUICK_FIX_HOST = "localhost";
    public static final int QUICK_FIX_PORT = 9878;
    public static final String DEFAULT_CHARACTER_SET = "ISO-8859-1";
    
        
    private static final FragmentContext TEST_FRAGMENT_CONTEXT = new FragmentContext(Charset.forName(DEFAULT_CHARACTER_SET), 
            Charset.forName(DEFAULT_CHARACTER_SET));
    
    public static int calculateChecksum(String msgBody) {

        byte[] body = msgBody.getBytes();
        int sum = 0;
        for (int i = 0; i < body.length; i++) {
            sum += (int) body[i];
        }

        return sum % 256;
    }

    public static void enableValidation() {
        SessionContext ctx = ThreadData.getSessionContext();
        ctx.setValue(SessionContextKey.VALIDATE_REQUIRED, Boolean.TRUE);
    }

    public static void populate40HeaderAll(FIXMsg msg) {
        msg.getHeader().setOnBehalfOfCompID("Company on behalf name");
        msg.getHeader().setDeliverToCompID("Deliver to this company");
        msg.getHeader().setSenderSubID("Sender sub identifier");
        msg.getHeader().setTargetSubID("Target sub identifier");
        msg.getHeader().setOnBehalfOfSubID("On behalf of sub identifier");
        msg.getHeader().setDeliverToSubID("Deliver to this company");
        msg.getHeader().setPossDupFlag(Boolean.TRUE);
        msg.getHeader().setPossResend(Boolean.FALSE);
        msg.getHeader().setSenderCompID("Marvisan");
        msg.getHeader().setTargetCompID("IB");
        msg.getHeader().setMsgSeqNum(1);
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_MONTH, 23);
        cal.set(Calendar.MONTH, 6);
        cal.set(Calendar.YEAR, 2008);
        cal.set(Calendar.HOUR_OF_DAY, 21);
        cal.set(Calendar.MINUTE, 45);
        cal.set(Calendar.SECOND, 15);
        cal.set(Calendar.MILLISECOND, 0);
        msg.getHeader().setSendingTime(cal.getTime());
        cal.add(Calendar.HOUR_OF_DAY, -2);
        msg.getHeader().setOrigSendingTime(cal.getTime());
        msg.getTrailer().setSignatureLength(new Integer(8));
        msg.getTrailer().setSignature(new byte[] {(byte) 10, (byte) 12, (byte) 14, (byte) 16, 
            (byte) 234, (byte) 236, (byte) 238, (byte) 240});
    }
    
    public static void populate41HeaderAll(FIXMsg msg) {
        msg.getHeader().setOnBehalfOfCompID("Company on behalf name");
        msg.getHeader().setDeliverToCompID("Deliver to this company");
        msg.getHeader().setSenderSubID("Sender sub identifier");
        msg.getHeader().setSenderLocationID("Sender location identifier");
        msg.getHeader().setTargetSubID("Target sub identifier");
        msg.getHeader().setOnBehalfOfSubID("On behalf of sub identifier");
        msg.getHeader().setOnBehalfOfLocationID("On behalf of location identifier");
        msg.getHeader().setDeliverToSubID("Deliver to this company");
        msg.getHeader().setDeliverToLocationID("Deliver to this location");
        msg.getHeader().setTargetLocationID("Target location ID");
        msg.getHeader().setPossDupFlag(Boolean.TRUE);
        msg.getHeader().setPossResend(Boolean.FALSE);
        msg.getHeader().setSenderCompID("Marvisan");
        msg.getHeader().setTargetCompID("IB");
        msg.getHeader().setMsgSeqNum(1);
        
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_MONTH, 23);
        cal.set(Calendar.MONTH, 6);
        cal.set(Calendar.YEAR, 2008);
        cal.set(Calendar.HOUR_OF_DAY, 21);
        cal.set(Calendar.MINUTE, 45);
        cal.set(Calendar.SECOND, 15);
        cal.set(Calendar.MILLISECOND, 0);
        msg.getHeader().setSendingTime(cal.getTime());
        cal.add(Calendar.HOUR_OF_DAY, -2);
        msg.getHeader().setOrigSendingTime(cal.getTime());
        msg.getTrailer().setSignatureLength(new Integer(8));
        msg.getTrailer().setSignature(new byte[] {(byte) 10, (byte) 12, (byte) 14, (byte) 16, 
            (byte) 234, (byte) 236, (byte) 238, (byte) 240});
    }
    
    public static void populate42HeaderAll(FIXMsg msg) {
        msg.getHeader().setOnBehalfOfCompID("Company on behalf name");
        msg.getHeader().setDeliverToCompID("Deliver to this company");
        msg.getHeader().setSenderSubID("Sender sub identifier");
        msg.getHeader().setSenderLocationID("Sender location identifier");
        msg.getHeader().setTargetSubID("Target sub identifier");
        msg.getHeader().setTargetLocationID("Target location identifier");
        msg.getHeader().setOnBehalfOfSubID("On behalf of sub identifier");
        msg.getHeader().setOnBehalfOfLocationID("On behalf of location identifier");
        msg.getHeader().setDeliverToSubID("Deliver to this company");
        msg.getHeader().setDeliverToLocationID("Deliver to this location");
        msg.getHeader().setPossDupFlag(Boolean.TRUE);
        msg.getHeader().setPossResend(Boolean.FALSE);
        msg.getHeader().setSenderCompID("Marvisan");
        msg.getHeader().setTargetCompID("IB");
        msg.getHeader().setMsgSeqNum(10);
        msg.getHeader().setMessageEncoding(Charset.forName("UTF-8"));
        String xmlData = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><doc></doc>";
        msg.getHeader().setXmlData(xmlData.getBytes(Charset.forName(TestUtils.DEFAULT_CHARACTER_SET)));
        msg.getHeader().setXmlDataLen(new Integer(msg.getHeader().getXmlData().length));
        msg.getHeader().setLastMsgSeqNumProcessed(new Integer(9));
        
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_MONTH, 23);
        cal.set(Calendar.MONTH, 6);
        cal.set(Calendar.YEAR, 2008);
        cal.set(Calendar.HOUR_OF_DAY, 21);
        cal.set(Calendar.MINUTE, 45);
        cal.set(Calendar.SECOND, 15);
        cal.set(Calendar.MILLISECOND, 0);
        msg.getHeader().setSendingTime(cal.getTime());
        cal.add(Calendar.HOUR_OF_DAY, -2);
        msg.getHeader().setOrigSendingTime(cal.getTime());
        cal.add(Calendar.HOUR_OF_DAY, -1);
        msg.getHeader().setOnBehalfOfSendingTime(cal.getTime());
        msg.getTrailer().setSignatureLength(new Integer(8));
        msg.getTrailer().setSignature(new byte[] {(byte) 10, (byte) 12, (byte) 14, (byte) 16, 
            (byte) 234, (byte) 236, (byte) 238, (byte) 240});
    }
    
    public static void populate43HeaderAll(FIXMsg msg) {
        msg.getHeader().setOnBehalfOfCompID("Company on behalf name");
        msg.getHeader().setDeliverToCompID("Deliver to this company");
        msg.getHeader().setSenderSubID("Sender sub identifier");
        msg.getHeader().setSenderLocationID("Sender location identifier");
        msg.getHeader().setTargetSubID("Target sub identifier");
        msg.getHeader().setTargetLocationID("Target location identifier");
        msg.getHeader().setOnBehalfOfSubID("On behalf of sub identifier");
        msg.getHeader().setOnBehalfOfLocationID("On behalf of location identifier");
        msg.getHeader().setDeliverToSubID("Deliver to this company");
        msg.getHeader().setDeliverToLocationID("Deliver to this location");
        msg.getHeader().setPossDupFlag(Boolean.TRUE);
        msg.getHeader().setPossResend(Boolean.FALSE);
        msg.getHeader().setSenderCompID("Marvisan");
        msg.getHeader().setTargetCompID("IB");
        msg.getHeader().setMsgSeqNum(10);
        msg.getHeader().setMessageEncoding(Charset.forName("UTF-8"));
        String xmlData = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><doc></doc>";
        msg.getHeader().setXmlData(xmlData.getBytes(Charset.forName(TestUtils.DEFAULT_CHARACTER_SET)));
        msg.getHeader().setXmlDataLen(new Integer(msg.getHeader().getXmlData().length));        msg.getHeader().setLastMsgSeqNumProcessed(new Integer(9));
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_MONTH, 23);
        cal.set(Calendar.MONTH, 6);
        cal.set(Calendar.YEAR, 2008);
        cal.set(Calendar.HOUR_OF_DAY, 21);
        cal.set(Calendar.MINUTE, 45);
        cal.set(Calendar.SECOND, 15);
        cal.set(Calendar.MILLISECOND, 0);
        msg.getHeader().setSendingTime(cal.getTime());
        cal.add(Calendar.HOUR_OF_DAY, -2);
        msg.getHeader().setOrigSendingTime(cal.getTime());
        cal.add(Calendar.HOUR_OF_DAY, -1);
        msg.getHeader().setOnBehalfOfSendingTime(cal.getTime());
        msg.getHeader().setNoHops(2);
        msg.getHeader().getHopsGroups()[0].setHopCompID("Hop No 1");
        msg.getHeader().getHopsGroups()[0].setHopSendingTime(cal.getTime());
        msg.getHeader().getHopsGroups()[0].setHopRefID(3);
        cal.add(Calendar.HOUR_OF_DAY, -1);
        msg.getHeader().getHopsGroups()[1].setHopCompID("Hop No 2");
        msg.getHeader().getHopsGroups()[1].setHopSendingTime(cal.getTime());
        msg.getHeader().getHopsGroups()[1].setHopRefID(4);
        
        msg.getTrailer().setSignatureLength(new Integer(8));
        msg.getTrailer().setSignature(new byte[] {(byte) 10, (byte) 12, (byte) 14, (byte) 16, 
            (byte) 234, (byte) 236, (byte) 238, (byte) 240});
    }
    
    public static void populate44HeaderAll(FIXMsg msg) {
        msg.getHeader().setOnBehalfOfCompID("Company on behalf name");
        msg.getHeader().setDeliverToCompID("Deliver to this company");
        msg.getHeader().setSenderSubID("Sender sub identifier");
        msg.getHeader().setSenderLocationID("Sender location identifier");
        msg.getHeader().setTargetSubID("Target sub identifier");
        msg.getHeader().setTargetLocationID("Target location identifier");
        msg.getHeader().setOnBehalfOfSubID("On behalf of sub identifier");
        msg.getHeader().setOnBehalfOfLocationID("On behalf of location identifier");
        msg.getHeader().setDeliverToSubID("Deliver to this company");
        msg.getHeader().setDeliverToLocationID("Deliver to this location");
        msg.getHeader().setPossDupFlag(Boolean.TRUE);
        msg.getHeader().setPossResend(Boolean.FALSE);
        msg.getHeader().setSenderCompID("Marvisan");
        msg.getHeader().setTargetCompID("IB");
        msg.getHeader().setMsgSeqNum(10);
        msg.getHeader().setMessageEncoding(Charset.forName("UTF-8"));
        String xmlData = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><doc></doc>";
        msg.getHeader().setXmlData(xmlData.getBytes(Charset.forName(TestUtils.DEFAULT_CHARACTER_SET)));
        msg.getHeader().setXmlDataLen(new Integer(msg.getHeader().getXmlData().length));        msg.getHeader().setLastMsgSeqNumProcessed(new Integer(9));
        msg.getHeader().setLastMsgSeqNumProcessed(new Integer(9));
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_MONTH, 23);
        cal.set(Calendar.MONTH, 6);
        cal.set(Calendar.YEAR, 2008);
        cal.set(Calendar.HOUR_OF_DAY, 21);
        cal.set(Calendar.MINUTE, 45);
        cal.set(Calendar.SECOND, 15);
        cal.set(Calendar.MILLISECOND, 0);
        msg.getHeader().setSendingTime(cal.getTime());
        cal.add(Calendar.HOUR_OF_DAY, -2);
        msg.getHeader().setOrigSendingTime(cal.getTime());
        msg.getHeader().setNoHops(1);
        msg.getHeader().getHopsGroups()[0].setHopCompID("Hop No 1");
        msg.getHeader().getHopsGroups()[0].setHopSendingTime(cal.getTime());
        msg.getHeader().getHopsGroups()[0].setHopRefID(3);
        cal.add(Calendar.HOUR_OF_DAY, -1);
        msg.getHeader().addHopsGroup();
        msg.getHeader().getHopsGroups()[1].setHopCompID("Hop No 2");
        msg.getHeader().getHopsGroups()[1].setHopSendingTime(cal.getTime());
        msg.getHeader().getHopsGroups()[1].setHopRefID(4);

        msg.getTrailer().setSignatureLength(new Integer(8));
        msg.getTrailer().setSignature(new byte[] {(byte) 10, (byte) 12, (byte) 14, (byte) 16, 
            (byte) 234, (byte) 236, (byte) 238, (byte) 240});
    }

    public static void populate44BatcHeader(Header header) {
        header.setSenderCompID("Marvisan");
        header.setTargetCompID("IB");
        header.setOnBehalfOfCompID("Company on behalf name");
        header.setDeliverToCompID("Deliver to this company");
        header.setSenderSubID("Sender sub identifier");
        header.setSenderLocationID("Sender location identifier");
        header.setTargetSubID("Target sub identifier");
        header.setTargetLocationID("Target location identifier");
        header.setOnBehalfOfSubID("On behalf of sub identifier");
        header.setOnBehalfOfLocationID("On behalf of location identifier");
        header.setDeliverToSubID("Deliver to this company");
        header.setDeliverToLocationID("Deliver to this location");
        header.setPossDupFlag(Boolean.FALSE);
        header.setPossResend(Boolean.FALSE);;
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_MONTH, 23);
        cal.set(Calendar.MONTH, 6);
        cal.set(Calendar.YEAR, 2008);
        cal.set(Calendar.HOUR_OF_DAY, 21);
        cal.set(Calendar.MINUTE, 45);
        cal.set(Calendar.SECOND, 15);
        cal.set(Calendar.MILLISECOND, 0);
        header.setSendingTime(cal.getTime());
        cal.add(Calendar.HOUR_OF_DAY, -2);
        header.setOrigSendingTime(cal.getTime());
        header.setMessageEncoding(Charset.forName("UTF-8"));
        header.setNoHops(1);
        header.getHopsGroups()[0].setHopCompID("Hop No 1");
        header.getHopsGroups()[0].setHopSendingTime(cal.getTime());
        header.getHopsGroups()[0].setHopRefID(3);
        cal.add(Calendar.HOUR_OF_DAY, -1);
        header.addHopsGroup();
        header.getHopsGroups()[1].setHopCompID("Hop No 2");
        header.getHopsGroups()[1].setHopSendingTime(cal.getTime());
        header.getHopsGroups()[1].setHopRefID(4);
    }

    public static void populate50HeaderAll(FIXMsg msg) {
        msg.getHeader().setCstmApplVerID("Custom version 2");
        msg.getHeader().setOnBehalfOfCompID("Company on behalf name");
        msg.getHeader().setDeliverToCompID("Deliver to this company");
        msg.getHeader().setSenderSubID("Sender sub identifier");
        msg.getHeader().setSenderLocationID("Sender location identifier");
        msg.getHeader().setTargetSubID("Target sub identifier");
        msg.getHeader().setTargetLocationID("Target location identifier");
        msg.getHeader().setOnBehalfOfSubID("On behalf of sub identifier");
        msg.getHeader().setOnBehalfOfLocationID("On behalf of location identifier");
        msg.getHeader().setDeliverToSubID("Deliver to this company");
        msg.getHeader().setDeliverToLocationID("Deliver to this location");
        msg.getHeader().setPossDupFlag(Boolean.TRUE);
        msg.getHeader().setPossResend(Boolean.FALSE);
        msg.getHeader().setSenderCompID("Marvisan");
        msg.getHeader().setTargetCompID("IB");
        msg.getHeader().setMsgSeqNum(10);
        msg.getHeader().setMessageEncoding(Charset.forName("UTF-8"));
        String xmlData = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><doc>Test</doc>";
        msg.getHeader().setXmlData(xmlData.getBytes(Charset.forName(TestUtils.DEFAULT_CHARACTER_SET)));
        msg.getHeader().setXmlDataLen(new Integer(msg.getHeader().getXmlData().length));
        msg.getHeader().setLastMsgSeqNumProcessed(new Integer(9));
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_MONTH, 23);
        cal.set(Calendar.MONTH, 6);
        cal.set(Calendar.YEAR, 2008);
        cal.set(Calendar.HOUR_OF_DAY, 21);
        cal.set(Calendar.MINUTE, 45);
        cal.set(Calendar.SECOND, 15);
        cal.set(Calendar.MILLISECOND, 0);
        msg.getHeader().setSendingTime(cal.getTime());
        cal.add(Calendar.HOUR_OF_DAY, -2);
        msg.getHeader().setOrigSendingTime(cal.getTime());
        msg.getHeader().setNoHops(2);
        msg.getHeader().getHopsGroups()[0].setHopCompID("Hop No 1");
        msg.getHeader().getHopsGroups()[0].setHopSendingTime(cal.getTime());
        msg.getHeader().getHopsGroups()[0].setHopRefID(3);
        msg.getHeader().getHopsGroups()[1].setHopCompID("Hop No Changed");
        msg.getHeader().getHopsGroups()[1].setHopSendingTime(cal.getTime());
        msg.getHeader().getHopsGroups()[1].setHopRefID(222);
        msg.getHeader().deleteHopsGroup(1);
        cal.add(Calendar.HOUR_OF_DAY, -1);
        msg.getHeader().addHopsGroup();
        msg.getHeader().getHopsGroups()[1].setHopCompID("Hop No 2");
        msg.getHeader().getHopsGroups()[1].setHopSendingTime(cal.getTime());
        msg.getHeader().getHopsGroups()[1].setHopRefID(4);

        msg.getTrailer().setSignatureLength(new Integer(8));
        msg.getTrailer().setSignature(new byte[] {(byte) 10, (byte) 12, (byte) 14, (byte) 16,
            (byte) 234, (byte) 236, (byte) 238, (byte) 240});
    }
    
    public static void populateFIXT11HeaderAll(FIXMsg msg) {
        msg.getHeader().setCstmApplVerID("Custom version 2");
        msg.getHeader().setOnBehalfOfCompID("Company on behalf name");
        msg.getHeader().setDeliverToCompID("Deliver to this company");
        msg.getHeader().setSenderSubID("Sender sub identifier");
        msg.getHeader().setSenderLocationID("Sender location identifier");
        msg.getHeader().setTargetSubID("Target sub identifier");
        msg.getHeader().setTargetLocationID("Target location identifier");
        msg.getHeader().setOnBehalfOfSubID("On behalf of sub identifier");
        msg.getHeader().setOnBehalfOfLocationID("On behalf of location identifier");
        msg.getHeader().setDeliverToSubID("Deliver to this company");
        msg.getHeader().setDeliverToLocationID("Deliver to this location");
        msg.getHeader().setPossDupFlag(Boolean.TRUE);
        msg.getHeader().setPossResend(Boolean.FALSE);
        msg.getHeader().setSenderCompID("Marvisan");
        msg.getHeader().setTargetCompID("IB");
        msg.getHeader().setMsgSeqNum(10);
        msg.getHeader().setMessageEncoding(Charset.forName("UTF-8"));
        String xmlData = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><doc>Test</doc>";
        msg.getHeader().setXmlData(xmlData.getBytes(Charset.forName(TestUtils.DEFAULT_CHARACTER_SET)));
        msg.getHeader().setXmlDataLen(new Integer(msg.getHeader().getXmlData().length));
        msg.getHeader().setLastMsgSeqNumProcessed(new Integer(9));
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_MONTH, 23);
        cal.set(Calendar.MONTH, 6);
        cal.set(Calendar.YEAR, 2008);
        cal.set(Calendar.HOUR_OF_DAY, 21);
        cal.set(Calendar.MINUTE, 45);
        cal.set(Calendar.SECOND, 15);
        cal.set(Calendar.MILLISECOND, 0);
        msg.getHeader().setSendingTime(cal.getTime());
        cal.add(Calendar.HOUR_OF_DAY, -2);
        msg.getHeader().setOrigSendingTime(cal.getTime());
        msg.getHeader().setNoHops(4);
        msg.getHeader().clearHopsGroups();
        msg.getHeader().setNoHops(2);
        msg.getHeader().getHopsGroups()[0].setHopCompID("Hop No 1");
        msg.getHeader().getHopsGroups()[0].setHopSendingTime(cal.getTime());
        msg.getHeader().getHopsGroups()[0].setHopRefID(3);
        msg.getHeader().getHopsGroups()[1].setHopCompID("Hop No Changed");
        msg.getHeader().getHopsGroups()[1].setHopSendingTime(cal.getTime());
        msg.getHeader().getHopsGroups()[1].setHopRefID(222);
        msg.getHeader().deleteHopsGroup(1);
        cal.add(Calendar.HOUR_OF_DAY, -1);
        msg.getHeader().addHopsGroup();
        msg.getHeader().getHopsGroups()[1].setHopCompID("Hop No 2");
        msg.getHeader().getHopsGroups()[1].setHopSendingTime(cal.getTime());
        msg.getHeader().getHopsGroups()[1].setHopRefID(4);
        
        msg.getTrailer().setSignatureLength(new Integer(8));
        msg.getTrailer().setSignature(new byte[] {(byte) 10, (byte) 12, (byte) 14, (byte) 16, 
            (byte) 234, (byte) 236, (byte) 238, (byte) 240});
    }

    public static void populateHeaderReq(FIXMsg msg) {
        msg.getHeader().setSenderCompID("Marvisan");
        msg.getHeader().setTargetCompID("IB");
        msg.getHeader().setMsgSeqNum(1);
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_MONTH, 23);
        cal.set(Calendar.MONTH, 6);
        cal.set(Calendar.YEAR, 2008);
        cal.set(Calendar.HOUR_OF_DAY, 21);
        cal.set(Calendar.MINUTE, 45);
        cal.set(Calendar.SECOND, 15);
        cal.set(Calendar.MILLISECOND, 0);
        msg.getHeader().setSendingTime(cal.getTime());
    }
    
    public static void populateQuicFIX40HeaderReq(quickfix.fix40.Message msg) {
        msg.getHeader().setString(quickfix.field.SenderCompID.FIELD, "Teleton");
        msg.getHeader().setString(quickfix.field.TargetCompID.FIELD, "BI");
        msg.getHeader().setInt(quickfix.field.MsgSeqNum.FIELD, 2);
        Calendar cal = Calendar.getInstance();
        cal.setTimeZone(TimeZone.getTimeZone("UTC"));
        cal.set(Calendar.DAY_OF_MONTH, 20);
        cal.set(Calendar.MONTH, 5);
        cal.set(Calendar.YEAR, 2008);
        cal.set(Calendar.HOUR_OF_DAY, 18);
        cal.set(Calendar.MINUTE, 40);
        cal.set(Calendar.SECOND, 13);
        cal.set(Calendar.MILLISECOND, 0);
        msg.getHeader().setUtcTimeStamp(quickfix.field.SendingTime.FIELD, cal.getTime());
    }
        
    public static void populateQuicFIX41HeaderReq(quickfix.fix41.Message msg) {
        msg.getHeader().setString(quickfix.field.SenderCompID.FIELD, "Teleton");
        msg.getHeader().setString(quickfix.field.TargetCompID.FIELD, "BI");
        msg.getHeader().setInt(quickfix.field.MsgSeqNum.FIELD, 2);
        Calendar cal = Calendar.getInstance();
        cal.setTimeZone(TimeZone.getTimeZone("UTC"));
        cal.set(Calendar.DAY_OF_MONTH, 20);
        cal.set(Calendar.MONTH, 5);
        cal.set(Calendar.YEAR, 2008);
        cal.set(Calendar.HOUR_OF_DAY, 18);
        cal.set(Calendar.MINUTE, 40);
        cal.set(Calendar.SECOND, 13);
        cal.set(Calendar.MILLISECOND, 0);
        msg.getHeader().setUtcTimeStamp(quickfix.field.SendingTime.FIELD, cal.getTime());
    }

    public static void populateQuicFIX42HeaderReq(quickfix.fix42.Message msg) {
        msg.getHeader().setString(quickfix.field.SenderCompID.FIELD, "Teleton");
        msg.getHeader().setString(quickfix.field.TargetCompID.FIELD, "BI");
        msg.getHeader().setInt(quickfix.field.MsgSeqNum.FIELD, 2);
        Calendar cal = Calendar.getInstance();
        cal.setTimeZone(TimeZone.getTimeZone("UTC"));
        cal.set(Calendar.DAY_OF_MONTH, 20);
        cal.set(Calendar.MONTH, 5);
        cal.set(Calendar.YEAR, 2008);
        cal.set(Calendar.HOUR_OF_DAY, 18);
        cal.set(Calendar.MINUTE, 40);
        cal.set(Calendar.SECOND, 13);
        cal.set(Calendar.MILLISECOND, 0);
        msg.getHeader().setUtcTimeStamp(quickfix.field.SendingTime.FIELD, cal.getTime());
    }
    
    public static void populateQuickFIX43HeaderReq(quickfix.fix43.Message msg) {
        msg.getHeader().setString(quickfix.field.SenderCompID.FIELD, "Teleton");
        msg.getHeader().setString(quickfix.field.TargetCompID.FIELD, "BI");
        msg.getHeader().setInt(quickfix.field.MsgSeqNum.FIELD, 2);
        Calendar cal = Calendar.getInstance();
        cal.setTimeZone(TimeZone.getTimeZone("UTC"));
        cal.set(Calendar.DAY_OF_MONTH, 20);
        cal.set(Calendar.MONTH, 5);
        cal.set(Calendar.YEAR, 2008);
        cal.set(Calendar.HOUR_OF_DAY, 18);
        cal.set(Calendar.MINUTE, 40);
        cal.set(Calendar.SECOND, 13);
        cal.set(Calendar.MILLISECOND, 0);
        msg.getHeader().setUtcTimeStamp(quickfix.field.SendingTime.FIELD, cal.getTime());
    }
    
    public static void populateQuickFIX44HeaderReq(quickfix.fix44.Message msg) {
        msg.getHeader().setString(quickfix.field.SenderCompID.FIELD, "Teleton");
        msg.getHeader().setString(quickfix.field.TargetCompID.FIELD, "BI");
        msg.getHeader().setInt(quickfix.field.MsgSeqNum.FIELD, 2);
        Calendar cal = Calendar.getInstance();
        cal.setTimeZone(TimeZone.getTimeZone("UTC"));
        cal.set(Calendar.DAY_OF_MONTH, 20);
        cal.set(Calendar.MONTH, 5);
        cal.set(Calendar.YEAR, 2008);
        cal.set(Calendar.HOUR_OF_DAY, 18);
        cal.set(Calendar.MINUTE, 40);
        cal.set(Calendar.SECOND, 13);
        cal.set(Calendar.MILLISECOND, 0);
        msg.getHeader().setUtcTimeStamp(quickfix.field.SendingTime.FIELD, cal.getTime());
    }

    public static void populateQuickFIX40HeaderAll(quickfix.fix40.Message msg) {
        msg.getHeader().setString(quickfix.field.SenderCompID.FIELD, "Teleton");
        msg.getHeader().setString(quickfix.field.TargetCompID.FIELD, "BI");
        msg.getHeader().setInt(quickfix.field.MsgSeqNum.FIELD, 2);
        Calendar cal = Calendar.getInstance();
        cal.setTimeZone(TimeZone.getTimeZone("UTC"));
        cal.set(Calendar.DAY_OF_MONTH, 20);
        cal.set(Calendar.MONTH, 5);
        cal.set(Calendar.YEAR, 2008);
        cal.set(Calendar.HOUR_OF_DAY, 18);
        cal.set(Calendar.MINUTE, 40);
        cal.set(Calendar.SECOND, 13);
        cal.set(Calendar.MILLISECOND, 0);
        msg.getHeader().setUtcTimeStamp(quickfix.field.SendingTime.FIELD, cal.getTime());
        msg.getHeader().setString(quickfix.field.OnBehalfOfCompID.FIELD, "Company on behalf name");
        msg.getHeader().setString(quickfix.field.DeliverToCompID.FIELD, "Deliver to this company");
        msg.getHeader().setString(quickfix.field.SenderSubID.FIELD, "Sender sub identifier");
        msg.getHeader().setString(quickfix.field.TargetSubID.FIELD, "Target sub identifier");
        msg.getHeader().setString(quickfix.field.OnBehalfOfSubID.FIELD, "On behalf of sub identifier");
        msg.getHeader().setString(quickfix.field.DeliverToSubID.FIELD, "Deliver to this company");
        msg.getHeader().setBoolean(quickfix.field.PossDupFlag.FIELD, true);
        msg.getHeader().setBoolean(quickfix.field.PossResend.FIELD, false);
        cal.add(Calendar.HOUR_OF_DAY, -2);
        msg.getHeader().setUtcTimeStamp(quickfix.field.OrigSendingTime.FIELD, cal.getTime());
    }
    
    public static void populateQuickFIX41HeaderAll(quickfix.fix41.Message msg) {
        msg.getHeader().setString(quickfix.field.SenderCompID.FIELD, "Teleton");
        msg.getHeader().setString(quickfix.field.TargetCompID.FIELD, "BI");
        msg.getHeader().setInt(quickfix.field.MsgSeqNum.FIELD, 2);
        Calendar cal = Calendar.getInstance();
        cal.setTimeZone(TimeZone.getTimeZone("UTC"));
        cal.set(Calendar.DAY_OF_MONTH, 20);
        cal.set(Calendar.MONTH, 5);
        cal.set(Calendar.YEAR, 2008);
        cal.set(Calendar.HOUR_OF_DAY, 18);
        cal.set(Calendar.MINUTE, 40);
        cal.set(Calendar.SECOND, 13);
        cal.set(Calendar.MILLISECOND, 0);
        msg.getHeader().setUtcTimeStamp(quickfix.field.SendingTime.FIELD, cal.getTime());
        msg.getHeader().setString(quickfix.field.OnBehalfOfCompID.FIELD, "Company on behalf name");
        msg.getHeader().setString(quickfix.field.DeliverToCompID.FIELD, "Deliver to this company");
        msg.getHeader().setString(quickfix.field.SenderSubID.FIELD, "Sender sub identifier");
        msg.getHeader().setString(quickfix.field.SenderLocationID.FIELD, "Sender location identifier");
        msg.getHeader().setString(quickfix.field.TargetSubID.FIELD, "Target sub identifier");
        msg.getHeader().setString(quickfix.field.OnBehalfOfSubID.FIELD, "On behalf of sub identifier");
        msg.getHeader().setString(quickfix.field.OnBehalfOfLocationID.FIELD, "On behalf of location identifier");
        msg.getHeader().setString(quickfix.field.DeliverToSubID.FIELD, "Deliver to this company");
        msg.getHeader().setString(quickfix.field.DeliverToLocationID.FIELD, "Deliver to this company location");
        msg.getHeader().setString(quickfix.field.TargetLocationID.FIELD, "Target location ID");
        msg.getHeader().setBoolean(quickfix.field.PossDupFlag.FIELD, true);
        msg.getHeader().setBoolean(quickfix.field.PossResend.FIELD, false);
        cal.add(Calendar.HOUR_OF_DAY, -2);
        msg.getHeader().setUtcTimeStamp(quickfix.field.OrigSendingTime.FIELD, cal.getTime());
    }
    
    public static void populateQuickFIX42HeaderAll(quickfix.fix42.Message msg) {
        msg.getHeader().setString(quickfix.field.SenderCompID.FIELD, "Teleton");
        msg.getHeader().setString(quickfix.field.TargetCompID.FIELD, "BI");
        msg.getHeader().setInt(quickfix.field.MsgSeqNum.FIELD, 2);
        Calendar cal = Calendar.getInstance();
        cal.setTimeZone(TimeZone.getTimeZone("UTC"));
        cal.set(Calendar.DAY_OF_MONTH, 20);
        cal.set(Calendar.MONTH, 5);
        cal.set(Calendar.YEAR, 2008);
        cal.set(Calendar.HOUR_OF_DAY, 18);
        cal.set(Calendar.MINUTE, 40);
        cal.set(Calendar.SECOND, 13);
        cal.set(Calendar.MILLISECOND, 0);
        msg.getHeader().setUtcTimeStamp(quickfix.field.SendingTime.FIELD, cal.getTime());
        msg.getHeader().setString(quickfix.field.OnBehalfOfCompID.FIELD, "Company on behalf name");
        msg.getHeader().setString(quickfix.field.DeliverToCompID.FIELD, "Deliver to this company");
        msg.getHeader().setString(quickfix.field.SenderSubID.FIELD, "Sender sub identifier");
        msg.getHeader().setString(quickfix.field.SenderLocationID.FIELD, "Sender location identifier");
        msg.getHeader().setString(quickfix.field.TargetSubID.FIELD, "Target sub identifier");
        msg.getHeader().setString(quickfix.field.TargetLocationID.FIELD, "Target location identifier");
        msg.getHeader().setString(quickfix.field.OnBehalfOfSubID.FIELD, "On behalf of sub identifier");
        msg.getHeader().setString(quickfix.field.OnBehalfOfLocationID.FIELD, "On behalf of location identifier");
        msg.getHeader().setString(quickfix.field.DeliverToSubID.FIELD, "Deliver to this company");
        msg.getHeader().setString(quickfix.field.DeliverToLocationID.FIELD, "Deliver to this company location");
        msg.getHeader().setBoolean(quickfix.field.PossDupFlag.FIELD, true);
        msg.getHeader().setBoolean(quickfix.field.PossResend.FIELD, false);
        msg.getHeader().setString(quickfix.field.MessageEncoding.FIELD, "UTF-8");
        String xmlData = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><doc></doc>";
        msg.getHeader().setString(quickfix.field.XmlData.FIELD, "<?xml version=\"1.0\" encoding=\"UTF-8\"?><doc></doc>");
        msg.getHeader().setInt(quickfix.field.XmlDataLen.FIELD, xmlData.getBytes(Charset.forName(TestUtils.DEFAULT_CHARACTER_SET)).length);
        msg.getHeader().setInt(quickfix.field.LastMsgSeqNumProcessed.FIELD, 1);
        cal.add(Calendar.HOUR_OF_DAY, -2);
        msg.getHeader().setUtcTimeStamp(quickfix.field.OrigSendingTime.FIELD, cal.getTime());
        cal.add(Calendar.HOUR_OF_DAY, -1);
        msg.getHeader().setUtcTimeStamp(quickfix.field.OnBehalfOfSendingTime.FIELD, cal.getTime());
    }
    
    public static void populateQuickFIX43HeaderAll(quickfix.fix43.Message msg) {
        msg.getHeader().setString(quickfix.field.SenderCompID.FIELD, "Teleton");
        msg.getHeader().setString(quickfix.field.TargetCompID.FIELD, "BI");
        msg.getHeader().setInt(quickfix.field.MsgSeqNum.FIELD, 2);
        Calendar cal = Calendar.getInstance();
        cal.setTimeZone(TimeZone.getTimeZone("UTC"));
        cal.set(Calendar.DAY_OF_MONTH, 20);
        cal.set(Calendar.MONTH, 5);
        cal.set(Calendar.YEAR, 2008);
        cal.set(Calendar.HOUR_OF_DAY, 18);
        cal.set(Calendar.MINUTE, 40);
        cal.set(Calendar.SECOND, 13);
        cal.set(Calendar.MILLISECOND, 0);
        msg.getHeader().setUtcTimeStamp(quickfix.field.SendingTime.FIELD, cal.getTime());
        msg.getHeader().setString(quickfix.field.OnBehalfOfCompID.FIELD, "Company on behalf name");
        msg.getHeader().setString(quickfix.field.DeliverToCompID.FIELD, "Deliver to this company");
        msg.getHeader().setString(quickfix.field.SenderSubID.FIELD, "Sender sub identifier");
        msg.getHeader().setString(quickfix.field.SenderLocationID.FIELD, "Sender location identifier");
        msg.getHeader().setString(quickfix.field.TargetSubID.FIELD, "Target sub identifier");
        msg.getHeader().setString(quickfix.field.TargetLocationID.FIELD, "Target location identifier");
        msg.getHeader().setString(quickfix.field.OnBehalfOfSubID.FIELD, "On behalf of sub identifier");
        msg.getHeader().setString(quickfix.field.OnBehalfOfLocationID.FIELD, "On behalf of location identifier");
        msg.getHeader().setString(quickfix.field.DeliverToSubID.FIELD, "Deliver to this company");
        msg.getHeader().setString(quickfix.field.DeliverToLocationID.FIELD, "Deliver to this company location");
        msg.getHeader().setBoolean(quickfix.field.PossDupFlag.FIELD, true);
        msg.getHeader().setBoolean(quickfix.field.PossResend.FIELD, false);
        msg.getHeader().setString(quickfix.field.MessageEncoding.FIELD, "UTF-8");
        String xmlData = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><doc></doc>";
        msg.getHeader().setString(quickfix.field.XmlData.FIELD, "<?xml version=\"1.0\" encoding=\"UTF-8\"?><doc></doc>");
        msg.getHeader().setInt(quickfix.field.XmlDataLen.FIELD, xmlData.getBytes(Charset.forName(TestUtils.DEFAULT_CHARACTER_SET)).length);
        msg.getHeader().setInt(quickfix.field.LastMsgSeqNumProcessed.FIELD, 1);
        cal.add(Calendar.HOUR_OF_DAY, -2);
        msg.getHeader().setUtcTimeStamp(quickfix.field.OrigSendingTime.FIELD, cal.getTime());
        cal.add(Calendar.HOUR_OF_DAY, -1);
        msg.getHeader().setUtcTimeStamp(quickfix.field.OnBehalfOfSendingTime.FIELD, cal.getTime());
        msg.getHeader().setInt(quickfix.field.NoHops.FIELD, 2);
        quickfix.fix43.Message.Header.NoHops hop1 = new quickfix.fix43.Message.Header.NoHops();
        hop1.setString(quickfix.field.HopCompID.FIELD, "Hop Comp ID 1");
        hop1.setUtcTimeStamp(quickfix.field.HopSendingTime.FIELD, cal.getTime());
        hop1.setInt(quickfix.field.HopRefID.FIELD, 6);
        quickfix.fix43.Message.Header.NoHops hop2 = new quickfix.fix43.Message.Header.NoHops();
        hop2.setString(quickfix.field.HopCompID.FIELD, "Hop Comp ID 2");
        cal.add(Calendar.HOUR_OF_DAY, -1);
        hop2.setUtcTimeStamp(quickfix.field.HopSendingTime.FIELD, cal.getTime());
        hop2.setInt(quickfix.field.HopRefID.FIELD, 8);
        msg.getHeader().addGroup(hop1);
        msg.getHeader().addGroup(hop2);
    }
    
    public static void populateQuickFIX44HeaderAll(quickfix.fix44.Message msg) {
        msg.getHeader().setString(quickfix.field.SenderCompID.FIELD, "Teleton");
        msg.getHeader().setString(quickfix.field.TargetCompID.FIELD, "BI");
        msg.getHeader().setInt(quickfix.field.MsgSeqNum.FIELD, 2);
        Calendar cal = Calendar.getInstance();
        cal.setTimeZone(TimeZone.getTimeZone("UTC"));
        cal.set(Calendar.DAY_OF_MONTH, 20);
        cal.set(Calendar.MONTH, 5);
        cal.set(Calendar.YEAR, 2008);
        cal.set(Calendar.HOUR_OF_DAY, 18);
        cal.set(Calendar.MINUTE, 40);
        cal.set(Calendar.SECOND, 13);
        cal.set(Calendar.MILLISECOND, 0);
        msg.getHeader().setUtcTimeStamp(quickfix.field.SendingTime.FIELD, cal.getTime());
        msg.getHeader().setString(quickfix.field.OnBehalfOfCompID.FIELD, "Company on behalf name");
        msg.getHeader().setString(quickfix.field.DeliverToCompID.FIELD, "Deliver to this company");
        msg.getHeader().setString(quickfix.field.SenderSubID.FIELD, "Sender sub identifier");
        msg.getHeader().setString(quickfix.field.SenderLocationID.FIELD, "Sender location identifier");
        msg.getHeader().setString(quickfix.field.TargetSubID.FIELD, "Target sub identifier");
        msg.getHeader().setString(quickfix.field.TargetLocationID.FIELD, "Target location identifier");
        msg.getHeader().setString(quickfix.field.OnBehalfOfSubID.FIELD, "On behalf of sub identifier");
        msg.getHeader().setString(quickfix.field.OnBehalfOfLocationID.FIELD, "On behalf of location identifier");
        msg.getHeader().setString(quickfix.field.DeliverToSubID.FIELD, "Deliver to this company");
        msg.getHeader().setString(quickfix.field.DeliverToLocationID.FIELD, "Deliver to this company location");
        msg.getHeader().setBoolean(quickfix.field.PossDupFlag.FIELD, true);
        msg.getHeader().setBoolean(quickfix.field.PossResend.FIELD, false);
        msg.getHeader().setString(quickfix.field.MessageEncoding.FIELD, "UTF-8");
        String xmlData = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><doc></doc>";
        msg.getHeader().setString(quickfix.field.XmlData.FIELD, "<?xml version=\"1.0\" encoding=\"UTF-8\"?><doc></doc>");
        msg.getHeader().setInt(quickfix.field.XmlDataLen.FIELD, xmlData.getBytes(Charset.forName(TestUtils.DEFAULT_CHARACTER_SET)).length);
        msg.getHeader().setInt(quickfix.field.LastMsgSeqNumProcessed.FIELD, 1);
        cal.add(Calendar.HOUR_OF_DAY, -2);
        msg.getHeader().setUtcTimeStamp(quickfix.field.OrigSendingTime.FIELD, cal.getTime());
        msg.getHeader().setInt(quickfix.field.NoHops.FIELD, 2);
        quickfix.fix43.Message.Header.NoHops hop1 = new quickfix.fix43.Message.Header.NoHops();
        hop1.setString(quickfix.field.HopCompID.FIELD, "Hop Comp ID 1");
        hop1.setUtcTimeStamp(quickfix.field.HopSendingTime.FIELD, cal.getTime());
        hop1.setInt(quickfix.field.HopRefID.FIELD, 6);
        quickfix.fix43.Message.Header.NoHops hop2 = new quickfix.fix43.Message.Header.NoHops();
        hop2.setString(quickfix.field.HopCompID.FIELD, "Hop Comp ID 2");
        cal.add(Calendar.HOUR_OF_DAY, -1);
        hop2.setUtcTimeStamp(quickfix.field.HopSendingTime.FIELD, cal.getTime());
        hop2.setInt(quickfix.field.HopRefID.FIELD, 8);
        msg.getHeader().addGroup(hop1);
        msg.getHeader().addGroup(hop2);
    }

    public static void populateQuickFIX50HeaderAll(quickfix.fix50.Message msg) {
        msg.getHeader().setString(quickfix.field.SenderCompID.FIELD, "Teleton");
        msg.getHeader().setString(quickfix.field.TargetCompID.FIELD, "BI");
        msg.getHeader().setInt(quickfix.field.MsgSeqNum.FIELD, 2);
        Calendar cal = Calendar.getInstance();
        cal.setTimeZone(TimeZone.getTimeZone("UTC"));
        cal.set(Calendar.DAY_OF_MONTH, 20);
        cal.set(Calendar.MONTH, 5);
        cal.set(Calendar.YEAR, 2008);
        cal.set(Calendar.HOUR_OF_DAY, 18);
        cal.set(Calendar.MINUTE, 40);
        cal.set(Calendar.SECOND, 13);
        cal.set(Calendar.MILLISECOND, 0);
        msg.getHeader().setUtcTimeStamp(quickfix.field.SendingTime.FIELD, cal.getTime());
        msg.getHeader().setString(quickfix.field.OnBehalfOfCompID.FIELD, "Company on behalf name");
        msg.getHeader().setString(quickfix.field.DeliverToCompID.FIELD, "Deliver to this company");
        msg.getHeader().setString(quickfix.field.SenderSubID.FIELD, "Sender sub identifier");
        msg.getHeader().setString(quickfix.field.SenderLocationID.FIELD, "Sender location identifier");
        msg.getHeader().setString(quickfix.field.TargetSubID.FIELD, "Target sub identifier");
        msg.getHeader().setString(quickfix.field.TargetLocationID.FIELD, "Target location identifier");
        msg.getHeader().setString(quickfix.field.OnBehalfOfSubID.FIELD, "On behalf of sub identifier");
        msg.getHeader().setString(quickfix.field.OnBehalfOfLocationID.FIELD, "On behalf of location identifier");
        msg.getHeader().setString(quickfix.field.DeliverToSubID.FIELD, "Deliver to this company");
        msg.getHeader().setString(quickfix.field.DeliverToLocationID.FIELD, "Deliver to this company location");
        msg.getHeader().setBoolean(quickfix.field.PossDupFlag.FIELD, true);
        msg.getHeader().setBoolean(quickfix.field.PossResend.FIELD, false);
        msg.getHeader().setString(quickfix.field.MessageEncoding.FIELD, "UTF-8");
        String xmlData = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><doc></doc>";
        msg.getHeader().setString(quickfix.field.XmlData.FIELD, "<?xml version=\"1.0\" encoding=\"UTF-8\"?><doc></doc>");
        msg.getHeader().setInt(quickfix.field.XmlDataLen.FIELD, xmlData.getBytes(Charset.forName(TestUtils.DEFAULT_CHARACTER_SET)).length);
        msg.getHeader().setInt(quickfix.field.LastMsgSeqNumProcessed.FIELD, 1);
        cal.add(Calendar.HOUR_OF_DAY, -2);
        msg.getHeader().setUtcTimeStamp(quickfix.field.OrigSendingTime.FIELD, cal.getTime());
        msg.getHeader().setInt(quickfix.field.NoHops.FIELD, 2);
        quickfix.fix43.Message.Header.NoHops hop1 = new quickfix.fix43.Message.Header.NoHops();
        hop1.setString(quickfix.field.HopCompID.FIELD, "Hop Comp ID 1");
        hop1.setUtcTimeStamp(quickfix.field.HopSendingTime.FIELD, cal.getTime());
        hop1.setInt(quickfix.field.HopRefID.FIELD, 6);
        quickfix.fix43.Message.Header.NoHops hop2 = new quickfix.fix43.Message.Header.NoHops();
        hop2.setString(quickfix.field.HopCompID.FIELD, "Hop Comp ID 2");
        cal.add(Calendar.HOUR_OF_DAY, -1);
        hop2.setUtcTimeStamp(quickfix.field.HopSendingTime.FIELD, cal.getTime());
        hop2.setInt(quickfix.field.HopRefID.FIELD, 8);
        msg.getHeader().addGroup(hop1);
        msg.getHeader().addGroup(hop2);
    }
}
