package com.gcme.globalstart.globalstart.magazine.Learn;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gcme.globalstart.globalstart.R;


public class Learn_Fragment extends Fragment {
    private ViewPager myPager;
    public Learn_Fragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.blank_page, container, false);
        myPager = (ViewPager) view.findViewById(R.id.mypager);
        FragmentManager manager = getChildFragmentManager();
        myPager.setAdapter(new Learn_Adapter(manager));
        return view;


    }
}
