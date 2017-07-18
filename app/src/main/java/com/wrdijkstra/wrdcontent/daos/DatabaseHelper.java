package com.wrdijkstra.wrdcontent.daos;

import android.app.Activity;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.List;


/**
 * Created by WDijkstra on 27-Jun-17.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    private Context context;
    private DatabaseProps databaseProps;

    public DatabaseHelper (Context context, DatabaseProps databaseProps ) {
        super(context, databaseProps.databaseName, null, databaseProps.databaseVersion);
        this.context = context;
        this.databaseProps = databaseProps;
    }

    public void onCreate(SQLiteDatabase db) {
        String sqlCreateDb = "CREATE TABLE " +
                databaseProps.table +      // Table's name
                " ( ";                     // The columns in the table

        for (int i=0; i<databaseProps.keyNames.length; i++ ) {
            if (databaseProps.keyTypes[i] == int.class) {
                sqlCreateDb = sqlCreateDb + databaseProps.keyNames[i] + " INTEGER";
            }
            else {
                sqlCreateDb = sqlCreateDb + databaseProps.keyNames[i] + " TEXT";
            }

            if (i==0) {
                // First key will be used as "primary key"
                sqlCreateDb = sqlCreateDb + " PRIMARY KEY";
            }
            if ((i + 1) < databaseProps.keyNames.length)
            {
                // Add comma, when more keys available
                sqlCreateDb = sqlCreateDb + ", ";
            }
        }
        sqlCreateDb = sqlCreateDb + " )";

        // Creates the main table
        db.execSQL(sqlCreateDb);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // For now, on upgrade, delete database and create new one
        context.deleteDatabase(databaseProps.databaseName);
        onCreate(db);
    }
}
