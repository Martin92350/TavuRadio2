package com.esiea.tavuradio;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private Button TavuLactu ;
    private Button Play ;
    private MediaPlayer mediaPlayer ;

    boolean prepared = false ;
    boolean started = false ;

    private String stream =  "https://www.radioking.com/play/tavu-radio";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.TavuLactu = (Button) findViewById(R.id.TavuLactu);
        this.Play = (Button) findViewById(R.id.Play);
        Play.setEnabled(false);
        Play.setText("LOADING");

        mediaPlayer = new MediaPlayer() ;
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);

        new PlayerTask().execute(stream) ;

        TavuLactu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent otherActivity = new Intent(getApplicationContext(),TavuLactu.class);
                startActivity(otherActivity);
                finish();
            }
        });

        Play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(started){
                    started = false ;
                    mediaPlayer.pause();
                    Play.setText("Play");
                }else{
                    started = true ;
                    mediaPlayer.start();
                    Play.setText("Pause");
                }

            }
        });

    }

     class PlayerTask extends AsyncTask<String, Void, Boolean > {
        @Override
        protected Boolean doInBackground(String... strings) {

            try {
                mediaPlayer.setDataSource(strings[0]);
                mediaPlayer.prepare();
                prepared = true ;
            } catch (IOException e) {
                e.printStackTrace();
            }
            return prepared;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            Play.setEnabled(true);
            Play.setText("PLAY");
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(started){
            mediaPlayer.pause();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(started){
            mediaPlayer.start();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(prepared){
            mediaPlayer.release();
        }
    }
}
