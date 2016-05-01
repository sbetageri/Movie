package com.example.admin.movie;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class DetailsMovie extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_movie);
        ImageView backdrop;
        TextView rating;
        TextView title;
        TextView overview;
        Intent intent = getIntent();
        int position = intent.getIntExtra(MainActivity.POSTER_POSITION, 0);
        backdrop = (ImageView)findViewById(R.id.details_image);
        MovieDetails movie = MovieList.getInstance().get(position);
        Picasso.with(this).load(MovieGridAdapter.imageBaseURL + MovieGridAdapter.backdropResolution + movie.getBackdropPath())
                .fit().into(backdrop);
        title = (TextView)findViewById(R.id.movie_title);
        title.setText(movie.getTitle());
        rating = (TextView)findViewById(R.id.movie_rating);
        rating.setText(movie.getRating().toString());
        overview = (TextView)findViewById(R.id.movie_overview);
        overview.setText(movie.getOverview());
    }
}
