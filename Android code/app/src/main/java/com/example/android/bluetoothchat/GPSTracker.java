package com.example.android.bluetoothchat;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;

/**
 * Created by Khanita Taskeen on 20-01-2018.
 */

public class GPSTracker implements LocationListener {
    Context context;
    public GPSTracker(Context c) {
        context=c;
    }
    public Location getLocation() {
        if(ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(context, "Permission not granted", Toast.LENGTH_SHORT).show();
            return null;
        }
        try {
        LocationManager lm= (LocationManager) context.getSystemService(context.LOCATION_SERVICE);
        boolean isGPSEnabled=lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
        if(isGPSEnabled) {
            lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 6000,10,this);
            Location loc = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            return loc;
        }
        else{
            Toast.makeText(context, "Please enable GPS", Toast.LENGTH_SHORT).show();
        }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}
