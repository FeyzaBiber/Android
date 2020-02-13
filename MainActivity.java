package com.example.seskontrolu;

import android.content.Context;
import android.media.AudioManager;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.media.MediaPlayer;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button btnornekses = (Button) findViewById(R.id.btnornekses);
        final Button btndurdur = (Button) findViewById(R.id.btndurdur);
        final MediaPlayer orneksesMediaPlayer = MediaPlayer.create(this, R.raw.ornekses);
        final Switch switch1 = findViewById(R.id.switch1);
        final Button btndurum = (Button) findViewById(R.id.btndurum);
        final AudioManager mAudioManager = (AudioManager)getSystemService(Context.AUDIO_SERVICE);

        btnornekses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                orneksesMediaPlayer.setLooping(true);
                orneksesMediaPlayer.seekTo(0);
                orneksesMediaPlayer.start();
                Toast.makeText(getApplicationContext(), "Ornek Ses Caliyor", Toast.LENGTH_SHORT).show();
            }
        });

        btndurdur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                orneksesMediaPlayer.pause();
                Toast.makeText(getApplicationContext(), "Ses Durduruldu", Toast.LENGTH_SHORT).show();
            }
        });

        btndurum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               int telmodu =  mAudioManager.getMode();
               if (telmodu == 0)
                Toast.makeText(getApplicationContext(),"Normal Mod", Toast.LENGTH_SHORT).show();
              else if (telmodu == 2)
                Toast.makeText(getApplicationContext(), "Speaker Modu", Toast.LENGTH_SHORT).show();
              else
                  Toast.makeText(getApplicationContext(), String.valueOf(telmodu), Toast.LENGTH_SHORT).show();
            }
        });



        switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                {
                    mAudioManager.setMode(AudioManager.MODE_RINGTONE | AudioManager.MODE_IN_CALL);
                    mAudioManager.setSpeakerphoneOn(true);
                    Toast.makeText(getApplicationContext(), "Hoparlor Modu", Toast.LENGTH_SHORT).show();

                }
                else
                {
                    mAudioManager.setMode(AudioManager.MODE_NORMAL);
                    mAudioManager.setSpeakerphoneOn(false);
                    Toast.makeText(getApplicationContext(), "Kulaklik Modu", Toast.LENGTH_SHORT).show();
                }
            }
        });



    }

}

