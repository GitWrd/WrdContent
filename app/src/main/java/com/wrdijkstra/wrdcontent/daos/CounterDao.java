package com.wrdijkstra.wrdcontent.daos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.provider.BaseColumns;
import android.util.Log;


/**
 * Created by WDijkstra on 30-Jun-17.
 */

public class CounterDao {
    private Context context;
    private DatabaseProps databaseProps;


    public CounterDao(Context context, String databaseName, int databaseVersion, String table, ContentValues keys) {
        this.context = context;
        databaseProps = new DatabaseProps(databaseName, databaseVersion, table, keys);
    }

    public long insert (ContentValues keys) {
        long num;
        SQLiteDatabase db = new DatabaseHelper(context, databaseProps).getWritableDatabase();

        num = db.insert(databaseProps.table,null,keys);
        db.close();

        if (num < 0) {
            Log.w("INSERT", "Failed inserting ID \"" + keys.getAsString(BaseColumns._ID) + "\"" );
        }

        return num;
    }

    public void delete (int id) {
        SQLiteDatabase db = new DatabaseHelper(context, databaseProps).getWritableDatabase();
        String[] selectionArgs = {Integer.toString(id)};
        db.delete( databaseProps.table, BaseColumns._ID + "=?", selectionArgs );

        db.close();
    }

    public int delete(String selection, String[] selectionArgs) {
        SQLiteDatabase db = new DatabaseHelper(context, databaseProps).getWritableDatabase();

        return db.delete( databaseProps.table, selection, selectionArgs );
    }

    public void deleteAll () {
        SQLiteDatabase db = new DatabaseHelper(context, databaseProps).getWritableDatabase();
        db.delete( databaseProps.table, null, null );

        db.close();
    }

    public Cursor query(String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
        qb.setTables( databaseProps.table);
        SQLiteDatabase db = new DatabaseHelper(context, databaseProps).getWritableDatabase();

        Cursor cursor = qb.query(db, projection, selection, selectionArgs, null, null, sortOrder);

        Log.d("CounterDao", "queried records: "+cursor.getCount());
        return cursor;
    }

    public int update(ContentValues contentValues, String whereClause, String[] whereArgs) {
        SQLiteDatabase db = new DatabaseHelper(context, databaseProps).getWritableDatabase();

        return db.update(databaseProps.table, contentValues, whereClause, whereArgs);
    }

    public void deleteDatabase () {
        context.deleteDatabase(databaseProps.databaseName);
    }
}
