/**
 * 
 */
package de.mbentwicklung.android.mobileroutetracker;

import java.util.HashMap;
import java.util.Map;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.util.Log;

/**
 * @author Marc Bellmann <marc.bellmann@mb-entwicklung.de>
 */
public class WebserviceLocationListener implements LocationListener {

	@Override
	public void onLocationChanged(Location location) {
		Log.i("mobileroutetracker", "Load Position");

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
