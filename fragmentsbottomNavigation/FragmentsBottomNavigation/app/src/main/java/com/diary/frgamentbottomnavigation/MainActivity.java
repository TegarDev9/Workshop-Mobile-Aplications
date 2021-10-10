package com.diary.frgamentbottomnavigation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {
//Memberikan action ke battom navigation
    BottomNavigationView bottomNavigation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_main );
        bottomNavigation = findViewById ( R.id.bottom_navigation );
        //berfungsi agar fragment menjadi defauld ketika di jalankan pertama
        getSupportFragmentManager ().beginTransaction ().replace ( R.id.fragment_container, new ChatFragment () ).commit ();
        //memberikan action jika nanti di klick apa yang akan terjadi
        bottomNavigation.setOnItemReselectedListener ( new NavigationBarView.OnItemReselectedListener () {
            @Override
            public void onNavigationItemReselected(@NonNull MenuItem item) {
                //Membuat objek dari sebuah fragment
               Fragment selectedFragment = null;

                switch ( (item.getItemId ())){

                    case R.id.nav_chats:
                        //Memanggil chat fragment yang di simpan di objek selected fragment
                        selectedFragment = new ChatFragment ();
                        break;

                    case R.id.nav_status:
                        selectedFragment = new StatusFragment ();
                        break;
                    case R.id.nav_calls:
                        selectedFragment = new callFragment ();
                        break;
                }
                //fungsi mengubah setipa fragment dengan dengan sebuah fungsi get,reolace itu bergantian sedangkan trancation menumpuk

getSupportFragmentManager ().beginTransaction ().replace ( R.id.fragment_container, selectedFragment ).commit ();

                return ;



            }
        } );
    }
}