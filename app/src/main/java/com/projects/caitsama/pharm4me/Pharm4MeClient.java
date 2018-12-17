package com.projects.caitsama.pharm4me;

import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by Caitsama on 2018-12-15.
 */

public class Pharm4MeClient extends WebViewClient {
    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        view.loadUrl(url);
        return true;
    }

    @Override
    public void onPageFinished(WebView view, String url) {
        //$( "footer" ).remove();
        view.loadUrl("javascript:$( \".navbar, footer, th\" ).hide();");

        //Hide part of the Prescription table (Commented out for now until I find a better solution)
        //view.loadUrl("javascript:$( \"td\" ).hide();");// $(\"td:eq(0)\").show(); $(\"td:eq(2)\").show();$(\"td:eq(9)\").show();");

    }

}
