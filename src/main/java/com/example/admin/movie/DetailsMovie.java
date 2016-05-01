package com.example.admin.movie;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class DetailsMovie extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.more_movie_details);
        ImageView backdrop;
        Bitmap poster;
        TextView rating;
        TextView title;
        TextView overview;
        TextView releaseDate;
        Intent intent = getIntent();
        int position = intent.getIntExtra(MainActivity.POSTER_POSITION, 0);
        poster = intent.getParcelableExtra(MainActivity.POSTER_IMAGE);
        backdrop = (ImageView)findViewById(R.id.details_poster);
        backdrop.setImageBitmap(poster);
        backdrop.setAdjustViewBounds(true);
        MovieDetails movie = MovieList.getInstance().get(position);
        title = (TextView)findViewById(R.id.details_title);
        title.setText(movie.getTitle());
        releaseDate = (TextView)findViewById(R.id.details_release_date);
        releaseDate.setText(movie.getReleaseDate().toString());
        rating = (TextView)findViewById(R.id.details_rating);
        rating.setText(movie.getRating().toString() + " / 10.0");
        overview = (TextView)findViewById(R.id.details_overview);
        overview.setText(movie.getOverview());
    }
}
