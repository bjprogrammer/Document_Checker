package com.example.bobby.trafficpolice;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

public class doccheckcomplete extends AppCompatActivity {
    ImageButton ib1,ib2;
    String plateno,mobile;
    String status;
    ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doccheckcomplete);

        mobile=getIntent().getStringExtra("mobile");
        ib1=(ImageButton)findViewById(R.id.imageButton4);
        ib2=(ImageButton)findViewById(R.id.imageButton5);
        status=getIntent().getStringExtra("status");
        plateno=getIntent().getStringExtra("plateno");

        iv=(ImageView)findViewById(R.id.imageView9);
    }

    @Override
    protected void onResume() {
        super.onResume();

        if(status.equalsIgnoreCase("nocomment"))
        {
            iv.setImageResource(R.drawable.nocomment);
        }
        else if(status.equalsIgnoreCase("seizedocument"))
        {
            iv.setImageResource(R.drawable.seizedocument);
        }
        else if(status.equalsIgnoreCase("payfine"))
        {
            iv.setImageResource(R.drawable.payfine);
        }
        else if(status.equalsIgnoreCase("warning"))
        {
             iv.setImageResource(R.drawable.warning);
        }

        ib1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(doccheckcomplete.this,docstatus.class);
                intent.putExtra("plateno",plateno);
                intent.putExtra("mobile",mobile);
                startActivity(intent);
                finish();
            }
        });

        ib2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(doccheckcomplete.this,MainActivity.class);
                intent.putExtra("mobile", mobile);
                startActivity(intent);
                finish();
            }
        });
    }
}
