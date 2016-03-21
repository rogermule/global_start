package com.gcme.globalstart.globalstart.News_Feed;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gcme.globalstart.globalstart.Database.MyDatabase;
import com.gcme.globalstart.globalstart.R;

/**
 * Created by BENGEOS on 3/17/16.
 */
public class NewsFeed_Fragment extends Fragment {
    private static RecyclerView myRecyclerView;
    private static RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private static MyDatabase myDatabase;
    private static Context myContext;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.newsfeed_page,container,false);
        myDatabase = new MyDatabase(getActivity());
        myRecyclerView = (RecyclerView) view.findViewById(R.id.my_recycler_view);
        mLayoutManager = new LinearLayoutManager(getActivity());
        myRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new NewsFeed_Adapter(getActivity(),myDatabase.get_All_News_Object());
        myRecyclerView.setAdapter(mAdapter);
        myContext = getActivity();
        return view;
    }
    public static void update_view(){
        mAdapter = new NewsFeed_Adapter(myContext,myDatabase.get_All_News_Object());
        myRecyclerView.setAdapter(mAdapter);
    }
}
