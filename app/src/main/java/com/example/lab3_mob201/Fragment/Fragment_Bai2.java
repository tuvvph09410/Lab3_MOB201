package com.example.lab3_mob201.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lab3_mob201.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Fragment_Bai2#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment_Bai2 extends Fragment {



    public Fragment_Bai2() {

    }


    public static Fragment_Bai2 newInstance() {
        Fragment_Bai2 fragment = new Fragment_Bai2();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_bai2, container, false);
    }
}