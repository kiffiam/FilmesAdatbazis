package hu.bme.aut.filmesadatbazis.data;


import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;


@Database(
        entities = {Movie.class},
        version = 1
)

public abstract class MovieDatabase extends RoomDatabase{
    public abstract MovieDao getMovieDao();
    //public abstract OwnListDao getOwnListDao();
    //public abstract ListMovieJoinDao getOwnListMovieJoinDao();
}
