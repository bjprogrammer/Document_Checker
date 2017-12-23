package com.example.bobby.trafficsubscriber;

import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
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

import com.example.bobby.trafficsubscriber.utility.DatabaseHelper;
import com.example.bobby.trafficsubscriber.utility.FragmentDrawer;
import com.theappguruz.imagezoom.ImageViewTouch;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements FragmentDrawer.FragmentDrawerListener{
    AlertDialog b2,b4,b6,b7;
    String username,otp;
    String App="no",SMS="no",email="no",Voicecall="no",Bypost="no",language;

    private Uri mImageCaptureUri;
    private static final int PICK_FROM_CAMERA = 1;
    private static final int CROP_FROM_CAMERA = 2;
    private static final int PICK_FROM_FILE = 3;
    byte[] b;
    static String encodedimageno="-",encodedImage,encodedImage1="-",encodedImage2="-",encodedImage3="-",encodedImage4="-",encodedImage5="-",encodedImage6="-",encodedImage7="-",encodedImage8="-",encodedImage9="-" ,encodedImage31="-",encodedImage32="-",encodedImage33="-",encodedImage34="-",encodedImage35="-",encodedImage36="-",encodedImage37="-",encodedImage38="-",encodedImage39="-",profilepic="-";
    Bitmap bitm;

    TextView t1,t2,t3,t4,t5,t6,t7,t8,t9;

    private ImageViewTouch ivLargeImage;
    private Toolbar mToolbar;
    private FragmentDrawer drawerFragment;

    String email1,mobile;
    ImageButton ib;

    ImageView iv;
    TextView t10;
    Bitmap image;

    String drivinglicensestatus,tyreexpirationstatus,fireextinguisherstatus,viostatus,insurancestatus,hackneypermitstatus,
            ownershipproofstatus,policepermitstatus,tintedglaasspermitstatus,drivinglicensepic,tyreexpirationpic,
            fireextinguisherpic,viopic,insurancepic,hackneypermitpic,ownershipproofpic,policepermitpic,tintedglaasspermitpic;

    TextView t21,t22,t23,t24,t25,t26,t27,t28,t29,t31,t32,t33,t34,t35,t36,t37,t38,t39;

    String docname;
    EditText e1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username=getIntent().getStringExtra("username");

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        drawerFragment = (FragmentDrawer)
                getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);
        drawerFragment.setUp(R.id.fragment_navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout), mToolbar);
        drawerFragment.setDrawerListener(this);

        displayView(0);

        t21=(TextView)findViewById(R.id.textView48);
        t31=(TextView)findViewById(R.id.textView17);
        t22=(TextView)findViewById(R.id.textView47);
        t32=(TextView)findViewById(R.id.textView19);
        t23=(TextView)findViewById(R.id.textView46);
        t33=(TextView)findViewById(R.id.textView21);
        t24=(TextView)findViewById(R.id.textView45);
        t34=(TextView)findViewById(R.id.textView23);
        t25=(TextView)findViewById(R.id.textView44);
        t35=(TextView)findViewById(R.id.textView25);
        t26=(TextView)findViewById(R.id.textView41);
        t36=(TextView)findViewById(R.id.textView28);
        t27=(TextView)findViewById(R.id.textView40);
        t37=(TextView)findViewById(R.id.textView30);
        t28=(TextView)findViewById(R.id.textView38);
        t38=(TextView)findViewById(R.id.textView32);
        t29=(TextView)findViewById(R.id.textView39);
        t39=(TextView)findViewById(R.id.textView34);

        new JSONP10().execute();
    }

    public class  JSONP extends AsyncTask<Void,Void,String> {
        String jsonurl, jsonstring;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            jsonurl ="http://bjprogrammer.co.nf/nigeriantraffic/otpverify.php";

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
                String postdata = URLEncoder.encode("username") + "=" + URLEncoder.encode(username) + "&" + URLEncoder.encode("otp") + "=" + URLEncoder.encode(otp);

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

            if(s.contains("verified"))
            {
                Toast.makeText(MainActivity.this, s, Toast.LENGTH_SHORT).show();
                b2.dismiss();
                final View v2 = getLayoutInflater().inflate(R.layout.setting, null);

                AlertDialog.Builder b3 = new AlertDialog.Builder(MainActivity.this)
                        .setCancelable(false)
                        .setView(v2);

                b4 = b3.create();
                b4.show();

                Button b10 = (Button) v2.findViewById(R.id.button7);

                b10.setOnClickListener(new View.OnClickListener() {
                    @Override

                    public void onClick(View v) {
                        CheckBox c1 = (CheckBox) v2.findViewById(R.id.checkBox);
                        CheckBox c2 = (CheckBox) v2.findViewById(R.id.checkBox2);
                        CheckBox c3 = (CheckBox) v2.findViewById(R.id.checkBox3);
                        CheckBox c4 = (CheckBox) v2.findViewById(R.id.checkBox4);
                        CheckBox c5 = (CheckBox) v2.findViewById(R.id.checkBox5);

                        if (c1.isChecked())
                        {
                            App="yes";
                        }
                        if (c2.isChecked())
                        {
                            email="yes";
                        }
                        if (c3.isChecked())
                        {
                            Voicecall="yes";
                        }
                        if (c4.isChecked())
                        {
                            Bypost="yes";
                        }
                        if (c5.isChecked())
                        {
                            SMS="yes";
                        }

                        RadioGroup rg=(RadioGroup)v2.findViewById(R.id.radioGroup);
                        int id=rg.getCheckedRadioButtonId();
                        RadioButton r1=(RadioButton)v2.findViewById(id);
                        language=r1.getText().toString();

                        if((Voicecall.equalsIgnoreCase("yes") || SMS.equalsIgnoreCase("yes") || Bypost.equalsIgnoreCase("yes") ||App.equalsIgnoreCase("yes") || email.equalsIgnoreCase("yes")) && !r1.getText().toString().isEmpty())
                        {
                            new JSONP2().execute();
                        }
                        else
                        {
                            Toast.makeText(MainActivity.this, "Please fill this form in order to continue.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
            else
            {
                Toast.makeText(MainActivity.this,s, Toast.LENGTH_LONG).show();
            }
        }
    }

    public class  JSONP2 extends AsyncTask<Void,Void,String> {
        String jsonurl, jsonstring;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            jsonurl ="http://bjprogrammer.co.nf/nigeriantraffic/settings.php";

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
                String postdata = URLEncoder.encode("username") + "=" + URLEncoder.encode(username) + "&" + URLEncoder.encode("sms") + "=" + URLEncoder.encode(SMS)
                        + "&" + URLEncoder.encode("voicecall") + "=" + URLEncoder.encode(Voicecall)+ "&" + URLEncoder.encode("byemail") + "=" + URLEncoder.encode(email)
                        + "&" + URLEncoder.encode("app") + "=" + URLEncoder.encode(App)+ "&" + URLEncoder.encode("bypost") + "=" + URLEncoder.encode(Bypost)
                        + "&" + URLEncoder.encode("language") + "=" + URLEncoder.encode(language);

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

            if(s.contains("adjusted"))
            {
                Toast.makeText(MainActivity.this, s, Toast.LENGTH_SHORT).show();

                b4.dismiss();
                View v3 = getLayoutInflater().inflate(R.layout.uploaddocuments, null);
                t1=(TextView) v3.findViewById(R.id.textView17);
                t2=(TextView) v3.findViewById(R.id.textView19);
                t3=(TextView) v3.findViewById(R.id.textView21);
                t4=(TextView) v3.findViewById(R.id.textView23);
                t5=(TextView) v3.findViewById(R.id.textView25);
                t6=(TextView) v3.findViewById(R.id.textView28);
                t7=(TextView) v3.findViewById(R.id.textView30);
                t8=(TextView) v3.findViewById(R.id.textView32);
                t9=(TextView) v3.findViewById(R.id.textView34);

                AlertDialog.Builder b5 = new AlertDialog.Builder(MainActivity.this)
                        .setCancelable(false)
                        .setView(v3);

                b7 = b5.create();
                b7.show();

                Button b10 = (Button) v3.findViewById(R.id.button7);
                Button b11 = (Button) v3.findViewById(R.id.button);

                t1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(t1.getText().toString().equalsIgnoreCase("Upload"))
                        {
                            android.support.v7.app.AlertDialog.Builder build = new android.support.v7.app.AlertDialog.Builder(MainActivity.this);
                            build.setTitle("Upload Driving License image using-");

                            build.setPositiveButton("camera", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                                    mImageCaptureUri = Uri.fromFile(new File(Environment.getExternalStorageDirectory(),
                                            "tmp_avatar_" + String.valueOf(System.currentTimeMillis()) + ".jpg"));

                                    intent.putExtra(MediaStore.EXTRA_OUTPUT, mImageCaptureUri);
                                    encodedimageno="1";
                                    try {
                                        intent.putExtra("return-data", true);
                                        startActivityForResult(intent, PICK_FROM_CAMERA);
                                    } catch (ActivityNotFoundException e) {
                                        e.printStackTrace();
                                    }
                                }

                            });

                            build.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                }
                            });

                            build.setNegativeButton("Gallery", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which)
                                {
                                    Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                                            android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                                    encodedimageno="1";
                                    startActivityForResult(galleryIntent, PICK_FROM_FILE);
                                }
                            });
                            build.show();

                        }

                        else
                        {
                            View v3 = getLayoutInflater().inflate(R.layout.showdoc, null);
                            ImageView iv=(ImageView) v3.findViewById(R.id.imageView10);
                            Bitmap s=decodeBase64(encodedImage1);
                            iv.setImageBitmap(s);
                            ivLargeImage = (ImageViewTouch) v3.findViewById(R.id.ivLargeImageView);
                            ivLargeImage.setImageBitmapReset(s, 0, true);
                            AlertDialog.Builder b5 = new AlertDialog.Builder(MainActivity.this)
                                    .setCancelable(true)
                                    .setView(v3);

                            b6 = b5.create();
                            b6.show();

                            Button b10 = (Button) v3.findViewById(R.id.button7);
                            b10.setOnClickListener(new View.OnClickListener() {
                                @Override

                                public void onClick(View v) {
                                    b6.cancel();
                                }
                            });
                        }
                    }
                });


                t2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(t2.getText().toString().equalsIgnoreCase("Upload"))
                        {
                            android.support.v7.app.AlertDialog.Builder build = new android.support.v7.app.AlertDialog.Builder(MainActivity.this);
                            build.setTitle("Upload Tyre Expiration document image using-");

                            build.setPositiveButton("camera", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                                    mImageCaptureUri = Uri.fromFile(new File(Environment.getExternalStorageDirectory(),
                                            "tmp_avatar_" + String.valueOf(System.currentTimeMillis()) + ".jpg"));

                                    intent.putExtra(MediaStore.EXTRA_OUTPUT, mImageCaptureUri);
                                    encodedimageno="2";
                                    try {
                                        intent.putExtra("return-data", true);
                                        startActivityForResult(intent, PICK_FROM_CAMERA);
                                    } catch (ActivityNotFoundException e) {
                                        e.printStackTrace();
                                    }
                                }

                            });


                            build.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                }
                            });

                            build.setNegativeButton("Gallery", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which)
                                {
                                    Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                                            android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                                    encodedimageno="2";
                                    startActivityForResult(galleryIntent, PICK_FROM_FILE);
                                }
                            });
                            build.show();

                        }

                        else
                        {
                            View v3 = getLayoutInflater().inflate(R.layout.showdoc, null);
                            ImageView iv=(ImageView) v3.findViewById(R.id.imageView10);
                            Bitmap s=decodeBase64(encodedImage2);
                            iv.setImageBitmap(s);
                            ivLargeImage = (ImageViewTouch) v3.findViewById(R.id.ivLargeImageView);
                            ivLargeImage.setImageBitmapReset(s, 0, true);
                            AlertDialog.Builder b5 = new AlertDialog.Builder(MainActivity.this)
                                    .setCancelable(true)
                                    .setView(v3);

                            b6 = b5.create();
                            b6.show();

                            Button b10 = (Button) v3.findViewById(R.id.button7);
                            b10.setOnClickListener(new View.OnClickListener() {
                                @Override

                                public void onClick(View v) {
                                    b6.cancel();
                                }
                            });
                        }
                    }
                });

                t3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(t3.getText().toString().equalsIgnoreCase("Upload"))
                        {
                            android.support.v7.app.AlertDialog.Builder build = new android.support.v7.app.AlertDialog.Builder(MainActivity.this);
                            build.setTitle("Upload Fire Extinguisher document image using-");

                            build.setPositiveButton("camera", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                                    mImageCaptureUri = Uri.fromFile(new File(Environment.getExternalStorageDirectory(),
                                            "tmp_avatar_" + String.valueOf(System.currentTimeMillis()) + ".jpg"));

                                    intent.putExtra(MediaStore.EXTRA_OUTPUT, mImageCaptureUri);
                                    encodedimageno="3";
                                    try {
                                        intent.putExtra("return-data", true);
                                        startActivityForResult(intent, PICK_FROM_CAMERA);
                                    } catch (ActivityNotFoundException e) {
                                        e.printStackTrace();
                                    }
                                }

                            });


                            build.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                }
                            });

                            build.setNegativeButton("Gallery", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which)
                                {
                                    Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                                            android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                                    encodedimageno="3";
                                    startActivityForResult(galleryIntent, PICK_FROM_FILE);
                                }
                            });
                            build.show();
                        }

                        else
                        {
                            View v3 = getLayoutInflater().inflate(R.layout.showdoc, null);
                            ImageView iv=(ImageView) v3.findViewById(R.id.imageView10);
                            Bitmap s=decodeBase64(encodedImage3);
                            iv.setImageBitmap(s);
                            ivLargeImage = (ImageViewTouch) v3.findViewById(R.id.ivLargeImageView);
                            ivLargeImage.setImageBitmapReset(s, 0, true);
                            AlertDialog.Builder b5 = new AlertDialog.Builder(MainActivity.this)
                                    .setCancelable(true)
                                    .setView(v3);

                            b6 = b5.create();
                            b6.show();

                            Button b10 = (Button) v3.findViewById(R.id.button7);
                            b10.setOnClickListener(new View.OnClickListener() {
                                @Override

                                public void onClick(View v) {
                                    b6.cancel();
                                }
                            });
                        }
                    }
                });

                t4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(t4.getText().toString().equalsIgnoreCase("Upload"))
                        {
                            android.support.v7.app.AlertDialog.Builder build = new android.support.v7.app.AlertDialog.Builder(MainActivity.this);
                            build.setTitle("Upload VIO document image using-");

                            build.setPositiveButton("camera", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                                    mImageCaptureUri = Uri.fromFile(new File(Environment.getExternalStorageDirectory(),
                                            "tmp_avatar_" + String.valueOf(System.currentTimeMillis()) + ".jpg"));

                                    intent.putExtra(MediaStore.EXTRA_OUTPUT, mImageCaptureUri);
                                    encodedimageno="4";
                                    try {
                                        intent.putExtra("return-data", true);
                                        startActivityForResult(intent, PICK_FROM_CAMERA);
                                    } catch (ActivityNotFoundException e) {
                                        e.printStackTrace();
                                    }
                                }

                            });


                            build.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                }
                            });

                            build.setNegativeButton("Gallery", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which)
                                {
                                    Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                                            android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                                    encodedimageno="4";
                                    startActivityForResult(galleryIntent, PICK_FROM_FILE);
                                }
                            });
                            build.show();

                        }
                        else
                        {
                            View v3 = getLayoutInflater().inflate(R.layout.showdoc, null);
                            ImageView iv=(ImageView) v3.findViewById(R.id.imageView10);
                            Bitmap s=decodeBase64(encodedImage4);
                            iv.setImageBitmap(s);
                            ivLargeImage = (ImageViewTouch) v3.findViewById(R.id.ivLargeImageView);
                            ivLargeImage.setImageBitmapReset(s, 0, true);
                            AlertDialog.Builder b5 = new AlertDialog.Builder(MainActivity.this)
                                    .setCancelable(true)
                                    .setView(v3);

                            b6 = b5.create();
                            b6.show();

                            Button b10 = (Button) v3.findViewById(R.id.button7);
                            b10.setOnClickListener(new View.OnClickListener() {
                                @Override

                                public void onClick(View v) {
                                    b6.cancel();
                                }
                            });
                        }
                    }
                });

                t5.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(t5.getText().toString().equalsIgnoreCase("Upload"))
                        {
                            android.support.v7.app.AlertDialog.Builder build = new android.support.v7.app.AlertDialog.Builder(MainActivity.this);
                            build.setTitle("Upload Insurance document image using-");

                            build.setPositiveButton("camera", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                                    mImageCaptureUri = Uri.fromFile(new File(Environment.getExternalStorageDirectory(),
                                            "tmp_avatar_" + String.valueOf(System.currentTimeMillis()) + ".jpg"));

                                    intent.putExtra(MediaStore.EXTRA_OUTPUT, mImageCaptureUri);
                                    encodedimageno="5";
                                    try {
                                        intent.putExtra("return-data", true);
                                        startActivityForResult(intent, PICK_FROM_CAMERA);
                                    } catch (ActivityNotFoundException e) {
                                        e.printStackTrace();
                                    }
                                }

                            });


                            build.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                }
                            });

                            build.setNegativeButton("Gallery", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which)
                                {
                                    Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                                            android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                                    encodedimageno="5";
                                    startActivityForResult(galleryIntent, PICK_FROM_FILE);
                                }
                            });
                            build.show();

                        }

                        else
                        {
                            View v3 = getLayoutInflater().inflate(R.layout.showdoc, null);
                            ImageView iv=(ImageView) v3.findViewById(R.id.imageView10);
                            Bitmap s=decodeBase64(encodedImage5);
                            iv.setImageBitmap(s);
                            ivLargeImage = (ImageViewTouch) v3.findViewById(R.id.ivLargeImageView);
                            ivLargeImage.setImageBitmapReset(s, 0, true);
                            AlertDialog.Builder b5 = new AlertDialog.Builder(MainActivity.this)
                                    .setCancelable(true)
                                    .setView(v3);

                            b6 = b5.create();
                            b6.show();

                            Button b10 = (Button) v3.findViewById(R.id.button7);
                            b10.setOnClickListener(new View.OnClickListener() {
                                @Override

                                public void onClick(View v) {
                                    b6.cancel();
                                }
                            });
                        }
                    }
                });

                t6.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(t6.getText().toString().equalsIgnoreCase("Upload"))
                        {
                            android.support.v7.app.AlertDialog.Builder build = new android.support.v7.app.AlertDialog.Builder(MainActivity.this);
                            build.setTitle("Upload Hackney Permit document image using-");

                            build.setPositiveButton("camera", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                                    mImageCaptureUri = Uri.fromFile(new File(Environment.getExternalStorageDirectory(),
                                            "tmp_avatar_" + String.valueOf(System.currentTimeMillis()) + ".jpg"));

                                    intent.putExtra(MediaStore.EXTRA_OUTPUT, mImageCaptureUri);
                                    encodedimageno="6";
                                    try {
                                        intent.putExtra("return-data", true);
                                        startActivityForResult(intent, PICK_FROM_CAMERA);
                                    } catch (ActivityNotFoundException e) {
                                        e.printStackTrace();
                                    }
                                }

                            });


                            build.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                }
                            });

                            build.setNegativeButton("Gallery", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which)
                                {
                                    Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                                            android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                                    encodedimageno="6";
                                    startActivityForResult(galleryIntent, PICK_FROM_FILE);
                                }
                            });
                            build.show();

                        }

                        else
                        {
                            View v3 = getLayoutInflater().inflate(R.layout.showdoc, null);
                            ImageView iv=(ImageView) v3.findViewById(R.id.imageView10);
                            Bitmap s=decodeBase64(encodedImage6);
                            iv.setImageBitmap(s);
                            ivLargeImage = (ImageViewTouch) v3.findViewById(R.id.ivLargeImageView);
                            ivLargeImage.setImageBitmapReset(s, 0, true);
                            AlertDialog.Builder b5 = new AlertDialog.Builder(MainActivity.this)
                                    .setCancelable(true)
                                    .setView(v3);

                            b6 = b5.create();
                            b6.show();

                            Button b10 = (Button) v3.findViewById(R.id.button7);
                            b10.setOnClickListener(new View.OnClickListener() {
                                @Override

                                public void onClick(View v) {
                                    b6.cancel();
                                }
                            });
                        }
                    }
                });

                t7.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(t7.getText().toString().equalsIgnoreCase("Upload"))
                        {
                            android.support.v7.app.AlertDialog.Builder build = new android.support.v7.app.AlertDialog.Builder(MainActivity.this);
                            build.setTitle("Upload Ownership Proof document image using-");

                            build.setPositiveButton("camera", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                                    mImageCaptureUri = Uri.fromFile(new File(Environment.getExternalStorageDirectory(),
                                            "tmp_avatar_" + String.valueOf(System.currentTimeMillis()) + ".jpg"));

                                    intent.putExtra(MediaStore.EXTRA_OUTPUT, mImageCaptureUri);
                                    encodedimageno="7";
                                    try {
                                        intent.putExtra("return-data", true);
                                        startActivityForResult(intent, PICK_FROM_CAMERA);
                                    } catch (ActivityNotFoundException e) {
                                        e.printStackTrace();
                                    }
                                }

                            });

                            build.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                }
                            });

                            build.setNegativeButton("Gallery", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which)
                                {
                                    Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                                            android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                                    encodedimageno="7";
                                    startActivityForResult(galleryIntent, PICK_FROM_FILE);
                                }
                            });
                            build.show();
                        }

                        else
                        {
                            View v3 = getLayoutInflater().inflate(R.layout.showdoc, null);
                            ImageView iv=(ImageView) v3.findViewById(R.id.imageView10);
                            Bitmap s=decodeBase64(encodedImage7);
                            iv.setImageBitmap(s);
                            ivLargeImage = (ImageViewTouch) v3.findViewById(R.id.ivLargeImageView);
                            ivLargeImage.setImageBitmapReset(s, 0, true);
                            AlertDialog.Builder b5 = new AlertDialog.Builder(MainActivity.this)
                                    .setCancelable(true)
                                    .setView(v3);

                            b6 = b5.create();
                            b6.show();

                            Button b10 = (Button) v3.findViewById(R.id.button7);
                            b10.setOnClickListener(new View.OnClickListener() {
                                @Override

                                public void onClick(View v) {
                                    b6.cancel();
                                }
                            });
                        }
                    }
                });

                t8.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(t8.getText().toString().equalsIgnoreCase("Upload"))
                        {
                            android.support.v7.app.AlertDialog.Builder build = new android.support.v7.app.AlertDialog.Builder(MainActivity.this);
                            build.setTitle("Upload Police Permit document image using-");

                            build.setPositiveButton("camera", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                                    mImageCaptureUri = Uri.fromFile(new File(Environment.getExternalStorageDirectory(),
                                            "tmp_avatar_" + String.valueOf(System.currentTimeMillis()) + ".jpg"));

                                    intent.putExtra(MediaStore.EXTRA_OUTPUT, mImageCaptureUri);
                                    encodedimageno="8";
                                    try {
                                        intent.putExtra("return-data", true);
                                        startActivityForResult(intent, PICK_FROM_CAMERA);
                                    } catch (ActivityNotFoundException e) {
                                        e.printStackTrace();
                                    }
                                }

                            });


                            build.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                }
                            });

                            build.setNegativeButton("Gallery", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which)
                                {
                                    Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                                            android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                                    encodedimageno="8";
                                    startActivityForResult(galleryIntent, PICK_FROM_FILE);
                                }
                            });
                            build.show();

                        }

                        else
                        {
                            View v3 = getLayoutInflater().inflate(R.layout.showdoc, null);
                            ImageView iv=(ImageView) v3.findViewById(R.id.imageView10);
                            Bitmap s=decodeBase64(encodedImage8);
                            iv.setImageBitmap(s);
                            ivLargeImage = (ImageViewTouch) v3.findViewById(R.id.ivLargeImageView);
                            ivLargeImage.setImageBitmapReset(s, 0, true);
                            AlertDialog.Builder b5 = new AlertDialog.Builder(MainActivity.this)
                                    .setCancelable(true)
                                    .setView(v3);

                            b6 = b5.create();
                            b6.show();

                            Button b10 = (Button) v3.findViewById(R.id.button7);
                            b10.setOnClickListener(new View.OnClickListener() {
                                @Override

                                public void onClick(View v) {
                                    b6.cancel();
                                }
                            });
                        }
                    }
                });

                t9.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(t9.getText().toString().equalsIgnoreCase("Upload"))
                        {
                            android.support.v7.app.AlertDialog.Builder build = new android.support.v7.app.AlertDialog.Builder(MainActivity.this);
                            build.setTitle("Upload Tinted Glass Permit document image using-");

                            build.setPositiveButton("camera", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                                    mImageCaptureUri = Uri.fromFile(new File(Environment.getExternalStorageDirectory(),
                                            "tmp_avatar_" + String.valueOf(System.currentTimeMillis()) + ".jpg"));

                                    intent.putExtra(MediaStore.EXTRA_OUTPUT, mImageCaptureUri);
                                    encodedimageno="9";
                                    try {
                                        intent.putExtra("return-data", true);
                                        startActivityForResult(intent, PICK_FROM_CAMERA);
                                    } catch (ActivityNotFoundException e) {
                                        e.printStackTrace();
                                    }
                                }

                            });

                            build.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                }
                            });

                            build.setNegativeButton("Gallery", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which)
                                {
                                    Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                                            android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                                    encodedimageno="9";
                                    startActivityForResult(galleryIntent, PICK_FROM_FILE);
                                }
                            });
                            build.show();
                        }

                        else
                        {
                            View v3 = getLayoutInflater().inflate(R.layout.showdoc, null);
                            ImageView iv=(ImageView) v3.findViewById(R.id.imageView10);
                            Bitmap s=decodeBase64(encodedImage9);
                            iv.setImageBitmap(s);
                            ivLargeImage = (ImageViewTouch) v3.findViewById(R.id.ivLargeImageView);
                            ivLargeImage.setImageBitmapReset(s, 0, true);
                            AlertDialog.Builder b5 = new AlertDialog.Builder(MainActivity.this)
                                    .setCancelable(true)
                                    .setView(v3);

                            b6 = b5.create();
                            b6.show();

                            Button b10 = (Button) v3.findViewById(R.id.button7);
                            b10.setOnClickListener(new View.OnClickListener() {
                                @Override

                                public void onClick(View v) {
                                    b6.cancel();
                                }
                            });
                        }
                    }
                });


                b10.setOnClickListener(new View.OnClickListener() {
                    @Override

                    public void onClick(View v) {
                        new JSONP3().execute();
                    }
                });

                b11.setOnClickListener(new View.OnClickListener() {
                    @Override

                    public void onClick(View v) {
                        b7.dismiss();
                    }
                });
            }
            else
            {
                Toast.makeText(MainActivity.this,s, Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode,resultCode,data);

        if (resultCode != RESULT_OK) return;

        switch (requestCode) {
            case PICK_FROM_CAMERA:
                //doCrop();
                String s = getRealPathFromURI(mImageCaptureUri);
                decodeFile(s);
                break;

            case PICK_FROM_FILE:
                mImageCaptureUri = data.getData();
                String s1 = getRealPathFromURI(mImageCaptureUri);
                //doCrop();
                decodeFile(s1);
                break;

            case CROP_FROM_CAMERA:
                Bundle extras = data.getExtras();

                if (extras != null) {
                    Bitmap photo = extras.getParcelable("data");

                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    photo.compress(Bitmap.CompressFormat.PNG, 100, baos);
                    b = baos.toByteArray();
                    encodedImage = Base64.encodeToString(b, Base64.URL_SAFE);

                    //ib.setImageBitmap(photo);
                    //new JSONP().execute(encodedImage);
                }

                File f = new File(mImageCaptureUri.getPath());
                if (f.exists()) f.delete();

                break;
        }

        if(encodedimageno.equalsIgnoreCase("1"))
        {
            encodedImage1=encodedImage;
            t1.setText("View Doc");
            t1.setTextColor(Color.GREEN);
        }
        else if(encodedimageno.equalsIgnoreCase("2"))
        {
            encodedImage2=encodedImage;
            t2.setText("View Doc");
            t2.setTextColor(Color.GREEN);
        }
        else if(encodedimageno.equalsIgnoreCase("3"))
        {
            encodedImage3=encodedImage;
            t3.setText("View Doc");
            t3.setTextColor(Color.GREEN);
        }
        else if(encodedimageno.equalsIgnoreCase("4"))
        {
            encodedImage4=encodedImage;
            t4.setText("View Doc");
            t4.setTextColor(Color.GREEN);
        }
        else if(encodedimageno.equalsIgnoreCase("5"))
        {
            encodedImage5=encodedImage;
            t5.setText("View Doc");
            t5.setTextColor(Color.GREEN);
        }
        else if(encodedimageno.equalsIgnoreCase("6"))
        {
            encodedImage6=encodedImage;
            t6.setText("View Doc");
            t6.setTextColor(Color.GREEN);
        }
        else if(encodedimageno.equalsIgnoreCase("7"))
        {
            encodedImage7=encodedImage;
            t7.setText("View Doc");
            t7.setTextColor(Color.GREEN);
        }
        else if(encodedimageno.equalsIgnoreCase("8"))
        {
            encodedImage8=encodedImage;
            t8.setText("View Doc");
            t8.setTextColor(Color.GREEN);
        }
        else if(encodedimageno.equalsIgnoreCase("9"))
        {
            encodedImage9=encodedImage;
            t9.setText("View Doc");
            t9.setTextColor(Color.GREEN);
        }

        else if(encodedimageno.equalsIgnoreCase("31"))
        {
            encodedImage31=encodedImage;

            View v3 = getLayoutInflater().inflate(R.layout.showdoc2, null);
            ImageView iv=(ImageView) v3.findViewById(R.id.imageView10);
            Bitmap s=decodeBase64(encodedImage31);
            iv.setImageBitmap(s);
            ivLargeImage = (ImageViewTouch) v3.findViewById(R.id.ivLargeImageView);
            ivLargeImage.setImageBitmapReset(s, 0, true);
            AlertDialog.Builder b5 = new AlertDialog.Builder(MainActivity.this)
                    .setCancelable(true)
                    .setView(v3);

            b6 = b5.create();
            b6.show();

            Button b10 = (Button) v3.findViewById(R.id.button7);

            b10.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    b6.cancel();
                }
            });

            Button b11 = (Button) v3.findViewById(R.id.button2);
            b11.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    docname="drivinglicense";
                    new JSONP9().execute();
                }
            });

        }
        else if(encodedimageno.equalsIgnoreCase("32"))
        {
            encodedImage32=encodedImage;
            View v3 = getLayoutInflater().inflate(R.layout.showdoc2, null);
            ImageView iv=(ImageView) v3.findViewById(R.id.imageView10);
            Bitmap s=decodeBase64(encodedImage32);
            iv.setImageBitmap(s);
            ivLargeImage = (ImageViewTouch) v3.findViewById(R.id.ivLargeImageView);
            ivLargeImage.setImageBitmapReset(s, 0, true);
            AlertDialog.Builder b5 = new AlertDialog.Builder(MainActivity.this)
                    .setCancelable(true)
                    .setView(v3);

            b6 = b5.create();
            b6.show();

            Button b10 = (Button) v3.findViewById(R.id.button7);
            b10.setOnClickListener(new View.OnClickListener() {
                @Override

                public void onClick(View v) {
                    b6.cancel();
                }
            });

            Button b11 = (Button) v3.findViewById(R.id.button2);
            b11.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    docname="tyreexpiration";
                    new JSONP9().execute();
                }
            });

        }
        else if(encodedimageno.equalsIgnoreCase("33"))
        {
            encodedImage33=encodedImage;
            View v3 = getLayoutInflater().inflate(R.layout.showdoc2, null);
            ImageView iv=(ImageView) v3.findViewById(R.id.imageView10);
            Bitmap s=decodeBase64(encodedImage33);
            iv.setImageBitmap(s);
            ivLargeImage = (ImageViewTouch) v3.findViewById(R.id.ivLargeImageView);
            ivLargeImage.setImageBitmapReset(s, 0, true);
            AlertDialog.Builder b5 = new AlertDialog.Builder(MainActivity.this)
                    .setCancelable(true)
                    .setView(v3);

            b6 = b5.create();
            b6.show();

            Button b10 = (Button) v3.findViewById(R.id.button7);
            b10.setOnClickListener(new View.OnClickListener() {
                @Override

                public void onClick(View v) {
                    b6.cancel();
                }
            });

            Button b11 = (Button) v3.findViewById(R.id.button2);
            b11.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    docname="fireextinguisher";
                    new JSONP9().execute();
                }
            });

        }
        else if(encodedimageno.equalsIgnoreCase("34"))
        {
            encodedImage34=encodedImage;
            View v3 = getLayoutInflater().inflate(R.layout.showdoc2, null);
            ImageView iv=(ImageView) v3.findViewById(R.id.imageView10);
            Bitmap s=decodeBase64(encodedImage34);
            iv.setImageBitmap(s);
            ivLargeImage = (ImageViewTouch) v3.findViewById(R.id.ivLargeImageView);
            ivLargeImage.setImageBitmapReset(s, 0, true);
            AlertDialog.Builder b5 = new AlertDialog.Builder(MainActivity.this)
                    .setCancelable(true)
                    .setView(v3);

            b6 = b5.create();
            b6.show();

            Button b10 = (Button) v3.findViewById(R.id.button7);
            b10.setOnClickListener(new View.OnClickListener() {
                @Override

                public void onClick(View v) {
                    b6.cancel();
                }
            });

            Button b11 = (Button) v3.findViewById(R.id.button2);
            b11.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    docname="viodoc";
                    new JSONP9().execute();
                }
            });

        }
        else if(encodedimageno.equalsIgnoreCase("35"))
        {
            encodedImage35=encodedImage;
            View v3 = getLayoutInflater().inflate(R.layout.showdoc2, null);
            ImageView iv=(ImageView) v3.findViewById(R.id.imageView10);
            Bitmap s=decodeBase64(encodedImage35);
            iv.setImageBitmap(s);
            ivLargeImage = (ImageViewTouch) v3.findViewById(R.id.ivLargeImageView);
            ivLargeImage.setImageBitmapReset(s, 0, true);
            AlertDialog.Builder b5 = new AlertDialog.Builder(MainActivity.this)
                    .setCancelable(true)
                    .setView(v3);

            b6 = b5.create();
            b6.show();

            Button b10 = (Button) v3.findViewById(R.id.button7);
            b10.setOnClickListener(new View.OnClickListener() {
                @Override

                public void onClick(View v) {
                    b6.cancel();
                }
            });

            Button b11 = (Button) v3.findViewById(R.id.button2);
            b11.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    docname="insurance";
                    new JSONP9().execute();
                }
            });

        }
        else if(encodedimageno.equalsIgnoreCase("36"))
        {
            encodedImage36=encodedImage;
            View v3 = getLayoutInflater().inflate(R.layout.showdoc2, null);
            ImageView iv=(ImageView) v3.findViewById(R.id.imageView10);
            Bitmap s=decodeBase64(encodedImage36);
            iv.setImageBitmap(s);
            ivLargeImage = (ImageViewTouch) v3.findViewById(R.id.ivLargeImageView);
            ivLargeImage.setImageBitmapReset(s, 0, true);
            AlertDialog.Builder b5 = new AlertDialog.Builder(MainActivity.this)
                    .setCancelable(true)
                    .setView(v3);

            b6 = b5.create();
            b6.show();

            Button b10 = (Button) v3.findViewById(R.id.button7);
            b10.setOnClickListener(new View.OnClickListener() {
                @Override

                public void onClick(View v) {
                    b6.cancel();
                }
            });

            Button b11 = (Button) v3.findViewById(R.id.button2);
            b11.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    docname="hackneypermit";
                    new JSONP9().execute();
                }
            });

        }
        else if(encodedimageno.equalsIgnoreCase("37"))
        {
            encodedImage37=encodedImage;
            View v3 = getLayoutInflater().inflate(R.layout.showdoc2, null);
            ImageView iv=(ImageView) v3.findViewById(R.id.imageView10);
            Bitmap s=decodeBase64(encodedImage37);
            iv.setImageBitmap(s);
            ivLargeImage = (ImageViewTouch) v3.findViewById(R.id.ivLargeImageView);
            ivLargeImage.setImageBitmapReset(s, 0, true);
            AlertDialog.Builder b5 = new AlertDialog.Builder(MainActivity.this)
                    .setCancelable(true)
                    .setView(v3);

            b6 = b5.create();
            b6.show();

            Button b10 = (Button) v3.findViewById(R.id.button7);
            b10.setOnClickListener(new View.OnClickListener() {
                @Override

                public void onClick(View v) {
                    b6.cancel();
                }
            });

            Button b11 = (Button) v3.findViewById(R.id.button2);
            b11.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    docname="ownershipproof";
                    new JSONP9().execute();
                }
            });

        }
        else if(encodedimageno.equalsIgnoreCase("38"))
        {
            encodedImage38=encodedImage;
            View v3 = getLayoutInflater().inflate(R.layout.showdoc2, null);
            ImageView iv=(ImageView) v3.findViewById(R.id.imageView10);
            Bitmap s=decodeBase64(encodedImage38);
            iv.setImageBitmap(s);
            ivLargeImage = (ImageViewTouch) v3.findViewById(R.id.ivLargeImageView);
            ivLargeImage.setImageBitmapReset(s, 0, true);
            AlertDialog.Builder b5 = new AlertDialog.Builder(MainActivity.this)
                    .setCancelable(true)
                    .setView(v3);

            b6 = b5.create();
            b6.show();

            Button b10 = (Button) v3.findViewById(R.id.button7);
            b10.setOnClickListener(new View.OnClickListener() {
                @Override

                public void onClick(View v) {
                    b6.cancel();
                }
            });

            Button b11 = (Button) v3.findViewById(R.id.button2);
            b11.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    docname="policepermit";
                    new JSONP9().execute();
                }
            });

        }
        else if(encodedimageno.equalsIgnoreCase("39"))
        {
            encodedImage39=encodedImage;
            View v3 = getLayoutInflater().inflate(R.layout.showdoc2, null);
            ImageView iv=(ImageView) v3.findViewById(R.id.imageView10);
            Bitmap s=decodeBase64(encodedImage39);
            iv.setImageBitmap(s);
            ivLargeImage = (ImageViewTouch) v3.findViewById(R.id.ivLargeImageView);
            ivLargeImage.setImageBitmapReset(s, 0, true);
            AlertDialog.Builder b5 = new AlertDialog.Builder(MainActivity.this)
                    .setCancelable(true)
                    .setView(v3);

            b6 = b5.create();
            b6.show();

            Button b10 = (Button) v3.findViewById(R.id.button7);
            b10.setOnClickListener(new View.OnClickListener() {
                @Override

                public void onClick(View v) {
                    b6.cancel();
                }
            });

            Button b11 = (Button) v3.findViewById(R.id.button2);
            b11.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    docname="tintedglasspermit";
                    new JSONP9().execute();
                }
            });

        }
        else
        {
            profilepic=encodedImage;
            Bitmap s=decodeBase64(encodedImage);
            ib.setImageBitmap(s);
        }
    }

    public void decodeFile(String filePath) {
        // Decode image size
        BitmapFactory.Options o = new BitmapFactory.Options();
        o.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(filePath, o);

        // The new size we want to scale to
        final int REQUIRED_SIZE = 1024;

        // Find the correct scale value. It should be the power of 2.
        int width_tmp = o.outWidth, height_tmp = o.outHeight;
        int scale = 1;
        while (true) {
            if (width_tmp < REQUIRED_SIZE && height_tmp < REQUIRED_SIZE)
                break;
            width_tmp /= 2;
            height_tmp /= 2;
            scale *= 2;
        }

        // Decode with inSampleSize
        BitmapFactory.Options o2 = new BitmapFactory.Options();
        o2.inSampleSize = scale;
        bitm = BitmapFactory.decodeFile(filePath, o2);

        //ib.setImageBitmap(bitm);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitm.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        b = baos.toByteArray();
        encodedImage = Base64.encodeToString(b, Base64.DEFAULT);

    }

    public String getRealPathFromURI(Uri uri) {

        if (uri == null)
        {
            Toast.makeText(MainActivity.this, "Image not selected", Toast.LENGTH_LONG).show();
            // TODO perform some logging or show user feedback
            return null;
        }
        // try to retrieve the image from the media store first
        // this will only work for images selected from gallery
        String[] projection = {MediaStore.Images.Media.DATA};
        Cursor cursor = managedQuery(uri, projection, null, null, null);
        if (cursor != null) {
            int column_index = cursor
                    .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        }
        // this is our fallback here
        return uri.getPath();
    }

    public  Bitmap decodeBase64(String input)
    {
        byte[] decodedBytes = Base64.decode(input, 0);
        return BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.length);
    }

    class JSONP3 extends AsyncTask<Void,Void,String> {
        String jsonurl, jsonstring;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            jsonurl = "http://bjprogrammer.co.nf/nigeriantraffic/uploaddoc.php";
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
                String postdata = URLEncoder.encode("drivinglicense") + "=" + URLEncoder.encode(encodedImage1) + "&" + URLEncoder.encode("tyreexpiration") + "=" + URLEncoder.encode(encodedImage2)
                        + "&" + URLEncoder.encode("fireextinguisher") + "=" + URLEncoder.encode(encodedImage3)+ "&" + URLEncoder.encode("viodocument") + "=" + URLEncoder.encode(encodedImage4)
                        + "&" + URLEncoder.encode("insurance") + "=" + URLEncoder.encode(encodedImage5)+ "&" + URLEncoder.encode("hackneypermit") + "=" + URLEncoder.encode(encodedImage6)
                        + "&" + URLEncoder.encode("ownershipproof") + "=" + URLEncoder.encode(encodedImage7)+ "&" + URLEncoder.encode("policepermit") + "=" + URLEncoder.encode(encodedImage8)
                        + "&" + URLEncoder.encode("tintedglasspermit") + "=" + URLEncoder.encode(encodedImage9)+ "&" + URLEncoder.encode("username") + "=" + URLEncoder.encode(username);

                bufferedWriter.write(postdata);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();

                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder stringBuilder = new StringBuilder();
                while ((jsonstring = bufferedReader.readLine()) != null) {
                    stringBuilder.append(jsonstring + "\n");
                }

                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();

                data = stringBuilder.toString();
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

           if(s.contains("successfully"))
           {
               Toast.makeText(MainActivity.this, s, Toast.LENGTH_LONG).show();
               b7.dismiss();
           }
            else
           {
               Toast.makeText(MainActivity.this, s, Toast.LENGTH_LONG).show();
           }
        }
    }

    @Override
    public void onDrawerItemSelected(View view, int position) {
        displayView(position);
    }

    private void displayView(int position) {
        Fragment fragment = null;
        String title = getString(R.string.app_name);
        switch (position) {
            case 1:
                final View v7 = getLayoutInflater().inflate(R.layout.updateprofile, null);

                AlertDialog.Builder b20 = new AlertDialog.Builder(MainActivity.this)
                        .setCancelable(false)
                        .setView(v7);

                b4 = b20.create();
                b4.show();

                Button b21 = (Button) v7.findViewById(R.id.button5);
                Button b22=(Button)  v7.findViewById(R.id.button9);
                ib=(ImageButton) v7.findViewById(R.id.imageButton);


                b21.setOnClickListener(new View.OnClickListener() {
                    @Override

                    public void onClick(View v) {

                        EditText t1=(EditText) v7.findViewById(R.id.editText4);
                        EditText t2=(EditText)v7.findViewById(R.id.editText5);

                        if(t1.getText().toString().isEmpty() && t2.getText().toString().isEmpty())
                        {
                            Toast.makeText(MainActivity.this, "Please fill this form in order to continue.", Toast.LENGTH_SHORT).show();
                        }
                        else if(!t1.getText().toString().contains("@") || !t1.getText().toString().contains("."))
                        {
                            Toast.makeText(MainActivity.this, "Please check your email id.", Toast.LENGTH_SHORT).show();
                        }
                        else if(t2.getText().toString().length()!=10)
                        {
                            Toast.makeText(MainActivity.this, "Please check your mobile no", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            email1=t1.getText().toString();
                            mobile=t2.getText().toString();
                            new JSONP6().execute();
                        }
                    }
                });

                b22.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        b4.dismiss();
                    }
                });

                ib.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        android.support.v7.app.AlertDialog.Builder build = new android.support.v7.app.AlertDialog.Builder(MainActivity.this);
                        build.setTitle("Upload profile image using-");

                        build.setPositiveButton("camera", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                                mImageCaptureUri = Uri.fromFile(new File(Environment.getExternalStorageDirectory(),
                                        "tmp_avatar_" + String.valueOf(System.currentTimeMillis()) + ".jpg"));

                                intent.putExtra(MediaStore.EXTRA_OUTPUT, mImageCaptureUri);

                                try
                                {
                                    intent.putExtra("return-data", true);
                                    startActivityForResult(intent, PICK_FROM_CAMERA);
                                }
                                catch (ActivityNotFoundException e)
                                {
                                    e.printStackTrace();
                                }
                            }

                        });


                        build.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });

                        build.setNegativeButton("Gallery", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which)
                            {
                                Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                                startActivityForResult(galleryIntent, PICK_FROM_FILE);
                            }
                        });
                        build.show();
                    }
                });

                break;

            case 2:
                final View v2 = getLayoutInflater().inflate(R.layout.language, null);

                AlertDialog.Builder b5 = new AlertDialog.Builder(MainActivity.this)
                        .setCancelable(false)
                        .setView(v2);

                b4 = b5.create();
                b4.show();

                Button b11 = (Button) v2.findViewById(R.id.button7);
                Button b12=(Button)  v2.findViewById(R.id.button6);

                b11.setOnClickListener(new View.OnClickListener() {
                    @Override

                    public void onClick(View v) {

                        RadioGroup rg=(RadioGroup)v2.findViewById(R.id.radioGroup);
                        int id=rg.getCheckedRadioButtonId();
                        RadioButton r1=(RadioButton)v2.findViewById(id);
                        language=r1.getText().toString();

                        if(!r1.getText().toString().isEmpty())
                        {
                            new JSONP4().execute();
                        }
                        else
                        {
                            Toast.makeText(MainActivity.this, "Please fill this form in order to continue.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                b12.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View view) {
                        b4.dismiss();
                    }
                });
                break;

            case 3:
                final View v4 = getLayoutInflater().inflate(R.layout.alert, null);
                AlertDialog.Builder b3 = new AlertDialog.Builder(MainActivity.this)
                        .setCancelable(false)
                        .setView(v4);

                b4 = b3.create();
                b4.show();

                Button b10 = (Button) v4.findViewById(R.id.button7);
                Button b13=(Button)  v4.findViewById(R.id.button8);

                b10.setOnClickListener(new View.OnClickListener() {
                    @Override

                    public void onClick(View v) {
                        CheckBox c1 = (CheckBox) v4.findViewById(R.id.checkBox);
                        CheckBox c2 = (CheckBox) v4.findViewById(R.id.checkBox2);
                        CheckBox c3 = (CheckBox) v4.findViewById(R.id.checkBox3);
                        CheckBox c4 = (CheckBox) v4.findViewById(R.id.checkBox4);
                        CheckBox c5 = (CheckBox) v4.findViewById(R.id.checkBox5);

                        if (c1.isChecked())
                        {
                            App="yes";
                        }
                        if (c2.isChecked())
                        {
                            email="yes";
                        }
                        if (c3.isChecked())
                        {
                            Voicecall="yes";
                        }
                        if (c4.isChecked())
                        {
                            Bypost="yes";
                        }
                        if (c5.isChecked())
                        {
                            SMS="yes";
                        }

                        if((Voicecall.equalsIgnoreCase("yes") || SMS.equalsIgnoreCase("yes") || Bypost.equalsIgnoreCase("yes") ||App.equalsIgnoreCase("yes") || email.equalsIgnoreCase("yes")))
                        {
                            new JSONP5().execute();
                        }
                        else
                        {
                            Toast.makeText(MainActivity.this, "Please fill this form in order to continue.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                b13.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        b4.dismiss();
                    }
                });

                break;
            case 4:

                Intent intent =new Intent(MainActivity.this,tickets.class);
                intent.putExtra("username",username);
                startActivity(intent);
                break;

            case 5:
                DatabaseHelper dh=new DatabaseHelper(MainActivity.this);
                if(SplashScreen.logout==true)
                {
                    SplashScreen.logout=false;
                    dh.deletedata(1);
                }
                Intent i=new Intent(MainActivity.this, Signin.class);
                startActivity(i);
                finish();
                break;

            default:
                break;
        }

        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container_body, fragment);
            fragmentTransaction.commit();

            // set the toolbar title
            getSupportActionBar().setTitle(title);
        }
    }


    public class  JSONP4 extends AsyncTask<Void,Void,String> {
        String jsonurl, jsonstring;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            jsonurl = "http://bjprogrammer.co.nf/nigeriantraffic/language.php";

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
                String postdata = URLEncoder.encode("username") + "=" + URLEncoder.encode(username) + "&" + URLEncoder.encode("language") + "=" + URLEncoder.encode(language);

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

            if(s.contains("adjusted")) {
                b4.dismiss();
                Toast.makeText(MainActivity.this,s, Toast.LENGTH_LONG).show();
            }
            else
            {
                Toast.makeText(MainActivity.this,s, Toast.LENGTH_LONG).show();
            }
        }
    }

    public class  JSONP5 extends AsyncTask<Void,Void,String> {
        String jsonurl, jsonstring;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            jsonurl = "http://bjprogrammer.co.nf/nigeriantraffic/alert.php";
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
                String postdata = URLEncoder.encode("username") + "=" + URLEncoder.encode(username) + "&" + URLEncoder.encode("sms") + "=" + URLEncoder.encode(SMS)
                        + "&" + URLEncoder.encode("voicecall") + "=" + URLEncoder.encode(Voicecall)+ "&" + URLEncoder.encode("byemail") + "=" + URLEncoder.encode(email)
                        + "&" + URLEncoder.encode("app") + "=" + URLEncoder.encode(App)+ "&" + URLEncoder.encode("bypost") + "=" + URLEncoder.encode(Bypost);

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

            if (s.contains("adjusted"))
            {
                b4.dismiss();
                Toast.makeText(MainActivity.this, s, Toast.LENGTH_LONG).show();
            }
            else
            {
                Toast.makeText(MainActivity.this, s, Toast.LENGTH_LONG).show();
            }
        }
    }

        class JSONP6 extends AsyncTask<Void,Void,String> {
            String jsonurl, jsonstring;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                jsonurl ="http://bjprogrammer.co.nf/nigeriantraffic/updateprofile.php";

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
                    String postdata = URLEncoder.encode("username") + "=" + URLEncoder.encode(username) + "&" + URLEncoder.encode("mobile") + "=" + URLEncoder.encode(mobile)
                            + "&" + URLEncoder.encode("email") + "=" + URLEncoder.encode(email1)+ "&" + URLEncoder.encode("pic") + "=" + URLEncoder.encode(profilepic);

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

                if(s.contains("updated"))
                {
                    Toast.makeText(MainActivity.this,s, Toast.LENGTH_LONG).show();
                    b4.dismiss();
                    DatabaseHelper dh=new DatabaseHelper(MainActivity.this);
                    if(SplashScreen.logout==true)
                    {
                        SplashScreen.logout=false;
                        dh.deletedata(1);
                    }
                    Intent i=new Intent(MainActivity.this, Signin.class);
                    startActivity(i);
                    finish();
                }
                else
                {
                    Toast.makeText(MainActivity.this,s, Toast.LENGTH_LONG).show();
                }
            }
        }

    @Override
    protected void onResume() {
        super.onResume();
        TextView t11=(TextView) findViewById(R.id.textView49);
        t11.setText("Note-\n1.)In order to take full advantage of this app,we recommend you to upload all the documents mentioned above. In case you have already uploaded documents,please be patient as it takes around a week in verification and  reflecting updated status of your documents over here.\n2.)In case any of your document is rejected for some reason, you will be notified through alert means set by you.You can again upload that document.\n3.)If your document is about to expire, you can upload a new one here.It will again go for verification");
        iv=(ImageView) findViewById(R.id.imageView11);
        t10=(TextView) findViewById(R.id.textView35);
        t10.setText(username);

        new  JSONP8().execute();


        t31.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(t31.getText().toString().equalsIgnoreCase("Upload"))
                {
                    android.support.v7.app.AlertDialog.Builder build = new android.support.v7.app.AlertDialog.Builder(MainActivity.this);
                    build.setTitle("Upload Driving License image using-");

                    build.setPositiveButton("camera", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                            mImageCaptureUri = Uri.fromFile(new File(Environment.getExternalStorageDirectory(),
                                    "tmp_avatar_" + String.valueOf(System.currentTimeMillis()) + ".jpg"));

                            intent.putExtra(MediaStore.EXTRA_OUTPUT, mImageCaptureUri);
                            encodedimageno="31";
                            try {
                                intent.putExtra("return-data", true);
                                startActivityForResult(intent, PICK_FROM_CAMERA);
                            } catch (ActivityNotFoundException e) {
                                e.printStackTrace();
                            }
                        }

                    });


                    build.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });

                    build.setNegativeButton("Gallery", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which)
                        {
                            Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                                    android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                            encodedimageno="31";
                            startActivityForResult(galleryIntent, PICK_FROM_FILE);
                        }
                    });
                    build.show();

                }

                else
                {
                    View v3 = getLayoutInflater().inflate(R.layout.showdoc, null);
                    ImageView iv=(ImageView) v3.findViewById(R.id.imageView10);
                    Bitmap s=decodeBase64(drivinglicensepic);
                    iv.setImageBitmap(s);
                    ivLargeImage = (ImageViewTouch) v3.findViewById(R.id.ivLargeImageView);
                    ivLargeImage.setImageBitmapReset(s, 0, true);
                    AlertDialog.Builder b5 = new AlertDialog.Builder(MainActivity.this)
                            .setCancelable(true)
                            .setView(v3);

                    b6 = b5.create();
                    b6.show();

                    Button b10 = (Button) v3.findViewById(R.id.button7);
                    b10.setOnClickListener(new View.OnClickListener() {
                        @Override

                        public void onClick(View v) {
                            b6.cancel();
                        }
                    });
                }
            }
        });


        t32.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(t32.getText().toString().equalsIgnoreCase("Upload"))
                {
                    android.support.v7.app.AlertDialog.Builder build = new android.support.v7.app.AlertDialog.Builder(MainActivity.this);
                    build.setTitle("Upload Tyre Expiration document image using-");

                    build.setPositiveButton("camera", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                            mImageCaptureUri = Uri.fromFile(new File(Environment.getExternalStorageDirectory(),
                                    "tmp_avatar_" + String.valueOf(System.currentTimeMillis()) + ".jpg"));

                            intent.putExtra(MediaStore.EXTRA_OUTPUT, mImageCaptureUri);
                            encodedimageno="32";
                            try {
                                intent.putExtra("return-data", true);
                                startActivityForResult(intent, PICK_FROM_CAMERA);
                            } catch (ActivityNotFoundException e) {
                                e.printStackTrace();
                            }
                        }

                    });


                    build.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });

                    build.setNegativeButton("Gallery", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which)
                        {
                            Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                                    android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                            encodedimageno="32";
                            startActivityForResult(galleryIntent, PICK_FROM_FILE);
                        }
                    });
                    build.show();

                }

                else
                {
                    View v3 = getLayoutInflater().inflate(R.layout.showdoc, null);
                    ImageView iv=(ImageView) v3.findViewById(R.id.imageView10);
                    Bitmap s=decodeBase64(tyreexpirationpic);
                    iv.setImageBitmap(s);
                    ivLargeImage = (ImageViewTouch) v3.findViewById(R.id.ivLargeImageView);
                    ivLargeImage.setImageBitmapReset(s, 0, true);
                    AlertDialog.Builder b5 = new AlertDialog.Builder(MainActivity.this)
                            .setCancelable(true)
                            .setView(v3);

                    b6 = b5.create();
                    b6.show();

                    Button b10 = (Button) v3.findViewById(R.id.button7);
                    b10.setOnClickListener(new View.OnClickListener() {
                        @Override

                        public void onClick(View v) {
                            b6.cancel();
                        }
                    });
                }
            }
        });

        t33.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(t33.getText().toString().equalsIgnoreCase("Upload"))
                {
                    android.support.v7.app.AlertDialog.Builder build = new android.support.v7.app.AlertDialog.Builder(MainActivity.this);
                    build.setTitle("Upload Fire Extinguisher document image using-");

                    build.setPositiveButton("camera", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                            mImageCaptureUri = Uri.fromFile(new File(Environment.getExternalStorageDirectory(),
                                    "tmp_avatar_" + String.valueOf(System.currentTimeMillis()) + ".jpg"));

                            intent.putExtra(MediaStore.EXTRA_OUTPUT, mImageCaptureUri);
                            encodedimageno="33";
                            try {
                                intent.putExtra("return-data", true);
                                startActivityForResult(intent, PICK_FROM_CAMERA);
                            } catch (ActivityNotFoundException e) {
                                e.printStackTrace();
                            }
                        }

                    });


                    build.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });

                    build.setNegativeButton("Gallery", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which)
                        {
                            Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                                    android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                            encodedimageno="33";
                            startActivityForResult(galleryIntent, PICK_FROM_FILE);
                        }
                    });
                    build.show();
                }

                else
                {
                    View v3 = getLayoutInflater().inflate(R.layout.showdoc, null);
                    ImageView iv=(ImageView) v3.findViewById(R.id.imageView10);
                    Bitmap s=decodeBase64(fireextinguisherpic);
                    iv.setImageBitmap(s);
                    ivLargeImage = (ImageViewTouch) v3.findViewById(R.id.ivLargeImageView);
                    ivLargeImage.setImageBitmapReset(s, 0, true);
                    AlertDialog.Builder b5 = new AlertDialog.Builder(MainActivity.this)
                            .setCancelable(true)
                            .setView(v3);

                    b6 = b5.create();
                    b6.show();

                    Button b10 = (Button) v3.findViewById(R.id.button7);
                    b10.setOnClickListener(new View.OnClickListener() {
                        @Override

                        public void onClick(View v) {
                            b6.cancel();
                        }
                    });
                }
            }
        });

        t34.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(t34.getText().toString().equalsIgnoreCase("Upload"))
                {
                    android.support.v7.app.AlertDialog.Builder build = new android.support.v7.app.AlertDialog.Builder(MainActivity.this);
                    build.setTitle("Upload VIO document image using-");

                    build.setPositiveButton("camera", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                            mImageCaptureUri = Uri.fromFile(new File(Environment.getExternalStorageDirectory(),
                                    "tmp_avatar_" + String.valueOf(System.currentTimeMillis()) + ".jpg"));

                            intent.putExtra(MediaStore.EXTRA_OUTPUT, mImageCaptureUri);
                            encodedimageno="34";
                            try {
                                intent.putExtra("return-data", true);
                                startActivityForResult(intent, PICK_FROM_CAMERA);
                            } catch (ActivityNotFoundException e) {
                                e.printStackTrace();
                            }
                        }

                    });


                    build.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });

                    build.setNegativeButton("Gallery", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which)
                        {
                            Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                                    android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                            encodedimageno="34";
                            startActivityForResult(galleryIntent, PICK_FROM_FILE);
                        }
                    });
                    build.show();

                }
                else
                {
                    View v3 = getLayoutInflater().inflate(R.layout.showdoc, null);
                    ImageView iv=(ImageView) v3.findViewById(R.id.imageView10);
                    Bitmap s=decodeBase64(viopic);
                    iv.setImageBitmap(s);
                    ivLargeImage = (ImageViewTouch) v3.findViewById(R.id.ivLargeImageView);
                    ivLargeImage.setImageBitmapReset(s, 0, true);
                    AlertDialog.Builder b5 = new AlertDialog.Builder(MainActivity.this)
                            .setCancelable(true)
                            .setView(v3);

                    b6 = b5.create();
                    b6.show();

                    Button b10 = (Button) v3.findViewById(R.id.button7);
                    b10.setOnClickListener(new View.OnClickListener() {
                        @Override

                        public void onClick(View v) {
                            b6.cancel();
                        }
                    });
                }
            }
        });

        t35.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(t5.getText().toString().equalsIgnoreCase("Upload"))
                {
                    android.support.v7.app.AlertDialog.Builder build = new android.support.v7.app.AlertDialog.Builder(MainActivity.this);
                    build.setTitle("Upload Insurance document image using-");

                    build.setPositiveButton("camera", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                            mImageCaptureUri = Uri.fromFile(new File(Environment.getExternalStorageDirectory(),
                                    "tmp_avatar_" + String.valueOf(System.currentTimeMillis()) + ".jpg"));

                            intent.putExtra(MediaStore.EXTRA_OUTPUT, mImageCaptureUri);
                            encodedimageno="35";
                            try {
                                intent.putExtra("return-data", true);
                                startActivityForResult(intent, PICK_FROM_CAMERA);
                            } catch (ActivityNotFoundException e) {
                                e.printStackTrace();
                            }
                        }

                    });


                    build.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });

                    build.setNegativeButton("Gallery", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which)
                        {
                            Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                                    android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                            encodedimageno="35";
                            startActivityForResult(galleryIntent, PICK_FROM_FILE);
                        }
                    });
                    build.show();

                }

                else
                {
                    View v3 = getLayoutInflater().inflate(R.layout.showdoc, null);
                    ImageView iv=(ImageView) v3.findViewById(R.id.imageView10);
                    Bitmap s=decodeBase64(insurancepic);
                    iv.setImageBitmap(s);
                    ivLargeImage = (ImageViewTouch) v3.findViewById(R.id.ivLargeImageView);
                    ivLargeImage.setImageBitmapReset(s, 0, true);
                    AlertDialog.Builder b5 = new AlertDialog.Builder(MainActivity.this)
                            .setCancelable(true)
                            .setView(v3);

                    b6 = b5.create();
                    b6.show();

                    Button b10 = (Button) v3.findViewById(R.id.button7);
                    b10.setOnClickListener(new View.OnClickListener() {
                        @Override

                        public void onClick(View v) {
                            b6.cancel();
                        }
                    });
                }
            }
        });

        t36.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(t36.getText().toString().equalsIgnoreCase("Upload"))
                {
                    android.support.v7.app.AlertDialog.Builder build = new android.support.v7.app.AlertDialog.Builder(MainActivity.this);
                    build.setTitle("Upload Hackney Permit document image using-");

                    build.setPositiveButton("camera", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                            mImageCaptureUri = Uri.fromFile(new File(Environment.getExternalStorageDirectory(),
                                    "tmp_avatar_" + String.valueOf(System.currentTimeMillis()) + ".jpg"));

                            intent.putExtra(MediaStore.EXTRA_OUTPUT, mImageCaptureUri);
                            encodedimageno="36";
                            try {
                                intent.putExtra("return-data", true);
                                startActivityForResult(intent, PICK_FROM_CAMERA);
                            } catch (ActivityNotFoundException e) {
                                e.printStackTrace();
                            }
                        }

                    });


                    build.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });

                    build.setNegativeButton("Gallery", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which)
                        {
                            Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                                    android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                            encodedimageno="36";
                            startActivityForResult(galleryIntent, PICK_FROM_FILE);
                        }
                    });
                    build.show();

                }

                else
                {
                    View v3 = getLayoutInflater().inflate(R.layout.showdoc, null);
                    ImageView iv=(ImageView) v3.findViewById(R.id.imageView10);
                    Bitmap s=decodeBase64(hackneypermitpic);
                    iv.setImageBitmap(s);
                    ivLargeImage = (ImageViewTouch) v3.findViewById(R.id.ivLargeImageView);
                    ivLargeImage.setImageBitmapReset(s, 0, true);
                    AlertDialog.Builder b5 = new AlertDialog.Builder(MainActivity.this)
                            .setCancelable(true)
                            .setView(v3);

                    b6 = b5.create();
                    b6.show();

                    Button b10 = (Button) v3.findViewById(R.id.button7);
                    b10.setOnClickListener(new View.OnClickListener() {
                        @Override

                        public void onClick(View v) {
                            b6.cancel();
                        }
                    });
                }
            }
        });

        t37.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(t37.getText().toString().equalsIgnoreCase("Upload"))
                {
                    android.support.v7.app.AlertDialog.Builder build = new android.support.v7.app.AlertDialog.Builder(MainActivity.this);
                    build.setTitle("Upload Ownership Proof document image using-");

                    build.setPositiveButton("camera", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                            mImageCaptureUri = Uri.fromFile(new File(Environment.getExternalStorageDirectory(),
                                    "tmp_avatar_" + String.valueOf(System.currentTimeMillis()) + ".jpg"));

                            intent.putExtra(MediaStore.EXTRA_OUTPUT, mImageCaptureUri);
                            encodedimageno="37";
                            try {
                                intent.putExtra("return-data", true);
                                startActivityForResult(intent, PICK_FROM_CAMERA);
                            } catch (ActivityNotFoundException e) {
                                e.printStackTrace();
                            }
                        }

                    });

                    build.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });

                    build.setNegativeButton("Gallery", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which)
                        {
                            Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                                    android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                            encodedimageno="37";
                            startActivityForResult(galleryIntent, PICK_FROM_FILE);
                        }
                    });
                    build.show();
                }

                else
                {
                    View v3 = getLayoutInflater().inflate(R.layout.showdoc, null);
                    ImageView iv=(ImageView) v3.findViewById(R.id.imageView10);
                    Bitmap s=decodeBase64(ownershipproofpic);
                    iv.setImageBitmap(s);
                    ivLargeImage = (ImageViewTouch) v3.findViewById(R.id.ivLargeImageView);
                    ivLargeImage.setImageBitmapReset(s, 0, true);
                    AlertDialog.Builder b5 = new AlertDialog.Builder(MainActivity.this)
                            .setCancelable(true)
                            .setView(v3);

                    b6 = b5.create();
                    b6.show();

                    Button b10 = (Button) v3.findViewById(R.id.button7);
                    b10.setOnClickListener(new View.OnClickListener() {
                        @Override

                        public void onClick(View v) {
                            b6.cancel();
                        }
                    });
                }
            }
        });

        t38.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(t38.getText().toString().equalsIgnoreCase("Upload"))
                {
                    android.support.v7.app.AlertDialog.Builder build = new android.support.v7.app.AlertDialog.Builder(MainActivity.this);
                    build.setTitle("Upload Police Permit document image using-");

                    build.setPositiveButton("camera", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                            mImageCaptureUri = Uri.fromFile(new File(Environment.getExternalStorageDirectory(),
                                    "tmp_avatar_" + String.valueOf(System.currentTimeMillis()) + ".jpg"));

                            intent.putExtra(MediaStore.EXTRA_OUTPUT, mImageCaptureUri);
                            encodedimageno="38";
                            try {
                                intent.putExtra("return-data", true);
                                startActivityForResult(intent, PICK_FROM_CAMERA);
                            } catch (ActivityNotFoundException e) {
                                e.printStackTrace();
                            }
                        }

                    });


                    build.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });

                    build.setNegativeButton("Gallery", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which)
                        {
                            Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                                    android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                            encodedimageno="38";
                            startActivityForResult(galleryIntent, PICK_FROM_FILE);
                        }
                    });
                    build.show();

                }

                else
                {
                    View v3 = getLayoutInflater().inflate(R.layout.showdoc, null);
                    ImageView iv=(ImageView) v3.findViewById(R.id.imageView10);
                    Bitmap s=decodeBase64(policepermitpic);
                    iv.setImageBitmap(s);
                    ivLargeImage = (ImageViewTouch) v3.findViewById(R.id.ivLargeImageView);
                    ivLargeImage.setImageBitmapReset(s, 0, true);
                    AlertDialog.Builder b5 = new AlertDialog.Builder(MainActivity.this)
                            .setCancelable(true)
                            .setView(v3);

                    b6 = b5.create();
                    b6.show();

                    Button b10 = (Button) v3.findViewById(R.id.button7);
                    b10.setOnClickListener(new View.OnClickListener() {
                        @Override

                        public void onClick(View v) {
                            b6.cancel();
                        }
                    });
                }
            }
        });

        t39.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(t39.getText().toString().equalsIgnoreCase("Upload"))
                {
                    android.support.v7.app.AlertDialog.Builder build = new android.support.v7.app.AlertDialog.Builder(MainActivity.this);
                    build.setTitle("Upload Tinted Glass Permit document image using-");

                    build.setPositiveButton("camera", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                            mImageCaptureUri = Uri.fromFile(new File(Environment.getExternalStorageDirectory(),
                                    "tmp_avatar_" + String.valueOf(System.currentTimeMillis()) + ".jpg"));

                            intent.putExtra(MediaStore.EXTRA_OUTPUT, mImageCaptureUri);
                            encodedimageno="39";
                            try {
                                intent.putExtra("return-data", true);
                                startActivityForResult(intent, PICK_FROM_CAMERA);
                            } catch (ActivityNotFoundException e) {
                                e.printStackTrace();
                            }
                        }

                    });

                    build.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });

                    build.setNegativeButton("Gallery", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which)
                        {
                            Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                                    android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                            encodedimageno="39";
                            startActivityForResult(galleryIntent, PICK_FROM_FILE);
                        }
                    });
                    build.show();
                }

                else
                {
                    View v3 = getLayoutInflater().inflate(R.layout.showdoc, null);
                    ImageView iv=(ImageView) v3.findViewById(R.id.imageView10);
                    Bitmap s=decodeBase64(tintedglaasspermitpic);
                    iv.setImageBitmap(s);
                    ivLargeImage = (ImageViewTouch) v3.findViewById(R.id.ivLargeImageView);
                    ivLargeImage.setImageBitmapReset(s, 0, true);
                    AlertDialog.Builder b5 = new AlertDialog.Builder(MainActivity.this)
                            .setCancelable(true)
                            .setView(v3);

                    b6 = b5.create();
                    b6.show();

                    Button b10 = (Button) v3.findViewById(R.id.button7);
                    b10.setOnClickListener(new View.OnClickListener() {
                        @Override

                        public void onClick(View v) {
                            b6.cancel();
                        }
                    });
                }
            }
        });
    }

    class JSONP7 extends AsyncTask<Void,Void,Bitmap>
    {
        String jsonurl,jsonstring;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            jsonurl="http://bjprogrammer.co.nf/nigeriantraffic/pic.php";
        }

        @Override
        protected Bitmap doInBackground(Void... params) {

            String data="";

            try {
                URL url=new URL(jsonurl);
                HttpURLConnection httpURLConnection= (HttpURLConnection) url.openConnection();

                httpURLConnection.setRequestMethod("POST");
                OutputStream outputStream=httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter=new BufferedWriter(new OutputStreamWriter(outputStream));
                String postdata= URLEncoder.encode("username")+"="+URLEncoder.encode(username);

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

                //Log.i("message",Signin.encodedImage);
                image= decodeBase64(stringBuilder.toString());
            }
            catch (MalformedURLException e)
            {
                e.printStackTrace();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }

            return  image;
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(Bitmap s) {
            super.onPostExecute(s);

            if(s!=null)
            {
                iv.setImageBitmap(s);
            }
        }

        public  Bitmap decodeBase64(String input)
        {
            byte[] decodedBytes = Base64.decode(input, 0);
            return BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.length);
        }
    }

    @Override
    public void onBackPressed() {
        final View v2 = getLayoutInflater().inflate(R.layout.exit, null);

        AlertDialog.Builder b5 = new AlertDialog.Builder(MainActivity.this)
                .setCancelable(false)
                .setView(v2);

        b4 = b5.create();
        b4.show();

        Button b11 = (Button) v2.findViewById(R.id.button7);
        Button b12=(Button)  v2.findViewById(R.id.button);

       b11.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               b4.dismiss();
           }
       });

        b12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    class JSONP8 extends AsyncTask<Void,Void,String>
    {
        String jsonurl,jsonstring;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            jsonurl="http://bjprogrammer.co.nf/nigeriantraffic/docstatus.php";
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
                String postdata= URLEncoder.encode("username")+"="+URLEncoder.encode(username);
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
                for(int i=0; i < jsonArray.length(); i++)
                {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    drivinglicensestatus = jsonObject.optString("drivinglicensestatus").toString();
                    tyreexpirationstatus = jsonObject.optString("tyreexpirationstatus").toString();
                    fireextinguisherstatus = jsonObject.optString("fireextinguisherstatus").toString();
                    viostatus = jsonObject.optString("viodocumentstatus").toString();
                    insurancestatus = jsonObject.optString("insurancestatus").toString();
                    hackneypermitstatus = jsonObject.optString("hackneypermitstatus").toString();
                    ownershipproofstatus = jsonObject.optString("ownershipproofstatus").toString();
                    policepermitstatus = jsonObject.optString("policepermitstatus").toString();
                    tintedglaasspermitstatus = jsonObject.optString("tintedglasspermitstatus").toString();
                    drivinglicensepic = jsonObject.optString("drivinglicense").toString();
                    tyreexpirationpic = jsonObject.optString("tyreexpiration").toString();
                    fireextinguisherpic = jsonObject.optString("fireextinguisher").toString();
                    viopic = jsonObject.optString("viodocument").toString();
                    insurancepic = jsonObject.optString("insurance").toString();
                    hackneypermitpic = jsonObject.optString("hackneypermit").toString();
                    ownershipproofpic = jsonObject.optString("ownershipproof").toString();
                    policepermitpic = jsonObject.optString("policepermit").toString();
                    tintedglaasspermitpic = jsonObject.optString("tintedglasspermit").toString();
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

            new JSONP7().execute();
            if(s.contains("successfully"))
            {
                t21.setText(drivinglicensestatus);
                t22.setText(tyreexpirationstatus);
                t23.setText(fireextinguisherstatus);
                t24.setText(viostatus);
                t25.setText(insurancestatus);
                t26.setText(hackneypermitstatus);
                t27.setText(ownershipproofstatus);
                t28.setText(policepermitstatus);
                t29.setText(tintedglaasspermitstatus);

                if(t21.getText().toString().equalsIgnoreCase("Under Verification"))
                {
                    t31.setText("View Doc");
                    t31.setTextColor(Color.GREEN);
                    t21.setTextColor(Color.YELLOW);
                }
                else if(t21.getText().toString().equalsIgnoreCase("Rejected"))
                {
                    t21.setTextColor(Color.RED);
                }
                else if(t21.getText().toString().equalsIgnoreCase("Verified"))
                {
                    t31.setText("View Doc");
                    t31.setTextColor(Color.GREEN);
                    t21.setTextColor(Color.GREEN);
                }
                else if(t21.getText().toString().equalsIgnoreCase("About to Expire"))
                {
                    t21.setTextColor(Color.CYAN);
                }
                else if(t21.getText().toString().equalsIgnoreCase("Expired"))
                {
                    t21.setTextColor(Color.RED);
                }

                if(t22.getText().toString().equalsIgnoreCase("Under Verification"))
                {
                    t32.setText("View Doc");
                    t32.setTextColor(Color.GREEN);
                    t22.setTextColor(Color.YELLOW);
                }
                else if(t22.getText().toString().equalsIgnoreCase("Rejected"))
                {
                    t22.setTextColor(Color.RED);
                }
                else if(t22.getText().toString().equalsIgnoreCase("Verified"))
                {
                    t32.setText("View Doc");
                    t32.setTextColor(Color.GREEN);
                    t22.setTextColor(Color.GREEN);
                }
                else if(t22.getText().toString().equalsIgnoreCase("About to Expire"))
                {
                    t22.setTextColor(Color.CYAN);
                }
                else if(t22.getText().toString().equalsIgnoreCase("Expired"))
                {
                    t22.setTextColor(Color.RED);
                }

                if(t23.getText().toString().equalsIgnoreCase("Under Verification"))
                {
                    t33.setText("View Doc");
                    t33.setTextColor(Color.GREEN);
                    t23.setTextColor(Color.YELLOW);
                }
                else if(t23.getText().toString().equalsIgnoreCase("Rejected"))
                {
                    t23.setTextColor(Color.RED);
                }
                else if(t23.getText().toString().equalsIgnoreCase("Verified"))
                {
                    t33.setText("View Doc");
                    t33.setTextColor(Color.GREEN);
                    t23.setTextColor(Color.GREEN);
                }
                else if(t23.getText().toString().equalsIgnoreCase("About to Expire"))
                {
                    t23.setTextColor(Color.CYAN);
                }
                else if(t23.getText().toString().equalsIgnoreCase("Expired"))
                {
                    t23.setTextColor(Color.RED);
                }

                if(t24.getText().toString().equalsIgnoreCase("Under Verification"))
                {
                    t34.setText("View Doc");
                    t34.setTextColor(Color.GREEN);
                    t24.setTextColor(Color.YELLOW);
                }
                else if(t24.getText().toString().equalsIgnoreCase("Rejected"))
                {
                    t24.setTextColor(Color.RED);
                }
                else if(t24.getText().toString().equalsIgnoreCase("Verified"))
                {
                    t34.setText("View Doc");
                    t34.setTextColor(Color.GREEN);
                    t24.setTextColor(Color.GREEN);
                }
                else if(t24.getText().toString().equalsIgnoreCase("About to Expire"))
                {
                    t24.setTextColor(Color.CYAN);
                }
                else if(t24.getText().toString().equalsIgnoreCase("Expired"))
                {
                    t24.setTextColor(Color.RED);
                }

                if(t25.getText().toString().equalsIgnoreCase("Under Verification"))
                {
                    t35.setText("View Doc");
                    t35.setTextColor(Color.GREEN);
                    t25.setTextColor(Color.YELLOW);
                }
                else if(t25.getText().toString().equalsIgnoreCase("Rejected"))
                {
                    t25.setTextColor(Color.RED);
                }
                else if(t25.getText().toString().equalsIgnoreCase("Verified"))
                {
                    t35.setText("View Doc");
                    t35.setTextColor(Color.GREEN);
                    t25.setTextColor(Color.GREEN);
                }
                else if(t25.getText().toString().equalsIgnoreCase("About to Expire"))
                {
                    t25.setTextColor(Color.CYAN);
                }
                else if(t25.getText().toString().equalsIgnoreCase("Expired"))
                {
                    t25.setTextColor(Color.RED);
                }

                if(t26.getText().toString().equalsIgnoreCase("Under Verification"))
                {
                    t36.setText("View Doc");
                    t36.setTextColor(Color.GREEN);
                    t26.setTextColor(Color.YELLOW);
                }
                else if(t26.getText().toString().equalsIgnoreCase("Rejected"))
                {
                    t26.setTextColor(Color.RED);
                }
                else if(t26.getText().toString().equalsIgnoreCase("Verified"))
                {
                    t36.setText("View Doc");
                    t36.setTextColor(Color.GREEN);
                    t26.setTextColor(Color.GREEN);
                }
                else if(t26.getText().toString().equalsIgnoreCase("About to Expire"))
                {
                    t26.setTextColor(Color.CYAN);
                }
                else if(t26.getText().toString().equalsIgnoreCase("Expired"))
                {
                    t26.setTextColor(Color.RED);
                }

                if(t27.getText().toString().equalsIgnoreCase("Under Verification"))
                {
                    t37.setText("View Doc");
                    t37.setTextColor(Color.GREEN);
                    t27.setTextColor(Color.YELLOW);
                }
                else if(t27.getText().toString().equalsIgnoreCase("Rejected"))
                {
                    t27.setTextColor(Color.RED);
                }
                else if(t27.getText().toString().equalsIgnoreCase("Verified"))
                {
                    t37.setText("View Doc");
                    t37.setTextColor(Color.GREEN);
                    t27.setTextColor(Color.GREEN);
                }
                else if(t27.getText().toString().equalsIgnoreCase("About to Expire"))
                {
                    t27.setTextColor(Color.CYAN);
                }
                else if(t27.getText().toString().equalsIgnoreCase("Expired"))
                {
                    t27.setTextColor(Color.RED);
                }

                if(t28.getText().toString().equalsIgnoreCase("Under Verification"))
                {
                    t38.setText("View Doc");
                    t38.setTextColor(Color.GREEN);
                    t28.setTextColor(Color.YELLOW);
                }
                else if(t28.getText().toString().equalsIgnoreCase("Rejected"))
                {
                    t28.setTextColor(Color.RED);
                }
                else if(t28.getText().toString().equalsIgnoreCase("Verified"))
                {
                    t38.setText("View Doc");
                    t38.setTextColor(Color.GREEN);
                    t28.setTextColor(Color.GREEN);
                }
                else if(t28.getText().toString().equalsIgnoreCase("About to Expire"))
                {
                    t28.setTextColor(Color.CYAN);
                }
                else if(t28.getText().toString().equalsIgnoreCase("Expired"))
                {
                    t28.setTextColor(Color.RED);
                }

                if(t29.getText().toString().equalsIgnoreCase("Under Verification"))
                {
                    t39.setText("View Doc");
                    t39.setTextColor(Color.GREEN);
                    t29.setTextColor(Color.YELLOW);
                }
                else if(t29.getText().toString().equalsIgnoreCase("Rejected"))
                {
                    t29.setTextColor(Color.RED);
                }
                else if(t29.getText().toString().equalsIgnoreCase("Verified"))
                {
                    t39.setText("View Doc");
                    t39.setTextColor(Color.GREEN);
                    t29.setTextColor(Color.GREEN);
                }
                else if(t29.getText().toString().equalsIgnoreCase("About to Expire"))
                {
                    t29.setTextColor(Color.CYAN);
                }
                else if(t29.getText().toString().equalsIgnoreCase("Expired"))
                {
                    t29.setTextColor(Color.RED);
                }
            }
        }
    }

    class JSONP9 extends AsyncTask<Void,Void,String>
    {
        String jsonurl,jsonstring;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            jsonurl="http://sixthsenseit.com/toshow/udr/updatedoc.php";
        }

        @Override
        protected String doInBackground(Void... params) {

            String data="";

            try {
                URL url=new URL(jsonurl);
                HttpURLConnection httpURLConnection= (HttpURLConnection) url.openConnection();

                httpURLConnection.setRequestMethod("POST");
                OutputStream outputStream=httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter=new BufferedWriter(new OutputStreamWriter(outputStream));
                String postdata= URLEncoder.encode("username")+"="+URLEncoder.encode(username)+"&"+URLEncoder.encode("pic")+"="+URLEncoder.encode(encodedImage)
                +"&"+URLEncoder.encode("docname")+"="+URLEncoder.encode(docname);

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
            }
            catch (MalformedURLException e)
            {
                e.printStackTrace();
            }
            catch (IOException e)
            {
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
                Toast.makeText(MainActivity.this, s, Toast.LENGTH_LONG).show();
                b6.cancel();

            if(s.contains("successfully"))
            {
                if(docname.equalsIgnoreCase("drivinglicense"))
                {
                    t21.setTextColor(Color.YELLOW);
                    t21.setText("Under Verification");
                    t31.setText("View Doc");
                    t31.setTextColor(Color.GREEN);
                    encodedImage31=encodedImage;
                }
                else if(docname.equalsIgnoreCase("tyreexpiration"))
                {
                    t22.setTextColor(Color.YELLOW);
                    t22.setText("Under Verification");
                    t32.setText("View Doc");
                    t32.setTextColor(Color.GREEN);
                    encodedImage32=encodedImage;
                }
                else if(docname.equalsIgnoreCase("fireextinguisher"))
                {
                    t23.setTextColor(Color.YELLOW);
                    t23.setText("Under Verification");
                    t33.setText("View Doc");
                    t33.setTextColor(Color.GREEN);
                    encodedImage33=encodedImage;
                }
               else if(docname.equalsIgnoreCase("viodoc"))
                {
                    t24.setTextColor(Color.YELLOW);
                    t24.setText("Under Verification");
                    t34.setText("View Doc");
                    t34.setTextColor(Color.GREEN);
                    encodedImage34=encodedImage;
                }
                else if(docname.equalsIgnoreCase("insurance"))
                {
                    t25.setTextColor(Color.YELLOW);
                    t25.setText("Under Verification");
                    t35.setText("View Doc");
                    t35.setTextColor(Color.GREEN);
                    encodedImage35=encodedImage;
                }
                else if(docname.equalsIgnoreCase("hackneypermit"))
                {
                    t26.setTextColor(Color.YELLOW);
                    t26.setText("Under Verification");
                    t36.setText("View Doc");
                    t36.setTextColor(Color.GREEN);
                    encodedImage36=encodedImage;
                }
                else if(docname.equalsIgnoreCase("ownershipproof"))
                {
                    t27.setTextColor(Color.YELLOW);
                    t27.setText("Under Verification");
                    t37.setText("View Doc");
                    t37.setTextColor(Color.GREEN);
                    encodedImage37=encodedImage;
                }
                else if(docname.equalsIgnoreCase("policepermit"))
                {
                    t28.setTextColor(Color.YELLOW);
                    t28.setText("Under Verification");
                    t38.setText("View Doc");
                    t38.setTextColor(Color.GREEN);
                    encodedImage38=encodedImage;
                }
                else if(docname.equalsIgnoreCase("tintedglasspermit"))
                {
                    t29.setTextColor(Color.YELLOW);
                    t29.setText("Under Verification");
                    t9.setText("View Doc");
                    t39.setTextColor(Color.GREEN);
                    encodedImage39=encodedImage;
                }
            }
        }
    }


    public class  JSONP10 extends AsyncTask<Void,Void,String> {
        String jsonurl, jsonstring;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            jsonurl ="http://bjprogrammer.co.nf/nigeriantraffic/otprequired.php";

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
                String postdata = URLEncoder.encode("username") + "=" + URLEncoder.encode(username) ;

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

            if(s.contains("already verified"))
            {}
            else
            {
                View v1 = getLayoutInflater().inflate(R.layout.otp, null);
                AlertDialog.Builder b1 = new AlertDialog.Builder(MainActivity.this)
                        .setCancelable(false)
                        .setView(v1);
                b2 = b1.create();
                b2.show();
                Button b10 = (Button) v1.findViewById(R.id.button7);
                e1=(EditText) v1.findViewById(R.id.editText13);
                b10.setOnClickListener(new View.OnClickListener() {
                    @Override

                    public void onClick(View v) {
                        if(!e1.getText().toString().isEmpty()) {
                            otp=e1.getText().toString();
                            new JSONP().execute();
                        }
                        else
                        {
                            Toast.makeText(MainActivity.this, "Please enter OTP in order to continue.", Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        }
    }
}
