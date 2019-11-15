package com.example.catbreeds;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class CatBreedDetailActivity extends AppCompatActivity {
    private TextView nameTextView;
    private ImageView breedImageView;
    private ImageView favoriteImageView;
    private TextView descriptionTextView;
    private TextView weightTextView;
    private TextView temperamentTextView;
    private TextView originTextView;
    private TextView lifeSpanTextView;
    private TextView wikiUrlTextView;
    private TextView dogFriendlyTextView;
    private Boolean isFavorite = false;
    private String imageUrl;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cat_breed_detail);

        nameTextView = findViewById(R.id.breedName);
        breedImageView = findViewById(R.id.breedImage);
        favoriteImageView = findViewById(R.id.breedFav);
        descriptionTextView = findViewById(R.id.breedDescription);
        weightTextView = findViewById(R.id.breedWeight);
        temperamentTextView = findViewById(R.id.breedTemperament);
        originTextView = findViewById(R.id.breedOrigin);
        lifeSpanTextView = findViewById(R.id.breedLifeSpan);
        wikiUrlTextView = findViewById(R.id.breedWikiUrl);
        dogFriendlyTextView = findViewById(R.id.breedDogFriendlyLevel);

        Intent intent = getIntent();
        final String breedID = intent.getStringExtra("CatBreedID");
        final AppDatabase db = AppDatabase.getInstance(this);
        db.catDao().setFav(0);
        CatBreed catBreed =  db.catDao().findCatById(breedID); //TODO: can change this into catName

        nameTextView.setText(catBreed.getName());


        favoriteImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isFavorite){//it was favorite and by clicking it is deleted from favorite
                    favoriteImageView.setImageResource(R.drawable.favorite_border);
                    db.catDao().updateFav(0, breedID);
                }else{ //it was not favorite and by clikcing it became favorite
                    favoriteImageView.setImageResource(R.drawable.favorites);
                    db.catDao().updateFav(1, breedID);
                }
                isFavorite = !isFavorite;
            }
        });

        descriptionTextView.setText(catBreed.getDescription());
        weightTextView.setText(catBreed.getWeight());
        //weightTextView.setText(catBreed.getWeight().getImperial_weight());
        temperamentTextView.setText("Temperament: " + catBreed.getTemperament());
        originTextView.setText("Origin: " + catBreed.getOrigin());
        lifeSpanTextView.setText("Life span: " + catBreed.getLifeSpan());
        wikiUrlTextView.setText("Wikipedia url: " + catBreed.getWikipediaUrl());
        dogFriendlyTextView.setText("Dog friendly level: "+ String.valueOf(catBreed.getDogFriendly()));

        final RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://api.thecatapi.com/v1/images/search?q="+breedID;
        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                Image[] imageArray = gson.fromJson(response, Image[].class);
                imageUrl = imageArray[0].getUrl();
                Glide.with(getApplicationContext()).load(imageUrl).into(breedImageView);
                queue.stop();
            }
        };

        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("error");
                queue.stop();
            }
        };
        System.out.println(url);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, responseListener, errorListener);
        queue.add(stringRequest);

    }
}
