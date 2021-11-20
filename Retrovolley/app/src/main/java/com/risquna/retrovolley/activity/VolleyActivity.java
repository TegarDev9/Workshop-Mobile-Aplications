package com.risquna.retrovolley.activity;

import static com.android.volley.VolleyLog.TAG;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.risquna.retrovolley.R;
import com.risquna.retrovolley.adapter.UserAdapter;
import com.risquna.retrovolley.retrofit.GlobalVariable;
import com.risquna.retrovolley.retrofit.UserResponse;

import org.json.JSONObject;

public class VolleyActivity extends AppCompatActivity {

    private final String TAG = getClass ().getSimpleName ();
    private ListView lvUserVolley;
    private SharedPreferences pref;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_volley );
        lvUserVolley = findViewById ( R.id.lv_user_volley );
        pref = getSharedPreferences ( GlobalVariable.PREFERENCE_NAME, MODE_PRIVATE );
        setTitle ( "Volley" );
        getUserFromAPI ();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater ().inflate ( R.menu.retrofit, menu );
        return super.onCreateOptionsMenu ( menu );
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId ()) {
            case R.id.action_add:
                Intent intent = new Intent ( this, AddUserActivity.class );
                intent.putExtra ( GlobalVariable.TYPE_CONN, GlobalVariable.VOLLEY );
                startActivity ( intent );
                break;
        }
        return super.onOptionsItemSelected ( item );

    }

    public void actionRefresh(View view) {
        getUserFromAPI ();
    }


    public void actionClose(View view) {
        finish ();
    }


    private void getUserFromAPI() {
        Gson gson = new Gson ();
        String URL = pref.getString ( GlobalVariable.BASE_URL, null )
                + "/volley/User_Registration.php";
        ProgressDialog proDialog = new ProgressDialog ( this );
        proDialog.setTitle ( "Volley" );
        proDialog.setMessage ( "Silahkan tunggu" );
        proDialog.show ();
        JsonObjectRequest request = new JsonObjectRequest ( Request.Method.GET, URL,
                null,
                new Response.Listener<JSONObject> () {
                    @Override
                    public void onResponse(JSONObject response) {
                        proDialog.dismiss ();
                        if (response != null) {
                            UserResponse userResponse = gson.fromJson ( response.toString (),
                                    UserResponse.class );
                            if (userResponse.getCode () == 200) {
                                UserAdapter adapter = new UserAdapter ( getApplicationContext ().
                                        userResponse.getUser_list () );
                                lvUserVolley.setAdapter ( adapter );
                                lvUserVolley.setOnItemClickListener (
                                        new AdapterView.OnItemClickListener () {

                                            @Override
                                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                                                Toast.makeText ( getApplicationContext (),
                                                        userResponse.getUser_list ().get ( i ).getUser_fullname (),
                                                        Toast.LENGTH_SHORT ).show ();
                                            }
                                        } );
                            }
                        }
                    }
                }, new Response.ErrorListener () {
            @Override
            public void onErrorResponse(VolleyError error) {
                proDialog.dismiss ();
                Toast.makeText ( getApplicationContext (), "Volley Error :" + error.getMessage (),
                        Toast.LENGTH_SHORT ).show ();
                Log.e ( TAG, "Error :" + error.getMessage () );
            }

        } );
        RequestQueue requestQueue = Volley.newRequestQueue ( getApplicationContext () );
        requestQueue.add ( request );
    }


}



