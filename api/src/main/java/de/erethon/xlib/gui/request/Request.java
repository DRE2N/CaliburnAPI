/*
 * Copyright (C) 2015-2026 Daniel Saukel.
 *
 * This library is free software: you can redistribute it and/or modify it under the
 * terms of the GNU Lesser General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A
 * PARTICULAR PURPOSE. See the GNULesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License along with
 * this program. If not, see <http://www.gnu.org/licenses/>.
 */
package de.erethon.xlib.gui.request;

import de.erethon.xlib.gui.GUI;
import java.util.Date;

/**
 * @deprecated draft
 * @author Daniel Saukel
 */
@Deprecated
public class Request {

    private RequestParticipator requester;
    private RequestParticipator target;
    private Date expiration;
    private GUI requestGUI;

    /**
     * @param requester  the RequestParticipator that poses the Request
     * @param target     the RequestParticipator that the Request is addressed to
     * @param expiration when the Request expires and is denied automatically
     * @param requestGUI the GUI to open to the target to pose the Request
     */
    public Request(RequestParticipator requester, RequestParticipator target, Date expiration, GUI requestGUI) {
        this.requester = requester;
        this.target = target;
        this.expiration = expiration;
        this.requestGUI = requestGUI;
    }

    /**
     * Creates a Request that does not expire.
     *
     * @param requester  the RequestParticipator that poses the Request
     * @param target     the RequestParticipator that the Request is addressed to
     * @param requestGUI the GUI to open to the target to pose the Request
     */
    public Request(RequestParticipator requester, RequestParticipator target, GUI requestGUI) {
        this.requester = requester;
        this.target = target;
        this.expiration = new Date(-1l);
        this.requestGUI = requestGUI;
    }

    /**
     * Returns the RequestParticipator that poses the Request.
     *
     * @return the RequestParticipator that poses the Request
     */
    public RequestParticipator getRequester() {
        return requester;
    }

    /**
     * Returns the RequestParticipator that the Request is addressed to.
     *
     * @return the RequestParticipator that the Request is addressed to
     */
    public RequestParticipator getTarget() {
        return target;
    }

    /**
     * Returns true if the Request is expired; false if not.
     *
     * @return true if the Request is expired; false if not
     */
    public boolean isExpired() {
        return expiration.getTime() != -1 && System.currentTimeMillis() > expiration.getTime();
    }

    /**
     * Returns the GUI to open to the target to pose the Request.
     *
     * @return the GUI to open to the target to pose the Request
     */
    public GUI getRequestGUI() {
        return requestGUI;
    }

    /**
     * Sends the Request to the target.
     */
    public void send() {
        requestGUI.open(target);
    }

}
