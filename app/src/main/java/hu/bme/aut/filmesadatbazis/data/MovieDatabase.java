package hu.bme.aut.filmesadatbazis.data;


import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;

import java.util.List;


@Database(
        entities = {Movie.class, OwnList.class, ListMovieJoin.class},
        version = 4
)

@TypeConverters(value = {Movie.Genre.class})
public abstract class MovieDatabase extends RoomDatabase{
    public abstract MovieDao movieDao();
    public abstract OwnListDao ownListDao();
    public abstract ListMovieJoinDao listMovieJoinDao();
}
