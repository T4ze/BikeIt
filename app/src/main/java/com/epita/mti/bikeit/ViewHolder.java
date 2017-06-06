package com.epita.mti.bikeit;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.epita.mti.bikeit.models.Station;


/**
 * Created by T4ze on 24/05/2017.
 */

public class ViewHolder extends RecyclerView.ViewHolder {

    public final View mView;
    public final TextView mContentView;
    public final ImageView mStatusView;
    public Station mItem;

    public ViewHolder(View view) {
        super(view);
        mView = view;
        mContentView = (TextView) view.findViewById(R.id.content);
        mStatusView = (ImageView) view.findViewById(R.id.status);
    }

    @Override
    public String toString() {
        return super.toString() + " '" + mContentView.getText() + "'";
    }
}
