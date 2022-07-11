package com.example.wahtcanmakecocktail.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;

import com.example.wahtcanmakecocktail.Adapters.AvailableDrinksAdapter;
import com.example.wahtcanmakecocktail.MainActivity;
import com.example.wahtcanmakecocktail.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class SearchFromBase extends Fragment {
    JSONArray drinks;
    List<JSONObject> availableDrinks = new ArrayList<>();
    Spinner baseSpinner;
    ImageButton imgBtn_filter;
    RecyclerView recycler_availableDrinks;

    ArrayAdapter<CharSequence> spinnerAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search_from_base, container, false);
        drinks = MainActivity.getDrinks();
        for(int i = 0; i < drinks.length(); i++) {
            try {
                availableDrinks.add(drinks.getJSONObject(i));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        baseSpinner = view.findViewById(R.id.baseSpinner);
        imgBtn_filter = view.findViewById(R.id.imgBtn_filter);
        recycler_availableDrinks = view.findViewById(R.id.recycler_availableDrinks);

        spinnerAdapter = ArrayAdapter.createFromResource(getContext(), R.array.alcohol_ingredient, android.R.layout.simple_spinner_item);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        baseSpinner.setAdapter(spinnerAdapter);


        AvailableDrinksAdapter adapter = new AvailableDrinksAdapter(getContext(), availableDrinks);
        recycler_availableDrinks.setHasFixedSize(true);
        recycler_availableDrinks.setLayoutManager(new GridLayoutManager(getContext(), 1));
        recycler_availableDrinks.setAdapter(adapter);


        return view;
    }
}