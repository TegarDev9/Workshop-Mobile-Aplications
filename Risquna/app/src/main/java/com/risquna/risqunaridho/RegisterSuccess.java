package com.risquna.risqunaridho;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;


public class RegisterSuccess extends AppCompatActivity {
    private Button btn_nextUp2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_register_success);

        btn_nextUp2 = findViewById( R.id.btn_nextUp2);
        btn_nextUp2.setOnClickListener(new View.OnClickListener() {
            @Override
            /**Membuat inten pindah */
            public void onClick(View v) {
                Intent pindah = new Intent(RegisterSuccess.this, MainActivity.class);
                startActivity(pindah);

            }
        });
    }
}