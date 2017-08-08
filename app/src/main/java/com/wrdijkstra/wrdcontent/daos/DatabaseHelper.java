package com.wrdijkstra.wrdcontent.daos;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;


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


        Set<Map.Entry<String, Object>> s=databaseProps.keys.valueSet();
        Iterator itr = s.iterator();

        while(itr.hasNext()) {
            Map.Entry me = (Map.Entry)itr.next();
            String key = me.getKey().toString();
            String value =  me.getValue().toString();

            sqlCreateDb = sqlCreateDb + key + " " + value;
            if (itr.hasNext())
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
