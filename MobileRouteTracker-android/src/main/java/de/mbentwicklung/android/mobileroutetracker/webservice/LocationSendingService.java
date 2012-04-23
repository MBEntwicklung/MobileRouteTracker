package de.mbentwicklung.android.mobileroutetracker.webservice;

import static de.mbentwicklung.android.mobileroutetracker.MRTConstants.ID;
import static de.mbentwicklung.android.mobileroutetracker.MRTConstants.PW;

import java.util.List;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import de.mbentwicklung.android.mobileroutetracker.ConnectionSetting;
import de.mbentwicklung.android.mobileroutetracker.MRTConstants.LogTags;
import de.mbentwicklung.android.mobileroutetracker.cache.Position;
import de.mbentwicklung.android.mobileroutetracker.cache.PositionCache;

/**
 * @author Marc Bellmann <marc.bellmann@mb-entwicklung.de>
 */
public class LocationSendingService extends Service {

	private PositionCache positionCache;

	@Override
	public void onCreate() {
		this.positionCache = new PositionCache(getApplicationContext());

		super.onCreate();
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {

		Log.d(LogTags.WS_TAG, "sending positions ...");

		final ConnectionSetting connectionSetting = createConnectionSetting(intent);
		final WebService webService = new WebService(connectionSetting);
		final List<Position> positions = positionCache.getCachedPositions();

		for (final Position position : positions) {

			Log.d(LogTags.WS_TAG, "send " + position);
			final boolean success = webService.send(position.toParamMap());
			if (success) {
				Log.d(LogTags.WS_TAG, "remove " + position);
				positionCache.removePostion(position);
			}
		}

		Log.d(LogTags.WS_TAG, "... sending positions finished");
		return super.onStartCommand(intent, flags, startId);
	}

	/**
	 * @param intent
	 * @return
	 */
	private ConnectionSetting createConnectionSetting(Intent intent) {
		Bundle extras = intent.getExtras();
		final String id = extras.getString(ID);
		final String pw = extras.getString(PW);
		final ConnectionSetting connectionSetting = new ConnectionSetting(id, pw);
		return connectionSetting;
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		this.positionCache.close();
	}

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}
}
