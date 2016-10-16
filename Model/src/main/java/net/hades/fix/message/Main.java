/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * Main.java
 */
package net.hades.fix.message;

import java.io.IOException;
import java.io.InputStream;
import java.util.Set;
import java.util.jar.Attributes;
import java.util.jar.Manifest;

/**
 * Dumps the current library build data.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 */
public class Main {

    public static void main(String[] args) {
        InputStream is = Main.class.getResourceAsStream("/META-INF/MANIFEST.MF");
        if (is != null) {
            try {
                Manifest mf = new Manifest(is);
                if (mf != null) {
                    Attributes attrs = mf.getAttributes("Release Data");
                    Set<Object> entries = attrs.keySet();
                    for (Object entry : entries) {
                        System.out.println(entry + " : " + attrs.getValue(entry.toString()));
                    }
                }
            } catch (IOException ex) {
                System.out.println("Error : " + ex.toString());
            }
        }
    }
}
