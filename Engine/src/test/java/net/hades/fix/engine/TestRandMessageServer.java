/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * TestEchoServer.java
 *
 * $Id: TestRandMessageServer.java,v 1.1 2010-03-27 08:44:50 vrotaru Exp $
 */
package net.hades.fix.engine;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Test echo TCP server.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 26/07/2008, 18:38:02
 */
public class TestRandMessageServer extends Thread {

    static final String APP_NAME = "RandMessageServer";

    static final int PORT = 6666;

    static ServerSocket serverSocket;

    Socket clientSocket;
    
    private boolean quit;

    BufferedReader is = null;

    BufferedWriter os = null;

    public TestRandMessageServer() {
    }
    
    public synchronized void shutdown() {
        quit = true;
    }

    @Override
    public void run() {
        try {
            printMsg("Staring server on port : " + PORT);
            serverSocket = new ServerSocket(PORT);
            clientSocket = serverSocket.accept();
            while (!quit) {
                if (is == null || os == null) {
                    createSocketStreams();
                }
                Thread.sleep(3000);
                sendRandomMessages();
                Thread.sleep(3000);
                os.flush();
            }
        } catch (Exception e) {
            printMsg("Cannot accept client connection. Error was: " + e.toString());
        } finally {
            cleanup();
        }
    }

    private void createSocketStreams() throws IOException {
        try {
            os = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
            is = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            printMsg("Created input/output streams successfully.");
        } catch (IOException ex) {
            printMsg("Could not create streams. Error was: " + ex.toString());
            throw ex;
        }
    }

    private void sendRandomMessages() throws IOException {
        int index = (int) (Math.random() * 100 % 10);
        String msgFileName = "msg" + String.valueOf(index);
        printMsg("Sending message : " + msgFileName);
        InputStream fis = this.getContextClassLoader().getResourceAsStream("com/marvisan/hades/fix/conn/data/msg/" + msgFileName);
        int c = 0;
        while ((c = fis.read()) != -1) {
            os.write(c);
        }
        os.flush();
    }

    private void cleanup() {
        try {
            if (is != null) {
                is.close();
                is = null;
            }
            if (os != null) {
                os.flush();
                os.close();
                os = null;
            }
            if (clientSocket != null) {
                clientSocket.close();
                clientSocket = null;
            }
        } catch (IOException e) {
            printMsg("I/O error while closing connections. Error eas : " + e.toString());
        }
    }

    private static void printMsg(String msg) {
        System.out.println(APP_NAME + ":  " + msg);
    }

    public static void main(String[] args) {
        TestRandMessageServer server = new TestRandMessageServer();
        server.start();
    }
}
