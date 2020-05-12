package com.esiea.tavuradio.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.esiea.tavuradio.recyclerview.Adapter;
import com.esiea.tavuradio.api.ApiClient;
import com.esiea.tavuradio.R;
import com.esiea.tavuradio.model.Articles;
import com.esiea.tavuradio.model.Headlines;


import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TavuLactu extends AppCompatActivity {

    RecyclerView recyclerView ;
    Adapter adapter ;
    final String API_KEY = "5761e969b23340f496da2c8560faf79d" ;
    List<Articles> articles = new ArrayList<>();
    private Button retour ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tavu);
        this.retour = (Button) findViewById(R.id.retour);

        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        String country = "fr" ;
        String category ="entertainment";
        retrieveJson(country,category,API_KEY);

        retour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent otherActivity = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(otherActivity);
                finish();
            }
        });
    }

    public void retrieveJson(String country,String category, String apiKey){

        Call<Headlines> call = ApiClient.getInstance().getApi().getHeadlines(country, category, apiKey);
        call.enqueue(new Callback<Headlines>() {
            @Override
            public void onResponse(Call<Headlines> call, Response<Headlines> response) {
                if(response.isSuccessful() && response.body().getArticles()!=null){
                    articles.clear();
                    articles = response.body().getArticles();
                    adapter = new Adapter(TavuLactu.this, articles);
                    recyclerView.setAdapter(adapter);

                }
            }

            @Override
            public void onFailure(Call<Headlines> call, Throwable t) {
                Toast.makeText(TavuLactu.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    /*public String getCountry(){
        Locale locale = Locale.getDefault();
        String country = locale.getCountry();
        return country.toLowerCase() ;
    }*/
}