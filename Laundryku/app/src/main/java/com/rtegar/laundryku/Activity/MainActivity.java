package com.rtegar.laundryku.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.rtegar.laundryku.API.APIRequestData;
import com.rtegar.laundryku.API.RetroServer;
import com.rtegar.laundryku.Adapter.AdapterData;
import com.rtegar.laundryku.Model.DataModel;
import com.rtegar.laundryku.Model.ResponseModel;
import com.rtegar.laundryku.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private RecyclerView rvData;
    private RecyclerView.Adapter adData;
    private RecyclerView.LayoutManager lmData;
    private List<DataModel> listData = new ArrayList<> ();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_main );

        rvData = findViewById ( R.id.rv_data );
        lmData = new LinearLayoutManager ( this, LinearLayoutManager.VERTICAL,false );
         rvData.setLayoutManager ( lmData );
        retrieveData ();
    }

    public void retrieveData(){
        APIRequestData ardData = RetroServer.konekRetrofit ().create ( APIRequestData.class );
        Call<ResponseModel> tampilData = ardData.ardRetrieveData ();


        tampilData.enqueue ( new Callback<ResponseModel> () {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
             int kode = response.body ().getKode ();
             String pesan = response.body ().getPesan ();

                Toast.makeText ( MainActivity.this, "Kode:" +kode+"  |Pesan:"+pesan, Toast.LENGTH_SHORT ).show ();

                listData = response.body ().getData ();

                adData = new AdapterData (  MainActivity.this, listData);
                rvData.setAdapter ( adData );
                adData.notifyDataSetChanged ();
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                Toast.makeText ( MainActivity.this, "Gagal Menghubungkan Server",Toast.LENGTH_SHORT).show ();
            }
        } );
    }
}