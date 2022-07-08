package com.example.wahtcanmakecocktail.Adapters;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wahtcanmakecocktail.ViewHolders.AvailableDrinkViewHolder;
import com.example.wahtcanmakecocktail.R;

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
//                Toast.makeText(context, "jump to browser", Toast.LENGTH_SHORT).show();
                showDialog(availableDrinks.get(holder.getAdapterPosition()));
            }
        });
        holder.tv_drinkName.setText(availableDrinks.get(holder.getAdapterPosition()));
    }

    @Override
    public int getItemCount() {
        return availableDrinks.size();
    }

    private void showDialog(String drinkName) {
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE );
        dialog.setContentView(R.layout.bottom_sheet_layout);

        LinearLayout row_openInBrowser = dialog.findViewById(R.id.row_openInBrowser);
        LinearLayout row_share = dialog.findViewById(R.id.row_share);

        row_openInBrowser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drinkName.replace(" ", "+");
                drinkName.replace("　", "+");
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com/search?q=" + drinkName));
                context.startActivity(intent);
                dialog.dismiss();
            }
        });

        row_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_SUBJECT, drinkName);
                context.startActivity(intent);
            }
        });

        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        WindowManager.LayoutParams attributes = dialog.getWindow().getAttributes();
        attributes.windowAnimations = R.style.DialogAnimation;
        dialog.getWindow().setAttributes(attributes);
        dialog.getWindow().setGravity(Gravity.BOTTOM);
        dialog.show();
    }

}
