package hu.bme.aut.filmesadatbazis.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import hu.bme.aut.filmesadatbazis.R;
import hu.bme.aut.filmesadatbazis.data.Movie;


public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder>{


    private final List<Movie> movies;

    private MovieClickListener listener;

    public MovieAdapter(MovieClickListener listener) {
        this.listener = listener;
        movies = new ArrayList<>();
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_movie, parent, false);
        return new MovieViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder movieViewHolder, int i) {
        //TODO: ide kellenek az adatbázisbol a dolgok

    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public interface MovieClickListener{
        void onItemChanged(Movie movie);
    }

    class MovieViewHolder extends RecyclerView.ViewHolder{
        MovieViewHolder(View movieView) {
        super(movieView);
    }

        Movie item;
    }

    public void addItem(Movie item) {
        movies.add(item);
        notifyItemInserted(movies.size() - 1);
    }

    public void update(List<Movie> movieList) {
        movies.clear();
        movies.addAll(movieList);
        notifyDataSetChanged();
    }


}
