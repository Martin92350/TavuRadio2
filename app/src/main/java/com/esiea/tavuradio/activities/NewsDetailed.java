package com.esiea.tavuradio.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.esiea.tavuradio.R;
import com.squareup.picasso.Picasso;

public class NewsDetailed extends AppCompatActivity {

    TextView tvTitle, tvDate, tvSource, tvDesccription ;
    ImageView imageView ;
    ProgressBar loader, webLoader ;
    WebView webView ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detailed);
        Intent intent = getIntent();

        tvTitle = findViewById(R.id.tvTitle);
        tvSource = findViewById(R.id.tvSource);
        tvDate = findViewById(R.id.tvDate);
        //tvDesccription = findViewById(R.id.tvDescription);
        webView = findViewById(R.id.web);
        imageView = findViewById(R.id.image);
        webLoader = findViewById(R.id.webLoader);
        webLoader.setVisibility(View.VISIBLE);


        String title = intent.getStringExtra("title");
        String source = intent.getStringExtra("source");
        String date = intent.getStringExtra("date");
       // String description = intent.getStringExtra("description");
        String image = intent.getStringExtra("image");
        String url = intent.getStringExtra("url");

        tvTitle.setText(title);
        tvSource.setText(source);
        tvDate.setText(date);
        //tvDesccription.setText(description);

        Picasso.with(NewsDetailed.this).load(image).into(imageView);

        webView.getSettings().setDomStorageEnabled(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setLoadsImagesAutomatically(true);
        webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(url);
        if(webView.isShown()){
            webLoader.setVisibility(View.INVISIBLE);
        }

    }
}
