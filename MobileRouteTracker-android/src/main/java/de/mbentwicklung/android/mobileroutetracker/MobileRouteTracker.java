package de.mbentwicklung.android.mobileroutetracker;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningServiceInfo;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;

/**
 * @author Marc Bellmann <marc.bellmann@mb-entwicklung.de>
 */
public class MobileRouteTracker extends Activity {

	private static final String APP_ID = "MobileRouteTracker";

	private AlarmManager alarmManager;

	private PendingIntent locationSendingService;

	private ComponentManager componentManager;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		this.componentManager = new ComponentManager(this);
		this.alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

		componentManager.getRouteIDEditText().setText(getPrefString(LocationSendingService.ID));
		componentManager.getRoutePWEditText().setText(getPrefString(LocationSendingService.PW));

		componentManager.getStartButton().setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				startLocationListener();
				componentManager.loadRunState();
			}
		});

		componentManager.getStopButton().setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				stopLocationListener();
				componentManager.loadWaitState();
			}
		});

		// if (isLocationSendingServiceRunning()) {
		// componentManager.loadStartState();
		// } else {
		// componentManager.loadStopState();
		// }
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();

		stopLocationListener();
	}

	private void startLocationListener() {

		final String id = componentManager.getRouteIDEditText().getText().toString();
		final String pw = componentManager.getRoutePWEditText().getText().toString();

		setPrefString(LocationSendingService.ID, id);
		setPrefString(LocationSendingService.PW, pw);

		final Intent service = new Intent(this, LocationSendingService.class);
		service.putExtra(LocationSendingService.ID, id);
		service.putExtra(LocationSendingService.PW, pw);
		locationSendingService = PendingIntent.getService(this, 0, service, 0);

		final long interval = DateUtils.SECOND_IN_MILLIS * componentManager.getTime();
		final long firstStart = System.currentTimeMillis();

		alarmManager.setInexactRepeating(AlarmManager.RTC, firstStart, interval,
				locationSendingService);

		Log.d("mobileroutetracker", "AlarmManager with interval " + interval + " started");
	}

	private void stopLocationListener() {
		alarmManager.cancel(locationSendingService);
		if (locationSendingService != null) {
			locationSendingService.cancel();
		}
	}

	private boolean isLocationSendingServiceRunning() {
		final String className = "de.mbentwicklung.android.mobileroutetracker.LocationSendingService";
		final ActivityManager manager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
		for (RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
			if (className.equals(service.service.getClassName())) {
				return true;
			}
		}
		return false;
	}

	private String getPrefString(final String key) {
		final SharedPreferences prefs = getSharedPreferences(APP_ID, Context.MODE_PRIVATE);
		return prefs.getString(key, "");
	}

	private void setPrefString(final String key, final String val) {
		final SharedPreferences prefs = getSharedPreferences(APP_ID, Context.MODE_PRIVATE);
		Editor editor = prefs.edit();
		editor.putString(key, val);
		editor.commit();
	}
}
