package com.wrdijkstra.wrdcontent.controllers;

import com.wrdijkstra.wrdcontent.daos.DatabaseHelper;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public class WrdContentProvider extends ContentProvider {
    private static final String DBNAME = "wrdDb";
    private static final int COUNTERS = 1;
    private static final int COUNTER_ID = 2;
    private static final UriMatcher URI_MATCHER;

    private DatabaseHelper mDatabaseHelper;
    private SQLiteDatabase db;

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
        // Implement this to handle requests to delete one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
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
        // TODO: Implement this to handle requests to insert a new row.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public boolean onCreate() {
        mDatabaseHelper = new DatabaseHelper(
                getContext(),        // the application context
                DBNAME
        );

        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        return null;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }
}
