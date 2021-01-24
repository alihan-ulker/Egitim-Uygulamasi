package com.example.tez.Activitys.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.tez.Activitys.Models.Kullanici;
import com.example.tez.R;

import java.util.List;




public class KullaniciAdapter2 extends RecyclerView.Adapter<KullaniciAdapter2.ProductViewHolder> {

    private Context mCtx;
    private List<Kullanici> kullaniciList;



    public KullaniciAdapter2(Context mCtx, List<Kullanici> kullaniciList) {
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

        holder.textViewMail.setText(kullanici.getMail());
        holder.textViewSkor.setText("BO skoru : " + kullanici.getBoskor());


    }

    @Override
    public int getItemCount() {
        return kullaniciList.size();
    }

    class ProductViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView textViewMail, textViewSkor;

        public ProductViewHolder(View itemView) {
            super(itemView);

            textViewMail = itemView.findViewById(R.id.textview_mail);
            textViewSkor = itemView.findViewById(R.id.textview_skor);


            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {

        }
    }

}
