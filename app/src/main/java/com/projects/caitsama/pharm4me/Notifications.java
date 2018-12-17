package com.projects.caitsama.pharm4me;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

public class Notifications extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);

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
                Toast.makeText(getApplicationContext(), "You are currently viewing you notifications from Pharm4Me.",Toast.LENGTH_SHORT);
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
                Intent i = new Intent(getApplicationContext(), MapsActivity.class);
                startActivity(i);
                finish();
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
}
