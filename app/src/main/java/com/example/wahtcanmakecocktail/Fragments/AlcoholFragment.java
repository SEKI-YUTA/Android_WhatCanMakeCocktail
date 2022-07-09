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
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class AlcoholFragment extends Fragment {
    private String[] alcoholArray;
    private List<Map<String,Boolean>> alcoholList = new ArrayList<>();
    private CompoundButton.OnCheckedChangeListener listener;
    ListView list_alcohol_ingredients;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        alcoholArray = getResources().getStringArray(R.array.alcohol_ingredient);
        for(String alcohol: alcoholArray) {
            Map<String, Boolean> item = new HashMap<>();
            item.put(alcohol, false);
            alcoholList.add(item);
        }
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_alcohol, container, false);
        list_alcohol_ingredients = view.findViewById(R.id.list_alcohol_ingredients);

//        Bundle passedArgs = getArguments();
//        listener = (CompoundButton.OnCheckedChangeListener) passedArgs.getSerializable("listener");

        listener = ((MainActivity) getActivity()).listener;

        MyListViewAdapter adapter = new MyListViewAdapter(getContext(), alcoholList, listener);

        list_alcohol_ingredients.setAdapter(adapter);
        return view;

    }


}