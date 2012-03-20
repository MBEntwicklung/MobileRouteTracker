/**
 * 
 */
package de.mbentwicklung.android.mobileroutetracker;

/**
 * 
 * 
 * @author Marc Bellmann <marc.bellmann@mb-entwicklung.de>
 */
public class ConnectionSetting {

	private static final String SLASH = "/";

	private final static String WEB_SERVICE_URL = "http://dhost23.bonn-local.de:3000/webservice/position";

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
		return WEB_SERVICE_URL + SLASH + eventId + SLASH + pass;
	}
}
