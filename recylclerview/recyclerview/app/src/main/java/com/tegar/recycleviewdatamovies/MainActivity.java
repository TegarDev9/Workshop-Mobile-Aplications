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
        objMovies.add(new Movies("The Last :Naruto the Movie", "Animation,Action,Adventure","Tsuneo Kobayashi",7.7,"Hanabi Hyuuga, the younger sister of Hinata is kidnapped by Toneri Ootsutsuki, and a disheartened Naruto Uzumaki must put himself together to help his new found love save her younger sister. Naruto's abilities are put to the test when he loses his most powerful form the Sage of Six Paths cloak.Silver Wind","2014"));
        objMovies.add(new Movies("Stand by Me Doraemon 2","Animation,Comedi,Darama","Ryuichi yagi", 7.9,"One day Nobita Nobi found a old stuffed bear which his grandmother gave it to him. Nobita decided to go back in time to meet his grandmother. Grandma accepts Nobita's belief that he is a young child who has suddenly arrived. Nobita' s grandmother said I want to see your bride once. And from this one sentence of grandma Doraemon and Nobita's big blasphemy begins. Nobita and Doraemon were trying to show their grandmother a marriage ceremony, but Adult Nobita escaped on the day of his marriage ceremony. Adult Gian and Suneo looking for Nobita. Shizuka who keeps waiting with confidence. Nobita and Doraemon take the time to fulfill the wishes of grandma, to meet the wishes of her family, friends and Shizuka Minamoto, who she likes very much.","2020"));
adapter = new MoviesRecyclerViewAdapter(objMovies);
recyclerView.setAdapter(adapter);
recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
    }
}