package com.example.flixster.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcel;

import java.util.ArrayList;
import java.util.List;

@Parcel
public class Movie {
    int movieID;
    String backdropPath;
    String posterPath;
    String title;
    String overview;
    double rating;

    // empty constructor needed by the Parceler library
    public Movie() {}



    public Movie(JSONObject jsonObject) throws JSONException {
        rating = jsonObject.getDouble("vote_average");
        backdropPath = jsonObject.getString("backdrop_path");
        posterPath = jsonObject.getString("poster_path");
        title = jsonObject.getString("title");
        overview = jsonObject.getString("overview");
        movieID = jsonObject.getInt("id");
    }

    public static List<Movie> fromJSONArray(JSONArray movieJSONArray) throws JSONException {
        List<Movie> movies = new ArrayList<>();
        for(int i = 0; i < movieJSONArray.length(); i++){
            movies.add(new Movie(movieJSONArray.getJSONObject(i)));
        }
        return movies;
    }

    public static Movie movieEmpty() throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("rating", 0.00);
        jsonObject.put("backdropPath", "");
        jsonObject.put("posterPath", "");
        jsonObject.put("title", "Movie_Not_Found");
        jsonObject.put("overview", "No Movie was loaded, check internet");
        jsonObject.put("id", 0);
        return new Movie(jsonObject);
    }

    public String getPosterPath() {
        return String.format("https://image.tmdb.org/t/p/w342/%s", posterPath);//%s means here is what I want to replace with <posterPath>
    }

    public String getTitle() {
        return title;
    }

    public String getOverview() {
        return overview;
    }

    public String getBackdropPath() { return String.format("https://image.tmdb.org/t/p/w342/%s", backdropPath);} //%s means here is what I want to replace with <posterPath>

    public double getRating() { return rating; }

    public int getMovieID() {
        return movieID;
    }

}
