/*
 *  Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *              Use is subject to license terms.
 */
package net.hades.fix.engine.process;

/**
 * Contract for a task that has priority and name.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 */
public interface PriorityNamedTask {

    int getPriority();
    String getName();

}
