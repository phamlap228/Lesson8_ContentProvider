package lap.hou.com.lesson8_contentprovinder;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Lap on 16/10/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper{
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Book.db";
    private static final String SQL_CREATE_BOOKS = "CREATE TABLE "
            + BookContract.BookEntry.TABLE_NAME
            + " ("
            + BookContract.BookEntry._ID
            + " INTEGER PRIMARY KEY,"
            + BookContract.BookEntry.COLUMN_NAME
            + " TEXT,"
            + BookContract.BookEntry.COLUMN_TITLE
            + " TEXT,";
    private static final String SQL_DELETE_BOOKS =
            "DROP TABLE IF EXISTS " + BookContract.BookEntry.TABLE_NAME;

    public DatabaseHelper(Context context) {
        super(context,  DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_CREATE_BOOKS);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(SQL_DELETE_BOOKS);
        sqLiteDatabase.execSQL(SQL_CREATE_BOOKS);

    }
}
