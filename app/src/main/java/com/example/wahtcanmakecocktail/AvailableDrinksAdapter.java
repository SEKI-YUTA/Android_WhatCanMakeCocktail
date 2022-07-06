package com.example.wahtcanmakecocktail;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AvailableDrinksAdapter extends RecyclerView.Adapter<AvailableDrinkViewHolder> {
    private Context context;
    private List<String> availableDrinks;

    public AvailableDrinksAdapter(Context context, List<String> availableDrinks) {
        this.context = context;
        this.availableDrinks = availableDrinks;
    }

    @NonNull
    @Override
    public AvailableDrinkViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AvailableDrinkViewHolder(LayoutInflater.from(context).inflate(R.layout.drink_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull AvailableDrinkViewHolder holder, int position) {
        holder.drinkCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "jump to browser", Toast.LENGTH_SHORT).show();
            }
        });
        holder.tv_drinkName.setText(availableDrinks.get(holder.getAdapterPosition()));
    }

    @Override
    public int getItemCount() {
        return availableDrinks.size();
    }
}
