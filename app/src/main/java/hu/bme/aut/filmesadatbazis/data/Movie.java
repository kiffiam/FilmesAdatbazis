package hu.bme.aut.filmesadatbazis.data;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity(tableName = "movie")
public class Movie {

    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = true)
    public long id;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "point")
    private int point;

    @ColumnInfo(name = "opinion")
    private String opinion;

    @ColumnInfo(name = "genre")
    private Genre genre;

    @ColumnInfo(name = "watchDate")
    private Date watchDate;

    public Movie(long id, String name, int point, String opinion, Genre genre, Date watchDate) {
        this.id = id;
        this.name = name;
        this.point = point;
        this.opinion = opinion;
        this.genre = genre;
        this.watchDate = watchDate;
    }

    public enum Genre {
        HORROR,
        THRILLER,
        COMEDY,
        ACTION,
        DRAMA,
        SCIFI,
        FANTASY,
        MUSICAL
    }

}


