/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * ManagedProcess.java
 *
 * $Id: ManagedProcess.java,v 1.5 2010-03-03 10:07:10 vrotaru Exp $
 */
package net.hades.fix.engine.process;

/**
 * Interface that defines the contract to be implemented by a process thread.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.5 $
 */
public interface ManagedProcess extends Manageable, Reportable, Commandable {
}
