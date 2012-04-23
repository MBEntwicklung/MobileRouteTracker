package de.mbentwicklung.android.mobileroutetracker.cache;

import static de.mbentwicklung.android.mobileroutetracker.MRTConstants.Params.ACCURACY;
import static de.mbentwicklung.android.mobileroutetracker.MRTConstants.Params.ALTITUDE;
import static de.mbentwicklung.android.mobileroutetracker.MRTConstants.Params.BEARING;
import static de.mbentwicklung.android.mobileroutetracker.MRTConstants.Params.LATITUDE;
import static de.mbentwicklung.android.mobileroutetracker.MRTConstants.Params.LONGITUDE;
import static de.mbentwicklung.android.mobileroutetracker.MRTConstants.Params.TIME;

import java.util.HashMap;
import java.util.Map;

import android.content.ContentValues;

/**
 * Position
 * 
 * @author Marc Bellmann <marc.bellmann@mb-entwicklung.de>
 */
public class Position {

	private final Integer id;
	private final Double longitude;
	private final Double latitude;
	private final Double altitude;
	private final Long time;

	/** Exaktheit */
	private final Float accuracy;

	/** Kompasspeilung */
	private final Float bearing;

	public Position(final Integer id, final Double longitude, final Double latitude,
			final Double altitude, final Long time, final Float accuracy, final Float bearing) {
		super();
		this.id = id;
		this.longitude = longitude;
		this.latitude = latitude;
		this.altitude = altitude;
		this.time = time;
		this.accuracy = accuracy;
		this.bearing = bearing;
	}

	public Integer getId() {
		return id;
	}

	public Double getLongitude() {
		return longitude;
	}

	public Double getLatitude() {
		return latitude;
	}

	public Double getAltitude() {
		return altitude;
	}

	public Long getTime() {
		return time;
	}

	public Float getAccuracy() {
		return accuracy;
	}

	public Float getBearing() {
		return bearing;
	}

	@Override
	public String toString() {
		return "Position [id=" + id + ", longitude=" + longitude + ", latitude=" + latitude
				+ ", altitude=" + altitude + ", time=" + time + ", accuracy=" + accuracy
				+ ", bearing=" + bearing + "]";
	}

	public Map<String, Object> toParamMap() {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put(LONGITUDE, longitude);
		params.put(LATITUDE, latitude);
		params.put(ALTITUDE, altitude);
		params.put(TIME, time);
		params.put(ACCURACY, accuracy);
		params.put(BEARING, bearing);
		return params;
	}

	public ContentValues toContentValues() {
		final ContentValues params = new ContentValues();
		params.put(LONGITUDE, longitude);
		params.put(LATITUDE, latitude);
		params.put(ALTITUDE, altitude);
		params.put(TIME, time);
		params.put(ACCURACY, accuracy);
		params.put(BEARING, bearing);
		return params;
	}
}
