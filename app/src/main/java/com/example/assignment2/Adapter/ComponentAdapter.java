package com.example.assignment2.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.assignment2.Object.Component;
import com.example.assignment2.R;

import java.util.ArrayList;
import java.util.List;

public class ComponentAdapter extends RecyclerView.Adapter<ComponentAdapter.ComponentHolder> {
    private List<Component> components = new ArrayList<>();

    @NonNull
    @Override
    public ComponentHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.component_item, parent, false);
        return new ComponentHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ComponentHolder holder, int position) {
        Component currentComponent = components.get(position);
        holder.componentTitle.setText(currentComponent.getComponentName());
        holder.componentPurchase.setText(currentComponent.getDateComponent());
        holder.componentLifetime.setText(currentComponent.getComponentLifetime());
    }

    @Override
    public int getItemCount() {
        return components.size();
    }

    //method which gets the list of components and shows it in recyclerview
    public void setComponents(List<Component> components){
        this.components = components;
        notifyDataSetChanged();
    }


    //
    class ComponentHolder extends RecyclerView.ViewHolder{
        private TextView componentTitle;
        private TextView componentPurchase;
        private TextView componentLifetime;


        public ComponentHolder(@NonNull View itemView) {
            super(itemView);

            componentTitle    = itemView.findViewById(R.id.componentTitle);
            componentPurchase = itemView.findViewById(R.id.purchaseTitle);
            componentLifetime = itemView.findViewById(R.id.lifetimeTitle);


        }
    }
}
