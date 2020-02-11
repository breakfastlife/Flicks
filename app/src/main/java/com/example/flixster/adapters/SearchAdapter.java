package com.example.flixster.adapters;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.flixster.DetailActivity;
import com.example.flixster.R;
import com.example.flixster.models.Movie;

import org.parceler.Parcels;

import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> {
    Context context;
    List<Movie> movies;
    String query;

    //Usually involves inflating a layout from XML and returning the holder
    @NonNull
    @Override
    public com.example.flixster.adapters.SearchAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d("SearchAdapter", "onCreateViewHolder");
        View searchView = LayoutInflater.from(context).inflate(R.layout.item_movie, parent, false);
        return new com.example.flixster.adapters.SearchAdapter.ViewHolder(searchView);
    }
    //Involves populating data into the item through holder
    @Override
    public void onBindViewHolder(@NonNull com.example.flixster.adapters.SearchAdapter.ViewHolder holder, int position) {
        Log.d("SearchAdapter", "onBindViewHolder: " + position);
        //Get the movie at the passed in position
        Movie movie = movies.get(position);
        //Bind the movie data into the VH
        holder.bind(movie);
    }
    //Returns the total count of items in the list
    @Override
    public int getItemCount() {
        return movies.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        RelativeLayout container;
        TextView tvTitle;
        TextView tvOverview;
        ImageView ivPoster;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvOverview = itemView.findViewById(R.id.tvOverview);
            ivPoster = itemView.findViewById(R.id.ivPoster);
            container = itemView.findViewById(R.id.container);
        }

        public void bind(final Movie movie) {
            tvTitle.setText(movie.getTitle());
            tvOverview.setText(movie.getOverview());
            String imageUrl = (context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE)
                    ? movie.getBackdropPath()
                    : movie.getPosterPath();

        /*int height = (context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE)
                ? 10000000
                : 350;*/

            Glide.with(context)
                    //.load("") //used to check if placeholder was working properly
                    .load(imageUrl)
                    //.override(height, Target.SIZE_ORIGINAL)
                    //.apply(new RequestOptions()
                    //    .placeholder(R.drawable.ic_launcher_background))
                    //.dontAnimate()
                    .into(ivPoster);

            //1. Register the click listener on the whole row
            container.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //2. Navigate to new activity on tap
                    Intent i = new Intent(context, DetailActivity.class);
                    i.putExtra("movie", Parcels.wrap(movie));
                    context.startActivity(i);
                }
            });


        }
    }

}
