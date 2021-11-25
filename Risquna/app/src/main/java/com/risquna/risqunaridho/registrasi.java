package com.risquna.risqunaridho;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;




public class registrasi extends AppCompatActivity {
    EditText et_emailUp1, et_passwordUp1;
    TextView tv_noteUp3;
    Button btn_nextUp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_registrasi );

        et_emailUp1 = (EditText) findViewById ( R.id.et_emailUp1 );
        et_passwordUp1 = (EditText) findViewById ( R.id.et_passwordUp1 );
        tv_noteUp3 = (TextView) findViewById ( R.id.tv_noteUp3 );
        btn_nextUp = (Button) findViewById ( R.id.btn_nextUp );


        btn_nextUp = findViewById( R.id.btn_nextUp);
        btn_nextUp.setOnClickListener(new View.OnClickListener() {
            @Override
            /**Membuat inten pindah */
            public void onClick(View v) {
                Intent pindah = new Intent(registrasi.this, Verify.class);
                startActivity(pindah);
            }
        });


    }
}