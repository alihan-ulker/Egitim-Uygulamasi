package com.example.tez.Activitys.Activitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.tez.R;


public class BoActivity extends AppCompatActivity {


    private Button btnkonu1,btnkonu2,btnkonu3,btnkonu4,btnkonu5,btnkonu6,btnkonu7,btnkonu8,btnkonu9,btnkonu10;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bilgisayar_org);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        btnkonu1 =(Button)findViewById(R.id.btnBoKonu1);
        btnkonu2 =(Button)findViewById(R.id.btnBoKonu2);
        btnkonu3 =(Button)findViewById(R.id.btnBoKonu3);
        btnkonu4 =(Button)findViewById(R.id.btnBoKonu4);
        btnkonu5 =(Button)findViewById(R.id.btnBoKonu5);
        btnkonu6 =(Button)findViewById(R.id.btnBoKonu6);
        btnkonu7 =(Button)findViewById(R.id.btnBoKonu7);
        btnkonu8 =(Button)findViewById(R.id.btnBoKonu8);
        btnkonu9 =(Button)findViewById(R.id.btnBoKonu9);
        btnkonu10 =(Button)findViewById(R.id.btnBoGenelTest);

        btnkonu1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BoActivity.this, IcerikActivity.class);
                intent.putExtra("intent", "1");
                intent.putExtra("ders","bo");

                startActivity(intent);
            }
        });
        btnkonu2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BoActivity.this, IcerikActivity.class);
                intent.putExtra("intent", "2");
                intent.putExtra("ders","bo");

                startActivity(intent);
            }
        });
        btnkonu3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BoActivity.this, IcerikActivity.class);
                intent.putExtra("intent", "3");
                intent.putExtra("ders","bo");

                startActivity(intent);
            }
        });
        btnkonu4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BoActivity.this, IcerikActivity.class);
                intent.putExtra("intent", "4");
                intent.putExtra("ders","bo");

                startActivity(intent);
            }
        });
        btnkonu5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BoActivity.this, IcerikActivity.class);
                intent.putExtra("intent", "5");
                intent.putExtra("ders","bo");

                startActivity(intent);
            }
        });
        btnkonu6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BoActivity.this, IcerikActivity.class);
                intent.putExtra("intent", "6");
                intent.putExtra("ders","bo");

                startActivity(intent);
            }
        });
        btnkonu7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BoActivity.this, IcerikActivity.class);
                intent.putExtra("intent", "7");
                intent.putExtra("ders","bo");

                startActivity(intent);
            }
        });
        btnkonu8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BoActivity.this, IcerikActivity.class);
                intent.putExtra("intent", "8");
                intent.putExtra("ders","bo");

                startActivity(intent);
            }
        });
        btnkonu9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BoActivity.this, IcerikActivity.class);
                intent.putExtra("intent", "9");
                intent.putExtra("ders","bo");

                startActivity(intent);
            }
        });
        btnkonu10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BoActivity.this, GenelTestActivity.class);
                intent.putExtra("intent", "c8");
                intent.putExtra("ders","bo");
                startActivity(intent);
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId()==android.R.id.home)
        {
            Intent intent =new Intent(getApplicationContext(), MenuActivity.class);
            NavUtils.navigateUpTo(this,intent);
            return true;
        }
        return  super.onOptionsItemSelected(item);
    }

}