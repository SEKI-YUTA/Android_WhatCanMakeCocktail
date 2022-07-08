package com.example.wahtcanmakecocktail.ViewHolders;

import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wahtcanmakecocktail.R;

public class IngredientViewHolder extends RecyclerView.ViewHolder {
    public TextView tv_ingredientName;
    public CheckBox ck_isAvailable;
    public IngredientViewHolder(@NonNull View itemView) {
        super(itemView);
        tv_ingredientName = itemView.findViewById(R.id.tv_ingredientName);
        ck_isAvailable = itemView.findViewById(R.id.ck_isAvailable);
    }
}
