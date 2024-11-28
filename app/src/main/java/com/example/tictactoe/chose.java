package com.example.tictactoe;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.Locale;

public class chose extends AppCompatActivity {
    Button b1, b2;
    ImageView ima;
    MediaPlayer mp;
    ImageView img;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chose);

        b1 = findViewById(R.id.computer);
        b2 = findViewById(R.id.friend);
        ima = findViewById(R.id.sound);
        img = findViewById(R.id.la);

        loadLanguage();

        ActionBar ac = getSupportActionBar();
        if (ac != null) {
            ac.setTitle(getResources().getString(R.string.app_name));
        }

        mp = MediaPlayer.create(getApplicationContext(), R.raw.backmusic);
        ima.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mp.isPlaying()) {
                    mp.pause();
                    ima.setImageResource(R.drawable.volume_off_512);
                } else {

                    mp.setLooping(true);
                    mp.start();
                    ima.setImageResource(R.drawable.hh);
                }
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(chose.this, friend.class);
                startActivity(i);
            }
        });

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(chose.this, computr.class);
                startActivity(i);
            }
        });


    img.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            changeLanguage();
        }
    });


    }



    private void changeLanguage() {
        final String[] list = {"Arabic", "English" , "Frensh" , "china" , "Russia"};
        AlertDialog.Builder b = new AlertDialog.Builder(chose.this);
        b.setTitle("Language");
        b.setSingleChoiceItems(list, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (which == 0) {
                    setLocale("ar");
                    recreate();
                } else if(which == 1) {
                    setLocale("en");
                    recreate();
                }
                else if (which == 2){
                    setLocale("fr");
                    recreate();
                }
                else if (which == 3){
                    setLocale("zh");
                    recreate();
                }
                else if (which == 4){
                    setLocale("ru");
                    recreate();
                }
                restartActivity();
                dialog.dismiss();
            }
        });
        AlertDialog m = b.create();
        m.show();
    }

    private void setLocale(String lang) {
        Locale l = new Locale(lang);
        Locale.setDefault(l);

        Configuration conf = new Configuration();
        conf.setLocale(l);
        getBaseContext().getResources().updateConfiguration(conf, getBaseContext().getResources().getDisplayMetrics());

        SharedPreferences.Editor e = getSharedPreferences("my_lang", MODE_PRIVATE).edit();
        e.putString("lang", lang);
        e.apply();
    }

    private void loadLanguage() {
        SharedPreferences sp = getSharedPreferences("my_lang", MODE_PRIVATE);
        String language = sp.getString("lang", "");
        if (!language.isEmpty()) {
            setLocale(language);
        }
    }
    private void restartActivity() {
        Intent intent = getIntent();
        finish();
        startActivity(intent);
    }
}
