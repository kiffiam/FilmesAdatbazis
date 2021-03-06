package hu.bme.aut.filmesadatbazis.data;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.ColorInt;

@Entity(tableName = "ownList")
public class OwnList {

    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = true)
    public long id;

    @ColumnInfo(name = "name")
    public String name;

    @ColumnInfo(name = "description")
    public String description;

    public OwnList(String name, String description) {
        this.name=name;
        this.description=description;
    }

    public OwnList(){

    }
}
