package hu.bme.aut.filmesadatbazis.data;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface ListMovieJoinDao {

    @Query("SELECT * FROM list_movie_join")
    List<ListMovieJoin> getAll();

    @Insert
    long insert(long id, long idl);

    @Update
    void update(ListMovieJoin listMovieJoin);

    @Delete
    void deleteItem(ListMovieJoin listMovieJoin);

}
