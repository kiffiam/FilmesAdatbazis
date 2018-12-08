package hu.bme.aut.filmesadatbazis;

import android.app.ListActivity;
import android.arch.persistence.room.Room;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import hu.bme.aut.filmesadatbazis.adapter.MovieAdapter;
import hu.bme.aut.filmesadatbazis.adapter.OwnListAdapter;
import hu.bme.aut.filmesadatbazis.data.Movie;
import hu.bme.aut.filmesadatbazis.data.MovieDatabase;
import hu.bme.aut.filmesadatbazis.data.OwnList;
import hu.bme.aut.filmesadatbazis.fragments.NewMovieDialogFragment;
import hu.bme.aut.filmesadatbazis.fragments.NewOwnListDialogFragment;

public class MainActivity extends AppCompatActivity
        implements NewMovieDialogFragment.NewMovieDialogListener, MovieAdapter.MovieClickListener,
        NewOwnListDialogFragment.NewOwnListDialogListener, OwnListAdapter.OwnListClickListener{

    private MovieDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);

        database = Room.databaseBuilder(
                getApplicationContext(), MovieDatabase.class, "movie-list"
        ).build();

        //New movie button pops up a dialogfragment
        ImageButton btnNewMovieDialog = findViewById(R.id.btnNewMovie);
        btnNewMovieDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new NewMovieDialogFragment().show(getSupportFragmentManager(), NewMovieDialogFragment.TAG);
            }
        });

        //New List button pops up a dialogfragment
        ImageButton btnNewOwnListDialog = findViewById(R.id.btnNewList);
        btnNewOwnListDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new NewOwnListDialogFragment().show(getSupportFragmentManager(), NewOwnListDialogFragment.TAG);
            }
        });

        //All Movies activity start
        ImageButton btnAllMovieList = findViewById(R.id.btnMovies);
        btnAllMovieList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO:AllMoivesActivity indítása
                Intent intent = new Intent(MainActivity.this, AllMovieActivity.class);
                startActivity(intent);

            }
        });
    }

    @Override
    public void onItemChanged(Movie movie) {

    }

    @Override
    public void onMovieCreated(final Movie newMovie) {
        new AsyncTask<Void, Void, Boolean>() {

            @Override
            protected Boolean doInBackground(Void... voids) {
                newMovie.id = database.movieDao().insert(newMovie);
                return true;
            }

            @Override
            protected void onPostExecute(Boolean isSuccessful) {
                Log.d("MainActivity", "MovieItem insert was successful");
            }

        }.execute();
    }

    @Override
    public void onOwnListCreated(OwnList newItem) {

    }

    @Override
    public void onItemChanged(OwnList ownList) {

    }
}
