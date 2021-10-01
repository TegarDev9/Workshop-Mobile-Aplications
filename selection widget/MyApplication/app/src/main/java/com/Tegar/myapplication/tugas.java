package com.Tegar.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.text.DateFormatSymbols;

public class tugas extends AppCompatActivity {
    ListView listViewMonth;
    String[] months;
    Spinner spinner;

    private static final String[] KOTA = new String[]{
            "Ambon","Aceh","Bandung","Bengkulu","Bangyuwangi","Bangkalan","Banjarmasin","Bondowoso","Bojonegoro"
    };
    AutoCompleteTextView autoCompletekota;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tugas);

        autoCompletekota = findViewById(R.id.autoCompletekota);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, KOTA);
        autoCompletekota.setAdapter(adapter);

        listViewMonth = findViewById(R.id.ListViewMonth);
        months = new DateFormatSymbols().getMonths();

        ArrayAdapter<String> list = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, months);
        listViewMonth.setAdapter(adapter);

        listViewMonth.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), "Anda Klik bulan: " + parent.getItemAtPosition(position), Toast.LENGTH_SHORT).show();

                }
            });

        }
    }