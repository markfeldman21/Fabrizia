package com.markfeldman.fabrizia.data;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

public interface CocktailsDAO {
    //Data Access Object

    @Query("SELECT * FROM cocktails ORDER BY cocktailName")
    List<CocktailEntry> loadAllTasks();

    @Insert
    void insertTask(CocktailEntry cocktailEntry);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateTask(CocktailEntry cocktailEntry);

    @Delete
    void deleteTask(CocktailEntry cocktailEntry);
}
