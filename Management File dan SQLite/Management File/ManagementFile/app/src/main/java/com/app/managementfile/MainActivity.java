package com.app.managementfile;

import static android.os.Build.VERSION_CODES.R;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import androidx.core.app.ActivityCompat;
import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.storage.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Writer;
import java.nio.charset.StandardCharsets;


public class MainActivity extends AppCompatActivity {

    EditText editText;
    private int STORAGE_PERMISSION_CODE_CODE = 23;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( com.example.storage.R.layout.activity_main );
        editText = (EditText) findViewById ( com.example.storage.R.id.editText2 );

    }

    public void next(View view) {
        Intent intent = new Intent ( MainActivity.this, Main2Activity.class );
        startActivity ( intent );

    }

    public void savePublic(View view) {
        ActivityCompat.requestPermissions ( this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, STORAGE_PERMISSION_CODE_CODE );
        String info = editText.getText ().toString ();
        File folder = Environment.getExternalStoragePublicDirectory ( Environment.DIRECTORY_DOWNLOADS );
        File myFile = new File ( folder, "myData1.txt" );
        writeData ( myFile, info );
        editText.setText ( "" );
    }

    public void savePrivate(View view) {
        String info = editText.getText ().toString ();
        File folder = getExternalFilesDir ( "Tegar" );
        File myFile = new File ( folder, "myData2.txt" );
        writeData ( myFile, info );
        editText.setText ( "" );

    }

    private void writeData(File myFile, String data) {
        FileOutputStream fileOutputStream = null;
        try {
            System.out.println ( "TES" );
            fileOutputStream = new FileOutputStream ( myFile );
            fileOutputStream.write ( data.getBytes () );
            Toast.makeText ( this, "Done" + myFile.getAbsolutePath (), Toast.LENGTH_SHORT ).show ();

        } catch (Exception e) {
            e.printStackTrace ();
        } finally {
            if (fileOutputStream != null) {

                try {
                    fileOutputStream.close ();

                } catch (IOException e) {
                    e.printStackTrace ();
                }
            }
        }
    }

}