package com.example.dwiprayogo.cobatanggal;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by dwi.prayogo on 9/6/2017.
 */

public class WelcomeActivity extends AppCompatActivity {
    public static boolean startedApp;

    /**
     * Called when the activity is first created.
     */
    protected boolean _active = true;
    //protected int _splashTime = 500;
    protected int _splashTime = 1000;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        Thread myThread = new Thread(){
            @Override
            public void run() {
                try {
                    sleep(_splashTime);
                    finish();
                    Intent intent = new Intent(getApplicationContext(),Login_2.class);
                    startActivity(intent);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        myThread.start();
    }
}
