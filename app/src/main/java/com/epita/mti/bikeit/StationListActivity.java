package com.epita.mti.bikeit;

import android.app.SearchManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;


import com.epita.mti.bikeit.models.Data;
import com.epita.mti.bikeit.models.Record;
import com.epita.mti.bikeit.models.Station;


import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * An activity representing a list of Stations. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link StationDetailActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
public class StationListActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private static List<Record> previousData = new ArrayList<>();

    private RecyclerView mRecyclerView;
    private SimpleItemRecyclerViewAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_station_list);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());

        mRecyclerView = (RecyclerView)findViewById(R.id.station_list);
        assert mRecyclerView != null;

        // Tablet mode
        mAdapter = new SimpleItemRecyclerViewAdapter();
        mAdapter.setData(previousData);
        mRecyclerView.setAdapter(mAdapter);


        // CHECK CONNEXION
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            // connected
        } else {
            // display error
            System.out.println("pas de connexion");
            return;
        }

        // CALL REQUEST
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(VelibService.ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        VelibService velibService = retrofit.create(VelibService.class);

        Call<Data> permissionList = velibService.getVelibData();
        permissionList.enqueue(new Callback<Data>() {
            @Override
            public void onResponse(Call<Data> call, Response<Data> response) {
                if (response.isSuccessful()) {
                    Data data = response.body();

                    mAdapter.setData(data.getRecords());
                    mAdapter.notifyDataSetChanged();
                    previousData = data.getRecords();
                } else {
                    // Error
                }
            }

            @Override
            public void onFailure(Call<Data> call, Throwable t) {
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options_menu, menu);

        SearchView searchView = (SearchView)menu.findItem(R.id.search_action).getActionView();
        searchView.setOnQueryTextListener(this);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.group_action:
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
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        mAdapter.setSearch(query);
        return true;
    }

    @Override
    public boolean onQueryTextChange(String query) {
        mAdapter.setSearch(query);
        return true;
    }

}
