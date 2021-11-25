package com.risquna.risqunaridho;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;


public class SplashScreen extends Activity {

    protected boolean _active = true;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_splashscreen );
        //Membuat jeda pada Splash screen
        Thread timeThread = new Thread () {
            public void run() {
                try {
                    sleep ( 2500 );
                } catch (InterruptedException e) {
                    e.printStackTrace ();
                } finally {
                    startMainScreen ();
                }
            }
        };
        timeThread.start ();

    }

    @Override
    protected void onPause() {
        super.onPause ();
        finish ();
    }

    public  void startMainScreen(){
        Intent intent = new Intent (this, Login.class);
        startActivity ( intent );
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction () == MotionEvent.ACTION_DOWN) {
            _active = false;

        }
        return true;
        }
    }



