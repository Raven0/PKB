package com.ismailr13.pkb;

import android.database.Cursor;
import android.database.SQLException;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import java.io.IOException;

/**
 * Created by azhzh on 6/1/2017.
 */

public class CopyDBActivity extends AppCompatActivity {

    Cursor c = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DataHelper myDbHelper = new DataHelper(CopyDBActivity.this);
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
        Toast.makeText(CopyDBActivity.this, "Successfully Imported", Toast.LENGTH_SHORT).show();
        c = myDbHelper.query("bab", null, null, null, null, null, null);
        if (c.moveToFirst()) {
            do {
                Toast.makeText(CopyDBActivity.this,
                        "_id: " + c.getString(0) + "\n" +
                                "nomor: " + c.getString(1) + "\n" +
                                "judul:  " + c.getString(3),
                        Toast.LENGTH_LONG).show();
            } while (c.moveToNext());
        }

    }


}
