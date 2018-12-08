package hu.bme.aut.filmesadatbazis.data;


import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;


@Database(
        entities = {Movie.class},
        version = 1
)

@TypeConverters(value = {Movie.Genre.class})
public abstract class MovieDatabase extends RoomDatabase{
    public abstract MovieDao movieDao();
    //public abstract OwnListDao getOwnListDao();
    //public abstract ListMovieJoinDao getOwnListMovieJoinDao();
}
