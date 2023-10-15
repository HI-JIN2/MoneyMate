package com.ssu.moneymate.ui.main.fixed;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.ArrayList;
import java.util.List;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface FixedDao
{
    @Insert(onConflict = REPLACE)
    void insert(FixedData fixedData);

    @Query("SELECT * FROM table_name")
    List<FixedData> getAll();

    @Query("DELETE FROM table_name")
    void deleteAll();
}