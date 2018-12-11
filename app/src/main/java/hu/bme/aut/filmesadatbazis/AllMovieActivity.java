package hu.bme.aut.filmesadatbazis;


import android.arch.persistence.room.Room;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.List;

import hu.bme.aut.filmesadatbazis.adapter.MovieAdapter;
import hu.bme.aut.filmesadatbazis.adapter.OwnListAdapter;
import hu.bme.aut.filmesadatbazis.data.DbContext;
import hu.bme.aut.filmesadatbazis.data.ListMovieJoin;
import hu.bme.aut.filmesadatbazis.data.Movie;
import hu.bme.aut.filmesadatbazis.data.OwnList;
import hu.bme.aut.filmesadatbazis.fragments.UpdateMovieDialogFragment;

public class AllMovieActivity extends AppCompatActivity
        implements MovieAdapter.MovieClickListener, UpdateMovieDialogFragment.UpdateMovieDialogListener, OwnListAdapter.OwnListClickListener {

    private RecyclerView recyclerView;
    private MovieAdapter adapter;

    private DbContext dbContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_movie);

        dbContext = new DbContext(getApplicationContext());

        initRecyclerView();
    }

    private void initRecyclerView(){
        recyclerView = findViewById(R.id.AllMovieRecyclerView);
        adapter = new MovieAdapter(this);
        loadItemsInBackground();
        loadItemsInBackgroundLists();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

    }

    private void loadItemsInBackgroundLists(){
        new AsyncTask<Void, Void, List<OwnList>>(){

            @Override
            protected List<OwnList> doInBackground(Void... voids){
                return dbContext.getOwnLists();
            }

            @Override
            protected void onPostExecute(List<OwnList> ownLists) {
                adapter.updateLists(ownLists);
                Log.d("AllMoviesActivity", "LISTS LOADED !!!!!!!!!!!!!!!!");
            }
        }.execute();
    }

    private void loadItemsInBackground(){
        new AsyncTask<Void, Void, List<Movie>>(){

            @Override
            protected List<Movie> doInBackground(Void... voids){
                return dbContext.getMovies();
            }

            @Override
            protected void onPostExecute(List<Movie> movies) {
                adapter.update(movies);
            }
        }.execute();
    }

    @Override
    public void onItemDeleted(final Movie movie) {
        new AsyncTask<Void, Void, Boolean>() {

            @Override
            protected Boolean doInBackground(Void... voids) {
                dbContext.deleteMovie(movie);
                return true;
            }

            @Override
            protected void onPostExecute(Boolean isSuccessful) {
                Log.d("AllMoviesActivity", "Movie deleted");
            }
        }.execute();
    }

    @Override
    public void onMovieUpdated(final Movie movie) {
        new AsyncTask<Void, Void, Boolean>() {

            @Override
            protected Boolean doInBackground(Void... voids) {
                dbContext.updateMovie(movie);
                return true;
            }

            @Override
            protected void onPostExecute(Boolean isSuccessful) {
                loadItemsInBackground();
                Log.d("AllMoviesActivity", "Movie updated");
            }
        }.execute();
    }

    @Override
    public void onMovieInsertToList(final Movie movie, final OwnList ownList) {
        new AsyncTask<Void, Void, Boolean>() {

            @Override
            protected Boolean doInBackground(Void... voids) {
                dbContext.insertListMovieJoin(new ListMovieJoin(movie.id,ownList.id));
                return true;
            }

            @Override
            protected void onPostExecute(Boolean isSuccessful) {
                loadItemsInBackground();
                Log.d("AllMoviesActivity", "Movie added to List");
            }
        }.execute();
    }

    @Override
    public void onDataClicked(Movie movie) {
        Bundle bundle = new Bundle();
        bundle.putLong("id",movie.id);
        bundle.putString("title", movie.title);
        bundle.putString("opinion", movie.opinion);
        bundle.putInt("point", movie.point);
        bundle.putInt("genre", Movie.Genre.toInt(movie.genre));

        UpdateMovieDialogFragment updateMovieDialogFragment = new UpdateMovieDialogFragment();
        updateMovieDialogFragment.setArguments(bundle);
        updateMovieDialogFragment.show(getSupportFragmentManager(), UpdateMovieDialogFragment.TAG);
    }


    @Override
    public void onDataClicked(OwnList ownList) {

    }

    @Override
    public void onItemDeleted(OwnList ownList) {

    }

    @Override
    public void onOpenList(OwnList ownList) {

    }
}
