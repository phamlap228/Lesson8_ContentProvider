package lap.hou.com.lesson8_contentprovinder;

import android.content.ContentValues;
import android.database.Cursor;


/**
 * Created by Lap on 16/10/2017.
 */

public class Book {
    private int mID;
    private String mName;
    private String mTitle;


    public Book (String name, String title) {
        mName = name;
        mTitle = title;
    }
    public Book (Cursor cursor){
        mName=cursor.getString(cursor.getColumnIndex(BookContract.BookEntry.COLUMN_NAME));
        mTitle =cursor.getString(cursor.getColumnIndex(BookContract.BookEntry.COLUMN_TITLE));
        mID=cursor.getInt(cursor.getColumnIndex(BookContract.BookEntry._ID));

    }

    public int getID() {
        return mID;
    }

    public void setID(int ID) {
        mID = ID;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }
    public ContentValues getContentValues() {
        ContentValues contentValues = new ContentValues();
        if (mName != null) {
            contentValues.put(BookContract.BookEntry.COLUMN_NAME, mName);
        }
        if (mTitle != null) {
            contentValues.put(BookContract.BookEntry.COLUMN_TITLE, mTitle);
        }
        if (mID != 0) {
            contentValues.put(BookContract.BookEntry._ID, mID);
        }
        return contentValues;
    }
    @Override
    public String toString() {
        return + mID + " -- "+getName() ;
    }
}
