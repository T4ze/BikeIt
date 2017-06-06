package com.epita.mti.bikeit;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.epita.mti.bikeit.models.Record;
import com.epita.mti.bikeit.models.Station;

/**
 * Created by T4ze on 27/05/2017.
 */

public class PlaceHolderFragment extends Fragment {

    private Record mItem;

    public PlaceHolderFragment() {
        mItem = new Record();
    }

    public void setStation(Station station) {
        mItem = station.getRecord();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.station_detail, container, false);



        TextView station_id = (TextView) rootView.findViewById(R.id.station_id);
        TextView station_name = (TextView) rootView.findViewById(R.id.station_name);
        ImageView mStatusView = (ImageView) rootView.findViewById(R.id.status);
        TextView available_bike_stands = (TextView) rootView.findViewById(R.id.available_bike_stands);
        TextView bike_stands = (TextView) rootView.findViewById(R.id.bike_stands);
        TextView address = (TextView) rootView.findViewById(R.id.address);
        TextView last_update = (TextView) rootView.findViewById(R.id.last_update);

        String[] stationName = mItem.getFields().getName().split(" - ");
        station_id.setText(stationName[0]);
        station_name.setText(stationName[1]);
        bike_stands.setText(String.valueOf(mItem.getFields().getBikeStands()));
        available_bike_stands.setText(String.valueOf(mItem.getFields().getAvailableBikeStands()));
        address.setText(TextUtils.join("\n", mItem.getFields().getAddress().split(" - ")));
        address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Double lat = mItem.getGeometry().getCoordinates().get(1);
                Double lng = mItem.getGeometry().getCoordinates().get(0);
                Uri gmmIntentUri = Uri.parse(String.format("geo:%.0f,%.0f?q=%.0f,%.0f(%s)", lat, lng, lat, lng, mItem.getFields().getName()));
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);
            }
        });
        String date = mItem.getFields().getLastUpdate();
        last_update.setText(date.replaceAll("([0-9]{4})-([0-9]{2})-([0-9]{2})T([:0-9]+)\\+(.*)", "$4 $3/$2/$1"));
        System.out.println(date.replaceAll("([0-9]{4})-([0-9]{2})-([0-9]{2})T([:0-9]+)\\+(.*)", "$4 $3/$2/$1"));

        if (mItem.getFields().getStatus().equals("CLOSED")) {
            int colorRed = ContextCompat.getColor(mStatusView.getContext(), R.color.error);
            mStatusView.setColorFilter(colorRed);
        } else {
            int greenRed = ContextCompat.getColor(mStatusView.getContext(), R.color.success);
            mStatusView.setColorFilter(greenRed);
        }



        FloatingActionButton fab = (FloatingActionButton) rootView.findViewById(R.id.fab);
        System.out.println(fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sharingIntent = new Intent(Intent.ACTION_SEND);
                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Station velib");
                sharingIntent.putExtra(Intent.EXTRA_TEXT, mItem.getFields().getName());
                sharingIntent.setType("text/plain");
                startActivity(Intent.createChooser(sharingIntent, getResources().getText(R.string.share_to)));
            }
        });

        return rootView;
    }
}