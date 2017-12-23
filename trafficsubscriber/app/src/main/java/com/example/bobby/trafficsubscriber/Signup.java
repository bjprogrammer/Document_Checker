package com.example.bobby.trafficsubscriber;

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
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.bobby.trafficsubscriber.utility.DatabaseHelper;

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
import java.text.Collator;
import java.util.ArrayList;
import java.util.List;

public class Signup extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    Spinner spinner2,spinner1;
    ImageButton ib;
    EditText e2,e3,e4,e5,e6,e7;
    Button b1;
    String name,address,username, email,mobile,password,item,district;

    private Uri mImageCaptureUri;
    private static final int PICK_FROM_CAMERA = 1;
    private static final int CROP_FROM_CAMERA = 2;
    private static final int PICK_FROM_FILE = 3;
    byte[] b;
    static String encodedImage ;
    Bitmap bitm=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        ib=(ImageButton) findViewById(R.id.imageButton);
        e2=(EditText) findViewById(R.id.editText3);
        e3=(EditText) findViewById(R.id.editText4);
        e4=(EditText) findViewById(R.id.editText5);
        e5=(EditText) findViewById(R.id.editText6);
        e6=(EditText) findViewById(R.id.editText11);
        e7=(EditText) findViewById(R.id.editText12);
        b1=(Button)findViewById(R.id.button5) ;

        spinner1 = (Spinner) findViewById(R.id.spinner1);
        spinner2 = (Spinner) findViewById(R.id.spinner2);

        // Spinner click listener
        spinner1.setOnItemSelectedListener(this);
        spinner2.setOnItemSelectedListener(this);

        List<String> categories = new ArrayList<String>();
        categories.add("Select State");
        categories.add("Abia");
        categories.add("Adamawa");
        categories.add("Akwa Ibom");
        categories.add("Anambra");
        categories.add("Bauchi");
        categories.add("Bayelsa");
        categories.add("Benue");
        categories.add("Bornu");
        categories.add("Cross River");
        categories.add("Delta");
        categories.add("Ebonyi ");
        categories.add("Edo");
        categories.add("Ekiti");
        categories.add("Enugu");
        categories.add("Federal Capital Territory");
        categories.add("Gombe");
        categories.add("Imo");
        categories.add("Jigawa");
        categories.add("Kaduna");
        categories.add("Kano");
        categories.add("Katsina");
        categories.add("Kebbi");
        categories.add("Kogi");
        categories.add("Kwara");
        categories.add("Lagos");
        categories.add("Nasarawa");
        categories.add("Niger");
        categories.add("Ogun");
        categories.add("Ondo");
        categories.add("Osun");
        categories.add("Plateau");
        categories.add("Rivers");
        categories.add("Sokoto");
        categories.add("Taraba");
        categories.add("Yobe");
        categories.add("Zamfara");

        // Creating adapter for spinner

        List<String> categories2 = new ArrayList <String>();
        categories2.add("Select LGA");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter <String>(this, android.R.layout.simple_spinner_item, categories);
        ArrayAdapter <String> dataAdapter2 = new ArrayAdapter <String>(this, android.R.layout.simple_spinner_item, categories2);

        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner1.setAdapter(dataAdapter);
        spinner2.setAdapter(dataAdapter2);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
    {

        if(parent.toString().contains("spinner1")) {
            item = parent.getItemAtPosition(position).toString();

            if (item.equals("Federal Capital Territory")) {
                List<String> categories2 = new ArrayList<String>();
                categories2.add("Gwagwalada");
                categories2.add("Kuje");
                categories2.add(" Abaji");
                categories2.add("Abuja Municipal");
                categories2.add("Bwari");
                categories2.add("Kwali");
                java.util.Collections.sort(categories2, Collator.getInstance());
                categories2.add(0,"Select LGA");

                ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories2);
                dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner2.setAdapter(dataAdapter2);
            } else if (item.equals("Abia")) {
                List<String> categories2 = new ArrayList<String>();
                categories2.add("Aba North");
                categories2.add("Aba South");
                categories2.add("Arochukwu");
                categories2.add("Bende");
                categories2.add("Ikwuano");
                categories2.add("Isiala-Ngwa North");
                categories2.add("Isiala-Ngwa South");
                categories2.add("Isuikwato");
                categories2.add("Obi Nwa");
                categories2.add("Ohafia");
                categories2.add("Osisioma");
                categories2.add("Ngwa");
                categories2.add("Ugwunagbo");
                categories2.add("Ukwa East");
                categories2.add("Ukwa West");
                categories2.add("Umuahia North");
                categories2.add("Umuahia South");
                categories2.add("Umu-Neochi");
                java.util.Collections.sort(categories2, Collator.getInstance());
                categories2.add(0,"Select LGA");

                ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories2);
                dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner2.setAdapter(dataAdapter2);
            } else if (item.equals("Adamawa")) {
                List<String> categories2 = new ArrayList<String>();
                categories2.add("Demsa");
                categories2.add("Fufore");

                categories2.add("Ganaye");
                categories2.add("Gireri");
                categories2.add("Gombi");
                categories2.add("Guyuk");
                categories2.add("Hong");
                categories2.add("Jada");
                categories2.add("Lamurde");
                categories2.add("Madagali");
                categories2.add("Maiha");
                categories2.add("Mayo-Belwa");
                categories2.add("Michika");
                categories2.add("Mubi North");
                categories2.add("Mubi South");
                categories2.add("Numan");
                categories2.add("Shelleng");
                categories2.add("Song");
                categories2.add("Toungo");
                categories2.add("Yola North");
                categories2.add("Yola South");
                java.util.Collections.sort(categories2, Collator.getInstance());
                categories2.add(0,"Select LGA");

                ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories2);
                dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner2.setAdapter(dataAdapter2);
            }
            else if (item.equals("Akwa Ibom")) {
                List<String> categories2 = new ArrayList<String>();
                categories2.add("Abak");
                categories2.add("Eastern Obolo");
                categories2.add("Eket");
                categories2.add("Esit Eket");
                categories2.add("Essien Udim");
                categories2.add("Etim Ekpo");
                categories2.add("Etinan");
                categories2.add("Ibeno");
                categories2.add("Ibesikpo Asutan");
                categories2.add("Ibiono Ibom");
                categories2.add("Ika");
                categories2.add("Ikono");
                categories2.add("Ikot Abasi");
                categories2.add("Ikot Ekpene");
                categories2.add("Ini");
                categories2.add("Itu");
                categories2.add("Mbo");
                categories2.add("Mkpat Enin");
                categories2.add("Nsit Atai");
                categories2.add("Nsit Ibom");
                categories2.add("Nsit Ubium");
                categories2.add("Obot Akara");
                categories2.add("Okobo");
                categories2.add("Onna");
                categories2.add("Oron");
                categories2.add("Oruk Anam");
                categories2.add("Udung Uko");
                categories2.add("Ukanafun");
                categories2.add("Uruan");
                categories2.add("Urue-Offong/Oruko");
                categories2.add("Uyo");
                java.util.Collections.sort(categories2, Collator.getInstance());
                categories2.add(0,"Select LGA");

                ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories2);
                dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner2.setAdapter(dataAdapter2);
            } else if (item.equals("Anambra")) {
                List<String> categories2 = new ArrayList<String>();

                categories2.add("Aguata");
                categories2.add("Anambra East");
                categories2.add("Anambra West");
                categories2.add("Anaocha");
                categories2.add("Awka North");
                categories2.add("Awka South");
                categories2.add("Ayamelum");
                categories2.add("Dunukofia");
                categories2.add("Ekwusigo");
                categories2.add("Idemili North");
                categories2.add("Idemili south");
                categories2.add("Ihiala");
                categories2.add("Njikoka");
                categories2.add("Nnewi North");
                categories2.add("Nnewi South");
                categories2.add("Ogbaru");
                categories2.add("Onitsha North");
                categories2.add("Onitsha South");
                categories2.add("Orumba North");
                categories2.add("Orumba South");
                categories2.add("Oyi");

                java.util.Collections.sort(categories2, Collator.getInstance());
                categories2.add(0,"Select LGA");

                ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories2);
                dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner2.setAdapter(dataAdapter2);
            } else if (item.equals("Bauchi")) {
                List<String> categories2 = new ArrayList<String>();
                categories2.add("Alkaleri");
                categories2.add("Bauchi");
                categories2.add("Bogoro");
                categories2.add("Damban");
                categories2.add("Darazo");
                categories2.add("Dass");
                categories2.add("Ganjuwa");
                categories2.add("Giade");
                categories2.add("Itas/Gadau");
                categories2.add("Jama'are");
                categories2.add("Katagum");
                categories2.add("Kirfi");
                categories2.add("Misau");
                categories2.add("Ningi");
                categories2.add("Shira");
                categories2.add("Tafawa-Balewa");
                categories2.add("Toro");
                categories2.add("Warji");
                categories2.add("Zaki");
                java.util.Collections.sort(categories2, Collator.getInstance());
                categories2.add(0,"Select LGA");

                ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories2);
                dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner2.setAdapter(dataAdapter2);
            } else if (item.equals("Bayelsa")) {
                List<String> categories2 = new ArrayList<String>();
                categories2.add("Brass");
                categories2.add("Ekeremor");
                categories2.add("Kolokuma/Opokuma");
                categories2.add("Nembe");
                categories2.add("Ogbia");
                categories2.add("Sagbama");
                categories2.add("Southern Jaw");
                categories2.add("Yenegoa");

                java.util.Collections.sort(categories2, Collator.getInstance());
                categories2.add(0,"Select LGA");

                ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories2);
                dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner2.setAdapter(dataAdapter2);
            } else if (item.equals("Benue")) {
                List<String> categories2 = new ArrayList<String>();
                categories2.add("Ado");
                categories2.add("Agatu");
                categories2.add("Apa");
                categories2.add("Buruku");
                categories2.add("Gboko");
                categories2.add("Guma");
                categories2.add("Gwer East");
                categories2.add("Gwer West");
                categories2.add("Katsina-Ala");
                categories2.add("Konshisha");
                categories2.add("Kwande");
                categories2.add("Logo");
                categories2.add("Makurdi");
                categories2.add("Obi");
                categories2.add("Ogbadibo");
                categories2.add("Oju");
                categories2.add("Okpokwu");
                categories2.add("Ohimini");
                categories2.add("Oturkpo");
                categories2.add("Tarka");
                categories2.add("Ukum");
                categories2.add("Ushongo");
                categories2.add("Vandeikya");

                java.util.Collections.sort(categories2, Collator.getInstance());
                categories2.add(0,"Select LGA");

                ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories2);
                dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner2.setAdapter(dataAdapter2);
            } else if (item.equals("Bornu")) {
                List<String> categories2 = new ArrayList<String>();
                categories2.add("Abadam");
                categories2.add("Askira/Uba");
                categories2.add("Bama");
                categories2.add("Bayo");
                categories2.add("Biu");
                categories2.add("Chibok");
                categories2.add("Damboa");
                categories2.add("Dikwa");
                categories2.add("Gubio");
                categories2.add("Guzamala");
                categories2.add("Gwoza");
                categories2.add("Hawul");
                categories2.add("Jere");
                categories2.add("Kaga");
                categories2.add("Kala/Balge");
                categories2.add("Konduga");
                categories2.add("Kukawa");
                categories2.add("Kwaya Kusar");
                categories2.add("Mafa");
                categories2.add("Magumeri");
                categories2.add("Maiduguri");
                categories2.add("Marte");
                categories2.add("Mobbar");
                categories2.add("Monguno");
                categories2.add("Ngala");
                categories2.add("Nganzai");
                categories2.add("Shani");
                java.util.Collections.sort(categories2, Collator.getInstance());
                categories2.add(0,"Select LGA");

                ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories2);
                dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner2.setAdapter(dataAdapter2);
            } else if (item.equals("Cross River")) {
                List<String> categories2 = new ArrayList<String>();
                categories2.add("Akpabuyo");
                categories2.add("Odukpani");
                categories2.add("Akamkpa");
                categories2.add("Biase");
                categories2.add("Abi");
                categories2.add("Ikom");
                categories2.add("Yarkur");
                categories2.add("Odubra");
                categories2.add("Boki");
                categories2.add("Ogoja");
                categories2.add("Yala");
                categories2.add("Obanliku");
                categories2.add("Obudu");
                categories2.add("Calabar South");
                categories2.add("Etung");
                categories2.add("Bekwara");
                categories2.add("Bakassi");
                categories2.add("Calabar Municipality");
                java.util.Collections.sort(categories2, Collator.getInstance());
                categories2.add(0,"Select LGA");

                ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories2);
                dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner2.setAdapter(dataAdapter2);
            } else if (item.equals("Delta")) {
                List<String> categories2 = new ArrayList<String>();
                categories2.add("Oshimili");
                categories2.add("Aniocha");
                categories2.add("Aniocha South");
                categories2.add("Ika South");
                categories2.add("Ika North-East");
                categories2.add("Ndokwa West");
                categories2.add("Ndokwa East");
                categories2.add("Isoko south");
                categories2.add("Isoko North");
                categories2.add("Bomadi");
                categories2.add("Burutu");
                categories2.add("Ughelli South");
                categories2.add("Ughelli North");
                categories2.add("Ethiope West");
                categories2.add("Ethiope East");
                categories2.add("Sapele");
                categories2.add("Okpe");
                categories2.add("Warri North");
                categories2.add("Warri South");
                categories2.add("Uvwie");
                categories2.add("Udu");
                categories2.add("Warri Central");
                categories2.add("Ukwani");
                categories2.add("Oshimili North");
                categories2.add("Patani");

                java.util.Collections.sort(categories2, Collator.getInstance());
                categories2.add(0,"Select LGA");

                ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories2);
                dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner2.setAdapter(dataAdapter2);
            }
            else if (item.equals("Ebonyi"))
            {
                List<String> categories2 = new ArrayList<String>();
                categories2.add("Afikpo South");
                categories2.add("Afikpo North");
                categories2.add("Onicha");
                categories2.add("Ohaozara");
                categories2.add("Abakaliki");
                categories2.add("Ishielu");
                categories2.add("lkwo");
                categories2.add("Ezza");
                categories2.add("Ezza South");
                categories2.add("Ohaukwu");
                categories2.add("Ebonyi");
                categories2.add("Ivo");

                java.util.Collections.sort(categories2, Collator.getInstance());
                categories2.add(0,"Select LGA");

                ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories2);
                dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner2.setAdapter(dataAdapter2);
            }
            else if (item.equals("Edo")) {
                List<String> categories2 = new ArrayList<String>();
                categories2.add("Esan North-East");
                categories2.add("Esan Central");
                categories2.add("Esan West");
                categories2.add("Egor");
                categories2.add("Ukpoba");
                categories2.add("Central");
                categories2.add("Etsako Central");
                categories2.add("Igueben");
                categories2.add("Oredo");
                categories2.add("Ovia SouthWest");
                categories2.add("Ovia South-East");
                categories2.add("Orhionwon");
                categories2.add("Uhunmwonde");
                categories2.add("Etsako East");
                categories2.add("Esan South-East");

                java.util.Collections.sort(categories2, Collator.getInstance());
                categories2.add(0,"Select LGA");

                ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories2);
                dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner2.setAdapter(dataAdapter2);
            } else if (item.equals("Ekitti")) {
                List<String> categories2 = new ArrayList<String>();
                categories2.add("Ado");
                categories2.add("Ekiti-East");
                categories2.add("Ekiti-West");
                categories2.add("Emure/Ise/Orun");
                categories2.add("Ekiti South-West");
                categories2.add("Ikare");
                categories2.add("Irepodun");
                categories2.add("Ijero");
                categories2.add("Ido/Osi");
                categories2.add("Ijero");
                categories2.add("Ikare-Irepodun");
                categories2.add("Ijero");
                categories2.add("Ido/Osi");
                categories2.add("Moba");
                categories2.add("Efon");
                categories2.add("Ise/Orun");
                categories2.add("ejemeje.MJ");

                java.util.Collections.sort(categories2, Collator.getInstance());
                categories2.add(0,"Select LGA");

                ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories2);
                dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner2.setAdapter(dataAdapter2);
            }
            else if (item.equals("Enugu")) {
                List<String> categories2 = new ArrayList<String>();
                categories2.add("Enugu South");
                categories2.add("Igbo-Eze South");
                categories2.add("Enugu North");
                categories2.add("Nkanu");
                categories2.add("Udi Agwu");
                categories2.add("Oji-River");
                categories2.add("Ezeagu");
                categories2.add("IgboEze North");
                categories2.add("Isi-Uzo");
                categories2.add("ukka");
                categories2.add("Nsukka");
                categories2.add("Igbo-Ekiti");
                categories2.add("Uzo-Uwani");
                categories2.add("Enugu Eas");
                categories2.add("Aninri");
                categories2.add("Nkanu East");

                java.util.Collections.sort(categories2, Collator.getInstance());
                categories2.add(0,"Select LGA");

                ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories2);
                dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner2.setAdapter(dataAdapter2);
            } else if (item.equals("Gombe")) {
                List<String> categories2 = new ArrayList<String>();

                categories2.add("Akko");
                categories2.add("Balanga");
                categories2.add("Billiri");
                categories2.add("Dukku");
                categories2.add("Kaltungo");
                categories2.add("Kwami");
                categories2.add("Shomgom");
                categories2.add("Funakaye");
                categories2.add("Gombe");
                categories2.add("Nafada/Bajoga");
                categories2.add("Yamaltu/Delta");
                java.util.Collections.sort(categories2, Collator.getInstance());
                categories2.add(0,"Select LGA");

                ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories2);
                dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner2.setAdapter(dataAdapter2);
            } else if (item.equals("Imo")) {
                List<String> categories2 = new ArrayList<String>();
                categories2.add("Aboh-Mbaise");
                categories2.add("Ahiazu-Mbaise");
                categories2.add("Ehime-Mbano");
                categories2.add("Ezinihitte");
                categories2.add("Ideato North");
                categories2.add("Ihitte/Uboma");
                categories2.add("Ikeduru");
                categories2.add("Isiala Mbano");
                categories2.add("Isu");
                categories2.add("Mbaitoli");
                categories2.add("Ngor-Okpala");
                categories2.add("Njaba");
                categories2.add("Nwangele");
                categories2.add("Nkwerre");
                categories2.add("Obowo");
                categories2.add("Oguta");
                categories2.add("Ohaji/Egbema");
                categories2.add("Okigwe");
                categories2.add("Orlu");
                categories2.add("Orsu");
                categories2.add("Oru East");
                categories2.add("Oru West");
                categories2.add("Owerri-Municipal");
                categories2.add("Owerri North");
                categories2.add("Owerri West");
                java.util.Collections.sort(categories2, Collator.getInstance());
                categories2.add(0,"Select LGA");

                ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories2);
                dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner2.setAdapter(dataAdapter2);
            } else if (item.equals("Jigawa")) {
                List<String> categories2 = new ArrayList<String>();
                categories2.add("Auyo");
                categories2.add("Babura");
                categories2.add("Birni Kudu");
                categories2.add("Biriniwa");
                categories2.add("Buji");
                categories2.add("Dutse");
                categories2.add("Gagarawa");
                categories2.add("Garki");
                categories2.add("Gumel");
                categories2.add("Guri");
                categories2.add("Gwaram");
                categories2.add("Gwiwa");
                categories2.add("Hadejia");
                categories2.add("Jahun");
                categories2.add("Kafin Hausa");
                categories2.add("Kaugama Kazaure");
                categories2.add("Kiri Kasamma");
                categories2.add("Kiyawa");
                categories2.add("Maigatari");
                categories2.add("Malam Madori");
                categories2.add("Miga");
                categories2.add("Ringim");
                categories2.add("Roni");
                categories2.add("Taura");
                categories2.add("Yankwashi");

                java.util.Collections.sort(categories2, Collator.getInstance());
                categories2.add(0,"Select LGA");
                ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories2);
                dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner2.setAdapter(dataAdapter2);
            } else if (item.equals("Kaduna")) {
                List<String> categories2 = new ArrayList<String>();

                categories2.add("Birni-Gwari");
                categories2.add("Chikun");
                categories2.add("Giwa");
                categories2.add("Igabi");
                categories2.add("Ikara");
                categories2.add("jaba");
                categories2.add("Jema'a");
                categories2.add("Kachia");
                categories2.add("Kaduna North");
                categories2.add("Kaduna South");
                categories2.add("Kagarko");
                categories2.add("Kajuru");
                categories2.add("Kaura");
                categories2.add("Kubau");
                categories2.add("Kudan");
                categories2.add("Lere");
                categories2.add("Makarfi");
                categories2.add("Sabon-Gari");
                categories2.add("Sanga");
                categories2.add("Soba");
                categories2.add("Zango-Kataf");
                categories2.add("Zaria");

                java.util.Collections.sort(categories2, Collator.getInstance());
                categories2.add(0,"Select LGA");

                ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories2);
                dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner2.setAdapter(dataAdapter2);
            } else if (item.equals("Kano")) {
                List<String> categories2 = new ArrayList<String>();
                categories2.add("Ajingi");
                categories2.add("Albasu");
                categories2.add("Bagwai");
                categories2.add("Bebeji");
                categories2.add("Bichi");
                categories2.add("Bunkure");
                categories2.add("Dala");
                categories2.add("Dambatta");
                categories2.add("Dawakin Kudu");
                categories2.add("Dawakin Tofa");
                categories2.add("Doguwa");
                categories2.add("Fagge");
                categories2.add("Gabasawa");
                categories2.add("Garko");
                categories2.add("Garum");
                categories2.add("Mallam");
                categories2.add("Gaya");
                categories2.add("Gezawa");
                categories2.add("Gwarzo");
                categories2.add("Kabo");
                categories2.add("Kano Municipal");
                categories2.add("Karaye");
                categories2.add("Kibiya");
                categories2.add("Kiru");
                categories2.add("kumbotso");
                categories2.add("Kunchi");
                categories2.add("Kura");
                categories2.add("Madobi");
                categories2.add("Makoda");
                categories2.add("Makoda");
                categories2.add(" Minjibir");
                categories2.add("Nasarawa");
                categories2.add("Nasarawa");
                categories2.add("Rano");
                categories2.add("Rimin Gado");
                categories2.add("Rogo");
                categories2.add("Shanono");
                categories2.add("Sumaila");
                categories2.add("Takali");
                categories2.add("Tarauni");
                categories2.add("Tofa");
                categories2.add("Tsanyawa");
                categories2.add("Tudun Wada");
                categories2.add("Ungogo");
                categories2.add("Warawa");

                java.util.Collections.sort(categories2, Collator.getInstance());
                categories2.add(0,"Select LGA");

                ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories2);
                dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner2.setAdapter(dataAdapter2);
            } else if (item.equals("Katsina")) {
                List<String> categories2 = new ArrayList<String>();
                categories2.add("Bakori");
                categories2.add("Batagarawa");
                categories2.add("Batsari");
                categories2.add("Baure");
                categories2.add("Bindawa");
                categories2.add("Charanchi");
                categories2.add("Dandume");
                categories2.add("Danja");
                categories2.add("Daura");
                categories2.add("Dutsi");
                categories2.add("Dutsin-Ma");
                categories2.add("Faskari");
                categories2.add("Funtua");
                categories2.add("Ingawa");
                categories2.add("Jibia");
                categories2.add("Kafur");
                categories2.add("Kaita");
                categories2.add("Kankara");
                categories2.add("Kankia");
                categories2.add("Katsina");
                categories2.add("Kurfi");
                categories2.add("Kusada");
                categories2.add("Mai'Adua");
                categories2.add("Malumfashi");
                categories2.add("Mani");
                categories2.add("Mashi");
                categories2.add("Matazuu");
                categories2.add("Musawa");
                categories2.add("Rimi");
                categories2.add("Sabuwa");
                categories2.add("Safana ");
                categories2.add("Sandamu ");
                categories2.add("Zango");
                java.util.Collections.sort(categories2, Collator.getInstance());
                categories2.add(0,"Select LGA");

                ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories2);
                dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner2.setAdapter(dataAdapter2);
            } else if (item.equals("Kebbi")) {
                List<String> categories2 = new ArrayList<String>();
                categories2.add("Aleiro");
                categories2.add("Arewa-Dandi");
                categories2.add("Argungu");
                categories2.add("Augie");
                categories2.add("Bagudo");
                categories2.add("Birnin Kebbi");
                categories2.add("Bunza");
                categories2.add("Dandi");
                categories2.add("Fakai");
                categories2.add("Gwandu");
                categories2.add("Jega");
                categories2.add("Kalgo");
                categories2.add("Koko/Besse");
                categories2.add("Maiyama");
                categories2.add("Ngaski");
                categories2.add("Sakaba");
                categories2.add("Shanga");
                categories2.add("Suru");
                categories2.add("Wasagu/Danko");
                categories2.add("Yauri");
                categories2.add("Zuru");

                java.util.Collections.sort(categories2, Collator.getInstance());
                categories2.add(0,"Select LGA");

                ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories2);
                dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner2.setAdapter(dataAdapter2);
            } else if (item.equals("Kogi")) {
                List<String> categories2 = new ArrayList<String>();
                categories2.add("Adavi");
                categories2.add("Ajaokuta");
                categories2.add("Ankpa");
                categories2.add("Bassa");
                categories2.add("Dekina");
                categories2.add("Ibaji");
                categories2.add("Idah");
                categories2.add("Igalamela-Odolu");
                categories2.add("Ijumu");
                categories2.add("Kabba/Bunu");
                categories2.add("Kogi");
                categories2.add("Lokoja");
                categories2.add("Mopa-Muro");
                categories2.add("Ofu");
                categories2.add("Ogori/Mangongo");
                categories2.add("Okehi");
                categories2.add("Okene");
                categories2.add("Olamabolo");
                categories2.add("Omala");
                categories2.add("Yagba East");
                categories2.add("Yagba West");

                java.util.Collections.sort(categories2, Collator.getInstance());
                categories2.add(0,"Select LGA");

                ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories2);
                dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner2.setAdapter(dataAdapter2);
            } else if (item.equals("Kwara")) {
                List<String> categories2 = new ArrayList<String>();
                categories2.add("Asa");
                categories2.add("Baruten");
                categories2.add("Edu");
                categories2.add("Ekiti");
                categories2.add("Ifelodun");
                categories2.add("Ilorin East");
                categories2.add("Ilorin West");
                categories2.add("Irepodun");
                categories2.add("Isin");
                categories2.add("Kaiama");
                categories2.add("Moro");
                categories2.add("Offa");
                categories2.add("Oke-Ero");
                categories2.add("Oyun");
                categories2.add("Pategi");

                java.util.Collections.sort(categories2, Collator.getInstance());
                categories2.add(0,"Select LGA");

                ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories2);
                dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner2.setAdapter(dataAdapter2);
            } else if (item.equals("Lagos")) {
                List<String> categories2 = new ArrayList<String>();
                categories2.add("Agege");
                categories2.add("Ajeromi-Ifelodun");
                categories2.add("Alimosho");
                categories2.add("Amuwo-Odofin");
                categories2.add("Apapa");
                categories2.add("Badagry");
                categories2.add("Epe");
                categories2.add("Eti-Osa");
                categories2.add("Ibeju/Lekki");
                categories2.add("Ifako-Ijaye");
                categories2.add("Ikeja");
                categories2.add("Ikorodu");
                categories2.add("Kosofe");
                categories2.add("Lagos Island");
                categories2.add("Lagos Mainland");
                categories2.add("Mushin");
                categories2.add("Ojo");
                categories2.add("Oshodi-Isolo");
                categories2.add("Shomolu");
                categories2.add("Surulere");

                java.util.Collections.sort(categories2, Collator.getInstance());
                categories2.add(0,"Select LGA");

                ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories2);
                dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner2.setAdapter(dataAdapter2);
            } else if (item.equals("Nasarawa")) {
                List<String> categories2 = new ArrayList<String>();
                categories2.add("Akwanga");
                categories2.add("Awe");
                categories2.add("Doma");
                categories2.add("Karu");
                categories2.add("Keana");
                categories2.add("Keffi");
                categories2.add("Kokona");
                categories2.add("Lafia");
                categories2.add("Nasarawa");
                categories2.add("Nasarawa-Eggon");
                categories2.add("Obi");
                categories2.add("Toto");
                categories2.add("Wamba");

                java.util.Collections.sort(categories2, Collator.getInstance());
                categories2.add(0,"Select LGA");

                ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories2);
                dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner2.setAdapter(dataAdapter2);
            } else if (item.equals("Niger")) {
                List<String> categories2 = new ArrayList<String>();
                categories2.add("Agaie");
                categories2.add("Agwara");
                categories2.add("Bida");
                categories2.add("Borgu");
                categories2.add("Bosso");
                categories2.add("Chanchaga");
                categories2.add("Edati");
                categories2.add("Gbako");
                categories2.add("Gurara");
                categories2.add("Katcha");
                categories2.add("Kontagora");
                categories2.add("Lapai");
                categories2.add("Lavun");
                categories2.add("Magama");
                categories2.add("Mariga");
                categories2.add("Mashegu");
                categories2.add("Mokwa");
                categories2.add("Muya");
                categories2.add("Pailoro");
                categories2.add("Rafi");
                categories2.add("Rijau");
                categories2.add("Tafa");
                categories2.add("Wushishi");

                java.util.Collections.sort(categories2, Collator.getInstance());
                categories2.add(0,"Select LGA");

                ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories2);
                dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner2.setAdapter(dataAdapter2);
            } else if (item.equals("Ogun")) {
                List<String> categories2 = new ArrayList<String>();
                categories2.add("Abeokuta North");
                categories2.add("Abeokuta South");
                categories2.add("Ado-Odo/Ota");
                categories2.add("Egbado North");
                categories2.add("Egbado South");
                categories2.add("Ewekoro");
                categories2.add("Ifo");
                categories2.add("Ijebu East");
                categories2.add("Ijebu North");
                categories2.add("Ijebu North East");
                categories2.add("Ijebu Ode");
                categories2.add("Ikenne");
                categories2.add("Imeko-Afon");
                categories2.add("Ipokia");
                categories2.add("Obafemi-Owode");
                categories2.add("Ogun Waterside");
                categories2.add("Odeda");
                categories2.add("Odogbolu");
                categories2.add("Remo North");
                categories2.add("Shagamu");

                java.util.Collections.sort(categories2, Collator.getInstance());
                categories2.add(0,"Select LGA");

                ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories2);
                dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner2.setAdapter(dataAdapter2);
            } else if (item.equals("Ondo")) {
                List<String> categories2 = new ArrayList<String>();
                categories2.add("Akoko North East");
                categories2.add("Akoko North West");
                categories2.add("Akoko South Akure East");
                categories2.add("Akoko South West");
                categories2.add("Akure North");
                categories2.add("Akure South");
                categories2.add("Ese-Odo");
                categories2.add("Idanre");
                categories2.add("Ifedore");
                categories2.add("Ilaje");
                categories2.add("Ile-Oluji");
                categories2.add("Okeigbo");
                categories2.add("Irele");
                categories2.add("Odigbo");
                categories2.add("Okitipupa");
                categories2.add("Ondo East");
                categories2.add("Ondo West");
                categories2.add("Ose");
                categories2.add("Owo");

                java.util.Collections.sort(categories2, Collator.getInstance());
                categories2.add(0,"Select LGA");

                ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories2);
                dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner2.setAdapter(dataAdapter2);
            } else if (item.equals("Osun")) {
                List<String> categories2 = new ArrayList<String>();
                categories2.add("Aiyedade");
                categories2.add("Aiyedire");
                categories2.add("Atakumosa East");
                categories2.add("Atakumosa West");
                categories2.add("Boluwaduro");
                categories2.add("Boripe");
                categories2.add("Ede North");
                categories2.add("Ede South");
                categories2.add("Egbedore");
                categories2.add("Ejigbo");
                categories2.add("Ife Central");
                categories2.add("Ife East");
                categories2.add("Ife North");
                categories2.add("Ife South");
                categories2.add("Ifedayo");
                categories2.add("Ifelodun");
                categories2.add("Ila");
                categories2.add("Ilesha East");
                categories2.add("Ilesha West");
                categories2.add("Irepodun");
                categories2.add("Irewole");
                categories2.add("Isokan");
                categories2.add("Iwo");
                categories2.add("Obokun");
                categories2.add("Odo-Otin");
                categories2.add("Ola-Oluwa");
                categories2.add("Olorunda");
                categories2.add("Oriade");
                categories2.add("Orolu");
                categories2.add("Osogbo");

                java.util.Collections.sort(categories2, Collator.getInstance());
                categories2.add(0,"Select LGA");

                ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories2);
                dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner2.setAdapter(dataAdapter2);
            } else if (item.equals("Plateau")) {
                List<String> categories2 = new ArrayList<String>();
                categories2.add("Barikin Ladi");
                categories2.add("Bassa");
                categories2.add("Bokkos");
                categories2.add("Jos East");
                categories2.add("Jos North");
                categories2.add("Jos South");
                categories2.add("Kanam");
                categories2.add("Kanke");
                categories2.add("Langtang North");
                categories2.add("Langtang South");
                categories2.add("Mangu");
                categories2.add("Mikang");
                categories2.add("Pankshin");
                categories2.add("Qua'an Pan");
                categories2.add("Riyom");
                categories2.add("Shendam");
                categories2.add("Wase");

                java.util.Collections.sort(categories2, Collator.getInstance());
                categories2.add(0,"Select LGA");

                ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories2);
                dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner2.setAdapter(dataAdapter2);
            } else if (item.equals("Rivers")) {
                List<String> categories2 = new ArrayList<String>();
                categories2.add("Abua/Odual");
                categories2.add("Ahoada East");
                categories2.add("Ahoada West");
                categories2.add("Akuku Toru");
                categories2.add("Andoni");
                categories2.add("Asari-Toru");
                categories2.add("Bonny");
                categories2.add("Degema");
                categories2.add("Emohua");
                categories2.add("Eleme");
                categories2.add("Etche");
                categories2.add("Gokana");
                categories2.add("Ikwerre");
                categories2.add("Khana");
                categories2.add("Obia/Akpor");
                categories2.add("Ogba/Egbema/Ndoni");
                categories2.add("Ogu/Bolo");
                categories2.add("Okrika");
                categories2.add("Omumma");
                categories2.add("Opobo/Nkoro");
                categories2.add("Oyigbo");
                categories2.add("Port-Harcourt");
                categories2.add("Tai");

                java.util.Collections.sort(categories2, Collator.getInstance());
                categories2.add(0,"Select LGA");

                ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories2);
                dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner2.setAdapter(dataAdapter2);
            } else if (item.equals("Sokoto")) {
                List<String> categories2 = new ArrayList<String>();
                categories2.add("Binji");
                categories2.add("Bodinga");
                categories2.add("Dange-shnsi");
                categories2.add("Gada");
                categories2.add("Goronyo");
                categories2.add("Gudu");
                categories2.add("Gawabawa");
                categories2.add("Illela");
                categories2.add("Isa");
                categories2.add("Kware");
                categories2.add("kebbe");
                categories2.add("Rabah");
                categories2.add("Sabon birni");
                categories2.add("Shagari");
                categories2.add("Silame");
                categories2.add("Sokoto North");
                categories2.add("Sokoto South");
                categories2.add("Tambuwal");
                categories2.add("Tqngaza");
                categories2.add("Tureta");
                categories2.add("Wamako");
                categories2.add("Wurno");
                categories2.add("Yabo");

                java.util.Collections.sort(categories2, Collator.getInstance());
                categories2.add(0,"Select LGA");
                ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories2);
                dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner2.setAdapter(dataAdapter2);
            }

            else if (item.equals("Taraba")) {
                List<String> categories2 = new ArrayList<String>();
                categories2.add("Ardo-kola");
                categories2.add("Bali");
                categories2.add("Donga");
                categories2.add("Gashaka");
                categories2.add("Cassol");
                categories2.add("Ibi");
                categories2.add("Jalingo");
                categories2.add("Karin-Lamido");
                categories2.add("Kurmi");
                categories2.add("Lau");
                categories2.add("Sardauna");
                categories2.add("Takum");
                categories2.add("Ussa");
                categories2.add("Wukari");
                categories2.add("Yorro");
                categories2.add("Zing");

                java.util.Collections.sort(categories2, Collator.getInstance());
                categories2.add(0,"Select LGA");
                ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories2);
                dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner2.setAdapter(dataAdapter2);
            }

            else if (item.equals("Yobe")) {
                List<String> categories2 = new ArrayList<String>();
                categories2.add("Bade");
                categories2.add("Bursari");
                categories2.add("Damaturu");
                categories2.add("Fika");
                categories2.add("Fune");
                categories2.add("Geidam");
                categories2.add("Gujba");
                categories2.add("Gulani");
                categories2.add("Jakusko");
                categories2.add("Karasuwa");
                categories2.add("Karawa");
                categories2.add("Machina");
                categories2.add("Nangere");
                categories2.add("Nguru Potiskum");
                categories2.add("Tarmua");
                categories2.add("Yunusari");
                categories2.add("Yusufari");

                java.util.Collections.sort(categories2, Collator.getInstance());
                categories2.add(0,"Select LGA");
                ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories2);
                dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner2.setAdapter(dataAdapter2);
            }

            else if (item.equals("Zamfara")) {
                List<String> categories2 = new ArrayList<String>();
                categories2.add("Anka");
                categories2.add("Bakura");
                categories2.add("Birnin Magaji");
                categories2.add("Bukkuyum");
                categories2.add("Bungudu");
                categories2.add("Gummi");
                categories2.add("Gusau");
                categories2.add("Kaura");
                categories2.add("Namoda");
                categories2.add("Maradun");
                categories2.add("Maru");
                categories2.add("Shinkafi");
                categories2.add("Talata Mafara");
                categories2.add("Tsafe");
                categories2.add("Zurmi");

                java.util.Collections.sort(categories2, Collator.getInstance());
                categories2.add(0,"Select LGA");
                ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories2);
                dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner2.setAdapter(dataAdapter2);
            }
        }
        else if(parent.toString().contains("spinner2")) {
            district=parent.getItemAtPosition(position).toString();
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }


    @Override
    protected void onResume() {
        super.onResume();

        ib.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder build = new AlertDialog.Builder(Signup.this);
                build.setTitle("Upload profile image using -");

                build.setPositiveButton("camera", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                        mImageCaptureUri = Uri.fromFile(new File(Environment.getExternalStorageDirectory(),
                                "tmp_avatar_" + String.valueOf(System.currentTimeMillis()) + ".jpeg"));

                        intent.putExtra(MediaStore.EXTRA_OUTPUT, mImageCaptureUri);

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
                        startActivityForResult(galleryIntent, PICK_FROM_FILE);
                    }
                });
                build.show();
            }
        });


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name= e2.getText().toString();
                email= e3.getText().toString();
                mobile= e4.getText().toString();
                password= e5.getText().toString();
                address=e6.getText().toString();
                username=e7.getText().toString();
                if(district.equals("Select district") ||item.equals("Select state") || e2.getText().toString().isEmpty() || e3.getText().toString().isEmpty() || e4.getText().toString().isEmpty() || e5.getText().toString().isEmpty() || e6.getText().toString().isEmpty() || e7.getText().toString().isEmpty())
                {
                    Toast.makeText(Signup.this, "Please fill all the details", Toast.LENGTH_SHORT).show();
                }
                else if(!email.contains("@") || !email.contains("."))
                {
                    Toast.makeText(Signup.this, "Please check your email id.", Toast.LENGTH_SHORT).show();
                }
                else if(mobile.length()!=10)
                {
                    Toast.makeText(Signup.this, "Please check your mobile no.", Toast.LENGTH_SHORT).show();
                }
                else if(password.length()<8)
                {
                    Toast.makeText(Signup.this, "Password length cannot be less than 8 characters", Toast.LENGTH_SHORT).show();
                }
                else if(b==null)
                {
                    Toast.makeText(Signup.this, "Please select profile image", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    new JSONP().execute();
                }
            }
        });
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
                    photo.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                    b = baos.toByteArray();
                    encodedImage = Base64.encodeToString(b, Base64.URL_SAFE);

                    ib.setImageBitmap(photo);
                    //new JSONP().execute(encodedImage);

                }

                File f = new File(mImageCaptureUri.getPath());
                if (f.exists()) f.delete();

                break;
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

        ib.setImageBitmap(bitm);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitm.compress(Bitmap.CompressFormat.PNG, 85, baos);
        b = baos.toByteArray();
        encodedImage = Base64.encodeToString(b, Base64.DEFAULT);
    }

    public String getRealPathFromURI(Uri uri) {

        if (uri == null) {
            Toast.makeText(Signup.this, "Image not selected", Toast.LENGTH_LONG).show();
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

    class JSONP extends AsyncTask<Void,Void,String> {
        String jsonurl, jsonstring;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            jsonurl ="http://bjprogrammer.co.nf/nigeriantraffic/signup.php";

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
                String postdata = URLEncoder.encode("username") + "=" + URLEncoder.encode(username) + "&" + URLEncoder.encode("password") + "=" + URLEncoder.encode(password)
                        + "&" + URLEncoder.encode("mobile") + "=" + URLEncoder.encode(mobile)+ "&" + URLEncoder.encode("email") + "=" + URLEncoder.encode(email)
                        + "&" + URLEncoder.encode("pic") + "=" + URLEncoder.encode(encodedImage)+ "&" + URLEncoder.encode("address") + "=" + URLEncoder.encode(address)
                        + "&" + URLEncoder.encode("LGA") + "=" + URLEncoder.encode(district)+ "&" + URLEncoder.encode("state") + "=" + URLEncoder.encode(item)
                        + "&" + URLEncoder.encode("name") + "=" + URLEncoder.encode(name);

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
                SplashScreen.logout=true;
                DatabaseHelper dh = new DatabaseHelper(Signup.this);
                boolean b = dh.deletedata(1);
                boolean b1 = dh.insertdata(username, password,SplashScreen.logout.toString());
                Toast.makeText(Signup.this,s, Toast.LENGTH_SHORT).show();
                Intent bj=new Intent(Signup.this,MainActivity.class);
                bj.putExtra("username",username);
                startActivity(bj);
                finish();
            }
            else
            {
                Toast.makeText(Signup.this,s, Toast.LENGTH_LONG).show();
            }
        }
    }
}
