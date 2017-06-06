package com.ismailr13.pkb;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class LihatAyat extends AppCompatActivity {

    String[] nomor;
    String[] content;

    TextView no, jud;

    ListView ListView01;
    Menu menu;
    protected Cursor cursor;
    DataHelper dbcenter;
    public static LihatAyat ma;

    private ArrayList listItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lihat_ayat);

        no = (TextView)findViewById(R.id.txt_number);
        jud = (TextView)findViewById(R.id.txt_title);

        no.setText(getIntent().getStringExtra("nomor"));
        jud.setText(getIntent().getStringExtra("judul"));

        ma = this;
        dbcenter = new DataHelper(this);
        RefreshList();
    }

    public void RefreshList(){
        SQLiteDatabase db = dbcenter.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM ayat WHERE pasal_id = '" + getIntent().getStringExtra("nomor") + "'",null);
        nomor = new String[cursor.getCount()];
        content = new String[cursor.getCount()];
        cursor.moveToFirst();

        listItem = new ArrayList<>();

        AyatModel law = null;

        for (int cc=0; cc < cursor.getCount(); cc++){
            cursor.moveToPosition(cc);
            nomor[cc] = cursor.getString(1).toString();
            content[cc] = cursor.getString(2).toString();

            law = new AyatModel();
            law.setNumber(nomor[cc]);
            law.setContent(content[cc]);

            listItem.add(law);
        }
        ListView01 = (ListView)findViewById(R.id.listViewAyat);

        AyatAdapter adapter = new AyatAdapter(LihatAyat.this, listItem);

        ListView01.setAdapter(adapter);
        ListView01.setSelected(true);
    }
}
