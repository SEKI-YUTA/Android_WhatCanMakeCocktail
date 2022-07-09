package com.example.wahtcanmakecocktail;

import android.os.Bundle;
import android.util.Log;
import android.widget.CompoundButton;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.wahtcanmakecocktail.Fragments.AlcoholFragment;
import com.example.wahtcanmakecocktail.Fragments.SoftDrinkFragment;

import java.io.Serializable;

public class HomeViewPagerAdapter extends FragmentStateAdapter {
    private CompoundButton.OnCheckedChangeListener listener;
    public HomeViewPagerAdapter(@NonNull FragmentActivity fragment, CompoundButton.OnCheckedChangeListener listener) {
        super(fragment);
        this.listener = listener;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        AlcoholFragment alFragment = new AlcoholFragment();
        SoftDrinkFragment sfFragment = new SoftDrinkFragment();

        Bundle args = new Bundle();
        switch (position) {
            case 0:
                Log.d("MyLog", "case 1");
//                args.putSerializable("listener", (Serializable) listener);
//                alFragment.setArguments(args);
                return alFragment;
            case 1:
                Log.d("MyLog", "case 2");
//                args.putSerializable("listener", (Serializable) listener);
//                sfFragment.setArguments(args);
                return sfFragment;
            default:
                Log.d("MyLog", "case default");
//                args.putSerializable("listener", (Serializable) listener);
//                alFragment.setArguments(args);
                return alFragment;
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
