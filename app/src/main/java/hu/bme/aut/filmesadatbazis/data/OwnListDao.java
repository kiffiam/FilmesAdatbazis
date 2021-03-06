package hu.bme.aut.filmesadatbazis.data;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface OwnListDao {

    @Query("SELECT * FROM OwnList")
    List<OwnList> getAll();

    @Insert
    long insert(OwnList ownList);

    @Update
    void update(OwnList ownList);

    @Delete
    void delete(OwnList ownList);

}
