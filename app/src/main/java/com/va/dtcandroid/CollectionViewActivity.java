package com.va.dtcandroid;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.*;


public class CollectionViewActivity extends FragmentActivity {
    private Collection _collection;
    private ViewPager _pager;
    private PiecesPageAdapter _piecesPageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collection_view);
        Intent intent = getIntent();
        _collection = intent.getParcelableExtra("selectedCollection");
        _piecesPageAdapter = new PiecesPageAdapter(getSupportFragmentManager(), _collection);
        _pager = (ViewPager)findViewById(R.id.piecesPager);
        _pager.setAdapter(_piecesPageAdapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_collection_view, menu);
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
