package hu.bme.aut.filmesadatbazis;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.List;

import hu.bme.aut.filmesadatbazis.adapter.MovieAdapter;
import hu.bme.aut.filmesadatbazis.adapter.OwnListAdapter;
import hu.bme.aut.filmesadatbazis.data.DbContext;
import hu.bme.aut.filmesadatbazis.data.Movie;
import hu.bme.aut.filmesadatbazis.data.MovieDatabase;
import hu.bme.aut.filmesadatbazis.data.OwnList;
import hu.bme.aut.filmesadatbazis.fragments.UpdateMovieDialogFragment;

public class AllOwnListActivity extends AppCompatActivity implements OwnListAdapter.OwnListClickListener {


    private RecyclerView recyclerView;
    private OwnListAdapter adapter;

    //private MovieDatabase database;
    private DbContext dbContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_own_list);


        dbContext = new DbContext(getApplicationContext());

        initRecyclerView();
    }

    private void initRecyclerView() {
        recyclerView = findViewById(R.id.AllOwnListRecyclerView);
        adapter = new OwnListAdapter(this);
        loadItemsInBackground();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    private void loadItemsInBackground(){
        new AsyncTask<Void, Void, List<OwnList>>(){

            @Override
            protected List<OwnList> doInBackground(Void... voids){
                return dbContext.getOwnLists();
            }

            @Override
            protected void onPostExecute(List<OwnList> lists) {
                adapter.update(lists);
            }
        }.execute();
    }


    @Override
    public void onDataClicked() {
        Intent intent = new Intent(AllOwnListActivity.this, DetailedListActivity.class);
        startActivity(intent);
    }

    @Override
    public void onItemDeleted(final OwnList ownList) {
        new AsyncTask<Void, Void, Boolean>() {

            @Override
            protected Boolean doInBackground(Void... voids) {
                dbContext.deleteOwnList(ownList);
                return true;
            }

            @Override
            protected void onPostExecute(Boolean isSuccessful) {
                Log.d("AllOwnListsActivity", "OwnList deleted");
            }
        }.execute();
    }
}

