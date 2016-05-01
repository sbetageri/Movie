package com.example.admin.movie;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Admin on 01-05-2016.
 */
public class MovieDetails {
    private static String MOVIE_DETAILS = "MOVIE_DETAILS";
    private String title;
    private String overview;
    private Double rating;
    // JSONObject from themoviedb encode rating under vote_average
    private String backdropPath;
    private String posterPath;

    public MovieDetails(JSONObject movie) {
        try {
            title = movie.getString("title");
            overview = movie.getString("overview");
            backdropPath = movie.getString("backdrop_path");
            posterPath = movie.getString("poster_path");
            rating = new Double(movie.getString("vote_average"));
        } catch (JSONException e) {
            e.printStackTrace();
            Log.e(MOVIE_DETAILS, e.toString());
        }
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }
}
