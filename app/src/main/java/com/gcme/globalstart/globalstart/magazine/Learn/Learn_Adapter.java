package com.gcme.globalstart.globalstart.magazine.Learn;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by BENGEOS on 3/16/16.
 */
public class Learn_Adapter extends FragmentPagerAdapter {

    public Learn_Adapter(FragmentManager fm) {
        super(fm);
        // TODO Auto-generated constructor stub
    }
    @Override
    public Fragment getItem(int position) {

        Fragment fragment;
        if(position ==0){
            fragment = new Learn1();
            return fragment;
        }
        else if(position ==1){
            fragment = new Learn2();
            return fragment;
        }
        else if(position ==2){
            fragment = new Learn3();
            return fragment;
        }
        else if(position ==3){
            fragment = new Learn4();
            return fragment;
        }
        return null;
    }
    @Override
    public CharSequence getPageTitle(int position) {
        if(position ==0){
            return "Understand the Youth";
        }
        else if(position ==1){
            return "Jesus's Model";
        }
        else if(position == 2){
            return "How to Learn";
        }
        else if(position == 3){
            return "Take Action";
        }
        return null;
    }
    @Override
    public int getCount() {

        return 4;

    }
}
