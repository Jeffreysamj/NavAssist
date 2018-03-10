package com.example.android.bluetoothchat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.android.common.activities.SampleActivityBase;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Locale;

public class Main5Activity extends SampleActivityBase {
    Button loc,button2;
    DBHelper dbh;
    String num;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);
        loc= (Button) findViewById(R.id.loc);
        button2=(Button)findViewById(R.id.button2);
        ActivityCompat.requestPermissions(Main5Activity.this, new String[] {Manifest.permission.ACCESS_FINE_LOCATION}, 123);

        loc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GPSTracker gt = new GPSTracker(getApplicationContext());
                Location l = gt.getLocation();
                if (l == null) {
                    Toast.makeText(getApplicationContext(), "GPS unable to get Value", Toast.LENGTH_SHORT).show();
                } else {
                    double lat = l.getLatitude();
                    double lon = l.getLongitude();
                    Geocoder geocoder;
                    List<Address> addresses = null;
                    geocoder = new Geocoder(Main5Activity.this, Locale.getDefault());

                    try {
                        addresses = geocoder.getFromLocation(lat, lon, 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    String address = addresses.get(0).getAddressLine(0);
                    ArrayList n = getNumbers();
                    // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
                    sendMessage(n,address);
                }
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoact1();
            }
        });
    }


    ArrayList<String> getNumbers() {
        ArrayList<String> numlist= new ArrayList<>();
        dbh = new DBHelper(Main5Activity.this);
        Cursor cur = dbh.fetchData();
        while (cur.moveToNext()) {
            String d = cur.getString(0);
            numlist.add(d);
        }
        return numlist;
    }
    void sendMessage(ArrayList<String> mblNumVar, String smsMsgVar)
    {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED)
        {
            try
            {
                SmsManager smsMgrVar = SmsManager.getDefault();
                ListIterator<String> li=mblNumVar.listIterator();
                while(li.hasNext()) {
                    String str=li.next().toString();
                    smsMgrVar.sendTextMessage(str, null, smsMsgVar, null, null);

                }
                //smsMgrVar.sendTextMessage(mblNumVar, null, smsMsgVar, null, null);
                Toast.makeText(getApplicationContext(), "Message Sent",
                        Toast.LENGTH_LONG).show();
            }
            catch (Exception ErrVar)
            {
                Toast.makeText(getApplicationContext(),ErrVar.getMessage().toString(),
                        Toast.LENGTH_LONG).show();
                ErrVar.printStackTrace();
            }
        }
        else
        {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
            {
                requestPermissions(new String[]{Manifest.permission.SEND_SMS}, 10);
            }
        }

    }
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater mi=getMenuInflater();
        mi.inflate(R.menu.menu1,menu);
        return super.onCreateOptionsMenu(menu);
    }
    private void gotoact1() {
        Intent in=new Intent(Main5Activity.this,Main3Activity.class);
        startActivity(in);
    }



    /*public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if(resultCode == RESULT_OK) {
                num = data.getStringExtra("key");
            }
        }
    }*/

}

