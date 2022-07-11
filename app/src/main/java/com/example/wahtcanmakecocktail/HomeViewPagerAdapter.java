package com.example.wahtcanmakecocktail;

import android.os.Bundle;
import android.util.Log;
import android.widget.CompoundButton;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.wahtcanmakecocktail.Fragments.AlcoholFragment;
import com.example.wahtcanmakecocktail.Fragments.SearchFromBase;
import com.example.wahtcanmakecocktail.Fragments.SoftDrinkFragment;

import java.io.Serializable;

public class HomeViewPagerAdapter extends FragmentStateAdapter {
    public HomeViewPagerAdapter(@NonNull FragmentActivity fragment) {
        super(fragment);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        AlcoholFragment alFragment = new AlcoholFragment();
        SoftDrinkFragment sfFragment = new SoftDrinkFragment();
        SearchFromBase searchFromBase = new SearchFromBase();

        switch (position) {
            case 0:
                Log.d("MyLog", "case 1");
                return alFragment;
            case 1:
                Log.d("MyLog", "case 1");
                return sfFragment;
            case 2:
                Log.d("MyLog", "case 2");
                return searchFromBase;
            default:
                Log.d("MyLog", "case default");
                return alFragment;
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
