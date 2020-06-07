package com.esiea.tavuradio.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.esiea.tavuradio.recyclerview.Adapter;
import com.esiea.tavuradio.api.ApiClient;
import com.esiea.tavuradio.R;
import com.esiea.tavuradio.model.Articles;
import com.esiea.tavuradio.model.Headlines;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;


import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TavuLactu extends AppCompatActivity {


    private RecyclerView recyclerView ;
    private SwipeRefreshLayout swipeRefreshLayout ;
    private RecyclerView.LayoutManager layoutManager;
    private Adapter adapter ;
    final String API_KEY = "5761e969b23340f496da2c8560faf79d" ;
    List<Articles> articles = new ArrayList<>();
    private ImageView retour ;
    private SharedPreferences sharedPreferences;
    public static Gson gson ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tavu);
        this.retour =  findViewById(R.id.retour);
        final String country = "fr" ;
        final String category ="entertainment";

        gson = new GsonBuilder()
                .setLenient()
                .create();

        sharedPreferences = getBaseContext().getSharedPreferences("application radio", Context.MODE_PRIVATE);
        articles = haveData();

        if(articles!=null){
            showList(articles);
       }else {
            retrieveJson(country, category, API_KEY);
        }

        retour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent otherActivity = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(otherActivity);
                finish();
            }
        });

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                retrieveJson(country, category, API_KEY);
            }
        });
    }

    public void showList(List<Articles> articles){

        swipeRefreshLayout = findViewById(R.id.swiperefresh);


        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);



        adapter = new Adapter(TavuLactu.this, articles);
        recyclerView.setAdapter(adapter);

    }

    public void retrieveJson(String country,String category, String apiKey){

        swipeRefreshLayout.setRefreshing(true);
        Call<Headlines> call = ApiClient.getInstance().getApi().getHeadlines(country, category, apiKey);
        call.enqueue(new Callback<Headlines>() {
            @Override
            public void onResponse(Call<Headlines> call, Response<Headlines> response) {
                if(response.isSuccessful() && response.body().getArticles()!=null){
                    swipeRefreshLayout.setRefreshing(false);
                    articles.clear();
                    articles = response.body().getArticles();
                    saveList(articles);
                    showList(articles);

                }
            }

            @Override
            public void onFailure(Call<Headlines> call, Throwable t) {
                Toast.makeText(TavuLactu.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    //sauvegarde de l'élément jsonString
    private void saveList(List<Articles> articles) {

        String jsonString = gson.toJson(articles);

        sharedPreferences
                .edit()
                .putString("jsonArticles", jsonString)
                .apply();

    }

    // recupère données de jsonArticles
    private List<Articles> haveData() {

        String jsonArticles = sharedPreferences.getString("jsonArticles", null);

        if(jsonArticles == null){
            return null ;
        }else{
            Type listType = new TypeToken<ArrayList<Articles>>(){}.getType();
             return gson.fromJson(jsonArticles, listType);
        }
    }
    public static Gson getGson() { return gson; }
}