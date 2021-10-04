package com.Tegar.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Menu extends AppCompatActivity {
    private Button ButtonLogin2;

    @Override
    /**mencari view id */
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);

        ButtonLogin2 =findViewById(R.id.btnLogin2);
        ButtonLogin2.setOnClickListener(new View.OnClickListener() {
            @Override
            /**Membuat pindah dengan intent**/
            public void onClick(View v) {
                Intent pindah = new Intent(Menu.this,tugas.class) ;
                startActivity(pindah);




                    }
                });




            }
        }
