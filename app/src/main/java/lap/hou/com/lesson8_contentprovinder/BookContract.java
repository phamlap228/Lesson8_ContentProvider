package lap.hou.com.lesson8_contentprovinder;

import android.provider.BaseColumns;

/**
 * Created by Lap on 16/10/2017.
 */

class BookContract {
    public BookContract() {
    }
    public static class BookEntry implements BaseColumns {
        public static final String TABLE_NAME = "tbl_book";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_TITLE = "title";
    }
}
