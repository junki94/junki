package com.dokgo.junkiproj.Holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dokgo.junkiproj.R;

/**
 * Created by user on 2018-02-09.
 */

public class CalHolder extends RecyclerView.ViewHolder{
    public TextView name, addr, memo;
    public ImageView img;
    public LinearLayout layout;
    public View parent;
    public CalHolder(View itemView) {
        super(itemView);
        name = (TextView)itemView.findViewById(R.id.cal_name);
        addr = (TextView)itemView.findViewById(R.id.cal_addr);
        memo = (TextView)itemView.findViewById(R.id.cal_memo);
        img = (ImageView)itemView.findViewById(R.id.cal_img);
        layout = (LinearLayout)itemView.findViewById(R.id.cal_layout);
        parent = itemView;
    }
}

