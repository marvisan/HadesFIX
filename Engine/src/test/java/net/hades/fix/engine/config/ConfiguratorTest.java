/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * ConfiguratorTest.java
 *
 * $Id: ConfiguratorTest.java,v 1.2 2010-06-05 09:32:29 vrotaru Exp $
 */
package net.hades.fix.engine.config;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import net.hades.fix.engine.config.model.ClientTcpConnectionInfo;
import net.hades.fix.engine.config.model.CounterpartyInfo;
import net.hades.fix.engine.config.model.HadesInstanceInfo;
import net.hades.fix.engine.config.model.HandlerDefInfo;
import net.hades.fix.engine.config.model.HandlerInfo;
import net.hades.fix.engine.config.model.SecuredFieldInfo;
import net.hades.fix.engine.config.model.SecuredMessageInfo;
import net.hades.fix.engine.config.model.SessionInfo;

/**
 * Test suite for Configurator class.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 * @created 15/07/2009, 11:18:16 AM
 */
public class ConfiguratorTest {

    public ConfiguratorTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
	System.setProperty("hades.engine.config.file", "/Projects/cvs/hades/HadesFIXE/tmp/hadescfg/ConfigTest1.hades.xml");
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

    /*
     * Test of readConfiguration method, of class Configurator.
     */
    @Test
    public void testReadConfiguration() throws Exception {
//        System.out.println("readConfiguration");
//        HadesInstanceInfo result = Configurator.readConfiguration();
//        assertEquals("ConnToCBOT", result.getName());
//        assertEquals(1, result.getCounterparties().length);
//        CounterpartyInfo cp = result.getCounterparties()[0];
//        assertEquals("CBOT", cp.getCompID());
//        assertEquals("A1", cp.getSubID());
//        assertEquals("B1", cp.getLocationID());
//        SessionInfo[] sessions = cp.getSessions();
//        assertEquals(1, sessions.length);
//        SessionInfo session = sessions[0];
//        assertEquals("MARV", session.getCompID());
//        assertEquals("M1", session.getSubID());
//        assertEquals("N1", session.getLocationID());
//        ClientTcpConnectionInfo con = (ClientTcpConnectionInfo) session.getConnection();
//        assertEquals("localhost", con.getHost());
//        assertEquals(20890, con.getPort());
//        assertEquals("password", con.getSslData().getKeyPasswd());
//        assertEquals("password1", con.getSslData().getKeyStorePasswd());
//        assertTrue(con.getSslData().isUseCliAuth());
//        assertEquals(1, cp.getHandlerDefs().length);
//        HandlerDefInfo hd = cp.getHandlerDefs()[0];
//        assertEquals("NOP", hd.getName());
//        assertEquals("net.hades.fix.engine.handler.nop.NOPHandler", hd.getImplClass());
//        assertEquals(2, hd.getParameters().length);
//        assertEquals("param1", hd.getParameters()[0].getName());
//        assertEquals("value1", hd.getParameters()[0].getValue());
//        assertEquals("param2", hd.getParameters()[1].getName());
//        assertEquals("value2", hd.getParameters()[1].getValue());
//        ProducerStreamInfo ps = session.getProducerStreamInfo();
//        assertEquals(2, ps.getFlows().length);
//        FlowInfo flow1 = ps.getFlows()[0];
//        assertEquals("d,8", flow1.getMsgFilter());
//        assertEquals("net.test.FilterClass1", flow1.getMsgFilterClass());
//        HandlerInfo h1 = flow1.getHandler();
//        assertEquals("NOP", h1.getName());
//        assertNull(h1.getNextHandler());
//        FlowInfo flow2 = ps.getFlows()[1];
//        assertEquals("a,x", flow2.getMsgFilter());
//        assertNull(flow2.getMsgFilterClass());
//        HandlerInfo h2 = flow2.getHandler();
//        assertEquals("NOP", h2.getName());
//        assertNull(h2.getNextHandler());
//        ConsumerStreamInfo cs = session.getConsumerStreamInfo();
//        assertEquals(1, cs.getFlows().length);
//        FlowInfo flow5 = ps.getFlows()[0];
//        assertEquals("d,8", flow5.getMsgFilter());
//        assertEquals("net.test.FilterClass1", flow5.getMsgFilterClass());
//        HandlerInfo h3 = cs.getFlows()[0].getHandler();
//        assertEquals("NOP", h3.getName());
//        assertNull(h3.getNextHandler());
//        assertEquals(1, cp.getSecuredMessages().length);
//        SecuredMessageInfo[] sm = cp.getSecuredMessages();
//        assertEquals(1, sm.length);
//        assertEquals("A", sm[0].getType());
//        SecuredFieldInfo[] sf = sm[0].getFields();
//        assertEquals(2, sf.length);
//        assertEquals(34, sf[0].getTagNum().intValue());
//        assertEquals(55, sf[1].getTagNum().intValue());
    }

    /*
     * Test of marshallConfig method, of class Configurator.
     */
//    @Test
//    public void testMarshallConfig() throws Exception {
//        System.out.println("marshallConfig");
//        ProcessChainInfo config = new ProcessChainInfo();
//        config.setName("ServiceName");
//        ProcessInfo proc1 = new ProcessInfo();
//        proc1.setName("Proc1");
//        proc1.setStartOrder(2);
//        proc1.setStopOrder(1);
//        proc1.setImplClass("com.mycomp.proc.ProcImpl");
//        List<String> rxList = new ArrayList<String>();
//        rxList.add("Proc3");
//        rxList.add("Proc4");
//        List<String> txList = new ArrayList<String>();
//        txList.add("Proc1");
//        txList.add("Proc2");
//        HandlerParamInfo[] params = new HandlerParamInfo[] {
//            new HandlerParamInfo("Param1", "Value1"),
//            new HandlerParamInfo("Param2", "Value2")};
//        proc1.setRequestListeners(rxList);
//        proc1.setResponseListeners(txList);
//        proc1.setParameters(params);
//        config.setProcesses(new ProcessInfo[] {proc1});
//        String result = Configurator.marshallConfig(config);
//        System.out.println("XML=" + result);
//    }

}