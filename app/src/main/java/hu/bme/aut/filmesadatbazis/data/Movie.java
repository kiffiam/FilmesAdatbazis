package hu.bme.aut.filmesadatbazis.data;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverter;


@Entity(tableName = "movie")
public class Movie {

    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = true)
    public long id;

    @ColumnInfo(name = "title")
    public String title;

    @ColumnInfo(name = "point")
    public int point;

    @ColumnInfo(name = "opinion")
    public String opinion;

    @ColumnInfo(name = "genre")
    public Genre genre;

    /*@ColumnInfo(name = "watchDate")
    private Date watchDate;*/

    public Movie(String title, int point, String opinion, Genre genre) {
        this.title = title;
        this.point = point;
        this.opinion = opinion;
        this.genre = genre;
    }

    public Movie(){

    }

    public enum Genre {
        HORROR,
        THRILLER,
        COMEDY,
        ACTION,
        DRAMA,
        SCIFI,
        FANTASY,
        MUSICAL;

        @TypeConverter
        public static Genre getByOrdinal(int ordinal) {
            Genre ret = null;
            for (Genre gen : Genre.values()) {
                if (gen.ordinal() == ordinal) {
                    ret = gen;
                    break;
                }
            }
            return ret;
        }

        @TypeConverter
        public static int toInt(Genre genre) {
            return genre.ordinal();
        }
    }
}




