package com.risquna.retrovolley.activity;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.risquna.retrovolley.R;
import com.risquna.retrovolley.model.User;
import com.risquna.retrovolley.retrofit.GlobalVariable;
import com.risquna.retrovolley.retrofit.MethodHTTP;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AddUserActivity extends AppCompatActivity {
    private EditText edtFullname, edtEmail, edtPassword;
    private TextView txtTitleLibrary;
    private Button btnSubmit;
    private String typeConn = "retrofit";
    private SharedPreferences pref;
    private final String TAG = getClass ().getSimpleName ();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_add_user );
        edtFullname = findViewById ( R.id.edt_fullname );
        edtEmail = findViewById ( R.id.edt_email );
        edtPassword = findViewById ( R.id.edt_password );
        btnSubmit = findViewById ( R.id.btn_submit );
        txtTitleLibrary = findViewById ( R.id.txt_title_library );
        setTitle ( "Tambah User" );
        pref = getSharedPreferences ( GlobalVariable.PREFERENCE_NAME, MODE_PRIVATE );
        Bundle extras = getIntent ().getExtras ();
        if (extras != null) {
            typeConn = extras.getString ( GlobalVariable.TYPE_CONN, "Undefined" );
            if (typeConn.equalsIgnoreCase ( GlobalVariable.RETROFIT )) {
                txtTitleLibrary.setText ( "Send using Retrofit" );

            } else txtTitleLibrary.setText ( "Send using Volley" );
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed ();
        finish ();
    }

    public void actionSubmit(View view) {
        boolean isInputValid = false;
        if (edtFullname.getText ().toString ().isEmpty ()) {
            edtFullname.setError ( "Tidak boleh kosong" );
            edtFullname.requestFocus ();
            isInputValid = false;
        } else {
            isInputValid = true;
        }
        if (edtEmail.getText ().toString ().isEmpty ()) {
            edtEmail.setError ( "Tidak boleh kosong" );
            edtEmail.requestFocus ();
            isInputValid = false;
        } else {
            isInputValid = true;
        }
        if (edtPassword.getText ().toString ().isEmpty ()) {
            edtPassword.setError ( "Tidak boleh kosong" );
            edtPassword.requestFocus ();
            isInputValid = false;
        } else {
            isInputValid = true;
        }


        if (isInputValid) {
            User user = new User ();
            user.setUser_fullname ( edtFullname.getText ().toString () );
            user.setUser_email ( edtEmail.getText ().toString () );
            user.setUser_password ( edtPassword.getText ().toString () );
            if (typeConn.equalsIgnoreCase ( GlobalVariable.RETROFIT ))
                submitByRetrofit ( user );
            else submitByVolley ( user );
        }


    }

    private void submitByRetrofit(User user) {
        ProgressDialog proDialog = new ProgressDialog(  this);
        proDialog.setTitle("Retrofit");
        proDialog.setMessage("Sedang disubmit");
        proDialog.show();

        String globalURL = pref.getString(GlobalVariable. BASE_URL,  null);

        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(globalURL)
                .addConverterFactory ( GsonConverterFactory.create());
        Retrofit retrofit = builder.build();
        MethodHTTP client = retrofit.create(MethodHTTP.class);
        Call<com.risquna.retrovolley.retrofit.Request> call = client.sendUser (user);
            call.enqueue ( new Callback<com.risquna.retrovolley.retrofit.Request> () {
                @Override
                public void onResponse(Call<com.risquna.retrovolley.retrofit.Request> call, Response<com.risquna.retrovolley.retrofit.Request> response) {
                    proDialog.dismiss();
                    if (response.body() != null) {
                        if (response.body().getCode() == 201) {
                            Toast.makeText(getApplicationContext(),
                                     "Response : "+response.body().getStatus(),
                                    Toast.LENGTH_SHORT).show();
                            finish();
                        } else if (response. body ().getCode() ==406) {
                            Toast.makeText(getApplicationContext(),
                                    Toast.makeText(getApplicationContext(),
                                     "Response : "+response.body().getStatus(),
                                    Toast.LENGTH_SHORT).show ();
                            edtEmail.requestFocus();
                        } else {
                            Toast.makeText(getApplicationContext(),
                                     "Response : "+response.body().getStatus (),
                                    Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    } else {
                        Toast.makeText(getApplicationContext(),
                                 "Data Kosong !", Toast.LENGTH_SHORT).show();
                    }
                    Log.e(TAG,  "Error : "+response.message());
                }

                @Override
                public void onFailure(Call<com.risquna.retrovolley.retrofit.Request> call, Throwable t) {

                proDialog.dismiss();
                Log.e(TAG,  "Error POST Retrofit : "+t.getMessage());

                }
            } );

    }

    private void submitByVolley(User user) {
        Gson gson = new Gson ();
        String URL = pref.getString ( GlobalVariable.BASE_URL, null )
                + "/volley/User_Registration.php";

        ProgressDialog proDialog = new ProgressDialog ( this );
        proDialog.setTitle ( "Volley" );
        proDialog.setMessage ( "Sedang disubmit" );
        proDialog.show ();

        String userRequest = gson.toJson ( user );
        RequestQueue requestQueue = Volley.newRequestQueue ( getApplicationContext () );
        JsonObjectRequest request = new JsonObjectRequest ( com.android.volley.Request.Method.POST,
                URL, null,
                new com.android.volley.Response.Listener<JSONObject> () {

                    @Override
                    public void onResponse(JSONObject response) {
                        proDialog.dismiss ();
                        if (response != null) {
                            Request requestFormat = gson.fromJson ( response.toString (), Request.class );
                            if (requestFormat.getCode () == 201) {
                                Toast.makeText ( getApplicationContext (),
                                        "Response :" +requestFormat.getStatus (),
                                        Toast.LENGTH_SHORT ).show ();
                                finish ();

                            } else if (requestFormat.getCode () == 406) {
                                    Toast.makeText ( getApplicationContext (),
                                            "Response : " +requestFormat.getStatus (),
                                            Toast.LENGTH_SHORT ).show ();

                        } else {
                            Toast.makeText ( getApplicationContext (),
                                    "Response :" +requestFormat.getStatus (),
                                    Toast.LENGTH_SHORT ).show ();
                            finish ();
                        }

                    }

                }

                }, new com.android.volley.Response.ErrorListener () {
            @Override
            public void onErrorResponse(VolleyError error) {
                proDialog.dismiss ();
                Log.e( TAG, "Error POST Volley : " + error.getMessage () );
            }
        } ) {


        };
        requestQueue.add ( request );
        requestQueue.start ();
    }
}




