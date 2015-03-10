package com.va.dtcandroid;

import java.io.*;
import java.util.*;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.*;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.*;


public class MainActivity extends ActionBarActivity {
    ListView collectionsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        collectionsList = (ListView)findViewById(R.id.list);

        try {
            InputStream inputStream = this.getResources().getAssets().open("catalog.json");
            JsonReader reader = new JsonReader(new InputStreamReader(inputStream, "UTF-8"));
            Catalog catalog = Catalog.parse(reader);

            List<String> collectionTitles = new ArrayList<String>();

            for (int i = 0; i < catalog.getCollections().size(); i++)
            {
                collectionTitles.add(catalog.getCollections().get(i).getTitle());
            }

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                collectionTitles);
            collectionsList.setAdapter(adapter);

            // Log.d(MainActivity.class.getSimpleName(), catalog.toString());
        } catch (Exception e) {
            Log.d(MainActivity.class.getSimpleName(), String.format("Couldn't load the json file: %s", e.getMessage()));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
