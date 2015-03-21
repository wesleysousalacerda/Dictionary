package com.example.hiltonwesley.dictionary;
import android.content.ContentResolver;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.UserDictionary;
import android.provider.UserDictionary.Words;
import android.support.v7.app.ActionBarActivity;
import android.widget.ListView;
import android.support.v4.widget.SimpleCursorAdapter;

/**
 * This is the central activity for the Provider Dictionary Example App. The purpose of this app is
 * to show an example of accessing the {@link Words} list via its' Content Provider.
 */
public class MainActivity extends ActionBarActivity {

    // For the SimpleCursorAdapter to match the UserDictionary columns to layout items.
    private static final String[] COLUMNS_TO_BE_BOUND  = new String[] {
            UserDictionary.Words.WORD,
            UserDictionary.Words.FREQUENCY
    };

    private static final int[] LAYOUT_ITEMS_TO_FILL = new int[] {
            android.R.id.text1,
            android.R.id.text2
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get the TextView which will be populated with the Dictionary ContentProvider data.
        ListView dictListView = (ListView) findViewById(R.id.dictionary_list_view);

        // Get the ContentResolver which will send a message to the ContentProvider.
        ContentResolver resolver = getContentResolver();

        // Get a Cursor containing all of the rows in the Words table.
        Cursor cursor = resolver.query(UserDictionary.Words.CONTENT_URI, null, null, null, null);

        // Set the Adapter to fill the standard two_line_list_item layout with data from the Cursor.
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,
                android.R.layout.two_line_list_item,
                cursor,
                COLUMNS_TO_BE_BOUND,
                LAYOUT_ITEMS_TO_FILL,
                0);

        // Attach the adapter to the ListView.
        dictListView.setAdapter(adapter);
    }
}
