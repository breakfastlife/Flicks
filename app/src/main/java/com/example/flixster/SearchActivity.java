package com.example.flixster;

import android.os.Bundle;
import android.view.Menu;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.flixster.adapters.MovieAdapter;
import com.example.flixster.adapters.SearchAdapter;
import com.example.flixster.models.Movie;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity {
    List<Movie> movies;

    protected void OnCreate(@Nullable Bundle savedInstanceState2) {
        super.onCreate(savedInstanceState2);
        setContentView(R.layout.activity_search);
        RecyclerView rvSearch = findViewById(R.id.rvSearch);
        movies = new ArrayList<>();

        List<Movie> movie = Parcels.unwrap(getIntent().getParcelableExtra("movieList"));
        // Find the toolbar view inside the activity layout
        androidx.appcompat.widget.Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        // Sets the Toolbar to act as the ActionBar for this Activity window.
        // Make sure the toolbar exists in the activity and is not null
        setSupportActionBar(toolbar);

        movies.addAll(movie);

        //Create the adapter
        final SearchAdapter searchAdapter = new SearchAdapter(this, movie);

        //Set the adapter to the recycler view
        rvSearch.setAdapter(searchAdapter);

        //Set a layout on the recycler view
        rvSearch.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_movie_detail, menu);
        return true;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }
}
