/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */
package net.hades.fix.engine.config.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Holds configuration data for a shared service. A shared service is a service
 * available to all the Counterparty configurations.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 */
@XmlRootElement(name = "sharedService")
@XmlType(name = "SharedServiceInfo")
@XmlAccessorType(XmlAccessType.NONE)
public class SharedServiceInfo implements Serializable {

    private static final long serialVersionUID = 1L;
}
