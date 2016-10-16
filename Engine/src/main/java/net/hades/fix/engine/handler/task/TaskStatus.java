/*
 *  Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *              Use is subject to license terms.
 */

package net.hades.fix.engine.handler.task;

/**
 * Status of the task thread run.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 */
public enum TaskStatus {
    New, Running, Exiting, TimedOut, Completed;

}
