/**
 * 
 */
package de.mbentwicklung.android.mobileroutetracker;

import de.mbentwicklung.android.mobileroutetracker.webservice.WebLinkFactory;

/**
 * 
 * 
 * @author Marc Bellmann <marc.bellmann@mb-entwicklung.de>
 */
public class ConnectionSetting {

	private final String eventId;
	private final String pass;

	public ConnectionSetting(final String eventId, final String pass) {
		super();
		this.eventId = eventId;
		this.pass = pass;
	}

	/**
	 * Gibt einen generierten Link für den Webservice zurück
	 * 
	 * @return Link zum WebService
	 */
	public String getWebserviceLink() {
		return WebLinkFactory.createLink(MRTConstants.WEB_SERVICE_URL, eventId, pass);
	}
}
