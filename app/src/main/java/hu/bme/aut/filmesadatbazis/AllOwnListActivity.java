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
import hu.bme.aut.filmesadatbazis.fragments.UpdateOwnListDialogFragment;

public class AllOwnListActivity extends AppCompatActivity implements
        OwnListAdapter.OwnListClickListener, UpdateOwnListDialogFragment.UpdateOwnListDialogListener {


    private RecyclerView recyclerView;
    private OwnListAdapter adapter;

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


    //legyen ez a szerkesztés, és külön gomb a listában található filmek
    @Override
    public void onDataClicked(OwnList ownList) {
        Bundle bundle = new Bundle();
        bundle.putLong("id",ownList.id);
        bundle.putString("name", ownList.name);
        bundle.putString("description", ownList.description);


        UpdateOwnListDialogFragment updateOwnListDialogFragment = new UpdateOwnListDialogFragment();
        updateOwnListDialogFragment.setArguments(bundle);
        updateOwnListDialogFragment.show(getSupportFragmentManager(), UpdateOwnListDialogFragment.TAG);
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

    @Override
    public void onOwnListUpdated(final OwnList ownList) {
        new AsyncTask<Void, Void, Boolean>() {

            @Override
            protected Boolean doInBackground(Void... voids) {
                dbContext.updateOwnList(ownList);
                return true;
            }

            @Override
            protected void onPostExecute(Boolean isSuccessful) {
                loadItemsInBackground();
                Log.d("AllOwnListActivity", "OwnList updated");
            }
        }.execute();
    }
}

