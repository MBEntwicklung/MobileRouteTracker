package de.mbentwicklung.android.mobileroutetracker.tracking;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.os.IBinder;
import android.util.Log;

/**
 * @author Marc Bellmann <marc.bellmann@mb-entwicklung.de>
 */
public class LocationTrackingService extends Service {

	@Override
	public void onCreate() {
		super.onCreate();
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		Log.d("TC", "tracking positions ...");
		new CachedLocationListener(locationManager(), getApplicationContext());
		Log.d("TC", "... tracking positions finished");

		return super.onStartCommand(intent, flags, startId);
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
	}

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	private LocationManager locationManager() {
		return (LocationManager) getApplicationContext().getSystemService(Context.LOCATION_SERVICE);
	}
}
