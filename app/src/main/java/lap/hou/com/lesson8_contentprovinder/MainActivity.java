package lap.hou.com.lesson8_contentprovinder;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private List<Book> mBooks;
    private ArrayAdapter<Book> mAdapter;
    private ListView mListViewBook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBooks =getData();
        mListViewBook =(ListView)findViewById(R.id.list_book);

        findViewById(R.id.button_add_book).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = new Random().nextInt(10000);
                Book book = new Book("Name " + id, "Title " + id);
                if (getContentResolver().insert(BookProvinder.CONTENT_URI, book.getContentValues()) != null) {
                    // reload data
                    mBooks.clear();
                    mBooks.addAll(getData());
                    mAdapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(MainActivity.this, "Insert failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
        mAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, mBooks);
        mListViewBook.setAdapter(mAdapter);
    }
    private List<Book> getData() {
        List<Book> books = new ArrayList<>();
        Cursor cursor = getContentResolver().query(BookProvinder.CONTENT_URI, null, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            do {
                books.add(new Book(cursor));
            } while (cursor.moveToNext());
        }
        return books;
    }
}
