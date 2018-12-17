package com.projects.caitsama.pharm4me;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class SettingsActivity extends AppCompatActivity {
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        Toolbar toolbar = (Toolbar) findViewById(R.id.tbSettings);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        listView = (ListView) findViewById(R.id.settingsView);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                switch (position){
                    case 0:Toast.makeText(getApplicationContext(), "FAQ under construction",
                            Toast.LENGTH_SHORT).show();
                        break;
                    case 1:Toast.makeText(getApplicationContext(), "Beta Version 1.0.0",
                            Toast.LENGTH_SHORT).show();
                        break;
                    case 2:Toast.makeText(getApplicationContext(), "Logging off...",
                            Toast.LENGTH_SHORT).show();
                            //String url  = "javascript:document.getElementById(\"logoutForm\").submit();";

//                        //Create a WebView and use custom class to load the URL along with jQuery.
//                        final WebView webView = (WebView) findViewById(R.id.logoff);
//                        Pharm4MeClient WebClient = new Pharm4MeClient();
//                        webView.setWebViewClient(WebClient);
//                        webView.getSettings().setJavaScriptEnabled(true);
//
//                        webView.loadUrl("http://pharm4metest.azurewebsites.net");
//                        webView.loadUrl(url);
//
//                        Intent i = new Intent(getApplicationContext(), MainActivity.class);
//                        startActivity(i);
//                        finish();
                        break;
                    default: break;
                }
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
