package com.example.wahtcanmakecocktail;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.wahtcanmakecocktail.Adapters.AvailableDrinksAdapter;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class AvailableListActivity extends AppCompatActivity {
    List<JSONObject> availableDrinks = new ArrayList<>();
    private RecyclerView recycler_availableDrinks;
    private RelativeLayout notFoundText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_available_list);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeAsUpIndicator(R.drawable.ic_back_arrow);
        actionBar.setDisplayHomeAsUpEnabled(true);

        availableDrinks.addAll(MainActivity.getAvailableDrinks());

//        availableDrinks = args.getStringArrayList("availableDrinks");
//        Toast.makeText(this, availableDrinks.toString(), Toast.LENGTH_SHORT).show();
//
        notFoundText = findViewById(R.id.notFoundText);
        recycler_availableDrinks = findViewById(R.id.recycler_availableDrinks);
        recycler_availableDrinks.setHasFixedSize(true);
        recycler_availableDrinks.setLayoutManager(new GridLayoutManager(this, 1));

        if(availableDrinks.size() <= 0) {
            notFoundText.setVisibility(View.VISIBLE);
        } else {
            notFoundText.setVisibility(View.GONE);
        }

        AvailableDrinksAdapter adapter = new AvailableDrinksAdapter(this, availableDrinks);
        recycler_availableDrinks.setAdapter(adapter);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        availableDrinks.clear();
        availableDrinks.addAll(MainActivity.getAvailableDrinks());
        AvailableDrinksAdapter adapter = new AvailableDrinksAdapter(this, availableDrinks);
        recycler_availableDrinks.setAdapter(adapter);
    }
}