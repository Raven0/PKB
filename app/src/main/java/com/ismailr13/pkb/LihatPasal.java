package com.ismailr13.pkb;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class LihatPasal extends AppCompatActivity {

    String[] id;
    String[] nomor;
    String[] judul;

    ListView ListView01;
    Menu menu;
    protected Cursor cursor;
    DataHelper dbcenter;
    public static LihatPasal ma;

    private ArrayList listItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lihat_pasal);

        ma = this;
        dbcenter = new DataHelper(this);
        RefreshList();
    }

    public void RefreshList(){
        SQLiteDatabase db = dbcenter.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM pasal WHERE bab_id = '" + getIntent().getStringExtra("_id") + "'",null);
        id = new String[cursor.getCount()];
        nomor = new String[cursor.getCount()];
        judul = new String[cursor.getCount()];
        cursor.moveToFirst();

        listItem = new ArrayList<>();

        PasalModel law = null;

        for (int cc=0; cc < cursor.getCount(); cc++){
            cursor.moveToPosition(cc);
            id[cc] = cursor.getString(0).toString();
            nomor[cc] = cursor.getString(1).toString();
            judul[cc] = cursor.getString(2).toString();

            law = new PasalModel();
            law.setNumber(nomor[cc]);
            law.setTitle(judul[cc]);

            listItem.add(law);
        }
        ListView01 = (ListView)findViewById(R.id.listViewPasal);

        PasalAdapter adapter = new PasalAdapter(LihatPasal.this, listItem);

        ListView01.setAdapter(adapter);
        ListView01.setSelected(true);
        ListView01.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView arg0, View arg1, int arg2, long arg3) {
                final String selection = nomor[arg2]; //.getItemAtPosition(arg2).toString();
                final String jdl = judul[arg2]; //.getItemAtPosition(arg2).toString();
                final CharSequence[] dialogitem = {"Lihat Ayat"};
                AlertDialog.Builder builder = new AlertDialog.Builder(LihatPasal.this);
                builder.setTitle("Pilihan");
                builder.setItems(dialogitem, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int item) {
                        switch(item){
                            case 0 :
                                Intent i = new Intent(getApplicationContext(), LihatAyat.class);
                                i.putExtra("nomor", selection);
                                i.putExtra("judul", jdl);
                                startActivity(i);
                                break;
                        }
                    }
                });
                builder.create().show();
            }});
    }
}
