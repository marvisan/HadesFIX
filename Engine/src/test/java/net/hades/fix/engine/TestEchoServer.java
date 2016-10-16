/*
 *   Copyright (c) 2006-2008 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * TestEchoServer.java
 *
 * $Id: TestEchoServer.java,v 1.1 2010-03-27 08:44:50 vrotaru Exp $
 */
package net.hades.fix.engine;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
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
public class TestEchoServer extends Thread {

    static final String APP_NAME = "EchoServer";

    static final int PORT = 6666;

    static ServerSocket serverSocket;

    Socket clientSocket;
    
    private boolean quit;

    BufferedReader is = null;

    BufferedWriter os = null;

    public TestEchoServer() {
    }
    
    public void shutdown() {
        quit = true;
    }

    @Override
    public void run() {
        try {
            serverSocket = new ServerSocket(PORT);
            clientSocket = serverSocket.accept();
            while (!quit) {
                os = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
                is = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                processClientRequest();
                Thread.sleep(20);
            }
            os.flush();
        } catch (Exception e) {
            printMsg("Cannot accept client connection. Error was: " + e.toString());
        } finally {
            cleanup();
        }
    }

    private void processClientRequest() {

        try {
            printMsg("Processing...");
            char[] end = new char[7];
            boolean writeToEnd = false;
            int endIdx = 0;
            int c = 0;
            int p = 0;
            while ((c = is.read()) != -1) {
                char ch = (char) c;
                if (c > 1) {
                    System.out.print(ch);
                } else {
                    System.out.print("*");
                }
                os.write(c);
                if ((char) c == '1') {
                    if (p == 1) {
                        writeToEnd = true;
                    }
                }
                if (writeToEnd) {
                    if (endIdx < 7) {
                        end[endIdx] = (char) c;
                        endIdx++;
                        if (endIdx == 7) {
                            if (isEndOfMsg(end)) {
                                printMsg("Flushing...");
                                os.flush();
                            }
                            writeToEnd = false;
                            end = new char[7];
                            endIdx = 0;
                        }
                    }
                }
                p = c;
            }
            os.flush();
            System.out.println();
            printMsg("Completed");
        } catch (IOException e) {
            printMsg("I/O error while processing client's print file - " + e.toString());
        }
    }

    private void cleanup() {
        try {
            if (is != null) {
                is.close();
            }
            if (os != null) {
                os.flush();
                os.close();
            }
            if (clientSocket != null) {
                clientSocket.close();
            }
        } catch (IOException e) {
            printMsg("I/O error while closing connections.");
        }
    }

    private static void printMsg(String msg) {
        System.out.println(APP_NAME + ":  " + msg);
    }
    
    private boolean isEndOfMsg(char[] end) {
        
        boolean result = false;
        
        System.out.println("end=" + new String(end));
        String field = new String(end);
        if (field.startsWith("10=")) {
            if ((int)end[6] == 1) {
                result = true;
            }
        }
        System.out.println("result=" + result);
        
        return result;
    }
}
