package com.example.bobby.trafficsubscriber;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewSwitcher;

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

public class Signin extends AppCompatActivity {
    ImageSwitcher imageSwitcher;
    int[] imageArray1 = { R.drawable.nigerianpolice1, R.drawable.nigerianpolice2,
            R.drawable.nigerianpolice3};
    int i = 0;
    Animation in,out;
    Button b1,b2,b3;
    EditText t1,t2;
    static String username,password;
    AlertDialog b4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        imageSwitcher=(ImageSwitcher) findViewById(R.id.imageSwitcher);
        imageSwitcher.setFactory(new ViewSwitcher.ViewFactory() {
            public View makeView() {
                ImageView myView = new ImageView(getApplicationContext());
                myView.setScaleType(ImageView.ScaleType.FIT_XY);
                myView.setLayoutParams(new ImageSwitcher.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT));
                return myView;
            }
        });

        b3=(Button)findViewById(R.id.button4);
        t1=(EditText) findViewById(R.id.editText);
        t2=(EditText) findViewById(R.id.editText2);
        TextView t3=(TextView) findViewById(R.id.textView58);
        TextView t4=(TextView) findViewById(R.id.textView50);

        t3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                username=t1.getText().toString();
                password=t2.getText().toString();

                if(t1.getText().toString().isEmpty() || t2.getText().toString().isEmpty())
                {
                    Toast.makeText(Signin.this, "Please fill all the details.", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    new JSONP().execute();
                }
            }
        });

        t4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Signin.this,Signup.class);
                startActivity(intent);
            }
        });

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Signin.this,Forgetpassword.class);
                startActivity(intent);
            }
        });

        in = AnimationUtils.loadAnimation(this,android.R.anim.slide_in_left);
        out=AnimationUtils.loadAnimation(this,android.R.anim.fade_in);
        imageSwitcher.setImageResource(R.drawable.nigerianpolice1);

        imageSwitcher.postDelayed(r,5000);
    }

    public class  JSONP extends AsyncTask<Void,Void,String> {
        String jsonurl, jsonstring;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            jsonurl ="http://bjprogrammer.co.nf/nigeriantraffic/signin.php";

        }

        @Override
        protected String doInBackground(Void... params) {

            String data = "";

            try {
                URL url = new URL(jsonurl);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

                httpURLConnection.setRequestMethod("POST");
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));

                String postdata = URLEncoder.encode("username") + "=" + URLEncoder.encode(username) + "&" + URLEncoder.encode("password") + "=" + URLEncoder.encode(password);

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

                data=stringBuilder.toString();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return data;
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);

        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            if(s.contains("logged"))
            {
                SplashScreen.logout=true;
                DatabaseHelper dh = new DatabaseHelper(Signin.this);
                boolean b = dh.deletedata(1);
                boolean b1 = dh.insertdata(username, password,SplashScreen.logout.toString());
                Intent bj=new Intent(Signin.this,MainActivity.class);
                bj.putExtra("username",username);
                startActivity(bj);
                finish();
            }
            else
            {
                Toast.makeText(Signin.this,s, Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        SpannableString ss = new SpannableString("By Logging in, you agree to our terms and conditions and that you have read our privacy policy.");
        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(View textView) {
                View v = getLayoutInflater().inflate(R.layout.termsandconditions, null);

                AlertDialog.Builder b2 = new AlertDialog.Builder(Signin.this)
                        .setCancelable(true)
                        .setView(v);

                b4 = b2.create();
                b4.show();

                Button b10 = (Button) v.findViewById(R.id.button7);

                b10.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        b4.cancel();
                    }
                });

            }

            @Override
            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);
                ds.setUnderlineText(false);
                ds.setColor(Color.RED);
            }
        };

        ss.setSpan(clickableSpan, 31,52, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        TextView textView = (TextView) findViewById(R.id.textView);
        textView.setText(ss);
        textView.setMovementMethod(LinkMovementMethod.getInstance());
        }

    Runnable r = new Runnable()
    {
        @Override
        public void run() {
            imageSwitcher.setInAnimation(in);
            if (i == 0) {
                i = imageArray1.length;
            }
            i--;
            imageSwitcher.setImageResource(imageArray1[i]);
            imageSwitcher.postDelayed(r, 5000);
        }
    };

}
