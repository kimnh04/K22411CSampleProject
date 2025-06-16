package com.nguyenkim.connectors;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;

public class SQLiteConnector {
    private static final String DATABASE_NAME = "SalesDatabase.sqlite";
    private static final String DB_PATH_SUFFIX = "/databases/";
    private SQLiteDatabase database = null;
    private Activity context;

    public SQLiteConnector() {
    }

    public SQLiteConnector(Activity context) {
        this.context = context;
    }

    public Activity getContext() {
        return context;
    }

    public void setContext(Activity context) {
        this.context = context;
    }

    // Open the SQLite database
    public SQLiteDatabase openDatabase() {
        if (context == null) {
            throw new IllegalStateException("Context is not set. Please set the context before opening the database.");
        }
        if (database == null || !database.isOpen()) {
            database = context.openOrCreateDatabase(DATABASE_NAME, Activity.MODE_PRIVATE, null);
        }
        return database;
    }

    public SQLiteDatabase getDatabase() {
        if (database == null || !database.isOpen()) {
            throw new IllegalStateException("Database is not opened. Call openDatabase() first.");
        }
        return database;
    }

    public void setDatabase(SQLiteDatabase database) {
        this.database = database;
    }
}