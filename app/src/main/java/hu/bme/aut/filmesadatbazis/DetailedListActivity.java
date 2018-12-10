package hu.bme.aut.filmesadatbazis;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.List;

import hu.bme.aut.filmesadatbazis.adapter.MovieAdapter;
import hu.bme.aut.filmesadatbazis.data.DbContext;
import hu.bme.aut.filmesadatbazis.data.Movie;
import hu.bme.aut.filmesadatbazis.data.OwnList;
import hu.bme.aut.filmesadatbazis.fragments.UpdateMovieDialogFragment;

public class DetailedListActivity extends AppCompatActivity
        implements MovieAdapter.MovieClickListener, UpdateMovieDialogFragment.UpdateMovieDialogListener{
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
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    //TODO:ne az összes filmet jelenítsók meg csak amiket kell
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

    //TODO:Csak a listáról tölrődjenek, tehát a joinból
    @Override
    public void onItemDeleted(final Movie movie) {
        new AsyncTask<Void, Void, Boolean>() {

            @Override
            protected Boolean doInBackground(Void... voids) {
                //dbContext.deleteMovie(movie);
                //TODO:csak a joinból törölje
                return true;
            }

            @Override
            protected void onPostExecute(Boolean isSuccessful) {
                Log.d("AllMoviesActivity", "Movie deleted");
            }
        }.execute();
    }



    @Override
    public void onItemChanged(final Movie movie) {
        new AsyncTask<Void, Void, Boolean>() {

            @Override
            protected Boolean doInBackground(Void... voids) {
                dbContext.updateMovie(movie);
                return true;
            }

            @Override
            protected void onPostExecute(Boolean isSuccessful) {
                Log.d("AllMoviesActivity", "Movie updated");
            }
        }.execute();
    }

    @Override
    public void onItemAddedToList(Movie movie, OwnList ownList) {

    }

    @Override
    public void onDataClicked(Movie movie) {
        Bundle bundle = new Bundle();

        new UpdateMovieDialogFragment().show(getSupportFragmentManager(), UpdateMovieDialogFragment.TAG);
    }

    @Override
    public void onMovieUpdated(Movie newMovie) {

    }
}
