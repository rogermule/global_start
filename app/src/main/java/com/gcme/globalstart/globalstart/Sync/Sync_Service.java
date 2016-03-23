package com.gcme.globalstart.globalstart.Sync;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.telephony.TelephonyManager;
import android.util.Log;

import com.gcme.globalstart.globalstart.DataTypes.News_Data;
import com.gcme.globalstart.globalstart.Database.MyDatabase;
import com.gcme.globalstart.globalstart.Global_Start;
import com.google.gson.Gson;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by BENGEOS on 3/17/16.
 */
public class Sync_Service extends Service {
    private MyDatabase myDatabase;
    private JSONParser myParser;
    private TelephonyManager Tele;
    private Context myContext;
    private ImageDownloader imageDownloader;
    private FileManager myFileManager;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        //Toast.makeText(this,"Service Started---->:",Toast.LENGTH_LONG).show();
        super.onCreate();
        myDatabase = new MyDatabase(this);
        myParser = new JSONParser();
        Tele = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        myContext = this;
        imageDownloader = new ImageDownloader(myContext,"");
        //new Make_Service().execute();
        myFileManager = new FileManager();
        //Log.i("Sync_Service", "Service Started");
        //Toast.makeText(this,"Sync Service Started:",Toast.LENGTH_LONG).show();
    }
    public class Make_Service extends AsyncTask<String,String,String>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Log.i("Sync_Service", "Sync Service Started");
        }
        private List<NameValuePair> init() {
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            Gson myGson = new Gson();
            params.add(new BasicNameValuePair("IMEI", Tele.getDeviceId()));
            if(myDatabase.get_All_NewsFeedLog().size()<0){
                String str = myGson.toJson(myDatabase.get_All_NewsFeedLog());
                params.add(new BasicNameValuePair("Service", "Send_NewsFeedLog"));
                params.add(new BasicNameValuePair("Param", str));
            }else{
                params.add(new BasicNameValuePair("Service", "GetNew_NewsFeed"));
                params.add(new BasicNameValuePair("Param", myGson.toJson(new ArrayList<News_Data>())));
            }

            Log.i("Sync_Service", "Request: \n" + params.toString());
            return params;
        }
        @Override
        protected String doInBackground(String... params) {
            try {
                //JSONObject myObject = myParser.makeHttpRequest(	"http://192.168.0.41/SyncSMS/public/api", "POST", init());
                JSONObject myObject = myParser.makeHttpRequest(	"http://192.168.0.18/SyncSMS/public/api", "POST", init());
                Log.i("Sync_Service", "Server Response: \n" + myObject.toString());
                Global_Start.addNew_NewsFeed(myObject.getJSONArray("NewsFeeds"));
                Log.i("Sync_Service", "Adding NewsFeed:\n"+myObject.getJSONArray("NewsFeeds"));
                Log.i("Sync_Service", "Sync Service Slept!");
                Thread.sleep(5000);
                Log.i("Sync_Service", "Sync Service awake!");
            }catch (Exception e){
                Log.i("Sync_Service", "Error Occurred:\n"+e.toString());
                try{
                    Thread.sleep(5000);
                }catch (Exception t){
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Log.i("Sync_Service", "Sync Service Restarting...!");
            ArrayList<News_Data> image_urls = myDatabase.getImagesURLs();
            if(image_urls.size()>0){
                File file = myFileManager.Create_ImageFile("Image"+image_urls.get(0).getNewsID()+".jpg");
                Log.i("Sync_Service", "Image Destination :\n"+file.getAbsolutePath());
                if(!file.isFile() || file.getTotalSpace()<1){
                    if(!imageDownloader.isRunning()){
                        imageDownloader = new ImageDownloader(myContext,image_urls.get(0).getImage());
                        imageDownloader.setImageURL("Image"+image_urls.get(0).getNewsID()+".jpg",image_urls.get(0).getImage());
                        imageDownloader.execute();
                        Log.i("Sync_Service", "Downloading :\n"+image_urls.get(0).getImage());
                    }
                }

            }
            new Make_Service().execute();
            Log.i("Sync_Service", "Sync Service Restarted!");
        }
    }
}
