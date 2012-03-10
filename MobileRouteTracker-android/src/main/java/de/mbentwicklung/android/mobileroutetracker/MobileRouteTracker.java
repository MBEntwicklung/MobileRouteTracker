package de.mbentwicklung.android.mobileroutetracker;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class MobileRouteTracker extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		startService(new Intent(this, TrackerWebservice.class));
	}
}
