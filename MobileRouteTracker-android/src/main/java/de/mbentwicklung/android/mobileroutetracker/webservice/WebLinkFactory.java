package de.mbentwicklung.android.mobileroutetracker.webservice;

/**
 * @author Marc Bellmann <marc.bellmann@mb-entwicklung.de>
 */
public class WebLinkFactory {

	private static final String SLASH = "/";

	/**
	 * Erstellt einen WebLink aus einem Array von Strings. Trennt Strings mit {@link #SLASH}.
	 * 
	 * @param partials
	 *            Strings
	 * @return WebLink
	 */
	public static String createLink(final String... partials) {
		final StringBuilder builder = new StringBuilder();

		for (String partial : partials) {
			builder.append(partial).append(SLASH);
		}

		return builder.toString();
	}
}
