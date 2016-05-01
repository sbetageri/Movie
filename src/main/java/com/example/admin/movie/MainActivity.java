package com.example.admin.movie;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity implements SharedPreferences.OnSharedPreferenceChangeListener{
    private MovieGridAdapter mMovieGridAdapter;
    public static String POSTER_POSITION = "POSTER_POSITION";
    public static String POSTER_IMAGE = "POSTER_IMAGE";
    public SharedPreferences pref;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.settings_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.settings_menu:
                Intent intent = new Intent(this, SettingsActivity.class);
                startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        if(mMovieGridAdapter != null) {
            String sortOrder = sharedPreferences.getString(getString(R.string.sort_pref), "popularity");
            String sortKey;
            if(sortOrder.equals("popularity")) {
                sortKey = MovieGridAdapter.popularURL;
            } else {
                sortKey = MovieGridAdapter.ratingURL;
            }
            mMovieGridAdapter.updateMovieDetails(sortKey);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        GridView posters = (GridView)findViewById(R.id.poster_grid);
        mMovieGridAdapter = new MovieGridAdapter(this);
        posters.setAdapter(mMovieGridAdapter);
        pref = PreferenceManager.getDefaultSharedPreferences(this);
        pref.registerOnSharedPreferenceChangeListener(this);
        posters.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ImageView poster = (ImageView)view;
                Intent intent = new Intent(parent.getContext(), DetailsMovie.class);
                Bitmap image = ((BitmapDrawable)poster.getDrawable()).getBitmap();
                intent.putExtra(POSTER_POSITION, position);
                intent.putExtra(POSTER_IMAGE, image);
                startActivity(intent);
            }
        });
    }
}
