package com.example.androidtimer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //this method will make the timer and then destroy itself after the time has reached;
        new CountDownTimer(10000,1000){
            public void onTick(long millisUntilFinished) {
                Log.i("Ticked", String.valueOf(millisUntilFinished/1000));
            }

            public void onFinish() {
                Log.i("Tick finished", "the counter has finihed now starting the self destruction");
            }
        };


        //this method will make the timer existing even when not in use;
        Handler handler = new Handler();
        Runnable run = new Runnable() {
            @Override
            public void run() {
                //Code to run;
                Log.i("Done","Counter has ticked");

                handler.postDelayed(this, 1000);
            }
        };

        handler.post(run);


    }
}