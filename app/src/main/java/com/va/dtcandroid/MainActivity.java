package com.va.dtcandroid;

import java.io.*;
import java.util.*;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.*;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;

public class MainActivity extends FragmentActivity {
    private ListView mCollectionsList;
    private Catalog mCatalog;

    public Catalog getCatalog() { return mCatalog; }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mCollectionsList = (ListView)findViewById(R.id.list);

        try {
            InputStream inputStream = this.getResources().getAssets().open("catalog.json");
            JsonReader reader = new JsonReader(new InputStreamReader(inputStream, "UTF-8"));
            mCatalog = Catalog.parse(reader);
            initCollectionsList();
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

    private void initCollectionsList()
    {
        List<String> collectionTitles = new ArrayList<String>();

        for (int i = 0; i < mCatalog.getCollections().size(); i++)
        {
            collectionTitles.add(mCatalog.getCollections().get(i).getTitle());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                collectionTitles);
        mCollectionsList.setAdapter(adapter);

        final MainActivity mainActivity = this;

        mCollectionsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                Intent intent = new Intent(mainActivity, CollectionViewActivity.class);
                intent.putExtra("selectedCollection", mCatalog.getCollections().get(position));
                startActivity(intent);
            }
        });
    }
}
