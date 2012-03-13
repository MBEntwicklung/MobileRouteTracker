package de.mbentwicklung.android.mobileroutetracker;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningServiceInfo;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

/**
 * @author Marc Bellmann <marc.bellmann@mb-entwicklung.de>
 */
public class MobileRouteTracker extends Activity {

	private AlarmManager alarmManager;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		this.alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

		final Button startButton = (Button) findViewById(R.id.button_start);
		final Button stopButton = (Button) findViewById(R.id.button_stop);
		final SeekBar timeBar = (SeekBar) findViewById(R.id.timeBar);
		final TextView timeValue = (TextView) findViewById(R.id.timeValue);
		
		timeBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			@Override
			public void onStopTrackingTouch(final SeekBar seekBar) {
				timeValue.setText("Interval:" + seekBar.getProgress());
			}
			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
			}
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
			}
		});

		startButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				startLocationListener();
				loadStartState(startButton, stopButton, timeBar);
			}
		});

		stopButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				stopLocationListener();
				loadStopState(startButton, stopButton, timeBar);
			}
		});

		if (isLocationSendingServiceRunning()) {
			loadStartState(startButton, stopButton, timeBar);
		} else {
			loadStopState(startButton, stopButton, timeBar);
		}
	}

	private void startLocationListener() {
		final SeekBar timeBar = (SeekBar) findViewById(R.id.timeBar);
		final Intent service = new Intent(this, LocationSendingService.class);
		final PendingIntent locationSendingService = PendingIntent.getService(this, 0, service, 0);

		final long interval = DateUtils.SECOND_IN_MILLIS * timeBar.getProgress();
		final long firstStart = System.currentTimeMillis();
		alarmManager.setInexactRepeating(AlarmManager.RTC, firstStart, interval,
				locationSendingService);

		Log.d("mobileroutetracker", "AlarmManager with interval " + interval + " started");
	}

	private void stopLocationListener() {
		final Intent service = new Intent(this, LocationSendingService.class);
		final PendingIntent locationSendingService = PendingIntent.getService(this, 0, service, 0);

		alarmManager.cancel(locationSendingService);
	}

	/**
	 * @param startButton
	 * @param stopButton
	 * @param timeBar
	 */
	private void loadStopState(final Button startButton, final Button stopButton,
			final SeekBar timeBar) {
		startButton.setEnabled(true);
		timeBar.setEnabled(true);
		stopButton.setEnabled(false);
	}

	/**
	 * @param startButton
	 * @param stopButton
	 * @param timeBar
	 */
	private void loadStartState(final Button startButton, final Button stopButton,
			final SeekBar timeBar) {
		stopButton.setEnabled(true);
		timeBar.setEnabled(false);
		startButton.setEnabled(false);
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
}
