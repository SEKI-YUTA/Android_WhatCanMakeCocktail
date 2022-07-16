package com.example.wahtcanmakecocktail.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.Spinner;

import com.example.wahtcanmakecocktail.Adapters.AvailableDrinksAdapter;
import com.example.wahtcanmakecocktail.MainActivity;
import com.example.wahtcanmakecocktail.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SearchFromBase extends Fragment {
    JSONArray drinks;
    List<JSONObject> availableDrinks = new ArrayList<>();
    private Spinner baseSpinner;
    private RecyclerView recycler_availableDrinks;
    private RelativeLayout notFoundText;

    ArrayAdapter<CharSequence> spinnerAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search_from_base, container, false);


        baseSpinner = view.findViewById(R.id.baseSpinner);
        recycler_availableDrinks = view.findViewById(R.id.recycler_availableDrinks);
        notFoundText = view.findViewById(R.id.notFoundText);

        spinnerAdapter = ArrayAdapter.createFromResource(getContext(), R.array.alcohol_ingredient, android.R.layout.simple_spinner_item);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        baseSpinner.setAdapter(spinnerAdapter);


        recycler_availableDrinks.setHasFixedSize(true);
        recycler_availableDrinks.setLayoutManager(new GridLayoutManager(getContext(), 1));



        baseSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                availableDrinks.clear();
                String base = getResources().getStringArray(R.array.alcohol_ingredient)[i];
                Log.d("Base", base);
                drinks = MainActivity.getDrinks();
                for(int j = 0; j < drinks.length(); j++) {
                    try {
                        String itemBase = drinks.getJSONObject(j).getString("base");
                        Log.d("Alcohol base", itemBase);
                        if(base.equals(itemBase)) {
                            availableDrinks.add(drinks.getJSONObject(j));
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                if(availableDrinks.size() <= 0) {
                    notFoundText.setVisibility(View.VISIBLE);
                } else {
                    notFoundText.setVisibility(View.GONE);
                }
                AvailableDrinksAdapter adapter = new AvailableDrinksAdapter(getContext(), availableDrinks);
                recycler_availableDrinks.setAdapter(adapter);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        return view;
    }

}