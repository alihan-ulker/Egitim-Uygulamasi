package com.example.tez.Activitys.Activitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.tez.Activitys.Helpers.BoSQLiteHelper;
import com.example.tez.Activitys.Helpers.CppSQLiteHelper;
import com.example.tez.R;


public class IcerikActivity extends AppCompatActivity {

    TextView ques;
    Button OptA, OptB, OptC, OptD;
    com.example.tez.Activitys.Helpers.CppSQLiteHelper CppSQLiteHelper;
    BoSQLiteHelper BoSQLiteHelper;
    public int  m = 1,  j = 0;
    String global = null, Ques,Opta,Optb,Optc,Optd,Ans;
    String ders ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.konu_icerik);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        Bundle extras = getIntent().getExtras();
        String value = extras.getString("intent");
        Bundle extras2 = getIntent().getExtras();
        String value2 = extras2.getString("ders");

        ders=value2;

        j=Integer.parseInt(value);

        CppSQLiteHelper = new CppSQLiteHelper(this);
        CppSQLiteHelper.createDatabase();
        CppSQLiteHelper.openDatabase();
        CppSQLiteHelper.getWritableDatabase();

        BoSQLiteHelper =new BoSQLiteHelper(this);
        BoSQLiteHelper.createDatabase();
        BoSQLiteHelper.openDatabase();
        BoSQLiteHelper.getWritableDatabase();

        OptA = (Button) findViewById(R.id.OptionA);
        OptB = (Button) findViewById(R.id.OptionB);
        OptC = (Button) findViewById(R.id.OptionC);
        OptD = (Button) findViewById(R.id.OptionD);
        ques = (TextView) findViewById(R.id.Questions);

        final Button btnileri;
        final Button btngeri;
        btnileri = (Button)findViewById(R.id.btnileri12);
        btngeri = (Button)findViewById(R.id.btngeri12);
        btngeri.setEnabled(false);
        btnileri.setEnabled(true);

        if(ders.equals("cpp")) {
            Ques = CppSQLiteHelper.readQuestion(1, j);
        }
        else{Ques= BoSQLiteHelper.readQuestion(m,j);
        }
        OptA.setVisibility(View.INVISIBLE);
        OptB.setVisibility(View.INVISIBLE);
        OptC.setVisibility(View.INVISIBLE);
        OptD.setVisibility(View.INVISIBLE);


        ques.setText(Ques);

        View.OnClickListener listener =  new View.OnClickListener() {
            public void onClick(View view) {
                if(view == findViewById(R.id.btnileri12))
                {   m++;

                    btngeri.setEnabled(true);
                    btnileri.setVisibility(View.VISIBLE);

                    if(ders.equals("cpp")) {
                        Ques = CppSQLiteHelper.readQuestion(m, j);
                        Opta = CppSQLiteHelper.readOptionA(m, j);
                        Optb = CppSQLiteHelper.readOptionB(m, j);
                        Optc = CppSQLiteHelper.readOptionC(m, j);
                        Optd = CppSQLiteHelper.readOptionD(m, j);
                        Ans = CppSQLiteHelper.readAnswer(m, j);

                    }
                    else if(ders.equals("bo")){

                        Ques = BoSQLiteHelper.readQuestion(m, j);
                        Opta = BoSQLiteHelper.readOptionA(m, j);
                        Optb = BoSQLiteHelper.readOptionB(m, j);
                        Optc = BoSQLiteHelper.readOptionC(m, j);
                        Optd = BoSQLiteHelper.readOptionD(m, j);
                        Ans = BoSQLiteHelper.readAnswer(m, j);


                    }
                    else{
                        Log.e("aaaaaaaaaaaaaaa",ders);
                    }

                    ques.setText(Ques);

                    if(Optb!=null) {
                        OptA.setText(Opta);
                        OptB.setText(Optb);
                        OptC.setText(Optc);
                        OptD.setText(Optd);


                        btnileri.setVisibility(View.INVISIBLE);

                        OptA.setVisibility(View.VISIBLE);
                        OptB.setVisibility(View.VISIBLE);
                        OptC.setVisibility(View.VISIBLE);
                        OptD.setVisibility(View.VISIBLE);
                        OptA.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if(OptA.getText().equals(Ans))
                                {
                                    Snackbar.make(v, "                               Doğru Cevap ☺", Snackbar.LENGTH_SHORT).show();
                                    btnileri.setVisibility(View.VISIBLE);

                                }
                                else {
                                    Snackbar.make(v, "                               Yanlış\t Cevap ", Snackbar.LENGTH_SHORT).show();

                                }
                            }
                        });
                        OptB.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if(OptB.getText().equals(Ans))
                                {
                                    Snackbar.make(v, "                               Doğru Cevap ☺", Snackbar.LENGTH_SHORT).show();
                                    btnileri.setVisibility(View.VISIBLE);
                                }
                                else {
                                    Snackbar.make(v, "                               Yanlış\t Cevap ", Snackbar.LENGTH_SHORT).show();
                                }
                            }
                        });
                        OptC.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if(OptC.getText().equals(Ans))
                                {
                                    Snackbar.make(v, "                               Doğru Cevap ☺", Snackbar.LENGTH_SHORT).show();
                                    btnileri.setVisibility(View.VISIBLE);

                                }
                                else {
                                    Snackbar.make(v, "                               Yanlış\t Cevap ", Snackbar.LENGTH_SHORT).show();

                                }
                            }
                        });
                        OptD.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if(OptD.getText().equals(Ans))
                                {
                                    Snackbar.make(v, "                               Doğru Cevap ☺", Snackbar.LENGTH_SHORT).show();
                                    btnileri.setVisibility(View.VISIBLE);

                                }
                                else {
                                    Snackbar.make(v, "                               Yanlış\t Cevap ", Snackbar.LENGTH_SHORT).show();

                                }
                            }
                        });

                    }
                    else{
                        OptA.setVisibility(View.INVISIBLE);
                        OptB.setVisibility(View.INVISIBLE);
                        OptC.setVisibility(View.INVISIBLE);
                        OptD.setVisibility(View.INVISIBLE);
                    }
                   if(Ques.equals("")){
                        btnileri.setEnabled(false);

                       OptA.setVisibility(View.INVISIBLE);
                       OptB.setVisibility(View.INVISIBLE);
                       OptC.setVisibility(View.INVISIBLE);
                       OptD.setVisibility(View.INVISIBLE);
                       ques.setText("\t\t\t\t\t\t\t\tKonuyu Başarı İle Tamamladınız! \n \t\t\t\t\t\t\t\t\t\tBir sonraki konuya geçiniz.");
                    }
                }
                else if (view == findViewById(R.id.btngeri12)){

                    m--;
                    btnileri.setEnabled(true);

                    if(ders.equals("cpp")) {
                        Ques = CppSQLiteHelper.readQuestion(m, j);
                        Opta = CppSQLiteHelper.readOptionA(m, j);
                        Optb = CppSQLiteHelper.readOptionB(m, j);
                        Optc = CppSQLiteHelper.readOptionC(m, j);
                        Optd = CppSQLiteHelper.readOptionD(m, j);
                        Ans = CppSQLiteHelper.readAnswer(m, j);
                    }
                    else if(ders.equals("bo")){
                        Ques = BoSQLiteHelper.readQuestion(m, j);
                        Opta = BoSQLiteHelper.readOptionA(m, j);
                        Optb = BoSQLiteHelper.readOptionB(m, j);
                        Optc = BoSQLiteHelper.readOptionC(m, j);
                        Optd = BoSQLiteHelper.readOptionD(m, j);
                        Ans = BoSQLiteHelper.readAnswer(m, j);
                    }
                    else{
                        Log.e("aaaaaaaaaaaaaaa",ders);
                    }

                    btnileri.setVisibility(View.VISIBLE);

                    ques.setText(Ques);
                    if(Optb!=null) {
                        OptA.setText(Opta);
                        OptB.setText(Optb);
                        OptC.setText(Optc);
                        OptD.setText(Optd);
                        btnileri.setVisibility(View.INVISIBLE);

                        OptA.setVisibility(View.VISIBLE);
                        OptB.setVisibility(View.VISIBLE);
                        OptC.setVisibility(View.VISIBLE);
                        OptD.setVisibility(View.VISIBLE);

                        OptA.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if(OptA.getText().equals(Ans))
                                {
                                    Snackbar.make(v, "                               Doğru Cevap ☺", Snackbar.LENGTH_SHORT).show();
                                    btnileri.setVisibility(View.VISIBLE);

                                }
                                else {
                                    Snackbar.make(v, "                               Yanlış\t Cevap ", Snackbar.LENGTH_SHORT).show();

                                }
                            }
                        });
                        OptB.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if(OptB.getText().equals(Ans))
                                {
                                    Snackbar.make(v, "                               Doğru Cevap ☺", Snackbar.LENGTH_SHORT).show();
                                    btnileri.setVisibility(View.VISIBLE);

                                }
                                else {
                                    Snackbar.make(v, "                               Yanlış\t Cevap ", Snackbar.LENGTH_SHORT).show();

                                }
                            }
                        });
                        OptC.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if(OptC.getText().equals(Ans))
                                {
                                    Snackbar.make(v, "                               Doğru Cevap ☺", Snackbar.LENGTH_SHORT).show();
                                    btnileri.setVisibility(View.VISIBLE);

                                }
                                else {
                                    Snackbar.make(v, "                               Yanlış\t Cevap ", Snackbar.LENGTH_SHORT).show();

                                }
                            }
                        });
                        OptD.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if(OptD.getText().equals(Ans))
                                {
                                    Snackbar.make(v, "                               Doğru Cevap ☺", Snackbar.LENGTH_SHORT).show();
                                    btnileri.setVisibility(View.VISIBLE);

                                }
                                else {
                                    Snackbar.make(v, "                               Yanlış\t Cevap ", Snackbar.LENGTH_SHORT).show();

                                }
                            }
                        });

                    }
                    else{
                        OptA.setVisibility(View.INVISIBLE);
                        OptB.setVisibility(View.INVISIBLE);
                        OptC.setVisibility(View.INVISIBLE);
                        OptD.setVisibility(View.INVISIBLE);
                    }
                    if(m==1)
                    {
                        btngeri.setEnabled(false);
                    }
                }
            }
        };
        btnileri.setOnClickListener(listener);
        btngeri.setOnClickListener(listener);
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId()==android.R.id.home)
        {   if(ders.equals("cpp")) {
            Intent intent = new Intent(getApplicationContext(), CplusplusActivity.class);
            NavUtils.navigateUpTo(this, intent);
            return true;
            }
            else{
            Intent intent = new Intent(getApplicationContext(), BoActivity.class);
            NavUtils.navigateUpTo(this, intent);
            return true;
            }
        }
        return  super.onOptionsItemSelected(item);
    }
}
