package com.tegar.recycleviewdatamovies;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    MoviesRecyclerViewAdapter adapter;
    ArrayList<Movies> objMovies = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);

        objMovies.add(new Movies("The Suicide Squad", "Action, Adventure, Comedy", "James Gunn", 7.3,"Supervillains Harley Quinn, Bloodsport, Peacemaker and a collection of nutty cons at Belle Reve prison join the super-secret, super-shady Task Force X as they are dropped off at the remote, enemy-infused island of Corto Maltese.","6 Agustus 2021"));

adapter = new MoviesRecyclerViewAdapter(objMovies);
recyclerView.setAdapter(adapter);
recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
    }
}