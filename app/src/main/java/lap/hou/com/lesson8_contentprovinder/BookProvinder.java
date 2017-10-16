package lap.hou.com.lesson8_contentprovinder;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by Lap on 16/10/2017.
 */

public class BookProvinder extends ContentProvider {
    public static final String AUTHORITY = "lap.hou.com.lesson8_contentprovinder";
    public static final String SCHEME = "content://";
    public static final String URL_DATA_BASE = SCHEME + AUTHORITY + "/book";
    public static final Uri CONTENT_URI = Uri.parse(URL_DATA_BASE);
    private static final int URI_BOOK = 0;

    private static UriMatcher sUriMatcher;

    static {
        sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        sUriMatcher.addURI(AUTHORITY, "book", URI_BOOK);
    }

    private DatabaseHelper mDatabaseHelper;
    private SQLiteDatabase mSqLiteDatabase;

    @Override
    public boolean onCreate() {
        mDatabaseHelper = new DatabaseHelper(getContext());
        return false;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection,
                        @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        mSqLiteDatabase = mDatabaseHelper.getWritableDatabase();
        switch (sUriMatcher.match(uri)) {
            case URI_BOOK:
                return mSqLiteDatabase.query(BookContract.BookEntry.TABLE_NAME,
                        projection, selection, selectionArgs, sortOrder, null, null);
            case UriMatcher.NO_MATCH:
            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        long index;
        mSqLiteDatabase = mDatabaseHelper.getWritableDatabase();
        switch (sUriMatcher.match(uri)) {
            case URI_BOOK:
                index = mSqLiteDatabase.insert(BookContract.BookEntry.TABLE_NAME,
                        null, values);
                Uri uri_ = null;
                if (index != -1) {
                    uri_ = ContentUris.withAppendedId(CONTENT_URI, index);
                }
                return uri_;
            case UriMatcher.NO_MATCH:
            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        mSqLiteDatabase = mDatabaseHelper.getWritableDatabase();
        switch (sUriMatcher.match(uri)) {
            case URI_BOOK:
                return mSqLiteDatabase.delete(BookContract.BookEntry.TABLE_NAME,
                        selection, selectionArgs);
            case UriMatcher.NO_MATCH:
            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        switch (sUriMatcher.match(uri)) {
            case URI_BOOK:
                return mSqLiteDatabase.update(BookContract.BookEntry.TABLE_NAME, values,
                        selection, selectionArgs);
            case UriMatcher.NO_MATCH:
            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }
    }
}
