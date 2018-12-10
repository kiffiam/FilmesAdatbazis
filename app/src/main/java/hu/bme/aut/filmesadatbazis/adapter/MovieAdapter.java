package hu.bme.aut.filmesadatbazis.adapter;

import android.media.Image;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import hu.bme.aut.filmesadatbazis.AllMovieActivity;
import hu.bme.aut.filmesadatbazis.R;
import hu.bme.aut.filmesadatbazis.data.Movie;
import hu.bme.aut.filmesadatbazis.data.OwnList;
import hu.bme.aut.filmesadatbazis.fragments.CreateMovieDialogFragment;
import hu.bme.aut.filmesadatbazis.fragments.UpdateMovieDialogFragment;


public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {


    private final List<Movie> movies;

    private final List<OwnList> ownLists;

    private MovieClickListener listener;

    public MovieAdapter(MovieClickListener listener) {
        this.listener = listener;
        movies = new ArrayList<>();
        ownLists = new ArrayList<>();
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie, parent, false);
        return new MovieViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, final int position) {
        final Movie item = movies.get(position);
        holder.titleTextView.setText(item.title);
        holder.pointTextView.setText(String.valueOf(item.point));
        holder.genreTextView.setText(getStringSource(item.genre));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               listener.onDataClicked(item); }
        });
        holder.item = item;
    }

    public @StringRes
    int getStringSource(Movie.Genre genre) {
        @StringRes int ret;
        switch (genre) {
            case DRAMA:
                ret = R.string.drama;
                break;
            case SCIFI:
                ret = R.string.scifi;
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
                ret = 0;
        }
        return ret;
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }


    public interface MovieClickListener {
        void onItemDeleted(Movie movie);

        void onItemAddedToList(Movie movie, OwnList ownList);

        void onDataClicked(Movie movie);

        void onMovieUpdated(Movie movie);
    }

    class MovieViewHolder extends RecyclerView.ViewHolder {

        TextView titleTextView;
        TextView pointTextView;
        TextView genreTextView;
        ImageButton addToListButton;
        ImageButton removeButton;
        Movie item;


        MovieViewHolder(View movieView) {
            super(movieView);

            titleTextView = itemView.findViewById(R.id.MovieItemTitleTextView);
            pointTextView = itemView.findViewById(R.id.MovieItemPointTextView);
            genreTextView = itemView.findViewById(R.id.MovieItemGenreTextView);
            removeButton = itemView.findViewById(R.id.MovieItemRemoveButton);
            addToListButton = itemView.findViewById(R.id.MovieItemAddToListButton);

            removeButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    deleteItem(getLayoutPosition());
                }
            });

            /*addToListButton.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    //Creating the instance of PopupMenu
                    PopupMenu popup = new PopupMenu(addToListButton);

                    for (OwnList list: ownLists) {
                        popup.getMenu().add(list.name);
                    }

                    //Inflating the Popup using xml file
                    popup.getMenuInflater().inflate(R.menu.menu_movie_item_add_to_list, popup.getMenu());

                    //registering popup with OnMenuItemClickListener
                    popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        public boolean onMenuItemClick(MenuItem item) {
                            addItemToList(getLayoutPosition(),ownLists.get(item.getItemId()));
                            return true;
                        }
                    });
                }
            });*/
        }
    }

    private void addItemToList(int layoutPosition, OwnList ownList) {
    Movie movieToAdd = movies.get(layoutPosition);

    listener.onItemAddedToList(movieToAdd, ownList);
    }

    public void update(List<Movie> movieList) {
        movies.clear();
        movies.addAll(movieList);
        notifyDataSetChanged();
    }

    public void deleteItem(int position) {
        Movie toRemove = movies.remove(position);
        notifyItemRemoved(position);
        listener.onItemDeleted(toRemove);
    }
}

