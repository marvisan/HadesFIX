/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */
package net.hades.fix.engine.mgmt.alert;

import java.io.Serializable;
import java.util.Date;

import net.hades.fix.message.util.format.DateFormatter;

/**
 * Model class for a published alert message.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 */
public class Alert implements Serializable {

    private static final long serialVersionUID = 1L;

    private Date timestamp;

    private String code;

    private SeverityType severity;

    private String component;

    private String message;

    private Throwable error;

    public Alert(Date timestamp, String thread, SeverityType severity, String component, String message) {
        this.timestamp = timestamp;
        this.severity = severity;
        this.component = component;
        this.message = message;
    }

    public Alert(Date timestamp, String thread, SeverityType severity, String component, String message, Throwable error) {
        this.timestamp = timestamp;
        this.severity = severity;
        this.component = component;
        this.message = message;
        this.error = error;
    }

    public Alert(Date timestamp, AlertCode code, String thread, SeverityType severity, String component, String message) {
        this.timestamp = timestamp;
        this.code = code.toString();
        this.severity = severity;
        this.component = component;
        this.message = message;
    }

     public Alert(Date timestamp, AlertCode code, String thread, SeverityType severity, String component, String message, Throwable error) {
        this.timestamp = timestamp;
        this.code = code.toString();
        this.severity = severity;
        this.component = component;
        this.message = message;
        this.error = error;
    }

    public Alert(Date timestamp, String code, String thread, SeverityType severity, String component) {
        this.timestamp = timestamp;
        this.code = code;
        this.severity = severity;
        this.component = component;
    }

    public static Alert createAlert(String thread, String component, SeverityType severity, AlertCode code, String message, Throwable exception) {
        if (code != null) {
            if (exception != null) {
                return new Alert(new Date(), code, thread, severity, component, message, exception);
            } else {
                return new Alert(new Date(), code, thread, severity, component, message);
            }
        } else {
            if (exception != null) {
                return new Alert(new Date(), thread, severity, component, message, exception);
            } else {
                return new Alert(new Date(), thread, severity, component, message);
            }
        }
    }

    public String getComponent() {
        return component;
    }

    public String getMessage() {
        return message;
    }

    public SeverityType getSeverity() {
        return severity;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public Throwable getError() {
        return error;
    }

    public String getCode() {
        return code;
    }

    @Override
    public String toString() {
        StringBuilder b = new StringBuilder("{Alert=");
        b.append("Timestamp=").append(DateFormatter.getFixDateFormat().format(timestamp));
        b.append(", Code=").append(code);
        b.append(", Severity=").append(severity);
        b.append(", Component=").append(component);
        if (message != null) {
            b.append(", Message=").append(message);
        }
        if (code != null) {
            b.append(", Type=").append(code);
        }
        if (error != null) {
            b.append(", Error=").append(error.getMessage());
        }
        b.append("}");

        return b.toString();
    }
}
