package com.gcme.globalstart.globalstart.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.gcme.globalstart.globalstart.DataTypes.News_Data;
import com.gcme.globalstart.globalstart.DataTypes.News_Log;
import com.gcme.globalstart.globalstart.Global_Start;
import com.gcme.globalstart.globalstart.News_Feed.NewsFeed_Object;

import java.util.ArrayList;


public class MyDatabase {
    private SQLiteDatabase myDatabase;
    private SQL_Helper mySQL;
    private Context myContext;

    public MyDatabase(Context context){
        myContext = context;
        mySQL = new SQL_Helper(myContext);
        myDatabase = mySQL.getWritableDatabase();
        mySQL.createTables(Global_Start.Table_NewsFeed, Global_Start.NewsFeed_FIELDS);
        mySQL.createTables(Global_Start.Table_NewsFeed_Log, Global_Start.NewsFeedLog_FIELDS);
    }

    public long insert(String DB_Table,ContentValues cv){
        long state = myDatabase.insert(DB_Table, null, cv);
        return state;
    }
    public long Delete_All(String DB_Table){
        long state = myDatabase.delete(DB_Table, null, null);
        return state;
    }
    public long remove(String DB_Table,int id){
        String[] args = {""+id};
        long val = myDatabase.delete(DB_Table, "id = ?", args);
        return val;
    }
    public long remove_news_log(String News_ID){
        String[] args = {""+News_ID};
        long val = myDatabase.delete(Global_Start.Table_NewsFeed_Log, "News_ID = ?", args);
        return val;
    }
    public long update(String DB_Table,ContentValues cv,int id){
        String[] args = {""+id};
        long state = myDatabase.update(DB_Table, cv, "id = ?", args);
        return state;
    }
    public int count(String DB_Table){
        Cursor c = myDatabase.query(DB_Table, getColumns(DB_Table), null, null, null, null, null);
        return c.getCount();
    }
    public ArrayList<News_Data> get_All_NewsFeed(String Category){
        String DB_Table = Global_Start.Table_NewsFeed;
        String[] Table_Fields = Global_Start.NewsFeed_Column;
        ArrayList<News_Data> found = new ArrayList<News_Data>();
        Cursor c = myDatabase.query(DB_Table, Table_Fields, null, null, null, null, null);
        c.moveToFirst();
        for(int i=0;i<c.getCount();i++){
            c.moveToPosition(i);
            News_Data dis = new News_Data();
            dis.setId(Integer.valueOf(c.getString(c.getColumnIndex(Table_Fields[0]))));
            dis.setNewsID(Integer.valueOf(c.getString(c.getColumnIndex(Table_Fields[1]))));
            dis.setTitle(c.getString(c.getColumnIndex(Table_Fields[2])));
            dis.setContent(c.getString(c.getColumnIndex(Table_Fields[3])));
            dis.setImage(c.getString(c.getColumnIndex(Table_Fields[4])));
            dis.setPublished_date(c.getString(c.getColumnIndex(Table_Fields[5])));
            found.add(dis);
        }
        return found;
    }
    public ArrayList<News_Log> get_All_NewsFeedLog(){
        ArrayList<News_Log> found = new ArrayList<News_Log>();
        String DB_Table = Global_Start.Table_NewsFeed_Log;
        String[] Table_Fields = Global_Start.NewsFeedLog_FIELDS;
        Cursor c = myDatabase.query(DB_Table, Table_Fields, null, null, null, null, null);
        c.moveToFirst();
        for(int i=0;i<c.getCount();i++){
            c.moveToPosition(i);
            News_Log dis = new News_Log();
            dis.setNews_ID(Integer.valueOf(c.getString(c.getColumnIndex(Table_Fields[0]))));
            found.add(dis);
        }
        return found;
    }
    public ArrayList<News_Data> get_All_News(){
        ArrayList<News_Data> found = new ArrayList<News_Data>();
        String DB_Table = Global_Start.Table_NewsFeed;
        String[] Table_Fields = Global_Start.NewsFeed_FIELDS;
        Cursor c = myDatabase.query(DB_Table, Table_Fields, null, null, null, null, null);
        c.moveToFirst();
        for(int i=0;i<c.getCount();i++){
            c.moveToPosition(i);
            News_Data dis = new News_Data();
            dis.setNewsID(Integer.valueOf(c.getString(c.getColumnIndex(Table_Fields[0]))));
            dis.setTitle(c.getString(c.getColumnIndex(Table_Fields[1])));
            dis.setContent(c.getString(c.getColumnIndex(Table_Fields[2])));
            dis.setImage(c.getString(c.getColumnIndex(Table_Fields[3])));
            dis.setPublished_date(c.getString(c.getColumnIndex(Table_Fields[4])));
            found.add(dis);
        }
        return found;
    }
    public ArrayList<NewsFeed_Object> get_All_News_Object(){
        ArrayList<NewsFeed_Object> found = new ArrayList<NewsFeed_Object>();
        String DB_Table = Global_Start.Table_NewsFeed;
        String[] Table_Fields = Global_Start.NewsFeed_FIELDS;
        Cursor c = myDatabase.query(DB_Table, Table_Fields, null, null, null, null, null);
        c.moveToFirst();
        for(int i=0;i<c.getCount();i++){
            c.moveToPosition(i);
            NewsFeed_Object dis = new NewsFeed_Object();
            dis.setNewsID(c.getString(c.getColumnIndex(Table_Fields[0])));
            dis.setTitle(c.getString(c.getColumnIndex(Table_Fields[1])));
            dis.setContent(c.getString(c.getColumnIndex(Table_Fields[2])));
            dis.setImage(c.getString(c.getColumnIndex(Table_Fields[3])));
            dis.setPub_Date(c.getString(c.getColumnIndex(Table_Fields[4])));
            found.add(dis);
        }
        return found;
    }
    public ArrayList<String> getImagesURL(){
        ArrayList<String> image_urls = new ArrayList<String>();
        String DB_Table = Global_Start.Table_NewsFeed;
        String[] Table_Fields = Global_Start.NewsFeed_FIELDS;
        Cursor c = myDatabase.query(DB_Table, Table_Fields, null, null, null, null, null);
        c.moveToFirst();
        for(int i=0;i<c.getCount();i++){
            c.moveToPosition(i);
            image_urls.add(c.getString(c.getColumnIndex(Table_Fields[3])));
        }
        return image_urls;
    }
    public ArrayList<News_Data> getImagesURLs(){
        ArrayList<News_Data> image_urls = new ArrayList<News_Data>();
        String DB_Table = Global_Start.Table_NewsFeed;
        String[] Table_Fields = Global_Start.NewsFeed_FIELDS;
        Cursor c = myDatabase.query(DB_Table, Table_Fields, null, null, null, null, null);
        c.moveToFirst();
        for(int i=0;i<c.getCount();i++){
            c.moveToPosition(i);
            News_Data news_data = new News_Data();
            news_data.setImage(c.getString(c.getColumnIndex(Table_Fields[3])));
            news_data.setNewsID(Integer.valueOf(c.getString(c.getColumnIndex(Table_Fields[0]))));
            image_urls.add(news_data);
        }
        return image_urls;
    }
    private String[] getColumns(String DB_Table){
        String[] strs = null;
        if(DB_Table == Global_Start.Table_NewsFeed){
            strs = Global_Start.NewsFeed_Column;
        }else if(DB_Table == Global_Start.Table_NewsFeed){
            strs = Global_Start.NewsFeed_Column;
        }
        return strs;
    }
}
