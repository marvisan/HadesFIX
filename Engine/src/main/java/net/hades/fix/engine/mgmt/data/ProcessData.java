/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * ProcessData.java
 *
 * $Id: ProcessData.java,v 1.2 2010-05-14 11:10:18 vrotaru Exp $
 */
package net.hades.fix.engine.mgmt.data;

import java.io.Serializable;


/**
 * Process management data collected bt the JMX server.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 * @created 28/04/2010
 */
public abstract class ProcessData implements Serializable {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">

    protected String id;

    protected String name;

    protected ProcessStatus status;

    protected String config;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public ProcessData() {
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Public Methods">
    
    public String getConfig() {
        return config;
    }

    public void setConfig(String config) {
        this.config = config;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ProcessStatus getStatus() {
        return status;
    }

    public void setStatus(ProcessStatus status) {
        this.status = status;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Package Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Private Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Inner Classes">
    // </editor-fold>
}
