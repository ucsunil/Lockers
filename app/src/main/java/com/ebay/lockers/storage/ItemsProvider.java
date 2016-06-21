package com.ebay.lockers.storage;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;

public class ItemsProvider extends ContentProvider {

    private static final String TAG = "ItemsProvider";

    private DatabaseHelper helper;

    private static final String AUTHORITY = "com.ebay.lockers.storage.items";
    private static final UriMatcher MATCHER;

    private static final String TABLE_DATA = "data";
    private static final int TABLE_DATA_MATCH = 1;
    static {
        MATCHER = new UriMatcher(UriMatcher.NO_MATCH);

        // to match the table itself
        // we do not need individual rows for now
        MATCHER.addURI(AUTHORITY, TABLE_DATA, TABLE_DATA_MATCH);
    }

    public ItemsProvider() {
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        // Implement this to handle requests to delete one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public String getType(Uri uri) {
        // TODO: Implement this to handle requests for the MIME type of the data
        // at the given URI.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        Uri position = null;
        long rowId = 0;

        switch (MATCHER.match(uri)) {
            case TABLE_DATA_MATCH:
                rowId = helper.getWritableDatabase().insert(TABLE_DATA, null, values);
                if(rowId > 0) {
                    position = ContentUris.withAppendedId(uri, rowId);
                    getContext().getContentResolver().notifyChange(position, null);
                }
                break;

            default:
                // do nothing
        }
        if(rowId <= 0) {
            throw new SQLException("Error in writing to database");
        }
        return position;
    }

    @Override
    public boolean onCreate() {
        helper = new DatabaseHelper(getContext());
        return (helper == null) ? true : false;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
        Cursor cursor = null;

        switch (MATCHER.match(uri)) {
            case TABLE_DATA_MATCH:
                qb.setTables(TABLE_DATA);
                cursor = qb.query(helper.getReadableDatabase(), projection, selection,
                            selectionArgs, null, null, sortOrder);
                return cursor;

            default:
                throw new SQLException("The data being requested is not valid");
        }
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        // TODO: Implement this to handle requests to update one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
