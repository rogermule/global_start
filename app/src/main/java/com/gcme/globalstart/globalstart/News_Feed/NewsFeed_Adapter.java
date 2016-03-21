package com.gcme.globalstart.globalstart.News_Feed;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.gcme.globalstart.globalstart.R;
import com.gcme.globalstart.globalstart.Sync.FileManager;

import java.io.File;
import java.util.ArrayList;


/**
 * Created by BENGEOS on 3/17/16.
 */
public class NewsFeed_Adapter extends RecyclerView.Adapter<NewsFeed_Adapter.DataObjectHolder> {
    private static String LOG_TAG = "MyRecyclerViewAdapter";
    private ArrayList<NewsFeed_Object> NewsFeeds;
    private static MyClickListener myClickListener;
    private static Context myContext;
    private FileManager fileManager;

    public static class DataObjectHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView Title,Content;
        TextView dateTime;
        ImageView Image;

        public DataObjectHolder(View itemView) {
            super(itemView);
            Title = (TextView) itemView.findViewById(R.id.txt_news_title);
            Content = (TextView) itemView.findViewById(R.id.txt_news_content);
            Image = (ImageView) itemView.findViewById(R.id.img_news_image);
            dateTime = (TextView) itemView.findViewById(R.id.txt_news_pub_date);

            Log.i(LOG_TAG, "Adding Listener");
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
           // myClickListener.onItemClick(getAdapterPosition(), v);
            Intent intent = new Intent(myContext,NewsFeed_Detail.class);
            myContext.startActivity(intent);
        }

    }
    public void setOnItemClickListener(MyClickListener myClickListener) {
        this.myClickListener = myClickListener;
    }
    public NewsFeed_Adapter(Context context, ArrayList<NewsFeed_Object> newsFeeds){
        this.NewsFeeds = newsFeeds;
        this.myContext = context;
        fileManager = new FileManager();

    }
    @Override
    public int getItemCount() {
        return NewsFeeds.size();
    }
    @Override
    public DataObjectHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.newsfeed_items,parent,false);
        DataObjectHolder dataObjectHolder = new DataObjectHolder(view);
        return dataObjectHolder;
    }
    @Override
    public void onBindViewHolder(DataObjectHolder holder, int position) {
        holder.Title.setText(NewsFeeds.get(position).getTitle());
        File file = fileManager.Create_ImageFile("Image"+NewsFeeds.get(position).getNewsID()+".jpg");
        if(file != null){
            Bitmap image = BitmapFactory.decodeFile(file.getAbsolutePath());
            holder.Image.setImageBitmap(image);
        }
        holder.Content.setText(NewsFeeds.get(position).getContent());
        holder.dateTime.setText(NewsFeeds.get(position).getPub_Date());

    }
    public void addItem(NewsFeed_Object news){
        NewsFeeds.add(news);
    }
    public interface MyClickListener {
        public void onItemClick(int position, View v);
    }
}
