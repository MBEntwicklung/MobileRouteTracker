package de.mbentwicklung.android.mobileroutetracker;

/**
 * Enum für alle auswählbaren Intervalle.
 * 
 * @author Marc Bellmann <marc.bellmann@mb-entwicklung.de>
 */
public enum TimeInterval {

	/**
	 * 10 Sekunden
	 */
	TEN_SECONDS(10, R.string.ten_seconds),

	/**
	 * 30 Sekunden
	 */
	THIRTY_SECONDS(30, R.string.thirty_seconds),

	/**
	 * 1 Minute
	 */
	ONE_MINUTE(60, R.string.one_minute),

	/**
	 * 10 Minuten
	 */
	TEN_MINUTE(10 * 60, R.string.ten_minute),

	/**
	 * 30 Minuten
	 */
	THIRTY_MINUTE(30 * 60, R.string.thirty_minute),

	/**
	 * 1 Stunde
	 */
	ONE_HOUR(60 * 60, R.string.one_hour);

	/**
	 * Zeit
	 */
	private final int time;

	/**
	 * Key für Text
	 */
	private final int stringId;

	/**
	 * Konstruktor
	 * 
	 * @param time
	 *            {@link #time}
	 * @param stringId
	 *            {@link #stringId}
	 */
	private TimeInterval(final int time, final int stringId) {
		this.time = time;
		this.stringId = stringId;
	}

	/**
	 * Getter
	 * 
	 * @return {@link #time}
	 */
	public int getTime() {
		return this.time;
	}

	/**
	 * Getter
	 * 
	 * @return {@link #stringId}
	 */
	public int getStringId() {
		return this.stringId;
	}
}
