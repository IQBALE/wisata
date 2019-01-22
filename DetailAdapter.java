package com.example.wisata;

import android.media.Image;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

public class DetailAdapter extends AppCompatActivity{
    private static final String TAG = "RecyclerViewAdapter";
    public static final String EXTRA_URL = "extra_url";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_item);
        try {
            // code that might throw an exception
            String img_url = getIntent().getStringExtra(EXTRA_URL);
            Log.d(TAG, "getIncomingIntent: checking for incoming intents.");

            ImageView showImg = findViewById(R.id.img_item_photo);
            Glide.with(this)
                    .load(img_url)
                    .apply(new RequestOptions().override(150, 150))
                    .into(showImg);

        } catch (Exception e) {
            Log.e("MYAPP", "exception", e);
        }
            }



}
