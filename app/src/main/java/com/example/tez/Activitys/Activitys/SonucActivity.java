package com.example.tez.Activitys.Activitys;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;
import com.example.tez.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Objects;



public class SonucActivity extends AppCompatActivity {
    int dogru = 0, yanlis = 0, cevaplanansoru = 0,eskiskor=0,scor=0;
    String ders;

    private FirebaseFirestore db;
    private FirebaseAuth auth;
    FirebaseUser firebaseUser;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sonuc);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);


        Bundle extras2 = getIntent().getExtras();
        int degerdogru = extras2.getInt("dogru");
        int degeryanlis = extras2.getInt("yanlis");
        int degercevaplanan = extras2.getInt("cevaplanan");
        ders = extras2.getString("ders");

        dogru = degerdogru;
        yanlis = degeryanlis;
        cevaplanansoru = degercevaplanan-1;
        scor = 50 * dogru;

        TextView  textViewDogru = (TextView) findViewById(R.id.textViewDogru);
        TextView  textViewYanlis = (TextView) findViewById(R.id.textViewYanlis);
        TextView  textViewCevaplanan = (TextView) findViewById(R.id.textViewCevaplanan);
        TextView  textViewSkor = (TextView) findViewById(R.id.score);
        TextView  texViewMesaj = (TextView) findViewById(R.id.you);


        textViewCevaplanan.setText("Cevaplanan          :" + cevaplanansoru);
        textViewDogru.setText("Doğru                    :" + dogru);
        textViewYanlis.setText("Yanlış                    :" + yanlis);
        textViewSkor.setText("Skor  :    " + scor);

        if (scor < 801)
            texViewMesaj.setText("Kendini Geliştirmelisin");
        else if (scor < 1501)
            texViewMesaj.setText("Ortalama Değerdesin");
        else if (scor < 2001)
            texViewMesaj.setText("Ortalamanın Üstündesin ");
        else
            texViewMesaj.setText("Çok iyisin!");


        auth = FirebaseAuth.getInstance();
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        db = FirebaseFirestore.getInstance();

        readUserSkor();
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

    private void updateData(){
        if(ders.equals("cpp")) {
            DocumentReference documentReference = db.collection("kullanıcıları").document(auth.getCurrentUser().getEmail());
            documentReference.update("cskor", scor);
        }
        else{
            DocumentReference documentReference = db.collection("kullanıcıları").document(auth.getCurrentUser().getEmail());
            documentReference.update("boskor", scor);
        }
    }

    private void readUserSkor()
    {
        if(ders.equals("cpp")) {
            DocumentReference documentReference = db.collection("kullanıcıları").document(auth.getCurrentUser().getEmail());
            documentReference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    DocumentSnapshot documentSnapshot = task.getResult();
                    StringBuilder stringBuilder = new StringBuilder("");
                    stringBuilder.append(documentSnapshot.get("cskor"));
                    String a=stringBuilder.toString();
                    eskiskor=Integer.parseInt(a);
                    if(eskiskor<scor) {
                        updateData();
                        Toast.makeText(SonucActivity.this, "Tebrikler Yüksek Skorunu Geçtin ! ", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(SonucActivity.this, "Yüksek Skorunu Geçemedin! ", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
        else{
            DocumentReference documentReference = db.collection("kullanıcıları").document(auth.getCurrentUser().getEmail());
            documentReference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    DocumentSnapshot documentSnapshot=task.getResult();
                    StringBuilder stringBuilder=new StringBuilder("");
                    stringBuilder.append(documentSnapshot.get("boskor"));
                    String a=stringBuilder.toString();
                    eskiskor=Integer.parseInt(a);

                    if(eskiskor<scor) {
                        updateData();
                        Toast.makeText(SonucActivity.this, "Tebrikler Yüksek Skorunu Geçtin ! ", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(SonucActivity.this, "Yüksek Skorunu Geçemedin! ", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

}
