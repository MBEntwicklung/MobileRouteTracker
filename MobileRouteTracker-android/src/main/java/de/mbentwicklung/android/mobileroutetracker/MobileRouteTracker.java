package de.mbentwicklung.android.mobileroutetracker;

import android.app.Activity;
import android.content.Context;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;

public class MobileRouteTracker extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		loadPositon(getApplicationContext());
	}

	private void loadPositon(final Context context) {
		final LocationManager locationManager = (LocationManager) context
				.getSystemService(Context.LOCATION_SERVICE);

		final WebserviceLocationListener l = new WebserviceLocationListener();
		locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000 * 10, 500, l);
	}

}
