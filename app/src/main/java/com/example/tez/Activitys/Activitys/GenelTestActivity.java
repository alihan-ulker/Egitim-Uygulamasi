package com.example.tez.Activitys.Activitys;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.tez.Activitys.Helpers.BoSoruSQLiteHelper;
import com.example.tez.Activitys.Helpers.CppSoruSQLiteHelper;
import com.example.tez.R;

import java.util.Locale;
import java.util.Objects;



public class GenelTestActivity extends AppCompatActivity {

    TextView ques,textViewSayac;
    Button OptA, OptB, OptC, OptD;
    CppSoruSQLiteHelper cppSoruSQLiteHelper;
    BoSoruSQLiteHelper boSoruSQLiteHelper;
    public int  m = 1;
    String global = null, Ques,Opta,Optb,Optc,Optd,Ans;
    String ders ,testsonu="";
    int dogru =0,yanlis =0,cevaplanansoru =0;
    private static final long baslangicSure = 15000;//600000
    private long kalanZaman = baslangicSure;
    private CountDownTimer sayac;
    private boolean mTimerRunning;
    public String kalanZamanFormat,kontrol="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_genel_test);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        Bundle extras2 = getIntent().getExtras();
        String value2 = extras2.getString("ders");

        ders=value2;

        textViewSayac = findViewById(R.id.textViewSure);

        startTimer();
        timerGuncelle();

        cppSoruSQLiteHelper = new CppSoruSQLiteHelper(this);
        cppSoruSQLiteHelper.createDatabase();
        cppSoruSQLiteHelper.openDatabase();
        cppSoruSQLiteHelper.getWritableDatabase();

        boSoruSQLiteHelper =new BoSoruSQLiteHelper(this);
        boSoruSQLiteHelper.createDatabase();
        boSoruSQLiteHelper.openDatabase();
        boSoruSQLiteHelper.getWritableDatabase();

        OptA = (Button) findViewById(R.id.btnA);
        OptB = (Button) findViewById(R.id.btnB);
        OptC = (Button) findViewById(R.id.btnC);
        OptD = (Button) findViewById(R.id.btnD);
        ques = (TextView) findViewById(R.id.textViewSoru);



        if(ders.equals("cpp")) {
            Ques = cppSoruSQLiteHelper.readQuestion(m);
            Opta = cppSoruSQLiteHelper.readOptionA(m);
            Optb = cppSoruSQLiteHelper.readOptionB(m);
            Optc = cppSoruSQLiteHelper.readOptionC(m);
            Optd = cppSoruSQLiteHelper.readOptionD(m);
            Ans = cppSoruSQLiteHelper.readAnswer(m);

        }
        else{
            Ques= boSoruSQLiteHelper.readQuestion(m);
            Opta = boSoruSQLiteHelper.readOptionA(m);
            Optb = boSoruSQLiteHelper.readOptionB(m);
            Optc = boSoruSQLiteHelper.readOptionC(m);
            Optd = boSoruSQLiteHelper.readOptionD(m);
            Ans = boSoruSQLiteHelper.readAnswer(m);

       }



        ques.setText(Ques);
        OptA.setText(Opta);
        OptB.setText(Optb);
        OptC.setText(Optc);
        OptD.setText(Optd);

        View.OnClickListener listener =  new View.OnClickListener() {
            public void onClick(View view) {
                  m++;
                  cevaplanansoru++;

                    if(ders.equals("cpp")) {
                        Ques = cppSoruSQLiteHelper.readQuestion(m);
                        Opta = cppSoruSQLiteHelper.readOptionA(m);
                        Optb = cppSoruSQLiteHelper.readOptionB(m);
                        Optc = cppSoruSQLiteHelper.readOptionC(m);
                        Optd = cppSoruSQLiteHelper.readOptionD(m);
                        Ans = cppSoruSQLiteHelper.readAnswer(m);

                    }
                    else if(ders.equals("bo")){

                        Ques = boSoruSQLiteHelper.readQuestion(m);
                        Opta = boSoruSQLiteHelper.readOptionA(m);
                        Optb = boSoruSQLiteHelper.readOptionB(m);
                        Optc = boSoruSQLiteHelper.readOptionC(m);
                        Optd = boSoruSQLiteHelper.readOptionD(m);
                        Ans = boSoruSQLiteHelper.readAnswer(m);
                    }


                    ques.setText(Ques);

                    if(Optb!=null) {
                        OptA.setText(Opta);
                        OptB.setText(Optb);
                        OptC.setText(Optc);
                        OptD.setText(Optd);

                        OptA.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if(OptA.getText().equals(Ans))
                                {
                                    dogru++;
                                    m++;
                                    cevaplanansoru++;
                                    if(ders.equals("cpp")) {
                                        Ques = cppSoruSQLiteHelper.readQuestion(m);
                                        Opta = cppSoruSQLiteHelper.readOptionA(m);
                                        Optb = cppSoruSQLiteHelper.readOptionB(m);
                                        Optc = cppSoruSQLiteHelper.readOptionC(m);
                                        Optd = cppSoruSQLiteHelper.readOptionD(m);
                                        Ans = cppSoruSQLiteHelper.readAnswer(m);

                                    }
                                    else{
                                        Ques= boSoruSQLiteHelper.readQuestion(m);
                                        Opta = boSoruSQLiteHelper.readOptionA(m);
                                        Optb = boSoruSQLiteHelper.readOptionB(m);
                                        Optc = boSoruSQLiteHelper.readOptionC(m);
                                        Optd = boSoruSQLiteHelper.readOptionD(m);
                                        Ans = boSoruSQLiteHelper.readAnswer(m);

                                    }



                                    ques.setText(Ques);
                                    OptA.setText(Opta);
                                    OptB.setText(Optb);
                                    OptC.setText(Optc);
                                    OptD.setText(Optd);
                                }
                                else {
                                    yanlis++;
                                    m++;
                                    cevaplanansoru++;
                                    if(ders.equals("cpp")) {
                                        Ques = cppSoruSQLiteHelper.readQuestion(m);
                                        Opta = cppSoruSQLiteHelper.readOptionA(m);
                                        Optb = cppSoruSQLiteHelper.readOptionB(m);
                                        Optc = cppSoruSQLiteHelper.readOptionC(m);
                                        Optd = cppSoruSQLiteHelper.readOptionD(m);
                                        Ans = cppSoruSQLiteHelper.readAnswer(m);

                                    }
                                    else{
                                        Ques= boSoruSQLiteHelper.readQuestion(m);
                                        Opta = boSoruSQLiteHelper.readOptionA(m);
                                        Optb = boSoruSQLiteHelper.readOptionB(m);
                                        Optc = boSoruSQLiteHelper.readOptionC(m);
                                        Optd = boSoruSQLiteHelper.readOptionD(m);
                                        Ans = boSoruSQLiteHelper.readAnswer(m);

                                    }



                                    ques.setText(Ques);
                                    OptA.setText(Opta);
                                    OptB.setText(Optb);
                                    OptC.setText(Optc);
                                    OptD.setText(Optd);
                                }
                                if(Ques.equals("")){

                                    OptA.setVisibility(View.INVISIBLE);
                                    OptB.setVisibility(View.INVISIBLE);
                                    OptC.setVisibility(View.INVISIBLE);
                                    OptD.setVisibility(View.INVISIBLE);
                                    ques.setText("Test_Sonu");
                                    testsonu="Test_Sonu";
                                    Intent i = new Intent(GenelTestActivity.this, SonucActivity.class);
                                    i.putExtra("dogru",dogru);
                                    i.putExtra("yanlis",yanlis);
                                    i.putExtra("cevaplanan",cevaplanansoru);
                                    i.putExtra("ders",ders);
                                    startActivity(i);
                                    finish();
                                }

                            }
                        });
                        OptB.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if(OptB.getText().equals(Ans))
                                {
                                    dogru++;
                                    m++;
                                    cevaplanansoru++;
                                    if(ders.equals("cpp")) {
                                        Ques = cppSoruSQLiteHelper.readQuestion(m);
                                        Opta = cppSoruSQLiteHelper.readOptionA(m);
                                        Optb = cppSoruSQLiteHelper.readOptionB(m);
                                        Optc = cppSoruSQLiteHelper.readOptionC(m);
                                        Optd = cppSoruSQLiteHelper.readOptionD(m);
                                        Ans = cppSoruSQLiteHelper.readAnswer(m);

                                    }
                                    else{
                                        Ques= boSoruSQLiteHelper.readQuestion(m);
                                        Opta = boSoruSQLiteHelper.readOptionA(m);
                                        Optb = boSoruSQLiteHelper.readOptionB(m);
                                        Optc = boSoruSQLiteHelper.readOptionC(m);
                                        Optd = boSoruSQLiteHelper.readOptionD(m);
                                        Ans = boSoruSQLiteHelper.readAnswer(m);

                                    }



                                    ques.setText(Ques);
                                    OptA.setText(Opta);
                                    OptB.setText(Optb);
                                    OptC.setText(Optc);
                                    OptD.setText(Optd);
                                }
                                else {
                                    yanlis++;
                                    m++;
                                    cevaplanansoru++;
                                    if(ders.equals("cpp")) {
                                        Ques = cppSoruSQLiteHelper.readQuestion(m);
                                        Opta = cppSoruSQLiteHelper.readOptionA(m);
                                        Optb = cppSoruSQLiteHelper.readOptionB(m);
                                        Optc = cppSoruSQLiteHelper.readOptionC(m);
                                        Optd = cppSoruSQLiteHelper.readOptionD(m);
                                        Ans = cppSoruSQLiteHelper.readAnswer(m);

                                    }
                                    else{
                                        Ques= boSoruSQLiteHelper.readQuestion(m);
                                        Opta = boSoruSQLiteHelper.readOptionA(m);
                                        Optb = boSoruSQLiteHelper.readOptionB(m);
                                        Optc = boSoruSQLiteHelper.readOptionC(m);
                                        Optd = boSoruSQLiteHelper.readOptionD(m);
                                        Ans = boSoruSQLiteHelper.readAnswer(m);

                                    }



                                    ques.setText(Ques);
                                    OptA.setText(Opta);
                                    OptB.setText(Optb);
                                    OptC.setText(Optc);
                                    OptD.setText(Optd);
                                }
                                if(Ques.equals("")){

                                    OptA.setVisibility(View.INVISIBLE);
                                    OptB.setVisibility(View.INVISIBLE);
                                    OptC.setVisibility(View.INVISIBLE);
                                    OptD.setVisibility(View.INVISIBLE);
                                    ques.setText("Test_Sonu");
                                    testsonu="Test_Sonu";
                                    Intent i = new Intent(GenelTestActivity.this, SonucActivity.class);
                                    i.putExtra("dogru",dogru);
                                    i.putExtra("yanlis",yanlis);
                                    i.putExtra("cevaplanan",cevaplanansoru);
                                    i.putExtra("ders",ders);
                                    startActivity(i);
                                    finish();
                                }
                            }
                        });
                        OptC.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if(OptC.getText().equals(Ans))
                                {
                                    dogru++;
                                    m++;
                                    cevaplanansoru++;
                                    if(ders.equals("cpp")) {
                                        Ques = cppSoruSQLiteHelper.readQuestion(m);
                                        Opta = cppSoruSQLiteHelper.readOptionA(m);
                                        Optb = cppSoruSQLiteHelper.readOptionB(m);
                                        Optc = cppSoruSQLiteHelper.readOptionC(m);
                                        Optd = cppSoruSQLiteHelper.readOptionD(m);
                                        Ans = cppSoruSQLiteHelper.readAnswer(m);

                                    }
                                    else{
                                        Ques= boSoruSQLiteHelper.readQuestion(m);
                                        Opta = boSoruSQLiteHelper.readOptionA(m);
                                        Optb = boSoruSQLiteHelper.readOptionB(m);
                                        Optc = boSoruSQLiteHelper.readOptionC(m);
                                        Optd = boSoruSQLiteHelper.readOptionD(m);
                                        Ans = boSoruSQLiteHelper.readAnswer(m);

                                    }



                                    ques.setText(Ques);
                                    OptA.setText(Opta);
                                    OptB.setText(Optb);
                                    OptC.setText(Optc);
                                    OptD.setText(Optd);
                                }
                                else {
                                    yanlis++;
                                    m++;
                                    cevaplanansoru++;
                                    if(ders.equals("cpp")) {
                                        Ques = cppSoruSQLiteHelper.readQuestion(m);
                                        Opta = cppSoruSQLiteHelper.readOptionA(m);
                                        Optb = cppSoruSQLiteHelper.readOptionB(m);
                                        Optc = cppSoruSQLiteHelper.readOptionC(m);
                                        Optd = cppSoruSQLiteHelper.readOptionD(m);
                                        Ans = cppSoruSQLiteHelper.readAnswer(m);

                                    }
                                    else{
                                        Ques= boSoruSQLiteHelper.readQuestion(m);
                                        Opta = boSoruSQLiteHelper.readOptionA(m);
                                        Optb = boSoruSQLiteHelper.readOptionB(m);
                                        Optc = boSoruSQLiteHelper.readOptionC(m);
                                        Optd = boSoruSQLiteHelper.readOptionD(m);
                                        Ans = boSoruSQLiteHelper.readAnswer(m);

                                    }



                                    ques.setText(Ques);
                                    OptA.setText(Opta);
                                    OptB.setText(Optb);
                                    OptC.setText(Optc);
                                    OptD.setText(Optd);

                                }
                                if(Ques.equals("")){

                                    OptA.setVisibility(View.INVISIBLE);
                                    OptB.setVisibility(View.INVISIBLE);
                                    OptC.setVisibility(View.INVISIBLE);
                                    OptD.setVisibility(View.INVISIBLE);
                                    ques.setText("Test_Sonu");
                                    testsonu="Test_Sonu";
                                    Intent i = new Intent(GenelTestActivity.this, SonucActivity.class);
                                    i.putExtra("dogru",dogru);
                                    i.putExtra("yanlis",yanlis);
                                    i.putExtra("cevaplanan",cevaplanansoru);
                                    i.putExtra("ders",ders);
                                    startActivity(i);
                                    finish();
                                }
                            }
                        });
                        OptD.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if(OptD.getText().equals(Ans))
                                {
                                    dogru++;
                                    m++;
                                    cevaplanansoru++;
                                    if(ders.equals("cpp")) {
                                        Ques = cppSoruSQLiteHelper.readQuestion(m);
                                        Opta = cppSoruSQLiteHelper.readOptionA(m);
                                        Optb = cppSoruSQLiteHelper.readOptionB(m);
                                        Optc = cppSoruSQLiteHelper.readOptionC(m);
                                        Optd = cppSoruSQLiteHelper.readOptionD(m);
                                        Ans = cppSoruSQLiteHelper.readAnswer(m);

                                    }
                                    else{
                                        Ques= boSoruSQLiteHelper.readQuestion(m);
                                        Opta = boSoruSQLiteHelper.readOptionA(m);
                                        Optb = boSoruSQLiteHelper.readOptionB(m);
                                        Optc = boSoruSQLiteHelper.readOptionC(m);
                                        Optd = boSoruSQLiteHelper.readOptionD(m);
                                        Ans = boSoruSQLiteHelper.readAnswer(m);

                                    }



                                    ques.setText(Ques);
                                    OptA.setText(Opta);
                                    OptB.setText(Optb);
                                    OptC.setText(Optc);
                                    OptD.setText(Optd);
                                }
                                else {
                                    yanlis++;
                                    m++;
                                    cevaplanansoru++;
                                    if(ders.equals("cpp")) {
                                        Ques = cppSoruSQLiteHelper.readQuestion(m);
                                        Opta = cppSoruSQLiteHelper.readOptionA(m);
                                        Optb = cppSoruSQLiteHelper.readOptionB(m);
                                        Optc = cppSoruSQLiteHelper.readOptionC(m);
                                        Optd = cppSoruSQLiteHelper.readOptionD(m);
                                        Ans = cppSoruSQLiteHelper.readAnswer(m);

                                    }
                                    else{
                                        Ques= boSoruSQLiteHelper.readQuestion(m);
                                        Opta = boSoruSQLiteHelper.readOptionA(m);
                                        Optb = boSoruSQLiteHelper.readOptionB(m);
                                        Optc = boSoruSQLiteHelper.readOptionC(m);
                                        Optd = boSoruSQLiteHelper.readOptionD(m);
                                        Ans = boSoruSQLiteHelper.readAnswer(m);

                                    }



                                    ques.setText(Ques);
                                    OptA.setText(Opta);
                                    OptB.setText(Optb);
                                    OptC.setText(Optc);
                                    OptD.setText(Optd);

                                }
                                if(Ques.equals("")){

                                    OptA.setVisibility(View.INVISIBLE);
                                    OptB.setVisibility(View.INVISIBLE);
                                    OptC.setVisibility(View.INVISIBLE);
                                    OptD.setVisibility(View.INVISIBLE);
                                    ques.setText("Test_Sonu");
                                    testsonu="Test_Sonu";
                                    Intent i = new Intent(GenelTestActivity.this, SonucActivity.class);
                                    i.putExtra("dogru",dogru);
                                    i.putExtra("yanlis",yanlis);
                                    i.putExtra("cevaplanan",cevaplanansoru);
                                    i.putExtra("ders",ders);
                                    startActivity(i);
                                    finish();
                                }
                            }
                        });

                    }


                }


        };
        OptA.setOnClickListener(listener);
        OptB.setOnClickListener(listener);
        OptC.setOnClickListener(listener);
        OptD.setOnClickListener(listener);

    }



    private void startTimer() {
        sayac = new CountDownTimer(kalanZaman, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                kalanZaman = millisUntilFinished;
                timerGuncelle();
            }

            @Override
            public void onFinish() {
                mTimerRunning = false;
                if(!testsonu.equals("Test_Sonu")&& kontrol==""){

                    //Test_Sonuna gelemedi demekki Sonuç sayfası açılmadı Süre bitince aç!

                    Intent i = new Intent(GenelTestActivity.this, SonucActivity.class);
                    i.putExtra("dogru", dogru);
                    i.putExtra("yanlis", yanlis);
                    i.putExtra("cevaplanan", cevaplanansoru);
                    i.putExtra("ders", ders);
                    i.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                    startActivity(i);
                    finish();
                }
            }
        }.start();

        mTimerRunning = true;

    }


    private void timerGuncelle() {
        int dakika = (int) (kalanZaman / 1000) / 60;
        int saniye = (int) (kalanZaman / 1000) % 60;

        kalanZamanFormat = String.format(Locale.getDefault(), "%02d:%02d", dakika, saniye);

        textViewSayac.setText(kalanZamanFormat);
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId()==android.R.id.home)
        {   if(ders.equals("cpp")) {

            if(!kalanZamanFormat.equals("00:01")){
                AlertDialog.Builder builder = new AlertDialog.Builder(GenelTestActivity.this);
                builder.setTitle("Çıkış");
                builder.setMessage("Çıkış Yapıyorsunuz.Skorunuz Kaydedilmicek!");
                builder.setNegativeButton("İptal", new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog, int id) {
                            //Hiç bişe yapma
                    }
                });
                builder.setPositiveButton("Tamam", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dogru =0;
                        yanlis =0;
                        cevaplanansoru =0;
                        startActivity(new Intent(GenelTestActivity.this, CplusplusActivity.class));
                        finish();
                    }

                });
                builder.show();
            }


        }
        else{
            if(!kalanZamanFormat.equals("00:01")){
                AlertDialog.Builder builder = new AlertDialog.Builder(GenelTestActivity.this);
                builder.setTitle("Çıkış");
                builder.setMessage("Çıkış Yapıyorsunuz.Skorunuz Kaydedilmicek!");
                builder.setNegativeButton("İptal", new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog, int id) {
                        //Hiç bişe yapma
                    }
                });
                builder.setPositiveButton("Tamam", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dogru =0;
                        yanlis =0;
                        cevaplanansoru =0;
                        kontrol ="activity_son";
                        startActivity(new Intent(GenelTestActivity.this, BoActivity.class));
                        finish();
                    }

                });
                builder.show();
            }
        }}
        return  super.onOptionsItemSelected(item);
    }
    @Override
    public void onBackPressed(){

          if(ders.equals("cpp")) {
              //C++ konular sayfasına gidiş

              if (!kalanZamanFormat.equals("00:01")) {
                  AlertDialog.Builder builder = new AlertDialog.Builder(GenelTestActivity.this);
                  builder.setTitle("Çıkış");
                  builder.setMessage("Çıkış Yapıyorsunuz.Skorunuz Kaydedilmicek!");
                  builder.setNegativeButton("İptal", new DialogInterface.OnClickListener() {
                      public void onClick(DialogInterface dialog, int id) {
                          //Hiç bişe yapma
                      }
                  });
                  builder.setPositiveButton("Tamam", new DialogInterface.OnClickListener() {
                      public void onClick(DialogInterface dialog, int id) {
                          dogru = 0;
                          yanlis = 0;
                          cevaplanansoru = 0;
                          startActivity(new Intent(GenelTestActivity.this, CplusplusActivity.class));
                          finish();
                      }

                  });
                  builder.show();
              }
          }
          else{
              //Bilgisayar organizasyonuna  konular sayfasına gidiş
            if(!kalanZamanFormat.equals("00:01")){
                AlertDialog.Builder builder = new AlertDialog.Builder(GenelTestActivity.this);
                builder.setTitle("Çıkış");
                builder.setMessage("Çıkış Yapıyorsunuz.Skorunuz Kaydedilmicek!");
                builder.setNegativeButton("İptal", new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog, int id) {
                        //Hiç bişe yapma
                    }
                });
                builder.setPositiveButton("Tamam", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dogru =0;
                        yanlis =0;
                        cevaplanansoru =0;
                        kontrol ="activity_son";
                        startActivity(new Intent(GenelTestActivity.this, BoActivity.class));
                        finish();
                    }

                });
                builder.show();
            }
        }
    }
}