package com.gcme.globalstart.globalstart.magazine.Do;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gcme.globalstart.globalstart.R;


public class Do3 extends Fragment {
    public Do3() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the splash for this fragment
        View view = inflater.inflate(R.layout.do_3, container, false);
        return view;


    }
}
