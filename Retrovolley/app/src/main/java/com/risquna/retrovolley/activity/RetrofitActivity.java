package com.risquna.retrovolley.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.risquna.retrovolley.R;
import com.risquna.retrovolley.adapter.UserAdapter;
import com.risquna.retrovolley.model.User;
import com.risquna.retrovolley.retrofit.MethodHTTP;
import com.risquna.retrovolley.retrofit.UserResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitActivity extends AppCompatActivity {
    private final String TAG = getClass().getSimpleName();
    private ListView TvUser;
    private List<User> listUser = new ArrayList<> ();

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_retrofit );
        TvUser = findViewById ( R.id.Tv_user );
        getUserFromAPI();
        setTitle ( getString ( R.string.retrofit));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu ( menu );
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        public void actionRefresh (View view){
            getUserFromAPI();
        }

        public void actionClose (View view){
            this.finish ();
        }



        private void getUserFromAPI(){
            ProgressDialog probialog = new ProgressDialog(  this);
            probialog.setTitle("Retrofit");
            probialog.setMessage("silahkan sunggu");
            probialog.show();
            Retrofit.Builder builder = new Retrofit.Builder()
                    .baseUrl("http://192.168.43.51/volley.2")
                    .addConverterFactory ( GsonConverterFactory.create());
            Retrofit retrofit = builder.build();
            MethodHTTP client = retrofit.create(MethodHTTP.class);
            Call<UserResponse> call = client.getUser();
            call.enqueue(new Callback<UserResponse> () {
                @Override
                public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                    probialog.dismiss ();
                    listUser = response.body ().getUser_list ();
                    UserAdapter userAdapter = new UserAdapter ( getApplicationContext (), listUser );
                    TvUser.setAdapter ( userAdapter );
                    TvUser.setOnItemClickListener ( new AdapterView.OnItemClickListener () {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                            Toast.makeText ( getApplicationContext (), listUser.get ( i ).getUser_fullname ().
                                    Toast.LENGHT_SHORT ).show ();
                        }
                    } );
                }

                @Override
                public void onFailure(Call<UserResponse> call, Throwable t) {

                    Log.d ( t, toString () );
                }
            });
        }