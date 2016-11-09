/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */
package net.hades.fix.engine.mgmt.data;

import java.io.Serializable;
import net.hades.fix.engine.process.TaskStatus;


/**
 * Process management data collected by the JMX server.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 */
public abstract class ProcessData implements Serializable {

    private static final long serialVersionUID = 1L;

    protected String id;
    protected String name;
    protected TaskStatus status;
    protected String config;

    public ProcessData() {
    }

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

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

}
