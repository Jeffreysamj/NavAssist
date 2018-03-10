package com.example.android.bluetoothchat;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.android.common.activities.SampleActivityBase;

import java.util.ArrayList;
import java.util.HashMap;

public class ViewActivity extends SampleActivityBase {

    ListView lv;
    ArrayList<HashMap<String, String>> Data;
    DBHelper dbh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        lv = (ListView) findViewById(R.id.lv);
        Data = new ArrayList<>();
        HashMap<String, String> d = new HashMap<>();
        dbh = new DBHelper(ViewActivity.this);
        Cursor cur = dbh.fetchData();
        while (cur.moveToNext()) {
            d = new HashMap<>();
            d.put("contact", cur.getString(0));
            Data.add(d);
            ListAdapter la = new SimpleAdapter(ViewActivity.this, Data, R.layout.list, new String[]{"contact"}, new int[]{R.id.tvnum});
            lv.setAdapter(la);
        }
    }
}
