package com.risquna.retrovolley.activity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.risquna.retrovolley.R;
import com.risquna.retrovolley.model.User;
import com.risquna.retrovolley.retrofit.GlobalVariable;
import com.risquna.retrovolley.retrofit.MethodHTTP;
import com.risquna.retrovolley.retrofit.Request;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UpdateUserActivity extends AppCompatActivity {
    private User user;
    private SharedPreferences pref;
    private EditText edtUsername, edtEmail, edtPassword;
    private final String TAG = getClass().getSimpleName();


    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_update_user );
        edtUsername = findViewById(R.id.edt_username);
        edtEmail = findViewById(R.id.edt_user_email);
        edtPassword = findViewById(R.id.edt_password_update);
        setTitle("Udpate Data User");
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            user = new User();
            user.setId(extras.getInt( GlobalVariable.CURRENT_USER_ID));

            edtUsername.setText(extras.getString(GlobalVariable.CURRENT_USERNAME));
            edtEmail.setText(extras.getString(GlobalVariable.CURRENT_USER_EMAIL));
            edtPassword.setText(extras.getString(GlobalVariable.CURRENT_USER_PASSWORD));

        }

        pref = getSharedPreferences (GlobalVariable.PREFERENCE_NAME, MODE_PRIVATE);
    }
    public void actionUpdate(View view) {
        boolean isValidated = false;
        if (edtUsername.getText().toString().isEmpty()){
            edtUsername.setError("Tidak boleh kosong");
            edtUsername.requestFocus();
            isValidated = false;
        } else
        isValidated = true;
        if (edtEmail.getText().toString().isEmpty()) {
            edtEmail.setError("Tidak boleh kosong");
            edtEmail.requestFocus();
            isValidated = false;
        } else
            isValidated = true;
        if (edtPassword.getText().toString().isEmpty()) {
            edtPassword.setError("Tidak boleh kosong");
            edtPassword.requestFocus();
            isValidated = false;
        } else
            isValidated = true;
        if (isValidated) {
            user.setUser_fullname (edtUsername.getText().toString());
            user.setUser_email(edtEmail.getText().toString());
            user.setUser_password(edtPassword.getText().toString());
            ProgressDialog proDialog = new ProgressDialog (  this);
            proDialog.setTitle("Retrofit");
            proDialog.setMessage("Sedang disubmit");
            proDialog.show();
            String globalURL= pref.getString(GlobalVariable.BASE_URL,  null);
            Retrofit. Builder builder = new Retrofit. Builder()
                    .baseUrl(globalURL)
                    .addConverterFactory( GsonConverterFactory.create());
            Retrofit retrofit = builder.build();
            MethodHTTP client = retrofit.create(MethodHTTP.class);
            Call<Request> call = client.updateUser(user);
            call.enqueue(new Callback<Request> () {
                @Override
                public void onResponse (Call<Request> call, Response<Request> response) {
                    proDialog.dismiss();
                    if (response.body() != null) {
                        if (response.body() = getCode() ==200) {
                            Toast.makeText(getApplicationContext(),
                                     "Response : "+response.body().getStatus(),
                                    Toast.LENGTH_SHORT).show();
                            finish();
                        } else {

                            Toast.makeText(getApplicationContext(),
                                     "Response : "+response.body().getStatus(),
                                    Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    }
                    Log.e(TAG,  "Error : "+response.message());
                }
                @Override
                public void onFailure (Call<Request> call, Throwable t) {
                    proDialog.dismiss ();
                    Log.e ( TAG, "Error Update : " + t.getMessage () );
                    }
                });
            }
        }
        public void actionDelete(View view) {
            int currentUserID = pref.getInt(GlobalVariable.CURRENT_USER_ID,  0);
            if (currentUserID == user.getId()){
                new AlertDialog.Builder(  this)
                        .setTitle("Peringatan!")
                        .setMessage("Tidak dapat menghapus pengguna yang sedang login!")
                        .setPositiveButton(  "OK", new DialogInterface.OnClickListener () {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                }).show();
            } else {
                new AlertDialog.Builder(  this)
                         .setTitle("Peringatan!")
                        .setMessage("Yakin hapus data?")
                        .setPositiveButton(  "OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        deleteUserByID( user.getId () );
                    }
                    }).
                    setNegativeButton(  "Batal", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss ();
                        }
                    }).show ();


                    }
            }


    public void deleteUserByID(int id) {

                        ProgressDialog proDialog = new ProgressDialog(  this);
                        proDialog.setTitle("Retrofit");
                        proDialog.setMessage ("silahkan tunggu");
                        proDialog.show();
                        String globalURL = pref.getString(GlobalVariable.BASE_URL,  null);
                        Retrofit.Builder builder = new Retrofit.Builder()
                                .baseUrl(globalURL)
                                .addConverterFactory(GsonConverterFactory.create());
                        Retrofit retrofit = builder.build();
                        MethodHTTP client = retrofit.create(MethodHTTP.class);
                        Call<Request> call = client.deleteUser(id);
                        call.enqueue (new Callback<Request>() {
                            @Override
                            public void onResponse (Call<Request> call, Response<Request> response) {
                                proDialog.dismiss();
                                if (response.body() != null) {
                                    if (response. body ().getCode() == 200) {
                                        Toast.makeText(getApplicationContext(),
                                                "Response : "+response.body().getStatus(),
                                                Toast. LENGTH_SHORT).show();
                                        finish();
                                    } else {
                                        Toast.makeText(getApplicationContext(),
                                                "Response : "+response.body().getStatus(),
                                                Toast.LENGTH_SHORT).show();
                                        finish();
                                    }
                                }
                                Log.e(TAG,  "Error : "+response.message());
                            }

                            @Override
                            public void onFailure(Call<Request> call, Throwable t) { Log.d(TAG, t.toString()); }
                        });

            }
                    }


    }