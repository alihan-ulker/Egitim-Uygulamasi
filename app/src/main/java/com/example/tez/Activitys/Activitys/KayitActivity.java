package com.example.tez.Activitys.Activitys;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.tez.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;




public class KayitActivity extends AppCompatActivity {

    private FirebaseFirestore firebaseFirestore;
    private EditText kayitMail;
    private EditText kayitSifre;
    private Button buttonRegister;
    private FirebaseAuth mAuth;
    private String mail;
    private String sifre;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kayit);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        kayitMail = (EditText)findViewById(R.id.edtKayitMail);
        kayitSifre = (EditText)findViewById(R.id.edtKayitSifre);
        buttonRegister = (Button) findViewById(R.id.btnKayitol);

        mAuth = FirebaseAuth.getInstance();
        firebaseFirestore= FirebaseFirestore.getInstance();

        // Kayıt Ol butonuna tıklandığında
        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mail = kayitMail.getText().toString();
                sifre = kayitSifre.getText().toString();
                if(sifre.isEmpty() || mail.isEmpty()){

                    Toast.makeText(getApplicationContext(),"Lütfen gerekli alanları doldurunuz!",Toast.LENGTH_SHORT).show();

                }else{

                    progressDialog  = ProgressDialog.show(KayitActivity.this,"Doğrulama","Bilgiler Kontrol ediliyor...",true);
                    progressDialog.show();
                    registerFunc();
                }

            }
        });
    }

    private void registerFunc() {

        mAuth.createUserWithEmailAndPassword(mail,sifre)
                .addOnCompleteListener(KayitActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            String sifre=kayitSifre.getText().toString();
                            String mail=kayitMail.getText().toString();

                            Map<String,Object> userMap= new HashMap<>();
                            userMap.put("sifre",sifre);
                            userMap.put("mail",mail);
                          //userMap.put("cbölüm",0);
                            userMap.put("cskor",0);
                          //userMap.put("bobölüm",0);
                            userMap.put("boskor",0);
                            firebaseFirestore.collection("kullanıcıları").document(mail).set(userMap);
                            progressDialog.dismiss();
                            Toast.makeText(getApplicationContext(),"Kayıt Başarılı!",Toast.LENGTH_LONG).show();

                            Intent i = new Intent(KayitActivity.this,MenuActivity.class);
                            startActivity(i);
                            finish();
                        }
                        else{
                            progressDialog.dismiss();
                            Toast.makeText(getApplicationContext(),"Lütfen Geçerli Mail Adresi Girdiğinizden Ve Şifre Uzunluğunun 6 Karakterden Fazla Olduğunu Kontrol Ediniz!",Toast.LENGTH_LONG).show();
                        }

                    }
                });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId()==android.R.id.home)
        {
            Intent intent =new Intent(getApplicationContext(), MainActivity.class);
            NavUtils.navigateUpTo(this,intent);
            return true;
        }
        return  super.onOptionsItemSelected(item);
    }
}
