package com.risquna.retrovolley;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.risquna.retrovolley.activity.RetrofitActivity;
import com.risquna.retrovolley.activity.VolleyActivity;
import com.risquna.retrovolley.model.User;
import com.risquna.retrovolley.retrofit.GlobalVariable;

public class MainActivity extends AppCompatActivity {
    private final String TAG = getClass ().getSimpleName ();
    private SharedPreferences pref;
    private int flagExit = 0;
    private User user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_main );

        pref = getSharedPreferences ( GlobalVariable.PREFERENCE_NAME, MODE_PRIVATE );
        Bundle extras = getIntent ().getExtras ();
        if (extras != null) {
            setTitle ( extras.getString ( GlobalVariable.CURRENT_USERNAME ) );
        } else
            setTitle ( "Home" );
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater ().inflate ( R.menu.main, menu );
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId ();
        switch (id) {
            case R.id.action_url:
                View urlView = getLayoutInflater ().inflate ( R.layout.prompt_url, null );
                EditText edtBaseURL = urlView.findViewById ( R.id.edt_base_url );
                String globalURL = pref.getString ( GlobalVariable.BASE_URL, null );

                if (globalURL != null) {
                    edtBaseURL.setText ( globalURL );
                }
                new AlertDialog.Builder ( this )
                        .setTitle ( "Base URL" )
                        .setView ( urlView )
                        .setPositiveButton ( "OK", new DialogInterface.OnClickListener () {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String globalURL = edtBaseURL.getText ().toString ();
                                SharedPreferences.Editor prefEditor = pref.edit ();
                                prefEditor.putString ( GlobalVariable.BASE_URL, globalURL );
                                prefEditor.apply ();
                            }
                        } ).setNegativeButton ( "Batal", new DialogInterface.OnClickListener () {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss ();
                    }
                } ).show ();
                break;
        }
        return super.onOptionsItemSelected (item );
    }

    @Override
    public void onBackPressed() {
        flagExit++;
        if (flagExit == 2) {
            super.onBackPressed ();
        } else {
            Toast.makeText ( this, "Tekan lagi untuk keluar", Toast.LENGTH_SHORT ).show ();
        }
    }

    public void actionRetrofit(View view) {
        Intent retrofit = new Intent ( this, RetrofitActivity.class );
        startActivity ( retrofit );
    }

    public void actionVolley(View view) {
        Intent volley = new Intent ( this, VolleyActivity.class );
        startActivity ( volley );
    }

}


