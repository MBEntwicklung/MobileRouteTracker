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
 * @author marc
 * 
 */
public class WebserviceLocationListener implements LocationListener {

	public WebserviceLocationListener() {

	}

	@Override
	public void onLocationChanged(Location location) {
		Log.i("mobileroutetracker", "Load Position");
		// Pass the parameters if needed , if not then pass dummy one as follows
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("account_id", 1);
		params.put("lat", location.getLatitude());
		params.put("lng", location.getLongitude());

		final WebService webService = new WebService();
		webService.webInvoke("", params);
		Log.i("mobileroutetracker", location.toString() + " registered");
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
