package com.example.wahtcanmakecocktail.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ListView;

import com.example.wahtcanmakecocktail.MainActivity;
import com.example.wahtcanmakecocktail.MyListViewAdapter;
import com.example.wahtcanmakecocktail.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SoftDrinkFragment extends Fragment {
    private String[] softDrinkArray;
    private List<Map<String,Boolean>> softDrinkList = new ArrayList<>();
    private CompoundButton.OnCheckedChangeListener listener;
    ListView list_softDrink_ingredients;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        softDrinkArray = getResources().getStringArray(R.array.softDrink_ingredient);
        for(String softDrink: softDrinkArray) {
            Map<String, Boolean> item = new HashMap<>();
            item.put(softDrink, false);
            softDrinkList.add(item);
        }
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_soft_drink, container, false);
        list_softDrink_ingredients = view.findViewById(R.id.list_softDrink_ingredients);

//        Bundle passedArgs = getArguments();
//        listener = (CompoundButton.OnCheckedChangeListener) passedArgs.getSerializable("listener");

        listener = ((MainActivity) getActivity()).listener;

        MyListViewAdapter adapter = new MyListViewAdapter(getContext(), softDrinkList, listener);

        list_softDrink_ingredients.setAdapter(adapter);
        return view;

    }
}