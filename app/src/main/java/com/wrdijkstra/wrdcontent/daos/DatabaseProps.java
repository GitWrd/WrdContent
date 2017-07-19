package com.wrdijkstra.wrdcontent.daos;

import android.content.ContentValues;

/**
 * Created by Wdijkstra on 18-Jul-17.
 */

public class DatabaseProps {
    public final String databaseName;
    public final int databaseVersion;
    public final String table;
    public final ContentValues keys;

    public DatabaseProps(String databaseName, int databaseVersion, String table, ContentValues keys) {
        this.databaseName = databaseName;
        this.databaseVersion = databaseVersion;
        this.table = table;
        this.keys = keys;
    }
}
