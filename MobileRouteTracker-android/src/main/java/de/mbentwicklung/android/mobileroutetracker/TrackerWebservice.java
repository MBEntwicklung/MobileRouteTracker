/**
 * 
 */
package de.mbentwicklung.android.mobileroutetracker;

import java.util.Timer;
import java.util.TimerTask;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.util.Log;

/**
 * @author marc
 * 
 */
public class TrackerWebservice extends IntentService {

	private static final int TIMEOUT = 1000 * 10;
	private LocationManager locationManager;

	/**
	 * @param name
	 */
	public TrackerWebservice(String name) {
		super(name);
		this.locationManager = getLocationManager();
	}

	public TrackerWebservice() {
		super("TrackerWebservice");
	}

	@Override
	public void onCreate() {
		super.onCreate();
		this.locationManager = getLocationManager();
	}

	private LocationManager getLocationManager() {
		return (LocationManager) getSystemService(Context.LOCATION_SERVICE);
	}

	@Override
	protected void onHandleIntent(final Intent intent) {
		Timer timeout = new Timer();
		timeout.schedule(new TimerTask() {

			@Override
			public void run() {
				runService();
				
				onHandleIntent(intent);
			}
		}, TIMEOUT);

	}

	private void runService() {
		Log.i("mobileroutetracker", "Run Service");

		// final WebserviceLocationListener l = new WebserviceLocationListener();
		// this.locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 500, l);
	}

}
