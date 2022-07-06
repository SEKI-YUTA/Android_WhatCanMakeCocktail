package com.example.wahtcanmakecocktail;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;

public class AvailableListActivity extends AppCompatActivity {
    List<String> availableDrinks;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_available_list);

        Intent passedIntent = getIntent();
        availableDrinks = (List<String>) passedIntent.getSerializableExtra("availableDrinks");
        Log.d("MyLog", availableDrinks.toString());

        Log.d("MyLog", availableDrinks.toString());
        Toast.makeText(this, availableDrinks.toString(), Toast.LENGTH_SHORT).show();
    }
}