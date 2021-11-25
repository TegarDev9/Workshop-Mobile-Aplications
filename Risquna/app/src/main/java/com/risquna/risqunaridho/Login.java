package com.risquna.risqunaridho;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;



public class Login extends AppCompatActivity {
    private Button btn_signin;
    EditText et_email, et_password;



    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_login );

        et_email = (EditText)findViewById( R.id.et_email);
        et_password = (EditText)findViewById( R.id.et_password);
        btn_signin = (Button)findViewById( R.id.btn_signin);



        btn_signin = findViewById( R.id.btn_signin);
        btn_signin.setOnClickListener(new View.OnClickListener() {
            @Override
            /**Membuat inten pindah */
            public void onClick(View v) {
                Intent pindah = new Intent(Login.this, registrasi.class);
                startActivity(pindah);
            }
        });


    }
}


