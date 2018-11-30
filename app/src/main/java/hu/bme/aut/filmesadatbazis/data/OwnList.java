package hu.bme.aut.filmesadatbazis.data;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "OwnList")
public class OwnList {

    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = true)
    public long id;

    @ColumnInfo(name = "name")
    public String name;

    public OwnList(long id, String name) {
        this.id = id;
        this.name = name;
    }
}
