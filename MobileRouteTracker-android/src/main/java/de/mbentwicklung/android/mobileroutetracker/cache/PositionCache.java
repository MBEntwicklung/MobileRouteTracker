package de.mbentwicklung.android.mobileroutetracker.cache;

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
	private static final int DATABASE_VERSION = 2;
	private static final String TABLE_NAME = "position_cache";

	public static final String LON = "lon";
	public static final String LAT = "lat";
	public static final String ELE = "ele";
	public static final String TIME = "time";

	public PositionCache(Context context) {
		super(context, TABLE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("CREATE TABLE " + TABLE_NAME + " (" + 
				BaseColumns._ID + " integer NOT NULL PRIMARY KEY" + ", " + 
				LON + DOUBLE_NOT_NULL + ", " + 
				LAT + DOUBLE_NOT_NULL + ", " + 
				ELE + DOUBLE_NOT_NULL + ", " + 
				TIME + " long not null "
				+ ");");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}

	public void insertNewPosition(final Position position) {
		final ContentValues values = new ContentValues();
		values.put(LON, position.getLon());
		values.put(LAT, position.getLat());
		values.put(ELE, position.getEle());
		values.put(TIME, position.getTime());
		
		getWritableDatabase().insert(TABLE_NAME, null, values);
	}
	
	public List<Position> getCachedPositions() {
		final List<Position> cachedPositions = new ArrayList<Position>();
		Cursor cursor = getReadableDatabase().query(TABLE_NAME, null, null, null, null, null, null);
		while (cursor.moveToNext()) {
			cachedPositions.add(new Position(
					cursor.getInt(0),
					cursor.getDouble(1), 
					cursor.getDouble(2), 
					cursor.getDouble(3), 
					cursor.getLong(4)
					));
		}
		cursor.close();
		return cachedPositions;
	}

	public void removePostion(final Position position) {
		getWritableDatabase().delete(TABLE_NAME, BaseColumns._ID + " = " + position.getId(), new String[0]);
	}
}
