package com.example.wahtcanmakecocktail;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MyListViewAdapter extends BaseAdapter {
    private Context context;
    private List<Map<String, Boolean>> ingredientsList = new ArrayList<>();
    private CompoundButton.OnCheckedChangeListener listener;

    public MyListViewAdapter(Context context, List<Map<String, Boolean>> ingredientsList, CompoundButton.OnCheckedChangeListener listener) {
        this.context = context;
        this.ingredientsList = ingredientsList;
        this.listener = listener;
    }

    @Override
    public int getCount() {
        return ingredientsList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }


    @Override
    public long getItemId(int i) {
        Log.d("getItemId", String.format("no.%s", i));
        return 0;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup container) {
        Log.d("getView", String.format("no.%s", position));
//        if(convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.ingredient_item, container, false);
//        }
        String text = "";
        boolean isChecked = false;
        Map<String, Boolean> item = ingredientsList.get(position);
        for(Map.Entry<String, Boolean> entry : item.entrySet()) {
            text = entry.getKey();
            isChecked = entry.getValue();
        }
        ((TextView)convertView.findViewById(R.id.tv_ingredientName)).setText(text);
        ((CheckBox )convertView.findViewById(R.id.ck_isAvailable)).setChecked(isChecked);
        ((CheckBox )convertView.findViewById(R.id.ck_isAvailable)).setText(text);
        ((CheckBox )convertView.findViewById(R.id.ck_isAvailable)).setOnCheckedChangeListener(listener);

        return convertView;
    }
}
