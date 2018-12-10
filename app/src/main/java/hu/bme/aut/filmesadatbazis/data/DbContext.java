package hu.bme.aut.filmesadatbazis.data;

import android.arch.persistence.room.Room;
import android.content.Context;

import java.util.List;

public class DbContext {

    private MovieDatabase database;

    public DbContext(Context applicationContext) {
        database = Room.databaseBuilder(
                applicationContext, MovieDatabase.class, "movie-list"
        ).fallbackToDestructiveMigration().build();
    }

    public long insertMovie(Movie newMovie) {
        return database.movieDao().insert(newMovie);
    }

    public List<Movie> getMovies() {
        return database.movieDao().getAll();
    }

    public void deleteMovie(Movie movie) {
        database.movieDao().deleteItem(movie);
    }

    public void updateMovie(Movie movie) {
        database.movieDao().update(movie);
    }

    public List<OwnList> getOwnLists() {
        return database.ownListDao().getAll();
    }

    public long insertOwnList(OwnList newOwnList) {
        return database.ownListDao().insert(newOwnList);
    }

    public void deleteOwnList(OwnList ownList) {
        database.ownListDao().delete(ownList);
    }

    /*public void insertMovieToList(long id, long id1) {
        database.ownListMovieJoinDao().insert(id,idl);
    }*/
}
