package com.example.assignment2.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.assignment2.R;
import com.example.assignment2.Object.Bike;

import java.util.ArrayList;
import java.util.List;

public class BikeAdapter extends RecyclerView.Adapter<BikeAdapter.BikeHolder> {

    private List<Bike> bikes = new ArrayList<>();

    @NonNull
    @Override
    public BikeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.bike_item, parent, false);

        return new BikeHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull BikeHolder holder, int position) {
        Bike currentBike = bikes.get(position);
        holder.title.setText(currentBike.getBikeBrand());
        holder.subtitle.setText(currentBike.getBikeModel());
    }

    @Override
    public int getItemCount() {
        return bikes.size();
    }

    public void setBikes(List<Bike> bikes){
        this.bikes = bikes;
        notifyDataSetChanged();
    }

    //method which returns an item from the list of bikes
    //at that particular index (position)
    public Bike getBikeAt(int position){
        return bikes.get(position);
    }

    class BikeHolder extends RecyclerView.ViewHolder {
        private TextView title;
        private TextView subtitle;


        public BikeHolder(@NonNull View itemView) {
            super(itemView);

            title    = itemView.findViewById(R.id.itemTitle);
            subtitle = itemView.findViewById(R.id.itemSubtitle);


        }
    }
}
