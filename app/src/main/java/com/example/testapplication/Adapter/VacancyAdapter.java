package com.example.testapplication.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.testapplication.Activity.RecyclerViewInterface;
import com.example.testapplication.Domain.VacancyDomain;
import com.example.testapplication.R;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class VacancyAdapter extends RecyclerView.Adapter<VacancyAdapter.ViewHolder> {
    ArrayList<VacancyDomain> items;
    DecimalFormat formatter;
    Context context;
    private final RecyclerViewInterface recyclerViewInterface;

    public VacancyAdapter(ArrayList<VacancyDomain> items, RecyclerViewInterface recyclerViewInterface) {
        this.items = items;
        formatter = new DecimalFormat("###,###,###,###.##");
        this.recyclerViewInterface = recyclerViewInterface;
    }

    @NonNull
    @Override
    public VacancyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_vacancy,parent,false);
        context = parent.getContext();
        return new ViewHolder(inflate, recyclerViewInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull VacancyAdapter.ViewHolder holder, int position) {
        holder.titleTxt.setText(items.get(position).getTitle());
        holder.ownerTxt.setText(items.get(position).getOwner());
        holder.starTxt.setText(formatter.format(items.get(position).getStar()));
        holder.priceTxt.setText("₩"+formatter.format(items.get(position).getPrice()));


        int drawableResourceId=holder.itemView.getResources().getIdentifier(items.get(position).getPicPath(),
                "drawable",holder.itemView.getContext().getPackageName());

        Glide.with(holder.itemView.getContext())
                .load(drawableResourceId)
                .into(holder.pic);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView titleTxt,ownerTxt,priceTxt,starTxt;
        ImageView pic;
        public ViewHolder(@NonNull View itemView, RecyclerViewInterface recyclerViewInterface) {
            super(itemView);
            titleTxt=itemView.findViewById(R.id.titleTxt);
            ownerTxt=itemView.findViewById(R.id.ownerTxt);
            priceTxt=itemView.findViewById(R.id.priceTxt);
            starTxt=itemView.findViewById(R.id.starTxt);
            pic=itemView.findViewById(R.id.pic);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (recyclerViewInterface != null){
                        int pos = getAdapterPosition();
                        if (pos != RecyclerView.NO_POSITION){
                            recyclerViewInterface.onItemClick(pos);
                        }
                    }
                }
            });
        }
    }
}
