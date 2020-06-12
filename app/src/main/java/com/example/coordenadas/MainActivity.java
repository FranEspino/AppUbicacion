package com.example.coordenadas;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    public class miservicio extends Service implements LocationListener {

        private final Context ctx;
        double latitud, longitud;
        Location location;
        boolean gpsactivado;
        TextView texto;
        LocationManager locationManager;

        public miservicio() {

            super();
            this.ctx = this.getApplicationContext();
        }

        public miservicio(Context c) {

            this.ctx = c;
            getLocation();
        }

        public void setView(View v) {
            texto = (TextView) v;
            texto.setText("Coordenadada: " + latitud + " , " + longitud);
        }

        public void getLocation() {
            try {
                locationManager = (LocationManager) this.ctx.getSystemService(LOCATION_SERVICE);
                gpsactivado = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);

            } catch (Exception e) {
            }

            if (gpsactivado) {
                if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    Activity#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for Activity#requestPermissions for more details.
                    return;
                }
                locationManager.requestLocationUpdates(locationManager.GPS_PROVIDER, 1000 * 60, 10, this);
                location = locationManager.getLastKnownLocation(locationManager.GPS_PROVIDER);
                latitud = location.getLatitude();
                longitud = location.getLongitude();
            }

        }

        @Override
        public IBinder onBind(Intent intent) {
            return null;
        }

        @Override
        public void onLocationChanged(Location location) {

        }

        @Override
        public void onStatusChanged(String s, int i, Bundle bundle) {

        }

        @Override
        public void onProviderEnabled(String s) {

        }

        @Override
        public void onProviderDisabled(String s) {

        }
    }
}
