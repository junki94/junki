package com.dokgo.junkiproj.Holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dokgo.junkiproj.R;

/**
 * Created by JawsGeun on 2018-01-30.
 */

public class ListHolder extends RecyclerView.ViewHolder {
    public TextView name, addr, id;
    public ImageView img;

    public LinearLayout layout;
    public View parent;
    public ListHolder(View itemView) {
        super(itemView);
        name = (TextView)itemView.findViewById(R.id.list_name);
        addr = (TextView)itemView.findViewById(R.id.list_addr);
        id=(TextView)itemView.findViewById(R.id.list_id);
        img = (ImageView)itemView.findViewById(R.id.list_img);
        layout = (LinearLayout)itemView.findViewById(R.id.list_layout);
        parent = itemView;
    }
}
