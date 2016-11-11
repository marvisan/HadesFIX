/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * FieldData.java
 *
 * $Id: FieldData.java,v 1.1 2011-03-17 07:36:17 vrotaru Exp $
 */
package net.hades.fix.admin.console.data;

/**
 * Meta-data about a field.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 17/03/2011
 */
public class FieldData {

    private String name;
    private String description;

    public FieldData() {
    }

    public FieldData(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
