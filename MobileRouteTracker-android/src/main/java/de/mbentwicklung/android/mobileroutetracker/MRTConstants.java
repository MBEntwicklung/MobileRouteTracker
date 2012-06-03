package de.mbentwicklung.android.mobileroutetracker;

/**
 * @author Marc Bellmann <marc.bellmann@mb-entwicklung.de>
 */
public class MRTConstants {

	public static class LogTags {
		public static final String WS_TAG = "WS";
	}

	public static class Params {

		public static final String LONGITUDE = "longitude";
		public static final String LATITUDE= "latitude";
		public static final String ALTITUDE = "altitude";
		public static final String TIME = "time";

		/** Exaktheit */
		public static final String ACCURACY= "accuracy";

		/** Kompasspeilung */
		public static final String BEARING = "bearing";
	}

	public final static String WEB_SERVICE_URL = "http://mobile-route-tracker.org/webservice/position";

	public static final String PW = "pw";
	public static final String ID = "id";
	public static final String POSITION_CACHE_TABLE_NAME = "position_cache";

}
