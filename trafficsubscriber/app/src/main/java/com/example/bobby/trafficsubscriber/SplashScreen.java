package com.example.bobby.trafficsubscriber;

import android.content.Intent;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.example.bobby.trafficsubscriber.utility.DatabaseHelper;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class SplashScreen extends AppCompatActivity {
    private static int SPLASH_TIME_OUT = 7000;
    DatabaseHelper dh;
    public static Boolean logout;
    public String username;
    MediaPlayer song;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splashscreen);

        dh = new DatabaseHelper(SplashScreen.this);

        new LongOperation().execute();
        song = MediaPlayer.create(SplashScreen.this, R.raw.udr);
    }

    private class LongOperation extends AsyncTask<Void, Void, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(Void... params) {

            String data = "";
            Cursor b10 = dh.getalldata();
            if (b10.getCount() == 0) {
                logout = false;
            } else {
                while (b10.moveToNext()) {
                    username = b10.getString(1);
                    if (b10.getString(3).equalsIgnoreCase("True")) {
                        logout = true;
                        data="logged";

                    } else {
                        logout = false;
                    }
                }
            }
            return data;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if (s.contains("logged")) {
                Intent bj = new Intent(SplashScreen.this, MainActivity.class);
                bj.putExtra("username", username);
                startActivity(bj);
                finish();
            }
            else
            {
                song.start();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        song.release();
                        Intent i = new Intent(SplashScreen.this, Signin.class);
                        startActivity(i);
                        finish();
                    }
                }, SPLASH_TIME_OUT);

                ImageView image = (ImageView)findViewById(R.id.imageView1);
                Animation animation1 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.anim);
                image.startAnimation(animation1);
            }
        }

    }
}
