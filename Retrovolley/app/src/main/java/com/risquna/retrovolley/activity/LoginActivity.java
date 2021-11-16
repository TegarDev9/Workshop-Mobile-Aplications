package com.risquna.retrovolley.activity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.risquna.retrovolley.MainActivity;
import com.risquna.retrovolley.R;
import com.risquna.retrovolley.retrofit.GlobalVariable;

public class LoginActivity extends AppCompatActivity {
    private final String TAG = getClass().getSimpleName();
    private SharedPreferences pref;
    private EditText edtUserEmail, edtUserPassword;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_login );
        pref = getSharedPreferences ( GlobalVariable.PREFERENCE_NAME, MODE_PRIVATE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate ( R.menu.main, menu );
        return true;
    }

    @Override

    public boolean onOptionsItemSelected (MenuItem item) {
        int id =item.getItemId();
        switch (id) {
            case R.id.action_url:
                View urlView = getLayoutInflater().inflate(R.layout.prompt_url,  null);
                EditText edtBaseURL = urlView.findViewById (R.id.edt_base_url);
                String globalURL = pref.getString(GlobalVariable. BASE_URL,  null);
                if (globalURL != null) {
                    edtBaseURL.setText(globalURL);
                }
                new AlertDialog.Builder( this)
                .setTitle("Base URL")
                    .setView(urlView)
                    .setPositiveButton ( "OK", new DialogInterface.OnClickListener () {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int which) {
                    String globalURL = edtBaseURL.getText().toString();
                    SharedPreferences.Editor prefEditor = pref.edit();
                    prefEditor.putString(GlobalVariable.BASE_URL, globalURL);
                    prefEditor.apply();
                }
            }). setNegativeButton ( "Batal", new DialogInterface.OnClickListener () {
                    @Override
                    public void onClick(DialogInterface dialog, int which) { dialog.dismiss (); }


            }).show();

                break;
        }
        return super.onOptionsItemSelected(item);
        public void actionRegister (View view) {
            Intent intent = new Intent(  this,AddUserActivity.class);
            intent.putExtra(GlobalVariable.TYPE_CONN, GlobalVariable.RETROFIT);
            startActivity(intent);
        }

        public void actionLogin(View view) {
            boolean isInputValid = false;
            if (edtUserEmail.getText().toString().isEmpty()) {
                edtUserEmail.setError("Tidak boleh kosong");
                edtUserEmail.requestFocus();
                isInputValid = false;
            } else
                isInputValid = true;

            if (edtUserPassword.getText().toString().isEmpty()) {
                edtUserPassword.setError("Tidak boleh kosong");
                edtUserPassword.requestFocus();
                isInputValid = false;
            } else
                isInputValid = true;
            if (isInputValid) {
                User user = new User();
                user.setUser_email(edtUserEmail.getText().toString());
                user.setUser_password(edtUserPassword.getText().toString();
                loginUsingRetrofit(user.getUser_email(), user.getUser_password());

                //loginusingvolley(user.getUser_email(), user.getUser_password());

        public void loginUsingRetrofit(String email, String password) {
            ProgressDialog proDialog = new ProgressDialog( this);
            proDialog.setTitle("Retrovolley");
            proDialog.setMessage("Silahkan tunggu");
            proDialog.show();
            String globalURL = pref.getString(GlobalVariable. BASE_URL,  null);
            Retrofit.Builder builder = new Retrofit.Builder()
                    .baseUrl(globalURL)
                    .addConverterFactory(GsonConverterFactory.create());
            Retrofit retrofit = builder.build();
            MethodHTTP client = retrofit.create(MethodHTTP.class);
            Call<UserResponse> call = client.login(email, password);
            call.enqueue(new Callback<UserResponse>() {


                @Override
                public void onResponse (Call<UserResponse> call, Response<UserResponse> response) {
                    proDialog.dismiss();
                    if (response. body() != null ) {
                        if (response.body().getCode() == 200) {
                            User loggedUser = response.body().getUser_list().get();
                            Intent intent = new Intent(  LoginActivity. this, MainActivity.class);
                            intent.putExtra(GlobalVariable.CURRENT_USERNAME,
                                    loggedUser.getUser_fullname());
                            startActivity(intent);
                            startActivity(intent);
                            finish();
                        } else if (response.body().getCode() == 401){
                            new AlertDialog.Builder(  LoginActivity. this)
                            setTitle("Peringatan!")
                                    .setMessage(response.body().getStatus())
                                    .setPositiveButton(  "OK", new DialogInterface.OnClickListener (){
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    edtUserPassword.setText("");
                                    dialogInterface. dismiss();
                                }
                            }).show();
                        } else {
                        //code 408


                            new AlertDialog.Builder(LoginActivity. this)
                                     .setTitle("Peringatan!")
                                    .setMessage(response.body().getStatus())
                                    .setPositiveButton ( "OK", new DialogInterface.OnClickListener () {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {


                            @Override
                            public void onClick(DialogInterface dialogInterface, int i){
                                Intent intent = new Intent ( LoginActivity.this,
                                        AddUserActivity.class);

                                intent.putExtra ( GlobalVariable.TYPE_CONN,
                                        GlobalVariable.RETROFIT );

                                                startActivity(intent);
                                                finish();
                                            }
                                        }).setNegativeButton(  "Batal",new DialogInterface.OnClickListener () {
                                            @override
                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                dialogInterface.dismiss();
                                            }
                                        }).show();
                                    }

                        } else{
                            Toast.makeText ( LoginActivity.this, "Status : Error!",
                                    Toast.LENGTH_SHORT ).show ();
                        }
                          log.e(TAG, "Error:" +response.message());

                        }
                        @Override
                        public void onFailure(Call<UserResponse> call, Throwable t) {
                            proDialog.dismiss();
                            Log.d(TAG, t.getMessage());
                            Toast.makeText(  LoginActivity. this,  "Error : "+t.toString(),
                                    Toast.LENGTH_SHORT).show();
                        }
                    });
                }





