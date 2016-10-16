/*
 *   Copyright (c) 2006-2008 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * AdvertisementMsgFIXML43Test.java
 *
 * $Id: AdvertisementMsgFIXML43Test.java,v 1.2 2010-10-04 05:15:28 vrotaru Exp $
 */
package net.hades.fix.message.impl.v43.fixml;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import net.hades.fix.message.AdvertisementMsg;
import net.hades.fix.message.MsgTest;
import net.hades.fix.message.XMLValidationResult;
import net.hades.fix.message.builder.FIXMsgBuilder;
import net.hades.fix.message.impl.v43.data.AdvertisementMsg43TestData;
import net.hades.fix.message.type.BeginString;
import net.hades.fix.message.type.MsgType;
import net.hades.fix.message.type.SecurityType;

/**
 * Test suite for AdvertismentMsg43 class FIXML implementation.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 * @created 20/10/2008, 20:57:03
 */
public class AdvertisementMsgFIXML43Test extends MsgTest {

    public AdvertisementMsgFIXML43Test() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of encode method, of class AdvertisementMsg for FIXML 4.3.
     * @throws Exception
     */
    @Test
    public void testMarshallUnmarshallFixmlMutualFund() throws Exception {
        System.out.println("-->testMarshallUnmarshallFixmlMutualFund");
        setPrintableValidatingFixml();
        try {
            AdvertisementMsg msg = (AdvertisementMsg) FIXMsgBuilder.build(MsgType.Advertisement.getValue(), BeginString.FIX_4_3);
            AdvertisementMsg43TestData.getInstance().populate(msg, SecurityType.MutualFund);
            String fixml = msg.toFixml();
            System.out.println("fixml-->" + fixml);
            XMLValidationResult result = validateXMLAgainstXSD(fixml, FIXML_SCHEMA_V43);
            if (result.hasErrors()) {
                System.out.println("\nERRORS:\n");
                System.out.println(result.getFatals());
                System.out.println(result.getErrors());
            }
            System.out.println(result.getWarnings());
            assertFalse(result.hasErrors());

            AdvertisementMsg dmsg = (AdvertisementMsg) FIXMsgBuilder.build(MsgType.Advertisement.getValue(), BeginString.FIX_4_3);
            dmsg.fromFixml(fixml);
            AdvertisementMsg43TestData.getInstance().check(msg, dmsg, SecurityType.MutualFund);
        } finally {
            unsetPrintableFixml();
        }
    }

    /**
     * Test of encode method, of class AdvertisementMsg for FIXML 4.3.
     * @throws Exception
     */
    @Test
    public void testMarshallUnmarshallFixmlOption() throws Exception {
        System.out.println("-->testMarshallUnmarshallFixmlOption");
        setPrintableValidatingFixml();
        try {
            AdvertisementMsg msg = (AdvertisementMsg) FIXMsgBuilder.build(MsgType.Advertisement.getValue(), BeginString.FIX_4_3);
            AdvertisementMsg43TestData.getInstance().populate(msg, SecurityType.Option);
            String fixml = msg.toFixml();
            System.out.println("fixml-->" + fixml);
            XMLValidationResult result = validateXMLAgainstXSD(fixml, FIXML_SCHEMA_V43);
            if (result.hasErrors()) {
                System.out.println("\nERRORS:\n");
                System.out.println(result.getFatals());
                System.out.println(result.getErrors());
            }
            System.out.println(result.getWarnings());
            assertFalse(result.hasErrors());

            AdvertisementMsg dmsg = (AdvertisementMsg) FIXMsgBuilder.build(MsgType.Advertisement.getValue(), BeginString.FIX_4_3);
            dmsg.fromFixml(fixml);
            AdvertisementMsg43TestData.getInstance().check(msg, dmsg, SecurityType.Option);
        } finally {
            unsetPrintableFixml();
        }
    }

    /**
     * Test of encode method, of class AdvertisementMsg for FIXML 4.3.
     * @throws Exception
     */
    @Test
    public void testMarshallUnmarshallFixmlFuture() throws Exception {
        System.out.println("-->testMarshallUnmarshallFixmlFuture");
        setPrintableValidatingFixml();
        try {
            AdvertisementMsg msg = (AdvertisementMsg) FIXMsgBuilder.build(MsgType.Advertisement.getValue(), BeginString.FIX_4_3);
            AdvertisementMsg43TestData.getInstance().populate(msg, SecurityType.Future);
            String fixml = msg.toFixml();
            System.out.println("fixml-->" + fixml);
            XMLValidationResult result = validateXMLAgainstXSD(fixml, FIXML_SCHEMA_V43);
            if (result.hasErrors()) {
                System.out.println("\nERRORS:\n");
                System.out.println(result.getFatals());
                System.out.println(result.getErrors());
            }
            System.out.println(result.getWarnings());
            assertFalse(result.hasErrors());

            AdvertisementMsg dmsg = (AdvertisementMsg) FIXMsgBuilder.build(MsgType.Advertisement.getValue(), BeginString.FIX_4_3);
            dmsg.fromFixml(fixml);
            AdvertisementMsg43TestData.getInstance().check(msg, dmsg, SecurityType.Future);
        } finally {
            unsetPrintableFixml();
        }
    }

    /**
     * Test of encode method, of class AdvertisementMsg for FIXML 4.3.
     * @throws Exception
     */
    @Test
    public void testMarshallUnmarshallFixmlCorporate() throws Exception {
        System.out.println("-->testMarshallUnmarshallFixmlCorporate");
        setPrintableValidatingFixml();
        try {
            AdvertisementMsg msg = (AdvertisementMsg) FIXMsgBuilder.build(MsgType.Advertisement.getValue(), BeginString.FIX_4_3);
            AdvertisementMsg43TestData.getInstance().populate(msg, SecurityType.CorporateBond);
            String fixml = msg.toFixml();
            System.out.println("fixml-->" + fixml);
            XMLValidationResult result = validateXMLAgainstXSD(fixml, FIXML_SCHEMA_V43);
            if (result.hasErrors()) {
                System.out.println("\nERRORS:\n");
                System.out.println(result.getFatals());
                System.out.println(result.getErrors());
            }
            System.out.println(result.getWarnings());
            assertFalse(result.hasErrors());

            AdvertisementMsg dmsg = (AdvertisementMsg) FIXMsgBuilder.build(MsgType.Advertisement.getValue(), BeginString.FIX_4_3);
            dmsg.fromFixml(fixml);
            AdvertisementMsg43TestData.getInstance().check(msg, dmsg, SecurityType.CorporateBond);
        } finally {
            unsetPrintableFixml();
        }
    }

    /**
     * Test of encode method, of class AdvertisementMsg for FIXML 4.3.
     * @throws Exception
     */
    @Test
    public void testMarshallUnmarshallFixmlGovernment() throws Exception {
        System.out.println("-->testMarshallUnmarshallFixmlGovernment");
        setPrintableValidatingFixml();
        try {
            AdvertisementMsg msg = (AdvertisementMsg) FIXMsgBuilder.build(MsgType.Advertisement.getValue(), BeginString.FIX_4_3);
            AdvertisementMsg43TestData.getInstance().populate(msg, SecurityType.USTreasuryBond);
            String fixml = msg.toFixml();
            System.out.println("fixml-->" + fixml);
            XMLValidationResult result = validateXMLAgainstXSD(fixml, FIXML_SCHEMA_V43);
            if (result.hasErrors()) {
                System.out.println("\nERRORS:\n");
                System.out.println(result.getFatals());
                System.out.println(result.getErrors());
            }
            System.out.println(result.getWarnings());
            assertFalse(result.hasErrors());

            AdvertisementMsg dmsg = (AdvertisementMsg) FIXMsgBuilder.build(MsgType.Advertisement.getValue(), BeginString.FIX_4_3);
            dmsg.fromFixml(fixml);
            AdvertisementMsg43TestData.getInstance().check(msg, dmsg, SecurityType.USTreasuryBond);
        } finally {
            unsetPrintableFixml();
        }
    }

    /**
     * Test of encode method, of class AdvertisementMsg for FIXML 4.3.
     * @throws Exception
     */
    @Test
    public void testMarshallUnmarshallFixmlMoneyMarket() throws Exception {
        System.out.println("-->testMarshallUnmarshallFixmlMoneyMarket");
        setPrintableValidatingFixml();
        try {
            AdvertisementMsg msg = (AdvertisementMsg) FIXMsgBuilder.build(MsgType.Advertisement.getValue(), BeginString.FIX_4_3);
            AdvertisementMsg43TestData.getInstance().populate(msg, SecurityType.RevolverLoan);
            String fixml = msg.toFixml();
            System.out.println("fixml-->" + fixml);
            XMLValidationResult result = validateXMLAgainstXSD(fixml, FIXML_SCHEMA_V43);
            if (result.hasErrors()) {
                System.out.println("\nERRORS:\n");
                System.out.println(result.getFatals());
                System.out.println(result.getErrors());
            }
            System.out.println(result.getWarnings());
            assertFalse(result.hasErrors());

            AdvertisementMsg dmsg = (AdvertisementMsg) FIXMsgBuilder.build(MsgType.Advertisement.getValue(), BeginString.FIX_4_3);
            dmsg.fromFixml(fixml);
            AdvertisementMsg43TestData.getInstance().check(msg, dmsg, SecurityType.RevolverLoan);
        } finally {
            unsetPrintableFixml();
        }
    }

    /**
     * Test of encode method, of class AdvertisementMsg for FIXML 4.3.
     * @throws Exception
     */
    @Test
    public void testMarshallUnmarshallFixmlLoan() throws Exception {
        System.out.println("-->testMarshallUnmarshallFixmlLoan");
        setPrintableValidatingFixml();
        try {
            AdvertisementMsg msg = (AdvertisementMsg) FIXMsgBuilder.build(MsgType.Advertisement.getValue(), BeginString.FIX_4_3);
            AdvertisementMsg43TestData.getInstance().populate(msg, SecurityType.MediumTermNotes);
            String fixml = msg.toFixml();
            System.out.println("fixml-->" + fixml);
            XMLValidationResult result = validateXMLAgainstXSD(fixml, FIXML_SCHEMA_V43);
            if (result.hasErrors()) {
                System.out.println("\nERRORS:\n");
                System.out.println(result.getFatals());
                System.out.println(result.getErrors());
            }
            System.out.println(result.getWarnings());
            assertFalse(result.hasErrors());

            AdvertisementMsg dmsg = (AdvertisementMsg) FIXMsgBuilder.build(MsgType.Advertisement.getValue(), BeginString.FIX_4_3);
            dmsg.fromFixml(fixml);
            AdvertisementMsg43TestData.getInstance().check(msg, dmsg, SecurityType.MediumTermNotes);
        } finally {
            unsetPrintableFixml();
        }
    }

    /**
     * Test of encode method, of class AdvertisementMsg for FIXML 4.3.
     * @throws Exception
     */
    @Test
    public void testMarshallUnmarshallFixmlMortgage() throws Exception {
        System.out.println("-->testMarshallUnmarshallFixmlMortgage");
        setPrintableValidatingFixml();
        try {
            AdvertisementMsg msg = (AdvertisementMsg) FIXMsgBuilder.build(MsgType.Advertisement.getValue(), BeginString.FIX_4_3);
            AdvertisementMsg43TestData.getInstance().populate(msg, SecurityType.CorpMortgageBackedSecurities);
            String fixml = msg.toFixml();
            System.out.println("fixml-->" + fixml);
            XMLValidationResult result = validateXMLAgainstXSD(fixml, FIXML_SCHEMA_V43);
            if (result.hasErrors()) {
                System.out.println("\nERRORS:\n");
                System.out.println(result.getFatals());
                System.out.println(result.getErrors());
            }
            System.out.println(result.getWarnings());
            assertFalse(result.hasErrors());

            AdvertisementMsg dmsg = (AdvertisementMsg) FIXMsgBuilder.build(MsgType.Advertisement.getValue(), BeginString.FIX_4_3);
            dmsg.fromFixml(fixml);
            AdvertisementMsg43TestData.getInstance().check(msg, dmsg, SecurityType.CorpMortgageBackedSecurities);
        } finally {
            unsetPrintableFixml();
        }
    }

    /**
     * Test of encode method, of class AdvertisementMsg for FIXML 4.3.
     * @throws Exception
     */
    @Test
    public void testMarshallUnmarshallFixmlMunicipal() throws Exception {
        System.out.println("-->testMarshallUnmarshallFixmlMunicipal");
        setPrintableValidatingFixml();
        try {
            AdvertisementMsg msg = (AdvertisementMsg) FIXMsgBuilder.build(MsgType.Advertisement.getValue(), BeginString.FIX_4_3);
            AdvertisementMsg43TestData.getInstance().populate(msg, SecurityType.SpecialAssessment);
            String fixml = msg.toFixml();
            System.out.println("fixml-->" + fixml);
            XMLValidationResult result = validateXMLAgainstXSD(fixml, FIXML_SCHEMA_V43);
            if (result.hasErrors()) {
                System.out.println("\nERRORS:\n");
                System.out.println(result.getFatals());
                System.out.println(result.getErrors());
            }
            System.out.println(result.getWarnings());
            assertFalse(result.hasErrors());

            AdvertisementMsg dmsg = (AdvertisementMsg) FIXMsgBuilder.build(MsgType.Advertisement.getValue(), BeginString.FIX_4_3);
            dmsg.fromFixml(fixml);
            AdvertisementMsg43TestData.getInstance().check(msg, dmsg, SecurityType.SpecialAssessment);
        } finally {
            unsetPrintableFixml();
        }
    }

    // NEGATIVE TEST CASES
    /////////////////////////////////////////
    // UTILITY MESSAGES
    /////////////////////////////////////////
}
