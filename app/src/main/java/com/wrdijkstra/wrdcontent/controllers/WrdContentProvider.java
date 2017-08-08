package com.wrdijkstra.wrdcontent.controllers;

import com.wrdijkstra.wrdcontent.daos.CounterDao;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public class WrdContentProvider extends ContentProvider {
    protected static final String DATABASE_NAME = "dbWrd";
    protected static final int DATABASE_VERSION = 7;
    protected static final String DATABASE_TABLE = "Counters";

    private static final int COUNTERS = 1;
    private static final int COUNTER_ID = 2;
    private static final UriMatcher URI_MATCHER;

    private CounterDao counterDao;

    static {
        URI_MATCHER = new UriMatcher(UriMatcher.NO_MATCH);
        URI_MATCHER.addURI(WrdContentContract.AUTHORITY,
                "counters",
                COUNTERS);
        URI_MATCHER.addURI(WrdContentContract.AUTHORITY,
                "counters/#",
                COUNTER_ID);
    }

    public WrdContentProvider() {
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        int count;

        if ( uri.equals(WrdContentContract.Counters.CONTENT_URI) == true) {
            count = counterDao.delete(selection,selectionArgs);
        }
        else {
            throw new IllegalArgumentException("Unsupported URI: " + uri);
        }

        getContext().getContentResolver().notifyChange(uri,null);
        return count;
    }

    @Override
    public String getType(Uri uri) {
        switch (URI_MATCHER.match(uri)) {
            /**
             * Get all counters records
             */
            case COUNTERS:
                return WrdContentContract.Counters.CONTENT_TYPE;
            /**
             * Get a particular counter
             */
            case COUNTER_ID:
                return WrdContentContract.Counters.CONTENT_ITEM_TYPE;
            default:
                throw new IllegalArgumentException("Unsupported URI: " + uri);
        }
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        if ( uri.equals(WrdContentContract.Counters.CONTENT_URI) == true) {
            counterDao.insert(values);
            getContext().getContentResolver().notifyChange(uri,null);
            return WrdContentContract.Counters.CONTENT_URI;
        }
        else {
            return null;
        }

    }

    @Override
//    public CounterDao(Context context, String databaseName, int databaseVersion, String table, ContentValues keys) {
    public boolean onCreate() {
        ContentValues keys = new ContentValues();
        keys.put(WrdContentContract.CommonColumns._ID   , "INTEGER PRIMARY KEY");
        keys.put(WrdContentContract.CommonColumns.LABEL , "TEXT"   );
        keys.put(WrdContentContract.CommonColumns._COUNT , "INTEGER");
        keys.put(WrdContentContract.CommonColumns.LOCKED, "INTEGER");

        counterDao = new CounterDao(
                getContext(),        // the application context
                DATABASE_NAME,
                DATABASE_VERSION,
                DATABASE_TABLE,
                keys );

        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        if ( uri.equals(WrdContentContract.Counters.CONTENT_URI) == true) {
            return counterDao.query(projection, selection, selectionArgs, sortOrder);
        }
        else {
            return null;
        }
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String s, @Nullable String[] strings) {
        int count;

        if ( uri.equals(WrdContentContract.Counters.CONTENT_URI) == true) {
            count = counterDao.update(contentValues, s, strings);
        }
        else {
            throw new IllegalArgumentException("Unsupported URI: " + uri);
        }

        getContext().getContentResolver().notifyChange(uri,null);
        return count;
    }
}
