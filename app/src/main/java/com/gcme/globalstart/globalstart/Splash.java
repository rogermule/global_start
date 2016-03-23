package com.gcme.globalstart.globalstart;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Roger on 3/23/2016.
 */
public class Splash extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);

        Thread splash = new Thread(){
            @Override
            public void run() {
                try {
                    sleep(5000);
                } catch(InterruptedException e){
                } finally {
                    getNextActivity();
                }
                //super.run();
            }
        };

        splash.start();
    }

    private void getNextActivity() {
        Intent intent = new Intent(this, MainActivity.class );
        startActivity(intent);

    }
    @Override
    protected void onDestroy() {

        super.onDestroy();
    }
}
