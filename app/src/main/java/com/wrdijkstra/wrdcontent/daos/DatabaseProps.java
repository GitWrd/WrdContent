package com.wrdijkstra.wrdcontent.daos;

/**
 * Created by Wdijkstra on 18-Jul-17.
 */

public class DatabaseProps {
    public final String databaseName;
    public final int databaseVersion;
    public final String table;
    public final String[] keyNames;
    public final Class<?>[] keyTypes;

    public DatabaseProps(String databaseName, int databaseVersion, String table, String[] keyNames, Class<?>[] keyTypes) {
        this.databaseName = databaseName;
        this.databaseVersion = databaseVersion;
        this.table = table;
        this.keyNames = keyNames;
        this.keyTypes = keyTypes;
    }
}
