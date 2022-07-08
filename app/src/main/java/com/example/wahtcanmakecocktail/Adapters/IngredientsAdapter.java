package com.example.wahtcanmakecocktail.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wahtcanmakecocktail.ViewHolders.IngredientViewHolder;
import com.example.wahtcanmakecocktail.R;

import java.util.List;

public class IngredientsAdapter extends RecyclerView.Adapter<IngredientViewHolder> {
    private Context context;
    private List<String> ingredients;
    private CompoundButton.OnCheckedChangeListener listener;

    public IngredientsAdapter(Context context, List<String> ingredients, CompoundButton.OnCheckedChangeListener listener) {
        this.context = context;
        this.ingredients = ingredients;
        this.listener = listener;
    }

    @NonNull
    @Override
    public IngredientViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new IngredientViewHolder(LayoutInflater.from(context).inflate(R.layout.ingredient_item, parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull IngredientViewHolder holder, int position) {
        holder.tv_ingredientName.setText(ingredients.get(holder.getAdapterPosition()));
        holder.setIsRecyclable(false);
        holder.ck_isAvailable.setOnCheckedChangeListener(null);
        holder.ck_isAvailable.setFocusable(false);
        holder.ck_isAvailable.setText(ingredients.get(holder.getAdapterPosition()));
        holder.ck_isAvailable.setOnCheckedChangeListener(listener);
    }

    @Override
    public int getItemCount() {
        return ingredients.size();
    }



}
