package com.example.bobby.trafficpolice;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.theappguruz.imagezoom.ImageViewTouch;

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
import java.text.Collator;
import java.util.ArrayList;
import java.util.List;

public class docstatus extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    String plateno;
    String drivinglicensestatus,tyreexpirationstatus,fireextinguisherstatus,viostatus,insurancestatus,hackneypermitstatus,
            ownershipproofstatus,policepermitstatus,tintedglaasspermitstatus,drivinglicensepic,tyreexpirationpic,
            fireextinguisherpic,viopic,insurancepic,hackneypermitpic,ownershipproofpic,policepermitpic,tintedglaasspermitpic;

    TextView t21,t22,t23,t24,t25,t26,t27,t28,t29,t31,t32,t33,t34,t35,t36,t37,t38,t39;
    AlertDialog b6,b4;
    private ImageViewTouch ivLargeImage;
    Spinner spinner;
    String comment;
    TextView t1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_docstatus);

        t21=(TextView)findViewById(R.id.textView82);
        t31=(TextView)findViewById(R.id.textView15);
        t22=(TextView)findViewById(R.id.textView81);
        t32=(TextView)findViewById(R.id.textView59);
        t23=(TextView)findViewById(R.id.textView80);
        t33=(TextView)findViewById(R.id.textView61);
        t24=(TextView)findViewById(R.id.textView79);
        t34=(TextView)findViewById(R.id.textView63);
        t25=(TextView)findViewById(R.id.textView78);
        t35=(TextView)findViewById(R.id.textView65);
        t26=(TextView)findViewById(R.id.textView77);
        t36=(TextView)findViewById(R.id.textView67);
        t27=(TextView)findViewById(R.id.textView76);
        t37=(TextView)findViewById(R.id.textView69);
        t28=(TextView)findViewById(R.id.textView75);
        t38=(TextView)findViewById(R.id.textView71);
        t29=(TextView)findViewById(R.id.textView74);
        t39=(TextView)findViewById(R.id.textView73);

        t1=(TextView) findViewById(R.id.textView17);

        plateno=getIntent().getStringExtra("plateno");

        spinner = (Spinner) findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(this);

        List<String> categories = new ArrayList<String>();
        categories.add("Reckless driving");
        categories.add("Drunkness");
        categories.add("Driving license expired");
        categories.add("Ownership proof not found");
        categories.add("Vehicle insurance not found");
        categories.add("Police permit not found");
        categories.add("Tinted glass permit not found");
        categories.add("VIO document expired");
        categories.add("Hackney permit not found");
        categories.add("Driving license not found");
        categories.add("Vehicle Insurance expired");
        categories.add("VIO document not found");
        categories.add("Illegal commercial use of vehicle");
        categories.add("Driving in opposite direction");
        categories.add("Parking in undesignated area");
        categories.add("Not abiding by the lanes of the road");
        categories.add("Exceeding posted speed limit on the road");
        categories.add("Vehicle at very low speed and obstructing traffic");
        categories.add("Driver/front seat passenger not wearing seat belts");
        categories.add("Children(10 years or below) occupying front seat");
        categories.add("Using mobile phone while driving");
        categories.add("Driving vehicle making noisy sounds/dense fume");
        categories.add("Using musical horns/emergency sirens");
        categories.add("Driving without adequate working horn");
        categories.add("Not abiding traffic signals");
        categories.add("No comments");

        java.util.Collections.sort(categories, Collator.getInstance());
        categories.add(0,"Select Comment");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter <String>(this,R.layout.spinner_item, categories);
        //dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);

        new JSONP().execute();
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        if(adapterView.toString().contains("spinner"))
        {
             comment= adapterView.getItemAtPosition(i).toString();
             if(comment.equalsIgnoreCase("No comments"))
             {
                 t1.setVisibility(View.VISIBLE);
                 t1.setTextColor(Color.GREEN);
                 t1.setOnClickListener(new View.OnClickListener() {
                     @Override
                     public void onClick(View view) {
                         Intent intent=new Intent(docstatus.this,doccheckcomplete.class);
                         intent.putExtra("plateno",plateno);
                         intent.putExtra("mobile",getIntent().getStringExtra("mobile"));
                         intent.putExtra("status","nocomment");
                         startActivity(intent);
                         finish();
                     }
                 });
             }
           else  if(!comment.equalsIgnoreCase("Select Comment"))
             {
                 t1.setVisibility(View.VISIBLE);
                 t1.setTextColor(Color.RED);
                 t1.setOnClickListener(new View.OnClickListener() {
                     @Override
                     public void onClick(View view) {
                         Intent intent=new Intent(docstatus.this,recording.class);
                         intent.putExtra("plateno",plateno);
                         intent.putExtra("comment",comment);
                         intent.putExtra("mobile",getIntent().getStringExtra("mobile"));
                         startActivity(intent);
                         finish();
                     }
                 });
             }
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    class JSONP extends AsyncTask<Void,Void,String>
    {
        String jsonurl,jsonstring;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            jsonurl="http://sixthsenseit.com/blood/partnerdocstatus.php";
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
                else if(t21.getText().toString().equalsIgnoreCase("Verified"))
                {
                    t32.setText("View Doc");
                    t32.setTextColor(Color.GREEN);
                    t22.setTextColor(Color.GREEN);
                }
                else if(t22.getText().toString().equalsIgnoreCase("About to Expire"))
                {
                    t22.setTextColor(Color.CYAN);
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

            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        t31.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(t31.getText().toString().equalsIgnoreCase("Upload"))
                {
                    Toast.makeText(docstatus.this, "Sorry! You don't have permission to upload documents.", Toast.LENGTH_SHORT).show();
                }

                else
                {
                    View v3 = getLayoutInflater().inflate(R.layout.showdoc, null);
                    ImageView iv=(ImageView) v3.findViewById(R.id.imageView10);
                    Bitmap s=decodeBase64(drivinglicensepic);
                    iv.setImageBitmap(s);
                    ivLargeImage = (ImageViewTouch) v3.findViewById(R.id.ivLargeImageView);
                    ivLargeImage.setImageBitmapReset(s, 0, true);
                    AlertDialog.Builder b5 = new AlertDialog.Builder(docstatus.this)
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
                    Toast.makeText(docstatus.this, "Sorry! You don't have permission to upload documents.", Toast.LENGTH_SHORT).show();
                }

                else
                {
                    View v3 = getLayoutInflater().inflate(R.layout.showdoc, null);
                    ImageView iv=(ImageView) v3.findViewById(R.id.imageView10);
                    Bitmap s=decodeBase64(tyreexpirationpic);
                    iv.setImageBitmap(s);
                    ivLargeImage = (ImageViewTouch) v3.findViewById(R.id.ivLargeImageView);
                    ivLargeImage.setImageBitmapReset(s, 0, true);
                    AlertDialog.Builder b5 = new AlertDialog.Builder(docstatus.this)
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
                    Toast.makeText(docstatus.this, "Sorry! You don't have permission to upload documents.", Toast.LENGTH_SHORT).show();
                }

                else
                {
                    View v3 = getLayoutInflater().inflate(R.layout.showdoc, null);
                    ImageView iv=(ImageView) v3.findViewById(R.id.imageView10);
                    Bitmap s=decodeBase64(fireextinguisherpic);
                    iv.setImageBitmap(s);
                    ivLargeImage = (ImageViewTouch) v3.findViewById(R.id.ivLargeImageView);
                    ivLargeImage.setImageBitmapReset(s, 0, true);
                    AlertDialog.Builder b5 = new AlertDialog.Builder(docstatus.this)
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
                    Toast.makeText(docstatus.this, "Sorry! You don't have permission to upload documents.", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    View v3 = getLayoutInflater().inflate(R.layout.showdoc, null);
                    ImageView iv=(ImageView) v3.findViewById(R.id.imageView10);
                    Bitmap s=decodeBase64(viopic);
                    iv.setImageBitmap(s);
                    ivLargeImage = (ImageViewTouch) v3.findViewById(R.id.ivLargeImageView);
                    ivLargeImage.setImageBitmapReset(s, 0, true);
                    AlertDialog.Builder b5 = new AlertDialog.Builder(docstatus.this)
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
                if(t35.getText().toString().equalsIgnoreCase("Upload"))
                {
                    Toast.makeText(docstatus.this, "Sorry! You don't have permission to upload documents.", Toast.LENGTH_SHORT).show();
                }

                else
                {
                    View v3 = getLayoutInflater().inflate(R.layout.showdoc, null);
                    ImageView iv=(ImageView) v3.findViewById(R.id.imageView10);
                    Bitmap s=decodeBase64(insurancepic);
                    iv.setImageBitmap(s);
                    ivLargeImage = (ImageViewTouch) v3.findViewById(R.id.ivLargeImageView);
                    ivLargeImage.setImageBitmapReset(s, 0, true);
                    AlertDialog.Builder b5 = new AlertDialog.Builder(docstatus.this)
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
                    Toast.makeText(docstatus.this, "Sorry! You don't have permission to upload documents.", Toast.LENGTH_SHORT).show();
                }

                else
                {
                    View v3 = getLayoutInflater().inflate(R.layout.showdoc, null);
                    ImageView iv=(ImageView) v3.findViewById(R.id.imageView10);
                    Bitmap s=decodeBase64(hackneypermitpic);
                    iv.setImageBitmap(s);
                    ivLargeImage = (ImageViewTouch) v3.findViewById(R.id.ivLargeImageView);
                    ivLargeImage.setImageBitmapReset(s, 0, true);
                    AlertDialog.Builder b5 = new AlertDialog.Builder(docstatus.this)
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
                    Toast.makeText(docstatus.this, "Sorry! You don't have permission to upload documents.", Toast.LENGTH_SHORT).show();
                }

                else
                {
                    View v3 = getLayoutInflater().inflate(R.layout.showdoc, null);
                    ImageView iv=(ImageView) v3.findViewById(R.id.imageView10);
                    Bitmap s=decodeBase64(ownershipproofpic);
                    iv.setImageBitmap(s);
                    ivLargeImage = (ImageViewTouch) v3.findViewById(R.id.ivLargeImageView);
                    ivLargeImage.setImageBitmapReset(s, 0, true);
                    AlertDialog.Builder b5 = new AlertDialog.Builder(docstatus.this)
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
                    Toast.makeText(docstatus.this, "Sorry! You don't have permission to upload documents.", Toast.LENGTH_SHORT).show();
                }

                else
                {
                    View v3 = getLayoutInflater().inflate(R.layout.showdoc, null);
                    ImageView iv=(ImageView) v3.findViewById(R.id.imageView10);
                    Bitmap s=decodeBase64(policepermitpic);
                    iv.setImageBitmap(s);
                    ivLargeImage = (ImageViewTouch) v3.findViewById(R.id.ivLargeImageView);
                    ivLargeImage.setImageBitmapReset(s, 0, true);
                    AlertDialog.Builder b5 = new AlertDialog.Builder(docstatus.this)
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
                    Toast.makeText(docstatus.this, "Sorry! You don't have permission to upload documents.", Toast.LENGTH_SHORT).show();
                }

                else
                {
                    View v3 = getLayoutInflater().inflate(R.layout.showdoc, null);
                    ImageView iv=(ImageView) v3.findViewById(R.id.imageView10);
                    Bitmap s=decodeBase64(tintedglaasspermitpic);
                    iv.setImageBitmap(s);
                    ivLargeImage = (ImageViewTouch) v3.findViewById(R.id.ivLargeImageView);
                    ivLargeImage.setImageBitmapReset(s, 0, true);
                    AlertDialog.Builder b5 = new AlertDialog.Builder(docstatus.this)
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

    public  Bitmap decodeBase64(String input)
    {
        byte[] decodedBytes = Base64.decode(input, 0);
        return BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.length);
    }

    @Override
    public void onBackPressed() {
        final View v2 = getLayoutInflater().inflate(R.layout.cancel, null);

        AlertDialog.Builder b5 = new AlertDialog.Builder(docstatus.this)
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
                Intent intent=new Intent(docstatus.this,MainActivity.class);
                intent.putExtra("mobile", getIntent().getStringExtra("mobile"));
                startActivity(intent);
                finish();
            }
        });
    }
}
