package com.example.tez.Activitys.Activitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import com.example.tez.Activitys.Adapters.KullaniciAdapter2;
import com.example.tez.Activitys.Models.Kullanici;
import com.example.tez.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;



public class YuksekSkorActivity2 extends AppCompatActivity {

    private RecyclerView recyclerView;
    private KullaniciAdapter2 adapter;
    private List<Kullanici> productList;
    private ProgressBar progressBar;
    private FirebaseFirestore db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yuksekskor);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);




        progressBar = findViewById(R.id.progressbar);
        recyclerView = findViewById(R.id.recyclerview_products);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        productList = new ArrayList<>();
        adapter = new KullaniciAdapter2(this, productList);
        recyclerView.setAdapter(adapter);

        db = FirebaseFirestore.getInstance();

        db.collection("kullanıcıları").get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        progressBar.setVisibility(View.GONE);
                        if (!queryDocumentSnapshots.isEmpty()) {
                            List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
                            for (DocumentSnapshot d : list) {
                                //LİSTEYE EKLE
                                Kullanici p = d.toObject(Kullanici.class);
                                p.setId(d.getId());
                                productList.add(p);
                                //LİSTEYİ SIRALA
                                Collections.sort(productList, new Comparator<Kullanici>() {
                                    @Override
                                    public int compare(Kullanici p1, Kullanici p2) {
                                        if (p1.getBoskor() < p2.getBoskor()) {
                                            return 1;
                                        } else if (p1.getBoskor() > p2.getBoskor()) {
                                            return -1;
                                        }
                                        return 0;
                                    }
                                });
                            }
                            adapter.notifyDataSetChanged();
                        }
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