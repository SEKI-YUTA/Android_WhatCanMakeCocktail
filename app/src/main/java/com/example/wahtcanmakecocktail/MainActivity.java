package com.example.wahtcanmakecocktail;

import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private String rawJsonData = "";
    private JSONObject jsonObject;
    private JSONArray drinks;

    String[] alcohol_ingredientArray, softDrink_ingredientArray;
    private List<String> availableIngredients = new ArrayList<>();
    private static List<Map<String, Boolean>> alcohol_ingredientsList = new ArrayList<>();
    private static List<Map<String, Boolean>> softDrink_ingredientsList = new ArrayList<>();

    private ViewPager2 viewPager;
    private TabLayout tabLayout;
    private HomeViewPagerAdapter viewPagerAdapter;

    private GeneralUtil generalUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = findViewById(R.id.viewPager);
        viewPagerAdapter = new HomeViewPagerAdapter(this);
        viewPager.setAdapter(viewPagerAdapter);
        tabLayout = findViewById(R.id.tabLayout);

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                Log.d("onPageSelected ", String.valueOf(position));
//                super.onPageSelected(position);
                tabLayout.getTabAt(position).select();
            }
        });


        // jsonファイルからテキストとしてデータを読み込み
        AssetManager am = getResources().getAssets();
        try {
            InputStream inputStream = am.open("cocktail_data.json");
            BufferedReader bf = new BufferedReader(new InputStreamReader(inputStream));
            String data = "";
            while ((data = bf.readLine()) != null) {
                rawJsonData += data;
            }
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

        } catch (JSONException e) {
            e.printStackTrace();
        }

        // 酒の名前の配列をstring.xmlから取得
        alcohol_ingredientArray = getResources().getStringArray(R.array.alcohol_ingredient);
        for (int i = 0; i < alcohol_ingredientArray.length; i++) {
            Map<String, Boolean> item = new HashMap();
            item.put(alcohol_ingredientArray[i], false);
            // 酒の名前と選択状態のMapのListを作成
            alcohol_ingredientsList.add(item);
        }

        // ソフトドリンクの名前の配列をstring.xmlから取得
        softDrink_ingredientArray = getResources().getStringArray(R.array.softDrink_ingredient);
        for (int i = 0; i < softDrink_ingredientArray.length; i++) {
            Map<String, Boolean> item = new HashMap();
            item.put(softDrink_ingredientArray[i], false);
            // ソフトドリンクの名前と選択状態のMapのListを作成
            softDrink_ingredientsList.add(item);
        }

        generalUtil = GeneralUtil.getInstance();
//
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.option_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.option_search:
                moveToResultActivity();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        if (generalUtil.isDoubleTapped()) {
            super.onBackPressed();
        } else {
            Toast.makeText(this, "Please double tap to go home screen", Toast.LENGTH_SHORT).show();
        }
    }

    public CompoundButton.OnCheckedChangeListener listener = new CompoundButton.OnCheckedChangeListener() {

        @Override
        public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
            ViewGroup vg = (ViewGroup) compoundButton.getParent();
            TextView tv_name = (TextView) vg.getChildAt(0);
            String name = tv_name.getText().toString();

            // アルコールかどうかをboolean型に保存
            boolean isAlcohol = Arrays.asList(alcohol_ingredientArray).contains(name);
            int index;
            String key;

            if (isAlcohol) {
                // アルコール飲料である場合の処理
                index = Arrays.asList(alcohol_ingredientArray)
                        .indexOf(compoundButton.getText());
                key = Arrays.asList(alcohol_ingredientArray).get(index);
                if (b) {
                    alcohol_ingredientsList.get(index).put(key, true);
                    availableIngredients.add((String) compoundButton.getText());
                    Toast.makeText(MainActivity.this, compoundButton.getText(), Toast.LENGTH_SHORT).show();
                } else {
                    alcohol_ingredientsList.get(index).put(key, false);
                    availableIngredients.remove(compoundButton.getText());
                }
            } else {
                // ソフトドリンクである場合の処理
                index = Arrays.asList(softDrink_ingredientArray)
                        .indexOf(compoundButton.getText());
                key = Arrays.asList(softDrink_ingredientArray).get(index);
                if (b) {
                    softDrink_ingredientsList.get(index).put(key, true);
                    availableIngredients.add((String) compoundButton.getText());
                    Toast.makeText(MainActivity.this, compoundButton.getText(), Toast.LENGTH_SHORT).show();
                } else {
                    softDrink_ingredientsList.get(index).put(key, false);
                    availableIngredients.remove(compoundButton.getText());
                }
            }
        }
    };

    public void moveToResultActivity() {
        List<String> availableDrinks = new ArrayList<>();
        for (int i = 0; i < drinks.length(); i++) {
            try {
                JSONObject drink = drinks.getJSONObject(i);
                JSONArray ingredients = drink.getJSONArray("ingredients");
                Log.d("ingredients", ingredients.toString());
                List<String> ingredientsList = new ArrayList<>();
                for (int j = 0; j < ingredients.length(); j++) {
                    ingredientsList.add(ingredients.getString(j));
                    Log.d("MyLog", "ingredients add");
                }

                // 選択された材料でカクテルを作れるかを判定
                boolean canMake = availableIngredients.containsAll(ingredientsList);
                if (canMake) {
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

    public static List<Map<String, Boolean>> getAlcoholIngredientsList() {
        return alcohol_ingredientsList;
    }

    public static List<Map<String, Boolean>> getSoftDrinkIngredientsList() {
        return softDrink_ingredientsList;
    }


}