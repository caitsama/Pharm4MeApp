package com.projects.caitsama.pharm4me;

import android.Manifest;
import android.app.AlertDialog;
import android.app.Notification;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;

import com.google.android.gms.location.FusedLocationProviderClient;


import java.util.Timer;
import java.util.TimerTask;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private FusedLocationProviderClient mFusedLocationClient;
    public Location myLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        //Go back to MainActivity
        ImageButton home =  findViewById(R.id.btnHome);
        home.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String url  = "http://pharm4metest.azurewebsites.net/Account/Login";

                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                i.putExtra("url", url);
                startActivity(i);
               finish();
            }
        });

        ImageButton notif =  findViewById(R.id.btnNotifs);
        notif.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Notifications.class);
                startActivity(i);
                finish();
            }
        });

        ImageButton pres =  findViewById(R.id.btnPrescription);
        pres.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String url  = "http://pharm4metest.azurewebsites.net/Prescripts/PatientsIndex";

                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                i.putExtra("url", url);
                startActivity(i);
                finish();
            }
        });

        ImageButton location =  findViewById(R.id.btnLocation);
        location.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "You are currently viewing Pharm4Me locations.",Toast.LENGTH_SHORT);
            }
        });

        ImageButton setting =  findViewById(R.id.btnSettings);
        setting.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), SettingsActivity.class);
                startActivity(i);
                finish();
            }
        });
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        mMap = googleMap;

        // Create a LatLngBounds for Ontario
        LatLngBounds ONTARIO = new LatLngBounds(
                new LatLng(43.63687,-79.471152 ), new LatLng(43.8824291,-79.191121));
        mMap.setLatLngBoundsForCameraTarget(ONTARIO);

        //Move map to user's current location
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        } else {
            // Permission has already been granted
            googleMap.setMyLocationEnabled(true);
            mMap.setMyLocationEnabled(true);
            mFusedLocationClient.getLastLocation();
            mFusedLocationClient.getLastLocation().addOnSuccessListener(this, new OnSuccessListener<Location>() {
                @Override
                public void onSuccess(Location location) {
                    // Got last known location. In some rare situations this can be null.
                    if(location == null)
                    {
                        Log.d("nulllocation", "Location returned null");
                    }
                    if (location != null) {
                        Log.d("gotlocation", "Location get!");
                        myLocation = location;
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(myLocation.getLatitude(),myLocation.getLongitude()), 10));
                    }
                }
            });

            //The Pharm4Me Locations
            LatLng scarborough  = new LatLng(43.7754702,-79.2327654);
            mMap.addMarker(new MarkerOptions().position(scarborough).title("Rexall").snippet("1127 Markham Rd, Scarborough, ON M1H 2Y5"));

            LatLng portunion  = new LatLng(43.7984704,-79.1503835);
            mMap.addMarker(new MarkerOptions().position(portunion).title("Pharmasave").snippet("6758 Kingston Rd #8, Scarborough, ON M1B 1G8"));

            LatLng scarborough2  = new LatLng(43.7593414,-79.2978631);
            mMap.addMarker(new MarkerOptions().position(scarborough2).title("Costco").snippet("1411 Warden Ave, Scarborough, ON M1R 2S3"));

            LatLng northyork  = new LatLng(43.7677908,-79.413555);
            mMap.addMarker(new MarkerOptions().position(northyork).title("Loblaws").snippet("5095 Yonge St, North York, ON M2N 6Z4"));

            LatLng toronto  = new LatLng(43.794953,-79.222497);
            mMap.addMarker(new MarkerOptions().position(toronto).title("Shoppers Drug Mart").snippet("700 Bay St, Toronto, ON M5G 1Z6"));

            LatLng markham  = new LatLng(43.8714791,-79.2178723);
            mMap.addMarker(new MarkerOptions().position(markham).title("Walmart Superstore").snippet("500 Copper Creek Dr, Markham, ON L6B 0S1"));


        }

    }
}
