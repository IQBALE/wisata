package com.example.wisata;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

public class CardViewWisataAdapter extends RecyclerView.Adapter<CardViewWisataAdapter.CardViewHolder> {
    private Context context;
    private ArrayList<Wisata> listWisata;
    private static final String TAG = "RecyclerViewAdapter";

    public ArrayList<Wisata> getListWisata() {
        return listWisata;
    }

    public void setListWisata(ArrayList<Wisata> listWisata) {
        this.listWisata = listWisata;
    }


    public CardViewWisataAdapter(Context context) {
        this.context = context;
    }




    @NonNull
    @Override
    public CardViewWisataAdapter.CardViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view  = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_cardview_wisata, viewGroup, false);
        return new CardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final CardViewWisataAdapter.CardViewHolder cardViewHolder, int i) {
        Wisata p = getListWisata().get(i);

        Glide.with(context)
                .load(p.getPhoto())
                .apply(new RequestOptions().override(350, 500))
                .into(cardViewHolder.imgPhoto);
        cardViewHolder.tvNama.setText(p.getNama());
        cardViewHolder.tvDesc.setText(p.getDesc());

        cardViewHolder.btnFavorite.setOnClickListener(new CustomOnItemClickListener(i, new CustomOnItemClickListener.OnItemClickCallback() {
            @Override
            public void onItemClicked(View view, int position) {
                Toast.makeText(context, "Favorite "+getListWisata().get(position).getNama(), Toast.LENGTH_SHORT).show();
            }
        }));
        cardViewHolder.btnShare.setOnClickListener(new CustomOnItemClickListener(i, new CustomOnItemClickListener.OnItemClickCallback() {
            @Override
            public void onItemClicked(View view, int position) {
                Toast.makeText(context,"Share "+getListWisata().get(position).getNama(), Toast.LENGTH_SHORT).show();
            }
        }));
        cardViewHolder.imgPhoto.setOnClickListener(new CustomOnItemClickListener(i, new CustomOnItemClickListener.OnItemClickCallback() {
            @Override
            public void onItemClicked(View view, int position) {
                Toast.makeText(context, "Clicked Photo"+getListWisata().get(position).getNama(), Toast.LENGTH_SHORT).show();

                try {
                    Intent intent = new Intent(context, DetailAdapter.class);
                    intent.putExtra(DetailAdapter.EXTRA_URL, getListWisata().get(position).getPhoto());
                    Log.d(TAG, "Value: "+getListWisata().get(position).getPhoto() );

                } catch (Exception e) {
                    Log.e("MYAPP", "exception", e);
                }
            }
        }));




    }

    @Override
    public int getItemCount() {
        return getListWisata().size();
    }

    class CardViewHolder extends RecyclerView.ViewHolder {
        ImageView imgPhoto;
        TextView tvNama, tvDesc;
        Button btnFavorite, btnShare;
        CardViewHolder(@NonNull View itemView) {
            super(itemView);
            imgPhoto = itemView.findViewById(R.id.img_item_photo);
            tvNama = itemView.findViewById(R.id.tv_item_name);
            tvDesc = itemView.findViewById(R.id.tv_item_desc);
            btnFavorite = itemView.findViewById(R.id.btn_set_favorite);
            btnShare = itemView.findViewById(R.id.btn_set_share);
        }
    }
}
