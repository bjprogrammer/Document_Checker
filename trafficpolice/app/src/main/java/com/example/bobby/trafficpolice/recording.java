package com.example.bobby.trafficpolice;

import android.app.AlertDialog;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.AsyncTask;
import android.os.Environment;
import android.os.Handler;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.commons.io.FileUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Locale;
import java.util.Random;

public class recording extends AppCompatActivity implements
        TextToSpeech.OnInitListener {

    private TextToSpeech tts;
    private Button btnSpeak;
    private EditText txtText;
    MediaPlayer song;

    private Button stopButton;
    private Button playButton;

    private MediaRecorder mediaRecorder;
    String voiceStoragePath;

    static final String AB = "abcdefghijklmnopqrstuvwxyz";
    static Random rnd = new Random();

    MediaPlayer mediaPlayer;
    AlertDialog b4;

    String mobile,comment,plateno,question,question2;
    int ticketno=3;

    TextView t1,t2;

    static String recording;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recording);

        mobile=getIntent().getStringExtra("mobile");
        comment=getIntent().getStringExtra("comment");
        plateno=getIntent().getStringExtra("plateno ");

        song = MediaPlayer.create(recording.this, R.raw.beep);
        tts = new TextToSpeech(this, this);
        btnSpeak = (Button) findViewById(R.id.recording_button);
        txtText = (EditText) findViewById(R.id.editText);

        voiceStoragePath = Environment.getExternalStorageDirectory().getAbsolutePath();
        File audioVoice = new File(voiceStoragePath + File.separator + "voices");
        if(!audioVoice.exists()){
            audioVoice.mkdir();
        }
        voiceStoragePath = voiceStoragePath + File.separator + "voices/" + generateVoiceFilename(6) + ".3gpp";

        stopButton = (Button)findViewById(R.id.stop_button);
        playButton = (Button)findViewById(R.id.play_button);

        stopButton.setEnabled(false);
        playButton.setEnabled(true);
        initializeMediaRecord();

        new JSONP().execute();

        /*t1=(TextView)findViewById(R.id.textView);
        t2=(TextView)findViewById(R.id.textView2);
        t2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(t1.getText().toString().equalsIgnoreCase(question))
                {
                    t1.setText(question2);
                }
                else
                {
                    t1.setText(question);
                }
            }
        });*/
    }

    @Override
    protected void onResume() {
        super.onResume();

        btnSpeak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                    if (mediaRecorder == null) {
                        initializeMediaRecord();
                    }
                    startAudioRecording();

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                speakOut();
                            }
                            catch (InterruptedException e)
                            {
                            }
                        }
                    }, 1000);
                }

        });

        stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopAudioRecording();
            }
        });

        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ticketno>=3)
                {
                    Intent intent=new Intent(recording.this,issueticket.class);
                    intent.putExtra("mobile", mobile);
                    intent.putExtra("plateno",plateno);
                    intent.putExtra("comment",comment);
                    startActivity(intent);
                    finish();
                }
                else
                {
                    new JSONP2().execute();
                }

            }
        });
    }

    @Override
    public void onDestroy()
    {
        // Don't forget to shutdown tts!
        if (tts != null)
        {
            tts.shutdown();
            song.release();
        }
        super.onDestroy();
    }

    @Override
    public void onInit(int status)
    {
        if (status == TextToSpeech.SUCCESS)
        {
            int result = tts.setLanguage(Locale.US);
            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED)
            {}
            else
            {
                btnSpeak.setEnabled(true);
            }
        }
        else {}
    }

    private void speakOut() throws InterruptedException
    {
        String text = txtText.getText().toString();
        tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
        while(tts.isSpeaking())
        {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {

                }
            },700);
        }
        stopButton.setEnabled(true);
        song.start();
    }

    private String generateVoiceFilename( int len ){
        StringBuilder sb = new StringBuilder( len );
        for( int i = 0; i < len; i++ )
            sb.append( AB.charAt( rnd.nextInt(AB.length()) ) );
        return sb.toString();
    }

    private void startAudioRecording(){
        btnSpeak.setEnabled(false);

        try {
            mediaRecorder.prepare();
            mediaRecorder.start();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private void stopAudioRecording(){
        if(mediaRecorder != null){
            mediaRecorder.stop();
            mediaRecorder.release();
            mediaRecorder = null;
        }
        stopButton.setEnabled(false);
        btnSpeak.setEnabled(true);
        final View v2 = getLayoutInflater().inflate(R.layout.play, null);

        AlertDialog.Builder b5 = new AlertDialog.Builder(recording.this)
                .setCancelable(false)
                .setView(v2);

        b4 = b5.create();
        b4.show();

        Button b11 = (Button) v2.findViewById(R.id.button2);
        Button b12=(Button)  v2.findViewById(R.id.button6);
        Button b13=(Button) v2.findViewById(R.id.button);

        b11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                b4.dismiss();
            }
        });

        tts.stop();


        File file = new File(voiceStoragePath);
        byte[] bytes = new byte[0];
        try {
            bytes = FileUtils.readFileToByteArray(file);
        } catch (IOException e) {
            e.printStackTrace();
        }

        recording = Base64.encodeToString(bytes, Base64.URL_SAFE);

        b12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ticketno>=3)
                {
                    Intent intent1=new Intent(recording.this,issueticket.class);
                    intent1.putExtra("mobile", mobile);
                    intent1.putExtra("plateno",plateno);
                    intent1.putExtra("comment",comment);
                    startActivity(intent1);
                    finish();
                }
                else
                {
                    new JSONP2().execute();
                }
            }
        });

        b13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playLastStoredAudioMusic();
                mediaPlayerPlaying();
            }
        });
    }

    private void playLastStoredAudioMusic(){
        mediaPlayer = new MediaPlayer();
        try {
            mediaPlayer.setDataSource(voiceStoragePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            mediaPlayer.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
        int maxVolume = 50;
        float log1=(float)(Math.log(maxVolume-maxVolume)/Math.log(maxVolume));
        mediaPlayer.setVolume(1-log1,1-log1);
        mediaPlayer.start();
        btnSpeak.setEnabled(true);
        //playButton.setEnabled(false);
    }

    private void stopAudioPlay(){
        if(mediaPlayer != null){
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    private void mediaPlayerPlaying(){
        if(!mediaPlayer.isPlaying()){
            stopAudioPlay();
        }
    }

    private void initializeMediaRecord(){
        mediaRecorder = new MediaRecorder();
        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        mediaRecorder.setAudioEncoder(MediaRecorder.OutputFormat.AMR_NB);
        mediaRecorder.setOutputFile(voiceStoragePath);
    }

    @Override
    public void onBackPressed() {
        final View v1 = getLayoutInflater().inflate(R.layout.cancel, null);

        AlertDialog.Builder b5 = new AlertDialog.Builder(recording.this)
                .setCancelable(false)
                .setView(v1);

        b4 = b5.create();
        b4.show();

        Button b21 = (Button) v1.findViewById(R.id.button7);
        Button b22 = (Button) v1.findViewById(R.id.button51);

        b21.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                b4.dismiss();
            }
        });

        b22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(recording.this, MainActivity.class);
                intent.putExtra("mobile", getIntent().getStringExtra("mobile"));
                startActivity(intent);
                finish();
            }
        });
    }


    class JSONP extends AsyncTask<Void,Void,String>
    {
        String jsonurl,jsonstring;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            jsonurl="http://sixthsenseit.com/blood/partnerfetchticket.php";
        }

        @Override
        protected String doInBackground(Void... params)
        {
            String data="";
            try {
                URL url=new URL(jsonurl);
                HttpURLConnection httpURLConnection= (HttpURLConnection) url.openConnection();

                httpURLConnection.setRequestMethod("POST");
                OutputStream outputStream=httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter=new BufferedWriter(new OutputStreamWriter(outputStream));
                bufferedWriter.close();
                outputStream.close();

                InputStream inputStream=httpURLConnection.getInputStream();
                BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream));
                String postdata= URLEncoder.encode("plateno")+"="+URLEncoder.encode(plateno);
                StringBuilder  stringBuilder=new StringBuilder();
                while((jsonstring= bufferedReader.readLine())!=null)
                {
                    stringBuilder.append(jsonstring+"\n");
                }

                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();

                JSONObject jsonRootObject = new JSONObject(stringBuilder.toString());
                JSONArray jsonArray = jsonRootObject.optJSONArray("server_response");
                for(int i=0; i < jsonArray.length(); i++)
                {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    ticketno = Integer.parseInt(jsonObject.optString("ticketno").toString());
                    question = jsonObject.optString("question").toString();
                    question2=jsonObject.optString("question2").toString();
                }
            }
            catch (MalformedURLException e)
            {
                e.printStackTrace();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return  data;
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if(s.contains("successfully"))
            {
                //t1.setText(drivinglicensestatus);
            }
        }
    }

    class JSONP2 extends AsyncTask<Void,Void,String>
    {
        String jsonurl,jsonstring;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            jsonurl="http://sixthsenseit.com/blood/partnerissuewarning.php";
        }

        @Override
        protected String doInBackground(Void... params)
        {
            String data="";
            try {
                URL url=new URL(jsonurl);
                HttpURLConnection httpURLConnection= (HttpURLConnection) url.openConnection();

                httpURLConnection.setRequestMethod("POST");
                OutputStream outputStream=httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter=new BufferedWriter(new OutputStreamWriter(outputStream));
                bufferedWriter.close();
                outputStream.close();

                InputStream inputStream=httpURLConnection.getInputStream();
                BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream));
                String postdata= URLEncoder.encode("plateno")+"="+URLEncoder.encode(plateno)+"&"+URLEncoder.encode("mobile")+"="+URLEncoder.encode(mobile)+"&"+URLEncoder.encode("genre")+"="+URLEncoder.encode(comment)
                        +"&"+URLEncoder.encode("issue")+"="+URLEncoder.encode("-")+"&"+URLEncoder.encode("title")+"="+URLEncoder.encode("Warning")
                        +"&"+URLEncoder.encode("recording")+"="+URLEncoder.encode(recording);
                StringBuilder  stringBuilder=new StringBuilder();
                while((jsonstring= bufferedReader.readLine())!=null)
                {
                    stringBuilder.append(jsonstring+"\n");
                }

                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();

                JSONObject jsonRootObject = new JSONObject(stringBuilder.toString());
                JSONArray jsonArray = jsonRootObject.optJSONArray("server_response");
            }
            catch (MalformedURLException e)
            {
                e.printStackTrace();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return  data;
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if(s.contains("successfully"))
            {
                Intent intent=new Intent(recording.this,doccheckcomplete.class);
                intent.putExtra("plateno",plateno);
                intent.putExtra("mobile",mobile);
                intent.putExtra("status","warning");
                startActivity(intent);
                finish();
            }
        }
    }


}
