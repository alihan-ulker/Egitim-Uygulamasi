package com.example.tez.Activitys.Activitys;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tez.R;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;



public class MainActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {


    private FirebaseAuth mAuth;
    FirebaseUser firebaseUser;
    Button btnGiris;
    Button btnKayitOl;
    private EditText edtMail;
    private EditText edtSifre;
    private String userName;
    private String userPassword;
    ProgressDialog progressDialog;
    private static final int RC_SIGN_IN = 9001;
    SignInButton signInButton;
    GoogleApiClient mGoogleApiClient;
    String TAG="HATALAR";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnGiris =(Button)findViewById(R.id.btnGiris);
        btnKayitOl =(Button)findViewById(R.id.btnKayitOl);
        edtMail =(EditText)findViewById(R.id.editTextEmail);
        edtSifre =(EditText)findViewById(R.id.editTextSifre);




        //FirebaseAuth sınıfının referans olduğu nesneleri kullanabilmek için getInstance methodunu kullanıyoruz.
        mAuth = FirebaseAuth.getInstance();

        firebaseUser = mAuth.getCurrentUser(); // authenticated user
        signInButton = (SignInButton)findViewById(R.id.sign_in_button);

        //Google Sign in Options Yapılandırması
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken("707821086031-1gdvctqnur0o1il5uoehsm1l5bgciqes.apps.googleusercontent.com")
                .requestEmail()
                .build();

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(MainActivity.this, this /* OnConnectionFailedListener */)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();



        //Geçerli bir yetkilendirme olup olmadığını kontrol ediyoruz.
        if(mAuth.getCurrentUser() != null){
            startActivity(new Intent(MainActivity.this,MenuActivity.class));
        }


        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                signIn();
            }
        });


        btnKayitOl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, KayitActivity.class);
                startActivity(intent);
            }
        });

        btnGiris.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                userName = edtMail.getText().toString();
                userPassword = edtSifre.getText().toString();
                if(userName.isEmpty() || userPassword.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Lütfen gerekli alanları doldurunuz!", Toast.LENGTH_SHORT).show();
                }else{

                    progressDialog  = ProgressDialog.show(MainActivity.this,"Doğrulama","Bilgiler Kontrol ediliyor...",true);
                    progressDialog.show();
                    loginFunc();
                }
            }
        });
    }

    private void loginFunc() {

        mAuth.signInWithEmailAndPassword(userName,userPassword).addOnCompleteListener(MainActivity.this,
                new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Intent i = new Intent(MainActivity.this,MenuActivity.class);
                            i.putExtra("mail",userName);
                            startActivity(i);
                            progressDialog.dismiss();
                            finish();

                        }
                        else{
                            // hata
                            progressDialog.dismiss();
                            Toast.makeText(getApplicationContext(),"Kullanıcı Adı veya Şifre Hatalı!", Toast.LENGTH_SHORT).show();
                        }
                    }

                });
    }


    private void signIn() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);

    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            if (result.isSuccess()) {
                // Google Sign In basarili oldugunda Firebase ile yetkilendir
                GoogleSignInAccount account = result.getSignInAccount();
                firebaseAuthWithGoogle(account);

            } else {
                Toast.makeText(MainActivity.this,"Google Sign'in de Hata Oluştu!("+resultCode+")",Toast.LENGTH_SHORT).show();
            }
        }
    }
    //GoogleSignInAccount nesnesinden ID token'ı alıp, bu Firebase güvenlik referansını kullanarak
    // Firebase ile yetkilendirme işlemini gerçekleştiriyoruz
    private void firebaseAuthWithGoogle(final GoogleSignInAccount acct) {
        Log.d(TAG, "firebaseAuthWithGooogle:" + acct.getId());
        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        //             Log.d(TAG, "signInWithCredential:onComplete:" + task.isSuccessful());

                        Log.d(TAG, "firebaseAuthWithGooogle:22222222" + acct.getId());

                        if (!task.isSuccessful()) {
                            //    Log.w(TAG, "signInWithCredential", task.getException());
                            Toast.makeText(MainActivity.this, "Yetkilendirme hatası.",
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            startActivity(new Intent(MainActivity.this, MenuActivity.class));
                            finish();
                        }
                    }
                });
    }



    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

        Log.d(TAG, "onConnectionFailed:" + connectionResult);
        Toast.makeText(this, "Google Play Services error.", Toast.LENGTH_SHORT).show();
    }
}