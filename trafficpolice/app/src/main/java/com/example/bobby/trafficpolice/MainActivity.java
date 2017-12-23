package com.example.bobby.trafficpolice;

import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bobby.trafficpolice.utility.DatabaseHelper;
import com.example.bobby.trafficpolice.utility.FragmentDrawer;

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

public class MainActivity extends AppCompatActivity  implements FragmentDrawer.FragmentDrawerListener {
    private Toolbar mToolbar;
    private FragmentDrawer drawerFragment;
    AlertDialog b4;
    ImageButton ib;
    String mobile,mobile1;

    private Uri mImageCaptureUri;
    private static final int PICK_FROM_CAMERA = 1;
    private static final int CROP_FROM_CAMERA = 2;
    private static final int PICK_FROM_FILE = 3;
    byte[] b;
    static String encodedImage;
    Bitmap bitm,image;
    ImageView iv;

    EditText no;
    Button b1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mobile1=getIntent().getStringExtra("mobile");
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        drawerFragment = (FragmentDrawer)
                getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);
        drawerFragment.setUp(R.id.fragment_navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout), mToolbar);
        drawerFragment.setDrawerListener(this);

        displayView(0);
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

                        EditText t2=(EditText)v7.findViewById(R.id.editText5);

                        if( t2.getText().toString().isEmpty())
                        {
                            Toast.makeText(MainActivity.this, "Please fill this form in order to continue.", Toast.LENGTH_SHORT).show();
                        }
                        else if(t2.getText().toString().length()!=10)
                        {
                            Toast.makeText(MainActivity.this, "Please check your mobile no.", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            mobile=t2.getText().toString();
                            new JSONP().execute();
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
                        build.setTitle("Upload profile image using -");

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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

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

        Bitmap s=decodeBase64(encodedImage);
        ib.setImageBitmap(s);

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

        if (uri == null) {
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


    class JSONP extends AsyncTask<Void,Void,String> {
        String jsonurl, jsonstring;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            jsonurl ="http://sixthsenseit.com/blood/partnerupdate.php";

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
                String postdata = URLEncoder.encode("mobile") + "=" + URLEncoder.encode(mobile1) + "&" + URLEncoder.encode("updatedmobile") + "=" + URLEncoder.encode(mobile)
                        + "&" + URLEncoder.encode("pic") + "=" + URLEncoder.encode(encodedImage);

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

            if(s.contains("Successfully"))
            {
                Toast.makeText(MainActivity.this,s, Toast.LENGTH_SHORT).show();
                b4.dismiss();
            }
            else
            {
                Toast.makeText(MainActivity.this,s, Toast.LENGTH_SHORT).show();
            }
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
                finish();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        iv = (ImageView) findViewById(R.id.imageView11);
        TextView t10 = (TextView) findViewById(R.id.textView35);
        t10.setText(mobile1);
        new JSONP2().execute();

        no=(EditText)findViewById(R.id.editText3);
        b1=(Button)findViewById(R.id.button2);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               if( no.getText().toString().isEmpty())
               {
                   Toast.makeText(MainActivity.this, "Please enter vehicle plate no.", Toast.LENGTH_SHORT).show();
               }
                else
               {
                   Intent intent =new Intent(MainActivity.this,docstatus.class);
                   intent.putExtra("plateno",no.getText().toString());
                   intent.putExtra("mobile",mobile1);
                   startActivity(intent);
               }
            }
        });
    }


    class JSONP2 extends AsyncTask<Void,Void,Bitmap>
    {
        String jsonurl,jsonstring;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            jsonurl="http://sixthsenseit.com/toshow/udr/partnerpic.php";
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
                String postdata= URLEncoder.encode("mobile")+"="+URLEncoder.encode(mobile1);

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

            if(s!=null) {
                iv.setImageBitmap(s);
            }
        }

        public  Bitmap decodeBase64(String input)
        {
            byte[] decodedBytes = Base64.decode(input, 0);
            return BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.length);
        }
    }
}
