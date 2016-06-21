package com.ebay.lockers.storage;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

/**
 * Created by sudavid on 6/20/2016.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "data.db";
    private static final String TABLE_DATA = "data";

    // columns in table data
    private static final String USER_ID = "userid";
    private static final String ITEM_NAME = "item";
    private static final String ITEM_DESCRIPTION = "description";
    private static final String ITEM_TAGS = "tags";
    private static final String LOCATION_OF_IMAGES = "images";

    // create the table
    private static final String CREATE_TABLE_DATA = "CREATE TABLE " + TABLE_DATA + " ("
                    + BaseColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + USER_ID
                    + " TEXT NOT NULL, " + ITEM_NAME + " TEXT NOT NULL, " + ITEM_DESCRIPTION
                    + " TEXT NOT NULL, " + ITEM_TAGS + " TEXT, " + LOCATION_OF_IMAGES
                    + " TEXT NOT NULL);";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(CREATE_TABLE_DATA);
        onCreate(database);
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int i1, int i2) {
        database.execSQL("DROP TABLE IF EXISTS " + TABLE_DATA);
        onCreate(database);
    }
}
