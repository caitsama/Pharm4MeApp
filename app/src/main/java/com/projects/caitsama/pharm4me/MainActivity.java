package com.projects.caitsama.pharm4me;

import android.app.Notification;
import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageButton;
import android.widget.Toast;

/*Menu was made following this tutorial:
* https://code.tutsplus.com/tutorials/how-to-code-a-navigation-drawer-in-an-android-app--cms-30263*/

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Set Toolbar to custom one
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        //Create a WebView and use custom class to load the URL along with jQuery.
        final WebView webView = (WebView) findViewById(R.id.weblogin);
        Pharm4MeClient WebClient = new Pharm4MeClient();
        webView.setWebViewClient(WebClient);
        webView.getSettings().setJavaScriptEnabled(true);


        Intent intent = getIntent();
        //Load the page on the WebView element
        try {
            webView.loadUrl(intent.getExtras().getString("url"));
        }
        catch (Exception e){
            Log.e("Error!", e.getMessage());
            webView.loadUrl("http://pharm4metest.azurewebsites.net/Account/Login");
        }
        Toast.makeText(getBaseContext(), "Checking login status...", Toast.LENGTH_LONG).show();

        //Onclick Listeners for the Image buttons (acting like a menu)
        //Home
        ImageButton home =  findViewById(R.id.btnHome);
        home.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                webView.loadUrl("http://pharm4metest.azurewebsites.net/Account/Login");
            }
        });

        //Notification (feature not complete)
        ImageButton notif =  findViewById(R.id.btnNotifs);
        notif.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent myIntent = new Intent(MainActivity.this, Notifications.class);
                MainActivity.this.startActivity(myIntent);
            }
        });

        //Prescription Button: Changes the webview url
        ImageButton prescription =  findViewById(R.id.btnPrescription);
        prescription.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
               webView.loadUrl("http://pharm4metest.azurewebsites.net/Prescripts/PatientsIndex");
            }
        });

        //Location button: Makes a new Intent showing Pharm4Me affiliated locations
        ImageButton location =  findViewById(R.id.btnLocation);
        location.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent myIntent = new Intent(MainActivity.this, MapsActivity.class);
                MainActivity.this.startActivity(myIntent);
            }
        });

        //Settings (not complete)
        ImageButton settings =  findViewById(R.id.btnSettings);
        settings.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent myIntent = new Intent(MainActivity.this, SettingsActivity.class);
                MainActivity.this.startActivity(myIntent);
            }
        });
    }


}

