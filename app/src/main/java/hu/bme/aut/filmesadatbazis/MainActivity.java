package hu.bme.aut.filmesadatbazis;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import hu.bme.aut.filmesadatbazis.data.DbContext;
import hu.bme.aut.filmesadatbazis.data.Movie;
import hu.bme.aut.filmesadatbazis.data.OwnList;
import hu.bme.aut.filmesadatbazis.fragments.CreateMovieDialogFragment;
import hu.bme.aut.filmesadatbazis.fragments.NewOwnListDialogFragment;

public class MainActivity extends AppCompatActivity
        implements CreateMovieDialogFragment.NewMovieDialogListener,
        NewOwnListDialogFragment.NewOwnListDialogListener{

    private DbContext dbContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);

        dbContext = new DbContext(getApplicationContext());

        //New movie button pops up a dialogfragment
        ImageButton btnNewMovieDialog = findViewById(R.id.btnNewMovie);
        btnNewMovieDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new CreateMovieDialogFragment().show(getSupportFragmentManager(), CreateMovieDialogFragment.TAG);
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
                Intent intent = new Intent(MainActivity.this, AllMovieActivity.class);
                startActivity(intent);

            }
        });

        //Own Lists activity start
        ImageButton btnOwnLists = findViewById(R.id.btnOwnLists);
        btnOwnLists.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AllOwnListActivity.class);
                startActivity(intent);

            }
        });
    }

    @Override
    public void onMovieCreated(final Movie newMovie) {
        new AsyncTask<Void, Void, Boolean>() {

            @Override
            protected Boolean doInBackground(Void... voids) {
                newMovie.id =  dbContext.insertMovie(newMovie);
                return true;
            }

            @Override
            protected void onPostExecute(Boolean isSuccessful) {
                Log.d("MainActivity", "MovieItem insert was successful");
            }

        }.execute();
    }

    @Override
    public void onOwnListCreated(final OwnList newOwnList) {
        new AsyncTask<Void, Void, Boolean>() {

            @Override
            protected Boolean doInBackground(Void... voids) {
                newOwnList.id =  dbContext.insertOwnList(newOwnList);
                return true;
            }

            @Override
            protected void onPostExecute(Boolean isSuccessful) {
                Log.d("MainActivity", "MovieItem insert was successful");
            }

        }.execute();
    }


}
