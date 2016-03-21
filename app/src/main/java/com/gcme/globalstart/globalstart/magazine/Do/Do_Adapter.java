package com.gcme.globalstart.globalstart.magazine.Do;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by BENGEOS on 3/16/16.
 */
public class Do_Adapter extends FragmentPagerAdapter {

    public Do_Adapter(FragmentManager fm) {
        super(fm);
        // TODO Auto-generated constructor stub
    }
    @Override
    public Fragment getItem(int position) {

        Fragment fragment;
        if(position ==0){
            fragment = new Do1();
            return fragment;
        }
        else if(position ==1){
            fragment = new Do2();
            return fragment;
        }
        else if(position ==2){
            fragment = new Do3();
            return fragment;
        }
        return null;
    }
    @Override
    public CharSequence getPageTitle(int position) {
        if(position ==0){
            return "Prayer";
        }
        else if(position ==1){
            return "Find Other to Help";
        }
        else if(position == 2){
            return "Taking Action";
        }
        return null;
    }
    @Override
    public int getCount() {

        return 3;

    }
}
