package com.example.tez.Activitys.Activitys;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.WindowManager;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.example.tez.R;

public class DerleyiciActivity extends AppCompatActivity {

    WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_derleyici);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        webView=(WebView)findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("https://rextester.com/");

        final ProgressDialog progressDialog = ProgressDialog.show(this,"DerleyiciActivity","Yükleniyor",true);
        progressDialog.show();

        webView.setWebViewClient(new WebViewClient(){

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                Toast.makeText(DerleyiciActivity.this,"Yüklendi",Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }

            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                super.onReceivedError(view, request, error);
                Toast.makeText(DerleyiciActivity.this, "Bir Hata Oluştu", Toast.LENGTH_SHORT);
                progressDialog.dismiss();
            }
        });

    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId()==android.R.id.home)
        {
            Intent intent =new Intent(getApplicationContext(), MenuActivity.class);
            NavUtils.navigateUpTo(this,intent);
            return true;
        }
        return  super.onOptionsItemSelected(item);
    }
}
