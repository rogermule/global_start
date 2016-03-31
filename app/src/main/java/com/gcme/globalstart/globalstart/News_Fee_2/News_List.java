package com.gcme.globalstart.globalstart.News_Fee_2;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.gcme.globalstart.globalstart.DataTypes.News_Data;
import com.gcme.globalstart.globalstart.News_Detail;
import com.gcme.globalstart.globalstart.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class News_List extends Fragment {
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    Button newsBut;
    News_Data d;
    private ListView lv_news;

    public News_List() {
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
        View view = inflater.inflate(R.layout.fragment_news_list, container, false);

        lv_news = (ListView) view.findViewById(R.id.ls_news_list);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            lv_news.setNestedScrollingEnabled(true);
        }
        //sample data
        List<News_Data> disciplesList = new ArrayList<News_Data>();

        //sample objects
        d = new News_Data();
        d.setTitle("Global start has been Launched");
        d.setContent("The highly anticipated mobile based system has been officially released. The app is believed to " +
                "change the way teenager evangelism is accomplished and helps to equip the youth, church and every one to do a strong teenager evangelism.");
        d.setDescription("The highly anticipated mobile based system has been officially released. The app is believed to change the " +
                "way teenager evangelism is accomplished and helps to equip the youth, church and every one to do a strong teenager " +
                "evangelism. \n \n The highly anticipated mobile based system has been officially released. The app is believed to change the way " +
                "teenager evangelism is accomplished and helps to equip the youth, church and every one to do a strong teenager evangelism." +
                "The highly anticipated mobile based system has been officially released. The app is believed to change the way teenager evangelism " +
                "is accomplished and helps to equip the youth, church and every one to do a strong teenager evangelism." +
                "\n" +
                " \n" +
                " The highly anticipated mobile based system has been officially released. The app is believed to change the way" +
                "teenager evangelism is accomplished and helps to equip the youth, church and every one to do a strong teenager evangelism." +
                "The highly anticipated mobile based system has been officially released. The app is believed to change the way teenager evangelism" +
                "is accomplished and helps to equip the youth, church and every one to do a strong teenager evangelism.");
        d.setId(1);
        d.setNewsID(1);
        d.setPublished_date("1/3/2016");

        disciplesList.add(d);
        disciplesList.add(d);
        disciplesList.add(d);
        disciplesList.add(d);


        lv_news.setAdapter(new MyDiscipleListAdapter(getContext(), (ArrayList<News_Data>) disciplesList));
        return view;

    }


    public class MyDiscipleListAdapter extends BaseAdapter
    {
        Context context;
        ArrayList<News_Data> news_list;
        public MyDiscipleListAdapter(Context context,ArrayList<News_Data> disciple)
        {
            this.context = context;
            news_list = disciple;
        }


        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return news_list.size();
        }

        @Override
        public Object getItem(int arg0) {
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            LayoutInflater inflate = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflate.inflate(R.layout.newsfeed_items,null);

            ImageView iv_newsImage = (ImageView) convertView.findViewById(R.id.img_news_image);

            TextView news_title =(TextView)convertView.findViewById(R.id.txt_news_title);
            TextView news_content = (TextView)convertView.findViewById(R.id.txt_news_content);
            TextView news_pub_date = (TextView)convertView.findViewById(R.id.txt_news_pub_date);

            Picasso.with(getActivity()).load("http://www.flooringvillage.co.uk/ekmps/shops/flooringvillage/images/request-a-sample--547-p.jpg").into(iv_newsImage);

            final String title = news_list.get(position).getTitle();
            final String content = news_list.get(position).getContent();
            final String date = news_list.get(position).getPublished_date();
            final String description = news_list.get(position).getDescription();
            final String image = news_list.get(position).getImage();

            news_title.setText(title);
            news_content.setText(content);
            news_pub_date.setText(date);

            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(),News_Detail.class);
                    Bundle b = new Bundle();
                    b.putString("title", title);
                    b.putString("content", content);
                    b.putString("date", date);
                    b.putString("desc", description);
                    intent.putExtras(b);
                    startActivity(intent);
                }
            });

            return convertView;
        }
    }



}
