package com.nguyenkim.connectors;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;

public class SQLiteConnector {
    String DATABASE_NAME="SalesDatabase.sqlite";
    private static final String DB_PATH_SUFFIX = "/databases/";
    SQLiteDatabase database=null;
    Activity context;


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
    // hàm mở cơ sở dữ liệu SQLite
    public SQLiteDatabase openDatabase() {
        // Slide 10 trang 15 copy dòng database=open...
        database=this.context.openOrCreateDatabase(DATABASE_NAME,
                Activity.MODE_PRIVATE, null);
        return database;
    }

    public SQLiteDatabase getDatabase() {
        return database;
    }

    public void setDatabase(SQLiteDatabase database) {
        this.database = database;
    }

}
