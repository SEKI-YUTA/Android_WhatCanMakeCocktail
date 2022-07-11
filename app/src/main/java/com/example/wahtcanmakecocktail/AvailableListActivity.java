package com.example.wahtcanmakecocktail;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.wahtcanmakecocktail.Adapters.AvailableDrinksAdapter;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class AvailableListActivity extends AppCompatActivity {
    List<JSONObject> availableDrinks = new ArrayList<>();
    private RecyclerView recycler_availableDrinks;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_available_list);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeAsUpIndicator(R.drawable.ic_back_arrow);
        actionBar.setDisplayHomeAsUpEnabled(true);

        Intent passedIntent = getIntent();
        Bundle args = passedIntent.getBundleExtra("args");

        availableDrinks.addAll(MainActivity.getAvailableDrinks());

//        availableDrinks = args.getStringArrayList("availableDrinks");
//        Toast.makeText(this, availableDrinks.toString(), Toast.LENGTH_SHORT).show();
//
        recycler_availableDrinks = findViewById(R.id.recycler_availableDrinks);
        recycler_availableDrinks.setHasFixedSize(true);
        recycler_availableDrinks.setLayoutManager(new GridLayoutManager(this, 1));

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
}