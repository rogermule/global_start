package com.gcme.globalstart.globalstart.Testimony;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.gcme.globalstart.globalstart.R;
import com.gcme.globalstart.globalstart.Sync.JSONParser;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by BENGEOS on 3/18/16.
 */
public class Testimony_Fragment extends Fragment {
    private Toolbar toolbar;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private EditText fullName, Testimony;
    private Button Send;
    private AlertDialog.Builder builder;
    private Context myContext;
    private TelephonyManager Tele;
    private JSONParser myParser;
    private LocationManager locManager;
    private LocationListener locListener = new MyLocationListener();
    private String GPS_Loc;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        locManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.testimony_page, container, false);
        try{
            locManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locListener);
            if ( Build.VERSION.SDK_INT >= 23 &&
                    ContextCompat.checkSelfPermission(myContext, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                    ContextCompat.checkSelfPermission(myContext, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                locManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locListener);
            }

        }catch (Exception e){

        }

        Tele = (TelephonyManager) getActivity().getSystemService(Context.TELEPHONY_SERVICE);
        myContext = getActivity();
        myParser = new JSONParser();
        fullName = (EditText) view.findViewById(R.id.txte_full_name);
        Testimony = (EditText) view.findViewById(R.id.txte_testimony);
        Send = (Button) view.findViewById(R.id.btn_send_testimony);
        Send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(locManager.isProviderEnabled(LocationManager.GPS_PROVIDER)){
                    if (Testimony.getText().toString().length() > 10) {
                        locManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locListener);
                        if ( Build.VERSION.SDK_INT >= 23 &&
                                ContextCompat.checkSelfPermission(myContext, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                                ContextCompat.checkSelfPermission(myContext, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                            locManager .requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locListener);
                        }
                        new Make_Service(fullName.getText().toString(), Testimony.getText().toString()).execute();
                    } else {
                        Show_Dialog("Your Testimony is to short to send, Write more");
                    }
                }else{
                    Show_Dialog("Sorry, location is not determined. Please enable location providers\n");
                }
            }
        });
        return view;
    }
    public void Show_Dialog(String str) {
        DialogInterface.OnClickListener dialogclicklistener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case DialogInterface.BUTTON_NEGATIVE:
                        break;
                    case DialogInterface.BUTTON_POSITIVE:
                        break;
                }
            }
        };
        builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.app_name).setMessage(str).setCancelable(true).setNegativeButton("Ok", dialogclicklistener).show();
    }

    public class Make_Service extends AsyncTask<String, String, String> {
        private ProgressDialog myDialog;
        private String Testimony, FullName;
        private JSONArray Found;

        public Make_Service(String name, String testimony) {
            Testimony = testimony;
            FullName = name;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Found = new JSONArray();
            myDialog = new ProgressDialog(myContext);
            myDialog.setTitle("Sending Testimony");
            myDialog.setCancelable(false);
            myDialog.show();
        }

        @Override
        protected String doInBackground(String... params) {
            List<NameValuePair> param = new ArrayList<NameValuePair>();
            param.add(new BasicNameValuePair("Testimony", Testimony));
            param.add(new BasicNameValuePair("Full_Name", FullName));
            param.add(new BasicNameValuePair("GPS", GPS_Loc));
            param.add(new BasicNameValuePair("Service", "Send_Testimony"));
            param.add(new BasicNameValuePair("Param", "==="));
            param.add(new BasicNameValuePair("IMEI", Tele.getDeviceId()));
            try {
                Log.i("Sync_Service", "New Testimony Sent: " + param.toString());
                JSONObject myObject = myParser.makeHttpRequest("http://192.168.0.41/SyncSMS/public/api", "POST", param);
                Found = myObject.getJSONArray("Testimony");
            } catch (Exception e) {

            }

            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            myDialog.cancel();
            if (Found.length() > 0) {
                Show_Dialog("Your Testimony sent Successfully");
            } else {
                Show_Dialog("Your Testimony not sent Successfully. Please try agian \n" + Found.toString());
            }
            super.onPostExecute(s);
        }
    }
    class MyLocationListener implements LocationListener {
        private String Lat,Long;
        public MyLocationListener(){
            Lat = "Lat: ";
            Long = "Long: ";
            GPS_Loc = getLocation();
        }
        public String getLocation(){
            return Lat+","+Long;
        }
        @Override
        public void onLocationChanged(Location location) {
            if(location != null){
                Lat = "Lat:" + location.getLatitude();
                Long = "Long:" + location.getLongitude();
                GPS_Loc = getLocation();
            }
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }
    }
}
