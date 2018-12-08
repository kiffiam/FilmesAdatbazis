package hu.bme.aut.filmesadatbazis.adapter;

import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import org.w3c.dom.Text;

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
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        Movie item = movies.get(position);
        holder.titleTextView.setText(item.title);
        holder.pointTextView.setText(String.valueOf(item.point));
        holder.genreTextView.setText(getStringSource(item.genre));

        holder.item=item;

    }

    private @StringRes int getStringSource(Movie.Genre genre){
        @StringRes int ret;
        switch (genre){
            case DRAMA:
                ret = R.string.drama;
                break;
            case SCIFI:
                ret= R.string.scifi;
                break;
            case ACTION:
                ret = R.string.action;
                break;
            case HORROR:
                ret = R.string.horror;
                break;
            case COMEDY:
                ret = R.string.comedy;
                break;
            case MUSICAL:
                ret = R.string.musical;
                break;
            case FANTASY:
                ret = R.string.fantasy;
                break;
            case THRILLER:
                ret = R.string.thriller;
                break;
            default:
                ret=0;
        }
        return ret;
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public interface MovieClickListener{
        void onItemChanged(Movie movie);
    }

    class MovieViewHolder extends RecyclerView.ViewHolder{

        TextView titleTextView;
        TextView pointTextView;
        TextView genreTextView;
        ImageButton removeButton;

        Movie item;


        MovieViewHolder(View movieView) {
            super(movieView);

            titleTextView = itemView.findViewById(R.id.MovieItemTitleTextView);
            pointTextView = itemView.findViewById(R.id.MovieItemPointTextView);
            genreTextView = itemView.findViewById(R.id.MovieItemGenreTextView);
            removeButton = itemView.findViewById(R.id.MovieItemRemoveButton);

        }
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
