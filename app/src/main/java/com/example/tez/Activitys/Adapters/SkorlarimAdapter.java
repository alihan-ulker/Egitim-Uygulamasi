package com.example.tez.Activitys.Adapters;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.tez.Activitys.Models.Kullanici;
import com.example.tez.R;

import java.util.List;



public class SkorlarimAdapter extends RecyclerView.Adapter<SkorlarimAdapter.ProductViewHolder>{

    private Context mCtx;
    private List<Kullanici> kullaniciList;



    public SkorlarimAdapter(Context mCtx, List<Kullanici> kullaniciList) {
        this.mCtx = mCtx;
        this.kullaniciList = kullaniciList;


    }


    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new ProductViewHolder(
                LayoutInflater.from(mCtx).inflate(R.layout.layout_liste, parent, false)
        );

    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
          Kullanici kullanici = kullaniciList.get(position);


          holder.textViewName.setText("       Yüksek Skorlarım");
          holder.textViewBrand.setText("PDP skoru :" + kullanici.getBoskor()+"\nC++ skoru : " + kullanici.getCskor());



    }

    @Override
    public int getItemCount() {
        return kullaniciList.size();
    }

    class ProductViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView textViewName, textViewBrand;

        public ProductViewHolder(View itemView) {
            super(itemView);

            textViewName = itemView.findViewById(R.id.textview_mail);
            textViewBrand = itemView.findViewById(R.id.textview_skor);
            textViewName.setTextSize(30);
            textViewName.setTextColor(Color.parseColor("#14662f"));
            textViewBrand.setTextColor(Color.parseColor("#2d3d32"));
            textViewBrand.setTextSize(25);

            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {

        }
    }
}
