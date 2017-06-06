package com.epita.mti.bikeit;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.ActionBar;
import android.view.MenuItem;

import com.epita.mti.bikeit.models.Record;
import com.epita.mti.bikeit.models.Station;

import java.util.ArrayList;
import java.util.List;


/**
 * An activity representing a single Station detail screen. This
 * activity is only used narrow width devices. On tablet-size devices,
 * item details are presented side-by-side with a list of items
 * in a {@link StationListActivity}.
 */
public class StationDetailActivity extends AppCompatActivity {

    private ViewPager mViewPager;
    private StationsPagerAdapter mPagerAdapter;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_station_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.detail_toolbar);
        setSupportActionBar(toolbar);

        final Intent intent = getIntent();
        final int itemId = (intent != null && intent.hasExtra("itemId")) ? Integer.parseInt(getIntent().getExtras().getString("itemId")) : 0;

        List<Station> data = SimpleItemRecyclerViewAdapter.mValues;
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mPagerAdapter = new StationsPagerAdapter(getSupportFragmentManager(), data);
        mViewPager.setAdapter(mPagerAdapter);
        mViewPager.setCurrentItem(itemId);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.detail_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_group:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage(R.string.group_alert)
                        .setCancelable(false)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
                builder.create().show();
                return true;
            case android.R.id.home:
                navigateUpTo(new Intent(this, StationListActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
