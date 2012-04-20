/**
 * 
 */
package de.mbentwicklung.android.mobileroutetracker.tracking;


import de.mbentwicklung.android.mobileroutetracker.cache.Position;
import de.mbentwicklung.android.mobileroutetracker.cache.PositionCache;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;

/**
 * @author Marc Bellmann <marc.bellmann@mb-entwicklung.de>
 */
public class CachedLocationListener implements LocationListener {

	private static final String GPS = LocationManager.GPS_PROVIDER;
	private static final int MIN_TIME_SEC = 1000 * 10;
	private static final int MIN_DISTANCE = 1000;

	private final LocationManager locationManager;
	private final PositionCache positionCache;

	public CachedLocationListener(final LocationManager locationManager, final Context context) {
		super();
		this.locationManager = locationManager;

		this.locationManager.requestLocationUpdates(GPS, MIN_TIME_SEC, MIN_DISTANCE, this);
		this.positionCache = new PositionCache(context);
	}

	@Override
	public void onLocationChanged(Location location) {
		this.locationManager.removeUpdates(this);

		final Position position = new Position(0, location.getLongitude(), location.getLatitude(),
				location.getAltitude(), location.getTime());

		Log.d("TC", "cache " + position);
		this.positionCache.insertNewPosition(position);
	}

	@Override
	public void onProviderDisabled(String provider) {
	}

	@Override
	public void onProviderEnabled(String provider) {
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
	}
}
