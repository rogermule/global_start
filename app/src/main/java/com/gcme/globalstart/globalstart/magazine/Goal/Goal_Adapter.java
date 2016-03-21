package com.gcme.globalstart.globalstart.magazine.Goal;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by BENGEOS on 3/16/16.
 */
public class Goal_Adapter extends FragmentPagerAdapter {

    public Goal_Adapter(FragmentManager fm) {
        super(fm);
        // TODO Auto-generated constructor stub
    }
    @Override
    public Fragment getItem(int position) {

        Fragment fragment;
        if(position ==0){
            fragment = new Goal1();
            return fragment;
        }
        else if(position ==1){
            fragment = new Goal2();
            return fragment;
        }
        else if(position ==2){
            fragment = new Goal3();
            return fragment;
        }
        else if(position ==3){
            fragment = new Goal4();
            return fragment;
        }
        else if(position ==4){
            fragment = new Goal5();
            return fragment;
        }
        else if(position ==5){
            fragment = new Goal6();
            return fragment;
        }
        return null;
    }
    @Override
    public CharSequence getPageTitle(int position) {
        if(position ==0){
            return "Know the Goal";
        }
        else if(position ==1){
            return "Win";
        }
        else if(position == 2){
            return "Sowing & Evangelism";
        }
        else if(position == 3){
            return "Build";
        }
        else if(position == 4){
            return "Send";
        }
        else if(position == 5){
            return "Spiritual Movements";
        }
        return null;
    }
    @Override
    public int getCount() {

        return 6;

    }
}
