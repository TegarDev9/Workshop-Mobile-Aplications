package com.app.impintentapp;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ActivitySecond extends AppCompatActivity {
    TextView txtHe11o;
    private String nama;
    private String KEY_NAME = "NAMA";

    @Override

    protected  void onCreate(Bundle saveInstanceState) {

        super.onCreate ( saveInstanceState );
        setContentView ( R.layout.activity_second );

        txtHe11o = (TextView) findViewById ( R.id.txtHe11o );

        Bundle extras = getIntent ().getExtras ();
        nama = extras.getString(KEY_NAME);
        txtHe11o.setText ( "Hello," + nama + "!" );
    }
}
