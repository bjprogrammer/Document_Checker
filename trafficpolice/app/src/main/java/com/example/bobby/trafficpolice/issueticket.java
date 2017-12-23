package com.example.bobby.trafficpolice;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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

public class issueticket extends AppCompatActivity {
    ImageButton ib1,ib2;
    String mobile,plateno,comment;
    AlertDialog b4,b2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_issueticket);

        mobile=getIntent().getStringExtra("mobile");
        plateno=getIntent().getStringExtra("plateno");
        comment=getIntent().getStringExtra("comment");

        ib1=(ImageButton) findViewById(R.id.imageButton6);
        ib2=(ImageButton) findViewById(R.id.imageButton7);

        ImageView image = (ImageView)findViewById(R.id.imageView12);
        Animation animation1 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.anim2);
        image.startAnimation(animation1);
    }

    @Override
    protected void onResume() {
        super.onResume();

        ib1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new JSONP().execute();
            }
        });

        ib2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                View v1 = getLayoutInflater().inflate(R.layout.assetdetail, null);
                AlertDialog.Builder b1 = new AlertDialog.Builder(issueticket.this)
                        .setCancelable(true)
                        .setView(v1);

                b2 = b1.create();
                b2.show();

                Button b10 = (Button) v1.findViewById(R.id.button7);
                Button b11 = (Button) v1.findViewById(R.id.button3);
                final EditText e1=(EditText) v1.findViewById(R.id.editText13);

                b10.setOnClickListener(new View.OnClickListener() {
                    @Override

                    public void onClick(View v) {
                        if(!e1.getText().toString().isEmpty()) {

                            new JSONP2().execute(e1.getText().toString());
                        }
                        else
                        {
                            Toast.makeText(issueticket.this, "Please enter asset details in order to continue.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                b11.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        b2.dismiss();
                    }
                });



            }
        });
    }

    @Override
    public void onBackPressed() {
        final View v2 = getLayoutInflater().inflate(R.layout.cancel, null);

        AlertDialog.Builder b5 = new AlertDialog.Builder(issueticket.this)
                .setCancelable(false)
                .setView(v2);

        b4 = b5.create();
        b4.show();

        Button b11 = (Button) v2.findViewById(R.id.button7);
        Button b12=(Button)  v2.findViewById(R.id.button51);

        b11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                b4.dismiss();
            }
        });

        b12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(issueticket.this,MainActivity.class);
                intent.putExtra("mobile",mobile);
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
            jsonurl="http://sixthsenseit.com/blood/partnerissueticket.php";
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
                        +"&"+URLEncoder.encode("issue")+"="+URLEncoder.encode("resolved")+"&"+URLEncoder.encode("title")+"="+URLEncoder.encode("Ticket Issued")
                        +"&"+URLEncoder.encode("recording")+"="+URLEncoder.encode(recording.recording);

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

            Intent intent=new Intent(issueticket.this,doccheckcomplete.class);
            intent.putExtra("plateno",plateno);
            intent.putExtra("mobile",mobile);
            intent.putExtra("status","payfine");
            startActivity(intent);
            finish();

            if(s.contains("successfully"))
            {

            }
        }
    }

    class JSONP2 extends AsyncTask<String,Void,String>
    {
        String jsonurl,jsonstring;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            jsonurl="http://sixthsenseit.com/blood/partnerissueticket2.php";
        }

        @Override
        protected String doInBackground(String... params)
        {
            String data="";
            try {
                URL url=new URL(jsonurl);
                HttpURLConnection httpURLConnection= (HttpURLConnection) url.openConnection();

                httpURLConnection.setRequestMethod("POST");
                OutputStream outputStream=httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter=new BufferedWriter(new OutputStreamWriter(outputStream));
                String postdata= URLEncoder.encode("plateno")+"="+URLEncoder.encode(plateno)+"&"+URLEncoder.encode("mobile")+"="+URLEncoder.encode(mobile)+"&"+URLEncoder.encode("genre")+"="+URLEncoder.encode(comment)
                        +"&"+URLEncoder.encode("issue")+"="+URLEncoder.encode("unresolved"+params[0]+" seized")+"&"+URLEncoder.encode("title")+"="+URLEncoder.encode("Ticket Issued")
                        +"&"+URLEncoder.encode("recording")+"="+URLEncoder.encode(recording.recording);
                bufferedWriter.write(postdata);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();

                InputStream inputStream=httpURLConnection.getInputStream();
                BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream));

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

            Intent intent=new Intent(issueticket.this,doccheckcomplete.class);
            intent.putExtra("plateno",plateno);
            intent.putExtra("mobile",mobile);
            intent.putExtra("status","seizedocument");
            startActivity(intent);
            finish();

            if(s.contains("successfully"))
            {

            }
        }
    }

}
