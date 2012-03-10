package de.mbentwicklung.android.mobileroutetracker;

import android.app.Activity;
import android.content.Context;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

/**
 * @author Marc Bellmann <marc.bellmann@mb-entwicklung.de>
 */
public class MobileRouteTracker extends Activity {

	private static final String GPS = LocationManager.GPS_PROVIDER;
	private static final int MIN_TIME_SEC = 1000 * 10;
	private static final int MIN_DISTANCE = 1000;
	private WebserviceLocationListener locationListener;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		final Button startButton = (Button) findViewById(R.id.button_start);
		final Button stopButton = (Button) findViewById(R.id.button_stop);

		startButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				startLocationListener();
				stopButton.setEnabled(true);
				startButton.setEnabled(false);
			}
		});

		stopButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				stopLocationListener();
				startButton.setEnabled(true);
				stopButton.setEnabled(false);
			}
		});
	}

	private void startLocationListener() {
		this.locationListener = new WebserviceLocationListener();
		locationManager().requestLocationUpdates(GPS, MIN_TIME_SEC, MIN_DISTANCE, locationListener);
	}

	private void stopLocationListener() {
		locationManager().removeUpdates(locationListener);
		this.locationListener = null;
	}

	private LocationManager locationManager() {
		return (LocationManager) getApplicationContext().getSystemService(Context.LOCATION_SERVICE);
	}
}
