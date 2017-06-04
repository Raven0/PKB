package com.ismailr13.pkb;

import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    Cursor c = null;
    private static int splashInterval = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // TODO Auto-generated method stub
                DataHelper myDbHelper = new DataHelper(MainActivity.this);
                try {
                    myDbHelper.createDataBase();
                } catch (IOException ioe) {
                    throw new Error("Unable to create database");
                }
                try {
                    myDbHelper.openDataBase();
                } catch (SQLException sqle) {
                    throw sqle;
                }
                Toast.makeText(MainActivity.this, "Successfully Imported", Toast.LENGTH_SHORT).show();
                c = myDbHelper.query("bab", null, null, null, null, null, null);
                c = myDbHelper.query("pasal", null, null, null, null, null, null);
                c = myDbHelper.query("ayat", null, null, null, null, null, null);

                Intent i = new Intent(MainActivity.this, LihatBab.class);

                startActivity(i); // menghubungkan activity splashscren ke main activity dengan intent

                //jeda selesai Splashscreen
                this.finish();
            }

            private void finish() {
                // TODO Auto-generated method stub

            }
        },splashInterval);
    }
}
