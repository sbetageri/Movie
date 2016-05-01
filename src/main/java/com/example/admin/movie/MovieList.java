package com.example.admin.movie;

import java.util.ArrayList;

/**
 * Created by Admin on 01-05-2016.
 */
public class MovieList {
    private static MovieList sMovieList;
    private ArrayList<MovieDetails> mMovieList;
    private MovieList() {

    }

    public static ArrayList<MovieDetails> getInstance() {
        if(sMovieList == null) {
            sMovieList = new MovieList();
            sMovieList.mMovieList = new ArrayList<MovieDetails>();
        }
        return sMovieList.mMovieList;
    }
}
