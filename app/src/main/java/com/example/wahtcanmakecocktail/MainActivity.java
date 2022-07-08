package com.example.wahtcanmakecocktail;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.example.wahtcanmakecocktail.Adapters.IngredientsAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private String rawJsonData = "";
    private JSONObject jsonObject;
    private JSONArray drinks;

    private Button btn_search;
    private RecyclerView recycler_ingredients;
    private List<String> availableIngredients = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // jsonファイルからテキストとしてデータを読み込み
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
        // -------------------------

        // テキストデータをjsonに変換してデータを取得
        try {
            jsonObject = new JSONObject(rawJsonData);
            int version = jsonObject.getInt("version");
            drinks = jsonObject.getJSONArray("drinks");
            Log.d("drinks", drinks.toString());

//            Log.d("jsonVersion", String.valueOf(version));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        // -------------------------

        recycler_ingredients = findViewById(R.id.recycler_ingredients);
        recycler_ingredients.setHasFixedSize(true);
        recycler_ingredients.setLayoutManager(new GridLayoutManager(this, 1));
        btn_search = findViewById(R.id.btn_search);

        IngredientsAdapter ingredientsAdapter = new IngredientsAdapter(this, Arrays.asList(getResources().getStringArray(R.array.ingredients)), listener);
        Log.d("MyLog", Arrays.asList(getResources().getStringArray(R.array.ingredients)).toString());
        recycler_ingredients.setAdapter(ingredientsAdapter);

        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<String> availableDrinks = new ArrayList<>();

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
                        Log.d("availableIngredients", availableDrinks.toString())   ;
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


    private CompoundButton.OnCheckedChangeListener listener = new CompoundButton.OnCheckedChangeListener() {

        @Override
        public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
            if(b) {
                availableIngredients.add((String) compoundButton.getText());
                Toast.makeText(MainActivity.this, compoundButton.getText(), Toast.LENGTH_SHORT).show();
            } else {
                availableIngredients.remove(compoundButton.getText());
            }
        }
    };


}