package com.wrdijkstra.wrdcontent.daos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.wrdijkstra.wrdcontent.vos.CounterVo;

import java.util.ArrayList;

/**
 * Created by WDijkstra on 30-Jun-17.
 */

public class CounterDao {
    protected static final String DATABASE_NAME = "dbWrd";
    protected static final int DATABASE_VERSION = 5;
    protected static final String TABLE = "Counters";
    protected static final String _ID = "_id";
    protected static final String LABEL = "label";
    protected static final String COUNT = "count";
    protected static final String LOCKED = "locked";

    protected static final String[] KEY_NAMES =   {_ID      , LABEL       , COUNT    , LOCKED   };
    protected static final Class<?>[] KEY_TYPES = {int.class, String.class, int.class, int.class};

    protected static final DatabaseProps DB_PROPS = new DatabaseProps(DATABASE_NAME, DATABASE_VERSION, TABLE, KEY_NAMES, KEY_TYPES);

    private Context context;

    public CounterDao(Context context) {
        this.context = context;
    }

    public ArrayList<CounterVo> getAll() {
        ArrayList<CounterVo> list = new ArrayList<>();
        SQLiteDatabase db = new DatabaseHelper(context, DB_PROPS).getWritableDatabase();
        Cursor cursor = db.query(TABLE, null, null, null, null, null, null );
        while(cursor.moveToNext()) {
            CounterVo counter = new CounterVo();
            counter.setId(cursor.getInt(cursor.getColumnIndex(_ID)));
            counter.setLabel(cursor.getString(cursor.getColumnIndex(LABEL)));
            counter.setCount(cursor.getInt(cursor.getColumnIndex(COUNT)));
            counter.setLocked(cursor.getInt(cursor.getColumnIndex(LOCKED)) == 1);

            list.add(counter);
        }
        cursor.close();
        db.close();
        return list;
    }

    public CounterVo get(int id) {
        SQLiteDatabase db = new DatabaseHelper(context, DB_PROPS).getWritableDatabase();
        String[] selectionArgs = {Integer.toString(id)};
        Cursor cursor = db.query(TABLE, null, _ID + "=?", selectionArgs, null, null, null );
        CounterVo counter = null;
        if(cursor.moveToFirst()) {
            counter = new CounterVo();
            counter.setId(cursor.getInt(cursor.getColumnIndex(_ID)));
            counter.setLabel(cursor.getString(cursor.getColumnIndex(LABEL)));
            counter.setCount(cursor.getInt(cursor.getColumnIndex(COUNT)));
            counter.setLocked(cursor.getInt(cursor.getColumnIndex(LOCKED)) == 1);
        }
        cursor.close();
        db.close();
        return counter;
    }

    public long insert (CounterVo counter) {
        long num;
        SQLiteDatabase db = new DatabaseHelper(context, DB_PROPS).getWritableDatabase();
        ContentValues values = new ContentValues();
        if(counter.getId()>0) {
            values.put(_ID,counter.getId());
        }
        values.put(LABEL, counter.getLabel());
        values.put(COUNT, counter.getCount());
        values.put(LOCKED, counter.isLocked());

        num = db.insert(TABLE,null,values);
        db.close();

        if (num < 0) {
            Log.w("INSERT", "Failed inserting ID \"" + Integer.toString(counter.getId()) + "\"" );
        }

        return num;
    }

    public long update (CounterVo counter) {
        long num;
        String[] selectionArgs = {Integer.toString(counter.getId())};
        SQLiteDatabase db = new DatabaseHelper(context, DB_PROPS).getWritableDatabase();
        ContentValues values = new ContentValues();
        if(counter.getId()>0) {
            values.put(_ID,counter.getId());
        }
        values.put(LABEL, counter.getLabel());
        values.put(COUNT, counter.getCount());
        values.put(LOCKED, counter.isLocked());

        num = db.update(TABLE, values, _ID + "=?", selectionArgs);
        db.close();

        return num;
    }

    public void delete (int id) {
        SQLiteDatabase db = new DatabaseHelper(context, DB_PROPS).getWritableDatabase();
        String[] selectionArgs = {Integer.toString(id)};
        db.delete( TABLE, _ID + "=?", selectionArgs );

        db.close();
    }

    public void delete (CounterVo counter) {
        delete(counter.getId());
    }

    public void deleteAll () {
        SQLiteDatabase db = new DatabaseHelper(context, DB_PROPS).getWritableDatabase();
        db.delete( TABLE, null, null );

        db.close();
    }

    public void deleteDatabase () {
        context.deleteDatabase(DATABASE_NAME);
    }
}
