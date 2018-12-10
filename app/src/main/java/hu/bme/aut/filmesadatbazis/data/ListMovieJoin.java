package hu.bme.aut.filmesadatbazis.data;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.support.annotation.ColorInt;


@Entity(tableName = "list_movie_join",
        primaryKeys = { "movieId", "ownListId" },
        foreignKeys = {
                @ForeignKey(entity = Movie.class, parentColumns = "id", childColumns = "movieId"),
                @ForeignKey(entity = OwnList.class, parentColumns = "id", childColumns = "ownListId")
        })
public class ListMovieJoin {

    @ColumnInfo(name = "movieId")
    public long movieId;

    @ColumnInfo(name = "ownListId")
    public long ownListId;
}
