package com.example.communityxchange;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyViewHolder>{
    private Context context;
    private List<DataClass> dataList;

    public MyAdapter(Context context, List<DataClass> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Glide.with(context).load(dataList.get(position).getDataImage()).into(holder.recImage);
        holder.recBusName.setText(dataList.get(position).getDataBusinessName());
        holder.recDesc.setText(dataList.get(position).getDataDescription());
        holder.recOwner.setText(dataList.get(position).getDataOwner());

        holder.recCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("Image", dataList.get(holder.getAdapterPosition()).getDataImage());
                intent.putExtra("Description", dataList.get(holder.getAdapterPosition()).getDataDescription());
                intent.putExtra("BusinessName", dataList.get(holder.getAdapterPosition()).getDataBusinessName());
                intent.putExtra("Owner", dataList.get(holder.getAdapterPosition()).getDataOwner());
                intent.putExtra("ContactDetails", dataList.get(holder.getAdapterPosition()).getDataContactDetails());

                //for data delete
                intent.putExtra("Key",dataList.get(holder.getAdapterPosition()).getKey());

                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    //to search
    public void searchDataList(ArrayList<DataClass> searchList){
        dataList = searchList;
        notifyDataSetChanged();
    }
}

class MyViewHolder extends RecyclerView.ViewHolder{

    ImageView recImage;
    TextView recBusName, recDesc, recOwner, recContact;
    CardView recCard;

    public MyViewHolder(@NonNull View itemView) {
        super(itemView);

        recImage = itemView.findViewById(R.id.recImage);
        recBusName = itemView.findViewById(R.id.recBusName);
        recDesc = itemView.findViewById(R.id.recDesc);
        recOwner = itemView.findViewById(R.id.recOwner);
        recCard = itemView.findViewById(R.id.recCard);

    }
}