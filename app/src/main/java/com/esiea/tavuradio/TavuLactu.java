package com.esiea.tavuradio;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TavuLactu extends AppCompatActivity {

    private static final String BASE_URL = "https://raw.githubusercontent.com/Martin92350/TavuRadio2/master/";


    private RecyclerView recyclerView;
    private TavuList mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private Button retour ;
    private SharedPreferences sharedPreferences ;
    private Gson gson ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tavu);
         sharedPreferences = getSharedPreferences("application_esiea", Context.MODE_PRIVATE);
        this.retour = (Button) findViewById(R.id.retour);

        gson = new GsonBuilder()
                .setLenient()
                .create();

        List<Actu> actuList = getDataFromCache() ;

        if(getDataFromCache()!= null){
            showList(actuList);
        }else{
            makeApiCall();
        }


        retour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent otherActivity = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(otherActivity);
                finish();
            }
        });

    }

    private List<Actu> getDataFromCache() {

        String jsonActu = sharedPreferences.getString("jsonActuList", null);

        if (jsonActu == null) {
            return null;
        } else{
            Type listType = new TypeToken<List<Actu>>() {
            }.getType();
        return gson.fromJson(jsonActu, listType);
        }
    }


    private void showList(List<Actu>actuList) {

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);



        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);



        mAdapter = new TavuList(actuList);
        recyclerView.setAdapter(mAdapter);
    }



    private void makeApiCall () {


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        ActuApi actuApi = retrofit.create(ActuApi.class);


        Call<RestActuResponse> call = actuApi.getActuResponse();
        call.enqueue(new Callback<RestActuResponse>() {
            @Override
            public void onResponse(Call<RestActuResponse> call, Response<RestActuResponse> response) {


                if(response.isSuccessful()&&response.body() != null){

                    List<Actu> actuList = response.body().getResults();
                    saveList(actuList);
                    showList(actuList);

                }else{

                    Toast.makeText(getApplicationContext(), "API error", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<RestActuResponse> call, Throwable t) {

                Toast.makeText(getApplicationContext(), "network failure", Toast.LENGTH_SHORT).show();
            }

        });
    }

    private void saveList(List<Actu> actuList) {

        String jsonString = gson.toJson(actuList);
        sharedPreferences
                .edit()
                .putString("jsonActuList", jsonString)
                .apply();

        Toast.makeText(getApplicationContext(), "List saved", Toast.LENGTH_SHORT).show();

    }
}
