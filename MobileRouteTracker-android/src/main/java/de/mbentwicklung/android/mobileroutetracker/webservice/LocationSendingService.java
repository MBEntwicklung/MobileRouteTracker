package de.mbentwicklung.android.mobileroutetracker.webservice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import de.mbentwicklung.android.mobileroutetracker.ConnectionSetting;
import de.mbentwicklung.android.mobileroutetracker.cache.Position;
import de.mbentwicklung.android.mobileroutetracker.cache.PositionCache;

public class LocationSendingService extends Service {

	private PositionCache positionCache;

	public static final String PW = "pw";
	public static final String ID = "id";

	@Override
	public void onCreate() {
		this.positionCache = new PositionCache(getApplicationContext());

		super.onCreate();
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		Bundle extras = intent.getExtras();
		final String id = extras.getString(ID);
		final String pw = extras.getString(PW);
		Log.d("WS", "sending positions ...");

		final WebService webService = new WebService(new ConnectionSetting(id, pw));

		final List<Position> positions = positionCache.getCachedPositions();
		for (final Position position : positions) {

			Map<String, Object> params = new HashMap<String, Object>();
			params.put("account_id", 1);
			params.put("lat", position.getLat());
			params.put("lng", position.getLon());

			Log.d("WS", "send " + position);
			webService.webInvoke(params);
			positionCache.removePostion(position);
			Log.d("WS", "remove " + position);
		}

		Log.d("WS", "... sending positions finished");
		return super.onStartCommand(intent, flags, startId);
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
