package com.example.admin.movie;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Admin on 01-05-2016.
 */
public class MovieGridAdapter extends BaseAdapter {
    public Context mContext;
    public ArrayList<MovieDetails> mMovieList;
    public static String baseURL = "https://api.themoviedb.org/3/discover/movie?api_key=";
    public static String apiKey = "ae13bc55fa2bfb06c6dfc5ab972073b9";
    public static String ratingURL = "&sort_by=vote_average.desc&vote_count.gte=1500";
    public static String imageBaseURL = "http://image.tmdb.org/t/p/w300";
    public static String MOVIE_GRID_ADAPTER = "MOVIE_GRID_ADAPTER";

    MovieGridAdapter(Context context) {
        Log.e(MOVIE_GRID_ADAPTER, "CONSTRUCTING");
        mContext = context;
        mMovieList = MovieList.getInstance();
        updateMovieDetails();
    }

    public void updateMovieDetails() {
        /*
        TODO
        obtain shared preferences for sort order and call the update method with the corresponding url
         */
        new MovieDownloader().execute(baseURL + apiKey);
    }

    @Override
    public int getCount() {
        return mMovieList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(position > mMovieList.size()) {
            Log.e(MOVIE_GRID_ADAPTER, "INDEX OUT OF BOUND, WILL CRASH");
        }
        MovieDetails movie = mMovieList.get(position);
        ImageView poster;
        if(convertView == null) {
            poster = new ImageView(mContext);
            poster.setAdjustViewBounds(true);
        } else {
            poster = (ImageView)convertView;
        }
        String imageURL = imageBaseURL + movie.getPosterPath();
        Picasso.with(mContext).load(imageURL).into(poster);
        poster.setAdjustViewBounds(true);
        return poster;
    }

    private class MovieDownloader extends AsyncTask<String, Void, Void> {
        @Override
        protected Void doInBackground(String ... params) {
            try {
                URL url = new URL(params[0]);
                HttpURLConnection conn = (HttpURLConnection)url.openConnection();
                conn.setRequestMethod("GET");
                InputStream is = conn.getInputStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(is));
                JSONObject obj = new JSONObject(br.readLine());
                Log.e(MOVIE_GRID_ADAPTER, obj.toString());
                JSONArray arr = obj.getJSONArray("results");
                int len = arr.length();
                int listLen = mMovieList.size();
                Log.e(MOVIE_GRID_ADAPTER, new Integer(len).toString());
                for(int i = 0; i < len; i++) {
                    MovieDetails movie = new MovieDetails(arr.getJSONObject(i));
                    if(listLen <= i) {
                        mMovieList.add(movie);
                    } else {
                        mMovieList.set(i, movie);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            notifyDataSetChanged();
            Toast.makeText(mContext, new Integer(mMovieList.size()).toString(), Toast.LENGTH_LONG).show();
        }
    }
}
