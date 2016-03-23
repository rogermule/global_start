package com.gcme.globalstart.globalstart.magazine.Intro;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.gcme.globalstart.globalstart.News_Detail;
import com.gcme.globalstart.globalstart.R;

public class IntroFragment extends Fragment {
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    Button newsBut;

    public IntroFragment() {
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
        View view = inflater.inflate(R.layout.intro_1, container, false);
        viewPager = (ViewPager) view.findViewById(R.id.mypager);

         newsBut = (Button) view.findViewById(R.id.news_button_intro);
         newsBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), News_Detail.class);
                startActivity(intent);
            }
        });

        return view;

    }

}
