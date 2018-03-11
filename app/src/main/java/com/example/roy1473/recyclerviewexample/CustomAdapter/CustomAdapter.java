package com.example.roy1473.recyclerviewexample.CustomAdapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.roy1473.recyclerviewexample.Interface.OnItemClickListener;
import com.example.roy1473.recyclerviewexample.Models.Shop;
import com.example.roy1473.recyclerviewexample.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {


    private List<Shop> resultData;
    private Context mContext;
    private int rowLayout;
    private OnItemClickListener mClickListener;

    public CustomAdapter(List<Shop> resultData, int rowLayout, Context context) {
        this.resultData = resultData;
        this.rowLayout = rowLayout;
        this.mContext = context;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(rowLayout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewholder, final int position) {
        viewholder.item_text.setText(resultData.get(position).getName());
        Picasso.with(mContext).load(resultData.get(position).getPhoto().getMobile().getS())
                .into(viewholder.item_pic);

    }

    @Override
    public int getItemCount() {
        return resultData.size();
    }

    public void setmClickListener(OnItemClickListener mClickListener) {
        this.mClickListener = mClickListener;
    }





    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView item_text;
        private ImageView item_pic;



        public ViewHolder(View itemView) {
            super(itemView);
            item_text = (TextView)itemView.findViewById(R.id.item_text);
            item_pic = (ImageView)itemView.findViewById(R.id.item_pic);
            itemView.setOnClickListener(this);


        }




        @Override
        public void onClick(View v) {
            mClickListener.onItemClick(v, getAdapterPosition());
        }
    }



}
