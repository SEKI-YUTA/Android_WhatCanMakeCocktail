package com.example.wahtcanmakecocktail;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;

public class AvailableListActivity extends AppCompatActivity {
    List<String> availableDrinks;
    private RecyclerView recycler_availableDrinks;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_available_list);

        Intent passedIntent = getIntent();
        Bundle args = passedIntent.getBundleExtra("args");
//        availableDrinks = (List<String>) passedIntent.getSerializableExtra("availableDrinks");
//        Log.d("MyLog", availableDrinks.toString());
//
//        Log.d("MyLog", availableDrinks.toString());
//        Toast.makeText(this, availableDrinks.toString(), Toast.LENGTH_SHORT).show();
        availableDrinks = args.getStringArrayList("availableDrinks");
        Toast.makeText(this, availableDrinks.toString(), Toast.LENGTH_SHORT).show();

        recycler_availableDrinks = findViewById(R.id.recycler_availableDrinks);
        recycler_availableDrinks.setHasFixedSize(true);
        recycler_availableDrinks.setLayoutManager(new GridLayoutManager(this, 1));

        AvailableDrinksAdapter adapter = new AvailableDrinksAdapter(this, availableDrinks);
        recycler_availableDrinks.setAdapter(adapter);
    }
}