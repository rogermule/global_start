package com.gcme.globalstart.globalstart;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.gcme.globalstart.globalstart.Menu_Options.ContactUsOptions;
import com.gcme.globalstart.globalstart.Menu_Options.MoreOptions;
import com.gcme.globalstart.globalstart.News_Fee_2.News_List;
import com.gcme.globalstart.globalstart.Testimony.Testimony_Fragment;
import com.gcme.globalstart.globalstart.magazine.Do.Do_Fragment;
import com.gcme.globalstart.globalstart.magazine.Goal.Goal_Fragment;
import com.gcme.globalstart.globalstart.magazine.Gods_heart.God_Heart_Fragment;
import com.gcme.globalstart.globalstart.magazine.Intro.IntroFragment;
import com.gcme.globalstart.globalstart.magazine.Learn.Learn_Fragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity{
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    CollapsingToolbarLayout collapsingToolbarLayout;
    static ImageView image;
    static int imagestate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        image = (ImageView) findViewById(R.id.image);
        image.setImageResource(R.drawable.global_head);
        imagestate = 1;
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

       // getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbarLayout.setTitle("Global Start");
        collapsingToolbarLayout.setExpandedTitleColor(getResources().getColor(android.R.color.transparent));

    }

    public static void setImage(){
        if(imagestate == 0){
            image.setImageResource(R.drawable.global_head);
            imagestate = 1;
        }
        else if(imagestate == 1){
            image.setImageResource(R.drawable.global);
            imagestate = 0;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement

        if(id == R.id.action_about){
                MoreOptions more = new MoreOptions();
                Bundle b = new Bundle();
                b.putString("Title","About Us");
                b.putString("Detail", "This App is a global start application which aims to bring about a chhnage in the way the society acts and walks in his way which Christ. \n");
                more.setArguments(b);
                more.show(getFragmentManager(),"About us");
            return true;
        }
        else if(id == R.id.action_contact){
            ContactUsOptions more = new ContactUsOptions();
            Bundle b = new Bundle();
            b.putString("Title","About Us");
            b.putString("Detail", "This App is a global start application which aims to bring about a chhnage in the way the society acts and walks in his way which Christ. \n");
            more.setArguments(b);
            more.show(getFragmentManager(),"About us");
            return true;
        }
        else if(id == R.id.menu_item_share){

        }
        else if( id == R.id.action_share){

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new News_List(), "News Feed");
        adapter.addFragment(new IntroFragment(), "Intro");
        adapter.addFragment(new God_Heart_Fragment(),"God`s Heart");
        adapter.addFragment(new Learn_Fragment(), "Learn");
        adapter.addFragment(new Do_Fragment(), "Do");
        adapter.addFragment(new Goal_Fragment(), "Goal");
        adapter.addFragment(new Testimony_Fragment(), "Testimony");
        viewPager.setAdapter(adapter);

    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

}
