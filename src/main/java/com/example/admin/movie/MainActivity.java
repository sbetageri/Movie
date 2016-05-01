package com.example.admin.movie;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.GridView;

public class MainActivity extends AppCompatActivity {
    private MovieGridAdapter mMovieGridAdapter;
    public static String POSTER_POSITION = "POSTER_POSITION";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        GridView posters = (GridView)findViewById(R.id.poster_grid);
        mMovieGridAdapter = new MovieGridAdapter(this);
        posters.setAdapter(mMovieGridAdapter);
        posters.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(parent.getContext(), DetailsMovie.class);
                intent.putExtra(POSTER_POSITION, position);
                startActivity(intent);
            }
        });
    }
}
