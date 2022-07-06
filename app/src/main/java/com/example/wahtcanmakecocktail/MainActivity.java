package com.example.wahtcanmakecocktail;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private String rawJsonData = "";
    private JSONObject jsonObject;
    private JSONArray drinks;

    private Button btn_search;
    private List<CheckBox> checkBoxList = new ArrayList<>();
    private List<String> availableIngredients = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AssetManager am = getResources().getAssets();
        try {
            InputStream inputStream = am.open("cocktail_data.json");
            BufferedReader bf = new BufferedReader(new InputStreamReader(inputStream));
            String data = "";
//            rawJsonData = bf.readLine();
            while((data = bf.readLine()) != null) {
                rawJsonData += data;
            }
//            Log.d("MyLog", rawJsonData);
            Log.d("readJson", "read json data successful");
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            jsonObject = new JSONObject(rawJsonData);
            int version = jsonObject.getInt("version");
            drinks = jsonObject.getJSONArray("drinks");
            Log.d("drinks", drinks.toString());

//            Log.d("jsonVersion", String.valueOf(version));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        checkBoxList.add(findViewById(R.id.ck_gin));
        checkBoxList.add(findViewById(R.id.ck_orange));
        checkBoxList.add(findViewById(R.id.ck_carbonated));
        checkBoxList.add(findViewById(R.id.ck_lemon));
        btn_search = findViewById(R.id.btn_search);

        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<String> availableDrinks = new ArrayList<>();
                checkAvailableIngredients();

                for(int i=0; i < drinks.length(); i++) {
                    try {
                        JSONObject drink = drinks.getJSONObject(i);
                        JSONArray ingredients = drink.getJSONArray("ingredients");
                        Log.d("ingredients", ingredients.toString());
                        List<String> ingredientsList = new ArrayList<>();
                        for(int j = 0; j < ingredients.length(); j ++) {
                            ingredientsList.add(ingredients.getString(j));
                            Log.d("MyLog", "ingredients add");
                        }
                        boolean canMake = availableIngredients.containsAll(ingredientsList);
                        if(canMake) {
                            Log.d("CanMake", drink.getString("drinkName"));
                            availableDrinks.add(drink.getString("drinkName"));
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                Intent intent = new Intent(MainActivity.this, AvailableListActivity.class);
                Bundle args = new Bundle();
                args.putStringArrayList("availableDrinks", (ArrayList<String>) availableDrinks);
                intent.putExtra("args", args);
                startActivity(intent);
            }
        });
    }

    private void checkAvailableIngredients() {
//        if(ck_gin.isChecked()) {
//            availableIngredients.add(getString(R.string.al_gin));
//        }
//        if(ck_orange.isChecked()) {
//            availableIngredients.add(getString(R.string.sf_orangeJuice));
//        }
//        if(ck_carbonated.isChecked()) {
//            availableIngredients.add(getString(R.string.sf_carbonatedWater));
//        }
        for(int i = 0; i < checkBoxList.size(); i++) {
            if(checkBoxList.get(i).isChecked()) {
                switch(checkBoxList.get(i).getId()) {
                    case R.id.ck_gin:
                        availableIngredients.add(getString(R.string.al_gin));
                        break;
                    case R.id.ck_orange:
                        availableIngredients.add(getString(R.string.sf_orangeJuice));
                        break;
                    case R.id.ck_carbonated:
                        availableIngredients.add(getString(R.string.sf_carbonatedWater));
                        break;
                    case R.id.ck_lemon:
                        availableIngredients.add(getString(R.string.sf_lemonJuice));
                        break;
                    default:
                        return;
                }
            }
        }
    }


}