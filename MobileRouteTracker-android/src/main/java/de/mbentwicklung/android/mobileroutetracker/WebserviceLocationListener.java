/**
 * 
 */
package de.mbentwicklung.android.mobileroutetracker;

import java.util.HashMap;
import java.util.Map;

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;

/**
 * @author Marc Bellmann <marc.bellmann@mb-entwicklung.de>
 */
public class WebserviceLocationListener implements LocationListener {

	private static final String GPS = LocationManager.GPS_PROVIDER;
	private static final int MIN_TIME_SEC = 1000 * 10;
	private static final int MIN_DISTANCE = 1000;
	
	private final LocationManager locationManager;
	
	public WebserviceLocationListener(LocationManager locationManager) {
		super();
		this.locationManager = locationManager;
		
		this.locationManager.requestLocationUpdates(GPS, MIN_TIME_SEC, MIN_DISTANCE, this);
	}

	@Override
	public void onLocationChanged(Location location) {
		this.locationManager.removeUpdates(this);
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("account_id", 1);
		params.put("lat", location.getLatitude());
		params.put("lng", location.getLongitude());

		final WebService webService = new WebService();
		webService.webInvoke(params);

		Log.i("mobileroutetracker", location.toString() + " registered");
	}

	@Override
	public void onProviderDisabled(final String provider) {

	}

	@Override
	public void onProviderEnabled(final String provider) {

	}

	@Override
	public void onStatusChanged(final String provider, final int status, final Bundle extras) {

	}
}
