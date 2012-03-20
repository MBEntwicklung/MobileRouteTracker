package de.mbentwicklung.android.mobileroutetracker;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

public class LocationSendingService extends Service {

	static final String PW = "pw";
	static final String ID = "id";

	@Override
	public void onCreate() {
		super.onCreate();
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		Bundle extras = intent.getExtras();
		final String id = extras.getString(ID);
		final String pw = extras.getString(PW);
		Log.i("", id + "/" + pw);
		
		new WebserviceLocationListener(locationManager(), new ConnectionSetting(id, pw));
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
