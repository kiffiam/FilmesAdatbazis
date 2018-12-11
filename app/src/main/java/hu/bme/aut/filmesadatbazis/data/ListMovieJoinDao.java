package hu.bme.aut.filmesadatbazis.data;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface ListMovieJoinDao {

    @Insert
    void insert(ListMovieJoin listMovieJoin);

    @Query("SELECT * FROM movie INNER JOIN list_movie_join ON movie.id=list_movie_join.movieId WHERE list_movie_join.ownListId=:ownListId")
    List<Movie> getMoviesForList(final long ownListId);

    @Query("SELECT * FROM ownList INNER JOIN list_movie_join ON ownList.id=list_movie_join.ownListId WHERE list_movie_join.movieId=:movieId")
    List<OwnList> getOwnListForMovie(final long movieId);

    @Delete
    void deleteItem(ListMovieJoin listMovieJoin);

}
