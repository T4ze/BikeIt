package com.epita.mti.bikeit;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.epita.mti.bikeit.models.Record;
import com.epita.mti.bikeit.models.Station;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Created by T4ze on 24/05/2017.
 */
public class SimpleItemRecyclerViewAdapter
        extends RecyclerView.Adapter<ViewHolder> {

    public static List<Station> mValues;
    public static List<Station> sValues;

    public SimpleItemRecyclerViewAdapter() {
        mValues = new ArrayList<>();
        sValues = new ArrayList<>();
    }

    public void setData(List<Record> myDataset) {
        mValues = new ArrayList<>();
        int index = 0;
        for (Record r: myDataset) {
            mValues.add(new Station(index++, r));
        }
        setSearch("");
    }

    public void setSearch(String query) {
        sValues = new ArrayList<>();
        for (Station s: mValues) {
            if(query.isEmpty() || Pattern.compile(Pattern.quote(query), Pattern.CASE_INSENSITIVE).matcher(s.getRecord().getFields().getName()).find()){
                sValues.add(s);
            }
        }
        notifyDataSetChanged();
    }

    public static Station getValue(int position) {
        return sValues.get(position);
    }

    @Override
    public com.epita.mti.bikeit.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.station_list_content, parent, false);
        return new com.epita.mti.bikeit.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final com.epita.mti.bikeit.ViewHolder holder, int position) {
        holder.mItem = sValues.get(position);
        holder.mContentView.setText(holder.mItem.getRecord().getFields().getName());

        if (holder.mItem.getRecord().getFields().getStatus().equals("CLOSED")) {
            holder.mStatusView.setImageResource(R.drawable.ic_lock_outline_black_24dp);
            int colorRed = ContextCompat.getColor(holder.mStatusView.getContext(), R.color.error);
            holder.mStatusView.setColorFilter(colorRed);
        } else {
            holder.mStatusView.setImageResource(R.drawable.ic_lock_open_black_24dp);
            int greenRed = ContextCompat.getColor(holder.mStatusView.getContext(), R.color.success);
            holder.mStatusView.setColorFilter(greenRed);
        }

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                Intent i = new Intent(context, StationDetailActivity.class);
                i.putExtra("itemId", String.valueOf(holder.mItem.getId()));
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return sValues.size();
    }

}
