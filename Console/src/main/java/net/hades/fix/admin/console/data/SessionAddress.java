/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.6
 *               Use is subject to license terms.
 */

/*
 * SessionAddress.java
 *
 * $Id: SessionAddress.java,v 1.1 2010-05-14 11:11:39 vrotaru Exp $
 */
package net.hades.fix.admin.console.data;

/**
 * Holds sessions address data.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 14/05/2010
 */
public class SessionAddress {

    // <editor-fold defaultstate="collapsed" desc="Constants">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">

    private String cptyAddress;

    private String sessAdrress;
    
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public SessionAddress(String cptyAddress, String sessAdrress) {
        this.cptyAddress = cptyAddress;
        this.sessAdrress = sessAdrress;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Public Methods">

    public String getCptyAddress() {
        return cptyAddress;
    }

    public void setCptyAddress(String cptyAddress) {
        this.cptyAddress = cptyAddress;
    }

    public String getSessAdrress() {
        return sessAdrress;
    }

    public void setSessAdrress(String sessAdrress) {
        this.sessAdrress = sessAdrress;
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
