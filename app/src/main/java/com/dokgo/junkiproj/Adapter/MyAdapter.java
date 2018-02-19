package com.dokgo.junkiproj.Adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dokgo.junkiproj.Activity.DetailActivity;
import com.dokgo.junkiproj.Data.CalData;
import com.dokgo.junkiproj.Data.ListData;
import com.dokgo.junkiproj.Holder.CalHolder;
import com.dokgo.junkiproj.Holder.ListHolder;
import com.dokgo.junkiproj.R;

import java.util.ArrayList;

/**
 * Created by JawsGeun on 2018-01-30.
 */

public class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private ArrayList<ListData> SharedData = new ArrayList<>();
    private ArrayList<CalData> SharedData1 = new ArrayList<>();
    private int type;
    public MyAdapter(ArrayList<ListData> SharedData) {
        this.SharedData=SharedData;
        type = 0;
    }
    public MyAdapter(ArrayList<CalData> SharedData,int a) {
        this.SharedData1=SharedData;
        type = 1;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        switch (type){
            case 0:  View parentView = inflater.inflate(R.layout.layout_detail,parent,false);
                viewHolder = new ListHolder(parentView);
                break;
            case 1:  View parentView1 = inflater.inflate(R.layout.layout_cal,parent,false);
                viewHolder = new CalHolder(parentView1);
                break;
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (type){
            case 0:  final ListHolder listHolder = (ListHolder)holder;
                final String name =SharedData.get(position).getName();
                Log.e("이름",name);
                final String id = SharedData.get(position).getId();
                listHolder.name.setText(name);
                listHolder.addr.setText(SharedData.get(position).getAddr());
                // TODO 홀더에서 이미지뷰 가져온다음 노인 이름에 매치해서 이미지 설정
                if(id.equals("1")) listHolder.img.setImageResource(R.drawable.s2);
                if(id.equals("3")) listHolder.img.setImageResource(R.drawable.s2);
                listHolder.layout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(listHolder.parent.getContext(), DetailActivity.class);
                        intent.putExtra("id",id);
                        listHolder.parent.getContext().startActivity(intent);
                        ((Activity)(listHolder.parent.getContext())).finish();
                    }
                });
                break;
            case 1: CalHolder calHolder = (CalHolder)holder;
            calHolder.name.setText(SharedData1.get(position).getName());
                calHolder.addr.setText(SharedData1.get(position).getAddr());
                calHolder.memo.setText(SharedData1.get(position).getMemo());
                break;
        }
    }

    @Override
    public int getItemCount() {

        switch (type){
            case 0:
                return SharedData.size();
            case 1:
                return SharedData1.size();
            default:
                return 0;
        }
    }
}
