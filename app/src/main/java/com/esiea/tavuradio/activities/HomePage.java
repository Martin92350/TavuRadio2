package com.esiea.tavuradio.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.esiea.tavuradio.R;

import static java.lang.Thread.sleep;

public class HomePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        final Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    sleep(1500);
                    Intent intent = new Intent(HomePage.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        });thread.start();
    }
}
