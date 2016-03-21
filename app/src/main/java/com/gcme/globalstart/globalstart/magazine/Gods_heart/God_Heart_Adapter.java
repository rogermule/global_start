package com.gcme.globalstart.globalstart.magazine.Gods_heart;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by BENGEOS on 3/16/16.
 */
public class God_Heart_Adapter extends FragmentPagerAdapter {

    public God_Heart_Adapter(FragmentManager fm) {
        super(fm);
        // TODO Auto-generated constructor stub
    }
    @Override
    public Fragment getItem(int position) {

        Fragment fragment;
        if(position ==0){
            fragment = new Heart1();
            return fragment;
        }
        else if(position ==1){
            fragment = new Heart2();
            return fragment;
        }
        else if(position ==2){
            fragment = new Heart3();
            return fragment;
        }
        return null;
    }
    @Override
    public CharSequence getPageTitle(int position) {
        if(position ==0){
            return "Teenagers";
        }
        else if(position ==1){
            return "Biblical Examples";
        }
        else if(position == 2){
            return "Take Action";
        }
        return null;
    }
    @Override
    public int getCount() {

        return 3;

    }
}
