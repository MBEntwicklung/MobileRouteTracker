package de.mbentwicklung.android.mobileroutetracker.cache;

import static de.mbentwicklung.android.mobileroutetracker.MRTConstants.POSITION_CACHE_TABLE_NAME;
import static de.mbentwicklung.android.mobileroutetracker.MRTConstants.Params.ACCURACY;
import static de.mbentwicklung.android.mobileroutetracker.MRTConstants.Params.ALTITUDE;
import static de.mbentwicklung.android.mobileroutetracker.MRTConstants.Params.BEARING;
import static de.mbentwicklung.android.mobileroutetracker.MRTConstants.Params.LATITUDE;
import static de.mbentwicklung.android.mobileroutetracker.MRTConstants.Params.LONGITUDE;
import static de.mbentwicklung.android.mobileroutetracker.MRTConstants.Params.TIME;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

/**
 * Cache für Zwischenspeichern aller Positionen
 * 
 * @author Marc Bellmann <marc.bellmann@mb-entwicklung.de>
 */
public class PositionCache extends SQLiteOpenHelper {

	private static final String DOUBLE_NOT_NULL = " double NOT NULL ";
	private static final String CREATE_TABLE = "CREATE TABLE " + POSITION_CACHE_TABLE_NAME + " ("
			+ BaseColumns._ID + " integer NOT NULL PRIMARY KEY" + ", " 
			+ LONGITUDE + DOUBLE_NOT_NULL + ", " 
			+ LATITUDE + DOUBLE_NOT_NULL + ", " 
			+ ALTITUDE + DOUBLE_NOT_NULL + ", " 
			+ TIME + " long not null " + ", " 
			+ ACCURACY + " float not null" + ", " 
			+ BEARING + " float not null" + ");";

	private static final String DELETE_TABLE = "DROP TABLE IF EXISTS " + POSITION_CACHE_TABLE_NAME;
	private static final int DATABASE_VERSION = 3;

	public PositionCache(Context context) {
		super(context, POSITION_CACHE_TABLE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(CREATE_TABLE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		if (oldVersion < newVersion) {
			db.execSQL(DELETE_TABLE);
			db.execSQL(CREATE_TABLE);
		}
	}

	public void insertNewPosition(final Position position) {
		final ContentValues contentValues = position.toContentValues();
		getWritableDatabase().insert(POSITION_CACHE_TABLE_NAME, null, contentValues);
	}

	public List<Position> getCachedPositions() {
		final List<Position> cachedPositions = new ArrayList<Position>();
		Cursor cursor = getReadableDatabase().query(POSITION_CACHE_TABLE_NAME, null, null, null,
				null, null, null);
		while (cursor.moveToNext()) {
			final Position position = toPosition(cursor);
			cachedPositions.add(position);
		}
		cursor.close();
		return cachedPositions;
	}

	public void removePostion(final Position position) {
		getWritableDatabase().delete(POSITION_CACHE_TABLE_NAME,
				BaseColumns._ID + " = " + position.getId(), new String[0]);
	}

	/**
	 * @param cursor
	 * @param columnIndex
	 * @return
	 */
	private Position toPosition(Cursor cursor) {
		int columnIndex = 0;
		return new Position(cursor.getInt(columnIndex++), cursor.getDouble(columnIndex++),
				cursor.getDouble(columnIndex++), cursor.getDouble(columnIndex++),
				cursor.getLong(columnIndex++), cursor.getFloat(columnIndex++),
				cursor.getFloat(columnIndex++));
	}

}
