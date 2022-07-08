package com.example.wahtcanmakecocktail.ViewHolders;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wahtcanmakecocktail.R;

public class AvailableDrinkViewHolder extends RecyclerView.ViewHolder {
    public CardView drinkCard;
    public TextView tv_drinkName;
    public AvailableDrinkViewHolder(@NonNull View itemView) {
        super(itemView);
        drinkCard = itemView.findViewById(R.id.drinkCard);
        tv_drinkName = itemView.findViewById(R.id.tv_drinkName);
    }
}
