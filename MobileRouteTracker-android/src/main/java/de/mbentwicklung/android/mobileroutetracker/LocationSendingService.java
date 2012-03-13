package de.mbentwicklung.android.mobileroutetracker;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.os.IBinder;

public class LocationSendingService extends Service {

	@Override
	public void onCreate() {
		super.onCreate();
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		new WebserviceLocationListener(locationManager());
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
