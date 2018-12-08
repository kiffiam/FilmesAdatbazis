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
import hu.bme.aut.filmesadatbazis.data.Movie;
import hu.bme.aut.filmesadatbazis.data.MovieDatabase;

public class AllMovieActivity extends AppCompatActivity
implements MovieAdapter.MovieClickListener{

    private RecyclerView recyclerView;
    private MovieAdapter adapter;

    private MovieDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_movie);

            //TODO: ez lehet mainactivitíbe kell mertkell adatbézis már a hozzáadáshoz
        database = Room.databaseBuilder(
                getApplicationContext(), MovieDatabase.class, "movie-list"
        ).build();

        initRecyclerView();
    }

    private void initRecyclerView(){
        recyclerView = findViewById(R.id.AllMovieRecyclerView);
        adapter = new MovieAdapter(this);
        loadItemsInBackground();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    //ez biztos kell ide mert betölteni csak akkor töltjük be ha
    //ezt az activity-t megnyitjuk
    private void loadItemsInBackground(){
        new AsyncTask<Void, Void, List<Movie>>(){

            @Override
            protected List<Movie> doInBackground(Void... voids){
                return database.movieDao().getAll();
            }

            @Override
            protected void onPostExecute(List<Movie> movies) {
                adapter.update(movies);
            }
        }.execute();
    }


    //TODO:kitalálni hogy ezidekell e. Mert Itt nem lesz csak törlés
    @Override
    public void onItemChanged(final Movie movie) {
        /*new AsyncTask<Void, Void, Boolean>() {
            @Override
            protected Boolean doInBackground(Void... voids) {
                database.movieDao().update(movie);
                return true;
            }

            @Override
        protected void onPostExecute(Boolean isSuccessful) {
            Log.d("MainActivity", "Movie update was successful");
        }
        }.execute();*/
    }
}
